import java.awt.Container;
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
import javax.swing.JToggleButton;
import static java.lang.Thread.sleep;

public class FireTask extends JFrame implements ComponentListener, ActionListener, ItemListener {

    // Main
    public static void main(String[] args) throws Exception {

        FireTask et = new FireTask();
        et.viewer.paintBackground();
        
        et.playAnimation();
    }

    // Atributos
    private JButton bForeground;
    private JButton bBackground;
    private JToggleButton tbPlay;
    private Viewer viewer;
    private FireAnimation animation;

    // Constructor
    public FireTask() {
        
        this.initFire(255, 95);
        this.configureJFrame();
        this.addUIComponents();
        this.setVisible(true);
        this.pack();
    }

    // Empezar animacion del fuego
    public void playAnimation() {
        while (true) {
            if (this.tbPlay.isSelected()) {

                this.viewer.paintFire();
            }
            try {
                sleep(50);
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
    }

    // Metodo para añadir los comopenetes de interfaz al container
    public void addUIComponents() {
        Container panel;

        panel = this.getContentPane();
        this.addViewerToPane(panel);
        this.addButtonsToPane(panel);
    }

    // Grid de los botones
    private void addButtonsToPane(Container panel) {
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.weighty = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 10, 10);

        this.bForeground = new JButton("Mostrar Fire");
        this.bForeground.addActionListener(this);
        panel.add(this.bForeground, c);

        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;

        this.bBackground = new JButton("Mostrar Background");
        this.bBackground.addActionListener(this);
        panel.add(this.bBackground, c);

        c.gridy = 2;
        this.tbPlay = new JToggleButton("Play");
        this.tbPlay.addActionListener(this);
        panel.add(this.tbPlay, c);
    }

    // Grid para la animacion
    private void addViewerToPane(Container panel) {
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 4;
        c.gridwidth = 1;

        this.viewer = new Viewer(512, 512, this.animation);
        panel.add(this.viewer, c);
    }

    // Inicializador de la animacion del fuego
    private void initFire(int w, int h) {
        this.animation = new FireAnimation(w, h);
    }

    // Configurciones del JFrame
    private void configureJFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.addComponentListener(this);
    }

    // Listener de los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        switch (str) {
            case "Mostrar Fire":

                this.viewer.paintFire();
                break;
            case "Mostrar Background":

                this.viewer.paintBackground();
                break;
            case "Play":
                break;
            default:
                System.err.println("Acción NO tratada: " + e);
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
    }
}
