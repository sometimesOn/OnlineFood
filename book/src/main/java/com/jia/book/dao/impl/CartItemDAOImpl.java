package com.jia.book.dao.impl;

import com.jia.book.dao.BookDAO;
import com.jia.book.dao.CartItemDAO;
import com.jia.book.dao.UserDAO;
import com.jia.book.myssm.util.C3P0Util;
import com.jia.book.pojo.Book;
import com.jia.book.pojo.CartItem;
import com.jia.book.pojo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAOImpl implements CartItemDAO {

    private QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());
    private UserDAO userDAO = new UserDAOImpl();
    private BookDAO bookDAO = new BookDAOImpl();

    @Override
    public void add(CartItem cartItem) {
        String sql = "insert into t_cart_item (book,buyCount,userBean) values(?,?,?)";
        try {
            queryRunner.update(sql,cartItem.getBook().getId(),cartItem.getBuyCount(),cartItem.getUser().getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void edit(CartItem cartItem) {
        String sql = "update t_cart_item set buyCount = ? where id = ?";
        try {
            queryRunner.update(sql,cartItem.getBuyCount(),cartItem.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        String sql = "select * from t_cart_item where userBean=?";
        try {
            ResultSetHandler<List<CartItem>> hander= new ResultSetHandler<List<CartItem>>() {
                @Override
                public List<CartItem> handle(ResultSet resultSet) throws SQLException {
                    List<CartItem> cartItems = new ArrayList<>();
                    while (resultSet.next()){
                        CartItem cartItem = new CartItem();
                        int id = resultSet.getInt("id");
                        cartItem.setId(id);
                        int bid = resultSet.getInt("book");
                        Book book = bookDAO.getBookById(bid);
                        cartItem.setBook(book);
                        int buyCount = resultSet.getInt("buyCount");
                        cartItem.setBuyCount(buyCount);
                        int uid = resultSet.getInt("userBean");
                        User user1 = userDAO.getUserById(uid);
                        cartItem.setUser(user1);
                        cartItems.add(cartItem);
                    }
                    return cartItems;
                }
            };
            return queryRunner.query(sql,hander,user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void del(CartItem cartItem) {
        String sql = "delete from t_cart_item where id = ?";
        try {
            queryRunner.update(sql,cartItem.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Integer getByCount(Integer id) {
        String sql = "select buyCount from t_cart_item where id = ?";
        try {
            return queryRunner.query(sql,new ScalarHandler<Integer>(),id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearCart(User user) {
        String sql = "delete from t_cart_item where userBean = ?";
        try {
            queryRunner.update(sql,user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
