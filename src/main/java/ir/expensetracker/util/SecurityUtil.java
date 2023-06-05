package ir.expensetracker.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SecurityUtil {
    private static Logger logger = LogManager.getLogger(SecurityUtil.class);
    private static Random random = new Random();

    public static String generateRandomPassword() {
        return String.valueOf(random.nextInt(999999 - 100000 + 1) + 100000);
    }

    public static String SHA256(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e,e);
        }
        return null;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder result = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                result.append('0');
            }
            result.append(hex);
        }
        return result.toString();
    }
}
