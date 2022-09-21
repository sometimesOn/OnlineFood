package com.jia.book.service;

import com.jia.book.pojo.Book;

import java.util.List;

public interface BookService {

    //根据关键字查询图书并分页
    List<Book> getBookList(String keyword,Integer paheNo);
    int getPageCount(String keyword);
}
