package com.example.demo.service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.LogMapper;
import com.example.demo.entity.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 16:56
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 请求实体类
 */
@Service
public class LogService extends ServiceImpl<LogMapper,SysLog> implements Serializable{

    @Autowired
    private LogMapper logMapper;

}
