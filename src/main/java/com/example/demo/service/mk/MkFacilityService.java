package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.DateUtil;
import com.example.demo.Utils.ListUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkFacilityMapper;
import com.example.demo.entity.mk.MkFacility;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class MkFacilityService extends ServiceImpl<MkFacilityMapper, MkFacility> {

    /**
     * 添加数据
     *
     * @param ftype
     * @return
     */
    public ResultJSON<MkFacility> add(String ftype, String name, String creatCode) {
        try {
            MkFacility entity = new MkFacility();
            if (StringUtil.isNotEmty(ftype)) {
                entity.setFtype(Integer.valueOf(ftype));
            }

            if (StringUtil.isNotEmty(name)) {
                entity.setName(name);
            }

            if (StringUtil.isNotEmty(creatCode)) {
                entity.setCreatCode(creatCode);
            }

            // entity.setDel(1);

            entity.setCreateDate(new Date());
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
    public ResultJSON<?> page(String val, Integer ftype, Integer limit, Integer row) {

        QueryWrapper<MkFacility> qw = new QueryWrapper<MkFacility>();
        try {
            if (StringUtil.isNotEmty(val)) {
                qw.like("name", val);
            }

            if (StringUtil.isNotEmty(ftype)) {
                qw.eq("ftype", ftype);
            }

            if (StringUtil.isNotEmty(limit) && StringUtil.isNotEmty(row)) {
                PageHelper.startPage(limit, row);
            }

            qw.eq("del", 1);
            List<MkFacility> infos = this.list(qw);
            infos = ListUtil.items(infos);

            PageInfo<MkFacility> page = new PageInfo<MkFacility>(infos);
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    public ResultJSON<?> del(Integer id) {

        try {
            if (StringUtil.isNotEmty(id)) {
                MkFacility entity = this.getById(id);
                entity.setDel(0);
                boolean f = this.saveOrUpdate(entity);
                if (f) {
                    return ResultJSON.error(CodeMsg.UPDATE_SUCCESS);
                } else {
                    return ResultJSON.error(CodeMsg.UPDATE_ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return ResultJSON.error(CodeMsg.SERVER_ERROR);
        }
        return ResultJSON.error(CodeMsg.SERVER_ERROR);
    }

    public ResultJSON<?> edit(Integer id, Integer ftype, String name, String creatCode) {
        try {
            MkFacility entity = new MkFacility();
            if (StringUtil.isNotEmty(id)) {
                entity = this.getById(id);
            }

            if (StringUtil.isNotEmty(ftype)) {
                entity.setFtype(Integer.valueOf(ftype));
            }

            if (StringUtil.isNotEmty(name)) {
                entity.setName(name);
            }

            entity.setCreateDate(DateUtil.getStringToDate(new Date().toString()));
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

    public ResultJSON<?> list() {
        QueryWrapper<MkFacility> qw = new QueryWrapper<MkFacility>();
        try {
            qw.eq("del", 1);
            List<MkFacility> infos = this.list(qw);
            infos = ListUtil.items(infos);
            PageInfo<MkFacility> page = new PageInfo<MkFacility>(infos);
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }

    public ResultJSON<?> getList(Integer ftype) {
        QueryWrapper<MkFacility> qw = new QueryWrapper<MkFacility>();
        try {
            qw.eq("ftype", ftype);
            qw.eq("del", 1);
            List<MkFacility> infos = this.list(qw);
            PageInfo<MkFacility> page = new PageInfo<MkFacility>(infos);
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }


    public List<Map<String, Object>> getInfos(String name, List list) {
            List item = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                if (StringUtil.isNotEmty(map.get(name))) {
                    String []str = map.get(name).toString().split(",");
                    for (int j = 0; j < str.length; j++) {
                        MkFacility entity = this.getById(str[j]);
                        item.add(entity);
                    }
                    map.put(name, item);
                }
            }
        return  list;
    }
}


