package com.example.demo.Utils;

import cn.hutool.http.HttpResponse;
import com.example.demo.entity.SysUser;
import com.sun.xml.internal.ws.client.ResponseContext;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 16:56
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 请求实体类
 */
public class HttpServletRequestUtil {

    /**
     * 获取请求对象
     * @return
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取作用域中存入的对象
     * @return
     */
    public static SysUser getSessionUser() {
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("user");
        if (null != user) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * 获取RESPONSE
     * @return
     */
    public static HttpServletResponse getResponse() {

        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

    }

    /**
     * 获取SESSION
     * @return
     */
    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession();
        return session;
    }
}
