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
 * 钱包接口
 */

@Api(value = "MK钱包模块", description = "MK钱包模块")
public interface MkWallkApi extends Serializable {

    /**
     * 钱包充值
     * @return
     */
    @ApiOperation(value = "MK钱包充值接口", notes = "MK钱包充值接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "amount", value = "金额"),
            @ApiImplicitParam(name = "userId", value = "用户ID")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/wallk/add")
    public ResultJSON<?> add(
            @RequestParam(required = false, value = "amount") BigDecimal amount,
            @RequestParam(required = false, value = "userId") String userId
    );

    /**
     * 钱包付款
     * @return
     */
    @ApiOperation(value = "MK钱包付款接口", notes = "MK钱包付款接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "amount", value = "金额"),
            @ApiImplicitParam(name = "userId", value = "用户ID")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/wallk/del")
    public ResultJSON<?> edit(
            @RequestParam(required = false, value = "amount") BigDecimal amount,
            @RequestParam(required = false, value = "userId") String userId
    );

    /**
     * 钱包信息
     * @return
     */
    @ApiOperation(value = "MK钱包详情接口", notes = "MK钱包详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/wallk/getInfo")
    public ResultJSON<?> getInfo(
            @RequestParam(required = false, value = "userId") String userId
    );

}
