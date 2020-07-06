package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkRuleMapper;
import com.example.demo.entity.mk.MkRule;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
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
public class MkRuleService extends ServiceImpl<MkRuleMapper, MkRule> {

    public ResultJSON<?> add(Integer ftype, String userId, String details, String title) {
        MkRule entity = new MkRule();

        try {
            if (StringUtil.isNotEmty(userId)) {
                entity.setUserId(userId);
                entity.setFtype(ftype);
                entity.setCreateDate(new Date());
                entity.setDetails(details);
                entity.setTitle(title);
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

    public ResultJSON<?> list(Integer ftype) {
        QueryWrapper<MkRule> qw = new QueryWrapper<MkRule>();
        try {
            if (StringUtil.isNotEmty(ftype)) {
                qw.like("ftype", ftype);
            }
            List<MkRule> infos = this.list(qw);
            return ResultJSON.success(infos);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }
}


