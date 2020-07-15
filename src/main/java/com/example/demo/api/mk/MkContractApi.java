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
 * 合同接口
 */

@Api(value = "MK合同模块", description = "MK合同模块")
public interface MkContractApi extends Serializable {

    /**
     * 合同添加
     * @return
     */
    @ApiOperation(value = "MK合同生成接口", notes = "MK合同生成接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "addr", value = "地址"),
            @ApiImplicitParam(name = "area", value = "面积"),
            @ApiImplicitParam(name = "startDate", value = "起租日期"),
            @ApiImplicitParam(name = "endDate", value = "结束日期"),
            @ApiImplicitParam(name = "payDate", value = "最晚支付日期"),
            @ApiImplicitParam(name = "amount", value = "支付金额"),
            @ApiImplicitParam(name = "payDay", value = "每月支付几日"),
            @ApiImplicitParam(name = "yamount", value = "押金"),
            @ApiImplicitParam(name = "startDay", value = "提前天数"),
            @ApiImplicitParam(name = "fuid", value = "房东ID"),
            @ApiImplicitParam(name = "zuid", value = "租客ID"),
            @ApiImplicitParam(name = "fid", value = "房源ID"),
            @ApiImplicitParam(name = "payType", value = "付款方式(1 :月付 2 季付, 3 半年付, 4 年付)")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/ht/gen")
    public ResultJSON<?> gen(
        @RequestParam(required = false, value = "addr") String addr,
        @RequestParam(required = false, value = "area") Double area,
        @RequestParam(required = false, value = "startDate") String startDate,
        @RequestParam(required = false, value = "endDate") String endDate,
        @RequestParam(required = false, value = "payDate") String payDate,
        @RequestParam(required = false, value = "payDay") Integer payDay,
        @RequestParam(required = false, value = "amount") BigDecimal amount,
        @RequestParam(required = false, value = "yamount") BigDecimal yamount,
            @RequestParam(required = false, value = "startDay") Integer startDay,
            @RequestParam(required = false, value = "fuid") String fuid,
            @RequestParam(required = false, value = "zuid") String zuid,
            @RequestParam(required = false, value = "fid") String fid,
            @RequestParam(required = false, value = "payType") Integer payType
    );

    /**
     * 合同获取
     * @return
     */
//    @ApiOperation(value = "MK合同所有信息接口", notes = "MK合同所有信息接口")
//    @ApiImplicitParams({})
//    @PostMapping(value = "/mk/api/ht/list")
    public ResultJSON<?> list();

    /**
     * 合同分页获取
     * @param limit
     * @param row
     * @return
     */
    @ApiOperation(value = "MK合同分页接口", notes = "MK合同添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "编号"),
            @ApiImplicitParam(name = "limit", value = "起始页"),
            @ApiImplicitParam(name = "row", value = "行数")
    })
    @PostMapping(value = "/mk/api/ht/page")
    public ResultJSON<?> page(
            @RequestParam(required = false, value = "userId") String userId,
            @RequestParam(required = false, value = "limit") Integer limit,
            @RequestParam(required = false, value = "row") Integer row);
//
//    /**
//     * 合同分页删除
//     *
//     * @param id
//     * @return
//     */
//    @ApiOperation(value = "MK合同删除接口", notes = "MK合同删除接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "ID"),
//    })
//    @PostMapping(value = "/mk/api/apart/del")
//    @Transactional(rollbackFor = Exception.class)
//    public ResultJSON<?> del(@RequestParam(required = true, value = "id") Integer id);
//
//
//    /**
//     * 合同修改
//     *
//     * @param ftype
//     * @param name
//     * @param creatCode
//     * @return
//     */
//    @ApiOperation(value = "MK合同添加接口", notes = "MK合同添加接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "设施编号"),
//            @ApiImplicitParam(name = "ftype", value = "设施类型"),
//            @ApiImplicitParam(name = "name", value = "设施名"),
//            @ApiImplicitParam(name = "creatCode", value = "创建人")
//    })
//    @Transactional(rollbackFor = Exception.class)
//    @PostMapping(value = "/mk/api/apart/edit")
//    public ResultJSON<?> edit(
//            @RequestParam("id") Integer id,
//            @RequestParam("ftype") Integer ftype,
//            @RequestParam("name") String name,
//            @RequestParam("creatCode") String creatCode);

}
