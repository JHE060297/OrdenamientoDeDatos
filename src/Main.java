import controller.NumerosController;
import model.NumerosModel;
import view.NumerosVista;

public class Main {
    public static void main(String[] args) {
        NumerosVista vista = new NumerosVista();
        NumerosModel modelo = new NumerosModel();

        NumerosController controlador = new NumerosController();

        vista.setVisible(true);
    }
}
