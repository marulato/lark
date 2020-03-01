package org.avalon.lark.systemadmin.entity;

import org.avalon.lark.common.base.BasePo;

public class UnknownLoginHistory extends BasePo {
    @Override
    public boolean needAuditColumns() {
        return true;
    }

    @Override
    public String getTableName() {
        return null;
    }
}
