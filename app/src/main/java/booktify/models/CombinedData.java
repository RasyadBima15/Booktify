package booktify.models;

public class CombinedData {
    private String bookName;
    private int priceBook;
    private String date;
    private int stockBuy;
    public CombinedData(String bookName, int priceBook, String date, int stockBuy) {
        this.bookName = bookName;
        this.priceBook = priceBook;
        this.date = date;
        this.stockBuy = stockBuy;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public int getPriceBook() {
        return priceBook;
    }
    public void setPriceBook(int priceBook) {
        this.priceBook = priceBook;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public int getStockBuy() {
        return stockBuy;
    }
    public void setStockBuy(int stockBuy) {
        this.stockBuy = stockBuy;
    }


}
