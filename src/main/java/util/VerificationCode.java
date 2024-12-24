package util;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Random;

public class VerificationCode {
    public static int generateVerificationCode() {
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }
    public static boolean verifyOrder(String orderHash, String signature, String publicKeyBase64) {
        try {
            // Chuyển PublicKey từ Base64 thành dạng Key
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));

            // Khởi tạo Signature với thuật toán SHA256withRSA
            Signature sig = Signature.getInstance("SHA256withRSA");
            sig.initVerify(publicKey);
            sig.update(orderHash.getBytes());

            // Kiểm tra chữ ký
            byte[] signatureBytes = Base64.getDecoder().decode(signature);
            return sig.verify(signatureBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
