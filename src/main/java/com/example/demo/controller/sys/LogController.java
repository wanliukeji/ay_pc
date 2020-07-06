package com.example.demo.controller.sys;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 日志控制器
 */
/*

@RestController
@Slf4j
@Api(value = "日志模块", description = "日志接口")
public class logController {

    @GetMapping("/login")
    public ModelAndView login() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>LOGIN<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return new ModelAndView("login");
    }

    @GetMapping(value = "/login/getDate")
    @Transactional
    public ApiJSON<SysUser> getDate(@RequestParam String name) {
        try {
            SysUser user = new SysUser();
            user.setPassword("111111111");
            user.setAccount("admin");
            user.setName("chenny");
            return ApiJSON.data(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
            return ApiJSON.error("错啦");
        }
    }
}
*/
