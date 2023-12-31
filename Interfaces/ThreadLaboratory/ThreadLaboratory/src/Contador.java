public class Contador {
    private int value;
    private boolean stockPositivo;

    public Contador(int v, boolean stockPositivo) {
        this.value = v;
        this.stockPositivo = stockPositivo;
    }

    public void inc() {
        if(stockPositivo ) {

            if (value < 100) {

                this.value = +1;
            }
        } else {

            this.value = +1;
        }
    }

    public void dec() {
        if(stockPositivo ) {

            if (value > 0) {

                this.value = -1;
            }
        } else {

            this.value = -1;
        }
    }

    public synchronized void incSync() {
        if(stockPositivo ) {

            if (value < 100) {

                this.value = +1;
            } else {
                try {
                    
                    wait();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        } else {

            this.value = +1;
        }
        
        notifyAll();
    }

    public synchronized void decSync() {
        if(stockPositivo ) {

            if (value > 1) {

                this.value = -1;
            } else {
                try {
                    
                    wait();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        } else {

            this.value = -1;
        }
        
        notifyAll();
    }

    public int getValue() {
        return this.value;
    }

    public synchronized int getValueSync() {
        return this.value;
    }
}
