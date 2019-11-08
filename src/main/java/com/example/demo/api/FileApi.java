package com.example.demo.api;

import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 10:50
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Api(value = "文件信息模块", description = "文件信息模块")
public interface FileApi {

    @GetMapping(value = "/api/file/getInfos", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "文件信息数据接口", notes = "文件信息数据接口")
    @Transactional
    public ResultJSON<?> getInfos(@RequestParam("fiedId") String fiedId) throws Exception;

    @GetMapping(value = "/api/file/gethoppyList", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "文件信息数据接口", notes = "文件信息数据接口")
    @Transactional
    public ResultJSON<?> gethoppyList(@RequestParam("text") String text) throws Exception;
}
