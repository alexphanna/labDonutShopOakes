package program;


import java.awt.*;
import javax.swing.*;

class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("My Application");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLayout(new BorderLayout());

            // NORTH
            JLabel label = new JLabel("Oak Donuts");
            frame.add(label, BorderLayout.NORTH);

            // WEST
            JLabel filtersLabel = new JLabel("Filters");
            frame.add(filtersLabel, BorderLayout.WEST);

            // CENTER
            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new GridLayout(1, 2));

            JPanel menuPanel = new JPanel();
            menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
            menuPanel.setBackground(Color.RED);
            menuPanel.add(new JLabel("Menu"));
            centerPanel.add(menuPanel);

            JPanel orderPanel = new JPanel();
            orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.X_AXIS));
            orderPanel.setBackground(Color.BLUE);
            orderPanel.add(new JLabel("Order"));
            centerPanel.add(orderPanel);

            frame.add(centerPanel, BorderLayout.CENTER);

            // SOUTH
            JPanel southPanel = new JPanel();
            southPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

            JButton clearButton = new JButton("Clear");
            clearButton.addActionListener(e -> label.setText("Button Clicked!"));
            southPanel.add(clearButton);

            JButton checkoutButton = new JButton("Checkout");
            checkoutButton.addActionListener(e -> label.setText("Checkout Clicked!"));
            southPanel.add(checkoutButton);

            frame.add(southPanel, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }
}