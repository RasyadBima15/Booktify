package booktify.models;

public class Books {
    private int ID;
    private String name;
    private String author;
    private String penerbit;
    private String category;
    private int price;
    private int stock;
    
    public Books(int iD, String name, String author, String penerbit, String category, int price, int stock) {
        ID = iD;
        this.name = name;
        this.author = author;
        this.penerbit = penerbit;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPenerbit() {
        return penerbit;
    }
    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

}
