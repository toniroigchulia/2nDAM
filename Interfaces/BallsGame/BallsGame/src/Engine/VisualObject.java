package Engine;

import java.awt.Graphics;
import java.util.Vector;

public interface VisualObject {
    public Graphics paint(Graphics g);
    
    public void move();
    
    public void bounce(Vector<Integer> bouncePosition, int bounceDirection);
    
    public void kill();
}
