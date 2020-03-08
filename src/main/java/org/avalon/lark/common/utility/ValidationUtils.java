package org.avalon.lark.common.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^\\\\s*\\\\w+(?:\\\\.{0,1}[\\\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\\\.[a-zA-Z]+\\\\s*$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidUsername(String username) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9_]{6,20}");
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("[^ ]{6,24}");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
