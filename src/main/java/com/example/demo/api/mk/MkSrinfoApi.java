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
import java.math.BigDecimal;

/**
 * 收入明细接口
 */

@Api(value = "MK收入明细模块", description = "MK收入明细模块")
public interface MkSrinfoApi extends Serializable {

    /**
     * 收入明细添加
     * @param ftype
     * @param payType
     * @param details
     * @param amount
     * @param userId
     * @return
     */
    @ApiOperation(value = "MK收入明细添加接口", notes = "MK收入明细添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ftype", value = "费用类型(-/+)"),
            @ApiImplicitParam(name = "payType", value = "付款方式"),
            @ApiImplicitParam(name = "details", value = "交易详情"),
            @ApiImplicitParam(name = "amount", value = "交易金额"),
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "remark", value = "备注")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/srinfo/add")
    public ResultJSON<?> add(@RequestParam(required = false, value = "ftype") String ftype,
                             @RequestParam(required = false, value = "payType") String payType,
                             @RequestParam(required = false, value = "details") String details,
                             @RequestParam(required = false, value = "amount") BigDecimal amount,
                             @RequestParam(required = false, value = "userId") String userId,
                             @RequestParam(required = false, value = "remark") String remark);

    /**
     * 收入明细分页获取
     * @param val
     * @param userId
     * @param limit
     * @param row
     * @return
     */
    @ApiOperation(value = "MK收入明细分页接口", notes = "MK收入明细分页接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "val", value = "查询"),
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "limit", value = "起始页"),
            @ApiImplicitParam(name = "row", value = "行数")
    })
    @PostMapping(value = "/mk/api/srinfo/page")
    public ResultJSON<?> page(@RequestParam(required = false, value = "val") String val,
                              @RequestParam(required = false, value = "userId") String userId,
                              @RequestParam(required = false, value = "limit") Integer limit,
                              @RequestParam(required = false, value = "row") Integer row);

    /**
     * 收入明细分页删除
     * @param id
     * @return
     */
    @ApiOperation(value = "MK收入明细删除接口", notes = "MK收入明细添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID")
    })
    @PostMapping(value = "/mk/api/srinfo/del")
    @Transactional(rollbackFor = Exception.class)
    public ResultJSON<?> del(@RequestParam(required = true, value = "id") Integer id);

}
