package com.example.demo.req;

import lombok.Data;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/24 10:25
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 通用查询对象
 */
@Data
public class ReqParam {

    /**
     * 起始页
     */
    private Integer pageNo;

    /**
     * 页大小
     */
    private Integer pageSize;

    /**
     * 内容
     */
    private String context;
}
