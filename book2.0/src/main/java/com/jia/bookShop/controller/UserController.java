package com.jia.bookShop.controller;

import com.jia.bookShop.pojo.User;
import com.jia.bookShop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/user/regist" ,method = RequestMethod.POST)
    public String regist(User user){
        //ע���û�
        userService.addUser(user);
        return "user/login";
    }


    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    public String login(User user, Model model, HttpSession session){
        //��֤�û��Ƿ����
        User currentUser = userService.getUserByName(user.getuName(),user.getPwd());
        if(currentUser!=null){
            session.setAttribute("currentUser",currentUser);
            return "redirect:/book/page";
        }
        String loginError = "�û������������!";
        model.addAttribute("loginError",loginError);
        return "user/login";
    }

    @RequestMapping(value = "/user/quit",method = RequestMethod.GET)
    public String quit(HttpSession session){
        session.invalidate();
        return "user/login";
    }

}
