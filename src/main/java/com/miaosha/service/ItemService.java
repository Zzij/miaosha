package com.miaosha.service;

import com.miaosha.error.BusinessException;
import com.miaosha.service.model.ItemModel;

import java.util.List;

public interface ItemService {

    //创建商品
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    //商品列表浏览
    List<ItemModel> listItem();

    //商品详情浏览
    ItemModel getItem(Integer id);

    //减库存

    boolean decreaseStick(Integer itemId, Integer amount);

    //商品销量增加
    void increaseSales(Integer itemId, Integer amount);

    String sayHello();
}
