package com.jia.book.dao;

import com.jia.book.pojo.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBookList(String keyword,Integer pageNo);
    int getBookCount(String keyword);

    Book getBookById(Integer id);
}
