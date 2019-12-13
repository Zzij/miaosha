package com.miaosha.controller;


import com.miaosha.controller.viewobject.ItemVo;
import com.miaosha.error.BusinessException;
import com.miaosha.response.CommonReturnType;
import com.miaosha.service.ItemService;
import com.miaosha.service.model.ItemModel;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller("item")
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ItemController extends BaseController{

    @Autowired
    private ItemService itemService;

    //創建商品
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonReturnType createItem(@RequestParam("title") String title,
                                       @RequestParam("description") String description,
                                       @RequestParam("price") BigDecimal price,
                                       @RequestParam("stock") Integer stock,
                                       @RequestParam("imgUrl") String imgUrl
                                       ) throws BusinessException {
        ItemModel itemModel = new ItemModel();
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setDescription(description);
        itemModel.setImgUrl(imgUrl);
        itemModel.setTitle(title);
        ItemModel itemModelReturn = itemService.createItem(itemModel);
        return CommonReturnType.create(convertModelToItemVO(itemModelReturn));
    }

    //商品详情界面
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType getItem(@RequestParam("id") Integer id){
        ItemModel itemModel = itemService.getItem(id);
        ItemVo itemVo = convertModelToItemVO(itemModel);
        return CommonReturnType.create(itemVo);
    }

    //商品浏览
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType getList(){
        List<ItemModel> itemModelList = itemService.listItem();
        List<ItemVo> itemVoList = itemModelList.stream().map(itemModel -> {
            return convertModelToItemVO(itemModel);
        }).collect(Collectors.toList());

        return CommonReturnType.create(itemVoList);
    }

    private ItemVo convertModelToItemVO(ItemModel itemModel){
        if(itemModel == null){
            return null;
        }
        ItemVo itemVo = new ItemVo();
        BeanUtils.copyProperties(itemModel, itemVo);
        if(itemModel.getPromoModel() != null){
            itemVo.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVo.setPromoId(itemModel.getPromoModel().getId());
            itemVo.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
            itemVo.setStartDate(itemModel.getPromoModel().getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        }else{
            itemVo.setPromoStatus(0);
        }
        return itemVo;
    }
}
