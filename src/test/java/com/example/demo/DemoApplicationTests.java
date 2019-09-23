package com.example.demo;

import io.swagger.annotations.Api;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Api(value = "测试模块", description = "测试接口")
public class DemoApplicationTests {

    @Test
    public void contextLoads() {

    }

}
