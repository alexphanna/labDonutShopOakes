package program;

import java.awt.*;
import javax.swing.*;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;

import entity.MenuItem;

class Main {
    public static void main(String[] args) {
        MenuItem[] menuItems = {
            new MenuItem("Glazed Donut", 250),
            new MenuItem("Chocolate Donut", 300),
            new MenuItem("Strawberry Donut", 350),
            new MenuItem("Vanilla Donut", 300),
            new MenuItem("Cinnamon Donut", 300),
            new MenuItem("Blueberry Donut", 350),
            new MenuItem("Maple Donut", 400),
            new MenuItem("Jelly Donut", 350),
            new MenuItem("Boston Cream Donut", 400),
            new MenuItem("Old Fashioned Donut", 300),
            new MenuItem("Powdered Sugar Donut", 300),
        };

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Access the database and collection
            MongoDatabase database = mongoClient.getDatabase("oak_donuts");
            MongoCollection<Document> menuCollection = database.getCollection("menu");

            // Clear the collection (optional)
            menuCollection.deleteMany(new Document());

            // 1. (Optional but recommended) Enforce the PRIMARY KEY from your SQL
            //    by creating a unique index on "item_name".
            menuCollection.createIndex(
                Indexes.ascending("item_name"),
                new IndexOptions().unique(true)
            );

            for (MenuItem item : menuItems) {
                // 2. Convert the MenuItem object to a Document
                Document newMenuItem = item.toDocument();

                // 3. Insert it.
                menuCollection.insertOne(newMenuItem);
            }
        }


        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("My Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new BorderLayout());

            // NORTH
            JLabel label = new JLabel("Oak Donuts");
            frame.add(label, BorderLayout.NORTH);

            // WEST
            JPanel westPanel = new JPanel();
            westPanel.setLayout(new BorderLayout());

            JPanel boxPanel = new JPanel();
            boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));

            JPanel filterPanel = new FilterPanel();
            boxPanel.add(filterPanel);

            JPanel itemOptionsPanel = new ItemOptionsPanel();
            boxPanel.add(itemOptionsPanel);

            westPanel.add(boxPanel, BorderLayout.NORTH);

            frame.add(westPanel, BorderLayout.WEST);

            // CENTER
            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new GridLayout(1, 2));

            JPanel menuPanel = new MenuPanel();
            centerPanel.add(menuPanel);

            JPanel orderPanel = new OrderPanel();
            centerPanel.add(orderPanel);

            frame.add(centerPanel, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}