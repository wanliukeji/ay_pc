package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkApartMapper;
import com.example.demo.entity.mk.MkApartment;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
public class MkApartService extends ServiceImpl<MkApartMapper, MkApartment> {


    public ResultJSON<?> add(String fileCode, String userId, Integer roomNum, String cityCode, String areaCode, String townCode, String addr, String communityName) {

        MkApartment entity = new MkApartment();

        try {
            entity.setFileCode(fileCode);
            if (StringUtil.isNotEmty(userId)) {
                entity.setUserId(Long.valueOf(userId));
            }

            if (StringUtil.isNotEmty(roomNum)) {
                entity.setRoomNum(Long.valueOf(roomNum));
            }

            entity.setCityCode(cityCode);
            entity.setAreaCode(areaCode);
            entity.setTownCode(townCode);
            entity.setAddr(addr);
            entity.setCommunityName(communityName);
            entity.setDel(1);
            entity.setFstatus(0);

            boolean f = this.save(entity);

            if (f) {
                return ResultJSON.success(entity);
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultJSON.error(CodeMsg.UPDATE_ERROR);
            }

        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return ResultJSON.error(CodeMsg.UPDATE_ERROR);
        }

    }

    public ResultJSON<?> page(String val, Integer limit, Integer row) {
        QueryWrapper<MkApartment> qw = new QueryWrapper<MkApartment>();
        try {
            if (StringUtil.isNotEmty(val)) {
                qw.like("communityName", val);
            }

            if (StringUtil.isNotEmty(limit) && StringUtil.isNotEmty(row)) {
                PageHelper.startPage(limit, row);
            }

            qw.eq("del", 1);
            List<MkApartment> infos = this.list(qw);

            PageInfo<MkApartment> page = new PageInfo<MkApartment>(infos);
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }

    }
}

