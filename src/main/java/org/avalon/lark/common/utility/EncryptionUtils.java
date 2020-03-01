package org.avalon.lark.common.utility;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionUtils {

    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String hashCode = cyclicHash(5, password);
        return encoder.encode(hashCode);
    }

    public static boolean matchPassword(String plain, String cipherPwd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String hashCode = cyclicHash(5, plain);
        return encoder.matches(hashCode, cipherPwd);
    }


    public static String cyclicHash(int loop, String plain) {
        String cipher = plain;
        for (int i = 0; i < loop; i++) {
            cipher = DigestUtils.sha512Hex(cipher);
        }
        return cipher;
    }
}
