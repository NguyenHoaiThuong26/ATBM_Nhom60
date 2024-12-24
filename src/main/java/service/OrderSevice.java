package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OrderSevice {

    public static String hashOrder(String orderData) {
        try {
            // Sử dụng SHA-256 để tạo hash
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(orderData.getBytes());

            // Chuyển hash thành chuỗi Hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Thông tin đơn hàng (mô phỏng)
        String orderData = "OrderID: 12345; Product: Laptop; Price: 1500$; Buyer: John Doe";

        // Tạo hash
        String orderHash = hashOrder(orderData);
        System.out.println("Hash của đơn hàng: " + orderHash);
    }
}
