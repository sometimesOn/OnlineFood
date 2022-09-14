package com.jia.bookShop.service;

import com.jia.bookShop.pojo.CartItem;

public interface OrderItemService {
    void insertOrderItem(CartItem cartItem, Integer orderId);
}
