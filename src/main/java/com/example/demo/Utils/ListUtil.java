package com.example.demo.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/25 23:50
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
public class ListUtil {

    private List<?> Lisit;

    /**
     * 获取ArrayList对象
     * @return
     */
    public static ArrayList<?> ArrayList(){
        return new ArrayList<Object>();
    }

    /**
     * LinkedList
     * @return
     */
    public static LinkedList<?> LinkedList(){
        return new LinkedList<Object>();
    }



}
