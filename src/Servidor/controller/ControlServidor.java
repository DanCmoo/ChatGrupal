package Servidor.controller;

import Servidor.model.Servidor;
import Servidor.model.conexion.ConexionProperties;
import Servidor.view.VistaServidor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ControlServidor {
    private Servidor servidor;
    private int puertoComunicacion;
    private int puertoMensaje;
    private VistaServidor vistaServidor;
    private ConexionProperties conexionProperties;
    private ArrayList<String> palabrasBaneadas;

    public ControlServidor(){
        vistaServidor= new VistaServidor(this,"Consola del servidor");
        cargarDatos();
        iniciarServidor();


    }

    public void cargarDatos(){
        try{
            conexionProperties = new ConexionProperties(vistaServidor.pedirArchivo("Archivo de propiedades del servidor","properties"));
            conexionProperties.cargarDatosIniciales();
            puertoComunicacion = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("servidor.puertoComunicacion"));
            puertoMensaje = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("servidor.puertoMensaje"));
            palabrasBaneadas =  new ArrayList<>(Arrays.asList(conexionProperties.getDatosServidor().getProperty("servidor.palabrasBaneadas").split(",")));
            vistaServidor.mostrarJOptionPane("Las propiedades han sido cargadas con exito");
        }catch (FileNotFoundException e) {
            vistaServidor.mostrarJOptionPane("El archivo no se ha encontrado");
        } catch (IOException e) {
            vistaServidor.mostrarJOptionPane("El archivo no se ha leído correctamente");
        }
    }

    public void iniciarServidor(){
        try {
            servidor = new Servidor(new ServerSocket(puertoComunicacion),new ServerSocket(puertoMensaje));
            servidor.setEscuchando(true);
            vistaServidor.mostrarMensaje("SERVIDOR ACTIVADO");
            while (servidor.isEscuchando()){
                try{
                    vistaServidor.mostrarMensaje("ESPERANDO USUARIOS");
                    Socket comunicacion = servidor.getServidorComunicacion().accept();
                    Socket mensaje = servidor.getServidorMensaje().accept();
                    servidor.setSocketComunicacion(comunicacion);
                    servidor.setSocketMensaje(mensaje);
                }catch (IOException ex2){
                    ex2.printStackTrace();
                }
                ControlUsuario controlUsuario = new ControlUsuario(this,servidor.getSocketComunicacion(),servidor.getSocketMensaje());
                controlUsuario.start();

            }
        } catch (IOException ex1){
            ex1.printStackTrace();
        }

    }

    public VistaServidor getVistaServidor() {
        return vistaServidor;
    }

    public ArrayList<String> getPalabrasBaneadas() {
        return palabrasBaneadas;
    }
}
