package Cliente.controller;

import Cliente.view.Chat;

import Cliente.model.Cliente;
import Cliente.view.ChatIndividual;
import Cliente.view.Vista;
import Servidor.model.conexion.ConexionProperties;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ControlCliente implements ActionListener {
    private DataInputStream entradaComunicacion;
    private DataOutputStream salida;
    private DataInputStream entradaMensaje;
    private Chat vistaChat;
    private Vista vista;
    private Cliente cliente;
    private int puertoComunicacion;
    private int puertoMensaje;
    private String nombre;
    private String ipServidor;
    private String chatActual;
    private ConexionProperties conexionProperties;


    public ControlCliente() {
        chatActual = "Chat Grupal";
        vista = new Vista();
        cargarDatos();
        conexion();

    }

    public void cargarDatos() {
        try {
            conexionProperties = new ConexionProperties(vista.pedirArchivo("Archivo de propiedades del servidor", "properties"));
            conexionProperties.cargarDatosIniciales();
            ipServidor = conexionProperties.getDatosServidor().getProperty("cliente.ipServidor");
            puertoComunicacion = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("cliente.puertoComunicacion"));
            puertoMensaje = Integer.parseInt(conexionProperties.getDatosServidor().getProperty("cliente.puertoMensaje"));
            vista.mostrarJOptionPane("Las propiedades han sido cargadas con exito");
        } catch (FileNotFoundException e) {
            vista.mostrarJOptionPane("El archivo no se ha encontrado");
        } catch (IOException e) {
            vista.mostrarJOptionPane("El archivo no se ha leído correctamente");
        }
    }

    public void conexion() {

        try {
            cliente = new Cliente(new Socket(ipServidor, puertoComunicacion), new Socket(ipServidor, puertoMensaje));
            entradaComunicacion = new DataInputStream(cliente.getSocketComunication().getInputStream());
            salida = new DataOutputStream(cliente.getSocketComunication().getOutputStream());
            entradaMensaje = new DataInputStream(cliente.getSocketMensaje().getInputStream());
            nombre = vista.pedirNombreUsusario("Introducir nombre de usuario :");
            salida.writeUTF(nombre);
            while(entradaComunicacion.readBoolean()){
                nombre = vista.pedirNombreUsusario("Introducir nombre de usuario :");

                salida.writeUTF(nombre);
            }
            System.out.println(nombre);
            vistaChat = new Chat(nombre);
            agregarUsuario("Chat Grupal");
            ControlUsuario usuario = new ControlUsuario(nombre,entradaMensaje,salida, this);

        } catch (IOException e) {
            vista.mostrarJOptionPane("\tEl servidor no esta levantado");
        }


    }

    public void recibirMensaje(String mensaje) {
        vistaChat.getChats().get(0).mostrarMensaje(mensaje);
        if(chatActual.equals("Chat Grupal")){

        }else{
            vistaChat.getChats().get(0).setVisible(false);
        }
    }

    public void enviarMensaje(String mensaje) {
        try {
            salida.writeInt(1);
            salida.writeUTF(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void enviarMensaje(String mensaje,String nombre, String nombreAmigo){
        try {
            salida.writeInt(3);
            salida.writeUTF(nombre);
            salida.writeUTF(nombreAmigo);
            salida.writeUTF(mensaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recibirMensaje(String mensaje, String nombreAmigo) {
        if(buscarChat(nombreAmigo) == null){
            buscarChat(chatActual).mostrarMensaje(mensaje);
        }else{
            buscarChat(nombreAmigo).mostrarMensaje(mensaje);
            if(chatActual.equals(nombreAmigo)){

            }else{
                buscarChat(nombreAmigo).setVisible(false);
            }
        }



    }

    public ChatIndividual buscarChat(String nombreAmigo){
        Iterator it = vistaChat.getChats().iterator();
        ChatIndividual chat = null;
        while (it.hasNext()){
            ChatIndividual chatActual = (ChatIndividual)it.next();
            if(chatActual.getNombre().equals(nombreAmigo)){
                chat = chatActual;
                break;
            }
        }
        return chat;

    }
    public boolean verificarUsuario(String nombre){
        boolean valor = false;
        Iterator it = vistaChat.getBotonesNombresUsuarios().iterator();
        while (it.hasNext()){
            String nombreContacto = ((JButton)it.next()).getText();
            if(nombre.equals(nombreContacto)){
                valor =true;
                break;
            }
        }
        return valor;
    }

    public void agregarUsuario(String nombre) {
        vistaChat.agregarUsuarios(nombre);
        vistaChat.getBotonesNombresUsuarios().get(vistaChat.getBotonesNombresUsuarios().size()-1).addActionListener(this);
        vistaChat.getChats().get(vistaChat.getChats().size()-1).getBotonEnviar().addActionListener(this);
        vistaChat.revalidate();
        vistaChat.repaint();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source instanceof JButton){
            JButton botonPresionado = (JButton)source;
            String actionCommand = botonPresionado.getActionCommand();
            if("Abrir_Chat".equals(actionCommand)){
                chatActual = botonPresionado.getText();
                abrirChat(chatActual);
            }
            if("Enviar_Mensaje".equals(actionCommand)){
                String mensaje = buscarChat(botonPresionado.getName()).getCajaMensaje().getText();
                if(botonPresionado.getName().equals("Chat Grupal")){
                    enviarMensaje(mensaje);
                }else{
                    enviarMensaje(mensaje,nombre,botonPresionado.getName());
                }
                buscarChat(botonPresionado.getName()).borrarCampos();
            }
        }

    }

    private void abrirChat(String nombre) {
        if(nombre.equals("Chat Grupal")){
            vistaChat.getChats().get(0).redimensionar();
            vistaChat.getChats().get(0).setVisible(true);

        }else{
            Iterator it = vistaChat.getChats().iterator();
            int i = 0;
            while (it.hasNext()){
                ChatIndividual chatActual = (ChatIndividual) it.next();
                if(nombre.equals(chatActual.getNombre())){
                    vistaChat.getChats().get(i).setVisible(true);
                } else{
                    vistaChat.getChats().get(i).setVisible(false);
                }
                i++;
            }
        }


    }
}
