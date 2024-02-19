package Comunications;

public class AppFrame {
    private AppFrameType appFrameType;
    private Object object;
    
    public AppFrame (AppFrameType appFrameType, Object object){
        this.appFrameType = appFrameType;
    }

    public AppFrameType getAppFrameType() {
        return appFrameType;
    }

    public void setAppFrameType(AppFrameType appFrameType) {
        this.appFrameType = appFrameType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}