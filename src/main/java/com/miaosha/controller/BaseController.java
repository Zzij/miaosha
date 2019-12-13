package com.miaosha.controller;

import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public static final String CONTENT_TYPE_FORM="application/x-www-form-urlencoded";

    //定义exceptionhandler解决未被controller层吸收的exception  返回的本地目录下的页面
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object exceptionHandler(HttpServletRequest req, Exception e){
        Map<String, Object> responseData = new HashMap<>();
        if(e instanceof BusinessException){
            BusinessException businessException = (BusinessException) e;
            responseData.put("ErrCode", businessException.getErrCode());
            responseData.put("ErrMsg", businessException.getErrMsg());
        }else{
            responseData.put("ErrCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("ErrMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }
        return CommonReturnType.create(responseData, "fail");
    }
}
