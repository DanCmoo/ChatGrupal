package Cliente.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class ChatGrupal extends JFrame {

    public File pedirArchivo(String textoCaracteristicas, String textoFiltro) throws IOException {
        JFileChooser seleccionArchivo = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(textoCaracteristicas, textoFiltro);
        seleccionArchivo.setFileFilter(filtro);
        seleccionArchivo.showOpenDialog(null);
        return seleccionArchivo.getSelectedFile();
    }

    public void mostrarJOptionPane(String m) {
        JOptionPane.showMessageDialog(null, m);
    }
    public String pedirNombreUsusario(String texto){
        return JOptionPane.showInputDialog(null,texto);

    }
}
