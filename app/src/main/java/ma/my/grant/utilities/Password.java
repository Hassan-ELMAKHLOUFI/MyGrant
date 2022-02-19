package ma.my.grant.utilities;

import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Password {
    public static String hashPassword(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : array) sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String generateRandomPassword(int max_length, boolean upperCase, boolean lowerCase, boolean numbers, boolean specialCharacters) {
        String upperCaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseChars = upperCaseChars.toLowerCase();
        String numberChars = "0123456789";
        String specialChars = "!@#$%^&*[]()_-+=<>?/{}~|";
        String allowedChars = "";

        Random rn;
        rn = new Random();
        StringBuilder sb = new StringBuilder(max_length);
        if (upperCase) {
            allowedChars += upperCaseChars;
            sb.append(upperCaseChars.charAt(rn.nextInt(upperCaseChars.length() - 1)));
        }

        if (lowerCase) {
            allowedChars += lowerCaseChars;
            sb.append(lowerCaseChars.charAt(rn.nextInt(lowerCaseChars.length() - 1)));
        }

        if (numbers) {
            allowedChars += numberChars;
            sb.append(numberChars.charAt(rn.nextInt(numberChars.length() - 1)));
        }

        if (specialCharacters) {
            allowedChars += specialChars;
            sb.append(specialChars.charAt(rn.nextInt(specialChars.length() - 1)));
        }

        for (int i = sb.length(); i < max_length; ++i) {
            sb.append(allowedChars.charAt(rn.nextInt(allowedChars.length())));
        }

        return sb.toString();
    }

}
