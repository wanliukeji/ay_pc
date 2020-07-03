package com.example.demo.Utils;

import com.example.demo.entity.SysUser;
import com.example.demo.entity.mk.MkUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

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
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取作用域中的对象
     *
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

    public static void  setSessionUser(MkUser user) {
        HttpServletRequestUtil.getRequest().getSession().setAttribute("user", user);
    }

    public static void  delSessionUser() {
        HttpServletRequestUtil.getRequest().getSession().removeAttribute("user");
    }

    /**
     * 获取RESPONSE
     *
     * @return
     */
    public static HttpServletResponse getResponse() {

        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

    }

    /**
     * 获取SESSION
     *
     * @return
     */
    public static HttpSession getSession() {
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession();
        return session;
    }

    /**
     * 获取
     * GETOUTPUTSTREAM
     *
     * @return
     */
    public static ServletOutputStream getOutputStream() {
        try {
            HttpServletResponse response = getResponse();
            ServletOutputStream out = response.getOutputStream();
            return out;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void closeOutputStream(ServletOutputStream out) {
        try {
            if (out != null) {
                out.close();
            }
            System.gc();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取
     * GETOUTPUTSTREAM
     *
     * @return
     */
    public static PrintWriter getPrintWriter() {
        try {
            HttpServletResponse response = getResponse();
            PrintWriter out = response.getWriter();
            return out;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void closePrintWriter() {
        try {
            HttpServletResponse response = getResponse();
            PrintWriter out = response.getWriter();
            out.flush();
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
