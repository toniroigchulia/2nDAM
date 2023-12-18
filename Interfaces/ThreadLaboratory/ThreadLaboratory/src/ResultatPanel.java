import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class ResultatPanel extends JPanel implements Runnable {

    //DTOLabResults
    private DTOLabResults labResults;

    // Contador productes totals
    private JLabel contador;
    private JTextField textContador;

    // Mil·lisegons en crear objectes Thread
    private JLabel crearThreads;
    private JTextField textCrearThreads;

    // Mil·lisegons arrancar els fils
    private JLabel startThreads;
    private JTextField textStartThreads;

    // Mil·lisegons processament dels fils consumidors
    private JLabel procesConsumidors;
    private JTextField textProcesConsumidors;

    // Mil·lisegons processament dels fils productors
    private JLabel procesProductors;
    private JTextField textProcesProductors;

    // Quantitat d'ítems produïts per cadascun dels productes
    private JLabel itemsProduitsProductos;
    private JTextField textItemsProduitProductor;

    // Quantitat d'ítems consumits per cadascun dels consumidors
    private JLabel itemsConsumitConsumidor;
    private JTextField textItemConsumitConsumidor;

    // Quantitat de fils productors processant
    private JLabel filsProductorsProcesant;
    private JTextField textFilsProductorsProcesant;

    // Quantitat de fils productors que han finalitzat
    private JLabel filsAcabatsProductors;
    private JTextField textFilsAcabatsProductors;

    // Quantitat de fils productors pendents
    private JLabel filsProductorsPendents;
    private JTextField textFilsProductorsPendents;

    // Quantitat de fils consumidors processant
    private JLabel filsConsumidorsConsumint;
    private JTextField textFilsConsumidorsConsumint;

    // Quantitat de fils consumidors que han finalitzat
    private JLabel filsConsumidorsFinalitzats;
    private JTextField textFilsConsumidorsFinalitzats;

    // Quantitat de fils consumidors pendents
    private JLabel filsConsumidorsPendents;
    private JTextField textFilsConsumidorsPendents;

    public ResultatPanel() {
        this.setLayout(new GridBagLayout());
        addContentToPane();
    }

    private void addContentToPane() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;

        // Contador productes totals
        c.insets = new Insets(10, 10, 1, 10);
        contador = new JLabel();
        contador.setText("Contador");
        this.add(contador, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textContador = new JTextField();
        textContador.setText("0");
        this.add(textContador, c);

        // Mil·lisegons en crear objectes Thread
        c.insets = new Insets(10, 10, 1, 10);
        c.gridy++;
        crearThreads = new JLabel();
        crearThreads.setText("Tiempo Crear Threads");
        this.add(crearThreads, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textCrearThreads = new JTextField();
        textCrearThreads.setText("0");
        this.add(textCrearThreads, c);

        // Mil·lisegons arrancar els fils
        c.insets = new Insets(10, 10, 1, 10);
        c.gridy++;
        startThreads = new JLabel();
        startThreads.setText("Tiempo Start Threads");
        this.add(startThreads, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textStartThreads = new JTextField();
        textStartThreads.setText("0");
        this.add(textStartThreads, c);

        // Mil·lisegons processament dels fils consumidors
        c.insets = new Insets(10, 10, 1, 10);
        c.gridy++;
        procesConsumidors = new JLabel();
        procesConsumidors.setText("Temps Proces Consumidors");
        this.add(procesConsumidors, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textProcesConsumidors = new JTextField();
        textProcesConsumidors.setText("0");
        this.add(textProcesConsumidors, c);

        // Mil·lisegons processament dels fils productors
        c.insets = new Insets(10, 10, 1, 10);
        c.gridy++;
        procesProductors = new JLabel();
        procesProductors.setText("Temps Proces Productors");
        this.add(procesProductors, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textProcesProductors = new JTextField();
        textProcesProductors.setText("0");
        this.add(textProcesProductors, c);

        // Quantitat d'ítems produïts per cadascun dels productes
        c.insets = new Insets(10, 10, 1, 10);
        c.gridy++;
        itemsProduitsProductos = new JLabel();
        itemsProduitsProductos.setText("Items per Productor");
        this.add(itemsProduitsProductos, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textItemsProduitProductor = new JTextField();
        textItemsProduitProductor.setText("0");
        this.add(textItemsProduitProductor, c);

        // Quantitat d'ítems consumits per cadascun dels consumidors
        c.insets = new Insets(10, 10, 1, 10);
        c.gridy++;
        itemsConsumitConsumidor = new JLabel();
        itemsConsumitConsumidor.setText("Items per Consumidor");
        this.add(itemsConsumitConsumidor, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textItemConsumitConsumidor = new JTextField();
        textItemConsumitConsumidor.setText("0");
        this.add(textItemConsumitConsumidor, c);

        // Quantitat de fils productors processant
        c.insets = new Insets(10, 10, 1, 10);
        c.gridy = 1;
        c.gridx = 1;
        filsProductorsProcesant = new JLabel();
        filsProductorsProcesant.setText("Productors Processant");
        this.add(filsProductorsProcesant, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textFilsProductorsProcesant = new JTextField();
        textFilsProductorsProcesant.setText("0");
        this.add(textFilsProductorsProcesant, c);

        // Quantitat de fils productors que han finalitzat
        c.insets = new Insets(10, 10, 1, 10);
        c.gridy++;
        filsAcabatsProductors = new JLabel();
        filsAcabatsProductors.setText("Productors Acabats");
        this.add(filsAcabatsProductors, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textFilsAcabatsProductors = new JTextField();
        textFilsAcabatsProductors.setText("0");
        this.add(textFilsAcabatsProductors, c);

        // Quantitat de fils productors pendents
        c.insets = new Insets(10, 10, 1, 10);
        c.gridy++;
        filsProductorsPendents = new JLabel();
        filsProductorsPendents.setText("Productors Pendents");
        this.add(filsProductorsPendents, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textFilsProductorsPendents = new JTextField();
        textFilsProductorsPendents.setText("0");
        this.add(textFilsProductorsPendents, c);

        // Quantitat de fils consumidors processant
        c.insets = new Insets(25, 10, 1, 10);
        c.gridy++;
        filsConsumidorsConsumint = new JLabel();
        filsConsumidorsConsumint.setText("Consumidors Processant");
        this.add(filsConsumidorsConsumint, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textFilsConsumidorsConsumint = new JTextField();
        textFilsConsumidorsConsumint.setText("0");
        this.add(textFilsConsumidorsConsumint, c);

        // Quantitat de fils consumidors que han finalitzat
        c.insets = new Insets(10, 10, 1, 10);
        c.gridy++;
        filsConsumidorsFinalitzats = new JLabel();
        filsConsumidorsFinalitzats.setText("Consumidors Acabats");
        this.add(filsConsumidorsFinalitzats, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textFilsConsumidorsFinalitzats = new JTextField();
        textFilsConsumidorsFinalitzats.setText("0");
        this.add(textFilsConsumidorsFinalitzats, c);

        // Quantitat de fils consumidors pendents
        c.insets = new Insets(10, 10, 1, 10);
        c.gridy++;
        filsConsumidorsPendents = new JLabel();
        filsConsumidorsPendents.setText("Consumidors Pendents");
        this.add(filsConsumidorsPendents, c);
        c.insets = new Insets(1, 10, 1, 10);
        c.gridy++;
        textFilsConsumidorsPendents = new JTextField();
        textFilsConsumidorsPendents.setText("0");
        this.add(textFilsConsumidorsPendents, c);
    }
    
    public DTOLabResults getLabResults() {
        return labResults;
    }

    public void setLabResults(DTOLabResults labResults) {
        this.labResults = labResults;
    }

    @Override
    public void run() {
        while (true) {

        }
    }
}
