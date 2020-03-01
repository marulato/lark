package org.avalon.lark.common.servlet;

import javax.servlet.http.HttpServletRequest;

public class ServletRequestReference {

    private static final ThreadLocal ref = new ThreadLocal();

    public static void setRequest(HttpServletRequest req) {
        ref.remove();
        ref.set(req);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) ref.get();
    }
}
