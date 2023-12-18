import java.util.Random;

public class Consumidor implements Runnable {
    private Product prod;
    private int items;
    private int tempsProd;
    private boolean randFix;
    private Random random = new Random();

    public Consumidor(Product v, int items, int tempsProd, boolean randFix) {
        this.prod = v;
        this.items = items;
        this.tempsProd = tempsProd;
        this.randFix = randFix;
    }

    public void consumir() {
        for (int i = 0; i < items; i++) {
            try {
                if (!randFix){

                    Thread.sleep(random.nextInt(tempsProd));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.prod.consume();
        }
    }

    public void run() {
        this.consumir();
    }
}
