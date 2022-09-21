package com.jia.book.controller;

import com.jia.book.pojo.Cart;
import com.jia.book.pojo.User;
import com.jia.book.service.CartItemService;
import com.jia.book.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class userController {

    private UserService userService;
    private CartItemService cartItemService;

    public String login(String uname, String pwd, HttpSession session){
        User currentUser = userService.login(uname,pwd);
        if(currentUser!=null){
            Cart cart = cartItemService.getCart(currentUser);
            currentUser.setCart(cart);

            session.setAttribute("currentUser",currentUser);
            return "redirect:book.do";

        }else {
            return "user/login";
        }
    }

    public String regist(String uname,String pwd,String cpwd,String email,HttpSession session,String key){
        Object kaptchaObj = session.getAttribute("KAPTCHA_SESSION_KEY");
        if(kaptchaObj==null || !kaptchaObj.equals(key)){
            return "user/regist";
        }
        else {
            if(kaptchaObj.equals(key)) {
                User user = new User();
                user.setUname(uname);
                user.setPwd(pwd);
                user.setEmail(email);
                userService.regist(user);
                return "user/login";
            }
            else {
                return "user/regist";
            }
        }
    }

    public String ckUname(String uname){
        User user = userService.getUserByUname(uname);
        if (user!=null){
            return "json:{'uname':'1'}";
        }else {
            return "json:{'uname':'0'}";
        }
    }

}
