package com.cn.sample.order.service.impl;

import com.cn.sample.api.model.po.Order;
import com.cn.sample.api.model.service.IOrderService;
import com.cn.sample.dal.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * 订单服务实现
 *
 * @author Chen Nan
 */
@Service
@Slf4j
public class OrderServiceImpl extends BaseServiceImpl<OrderMapper, Order, String>
        implements IOrderService {

}
