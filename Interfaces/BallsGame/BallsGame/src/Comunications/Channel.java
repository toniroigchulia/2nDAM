package Comunications;

public class Channel implements Runnable {

    private TGComunications tgComunications;
    private TestChanel testChanel;
    private Socket socket;

    private int sendTime;
    private int recievedTime;

    private int PORT;
    private String ipAddres;
    private boolean runState;

    public Channel(TGComunications tgComunications, String ipAddres) {
        this.tgComunications = tgComunications;
        this.ipAddres = ipAddres;
        this.testChanel = new TestChanel(this);
    }

    @Override
    public void run() {

    }

    private void recivedPacket() {

    }

    private void sendPacket() {

    }

    private void setSocket() {

    }

    private void setState() {

    }
}
