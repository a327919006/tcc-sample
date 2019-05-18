package com.cn.sample.order.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 服务层启动类
 *
 * @author Chen Nan
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.cn.sample.dal.mapper")
@ComponentScan(basePackages = {
        "com.cn.sample.dal.mapper"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}