package com.cn.sample.app.config;

import com.cn.sample.api.model.service.IOrderService;
import com.cn.sample.app.service.BusinessService;
import io.seata.spring.annotation.GlobalTransactionScanner;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/5/24.
 */
@Configuration
public class TccConfig {

    @Bean
    public GlobalTransactionScanner globalTransactionScanner() {
        return new GlobalTransactionScanner("tcc-sample", "my_test_tx_group");
    }

    @Bean
    public BusinessService businessService(){
        return new BusinessService();
    }
}
