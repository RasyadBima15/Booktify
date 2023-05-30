package booktify.models;

public class Transaction {
    private String id_Name;
    private String id_Book;
    private String date;
    
    public Transaction(String id_Name, String id_Book, String date) {
        this.id_Name = id_Name;
        this.id_Book = id_Book;
        this.date = date;
    }
    
    public String getid_Name() {
        return id_Name;
    }
    public void setid_Name(String id_Name) {
        this.id_Name = id_Name;
    }
    public String getid_Book() {
        return id_Book;
    }
    public void setid_Book(String id_Book) {
        this.id_Book = id_Book;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
