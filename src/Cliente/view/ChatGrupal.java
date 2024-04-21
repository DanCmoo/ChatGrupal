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

    public ChatGrupal(){
        super("Chat");
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.inicializar();
        this.setVisible(true);
    }

    public void inicializar(){

        Font fuenteTexto = new Font("FixedSys", Font.BOLD, 15);

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

        scrollPane = new JScrollPane(panelIzquierdoPrincipal);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 0));
        panelPrincipal.add(scrollPane, BorderLayout.WEST);

        panelchat = new ChatIndividual("Chat grupal");
        panelchat.setPreferredSize(new Dimension(400,0));
        panelPrincipal.add(panelchat, BorderLayout.CENTER);
    }

    public void agregarBotones(String nombreDeUsuario){
        JButton boton = new JButton();
    }
    public static void main(String[] args) {
        ChatGrupal ventanaGrupal = new ChatGrupal();
    }
}
