package com.example.demo.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Vector;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 字符工具类
 */
public class StringUtil {

    public static String toString(Object data) {
        if (null != data) {
            return data + "";
        }
        return "";
    }

    /**
     * 判断是否为空
     *
     * @param data
     * @return
     */
    public static boolean isNotEmty(Object data) {

        if (null != data || !"".equals(data)) {
            return true;
        }
        return false;
    }

    ;

    /**
     * 判断是否为空
     *
     * @param data
     * @return
     */
    public static boolean isEmty(Object data) {

        if (null != data || !"".equals(data)) {
            return false;
        }
        return true;
    }

    public static Collection StringToList(String ids) {

        ArrayList listIds = new ArrayList();

        String [] items = ids.split(",");

        for (int i = 0; i < items.length; i++) {
             listIds.add(items[i]);
        }

        return (Collection)listIds;
    };

    public static ArrayList<Integer> StringToArrayList(String ids) {

        ArrayList listIds = new ArrayList();

        String [] items = ids.split(",");

        for (int i = 0; i < items.length; i++) {
            listIds.add(items[i]);
        }
        return listIds;
    };

    public static String get_Feid_type(String val) {
        if (val.contains("加工")) {
            return "加工";
        } else if (val.contains("安装")) {
            return "安装";
        } else if (val.contains("点工")) {
            return "点工";
        } else if (val.contains("门窗厂")) {
            return "门窗厂";
        } else if (val.contains("玻璃厂")) {
            return "玻璃厂";
        } else if (val.contains("配件")) {
            return "配件";
        } else if (val.contains("定点")) {
            return "定点";
        } else if (val.contains("店铺")) {
            return "店铺";
        } else if (val.contains("厂房")) {
            return "厂房";
        } else if (val.contains("设备")) {
            return "设备";
        } else {
            return "加工";
        }

    }

}
