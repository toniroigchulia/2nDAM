package Model;

public class Contador {
    private int value;
    private Model model;

    public Contador(int v, Model model) {
        this.value = v;
        this.model = model;
    }

    public void inc() {
        if (this.model.getConfig().isStockPositivo()) {

            if (value < 10000) {

                this.value = this.value + 1;
            }
        } else {

            this.value = this.value + 1;
        }
    }

    public void dec() {
        if (this.model.getConfig().isStockPositivo()) {

            if (value > 0) {

                this.value = this.value - 1;
            }
        } else {

            this.value = this.value - 1;
        }
    }

    public synchronized void incSync() {
        if (this.model.getConfig().isStockPositivo()) {

            if (value < 10000) {

                this.value = this.value + 1;
            } else {
                try {

                    wait();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        } else {

            this.value = this.value + 1;
        }

        notifyAll();
    }

    public synchronized void decSync() {
        if (this.model.getConfig().isStockPositivo()) {

            if (value > 0) {

                this.value = this.value - 1;
            } else {
                try {

                    wait();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        } else {

            this.value = this.value - 1;
        }

        notifyAll();
    }

    public int getValue() {
        return this.value;
    }

    public synchronized int getValueSync() {
        return this.value;
    }

    public void setValue(int v) {
        this.value = v;
    }
}