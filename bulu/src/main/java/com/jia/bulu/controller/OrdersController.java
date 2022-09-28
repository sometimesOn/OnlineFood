package com.jia.bulu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jia.bulu.common.R;
import com.jia.bulu.entity.Orders;
import com.jia.bulu.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RequestMapping("/order")
@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){

        ordersService.submit(orders);

        return R.success("用户下单成功");

    }


    @GetMapping("/page")
    @Cacheable("orderCache")
    public R<Page<Orders>> page(int page, int pageSize, Long number,LocalDateTime beginDate,LocalDateTime endTime){

        System.out.println(page);
        System.out.println(pageSize);
        System.out.println(number);
        System.out.println(beginDate);
        System.out.println(endTime);

        return null;
    }


}
