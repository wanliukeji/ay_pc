package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkAttrMapper;
import com.example.demo.entity.mk.MkAttr;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class MkAttrService extends ServiceImpl<MkAttrMapper, MkAttr> {


    /**
     * 添加数据
     *
     * @return
     */
    public ResultJSON<MkAttr> add(String fname, Integer pid) {
        try {
            MkAttr entity = new MkAttr();
            if (StringUtil.isNotEmty(fname) && StringUtil.isNotEmty(pid)) {
                entity.setFname(fname);
                entity.setPid(pid);
            }

            boolean f = this.save(entity);
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
    public List list(Long pid) {

        QueryWrapper<MkAttr> qw = new QueryWrapper<MkAttr>();
        try {
            if (StringUtil.isNotEmty(pid)) {
                qw.eq("pid", pid);
            }
            List<MkAttr> infos = this.list(qw);
            return infos;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

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


