package com.jia.bulu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jia.bulu.entity.Employee;
import com.jia.bulu.service.EmployeeService;
import com.jia.bulu.common.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * @param employee
     * @param session
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee,
                             HttpSession session){

        //将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //根据用户名查询数据库
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(lambdaQueryWrapper);

        //如果没有查询到则返回失败结果
        if(emp == null){
            return R.error("登陆失败");
        }

        //密码比对
        if(!emp.getPassword().equals(password)){
            return R.error("密码错误");
        }

        //查看员工状态
        if (emp.getStatus() == 0){
            return R.error("账号已禁用");
        }

        //登录成功，将用户id存入session
        session.setAttribute("employee",emp.getId());


        return R.success(emp);
    }


    @PostMapping("/logout")
    public R<String> logout(HttpSession session){

        //清除session中的id
        session.removeAttribute("employee");

        return R.success("退出成功");
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Employee employee,
                          HttpSession session){
        log.info("新增员工，员工信息： {}",employee.toString());

        //设置初始密码，md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        //employee.setCreateTime(LocalDateTime.now());
        //employee.setUpdateTime(LocalDateTime.now());

        //获取当前登录用户的id
        //Object empId = session.getAttribute("employee");
        //employee.setCreateUser((Long) empId);
        //employee.setUpdateUser((Long) empId);

        employeeService.save(employee);

        return R.success("新增员工成功");
    }

    /**
     * 员工信息的分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){

//        log.info("page: {},");
        //分页构造器
        Page pageInfo = new Page(page,pageSize);

        //条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        //排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);


        //执行查询
        employeeService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    /**
     * 根据id修改员工信息
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody Employee employee){
        log.info(employee.toString());


        //employee.setUpdateTime(LocalDateTime.now());
        //获取登录用户的id
        //Long empId = (Long) session.getAttribute("employee");
        //employee.setUpdateUser(empId);

        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        if (employee != null){
            return R.success(employee);
        }
        return R.error("没有查询到员工信息");
    }

}
