package com.cn.sample.api.model.service;

import com.cn.sample.api.model.po.Order;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.math.BigDecimal;

/**
 * 订单服务接口
 *
 * @author Chen Nan
 */
public interface IOrderService extends IBaseService<Order, String> {

    /**
     * 模拟银行回调-支付成功
     *
     * @param orderId 订单ID
     * @param money   成功支付金额
     */
    void paySuccessNormal(String orderId, BigDecimal money);

    /**
     * TCC-模拟银行回调-支付成功
     *
     */
    @TwoPhaseBusinessAction(name = "tryPaySuccess",
            commitMethod = "commit",
            rollbackMethod = "rollback")
    boolean prepare(BusinessActionContext actionContext);

    boolean commit(BusinessActionContext actionContext);

    boolean rollback(BusinessActionContext actionContext);
}
