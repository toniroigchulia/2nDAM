package Engine;

import java.util.ArrayList;
import java.util.Vector;

public class TGModel {
    private TGLocalController controller;
    private ArrayList<Ball> visualElements = new ArrayList<>();

    public TGModel(TGLocalController controller) {
        this.controller = controller;
    }

    public void addBall(Vector<Integer> ballVelocity, Vector<Integer> ballInitPosition) {
        Ball ball = new Ball(this, ballVelocity, ballInitPosition);
        this.visualElements.add(ball);

        new Thread(ball).start();
    }

    public void addBall(Ball ball) {
        this.visualElements.add(ball);

        new Thread(ball).start();
    }

    public void removeBall(Ball ball) {
        ball.setAlive(false);
        for (int i = 0; i < visualElements.size(); i++) {
            if (ball == visualElements.get(i)) {
                visualElements.remove(i);
            }
        }
    }

    public synchronized void checkBallMovement(Ball ball) {
        ArrayList<Ball> ballsColiding = new ArrayList<>();
        ball.setBounceInmunity(false);

        for (int i = 0; i < visualElements.size(); i++) {
            if ((isColiding(ball, visualElements.get(i))) && ball != visualElements.get(i) && !visualElements.get(i).isBounceInmunity()) {
                ball.setBounceInmunity(true);
                ballsColiding.add(visualElements.get(i));
            }
        }
        
        this.controller.checkBallColison(ball, ballsColiding);
    }

    private boolean isColiding(Ball mainBall, Ball possibleColisionBall) {
        boolean impacto;
        int distanciaCentros = calcColision(mainBall.getNextPosition(), possibleColisionBall.getNextPosition());
        int sumaRadios = mainBall.getRad() + possibleColisionBall.getRad();
        impacto = distanciaCentros <= sumaRadios;
        return impacto;
    }

    private int calcColision(Vector<Integer> mainBallPosition, Vector<Integer> possibleColisionBallPosition) {
        int distanciaX = mainBallPosition.get(0) - possibleColisionBallPosition.get(0);
        int distanciaY = mainBallPosition.get(1) - possibleColisionBallPosition.get(1);

        int distanciaEntreCentros = (int) (Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY) + 20);

        return distanciaEntreCentros;
    }
    
    public ArrayList<Ball> getVisualElements() {
        return this.visualElements;
    }
}
