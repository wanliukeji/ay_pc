package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.ExportExcelUtil;
import com.example.demo.Utils.NumberUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.FiedMapper;
import com.example.demo.entity.Fied;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/23 11:03
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@Service
public class FiedService extends ServiceImpl<FiedMapper, Fied> implements Serializable {

    /**
     * 获取页面数据
     * 查询数据
     *
     * @param param
     * @return
     */
    public ResultJSON<?> getByPage(ReqParam param) {

//        try {
//            List<Fied> page = baseMapper.getByPage(Integer.valueOf(param.getPageNo()),
//                                                   Integer.valueOf(param.getPageSize()),
//                                                   param.getContext());
//            return page;
//        } catch (Exception we) {
//            return null;
//        }

        try {
            IPage<Fied> page = new Page<Fied>(param.getPageNo(), param.getPageSize());
            QueryWrapper<Fied> qw = new QueryWrapper<Fied>();
            qw.like("head_line", param.getContext()).or()
                    .like("company_name", param.getContext()).or()
                    .like("address", param.getContext());
            page = this.page(page, null);
            ((Page<Fied>) page).setDesc("createDate");
            return ResultJSON.success(page);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResultJSON.error(CodeMsg.SESSION_ERROR);
        }
    }

    ;

    /**
     * 导出当前页面内容
     *
     * @param ids
     * @return
     * @throws Exception
     */
    public ResultJSON<?> export(String ids) throws Exception {

        ExportExcelUtil<Fied> util = new ExportExcelUtil<Fied>();
        String path = "com.example.demo.entity.Fied";
        // 准备数据
        Collection<Fied> list = new ArrayList<Fied>();

        list = this.listByIds(StringUtil.StringToList(ids));

        String[] columnNames = {"", "编号", "标题", "类型", "区域", "QQ", "微信", "手机",
                "联系人", "创建时间", "公司名称", "省份", "城市", "市区", "视频路径",
                "广告地址", "作品一", "作品二", "作品三", "案例一", "案例二", "案例三",
                "服务", "详细地址"};

        String fileUrl = "D:/exportFile/加工信息数据表.xls";

        try {
            util.exportExcel("加工信息导出", columnNames, list, new FileOutputStream(fileUrl), ExportExcelUtil.EXCEL_FILE_2003);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    ;

    /**
     * 批量删除
     *
     * @param ids
     * @return
     * @throws Exception
     */
    public ResultJSON<?> delete(String ids) throws Exception {
        // 准备数据
        boolean f = this.removeByIds(StringUtil.StringToList(ids));
        return ResultJSON.success(f);
    }

    ;

    /**
     * 批量启用
     *
     * @param ids
     * @return
     * @throws Exception
     */
    public ResultJSON<?> aunt(String ids) throws Exception {

        ArrayList<Integer> idItems = StringUtil.StringToArrayList(ids);
        for (int i = 0; i < idItems.size(); i++) {
            Fied fied = new Fied();
            fied = getById(idItems.get(i));
            fied.setStatus(1);
            saveOrUpdate(fied);
        }
        return ResultJSON.success(null);
    }

    ;

    /**
     * 批量禁用
     *
     * @param ids
     * @return
     * @throws Exception
     */
    public ResultJSON<?> unaunt(String ids) throws Exception {

        ArrayList<Integer> idItems = StringUtil.StringToArrayList(ids);
        for (int i = 0; i < idItems.size(); i++) {
            Fied fied = new Fied();
            fied = getById(idItems.get(i));
            fied.setStatus(0);
            saveOrUpdate(fied);
        }
        return ResultJSON.success(null);
    }

    ;

//        查询分页备份
//        try {
//            IPage<Fied> page = new Page<Fied>(param.getPageNo(), param.getPageSize());
//            QueryWrapper<Fied> qw = new QueryWrapper<Fied>();
//            qw.like("head_line", param.getContext()).or()
//                    .like("company_name", param.getContext()).or()
//                    .like("address", param.getContext());
//            page = this.page(page, null);
//            ((Page<Fied>) page).setDesc("createDate");
//            return ResultJSON.success(page);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return ResultJSON.error(CodeMsg.SESSION_ERROR);
//        }
}
