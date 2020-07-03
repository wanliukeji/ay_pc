package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkSrinfoMapper;
import com.example.demo.entity.mk.MkSrinfo;
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
public class MkSrunfoService extends ServiceImpl<MkSrinfoMapper, MkSrinfo> {

    /**
     * 添加数据
     *
     * @param ftype
     * @param payType
     * @param details
     * @param amount
     * @param userId
     * @param remark
     * @return
     */
    public ResultJSON<MkSrinfo> add(String ftype, String payType, String details, BigDecimal amount, String userId, String remark) {
        try {
            if (userId == null) {
                return ResultJSON.error("用户ID不能为空");
            }
            MkSrinfo srinfo = new MkSrinfo();
            srinfo.setFtype(ftype);
            srinfo.setPayType(payType);
            srinfo.setDetails(details);
            srinfo.setUserId(userId);
            srinfo.setAmount(amount);
            srinfo.setRemark(remark);
            srinfo.setDel(1);
            srinfo.setCreateDate(new Date());
            boolean f = this.save(srinfo);
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

    /**
     * 获取页面数据
     * 查询数据
     *
     * @return
     */
    public ResultJSON<?> page(String val, String userId, Integer limit, Integer row) {

        try {
            QueryWrapper<MkSrinfo> qw = new QueryWrapper<>();
            if (StringUtil.isNotEmty(val)) {
                qw.like("details", val).or().like("remark",val);
            }

            if (StringUtil.isNotEmty(userId)) {
                qw.eq("userId", userId);
            }

            limit = (limit == null ? 0 : Integer.valueOf(limit));
            row = (row == null ? 10 : Integer.valueOf(row));

            qw.eq("del", 1);
            //qw.like("details", val).or()
            PageHelper.startPage(limit, row);
            List<MkSrinfo> infos = this.list(qw);
            qw.orderByDesc("createTime");
            PageInfo<MkSrinfo> page = new PageInfo<MkSrinfo>(infos);
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public ResultJSON<?> del(Integer id) {

        try {
            if (StringUtil.isNotEmty(id)) {
                MkSrinfo srinfo = this.getById(id);
                srinfo.setDel(0);
                boolean f = this.saveOrUpdate(srinfo);
                if (f) {
                    return ResultJSON.error(CodeMsg.UPDATE_SUCCESS);
                } else {
                    return ResultJSON.error(CodeMsg.UPDATE_ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultJSON.error(CodeMsg.SERVER_ERROR);
        }
        return ResultJSON.error(CodeMsg.SERVER_ERROR);
    }
}


