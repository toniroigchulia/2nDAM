public class DTOLabParameters {
    private int productores;
    private int quantityItemsP;
    private boolean tempsMaximCheckBoxP;
    private int tempsMaximP;

    private int consumidores;
    private int quantityItemsC;
    private boolean tempsMaximCheckBoxC;
    private int tempsMaximC;

    private boolean playing;

    public DTOLabParameters(){
        this.productores = 200;
        this.quantityItemsP = 100;
        this.tempsMaximCheckBoxC = false;
        this.tempsMaximP = 100;
        this.consumidores = 400;
        this.quantityItemsC = 100;
        this.tempsMaximCheckBoxC = false;
        this.tempsMaximC = 100;
    }

    public int getProductores() {
        return productores;
    }

    public void setProductores(int productores) {
        this.productores = productores;
    }

    public int getQuantityItemsP() {
        return quantityItemsP;
    }

    public void setQuantityItemsP(int quantityItemsP) {
        this.quantityItemsP = quantityItemsP;
    }

    public boolean isTempsMaximCheckBoxP() {
        return tempsMaximCheckBoxP;
    }

    public void setTempsMaximCheckBoxP(boolean tempsMaximCheckBoxP) {
        this.tempsMaximCheckBoxP = tempsMaximCheckBoxP;
    }

    public int getTempsMaximP() {
        return tempsMaximP;
    }

    public void setTempsMaximP(int tempsMaximP) {
        this.tempsMaximP = tempsMaximP;
    }

    public int getConsumidores() {
        return consumidores;
    }

    public void setConsumidores(int consumidores) {
        this.consumidores = consumidores;
    }

    public int getQuantityItemsC() {
        return quantityItemsC;
    }

    public void setQuantityItemsC(int quantityItemsC) {
        this.quantityItemsC = quantityItemsC;
    }

    public boolean isTempsMaximCheckBoxC() {
        return tempsMaximCheckBoxC;
    }

    public void setTempsMaximCheckBoxC(boolean tempsMaximCheckBoxC) {
        this.tempsMaximCheckBoxC = tempsMaximCheckBoxC;
    }

    public int getTempsMaximC() {
        return tempsMaximC;
    }

    public void setTempsMaximC(int tempsMaximC) {
        this.tempsMaximC = tempsMaximC;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
