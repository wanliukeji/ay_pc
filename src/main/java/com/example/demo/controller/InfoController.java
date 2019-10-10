package com.example.demo.controller;

import com.example.demo.json.ResultJSON;
import com.example.demo.service.FiedService;
import com.example.demo.service.FileService;
import com.example.demo.vo.FiedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 控制器 页面切换控制器
 */
@Api(value = "检查模板操作", description = "检查模板操作")
@RestController
public class InfoController {

    @Autowired
    private FiedService fiedService;

    /**
     * 服务内容页
     * @return
     */
    @GetMapping("api/fied/getInfoVo")
    public ResultJSON<?> getInfoVo(@RequestParam("id") Integer id) {
        return ResultJSON.success(fiedService.getInfoVo(id));
    }

}
