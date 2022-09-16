package com.jia.bookShop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ����ǰ���ʾΪ�쳣��������
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String Exception(Throwable ex,Model model){
        //ex��ʾ�������������쳣��Ϣ
        model.addAttribute("ex",ex);
        return "error";
    }
}
