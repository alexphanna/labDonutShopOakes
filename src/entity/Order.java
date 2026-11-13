/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import org.bson.Document;

/**
 *
 * @author Gokhan
 */
public class Order
{
    private int ID;
    private int price;
    private String dateTime;
    private MenuItem[] items;

    public Order(int ID, int price, String dateTime)
    {
        this.ID = ID;
        this.price = price;
        this.dateTime = dateTime;
        this.items = new MenuItem[0]; // Initialize with an empty array
    }

    public int getID() {
        return ID;
    }

    public int getPrice() {
        return price;
    }

    public String getDateTime() {
        return dateTime;
    }

    public MenuItem[] getItems() {
        return items;
    }

    public Document toDocument() {
        Document items = new Document();
        
        for (int i = 0; i < this.items.length; i++) {
            items.append("item_" + (i + 1), this.items[i].toDocument());
        }

        Document doc = new Document("ID", ID)
                .append("price", price)
                .append("dateTime", dateTime)
                .append("items", items); // Assuming items is an array of MenuItem objects
        return doc;
    }

    @Override
    public String toString() {
        return "Order{" + "ID=" + ID + ", price=" + price + ", dateTime=" + dateTime + ", items=" + java.util.Arrays.toString(items) + '}';
    }
}
