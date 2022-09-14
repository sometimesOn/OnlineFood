package com.jia.bookShop.controller;

import com.jia.bookShop.pojo.Cart;
import com.jia.bookShop.pojo.CartItem;
import com.jia.bookShop.pojo.Order;
import com.jia.bookShop.pojo.User;
import com.jia.bookShop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class CartController {


    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart",method = RequestMethod.GET)
    @ResponseBody
    public CartItem getCart (HttpSession session){
        Object currentUserObj = session.getAttribute("currentUser");
        User currentUser = (User) currentUserObj;
        Map<Integer, Cart> cartMap = cartService.getAllCart(currentUser);
        CartItem cartItem = new CartItem();
        cartItem.setCartMap(cartMap);
        currentUser.setCartItem(cartItem);
        session.setAttribute("currentUser",currentUser);
        return cartItem;
    }

    @RequestMapping(value = "/cart/{bookId}",method = RequestMethod.POST)
    public String insertCart(@PathVariable Integer bookId,HttpSession session){
        //判断该购物项是否存在
        User currentUser = (User) session.getAttribute("currentUser");
        Integer uId = currentUser.getuId();
        Cart cart = cartService.getCartByBookId(bookId,uId);
        if(cart != null){
            cartService.updateBuycount(cart.getBuyCount()+1,cart.getCartId());
        }else {
            cartService.insertCart(bookId,uId);
        }
        return "cart/cartItem";
    }

    @RequestMapping(value = "/cart",method = RequestMethod.PUT)
    public void updateBuyCount(@RequestBody Cart cart, HttpServletResponse response) throws IOException {
        //更新购物车内对应书籍的数量
        cartService.updateBuycount(cart.getBuyCount(),cart.getCartId());
        response.getWriter().write("ok");
    }

    @RequestMapping(value = "/cart",method =RequestMethod.DELETE)
    public void deleteCart(@RequestBody Cart cart,HttpServletResponse response) throws IOException {
        cartService.deleteCart(cart.getCartId());
        response.getWriter().write("ok");
    }

}
