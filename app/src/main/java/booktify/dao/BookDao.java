package booktify.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import booktify.models.Books;
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
                    " kategori VARCHAR(255) NOT NULL" + 
                    " harga INTEGER NOT NULL, " +
                    " stock INTEGER NOT NULL";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Books> get(String category, String nameBook) throws SQLException {
        try {
            List<Books> listBooks = new ArrayList<>();
            stmt = conn.createStatement();
            ResultSet rs;
            if (category == null){
                rs = stmt.executeQuery("SELECT * FROM books WHERE nama = " + nameBook);
            } else {
                rs = stmt.executeQuery("SELECT * FROM books WHERE kategori = " + category + " AND nama = " + nameBook);
            }
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nama");
                String penulis = rs.getString("penulis");
                String kategori = rs.getString("kategori");
                int harga = rs.getInt("harga");
                int stock = rs.getInt("stock");
                listBooks.add(new Books(id, name, penulis, kategori, harga, stock));
            }
            return listBooks;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
    public List<Books> get(String category) throws SQLException {
        try {
            List<Books> listBooks = new ArrayList<>();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nama, penulis, kategori, harga, stock FROM books WHERE kategori = " + category);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nama");
                String penulis = rs.getString("penulis");
                String kategori = rs.getString("kategori");
                int harga = rs.getInt("harga");
                int stock = rs.getInt("stock");
                listBooks.add(new Books(id,name, penulis, kategori, harga, stock));
            }
            return listBooks;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
    // public void syncData(List<Books> listBook) {
    //     try {
    //         stmt.executeUpdate("DELETE from books");
    //         stmt = conn.createStatement();
    //         for (Books book : listBook){
    //             String sql = String.format("""
    //                 INSERT INTO books(nama, penulis, kategori, harga, stock) 
    //                 VALUES('%s', '%s', '%s', '%d', '%d')
    //             """, 
    //             book.getName(),
    //             book.getAuthor(),
    //             book.getCategory(),
    //             book.getPrice(),
    //             book.getStock());
    //             stmt.executeUpdate(sql);
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
    public void delete() {
        
    }
}
