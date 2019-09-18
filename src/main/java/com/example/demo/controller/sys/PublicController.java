package com.example.demo.controller.sys;

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
     * 音频
     *
     * @return
     */
    @GetMapping("/audio")
    public String audio() {
        return "audio";
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

    /**
     * 信息
     *
     * @return
     */
    @GetMapping("/message")
    public String message() {
        return "message";
    }

    /**
     * 发布信息
     *
     * @return
     */
    @GetMapping("/release")
    public String release_a() {
        return "ay/release";
    }

    /**
     * 好友列表
     *
     * @return
     */
    @GetMapping("/master")
    public String master() {
        return "ay/master";
    }

    /**
     * 测试
     * @return
     */
    @GetMapping("/mine")
    public String boxed() {
        return "ay/mine";
    }

    /**
     * @return
     */
    @GetMapping("/vtable")
    public String vtable() {
        return "vtable";
    }

    /**
     * @return
     */
    @GetMapping("/rtable")
    public String rtable() {
        return "rtable";
    }

    /**
     * @return
     */
    @GetMapping("/ltable")
    public String ltable() {
        return "ltable";
    }

    /**
     * @return
     */
    @GetMapping("/atable")
    public String atable() {
        return "atable";
    }

    /**
     * @return
     */
    @GetMapping("/stable")
    public String satable() {
        return "stable";
    }

    /**
     * @return
     */
    @GetMapping("/admin-table")
    public String admin() {
        return "model/html/admin-404";
    }


}
