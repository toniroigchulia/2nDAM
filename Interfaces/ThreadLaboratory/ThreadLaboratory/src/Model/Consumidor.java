package Model;
import java.util.Random;

public class Consumidor implements Runnable {
    private Model model;
    private Random random = new Random();

    public Consumidor(Model model) {
        this.model = model;
    }

    public void consumir() {
        for (int i = 0; i < this.model.getConfig().getQuantityItemsC(); i++) {
            try {
                if (!this.model.getConfig().isTempsMaximCheckBoxC()){

                    Thread.sleep(random.nextInt(this.model.getConfig().getTempsMaximC()));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.model.getProduc().consume();
        }
    }

    public void run() {
        this.consumir();
    }
}
