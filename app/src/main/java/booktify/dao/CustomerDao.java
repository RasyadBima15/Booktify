package booktify.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import booktify.models.Customer;
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
                    " password VARCHAR(255) NOT NULL, " +
                    " uang INTEGER NOT NULL, " +
                    " token INTEGER NOT NULL CHECK (token IN (0, 1)))";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insert(List<Customer> listCustomer) {
        try {
            // stmt.executeUpdate("DELETE from customers");
            stmt = conn.createStatement();
            for (int index = 0; index < listCustomer.size(); index++){
                String sql = String.format("""
                    INSERT INTO customers(username, password, uang, token) 
                    VALUES('%s', '%s', '%d', '%d')
                """,
                listCustomer.get(index).getUsername(),
                listCustomer.get(index).getPassword(),
                listCustomer.get(index).getUang(),
                listCustomer.get(index).getToken());
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Customer> get(String username) throws SQLException {
        try {
            List<Customer> listBooks = new ArrayList<>();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE username = '" + username + "'");
            while (rs.next()) {
                String name = rs.getString("username");
                String password = rs.getString("password");
                int uang = rs.getInt("uang");
                int token = rs.getInt("token");
                listBooks.add(new Customer(name, password, uang, token));
            }
            return listBooks;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
    public void unactivateToken(String username) throws SQLException {
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE customers SET token = 0 WHERE username = '" + username + "'");
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
    public boolean checkUsernameExist(String username) throws SQLException {
        boolean exist = false;
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE username = '" + username + "'");
            if (rs.next()){
                exist = true;
            }
        } catch (SQLException e) {
            throw new SQLException();
        }
        return exist;
    }
    public int addBalance(String username, int uang) throws SQLException {
        try {
            stmt = conn.createStatement();
            int id = stmt.executeUpdate("UPDATE customers SET uang = '" + (uang + 300000) + "'" + " WHERE username = '" + username + "'" + " AND TOKEN = 1 RETURNING id" );
            return id;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }
}
