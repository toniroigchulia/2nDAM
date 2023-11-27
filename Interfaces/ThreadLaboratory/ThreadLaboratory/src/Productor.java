import java.util.Random;

public class Productor implements Runnable {
    private Product prod;
    private Random random = new Random();

    public Productor(Product p) {
        this.prod = p;
    }

    public void setComptador() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // this.prod.In();
        }
    }

    public void run() {
        // comp.setTiempoInicio(System.currentTimeMillis());
        // comp.sumProductoresTraballando();
        this.setComptador();
        // comp.resProductoresTraballando();
        // comp.sumProductoresAcabados();
        // comp.setTiempoFinal(System.currentTimeMillis());
    }
}
