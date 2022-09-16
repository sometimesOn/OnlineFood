package com.jia.bulu.utils;

/**
 * 基于ThreadLocal封装的工具类，用于保存和获取用户的id
 */
public class BaseContextUtil {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
