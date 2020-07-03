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
 * 意见反馈接口
 */

@Api(value = "MK意见反馈模块", description = "MK意见反馈模块")
public interface MkOpinionApi extends Serializable {

    /**
     * 意见反馈添加
     * @return
     */
    @ApiOperation(value = "MK意见反馈添加接口", notes = "MK意见反馈添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "details", value = "意见内容"),
            @ApiImplicitParam(name = "phone", value = "联系电话")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/opinion/add")
    public ResultJSON<?> add(
                             @RequestParam(required = false, value = "userId") String userId,
                             @RequestParam(required = false, value = "details") String details,
                             @RequestParam(required = false, value = "phone") String phone
    );
//
//
//    /**
//     * 意见反馈分页获取
//     * @param limit
//     * @param row
//     * @return
//     */
//    @ApiOperation(value = "MK意见反馈分页接口", notes = "MK意见反馈添加接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "编号"),
//            @ApiImplicitParam(name = "limit", value = "起始页"),
//            @ApiImplicitParam(name = "row", value = "行数")
//    })
//    @PostMapping(value = "/mk/api/apart/page")
//    public ResultJSON<?> page(
//            @RequestParam(required = false, value = "val") String val,
//            @RequestParam(required = false, value = "limit") Integer limit,
//            @RequestParam(required = false, value = "row") Integer row);
//
//    /**
//     * 意见反馈分页删除
//     *
//     * @param id
//     * @return
//     */
//    @ApiOperation(value = "MK意见反馈删除接口", notes = "MK意见反馈删除接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "ID"),
//    })
//    @PostMapping(value = "/mk/api/apart/del")
//    @Transactional(rollbackFor = Exception.class)
//    public ResultJSON<?> del(@RequestParam(required = true, value = "id") Integer id);
//
//
//    /**
//     * 意见反馈修改
//     *
//     * @param ftype
//     * @param name
//     * @param creatCode
//     * @return
//     */
//    @ApiOperation(value = "MK意见反馈添加接口", notes = "MK意见反馈添加接口")
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
