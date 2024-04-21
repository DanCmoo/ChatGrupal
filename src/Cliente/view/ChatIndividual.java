package Cliente.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatIndividual extends JPanel {
    private JPanel panelNorte, panelCentro, panelSur;
    private JLabel textoUsuarioPrivado;
    private JTextField cajaMensaje;
    private JTextArea areaMensaje;
    private JScrollPane scrollPane;
    private JButton botonEnviar;
    private String nombre;


    public ChatIndividual(String name) {
        this.nombre = name;
        this.inicializar();
    }

    public void inicializar(){

        Font fuenteTexto = new Font("FixedSys", Font.BOLD, 15);
        int borde = 5;

        this.setLayout(new BorderLayout());

        panelNorte = new JPanel();
        panelNorte.setPreferredSize(new Dimension(0, 60));
        panelNorte.setLayout(new BorderLayout());
        panelNorte.setBackground(new Color(7,94,84));

        textoUsuarioPrivado = new JLabel(nombre);
        textoUsuarioPrivado.setFont(new Font("FixedSys", Font.BOLD, 20));
        textoUsuarioPrivado.setForeground(Color.WHITE);
        textoUsuarioPrivado.setBorder(new EmptyBorder(borde, borde+15, borde, borde));
        panelNorte.add(textoUsuarioPrivado, BorderLayout.WEST);

        this.add(panelNorte, BorderLayout.NORTH);

        panelCentro = new JPanel();
        panelCentro.setBackground(Color.WHITE);
        panelCentro.setLayout(new BorderLayout());

        areaMensaje = new JTextArea();
        areaMensaje.setFont(fuenteTexto);
        areaMensaje.setEditable(false);

        scrollPane = new JScrollPane(areaMensaje);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        panelCentro.add(scrollPane, BorderLayout.CENTER);

        this.add(panelCentro, BorderLayout.CENTER);

        panelSur = new JPanel();
        panelSur.setPreferredSize(new Dimension(0,50));
        panelSur.setLayout(new BorderLayout());
        panelSur.setBackground(Color.WHITE);

        cajaMensaje = new JTextField();
        cajaMensaje.setPreferredSize(new Dimension(300,50));
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

        this.add(panelSur, BorderLayout.SOUTH);
    }

    public void borrarCampos(){
        this.cajaMensaje.setText("");
    }

}
