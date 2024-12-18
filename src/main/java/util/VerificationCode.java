package util;

import java.util.Random;

public class VerificationCode {
    public static int generateVerificationCode() {
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }
}
