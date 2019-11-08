package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Utils.ExportExcelUtil;
import com.example.demo.Utils.FileUploadTool;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.dao.FiedMapper;
import com.example.demo.dao.MenuMapper;
import com.example.demo.entity.Fied;
import com.example.demo.entity.FileEntity;
import com.example.demo.entity.SysMenu;
import com.example.demo.exception.CodeMsg;
import com.example.demo.json.ApiJSON;
import com.example.demo.json.ResultJSON;
import com.example.demo.req.ReqParam;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
public class MenuService extends ServiceImpl<MenuMapper, SysMenu> implements Serializable {

    /**
     * 获取页面数据
     * 查询数据
     *
     * @param param
     * @return
     */
    public ResultJSON<?> getByPage(ReqParam param) {

        try {
            QueryWrapper<SysMenu> qw = new QueryWrapper<>();
            qw.like("name", param.getContext()).or()
                    .like("navigation", param.getContext()).or()
                    .like("remark", param.getContext());

            PageHelper.startPage(param.getPageNo(), param.getPageSize());
            List<SysMenu> menus = this.list(qw);
            qw.orderByDesc("createTime");
            PageInfo<SysMenu> page = new PageInfo<SysMenu>(menus);
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
    public Object export(String ids, String fileUrl, String title, String beanPath, String[] columnNames) throws Exception {

        ExportExcelUtil<SysMenu> util = new ExportExcelUtil<SysMenu>();
        // 准备数据
        Collection<SysMenu> list = new ArrayList<SysMenu>();

        list = this.listByIds(StringUtil.StringToList(ids));

        HttpServletRequestUtil.getResponse();

        HttpServletResponse resp = HttpServletRequestUtil.getResponse();

        OutputStream out = HttpServletRequestUtil.getResponse().getOutputStream();
        out = new FileOutputStream(fileUrl);
        //设置文件名，boot.xls 是下载文件名
        resp.addHeader("Content-Disposition", "attachment;filename=" + new String(title.getBytes()));
        //设置输出流
        OutputStream os = new BufferedOutputStream(resp.getOutputStream());
        //设置格式
        resp.setContentType("application/vnd.ms-excel;charset=gb2312");
        try {
            return util.exportExcel(title, columnNames, list, out, ExportExcelUtil.EXCEL_FILE_2003);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    ;

    /**
     * 批量删除
     * 假删
     *
     * @param ids
     * @return
     * @throws Exception
     */
    public ResultJSON<?> delete(String ids) throws Exception {
        // 准备数据

        boolean f = false;
        List idsList = StringUtil.StringToArrayList(ids);

        for (int i = 0; i < idsList.size(); i++) {
            SysMenu menu = this.getById((Integer) idsList.get(i));
            menu.setDelState(1);
            f = this.saveOrUpdate(menu);
        }

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
            SysMenu menu = new SysMenu();
            menu = getById(idItems.get(i));
            menu.setEnable(1);
            saveOrUpdate(menu);
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
            SysMenu menu = new SysMenu();
            menu = getById(idItems.get(i));
            menu.setEnable(0);
            saveOrUpdate(menu);
        }
        return ResultJSON.success(null);
    }

    @Transactional
    public ApiJSON<Boolean> import_file(MultipartFile multipartFile) {
        BufferedReader input;
        HttpServletRequest req = HttpServletRequestUtil.getRequest();

        try {
            FileEntity entity = new FileEntity();
            FileUploadTool fileUploadTool = new FileUploadTool();
            entity = fileUploadTool.createFile(multipartFile, req, null);
            String s = new String();
            InputStreamReader isr = new InputStreamReader(new FileInputStream(entity.getPath()), "GBK");
            input = new BufferedReader(isr);
            int i = 0;
            while ((s = input.readLine()) != null) { // 判断是否读到了最后一行
                i++;
                SysMenu menu = new SysMenu();
                String info[] = s.split("\t");
                if (info.length >= 12) {
                    if (i != 1) { //忽略表头

                    }
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
