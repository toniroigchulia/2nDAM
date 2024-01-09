package Engine;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class TGViewer extends JFrame implements MouseListener {
    private TGController controller;
    private ControlPanel controlPanel;
    private VisualPanel visualPanel;
    private int xSize = 920;
    private int ySize = 720;
    
    private Vector<Integer> ballVelocity;
    private Vector<Integer> ballInitPosition;

    public TGViewer(TGController controller) {
        this.controlPanel = new ControlPanel();
        this.controller = controller;
        
        this.visualPanel = new VisualPanel(this);
        Thread threadVisual = new Thread(this.visualPanel);
        threadVisual.start();

        configureFrame();
    }
    
    private void configureFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        this.setTitle("Balls Game");
        this.addMouseListener(this);
        this.setSize(xSize, ySize);
        this.setVisible(true);
        this.addUIComponents();
    }

    private void addUIComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        this.add(visualPanel, c);
    }
    
    public ArrayList<VisualObject> getVisualElements() {
        return this.controller.getVisualElements();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ballInitPosition = new Vector<>();
        ballInitPosition.add(e.getX());
        ballInitPosition.add(e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ballVelocity = new Vector<>();
        ballVelocity.add(ballInitPosition.get(0) - e.getX());
        ballVelocity.add(ballInitPosition.get(1) - e.getY());
        
        this.controller.addBall(ballVelocity, ballInitPosition);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
