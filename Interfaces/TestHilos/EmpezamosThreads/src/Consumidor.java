import java.util.Random;

public class Consumidor implements Runnable {
    private Comptador comp;
    private Random random = new Random();

    public Consumidor(Comptador c) {
        this.comp = c;
    }

    public void setComptador() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.comp.des();
        }
    }

    public void run() {
        comp.setTiempoInicio(System.currentTimeMillis());
        comp.sumConsumidoresConsumiendo();
        this.setComptador();
        comp.resConsumidoresConsumiendo();
        comp.sumConsumidoresConsumidos();
        comp.setTiempoFinal(System.currentTimeMillis());
    }
}
