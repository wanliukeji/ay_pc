package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.SysUSerMapper;
import com.example.demo.entity.SysUser;
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
public class SysUserService extends ServiceImpl<SysUSerMapper,SysUser> {
}
