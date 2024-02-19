package Comunications;

public class DataFrameType {
    private boolean Aplication_Frame;
    private boolean Frame_Refused;
    private boolean Internal_Info;
    private boolean Keep_ALive;
    private boolean Keep_Alive_Back;

    public boolean isAplication_Frame() {
        return Aplication_Frame;
    }

    public void setAplication_Frame(boolean aplication_Frame) {
        Aplication_Frame = aplication_Frame;
    }

    public boolean isFrame_Refused() {
        return Frame_Refused;
    }

    public void setFrame_Refused(boolean frame_Refused) {
        Frame_Refused = frame_Refused;
    }

    public boolean isInternal_Info() {
        return Internal_Info;
    }

    public void setInternal_Info(boolean internal_Info) {
        Internal_Info = internal_Info;
    }

    public boolean isKeep_ALive() {
        return Keep_ALive;
    }

    public void setKeep_ALive(boolean keep_ALive) {
        Keep_ALive = keep_ALive;
    }

    public boolean isKeep_Alive_Back() {
        return Keep_Alive_Back;
    }

    public void setKeep_Alive_Back(boolean keep_Alive_Back) {
        Keep_Alive_Back = keep_Alive_Back;
    }
}
