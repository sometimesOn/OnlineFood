package com.jia.bookShop.pojo;

import java.sql.Date;

public class Order {
    private Integer orderId;
    private Date orderDate;
    private String orderNo;
    private User orderUser;

    public Order(Integer orderId, Date orderDate, String orderNo, User orderUser, Integer orderStatus, Double orderMoney) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderNo = orderNo;
        this.orderUser = orderUser;
        this.orderStatus = orderStatus;
        this.orderMoney = orderMoney;
    }

    private Integer orderStatus;
    private Double orderMoney;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", orderNo='" + orderNo + '\'' +
                ", orderUser=" + orderUser +
                ", orderStatus=" + orderStatus +
                ", orderMoney=" + orderMoney +
                '}';
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setOrderUser(User orderUser) {
        this.orderUser = orderUser;
    }

    public Order() {
    }
}
