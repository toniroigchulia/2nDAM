package Comunications;

import java.net.Socket;

public class PeerIDIdentificator implements Runnable{
    private Socket CLSOCK;
    private ServerConection serverConection;
    
    public PeerIDIdentificator(ServerConection serverConection, Socket CLSOCK) {
        this.serverConection = serverConection;
        this.CLSOCK = CLSOCK;
    }

    @Override
    public void run() {
        if(CLSOCK != null){
        
            System.out.println("Direccion de la Conexion: " + CLSOCK.getInetAddress());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
