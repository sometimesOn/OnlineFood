package com.jia.bulu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jia.bulu.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
