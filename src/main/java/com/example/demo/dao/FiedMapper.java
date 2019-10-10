package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Fied;
import com.example.demo.vo.FiedVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
public interface FiedMapper extends BaseMapper<Fied> {

    List<Fied> getByPage(@Param("currentPage") Integer currentPage,
                         @Param("pageSize") Integer pageSize,
                         @Param("context") String context);

    List<FiedVo> getVoList(@Param("type")String type);

    FiedVo getInfoVo(@Param("id") Integer id);

    List<Fied> getPageVos(@Param("procode") Integer procode, @Param("citycode") Integer citycode,
                          @Param("countycode") Integer countycode,@Param("type") String type,
                          @Param("fw") String fw);
}
