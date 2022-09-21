package com.jia.book.service;

import com.jia.book.pojo.Cart;
import com.jia.book.pojo.CartItem;
import com.jia.book.pojo.User;

public interface CartItemService {
    void add(CartItem cartItem);
    void edit(CartItem cartItem);
    void addOrEdit(CartItem cartItem, Cart cart);
    //加载特定用户的购物车信息
    Cart getCart(User user);
    void del(CartItem cartItem);
    Integer getBuyCount(Integer id);

    void clearCart(User user);
}
