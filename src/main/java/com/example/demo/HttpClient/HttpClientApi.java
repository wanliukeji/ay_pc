package com.example.demo.HttpClient;

import com.example.demo.entity.Fied;
import com.example.demo.json.ApiJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/10/19 14:22
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Api(value = "爬虫模块", description = "爬虫模块")
public interface HttpClientApi {

    @PostMapping(value = "/api/http/getHtml", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "爬虫数据保存接口", notes = "爬虫数据保存接口")
    @Transactional
    public ApiJSON saveContext(@RequestParam("url") String url) throws Exception;
}
