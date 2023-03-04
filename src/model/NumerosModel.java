package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class NumerosModel {

  private int[] numeros;

  public NumerosModel() {
  }

  /**
   * Lee numeros desde un archivo de texto donde cada linea contiene un numero
   * entero.
   * Los números se almacenan en un arreglo de enteros.
   * 
   * @param archivo Ruta del archivo a leer.
   * @throws IOException Si ocurre un error al leer el archivo.
   */
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

  /**
   * Escribe los números de la lista en un archivo de texto con el nombre
   * especificado.
   * 
   * @param archivo el nombre del archivo de texto en el que se escribirán los
   *                números.
   * @throws IOException si hay un error al escribir en el archivo.
   */
  public void escribirNumerosEnArchivo(String archivo) throws IOException {
    FileWriter escritor = new FileWriter(archivo);
    for (int i = 0; i < this.numeros.length; i++) {
      escritor.write(String.valueOf(this.numeros[i] + "\n"));
    }
    escritor.close();
  }

  /**
   * Implementación del algoritmo de ordenamiento QuickSort que ordena un array de
   * números enteros.
   * 
   * @param array      El array de números enteros que se desea ordenar.
   * @param izquierda  El índice del elemento más a la izquierda del array.
   * @param derecha    El índice del elemento más a la derecha del array.
   * @param ascendente Si se desea ordenar el array de forma ascendente o
   *                   descendente.
   */
  private void quickSort(int[] array, int izquierda, int derecha, boolean ascendeten) {
    if (izquierda < derecha) {
      int pivote = particion(array, izquierda, derecha, ascendeten);
      quickSort(array, izquierda, pivote - 1, ascendeten);
      quickSort(array, pivote + 1, derecha, ascendeten);
    }
  }

  /**
   * Método auxiliar que particiona el array en dos sub-arrays alrededor del
   * pivote y retorna el índice del pivote.
   * 
   * @param array      El array de números enteros a particionar.
   * @param izquierda  El índice del elemento más a la izquierda del sub-array a
   *                   particionar.
   * @param derecha    El índice del elemento más a la derecha del sub-array a
   *                   particionar.
   * @param ascendente Si se desea ordenar el array de forma ascendente o
   *                   descendente.
   * @return El índice del pivote.
   */
  private int particion(int[] array, int izquierda, int derecha, boolean ascendente) {
    int pivote = array[derecha];
    int i = izquierda - 1;
    for (int j = izquierda; j < derecha; j++) {
      if ((ascendente && array[j] < pivote) || (!ascendente && array[j] > pivote)) {
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

  /**
   * Ordena los números del modelo utilizando el algoritmo QuickSort.
   * 
   * @param ascendente Si se desea ordenar el array de forma ascendente o
   *                   descendente.
   */
  public void ordenarNumerosQuickSort(boolean ascendente) {
    quickSort(this.numeros, 0, this.numeros.length - 1, ascendente);
  }

  /**
   * Ordena un arreglo de números utilizando el algoritmo de Shell Sort.
   * 
   * @param array      el arreglo de números a ordenar
   * @param ascendente un booleano que indica si se debe ordenar en orden
   *                   ascendente o descendente
   */

  private void shellSort(int[] array, boolean ascendente) {
    int n = array.length;
    for (int gap = n / 2; gap > 0; gap /= 2) {
      for (int i = gap; i < n; i += 1) {
        int temp = array[i];
        int j;
        for (j = i; j >= gap
            && ((ascendente && array[j - gap] > temp) || (!ascendente && array[j - gap] < temp)); j -= gap) {
          array[j] = array[j - gap];
        }
        array[j] = temp;
      }
    }
  }

  /**
   * Ordena los números del modelo utilizando el algoritmo ShellSort.
   * 
   * @param ascendente Si se desea ordenar el array de forma ascendente o
   *                   descendente.
   */

  public void ordenarNumerosShellSort(boolean ascendente) {
    shellSort(this.numeros, ascendente);
  }
}
