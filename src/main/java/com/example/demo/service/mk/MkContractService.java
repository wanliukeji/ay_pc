package com.example.demo.service.mk;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.DateUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkContractMapper;
import com.example.demo.entity.mk.*;
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

    @Autowired
    private MkBillService billService;

    @Autowired
    private MkAddrService addrService;

    public ResultJSON<?> gen(String addr, Double area, String startDate,
                             String endDate, String payDate,
                             Integer payDay, BigDecimal amount,
                             BigDecimal yamount, Integer startDay,
                             String fuid, String zuid, String fid,
                             Integer payType) {
        MkContract entity = new MkContract();
        try {
            if (StringUtil.isNotEmty(fuid) && StringUtil.isNotEmty(zuid)) {
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
                entity.setZuid(zuid);

                if (payType == 2) {
                    entity.setPayType("季付");
                } else if (payType == 3) {
                    entity.setPayType("半年付");
                } else if (payType == 4) {
                    entity.setPayType("年付");
                } else {
                    entity.setPayType("月付");
                }

                MkUser zuser = userService.getById(zuid);
                MkUser fuser = userService.getById(fuid);
                if (zuser != null && fuser != null) {
                    MkListing mkListing = fservice.getById(fid);
                    MkApartment apartment = new MkApartment();
                    if (StringUtil.isNotEmty(mkListing)) {
                        apartment = apartService.getById(mkListing.getApartmentId());
                    } else {
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return ResultJSON.error(CodeMsg.UPDATE_ERROR);
                    }

                    if (StringUtil.isNotEmty(apartment)) {
                        Integer roomNum = apartment.getRoomNum();
                        roomNum = roomNum == null ? 0 : roomNum;
                        apartment.setRoomNum((roomNum - 1) <= 0 ? 0 : (roomNum - 1));
                        apartService.saveOrUpdate(apartment);
                    }

                    boolean f = this.save(entity);
                    // 生成合同
                    if (f) {
                        Calendar calendar = Calendar.getInstance();
                        String y = calendar.get(Calendar.YEAR) + "";
                        String m = (calendar.get(Calendar.MONTH) + 1) + "";
                        String d = calendar.get(Calendar.DATE) + "";
                        String fileUrl = TestTempletTicket.gen(
                                addr, area, startDate, endDate,
                                payDate, amount, yamount, startDay, fuser.getUname(), fuser.getIDcard(), fuser.getPhone(), y, m, d,
                                zuser.getUname(), zuser.getIDcard(), zuser.getPhone(), y, m, d);


                        MkAddr mkAddr = addrService.getById(mkListing.getAddrId());

                        String billTitel = "";

                        if (null != addr) {
                            billTitel = StringUtil.toString(mkAddr.getComName() + mkAddr.getDong() + "号" + mkAddr.getUnit() + "单元" + mkAddr.getRoomNo());
                        }

                        // 生成账单
                        if (StringUtil.isNotEmty(payType)) {
                            String remark = "";
                            //  月付
                            switch (payType) {
                                case 1:
                                    List ms = DateUtil.dateForEach(startDate, endDate, 3);
                                    for (int i = 0; i < ms.size() - 1; i++) {
                                        remark = "应付日"+ ms.get(i);
                                        // 房东账单
                                        billService.add(fuid, 1, billTitel, (i+1) + "期", amount, remark, fid);

                                        // 房客账单
                                        billService.add(zuid, 0, billTitel, (i+1) + "期", amount, remark, fid);
                                    }
                                    break;
//                                    季度
                                case 2:

                                    List js = DateUtil.dateForEach(startDate, endDate, 3);
                                    for (int i = 0; i < js.size() - 1;) {
                                        remark = "应付日"+ js.get(i);
                                        // 房东账单
                                        billService.add(fuid, 1, billTitel, (i+1) + "期", amount, remark, fid);

                                        // 房客账单
                                        billService.add(zuid, 0, billTitel, (i+1) + "期", amount, remark, fid);
                                        i =+ 3;
                                    }
                                    break;
//                                    半年付
                                case 3:

                                    List bs = DateUtil.dateForEach(startDate, endDate, 3);
                                    for (int i = 0; i < bs.size() - 1;) {
                                        remark = "应付日"+ bs.get(i);
                                        // 房东账单
                                        billService.add(fuid, 1, billTitel, (i+1) + "期", amount, remark, fid);

                                        // 房客账单
                                        billService.add(zuid, 0, billTitel, (i+1) + "期", amount, remark, fid);
                                        i =+ 6;
                                    }

                                    break;
//                                    年付
                                case 4:

                                    List ys = DateUtil.dateForEach(startDate, endDate, 3);
                                    for (int i = 0; i < ys.size() - 1;) {
                                        remark = "应付日"+ ys.get(i);
                                        // 房东账单
                                        billService.add(fuid, 1, billTitel, (i+1) + "期", amount, remark, fid);

                                        // 房客账单
                                        billService.add(zuid, 0, billTitel, (i+1) + "期", amount, remark, fid);
                                        i =+ 12;
                                    }
                                    break;

                            }
                        }

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


