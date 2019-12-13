package com.miaosha.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
    //校验是否有错
    private boolean hasError;


    //错误信息map
    private Map<String, String> errorMsgMap;

    public ValidationResult() {
        hasError = false;
        errorMsgMap = new HashMap<>();
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }


    public String getErrorMsg(){
        return StringUtils.join(errorMsgMap.values(), ",");
    }
}
