package com.example.demo.redis;

import com.example.demo.api.mk.MkRedistApi;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Redis模块", description = "Redis接口")
public class RedisController implements MkRedistApi {


    @Autowired
    private RedisService redisService ;


    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public void redisTest(){
        redisService.set("1","value22222");
    }

    @Override
    public ResultJSON<?> initia(String name, String x, String y, String fid) {
        return redisService.initia(name, x, y, fid);
    }

    @Override
    public ResultJSON<?> nearby(String lng, String lat, Integer distance) {
        return  redisService.nearby(lng, lat, distance);
    }

    @Override
    public ResultJSON<?> isnNearby(String ax, String ay, String bx, String by) {
        return redisService.isNearby(ax, ay, bx, by);
    }

    @Override
    public ResultJSON<?> del(String val) {
        try {
            redisService.remove(val);
            return ResultJSON.success(CodeMsg.UPDATE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultJSON.error(CodeMsg.UPDATE_ERROR);
        }
    }
}