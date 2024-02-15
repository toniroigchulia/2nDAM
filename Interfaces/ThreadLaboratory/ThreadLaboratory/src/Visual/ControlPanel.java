package Visual;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ControlPanel extends JPanel{
    
    private ActionListener actionListener;
    private JButton play;
    private JButton pause;
    private JButton aplicar;
    private JButton reiniciar;
    private JToggleButton protRegCrit;
    private JToggleButton stockPositive;

    public ControlPanel(ActionListener listener) {
        this.actionListener = listener;
        this.setLayout(new GridBagLayout());
        addContentToPane();
    }

    private void addContentToPane() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;

        play = new JButton();
        play.setText("Play");
        play.addActionListener(actionListener);
        this.add(play, c);

        c.gridy++;
        pause = new JButton();
        pause.setText("Pause");
        pause.addActionListener(actionListener);
        this.add(pause, c);

        c.gridy++;
        aplicar = new JButton();
        aplicar.setText("Aplicar");
        aplicar.addActionListener(actionListener);
        this.add(aplicar, c);

        c.gridy++;
        reiniciar = new JButton();
        reiniciar.setText("Reiniciar");
        reiniciar.addActionListener(actionListener);
        this.add(reiniciar, c);

        c.gridy++;
        protRegCrit = new JToggleButton();
        protRegCrit.setText("ProtRegCrit");
        this.add(protRegCrit, c);

        c.gridy++;
        stockPositive = new JToggleButton();
        stockPositive.setText("StockPositivo");
        this.add(stockPositive, c);
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public JButton getPlay() {
        return play;
    }

    public void setPlay(JButton play) {
        this.play = play;
    }

    public JButton getPause() {
        return pause;
    }

    public void setPause(JButton pause) {
        this.pause = pause;
    }

    public JButton getAplicar() {
        return aplicar;
    }

    public void setAplicar(JButton aplicar) {
        this.aplicar = aplicar;
    }

    public JButton getReiniciar() {
        return reiniciar;
    }

    public void setReiniciar(JButton reiniciar) {
        this.reiniciar = reiniciar;
    }

    public JToggleButton getProtRegCrit() {
        return protRegCrit;
    }

    public void setProtRegCrit(JToggleButton protRegCrit) {
        this.protRegCrit = protRegCrit;
    }

    public JToggleButton getStockPositive() {
        return stockPositive;
    }

    public void setStockPositive(JToggleButton stockPositive) {
        this.stockPositive = stockPositive;
    }
}
