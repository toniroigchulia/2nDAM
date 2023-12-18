public class Product extends Contador {

    private DTOLabResults labResults;
    private boolean protegerRegCritic;

    public Product(int v, DTOLabResults labResults, boolean protegerRegCritic, boolean stockPositivo) {
        super(v, stockPositivo);
        this.labResults = labResults;
        this.protegerRegCritic = protegerRegCritic;
    }

    public void consume() {
        if (protegerRegCritic) {

            decSync();
            labResults.setProductosActuales(getValueSync());
        } else {
            dec();
            labResults.setProductosActuales(getValue());
        }
    }

    public void produce() {
        if (protegerRegCritic) {

            incSync();
            labResults.setProductosActuales(getValueSync());
        } else {

            inc();
            labResults.setProductosActuales(getValue());
        }
    }
}
