package com.miaosha.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemModel {
    private Integer id;
    //商品名称
    @NotNull(message = "商品名称不为空")
    private String title;
    //价格
    @NotNull(message = "商品价格不为空")
    @Min(value = 0, message = "价格不能小于0")
    private BigDecimal price;
    //库存
    @NotNull(message = "商品库存不为空")
    private Integer stock;
    //描述
    @NotNull(message = "商品描述不为空")
    private String description;
    //销量
    private Integer sales;
    //图片地址
    @NotNull(message = "商品图片不为空")
    private String imgUrl;

    //聚合模型  如果不为空表示有秒杀活动 否则没有
    private PromoModel promoModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public PromoModel getPromoModel() {
        return promoModel;
    }

    public void setPromoModel(PromoModel promoModel) {
        this.promoModel = promoModel;
    }
}
