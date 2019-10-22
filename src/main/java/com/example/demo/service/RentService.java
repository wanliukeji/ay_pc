package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.ExportExcelUtil;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.FiedMapper;
import com.example.demo.dao.RentMapper;
import com.example.demo.entity.Fied;
import com.example.demo.entity.Rent;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqFiedParam;
import com.example.demo.req.ReqParam;
import com.example.demo.vo.FiedVo;
import com.example.demo.vo.RentVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 11:03
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Service
public class RentService extends ServiceImpl<RentMapper, Rent> implements Serializable {


    public ResultJSON<?> getRentInfoVos(String type) {
        try {
           return ResultJSON.success(baseMapper.getRentInfoVos(type));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultJSON.error("查询失败");
    }

    public ResultJSON<RentVo> getInfo(Integer id) {
        try {
            return ResultJSON.success(baseMapper.getInfo(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultJSON.error("查询失败");
    }
}
