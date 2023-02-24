package view;

import java.io.IOException;

import model.NumerosModel;

public class Main {
    
    public static void main(String[] args) throws IOException {
        NumerosModel modelo = new NumerosModel();
        modelo.leerNumerosDesdeArchivo("numeros.txt");
        modelo.ordenarNumerosMergeAscendente();
        modelo.escribirNumerosEnArchivo("numeros_ordenados.txt");
        System.out.println("Numeros Ordenados Correctamente");
    }
    
}
