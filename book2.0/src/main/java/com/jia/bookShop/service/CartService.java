package com.jia.bookShop.service;

import com.jia.bookShop.pojo.Cart;
import com.jia.bookShop.pojo.User;

import java.util.List;
import java.util.Map;

public interface CartService {
    Map<Integer,Cart> getAllCart(User currentUser);

    void deleteCart(Integer cartId);

    void insertCart(Integer bookId, Integer uId);

    Cart getCartByBookId(Integer bookId, Integer uId);

    void updateBuycount(Integer buyCount, Integer cartId);

    void deleteAllCart(User currentUser);
}
