package Cliente.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatIndividual extends JFrame {
    private JPanel panelPrincipal, panelNorte, panelCentro, panelSur;
    private JLabel textoEscribir, textoUsuarioPrivado;
    private JTextArea areaMensajes;
    private JTextField cajaMensaje;
    private JScrollPane scrollPane;
    private JButton botonEnviar;


    public ChatIndividual() {
        super("Chat Privado");
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.inicializar();

        this.setVisible(true);
    }

    public void inicializar(){

        Font fuenteTexto = new Font("FixedSys", Font.BOLD, 15);
        int borde = 5;

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        this.getContentPane().add(panelPrincipal);

        panelNorte = new JPanel();
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelNorte.setLayout(new BorderLayout());
        panelNorte.setBackground(new Color(7,94,84));

        textoUsuarioPrivado = new JLabel("Sergio");
        textoUsuarioPrivado.setFont(new Font("FixedSys", Font.BOLD, 20));
        textoUsuarioPrivado.setForeground(Color.WHITE);
        textoUsuarioPrivado.setBorder(new EmptyBorder(borde, borde+15, borde, borde));
        panelNorte.add(textoUsuarioPrivado, BorderLayout.WEST);

        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        panelCentro = new JPanel();
        panelCentro.setBackground(Color.WHITE);

        areaMensajes = new JTextArea();
        areaMensajes.setEditable(false);
        panelCentro.add(areaMensajes);

        scrollPane = new JScrollPane(panelCentro);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        panelSur = new JPanel();
        panelSur.setPreferredSize(new Dimension(0,90));
        panelSur.setLayout(new BorderLayout());
        panelSur.setBackground(Color.WHITE);

        textoEscribir = new JLabel("  Escribe un mensaje");
        textoEscribir.setPreferredSize(new Dimension(0,40));
        textoEscribir.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        textoEscribir.setFont(fuenteTexto);
        textoEscribir.setForeground(Color.LIGHT_GRAY);
        panelSur.add(textoEscribir, BorderLayout.NORTH);

        cajaMensaje = new JTextField();
        cajaMensaje.setPreferredSize(new Dimension(900,50));
        cajaMensaje.setFont(fuenteTexto);
        cajaMensaje.setBorder(new EmptyBorder(borde, borde, borde, borde));
        panelSur.add(cajaMensaje, BorderLayout.CENTER);

        botonEnviar = new JButton("Enviar");
        botonEnviar.setPreferredSize(new Dimension(100,50));
        botonEnviar.setActionCommand("ENVIAR");
        botonEnviar.setFont(fuenteTexto);
        botonEnviar.setForeground(new Color(7,94,84));
        botonEnviar.setBorder(BorderFactory.createLineBorder(new Color(7,94,84),3));
        botonEnviar.setBackground(Color.WHITE);

        panelSur.add(botonEnviar, BorderLayout.EAST);

        panelPrincipal.add(panelSur, BorderLayout.SOUTH);
    }

    public void agregarMensaje(String mensaje){
        this.areaMensajes.append(mensaje + "\n");
    }

    public static void main(String[] args) {
        ChatIndividual ventanaPrivada = new ChatIndividual();
    }
}
