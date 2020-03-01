package org.avalon.lark.systemadmin.entity;

import org.avalon.lark.common.base.BasePo;

public class Config extends BasePo {

    public static final String TABLE_NAME = "SA_CONFIG";

    @Override
    public boolean needAuditColumns() {
        return false;
    }
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    private String configKey;
    private String configValue;
    private String description;
    private String isSystem;
    private String needRestart;

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(String isSystem) {
        this.isSystem = isSystem;
    }

    public String getNeedRestart() {
        return needRestart;
    }

    public void setNeedRestart(String needRestart) {
        this.needRestart = needRestart;
    }
}
