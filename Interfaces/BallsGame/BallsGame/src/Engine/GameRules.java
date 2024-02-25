package Engine;

import Comunications.PeerLocation;

public class GameRules {
    private TGLocalController localController;

    public GameRules(TGLocalController controller) {
        this.localController = controller;
    }

    public boolean checkGate(int direcc) {

        for (int i = 0; i < this.localController.getTgController().getTgComunications().getChannels().size(); i++) {
            if (this.localController.getTgController().getTgComunications().getChannels().get(i).getInterlocutor()
                    .getPeerLocation() == PeerLocation.EAST && direcc == 1) {

                return true;
            } else if (this.localController.getTgController().getTgComunications().getChannels().get(i).getInterlocutor()
                    .getPeerLocation() == PeerLocation.WEST && direcc == 2) {

                return true;
            }
        }

        return false;
    }
}
