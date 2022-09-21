package com.jia.book.dao;

import com.jia.book.pojo.User;

public interface UserDAO {
    User getUser(String uname,String pwd);
    User getUserById(Integer id);
    void addUser(User user);
    User getUserByUname(String uname);
}
