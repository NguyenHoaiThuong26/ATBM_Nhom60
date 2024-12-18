package util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Encode {
    public static String toSHA1(String input) {

        String salt = "axrbumalqdsoa;aks,qke";
        String output = null;

        input = input + salt;

        try {

            byte[] dataBytes = input.getBytes(StandardCharsets.UTF_8);
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            output = Base64.encodeBase64String(md.digest(dataBytes));

        } catch (Exception e) {
            System.out.println("error");
        }

        return output;
    }

    public static void main(String[] args) {
        Encode encodeInstance = new Encode();
        System.out.println(toSHA1("123123"));
    }
}
