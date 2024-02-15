package Model;

public class Product extends Contador {
    private Model model;
    private boolean protegerRegCritic;

    public Product(int v,boolean stockPositivo, Model model,  boolean protegerRegCritic) {
        super(v, stockPositivo);
        this.protegerRegCritic = protegerRegCritic;
        this.model = model;
    }

    public void consume() {
        if (this.protegerRegCritic) {

            decSync();
        } else {
            dec();
        }

        this.model.getResults().setProductosActuales(getValueSync());
    }

    public void produce() {
        if (this.protegerRegCritic) {

            incSync();
        } else {

            inc();
        }

        this.model.getResults().setProductosActuales(getValueSync());
    }

    public void setProtegRegCrit(boolean isProtected) {
        this.protegerRegCritic = isProtected;
    }

    public void setStockPositivo(boolean isStockPositivo){
        super.setStockPositivo(isStockPositivo);
    }
}
