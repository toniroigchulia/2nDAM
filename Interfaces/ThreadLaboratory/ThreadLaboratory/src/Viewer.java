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
import javax.swing.JFrame;

public class Viewer extends JFrame implements ComponentListener, ActionListener, ItemListener, Runnable{
    private LaboratoryController controller;
    
    private DTOLabParameters labParameters;
    
    private ControlPanel controlPanel;
    private ParameterPanel parameterPanel;
    private ResultatPanel resultatPanel;
    
    // METODOS CONSTRUCTORES
    public Viewer(LaboratoryController controller) {
        this.controller = controller;
        this.configureJFrame();
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
        this.setSize(920, 520);
        this.setVisible(true);
        this.addUIComponents();
    }

    // metodo que une todo para gestionar la union entre todas las clases
    private void addComponents(Container panel) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;

        c.gridy = 0;
        c.gridx = 0;
        this.controlPanel = new ControlPanel();
        panel.add(this.controlPanel, c);

        c.gridx = 1;
        this.resultatPanel = new ResultatPanel();
        panel.add(this.resultatPanel, c);

        c.gridx = 2;
        this.parameterPanel = new ParameterPanel();
        panel.add(this.parameterPanel, c);
    }

    @Override
    public void run() {
        try {
            while(true){
                this.resultatPanel.setLabResults(this.controller.getResults());
                this.controller.applyConfig(this.labParameters);
                Thread.sleep(50);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'itemStateChanged'");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    @Override
    public void componentResized(ComponentEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'componentResized'");
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'componentMoved'");
    }

    @Override
    public void componentShown(ComponentEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'componentShown'");
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        // throw new UnsupportedOperationException("Unimplemented method 'componentHidden'");
    }
}
