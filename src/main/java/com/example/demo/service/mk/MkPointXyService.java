package com.example.demo.service.mk;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.mk.MkPointXyMapper;
import com.example.demo.entity.mk.MkPointxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/24 10:43
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Service
@Slf4j
public class MkPointXyService extends ServiceImpl<MkPointXyMapper, MkPointxy> {


    public boolean add(MkPointxy entity) {
        return this.save(entity);
    }
}


