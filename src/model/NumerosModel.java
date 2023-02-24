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

    /*
     * Metodo de Ordenamiento MergeSort Ascendente - Divide y venceras
     */

    private void mergeSortAscendente(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSortAscendente(arr, l, m);
            mergeSortAscendente(arr, m + 1, r);
            mergeAscendente(arr, l, m, r);
        }
    }

    private void mergeAscendente(int[] arr, int l, int m, int r) {
        int nl = m - l + 1;
        int nr = r - m;
        int[] left = new int[nl];
        int[] right = new int[nr];
        for (int i = 0; i < nl; i++) {
            left[i] = arr[l + i];
        }
        for (int j = 0; j < nr; j++) {
            right[j] = arr[m + 1 + j];
        }
        int i = 0, j = 0, k = l;
        while (i < nl && j < nr) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < nl) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < nr) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    /*
     * Ordenamiento descendente
     */

    private void mergeSortDescendente(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSortDescendente(arr, l, m);
            mergeSortDescendente(arr, m + 1, r);
            mergeDescendente(arr, l, m, r);
        }
    }

    private void mergeDescendente(int[] arr, int l, int m, int r) {
        int nl = m - l + 1;
        int nr = r - m;
        int[] left = new int[nl];
        int[] right = new int[nr];
        for (int i = 0; i < nl; i++) {
            left[i] = arr[l + i];
        }
        for (int j = 0; j < nr; j++) {
            right[j] = arr[m + 1 + j];
        }
        int i = 0, j = 0, k = l;
        while (i < nl && j < nr) {
            if (left[i] >= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < nl) {
            arr[k] = left[i];
            i++;
            k++;
        }
        while (j < nr) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
    /*
     * Metodo burbuja
     */

    public void ordernarNumerosBurbuja(boolean ascendente) {
        int n = numeros.length;
        int temp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (ascendente) {
                    if (numeros[j - 1] > numeros[j]) {
                        temp = numeros[j - 1];
                        numeros[j - 1] = numeros[j];
                        numeros[j] = temp;
                    }
                } else {
                    if (numeros[j - 1] < numeros[j]) {
                        temp = numeros[j - 1];
                        numeros[j - 1] = numeros[j];
                        numeros[j] = temp;
                    }
                }
            }
        }
    }

    public void ordenarNumerosMergeAscendente() {
        mergeSortAscendente(this.numeros, 0, this.numeros.length - 1);
    }

    public void ordenarNumerosMergeDescendente() {
        mergeSortDescendente(this.numeros, 0, this.numeros.length - 1);
    }

    public void escribirNumerosEnArchivo(String archivo) throws IOException {
        FileWriter escritor = new FileWriter(archivo);
        for (int i = 0; i < this.numeros.length; i++) {
            escritor.write(String.valueOf(this.numeros[i] + "\n"));
        }
        escritor.close();
    }

}
