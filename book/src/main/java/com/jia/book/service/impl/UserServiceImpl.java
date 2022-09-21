package com.jia.book.service.impl;

import com.jia.book.dao.UserDAO;
import com.jia.book.pojo.User;
import com.jia.book.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = null;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname,pwd);
    }

    @Override
    public void regist(User user) {
        userDAO.addUser(user);
    }

    @Override
    public User getUserByUname(String uname) {
        return userDAO.getUserByUname(uname);
    }
}
