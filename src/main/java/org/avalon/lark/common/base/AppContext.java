package org.avalon.lark.common.base;

public class AppContext {

    public static final ThreadLocal<AppContext> threadContext = new ThreadLocal<AppContext>();
    private String loginId;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
