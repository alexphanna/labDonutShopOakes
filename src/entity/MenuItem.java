package entity;

public class MenuItem {
    private String itemName;
    private String[] options;
    private int price;

    public MenuItem(String itemName, String[] options, int price)
    {
        this.itemName = itemName;
        this.options = options;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public String[] getOptions() {
        return options;
    }

    @Override
    public String toString() {
        return "MenuItem{" + "name='" + itemName + '\'' + ", options=" + java.util.Arrays.toString(options) + ", price=" + price + '}';
    }
}
