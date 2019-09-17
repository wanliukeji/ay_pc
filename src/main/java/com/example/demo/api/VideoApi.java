package com.example.demo.api;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.aspect.Log;
import com.example.demo.entity.Video;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 视频接口
 */
public interface VideoApi extends Serializable {

    /**
     * 获取数据
     * @param id
     * @return
     */
    @GetMapping("/api/video/getInfo")
    public ResultJSON<Video> getInfo(@RequestParam("id") String id);

    /**
     * 保存数据
     * @param info
     * @return
     */
    @PostMapping("/api/video/save")
    @Log(value = "保存视频")
//    @Transactional
    public ResultJSON<Boolean> save(@RequestBody Video info);

    /**
     * 修改数据
     * @param video
     * @return
     */
    @PostMapping("/api/video/edit")
    @Transactional
    public ResultJSON<Boolean> edit(@RequestBody Video video);

    /**
     * 删除数据
     * @param ids
     * @return
     */
    @PostMapping("/api/video/deletes")
    @Transactional
    public ResultJSON<Boolean> delete(@RequestParam("ids") List<?> ids);

    @PostMapping("/api/video/list")
    @Log(value = "获取视频列表")
    public ResultJSON<List<Video>> getVideoList(@RequestParam("ids") List<?> ids);

    @PostMapping("/api/video/getPage")
    public ResultJSON<IPage<Video>> getPage(@RequestBody ReqParam param);

    /**
     * 删除数据
     * @param id
     * @return
     */
    @PostMapping("/api/video/delete")
    @Transactional
    public ResultJSON<Boolean> delete(@RequestParam("id") String id);
}
