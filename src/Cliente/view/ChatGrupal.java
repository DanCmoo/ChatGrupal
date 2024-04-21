package Cliente.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChatGrupal extends JFrame {
    private JPanel panelPrincipal, panelIzquierdoPrincipal;
    private JScrollPane scrollPane ;
    private JMenuBar barraMenu;
    private JMenu menuAcercaDe;
    private JMenuItem itemAcercaDe;
    private ChatIndividual panelchat;
    private ArrayList<JButton> botonesNombresUsuarios;
    private ArrayList<ChatIndividual> chats;
    Font fuenteTexto = new Font("FixedSys", Font.BOLD, 15);

    public ChatGrupal(){
        super("Chat");
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.inicializar();
        this.setVisible(true);
    }

    public void inicializar(){

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        this.getContentPane().add(panelPrincipal);

        barraMenu = new JMenuBar();

        menuAcercaDe = new JMenu("Acerca de");
        menuAcercaDe.setBackground(Color.WHITE);
        menuAcercaDe.setBorder(BorderFactory.createLineBorder(new Color(7, 94, 84), 2));
        menuAcercaDe.setFont(fuenteTexto);
        menuAcercaDe.setForeground(new Color(7, 94, 84));

        itemAcercaDe = new JMenuItem("Créditos");
        itemAcercaDe.setBorder(BorderFactory.createLineBorder(new Color(7, 94, 84), 2));
        itemAcercaDe.setFont(fuenteTexto);
        itemAcercaDe.setForeground(new Color(7, 94, 84));
        itemAcercaDe.setActionCommand("CREDITOS");
        menuAcercaDe.add(itemAcercaDe);

        barraMenu.add(menuAcercaDe,0);

        this.panelPrincipal.add(barraMenu, BorderLayout.NORTH);

        panelIzquierdoPrincipal = new JPanel();
        panelIzquierdoPrincipal.setLayout(new BoxLayout(panelIzquierdoPrincipal, BoxLayout.Y_AXIS));
        panelIzquierdoPrincipal.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(panelIzquierdoPrincipal);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 0));
        panelPrincipal.add(scrollPane, BorderLayout.WEST);

        panelchat = new ChatIndividual("Chat grupal");
        panelchat.setPreferredSize(new Dimension(400,0));
        panelPrincipal.add(panelchat, BorderLayout.CENTER);
    }

    public void agregarUsuarios(String nombreDeUsuario){
        JButton boton = new JButton();
        botonesNombresUsuarios = new ArrayList<JButton>();
        boton.setBackground(Color.WHITE);
        boton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        boton.setFont(fuenteTexto);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setText(nombreDeUsuario);
        botonesNombresUsuarios.add(boton);
        panelIzquierdoPrincipal.add(boton);

        ChatIndividual chat = new ChatIndividual(nombreDeUsuario);
        chats = new ArrayList<ChatIndividual>();
        chat.setPreferredSize(new Dimension(400, 0));
        chats.add(chat);
        panelPrincipal.add(chat, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }

    public void mostrarJOption(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje);
    }

    public static void main(String[] args) {
        ChatGrupal ventanaGrupal = new ChatGrupal();
        ventanaGrupal.agregarUsuarios("Usuario1");
    }
}
