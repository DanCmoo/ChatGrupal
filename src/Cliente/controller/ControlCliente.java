package Cliente.controller;

import Cliente.view.Chat;

import Cliente.model.Cliente;
import Servidor.model.conexion.ConexionProperties;

import java.io.*;
import java.net.Socket;

public class ControlCliente {
    private DataInputStream entradaComunicacion;
    private DataOutputStream salida;
    private DataInputStream entradaMensaje;
    private Chat vistaChat;
    private Cliente cliente;
    private int puertoComunicacion;
    private int puertoMensaje;
    private String ipServidor;
    private ConexionProperties conexionProperties;


    public ControlCliente() {
        vistaChat = new Chat();
        cargarDatos();
        conexion();

    }

    public void cargarDatos() {
        try {
            conexionProperties = new ConexionProperties(vistaChat.pedirArchivo("Archivo de propiedades del servidor", "properties"));
            conexionProperties.cargarDatosIniciales();
            ipServidor = conexionProperties.getDatosServidor().getProperty("cliente.ipServidor");
            puertoComunicacion = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("cliente.puertoComunicacion"));
            puertoMensaje = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("cliente.puertoMensaje"));
            vistaChat.mostrarJOptionPane("Las propiedades han sido cargadas con exito");
        } catch (FileNotFoundException e) {
            vistaChat.mostrarJOptionPane("El archivo no se ha encontrado");
        } catch (IOException e) {
            vistaChat.mostrarJOptionPane("El archivo no se ha leído correctamente");
        }
    }

    public void conexion() {

        try {
            cliente = new Cliente(new Socket(ipServidor, puertoComunicacion), new Socket(ipServidor, puertoMensaje));
            entradaComunicacion = new DataInputStream(cliente.getSocketComunication().getInputStream());
            salida = new DataOutputStream(cliente.getSocketComunication().getOutputStream());
            entradaMensaje = new DataInputStream(cliente.getSocketMensaje().getInputStream());
            String nomCliente = vistaChat.pedirNombreUsusario("Introducir Nick :");
            //vent.setNombreUser(nomCliente);
            //salida.writeUTF(nomCliente);
            ControlUsuario usuario = new ControlUsuario(entradaMensaje, this);
            usuario.start();

        } catch (IOException e) {
            vistaChat.mostrarJOptionPane("\tEl servidor no esta levantado");
        }


    }

    public boolean verificarNombre(String nombre) {
        boolean valor = false;
        return valor;
    }

    public void mensaje(String mensaje) {
        try {
            salida.writeInt(1);
            salida.writeUTF(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mensaje(String mensaje, String nombreAmigo) {
        try {
            salida.writeInt(3);
            salida.writeUTF(nombreAmigo);
            salida.writeUTF(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void agregarUsuario(String nombre) {
        // Metodo de la vista para agregar
    }
}
