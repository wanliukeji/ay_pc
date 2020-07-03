package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.DateUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkListingMapper;
import com.example.demo.entity.mk.MkListing;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


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
public class MkListingService extends ServiceImpl<MkListingMapper, MkListing> {

    @Resource
    private  MkFacilityService facilityService;

    @Resource(name = "mkFileService")
    private MkFileService fileService;

    /**
     * 添加数据
     * @param area
     * @param unitType
     * @param depositMethod
     * @param payTime
     * @param zqType
     * @param mPay
     * @param jPay
     * @param bPay
     * @param nPay
     * @param decoration
     * @param towards
     * @param supporting
     * @param features
     * @param expectations
     * @param hostType
     * @param aId
     * @param rId
     * @param userId
     * @param fileId
     * @param leaseType
     * @return
     * @throws Exception
     */
    public MkListing add(String area,
                       String unitType, String depositMethod, String payTime,
                       String zqType, String mPay, String jPay, String bPay,
                       String nPay, String decoration, String towards,
                       String supporting, String features, String expectations,
                       String hostType,
                       Long aId, Long rId, Integer userId,
                       Integer fileId,Integer leaseType,
                       Integer apartmentId,
                       String labeles) throws Exception {
        MkListing entity = new MkListing();


        if (StringUtil.isNotEmty(area)) {
            entity.setArea(Double.valueOf(area));
        }

        if (StringUtil.isNotEmty(unitType)) {
            entity.setUnitTypeA(unitType);
        }

        if (StringUtil.isNotEmty(leaseType)) {
            entity.setLeaseType(leaseType);
        }

        entity.setDepositMethod(depositMethod);
        entity.setYPay(mPay);
        entity.setJPay(jPay);
        entity.setBPay(bPay);
        entity.setNPay(nPay);
        entity.setLongCode(zqType);
        entity.setDecoration(decoration);
        entity.setTowards(towards);
        entity.setSupporting(supporting);
        entity.setFeatures(features);
        entity.setExpectations(expectations);
        entity.setHostType(hostType);
        entity.setCreadCode(userId + "");
        entity.setCreateDate(new Date());
        entity.setAddrId(aId);
        entity.setRentalId(rId);
        entity.setFileCodes(fileId + "");
        entity.setPayTime(DateUtil.strToDateLong(payTime));
        entity.setFstatus(0);
        entity.setLabels(labeles);

        if (StringUtil.isNotEmty(apartmentId)) {
            entity.setApartmentId(Long.valueOf(apartmentId));
        }
        this.saveOrUpdate(entity);
        return entity;
    }

    public List<Map<String, Object>> page(Integer leaseType, Integer areaCode, Integer townCode, Integer maxPrice, Integer minPrice,
                              String unitTypeA, Integer limit, Integer row, String longCode, Double maxArea, Double minArea,
                              Integer hostType, Integer apartmentId, Integer decoration, Integer jstatus, Integer tstatus,
                              String val, Integer id) throws Exception{

        Map<String, Object> paraMap = new HashMap<String, Object>();
        limit = limit == null ? 0 : limit;
        row = row == null ? 30 : row;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        QueryWrapper<MkListing> qw = new QueryWrapper<MkListing>();
        try {
            list = this.baseMapper.getByPage(leaseType, areaCode, townCode, maxPrice, minPrice,
                    unitTypeA, limit, row, longCode, maxArea, minArea, hostType,
                    apartmentId, decoration, jstatus, tstatus, val, id);
            list = facilityService.getInfos("labels",list);
            list = facilityService.getInfos("decoration",list);
            list = facilityService.getInfos("towards",list);
            list = facilityService.getInfos("supporting",list);
            list = facilityService.getInfos("features",list);
            list = facilityService.getInfos("expectations",list);
           return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public Map page2(Integer limit, Integer row, String fid) throws Exception{

        Map<String, Object> paraMap = new HashMap<String, Object>();
        limit = limit == null ? 0 : limit;
        row = row == null ? 30 : row;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        QueryWrapper<MkListing> qw = new QueryWrapper<MkListing>();
        try {
            Map map = this.baseMapper.getByPage2(limit, row, fid);
            list.add(map);
            list = facilityService.getInfos("labels",list);
            list = facilityService.getInfos("decoration",list);
            list = facilityService.getInfos("towards",list);
            list = facilityService.getInfos("supporting",list);
            list = facilityService.getInfos("features",list);
            list = facilityService.getInfos("expectations",list);
            return list.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }



    public ResultJSON<?> getInfo(Integer id) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        QueryWrapper<MkListing> qw = new QueryWrapper<MkListing>();
        try {
            list = this.baseMapper.getByPage(null, null, null, null, null,
                    null, 0, 1, null, null, null, null,
                    null, null, null, null, null, id);
            list = fileService.getInfos("fileCodes",list);
            list = facilityService.getInfos("labels",list);
            list = facilityService.getInfos("decoration",list);
            list = facilityService.getInfos("towards",list);
            list = facilityService.getInfos("supporting",list);
            list = facilityService.getInfos("features",list);
            list = facilityService.getInfos("expectations",list);
            return ResultJSON.success(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }


    public Map getInfo2(Integer id) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        QueryWrapper<MkListing> qw = new QueryWrapper<MkListing>();

        MkListing entity = new MkListing();
        try {
            Map map = this.baseMapper.getInfo(id);
            list.add(map);
            list = fileService.getInfos("fileCodes",list);
            list = facilityService.getInfos("labels",list);
            list = facilityService.getInfos("decoration",list);
            list = facilityService.getInfos("towards",list);
            list = facilityService.getInfos("supporting",list);
            list = facilityService.getInfos("features",list);
            list = facilityService.getInfos("expectations",list);
            return list.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 获取页面数据
     * 查询数据
     *
     * @return
     */
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


