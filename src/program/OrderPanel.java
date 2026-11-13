package program;

import java.awt.*;
import javax.swing.*;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import entity.Order;

public class OrderPanel extends JPanel {
    public OrderPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Order"));

        // Add scroll pane with list of menu items
        String[] menuItems = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10"};
        JList<String> menuList = new JList<>(menuItems);
        JScrollPane scrollPane = new JScrollPane(menuList);
        // add table to the panel
        JTable table = new JTable(new Object[][]{}, new Object[]{"Item", "Options", "Qty", "Price", "Total"});

         try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Access the database and collection
            MongoDatabase database = mongoClient.getDatabase("oak_donuts");
            MongoCollection<Document> ordersCollection = database.getCollection("orders");

            // add a new order to the collection
            Order newOrder = new Order(1, 696, "2024-06-01 12:00:00");
            ordersCollection.insertOne(newOrder.toDocument());
        }

        scrollPane.setViewportView(table);
        add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(400, 1000));
        
        // Receipt panel with subtotal, tax, and total

        JPanel subtotalPanel = new JPanel();
        subtotalPanel.setLayout(new BorderLayout());
        subtotalPanel.add(new JLabel("Subtotal:"), BorderLayout.WEST);
        subtotalPanel.add(new JPanel(), BorderLayout.CENTER); // spacer
        subtotalPanel.add(new JLabel("$6.96"), BorderLayout.EAST);
        add(subtotalPanel);

        JPanel taxPanel = new JPanel();
        taxPanel.setLayout(new BorderLayout());
        taxPanel.add(new JLabel("Tax (%6):"), BorderLayout.WEST);
        taxPanel.add(new JPanel(), BorderLayout.CENTER); // spacer
        taxPanel.add(new JLabel("$0.42"), BorderLayout.EAST);
        add(taxPanel);

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new BorderLayout());
        totalPanel.add(new JLabel("Total:"), BorderLayout.WEST);
        totalPanel.add(new JPanel(), BorderLayout.CENTER); // spacer
        totalPanel.add(new JLabel("$7.38"), BorderLayout.EAST);
        add(totalPanel);

        JPanel bottomRow = new JPanel();
        bottomRow.setLayout(new FlowLayout());
        JButton clearButton = new JButton("Clear");
        bottomRow.add(clearButton);

        JButton checkoutButton = new JButton("Checkout");
        bottomRow.add(checkoutButton);
        add(bottomRow);
    }
}
