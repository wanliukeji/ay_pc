package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkBountyMapper;
import com.example.demo.entity.mk.MkBounty;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
public class MkBountyService extends ServiceImpl<MkBountyMapper, MkBounty> {

    public ResultJSON<?> add(Double proportion, String bountyType, String userId, String details, BigDecimal amount, Integer fstatus) {
        MkBounty entity = new MkBounty();

        try {
            if (StringUtil.isNotEmty(userId)) {
                entity.setUserId(userId);
                entity.setDel(1);
                entity.setFstatus(fstatus);
                entity.setProportion(proportion);
                entity.setBountyType(bountyType);
                entity.setDetails(details);
                entity.setAmount(amount);
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

    public ResultJSON<?> list(String userId) {
        QueryWrapper<MkBounty> qw = new QueryWrapper<MkBounty>();
        try {
            if (StringUtil.isNotEmty(userId)) {
                qw.like("userId", userId);
            }
            qw.eq("del", 1);
            qw.eq("fstatus", 1);
            List<MkBounty> infos = this.list(qw);
            PageInfo<MkBounty> page = new PageInfo<MkBounty>(infos);
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }

    public List<Map<String, Object>> getInfos(String name, List list) {
        for (int i = 0; i < list.size(); i++) {
            Map map = (Map) list.get(i);
            List item = new ArrayList();
            if (StringUtil.isNotEmty(map.get(name))) {
                String []str = map.get(name).toString().split(",");
                for (int j = 0; j < str.length; j++) {
                    MkBounty entity = this.getById(str[j]);
                    item.add(entity);
                }
                map.put(name, item);
            }
        }
        return  list;
    }
}


