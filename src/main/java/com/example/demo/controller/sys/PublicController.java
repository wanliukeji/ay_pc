package com.example.demo.controller.sys;

import io.swagger.annotations.Api;
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
     * 接口测试
     */

    @GetMapping("/")
    public String initial() {
        return "ay/about";
    }



    /**
     * 登录
     *
     * @return
     */
//    @GetMapping("/")
//    public String index() {
//        return "ay/home";
//    }
//
//    /**
//     * 登录
//     *
//     * @return
//     */
//    @GetMapping("/login")
//    public String login() {
//        return "ay/login";
//    }
//
//    /**
//     * 注册
//     *
//     * @return
//     */
//    @GetMapping("/register")
//    public String register() {
//        return "ay/register";
//    }
//
//    /*
//     *首页
//     */
//    @GetMapping("/home")
//    public String blog() {
//        return "ay/home";
//    }
//
//    /**
//     * 广告滑动页
//     *
//     * @return
//     */
//    @GetMapping("/img")
//    public String record() {
//        return "ay/imgModel";
//    }
//
//    /**
//     * 筛选页
//     *
//     * @return
//     */
//    @GetMapping("/group")
//    public String legend() {
//        return "ay/group";
//    }
//
//    /**
//     * 服务内容页
//     *
//     * @return
//     */
//    @GetMapping("/info")
//    public String story() {
//        return "ay/info";
//    }
//
//    /**
//     * 企业
//     *
//     * @return
//     */
//    @GetMapping("/company")
//    public String company() {
//        return "ay/company";
//    }
//
//    /**
//     * 加工安装
//     *
//     * @return
//     */
//    @GetMapping("/release")
//    public String release() {
//        return "ay/release";
//    }
//
//    /**
//     * 材料配送
//     *
//     * @return
//     */
//    @GetMapping("/relPs")
//    public String relPs() {
//        return "ay/relPs";
//    }
//
//    /**
//     * 出租转让
//     *
//     * @return
//     */
//    @GetMapping("/relCz")
//    public String relCz() {
//        return "ay/rent";
//    }
//
//    /**
//     * 设备出租转让
//     *
//     * @return
//     */
//    @GetMapping("/relSb")
//    public String relSb() {
//        return "ay/relSb";
//    }
//
//    /**
//     * 出租转让
//     *
//     * @return
//     */
//    @GetMapping("/info_cz")
//    public String info_cz() {
//        return "ay/info_cz";
//    }
//
//    /**
//     * 材料配送
//     *
//     * @return
//     */
//    @GetMapping("/info_cl")
//    public String info_cl() {
//        return "ay/info_cl";
//    }
//
//    /**
//     * 设备
//     *
//     * @return
//     */
//    @GetMapping("/info_sb")
//    public String info_sb() {
//        return "ay/info_sb";
//    }
//
//    /**
//     * 信息发布页
//     *
//     * @return
//     */
//    @GetMapping("/menu")
//    public String msg() {
//        return "bg/html/system/menu";
//    }
//
//    /**
//     * 信息发布页
//     *
//     * @return
//     */
//    @GetMapping("/mine")
//    public String mine() {
//        return "ay/mine";
//    }
//
//    /**
//     * 聊天
//     *
//     * @return
//     */
//    @GetMapping("/msg")
//    public String menu() {
//        return "ay/message";
//    }
//
//    /**
//     * 聊天 xiaohua
//     * @return
//     */
//    @GetMapping("/xiaohua")
//    public String xiaohua() {
//        return "socket/xiaohua";
//    }
//
//    /**
//     * 聊天 xiaoMing
//     * @return
//     */
//    @GetMapping("/xiaoMing")
//    public String xiaoMing() {
//        return "socket/xiaoMing";
//    }
//
//    /**
//     * 材料商
//     * @return
//     */
//    @GetMapping("/relcl")
//    public String relcl() {
//        return "ay/relcl";
//    }
//
//    /**
//     * 转让
//     * @return
//     */
//    @GetMapping("/zr")
//    public String zr() {
//        return "ay/zr";
//    }
//
//    /**
//     * VIP视频解析
//     * @return
//     */
    @GetMapping("/video")
    public String video() {
        return "ay/video";
    }
//
//    /**
//     * 展示页面
//     * @return
//     */
    @GetMapping("/about")
    public String about() {
        return "ay/about";
    }
//
//    /**
//     * 蜘蛛页面
//     * @return
//     */
//    @GetMapping("/zz")
//    public String zz() {
//        return "ay/zz";
//    }
//
//    /**
//     * 测试页面
//     * @return
//     */
//    @GetMapping("/model")
//    public String model() {
//        return "other/model";
//    }
}
