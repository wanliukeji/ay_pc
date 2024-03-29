package com.example.demo.service.mk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.mk.MkOtherBountyMapper;
import com.example.demo.entity.mk.MkAttr;
import com.example.demo.entity.mk.MkOtherBounty;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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
public class MkOtherBountyService extends ServiceImpl<MkOtherBountyMapper, MkOtherBounty> {


    @Autowired
    private MkAttrService attrService;


    public ResultJSON<?> getInfo(Integer id) {
        QueryWrapper<MkOtherBounty> qw = new QueryWrapper<MkOtherBounty>();
        try {
            if (StringUtil.isNotEmty(id)) {
                MkOtherBounty entity = this.getById(id);
                if(StringUtil.isNotEmty(entity)) {
                    return ResultJSON.success(entity);
                }
                return  ResultJSON.error("未找到数据");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
        return  ResultJSON.error("未找到数据");
    }

    public ResultJSON<?> add(String fname, BigDecimal amount, Integer fstatus, String remark,Integer ftype) {
        Map <Object, Object> map = new HashMap<>();
        try {
            MkOtherBounty entity  = new MkOtherBounty();
            entity.setAmount(amount);
            entity.setFname(fname);
            entity.setFstatus(fstatus);
            entity.setRemark(remark);
            entity.setFtype(ftype);
            boolean f = this.save(entity);
            if (f) {
                return ResultJSON.success(entity);
            }
            return ResultJSON.error(CodeMsg.UPDATE_ERROR);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }

    }

    public ResultJSON<?> list(String userId, Integer ftype) {
        QueryWrapper<MkOtherBounty> qw = new QueryWrapper<MkOtherBounty>();
        try {
            if (StringUtil.isNotEmty(userId)) {
                qw.eq("userId", userId).eq("ftype",ftype);
                List<MkOtherBounty> entitys = this.list(qw);

                for (int i = 0; i < entitys.size(); i++) {
                    MkOtherBounty pojo = entitys.get(i);
                    List<MkAttr> attrs = attrService.list(pojo.getId());
                    pojo.setChildren(attrs);
                }
                return ResultJSON.success(entitys);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
        return  ResultJSON.error("未找到数据");
    }

    public List<Map<String, Object>> getInfos(String name, List list) {
        for (int i = 0; i < list.size(); i++) {
            Map map = (Map) list.get(i);
            List item = new ArrayList();
            if (StringUtil.isNotEmty(map.get(name))) {
                String []str = map.get(name).toString().split(",");
                for (int j = 0; j < str.length; j++) {
                    MkOtherBounty entity = this.getById(str[j]);
                    item.add(entity);
                }
                map.put(name, item);
            }
        }
        return  list;
    }
}


