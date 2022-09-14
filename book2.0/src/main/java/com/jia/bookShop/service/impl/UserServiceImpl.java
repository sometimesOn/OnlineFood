package com.jia.bookShop.service.impl;

import com.jia.bookShop.mapper.UserMapper;
import com.jia.bookShop.pojo.User;
import com.jia.bookShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public User getUserByName(String uname, String pwd) {
        return userMapper.getUserByName(uname,pwd);
    }
}
