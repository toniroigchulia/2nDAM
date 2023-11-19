public class Controller {
    Viewer viewer;
    Model model;
    Thread threadView;

    public static void main(String Args[]) {
        Controller fc = new Controller();
        fc.inicio();
    }

    // METODOS CONSTRUCTORES
    public Controller() {
        this.model = new Model();
        this.viewer = new Viewer(this);
        threadView = new Thread(this.viewer);
        threadView.start();
    }

    // METODOS
    // Este metodo es el inicio de la aplicacion
    public void start() {
        model.calc();
    }
    
    public void inicio() {
        System.out.println("Programa Iniciado");
    }
    
    public Comptador getCm() {
    
        return this.model.getCm();
    }
}