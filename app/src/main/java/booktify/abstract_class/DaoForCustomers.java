package booktify.abstract_class;

import java.sql.SQLException;
import java.util.List;

import booktify.models.Customer;

public interface DaoForCustomers {
    public void setupTable();
    public void insert(List<Customer> listCustomer);
    public List<Customer> get(String username) throws SQLException;
    public void unactivateToken(String username) throws SQLException;
    public boolean checkUsernameExist(String username) throws SQLException;
    public int addBalance(String username, int uang) throws SQLException;
}
