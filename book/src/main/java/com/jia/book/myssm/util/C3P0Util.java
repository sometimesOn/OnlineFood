package com.jia.book.myssm.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {
    private static ComboPooledDataSource datasource;

    private void C3P0Util() {

    }
    static {
        datasource = new ComboPooledDataSource("mysql");
    }
    public static ComboPooledDataSource getDataSource() {
        return datasource;
    }
}
