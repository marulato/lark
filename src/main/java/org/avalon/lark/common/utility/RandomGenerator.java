package org.avalon.lark.common.utility;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class RandomGenerator {

    private static final char[] UPPERCASE = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private static final char[] LOWERCASE = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private static final char[] NUMBER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static final char[] CHARACTER = {'~', '!', '@', '#', '$', '%', '^', '&', '*','-', '_', '+',
            '=', '?', '/', '<', '>', ',', '.'};

    private static SecureRandom secureRandom;

    static {
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException e) {
            secureRandom = new SecureRandom();
        }
    }

    public static String generateTokenSlash(int length) {
        char[] pool = ArrayUtils.joint(UPPERCASE, NUMBER);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int next = secureRandom.nextInt(pool.length);
            builder.append(pool[next]);
            if ((i + 1)  % 4 == 0)
                builder.append("-");
        }
        if (builder.lastIndexOf("-") == builder.length() - 1)
            builder.deleteCharAt(builder.lastIndexOf("-"));
        return builder.toString();
    }

    public static String generateTokenNoSlash(int length) {
        char[] pool = ArrayUtils.joint(UPPERCASE, NUMBER, LOWERCASE);
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < length; i ++) {
            int next = secureRandom.nextInt(pool.length);
            token.append(pool[next]);
        }
        return token.toString();
    }

    public static String randomString(int length) {
        char[] pool = ArrayUtils.joint(UPPERCASE, LOWERCASE, NUMBER, CHARACTER);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i ++) {
            int next = secureRandom.nextInt(pool.length);
            builder.append(pool[next]);
        }
        return builder.toString();
    }
}
