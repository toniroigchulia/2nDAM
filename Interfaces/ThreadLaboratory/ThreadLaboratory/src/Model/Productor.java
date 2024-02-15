package Model;
import java.util.Random;

public class Productor implements Runnable {
    private Model model;
    private Random random = new Random();

    public Productor(Model model) {
        this.model = model;
    }

    public void producir() {
        for (int i = 0; i < this.model.getConfig().getQuantityItemsP(); i++) {
            try {
                if (!this.model.getConfig().isTempsMaximCheckBoxP()){
                    
                    Thread.sleep(random.nextInt(this.model.getConfig().getTempsMaximP()));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.model.getProduc().produce();
        }
    }

    public void run() {
        this.producir();
    }
}
