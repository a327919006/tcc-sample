package com.cn.sample.order.service.impl;

import com.cn.sample.api.enums.OrderStatusEnum;
import com.cn.sample.api.model.po.Order;
import com.cn.sample.api.model.service.IAccountService;
import com.cn.sample.api.model.service.IOrderService;
import com.cn.sample.dal.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.Propagation;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单服务实现
 *
 * @author Chen Nan
 */
@Service
@Slf4j
public class OrderServiceImpl extends BaseServiceImpl<OrderMapper, Order, String>
        implements IOrderService {

    @Reference
    private IAccountService accountService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void paySuccessNormal(String orderId, BigDecimal money) {
        log.info("【订单】paySuccess, orderId={}", orderId);
        Order order = mapper.selectByPrimaryKey(orderId);

        if (order == null || order.getStatus() != OrderStatusEnum.WAIT.getValue()) {
            log.info("【订单】该订单已处理, orderId={}", orderId);
            return;
        }

        order.setMoney(money);
        order.setStatus(OrderStatusEnum.SUCCESS.getValue());
        order.setUpdateTime(LocalDateTime.now());
        mapper.updateByPrimaryKeySelective(order);

        accountService.addMoneyNormal(order.getAccountId(), orderId, money);
    }

    @Override
    @Compensable(confirmMethod = "confirmPaySuccess", cancelMethod = "cancelPaySuccess")
    public void tryPaySuccess(String orderId, BigDecimal money) {
        log.info("【订单】tryPaySuccess, orderId={}", orderId);
        Order order = mapper.selectByPrimaryKey(orderId);

        if (order == null || order.getStatus() != OrderStatusEnum.WAIT.getValue()) {
            log.info("【订单】该订单已处理, orderId={}", orderId);
            return;
        }

        accountService.tryAddMoney(null, order.getAccountId(), orderId, money);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmPaySuccess(String orderId, BigDecimal money) {
        log.info("【订单】confirmPaySuccess, orderId={}", orderId);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelPaySuccess(String orderId, BigDecimal money) {
        log.info("【订单】cancelPaySuccess, orderId={}", orderId);

    }
}
