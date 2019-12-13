package com.miaosha.controller;


import com.miaosha.dao.UserDOMapper;
import com.miaosha.dataobject.UserDO;
import com.miaosha.pp.Pp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping("/")
    public String hello(){

        UserDO userDO = userDOMapper.selectByPrimaryKey(1);
        if(userDO == null){
            return "用户为空";
        }
        return userDO.toString();
    }

    @RequestMapping("/test")
    public String sayhello(){

        Pp p = new Pp();
        return p.sayHello();
    }
}
