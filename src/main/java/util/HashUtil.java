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

    // Method to hash input text with a specified algorithm
    public String hashText(String inputText, String algorithm) throws NoSuchAlgorithmException {
        if (inputText == null || inputText.isEmpty()) {
            throw new IllegalArgumentException("Input text cannot be null or empty");
        }

        if (algorithm == null || algorithm.isEmpty()) {
            throw new IllegalArgumentException("Algorithm cannot be null or empty");
        }

        // Ensure the algorithm is one of the supported types
        if (!(MD5.equals(algorithm) || SHA_1.equals(algorithm) || SHA_256.equals(algorithm) || SHA_512.equals(algorithm))) {
            throw new IllegalArgumentException("Unsupported algorithm: " + algorithm);
        }

        MessageDigest md = MessageDigest.getInstance(algorithm);
        byte[] messageDigest = md.digest(inputText.getBytes(StandardCharsets.UTF_8));

        // Convert the byte array to a hexadecimal string
        BigInteger number = new BigInteger(1, messageDigest);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Ensure the output is always of the expected length by padding with leading zeros if needed
        while (hexString.length() < md.getDigestLength() * 2) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public static void main(String[] args) throws Exception {
        // Example usage of HashUtil
        HashUtil hashUtil = new HashUtil();

        // Test with different algorithms
        String input = "TestString";

        System.out.println("MD5: " + hashUtil.hashText(input, MD5));
        System.out.println("SHA-1: " + hashUtil.hashText(input, SHA_1));
        System.out.println("SHA-256: " + hashUtil.hashText(input, SHA_256));
        System.out.println("SHA-512: " + hashUtil.hashText(input, SHA_512));
    }
}
