package dao;

import bean.Payment_Method;
import db.JDBIConnector;

public class PaymentDAO {
    public void savePayment(int billId, String paymentMethod, boolean isCashOnDelivery) {
        JDBIConnector.me().useHandle(handle ->
                handle.createUpdate("INSERT INTO payments (billId, method) VALUES (:billId, :method)")
                        .bind("billId", billId)
                        .bind("method", isCashOnDelivery ? 1 : 0)
                        .execute()
        );
    }

    public static void main(String[] args) {

    }
}