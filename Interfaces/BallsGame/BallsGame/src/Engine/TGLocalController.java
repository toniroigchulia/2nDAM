package Engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Comunications.PeerLocation;
import MainController.*;

public class TGLocalController {
    private TGModel tgModel;
    private TGViewer tgViewer;
    private GameRules rules;
    private TGController tgController;


    // CONSTRUCTOR
    public TGLocalController(TGController controller) {
        this.tgModel = new TGModel(this);
        this.tgViewer = new TGViewer(this);
        this.rules = new GameRules(this);
        this.tgController = controller;
    }


    // METODOS
    public void checkBallColison(Ball mainBall, ArrayList<Ball> ballsColiding) {
        // Colision contra otras bolas
        if (ballsColiding.size() > 0) {
            for (int i = 0; i < ballsColiding.size(); i++) {
                ballToBallBounce(mainBall, ballsColiding.get(i));
            }
        }

        // Colision contra bordes
        if (borderBounce(mainBall)) {
            mainBall.move();
        }
    }

    private void ballToBallBounce(Ball mainBall, Ball otherBall) {
        // Si tienen direcciones contrarias
        if ((mainBall.getVelocity().getX() > 0 && otherBall.getVelocity().getX() < 0)
                || (mainBall.getVelocity().getX() < 0 && otherBall.getVelocity().getX() > 0)) {

            mainBall.getVelocity().setX(mainBall.getVelocity().getX() * (-1));
            otherBall.getVelocity().setX(otherBall.getVelocity().getX() * (-1));
        }

        if ((mainBall.getVelocity().getX() > 0 && otherBall.getVelocity().getY() < 0)
                || (mainBall.getVelocity().getY() < 0 && otherBall.getVelocity().getY() > 0)) {

            mainBall.getVelocity().setY(mainBall.getVelocity().getY() * (-1));
            otherBall.getVelocity().setY(otherBall.getVelocity().getY() * (-1));
        }

        // Si las bolas tienen la misma direccion
        if ((mainBall.getVelocity().getX() > 0 && otherBall.getVelocity().getX() > 0)
                || (mainBall.getVelocity().getX() < 0 && otherBall.getVelocity().getX() < 0)) {

            mainBall.getVelocity().setX(mainBall.getVelocity().getX() * (-1));
        }

        if ((mainBall.getVelocity().getY() > 0 && otherBall.getVelocity().getY() > 0)
                || (mainBall.getVelocity().getY() < 0 && otherBall.getVelocity().getY() < 0)) {

            mainBall.getVelocity().setY(mainBall.getVelocity().getY() * (-1));
        }
        
        // Si una de las velocidades es 0
        if (otherBall.getVelocity().getX() == 0) {
            
            mainBall.getVelocity().setX(mainBall.getVelocity().getX() * (-1));
        }
        
        if (otherBall.getVelocity().getY() == 0) {
            
            mainBall.getVelocity().setY(mainBall.getVelocity().getY() * (-1));
        }
    }

    private boolean borderBounce(Ball mainBall) {
        // Colision contra bordes
        Vector<Integer> canvaSize = this.getCanvasSize();
        CoordinatesDTO bouncePosition = new CoordinatesDTO();

        // Bordes Laterales
        if (mainBall.getNextPosition().getX() <= 0) {

            if (!this.rules.checkGate(2)) {

                bouncePosition.setX(0);
                bouncePosition.setY(mainBall.getNextPosition().getY());

                mainBall.bounce(bouncePosition, 0);
                return true;
            } else {

                sendObject(mainBall, PeerLocation.WEST);
                return false;
            }
        } else if (mainBall.getNextPosition().getX() >= canvaSize.get(0)) {

            if (!this.rules.checkGate(1)) {

                bouncePosition.setX(canvaSize.get(0));
                bouncePosition.setY(mainBall.getNextPosition().getY());

                mainBall.bounce(bouncePosition, 0);
                return true;
            } else {

                sendObject(mainBall, PeerLocation.EAST);
                return false;
            }
        }

        // Techo y Suelo
        if (mainBall.getNextPosition().getY() <= 0) {

            bouncePosition.setX(mainBall.getNextPosition().getX());
            bouncePosition.setY(0);

            mainBall.bounce(bouncePosition, 1);

            return true;
        } else if (mainBall.getNextPosition().getY() >= canvaSize.get(1)) {

            bouncePosition.setX(mainBall.getNextPosition().getX());
            bouncePosition.setY(canvaSize.get(1));

            mainBall.bounce(bouncePosition, 1);

            return true;
        }

        return true;
    }

    public Vector<Integer> getCanvasSize() {
        Vector<Integer> canvaSize = new Vector<>();
        canvaSize.add(this.tgViewer.getxSize());
        canvaSize.add(this.tgViewer.getySize());
        return canvaSize;
    }

    public void addBall(VectorDTO ballVelocity, CoordinatesDTO ballInitPosition) {
        this.tgModel.addBall(ballVelocity, ballInitPosition);
    }

    public void addBall(Ball ball) {
        CoordinatesDTO newPosition = new CoordinatesDTO(0, ball.getPosition().getY());
        
        if (ball.getPosition().getX() >= getCanvasSize().get(0)/2) {
            
            newPosition.setX(0);
            ball.setPosition(newPosition);
        } else if (ball.getPosition().getX() < getCanvasSize().get(0)/2) {
            
            newPosition.setX(getCanvasSize().get(0));
            ball.setPosition(newPosition);
        }

        this.tgModel.addBall(ball);
    }

    public void sendObject(Ball ball, Enum<PeerLocation> direc) {
        this.tgModel.removeBall(ball);
        
        if(!ball.isAlive()){
            this.tgController.sendObject(ball, direc);
        }
    }

    // GETTERS AND SETTERS
    public List<VisualObject> getVisualElements() {
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
