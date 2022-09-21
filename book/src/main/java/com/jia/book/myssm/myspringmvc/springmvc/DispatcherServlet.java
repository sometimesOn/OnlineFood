package com.jia.book.myssm.myspringmvc.springmvc;



import com.jia.book.dao.CartItemDAO;
import com.jia.book.myssm.myspringmvc.ioc.BeanFactory;
import com.jia.book.myssm.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.Instant;
import java.util.Date;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet{

    private BeanFactory beanFactory;

    public DispatcherServlet(){

    }

    public void init() throws ServletException {
        super.init();
//        beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("BeanFactory");
        if(beanFactoryObj!=null){
            beanFactory = (BeanFactory) beanFactoryObj;
        }else {
            throw new RuntimeException("IOC容器获取失败");
        }
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int  lastIndexOf= servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0,lastIndexOf);

        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = request.getParameter("operate");
        if(StringUtil.isEmpty(operate)){
            operate = "index";
        }

        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods){
                if(method.getName().equals(operate)){
                    Parameter[] parameters = method.getParameters();

                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
//                        System.out.println("parameterName = " + parameterName);
                        if("request".equals(parameterName)){
                            parameterValues[i] = request;
                        } else if ("response".equals(parameterName)) {
                            parameterValues[i] = response;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = request.getSession();
                        }else {
                            String parameterValue = request.getParameter(parameterName);
                            String typeName = parameter.getType().getName();

                            Object parameterObj = parameterValue;
                            if(parameterObj != null){
                                if("java.lang.Integer".equals(typeName)){
                                    parameterObj = Integer.parseInt(parameterValue);
                                } else if ("java.lang.Double".equals(typeName)) {
                                    parameterObj = Double.parseDouble(parameterValue);
                                } else if ("java.util.Date".equals(typeName)) {
                                    parameterObj = Date.from(Instant.parse(parameterValue));
                                } else if ("java.sql.Date".equals(typeName)) {
                                    parameterObj = Date.from(Instant.parse(parameterValue));
                                }
                            }

                            parameterValues[i] = parameterObj;
                        }
                    }

                    //
                    method.setAccessible(true);
                    Object returnObj = method.invoke(controllerBeanObj,parameterValues);

                    //视图处理
                    String methodreturnStr = (String) returnObj;
                    if(StringUtil.isEmpty(methodreturnStr)){
                        return;
                    }
                    if(methodreturnStr.startsWith("redirect:")){
                        String returnStr = methodreturnStr.substring("redirect:".length());
                        response.sendRedirect(returnStr);
                    } else if (methodreturnStr.startsWith("json:")) {
                        String returnStr = methodreturnStr.substring("json:".length());
                        PrintWriter out = response.getWriter();
                        out.println(returnStr);
                        out.flush();
                    } else {
                        super.processTemplate(methodreturnStr,request,response);
                    }
                }
            }

        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

//        Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
//        for (Method m : methods){
//            if(operate.equals(m.getName())){
//                try {
//                    m.invoke(this,request,response);
//                    return;
//                } catch (IllegalAccessException e) {
//                    throw new RuntimeException(e);
//                } catch (InvocationTargetException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//        throw new RuntimeException("operate非法");

    }
}
