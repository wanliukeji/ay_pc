package com.example.demo.exception;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/5/22 16:43
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 用于实现接口返回规范的类  所有接口返回值都由该类封装
 */
public class CodeMsg {
    
    private int code;
    private String msg;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    /*服务端异常*/
    public static CodeMsg SUCCESS = new CodeMsg(200,"SUCCESS");
    public static CodeMsg SERVER_ERROR = new CodeMsg(100,"系统异常：%s");
    public static CodeMsg BIND_ERROR = new CodeMsg(101,"(绑定异常)参数校验异常：%s"); /*用占位符 传入一个参数*/
    public static CodeMsg SESSION_ERROR = new CodeMsg(102,"没有SESSION！"); /*用占位符 传入一个参数*/
    public static CodeMsg REQUEST_ERROR = new CodeMsg(103,"非法请求！"); /*用占位符 传入一个参数*/
    public static CodeMsg REQUEST_OVER_LIMIT = new CodeMsg(104,"请求次数过多！"); /*用占位符 传入一个参数*/

    private CodeMsg( ) {
    }
    private CodeMsg( int code,String msg ) {
        this.code = code;
        this.msg = msg;
    }
    //不定参的构造函数
    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }
    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }
}