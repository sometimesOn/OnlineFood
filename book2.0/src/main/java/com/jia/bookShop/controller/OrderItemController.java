package com.jia.bookShop.controller;

import com.jia.bookShop.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;
}
