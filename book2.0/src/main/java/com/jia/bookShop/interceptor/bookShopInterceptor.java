package com.jia.bookShop.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class bookShopInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        //�ж��Ƿ��������¼����ע���·��
        if(uri.contains("/to/login")||uri.contains("/to/regist")){
            return true;
        }else {
            //�ж��û��Ƿ��¼
            if(request.getSession().getAttribute("currentUser") != null){
                return true;
            }else {
                response.sendRedirect(request.getContextPath()+"/to/login");
            }
        }

        //Ĭ������
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
