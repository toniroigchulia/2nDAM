package Comunications;

import java.util.ArrayList;

import MainController.TGController;

public class TGComunications {

    private ArrayList<Channel> channels = new ArrayList<>();
    private ClientConection clientConection;
    private ServerConection serverConection;
    
    private TGController tgController;

    public TGComunications(TGController tgController) {
        this.tgController = tgController;
        this.addChannels();
    }

    private void addChannels() {
        channels.add(new Channel(this));
        new Thread(channels.get(0)).start();
    }

    private void sendObject() {

    }

    private void addBall() {

    }

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
}