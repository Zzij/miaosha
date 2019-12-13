package com.miaosha.controller;

import com.alibaba.druid.util.StringUtils;
import com.miaosha.controller.viewobject.UserVO;
import com.miaosha.error.BusinessException;
import com.miaosha.error.EmBusinessError;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.UserService;
import com.miaosha.service.model.UserModel;
import com.miaosha.validator.ValidatorImpl;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;


    //登录接口
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FORM})
    @ResponseBody
    public CommonReturnType login(@RequestParam("phone") String phone,
                                  @RequestParam("password") String password) throws Exception {
        if(org.apache.commons.lang3.StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
        UserModel userModel = userService.login(phone, this.encodeByMD5(password));
        //将登录凭证加入到登录成功的session中
        this.request.getSession().setAttribute("IS_LOGIN", true);
        this.request.getSession().setAttribute("LOGIN_USER", userModel);
        return CommonReturnType.create(null);
    }
    //注册接口
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FORM})
    @ResponseBody
    public CommonReturnType register(@RequestParam("phone") String phone,
                                     @RequestParam("optCode") String optCode,
                                     @RequestParam("name") String name,
                                     @RequestParam("gender") Integer gender,
                                     @RequestParam("age") Integer age,
                                     @RequestParam("password") String password) throws Exception {
        //验证手机号和optcode
        String sessionOptCode = (String) request.getSession().getAttribute(phone);
        System.out.println(sessionOptCode);
        if(!StringUtils.equals(optCode, sessionOptCode)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "短信验证码不正确");
        }
        //用户注册流程
        UserModel userModel = new UserModel();
        userModel.setAge(age);
        userModel.setName(name);
        userModel.setTelephone(phone);
        userModel.setGender(gender);
        userModel.setRegisterMode("ByPhone");
        userModel.setEncrptPassword(encodeByMD5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }


    //opt手机短信验证码
    @RequestMapping(value = "/getOpt", method = RequestMethod.POST, consumes = {CONTENT_TYPE_FORM})
    @ResponseBody
    public CommonReturnType getOpt(@RequestParam("phone") String phone){
        //按照一定规则生成opt
        Random random = new Random();
        int nextInt = random.nextInt(99999);
        nextInt += 100000;
        String optCode = String.valueOf(nextInt);

        //将opt验证码同对应用户手机关联,使用httpsession存储，企业中采用redis存储phone-optCode
        request.getSession().setAttribute(phone, optCode);

        //将opt验证码通过短信通道发送给用户
        System.out.println("telePhone=" + phone + " &optCode=" + optCode);
        return CommonReturnType.create(null);
    }


    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id") Integer id) throws BusinessException {
        //将核心领域模型的用户对象转化为可供UI使用的viewobject
        UserModel userModel = userService.getUserById(id);
        if(userModel == null){
            //throw new BusinessException(EmBusinessError.USER_NOT_EXDIST);
            userModel.setEncrptPassword("123");
        }
        UserVO userVO = getUserVO(userModel);
        return CommonReturnType.create(userVO);
    }

    private UserVO getUserVO(UserModel userModel){
        UserVO userVO = new UserVO();
        if(userModel == null){
            return null;
        }
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }

    public String encodeByMD5(String str) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String newStr = base64Encoder.encode(messageDigest.digest(str.getBytes("utf8")));
        return newStr;
    }

}
