package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.api.FiedApi;
import com.example.demo.api.RelApi;
import com.example.demo.entity.Fied;
import com.example.demo.entity.SysUser;
import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqFiedParam;
import com.example.demo.req.ReqParam;
import com.example.demo.service.FiedService;
import com.example.demo.service.RelService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
public class FiedController implements FiedApi {

    @Autowired
    private FiedService fiedService;

    @Autowired
    private RelService relService;

    public ResultJSON<?> getByPage(ReqParam param) throws Exception {
        return ResultJSON.success(fiedService.getByPage(param));
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
        fiedService.export(ids, fileUrl, title, path, columnNames);
        return null;
    }

    @Override
    public ResultJSON<?> delete(String ids) throws Exception {
        return fiedService.delete(ids);
    }

    @Override
    public ResultJSON<?> aunt(String ids) throws Exception {
        return fiedService.aunt(ids);
    }

    @Override
    public ResultJSON<?> unaunt(String ids) throws Exception {
        return fiedService.unaunt(ids);
    }

    @Override
    public ResultJSON<?> getVos(String type) throws Exception {
        return fiedService.getVos(type);
    }

    @Override
    public ResultJSON<?> getPageVos(ReqFiedParam param) throws Exception {
        return ResultJSON.success(fiedService.getPageVos(param));
    }

    @Override
    public ApiJSON saveRel(Fied fied) throws Exception {
        boolean flag = relService.save(fied);
        return ApiJSON.data(flag);
    }

}

