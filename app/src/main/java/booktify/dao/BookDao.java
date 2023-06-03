package booktify.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import booktify.abstract_class.DaoForBooks;
import booktify.models.Books;
import booktify.utils.DatabaseConfig;

public class BookDao implements DaoForBooks{
    private Connection conn;
    private Statement stmt;
    public BookDao(){
        conn = DatabaseConfig.getConnection();
        setupTable();
    }
    public void setupTable(){
        try {
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet rs = meta.getTables(null, null, "books", null);
            if (!rs.next()){
                stmt = conn.createStatement();
                String sql = "CREATE TABLE books " +
                    "(id INTEGER PRIMARY KEY, " +
                    " nama VARCHAR(255) NOT NULL, " + 
                    " penulis VARCHAR(255) NOT NULL, " +
                    " kategori VARCHAR(255) NOT NULL, " + 
                    " harga INTEGER NOT NULL, " +
                    " stock INTEGER NOT NULL)";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Books> get() throws SQLException {
        try {
            List<Books> listBooks = new ArrayList<>();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
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
    public void insert(List<Books> listBook) {
        try {
            stmt = conn.createStatement();
            for (int index = 0; index < listBook.size(); index++){
                String sql = String.format("""
                    INSERT INTO books(id, nama, penulis, kategori, harga, stock) 
                    VALUES('%d', '%s', '%s', '%s', '%d', '%d')
                """,
                index + 1,
                listBook.get(index).getName(),
                listBook.get(index).getAuthor(),
                listBook.get(index).getCategory(),
                listBook.get(index).getPrice(),
                listBook.get(index).getStock());
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void buyBook(int BookId, int stock, int stockBuy, int uang, int harga, String username) throws SQLException{
        if (stock - stockBuy >= 0 && uang - (stockBuy * harga) >= 0){
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE books SET stock = '" + (stock - stockBuy) + "' WHERE id = '" + BookId + "'");
            stmt.executeUpdate("UPDATE customers SET uang = '" + (uang - (stockBuy * harga)) + "' WHERE username = '" + username + "'");
        }
    }
}
