package com.cn.sample.api.model.service;

import com.cn.sample.api.model.po.Account;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

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
    @TwoPhaseBusinessAction(name = "tryAddMoney",
            commitMethod = "confirmAddMoney",
            rollbackMethod = "cancelAddMoney")
    void tryAddMoney(BusinessActionContext actionContext,
                     @BusinessActionContextParameter(paramName = "accountId")String accountId,
                     @BusinessActionContextParameter(paramName = "orderId")String orderId,
                     @BusinessActionContextParameter(paramName = "money")BigDecimal money);

    void confirmAddMoney(BusinessActionContext actionContext);

    void cancelAddMoney(BusinessActionContext actionContext);
}
