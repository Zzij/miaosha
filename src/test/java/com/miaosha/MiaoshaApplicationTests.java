package com.miaosha;

import com.miaosha.dao.ItemDOMapper;
import com.miaosha.dataobject.ItemDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MiaoshaApplicationTests {

    @Autowired
    private ItemDOMapper itemDOMapper;


    @Test
    public void contextLoads() {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(2);
        System.out.println(itemDO);
    }

}

