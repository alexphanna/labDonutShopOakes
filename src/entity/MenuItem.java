package entity;

import org.bson.Document;

public class MenuItem {
    private String itemName;
    private int price;

    public MenuItem(String itemName, int price)
    {
        this.itemName = itemName;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
    
    public String getItemName() {
        return itemName;
    }

    public Document toDocument() {
        Document doc = new Document("item_name", itemName)
                .append("price", price);
        return doc;
    }

    @Override
    public String toString() {
        return "MenuItem{" + "name='" + itemName + '\'' + ", price=" + price + '}';
    }
}
