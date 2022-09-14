package com.jia.bookShop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 将当前类表示为异常处理的组件
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String Exception(Throwable ex,Model model){
        //ex表示控制器方法的异常信息
        model.addAttribute("ex",ex);
        return "error";
    }
}
