import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class ParameterPanel extends JPanel {

    private JLabel productors;
    private JTextField textProductors;

    private JLabel quantItemProductor;
    private JTextField textQuanItemProductor;

    private JToggleButton tempsProduccio;

    private JLabel tempsMaximProduccio;
    private JTextField textTempsMaximProduccio;

    private JLabel consumidors;
    private JTextField textConsumidors;

    private JLabel quantItemConsumidors;
    private JTextField textQuantItemConsumidor;

    private JToggleButton tempsConsumir;

    private JLabel tempsMaximConsumir;
    private JTextField textTempsMaximConsumir;

    public ParameterPanel() {
        this.setLayout(new GridBagLayout());
        addContentToPane();
    }

    private void addContentToPane() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        // Producotrs
        c.insets = new Insets(10, 10, 1, 10);
        productors = new JLabel();
        productors.setText("Productores");
        this.add(productors, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textProductors = new JTextField();
        textProductors.setText("200");
        this.add(textProductors, c);

        c.insets = new Insets(10, 10, 1, 10);
        c.gridy++;
        quantItemProductor = new JLabel();
        quantItemProductor.setText("Items per Productor");
        this.add(quantItemProductor, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textQuanItemProductor = new JTextField();
        textQuanItemProductor.setText("100");
        this.add(textQuanItemProductor, c);

        c.insets = new Insets(10, 10, 10, 10);
        c.gridy++;
        tempsProduccio = new JToggleButton();
        tempsProduccio.setText("TempProd.Rand/Fix");
        this.add(tempsProduccio, c);

        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        tempsMaximProduccio = new JLabel();
        tempsMaximProduccio.setText("Temps Maxim Produccio");
        this.add(tempsMaximProduccio, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textTempsMaximProduccio = new JTextField();
        textTempsMaximProduccio.setText("100");
        this.add(textTempsMaximProduccio, c);

        // Consumidors
        c.insets = new Insets(25, 10, 1, 10);
        c.gridy++;
        consumidors = new JLabel();
        consumidors.setText("Consumidors");
        this.add(consumidors, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textConsumidors = new JTextField();
        textConsumidors.setText("400");
        this.add(textConsumidors, c);

        c.insets = new Insets(10, 10, 1, 10);
        c.gridy++;
        quantItemConsumidors = new JLabel();
        quantItemConsumidors.setText("Items per Consumidor");
        this.add(quantItemConsumidors, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textQuantItemConsumidor = new JTextField();
        textQuantItemConsumidor.setText("100");
        this.add(textQuantItemConsumidor, c);

        c.insets = new Insets(10, 10, 10, 10);
        c.gridy++;
        tempsConsumir = new JToggleButton();
        tempsConsumir.setText("TempCon.Rand/Fix");
        this.add(tempsConsumir, c);

        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        tempsMaximConsumir = new JLabel();
        tempsMaximConsumir.setText("Temps Maxim Consumir");
        this.add(tempsMaximConsumir, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textTempsMaximConsumir = new JTextField();
        textTempsMaximConsumir.setText("100");
        this.add(textTempsMaximConsumir, c);
    }
}
