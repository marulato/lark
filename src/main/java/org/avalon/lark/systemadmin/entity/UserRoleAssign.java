package org.avalon.lark.systemadmin.entity;

import org.avalon.lark.common.base.BasePo;
import java.util.Date;

public class UserRoleAssign extends BasePo {

    public static final String TABLE_NAME       = "SA_USER_ROLE_ASSIGN";
    public static final String AUDIT_TABLE_NAME = "H_SA_USER_ROLE_ASSIGN";
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }
    @Override
    public boolean needAuditColumns() {
        return false;
    }

    private long userId;
    private String roleId;
    private String remarks;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
