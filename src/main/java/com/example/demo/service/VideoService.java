package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.VideoMapper;
import com.example.demo.entity.Video;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 16:56
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 视频实现业务类
 */
@Service
@Slf4j
public class VideoService extends ServiceImpl<VideoMapper, Video> implements Serializable {
    @Autowired
    private VideoMapper mapper;
}
