package utilities;

import java.util.Random;

public class RandomDataUtil {

    public static String randomString(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String randomNumber(int length) {
        String digits = "0123456789";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < length; i++) {
            sb.append(digits.charAt(rnd.nextInt(digits.length())));
        }
        return sb.toString();
    }
}
