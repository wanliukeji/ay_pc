package com.example.demo.service.redis;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "登录模块", description = "登录接口")
public class RedisController {


    @Autowired
    private RedisService redisService ;


    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public void redisTest(){
        redisService.set("1","value22222");
    }

}