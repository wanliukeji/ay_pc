package com.example.demo.api.mk;

import com.example.demo.json.ResultJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * Redis接口
 */

@Api(value = "MKRedis模块", description = "MKRedis模块")
public interface MkRedistApi extends Serializable {

    /**
     * Redis保存房源经纬度基本信息
     * @return
     */
    @ApiOperation(value = "MKRedis数据初始化接口", notes = "MKRedis数据初始化接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "房源名称"),
            @ApiImplicitParam(name = "x", value = "经度"),
            @ApiImplicitParam(name = "y ", value = "纬度"),
            @ApiImplicitParam(name = "fid ", value = "房源ID")
    })
    @PostMapping(value = "/mk/api/redis/add")
    @Transactional(rollbackFor = Exception.class)
    public ResultJSON<?> initia(
            @RequestParam(required = false, value = "房源名称") String name,
            @RequestParam(required = false, value = "经度") String x,
            @RequestParam(required = false, value = "纬度") String y,
            @RequestParam(required = false, value = "房源ID") String fid
    );

    /**
     * Redis初始化经纬度
     * @return
     */
    @ApiOperation(value = "MKRedis搜索附近接口", notes = "MKRedis搜索附近接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "经度"),
            @ApiImplicitParam(name = "y ", value = "维度"),
            @ApiImplicitParam(name = "distance ", value = "方圆公里数")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/redis/nearby")
    public ResultJSON<?> nearby(
            @RequestParam(required = false, value = "x") String lng,
            @RequestParam(required = false, value = "y") String lat,
            @RequestParam(required = false, value = "distance") Integer distance
    );

    /**
     * Redis初始化经纬度
     * @return
     */
    @ApiOperation(value = "MKRedis是否在附近接口", notes = "MKRedis是否在附近接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ax", value = "房源经度"),
            @ApiImplicitParam(name = "ay ", value = "房源维度"),
            @ApiImplicitParam(name = "bx ", value = "用户维度"),
            @ApiImplicitParam(name = "by ", value = "用户维度")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/redis/isNearby")
    public ResultJSON<?> isnNearby(
            @RequestParam(required = false, value = "ax") String ax,
            @RequestParam(required = false, value = "ay") String ay,
            @RequestParam(required = false, value = "bx") String bx,
            @RequestParam(required = false, value = "by") String by
    );

    /**
     * Redis删除
     * @return
     */
    @ApiOperation(value = "MKRedis删除接口", notes = "MKRedis删除接口")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "lng", value = "经度"),
            @ApiImplicitParam(name = "val ", value = "删除键")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/redis/del")
    public ResultJSON<?> del(
            @RequestParam(required = false, value = "val") String val
    );

}
