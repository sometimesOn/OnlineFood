package com.jia.book.controller;

import com.jia.book.pojo.Book;
import com.jia.book.pojo.Cart;
import com.jia.book.pojo.CartItem;
import com.jia.book.pojo.User;
import com.jia.book.service.CartItemService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

public class cartController {

    private CartItemService cartItemService;

    private String index(HttpSession session){
        //获取当前用户，并给当前用户赋值，更新资源
        User user = (User) session.getAttribute("currentUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currentUser",user);
        return "cart/cart";
    }

    public String add(Integer bookId, HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");
        CartItem cartItem = new CartItem(new Book(bookId),1,currentUser);
        /*将指定的图书添加到当前用户的购物车
        1，如果当前用户的购物车已经存在这个图书，那么加一
        2，如果购物车没有该图书，那么新增图书，数量为一*/
        cartItemService.addOrEdit(cartItem,currentUser.getCart());
        return "redirect:cart.do";
    }

    public String del(Integer cartItemId){
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemId);
        if(cartItemId!=null){
            cartItemService.del(cartItem);
        }
        return "redirect:cart.do";
    }

    public String clear(HttpSession session){
        User user = (User) session.getAttribute("currentUser");
        if(user!=null){
            cartItemService.clearCart(user);
        }
        return "redirect:cart.do";
    }

    public String count(Integer flag,Integer cartItemId){
        if(flag!=null){
            CartItem cartItem = new CartItem();
            cartItem.setId(cartItemId);
            cartItem.setBuyCount(flag+cartItemService.getBuyCount(cartItemId));
            cartItemService.edit(cartItem);
        }
        return "redirect:cart.do";
    }
}
