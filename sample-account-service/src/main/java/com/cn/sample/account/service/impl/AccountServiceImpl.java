package com.cn.sample.account.service.impl;

import cn.hutool.core.util.IdUtil;
import com.cn.sample.api.enums.AccountBillStatusEnum;
import com.cn.sample.api.model.po.Account;
import com.cn.sample.api.model.po.AccountBill;
import com.cn.sample.api.model.service.IAccountBillService;
import com.cn.sample.api.model.service.IAccountService;
import com.cn.sample.dal.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 账户服务实现
 *
 * @author Chen Nan
 */
@Service
@Slf4j
public class AccountServiceImpl extends BaseServiceImpl<AccountMapper, Account, String>
        implements IAccountService {

    @Reference
    private IAccountBillService accountBillService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addMoneyNormal(String accountId, String orderId, BigDecimal money) {
        log.info("【账户】addMoneyNormal, accountId={}, orderId={}, money={}", accountId, orderId, money);

        Account account = mapper.selectByPrimaryKey(accountId);
        if (account == null) {
            log.error("【账户】账户不存在, accountId={}, orderId={}, money={}", accountId, orderId, money);
            throw new RuntimeException("账户不存在");
        }

        AccountBill accountBill = new AccountBill();
        accountBill.setBillId(IdUtil.simpleUUID());
        accountBill.setAccountId(accountId);
        accountBill.setOrderId(orderId);
        accountBill.setOldMoney(account.getMoney());
        accountBill.setChangeMoney(money);
        accountBill.setNewMoney(account.getMoney().add(money));
        accountBill.setStatus(AccountBillStatusEnum.SUCCESS.getValue());
        accountBill.setCreateTime(LocalDateTime.now());
        accountBill.setUpdateTime(LocalDateTime.now());
        accountBillService.insertSelective(accountBill);

        Account updateAccount = new Account();
        updateAccount.setAccountId(accountId);
        updateAccount.setMoney(money);
        mapper.addMoney(updateAccount);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tryAddMoney(String accountId, String orderId, BigDecimal money) {
        log.info("【账户】tryAddMoney, accountId={}, orderId={}, money={}", accountId, orderId, money);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void confirmAddMoney(String accountId, String orderId, BigDecimal money) {
        log.info("【账户】confirmAddMoney, accountId={}, orderId={}, money={}", accountId, orderId, money);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelAddMoney(String accountId, String orderId, BigDecimal money) {
        log.info("【账户】cancelAddMoney, accountId={}, orderId={}, money={}", accountId, orderId, money);
    }
}
