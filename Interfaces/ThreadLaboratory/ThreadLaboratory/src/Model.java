public class Model {
    private LabParameters config;
    private LabResults results;
    private Contador produc;
    private Thread[] hilosProductor = new Thread[200];
    private Thread[] hilosConsumidor = new Thread[400];

    public Model() {
        this.produc = new Contador(0);
    }

    public void calc() {
        for (int i = 0; i < 200; i++) {

            hilosProductor[i] = (new Thread(new Productor(produc)));
            hilosProductor[i].start();
        }

        for (int i = 0; i < 400; i++) {

            hilosConsumidor[i] = (new Thread(new Consumidor(produc)));
            hilosConsumidor[i].start();
        }
    }

    public void setConfig(LabParameters config){
        this.config = config;
    }

    public LabResults getResults(){
        return this.results;
    }
}
