package com.example.demo.itextPdf;


import com.example.demo.Utils.DateUtil;
import com.example.demo.Utils.HttpServletRequestUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;

public class TestTempletTicket {


    public static String gen (String addr, Double area, String startDate, String endDate, String payDate, BigDecimal amount,
                                     BigDecimal yamount, Integer startDay, String aName,String aIdCard, String aphone, String sy,
                                    String sm, String sd, String bName, String bIdCard, String bphone, String ey, String em, String ed) {
        try {
            Ticket ticket = new Ticket();
            ticket.setAmount(amount);
            ticket.setEndDate(endDate);
            ticket.setStartDate(startDate);
            ticket.setAddr(addr);
            ticket.setArea(area);
            ticket.setAIdCard(aIdCard);
            ticket.setAName(aName);
            ticket.setAphone(aphone);
            ticket.setBIdCard(bIdCard);
            ticket.setBphone(bphone);
            ticket.setEd(ed);
            ticket.setEm(em);
            ticket.setEy(ey);
            ticket.setPayDate(payDate);
            ticket.setSd(sd);
            ticket.setSm(sm);
            ticket.setSy(sy);
            ticket.setYamount(yamount);
            ticket.setStartDay(startDay);
            ticket.setBName(bName);
            PDFTempletTicket pdfTT = new PDFTempletTicket();

//            //保存本地项目
//            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
//            String logoRealPathDir = path + "/static" + logoPathDir;
//            File logoSaveFile = new File(logoRealPathDir);
//
//            if (!logoSaveFile.exists()) {
//                logoSaveFile.mkdirs();
//            }


            Resource resource = new ClassPathResource("");
            String url = resource.getFile().getAbsolutePath();

            String baseUrl = url + "\\pdf\\contractPdf.pdf";
            pdfTT.setTemplatePdfPath(baseUrl);//你的pdf模板的位置




            String outName = url + "\\static\\upload\\" + "contractPdf" + DateUtil.getDateYMDHMS() + ".pdf";


//            String outName = path + "\\static\\upload\\" + "contractPdf" + DateUtil.getDateYMDHMS() + ".pdf";

            HttpServletRequest request = HttpServletRequestUtil.getRequest();
            String contextPath = request.getContextPath();
            String basePath = request.getScheme()+"://"+request.getServerName()+":"+
                    request.getServerPort()+contextPath+"/";
            String path2 = basePath + "/static/upload/" + "contractPdf" + DateUtil.getDateYMDHMS() + ".pdf";
            pdfTT.setTargetPdfpath(path2);

            pdfTT.setTicket(ticket);
            File file = new File(outName);
            file.createNewFile();
            pdfTT.templetTicket(file);
            return path2;
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ex.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) throws Exception {
        Resource resource = new ClassPathResource("");
        System.out.println(resource.getFile().getAbsolutePath());

    }
}