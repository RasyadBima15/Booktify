package booktify.class;

public class Transaction {
    private String username;
    private String nameBooks;
    private String date;
    private int harga;
    
    public Transaction(String username, String nameBooks, String date, int harga) {
        this.username = username;
        this.nameBooks = nameBooks;
        this.date = date;
        this.harga = harga;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getNameBooks() {
        return nameBooks;
    }
    public void setNameBooks(String nameBooks) {
        this.nameBooks = nameBooks;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getHarga() {
        return harga;
    }
    public void setHarga(int harga) {
        this.harga = harga;
    }
}
