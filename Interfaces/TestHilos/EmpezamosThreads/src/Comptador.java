
public class Comptador {
    // ATRIBUTOS
    private int valor;
    private int productoresTraballando;
    private int productoresAcabados;
    private int consumidoresConsumiendo;
    private int consumidoresConsumidos;
    private long tiempoInicio;
    private long tiempoFinal;

    public Comptador(int c) {
        this.valor = c;
    }

    public synchronized void In() {
        if (this.valor >= 10000) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            
            this.valor++;
        }
        notifyAll();
    }

    public synchronized void des() {
        if (this.valor <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            
            this.valor--;
        }
        notifyAll();
    }

    public int get() {
        return this.valor;
    }

    public int getValue() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public synchronized void sumProductoresTraballando() {
        this.productoresTraballando++;
    }

    public synchronized void resProductoresTraballando() {
        this.productoresTraballando--;
    }

    public synchronized void sumProductoresAcabados() {
        this.productoresAcabados++;
    }

    public synchronized void sumConsumidoresConsumiendo() {
        this.consumidoresConsumiendo++;
    }

    public synchronized void resConsumidoresConsumiendo() {
        this.consumidoresConsumiendo--;
    }

    public synchronized void sumConsumidoresConsumidos() {
        this.consumidoresConsumidos++;
    }

    public int getValor() {
        return valor;
    }

    public synchronized int getProductoresTraballando() {
        return productoresTraballando;
    }

    public void setProductoresTraballando(int productoresTraballando) {
        this.productoresTraballando = productoresTraballando;
    }

    public synchronized int getProductoresAcabados() {
        return productoresAcabados;
    }

    public void setProductoresAcabados(int productoresAcabados) {
        this.productoresAcabados = productoresAcabados;
    }

    public synchronized int getConsumidoresConsumiendo() {
        return consumidoresConsumiendo;
    }

    public void setConsumidoresConsumiendo(int consumidoresConsumiendo) {
        this.consumidoresConsumiendo = consumidoresConsumiendo;
    }

    public synchronized int getConsumidoresConsumidos() {
        return consumidoresConsumidos;
    }

    public void setConsumidoresConsumidos(int consumidoresConsumidos) {
        this.consumidoresConsumidos = consumidoresConsumidos;
    }

    public long getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(long tiempoInicio) {
        if (this.tiempoInicio == 0) {

            this.tiempoInicio = tiempoInicio;
        } else if (tiempoInicio < this.tiempoInicio) {

            this.tiempoInicio = tiempoInicio;
        }
    }

    public long getTiempoFinal() {
        return tiempoFinal;
    }

    public void setTiempoFinal(long tiempoFinal) {
        if (tiempoFinal > this.tiempoFinal) {
            this.tiempoFinal = tiempoFinal;
        }
    }

    public long getTiempoTotal() {
        if (tiempoFinal == 0) {

            return tiempoInicio;
        } else {
            return tiempoFinal - tiempoInicio;
        }
    }
}
