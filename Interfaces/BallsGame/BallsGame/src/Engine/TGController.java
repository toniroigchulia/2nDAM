package Engine;

import java.util.ArrayList;
import java.util.Vector;

public class TGController {
    private TGModel tgModel;
    private TGViewer tgViewer;
    
    public TGController() {
        this.tgModel = new TGModel(this);
        this.tgViewer = new TGViewer(this);
    }
    
    public void addBall(Vector<Integer> ballVelocity, Vector<Integer> ballInitPosition) {
        this.tgModel.addBall(ballVelocity, ballInitPosition);
    }
    
    public void removeBall() {
    
    }
    
    public boolean checkBallMovement() {
        
        return true;
    }
    
    public ArrayList<VisualObject> getVisualElements(){
        return this.tgModel.getVisualElements();
    }
}
