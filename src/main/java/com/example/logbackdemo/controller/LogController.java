package com.example.logbackdemo.controller;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.RequestMethod;
///**
// * @author denglei
// * Java课程日志课后作业
// */
//
//@RestController
//@RequestMapping(value = "/log")
//@Component//加入IOC容器
//public class LogController {
//
//    /*
//    (1)出于资源利用的考虑，用static修饰的变量归这个类使用，不论这个类实例化多少个，大家用的都是同一个static变量
//    而日志记录的是当前类的日志，不是每个实例的日志，所以一个类只要一个Logger就可以了
//    (2)用final修饰不做修改的变量，表示一种编程习惯，表示该类的Logger只是记录该类的信息，否则日志会无法提供可以令人信服的记录
//    */
//    private static final Logger logger = LoggerFactory.getLogger(LogController.class);
//
//    @RequestMapping(value = "/logback",method = RequestMethod.GET)
//    public String say() throws Exception{
//        logger.info("[logback日志start][Java课程日志模块slf4j+logback][入参无]");
//        long methodStartTime = System.currentTimeMillis();
//
//        String everybodySay = "Hello World!";
//        Thread.sleep(1000);             //睡眠1s,为了更直观的查看方法耗时
//
//        long methodEndTime = System.currentTimeMillis();
//        logger.info("[logback日志end][Java课程日志模块slf4j+logback][出参:everybodySay={}][方法耗时:{}秒]",everybodySay,(methodEndTime-methodStartTime)/1000F);
//        return everybodySay;
//    }
//}



import com.example.logbackdemo.aop.ControllerWebLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/log")
public class LogController {

    @GetMapping("/method1")
    @ControllerWebLog(name = "方法一")
    public String methodBase1(Long id, String name) {

        return "Java培训作业：方法一";
    }
    @GetMapping("/method3")
    @ControllerWebLog(name = "方法三：用于验证基于方法的切面类")
    public String typeBase(Long id, String name) {

        return "Java培训作业：用于验证基于方法的切面类";
    }
}
