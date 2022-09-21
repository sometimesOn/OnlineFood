import com.jia.book.dao.BookDAO;
import com.jia.book.dao.impl.BookDAOImpl;
import com.jia.book.pojo.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BookDAOImplTest {

    private BookDAO bookDAO = new BookDAOImpl();

    @Test
    public void getBookTest(){
        List<Book> bookList = bookDAO.getBookList("大",1);
        System.out.println("bookList = " + bookList);
    }
    @Test
    public void getBookCountTest(){
        System.out.println(bookDAO.getBookCount("又"));
    }

    @Test
    public void getBookByIdTest(){
        System.out.println(bookDAO.getBookById(987654321));
    }

}
