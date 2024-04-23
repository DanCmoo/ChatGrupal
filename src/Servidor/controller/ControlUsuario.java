package Servidor.controller;

import Servidor.model.Servidor;
import Servidor.model.Usuario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

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

        this.controlServidor.getVistaServidor().mostrarMensaje("Cliente agregado"+ this);

    }

    public void run(){
        controlServidor.getVistaServidor().mostrarMensaje("Esperando mensajes...");
        try{
            entrada = new DataInputStream(socketComunicacion.getInputStream());
            salidaComunicacion = new DataOutputStream((socketComunicacion.getOutputStream()));
            salidaMensaje = new DataOutputStream(socketMensaje.getOutputStream());
            String nombreUsuario = entrada.readUTF();
            while(verificarNombre(nombreUsuario)){
                salidaComunicacion.writeBoolean(true);
                nombreUsuario = entrada.readUTF();
            }
            salidaComunicacion.writeBoolean(false);
            usuarioActual.setNombre(nombreUsuario);
            usuariosActivos.add(this);
            controlServidor.getVistaServidor().mostrarMensaje("El usuario "+nombreUsuario+" se ha conecatado");
            enviaUsuariosActivos();
            recibirUsuariosActivos();

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
            actualizaUsuariosActivos();
            try{
                socketComunicacion.close();
            } catch (Exception e) {
                controlServidor.getVistaServidor().mostrarMensaje("Ha ocurrido un error al cerrar el socket");

            }


        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void actualizaUsuariosActivos() {
        ControlUsuario usuario = null;
        for (int i = 0; i < usuariosActivos.size(); i++) {
            try{
                usuario = usuariosActivos.get(i);
                usuario.getSalidaMensaje().writeInt(4);
                usuario.getSalidaMensaje().writeUTF(this.usuarioActual.getNombre());

            }catch (IOException ex){
                ex.printStackTrace();
            }

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
    public void recibirUsuariosActivos(){
        ControlUsuario usuario = null;
        for (int i = 0; i < usuariosActivos.size(); i++) {
            try{
                usuario = usuariosActivos.get(i);
                if(usuario!=this){
                    this.getSalidaMensaje().writeInt(2);
                    this.getSalidaMensaje().writeUTF(usuario.getUsuarioActual().getNombre());
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
                if(!verificaMensaje(mensaje)){
                    enviaMensaje(mensaje);
                }

                break;
            case 2:
                cantidadUsuarios=usuariosActivos.size();
                salidaComunicacion.writeInt(2);
                for(int i=0;i<cantidadUsuarios;i++)
                    salidaComunicacion.writeUTF(usuariosActivos.get(i).getUsuarioActual().getNombre());
                break;
            case 3:
                String nombre = entrada.readUTF();
                nombreAmigo=entrada.readUTF();
                mensaje=entrada.readUTF();
                if(!verificaMensaje(mensaje)){
                    enviaMensaje(nombre,nombreAmigo,mensaje);
                }
                break;
            case 4:
                nombreAmigo = entrada.readUTF();
                ControlUsuario usuario = null;
                for (int i = 0; i < usuariosActivos.size(); i++) {
                    try{
                        usuario = usuariosActivos.get(i);
                        if(usuario==this){
                            usuario.getSalidaMensaje().writeInt(2);
                            usuario.getSalidaMensaje().writeUTF(nombreAmigo);
                        }
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }

                }

        }
    }

    private void enviaMensaje(String mensaje) {
        ControlUsuario usuario=null;
        for(int i=0;i<usuariosActivos.size();i++) {
            controlServidor.getVistaServidor().mostrarMensaje("MENSAJE DEVUELTO:"+mensaje);
            try {
                usuario=usuariosActivos.get(i);
                usuario.getSalidaMensaje().writeInt(1);
                usuario.getSalidaMensaje().writeUTF(usuarioActual.getNombre()+" >> "+ mensaje);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void enviaMensaje(String nombre,String nombreAmigo,String mensaje) {
        ControlUsuario usuario=null;
        for(int i=0;i<usuariosActivos.size();i++) {
            try {
                controlServidor.getVistaServidor().mostrarMensaje("MENSAJE DEVUELTO "+mensaje);
                usuario=usuariosActivos.get(i);
                if(usuario.getUsuarioActual().getNombre().equals(nombreAmigo)) {
                    usuario.getSalidaMensaje().writeInt(3);//opcion de mensage amigo
                    usuario.getSalidaMensaje().writeUTF(usuarioActual.getNombre());
                    usuario.getSalidaMensaje().writeUTF(usuarioActual.getNombre()+" >> "+mensaje);
                }
                if(usuario.getUsuarioActual().getNombre().equals(nombre)){
                    usuario.getSalidaMensaje().writeInt(3);//opcion de mensage amigo
                    usuario.getSalidaMensaje().writeUTF(usuarioActual.getNombre());
                    usuario.getSalidaMensaje().writeUTF(usuarioActual.getNombre()+" >> "+mensaje);

                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean verificaMensaje(String mensaje){
        boolean encontrado = false;
        String mensajeVerificar = mensaje.toLowerCase();
        Iterator it = controlServidor.getPalabrasBaneadas().iterator();
        while(it.hasNext()){
            if(mensajeVerificar.contains((String) it.next())){
                encontrado= true;
                break;
            }
        }
        return encontrado;

    }

    public boolean verificarNombre(String nombre){
        boolean valor=false;
        Iterator it = usuariosActivos.iterator();
        while (it.hasNext()){
            ControlUsuario actual = (ControlUsuario) it.next();
            if(nombre.equals(actual.getUsuarioActual().getNombre())){
                valor = true;
                break;

            }
        }

        return valor;
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
