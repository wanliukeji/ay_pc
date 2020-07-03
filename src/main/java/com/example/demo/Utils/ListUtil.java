package com.example.demo.Utils;

import com.example.demo.entity.mk.MkAddress;
import com.example.demo.entity.mk.MkFacility;

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
    public static ArrayList<Object> ArrayList(){
        return new ArrayList<Object>();
    }

    /**
     * LinkedList
     * @return
     */
    public static LinkedList<?> LinkedList(){
        return new LinkedList<Object>();
    }


    /**
     *
     */

    public static List<MkFacility> items(List lits){
        List<MkFacility> item = new ArrayList<MkFacility>();
        if (null != lits && lits.size() > 0) {
            for (int i = 0; i < lits.size(); i++) {
                MkFacility entity = (MkFacility)lits.get(i);
                if (null == entity.getFtype()) {
                    entity.setChildren(getChilds(StringUtil.toString(entity.getId()), lits));
                    item.add(entity);
                }
            }
        }

        return item;
    }

    public static ArrayList<MkFacility> getChilds(String id, List item) {
        ArrayList <MkFacility>items = new ArrayList<MkFacility>();
        for (int i = 0; i < item.size(); i++) {
            MkFacility entity = (MkFacility) item.get(i);
            if (StringUtil.toString(entity.getFtype()).equals(id)) {
                  items.add(entity);
            }
        }
        return items;
    }


    /**
     *
     */

    public static List<MkAddress> addritems(List lits){
        List<MkAddress> item = new ArrayList<MkAddress>();
        if (null != lits && lits.size() > 0) {
            for (int i = 0; i < lits.size(); i++) {
                MkAddress entity = (MkAddress)lits.get(i);
                if (entity.getPcode() == 0) {
                    entity.setChildren(getChild(StringUtil.toString(entity.getCode()), lits));
                    item.add(entity);
                }
            }
        }

        return item;
    }

    public static ArrayList<MkAddress> getAddrChilds(String id, List item) {
        ArrayList <MkAddress>items = new ArrayList<MkAddress>();
        for (int i = 0; i < item.size(); i++) {
            MkAddress entity = (MkAddress) item.get(i);
//            if (StringUtil.toString(entity.()).equals(id)) {
                items.add(entity);
            }
        return items;
    }


    private static List<MkAddress> getChild(String id, List<MkAddress> rootMenu) {
        // 子菜单
        List<MkAddress> childList = new ArrayList<>();
        for (MkAddress menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (StringUtil.toString(menu.getPcode()).equals(id)) {
                childList.add(menu);
            }
        }

        // 把子菜单的子菜单再循环一遍
        for (MkAddress menu : childList) {
            menu.setChildren(getChild(StringUtil.toString(menu.getCode()), rootMenu));// 递归
        }

        // 判断递归结束
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}
