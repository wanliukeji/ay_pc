package com.example.demo.Utils;

import java.io.File;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/9/26 11:39
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
public class FileUtil {
    public static void createFile(String fileUrl){
        File logoSaveFile = new File(fileUrl);

        if (!logoSaveFile.exists()) {
            logoSaveFile.mkdirs();
        }
    };
}
