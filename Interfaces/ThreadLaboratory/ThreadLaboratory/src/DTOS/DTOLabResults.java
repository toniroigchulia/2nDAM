package DTOS;

public class DTOLabResults {
    private String msCrearThread;
    private String msArrancarThread;
    private String msProcesoConsumidor;
    private String msProcesoProductor;
    private int productosActuales;
    private int quantityProduit;
    private int quantityConsumit;
    private int quantityProductorProcesando;
    private int quantityProductorFinalizados;
    private int quantityProductorPendientes;
    private int quantityConsumidorProcesando;
    private int quantityConsumidorFinalizados;
    private int quantityConsumidorPendientes;

    public DTOLabResults() {
        this.msCrearThread = "0";
        this.msArrancarThread = "0";
        this.msProcesoConsumidor = "0";
        this.msProcesoProductor = "0";
        this.productosActuales = 0;
        this.quantityProduit = 0;
        this.quantityConsumit = 0;
        this.quantityProductorProcesando = 0;
        this.quantityProductorFinalizados = 0;
        this.quantityProductorPendientes = 0;
        this.quantityConsumidorProcesando = 0;
        this.quantityConsumidorFinalizados = 0;
        this.quantityConsumidorPendientes = 0;
    }

    public String getMsCrearThread() {
        return msCrearThread;
    }

    public void setMsCrearThread(String msCrearThread) {
        this.msCrearThread = msCrearThread;
    }

    public String getMsArrancarThread() {
        return msArrancarThread;
    }

    public void setMsArrancarThread(String msArrancarThread) {
        this.msArrancarThread = msArrancarThread;
    }

    public String getMsProcesoConsumidor() {
        return msProcesoConsumidor;
    }

    public void setMsProcesoConsumidor(String msProcesoConsumidor) {
        this.msProcesoConsumidor = msProcesoConsumidor;
    }

    public String getMsProcesoProductor() {
        return msProcesoProductor;
    }

    public void setMsProcesoProductor(String msProcesoProductor) {
        this.msProcesoProductor = msProcesoProductor;
    }

    public int getProductosActuales() {
        return productosActuales;
    }

    public void setProductosActuales(int productosActuales) {
        this.productosActuales = productosActuales;
    }

    public int getQuantityProduit() {
        return quantityProduit;
    }

    public void setQuantityProduit(int quantityProduit) {
        this.quantityProduit = quantityProduit;
    }

    public int getQuantityConsumit() {
        return quantityConsumit;
    }

    public synchronized void setQuantityConsumit(int quantityConsumit) {
        this.quantityConsumit = quantityConsumit;
    }

    public synchronized int getQuantityProductorProcesando() {
        return quantityProductorProcesando;
    }

    public synchronized void setQuantityProductorProcesando(int quantityProductorProcesando) {
        this.quantityProductorProcesando = quantityProductorProcesando;
    }

    public synchronized int getQuantityProductorFinalizados() {
        return quantityProductorFinalizados;
    }

    public synchronized void setQuantityProductorFinalizados(int quantityProductorFinalizados) {
        this.quantityProductorFinalizados = quantityProductorFinalizados;
    }

    public synchronized int getQuantityProductorPendientes() {
        return quantityProductorPendientes;
    }

    public synchronized void setQuantityProductorPendientes(int quantityProductorPendientes) {
        this.quantityProductorPendientes = quantityProductorPendientes;
    }

    public synchronized int getQuantityConsumidorProcesando() {
        return quantityConsumidorProcesando;
    }

    public synchronized void setQuantityConsumidorProcesando(int quantityConsumidorProcesando) {
        this.quantityConsumidorProcesando = quantityConsumidorProcesando;
    }

    public synchronized int getQuantityConsumidorFinalizados() {
        return quantityConsumidorFinalizados;
    }

    public synchronized void setQuantityConsumidorFinalizados(int quantityConsumidorFinalizados) {
        this.quantityConsumidorFinalizados = quantityConsumidorFinalizados;
    }

    public synchronized int getQuantityConsumidorPendientes() {
        return quantityConsumidorPendientes;
    }

    public synchronized void setQuantityConsumidorPendientes(int quantityConsumidorPendientes) {
        this.quantityConsumidorPendientes = quantityConsumidorPendientes;
    }
}