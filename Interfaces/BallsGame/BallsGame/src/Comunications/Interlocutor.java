package Comunications;

public class Interlocutor {
    private String IP = "localhost";
    private String interlocutorId;
    
    public Interlocutor(String IP, String interlocutorId) {
        this.IP = IP;
        this.interlocutorId = interlocutorId;
    }
    
    public String getIP() {
        return IP;
    }

    public String getInterlocutorId() {
        return interlocutorId;
    }
}
