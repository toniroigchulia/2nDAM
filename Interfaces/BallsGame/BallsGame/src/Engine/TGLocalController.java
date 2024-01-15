package Engine;

import java.util.ArrayList;
import java.util.Vector;

public class TGLocalController {
    private TGModel tgModel;
    private TGViewer tgViewer;
    private GameRules rules;

    public TGLocalController() {
        this.tgModel = new TGModel(this);
        this.tgViewer = new TGViewer(this);
        this.rules = new GameRules(this);
    }

    public synchronized void checkBallColison(Ball mainBall, ArrayList<Ball> ballsColiding) {
        // Colision contra otras bolas
        if (ballsColiding.size() > 0) {
            for (int i = 0; i < ballsColiding.size(); i++) {
                ballToBallBounce(mainBall, ballsColiding.get(i));
            }
        }

        // Colision contra bordes
        if (borderBounce(mainBall)) {
            mainBall.checkNextMove();
        }

        mainBall.move();
    }

    private void ballToBallBounce(Ball pelota1, Ball pelota2) {
        Vector<Float> velocidadRelativa = new Vector<>();
        velocidadRelativa.add(0, pelota2.getVelocity().get(0) - pelota1.getVelocity().get(0));
        velocidadRelativa.add(1, pelota2.getVelocity().get(1) - pelota1.getVelocity().get(1));

        double distanciaX = pelota2.getNextPosition().get(0) - pelota1.getNextPosition().get(0);
        double distanciaY = pelota2.getNextPosition().get(1) - pelota1.getNextPosition().get(1);
        double distancia = Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);

        double normalX = distanciaX / distancia;
        double normalY = distanciaY / distancia;

        double velocidadRelativaEnNormal = velocidadRelativa.get(0) * normalX + velocidadRelativa.get(1) * normalY;

        double cambioVelocidadPelota1X = 2 * velocidadRelativaEnNormal * normalX;
        double cambioVelocidadPelota1Y = 2 * velocidadRelativaEnNormal * normalY;

        double cambioVelocidadPelota2X = velocidadRelativa.get(0) - cambioVelocidadPelota1X;
        double cambioVelocidadPelota2Y = velocidadRelativa.get(1) - cambioVelocidadPelota1Y;

        pelota1.getVelocity().set(0, (float) (pelota1.getVelocity().get(0) + cambioVelocidadPelota1X));
        pelota1.getVelocity().set(1, (float) (pelota1.getVelocity().get(1) + cambioVelocidadPelota1Y));

        pelota2.getVelocity().set(0,(float) (pelota2.getVelocity().get(0) + cambioVelocidadPelota2X));
        pelota2.getVelocity().set(1,(float) (pelota2.getVelocity().get(1) + cambioVelocidadPelota2Y));
    }


    private boolean borderBounce(Ball mainBall) {
        // Colision contra bordes
        Vector<Integer> canvaSize = this.getCanvasSize();
        Vector<Integer> bouncePosition = new Vector<>();
        // Bordes Laterales
        if (mainBall.getNextPosition().get(0) <= 0) {

            if (!this.rules.checkGate()) {

                bouncePosition.add(0);
                bouncePosition.add(mainBall.getNextPosition().get(1));

                mainBall.bounce(bouncePosition, 0);
            } else {

                removeBall(mainBall);
            }

            return true;
        } else if (mainBall.getNextPosition().get(0) >= canvaSize.get(0)) {

            if (!this.rules.checkGate()) {

                bouncePosition.add(canvaSize.get(0));
                bouncePosition.add(mainBall.getNextPosition().get(1));

                mainBall.bounce(bouncePosition, 0);
            } else {

                removeBall(mainBall);
            }

            return true;
        }

        // Techo y Suelo
        if (mainBall.getNextPosition().get(1) <= 0) {

            bouncePosition.add(mainBall.getNextPosition().get(0));
            bouncePosition.add(0);

            mainBall.bounce(bouncePosition, 1);

            return true;
        } else if (mainBall.getNextPosition().get(1) >= canvaSize.get(1)) {

            bouncePosition.add(mainBall.getNextPosition().get(0));
            bouncePosition.add(canvaSize.get(1));

            mainBall.bounce(bouncePosition, 1);

            return true;
        }

        return false;
    }

    public Vector<Integer> getCanvasSize() {
        Vector<Integer> canvaSize = new Vector<>();
        canvaSize.add(this.tgViewer.getxSize());
        canvaSize.add(this.tgViewer.getySize());
        return canvaSize;
    }

    public void addBall(Vector<Integer> ballVelocity, Vector<Integer> ballInitPosition) {
        this.tgModel.addBall(ballVelocity, ballInitPosition);
    }

    public void addBall(Ball ball) {
        this.tgModel.addBall(ball);
    }

    public void removeBall(Ball ball) {
        this.tgModel.removeBall(ball);
    }

    public ArrayList<Ball> getVisualElements() {
        return this.tgModel.getVisualElements();
    }
}
