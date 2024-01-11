package Engine;

import java.awt.Graphics;

public interface VisualObject {
    public Graphics paint(Graphics g);
    
    public void move();
    
    public void kill();
    
    public void bounce();
}
