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
        
    }
}
