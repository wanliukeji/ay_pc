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
 * 账单接口
 */

@Api(value = "MK账单模块", description = "MK账单模块")
public interface MkBillApi extends Serializable {

    /**
     * 账单添加
     * @return
     */
    @ApiOperation(value = "MK账单添加接口", notes = "MK账单添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户ID"),
            @ApiImplicitParam(name = "payUid", value = "收款人ID"),
            @ApiImplicitParam(name = "pm", value = "(0 支出 1 = 获得)"),
            @ApiImplicitParam(name = "title", value = "账单标题"),
            @ApiImplicitParam(name = "ftype", value = "明细类型"),
            @ApiImplicitParam(name = "amount", value = "金额"),
            @ApiImplicitParam(name = "mark", value = "备注"),
            @ApiImplicitParam(name = "fid", value = "房源ID")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/bill/add")
    public ResultJSON<?> add(
                 @RequestParam(required = false, value = "uid") String uid,
                 @RequestParam(required = false, value = "payUid") String payUid,
                 @RequestParam(required = false, value = "pm") Integer pm,
                 @RequestParam(required = false, value = "title") String title,
                 @RequestParam(required = false, value = "ftype") String ftype,
                 @RequestParam(required = false, value = "amount") BigDecimal amount,
                 @RequestParam(required = false, value = "mark") String mark,
                 @RequestParam(required = false, value = "fid") String fid
    );

    /**
     * 账单获取
     * @return
     */
//    @ApiOperation(value = "MK账单所有信息接口", notes = "MK账单所有信息接口")
//    @ApiImplicitParams({})
//    @PostMapping(value = "/mk/api/bill/list")
//    public ResultJSON<?> list();

    /**
     * 账单分页获取
     * @param limit
     * @param row
     * @return
     */
    @ApiOperation(value = "MK账单分页接口", notes = "MK账单添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户编号"),
            @ApiImplicitParam(name = "limit", value = "起始页"),
            @ApiImplicitParam(name = "row", value = "行数")
    })
    @PostMapping(value = "/mk/api/bill/page")
    public ResultJSON<?> page(
            @RequestParam(required = false, value = "uid") String uid,
            @RequestParam(required = false, value = "limit") Integer limit,
            @RequestParam(required = false, value = "row") Integer row);

    /**
     * 账单分页删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "MK账单删除接口", notes = "MK账单删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID"),
    })
    @PostMapping(value = "/mk/api/bill/del")
    @Transactional(rollbackFor = Exception.class)
    public ResultJSON<?> del(@RequestParam(required = true, value = "id") Integer id);

    /**
     * 账单分页删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "MK账单详情接口", notes = "MK账单详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID"),
    })
    @PostMapping(value = "/mk/api/bill/getInfo")
    @Transactional(rollbackFor = Exception.class)
    public ResultJSON<?> getInfo(@RequestParam(required = true, value = "id") Integer id);


}
