package org.avalon.lark.common.base;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.avalon.lark.common.exception.IllegalConversionException;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseDto implements Serializable, Cloneable {

    private static final long serialVersionUID = -1500594387608518741L;

    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    private String createdAtStr;
    private String updatedAtStr;
    private String createdAtFullStr;
    private String updatedAtFullStr;

    public BaseDto(BasePo po) {
        if (po != null) {
            BeanUtils.copyProperties(po, this);
        }
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

    public String getCreatedAtStr() {
        return createdAtStr;
    }

    public void setCreatedAtStr(String createdAtStr) {
        this.createdAtStr = createdAtStr;
    }

    public String getUpdatedAtStr() {
        return updatedAtStr;
    }

    public void setUpdatedAtStr(String updatedAtStr) {
        this.updatedAtStr = updatedAtStr;
    }

    public String getCreatedAtFullStr() {
        return createdAtFullStr;
    }

    public void setCreatedAtFullStr(String createdAtFullStr) {
        this.createdAtFullStr = createdAtFullStr;
    }

    public String getUpdatedAtFullStr() {
        return updatedAtFullStr;
    }

    public void setUpdatedAtFullStr(String updatedAtFullStr) {
        this.updatedAtFullStr = updatedAtFullStr;
    }
}
