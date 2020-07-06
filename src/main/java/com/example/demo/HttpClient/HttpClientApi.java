package com.example.demo.HttpClient;

//import com.example.demo.entity.Fied;
import com.example.demo.json.ApiJSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/10/19 14:22
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
//@Api(value = "爬虫模块", description = "爬虫教程: 先进入58同城发布详细页面在复制该页面路径,再点击查看号码并分别复制于输入框内")
public interface HttpClientApi {

//    @GetMapping(value = "/api/http/get_WB", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "爬虫数据保存接口", notes = "爬虫数据保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reqUrl", value = "请输入抓取路径"),
            @ApiImplicitParam(name = "phone", value = "请输入对方联系号码"),
            @ApiImplicitParam(name = "type", value = "请输入自定义类型,默认加工类型")
    })
//    public ApiJSON get_WB(@RequestParam("phone") String phone, @RequestParam(name = "type", required = false) String type) throws Exception;
    public ApiJSON get_WB(@RequestParam("reqUrl") String reqUrl, @RequestParam("phone") String phone, @RequestParam(name = "type", required = false) String type) throws Exception;
}
