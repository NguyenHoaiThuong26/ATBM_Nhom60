package util;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    public static final String MD5 = "MD5";
    public static final String SHA_1 = "SHA-1";
    public static final String SHA_256 = "SHA-256";
    public static final String SHA_512 = "SHA-512";

    public static String hashText(String inputText, String algorithm) throws NoSuchAlgorithmException {
        if (inputText == null || inputText.isEmpty()) {
            throw new IllegalArgumentException("Input text cannot be null or empty");
        }

        if (algorithm == null || algorithm.isEmpty()) {
            throw new IllegalArgumentException("Algorithm cannot be null or empty");
        }

        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] messageDigest = md.digest(inputText.getBytes(StandardCharsets.UTF_8));

        BigInteger number = new BigInteger(1, messageDigest);

        return number.toString(16);
    }

    public static void main(String[] args) throws Exception {
        System.out.println(hashText("hello my friend", HashUtil.SHA_256));
//      5d575bc10fbfbf62435849d4f8d0382a5acc939e9f92492d87e70852655d8be9
    }

}
