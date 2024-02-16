package Main;
import DTOS.DTOLabParameters;
import DTOS.DTOLabResults;
import Model.Model;
import Visual.Viewer;

public class LaboratoryController {
    Viewer viewer;
    Thread threadView;
    Model model;
    public static void main(String[] args) throws Exception {
        LaboratoryController lc = new LaboratoryController();
    }

    // METODOS CONSTRUCTORES
    public LaboratoryController() {
        this.model = new Model(new DTOLabParameters(), this);
        this.viewer = new Viewer(this);

        threadView = new Thread(this.viewer);
        threadView.start();
    }

    public void modelStart(){
        this.model.start();
    }

    public DTOLabResults getResults(){
        return this.model.getResults();
    }

    public void resetResult(){
        this.model.setResults(new DTOLabResults());
        this.model.getProduc().setValue(0);
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
