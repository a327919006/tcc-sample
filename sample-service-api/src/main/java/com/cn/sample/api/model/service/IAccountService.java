package com.cn.sample.api.model.service;

import com.cn.sample.api.model.po.Account;
import org.mengyun.tcctransaction.api.Compensable;

import java.math.BigDecimal;

/**
 * 账户服务接口
 *
 * @author Chen Nan
 */
public interface IAccountService extends IBaseService<Account, String> {

    /**
     * 添加账户金额
     *
     * @param accountId 账户ID
     * @param orderId   订单ID
     * @param money     金额
     */
    void addMoneyNormal(String accountId, String orderId, BigDecimal money);

    /**
     * TCC-添加账户金额
     *
     * @param accountId 账户ID
     * @param orderId   订单ID
     * @param money     金额
     */
    @Compensable
    void tryAddMoney(String accountId, String orderId, BigDecimal money);

}
