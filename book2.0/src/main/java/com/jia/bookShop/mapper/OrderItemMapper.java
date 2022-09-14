package com.jia.bookShop.mapper;

import com.jia.bookShop.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface OrderItemMapper {
    void insertOrderItem(@Param("carts") Collection<Cart> carts, @Param("orderId") Integer orderId);
}
