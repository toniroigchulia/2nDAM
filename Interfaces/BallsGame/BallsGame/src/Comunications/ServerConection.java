package Comunications;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConection implements Runnable {
    private TGComunications tgComunications;
    private int port;
    private ServerSocket socket;
    private Socket clsock;

    public ServerConection(TGComunications tgComunications, int port) {
        this.tgComunications = tgComunications;
        this.port = port;
        try {
            this.socket = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void run() {
        try {

            System.out.println("Conectando como servidor...");
            this.clsock = socket.accept();
        } catch (Exception e) {

            System.out.println("ServerConnector error: " + e);
        }
    }

    public void killSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSocketClosed() {
        return socket.isClosed();
    }

    public TGComunications getTgComunications() {
        return tgComunications;
    }

    public void setTgComunications(TGComunications tgComunications) {
        this.tgComunications = tgComunications;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerSocket getSocket() {
        return socket;
    }

    public void setSocket(ServerSocket socket) {
        this.socket = socket;
    }

    public Socket getClsock() {
        return clsock;
    }

    public void setClsock(Socket clsock) {
        this.clsock = clsock;
    }
}
