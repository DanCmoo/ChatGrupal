package Servidor.model;

import java.net.Socket;
import java.util.ArrayList;

public class Usuario {
    private String nombre;


    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public Usuario() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



}
