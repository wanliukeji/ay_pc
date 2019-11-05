package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "role")
public class Role implements Serializable {

    private static final long serialVersionUID = -6775222679549090142L;

    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd 24HH:mm:ss")
    private Date modifyDate;

    private String description;

    private int isSystem;

    private String name;

    private int permissions;

}
