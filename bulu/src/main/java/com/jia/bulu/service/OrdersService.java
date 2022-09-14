package com.jia.bulu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jia.bulu.entity.Orders;

public interface OrdersService extends IService<Orders> {
    void submit(Orders orders);
}
