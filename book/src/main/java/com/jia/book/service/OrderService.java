package com.jia.book.service;

import com.jia.book.pojo.OrderBean;
import com.jia.book.pojo.User;

import java.util.List;

public interface OrderService {
    void addOrderBean(OrderBean orderBean);
    List<OrderBean> getOrderList(User user);
}
