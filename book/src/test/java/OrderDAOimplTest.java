import com.jia.book.dao.OrderDAO;
import com.jia.book.dao.impl.OrderDAOImpl;
import com.jia.book.pojo.OrderBean;
import com.jia.book.pojo.User;
import org.junit.jupiter.api.Test;

public class OrderDAOimplTest {

    private OrderDAO orderDAO = new OrderDAOImpl();

    @Test
    public void getOrderListTest(){
        User user = new User();
        user.setId(1);
        System.out.println(orderDAO.getOrderList(user));
    }

    @Test
    public void getTotalBookCountTest(){
        OrderBean orderBean = new OrderBean();
        orderBean.setId(19);
        System.out.println(orderDAO.getTotalBookCount(orderBean));
    }
}
