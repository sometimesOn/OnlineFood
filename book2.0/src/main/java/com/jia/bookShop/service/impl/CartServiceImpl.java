package com.jia.bookShop.service.impl;

import com.jia.bookShop.mapper.CartMapper;
import com.jia.bookShop.pojo.Cart;
import com.jia.bookShop.pojo.User;
import com.jia.bookShop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public Map<Integer, Cart> getAllCart(User currentUser) {
        List<Cart> cartList = cartMapper.getAllCart(currentUser);
        Map<Integer,Cart> map = new HashMap<>();
        for (Cart cart : cartList){
            map.put(cart.getCartId(),cart);
        }
        return map;
    }

    @Override
    public void deleteCart(Integer cartId) {
        cartMapper.deleteCart(cartId);
    }

    @Override
    public void insertCart(Integer bookId, Integer uId) {
        cartMapper.insertCart(bookId,uId);
    }

    @Override
    public Cart getCartByBookId(Integer bookId, Integer uId) {
        return cartMapper.getCartByBookId(bookId,uId);
    }

    @Override
    public void updateBuycount(Integer buyCount, Integer cartId) {
        cartMapper.updateBuyCount(buyCount, cartId);
    }

    @Override
    public void deleteAllCart(User currentUser) {
        cartMapper.deleteAlCart(currentUser);
    }
}
