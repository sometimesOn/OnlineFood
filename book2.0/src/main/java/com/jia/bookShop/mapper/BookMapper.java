package com.jia.bookShop.mapper;

import com.jia.bookShop.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    List<Book> getAllBook(@Param("keyword") String keyword);
    Book getBookById(@Param("bookId") Integer bookId);

    void updateBook(@Param("bookId") Integer bookId,@Param("option") String option,@Param("obj") Object obj);
}
