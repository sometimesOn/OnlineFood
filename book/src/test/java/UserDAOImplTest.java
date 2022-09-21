import com.jia.book.pojo.User;
import org.junit.jupiter.api.Test;

public class UserDAOImplTest {
    private com.jia.book.dao.UserDAO userDAO = new com.jia.book.dao.impl.UserDAOImpl();

    @Test
    public void getUserTest(){
        User user = userDAO.getUser("chen","123");
        System.out.println("user = " + user);
    }
    @Test
    public void getUserByIdTest(){
        System.out.println(userDAO.getUserById(1));
    }

}
