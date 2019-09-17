package com.example.demo.api;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.aspect.Log;
import com.example.demo.entity.Vfile;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
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
public interface VfileApi extends Serializable {

    /**
     * 获取数据
     * @param id
     * @return
     */
    @GetMapping("/api/vfile/getInfo")
    public ResultJSON<Vfile> getInfo(@RequestParam("id") String id);

    @PostMapping("/api/vfile/list")
    @Log(value = "获取视频列表")
    public ResultJSON<List<Vfile>> getvfileList();

    @PostMapping("/api/vfile/getPage")
    public ResultJSON<IPage<Vfile>> getPage(@RequestBody ReqParam param);

}
