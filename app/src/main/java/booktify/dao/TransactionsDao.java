package booktify.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import booktify.models.Transaction;
import booktify.utils.DatabaseConfig;

public class TransactionsDao {
    private Connection conn;
    private Statement stmt;
    public TransactionsDao(){
        conn = DatabaseConfig.getConnection();
        setupTable();
    }
    private void setupTable(){
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "transactions", null);
            if (!rs.next()){
                stmt = conn.createStatement();
                String sql = "CREATE TABLE transactions " +
                    "(id INTEGER PRIMARY KEY, " +
                    " id_book INTEGER NOT NULL, " +
                    " tanggal VARCHAR(255) NOT NULL, " +
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
                    INSERT INTO transactions(id, id_book, tanggal) 
                    VALUES('%d', '%d', '%s')
                """,
                index + 1,
                listTransaction.get(index).getid_Book(),
                listTransaction.get(index).getDate());
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Transaction> get(int BookId) throws SQLException {
        try {
            List<Transaction> listBooks = new ArrayList<>();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from transactions WHERE id_book = " + BookId);
            while (rs.next()) {
                int bookId = rs.getInt("id_book");
                String date = rs.getString("tanggal");
                listBooks.add(new Transaction(bookId, date));
            }
            return listBooks;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
    public List<Transaction> get() throws SQLException {
        try {
            List<Transaction> listBooks = new ArrayList<>();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM transactions");
            while (rs.next()) {
                int bookId = rs.getInt("id_book");
                String date = rs.getString("tanggal");
                listBooks.add(new Transaction(bookId, date));
            }
            return listBooks;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
}
