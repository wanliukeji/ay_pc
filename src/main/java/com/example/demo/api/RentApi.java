package com.example.demo.api;

import com.example.demo.entity.Rent;
import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
import com.example.demo.vo.RentVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 10:50
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
//@Api(value = "出租/转让信息模块", description = "出租/转让信息模块")
public interface RentApi {

//    @PostMapping(value = "/api/rent/save", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "出租/转让信息发布保存数据接口", notes = "出租/转让信息发布保存数据接口")
    @Transactional
    public ApiJSON saveRent(@RequestBody Rent entity) throws Exception;

//    @GetMapping(value = "/api/rent/getRentInfoVos", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "首页数据接口", notes = "首页筛选数据接口")
    public ResultJSON<?> getRentInfoVos(@RequestParam("type") String type) throws Exception;

//    @GetMapping(value = "/api/rent/getInfo", produces = {"application/json;charset=UTF-8"})
    @ApiOperation(value = "数据接口", notes = "首页筛选数据接口")
    public ResultJSON<RentVo> getInfo(@RequestParam("id") Integer id) throws Exception;

}
