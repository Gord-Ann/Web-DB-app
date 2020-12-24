package app.model;

public class Staff extends Customers implements Identifiable {
    private long id;
    private String name;
    private int rank;

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

    public int getRank() { return rank; }
    public void setRank(int rank) {
        this.rank = rank;
    }

}