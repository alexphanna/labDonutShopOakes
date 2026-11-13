package program;

import java.awt.*;
import javax.swing.*;

public class FilterPanel extends JPanel {
    public FilterPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel filterLabel = new JLabel("Filters");
        filterLabel.setBackground(Color.BLUE);
        // align the label to the left
        filterLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(filterLabel);
        
        add(new JLabel("Category:"));
        String[] categories = {"All", "Donuts", "Coffee", "Tea"};
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);
        add(categoryComboBox);

        add(new JLabel("Search:"));
        add(new JTextField(10));
    }
}
