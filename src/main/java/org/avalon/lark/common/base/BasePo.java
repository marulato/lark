package org.avalon.lark.common.base;

import java.util.Date;

public abstract class BasePo implements BaseBean {

    private static final long serialVersionUID = -4602595805392892083L;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;

    public abstract boolean needAuditColumns();

    public void setAudit(String createdBy, Date createdAt, String updatedBy, Date updatedAt) {
        setCreatedAt(createdAt);
        this.createdBy = createdBy;
        setUpdatedAt(updatedAt);
        this.updatedBy = updatedBy;
    }

    public void setAudit(String by, Date at) {
        setAudit(by, at, by, at);
    }

    public void setUpdateAudit(String  updatedBy, Date updatedAt) {
        this.updatedBy = updatedBy;
        setUpdatedAt(updatedAt);
    }

    public void setCreateAudit(String  createdBy, Date createdAt) {
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

}
