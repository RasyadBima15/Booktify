package booktify.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import booktify.abstract_class.DaoForTransactions;
import booktify.models.Transaction;
import booktify.utils.DatabaseConfig;

public class TransactionsDao implements DaoForTransactions{
    private Connection conn;
    private Statement stmt;
    public TransactionsDao(){
        conn = DatabaseConfig.getConnection();
        setupTable();
    }
    public void setupTable(){
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "transactions", null);
            if (!rs.next()){
                stmt = conn.createStatement();
                String sql = "CREATE TABLE transactions " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " id_book INTEGER NOT NULL, " +
                    " customer_id INTEGER NOT NULL, " +
                    " tanggal VARCHAR(255) NOT NULL, " +
                    " stock_purchased INTEGER NOT NULL, " +
                    " FOREIGN KEY (customer_id) REFERENCES customers(id), " +
                    " FOREIGN KEY (id_book) REFERENCES books(id))";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insert(List<Transaction> listTransaction) {
        try {
            stmt = conn.createStatement();
            for (int index = 0; index < listTransaction.size(); index++){
                String sql = String.format("""
                    INSERT INTO transactions(id_book, customer_id, tanggal, stock_purchased) 
                    VALUES('%d', '%d', '%s', '%d')
                """,
                listTransaction.get(index).getid_Book(),
                listTransaction.get(index).getId_customer(),
                listTransaction.get(index).getDate(),
                listTransaction.get(index).getStockPurchased());
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Transaction> get() throws SQLException {
        try {
            List<Transaction> listBooks = new ArrayList<>();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transactions");
            while (rs.next()) {
                int bookId = rs.getInt("id_book");
                int customerId = rs.getInt("customer_id");
                String date = rs.getString("tanggal");
                int stockPurchased = rs.getInt("stock_purchased");
                listBooks.add(new Transaction(bookId, customerId, date, stockPurchased));
            }
            return listBooks;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
}
