package com.example.demo.api;

import com.example.demo.entity.Fied;
import com.example.demo.json.ApiJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(value = "发布信息模块", description = "发布信息接口")
public interface RelApi {

    @PostMapping(value = "/api/import/jg")
    @ApiOperation(value = "加工数据导入接口", notes = "加工数据导入接口")
    @Transactional
    public ApiJSON import_jg(@RequestParam("fileName") MultipartFile file) throws Exception;
}
