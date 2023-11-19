public class Model {
    private Comptador cm;
    private Thread[] hilosProductor = new Thread[200];
    private Thread[] hilosConsumidor = new Thread[400];

    public Model() {

        this.cm = new Comptador(0);
    }

    public void calc() {
        for (int i = 0; i < 200; i++) {

            hilosProductor[i] = (new Thread(new Productor(cm)));
            hilosProductor[i].start();
        }

        for (int i = 0; i < 400; i++) {

            hilosConsumidor[i] = (new Thread(new Consumidor(cm)));
            hilosConsumidor[i].start();
        }
    }

    public Comptador getCm() {
        return this.cm;
    }

    public void setCm(Comptador cm) {
        this.cm = cm;
    }
}
