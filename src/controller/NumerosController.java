package controller;

import model.NumerosModel;
import view.NumerosVista;

public class NumerosController {
    
    private NumerosVista vista;
    private NumerosModel modelo;

    public NumerosController() {
    }

    public NumerosController(NumerosVista vista, NumerosModel modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

}
