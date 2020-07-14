package com.example.demo.dao.mk;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.mk.MkApartment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/24 10:44
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */

@Mapper
public interface MkApartMapper extends BaseMapper<MkApartment> {

    List<Map<String, Object>> getByGroup();
}
