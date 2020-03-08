package org.avalon.lark.common.validation;

import org.avalon.lark.common.utility.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {

    private boolean valid;
    private String code;
    private String msg;
    private Map<String, String> errorMsgMap;

    public ValidationResult() {
        errorMsgMap = new HashMap<>();
    }

    public void addError(String fieldName, String errorCode) {

    }

    public void addErrorMsg(String fieldName, String msg) {
        if (StringUtils.isNotBlank(fieldName) && StringUtils.isNotEmpty(msg)) {
            valid = false;
            errorMsgMap.put(fieldName, msg);
        }
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
