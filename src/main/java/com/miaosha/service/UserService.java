package com.miaosha.service;

import com.miaosha.error.BusinessException;
import com.miaosha.service.model.UserModel;

public interface UserService {
    //通过id查找用户
    UserModel getUserById(Integer id);

    void register(UserModel userModel) throws BusinessException;

    UserModel login(String phone, String encrptPassword) throws BusinessException;
}
