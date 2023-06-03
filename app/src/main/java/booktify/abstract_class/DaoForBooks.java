package booktify.abstract_class;

import java.sql.SQLException;
import java.util.List;

import booktify.models.Books;

public interface DaoForBooks {
    public void setupTable();
    public void insert(List<Books> list);
    public List<Books> get() throws SQLException;
    public void buyBook(int BookId, int stock, int stockBuy, int uang, int harga, String username) throws SQLException;
}
