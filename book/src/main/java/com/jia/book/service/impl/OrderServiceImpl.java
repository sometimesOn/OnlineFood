package com.jia.book.service.impl;

import com.jia.book.dao.CartItemDAO;
import com.jia.book.dao.OrderDAO;
import com.jia.book.dao.OrderItemDAO;
import com.jia.book.pojo.CartItem;
import com.jia.book.pojo.OrderBean;
import com.jia.book.pojo.OrderItem;
import com.jia.book.pojo.User;
import com.jia.book.service.OrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;

    private CartItemDAO cartItemDAO;

    @Override
    public void addOrderBean(OrderBean orderBean) {
        orderDAO.addOrderBean(orderBean);
        orderBean.setId(orderDAO.getOrderBeanId(orderBean));
        //把订单添加到用户的订单表中
        Map<Integer, CartItem> cartItemMap = orderBean.getOrderUser().getCart().getCartItemMap();
        for (CartItem cartItem : cartItemMap.values()){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderBean(orderBean);
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItemDAO.addOrderItem(orderItem);
        }
        //删除购物车当前用户里的数据
        cartItemDAO.clearCart(orderBean.getOrderUser());
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        List<OrderBean> orderBeanList = orderDAO.getOrderList(user);

        for (OrderBean orderBean : orderBeanList){
            Integer totalBookCount = orderDAO.getTotalBookCount(orderBean);
            orderBean.setTotalBookCount(totalBookCount);
        }

        return orderBeanList;
    }
}
