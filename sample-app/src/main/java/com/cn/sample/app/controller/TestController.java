package com.cn.sample.app.controller;

import cn.hutool.core.util.IdUtil;
import com.cn.sample.api.enums.OrderStatusEnum;
import com.cn.sample.api.model.po.Order;
import com.cn.sample.api.model.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>消息管理制器</p>
 *
 * @author Chen Nan
 * @date 2019/3/11.
 */
@RestController
@Api(tags = "测试", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(value = "/test")
@Slf4j
public class TestController {

    @Reference
    private IOrderService orderService;

    @ApiOperation("测试业务，无TCC")
    @GetMapping
    public Object testNormal() {
        log.info("【test】测试业务");

        BigDecimal money = new BigDecimal(0.98);
        Order order = new Order();
        order.setOrderId(IdUtil.simpleUUID());
        order.setAccountId("1");
        order.setMoney(money);
        order.setStatus(OrderStatusEnum.WAIT.getValue());
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        orderService.insertSelective(order);

        orderService.paySuccessNormal(order.getOrderId(), money);

        log.info("【test】测试业务成功");
        Map<String, Object> rsp = new HashMap<>();
        rsp.put("code", 0);
        rsp.put("msg", "SUCCESS");
        return rsp;
    }

    @ApiOperation("测试TCC")
    @PostMapping
    public Object testTcc() {
        log.info("【test】测试TCC");

        BigDecimal money = new BigDecimal(0.98);
        Order order = new Order();
        order.setOrderId(IdUtil.simpleUUID());
        order.setAccountId("1");
        order.setMoney(money);
        order.setStatus(OrderStatusEnum.WAIT.getValue());
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        orderService.insertSelective(order);

        orderService.tryPaySuccess(order.getOrderId(), money);

        log.info("【test】测试TCC成功");
        Map<String, Object> rsp = new HashMap<>();
        rsp.put("code", 0);
        rsp.put("msg", "SUCCESS");
        return rsp;
    }
}
