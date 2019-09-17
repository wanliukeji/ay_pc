package com.example.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.api.LegendApi;
import com.example.demo.entity.Legend;
import com.example.demo.entity.Record;
import com.example.demo.entity.SysUser;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import com.example.demo.service.LegendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 传说控制器
 */
@RestController
@Slf4j
public class LegendController implements LegendApi {

    @Autowired
    private LegendService service;

    @Override
    public ResultJSON<Legend> getInfo(String id) {
        return null;
    }

    @Override
    public ResultJSON<Boolean> save(Legend info) {
        Boolean flag = false;
        try {
            if (StringUtil.isNotEmty(info)){
                info.setCreateTime(new Date());
                SysUser user = HttpServletRequestUtil.getSessionUser();
                if (null != user){
                    info.setAuthor(user.getName());
                }
                flag = service.save(info);
                if (flag){
                    return ResultJSON.success(flag);
                }
                return ResultJSON.error(CodeMsg.SESSION_ERROR);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            log.info(ex.getMessage());
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
        return ResultJSON.error(CodeMsg.SESSION_ERROR);
    }

    @Override
    public ResultJSON<Legend> edit(Legend entity) {
        return null;
    }

    @Override
    public ResultJSON<Legend> delete(List<?> ids) {
        return null;
    }

    @Override
    public ResultJSON<List<Legend>> getVideoList(List<?> ids) {
        try {
            List<Legend> legends = service.list(null);
            if (legends.size() > 0){
                return ResultJSON.success(legends);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
        return ResultJSON.error(CodeMsg.SESSION_ERROR);
    }

    @Override
    public ResultJSON<IPage<Legend>> getPage(ReqParam param) {
        try {
            IPage<Legend> page = new Page<Legend>(param.getPageNo() + 1, param.getPageSize() + 1);
            QueryWrapper<Legend> qw = new QueryWrapper<Legend>();
            qw.like("vname",param.getContext()).or()
                    .like("author",param.getContext()).or()
                    .like("question",param.getContext()).or()
                    .like("context",param.getContext()).or()
                    .like("answer",param.getContext());
            page = service.page(page ,qw);
            ((Page<Legend>) page).setDesc("createTime");
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }
}
