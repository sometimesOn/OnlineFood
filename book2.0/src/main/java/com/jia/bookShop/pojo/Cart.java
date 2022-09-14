package com.jia.bookShop.pojo;

import java.math.BigDecimal;

public class Cart {

    private Integer cartId;
    private Book book;
    private Integer buyCount;
    private User userBean;
    private Double totalValue;

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", book=" + book +
                ", buyCount=" + buyCount +
                ", userBean=" + userBean +
                ", totalValue=" + totalValue +
                '}';
    }


    public Cart(Integer cartId, Book book, Integer buyCount, User userBean) {
        this.cartId = cartId;
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Double getTotalValue() {
        BigDecimal priceStr = new BigDecimal(Double.toString(book.getPrice()));
        BigDecimal buyCountStr = new BigDecimal(Integer.toString(buyCount));

        return priceStr.multiply(buyCountStr).doubleValue();
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
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

    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(User userBean) {
        this.userBean = userBean;
    }

    public Cart() {
    }
}
