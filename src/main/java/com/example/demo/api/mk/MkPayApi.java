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
 * 付款明细接口
 */

@Api(value = "MK付款方式模块", description = "MK付款方式模块")
public interface MkPayApi extends Serializable {

    /**
     * 收入付款方式添加
     *
     * @param amount
     * @param name
     * @param fid
     * @return
     */
    @ApiOperation(value = "MK付款方式添加接口", notes = "MK付款方式添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "付款方式"),
            @ApiImplicitParam(name = "amount", value = "金额"),
            @ApiImplicitParam(name = "fid", value = "房源ID"),
            @ApiImplicitParam(name = "kdCosts", value = "宽带费"),
            @ApiImplicitParam(name = "dCosts", value = "电费"),
            @ApiImplicitParam(name = "sCosts", value = "水费"),
            @ApiImplicitParam(name = "wyCosts", value = "物业费"),
            @ApiImplicitParam(name = "tcCosts", value = "停车费"),
            @ApiImplicitParam(name = "rqCosts", value = "燃气费"),
            @ApiImplicitParam(name = "sAmount", value = "赏金"),
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "remark", value = "说明"),
            @ApiImplicitParam(name = "zType", value = "起租类型"),
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/pay/add")
    public ResultJSON<?> add(@RequestParam(required = false, value = "name") String name,
                             @RequestParam(required = false, value = "amount") Double amount,
                             @RequestParam(required = false, value = "fid") String fid,
                             @RequestParam(required = false, name = "kdCosts") Double kdCosts,
                             @RequestParam(required = false, name = "dCosts") Double dCosts,
                             @RequestParam(required = false, name = "sCosts") Double sCosts,
                             @RequestParam(required = false, name = "wyCosts") Double wyCosts,
                             @RequestParam(required = false, name = "tcCosts") Double tcCosts,
                             @RequestParam(required = false, name = "rqCosts") Double rqCosts,
                             @RequestParam(required = false, value = "sAmount") Double sAmount,
                             @RequestParam(required = false, value = "userId") String userId,
                             @RequestParam(required = false, value = "remark") String remark,
                             @RequestParam(required = false, value = "zType") String zType
                             );

    /**
     * 付款方式分页获取
     *
     * @param val
     * @param limit
     * @param row
     * @return
     */
    @ApiOperation(value = "MK付款方式分页接口", notes = "MK付款方式添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "val", value = "名称"),
            @ApiImplicitParam(name = "limit", value = "起始页"),
            @ApiImplicitParam(name = "row", value = "行数")
    })
    @PostMapping(value = "/mk/api/pay/page")
    public ResultJSON<?> page(
            @RequestParam(required = false, value = "val") String val,
            @RequestParam(required = false, value = "limit") Integer limit,
            @RequestParam(required = false, value = "row") Integer row);

    /**
     * 付款方式分页获取
     * @return
     */
    @ApiOperation(value = "MK付款方式详情接口", notes = "MK付款方式详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zType", value = "租期类型"),
            @ApiImplicitParam(name = "name", value = "付款方式"),
            @ApiImplicitParam(name = "fid", value = "房源ID")
    })
    @PostMapping(value = "/mk/api/pay/getInfo")
    public ResultJSON<?> getInfo(
            @RequestParam(required = false, value = "zType") String zType,
            @RequestParam(required = false, value = "name") String name,
            @RequestParam(required = false, value = "fid") Integer fid);

}
