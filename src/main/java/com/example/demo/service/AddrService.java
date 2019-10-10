package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.AddrMapper;
import com.example.demo.entity.TbChina;
import com.example.demo.json.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 16:56
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 文件实现业务类
 */
@Service
@Slf4j
public class AddrService extends ServiceImpl<AddrMapper, TbChina> implements Serializable {

    public ResultJSON<?> getInfos(Integer pid) {
        try {
            List<TbChina> addrs = this.list(new QueryWrapper<TbChina>().eq("pid", pid));
            return ResultJSON.success(addrs);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResultJSON.success("没有数据");
    }


    public ResultJSON<?> getInfos(String name) {
        try {
            TbChina addr = this.getOne(new QueryWrapper<TbChina>().eq("name", name));

            if (addr != null) {
                List<TbChina> addrs = this.list(new QueryWrapper<TbChina>().eq("pid", addr.getId()));
                return ResultJSON.success(addrs);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResultJSON.success("没有数据");
    }

}
