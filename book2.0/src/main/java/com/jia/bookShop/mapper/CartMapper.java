package com.jia.bookShop.mapper;

import com.jia.bookShop.pojo.Cart;
import com.jia.bookShop.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    List<Cart> getAllCart(User currentUser);

    void deleteCart(@Param("cartId") Integer cartId);

    void insertCart(@Param("bookId") Integer bookId,@Param("uId") Integer uId);

    Cart getCartByBookId(@Param("bookId") Integer bookId,@Param("uId") Integer uId);

    void updateBuyCount(@Param("buyCount") Integer buyCount,@Param("cartId") Integer cartId);

    void deleteAlCart(User currentUser);
}
