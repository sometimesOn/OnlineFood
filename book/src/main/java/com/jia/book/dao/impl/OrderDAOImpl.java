package com.jia.book.dao.impl;

import com.jia.book.dao.OrderDAO;
import com.jia.book.dao.UserDAO;
import com.jia.book.myssm.util.C3P0Util;
import com.jia.book.pojo.OrderBean;
import com.jia.book.pojo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public void addOrderBean(OrderBean orderBean) {
        String sql = "insert into t_order (orderNo,orderDate,orderUser,orderMoney) values(?,?,?,?)";
        try {
            queryRunner.update(sql,orderBean.getOrderNo(),orderBean.getOrderDate(),orderBean.getOrderUser().getId(),orderBean.getOrderMoney());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer getOrderBeanId(OrderBean orderBean) {
        String sql = "select id from t_order where orderNo = ?";
        try {
            return queryRunner.query(sql,new ScalarHandler<Integer>(),orderBean.getOrderNo());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        String sql = "select * from t_order where orderUser = ?";
        ResultSetHandler<List<OrderBean>> hander = new ResultSetHandler<List<OrderBean>>() {
            @Override
            public List<OrderBean> handle(ResultSet resultSet) throws SQLException {
                List<OrderBean> orderBeanList = new ArrayList<>();
                while (resultSet.next()){
                    OrderBean orderBean = new OrderBean();
                    int id = resultSet.getInt("id");
                    orderBean.setId(id);
                    String orderNo = resultSet.getString("orderNo");
                    orderBean.setOrderNo(orderNo);
                    Date orderDate = resultSet.getDate("orderDate");
                    orderBean.setOrderDate(orderDate);
                    int orderUserId = resultSet.getInt("orderUser");
                    User orderUser = userDAO.getUserById(orderUserId);
                    orderBean.setOrderUser(orderUser);
                    Double orderMoney = resultSet.getDouble("orderMoney");
                    orderBean.setOrderMoney(orderMoney);
                    int orderStatus = resultSet.getInt("orderStatus");
                    orderBean.setOrderStatus(orderStatus);
                    orderBeanList.add(orderBean);
                }
                return orderBeanList;
            }
        };
        try {
            return queryRunner.query(sql,hander,user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer getTotalBookCount(OrderBean orderBean) {
        String sql = "SELECT SUM(t3.buyCount) as totalBookCount,t3.orderBean from (SELECT t1.id,t2.buyCount,t2.orderBean FROM t_order t1 INNER JOIN t_order_item t2 ON t1.id = t2.orderBean) t3 WHERE t3.orderBean=? GROUP BY t3.orderBean";
        try {
            return queryRunner.query(sql,new ScalarHandler<BigDecimal>(),orderBean.getId()).intValue();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
