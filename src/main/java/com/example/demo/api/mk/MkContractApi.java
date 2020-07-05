package com.example.demo.api.mk;

import com.example.demo.json.ResultJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;

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
//            @ApiImplicitParam(name = "fileCode", value = "文件ID"),
//            @ApiImplicitParam(name = "userId", value = "用户ID"),
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/mk/api/ht/gen")
    public ResultJSON<?> gen();

//    /**
//     * 合同获取
//     * @return
//     */
//    @ApiOperation(value = "MK合同所有信息接口", notes = "MK合同所有信息接口")
//    @ApiImplicitParams({})
//    @PostMapping(value = "/mk/api/apart/list")
//    public ResultJSON<?> list();
//
//    /**
//     * 合同分页获取
//     * @param limit
//     * @param row
//     * @return
//     */
//    @ApiOperation(value = "MK合同分页接口", notes = "MK合同添加接口")
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
