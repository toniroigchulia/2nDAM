package Comunications;
import java.net.Socket;

public class ClientConection implements Runnable {
    private TGComunications tgComunications;
    private Socket SOCKET;

    public ClientConection(TGComunications tgComunications) {
        this.tgComunications = tgComunications;
    }

    @Override
    public void run() {
        while (true) {
            this.SOCKET = new Socket();
            createConnection();
        }
    }

    public void createConnection() {
        if (this.tgComunications.getDownChannels() != null) {
            for (int i = 0; i < this.tgComunications.getDownChannels().size(); i++) {
                System.out.println("Entramos a hacer la conexion");
                try {

                    System.out.println("Conectando como cliente al canal: " + i);
                    this.SOCKET = new Socket(this.tgComunications.getDownChannels().get(i).getInterlocutor().getIP(), 4000);
                    this.tgComunications.getDownChannels().get(i).setSocket(this.SOCKET);
                    this.tgComunications.getChannels().add(this.tgComunications.getDownChannels().get(i));
                    new Thread(this.tgComunications.getChannels().get(i)).start();
                    this.tgComunications.getDownChannels().remove(i);
                } catch (Exception e) {
                
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException ex) {

                        throw new RuntimeException(ex);
                    }
                }
            }
        }
        
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {

            throw new RuntimeException(ex);
        }
    }

    // Getters And Setters
    public TGComunications getTgComunications() {
        return tgComunications;
    }

    public void setTgComunications(TGComunications tgComunications) {
        this.tgComunications = tgComunications;
    }

    public Socket getSOCKET() {
        return SOCKET;
    }

    public void setSOCKET(Socket sOCKET) {
        SOCKET = sOCKET;
    }
}
