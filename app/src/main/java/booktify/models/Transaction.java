package booktify.models;
public class Transaction {
    private int id_Book;
    private int id_customer;

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    private int stockPurchased;

    public int getStockPurchased() {
        return stockPurchased;
    }

    public void setStockPurchased(int stockPurchased) {
        this.stockPurchased = stockPurchased;
    }

    private String date;

    public Transaction(int id_Book, int id_customer, String date, int stockPurchased) {
        this.id_Book = id_Book;
        this.id_customer = id_customer;
        this.date = date;
        this.stockPurchased = stockPurchased;
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
