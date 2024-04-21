package Cliente.model.conexion;

import java.io.*;
import java.util.Properties;

public class ConexionProperties {

    private Properties datosServidor;
    private static InputStream entrada;


    public ConexionProperties(File archivo) throws FileNotFoundException {
        datosServidor = new Properties();
        entrada = new FileInputStream(archivo);

    }
    public void cargarDatosIniciales() throws IOException {
        datosServidor.load(entrada);


    }

    public Properties getDatosServidor() {
        return datosServidor;
    }

    public InputStream getEntrada() {
        return entrada;
    }
}
