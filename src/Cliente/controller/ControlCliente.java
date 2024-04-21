package Cliente.controller;

import Cliente.view.ChatGrupal;

import Cliente.model.Cliente;
import Servidor.model.conexion.ConexionProperties;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class ControlCliente {
    private DataInputStream entradaComunicacion;
    private DataOutputStream salida;
    private DataInputStream entradaMensaje;
    private ChatGrupal vistaChatGrupal;
    private Cliente cliente;
    private int puertoComunicacion;
    private int puertoMensaje;
    private String ipServidor;
    private ConexionProperties conexionProperties;


    public ControlCliente() {
        vistaChatGrupal = new ChatGrupal();
        cargarDatos();
        conexion();

    }

    public void cargarDatos() {
        try {
            conexionProperties = new ConexionProperties(vistaChatGrupal.pedirArchivo("Archivo de propiedades del servidor", "properties"));
            conexionProperties.cargarDatosIniciales();
            ipServidor = conexionProperties.getDatosServidor().getProperty("cliente.ipServidor");
            puertoComunicacion = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("cliente.puertoComunicacion"));
            puertoMensaje = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("cliente.puertoMensaje"));
            vistaChatGrupal.mostrarJOptionPane("Las propiedades han sido cargadas con exito");
        } catch (FileNotFoundException e) {
            vistaChatGrupal.mostrarJOptionPane("El archivo no se ha encontrado");
        } catch (IOException e) {
            vistaChatGrupal.mostrarJOptionPane("El archivo no se ha leído correctamente");
        }
    }

    public void conexion() {

        try {
            cliente = new Cliente(new Socket(ipServidor, puertoComunicacion), new Socket(ipServidor, puertoMensaje));
            entradaComunicacion = new DataInputStream(cliente.getSocketComunication().getInputStream());
            salida = new DataOutputStream(cliente.getSocketComunication().getOutputStream());
            entradaMensaje = new DataInputStream(cliente.getSocketMensaje().getInputStream());
            String nomCliente = vistaChatGrupal.pedirNombreUsusario("Introducir Nick :");
            //vent.setNombreUser(nomCliente);
            //salida.writeUTF(nomCliente);
            ControlUsuario usuario=new ControlUsuario(entradaMensaje,this);

        } catch (IOException e) {
            vistaChatGrupal.mostrarJOptionPane("\tEl servidor no esta levantado");
        }


    }

}
