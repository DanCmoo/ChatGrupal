package Servidor.controller;

import Servidor.model.Servidor;
import Servidor.model.Usuario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ControlUsuario extends Thread{
    public static ArrayList<ControlUsuario> usuariosActivos = new ArrayList<ControlUsuario>();
    private ControlServidor controlServidor;
    private Socket socketComunicacion;
    private Socket socketMensaje;
    private DataInputStream entrada;
    private DataOutputStream salidaComunicacion;
    private DataOutputStream salidaMensaje;
    private Usuario usuarioActual;

    public ControlUsuario(ControlServidor controlServidor,Socket socketComunicacion,Socket socketMensaje){
        this.controlServidor = controlServidor;
        this.socketComunicacion = socketComunicacion;
        this.socketMensaje = socketMensaje;
        usuarioActual = new Usuario();
        usuariosActivos.add(this);
        this.controlServidor.getVistaServidor().mostrarMensaje("Cliente agregado"+ this);

    }

    public void run(){
        controlServidor.getVistaServidor().mostrarMensaje("Esperando mensajes...");
        try{
            entrada = new DataInputStream(socketComunicacion.getInputStream());
            salidaComunicacion = new DataOutputStream((socketComunicacion.getOutputStream()));
            salidaMensaje = new DataOutputStream(socketMensaje.getOutputStream());
            usuarioActual.setNombre(entrada.readUTF());
            enviaUsuariosActivos();

            while (true){
                try{
                    acciones();
                }catch (IOException ex){
                    controlServidor.getVistaServidor().mostrarMensaje("El usuario se ha desconectado");
                    break;
                }
            }
            controlServidor.getVistaServidor().mostrarMensaje("Se removió un usuario");
            usuariosActivos.remove(this);
            try{
                socketComunicacion.close();
            } catch (Exception e) {
                controlServidor.getVistaServidor().mostrarMensaje("Ha ocurrido un error al cerrar el socket");

            }


        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void enviaUsuariosActivos() {
        ControlUsuario usuario = null;
        for (int i = 0; i < usuariosActivos.size(); i++) {
            try{
                usuario = usuariosActivos.get(i);
                if(usuario!=this){
                    usuario.getSalidaMensaje().writeInt(2);
                    usuario.getSalidaMensaje().writeUTF(this.usuarioActual.getNombre());
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
            
        }
    }

    public void acciones() throws IOException{
        int opcion=0,cantidadUsuarios=0;
        String nombreAmigo="",mensaje="";
        opcion = entrada.readInt();
        switch (opcion){
            case 1:
                mensaje=entrada.readUTF();
                controlServidor.getVistaServidor().mostrarMensaje("mensaje recibido "+mensaje);
                enviaMensaje(mensaje);
                break;
            case 2:
                cantidadUsuarios=usuariosActivos.size();
                salidaComunicacion.writeInt(cantidadUsuarios);
                for(int i=0;i<cantidadUsuarios;i++)
                    salidaComunicacion.writeUTF(usuariosActivos.get(i).getUsuarioActual().getNombre());
                break;
            case 3:
                nombreAmigo=entrada.readUTF();
                mensaje=entrada.readUTF();
                enviaMensaje(nombreAmigo,mensaje);
                break;
        }
    }

    private void enviaMensaje(String mensaje) {
        ControlUsuario usuario=null;
        for(int i=0;i<usuariosActivos.size();i++)
        {
            controlServidor.getVistaServidor().mostrarMensaje("MENSAJE DEVUELTO:"+mensaje);
            try {
                usuario=usuariosActivos.get(i);
                usuario.getSalidaMensaje().writeInt(1);
                usuario.getSalidaMensaje().writeUTF(""+this.usuarioActual.getNombre()+" > "+ mensaje);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void enviaMensaje(String nombreAmigo,String mensaje) {
        ControlUsuario usuario=null;
        for(int i=0;i<usuariosActivos.size();i++)
        {
            try
            {
                usuario=usuariosActivos.get(i);
                if(usuario.getUsuarioActual().getNombre().equals(nombreAmigo))
                {
                    usuario.getSalidaMensaje().writeInt(3);//opcion de mensage amigo
                    usuario.getSalidaMensaje().writeUTF(this.usuarioActual.getNombre());
                    usuario.getSalidaMensaje().writeUTF(""+this.usuarioActual.getNombre()+" > "+mensaje);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public Socket getSocketComunicacion() {
        return socketComunicacion;
    }

    public Socket getSocketMensaje() {
        return socketMensaje;
    }

    public ControlServidor getControlServidor() {
        return controlServidor;
    }

    public DataInputStream getEntrada() {
        return entrada;
    }

    public DataOutputStream getSalidaComunicacion() {
        return salidaComunicacion;
    }

    public DataOutputStream getSalidaMensaje() {
        return salidaMensaje;
    }
}
