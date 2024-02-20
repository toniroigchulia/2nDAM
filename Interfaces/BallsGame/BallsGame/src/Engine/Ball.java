package Engine;
import static java.lang.Thread.sleep;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.Vector;

public class Ball implements VisualObject, Runnable, Serializable{
    private Vector<Integer> nextPosition = new Vector<>();
    private Vector<Integer> position = new Vector<>();
    private Vector<Float> velocity = new Vector<>();
    private boolean bounceInmunity;
    private boolean alive = true;
    private transient TGModel model;
    private int rad = 30;
    private int mass = 10;
    
    public Ball(TGModel model, Vector<Integer> velocity, Vector<Integer> position) {
        this.velocity = simplifyVelocity(velocity);
        this.position = position;
        this.model = model;
    }
    
    private Vector<Float> simplifyVelocity(Vector<Integer> ballVelocity) {
        Vector<Float> vectorFloat = new Vector<>();
        int x = ballVelocity.get(0);
        int y = ballVelocity.get(1);

        float normalizedX = (float) (x / 25);
        float normalizedY = (float) (y / 25);

        vectorFloat.add(normalizedX);
        vectorFloat.add(normalizedY);

        return vectorFloat;
    }
    
    public void checkNextMove(){
        this.getNextPosition();
        
        this.model.checkBallMovement(this);
    }
    
    @Override
    public void run() {
        nextPosition.add(Math.round(position.get(0) + (velocity.get(0))));
        nextPosition.add(Math.round(position.get(1) + (velocity.get(1))));
        
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
    public void bounce(Vector<Integer> bouncePosition, int bounceDirection) {
        position.set(0, bouncePosition.get(0));
        position.set(1, bouncePosition.get(1));
        
        if(bounceDirection == 0){
            
            this.velocity.set(0, this.velocity.get(0) * (-1));
        } else {
            
            this.velocity.set(1, this.velocity.get(1) * (-1));
        }
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
    
    public synchronized Vector<Integer> getNextPosition() {
        nextPosition.set(0, Math.round(position.get(0) + (velocity.get(0))));
        nextPosition.set(1, Math.round(position.get(1) + (velocity.get(1))));
        
        return nextPosition;
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

    public void setNextPosition(Vector<Integer> nextPosition) {
        this.nextPosition = nextPosition;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public boolean isBounceInmunity() {
        return bounceInmunity;
    }

    public void setBounceInmunity(boolean bounceInmunity) {
        this.bounceInmunity = bounceInmunity;
    }
}
