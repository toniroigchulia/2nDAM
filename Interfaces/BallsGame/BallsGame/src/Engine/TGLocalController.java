package Engine;
import java.util.ArrayList;
import java.util.Vector;

import Comunications.PeerLocation;
import MainController.*;

public class TGLocalController {
    private TGModel tgModel;
    private TGViewer tgViewer;
    private GameRules rules;
    private TGController tgController;

    public TGLocalController(TGController controller) {
        this.tgModel = new TGModel(this);
        this.tgViewer = new TGViewer(this);
        this.rules = new GameRules(this);
        this.tgController = controller;
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

    private void ballToBallBounce(Ball mainBall, Ball otherBall) {
        // Si tienen direcciones contrarias
        if ((mainBall.getVelocity().get(0) > 0 && otherBall.getVelocity().get(0) < 0)
                || (mainBall.getVelocity().get(0) < 0 && otherBall.getVelocity().get(0) > 0)) {

            mainBall.getVelocity().set(0, mainBall.getVelocity().get(0) * (-1));
            otherBall.getVelocity().set(0, otherBall.getVelocity().get(0) * (-1));
        }

        if ((mainBall.getVelocity().get(1) > 0 && otherBall.getVelocity().get(1) < 0)
        || (mainBall.getVelocity().get(1) < 0 && otherBall.getVelocity().get(1) > 0)) {
            
            mainBall.getVelocity().set(1, mainBall.getVelocity().get(1) * (-1));
            otherBall.getVelocity().set(1, otherBall.getVelocity().get(1) * (-1));
        }
        
        //Si las bolas tienen la misma direccion
        if ((mainBall.getVelocity().get(0) > 0 && otherBall.getVelocity().get(0) > 0)
                || (mainBall.getVelocity().get(0) < 0 && otherBall.getVelocity().get(0) < 0)) {

            mainBall.getVelocity().set(0, mainBall.getVelocity().get(0) * (-1));
        }

        if ((mainBall.getVelocity().get(1) > 0 && otherBall.getVelocity().get(1) > 0)
                || (mainBall.getVelocity().get(1) < 0 && otherBall.getVelocity().get(1) < 0)) {

            mainBall.getVelocity().set(1, mainBall.getVelocity().get(1) * (-1));
        }
    }

    private boolean borderBounce(Ball mainBall) {
        // Colision contra bordes
        Vector<Integer> canvaSize = this.getCanvasSize();
        Vector<Integer> bouncePosition = new Vector<>();
        
        // Bordes Laterales
        if (mainBall.getNextPosition().get(0) <= 0) {

            if (!this.rules.checkGate(1)) {

                bouncePosition.add(0);
                bouncePosition.add(mainBall.getNextPosition().get(1));

                mainBall.bounce(bouncePosition, 0);
            } else {

                sendObject(mainBall, PeerLocation.EAST);
            }

            return true;
        } else if (mainBall.getNextPosition().get(0) >= canvaSize.get(0)) {

            if (!this.rules.checkGate(2)) {

                bouncePosition.add(canvaSize.get(0));
                bouncePosition.add(mainBall.getNextPosition().get(1));

                mainBall.bounce(bouncePosition, 0);
            } else {

                sendObject(mainBall, PeerLocation.WEAST);
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

    public void sendObject(Ball ball, Enum<PeerLocation> direc) {
        this.tgController.sendObject(ball, direc);
        this.tgModel.removeBall(ball);
    }
    
    //Getters And Setters
    public ArrayList<Ball> getVisualElements() {
        return this.tgModel.getVisualElements();
    }

    public TGModel getTgModel() {
        return tgModel;
    }

    public void setTgModel(TGModel tgModel) {
        this.tgModel = tgModel;
    }

    public TGViewer getTgViewer() {
        return tgViewer;
    }

    public void setTgViewer(TGViewer tgViewer) {
        this.tgViewer = tgViewer;
    }

    public GameRules getRules() {
        return rules;
    }

    public void setRules(GameRules rules) {
        this.rules = rules;
    }

    public TGController getTgController() {
        return tgController;
    }

    public void setTgController(TGController tgController) {
        this.tgController = tgController;
    }
}
