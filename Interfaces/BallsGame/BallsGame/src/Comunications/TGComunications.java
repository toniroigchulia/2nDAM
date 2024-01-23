package Comunications;

import java.util.ArrayList;

import MainController.TGController;

public class TGComunications {

    private ArrayList<Channel> channels = new ArrayList<>();
    private TGController tgController;
    private ClientConection clientConection;
    private ServerConection serverConection;

    public TGComunications(TGController tgController) {
        this.tgController = tgController;
        this.clientConection = new ClientConection(this);
        this.serverConection = new ServerConection(this);
        this.addChannels();
    }

    private void addChannels() {

    }

    private void sendObject() {

    }

    private void addBall() {

    }
}