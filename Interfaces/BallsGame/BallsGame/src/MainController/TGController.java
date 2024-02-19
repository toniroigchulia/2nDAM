package MainController;
import Engine.*;
import java.util.ArrayList;
import Comunications.*;

public class TGController {
    private TGLocalController tgLocalController;
    private TGComunications tgComunications;
    private ArrayList<Interlocutor> peerInterlocutors = new ArrayList();
    public static void main(String[] args) throws Exception {
    
        TGController peerController = new TGController();
        peerController.init();
    }
    
    public void init() {
        this.peerInterlocutors.add(new Interlocutor("localhost"));
        //this.peerInterlocutors.add(new Interlocutor("localhost"));
        this.tgLocalController = new TGLocalController(this);
        this.tgComunications = new TGComunications(this);
    }
    
    public void addBall(){
    
    }
    
    public void removeBall(){
    
    }

    public ArrayList<Interlocutor> getPeerInterlocutors() {
        return peerInterlocutors;
    }

    public void setPeerInterlocutors(ArrayList<Interlocutor> peerInterlocutors) {
        this.peerInterlocutors = peerInterlocutors;
    }
}