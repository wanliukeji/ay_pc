package com.example.demo.api;

import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 10:50
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
//@Api(value = "菜单模块", description = "菜单模块")
public interface MenuApi {

//    @GetMapping(value = "/api/menu/getPage", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "分页数据接口", notes = "分页数据接口")
    @Transactional
    public ResultJSON<?> getByPage(ReqParam param) throws Exception;

//    @GetMapping(value = "/api/menu/export",  produces = "text/plain;charset=UTF-8")
    @ApiOperation(value = "导出数据接口", notes = "导出数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "ids")
    })
    public Object export(@RequestParam("ids") String ids) throws Exception;

//    @GetMapping(value = "/api/menu/delete", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "删除数据接口", notes = "删除数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "ids")
    })
    @Transactional
    public ResultJSON<?> delete(@RequestParam("ids") String ids) throws Exception;

//    @GetMapping(value = "/api/menu/aunt", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "审核数据接口", notes = "审核数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "ids")
    })
    @Transactional
    public ResultJSON<?> aunt(@RequestParam("ids") String ids) throws Exception;

//    @GetMapping(value = "/api/menu/unaunt", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "反审数据接口", notes = "反审数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "ids")
    })
    @Transactional
    public ResultJSON<?> unaunt(@RequestParam("ids") String ids) throws Exception;

//    @PostMapping(value = "/api/menu/import")
    @ApiOperation(value = "数据导入接口", notes = "数据导入接口")
    @Transactional
    public ApiJSON import_file(@RequestParam("fileName") MultipartFile file) throws Exception;
}
