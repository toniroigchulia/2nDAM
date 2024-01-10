package Engine;
import static java.lang.Thread.sleep;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class Ball implements VisualObject, Runnable{
    private Vector<Integer> position = new Vector<>();
    private Vector<Integer> nextPosition = new Vector<>();
    private Vector<Float> velocity = new Vector<>();
    private boolean alive = true;
    private TGModel model;
    private int rad = 20;
    
    public Ball(TGModel model, Vector<Integer> velocity, Vector<Integer> position) {
        this.velocity = simplifyVelocity(velocity);
        this.position = position;
        this.model = model;
    }
    
    private Vector<Float> simplifyVelocity(Vector<Integer> ballVelocity) {
        Vector<Float> vectorFloat = new Vector<>();
        int x = ballVelocity.get(0);
        int y = ballVelocity.get(1);

        float normalizedX = (float) (x / 20);
        float normalizedY = (float) (y / 20);

        vectorFloat.add(normalizedX);
        vectorFloat.add(normalizedY);

        return vectorFloat;
    }
    
    private void checkNextMove(){
        this.nextPosition = new Vector<>();
        nextPosition.add(Math.round(position.get(0) + (velocity.get(0))));
        nextPosition.add(Math.round(position.get(1) + (velocity.get(1))));
        
        this.model.checkBallMovement(this);
    }
    
    @Override
    public void run() {
        while (alive) {
            try {
                sleep(20);
                checkNextMove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void move() {
        position.set(0, this.nextPosition.get(0));
        position.set(1, this.nextPosition.get(1));
    }
    
    @Override
    public Graphics paint(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(this.position.get(0) - rad, this.position.get(1) - rad, rad, rad);
        return g;
    }

    @Override
    public void kill() {
    }

    @Override
    public void bounce() {
    }

    public Vector<Integer> getPosition() {
        return position;
    }

    public void setPosition(Vector<Integer> position) {
        this.position = position;
    }

    public int getRad() {
        return rad;
    }

    public void setRad(int rad) {
        this.rad = rad;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public TGModel getModel() {
        return model;
    }

    public void setModel(TGModel model) {
        this.model = model;
    }

    public Vector<Float> getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector<Float> velocity) {
        this.velocity = velocity;
    }

    public Vector<Integer> getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition(Vector<Integer> nextPosition) {
        this.nextPosition = nextPosition;
    }
}
