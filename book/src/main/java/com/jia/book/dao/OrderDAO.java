package com.jia.book.dao;

import com.jia.book.pojo.OrderBean;
import com.jia.book.pojo.User;

import java.util.List;

public interface OrderDAO {
    void addOrderBean(OrderBean orderBean);
    Integer getOrderBeanId(OrderBean orderBean);
    //获取特定用户的订单
    List<OrderBean> getOrderList(User user);
    Integer getTotalBookCount(OrderBean orderBean);
}
