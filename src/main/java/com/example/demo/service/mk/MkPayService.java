package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkPayMapper;
import com.example.demo.entity.mk.MkPay;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class MkPayService extends ServiceImpl<MkPayMapper, MkPay> {

    /**
     * 添加数据
     *
     * @return
     */
    public ResultJSON<MkPay> add(String name, Double amount, String fid, Double kdCosts, Double dCosts, Double sCosts,
                                 Double wyCosts, Double tcCosts, Double rqCosts, Double sAmount, String userId,
                                 String remark, String zType) {
        try {
            MkPay entity = new MkPay();

            entity.setName(name);

            if (StringUtil.isNotEmty(amount)) {
                entity.setAmount(BigDecimal.valueOf(amount));
            }

            if (StringUtil.isNotEmty(sAmount)) {
                entity.setSAmount(BigDecimal.valueOf(sAmount));
            }

            if (StringUtil.isNotEmty(fid)) {
                entity.setFid(Long.valueOf(fid));
            }

            if (StringUtil.isNotEmty(kdCosts)) {
                entity.setKdCosts(BigDecimal.valueOf(kdCosts));
            }

            if (StringUtil.isNotEmty(dCosts)) {
                entity.setDCosts(BigDecimal.valueOf(dCosts));
            }


            if (StringUtil.isNotEmty(sCosts)) {
                entity.setSCosts(BigDecimal.valueOf(sCosts));
            }

            if (StringUtil.isNotEmty(wyCosts)) {
                entity.setWyCosts(BigDecimal.valueOf(wyCosts));
            }

            if (StringUtil.isNotEmty(tcCosts)) {
                entity.setTcCosts(BigDecimal.valueOf(tcCosts));
            }

            if (StringUtil.isNotEmty(rqCosts)) {
                entity.setRqCosts(BigDecimal.valueOf(rqCosts));
            }


            entity.setCreatDate(new Date());

            entity.setCreadCode(userId);

            entity.setRemark(remark);

            entity.setZType(zType);

            entity.setDel(1);

            entity.setFstatus(1);

            boolean f = this.save(entity);
            if (f) {
                return ResultJSON.error(CodeMsg.UPDATE_SUCCESS);
            } else {
                return ResultJSON.error(CodeMsg.UPDATE_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultJSON.error(CodeMsg.SERVER_ERROR);
        }
    }

    public ResultJSON<?> page(String val, Integer limit, Integer row) {
        QueryWrapper<MkPay> qw = new QueryWrapper<MkPay>();
        try {
            if (StringUtil.isNotEmty(val)) {
                qw.like("name", val);
            }

            if (StringUtil.isNotEmty(limit) && StringUtil.isNotEmty(row)) {
                PageHelper.startPage(limit, row);
            }

            qw.eq("del", 1);
            List<MkPay> infos = this.list(qw);
            PageInfo<MkPay> page = new PageInfo<MkPay>(infos);
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }

    }

    public ResultJSON<?> getInfo(String zType, String name, Integer fid) {

        QueryWrapper<MkPay> qw = new QueryWrapper<MkPay>();
        try {
            if (StringUtil.isNotEmty(fid)) {
                qw.eq("fid", fid);
            } else {
                return  ResultJSON.error("房源编号不能为空");
            }

            if (StringUtil.isNotEmty(zType)) {
                qw.like("zType", zType);
            }

            if (StringUtil.isNotEmty(name)) {
                qw.like("name", name);
            }
            qw.eq("del", 1);
            List<MkPay> infos = this.list(qw);
            return ResultJSON.success(infos);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }

//    /**
//     * 获取页面数据
//     * 查询数据
//     *
//     * @return
//     */
//    public ResultJSON<?> page(String val,Integer ftype, Integer limit, Integer row) {
//
//        QueryWrapper<MkFacility> qw = new QueryWrapper<MkFacility>();
//        try {
//            if (StringUtil.isNotEmty(val)) {
//                qw.like("name", val);
//            }
//
//            if (StringUtil.isNotEmty(ftype)) {
//                qw.like("ftype", ftype);
//            }
//
//           if (StringUtil.isNotEmty(limit) && StringUtil.isNotEmty(row)) {
//               PageHelper.startPage(limit, row);
//           }
//
//            qw.eq("del", 1);
//            List<MkFacility> infos = this.list(qw);
//            infos = ListUtil.items(infos);
//
//            PageInfo<MkFacility> page = new PageInfo<MkFacility>(infos);
//            return ResultJSON.success(page);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return ResultJSON.error(CodeMsg.SESSION_ERROR);
//        }
//    }
//
//    /**
//     * 删除数据
//     *
//     * @param id
//     * @return
//     */
//    public ResultJSON<?> del(Integer id) {
//
//        try {
//            if (StringUtil.isNotEmty(id)) {
//                MkFacility entity = this.getById(id);
//                entity.setDel(0);
//                boolean f = this.saveOrUpdate(entity);
//                if (f) {
//                    return ResultJSON.error(CodeMsg.UPDATE_SUCCESS);
//                } else {
//                    return ResultJSON.error(CodeMsg.UPDATE_ERROR);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//            return ResultJSON.error(CodeMsg.SERVER_ERROR);
//        }
//        return ResultJSON.error(CodeMsg.SERVER_ERROR);
//    }
//
//    public ResultJSON<?> edit(Integer id, Integer ftype, String name, String creatCode) {
//        try {
//            MkFacility entity = new MkFacility();
//            if (StringUtil.isNotEmty(id)) {
//                entity = this.getById(id);
//            }
//
//            if (StringUtil.isNotEmty(ftype)) {
//                entity.setFtype(Integer.valueOf(ftype));
//            }
//
//            if (StringUtil.isNotEmty(name)) {
//                entity.setName(name);
//            }
//
//            entity.setCreateDate(DateUtil.getStringToDate(new Date().toString()));
//            boolean f = this.save(entity);
//            if (f) {
//                return ResultJSON.error(CodeMsg.UPDATE_SUCCESS);
//            } else {
//                return ResultJSON.error(CodeMsg.UPDATE_ERROR);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//            return ResultJSON.error(CodeMsg.SERVER_ERROR);
//        }
//    }
}


