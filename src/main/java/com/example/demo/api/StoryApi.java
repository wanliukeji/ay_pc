package com.example.demo.api;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.aspect.Log;
import com.example.demo.entity.Story;
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
 * @describe 故事接口
 */
public interface StoryApi extends Serializable {

    /**
     * 获取数据
     * @param id
     * @return
     */
    @GetMapping("/api/story/getInfo")
    public ResultJSON<Story> getInfo(@RequestParam("id") String id);

    /**
     * 保存数据
     * @param info
     * @return
     */
    @PostMapping("/api/story/save")
    @Log(value = "保存视频")
//    @Transactional
    public ResultJSON<Boolean> save(@RequestBody Story info);

    /**
     * 修改数据
     * @param entity
     * @return
     */
    @PostMapping("/api/story/edit")
    @Transactional
    public ResultJSON<Story> edit(@RequestBody Story entity);

    /**
     * 删除数据
     * @param ids
     * @return
     */
    @PostMapping("/api/story/delete")
    @Transactional
    public ResultJSON<Story> delete(@RequestParam("ids") List<?> ids);

    @PostMapping("/api/story/list")
    @Log(value = "获取视频列表")
    public ResultJSON<List<Story>> getVideoList(@RequestParam("ids") List<?> ids);

    @PostMapping("/api/story/getPage")
    public ResultJSON<IPage<Story>> getPage(@RequestBody ReqParam param);

}
