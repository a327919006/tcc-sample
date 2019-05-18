package com.cn.sample.account.service.impl;

import com.cn.sample.api.model.po.Account;
import com.cn.sample.api.model.service.IAccountService;
import com.cn.sample.dal.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * 账户服务实现
 *
 * @author Chen Nan
 */
@Service
@Slf4j
public class AccountServiceImpl extends BaseServiceImpl<AccountMapper, Account, String>
        implements IAccountService {

}
