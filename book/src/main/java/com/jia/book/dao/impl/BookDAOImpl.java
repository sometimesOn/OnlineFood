package com.jia.book.dao.impl;

import com.jia.book.dao.BookDAO;
import com.jia.book.myssm.util.C3P0Util;
import com.jia.book.pojo.Book;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());

    @Override
    public List<Book> getBookList(String keyword, Integer pageNo) {
        String sql = "select * from t_book where bookName or author like ? and bookStatus=1 limit ?,10";
        try {
            return queryRunner.query(sql,new BeanListHandler<Book>(Book.class),'%'+keyword+'%',(pageNo-1)*10);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getBookCount(String keyword) {
        String sql = "select count(*) from t_book where bookName or author like ? and bookStatus=1";
        try {
            return queryRunner.query(sql,new ScalarHandler<Long>(),'%'+keyword+'%').intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book getBookById(Integer id) {
        String sql = "select * from t_book where id = ?";
        try {
            return queryRunner.query(sql,new BeanHandler<Book>(Book.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
