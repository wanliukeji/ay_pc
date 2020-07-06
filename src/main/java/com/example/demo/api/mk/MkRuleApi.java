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
 * 规则/协议接口
 */

@Api(value = "MK规则/协议模块", description = "MK规则/协议模块")
public interface MkRuleApi extends Serializable {

    /**
     * 规则/协议添加
     * @return
     */
    @ApiOperation(value = "MK规则/协议添加接口", notes = "MK规则/协议添加接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ftype", value = "(1 协议 2 规则  3 某某协议 4 隐私政策)"),
            @ApiImplicitParam(name = "userId", value = "创建人"),
            @ApiImplicitParam(name = "details", value = "规则/协议详情"),
            @ApiImplicitParam(name = "title", value = "标题")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/rule/add")
    public ResultJSON<?> add(@RequestParam(required = false, value = "ftype") Integer ftype,
                             @RequestParam(required = false, value = "userId") String userId,
                             @RequestParam(required = false, value = "details") String details,
                             @RequestParam(required = false, value = "title") String title
    );

//    /**
//     * 规则/协议获取
//     * @return
//     */
//    @ApiOperation(value = "MK规则/协议所有信息接口", notes = "MK规则/协议所有信息接口")
//    @ApiImplicitParams({})
//    @PostMapping(value = "/mk/api/apart/list")
//    public ResultJSON<?> list();

    /**
     * 规则/协议获取
     * @return
     */
    @ApiOperation(value = "MK规则/协议获取接口", notes = "MK规则/协议获取接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ftype", value = "类型(1 协议 2 规则  3 某某协议 4 隐私政策)")
    })
    @PostMapping(value = "/mk/api/rule/list")
    public ResultJSON<?> list(
            @RequestParam(required = false, value = "ftype") Integer ftype
    );
//
//    /**
//     * 规则/协议分页删除
//     *
//     * @param id
//     * @return
//     */
//    @ApiOperation(value = "MK规则/协议删除接口", notes = "MK规则/协议删除接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "ID"),
//    })
//    @PostMapping(value = "/mk/api/apart/del")
//    @Transactional(rollbackFor = Exception.class)
//    public ResultJSON<?> del(@RequestParam(required = true, value = "id") Integer id);
//
//
//    /**
//     * 规则/协议修改
//     *
//     * @param ftype
//     * @param name
//     * @param creatCode
//     * @return
//     */
//    @ApiOperation(value = "MK规则/协议添加接口", notes = "MK规则/协议添加接口")
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
