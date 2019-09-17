package com.example.demo.aspect;
import com.example.demo.Utils.HttpServletRequestUtil;
import com.example.demo.Utils.StringUtil;
import com.example.demo.entity.SysLog;
import com.example.demo.entity.SysUser;
import com.example.demo.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Chenny
 * @version 1.0
 * @date 2019/7/22 10:19
 * @email bbc123good@163.com
 * @address http://106.12.38.131:8011
 * @describe 日志面向切面
 */
@Aspect
@Component
@Slf4j
public class LogAopAspect {

    @Autowired
    LogService logService;

    //声明切入点
    @Pointcut("execution(public * com.example.demo.api.*.*())")
//    @Pointcut(value = "@annotation(com.example.demo.aspect.Log)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        saveLog(point, time);
        return result;
    }

    void saveLog(ProceedingJoinPoint joinPoint, long time) throws InterruptedException {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        Log syslog = method.getAnnotation(Log.class);
        if (syslog != null) {
            // 注解上的描述
            sysLog.setDescription(syslog.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethodName(className + "." + methodName + "()");
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        sysLog.setStartTime(new Date());
        HttpServletRequest req = HttpServletRequestUtil.getRequest();
        if (null != req){
            sysLog.setRemoteAddr(req.getRemoteAddr());
        }
        sysLog.setExecuteTime(time);
        SysUser user = HttpServletRequestUtil.getSessionUser();
        if (null != user){
            sysLog.setUserId(user.getId()+"");
        }
        sysLog.setLocation(req.getRequestURI());
        sysLog.setEndTime(new Date());
        sysLog.setRequestUrl(req.getServerPort()+"");
        // 保存系统日志
        logService.save(sysLog);
        log.info(StringUtil.toString(sysLog));
    }
}
