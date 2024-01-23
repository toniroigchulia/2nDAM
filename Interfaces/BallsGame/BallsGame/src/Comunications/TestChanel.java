package Comunications;
public class TestChanel implements Runnable{

    private Channel channel;
    private long timeOut;
    private boolean working;

    public TestChanel(Channel channel){
        this.channel = channel;
    }

    @Override
    public void run() {
        while(working){
            long currentTime = System.currentTimeMillis();
        }
    }
}
