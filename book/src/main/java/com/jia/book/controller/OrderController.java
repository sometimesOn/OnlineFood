package com.jia.book.controller;

import com.jia.book.dao.OrderDAO;
import com.jia.book.pojo.OrderBean;
import com.jia.book.pojo.User;
import com.jia.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class OrderController {

    private OrderService orderService;

    public String checkout(HttpSession session){

        User user = (User) session.getAttribute("currentUser");

        java.util.Date date = new  java.util.Date();
        Date now = new Date(date.getTime());
        String nowStr = now.toString();
        String[] strings = nowStr.split("-");
        String nowString = "";
        for (String s : strings){
            nowString = nowString + s;
        }


        OrderBean orderBean = new OrderBean();
        orderBean.setOrderNo(UUID.randomUUID().toString()+nowString);
        orderBean.setOrderDate(now);
        orderBean.setOrderMoney(user.getCart().getTotalMoney());
        orderBean.setOrderUser(user);


        orderService.addOrderBean(orderBean);

        return "redirect:cart.do";
    }

    public String showOrder(HttpSession session){

        User user = (User) session.getAttribute("currentUser");
        if(user!=null){
            List<OrderBean> orderBeanList = orderService.getOrderList(user);
            user.setOrderBeanList(orderBeanList);
            session.setAttribute("currentUser",user);
        }
        return "order/order";
    }
}
