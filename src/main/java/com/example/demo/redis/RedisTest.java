package com.example.demo.redis;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.SysUser;
import com.example.demo.service.SysUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * @author xcbeyond
 * 2018年7月19日下午3:08:04
 * Redis 使用示例
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {
    @Resource
    private RedisUtil util;

    @Autowired
    private SysUserService userService;

    @Test
//    @Cacheable 查询redis里有没有该对象 有的话就从缓存里读取不再执行方法 没有的话继续从后台取
    @Cacheable(cacheNames = "user", key = "'user_'+ #user")
    public void save(SysUser user) {
        try {
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

    @Test
//    @CacheEvict 从Redis缓存中清除指定的key对应的数据
    @CacheEvict(cacheNames = "user", key = "'user_'+ #user")
    public void dele(SysUser user) {
        try {
            boolean f = userService.removeById(user.getId());
            // System.out.println(RedisUtil.set("CHENYU",JSON.toJSONString(user)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * @CachePut 一般用于更新查插入操作，每次都会请求db  数据保存/更新至Redis缓存机制内
     */
    @CachePut(key = "'currentTime'+#id")
    public long updateTime(String id) {
        return System.currentTimeMillis();
    }


}