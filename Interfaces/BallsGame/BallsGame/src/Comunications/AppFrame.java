package Comunications;

public class AppFrame {
    private AppFrameType appFrameType;
    
    public AppFrame (AppFrameType appFrameType){
        this.appFrameType = appFrameType;
    }

    public AppFrameType getAppFrameType() {
        return appFrameType;
    }

    public void setAppFrameType(AppFrameType appFrameType) {
        this.appFrameType = appFrameType;
    }
}
