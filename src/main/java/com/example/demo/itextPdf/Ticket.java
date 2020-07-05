package com.example.demo.itextPdf;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Ticket {

    String idCardA;
    String idCardB;
    String addrInfo;
    String startDate;
    String endDate;
    BigDecimal amount;
    BigDecimal bAmount;
    Integer mouth;
    Integer day;
    String phone;
    String qm;
    String creadDate;


}