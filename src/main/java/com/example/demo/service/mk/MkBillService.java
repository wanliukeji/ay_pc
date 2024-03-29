package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkBillMapper;
import com.example.demo.entity.mk.MkBill;
import com.example.demo.entity.mk.MkRent;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
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
public class MkBillService extends ServiceImpl<MkBillMapper, MkBill> {

    @Autowired
    private MkRentService rentService;

    public ResultJSON<?> add(String uid, Integer pm, String title, String ftype,
                             BigDecimal amount, String mark, String fid, Date payDate,Long rentId ) {
        MkBill entity = new MkBill();

        try {
            if (StringUtil.isNotEmty(uid)) {
                entity.setUid(uid);
                entity.setPm(pm);
                entity.setTitle(title);
                entity.setFtype(ftype);
                entity.setAmount(amount);
                entity.setMark(mark);
                entity.setCreateDate(new Date());
                entity.setFid(fid);
                entity.setDel(1);
                entity.setFstatus(0);
                entity.setPayDate(payDate);
                entity.setRentId(rentId);
            } else {
                return ResultJSON.error("支付人或者收款人信息不能为空");
            }
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

    public ResultJSON<?> page(String uid, Integer limit, Integer row, Integer rentId) {
        try {
            if (StringUtil.isNotEmty(uid) && StringUtil.isNotEmty(rentId) ) {
                limit = limit == null ? 0 : limit;
                row = row == null ? 10 : row;
                List<Map<String, Object>> maps = this.baseMapper.getByGroup(uid, limit, row, rentId);
                Map map = new HashMap();
                MkRent rent = rentService.getById(rentId);
                if (StringUtil.isNotEmty(rent)) {
                    map.put("rent", rent);
                    map.put("items", maps);
                }
                return ResultJSON.success(map);
            }
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        }
    }

    public ResultJSON<?> getInfo(Integer id) {
        QueryWrapper<MkBill> qw = new QueryWrapper<MkBill>();
        try {
            if (StringUtil.isNotEmty(id)) {
                qw.eq("del", 1);
                qw.eq("id", id);
                MkBill entity = this.getOne(qw);
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
                MkBill entity = this.getById(id);
                entity.setDel(0);
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
//        MkBill entity = new MkBill();
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
//        QueryWrapper<MkBill> qw = new QueryWrapper<MkBill>();
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
//            List<MkBill> infos = this.list(qw);
//
//            PageInfo<MkBill> page = new PageInfo<MkBill>(infos);
//            return ResultJSON.success(page);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return ResultJSON.error(CodeMsg.SESSION_ERROR);
//        }
//
//    }
}


