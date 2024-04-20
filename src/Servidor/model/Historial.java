package Servidor.model;

import java.util.ArrayList;

public class Historial {
    private String nombreRemitente;
    private ArrayList<String> mensajes;

    public Historial(String nombreRemitente){
        this.nombreRemitente = nombreRemitente;

    }

    public String getNombreRemitente() {
        return nombreRemitente;
    }

    public void setNombreRemitente(String nombreRemitente) {
        this.nombreRemitente = nombreRemitente;
    }

    public ArrayList<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<String> mensajes) {
        this.mensajes = mensajes;
    }
}
