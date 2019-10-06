package com.example.demo.controller;

import com.example.demo.api.FiedApi;
import com.example.demo.api.MenuApi;
import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import com.example.demo.service.FiedService;
import com.example.demo.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 10:45
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@RestController
@Slf4j
public class MenuController implements MenuApi {

    @Autowired
    private MenuService menuService;

    public ResultJSON<?> getByPage(ReqParam param) throws Exception {
        return ResultJSON.success(menuService.getByPage(param));
    }

    @Override
    public Object export(String ids) throws Exception {
        String fileUrl = "D:/exportFile/加工信息数据表.xls";
        String title = "加工信息导出";
        String path = "com.example.demo.entity.Fied";
        String[] columnNames = {"", "编号", "标题", "类型", "区域", "QQ", "微信", "手机",
                "联系人", "创建时间", "公司名称", "省份", "城市", "市区", "视频路径",
                "广告地址", "作品一", "作品二", "作品三", "案例一", "案例二", "案例三",
                "服务", "详细地址"};
        menuService.export(ids, fileUrl, title, path, columnNames);
        return null;
    }

    @Override
    public ResultJSON<?> delete(String ids) throws Exception {
        return menuService.delete(ids);
    }

    @Override
    public ResultJSON<?> aunt(String ids) throws Exception {
        return menuService.aunt(ids);
    }

    @Override
    public ResultJSON<?> unaunt(String ids) throws Exception {
        return menuService.unaunt(ids);
    }

    @Override
    public ApiJSON import_file(MultipartFile file) throws Exception {
        return menuService.import_file(file);
    };
}

