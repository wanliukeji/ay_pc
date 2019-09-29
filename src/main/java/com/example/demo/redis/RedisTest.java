package com.example.demo.redis;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.SysUser;
import com.example.demo.service.SysUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author xcbeyond
 * 2018年7月19日下午3:08:04
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {
    @Resource
    private RedisUtil util;

    @Autowired
    private SysUserService userService;

    @Test
    @Cacheable(cacheNames = "user", key = "user")
    public void seve() {
        try {
            SysUser user = new SysUser();
            user.setAccount("redis");
            user.setEmail("redis@126.com");
            user.setPassword("123456");
            user.setName("陈育");
            user.setCreateTime(new Date());
            user.setCreateBy("redis");
            user.setAvatar("redis");
            user.setPhone("123232454");
            user.setRemark("REDIS");
            user.setUpdateBy("chenyi");
            boolean f = userService.save(user);
            // System.out.println(RedisUtil.set("CHENYU",JSON.toJSONString(user)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}