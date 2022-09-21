import com.jia.book.dao.BookDAO;
import com.jia.book.dao.CartItemDAO;
import com.jia.book.dao.UserDAO;
import com.jia.book.dao.impl.BookDAOImpl;
import com.jia.book.dao.impl.CartItemDAOImpl;
import com.jia.book.dao.impl.UserDAOImpl;
import com.jia.book.myssm.util.C3P0Util;
import com.jia.book.pojo.Book;
import com.jia.book.pojo.CartItem;
import com.jia.book.pojo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartItemDAOImplTest {

    private QueryRunner queryRunner = new QueryRunner(C3P0Util.getDataSource());

    private CartItemDAO cartItemDAO = new CartItemDAOImpl();
    private BookDAO bookDAO = new BookDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();

//    @Test
//    public void getCartItemTest(){
//        User user = new User();
//        user.setId(1);
//        user.setUname("chen");
//        user.setPwd("123");
//        System.out.println(cartItemDAO.getCartItem(user));
//    }
    @Test
    public void teat11(){
        String sql = "select * from t_cart_item where userBean=?";
        try {
//            return queryRunner.query(sql,new BeanListHandler<CartItem>(CartItem.class),user.getId());
            User user = new User();
            user.setId(1);

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
            List<CartItem> cartItemList = queryRunner.query(sql,hander,user.getId());
            for (CartItem cartItem : cartItemList){
                System.out.println(cartItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getCartItemListTest(){
        User user = new User();
        user.setId(1);
        System.out.println(cartItemDAO.getCartItemList(user));
    }
    @Test
    public void addTest(){
        CartItem cartItem = new CartItem();
        cartItem.setBuyCount(1);
        Book book = new Book();
        book.setId(987654321);
        cartItem.setBook(book);
        User user = new User();
        user.setId(1);
        cartItem.setUser(user);
        cartItemDAO.add(cartItem);
    }


    @Test
    public void getByCountTest(){
        System.out.println("cartItemDAO.getByCount(29) = " + cartItemDAO.getByCount(29));
    }
}
