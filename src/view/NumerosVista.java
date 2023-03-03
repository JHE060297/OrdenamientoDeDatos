package view;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.NumerosModel;

public class NumerosVista extends JFrame implements ActionListener {

  private static final String EXTENSION_ARCHIVO = "txt";
  private static final String ARCHIVO_INVALIDO = "Nombre de archivo invalido";
  private static final String MENSAJE_EXITOSO = "Se ha ordenado correctamente";
  private static final String[] OPCIONES = { "QuickSort", "ShellSort" };

  private NumerosModel modelo;
  private File archivoSeleccionado;
  private File ruta;

  private JPanel panelContenido;
  private JPanel panelTitulo;

  private JLabel lblTitulo;
  private JLabel lblRutaGuardado;

  private JButton botonExaminarArchivo;
  private JButton botonOrdenarAscendente;
  private JButton botonOrdenarDescendente;
  private JButton btnAbrir;

  private JComboBox<String> comboBox;

  private JTextField txtRuta;

  public NumerosVista() {
    modelo = new NumerosModel();
    iniciarComponentes();
  }

  private void iniciarComponentes() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Ordenamiento de datos");
    setSize(480, 225);
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
    comboBox.setBounds(125, 115, 100, 25);
    panelContenido.add(comboBox);

    botonOrdenarAscendente = new JButton("Ascendente");
    botonOrdenarAscendente.setBounds(5, 150, 175, 25);
    botonOrdenarAscendente.addActionListener(this);
    panelContenido.add(botonOrdenarAscendente);

    botonOrdenarDescendente = new JButton("Descendente");
    botonOrdenarDescendente.setBounds(185, 150, 175, 25);
    botonOrdenarDescendente.addActionListener(this);
    panelContenido.add(botonOrdenarDescendente);

    lblRutaGuardado = new JLabel("");
    lblRutaGuardado.setBounds(10, 83, 355, 25);
    panelContenido.add(lblRutaGuardado);

    btnAbrir = new JButton("Abrir");
    btnAbrir.setBounds(368, 84, 89, 25);
    btnAbrir.addActionListener(this);
    panelContenido.add(btnAbrir);
  }

  private File examinarArchivo(JTextField txtRuta) {
    JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    chooser.setFileFilter(
      new FileNameExtensionFilter("Archivos de texto", EXTENSION_ARCHIVO)
    );
    int returnVal = chooser.showOpenDialog(botonExaminarArchivo);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File archivo = chooser.getSelectedFile();
      if (
        archivo != null &&
        archivo.isFile() &&
        archivo.getName().endsWith(EXTENSION_ARCHIVO)
      ) {
        txtRuta.setText(archivo.getAbsolutePath());
        return archivo;
      } else {
        JOptionPane.showMessageDialog(
          null,
          ARCHIVO_INVALIDO,
          ARCHIVO_INVALIDO,
          JOptionPane.ERROR_MESSAGE
        );
      }
    }

    return null;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == botonExaminarArchivo) {
      archivoSeleccionado = examinarArchivo(txtRuta);
    }

    if (e.getSource() == botonOrdenarAscendente) {
      String opcionSeleccionada = (String) comboBox.getSelectedItem();
      switch (opcionSeleccionada) {
        case "QuickSort":
          try {
            modelo.leerNumerosDesdeArchivo(txtRuta.getText());
            modelo.ordenarNumerosQuickSort(true);
            String nombreArchivoOrdenado =
              txtRuta
                .getText()
                .substring(0, txtRuta.getText().lastIndexOf(".")) +
              "Ordenados Ascendentemente.txt";
            modelo.escribirNumerosEnArchivo(nombreArchivoOrdenado);
            lblRutaGuardado.setText(nombreArchivoOrdenado);
            JOptionPane.showMessageDialog(
              null,
              MENSAJE_EXITOSO,
              "Ordenamiento",
              JOptionPane.INFORMATION_MESSAGE
            );
          } catch (IOException ex) {
            ex.printStackTrace();
          }
          break;
        case "ShellSort":
          // LOGICA
          break;
      }
    }

    if (e.getSource() == botonOrdenarDescendente) {
      String opcionSeleccionada = (String) comboBox.getSelectedItem();
      switch (opcionSeleccionada) {
        case "QuickSort":
          try {
            modelo.leerNumerosDesdeArchivo(txtRuta.getText());
            modelo.ordenarNumerosQuickSort(false);
            String nombreArchivoOrdenado =
              txtRuta
                .getText()
                .substring(0, txtRuta.getText().lastIndexOf(".")) +
              "Ordenados descendentemente.txt";
            modelo.escribirNumerosEnArchivo(nombreArchivoOrdenado);
            lblRutaGuardado.setText(nombreArchivoOrdenado);
            JOptionPane.showMessageDialog(
              null,
              MENSAJE_EXITOSO,
              "Ordenamiento",
              JOptionPane.INFORMATION_MESSAGE
            );
          } catch (IOException ex) {
            ex.printStackTrace();
          }
          break;
          case "ShellSort":
          break;
      }
    }

    if (e.getSource() == btnAbrir) {
      if (Desktop.isDesktopSupported()) {
        try {
          File archivo = new File(ruta.getAbsolutePath() + "_Ordenados.txt");
          Desktop.getDesktop().open(archivo);
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    NumerosVista vista = new NumerosVista();
    vista.setVisible(true);
  }
}
