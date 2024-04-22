package Cliente.controller;

import Cliente.model.Usuario;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ControlUsuario{
    private ControlCliente controlCliente;
    private DataOutputStream salida;
    private DataInputStream entrada;
    private Usuario usuario;
    public ControlUsuario (String nombreUsusario, DataInputStream entrada,DataOutputStream salida, ControlCliente controlCliente) throws IOException {
        this.entrada=entrada;
        this.salida = salida;
        this.controlCliente=controlCliente;
        usuario = new Usuario(nombreUsusario);
        run();
    }
    public void run() {

        while(true) {
            try{
                acciones();
            }
            catch (IOException e){
                //System.out.println("Error en la comunicaci�n "+"Informaci�n para el usuario");
                break;
            }
        }
        //System.out.println("se desconecto el servidor");

    }
    public void acciones ()throws IOException {
        String mensaje="",nombreAmigo="";
        int opcion=0;
        opcion=entrada.readInt();
        switch(opcion) {
            case 1: // Recepccion del mensaje chat grupal
                mensaje=entrada.readUTF();
                controlCliente.recibirMensaje(mensaje);
                break;
            case 2:
                mensaje=entrada.readUTF();
                if(controlCliente.verificarUsuario(mensaje)){

                }else{
                    controlCliente.agregarUsuario(mensaje);
                }


                break;
            case 3:// Recepccion del mensaje del amigo
                nombreAmigo=entrada.readUTF();
                mensaje=entrada.readUTF();
                controlCliente.recibirMensaje(mensaje,nombreAmigo);
                break;

        }


    }
}


