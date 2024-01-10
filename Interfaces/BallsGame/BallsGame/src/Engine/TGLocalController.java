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
        borderBounce(mainBall);

        mainBall.move();
    }

    private void ballToBallBounce(Ball mainBall, Ball otherBall) {
        Vector<Float> mainVelocity = new Vector<>();
        Vector<Float> otherVelocity = new Vector<>();

        mainVelocity.add(0, (float) (mainBall.getVelocity().get(0) - (otherBall.getVelocity().get(0))));
        mainVelocity.add(1, (float) (mainBall.getVelocity().get(1) - (otherBall.getVelocity().get(0))));

        otherVelocity.add(0, (float) (otherBall.getVelocity().get(0) + (mainBall.getVelocity().get(0))));
        otherVelocity.add(1, (float) (otherBall.getVelocity().get(1) + (mainBall.getVelocity().get(1))));

        if (mainVelocity.get(0) > 0 && otherVelocity.get(0) > 0) {
            mainVelocity.set(0, mainVelocity.get(0) * (-1));
        }

        if (mainVelocity.get(1) > 0 && otherVelocity.get(1) > 0) {
            mainVelocity.set(1, mainVelocity.get(1) * (-1));
        }

        mainBall.setVelocity(mainVelocity);
        otherBall.setVelocity(otherVelocity);
    }

    private void borderBounce(Ball mainBall) {
        // Colision contra bordes
        Vector<Integer> canvaSize = this.getCanvasSize();

        // Bordes Laterales
        if (mainBall.getNextPosition().get(0) < 0) {

            if (!this.rules.checkGate()) {

                mainBall.getVelocity().set(0, (mainBall.getVelocity().get(0)) * (-1));
            } else {

                removeBall(mainBall);
            }
        } else if (mainBall.getNextPosition().get(0) > canvaSize.get(0)) {

            if (!this.rules.checkGate()) {

                mainBall.getVelocity().set(0, (mainBall.getVelocity().get(0)) * (-1));
            } else {

                removeBall(mainBall);
            }
        }

        // Techo y Suelo
        if (mainBall.getNextPosition().get(1) < 0) {

            if (!this.rules.checkGate()) {

                mainBall.getVelocity().set(1, (mainBall.getVelocity().get(1)) * (-1));
            } else {

                removeBall(mainBall);
            }
        } else if (mainBall.getNextPosition().get(1) > canvaSize.get(1)) {

            if (!this.rules.checkGate()) {

                mainBall.getVelocity().set(1, (mainBall.getVelocity().get(1)) * (-1));
            } else {

                removeBall(mainBall);
            }
        }
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
