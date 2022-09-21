package com.jia.book.dao.impl;

import com.jia.book.dao.OrderItemDAO;
import com.jia.book.myssm.util.C3P0Util;
import com.jia.book.pojo.OrderItem;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class OrderItemDAOImpl implements OrderItemDAO {

    private QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());

    @Override
    public void addOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item (book,buyCount,orderBean) values(?,?,?)";
        try {
            queryRunner.update(sql,orderItem.getBook().getId(),orderItem.getBuyCount(),orderItem.getOrderBean().getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
