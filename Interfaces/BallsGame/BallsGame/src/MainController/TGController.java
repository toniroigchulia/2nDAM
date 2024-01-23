package MainController;
import Engine.*;
import Comunications.*;

public class TGController {
    private TGLocalController tgLocalController;
    private TGComunications tgComunications;
    public static void main(String[] args) throws Exception {
    
        TGController peerController = new TGController();
        peerController.init();
    }
    
    public void init() {
        this.tgLocalController = new TGLocalController(this);
        this.tgComunications = new TGComunications();
    }
    
    public void addBall(){
    
    }
    
    public void removeBall(){
    
    }
    
    public void createAllChannels(){
    
    }
}