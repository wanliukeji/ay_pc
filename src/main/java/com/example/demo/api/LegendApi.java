package com.example.demo.api;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.aspect.Log;
import com.example.demo.entity.Legend;
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
 * @describe 传说接口
 */
public interface LegendApi extends Serializable {

    /**
     * 获取数据
     * @param id
     * @return
     */
    @GetMapping("/api/legend/getInfo")
    public ResultJSON<Legend> getInfo(@RequestParam("id") String id);

    /**
     * 保存数据
     * @param info
     * @return
     */
    @PostMapping("/api/legend/save")
    @Log(value = "保存视频")
//    @Transactional
    public ResultJSON<Boolean> save(@RequestBody Legend info);

    /**
     * 修改数据
     * @param entity
     * @return
     */
    @PostMapping("/api/legend/edit")
    @Transactional
    public ResultJSON<Legend> edit(@RequestBody Legend entity);

    /**
     * 删除数据
     * @param ids
     * @return
     */
    @PostMapping("/api/legend/delete")
    @Transactional
    public ResultJSON<Legend> delete(@RequestParam("ids") List<?> ids);

    @PostMapping("/api/legend/list")
    @Log(value = "获取视频列表")
    public ResultJSON<List<Legend>> getVideoList(@RequestParam("ids") List<?> ids);

    @PostMapping("/api/legend/getPage")
    public ResultJSON<IPage<Legend>> getPage(@RequestBody ReqParam param);
}
