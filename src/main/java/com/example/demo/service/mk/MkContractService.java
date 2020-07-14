package com.example.demo.service.mk;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkContractMapper;
import com.example.demo.entity.mk.MkApartment;
import com.example.demo.entity.mk.MkContract;
import com.example.demo.entity.mk.MkListing;
import com.example.demo.entity.mk.MkUser;
import com.example.demo.exception.CodeMsg;
import com.example.demo.itextPdf.TestTempletTicket;
import com.example.demo.json.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
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

    @Resource(name = "mkUserService")
    private MkUserService userService;

    @Resource
    private MkApartService apartService;

    @Autowired
    private MkListingService fservice;

    public ResultJSON<?> gen(String addr, Double area, String startDate,
                             String endDate, String payDate,
                             Integer payDay, BigDecimal amount,
                             BigDecimal yamount, Integer startDay,
                             String fuid, String zuid, String fid) {
        MkContract entity = new MkContract();
        try {
            if (StringUtil.isNotEmty(fuid) && StringUtil.isNotEmty(zuid) ) {
                entity.setAddr(addr);
                entity.setCreatDate(new Date());
                entity.setEndDate(endDate);
                entity.setStartDate(startDate);
                entity.setFid(fid);
                entity.setFuid(fuid);
                entity.setArea(area);
                entity.setPayDate(payDate);
                entity.setPayDay(payDay);
                entity.setAmount(amount);
                entity.setYamount(yamount);
                entity.setStartDay(startDay);
                entity.setDel(1);
                MkUser zuser = userService.getById(zuid);
                MkUser fuser = userService.getById(fuid);
                if (zuser != null && fuser != null) {
                    MkListing mkListing = fservice.getById(fid);
                    MkApartment apartment = new MkApartment();
                    if (StringUtil.isNotEmty(mkListing)) {
                        apartment = apartService.getById(mkListing.getApartmentId());
                    }

                    if (StringUtil.isNotEmty(apartment)) {
                        Integer roomNum = apartment.getRoomNum();
                        roomNum = roomNum == null ? 0 : roomNum;
                        apartment.setRoomNum((roomNum - 1) <= 0 ? 0 : (roomNum - 1));
                    }

                    boolean f = this.save(entity);
                    if (f) {
                        Calendar calendar = Calendar.getInstance();
                        String y =  calendar.get(Calendar.YEAR) + "";
                        String m =  (calendar.get(Calendar.MONTH) + 1) + "";
                        String d =  calendar.get(Calendar.DATE) + "";
                        String fileUrl = TestTempletTicket.gen(
                                addr, area, startDate,endDate,
                                payDate, amount, yamount, startDay,fuser.getUname(), fuser.getIDcard(), fuser.getPhone(), y, m, d,
                                zuser.getUname(), zuser.getIDcard(), zuser.getPhone(), y, m, d);
                        if (fileUrl != null) {
                            entity.setFileUrl(fileUrl);
                            this.saveOrUpdate(entity);
                            return ResultJSON.success(entity);
                        }
                    } else {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return ResultJSON.error(CodeMsg.UPDATE_ERROR);
                    }
                }
            }
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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


