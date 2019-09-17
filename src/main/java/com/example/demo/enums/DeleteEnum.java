package com.example.demo.enums;

import com.example.demo.state.DeleteState;
import com.example.demo.state.EnableState;

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
public enum DeleteEnum {

    TRUE(1, true),

    FALSE(2, false);

    private Integer value;

    private Boolean name;

    DeleteEnum(Integer value, boolean name) {
        this.value = value;
        this.name = name;
    }

    public Integer value() {
        return value;
    }

    public static List<DeleteState> getStates() {
        List<DeleteState> list = new ArrayList();
        DeleteEnum[] array = DeleteEnum.values();
        for (DeleteEnum l1 : array) {
            list.add(new DeleteState(l1.name, l1.value));
        }
        return list;
    }

}
