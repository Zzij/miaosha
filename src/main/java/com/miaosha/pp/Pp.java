package com.miaosha.pp;

import com.miaosha.service.ItemService;
import com.miaosha.util.SpringUtils;
import org.springframework.boot.web.servlet.context.WebApplicationContextServletContextAwareProcessor;

public class Pp {

    public String sayHello(){

        ItemService bean = SpringUtils.getContext().getBean(ItemService.class);
        return bean.sayHello();

    }
}
