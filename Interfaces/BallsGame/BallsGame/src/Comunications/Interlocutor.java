package Comunications;

public class Interlocutor {
    private String IP = "localhost";
    
    public Interlocutor(String IP) {
        this.IP = IP;
    }
    
    public String getIP() {
        return IP;
    }
}
