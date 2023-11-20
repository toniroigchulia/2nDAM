import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ControlPanel extends JPanel{
    
    private JToggleButton playPause;
    private JButton reiniciar;
    private JToggleButton protRegCrit;
    private JToggleButton stockPositive;

    public ControlPanel() {
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

        playPause = new JToggleButton();
        playPause.setText("Play / Pause");
        this.add(playPause, c);

        c.gridy++;
        reiniciar = new JButton();
        reiniciar.setText("Reiniciar");
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
}
