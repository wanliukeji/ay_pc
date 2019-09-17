package com.example.demo.api;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.aspect.Log;
import com.example.demo.entity.Audio;
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
 * @describe 音频接口
 */
public interface AudioApi extends Serializable {

    /**
     * 获取数据
     * @param id
     * @return
     */
    @GetMapping("api/audio/getInfo")
    public ResultJSON<Audio> getInfo(@RequestParam("id") String id);

    /**
     * 保存数据
     * @param info
     * @return
     */
    @PostMapping("api/audio/save")
    @Log(value = "保存视频")
//    @Transactional
    public ResultJSON<Boolean> save(@RequestBody Audio info);

    /**
     * 修改数据
     * @param entity
     * @return
     */
    @PostMapping("api/audio/edit")
    @Transactional
    public ResultJSON<Audio> edit(@RequestBody Audio entity);

    /**
     * 删除数据
     * @param ids
     * @return
     */
    @PostMapping("api/audio/delete")
    @Transactional
    public ResultJSON<Audio> delete(@RequestParam("ids") List<?> ids);

    @PostMapping("api/audio/list")
    @Log(value = "获取视频列表")
    public ResultJSON<List<Audio>> getVideoList(@RequestParam("ids") List<?> ids);

    @PostMapping("/api/audio/getPage")
    public ResultJSON<IPage<Audio>> getPage(@RequestBody ReqParam param);

}
