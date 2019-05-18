package com.cn.sample.account.service.impl;

import com.cn.sample.api.model.po.AccountBill;
import com.cn.sample.api.model.service.IAccountBillService;
import com.cn.sample.dal.mapper.AccountBillMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * 账户流水服务实现
 *
 * @author Chen Nan
 */
@Service
@Slf4j
public class AccountBillServiceImpl extends BaseServiceImpl<AccountBillMapper, AccountBill, String>
        implements IAccountBillService {

}
