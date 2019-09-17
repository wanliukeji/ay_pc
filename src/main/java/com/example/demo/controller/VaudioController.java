package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Utils.StringUtil;
import com.example.demo.api.VaudioApi;
import com.example.demo.api.VfileApi;
import com.example.demo.entity.Vaudio;
import com.example.demo.entity.Vfile;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import com.example.demo.service.VaudioService;
import com.example.demo.service.VfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 音频控制器
 */
@RestController
@Slf4j
public class VaudioController implements VaudioApi {

    @Autowired
    private VaudioService service;

    @Override
    public ResultJSON<Vaudio> getInfo(String id) {
        try {
            if (StringUtil.isNotEmty(id)){
                Vaudio entity = service.getById(id);
                if (null != entity){
                    return ResultJSON.success(entity);
                }
                return ResultJSON.error(CodeMsg.REQUEST_ERROR);
            }
        } catch (Exception ex) {
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
        return null;
    }

    @Override
    public ResultJSON<List<Vaudio>> getVaudioList() {
        try {
            List<Vaudio> vaudios = service.list(null);
            if (vaudios.size() > 0){
                return ResultJSON.success(vaudios);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
        return ResultJSON.error(CodeMsg.SESSION_ERROR);
    }

    @Override
    public ResultJSON<IPage<Vaudio>> getPage(ReqParam param) {
        try {
            IPage<Vaudio> page = new Page<Vaudio>(param.getPageNo() + 1, param.getPageSize() + 1);
            QueryWrapper<Vaudio> qw = new QueryWrapper<Vaudio>();
            qw.like("vname",param.getContext()).or()
                    .like("actor",param.getContext()).or()
                    .like("context",param.getContext());
            page = service.page(page ,qw);
            ((Page<Vaudio>) page).setDesc("createTime");
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }

}
