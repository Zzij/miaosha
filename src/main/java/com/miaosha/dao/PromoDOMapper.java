package com.miaosha.dao;

import com.miaosha.dataobject.PromoDO;
import org.apache.ibatis.annotations.Param;

public interface PromoDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Tue Mar 12 21:43:20 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Tue Mar 12 21:43:20 CST 2019
     */
    int insert(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Tue Mar 12 21:43:20 CST 2019
     */
    int insertSelective(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Tue Mar 12 21:43:20 CST 2019
     */
    PromoDO selectByPrimaryKey(Integer id);


    PromoDO selectByItemId(@Param("itemId") Integer itemId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Tue Mar 12 21:43:20 CST 2019
     */
    int updateByPrimaryKeySelective(PromoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promo
     *
     * @mbg.generated Tue Mar 12 21:43:20 CST 2019
     */
    int updateByPrimaryKey(PromoDO record);
}