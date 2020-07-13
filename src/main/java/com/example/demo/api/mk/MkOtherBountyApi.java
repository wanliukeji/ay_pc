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
 * 费用押金接口
 */

@Api(value = "MK费用押金模块", description = "MK费用押金模块")
public interface MkOtherBountyApi extends Serializable {

    /**
     * 费用押金添加
     * @return
     */
    @ApiOperation(value = "MK费用押金添加接口", notes = "MK费用押金添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fname", value = "费用名称"),
            @ApiImplicitParam(name = "amount", value = "金额"),
            @ApiImplicitParam(name = "fstatus", value = "审核状态(0 未审核  1 已审核)"),
            @ApiImplicitParam(name = "remark", value = "费用说明"),
            @ApiImplicitParam(name = "ftype", value = "费用类型(1 押金 2 其他费用)"),
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/other/add")
    public ResultJSON<?> add(@RequestParam(required = false, value = "fname") String fname,
                             @RequestParam(required = false, value = "amount") BigDecimal amount,
                             @RequestParam(required = false, value = "fstatus") Integer fstatus,
                             @RequestParam(required = false, value = "remark") String remark,
                             @RequestParam(required = false, value = "ftype") Integer ftype
    );

    /**
     * 费用押金获取
     * @return
     */
    @ApiOperation(value = "MK费用押金详情接口", notes = "MK费用押金详情接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "获取费用押金详情")
    })
    @PostMapping(value = "/mk/api/other/getInfo")
    public ResultJSON<?> getInfo(
            @RequestParam(required = false, value = "id") Integer id
    );

    /**
     * 费用押金分页获取
     * @return
     */
    @ApiOperation(value = "MK费用押金接口", notes = "MK费用押金接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "ftype", value = "其他(1 费用 2 押金)")
    })
    @PostMapping(value = "/mk/api/other/list")
    public ResultJSON<?> list(
            @RequestParam(required = false, value = "userId") String userId,
            @RequestParam(required = false, value = "ftype") Integer ftype
    );
//
//    /**
//     * 费用押金分页删除
//     *
//     * @param id
//     * @return
//     */
////    @ApiOperation(value = "MK费用押金删除接口", notes = "MK费用押金删除接口")
////    @ApiImplicitParams({
////            @ApiImplicitParam(name = "id", value = "ID"),
////    })
////    @PostMapping(value = "/mk/api/apart/del")
//    @Transactional(rollbackFor = Exception.class)
//    public ResultJSON<?> del(@RequestParam(required = true, value = "id") Integer id);
//
//
//    /**
//     * 费用押金修改
//     *
//     * @param ftype
//     * @param name
//     * @param creatCode
//     * @return
//     */
////    @ApiOperation(value = "MK费用押金添加接口", notes = "MK费用押金添加接口")
////    @ApiImplicitParams({
////            @ApiImplicitParam(name = "id", value = "设施编号"),
////            @ApiImplicitParam(name = "ftype", value = "设施类型"),
////            @ApiImplicitParam(name = "name", value = "设施名"),
////            @ApiImplicitParam(name = "creatCode", value = "创建人")
////    })
////    @Transactional(rollbackFor = Exception.class)
////    @PostMapping(value = "/mk/api/apart/edit")
//    public ResultJSON<?> edit(
//            @RequestParam("id") Integer id,
//            @RequestParam("ftype") Integer ftype,
//            @RequestParam("name") String name,
//            @RequestParam("creatCode") String creatCode);

}
