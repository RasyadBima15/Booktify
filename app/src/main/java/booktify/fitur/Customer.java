package booktify.fitur;

public class Customer {
    private int ID;
    private String username;
    private String password;
    private int uang;
    
    public Customer(int iD, String username, String password, int uang) {
        ID = iD;
        this.username = username;
        this.password = password;
        this.uang = uang;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
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
