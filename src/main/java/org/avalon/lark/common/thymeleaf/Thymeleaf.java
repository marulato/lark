package org.avalon.lark.common.thymeleaf;

import org.apache.commons.text.StringEscapeUtils;
import org.avalon.lark.common.consts.AppConsts;
import org.avalon.lark.common.utility.EncryptionUtils;

public class Thymeleaf {

    public static String encrypt(String plainText) throws Exception {
        return EncryptionUtils.encryptAES(plainText, AppConsts.WEB_ENC_KEY, true);
    }

    public static String escape(String s) {
        return StringEscapeUtils.escapeHtml4(s);
    }
}
