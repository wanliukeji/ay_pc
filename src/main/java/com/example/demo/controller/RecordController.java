package com.example.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.api.RecordApi;
import com.example.demo.entity.Record;
import com.example.demo.entity.Story;
import com.example.demo.entity.SysUser;
import com.example.demo.entity.Vfile;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import com.example.demo.service.RecordService;
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
 * @describe 档案控制器
 */
@RestController
@Slf4j
public class RecordController implements RecordApi {

    @Autowired
    private RecordService service;

    @Override
    public ResultJSON<Record> getInfo(String id) {
        return null;
    }

    @Override
    public ResultJSON<Boolean> save(Record info) {
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
    public ResultJSON<Record> edit(Record entity) {
        return null;
    }

    @Override
    public ResultJSON<Record> delete(List<?> ids) {
        return null;
    }

    @Override
    public ResultJSON<List<Record>> getVideoList(List<?> ids) {
        try {
            List<Record> records = service.list(null);
            if (records.size() > 0){
                return ResultJSON.success(records);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
        return ResultJSON.error(CodeMsg.SESSION_ERROR);
    }

    @Override
    public ResultJSON<IPage<Record>> getPage(ReqParam param) {
        try {
            IPage<Record> page = new Page<Record>(param.getPageNo() + 1, param.getPageSize() + 1);
            QueryWrapper<Record> qw = new QueryWrapper<Record>();
            qw.like("vname",param.getContext()).or()
                    .like("author",param.getContext()).or()
                    .like("context",param.getContext()).or()
                    .like("url",param.getContext());
            page = service.page(page ,qw);
            ((Page<Record>) page).setDesc("createTime");
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }
}
