package com.jia.book.pojo;

import java.math.BigDecimal;

public class CartItem {
    private Integer id;
    private Book book;
    private Integer buyCount;
    private User userbean;

    private Double totalValue;

    public Double getTotalValue() {
        BigDecimal priceStr = new BigDecimal(Double.toString(book.getPrice()));
        BigDecimal buyCountStr = new BigDecimal(Integer.toString(buyCount));

        return priceStr.multiply(buyCountStr).doubleValue();
    }

    public CartItem(){}

    public CartItem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getUser() {
        return userbean;
    }

    public void setUser(User user) {
        this.userbean = user;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", book=" + book +
                ", buyCount=" + buyCount +
                ", userbean=" + userbean +
                '}';
    }

    public CartItem(Book book, Integer buyCount, User userbean) {
        this.book = book;
        this.buyCount = buyCount;
        this.userbean = userbean;
    }
}
