package com.example.demo.dao.mk;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.mk.MkRental;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/24 10:44
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */

@Mapper
public interface MkRentalMapper extends BaseMapper<MkRental> {
}
