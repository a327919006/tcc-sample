package com.cn.sample.order.service.config;

import org.apache.dubbo.remoting.TimeoutException;
import org.mengyun.tcctransaction.recover.TransactionRecovery;
import org.mengyun.tcctransaction.repository.ZooKeeperTransactionRepository;
import org.mengyun.tcctransaction.spring.ConfigurableCoordinatorAspect;
import org.mengyun.tcctransaction.spring.ConfigurableTransactionAspect;
import org.mengyun.tcctransaction.spring.recover.DefaultRecoverConfig;
import org.mengyun.tcctransaction.spring.recover.RecoverScheduledJob;
import org.mengyun.tcctransaction.spring.support.SpringBeanFactory;
import org.mengyun.tcctransaction.spring.support.SpringTransactionConfigurator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/5/19.
 */
@Configuration
public class TccConfig {

    /**
     * 设置TransactionRepository
     * <p>
     * 需要为参与事务的应用项目配置一个TransactionRepository，
     * tcc-transaction框架使用transactionRepository持久化事务日志。
     * 可以选择:
     * FileSystemTransactionRepository、
     * SpringJdbcTransactionRepository、
     * RedisTransactionRepository、
     * ZooKeeperTransactionRepository。
     */
    @Bean
    public ZooKeeperTransactionRepository transactionRepository() {
        ZooKeeperTransactionRepository repository = new ZooKeeperTransactionRepository();
        repository.setZkServers("localhost:2181");
        repository.setZkTimeout(10000);
        repository.setZkRootPath("/tcc_order");
        return repository;
    }

    /**
     * 设置恢复策略(可选）
     * 当Tcc事务异常后，恢复Job将会定期恢复事务。
     */
    @Bean
    public DefaultRecoverConfig defaultRecoverConfig() {
        Set<Class<? extends Exception>> exceptionSet = new HashSet<>();
        exceptionSet.add(TimeoutException.class);

        DefaultRecoverConfig recoverConfig = new DefaultRecoverConfig();
        recoverConfig.setMaxRetryCount(30);
        recoverConfig.setRecoverDuration(120);
        recoverConfig.setCronExpression("0 */1 * * * ?");
        recoverConfig.setDelayCancelExceptions(exceptionSet);
        return recoverConfig;
    }

    @Bean
    public RecoverScheduledJob recoverScheduledJob() {
        RecoverScheduledJob job = new RecoverScheduledJob();
        job.setTransactionRecovery(transactionRecovery());
        job.setTransactionConfigurator(transactionConfigurator());
        job.setScheduler(recoverScheduler().getScheduler());
        job.init();
        return job;
    }

    @Bean
    public SchedulerFactoryBean recoverScheduler() {
        return new SchedulerFactoryBean();
    }

    @Bean
    public TransactionRecovery transactionRecovery() {
        TransactionRecovery recovery = new TransactionRecovery();
        recovery.setTransactionConfigurator(transactionConfigurator());
        return recovery;
    }

    @Bean
    public ConfigurableCoordinatorAspect resourceCoordinatorAspect() {
        ConfigurableCoordinatorAspect aspect = new ConfigurableCoordinatorAspect();
        aspect.setTransactionConfigurator(transactionConfigurator());
        aspect.init();
        return aspect;
    }

    @Bean
    public ConfigurableTransactionAspect compensableTransactionAspect() {
        ConfigurableTransactionAspect aspect = new ConfigurableTransactionAspect();
        aspect.setTransactionConfigurator(transactionConfigurator());
        aspect.init();
        return aspect;
    }

    @Bean
    public SpringBeanFactory springBeanFactory() {
        return new SpringBeanFactory();
    }

    @Bean
    public SpringTransactionConfigurator transactionConfigurator() {
        SpringTransactionConfigurator configurator = new SpringTransactionConfigurator();
        configurator.init();
        return configurator;
    }
}
