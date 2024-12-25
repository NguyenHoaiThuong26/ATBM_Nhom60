package dao;

import bean.*;
import db.JDBIConnector;
import org.jdbi.v3.core.Handle;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class BillDAO {
    private static BillDAO instance;

    public static BillDAO getInstance() {
        if (instance == null) instance = new BillDAO();
        return instance;
    }

    private BillDAO() {
    }

    public static List<Item> getBillDetailsById(int billId) {
        return JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT bd.id, bd.productId, bd.quantity, bd.product_color, bd.total_price, pd.name AS product_name, pd.totalPrice AS product_price FROM bill_details bd JOIN product_details pd ON bd.productId = pd.id WHERE bd.billId = :billId")
                        .bind("billId", billId)
                        .map((rs, ctx) -> {
                            int id = rs.getInt("id");
                            int productId = rs.getInt("productId");
                            int quantity = rs.getInt("quantity");
                            String colorName = rs.getString("product_color");
                            double totalPrice = rs.getDouble("total_price");
                            String productName = rs.getString("product_name");
                            double productPrice = rs.getDouble("product_price");

                            // Tạo đối tượng Product
                            Product product = new Product(productId, productName, productPrice);

                            // Tạo đối tượng Item
                            Item item = new Item(product, quantity, colorName);
                            item.setId(id);
                            item.setPrice(totalPrice);

                            return item;
                        })
                        .list()
        );
    }

    public static List<Bill> getBillsByUser(User user) {
        try (Handle handle = JDBIConnector.me().open()) {
            return handle.createQuery("SELECT * FROM bills WHERE userId = :userId")
                    .bind("userId", user.getId())
                    .mapToBean(Bill.class)
                    .list();
        }
    }


    public List<Bill> getBillList() {
        return JDBIConnector.me().withHandle(handle ->
                handle.createQuery("select * from bills")
                        .mapToBean(Bill.class)
                        .collect(Collectors.toList())
        );
    }


    public static Bill adminViewBill(int id) {
        Bill bill = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT * FROM bills WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Bill.class)
                        .findOne()
                        .orElse(null) // Giả sử trả về null nếu không tìm thấy hóa đơn
        );
        return bill;
    }


    public void createOrder(String name, String phone, String address, String payment, HttpServletRequest req) {
        var cart = (List<Item>)req.getSession().getAttribute("cart");
        var user = req.getSession().getAttribute("auth");
        if (cart != null && user != null) {
            var total = 0.0;
            for (var item : cart) {
                total += item.getPrice();
            }
            var bill = new Bill((User) user, name, phone, address, total, payment);

            JDBIConnector.me().useHandle(handle -> {
                long billId = handle.createUpdate("INSERT INTO bills (userId, full_name, phone, address, totalPrice, payment_method) VALUES (:userId, :name, :phone, :address, :total, :payment)")
                        .bind("userId", bill.getUser().getId())
                        .bind("name", bill.getFullName())
                        .bind("phone", bill.getPhone())
                        .bind("address", bill.getAddress())
                        .bind("total", bill.getTotalPrice())
                        .bind("payment", bill.getPaymentMethod())
                        .executeAndReturnGeneratedKeys()
                        .mapTo(Long.class)
                        .findOnly();
                for (var item : cart) {
                    handle.createUpdate("INSERT INTO bill_details (billId, productId, quantity, total_price, product_color) VALUES (:billId, :productId, :quantity, :price, :color)")
                            .bind("billId", billId)
                            .bind("productId", item.getProduct().getId())
                            .bind("quantity", item.getQuantity())
                            .bind("price", item.getPrice())
                            .bind("color", item.getColorName())
                            .execute();
                }
            });
        }
    }

    public static int getLastestBill() {
        Integer billId = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT id FROM bills ORDER BY id DESC LIMIT 1")
                        .mapTo(Integer.class)
                        .findOne()
                        .orElse(null) // Giả sử trả về null nếu không tìm thấy hóa đơn
        );
        return billId != null ? billId : -1; // Trả về -1 nếu không có bill nào
    }

    public static String getBillAndBillDetailsToHash(int billId) {
        StringBuilder dataToHash = new StringBuilder();
        JDBIConnector.me().useHandle(handle -> {
            // Truy vấn dữ liệu từ bảng bills và bill_details
            List<String> results = handle.createQuery("SELECT b.id AS bill_id, b.userId, b.full_name, b.phone, b.address, b.totalPrice, b.payment_method, b.createDate, bd.productId, bd.quantity, bd.total_price, bd.product_color, p.name FROM bills b JOIN bill_details bd ON b.id = bd.billId JOIN product_details p ON bd.productId = p.id WHERE b.id = :billId")
                    .bind("billId", billId)
                    .map((rs, ctx) -> {
                        // Định dạng dữ liệu
                        String createDateFormatted = rs.getTimestamp("createDate").toLocalDateTime().toString(); // ISO-8601 format
                        String totalPriceFormatted = String.format("%.2f", rs.getDouble("totalPrice"));
                        String itemTotalPriceFormatted = String.format("%.2f", rs.getDouble("total_price"));
                        String productColor = rs.getString("product_color") != null ? rs.getString("product_color") : ""; // Thay null bằng ""

                        // Kết hợp dữ liệu từng dòng thành chuỗi
                        return new StringBuilder()
                                .append(rs.getLong("bill_id")).append("|")
                                .append(rs.getInt("userId")).append("|")
                                .append(rs.getString("full_name") != null ? rs.getString("full_name") : "").append("|")
                                .append(rs.getString("phone") != null ? rs.getString("phone") : "").append("|")
                                .append(rs.getString("address") != null ? rs.getString("address") : "").append("|")
                                .append(totalPriceFormatted).append("|")
                                .append(rs.getString("payment_method") != null ? rs.getString("payment_method") : "").append("|")
                                .append(createDateFormatted).append("|")
                                .append(rs.getInt("productId")).append("|")
                                .append(rs.getString("name")).append("|")
                                .append(rs.getInt("quantity")).append("|")
                                .append(itemTotalPriceFormatted).append("|")
                                .append(productColor)
                                .toString();
                    }).list();

            // Kết hợp tất cả các dòng lại thành một chuỗi duy nhất
            for (String row : results) {
                dataToHash.append(row).append("\n"); // Thêm xuống dòng để tách từng dòng dữ liệu
            }
        });

        return dataToHash.toString().trim(); // Trả về chuỗi kết quả để hash
    }

    public static void saveHashCode(String hashedBill, int billId) {
        JDBIConnector.me().useHandle(handle -> {
            handle.createUpdate("UPDATE bills SET hash_bill = :hash WHERE id = :billId")
                    .bind("hash", hashedBill)
                    .bind("billId", billId)
                    .execute();
        });
    }

    public static void saveSignature(String signature, int billId) {
        JDBIConnector.me().useHandle(handle -> {
            handle.createUpdate("UPDATE bills SET signature = :signature WHERE id = :billId")
                    .bind("signature", signature)
                    .bind("billId", billId)
                    .execute();
        });
    }

    public static String getHashCodeByBillId(int billId) {
        return JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT hash_bill FROM bills WHERE id = :billId")
                        .bind("billId", billId)
                        .mapTo(String.class)
                        .findOne()
                        .orElse(null) // Trả về null nếu không tìm thấy kết quả
        );
    }


    public static String getSignatureByBillId(int billId) {
        return JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT signature FROM bills WHERE id = :billId")
                        .bind("billId", billId)
                        .mapTo(String.class)
                        .findOne()
                        .orElse(null) // Trả về null nếu không tìm thấy kết quả
        );
    }




    public Bill getBillById(int id) {
        Bill bill = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT bills.id, bills.status, bills.verified_status FROM bills WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Bill.class)
                        .findOne()
                        .orElse(null) // Giả sử trả về null nếu không tìm thấy sản phẩm
        );
        return bill;
    }

    public static void changeStatusByBillId(int billId, String status, String verifiedStatus) {
        JDBIConnector.me().useHandle(handle ->
                handle.createUpdate("UPDATE bills SET status = :status, verified_status = :verifiedStatus WHERE id = :billId")
                        .bind("status", status)
                        .bind("verifiedStatus", verifiedStatus)
                        .bind("billId", billId)
                        .execute()
        );
    }



    public static void changeInfoBill(int id, String status) {
        JDBIConnector.me().withHandle(handle ->
                handle.createUpdate("UPDATE bills set " +
                                "status = :status" +
                                " where id = :id")
                        .bind("id", id)
                        .bind("status", status)
                        .execute()
        );
        System.out.println("DOne");
    }


    public static void main(String[] args) {
        Bill bill = BillDAO.getInstance().getBillById(110);
        System.out.println(bill);
//        changeInfoBill(1, "SHIPPING");
//        String bill = getBillAndBillDetailsToHash(112);
//        int billId = getLastestBill();
//        System.out.println(bill);

    }

}