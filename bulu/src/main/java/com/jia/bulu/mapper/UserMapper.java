package com.jia.bulu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jia.bulu.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
