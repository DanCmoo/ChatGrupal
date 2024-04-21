package Cliente.controller;

import java.io.DataInputStream;
import java.io.IOException;

public class ControlUsuario extends Thread{
    private ControlCliente controlCliente;
    private DataInputStream entrada;
    public ControlUsuario (DataInputStream entrada, ControlCliente controlCliente) throws IOException
    {
        this.entrada=entrada;
        this.controlCliente=controlCliente;
    }
    public void run()
    {

        while(true)
        {
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
        switch(opcion)
        {
            case 1://mensage enviado
                mensaje=entrada.readUTF();
                //System.out.println("ECO del servidor:"+mensaje);
                        //.mostrarMsg(mensaje);
                break;
            case 2://se agrega
                mensaje=entrada.readUTF();
                //vcli.agregarUser(mensaje);
                break;
            case 3://mensage de amigo
                nombreAmigo=entrada.readUTF();
                mensaje=entrada.readUTF();
                //vcli.mensageAmigo(amigo,menser);
                //System.out.println("ECO del servidor:"+mensaje);
                break;
        }


    }
}


