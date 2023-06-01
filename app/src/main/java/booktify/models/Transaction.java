package booktify.models;
public class Transaction {
    private int id_Book;
    private String date;

    public Transaction(int id_Book, String date) {
        this.id_Book = id_Book;
        this.date = date;
    }

    public int getid_Book() {
        return id_Book;
    }
    public void setid_Book(int id_Book) {
        this.id_Book = id_Book;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
