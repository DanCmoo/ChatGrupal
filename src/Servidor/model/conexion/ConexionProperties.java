package Servidor.model.conexion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author USUARIO
 */
public class ConexionProperties {

    private Properties datosServidor;
    private static InputStream entrada;


    public ConexionProperties(File archivo) throws FileNotFoundException {
        datosServidor = new Properties();
        entrada = new FileInputStream(archivo);

    }
    public void cargarDatosIniciales() throws IOException{
        datosServidor.load(entrada);


    }

    public Properties getDatosServidor() {
        return datosServidor;
    }

    public InputStream getEntrada() {
        return entrada;
    }
}
