package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkListingApi;
import com.example.demo.entity.mk.MkAddr;
import com.example.demo.entity.mk.MkApartment;
import com.example.demo.entity.mk.MkListing;
import com.example.demo.entity.mk.MkRental;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.example.demo.redis.RedisService;
import com.example.demo.service.mk.*;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 房源基本信息
 */
@RestController
@Slf4j
public class MkListingController implements MkListingApi {

    @Autowired
    private MkListingService fservice;

    @Autowired
    private MkAddrService afservice;

    @Autowired
    private MkRentalService rfservice;

    @Resource(name = "mkFileService")
    private MkFileService fileService;

    @Resource(name = "redisService")
    private RedisService redisService;

    @Resource(name = "mkApartService")
    private MkApartService apartService;

    @Resource(name = "mkPointXyService")
    private MkPointXyService pointXyService;

    @Override
    public ResultJSON<?> add(
            Long apartmentId,
                             Integer dong,
                             Integer unit,
                             String  roomNo,
                             Double area,
                             Integer floors,
                             String unitType,
                             String depositMethod,
                             Integer payDay,
                             String otherAmount,
                             String longType,
                             String mPay,
                             String jPay,
                             String bPay,
                             String nPay,
                             BigDecimal kdCosts,
                             BigDecimal dCosts,
                             BigDecimal sCosts,
                             BigDecimal wyCosts,
                             BigDecimal tcCosts,
                             BigDecimal rqCosts,
                             String decoration,
                             String towards,
                             String supporting,
                             String features,
                             String expectations,
                             Integer fidentity,
                             String userId,
                             Integer fileId,
                             String proCode,
                             String cityCode,
                             String areaCode,
                             String addrName,
                             Integer leaseType,
                             String labeles,
                             String x,
                             String y,
                             Integer floosSum,
                             String remark,
                             BigDecimal zAmount,
                             BigDecimal yAmount
    ) {
        try {
            // 公寓信息
            MkApartment apartment = apartService.getById(apartmentId);
            if (null != apartment) {
                MkAddr addr = afservice.add(apartment.getCommunityName() , dong, unit, roomNo,
                        floors, userId, proCode, cityCode, areaCode, floosSum, addrName);
                MkRental rental = rfservice.add(otherAmount, kdCosts, dCosts,
                        sCosts, wyCosts, tcCosts,
                        rqCosts, userId, longType,
                        depositMethod, payDay, zAmount,yAmount);
                if (null != addr && rental != null) {
                    //添加房源
                    MkListing enitty = fservice.add(area, unitType,
                                                     mPay, jPay, bPay, nPay,
                                                    decoration, towards, supporting,
                                                    features, expectations, fidentity,
                                                    userId,fileId, leaseType, labeles, apartmentId,
                                                    addr.getId(), rental.getId(), remark);
                    if (null != enitty) {
//                        MkPointxy pointxy = new MkPointxy();
//                        pointxy.setName(apartment.getCommunityName());
//                        pointxy.setY(y);
//                        pointxy.setX(x);
//                        pointxy.setCreadDate(new Date());
//                        pointxy.setFid(enitty.getId() + "");
//                        pointXyService.save(pointxy);
                        redisService.initia(apartment.getCommunityName(), x, y, String.valueOf(enitty.getId()));
                        apartment.setRoomNum((apartment.getRoomNum() != null ? apartment.getRoomNum() + 1 : 1));
                        apartService.saveOrUpdate(apartment);
                        return ResultJSON.success(CodeMsg.UPDATE_SUCCESS);
                    } else {
                        //强制事务回滚
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return ResultJSON.success(CodeMsg.UPDATE_ERROR);
                    }
                } else {
                    //强制事务回滚
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return ResultJSON.error(CodeMsg.UPDATE_ERROR);
                }

            }
        } catch (Exception ex) {
            //强制事务回滚
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJSON.error(CodeMsg.UPDATE_ERROR);
        }
        return ResultJSON.error(CodeMsg.UPDATE_ERROR);
    }


