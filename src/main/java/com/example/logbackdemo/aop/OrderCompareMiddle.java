package com.example.logbackdemo.aop;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect//表示这是一个切面类
@Component//加入IOC容器
@Order(50)
public class OrderCompareMiddle {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);


    @Pointcut("execution(public * com.example.logbackdemo.controller.OrderCompareController.orderCompare()))")
    public void webLog() {
    }
    @Before(value="webLog()")
    public void showBeginLog(){
        logger.info("AOP日志开始================order（50）==================");
    }
    @AfterThrowing(value="webLog()")
    public void showExceptionLog(){
        logger.info("AOP方法异常=============order（50）==============");
    }
    @AfterReturning(value="webLog()")
    public void showAfterReturnLog(){
        logger.info("AOP日志返回通知================order（50）=================");
    }
    @After(value="webLog()")
    public void showAfterLog() {
        logger.info("Aop后置通知，日志结束===============order（50）=================");
    }
}
