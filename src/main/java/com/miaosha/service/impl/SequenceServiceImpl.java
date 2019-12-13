package com.miaosha.service.impl;

import com.miaosha.dao.SequenceDOMapper;
import com.miaosha.dataobject.SequenceDO;
import com.miaosha.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SequenceServiceImpl implements SequenceService {

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    //注解的意思是 即使外层的事务 回滚了但是这个方法不会滚   保证唯一性
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String generateOrderNo() {

        //订单号16位信息
        StringBuilder sb = new StringBuilder();
        //前8位时间信息
        LocalDateTime now = LocalDateTime.now();
        sb.append(now.format(DateTimeFormatter.ISO_DATE).replace("-",""));
        //中间6位为自增序列
        int sequence = 0;
        SequenceDO sequenceDO = sequenceDOMapper.getSequenceByName("order_info");
        sequence = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        String sequenceStr = String.valueOf(sequence);
        for (int i = 0; i < 6-sequenceStr.length(); i++) {
            sb.append(0);
        }
        sb.append(sequenceStr);
        //最后2位为分库分表
        sb.append("00");
        return sb.toString();

    }
}
