package Comunications;

import java.net.Socket;

public class ClientConection implements Runnable {
    private TGComunications tgComunications;
    private int port;
    private String ip;
    private Socket socket;

    private boolean conexionEstablecida = false;

    private volatile boolean intentarReconectar = true;

    public ClientConection(TGComunications tgComunications, int port, String ip) {
        this.tgComunications = tgComunications;
        this.port = port;
        this.ip = ip;
    }

    @Override
    public void run() {
        try {
        
            System.out.println("Conectando como cliente...");
            while (this.socket == null) {
            
                this.socket = new Socket(this.ip, this.port);
            }
            conexionEstablecida = true;
        } catch (Exception e) {
        
            System.out.println("Esperando conexión...");
            try {
                Thread.sleep(5000);
                if (intentarReconectar) {
                    run();
                }
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isConexionEstablecida() {
        return conexionEstablecida;
    }

    public void setConexionEstablecida(boolean conexionEstablecida) {
        this.conexionEstablecida = conexionEstablecida;
    }

    public boolean isIntentarReconectar() {
        return intentarReconectar;
    }

    public void setIntentarReconectar(boolean intentarReconectar) {
        this.intentarReconectar = intentarReconectar;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
