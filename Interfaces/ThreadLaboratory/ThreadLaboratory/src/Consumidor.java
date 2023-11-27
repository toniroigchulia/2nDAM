import java.util.Random;

public class Consumidor implements Runnable {
    private Product prod;
    private Random random = new Random();

    public Consumidor(Product v) {
        this.prod = v;
    }

    public void consumir() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // this.prod.des();
        }
    }

    public void run() {
        // comp.setTiempoInicio(System.currentTimeMillis());
        // comp.sumConsumidoresConsumiendo();
        this.consumir();
        // comp.resConsumidoresConsumiendo();
        // comp.sumConsumidoresConsumidos();
        // comp.setTiempoFinal(System.currentTimeMillis());
    }
}
