package Comunications;

import java.net.Socket;

public class ClientConection implements Runnable{
    private TGComunications tgComunications;
    private Socket SOCKET;
    private String IP;
    private int PORT;

    private boolean conexionEstablecida = false;

    private volatile boolean intentarReconectar = true;

    public ClientConection(TGComunications tgComunications, int PORT, String IP) {
        this.tgComunications = tgComunications;
        this.PORT = PORT;
        this.IP = IP;
    }
    
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public void createConnection() {
        while (!conexionEstablecida) {
            if (!conexionEstablecida && intentarReconectar) {

                try {

                    System.out.println("Conectando como cliente...");
                    this.SOCKET = new Socket(this.IP, this.PORT);

                    conexionEstablecida = true;
                    
                } catch (Exception e) {

                    System.out.println("Esperando conexión...");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {

                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }
    
    // Getters And Setters
    public TGComunications getTgComunications() {
        return tgComunications;
    }

    public void setTgComunications(TGComunications tgComunications) {
        this.tgComunications = tgComunications;
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

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int pORT) {
        PORT = pORT;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String iP) {
        IP = iP;
    }

    public Socket getSOCKET() {
        return SOCKET;
    }

    public void setSOCKET(Socket sOCKET) {
        SOCKET = sOCKET;
    }
}
