package app.model;

public class Goods implements Identifiable{
    private long id;
    private String name;
    private int stockBalance;
    private int price;

    @Override
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getStockBalance() {
        return stockBalance;
    }
    public void setStockBalance(int stockBalance) {
        this.stockBalance = stockBalance;
    }

    public int getPrice(){return price;}
    public void setPrice(int price) { this.price = price; }
}
