package Comunications;

import java.util.ArrayList;

import MainController.TGController;

public class TGComunications {

    private ArrayList<Channel> downChannels = new ArrayList<>();
    private ArrayList<Channel> channels = new ArrayList<>();
    private ClientConection clientConection;
    private ServerConection serverConection;
    
    private TGController tgController;

    public TGComunications(TGController tgController) {
        this.tgController = tgController;
        this.clientConection = new ClientConection(this);
        this.serverConection = new ServerConection(this, 4040);
        
        new Thread(this.clientConection).start();
        new Thread(this.serverConection).start();
        
        createChannels();
    }

    private synchronized void createChannels() {
        for (int i = 0; i < this.tgController.getPeerInterlocutors().size(); i++) {
            this.downChannels.add(new Channel(this, this.tgController.getPeerInterlocutors().get(i)));
        }
    }

    private void sendBall() {

    }

    private void addBall() {

    }

    // Getters And Setters
    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;
    }

    public ClientConection getClientConection() {
        return clientConection;
    }

    public void setClientConection(ClientConection clientConection) {
        this.clientConection = clientConection;
    }

    public ServerConection getServerConection() {
        return serverConection;
    }

    public void setServerConection(ServerConection serverConection) {
        this.serverConection = serverConection;
    }

    public TGController getTgController() {
        return tgController;
    }

    public void setTgController(TGController tgController) {
        this.tgController = tgController;
    }

    public ArrayList<Channel> getDownChannels() {
        return downChannels;
    }

    public void setDownChannels(ArrayList<Channel> downChannels) {
        this.downChannels = downChannels;
    }
}