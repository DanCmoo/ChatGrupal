package Cliente.controller;

import Cliente.model.Usuario;

import java.io.DataInputStream;
import java.io.IOException;

public class ControlUsuario extends Thread{
    private ControlCliente controlCliente;
    private DataInputStream entrada;
    private Usuario usuario;
    public ControlUsuario (DataInputStream entrada, ControlCliente controlCliente) throws IOException {
        this.entrada=entrada;
        this.controlCliente=controlCliente;
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
            case 1:
                mensaje=entrada.readUTF();
                //System.out.println("ECO del servidor:"+mensaje);
                        //.mostrarMsg(mensaje);
                controlCliente.mensaje(mensaje);
                break;
            case 2:
                mensaje=entrada.readUTF();
                controlCliente.agregarUsuario(mensaje);
                break;
            case 3:
                nombreAmigo=entrada.readUTF();
                mensaje=entrada.readUTF();
                //vcli.mensageAmigo(amigo,menser);
                controlCliente.mensaje(mensaje,nombreAmigo);
                break;
        }


    }
}


