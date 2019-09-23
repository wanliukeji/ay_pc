package com.example.demo.controller.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 控制器 页面切换控制器
 */
@Api(value = "检查模板操作", description = "检查模板操作")
@Controller
public class PublicController {

    /**
     * 登录
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "ay/home";
    }

    /**
     * 登录
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "ay/login";
    }

    /**
     * 注册
     *
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return "ay/register";
    }

    /*
     *首页
     */
    @GetMapping("/home")
    public String blog() {
        return "ay/home";
    }

    /**
     * 广告滑动页
     *
     * @return
     */
    @GetMapping("/img")
    public String record() {
        return "ay/imgModel";
    }

    /**
     * 传说
     *
     * @return
     */
    @GetMapping("/group")
    public String legend() {
        return "ay/group";
    }

    /**
     * 公司详情
     *
     * @return
     */
    @GetMapping("/info")
    public String story() {
        return "ay/info";
    }

    /**
     * 企业
     *
     * @return
     */
    @GetMapping("/company")
    public String company() {
        return "ay/company";
    }

}
