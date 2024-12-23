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

    public Bill getBillById(int id) {
        Bill bill = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("SELECT bills.id, bills.status FROM bills WHERE id = :id")
                        .bind("id", id)
                        .mapToBean(Bill.class)
                        .findOne()
                        .orElse(null) // Giả sử trả về null nếu không tìm thấy sản phẩm
        );
        return bill;
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
//        Bill bill = BillDAO.getInstance().getBillById(1);
//        System.out.println(bill);
//        changeInfoBill(1, "SHIPPING");
        List<Item> items = getBillDetailsById(109);
        System.out.println(items);
    }

}