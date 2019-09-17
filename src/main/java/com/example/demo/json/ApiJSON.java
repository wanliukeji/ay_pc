package com.example.demo.json;

import com.example.demo.enums.StatusEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/5/22 16:43
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 通用JSON格式响应类
 */
@SuppressWarnings("ALL")
@Data
@Slf4j
public class ApiJSON<T> implements Serializable {
    /**
     * 返回值代码 由Status类定义
     *
     * @see Status
     */
    private int code;
    /**
     * 返回的附加提示信息
     */
    private String msg;
    /**
     * 返回的数据 若没有则为空
     */
    private T data;

    /**
     * 无参构造必须有
     */
    private ApiJSON() {
    }

    /**
     * 全参构造方法
     *
     * @param code 状态码
     * @param msg  附加提示信息
     * @param data 数据
     */
    private ApiJSON(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功消息的返回
     * +2 重载方法
     *
     * @return
     */
    public static ApiJSON success() {
        return builder().code(StatusEnum.OK).build();
    }

    /**
     * 链式调用 设置返回数据
     *
     * @param data 返回数据
     * @return
     */
    public static ApiJSON data(Object data) {
        return builder().data(data).build();
    }

    /**
     * 成功消息的返回
     * +2 重载方法
     *
     * @param msg 附加提示消息
     * @return
     */
    public static ApiJSON success(String msg) {
        return builder().code(StatusEnum.OK).msg(msg).build();
    }

    /**
     * 成功消息的返回
     * +2 重载方法
     *
     * @param msg 附加提示消息
     * @return
     */
    public static ApiJSON successOrData(String msg,Object data) {
        return builder().code(StatusEnum.OK).msg(msg).data(data).build();
    }

    /**
     * 成功消息的返回
     * +2 重载方法
     *
     * @param msg  附加提示消息
     * @param data 需要返回的数据
     * @param <T>
     * @return
     */
    public static <T> ApiJSON success(String msg, T data) {
        return builder().code(StatusEnum.OK).msg(msg).data(data).build();
    }

    /**
     * 出错消息提示 该方法要求必须返回错误消息
     *
     * @param msg 错误消息
     * @return
     */
    public static ApiJSON error(String msg) {
        return builder().code(StatusEnum.ERR).msg(msg).build();
    }

    /**
     * 产生异常提示  必须返回异常消息
     *
     * @param msg 异常消息
     * @return
     */
    public static ApiJSON exception(String msg) {
        return builder().code(StatusEnum.EXCEPTION).msg(msg).build();
    }

    /**
     * 未找到的提示消息返回
     *
     * @return
     */
    public static ApiJSON notFound() {
        return builder().code(StatusEnum.NOTFOND).build();
    }

    /**
     * 构建一个AppResponseBuilder 对象
     *
     * @param <T>
     * @return
     */
    public static <T> AppResponseBuilder builder() {
        return new AppResponseBuilder();
    }

    /**
     * 内部类 用来解决static 不能使用泛型的问题
     *
     * @param <T>
     */
    private static class AppResponseBuilder<T> {
        /**
         * 返回值代码 由Status类定义
         *
         * @see Status
         */
        private int code;
        /**
         * 返回的附加提示信息
         */
        private String msg;
        /**
         * 返回的数据 若没有则为空
         */
        private T data;

        /**
         * 构建一个AppResponse 对象
         *
         * @return
         */
        public ApiJSON build() {
            return new ApiJSON<T>(this.code, this.msg, this.data);
        }

        /**
         * 链式调用 设置状态码
         *
         * @param status 状态 参考Status
         * @return
         */
        public AppResponseBuilder code(StatusEnum status) {
            this.code = status.value();
            return this;
        }

        /**
         * 链式调用 设置提示信息
         *
         * @param msg 提示信息
         * @return
         */
        public AppResponseBuilder msg(String msg) {
            this.msg = msg;
            return this;
        }

        /**
         * 链式调用 设置返回数据
         *
         * @param data 返回数据
         * @return
         */
        public AppResponseBuilder data(T data) {
            this.data = data;
            return this;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}