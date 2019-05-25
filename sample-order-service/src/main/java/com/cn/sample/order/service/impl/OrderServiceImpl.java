package com.cn.sample.order.service.impl;

import com.cn.sample.api.enums.OrderStatusEnum;
import com.cn.sample.api.model.po.Order;
import com.cn.sample.api.model.service.IAccountService;
import com.cn.sample.api.model.service.IOrderService;
import com.cn.sample.dal.mapper.OrderMapper;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
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
    @TwoPhaseBusinessAction(name = "tryPaySuccess",
            commitMethod = "confirmPaySuccess",
            rollbackMethod = "cancelPaySuccess")
    public boolean prepare(BusinessActionContext actionContext) {
        log.info("【订单】tryPaySuccess");
//        Order order = mapper.selectByPrimaryKey(orderId);
//
//        if (order == null || order.getStatus() != OrderStatusEnum.WAIT.getValue()) {
//            log.info("【订单】该订单已处理, orderId={}", orderId);
//            throw new RuntimeException("该订单已处理");
//        }

//        accountService.tryAddMoney(actionContext, order.getAccountId(), orderId, money);
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        log.info("【订单】confirmPaySuccess");
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        log.info("【订单】cancelPaySuccess");
        return true;
    }
}
