package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkFshareMapper;
import com.example.demo.entity.mk.MkFshare;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
public class MkFshareService extends ServiceImpl<MkFshareMapper, MkFshare> {

    @Resource(name = "mkListingService")
    private MkListingService mkListingService;

    public ResultJSON<?> add(Integer ftype, String userId, String fid) {
        MkFshare entity = new MkFshare();
        try {

            if (StringUtil.isNotEmty(userId)) {
                entity.setUserId(StringUtil.toString(userId));
            }

            entity.setFtype(ftype);
            entity.setFid(fid);
            entity.setDel(1);
            entity.setCreadDate(new Date());
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

    public ResultJSON<?> del(String userId, String fid) {
        MkFshare entity = new MkFshare();
        try {
            entity = this.getOne(
                    new QueryWrapper<MkFshare>().eq("userId", userId).eq("fid",fid)
            );
            entity.setDel(0);
            boolean f = this.saveOrUpdate(entity);
            if (f) {
                return ResultJSON.success("操作成功");
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

    public ResultJSON<?> page(Integer limit, Integer row, Integer userId) {
        QueryWrapper<MkFshare> qw = new QueryWrapper<MkFshare>();
        try {
            if (StringUtil.isNotEmty(userId)) {
                qw.eq("userId", userId);
            }
            qw.eq("del", 1);

            List<MkFshare> item = this.list(qw);

            List entitys = new ArrayList();

            for (int i = 0; i < item.size(); i++) {
                MkFshare entity = item.get(i);
                if (null != entity) {
                    Map mp = mkListingService.page2(limit, row, entity.getFid());
                    if (null != mp && mp.size() > 0) {
                        entitys.add(mp);
                    }
                }
            }
            return ResultJSON.success(entitys);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }

    }
}


