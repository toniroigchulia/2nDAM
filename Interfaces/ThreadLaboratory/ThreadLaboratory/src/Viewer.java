import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Viewer extends JFrame implements Runnable {
    private LaboratoryController controller;

    private DTOLabParameters labParameters;
    private ActionListener listener;
    private ControlPanel controlPanel;
    private ParameterPanel parameterPanel;
    private Thread parameterThread;
    private ResultatPanel resultatPanel;

    // METODOS CONSTRUCTORES
    public Viewer(LaboratoryController controller) {
        this.controller = controller;
        this.labParameters = new DTOLabParameters();
        this.listener = new Listener();
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
        this.setVisible(true);
        this.addUIComponents();
        this.setSize(920, 520);
    }

    // metodo que une todo para gestionar la union entre todas las clases
    private void addComponents(Container panel) {
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;

        c.gridy = 0;
        c.gridx = 0;
        this.controlPanel = new ControlPanel(listener);
        panel.add(this.controlPanel, c);

        c.gridx = 1;
        this.resultatPanel = new ResultatPanel();
        this.parameterThread = new Thread(this.resultatPanel);
        this.parameterThread.start();
        panel.add(this.resultatPanel, c);

        c.gridx = 2;
        this.parameterPanel = new ParameterPanel(new DTOLabParameters());
        panel.add(this.parameterPanel, c);
    }

    @Override
    public void run() {
        try {
            while (true) {
                this.resultatPanel.setLabResults(this.controller.getResults());
                Thread.sleep(50);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            switch (str) {
                case "Play":
                    labParameters.setPlaying(true);
                    controller.getModel().start();

                    controller.applyConfig(labParameters);
                    //System.out.println("Play");
                    break;
                case "Pause":
                    labParameters.setPlaying(false);

                    controller.applyConfig(labParameters);
                    //System.out.println("Pause");
                    break;
                case "Aplicar":
                    labParameters.setProductores(Integer.parseInt(getParameterPanel().getTextProductors().getText()));
                    labParameters.setQuantityItemsP(
                            Integer.parseInt(getParameterPanel().getTextQuanItemProductor().getText()));
                    labParameters.setTempsMaximCheckBoxP(getParameterPanel().getTempsProduccio().isSelected());
                    labParameters.setTempsMaximP(
                            Integer.parseInt(getParameterPanel().getTextTempsMaximProduccio().getText()));

                    labParameters.setConsumidores(Integer.parseInt(getParameterPanel().getTextConsumidors().getText()));
                    labParameters.setQuantityItemsC(
                            Integer.parseInt(getParameterPanel().getTextQuantItemConsumidor().getText()));
                    labParameters.setTempsMaximCheckBoxC(getParameterPanel().getTempsConsumir().isSelected());
                    labParameters.setTempsMaximC(
                            Integer.parseInt(getParameterPanel().getTextTempsMaximConsumir().getText()));

                    labParameters.setProtegerRegCritic(getControlPanel().getProtRegCrit().isSelected());
                    labParameters.setStockPositivo(getControlPanel().getStockPositive().isSelected());

                    controller.applyConfig(labParameters);
                    // System.out.println("Aplicar");
                    break;
                case "Reiniciar":

                    getParameterPanel().setLabParameters(new DTOLabParameters());
                    getParameterPanel().setDefaultFieldsValues();
                    controller.applyConfig(labParameters);
                    // System.out.println("Reiniciar");
                    break;
                default:
                    System.err.println("Acción NO tratada: " + e);
            }
        }
    }

    public LaboratoryController getController() {
        return controller;
    }

    public void setController(LaboratoryController controller) {
        this.controller = controller;
    }

    public DTOLabParameters getLabParameters() {
        return labParameters;
    }

    public void setLabParameters(DTOLabParameters labParameters) {
        this.labParameters = labParameters;
    }

    public ActionListener getListener() {
        return listener;
    }

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public ParameterPanel getParameterPanel() {
        return parameterPanel;
    }

    public void setParameterPanel(ParameterPanel parameterPanel) {
        this.parameterPanel = parameterPanel;
    }

    public ResultatPanel getResultatPanel() {
        return resultatPanel;
    }

    public void setResultatPanel(ResultatPanel resultatPanel) {
        this.resultatPanel = resultatPanel;
    }
}