    @Override
    public ResultJSON<?> page(Integer leaseType,
                              String areaCode,
                              Integer maxPrice,
                              Integer minPrice,
                              String unitType,
                              Integer limit,
                              Integer row,
                              String longType,
                              Double maxArea,
                              Double minArea,
                              Integer fidentity,
                              Integer apartmentId,
                              Integer decoration,
                              Integer jstatus,
                              Integer tstatus,
                              String val,
                              Integer id,
                              String cityCode,
                              String comName,
                              String userId
                              ) {
        try {
            List item = fservice.page(leaseType, areaCode, maxPrice, minPrice, unitType,
                    limit, row, longType, maxArea, minArea, fidentity, apartmentId, decoration,
                    jstatus, tstatus, val, id, cityCode, comName, userId);
           if (null != item && item.size() > 0) {
               item = fileService.getInfos("fileId", item);
               PageInfo<?> page = new PageInfo(item);
               return ResultJSON.success(page);
           }
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        }
    }

    @Override
    public ResultJSON<?> getInfo(Integer id) {
        return fservice.getInfo(id);
    }

    @Override
    public ResultJSON<?> edit(Long apartmentId, Integer dong, Integer unit,
                              String roomNo, Double area, Integer floors,
                              String unitType, String depositMethod, Integer payDay,
                              String otherAmount, String longType, String mPay,
                              String jPay, String bPay, String nPay, BigDecimal kdCosts,
                              BigDecimal dCosts, BigDecimal sCosts, BigDecimal wyCosts,
                              BigDecimal tcCosts, BigDecimal rqCosts, String decoration,
                              String towards, String supporting, String features, String expectations,
                              Integer fidentity, Integer fileId, String proCode,
                              String cityCode, String areaCode, String addrName, Integer leaseType,
                              String labeles, String x, String y, Integer floosSum, String remark,
                              BigDecimal zAmount, BigDecimal yAmount, String fid) {
        try {
            // 公寓信息
            MkApartment apartment = apartService.getById(apartmentId);

            MkListing pojo = fservice.getById(fid);

            if (null != pojo && null != apartment) {
                MkAddr addr = afservice.edit(apartment.getCommunityName() , dong, unit, roomNo,
                        floors,  proCode, cityCode, areaCode, floosSum, addrName, pojo.getAddrId());
                MkRental rental = rfservice.edit(otherAmount, kdCosts, dCosts,
                        sCosts, wyCosts, tcCosts,
                        rqCosts, longType,
                        depositMethod, payDay, zAmount,yAmount, pojo.getRentalId());
                if (null != addr && rental != null) {
                    //添加房源
                    MkListing enitty = fservice.edit(area, unitType,
                            mPay, jPay, bPay, nPay,
                            decoration, towards, supporting,
                            features, expectations, fidentity,
                            fileId, leaseType, labeles, apartmentId,
                            addr.getId(), rental.getId(), remark, pojo.getId());
                    if (null != enitty) {
                        redisService.initia(apartment.getCommunityName(), x, y, String.valueOf(enitty.getId()));
                        apartment.setRoomNum((apartment.getRoomNum() != null ? apartment.getRoomNum() + 1 : 1));
                        apartService.saveOrUpdate(apartment);
                        return ResultJSON.success(CodeMsg.UPDATE_SUCCESS);
                    } else {
                        //强制事务回滚
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return ResultJSON.success(CodeMsg.UPDATE_ERROR);
                    }
                } else {
                    //强制事务回滚
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return ResultJSON.error(CodeMsg.UPDATE_ERROR);
                }

            }
        } catch (Exception ex) {
            //强制事务回滚
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJSON.error(CodeMsg.UPDATE_ERROR);
        }
        return ResultJSON.error(CodeMsg.UPDATE_ERROR);
    }

//    public ResultJSON<?> page(Integer leaseType, Integer areaCode, Integer townCode, Integer maxPrice, Integer minPrice, String unitTypeA, Integer limit, Integer row,Integer longCode, Double area, String hostType,Integer apartmentId,String decoration) {
//        return fservice.page(leaseType, areaCode, townCode, maxPrice, minPrice, unitTypeA, limit, row, longCode, area, hostType, apartmentId, decoration );
//    }
//
//    @Override
//    public ResultJSON<?> page(String val,Integer ftype, Integer limit, Integer row) {
//        return service.page(val,ftype, limit, row);
//    }
//
//    @Override
//    public ResultJSON<?> del(Integer id) {
//        return service.del(id);
//    }
//
//    @Override
//    public ResultJSON<?> edit(Integer id, Integer ftype, String name, String creatCode) {
//        return service.edit(id, ftype, name, creatCode);
//    }
}
