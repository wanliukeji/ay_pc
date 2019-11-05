package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table (name = "users")
@Data
public class Users implements Serializable {

    private static final long serialVersionUID = -6775222679549090142L;

    @Id
    @TableId(type = IdType.AUTO)
    @Column
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")
    private java.sql.Timestamp createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")
    private java.sql.Timestamp modifyDate;
    private String isEnabled;
    private String isLocked;

    @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")
    private java.sql.Timestamp lastLoginDate;
    private String lastLoginIp;

    @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")
    private java.sql.Timestamp lockDate;

}
