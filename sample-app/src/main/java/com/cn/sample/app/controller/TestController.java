package com.cn.sample.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>消息管理制器</p>
 *
 * @author Chen Nan
 * @date 2019/3/11.
 */
@RestController
@Api(tags = "测试", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequestMapping(value = "/test")
@Slf4j
public class TestController {


    @ApiOperation("测试TCC")
    @GetMapping
    public Object sendNotify() {
        log.info("【test】测试TCC");


        log.info("【test】测试TCC成功");
        Map<String, Object> rsp = new HashMap<>();
        rsp.put("code", 0);
        rsp.put("msg", "SUCCESS");
        return rsp;
    }
}
