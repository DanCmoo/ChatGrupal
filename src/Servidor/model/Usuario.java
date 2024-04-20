package Servidor.model;

import java.net.Socket;
import java.util.ArrayList;

public class Usuario {
    private String nombre;

    private ArrayList<Historial> historiales;

    public Usuario(String nombre) {
        this.nombre = nombre;
        historiales = new ArrayList<Historial>();
    }

    public Usuario() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Historial> getHistoriales() {
        return historiales;
    }

    public void setHistoriales(ArrayList<Historial> historiales) {
        this.historiales = historiales;
    }
}
