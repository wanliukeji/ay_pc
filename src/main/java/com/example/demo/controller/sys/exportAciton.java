package com.example.demo.controller.sys;

import com.example.demo.Utils.DateUtil;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.entity.FileEntity;
import com.example.demo.jasper.JasperHelper;
import com.example.demo.service.FileService;
import io.swagger.annotations.Api;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/25 9:33
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 导出控制器
 */
@Api(value = "导出模板操作", description = "导出模板操作")
@Controller
public class exportAciton {

    @Autowired
    FileService fileService;

    /**
     * 登录后台
     *
     * @return
     */
    @GetMapping("/api/export/file")
    public void export() {
        List<FileEntity> list = fileService.list(null);

        //JRBeanCollectionDataSource通过构造注入collection类型的参数，这里我们用的是list结构
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(list);

        //构建参数map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("query", "其他参数测试");
        //子数据源测试
        map.put("chart1", list);
        map.put("IMAGE_URL","D:\\Work\\ay_pc\\src\\main\\resources\\static\\Qrc\\myQRC.png");

        //指定模板文件
        ServletContext context = HttpServletRequestUtil.getSession().getServletContext();
        File reportFile = null;
        reportFile = new File(context.getRealPath("jasper\\demo.jasper"));
        //指定导出文件名称
        String exportFilePath = "报表导出测试单" + DateUtil.getDateYMDHMS();
        //调用工具类
        //JasperHelper.showHtml(exportFilePath , reportFile.getPath(), request,response, map, jrDataSource);
        //JasperHelper.export("excel", exportFilePath, reportFile, request, response, map, jrDataSource);
        try {
        // JasperHelper.showPdf(exportFilePath, reportFile.getPath(), request, response, map, jrDataSource);
            JasperHelper.export("pdf", exportFilePath, reportFile, map, jrDataSource);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

}
