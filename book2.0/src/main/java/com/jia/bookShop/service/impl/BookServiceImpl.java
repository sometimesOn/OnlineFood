package com.jia.bookShop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jia.bookShop.mapper.BookMapper;
import com.jia.bookShop.pojo.Book;
import com.jia.bookShop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {


    @Autowired
    private BookMapper bookMapper;

    @Override
    public PageInfo<Book> getBookPage(Integer pageNum, String keyword) {
        //������ҳ����
        PageHelper.startPage(pageNum,10);
        //��ѯ�����鼮��Ϣ
        List<Book> list = bookMapper.getAllBook(keyword);
        //��ȡ��ҳ�������
        PageInfo<Book> pageInfo = new PageInfo<>(list,3);
        return pageInfo;
    }

    @Override
    public List<Book> getAllBook(String keyword) {
        return bookMapper.getAllBook(keyword);
    }

    @Override
    public void updateBook(Integer bookId, String option, Object obj) {
        bookMapper.updateBook(bookId,option,obj);
    }
}
