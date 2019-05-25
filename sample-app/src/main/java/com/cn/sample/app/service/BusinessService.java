package com.cn.sample.app.service;

import cn.hutool.core.util.IdUtil;
import com.cn.sample.api.enums.OrderStatusEnum;
import com.cn.sample.api.model.po.Order;
import com.cn.sample.api.model.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/5/24.
 */
public class BusinessService {

    @Autowired
    private IOrderService orderService;

    @GlobalTransactional
    public void paySuccess(){
        BigDecimal money = new BigDecimal(0.98);
        Order order = new Order();
        order.setOrderId(IdUtil.simpleUUID());
//        order.setAccountId("1");
//        order.setMoney(money);
//        order.setStatus(OrderStatusEnum.WAIT.getValue());
//        order.setCreateTime(LocalDateTime.now());
//        order.setUpdateTime(LocalDateTime.now());
//        orderService.insertSelective(order);

        orderService.prepare(null);
    }
}
