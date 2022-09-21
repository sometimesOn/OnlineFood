package com.jia.book.controller;

import com.jia.book.myssm.util.StringUtil;
import com.jia.book.pojo.Book;
import com.jia.book.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class bookController {

    private BookService bookService;

    public String index(String keyword, Integer pageNo, HttpSession session,String oper){
        if(pageNo==null){
            pageNo = 1;
        }


        if(StringUtil.isNotEmpty(oper)&&oper.equals("search")) {
            if (StringUtil.isEmpty(keyword)) {
                pageNo = 1;
                keyword = "";
            }
            session.setAttribute("keyword", keyword);
        }else {
            Object keywordObj = session.getAttribute("keyword");
            if(keyword!=null){
                keyword = (String) keywordObj;
            }else {
                keyword = "";
            }
        }

        int pageCount = bookService.getPageCount(keyword);
        if(pageCount==0){
            pageCount = 1;
        }
        List<Book> bookList = bookService.getBookList(keyword,pageNo);
        session.setAttribute("bookList",bookList);
        session.setAttribute("pageNo",pageNo);
        session.setAttribute("pageCount",pageCount);

        return "user/index";

    }
}
