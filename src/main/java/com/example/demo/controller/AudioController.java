package com.example.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.api.AudioApi;
import com.example.demo.entity.Audio;
import com.example.demo.entity.Legend;
import com.example.demo.entity.SysUser;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import com.example.demo.service.AudioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;
import java.util.Date;
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
public class AudioController implements AudioApi {

    @Autowired
    private AudioService service;

    @Override
    public ResultJSON<Audio> getInfo(String id) {
        return null;
    }

    @Override
    public ResultJSON<Boolean> save(Audio info) {
        Boolean flag = false;
        try {
            if (StringUtil.isNotEmty(info)){
                info.setCreateTime(new Date());
                SysUser user = HttpServletRequestUtil.getSessionUser();
                if (null != user){
                    info.setUserid(user.getId());
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
    public ResultJSON<Audio> edit(Audio entity) {
        return null;
    }

    @Override
    public ResultJSON<Audio> delete(List<?> ids) {
        return null;
    }

    @Override
    public ResultJSON<List<Audio>> getVideoList(List<?> ids) {
        try {
            List<Audio> stories = service.list(null);
            if (stories.size() > 0){
                return ResultJSON.success(stories);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
        return ResultJSON.error(CodeMsg.SESSION_ERROR);
    }

    @Override
    public ResultJSON<IPage<Audio>> getPage(ReqParam param) {
        try {
            IPage<Audio> page = new Page<Audio>(param.getPageNo() + 1, param.getPageSize() + 1);
            QueryWrapper<Audio> qw = new QueryWrapper<Audio>();
            qw.like("vname",param.getContext()).or()
                    .like("actor",param.getContext()).or()
                    .like("direct",param.getContext());
            page = service.page(page ,qw);
            ((Page<Audio>) page).setDesc("createTime");
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }
}
