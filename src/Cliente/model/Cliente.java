package Cliente.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente {
    private DataInputStream entrada = null;
    private DataOutputStream salida = null;
    private DataInputStream entrada2 = null;
    private Socket comunication = null;// Para realizar la comunicación
    private Socket comunication2 = null; //Para recibir los mensajes

    public Cliente(){

    }

}
