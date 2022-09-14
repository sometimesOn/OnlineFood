package com.jia.bookShop.pojo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class CartItem {
    private Map<Integer,Cart> cartMap;
    private Integer totalCount;
    private Double totalMoney;

    private Integer totalBookCount;

    public Integer getTotalBookCount() {

        Integer totalBookCount = 0;

        if(cartMap.size() > 0 && cartMap != null){
            for (Cart cart : cartMap.values()){
                totalBookCount = totalBookCount + cart.getBuyCount();
            }
        }

        return totalBookCount;
    }

    public CartItem() {

    }

    @Override
    public String toString() {
        return "cartItem{" +
                "cartMap=" + cartMap +
                ", totalCount=" + totalCount +
                ", totalMoney=" + totalMoney +
                '}';
    }

    public Map<Integer, Cart> getCartMap() {
        return cartMap;
    }

    public void setCartMap(Map<Integer, Cart> cartMap) {
        this.cartMap = cartMap;
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        if(cartMap!=null && cartMap.size()>0){
            totalCount = cartMap.size();
        }
        return totalCount;
    }


    public Double getTotalMoney() {
        BigDecimal totalMoneyStr = new BigDecimal(Double.toString(0.0));
        if(cartMap!=null&&cartMap.size()>0){
            Set<Map.Entry<Integer,Cart>> entries = cartMap.entrySet();
            for (Map.Entry<Integer,Cart> cartEntry : entries){
                Cart cart = cartEntry.getValue();
                BigDecimal tempStr = new BigDecimal(cart.getTotalValue().toString());
                totalMoneyStr = totalMoneyStr.add(tempStr);
            }
        }
        return totalMoneyStr.doubleValue();
    }


}
