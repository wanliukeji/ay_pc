package com.example.demo.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Fied;
import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 10:50
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Api(value = "加工信息模块", description = "加工信息模块")
public interface FiedApi {

    @GetMapping(value = "/api/jg/getPage", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "加工信息数据接口", notes = "加工信息数据接口")
    @Transactional
    public ResultJSON<?> getByPage(ReqParam param) throws Exception;

    @GetMapping(value = "/api/jg/export", produces = "text/plain;charset=UTF-8")
    @ApiOperation(value = "加工导出数据接口", notes = "加工导出数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "ids")
    })
    public Object export(@RequestParam("ids") String ids) throws Exception;

    @GetMapping(value = "/api/jg/delete", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "加工删除数据接口", notes = "加工删除数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "ids")
    })
    @Transactional
    public ResultJSON<?> delete(@RequestParam("ids") String ids) throws Exception;

    @GetMapping(value = "/api/jg/aunt", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "加工审核数据接口", notes = "加工审核数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "ids")
    })
    @Transactional
    public ResultJSON<?> aunt(@RequestParam("ids") String ids) throws Exception;

    @GetMapping(value = "/api/jg/unaunt", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "加工反审数据接口", notes = "加工反审数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "ids")
    })
    @Transactional
    public ResultJSON<?> unaunt(@RequestParam("ids") String ids) throws Exception;

    @GetMapping(value = "/api/fied/getVos", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "查询临时数据接口", notes = "查询临时数据接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ID", value = "ids")
    })
    public ResultJSON<?> getVos(@RequestParam(name = "type") String type) throws Exception;
}
