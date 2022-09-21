package com.jia.book.pojo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class Cart {
    private Map<Integer,CartItem> cartItemMap; //购物车项的集合
    private Double totalMoney;
    private Integer totalCount;
    private Integer totalBookCount;


    public Cart(){}

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public Double getTotalMoney() {
        BigDecimal totalMoneyStr = new BigDecimal(Double.toString(0.0));
        if(cartItemMap!=null&&cartItemMap.size()>0){
            Set<Map.Entry<Integer,CartItem>> entries = cartItemMap.entrySet();
            for (Map.Entry<Integer,CartItem> cartItemEntry : entries){
                CartItem cartItem = cartItemEntry.getValue();
                BigDecimal tempStr = new BigDecimal(Double.toString(cartItem.getTotalValue()));
                totalMoneyStr = totalMoneyStr.add(tempStr);
            }
        }
        return totalMoneyStr.doubleValue();
    }

    public Integer getTotalCount() {
        totalCount = 0;
        if(cartItemMap!=null && cartItemMap.size()>0){
            totalCount = cartItemMap.size();
        }
        return totalCount;
    }

    public Integer getTotalBookCount() {
        totalBookCount = 0;
        if (cartItemMap!=null && cartItemMap.size()>0){
            for (CartItem cartItem : cartItemMap.values()){
                totalBookCount = totalBookCount + cartItem.getBuyCount();
            }
        }
        return totalBookCount;
    }
}
