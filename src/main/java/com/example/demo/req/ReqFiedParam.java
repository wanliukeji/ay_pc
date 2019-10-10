package com.example.demo.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/24 10:25
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 通用查询对象
 */
@Data
public class ReqFiedParam implements Serializable {

    private Integer pageNo;

    private Integer pageSize;

    private Integer procode;

    private Integer citycode;

    private Integer countycode;

    private String type;

    private String fw;

}
