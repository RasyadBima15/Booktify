package booktify.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import booktify.utils.DatabaseConfig;

public class CustomerDao {
    private Connection conn;
    private Statement stmt;
    public CustomerDao(){
        conn = DatabaseConfig.getConnection();
        setupTable();
    }
    private void setupTable(){
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "customers", null);
            if (!rs.next()){
                stmt = conn.createStatement();
                String sql = "CREATE TABLE customers " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " username VARCHAR(255) NOT NULL UNIQUE, " + 
                    "password VARCHAR(255) NOT NULL UNIQUE, " +
                    "uang INTEGER NOT NULL)";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
