package view;

import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.NumerosModel;

public class NumerosVista extends JFrame implements ActionListener {

  private static final String EXTENSION_ARCHIVO = "txt";
  private static final String MENSAJE_EXITOSO = "Se ha ordenado correctamente";
  private static final String[] OPCIONES = { "QuickSort", "ShellSort" };

  private Desktop desktop;

  private NumerosModel modelo;
  private File archivoSeleccionado;

  private JPanel panelContenido;
  private JPanel panelTitulo;

  private JLabel lblTitulo;
  private JLabel lblRutaGuardado;

  private JButton botonExaminarArchivo;
  private JButton botonOrdenarAscendentemente;
  private JButton botonOrdenarDescendentemente;
  private JButton botonAbrirArchivo;

  private JComboBox<String> comboBox;

  private JTextField txtRuta;

  public NumerosVista() {
    modelo = new NumerosModel();
    desktop = Desktop.getDesktop();
    iniciarComponentes();
  }

  /**
   * Inicializa los componentes de la interfaz gráfica.
   */

  private void iniciarComponentes() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Ordenamiento de datos");
    setSize(480, 200);
    setLocationRelativeTo(null);
    panelContenido = new JPanel();
    panelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(panelContenido);
    panelContenido.setLayout(null);

    panelTitulo = new JPanel();
    panelTitulo.setBounds(0, 0, 464, 47);
    panelContenido.add(panelTitulo);

    lblTitulo = new JLabel("Ordenamiento de datos");
    lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 35));
    lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
    panelTitulo.add(lblTitulo);

    txtRuta = new JTextField();
    txtRuta.setBounds(5, 58, 355, 25);
    panelContenido.add(txtRuta);
    txtRuta.setColumns(10);

    botonExaminarArchivo = new JButton("Examinar");
    botonExaminarArchivo.setBounds(368, 58, 89, 25);
    botonExaminarArchivo.addActionListener(this);
    panelContenido.add(botonExaminarArchivo);

    comboBox = new JComboBox<>(OPCIONES);
    comboBox.setBounds(5, 115, 100, 25);
    panelContenido.add(comboBox);

    botonOrdenarAscendentemente = new JButton("Ascendente");
    botonOrdenarAscendentemente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    botonOrdenarAscendentemente.setBounds(120, 115, 150, 25);
    botonOrdenarAscendentemente.addActionListener(this);
    panelContenido.add(botonOrdenarAscendentemente);

    botonOrdenarDescendentemente = new JButton("Descendente");
    botonOrdenarDescendentemente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    botonOrdenarDescendentemente.setBounds(285, 115, 150, 25);
    botonOrdenarDescendentemente.addActionListener(this);
    panelContenido.add(botonOrdenarDescendentemente);

    lblRutaGuardado = new JLabel("");
    lblRutaGuardado.setBounds(10, 83, 355, 25);
    panelContenido.add(lblRutaGuardado);

    botonAbrirArchivo = new JButton("Abrir");
    botonAbrirArchivo.setBounds(368, 84, 89, 25);
    botonAbrirArchivo.addActionListener(this);
    panelContenido.add(botonAbrirArchivo);
  }

  /**
   * Abre un diálogo para seleccionar un archivo de texto y devuelve el archivo
   * seleccionado.
   * 
   * @return El archivo seleccionado.
   * @throws IOException si el archivo seleccionado no cumple con el filtro
   *                     establecido.
   */

  private File examinarArchivo() throws IOException {
    JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto", EXTENSION_ARCHIVO);
    chooser.setFileFilter(filtro);
    int returnVal = chooser.showOpenDialog(botonExaminarArchivo);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File archivo = chooser.getSelectedFile();
      if (filtro.accept(archivo)) {
        txtRuta.setText(archivo.getAbsolutePath());
        return archivo;
      } else {
        throw new IOException("Archivo inválido");
      }
    }
    return null;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    /**
     * Maneja eventos de acción para el botón de examinar archivo.
     * Invoca el método examinarArchivo() para permitir al usuario seleccionar un
     * archivo.
     */
    if (e.getSource() == botonExaminarArchivo) {
      try {
        archivoSeleccionado = examinarArchivo();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }

    /**
     * Este bloque de código se ejecuta cuando el usuario presiona el botón para
     * ordenar los números en orden ascendente
     */

    if (e.getSource() == botonOrdenarAscendentemente) {
      String opcionSeleccionada = (String) comboBox.getSelectedItem();
      String nombreArchivoOrdenado = "Numeros ordenados ascendentemente." + EXTENSION_ARCHIVO;
      switch (opcionSeleccionada) {
        case "QuickSort":
          try {
            modelo.leerNumerosDesdeArchivo(txtRuta.getText());
            modelo.ordenarNumerosQuickSort(true);
            modelo.escribirNumerosEnArchivo(nombreArchivoOrdenado);
            lblRutaGuardado.setText(nombreArchivoOrdenado);
            JOptionPane.showMessageDialog(null, MENSAJE_EXITOSO, "Ordenamiento", JOptionPane.INFORMATION_MESSAGE);
          } catch (IOException ex) {
            ex.printStackTrace();
          }
          break;
        case "ShellSort":
          try {
            modelo.leerNumerosDesdeArchivo(txtRuta.getText());
            modelo.ordenarNumerosShellSort(true);
            modelo.escribirNumerosEnArchivo(nombreArchivoOrdenado);
            lblRutaGuardado.setText(nombreArchivoOrdenado);
            JOptionPane.showMessageDialog(null, MENSAJE_EXITOSO, "Ordenamiento", JOptionPane.INFORMATION_MESSAGE);
          } catch (IOException ex) {
            ex.printStackTrace();
          }
          break;
      }
    }

    /**
     * Este bloque de código se ejecuta cuando el usuario presiona el botón para
     * ordenar los números en orden descendente
     */

    if (e.getSource() == botonOrdenarDescendentemente) {
      String opcionSeleccionada = (String) comboBox.getSelectedItem();
      String nombreArchivoOrdenado = "Numeros ordenados descendentemente." + EXTENSION_ARCHIVO;
      switch (opcionSeleccionada) {
        case "QuickSort":
          try {
            modelo.leerNumerosDesdeArchivo(txtRuta.getText());
            modelo.ordenarNumerosQuickSort(false);
            modelo.escribirNumerosEnArchivo(nombreArchivoOrdenado);
            lblRutaGuardado.setText(nombreArchivoOrdenado);
            JOptionPane.showMessageDialog(null, MENSAJE_EXITOSO, "Ordenamiento", JOptionPane.INFORMATION_MESSAGE);
          } catch (IOException ex) {
            ex.printStackTrace();
          }
          break;
        case "ShellSort":
          try {
            modelo.leerNumerosDesdeArchivo(txtRuta.getText());
            modelo.ordenarNumerosShellSort(false);
            modelo.escribirNumerosEnArchivo(nombreArchivoOrdenado);
            lblRutaGuardado.setText(nombreArchivoOrdenado);
            JOptionPane.showMessageDialog(null, MENSAJE_EXITOSO, "Ordenamiento", JOptionPane.INFORMATION_MESSAGE);
          } catch (IOException ex) {
            ex.printStackTrace();
          }
          break;
      }
    }

    /**
     * Maneja el evento de hacer clic en el botón "Abrir archivo".
     * Abre el archivo seleccionado en la aplicación predeterminada del sistema
     * operativo utilizando la clase "Desktop".
     */

    if (e.getSource() == botonAbrirArchivo) {
      try {
        archivoSeleccionado = examinarArchivo();
        desktop.open(archivoSeleccionado);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }
}
