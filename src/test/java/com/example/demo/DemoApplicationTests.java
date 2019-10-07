package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.Utils.IpUtil;
import com.example.demo.entity.Fied;
import com.example.demo.redis.RedisUtil;
import com.example.demo.service.FiedService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Api(value = "测试模块", description = "测试接口")
public class DemoApplicationTests {

    @Autowired
    FiedService fiedService;

    @Test
    public void contextLoads() {

    }

    public static void main(String[] args) throws Exception {

    }
}
