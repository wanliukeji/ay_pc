package com.example.demo.controller.sys;

import com.example.demo.Utils.ClassUtil;
import com.example.demo.json.ApiJSON;
import com.example.demo.pojoUtil.Service;
import com.example.demo.pojoUtil.Type;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 异常控制器
 */
@RestController
//@Api(value = "类属性获取模块", description = "类属性获取接口")
public class ItemsController {

//    @GetMapping("/getClassField_fw")
    public ApiJSON getClassField_fw() {
        try {
            List<?> list = ClassUtil.getFieldValueByNameToList(new Service());
            return ApiJSON.data(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ApiJSON.error(null);
        }

    }

//    @GetMapping("/getClassField_ty")
    public ApiJSON getClassField_ty() {
        try {
            List<?> list = ClassUtil.getFieldValueByNameToList(new Type());
            return ApiJSON.data(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ApiJSON.error(null);
        }

    }
}
