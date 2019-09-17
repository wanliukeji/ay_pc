package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.LegendMapper;
import com.example.demo.dao.StoryMapper;
import com.example.demo.entity.Legend;
import com.example.demo.entity.Story;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 16:56
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 故事实现业务类
 */
@Service
@Slf4j
public class StoryService extends ServiceImpl<StoryMapper, Story> implements Serializable {

}
