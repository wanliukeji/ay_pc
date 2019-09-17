package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.SysUserMapper;
import com.example.demo.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 16:56
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 请求实体类
 */
@Service
@Slf4j
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements Serializable {

    @Autowired
    private SysUserMapper userMapper;

    public List<SysUser> list() {
        return baseMapper.list();
    }

    public List<SysUser> getUsers() {

        return baseMapper.selectList(null);
    }



    /*public PageInfo<SysUser> getPage(QueryWrapper ew){
        List<SysUser> us = baseMapper
    }*/

//    public SysUser getUserInfo(SysUser user){
//        return baseMapper.selectList()
//    }
}
