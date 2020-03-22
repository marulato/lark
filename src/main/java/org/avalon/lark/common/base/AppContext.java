package org.avalon.lark.common.base;

import org.avalon.lark.systemadmin.entity.UserRoleAssign;

import java.util.Date;
import java.util.List;

public class AppContext {

    public static final ThreadLocal<AppContext> threadContext = new ThreadLocal<AppContext>();
    private String loginId;
    private List<UserRoleAssign> roles;
    private Date loginDatetime;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public List<UserRoleAssign> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleAssign> roles) {
        this.roles = roles;
    }

    public Date getLoginDatetime() {
        return loginDatetime;
    }

    public void setLoginDatetime(Date loginDatetime) {
        this.loginDatetime = loginDatetime;
    }
}

