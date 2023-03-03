package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class NumerosModel {

  private int[] numeros;

  public NumerosModel() {}

  public void leerNumerosDesdeArchivo(String archivo) throws IOException {
    BufferedReader lector = new BufferedReader(new FileReader(archivo));
    String linea = lector.readLine();
    ArrayList<Integer> numerosLista = new ArrayList<>();
    while (linea != null) {
      numerosLista.add(Integer.parseInt(linea));
      linea = lector.readLine();
    }
    lector.close();
    this.numeros = new int[numerosLista.size()];
    for (int i = 0; i < numerosLista.size(); i++) {
      this.numeros[i] = numerosLista.get(i);
    }
  }

  public void escribirNumerosEnArchivo(String archivo) throws IOException {
    FileWriter escritor = new FileWriter(archivo);
    for (int i = 0; i < this.numeros.length; i++) {
      escritor.write(String.valueOf(this.numeros[i] + "\n"));
    }
    escritor.close();
  }

  private void quickSort(
    int[] array,
    int izquierda,
    int derecha,
    boolean ascendeten
  ) {
    if (izquierda < derecha) {
      int pivote = particion(array, izquierda, derecha, ascendeten);
      quickSort(array, izquierda, pivote - 1, ascendeten);
      quickSort(array, pivote + 1, derecha, ascendeten);
    }
  }

  private int particion(
    int[] array,
    int izquierda,
    int derecha,
    boolean ascendente
  ) {
    int pivote = array[derecha];
    int i = izquierda - 1;
    for (int j = izquierda; j < derecha; j++) {
      if (
        (ascendente && array[j] < pivote) || (!ascendente && array[j] > pivote)
      ) {
        i++;
        int temporal = array[i];
        array[i] = array[j];
        array[j] = temporal;
      }
    }
    int temporal = array[i + 1];
    array[i + 1] = array[derecha];
    array[derecha] = temporal;
    return i + 1;
  }

  public void ordenarNumerosQuickSort(boolean ascendente) {
    quickSort(this.numeros, 0, this.numeros.length - 1, ascendente);
  }
}
