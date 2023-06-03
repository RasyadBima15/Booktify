package booktify.abstract_class;

import java.sql.SQLException;
import java.util.List;

import booktify.models.Transaction;

public interface DaoForTransactions {
    public void setupTable();
    public void insert(List<Transaction> listTransaction);
    public List<Transaction> get() throws SQLException;
}
