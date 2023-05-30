package booktify.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import booktify.utils.DatabaseConfig;

public class BookDao {
    private Connection conn;
    private Statement stmt;
    public BookDao(){
        conn = DatabaseConfig.getConnection();
        setupTable();
    }
    private void setupTable(){
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "books", null);
            if (!rs.next()){
                stmt = conn.createStatement();
                String sql = "CREATE TABLE books " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " nama VARCHAR(255) NOT NULL, " + 
                    " penulis VARCHAR(255) NOT NULL, " +
                    " penerbit VARCHAR(255) NOT NULL, " +
                    " kategori VARCHAR(255) NOT NULL" + 
                    " harga INTEGER NOT NULL, " +
                    " stock INTEGER NOT NULL";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
