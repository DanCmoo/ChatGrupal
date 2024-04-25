package Cliente.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class Vista {

    public Vista(){

    }

    public File pedirArchivo(String textoCaracteristicas, String textoFiltro) throws IOException {
        JFileChooser seleccionArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(textoCaracteristicas, textoFiltro);
        seleccionArchivo.setFileFilter(filtro);
        seleccionArchivo.showOpenDialog(null);
        return seleccionArchivo.getSelectedFile();
    }

    public String autores(){
        return "Hecho por:" + "\nDaniel Esteban Camacho Ospina" + "\nGiovanni Alexander Vargas Castañeda" + "\nEdwin Alejandro Orjuela Olarte";
    }
    public String pedirNombreUsusario(String texto){
        return JOptionPane.showInputDialog(null,texto);

    }

    public void mostrarJOptionPane(String s) {
        JOptionPane.showMessageDialog(null,s);
    }
}
