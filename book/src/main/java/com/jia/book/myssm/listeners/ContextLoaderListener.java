package com.jia.book.myssm.listeners;

import com.jia.book.myssm.myspringmvc.ioc.BeanFactory;
import com.jia.book.myssm.myspringmvc.ioc.impl.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        String path = application.getInitParameter("contextConfig");
        BeanFactory beanFactory = new ClassPathXmlApplicationContext(path);
        application.setAttribute("BeanFactory",beanFactory);
    }
}
