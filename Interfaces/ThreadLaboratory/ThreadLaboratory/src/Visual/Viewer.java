package Visual;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import DTOS.*;
import Main.*;

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
    // Metodo que añade las componentes del JFrame
    private void addUIComponents() {
        Container panel;

        panel = this.getContentPane();
        this.addComponents(panel);
    }

    // Metood que configura la estructura del JFrame
    private void configureJFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setVisible(true);
        this.addUIComponents();
        this.setSize(920, 520);
    }

    // Metodo que une todo para gestionar la union entre todas las clases
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
                Thread.sleep(50);
                this.resultatPanel.setLabResults(this.controller.getResults());
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
                    controller.modelStart();

                    controller.applyConfig(labParameters);
                    break;
                case "Pause":
                    labParameters.setPlaying(false);

                    controller.applyConfig(labParameters);
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
                    break;
                case "Reiniciar":
                    controller.resetResult();
                    getParameterPanel().setLabParameters(new DTOLabParameters());
                    getParameterPanel().setDefaultFieldsValues();
                    controller.applyConfig(labParameters);
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