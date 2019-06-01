package com.cn.sample.api.model.service;

import com.cn.sample.api.model.po.Order;
import org.mengyun.tcctransaction.api.Compensable;

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
     * @param orderId 订单ID
     * @param money   成功支付金额
     */
    void tryPaySuccess(String orderId, BigDecimal money);

    void confirmPaySuccess(String orderId, BigDecimal money);

    void cancelPaySuccess(String orderId, BigDecimal money);
}
