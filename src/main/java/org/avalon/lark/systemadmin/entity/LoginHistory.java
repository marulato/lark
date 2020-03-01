package org.avalon.lark.systemadmin.entity;

import org.avalon.lark.common.base.BasePo;
import java.util.Date;

public class LoginHistory extends BasePo {

    public static final String TABLE_NAME = "USER_LOGIN_HISTORY";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
    @Override
    public boolean needAuditColumns() {
        return true;
    }
    private long userId;
    private String loginId;
    private Date loginAttemptAt;
    private String isSuccessful;
    private String client;
    private String ip;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Date getLoginAttemptAt() {
        return loginAttemptAt;
    }

    public void setLoginAttemptAt(Date loginAttemptAt) {
        this.loginAttemptAt = loginAttemptAt;
    }

    public String getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(String isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
