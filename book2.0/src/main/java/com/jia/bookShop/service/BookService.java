package com.jia.bookShop.service;

import com.github.pagehelper.PageInfo;
import com.jia.bookShop.pojo.Book;

import java.util.List;

public interface BookService {
    PageInfo<Book> getBookPage(Integer pageNum, String keyword);

    List<Book> getAllBook(String keyword);

    void updateBook(Integer bookId, String option, Object obj);
}
