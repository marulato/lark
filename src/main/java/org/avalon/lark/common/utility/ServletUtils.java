package org.avalon.lark.common.utility;

import org.avalon.lark.common.servlet.ServletRequestReference;
import javax.servlet.http.HttpServletRequest;

public class ServletUtils {

    public static final HttpServletRequest getCurrentHttpServletRequest() {
        return ServletRequestReference.getRequest();
    }
}
