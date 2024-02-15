package Visual;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import DTOS.*;

public class ParameterPanel extends JPanel {

    private DTOLabParameters labParameters;

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

    public ParameterPanel(DTOLabParameters labParameters) {
        this.labParameters = labParameters;
        this.setLayout(new GridBagLayout());
        addContentToPane();
        setDefaultFieldsValues();
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
        this.add(textProductors, c);

        c.insets = new Insets(10, 10, 1, 10);
        c.gridy++;
        quantItemProductor = new JLabel();
        quantItemProductor.setText("Items per Productor");
        this.add(quantItemProductor, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textQuanItemProductor = new JTextField();
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
        this.add(textConsumidors, c);

        c.insets = new Insets(10, 10, 1, 10);
        c.gridy++;
        quantItemConsumidors = new JLabel();
        quantItemConsumidors.setText("Items per Consumidor");
        this.add(quantItemConsumidors, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textQuantItemConsumidor = new JTextField();
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

        this.add(textTempsMaximConsumir, c);
    }

    public void setDefaultFieldsValues(){
        textProductors.setText(labParameters.getProductores()+"");
        textQuanItemProductor.setText(labParameters.getQuantityItemsP()+"");
        tempsProduccio.setSelected(labParameters.isTempsMaximCheckBoxP());
        textTempsMaximProduccio.setText(labParameters.getTempsMaximP()+"");

        textConsumidors.setText(labParameters.getConsumidores()+"");
        textQuantItemConsumidor.setText(labParameters.getQuantityItemsC()+"");
        tempsConsumir.setSelected(labParameters.isTempsMaximCheckBoxC());
        textTempsMaximConsumir.setText(labParameters.getTempsMaximC()+"");
    }

    public DTOLabParameters getLabParameters() {
        return labParameters;
    }

    public void setLabParameters(DTOLabParameters labParameters) {
        this.labParameters = labParameters;
    }

    public JLabel getProductors() {
        return productors;
    }

    public void setProductors(JLabel productors) {
        this.productors = productors;
    }

    public JTextField getTextProductors() {
        return textProductors;
    }

    public void setTextProductors(JTextField textProductors) {
        this.textProductors = textProductors;
    }

    public JLabel getQuantItemProductor() {
        return quantItemProductor;
    }

    public void setQuantItemProductor(JLabel quantItemProductor) {
        this.quantItemProductor = quantItemProductor;
    }

    public JTextField getTextQuanItemProductor() {
        return textQuanItemProductor;
    }

    public void setTextQuanItemProductor(JTextField textQuanItemProductor) {
        this.textQuanItemProductor = textQuanItemProductor;
    }

    public JToggleButton getTempsProduccio() {
        return tempsProduccio;
    }

    public void setTempsProduccio(JToggleButton tempsProduccio) {
        this.tempsProduccio = tempsProduccio;
    }

    public JLabel getTempsMaximProduccio() {
        return tempsMaximProduccio;
    }

    public void setTempsMaximProduccio(JLabel tempsMaximProduccio) {
        this.tempsMaximProduccio = tempsMaximProduccio;
    }

    public JTextField getTextTempsMaximProduccio() {
        return textTempsMaximProduccio;
    }

    public void setTextTempsMaximProduccio(JTextField textTempsMaximProduccio) {
        this.textTempsMaximProduccio = textTempsMaximProduccio;
    }

    public JLabel getConsumidors() {
        return consumidors;
    }

    public void setConsumidors(JLabel consumidors) {
        this.consumidors = consumidors;
    }

    public JTextField getTextConsumidors() {
        return textConsumidors;
    }

    public void setTextConsumidors(JTextField textConsumidors) {
        this.textConsumidors = textConsumidors;
    }

    public JLabel getQuantItemConsumidors() {
        return quantItemConsumidors;
    }

    public void setQuantItemConsumidors(JLabel quantItemConsumidors) {
        this.quantItemConsumidors = quantItemConsumidors;
    }

    public JTextField getTextQuantItemConsumidor() {
        return textQuantItemConsumidor;
    }

    public void setTextQuantItemConsumidor(JTextField textQuantItemConsumidor) {
        this.textQuantItemConsumidor = textQuantItemConsumidor;
    }

    public JToggleButton getTempsConsumir() {
        return tempsConsumir;
    }

    public void setTempsConsumir(JToggleButton tempsConsumir) {
        this.tempsConsumir = tempsConsumir;
    }

    public JLabel getTempsMaximConsumir() {
        return tempsMaximConsumir;
    }

    public void setTempsMaximConsumir(JLabel tempsMaximConsumir) {
        this.tempsMaximConsumir = tempsMaximConsumir;
    }

    public JTextField getTextTempsMaximConsumir() {
        return textTempsMaximConsumir;
    }

    public void setTextTempsMaximConsumir(JTextField textTempsMaximConsumir) {
        this.textTempsMaximConsumir = textTempsMaximConsumir;
    }
}
