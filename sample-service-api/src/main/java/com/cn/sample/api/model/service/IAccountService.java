package com.cn.sample.api.model.service;

import com.cn.sample.api.model.po.Account;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;

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
    void tryAddMoney(TransactionContext transactionContext, String accountId, String orderId, BigDecimal money);

    void confirmAddMoney(TransactionContext transactionContext, String accountId, String orderId, BigDecimal money);

    void cancelAddMoney(TransactionContext transactionContext, String accountId, String orderId, BigDecimal money);
}
