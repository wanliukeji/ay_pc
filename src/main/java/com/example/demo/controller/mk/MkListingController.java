package com.example.demo.controller.mk;

import com.example.demo.api.mk.MkListingApi;
import com.example.demo.entity.mk.MkAddr;
import com.example.demo.entity.mk.MkListing;
import com.example.demo.entity.mk.MkRental;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.example.demo.redis.RedisService;
import com.example.demo.service.mk.MkAddrService;
import com.example.demo.service.mk.MkFileService;
import com.example.demo.service.mk.MkListingService;
import com.example.demo.service.mk.MkRentalService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @Override
    public ResultJSON<?> add(
                             String comName, Integer dong, Integer unit, Integer roomNo,
                             String area, Integer floors, String unitType, String depositMethod,
                             String payTime, String fOther, String zqType, String mPay, String jPay,
                             String bPay, String nPay, Double kdCosts, Double dCosts, Double sCosts,
                             Double wyCosts, Double tcCosts, Double rqCosts, String decoration,
                             String towards, String supporting, String features, String expectations,
                             String hostType, Integer userId, Integer fileId, String proCode, String cityCode,
                             String areaCode, String addrName, Integer leaseType, Integer apartmentId, String labeles,
                             String x, String y, Integer floosSum) {
        try {
            MkAddr addr = afservice.add(comName, dong, unit, roomNo, floors, userId, proCode, cityCode, areaCode, floosSum, addrName);

            MkRental rental = rfservice.add(payTime, fOther, zqType, kdCosts, dCosts, sCosts, wyCosts, tcCosts, rqCosts, userId);


            if (null != addr && rental != null) {
                //添加房源
                 MkListing enitty = fservice.add(area, unitType, depositMethod, payTime,
                        zqType, mPay, jPay, bPay, nPay, decoration, towards, supporting,
                        features, expectations, hostType, addr.getId(), rental.getId(), userId, fileId, leaseType, apartmentId, labeles);
                if (null != enitty) {
                    redisService.initia(comName, x, y, String.valueOf(enitty.getId()));
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

        } catch (Exception ex) {
            //强制事务回滚
            ex.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultJSON.error(CodeMsg.UPDATE_ERROR);
        }

    }

    @Override
    public ResultJSON<?> page(Integer leaseType, Integer areaCode, Integer townCode, Integer maxPrice, Integer minPrice,
                              String unitTypeA, Integer limit, Integer row, String longCode,
                              Double maxArea, Double minArea, Integer hostType, Integer apartmentId,
                              Integer decoration, Integer jstatus, Integer tstatus, String val,
                              Integer id) {
        try {
            List item = fservice.page(leaseType, areaCode, townCode, maxPrice, minPrice, unitTypeA,
                    limit, row, longCode, maxArea, minArea, hostType, apartmentId, decoration,
                    jstatus, tstatus, val, id);
            item = fileService.getInfos("fileCodes", item);
            PageInfo<?> page = new PageInfo(item);
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.QUERY_ERROR);
        }
    }

    @Override
    public ResultJSON<?> getInfo(Integer id) {
        return fservice.getInfo(id);
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
