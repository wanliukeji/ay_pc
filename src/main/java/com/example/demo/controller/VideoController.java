package com.example.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Utils.StringUtil;
import com.example.demo.api.VideoApi;
import com.example.demo.entity.Video;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import com.example.demo.service.FileService;
import com.example.demo.service.VideoService;
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
 * @describe 视频控制器
 */
@RestController
@Slf4j
public class VideoController implements VideoApi {

    @Autowired
    private VideoService videoService;

    @Autowired
    private FileService fileService;

    @Override
    public ResultJSON<Video> getInfo(String id) {
        try {
            if (StringUtil.isNotEmty(id)){
                Video video = videoService.getById(id);
                if (null != video){
                    return ResultJSON.success(video);
                }
                return ResultJSON.error(CodeMsg.REQUEST_ERROR);
            }
        } catch (Exception ex) {
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
        return null;
    }

    @Override
    public ResultJSON<Boolean> save(Video info) {
        Boolean flag = false;
        try {
            if (StringUtil.isNotEmty(info)){
//                info.setShare(0l);
//                info.setLike(0l);
//                info.setDelState(0);
                info.setCreateTime(new Date());
//                info.setEnable(1);
//                info.setGrade(0);
                flag = videoService.save(info);
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
    public ResultJSON<Boolean> edit(Video video) {
        Boolean flag = false;
        try {
            if (StringUtil.isNotEmty(video)){
//                info.setShare(0l);
//                info.setLike(0l);
//                info.setDelState(0);
                video.setUpdateTime(new Date());
//                info.setEnable(1);
//                info.setGrade(0);
                flag = videoService.saveOrUpdate(video);
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
    public ResultJSON<Boolean> delete(List<?> ids) {
        return null;
    }

    @Override
    public ResultJSON<Boolean> delete(String id) {
        try {
            boolean flag = videoService.removeById(id);
            return ResultJSON.success(flag);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }

    @Override
    public ResultJSON<List<Video>> getVideoList(List<?> ids) {
        return null;
    }

    @Override
    public ResultJSON<IPage<Video>> getPage(ReqParam param) {
        try {
            IPage<Video> page = new Page<Video>(param.getPageNo() + 1, param.getPageSize() + 1);
            QueryWrapper<Video> qw = new QueryWrapper<Video>();
            qw.like("vname",param.getContext()).or()
                    .like("actor",param.getContext()).or()
                    .like("direct",param.getContext());
            page = videoService.page(page ,qw);
            ((Page<Video>) page).setDesc("createTime");
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }
}
