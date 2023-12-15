import java.util.Random;

public class Productor implements Runnable {
    private Contador prod;
    private Random random = new Random();

    public Productor(Contador p) {
        this.prod = p;
    }

    public void producir() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //this.prod.produce();
        }
    }

    public void run() {
        // comp.setTiempoInicio(System.currentTimeMillis());
        // comp.sumProductoresTraballando();
        this.producir();
        // comp.resProductoresTraballando();
        // comp.sumProductoresAcabados();
        // comp.setTiempoFinal(System.currentTimeMillis());
    }
}
