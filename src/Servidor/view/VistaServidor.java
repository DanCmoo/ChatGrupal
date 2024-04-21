package Servidor.view;

import Servidor.controller.ControlServidor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class VistaServidor extends JFrame{
    public JScrollPane panelScroll;
    public JTextArea cajaTexto;
    private ControlServidor controlServidor;

    public VistaServidor(ControlServidor c,String title){
        super(title);
        this.controlServidor = c;
        setSize(1200,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    protected void frameInit() {
        super.frameInit();

        cajaTexto = new JTextArea();

        panelScroll = new JScrollPane(cajaTexto);
        add(panelScroll);



    }

    public File pedirArchivo(String textoCaracteristicas, String textoFiltro) throws IOException {
        JFileChooser seleccionArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(textoCaracteristicas, textoFiltro);
        seleccionArchivo.setFileFilter(filtro);
        seleccionArchivo.showOpenDialog(null);
        return seleccionArchivo.getSelectedFile();
    }

    public void mostrarMensaje(String m){
        cajaTexto.append(m + "\n");

    }

    public void mostrarJOptionPane(String m) {
        JOptionPane.showMessageDialog(null,m);
    }

}
