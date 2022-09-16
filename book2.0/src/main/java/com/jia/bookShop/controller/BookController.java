package com.jia.bookShop.controller;

import com.github.pagehelper.PageInfo;
import com.jia.bookShop.pojo.Book;
import com.jia.bookShop.service.BookService;
import com.jia.bookShop.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/book",method = RequestMethod.GET)
    @ResponseBody
    public List<Book> getAllBook(){
        List<Book> bookList = bookService.getAllBook("");
        return bookList;
    }


    @RequestMapping(value = "/book/page" ,method = RequestMethod.GET)
    public String getBookPage(Integer pageNum, String keyword, HttpSession session, String operate){
        if(pageNum == null){
            pageNum = 1;
        }
        //�Ƿ�ͨ��search����ѯ�鼮��Ϣ
        if(StringUtil.isNotEmpty(operate)&&operate.equals("search")){
            if(StringUtil.isEmpty(keyword)){
                keyword = "";
            }
            session.setAttribute("keyword",keyword);
        }
        else {
            Object keywordObj = session.getAttribute("keyword");
            if(keyword!=null){
                keyword = (String) keywordObj;
            }else {
                keyword = "";
            }
        }
        //��ȡ��pageNumҳ���鼮��Ϣ
        PageInfo<Book> pageInfo = bookService.getBookPage(pageNum,keyword);
        //���������й�������
        session.setAttribute("pageInfo",pageInfo);
        //��תҳ��
        return "user/index";
    }

    @RequestMapping(value = "/book",method = RequestMethod.PUT)
    public void updateBook(@RequestBody Map<String,Object> map, HttpServletResponse response) throws IOException {
        Integer bookId = (Integer) map.get("bookId");
        String option = (String) map.get("option");
        Object obj = map.get("text");

        bookService.updateBook(bookId,option,obj);

        response.getWriter().write("ok");


    }

}
