package Cliente.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente {
    private Socket socketComunication ;// Para realizar la comunicación
    private Socket socketMensaje ; //Para recibir los mensajes

    public Cliente(Socket comunication, Socket mensaje){
        this.socketComunication = comunication ;
        this.socketMensaje = mensaje ;
    }

    public Socket getSocketComunication() {
        return socketComunication;
    }

    public void setSocketComunication(Socket socketComunication) {
        this.socketComunication = socketComunication;
    }

    public Socket getSocketMensaje() {
        return socketMensaje;
    }

    public void setSocketMensaje(Socket socketMensaje) {
        this.socketMensaje = socketMensaje;
    }
}
