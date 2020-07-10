package com.example.demo.api.mk;

import com.example.demo.json.ResultJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

/**
 * 行政地区接口
 */

@Api(value = "MK行政地区模块", description = "MK行政地区模块")
public interface MkAddressApi extends Serializable {

    
    /**
     * 行政地区获取
     * @return
     */
    @ApiOperation(value = "MK行政地区所有信息接口", notes = "MK行政地区所有信息接口")
    @ApiImplicitParams({})
    @PostMapping(value = "/mk/api/china/list")
    public ResultJSON<?> list();

    /**
     * 行政地区详情获取
     * @return
     */
    @ApiOperation(value = "MK行政地区详情接口", notes = "MK行政地区详情接口")
    @ApiImplicitParams({})
    @PostMapping(value = "/mk/api/china/getInfo")
    public ResultJSON<?> getInfo(@RequestParam("name") String name);

}
