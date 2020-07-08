package com.example.demo.dao.mk;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.mk.MkListing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
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
public interface MkListingMapper extends BaseMapper<MkListing>, Serializable {

//    List<Map<String, Object>> getByPage(Map<String, Object> paramMap);


    Map<String, Object> getInfo(@Param("id") Integer id);

    List<Map<String, Object>> getByPage(
            @Param("leaseType") Integer leaseType,
            @Param("areaCode") String areaCode,
            @Param("maxPrice") Integer maxPrice,
            @Param("minPrice") Integer minPrice,
            @Param("unitType") String unitType,
            @Param("limit") Integer limit,
            @Param("row") Integer row,
            @Param("longType") String longType,
            @Param("maxArea") Double maxArea,
            @Param("minArea") Double minArea,
            @Param("fidentity") Integer fidentity,
            @Param("apartmentId") Integer apartmentId,
            @Param("decoration") Integer decoration,
            @Param("jstatus") Integer jstatus,
            @Param("tstatus") Integer tstatus,
            @Param("val") String val,
            @Param("fid") Integer id
    );

    Map<String, Object> getByPage2(
            @Param("limit") Integer limit,
            @Param("row") Integer row,
            @Param("fid") String fid
    );
}

