package com.example.logbackdemo.controller;

import com.example.logbackdemo.aop.ControllerWebLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/log")
public class OrderCompareController {
        @GetMapping("/ordercompare")
        @ControllerWebLog(name = "====================该方法用于验证Order数值对日志的影响===================")
        public String orderCompare() {
            return "====================该方法用于验证Order数值对日志的影响===================";
        }
}


