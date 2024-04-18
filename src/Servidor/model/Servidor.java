package Servidor.model;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private ServerSocket servidorComunicacion;
    private ServerSocket servidorMensaje;
    private boolean escuchando = false;
    private Socket socketComunicacion;
    private Socket socketMensaje;

    public Servidor(ServerSocket servidorComunicacion, ServerSocket servidorMensaje){
        this.servidorComunicacion=servidorComunicacion;
        this.servidorMensaje=servidorMensaje;
        this.socketComunicacion = null;
        this.socketMensaje = null;

    }

    public ServerSocket getServidorComunicacion() {
        return servidorComunicacion;
    }

    public void setServidorComunicacion(ServerSocket servidorComunicacion) {
        this.servidorComunicacion = servidorComunicacion;
    }

    public ServerSocket getServidorMensaje() {
        return servidorMensaje;
    }

    public void setServidorMensaje(ServerSocket servidorMensaje) {
        this.servidorMensaje = servidorMensaje;
    }

    public boolean isEscuchando() {
        return escuchando;
    }

    public void setEscuchando(boolean escuchando) {
        this.escuchando = escuchando;
    }

    public Socket getSocketComunicacion() {
        return socketComunicacion;
    }

    public void setSocketComunicacion(Socket socketComunicacion) {
        this.socketComunicacion = socketComunicacion;
    }

    public Socket getSocketMensaje() {
        return socketMensaje;
    }

    public void setSocketMensaje(Socket socketMensaje) {
        this.socketMensaje = socketMensaje;
    }
}
