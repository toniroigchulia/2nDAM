package Comunications;

public class DataFrame {
    private DataFrameType dataFramType;
    private Object sendObject;
    
    public DataFrame(DataFrameType dataFramType, Object object){
        this.dataFramType = dataFramType;
    }

    public DataFrameType getDataFramType() {
        return dataFramType;
    }

    public void setDataFramType(DataFrameType dataFramType) {
        this.dataFramType = dataFramType;
    }

    public Object getSendObject() {
        return sendObject;
    }

    public void setSendObject(Object sendObject) {
        this.sendObject = sendObject;
    }
}
