import Engine.*;
import Comunications.*;

public class TGPeerController {
    private TGController tgController;
    private TGComunications tgComunications;
    private GameRules gameRules;
    public static void main(String[] args) throws Exception {
    
        TGPeerController peerController = new TGPeerController();
        peerController.init();
    }
    
    
    public void init() {
        this.tgController = new TGController();
        this.tgComunications = new TGComunications();
        this.gameRules = new GameRules();
    }
    
    public void addBall(){
    
    }
    
    public void removeBall(){
    
    }
    
    public void createAllChannels()
    {
    
    }
}