package com.example.demo.entity.mk;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "mk_china")
public class MkChina implements Serializable {

    private static final long serialVersionUID = -6775222679549090142L;

    @Id
    @TableId(type = IdType.AUTO)
    @Column
    private long id;
    private long pid;
    private String name;
    private long type;

}
