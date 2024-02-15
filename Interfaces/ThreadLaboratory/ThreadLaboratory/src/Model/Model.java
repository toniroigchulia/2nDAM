package Model;
import DTOS.DTOLabParameters;
import DTOS.DTOLabResults;
import Main.LaboratoryController;

public class Model {
    private LaboratoryController laboratoryController;
    private DTOLabParameters config;
    private DTOLabResults results;
    private Product produc;

    public Model(DTOLabParameters config, LaboratoryController laboratoryController) {
        this.laboratoryController = laboratoryController;
        this.config = config;
        this.results = new DTOLabResults();
        this.produc = new Product(0, config.isStockPositivo(), this, config.isProtegerRegCritic());
    }

    public void start() {
        Thread[] hilosProductor = new Thread[config.getProductores()];
        for (int i = 0; i < config.getProductores(); i++) {
            
            hilosProductor[i] = (new Thread(new Productor(this)));
            hilosProductor[i].start();
        }

        Thread[] hilosConsumidor = new Thread[config.getConsumidores()];
        for (int i = 0; i < config.getConsumidores(); i++) {

            hilosConsumidor[i] = (new Thread(new Consumidor(this)));
            hilosConsumidor[i].start();
        }
    }

    public DTOLabParameters getConfig() {
        return config;
    }

    public void setConfig(DTOLabParameters config) {
        this.config = config;
    }

    public DTOLabResults getResults() {
        return results;
    }

    public void setResults(DTOLabResults results) {
        this.results = results;
    }

    public LaboratoryController getLaboratoryController() {
        return laboratoryController;
    }

    public void setLaboratoryController(LaboratoryController laboratoryController) {
        this.laboratoryController = laboratoryController;
    }

    public Product getProduc() {
        return produc;
    }

    public void setProduc(Product produc) {
        this.produc = produc;
    }
}
