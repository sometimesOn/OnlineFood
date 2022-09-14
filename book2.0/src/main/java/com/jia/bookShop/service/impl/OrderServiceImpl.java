package com.jia.bookShop.service.impl;

import com.jia.bookShop.mapper.OrderMapper;
import com.jia.bookShop.pojo.Order;
import com.jia.bookShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void insertOrder(Order order) {
        orderMapper.insertOrder(order);
    }
}
