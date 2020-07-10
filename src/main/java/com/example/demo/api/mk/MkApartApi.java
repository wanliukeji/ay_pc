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
 * 品牌公寓/小区接口
 */

@Api(value = "MK品牌公寓/小区模块", description = "MK品牌公寓/小区模块")
public interface MkApartApi extends Serializable {

    /**
     * 品牌公寓/小区添加
     * @return
     */
    @ApiOperation(value = "MK品牌公寓/小区添加接口", notes = "MK品牌公寓/小区添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileCode", value = "文件ID"),
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "roomNum", value = "房源数"),
            @ApiImplicitParam(name = "cityCode", value = "城市编号"),
            @ApiImplicitParam(name = "areaCode", value = "县区编号"),
            @ApiImplicitParam(name = "townCode", value = "乡镇编号"),
            @ApiImplicitParam(name = "addr", value = "地址"),
            @ApiImplicitParam(name = "communityName", value = "公寓名称"),
            @ApiImplicitParam(name = "x", value = "经度"),
            @ApiImplicitParam(name = "y", value = "纬度")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/apart/add")
    public ResultJSON<?> add(@RequestParam(required = false, value = "fileCode") String fileCode,
                             @RequestParam(required = false, value = "userId") String userId,
                             @RequestParam(required = false, value = "roomNum") Integer roomNum,
                             @RequestParam(required = false, value = "cityCode") String cityCode,
                             @RequestParam(required = false, value = "areaCode") String areaCode,
                             @RequestParam(required = false, value = "townCode") String townCode,
                             @RequestParam(required = false, value = "addr") String addr,
                             @RequestParam(required = false, value = "communityName") String communityName,
                             @RequestParam(required = false, value = "x") String x,
                             @RequestParam(required = false, value = "y") String y
    );

    /**
     * 品牌公寓/小区获取
     * @return
     */
    @ApiOperation(value = "MK品牌公寓/小区所有信息接口", notes = "MK品牌公寓/小区所有信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "communityName", value = "小区/公寓名称"),
            @ApiImplicitParam(name = "cityCode", value = "城市编号"),
            @ApiImplicitParam(name = "areaCode", value = "县区编号")
    })
    @PostMapping(value = "/mk/api/apart/list")
    public ResultJSON<?> list(
            @RequestParam(required = false, value = "communityName") String communityName,
            @RequestParam(required = false, value = "cityCode") String cityCode,
            @RequestParam(required = false, value = "areaCode") String areaCode
    );

    /**
     * 品牌公寓/小区分页获取
     * @param limit
     * @param row
     * @return
     */
//    @ApiOperation(value = "MK品牌公寓/小区分页接口", notes = "MK品牌公寓/小区添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "limit", value = "起始页"),
            @ApiImplicitParam(name = "row", value = "行数")
    })
//    @PostMapping(value = "/mk/api/apart/page")
    public ResultJSON<?> page(
            @RequestParam(required = false, value = "userId") String userId,
            @RequestParam(required = false, value = "limit") Integer limit,
            @RequestParam(required = false, value = "row") Integer row);

    /**
     * 品牌公寓/小区分页删除
     *
     * @param id
     * @return
     */
//    @ApiOperation(value = "MK品牌公寓/小区删除接口", notes = "MK品牌公寓/小区删除接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "ID"),
//    })
//    @PostMapping(value = "/mk/api/apart/del")
    @Transactional(rollbackFor = Exception.class)
    public ResultJSON<?> del(@RequestParam(required = true, value = "id") Integer id);


    /**
     * 品牌公寓/小区修改
     *
     * @param ftype
     * @param name
     * @param creatCode
     * @return
     */
//    @ApiOperation(value = "MK品牌公寓/小区添加接口", notes = "MK品牌公寓/小区添加接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "设施编号"),
//            @ApiImplicitParam(name = "ftype", value = "设施类型"),
//            @ApiImplicitParam(name = "name", value = "设施名"),
//            @ApiImplicitParam(name = "creatCode", value = "创建人")
//    })
//    @Transactional(rollbackFor = Exception.class)
//    @PostMapping(value = "/mk/api/apart/edit")
    public ResultJSON<?> edit(
            @RequestParam("id") Integer id,
            @RequestParam("ftype") Integer ftype,
            @RequestParam("name") String name,
            @RequestParam("creatCode") String creatCode);

}
