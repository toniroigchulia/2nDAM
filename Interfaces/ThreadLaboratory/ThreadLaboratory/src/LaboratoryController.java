public class LaboratoryController {
    Viewer viewer;
    Thread threadView;
    Model model;
    public static void main(String[] args) throws Exception {
        LaboratoryController lc = new LaboratoryController();
    }

    // METODOS CONSTRUCTORES
    public LaboratoryController() {
        this.model = new Model();
        this.viewer = new Viewer(this);
        threadView = new Thread(this.viewer);
        threadView.start();
    }

    public LabResults getResults(){
        return this.model.getResults();
    }

    public void applyConfig(LabParameters config){
        this.model.setConfig(config);
    }
}
