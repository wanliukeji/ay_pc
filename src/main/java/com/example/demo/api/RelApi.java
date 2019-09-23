package com.example.demo.api;

import com.example.demo.entity.Classified;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 10:50
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Api(value = "发布信息模块",description = "发布信息接口")
public interface RelApi {

    @PostMapping("/api/saveRel")
    @ApiOperation(value = "信息发布保存数据接口",notes = "信息发布保存数据接口",response = String.class)
    public Boolean saveRel(
            @ApiParam
            @RequestBody Classified entity) throws Exception;

}
