package view;

import java.awt.Font;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.NumerosModel;

public class NumerosVista extends JFrame implements ActionListener {

    private static final String EXTENSION_ARCHIVO = "txt";
    private static final String ARCHIVO_INVALIDO = "Nombre de archivo invalido";
    private static final String MENSAJE_EXITOSO = "Se ha ordenado correctamente";

    private NumerosModel modelo;
    private File archivoSeleccionado;
    private File ruta;

    private JPanel panelContenido;
    private JPanel panelTitulo;

    private JLabel lblTitulo;
    private JLabel lblRutaGuardado;

    private JButton botonExaminarArchivo;
    private JButton botonOrdenarMergeSortAscendente;
    private JButton botonOrdenarMergeSortDescendente;
    private JButton btnAbrir;

    private JTextField txtRuta;

    public NumerosVista() {
        modelo = new NumerosModel();
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Ordenamiento De Datos");
        setSize(480, 186);
        setLocationRelativeTo(null);
        panelContenido = new JPanel();
        panelContenido.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelContenido);
        panelContenido.setLayout(null);

        panelTitulo = new JPanel();
        panelTitulo.setBounds(0, 0, 464, 47);
        panelContenido.add(panelTitulo);

        lblTitulo = new JLabel("Ordenamiento De Datos");
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

        botonOrdenarMergeSortAscendente = new JButton("MergeSortAscendente");
        botonOrdenarMergeSortAscendente.setBounds(5, 111, 175, 25);
        botonOrdenarMergeSortAscendente.addActionListener(this);
        panelContenido.add(botonOrdenarMergeSortAscendente);

        botonOrdenarMergeSortDescendente = new JButton("MergeSort Descendente");
        botonOrdenarMergeSortDescendente.setBounds(185, 111, 175, 25);
        botonOrdenarMergeSortDescendente.addActionListener(this);
        panelContenido.add(botonOrdenarMergeSortDescendente);

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
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto", EXTENSION_ARCHIVO));
        int returnVal = chooser.showOpenDialog(botonExaminarArchivo);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();
            if (archivo != null && archivo.isFile() && archivo.getName().endsWith(EXTENSION_ARCHIVO)) {
                txtRuta.setText(archivo.getAbsolutePath());
                return archivo;
            } else {
                JOptionPane.showMessageDialog(null, ARCHIVO_INVALIDO, ARCHIVO_INVALIDO, JOptionPane.ERROR_MESSAGE);
            }
        }

        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonExaminarArchivo) {
            archivoSeleccionado = examinarArchivo(txtRuta);
        }

        if (e.getSource() == botonOrdenarMergeSortAscendente) {
            try {
                modelo.leerNumerosDesdeArchivo(txtRuta.getText());
                modelo.ordenarNumerosMergeAscendente();
                String nombreArchivoOrdenado = txtRuta.getText().substring(0, txtRuta.getText().lastIndexOf("."))+ "Ordenados.txt";
                modelo.escribirNumerosEnArchivo(nombreArchivoOrdenado);
                lblRutaGuardado.setText(nombreArchivoOrdenado);
                JOptionPane.showMessageDialog(null, "Se ha ordenado correctamente.", "Ordenamiento",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == botonOrdenarMergeSortDescendente) {
            try {
                modelo.leerNumerosDesdeArchivo(ruta.getAbsolutePath());
                modelo.ordenarNumerosMergeDescendente();
                modelo.escribirNumerosEnArchivo(ruta.getAbsolutePath() + "_Ordenados.txt");
                JOptionPane.showMessageDialog(null, "Se ha ordenado correctamente.", "Ordenamiento",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
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
