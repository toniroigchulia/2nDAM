import java.util.Random;

public class Productor implements Runnable {
    private Product prod;
    private int items;
    private int tempsProd;
    private boolean randFix;
    private Random random = new Random();

    public Productor(Product p, int items, int tempsProd, boolean randFix) {
        this.prod = p;
        this.items = items;
        this.tempsProd = tempsProd;
        this.randFix = randFix;
    }

    public void producir() {
        for (int i = 0; i < items; i++) {
            try {
                if (!randFix){
                    Thread.sleep(random.nextInt(tempsProd));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.prod.produce();
        }
    }

    public void run() {
        this.producir();
    }
}
