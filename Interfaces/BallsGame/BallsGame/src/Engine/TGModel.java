package Engine;

import java.util.ArrayList;
import java.util.Vector;

public class TGModel {
    private TGController controller;
    private ArrayList<VisualObject> visualElements = new ArrayList<>();
    
    public TGModel(TGController controller){
        this.controller = controller;
    }
    
    public void addBall(Vector<Integer> ballVelocity, Vector<Integer> ballInitPosition) {
        Ball ball = new Ball(this, ballVelocity, ballInitPosition);
        this.visualElements.add(ball);
        
        Thread ballThread = new Thread(ball);
        ballThread.start();
    }
    
    public void removeBall() {
        
    }
    
    public boolean checkBallMovement() {
        
        return controller.checkBallMovement();
    }
    
    public ArrayList<VisualObject> getVisualElements(){
        return this.visualElements;
    }
}
