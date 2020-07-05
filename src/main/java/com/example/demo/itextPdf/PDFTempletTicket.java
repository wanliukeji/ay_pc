package com.example.demo.itextPdf;


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

        s.setField("idCardA", ticket.getIdCardA());
        s.setField("idCardB", ticket.getIdCardB());
        s.setField("addrInfo", ticket.getAddrInfo());
        s.setField("startDate", ticket.getStartDate()+"");
        s.setField("endDate", ticket.getEndDate() + "");
        s.setField("amount", ticket.getAmount() + "");
        s.setField("bAmount", ticket.getBAmount() + "");
        s.setField("mouth", ticket.getMouth() + "");
        s.setField("day", ticket.getDay() + "");
        s.setField("phone", ticket.getPhone() + "");
        s.setField("qm", ticket.getQm() + "");
        s.setField("creatDate", ticket.getCreadDate() + "");

        ps.setFormFlattening(false);
        ps.close();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bos.toByteArray());
        fos.close();
    }
}