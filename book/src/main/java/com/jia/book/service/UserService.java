package com.jia.book.service;

import com.jia.book.pojo.User;

public interface UserService {
    User login(String uname,String pwd);
    void regist(User user);
    User getUserByUname(String uname);
}
