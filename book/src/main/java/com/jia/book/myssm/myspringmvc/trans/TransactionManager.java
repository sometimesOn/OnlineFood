package com.jia.book.myssm.myspringmvc.trans;


import com.jia.book.myssm.util.C3P0Util;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    public static void beginTrans() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection==null){
            connection = C3P0Util.getDataSource().getConnection();
            threadLocal.set(connection);
        }
        connection.setAutoCommit(false);
    }

    //
    public static void commit() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection==null){
            connection = C3P0Util.getDataSource().getConnection();
            threadLocal.set(connection);
        }
        connection.commit();
        connection.close();
    }

    //
    public static void rollback() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection==null){
            connection = C3P0Util.getDataSource().getConnection();
            threadLocal.set(connection);
        }
        connection.rollback();
        connection.close();
    }
}
