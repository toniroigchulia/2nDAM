public class LaboratoryController {
    Viewer viewer;
    Thread threadView;
    Model model;
    public static void main(String[] args) throws Exception {
        LaboratoryController lc = new LaboratoryController();
    }

    // METODOS CONSTRUCTORES
    public LaboratoryController() {
        this.model = new Model(new DTOLabParameters());
        this.viewer = new Viewer(this);
        threadView = new Thread(this.viewer);
        threadView.start();
    }

    public DTOLabResults getResults(){
        return this.model.getResults();
    }

    public void applyConfig(DTOLabParameters config){
        this.model.setConfig(config);
    }

    public Viewer getViewer() {
        return viewer;
    }

    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
    }

    public Thread getThreadView() {
        return threadView;
    }

    public void setThreadView(Thread threadView) {
        this.threadView = threadView;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
