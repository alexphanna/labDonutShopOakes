package program;

import java.awt.*;
import javax.swing.*;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;

import entity.Order;

import org.bson.Document;

public class MenuPanel extends JPanel {
    public MenuPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Menu"));


        JList<String> menuList = new JList<>();
        MongoCollection<Document> ordersCollection;

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Access the database and collection
            MongoDatabase database = mongoClient.getDatabase("oak_donuts");
            MongoCollection<Document> menuCollection = database.getCollection("menu");
            ordersCollection = database.getCollection("orders");

            menuList.setListData(menuCollection.find().map(doc -> doc.getString("item_name") + " - $" + doc.getInteger("price") / 100.0).into(new java.util.ArrayList<>()).toArray(new String[0]));
        }
        
        JScrollPane scrollPane = new JScrollPane(menuList);
        add(scrollPane);
        scrollPane.setPreferredSize(new Dimension(400, 1000));

        JPanel bottomRow = new JPanel();
        bottomRow.setLayout(new FlowLayout());
        bottomRow.add(new JLabel("Qty: "));
        bottomRow.add(new JTextField(5));
        bottomRow.add(new JLabel("Unit: $1.99"));

        JButton addToOrderButton = new JButton("Add to Order");
        addToOrderButton.addActionListener(e -> {
            String selectedItem = menuList.getSelectedValue();
            String quantityText = ((JTextField) bottomRow.getComponent(1)).getText();
            int quantity = 0;
            try {
                quantity = Integer.parseInt(quantityText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid quantity.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
        });
        bottomRow.add(addToOrderButton);
        add(bottomRow);
    }
}
