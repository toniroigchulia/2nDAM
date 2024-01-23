package Comunications;

public class TestChanel implements Runnable {

    private Channel channel;
    private long timeOut;
    private boolean working;

    public TestChanel(Channel channel, long timeOut) {
        this.channel = channel;
        this.timeOut = timeOut;
    }

    @Override
    public void run() {
        while(working) {
            System.out.println("Health Care: Checking...");
            // Verificar si ha pasado más tiempo que timeOut desde que se recibió el último mensaje
            long currentTime = System.currentTimeMillis();
            long timeLastMessage = channel.getRecievedTime();
            long diferencia = currentTime - timeLastMessage;
            
            if(diferencia > timeOut) {
            
                System.out.println("Health Care: last recieved message was " + diferencia + " ago");
                if(channel.ping()){
                
                    System.out.println("Health Care: Ping sent successfully");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                
                    System.out.println("HealthCareConnection: Cerrando conexion.");
                    channel.killSocket();
                    break;
                }
            }
        }
        System.err.println(this.getClass().getSimpleName() + " detenido.");
    }
    
    public void pararEjecucion() {
        System.err.println("Deteniendo Health Care Connection...");
        working = false;
    }
}
