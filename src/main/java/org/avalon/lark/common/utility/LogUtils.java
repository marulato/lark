package org.avalon.lark.common.utility;

public class LogUtils {

    public static String printLine(int length, String character) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(character);
        }
        return builder.toString();
    }
}
