package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkListingMapper;
import com.example.demo.entity.mk.MkListing;
import com.example.demo.entity.mk.MkUser;
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
    private MkFacilityService facilityService;

    @Resource(name = "mkFileService")
    private MkFileService fileService;

    @Resource(name = "mkUserService")
    private MkUserService mkUserService;

    /**
     * 添加数据
     *
     * @param area
     * @param unitType
     * @param mPay
     * @param jPay
     * @param bPay
     * @param nPay
     * @param decoration
     * @param towards
     * @param supporting
     * @param features
     * @param expectations
     * @param fidentity
     * @param userId
     * @param fileId
     * @param leaseType
     * @return
     * @throws Exception
     */
    public MkListing add(Double area, String unitType, String mPay, String jPay, String bPay,
                         String nPay, String decoration, String towards,
                         String supporting, String features, String expectations,
                         Integer fidentity,
                         String userId,
                         Integer fileId, Integer leaseType,
                         String labeles, Long apartmentId, Long addrId, Long rId, String remark) throws Exception {
        MkListing entity = new MkListing();

        entity.setArea(Double.valueOf(area));
        entity.setLeaseType(leaseType);
        entity.setUnitType(unitType);
        entity.setFidentity(fidentity);
        entity.setYPay(mPay);
        entity.setJPay(jPay);
        entity.setBPay(bPay);
        entity.setNPay(nPay);
        entity.setDecoration(decoration);
        entity.setTowards(towards);
        entity.setSupporting(supporting);
        entity.setFeatures(features);
        entity.setExpectations(expectations);
        entity.setUserId(userId);
        entity.setCreateDate(new Date());
        entity.setAddrId(addrId);
        entity.setRentalId(rId);
        entity.setFileId(fileId);
        entity.setFstatus(0);
        entity.setLabels(labeles);
        entity.setRemark(remark);
        entity.setDel(1);


        if (StringUtil.isNotEmty(apartmentId)) {
            entity.setApartmentId(apartmentId);
        }
        this.saveOrUpdate(entity);
        return entity;
    }

    public MkListing edit(Double area, String unitType, String mPay, String jPay, String bPay,
                          String nPay, String decoration, String towards,
                          String supporting, String features, String expectations,
                          Integer fidentity, Integer fileId, Integer leaseType,
                          String labeles, Long apartmentId, Long addrId,
                          Long rId, String remark, Long id) throws Exception {
        try {
            MkListing entity = this.getById(id);

            if (null != entity) {
                entity.setArea(Double.valueOf(area));
                entity.setLeaseType(leaseType);
                entity.setUnitType(unitType);
                entity.setFidentity(fidentity);
                entity.setYPay(mPay);
                entity.setJPay(jPay);
                entity.setBPay(bPay);
                entity.setNPay(nPay);
                entity.setDecoration(decoration);
                entity.setTowards(towards);
                entity.setSupporting(supporting);
                entity.setFeatures(features);
                entity.setExpectations(expectations);
                entity.setAddrId(addrId);
                entity.setRentalId(rId);
                entity.setFileId(fileId);
                entity.setLabels(labeles);
                entity.setRemark(remark);
                if (StringUtil.isNotEmty(apartmentId)) {
                    entity.setApartmentId(apartmentId);
                }
                boolean f = this.saveOrUpdate(entity);
                if (f) {
                    return entity;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public List<Map<String, Object>> page(Integer leaseType,
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
    ) throws Exception {
        try {
            limit = limit == null ? 0 : limit;
            row = row == null ? 30 : row;
            QueryWrapper<MkListing> qw = new QueryWrapper<MkListing>();
            if (StringUtil.isNotEmty(userId)) {
                MkUser user = mkUserService.getById(userId);
                if (null != user && user.getUtype() != 3) {
                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                    list = this.baseMapper.getByPage(leaseType, areaCode, maxPrice, minPrice,
                            unitType, limit, row, longType, maxArea, minArea, fidentity,
                            apartmentId, decoration, jstatus, tstatus, val, id, cityCode,
                            comName, userId);
                    list = facilityService.getInfos("labels", list);
                    list = facilityService.getInfos("decoration", list);
                    list = facilityService.getInfos("towards", list);
                    list = facilityService.getInfos("supporting", list);
                    list = facilityService.getInfos("features", list);
                    list = facilityService.getInfos("expectations", list);
                    return list;
                } else if (null != user) {
                    List<MkUser> chidrens = mkUserService.list(new QueryWrapper<MkUser>().eq("pOpenid", user.getOpenId()).or().eq("openId", user.getOpenId()));
                    List<Map<String, Object>> item = new ArrayList<Map<String, Object>>();
                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                    for (int i = 0; i < chidrens.size(); i++) {
                        MkUser entity = chidrens.get(i);
                        item = this.baseMapper.getByPage(leaseType, areaCode, maxPrice, minPrice,
                                unitType, limit, row, longType, maxArea, minArea, fidentity,
                                apartmentId, decoration, jstatus, tstatus, val, id, cityCode,
                                comName, StringUtil.toString(entity.getId()));
                        item = facilityService.getInfos("labels", item);
                        item = facilityService.getInfos("decoration", item);
                        item = facilityService.getInfos("towards", item);
                        item = facilityService.getInfos("supporting", item);
                        item = facilityService.getInfos("features", item);
                        item = facilityService.getInfos("expectations", item);

                        if (null != item && item.size() > 0) {
                            list.addAll(item);
                        }
                    }
                    return list;
                }
            } else {
                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                list = this.baseMapper.getByPage(leaseType, areaCode, maxPrice, minPrice,
                        unitType, limit, row, longType, maxArea, minArea, fidentity,
                        apartmentId, decoration, jstatus, tstatus, val, id, cityCode,
                        comName, userId);
                list = facilityService.getInfos("labels", list);
                list = facilityService.getInfos("decoration", list);
                list = facilityService.getInfos("towards", list);
                list = facilityService.getInfos("supporting", list);
                list = facilityService.getInfos("features", list);
                list = facilityService.getInfos("expectations", list);
                return list;
            }
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public Map page2(Integer limit, Integer row, String fid) throws Exception {

        Map<String, Object> paraMap = new HashMap<String, Object>();
        limit = limit == null ? 0 : limit;
        row = row == null ? 30 : row;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        QueryWrapper<MkListing> qw = new QueryWrapper<MkListing>();
        try {
            Map map = this.baseMapper.getByPage2(limit, row, fid);
            list.add(map);
            list = facilityService.getInfos("labels", list);
            list = facilityService.getInfos("decoration", list);
            list = facilityService.getInfos("towards", list);
            list = facilityService.getInfos("supporting", list);
            list = facilityService.getInfos("features", list);
            list = facilityService.getInfos("expectations", list);
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
            list = this.baseMapper.getByPage(null,
                    null, null, null, null,
                    0, 1, null, null, null, null, null,
                    null, null, null, null, id, null, null, null);
            list = fileService.getInfos("fileId", list);
            list = facilityService.getInfos("labels", list);
            list = facilityService.getInfos("decoration", list);
            list = facilityService.getInfos("towards", list);
            list = facilityService.getInfos("supporting", list);
            list = facilityService.getInfos("features", list);
            list = facilityService.getInfos("expectations", list);
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
            list = fileService.getInfos("fileId", list);
            list = facilityService.getInfos("labels", list);
            list = facilityService.getInfos("decoration", list);
            list = facilityService.getInfos("towards", list);
            list = facilityService.getInfos("supporting", list);
            list = facilityService.getInfos("features", list);
            list = facilityService.getInfos("expectations", list);
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


