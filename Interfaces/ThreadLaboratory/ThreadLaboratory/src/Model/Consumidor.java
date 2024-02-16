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
            this.model.getResults().setQuantityConsumit(this.model.getResults().getQuantityConsumit() + 1);
            this.model.getProduc().consume();
        }
    }
    
    public void run() {
        this.model.getResults().setQuantityConsumidorProcesando(this.model.getResults().getQuantityConsumidorProcesando() + 1);
        this.consumir();
        this.model.getResults().setQuantityConsumidorFinalizados(this.model.getResults().getQuantityConsumidorFinalizados() + 1);
        this.model.getResults().setQuantityConsumidorProcesando(this.model.getResults().getQuantityConsumidorProcesando() - 1);
        this.model.getResults().setQuantityConsumidorPendientes(this.model.getResults().getQuantityConsumidorPendientes() - 1);
        this.model.getResults().setMsProcesoConsumidor(((this.model.getTiempIniProCons() - System.currentTimeMillis()) / -1000)+"");
    }
}
