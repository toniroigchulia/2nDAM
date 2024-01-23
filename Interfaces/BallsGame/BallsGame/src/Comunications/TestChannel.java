package Comunications;

public class TestChannel implements Runnable {

    private Channel channel;
    private long timeOut;
    private boolean working = true;

    public TestChannel(Channel channel, long timeOut) {
        this.channel = channel;
        this.timeOut = timeOut;
    }

    @Override
    public void run() {
        while (working) {
            System.out.println("Health Care: Checking...");
            // Verificar si ha pasado más tiempo que timeOut desde que se recibió el último
            // mensaje
            long currentTime = System.currentTimeMillis();
            long timeLastMessage = channel.getRecievedTime();
            long diferencia = currentTime - timeLastMessage;

            if (diferencia > timeOut) {

                System.out.println("Health Care: last recieved message was " + diferencia + " ago");
                if (channel.ping()) {

                    System.out.println("Health Care: Ping sent successfully");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {

                    System.out.println("HealthCareConnection: Cerrando conexion.");
                    channel.killSocket();
                    try {
                        Thread.sleep(5000);
                        channel.setSocket();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    working = false;
                }
            }
        }

        System.err.println(this.getClass().getSimpleName() + " detenido.");
    }

    public void pararEjecucion() {
        System.err.println("Deteniendo Health Care Connection...");
        working = false;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public boolean isWorking() {
        return working;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }
}
