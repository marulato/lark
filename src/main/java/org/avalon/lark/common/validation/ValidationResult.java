package org.avalon.lark.common.validation;

import org.avalon.lark.common.utility.StringUtils;
import java.util.HashMap;
import java.util.Map;

public class ValidationResult {

    private boolean isValid;
    private Map<String, String> errorMsgMap;

    public ValidationResult() {
        errorMsgMap = new HashMap<>();
    }

    public void addError(String fieldName, String errorCode) {

    }

    public void addErrorMsg(String fieldName, String msg) {
        if (StringUtils.isNotBlank(fieldName) && StringUtils.isNotEmpty(msg)) {
            errorMsgMap.put(fieldName, msg);
        }
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }
}
