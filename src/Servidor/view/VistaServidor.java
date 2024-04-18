package Servidor.view;

import Servidor.controller.ControlServidor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class VistaServidor {
    private ControlServidor controlServidor;
    public VistaServidor(ControlServidor c){
        this.controlServidor = c;
    }

    public File pedirArchivo(String textoCaracteristicas, String textoFiltro) throws IOException {
        JFileChooser seleccionArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(textoCaracteristicas, textoFiltro);
        seleccionArchivo.setFileFilter(filtro);
        seleccionArchivo.showOpenDialog(null);
        return seleccionArchivo.getSelectedFile();
    }

    public void mostrarJOptionPane(String m){
        JOptionPane.showMessageDialog(null,m);

    }
}
