package com.jia.bulu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jia.bulu.entity.User;
import com.jia.bulu.mapper.UserMapper;
import com.jia.bulu.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
