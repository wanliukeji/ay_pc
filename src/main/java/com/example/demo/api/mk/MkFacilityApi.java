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
 * 房屋基本配置接口
 */

@Api(value = "MK房屋基本配置模块", description = "MK房屋基本配置模块")
public interface MkFacilityApi extends Serializable {

    /**
     * 房屋基本配置添加
     *
     * @param ftype
     * @param name
     * @param creatCode
     * @return
     */
    @ApiOperation(value = "MK房屋基本配置添加接口", notes = "MK房屋基本配置添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ftype", value = "设施类型"),
            @ApiImplicitParam(name = "name", value = "设施名"),
            @ApiImplicitParam(name = "creatCode", value = "创建人")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/facility/add")
    public ResultJSON<?> add(@RequestParam(required = false, value = "ftype") String ftype,
                             @RequestParam(required = false, value = "name") String name,
                             @RequestParam(required = false, value = "creatCode") String creatCode);

    /**
     * 房屋基本配置获取
     * @return
     */
    @ApiOperation(value = "MK房屋基本配置所有信息接口", notes = "MK房屋基本配置所有信息接口")
    @ApiImplicitParams({})
    @PostMapping(value = "/mk/api/facility/list")
    public ResultJSON<?> list();

    /**
     * 房屋基本配置获取
     * @return
     */
    @ApiOperation(value = "MK房屋基本配置分类信息接口", notes = "MK房屋基本配置分类信息接口")
    @ApiImplicitParams({})
    @PostMapping(value = "/mk/api/facility/getList")
    public ResultJSON<?> getList(@RequestParam("ftype") Integer ftype);

    /**
     * 房屋基本配置分页获取
     *
     * @param val
     * @param limit
     * @param row
     * @return
     */
    @ApiOperation(value = "MK房屋基本配置分页接口", notes = "MK房屋基本配置添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "val", value = "查询"),
            @ApiImplicitParam(name = "ftype", value = "设施类型"),
            @ApiImplicitParam(name = "limit", value = "起始页"),
            @ApiImplicitParam(name = "row", value = "行数")
    })
    @PostMapping(value = "/mk/api/facility/page")
    public ResultJSON<?> page(
            @RequestParam(required = false, value = "val") String val,
            @RequestParam(required = false, value = "ftype") Integer ftype,
            @RequestParam(required = false, value = "limit") Integer limit,
            @RequestParam(required = false, value = "row") Integer row);

    /**
     * 房屋基本配置分页删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "MK房屋基本配置删除接口", notes = "MK房屋基本配置删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID"),
    })
    @PostMapping(value = "/mk/api/facility/del")
    @Transactional(rollbackFor = Exception.class)
    public ResultJSON<?> del(@RequestParam(required = true, value = "id") Integer id);


    /**
     * 房屋基本配置修改
     *
     * @param ftype
     * @param name
     * @param creatCode
     * @return
     */
    @ApiOperation(value = "MK房屋基本配置添加接口", notes = "MK房屋基本配置添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "设施编号"),
            @ApiImplicitParam(name = "ftype", value = "设施类型"),
            @ApiImplicitParam(name = "name", value = "设施名"),
            @ApiImplicitParam(name = "creatCode", value = "创建人")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/facility/edit")
    public ResultJSON<?> edit(
            @RequestParam("id") Integer id,
            @RequestParam("ftype") Integer ftype,
            @RequestParam("name") String name,
            @RequestParam("creatCode") String creatCode);

}
