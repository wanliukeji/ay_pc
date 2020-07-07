package com.example.demo.service.mk;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.DateUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkContractMapper;
import com.example.demo.entity.mk.MkContract;
import com.example.demo.exception.CodeMsg;
import com.example.demo.itextPdf.TestTempletTicket;
import com.example.demo.json.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
public class MkContractService extends ServiceImpl<MkContractMapper, MkContract> {

    public ResultJSON<?> gen() {
        return TestTempletTicket.gen();
    }

    public ResultJSON<?> gen(String zTime, String startDate, String endDate, String fuid, String zuid, String fileUrl,String addr, String fid) {

        MkContract entity = new MkContract();

        try {
            if (StringUtil.isNotEmty(fuid) && StringUtil.isNotEmty(zuid) ) {
                entity.setAddr(addr);
                entity.setCreatDate(new Date());
                entity.setEndDate(DateUtil.getStringToDate(endDate));
                entity.setStartDate(DateUtil.getStringToDate(startDate));
                entity.setFid(fid);
                entity.setFuid(fuid);
                entity.setFileUrl(fileUrl);
                entity.setZTime(zTime);
                entity.setDel(1);
                boolean f = this.save(entity);
                if (f) {
                    TestTempletTicket.gen();
                    return ResultJSON.success(entity);
                } else {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return ResultJSON.error(CodeMsg.UPDATE_ERROR);
                }
            }
            return ResultJSON.error(CodeMsg.UPDATE_ERROR);
        } catch (Exception e) {
            // 强制事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return ResultJSON.error(CodeMsg.UPDATE_ERROR);
        }
    }

    public ResultJSON<?> page(String userId, Integer limit, Integer row) {
        try {
            limit = limit == null ? 0 : limit;
            row = row == null ? 30 : row;
            List<Map<String, Object>> item = this.baseMapper.getByPage(userId, limit, row);
            return ResultJSON.success(item);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }

    }
}


