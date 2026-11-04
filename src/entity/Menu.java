package entity;

public class Menu {

    public Menu(int ID, int price, String description) {
        this.ID = ID;
        this.price = price;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private int ID;
    private int price;
    private String description;
}
