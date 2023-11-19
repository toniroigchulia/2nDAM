import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Viewer extends JFrame implements ComponentListener, ActionListener, ItemListener, Runnable {
    // ATRIBUTOS
    private Controller controller;

    private JButton jb;
    private JTextField contador;

    private JLabel textoProductoresProduciendo;
    private JTextField productoresProduciendo;

    private JLabel textoProductoresProducidos;
    private JTextField productoresProducidos;

    private JLabel textoConsumidoresConsumiendo;
    private JTextField consumidoresConsumiendio;

    private JLabel textoConsumidoresConsumidos;
    private JTextField consumidoresConsumidos;

    private JLabel textoTiempoTotal;
    private JTextField tiempoTotal;

    private JToggleButton tiempoProducirAleatorio;
    private JSlider tiempoProducir;

    private JToggleButton tiempoConsumidorAleatorio;
    private JSlider tiempoConsumidor;

    private JLabel textoConsumidores;
    private JTextField numConsumidores;

    private JLabel textoProductores;
    private JTextField numProductores;

    private JLabel tiemposCreateThreads;
    private JLabel textoTiempoTotalCreateThreads;
    private JTextField tiempoTotalCreateThreads;
    private JLabel textoTiempoMedioCreateThreads;
    private JTextField tiempoMedioCreateThreads;

    private JLabel tiemposStartThreads;
    private JLabel textoTiempoTotalStartThreads;
    private JTextField tiempoTotalStartThreads;
    private JLabel textoTiempoMedioStartThreads;
    private JTextField tiempoMedioStartThreads;

    // METODOS CONSTRUCTORES
    public Viewer(Controller controller) {
        this.controller = controller;
        this.initComponents();
        this.configureJFrame();
        this.addUIComponents();
        this.setVisible(true);
        this.pack();
        this.setSize(500, 500);
    }

    // METODOS
    // metodo que añade las componentes del JFrame
    private void addUIComponents() {
        Container panel;

        panel = this.getContentPane();
        this.addComponents(panel);
    }

    // metood que configura la estructura del JFrame
    private void configureJFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.addComponentListener(this);
    }

    // metodo que une todo para gestionar la union entre todas las clases
    private void addComponents(Container panel) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        // LABELS
        c.gridx = 1;
        c.gridy = 0;
        panel.add(this.contador, c);

        c.gridx = 1;
        c.gridy = 1;
        panel.add(this.consumidoresConsumidos, c);

        c.gridx = 1;
        c.gridy = 2;
        panel.add(this.consumidoresConsumiendio, c);

        c.gridx = 1;
        c.gridy = 3;
        panel.add(this.productoresProducidos, c);

        c.gridx = 1;
        c.gridy = 4;
        panel.add(this.productoresProduciendo, c);

        c.gridx = 1;
        c.gridy = 5;
        panel.add(this.tiempoTotal, c);

        c.gridx = 2;
        c.gridy = 0;
        panel.add(this.tiempoConsumidorAleatorio, c);

        c.gridx = 3;
        panel.add(this.tiempoConsumidor, c);

        c.gridx = 2;
        c.gridy = 1;
        panel.add(this.tiempoProducirAleatorio, c);

        c.gridx = 3;
        panel.add(this.tiempoProducir, c);

        c.gridx = 2;
        c.gridy = 2;
        panel.add(this.textoConsumidores, c);

        c.gridy = 3;
        panel.add(this.textoProductores, c);

        // TEXT FILDS
        c.gridx = 0;
        c.gridy = 0;
        panel.add(this.jb, c);

        c.gridx = 0;
        c.gridy = 1;
        panel.add(this.textoConsumidoresConsumidos, c);

        c.gridx = 0;
        c.gridy = 2;
        panel.add(this.textoConsumidoresConsumiendo, c);

        c.gridx = 0;
        c.gridy = 3;
        panel.add(this.textoProductoresProducidos, c);

        c.gridx = 0;
        c.gridy = 4;
        panel.add(this.textoProductoresProduciendo, c);

        c.gridx = 0;
        c.gridy = 5;
        panel.add(this.textoTiempoTotal, c);

        c.gridx = 3;
        c.gridy = 2;
        panel.add(this.numProductores, c);

        c.gridy = 3;
        panel.add(this.numConsumidores, c);
    }

    private void initComponents() {
        this.jb = new JButton("IniciarHilos");
        this.jb.setFont(new Font("Arial", Font.BOLD, 16));

        Dimension d = new Dimension(150, 25);
        this.jb.setPreferredSize(d);
        this.jb.addActionListener(this);

        this.contador = new JTextField();
        this.contador.setPreferredSize(d);

        // JToggles
        this.tiempoConsumidorAleatorio = new JToggleButton();
        this.tiempoConsumidorAleatorio.setPreferredSize(d);
        this.tiempoConsumidorAleatorio.setText("Consumir Aleatoriamente");

        this.tiempoProducirAleatorio = new JToggleButton();
        this.tiempoProducirAleatorio.setPreferredSize(d);
        this.tiempoProducirAleatorio.setText("Producir Aleatoriamente");

        // JSlidder
        this.tiempoConsumidor = new JSlider();
        this.tiempoConsumidor.setPreferredSize(d);

        this.tiempoProducir = new JSlider();
        this.tiempoProducir.setPreferredSize(d);

        // TextFields
        // Consumidores
        this.consumidoresConsumidos = new JTextField();
        this.consumidoresConsumidos.setPreferredSize(d);

        this.consumidoresConsumiendio = new JTextField();
        this.consumidoresConsumiendio.setPreferredSize(d);

        this.numConsumidores = new JTextField();
        this.numConsumidores.setPreferredSize(d);

        // Productores
        this.productoresProducidos = new JTextField();
        this.productoresProducidos.setPreferredSize(d);

        this.productoresProduciendo = new JTextField();
        this.productoresProduciendo.setPreferredSize(d);

        this.numProductores = new JTextField();
        this.numProductores.setPreferredSize(d);

        // Tiempo total
        this.tiempoTotal = new JTextField();
        this.tiempoTotal.setPreferredSize(d);

        // Tiempo Create Threads
        this.tiempoTotalCreateThreads = new JTextField();
        this.tiempoTotalCreateThreads.setPreferredSize(d);

        this.tiempoMedioCreateThreads = new JTextField();
        this.tiempoMedioCreateThreads.setPreferredSize(d);

        // Tiempo Start Threads
        this.tiempoTotalStartThreads = new JTextField();
        this.tiempoTotalStartThreads.setPreferredSize(d);

        this.tiempoMedioStartThreads = new JTextField();
        this.tiempoMedioStartThreads.setPreferredSize(d);

        // Labels
        // Consumidores
        this.textoConsumidoresConsumidos = new JLabel();
        this.textoConsumidoresConsumidos.setPreferredSize(d);
        this.textoConsumidoresConsumidos.setText("Consumidores Consumidos");

        this.textoConsumidoresConsumiendo = new JLabel();
        this.textoConsumidoresConsumiendo.setPreferredSize(d);
        this.textoConsumidoresConsumiendo.setText("Consumidores Consumiendo");

        this.textoConsumidores = new JLabel();
        this.textoConsumidores.setPreferredSize(d);
        this.textoConsumidores.setText("Numero Consumidores");

        // Productores
        this.textoProductoresProducidos = new JLabel();
        this.textoProductoresProducidos.setPreferredSize(d);
        this.textoProductoresProducidos.setText("Productores Producidos");

        this.textoProductoresProduciendo = new JLabel();
        this.textoProductoresProduciendo.setPreferredSize(d);
        this.textoProductoresProduciendo.setText("Productores Produciendo");

        this.textoProductores = new JLabel();
        this.textoProductores.setPreferredSize(d);
        this.textoProductores.setText("Numero Productores");

        // Tiempo Total
        this.textoTiempoTotal = new JLabel();
        this.textoTiempoTotal.setPreferredSize(d);
        this.textoTiempoTotal.setText("Tiempo Total");

        // Tiempos de Crear Threads
        this.tiemposCreateThreads = new JLabel();
        this.tiemposCreateThreads.setPreferredSize(d);
        this.tiemposCreateThreads.setText("Tiempo de Crear Threads");

        this.textoTiempoTotalCreateThreads = new JLabel();
        this.textoTiempoTotalCreateThreads.setPreferredSize(d);
        this.textoTiempoTotalCreateThreads.setText("Tiempo Total de Crear Threads");
        ;

        this.textoTiempoMedioCreateThreads = new JLabel();
        this.textoTiempoMedioCreateThreads.setPreferredSize(d);
        this.textoTiempoMedioCreateThreads.setText("Tiempo Medio de Crear Threads");

        // Tiempo de Start Threads
        this.tiemposStartThreads = new JLabel();
        this.tiemposStartThreads.setPreferredSize(d);
        this.tiemposStartThreads.setText("Tiempo de Start Threads");

        this.textoTiempoTotalStartThreads = new JLabel();
        this.textoTiempoTotalStartThreads.setPreferredSize(d);
        this.textoTiempoTotalStartThreads.setText("Tiempo total de Empezar Threads");

        this.textoTiempoMedioStartThreads = new JLabel();
        this.textoTiempoMedioStartThreads.setPreferredSize(d);
        this.textoTiempoMedioStartThreads.setText("Tiempo Medio de Empezar Threads");
    }

    // metodos que se encargan de los implements
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        switch (str) {

            case "IniciarHilos":

                controller.start();
                break;
            default:
        }
    }

    @Override
    public void componentHidden(ComponentEvent ce) {
        // System.out.println("Frame hidden");
    }

    @Override
    public void componentMoved(ComponentEvent ce) {
        // System.out.println("Frame moved");
    }

    @Override
    public void componentResized(ComponentEvent ce) {
        // System.out.println("Frame resized");
    }

    @Override
    public void componentShown(ComponentEvent ce) {
        // System.out.println("Frame Shown");
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        int estado = itemEvent.getStateChange();
        if (estado == ItemEvent.SELECTED) {
        } else {
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                contador.setText(controller.getCm().getValue() + "");
                productoresProducidos.setText(controller.getCm().getProductoresAcabados() + "");
                productoresProduciendo.setText(controller.getCm().getProductoresTraballando() + "");
                consumidoresConsumidos.setText(controller.getCm().getConsumidoresConsumidos() + "");
                consumidoresConsumiendio.setText(controller.getCm().getConsumidoresConsumiendo() + "");
                tiempoTotal.setText(String.valueOf(controller.getCm().getTiempoTotal()));
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}