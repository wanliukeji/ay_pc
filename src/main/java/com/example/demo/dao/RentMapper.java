package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Fied;
import com.example.demo.entity.Rent;
import com.example.demo.vo.FiedVo;
import com.example.demo.vo.RentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 17:53    ·
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Mapper
public interface RentMapper extends BaseMapper<Rent> {

    List<Rent> getByPage(@Param("currentPage") Integer currentPage,
                         @Param("pageSize") Integer pageSize,
                         @Param("context") String context);

    RentVo getInfo(@Param("id") Integer id);

    List<RentVo> getRentInfoVos(@Param("type") String type);
}
