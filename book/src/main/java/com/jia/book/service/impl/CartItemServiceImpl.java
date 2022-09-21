package com.jia.book.service.impl;

import com.jia.book.dao.CartItemDAO;
import com.jia.book.pojo.Cart;
import com.jia.book.pojo.CartItem;
import com.jia.book.pojo.User;
import com.jia.book.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl implements CartItemService {

    private CartItemDAO cartItemDAO;

    @Override
    public void add(CartItem cartItem) {
        cartItemDAO.add(cartItem);
    }

    @Override
    public void edit(CartItem cartItem) {
        cartItemDAO.edit(cartItem);
    }

    @Override
    public void addOrEdit(CartItem cartItem, Cart cart) {
        if(cart!=null){
            Map<Integer,CartItem> cartItemMap = cart.getCartItemMap();
            if(cartItemMap==null){
                cartItemMap = new HashMap<>();
            }

            if (cartItemMap.containsKey(cartItem.getBook().getId())){
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount()+1);
                edit(cartItemTemp);
            }else {

                add(cartItem);
            }
        }else {
            add(cartItem);
        }
    }

    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemList = cartItemDAO.getCartItemList(user);
        Map<Integer,CartItem> cartItemMap = new HashMap<>();
        for (CartItem cartItem : cartItemList){
            cartItemMap.put(cartItem.getBook().getId(),cartItem);
        }
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        return cart;
    }

    @Override
    public void del(CartItem cartItem) {
        cartItemDAO.del(cartItem);
    }

    @Override
    public Integer getBuyCount(Integer id) {
        return cartItemDAO.getByCount(id);
    }

    @Override
    public void clearCart(User user) {
        cartItemDAO.clearCart(user);
    }
}
