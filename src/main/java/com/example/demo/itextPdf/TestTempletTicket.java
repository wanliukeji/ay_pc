package com.example.demo.itextPdf;


import com.example.demo.Utils.DateUtil;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;

public class TestTempletTicket {

    public static void main(String[] args) throws Exception {

        Ticket ticket = new Ticket();

        ticket.setIdCardA("360123198208058989");
        ticket.setIdCardB("370123198203213231");
        ticket.setAddrInfo("宁波市北仑区大榭街道海文化园15-107");
        ticket.setStartDate(DateUtil.formatDate2(DateUtil.getStringToDate("2020-04-17")));
        ticket.setEndDate(DateUtil.formatDate2(DateUtil.getStringToDate("2020-05-17")));
        ticket.setAmount(BigDecimal.valueOf(1000.00));
        ticket.setBAmount(BigDecimal.valueOf(3000.00));
        ticket.setMouth(2);
        ticket.setDay(3);
        ticket.setPhone("13278789090");
        ticket.setQm("CHENNY");
        ticket.setCreadDate(DateUtil.formatDate2(new Date()));

        PDFTempletTicket pdfTT = new PDFTempletTicket();
        pdfTT.setTemplatePdfPath("D:\\Work\\ay_pc\\src\\main\\resources\\pdf\\contractPdf.pdf");//你的pdf模板的位置

        String outName = "D:\\Work\\ay_pc\\src\\main\\resources\\pdf\\" + "contractPdf" + DateUtil.getDateYMDHMS() + ".pdf";
        pdfTT.setTargetPdfpath(outName);
        pdfTT.setTicket(ticket);

        System.out.println(outName);

        File file = new File(outName);
        file.createNewFile();
        pdfTT.templetTicket(file);
    }
}