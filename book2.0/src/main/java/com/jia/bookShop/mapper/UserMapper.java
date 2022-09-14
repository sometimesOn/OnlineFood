package com.jia.bookShop.mapper;

import com.jia.bookShop.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    void addUser(User user);

    User getUserByName(@Param("uname") String uname,@Param("pwd") String pwd);

    User getUserById(@Param("uId") Integer uId);
}
