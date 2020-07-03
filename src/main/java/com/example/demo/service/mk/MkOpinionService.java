package com.example.demo.service.mk;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkOpoinionMapper;
import com.example.demo.entity.mk.MkOpinion;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


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
public class MkOpinionService extends ServiceImpl<MkOpoinionMapper, MkOpinion> {


    public ResultJSON<?> add(String userId, String details, String phone) {

        MkOpinion entity = new MkOpinion();

        try {
            if (StringUtil.isNotEmty(userId)) {
                entity.setUid(userId);
                entity.setDetails(details);
                entity.setPhone(phone);
                boolean f = this.save(entity);
                if (f) {
                    return ResultJSON.success(entity);
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return ResultJSON.error(CodeMsg.UPDATE_ERROR);
                }

            }
                return ResultJSON.error(CodeMsg.UPDATE_ERROR);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return ResultJSON.error(CodeMsg.UPDATE_ERROR);
        }

    }

//    public ResultJSON<?> page(String val, Integer limit, Integer row) {
//        QueryWrapper<MkApartment> qw = new QueryWrapper<MkApartment>();
//        try {
//            if (StringUtil.isNotEmty(val)) {
//                qw.like("communityName", val);
//            }
//
//            if (StringUtil.isNotEmty(limit) && StringUtil.isNotEmty(row)) {
//                PageHelper.startPage(limit, row);
//            }
//
//            qw.eq("del", 1);
//            List<MkApartment> infos = this.list(qw);
//
//            PageInfo<MkApartment> page = new PageInfo<MkApartment>(infos);
//            return ResultJSON.success(page);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return ResultJSON.error(CodeMsg.SESSION_ERROR);
//        }
//
//    }
}


