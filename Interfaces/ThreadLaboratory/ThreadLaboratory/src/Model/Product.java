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
            this.model.getResults().setProductosActuales(getValueSync());
        } else {

            dec();
            this.model.getResults().setProductosActuales(getValue());
        }

    }

    public void produce() {
        if (this.model.getConfig().isProtegerRegCritic()) {

            incSync();
            this.model.getResults().setProductosActuales(getValueSync());
        } else {

            inc();
            this.model.getResults().setProductosActuales(getValue());
        }
    }

    public void setValue(int v) {
        super.setValue(v);
    }
}