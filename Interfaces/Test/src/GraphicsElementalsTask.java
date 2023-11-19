/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicselementalstask;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import static java.lang.Thread.sleep;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToggleButton;


public class GraphicsElementalsTask extends JFrame implements ComponentListener, ActionListener, ItemListener {

    private JButton bForeground;
    private JButton bBackground;
    private JToggleButton tbPlay;
    private Viewer viewer;
    private MyAnimation animation;


    /**
     * Mètodes estàtics
     */
    public static void main(String[] args) {
        GraphicsElementalsTask et;

        et = new GraphicsElementalsTask();
        et.playAnimation();
    }


    /**
     * Constructors
     */
    public GraphicsElementalsTask() {
        this.initClass();
        this.configureJFrame();
        this.addUIComponents();
        this.setVisible(true);
        this.pack();
    }


    /**
     * Mètodes públics
     */
    public void playAnimation() {
        while (true) {
            if (this.tbPlay.isSelected()) {
                this.viewer.paintBackground();
                this.viewer.paintForegroundImage();
            }
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }
    }


    /**
     * Mètodes privats
     */
    private void addButtonsToPane(Container panel) {
        GridBagConstraints c = new GridBagConstraints();

        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.gridheight = 1;
        c.gridwidth = 1;

        this.bForeground = new JButton("Mostrar Foreground");
        this.bForeground.addActionListener(this);
        panel.add(this.bForeground, c);

        c.anchor = GridBagConstraints.NORTHWEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 1;

        this.bBackground = new JButton("Mostrar Background");
        this.bBackground.addActionListener(this);
        panel.add(this.bBackground, c);

        c.gridy = 2;
        this.tbPlay = new JToggleButton("Play");
        this.tbPlay.addActionListener(this);
        panel.add(this.tbPlay, c);
    }


    public void addUIComponents() {
        Container panel;

        panel = this.getContentPane();
        this.addViewerToPane(panel);
        this.addButtonsToPane(panel);
    }


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

    private void initClass() {
        this.animation = new MyAnimation(100, 100);
    }

    private void configureJFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.addComponentListener(this);
    }


    /**
     * Mètodes sobreescrits
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        switch (str) {
            case "Mostrar Foreground":
                this.viewer.paintForegroundImage();
                break;
            case "Mostrar Background":
                this.viewer.paintBackground();
                break;
            case "Play":
                this.viewer.paintBackground();
                break;
            default:
                System.err.println("Acción NO tratada: " + e);
        }
    }


    @Override
    public void componentHidden(ComponentEvent ce) {
        //System.out.println("Frame hidden");
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
}
