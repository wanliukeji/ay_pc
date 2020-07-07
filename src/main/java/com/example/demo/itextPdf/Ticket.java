package com.example.demo.itextPdf;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Ticket implements Serializable {
    private String addr;
    private Double area;
    private String startDate;
    private String endDate;
    private String payDate;
    private BigDecimal amount;
    private BigDecimal yamount;
    private Integer startDay;
    private String aName;
    private String aIdCard;
    private String aphone;
    private String sy;
    private String sm;
    private String sd;
    private String bName;
    private String bIdCard;
    private String bphone;
    private String ey;
    private String em;
    private String ed;
}