package com.jia.book.dao.impl;

import com.jia.book.dao.UserDAO;
import com.jia.book.myssm.util.C3P0Util;
import com.jia.book.pojo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    private QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());

    @Override
    public User getUser(String uname, String pwd) {
        String sql = "select * from t_user where uname=? and pwd=?";
        try {
            return queryRunner.query(sql,new BeanHandler<User>(User.class),uname,pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(Integer id) {
        String sql ="select * from t_user where id = ?";
        try {
            return queryRunner.query(sql,new BeanHandler<User>(User.class),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(User user) {
        String sql = "insert into t_user (uname,pwd,email) values(?,?,?)";
        try {
            queryRunner.update(sql,user.getUname(),user.getPwd(),user.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByUname(String uname) {
        String sql = "select * from t_user where uname = ?";
        try {
            return queryRunner.query(sql,new BeanHandler<User>(User.class),uname);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
