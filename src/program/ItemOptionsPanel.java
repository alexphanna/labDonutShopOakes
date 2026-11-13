package program;

import java.awt.*;
import javax.swing.*;

public class ItemOptionsPanel extends JPanel {
    public ItemOptionsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Item Options"));
        
        add(new JLabel("Icing:"));
        String[] categories = {"All", "Donuts", "Coffee", "Tea"};
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);
        add(categoryComboBox);

        add(new JLabel("Filling:"));
        categoryComboBox = new JComboBox<>(categories);
        add(categoryComboBox);
    }
}
