package com.jia.book.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@WebFilter(urlPatterns = {"*.do","*.html"},initParams = {
        @WebInitParam(name= "chen",value = "/book_war_exploded/page.do?operate=page&page=user/login,/book_war_exploded/user.do?null,/book_war_exploded/page.do?operate=page&page=user/regist")
})
public class bookFilter implements Filter {

    List<String> chenList = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String chen = filterConfig.getInitParameter("chen");
        String[] chenArr = chen.split(",");
        chenList = Arrays.asList(chenArr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
//        System.out.println("uri = " + uri);
//        System.out.println("queryString = " + queryString);
        String str = uri + "?" +queryString;

        HttpSession session = request.getSession();
        Object currentUserObj = session.getAttribute("currentUser");

        if(chenList.contains(str)){
            filterChain.doFilter(request,response);
            return;
        }else {
            if(currentUserObj==null){
                response.sendRedirect("page.do?operate=page&page=user/login");
            }else {
                filterChain.doFilter(request,response);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
