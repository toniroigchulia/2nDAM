package Engine;
import javax.swing.JFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class TGViewer extends JFrame implements MouseListener {
    private TGLocalController controller;
    private ControlPanel controlPanel;
    private VisualPanel visualPanel;
    private int xSize = 920;
    private int ySize = 720;
    
    private Vector<Integer> ballVelocity;
    private Vector<Integer> ballInitPosition;

    public TGViewer(TGLocalController controller) {
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
    
    public ArrayList<Ball> getVisualElements() {
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
        
        if(!((ballVelocity.get(0) == 0) && (ballVelocity.get(1) == 0))){
        
            this.controller.addBall(ballVelocity, ballInitPosition);
        }
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

    public TGLocalController getController() {
        return controller;
    }

    public void setController(TGLocalController controller) {
        this.controller = controller;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public VisualPanel getVisualPanel() {
        return visualPanel;
    }

    public void setVisualPanel(VisualPanel visualPanel) {
        this.visualPanel = visualPanel;
    }

    public int getxSize() {
        return xSize;
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    public int getySize() {
        return ySize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }

    public Vector<Integer> getBallVelocity() {
        return ballVelocity;
    }

    public void setBallVelocity(Vector<Integer> ballVelocity) {
        this.ballVelocity = ballVelocity;
    }

    public Vector<Integer> getBallInitPosition() {
        return ballInitPosition;
    }

    public void setBallInitPosition(Vector<Integer> ballInitPosition) {
        this.ballInitPosition = ballInitPosition;
    }
}
