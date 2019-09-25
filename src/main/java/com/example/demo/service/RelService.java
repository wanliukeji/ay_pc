package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.FiedMapper;
import com.example.demo.entity.Fied;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 11:03
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Service
public class RelService extends ServiceImpl<FiedMapper, Fied> implements Serializable {

}
