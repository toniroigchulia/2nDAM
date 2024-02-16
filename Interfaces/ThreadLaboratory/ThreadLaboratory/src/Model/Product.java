package Model;

public class Product extends Contador {
    private Model model;

    public Product(int v, boolean stockPositivo, Model model) {
        super(v, model);
        this.model = model;
    }

    public void consume() {
        if (this.model.getConfig().isProtegerRegCritic()) {

            decSync();
        } else {

            dec();
        }

        this.model.getResults().setProductosActuales(getValueSync());
    }

    public void produce() {
        if (this.model.getConfig().isProtegerRegCritic()) {

            incSync();
        } else {

            inc();
        }

        this.model.getResults().setProductosActuales(getValueSync());
    }

    public void setValue(int v) {
        super.setValue(v);
    }
}