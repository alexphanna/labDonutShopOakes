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

            JLabel label = new JLabel("Hello, World!", SwingConstants.CENTER);
            frame.add(label, BorderLayout.CENTER);

            JButton button = new JButton("Click Me");
            button.addActionListener(e -> label.setText("Button Clicked!"));
            frame.add(button, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }
}