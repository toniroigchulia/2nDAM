public class Model {
    private DTOLabParameters config;
    private DTOLabResults results;
    private Product produc;

    public Model(DTOLabParameters config) {
        this.config = config;
        this.results = new DTOLabResults();
        this.produc = new Product(0, results, config.isProtegerRegCritic(), config.isStockPositivo());
    }

    public void start() {
        Thread[] hilosProductor = new Thread[config.getProductores()];
        for (int i = 0; i < config.getProductores(); i++) {
            hilosProductor[i] = (new Thread(new Productor(produc, config.getQuantityItemsP(), config.getTempsMaximP(), config.isTempsMaximCheckBoxP())));
            hilosProductor[i].start();
        }

        Thread[] hilosConsumidor = new Thread[config.getConsumidores()];
        for (int i = 0; i < config.getConsumidores(); i++) {

            hilosConsumidor[i] = (new Thread(new Consumidor(produc, config.getQuantityItemsC(), config.getTempsMaximC(), config.isTempsMaximCheckBoxC())));
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
}
