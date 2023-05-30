package booktify.models;

public class Books {
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private String name;
    private String author;
    private String category;
    private int price;
    private int stock;
    
    public Books(int id, String name, String author, String category, int price, int stock) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }
    public Books(String name, String author, String category, int price, int stock) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.price = price;
        this.stock = stock;
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
