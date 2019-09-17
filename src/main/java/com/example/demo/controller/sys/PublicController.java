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
     * 注册
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "ay/login";
    }

    /**
     * 登录
     *
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return "ay/register";
    }

    /*
     *视频
     */
    @GetMapping("/home")
    public String blog() {
        return "ay/home";
    }

    /**
     * 档案
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
     * 故事
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
     * 正文
     *
     * @return
     */
    @GetMapping("/article")
    public String article() {
        return "article";
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
     * 通知
     *
     * @return
     */
    @GetMapping("/inform")
    public String inform() {
        return "inform";
    }

    /**
     * 好友列表
     *
     * @return
     */
    @GetMapping("/friends")
    public String friends() {
        return "friends";
    }

    /**
     * 测试
     * @return
     */
    @GetMapping("/boxed")
    public String boxed() {
        return "boxed";
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
