package app.model;

import java.util.List;

public class Order implements Identifiable {
    private long id;
    private int quantity;

    private List<Goods> goods;
    private List<Customers> customers;

    @Override
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity= quantity;
    }

    public List<Goods> getGoods() {
        return goods;
    }
    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public List<Customers> getCustomers() {
        return customers;
    }
    public void setCustomers(List<Customers> customers) {
        this.customers = customers;
    }
}
