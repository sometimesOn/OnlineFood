package com.jia.book.dao;

import com.jia.book.pojo.CartItem;
import com.jia.book.pojo.User;

import java.util.List;

public interface CartItemDAO {
    //新增购物车项
    void add(CartItem cartItem);
    //修改特定的购物车项
    void edit(CartItem cartItem);
    //获取特定用户的购物车项
    List<CartItem> getCartItemList(User user);
    //删除特定的购物车项
    void del(CartItem cartItem);
    Integer getByCount(Integer id);
    void clearCart(User user);
}
