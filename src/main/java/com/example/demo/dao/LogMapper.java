package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.SysLog;
import org.springframework.stereotype.Component;


/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 日志接口
 */
@Component
public interface LogMapper extends BaseMapper<SysLog> {

}
