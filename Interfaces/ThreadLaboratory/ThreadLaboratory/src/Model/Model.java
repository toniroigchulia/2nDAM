package Model;

import DTOS.DTOLabParameters;
import DTOS.DTOLabResults;
import Main.LaboratoryController;

public class Model {
    private LaboratoryController laboratoryController;
    private DTOLabParameters config;
    private DTOLabResults results;
    private Product produc;
    private long tiempIniProCons = 0;
    private long tiempIniProProd = 0;

    public Model(DTOLabParameters config, LaboratoryController laboratoryController) {
        this.laboratoryController = laboratoryController;
        this.config = config;
        this.results = new DTOLabResults();
        this.produc = new Product(0, config.isStockPositivo(), this);
    }

    public void start() {
        // Bucles parac crear los hilos
        long tiempoCrearThreads = System.currentTimeMillis();

        Thread[] hilosProductor = new Thread[config.getProductores()];
        Thread[] hilosConsumidor = new Thread[config.getConsumidores()];

        // Productoes
        for (int i = 0; i < config.getProductores(); i++) {

            hilosProductor[i] = (new Thread(new Productor(this)));
            results.setQuantityProductorPendientes(results.getQuantityProductorPendientes() + 1);
        }
        // Consumidores
        for (int i = 0; i < config.getConsumidores(); i++) {

            hilosConsumidor[i] = (new Thread(new Consumidor(this)));
            results.setQuantityConsumidorPendientes(results.getQuantityConsumidorPendientes() + 1);
        }
        this.results.setMsCrearThread((System.currentTimeMillis() - tiempoCrearThreads) + "");

        // Bucles para iniciar los hilos
        long tiempoEmpezarThreads = System.currentTimeMillis();

        // Productores
        for (int i = 0; i < config.getProductores(); i++) {

            hilosProductor[i].start();
            if (this.tiempIniProProd == 0) {
                this.tiempIniProProd = System.currentTimeMillis();
            }
        }
        // Consumidores
        for (int i = 0; i < config.getConsumidores(); i++) {

            hilosConsumidor[i].start();
            if (this.tiempIniProCons == 0) {
                this.tiempIniProCons = System.currentTimeMillis();
            }
        }
        this.results.setMsArrancarThread((System.currentTimeMillis() - tiempoEmpezarThreads) + "");
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

    public long getTiempIniProCons() {
        return tiempIniProCons;
    }

    public long getTiempIniProProd() {
        return tiempIniProProd;
    }
}