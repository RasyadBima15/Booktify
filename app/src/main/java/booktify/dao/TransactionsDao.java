package booktify.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            ResultSet rs = meta.getTables(null, null, "booktify", null);
            if (!rs.next()){
                stmt = conn.createStatement();
                String sql = "CREATE TABLE transactions " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " id_customer INTEGER NOT NULL, " + 
                    " id_book INTEGER NOT NULL, " +
                    " tanggal VARCHAR(255) NOT NULL, " +
                    " FOREIGN KEY (id_customer) REFERENCES customers(id), " +
                    " FOREIGN KEY (id_book) REFERENCES books(id)";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
