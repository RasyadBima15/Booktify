package booktify.models;

public class Customer {
    private int id;
    private String username;
    private String password;
    private int uang;
    private int token;
    
    public int getToken() {
        return token;
    }
    public void setToken(int token) {
        this.token = token;
    }
    public Customer(String username, String password, int uang, int token) {
        this.username = username;
        this.password = password;
        this.uang = uang;
        this.token = token;
    }
    public Customer(int id, String username, String password, int uang, int token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.uang = uang;
        this.token = token;
    }
    public int getid() {
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getUang() {
        return uang;
    }
    public void setUang(int uang) {
        this.uang = uang;
    }
}
