package com.jia.bookShop.service.impl;

import com.jia.bookShop.mapper.OrderItemMapper;
import com.jia.bookShop.pojo.Cart;
import com.jia.bookShop.pojo.CartItem;
import com.jia.bookShop.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public void insertOrderItem(CartItem cartItem, Integer orderId) {
        Collection<Cart> carts = cartItem.getCartMap().values();
        orderItemMapper.insertOrderItem(carts,orderId);
    }
}
