package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.ListUtil;
import com.example.demo.dao.mk.MkAddressMapper;
import com.example.demo.entity.mk.MkAddress;
import com.example.demo.json.ResultJSON;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


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
public class MkAddressService extends ServiceImpl<MkAddressMapper, MkAddress> {


    public ResultJSON<?> list() {

        QueryWrapper<MkAddress> qw = new QueryWrapper<MkAddress>();
        try {
            qw.eq("level",1).or().eq("level",2).or().eq("level",3);
//            qw.notIn("level",5);
//            qw.notIn("level",4);
//            qw.notIn("level",3);
            List<MkAddress> infos = this.list(qw);
            infos = ListUtil.addritems(infos);

            PageInfo<?> page = new PageInfo(infos);
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error("获取数据失败");
        }
    }

    public ResultJSON<?> getInfo(String name) {
        QueryWrapper<MkAddress> qw = new QueryWrapper<MkAddress>();
        try {
//            qw.eq("level",1).or().eq("level",2).or().eq("level",3);
            qw.eq("name", name);
//            qw.notIn("level",5);
//            qw.notIn("level",4);
//            qw.notIn("level",3);
            MkAddress info = this.getOne(qw);
            return ResultJSON.success(info);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error("获取数据失败");
        }
    }

    public ResultJSON<?> getChilds(String code) {

        QueryWrapper<MkAddress> qw = new QueryWrapper<MkAddress>();
        try {
//            qw.eq("level",1).or().eq("level",2).or().eq("level",3);
            qw.eq("pcode", code);
//            qw.notIn("level",5);p
//            qw.notIn("level",4);
//            qw.notIn("level",3);
            List<MkAddress> infos = this.list(qw);
            return ResultJSON.success(infos);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error("获取数据失败");
        }
    }
}


