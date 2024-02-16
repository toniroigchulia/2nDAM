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
            this.model.getResults().setQuantityProduit(this.model.getResults().getQuantityProduit() + 1);
            this.model.getProduc().produce();
        }
    }

    public void run() {
        this.model.getResults().setQuantityProductorProcesando(this.model.getResults().getQuantityProductorProcesando() + 1);
        this.producir();
        this.model.getResults().setQuantityProductorFinalizados(this.model.getResults().getQuantityProductorFinalizados() + 1);
        this.model.getResults().setQuantityProductorProcesando(this.model.getResults().getQuantityProductorProcesando() - 1);
        this.model.getResults().setQuantityProductorPendientes(this.model.getResults().getQuantityProductorPendientes() - 1);
        this.model.getResults().setMsProcesoProductor(((this.model.getTiempIniProProd() - System.currentTimeMillis()) / -1000)+"");
    }
}
