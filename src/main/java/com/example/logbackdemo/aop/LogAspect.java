package com.example.logbackdemo.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Aspect//表示这是一个切面类
@Component//加入IOC容器
@Order(150)
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();
    @Pointcut("execution(public * com.example.logbackdemo.controller.LogController.*(..))")
    public void webLog() {
    }
    @Before(value="webLog()&& @annotation(controllerWebLog)")
    public void showBeginLog(JoinPoint joinPoint,ControllerWebLog controllerWebLog){

        // 接收到请求
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        // 记录请求内容，threadInfo存储所有内容
        logger.info("AOP日志开始");
        Map<String, Object> threadInfo = new HashMap<>();
        logger.info("URL : " + request.getRequestURL());
        threadInfo.put("url", request.getRequestURL());
        logger.info("URI : " + request.getRequestURI());
        threadInfo.put("uri", request.getRequestURI());
        logger.info("HTTP_METHOD : " + request.getMethod());
        threadInfo.put("httpMethod", request.getMethod());
        logger.info("REMOTE_ADDR : " + request.getRemoteAddr());
        threadInfo.put("ip", request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        threadInfo.put("classMethod",
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        threadInfo.put("args", Arrays.toString(joinPoint.getArgs()));
        logger.info("USER_AGENT"+request.getHeader("User-Agent"));
        threadInfo.put("userAgent", request.getHeader("User-Agent"));
        logger.info("执行方法：" + controllerWebLog.name());
        threadInfo.put("methodName", controllerWebLog.name());
        threadLocal.set(threadInfo);
    }



    @AfterThrowing(value="webLog()")
    public void showExceptionLog(){
        logger.info("AOP方法异常");
    }
    @AfterReturning(value="webLog()")
    public void showAfterReturnLog(){
        logger.info("AOP日志返回通知");
    }
    @After(value="webLog()")
    public void showAfterLog() {
        logger.info("Aop后置通知，日志结束");
    }
}
