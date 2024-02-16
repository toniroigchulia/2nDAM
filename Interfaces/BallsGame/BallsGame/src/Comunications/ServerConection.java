package Comunications;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConection implements Runnable{
    private TGComunications tgComunications;
    private ServerSocket SOCKET;
    private Socket CLSOCK;
    private int PORT;

    public ServerConection(TGComunications tgComunications, int port) {
        this.tgComunications = tgComunications;
        this.PORT = port;

    }
    
    @Override
    public void run() {
        while (true) {
            try {
                this.SOCKET = new ServerSocket(this.PORT);
                createConnection();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public void createConnection() {
        try {
            
            System.out.println("Conectando como servidor...");
            this.CLSOCK = SOCKET.accept();
            new Thread(new PeerIDIdentificator(this, this.CLSOCK)).start();
        } catch (Exception e) {

            System.out.println("ServerConnector error: " + e);
        }
    }
    
    // Getters And Setters
    public boolean isSocketClosed() {
        return SOCKET.isClosed();
    }

    public TGComunications getTgComunications() {
        return tgComunications;
    }

    public void setTgComunications(TGComunications tgComunications) {
        this.tgComunications = tgComunications;
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int port) {
        this.PORT = port;
    }

    public ServerSocket getSOCKET() {
        return SOCKET;
    }

    public void setSOCKET(ServerSocket socket) {
        this.SOCKET = socket;
    }

    public Socket getCLSOCK() {
        return CLSOCK;
    }

    public void setCLSOCK(Socket clsock) {
        this.CLSOCK = clsock;
    }
}
