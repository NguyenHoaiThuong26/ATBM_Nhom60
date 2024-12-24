package dao;

import bean.Order;
import bean.User;
import db.JDBIConnector;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Update;

import java.util.Optional;

public class OrderDAO {
    private static final Jdbi jdbi = JDBIConnector.me();

    // Lưu đơn hàng và chữ ký vào database
    public static void saveOrder(String orderHash, String signature, String publicKey) {
        String sql = "INSERT INTO orders (order_hash, signature, public_key) VALUES (:orderHash, :signature, :publicKey)";

        jdbi.useHandle(handle -> {
            Update update = handle.createUpdate(sql)
                    .bind("orderHash", orderHash)
                    .bind("signature", signature)
                    .bind("publicKey", publicKey);

            int rows = update.execute();
            if (rows > 0) {
                System.out.println("Đơn hàng đã được lưu thành công.");
            } else {
                System.out.println("Không thể lưu đơn hàng.");
            }
        });
    }

    // Lấy thông tin đơn hàng từ database (theo orderHash)
    public static Order getOrder(String orderHash) {
        String sql = "SELECT order_hash, signature, public_key FROM orders WHERE order_hash = :orderHash";

        return jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bind("orderHash", orderHash)
                        .map((rs, ctx) -> new Order(
                                rs.getString("order_hash"),
                                rs.getString("signature"),
                                rs.getString("public_key")
                        ))
                        .findOne()
                        .orElse(null)
        );
    }
    public static void checkAndCreateTable() {
        String createTableSQL = """
        CREATE TABLE IF NOT EXISTS orders (
            id INT AUTO_INCREMENT PRIMARY KEY,
            order_hash VARCHAR(255) NOT NULL UNIQUE,
            signature TEXT NOT NULL,
            public_key TEXT NOT NULL
        )
    """;

        jdbi.useHandle(handle -> handle.execute(createTableSQL));
    }

    public static void main(String[] args) {
        checkAndCreateTable();
        saveOrder(
                "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855",
                "MEUCIQDwRi2B...",
                "MIIBIjANBgkqh..."
        );
        Order order = OrderDAO.getOrder("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855");
        if (order != null) {
            System.out.println("Đơn hàng: " + order);
        } else {
            System.out.println("Không tìm thấy đơn hàng.");
        }
    }


}
