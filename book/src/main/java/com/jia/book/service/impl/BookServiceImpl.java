package com.jia.book.service.impl;

import com.jia.book.dao.BookDAO;
import com.jia.book.pojo.Book;
import com.jia.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;

    @Override
    public List<Book> getBookList(String keyword, Integer paheNo) {
        return bookDAO.getBookList(keyword,paheNo);
    }

    @Override
    public int getPageCount(String keyword) {
        int bookCount = bookDAO.getBookCount(keyword);
        return (bookCount+10-1)/10;

    }

}
