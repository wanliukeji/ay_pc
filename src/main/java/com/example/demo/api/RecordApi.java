package com.example.demo.api;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.aspect.Log;
import com.example.demo.entity.Record;
import com.example.demo.entity.Video;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import oracle.jrockit.jfr.openmbean.RecordingType;
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
 * @describe 档案接口
 */
public interface RecordApi extends Serializable {

    /**
     * 获取数据
     * @param id
     * @return
     */
    @GetMapping("/api/record/getInfo")
    public ResultJSON<Record> getInfo(@RequestParam("id") String id);

    /**
     * 保存数据
     * @param info
     * @return
     */
    @PostMapping("/api/record/save")
    @Log(value = "保存视频")
//    @Transactional
    public ResultJSON<Boolean> save(@RequestBody Record info);

    /**
     * 修改数据
     * @param entity
     * @return
     */
    @PostMapping("/api/record/edit")
    @Transactional
    public ResultJSON<Record> edit(@RequestBody Record entity);

    /**
     * 删除数据
     * @param ids
     * @return
     */
    @PostMapping("/api/record/delete")
    @Transactional
    public ResultJSON<Record> delete(@RequestParam("ids") List<?> ids);

    @PostMapping("/api/record/list")
    @Log(value = "获取视频列表")
    public ResultJSON<List<Record>> getVideoList(@RequestParam("ids") List<?> ids);

    @PostMapping("/api/record/getPage")
    public ResultJSON<IPage<Record>> getPage(@RequestBody ReqParam param);
}
