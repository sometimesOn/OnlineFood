package com.jia.bulu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jia.bulu.entity.Employee;
import com.jia.bulu.mapper.EmployeeMapper;
import com.jia.bulu.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
