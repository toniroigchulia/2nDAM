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

    // METODOS
    // Este metodo es el inicio de la aplicacion
//     public void start() {
//         model.calc();
//     }
    
//     public void inicio() {
//         System.out.println("Programa Iniciado");
//     }
    
//     public Contador getCm() {
    
//         // return this.model.getCm();
//     }
}
