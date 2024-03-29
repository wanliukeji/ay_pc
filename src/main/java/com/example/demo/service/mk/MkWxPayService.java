package com.example.demo.service.mk;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.mk.MkWxPayMapper;
import com.example.demo.entity.mk.MkWxPay;
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
public class MkWxPayService extends ServiceImpl<MkWxPayMapper, MkWxPay> {

  private Boolean add (MkWxPay mkWxPay) {
      return this.save(mkWxPay);
  }
}


