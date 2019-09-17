package com.example.demo.json;

import com.example.demo.exception.CodeMsg;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/5/24 16:43
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 通用JSON格式响应类
 */
@SuppressWarnings("ALL")
@Slf4j
@Data
public class ResultJSON<T> implements Serializable{

    /**
     *自定义状态码
     */
    private int code;

    /**
     * 请求状态描述，调试用
     */
    private String message;//

    /**
     * 请求数据，对象或数组均可
     */
    private T data;

    public ResultJSON() {
    }

    /**
     * 成功时候的调用
     * @param data data
     * @param <T> t
     * @return ResultJSON
     */
    public static <T> ResultJSON<T> success(T data){
        return new ResultJSON<T>(data);
    }

    /**
     * 失败时候的调用
     * @param CodeMsg CodeMsg
     * @param <T> t
     * @return ResultJSON
     */
    public static <T> ResultJSON<T> error(CodeMsg CodeMsg){
        return new ResultJSON<T>(CodeMsg);
    }

    /**
     * 成功的构造函数
     * @param data data
     */
    public ResultJSON(T data){
        this.code = 200;//默认200是成功
        this.message = "SUCCESS";
        this.data = data;
    }

    public ResultJSON(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 失败的构造函数
     * @param CodeMsg CodeMsg
     */
    private ResultJSON(CodeMsg CodeMsg) {
        if(CodeMsg != null) {
            this.code = CodeMsg.getCode();
            this.message = CodeMsg.getMsg();
        }
    }

    @Override
    public String toString() {

        String str = "{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
        return str;
    }
}
