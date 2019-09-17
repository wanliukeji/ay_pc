package com.example.demo.enums;

import com.example.demo.state.EnableState;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/5/22 16:43
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
@NoArgsConstructor
public enum StatusEnum {

    OK(200,"SUCCESS"),

    NOTFOND(404, "NOTFOND"),

    EXCEPTION(500, "EXCEPTION"),

    ERR(505, "ERR");

    private Integer value;

    private String name;

    StatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer value() {
        return value;
    }

    public static List<EnableState> getStates() {
        List<EnableState> list = new ArrayList();
        StatusEnum[] array = StatusEnum.values();
        for (StatusEnum l1 : array) {
            list.add(new EnableState(l1.name, l1.value));
        }
        return list;
    }
}
