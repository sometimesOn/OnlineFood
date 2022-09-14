package com.jia.bookShop.service;

import com.jia.bookShop.pojo.User;

public interface UserService {
    void addUser(User user);

    User getUserByName(String uname, String pwd);
}
