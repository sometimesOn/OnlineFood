package com.jia.bookShop.controller;

import com.jia.bookShop.pojo.Order;
import com.jia.bookShop.pojo.User;
import com.jia.bookShop.service.CartService;
import com.jia.bookShop.service.OrderItemService;
import com.jia.bookShop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.UUID;

@Controller
@Transactional
public class PayController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;


    @RequestMapping(value = "/pay",method = RequestMethod.GET)
    public String pay(HttpSession session){
        User currentUser = (User) session.getAttribute("currentUser");

        java.util.Date date = new  java.util.Date();
        Date orderDate = new Date(date.getTime());
        String nowStr = orderDate.toString();
        String[] strings = nowStr.split("-");
        String nowString = "";
        for (String s : strings){
            nowString = nowString + s;
        }

        String orderNO = UUID.randomUUID().toString() + nowString;

        Order order = new Order(null,orderDate, orderNO, currentUser,0,currentUser.getCartItem().getTotalMoney());

        orderService.insertOrder(order);
        cartService.deleteAllCart(currentUser);
        orderItemService.insertOrderItem(currentUser.getCartItem(),order.getOrderId());

        return "pay/success";
    }



}
