package com.example.demo.itextPdf;


import com.example.demo.Utils.StringUtil;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import lombok.Data;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

@Data
public class PDFTempletTicket {

    private String templatePdfPath;
    private String ttcPath;
    private String targetPdfpath;
    private Ticket ticket;

    public PDFTempletTicket() {
        super();
    }

    public PDFTempletTicket(String templatePdfPath, String ttcPath,
                            String targetPdfpath, Ticket ticket) {
        this.templatePdfPath = templatePdfPath;
        this.ttcPath = ttcPath;
        this.targetPdfpath = targetPdfpath;
        this.ticket = ticket;
    }

    public void templetTicket(File file) throws Exception {

        PdfReader reader = new PdfReader(templatePdfPath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfStamper ps = new PdfStamper(reader, bos);

        /*使用中文字体 */
        BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

        /*BaseFont bf = BaseFont.createFont(PDFTicket.class.getResource("/") + "org/csun/ns/util/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);*/

        ArrayList<BaseFont> fontList = new ArrayList<BaseFont>();
        fontList.add(bf);

        AcroFields s = ps.getAcroFields();
        s.setSubstitutionFonts(fontList);
        s.setField("addr", ticket.getAddr());
        s.setField("area", StringUtil.toString(ticket.getArea()));
        s.setField("startDate", StringUtil.toString(ticket.getStartDate()));
        s.setField("endDate", StringUtil.toString(ticket.getEndDate()));
        s.setField("payDate", StringUtil.toString(ticket.getPayDate()));
        s.setField("amount", StringUtil.toString(ticket.getAmount()));
        s.setField("payDay", StringUtil.toString(ticket.getPayDate()));
        s.setField("yamount", StringUtil.toString(ticket.getYamount()));
        s.setField("startDay", StringUtil.toString(ticket.getStartDay()));
        s.setField("aName", ticket.getAName());
        s.setField("aIdCard", ticket.getAIdCard());
        s.setField("aphone", ticket.getAphone());
        s.setField("bName", ticket.getBName());
        s.setField("bIdCard", ticket.getBIdCard());
        s.setField("bphone", ticket.getBphone());
        s.setField("sy", ticket.getSy());
        s.setField("sm", ticket.getSm());
        s.setField("sd", ticket.getSd());
        s.setField("ey", ticket.getEy());
        s.setField("em", ticket.getEm());
        s.setField("ed", ticket.getEd());
//        String imgUrl = "D:\\Work\\ay_pc\\src\\main\\resources\\pdf\\gz.gif";
//        s.setField("imgUrl", imgUrl);

        ps.setFormFlattening(false);
        ps.close();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bos.toByteArray());
        fos.close();
    }


    public static void main(String[] args) {
        String pdfUrl = "D:\\Work\\ay_pc\\target\\classes\\pdf\\contractPdf20200707152412.pdf";
        String imgUrl = "D:\\Work\\ay_pc\\src\\main\\resources\\pdf\\gz.gif";
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}