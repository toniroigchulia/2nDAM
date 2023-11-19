import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GridBagLayoutExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GridBagLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        // // Create GridBagConstraints
        // GridBagConstraints gbc = new GridBagConstraints();
        // gbc.gridx = 0;
        // gbc.gridy = 0;
        // gbc.gridwidth = 1;
        // gbc.gridheight = 1;
        // gbc.fill = GridBagConstraints.HORIZONTAL;
        // gbc.insets = new Insets(5, 5, 5, 5);
        // gbc.anchor = GridBagConstraints.WEST;
        
        BufferedImage img = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        int r = 255;
        int g = 0;
        int b = 0;
        int col = (r << 16) | (g << 8) | b;
        img.setRGB(20, 20, col);
        
        ImageIcon Image = new ImageIcon(img);
        frame.drawImage(Image);
        
        
        // // Create a panel for the left side buttons
        // JPanel leftPanel = new JPanel();
        // leftPanel.setLayout(new GridBagLayout());
        // gbc.weightx = 0.0; // No horizontal expansion
        // gbc.weighty = 1.0; // Allow vertical expansion
        // gbc.fill = GridBagConstraints.BOTH; // Fill available horizontal and vertical space
        // frame.add(leftPanel, gbc);

        // // Add the first button to the left panel (top)
        // JButton leftButton1 = new JButton("Left Button 1");
        // GridBagConstraints leftButtonGBC1 = new GridBagConstraints();
        // leftButtonGBC1.gridx = 0;
        // leftButtonGBC1.gridy = 0; // Top position
        // leftButtonGBC1.fill = GridBagConstraints.HORIZONTAL;
        // leftPanel.add(leftButton1, leftButtonGBC1);

        // // Add the second button to the left panel (bottom)
        // JButton leftButton2 = new JButton("Left Button 2");
        // GridBagConstraints leftButtonGBC2 = new GridBagConstraints();
        // leftButtonGBC2.gridx = 0;
        // leftButtonGBC2.gridy = 1; // Bottom position
        // leftButtonGBC2.fill = GridBagConstraints.HORIZONTAL;
        // leftPanel.add(leftButton2, leftButtonGBC2);

        // // Create and add a button on the right that occupies all available horizontal
        // // and vertical space
        // JButton rightButton = new JButton("Right Button");
        // gbc.gridx = 1; // Move to the right column
        // gbc.weightx = 1.0; // Allow horizontal expansion
        // gbc.weighty = 1.0; // Allow vertical expansion
        // gbc.fill = GridBagConstraints.BOTH; // Fill available horizontal and vertical space
        // frame.add(rightButton, gbc);

        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}
