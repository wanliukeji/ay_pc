package com.example.demo.service.mk;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.mk.MkAddrMapper;
import com.example.demo.entity.mk.MkAddr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;


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
public class MkAddrService extends ServiceImpl<MkAddrMapper, MkAddr> {


    public MkAddr add(String comName, Integer dong, Integer unit, Integer roomNo, Integer floors,
                      Integer userId,
                      String proCode, String cityCode, String areaCode, Integer floosSum, String addrName) throws Exception {
        MkAddr addr = new MkAddr();
        try {
            addr.setComName(comName);
            addr.setDong(dong + "");
            addr.setUnit(unit);
            addr.setRoomNo(roomNo + "");
            addr.setFloors(floors);
            addr.setCreatCode(userId + "");
            addr.setProCode(proCode);
            addr.setCityCode(cityCode);
            addr.setStreetCode(areaCode);
            addr.setFloorSum(floosSum);
            addr.setAddrName(addrName);

            addr.setCreatDate(new Date());

            boolean f = this.save(addr);

            if (f) {
                return addr;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 添加数据
     *
     * @param ftype
     * @return
     */
//    public ResultJSON<MkFacility> add(String ftype, String name, String creatCode) {
//        try {
//            MkFacility entity = new MkFacility();
//            if (StringUtil.isNotEmty(ftype)) {
//                entity.setFtype(Integer.valueOf(ftype));
//            }
//
//            if (StringUtil.isNotEmty(name)) {
//                entity.setName(name);
//            }
//
//            if (StringUtil.isNotEmty(creatCode)) {
//                entity.setCreatCode(creatCode);
//            }
//
//            // entity.setDel(1);
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
//
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


