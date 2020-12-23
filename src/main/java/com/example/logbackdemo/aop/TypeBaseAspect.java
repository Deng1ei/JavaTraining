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

/**
 * 基于方法三的切面类
 * 对比基于LogController类的切面
 */
@Aspect//表示这是一个切面类
@Component//加入IOC容器
@Order(200)
public class TypeBaseAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @Pointcut("execution(public * com.example.logbackdemo.controller.LogController.typeBase(Long,String))")
    public void webLog() {
    }
    @Before(value="webLog()&& @annotation(controllerWebLog)")
    public void showBeginLog(JoinPoint joinPoint,ControllerWebLog controllerWebLog){
        logger.info("======================方法三的开始标志：这是方法三的通知，基于方法的切面==========================" );
    }


    @AfterThrowing(value="webLog()")
    public void showExceptionLog(){
        logger.info("===============================AOP日志异常：方法三===================================");
    }
    @After(value="webLog()")
    public void showAfterLog() {
        logger.info("=============================基于方法三的日志后置通知=============================");
    }
    @AfterReturning(value="webLog()")
    public void showAfterReturnLog() {
        logger.info("=============================基于方法三的日志返回通知====================================");
    }

}
