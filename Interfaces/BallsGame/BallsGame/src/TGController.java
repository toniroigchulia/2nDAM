import Engine.*;
import Comunications.*;

public class TGController {
    private TGLocalController tgController;
    private TGComunications tgComunications;
    public static void main(String[] args) throws Exception {
    
        TGController peerController = new TGController();
        peerController.init();
    }
    
    public void init() {
        this.tgController = new TGLocalController();
        this.tgComunications = new TGComunications();
    }
    
    public void addBall(){
    
    }
    
    public void removeBall(){
    
    }
    
    public void createAllChannels()
    {
    
    }
}