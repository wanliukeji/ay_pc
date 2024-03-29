package com.example.demo.Utils;

//import com.example.demo.entity.Fied;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/26 12:07
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 类属性封装集合
 */
public class ClassUtil {

    /**
     * 获取属性名数组
     */
    public static String[] getFiledName(String path) {
        try {
            Class clazz = Class.forName(path);
            // 获取属性
            Field[] fields = clazz.getDeclaredFields(); // 返回所有的属性
            String[] fieldNames = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                fieldNames[i] = fields[i].getName();
            }
            return fieldNames;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* 根据属性名获取属性值
     *
     */

    public static Map getFieldValueByNameToMap(Object model) throws Exception {
        Map<Object, Object> maps = new HashMap<Object, Object>();
        for (Field field : model.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            maps.put(field.getName(), field.get(model));
        }
        return maps;
    }


    public static List<?> getFieldValueByNameToList(Object model) throws Exception {
        List<Object> list = new ArrayList<Object>();
        for (Field field : model.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            list.add(field.get(model));
        }
        return list;
    }

}
