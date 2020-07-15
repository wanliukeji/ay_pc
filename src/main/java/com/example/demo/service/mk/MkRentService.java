package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkRentMapper;
import com.example.demo.entity.mk.MkRent;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
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
public class MkRentService extends ServiceImpl<MkRentMapper, MkRent> {

    public MkRent add(String uid, Integer pm, String title,
                             BigDecimal amount, String mark, String fid, Date payDate, Integer periodNum) {
        MkRent entity = new MkRent();

        try {
            if (StringUtil.isNotEmty(uid)) {
                entity.setAmount(amount);
                entity.setCreatDate(new Date());
                entity.setDel(1);
                entity.setMark(mark);
                entity.setPayDate(payDate);
                entity.setTitle(title);
                entity.setUid(uid);
                entity.setPm(pm);
                entity.setPeriodNum(periodNum);
                entity.setFid(fid);
            } else {
                return null;
            }
            boolean f = this.save(entity);
            if (f) {
                return entity;
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return null;
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return null;
        }
    }

    public ResultJSON<?> page(String uid, Integer limit, Integer row) {
        QueryWrapper<MkRent> qw = new QueryWrapper<MkRent>();
        try {
            if (StringUtil.isNotEmty(uid)) {
                limit = limit == null ? 10 : limit;
                row = row == null ? 10 : row;
                qw.eq("uid", uid);
                qw.eq("del", 1);
                List items = this.list(qw);
                return ResultJSON.success(items);
            }
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        }
    }

    public ResultJSON<?> getInfo(Integer id) {
        QueryWrapper<MkRent> qw = new QueryWrapper<MkRent>();
        try {
            if (StringUtil.isNotEmty(id)) {
                qw.eq("del", 1);
                qw.eq("id", id);
                MkRent entity = this.getOne(qw);
                return ResultJSON.success(entity);
            }
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        }
    }

    public ResultJSON<?> del(Integer id) {
        try {
            if (StringUtil.isNotEmty(id)) {
                MkRent entity = this.getById(id);
                return ResultJSON.success(CodeMsg.UPDATE_SUCCESS);
            }
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        }
    }


//    public ResultJSON<?> add(String fileCode, String userId, Integer roomNum, String cityCode, String areaCode, String townCode, String addr, String communityName) {
//
//        MkRent entity = new MkRent();
//
//        try {
//            entity.setFileCode(fileCode);
//            if (StringUtil.isNotEmty(userId)) {
//                entity.setUserId(Long.valueOf(userId));
//            }
//
//            if (StringUtil.isNotEmty(roomNum)) {
//                entity.setRoomNum(Long.valueOf(roomNum));
//            }
//
//            entity.setCityCode(cityCode);
//            entity.setAreaCode(areaCode);
//            entity.setTownCode(townCode);
//            entity.setAddr(addr);
//            entity.setCommunityName(communityName);
//            entity.setDel(1);
//            entity.setFstatus(0);
//
//            boolean f = this.save(entity);
//
//            if (f) {
//                return ResultJSON.success(entity);
//            } else {
//                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                return ResultJSON.error(CodeMsg.UPDATE_ERROR);
//            }
//
//        } catch (Exception e) {
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//            e.printStackTrace();
//            return ResultJSON.error(CodeMsg.UPDATE_ERROR);
//        }
//
//    }
//
//    public ResultJSON<?> page(String val, Integer limit, Integer row) {
//        QueryWrapper<MkRent> qw = new QueryWrapper<MkRent>();
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
//            List<MkRent> infos = this.list(qw);
//
//            PageInfo<MkRent> page = new PageInfo<MkRent>(infos);
//            return ResultJSON.success(page);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return ResultJSON.error(CodeMsg.SESSION_ERROR);
//        }
//
//    }
}


