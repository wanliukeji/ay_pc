package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.mk.MkWallkMapper;
import com.example.demo.entity.mk.MkWallk;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.Date;


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
public class MkWallkService extends ServiceImpl<MkWallkMapper, MkWallk> {


    public ResultJSON<?> edit(BigDecimal amount, String userId, Integer type) {

        // 充值
        if (type == 1) {
            try {
                MkWallk entity = this.getOne(new QueryWrapper<MkWallk>().eq("userId", userId));
                if (entity == null) {
                    entity = new MkWallk();
                }
                amount = amount == null ? new BigDecimal("0.00") : amount;
                if (entity.getAmount() == null) {
                    entity.setAmount(amount);
                } else {
                    entity.setAmount(amount.add(entity.getAmount()));
                }
                entity.setUserId(userId);
                entity.setCreatDate(new Date());
                boolean f = this.saveOrUpdate(entity);

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


        } else if (type == 2) {
            try {
                MkWallk entity = this.getOne(new QueryWrapper<MkWallk>().eq("userId", userId));
                if (entity == null) {
                    entity = new MkWallk();
                }
                amount = amount == null ? new BigDecimal("0.00") : amount;
                // 付款金额是否大于余额
                int flag = amount.compareTo(entity.getAmount());
                if ((entity.getAmount() == null) || flag == 1) {
                    return ResultJSON.error("金额不足,请充值");
                } else {
                    entity.setAmount(entity.getAmount().subtract(amount));
                }
                entity.setCreatDate(new Date());
                boolean f = this.saveOrUpdate(entity);

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

        return null;
    }

    public ResultJSON<?> getInfo(String userId) {
        try {
            MkWallk entity = this.getOne(new QueryWrapper<MkWallk>().eq("userId", userId));
            if (entity == null) {
                entity = new MkWallk();
                entity.setAmount(new BigDecimal("0.00"));
                entity.setCreatDate(new Date());
                this.saveOrUpdate(entity);
                return ResultJSON.success(entity);
            }
            return ResultJSON.success(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        }
    }
}


