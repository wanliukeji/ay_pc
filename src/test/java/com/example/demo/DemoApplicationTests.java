package com.example.demo;

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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Api(value = "测试模块", description = "测试接口")
public class DemoApplicationTests {

    @Autowired
    FiedService fiedService;

    @Test
    public void contextLoads() {
        PageHelper.startPage(1, 5);
        List<Fied> deptList = fiedService.list(null);
        PageInfo<Fied>  p = new PageInfo<>(deptList);
        System.out.println(p);

    }

    @Test
    public void setVal(){
        RedisUtil.set("first","JKIEO");
    }

}
