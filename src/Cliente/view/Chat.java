package Cliente.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Chat extends JFrame {
    private JPanel panelPrincipal, panelIzquierdoPrincipal;
    private JScrollPane scrollPane ;
    private JMenuBar barraMenu;
    private JMenu menuAcercaDe;
    private JMenuItem itemAcercaDe;
    private ChatIndividual panelchat;
    private ArrayList<JButton> botonesNombresUsuarios;
    private ArrayList<ChatIndividual> chats;
    Font fuenteTexto = new Font("FixedSys", Font.BOLD, 15);

    public Chat(String nombre){
        super("Usuario :" + nombre);
        this.setSize(800,600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.inicializar();
        this.setVisible(true);
    }

    public void inicializar(){

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        this.getContentPane().add(panelPrincipal);

        botonesNombresUsuarios = new ArrayList<JButton>();
        chats = new ArrayList<ChatIndividual>();

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

    }

    public void agregarUsuarios(String nombreDeUsuario){
        JButton boton = new JButton();
        boton.setBackground(Color.WHITE);
        boton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        boton.setFont(fuenteTexto);
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        boton.setText(nombreDeUsuario);
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE,50));
        boton.setActionCommand("Abrir_Chat");
        botonesNombresUsuarios.add(boton);
        panelIzquierdoPrincipal.add(boton);

        ChatIndividual chat = new ChatIndividual(nombreDeUsuario);
        chat.setPreferredSize(new Dimension(400, 0));
        chats.add(chat);
        panelPrincipal.add(chat, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    public void mostrarJOptionPane(String m) {
        JOptionPane.showMessageDialog(null, m);
    }
    public String pedirNombreUsusario(String texto){
        return JOptionPane.showInputDialog(null,texto);

    }


    public void mostrarJOption(String mensaje){
        JOptionPane.showMessageDialog(null,mensaje);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public void setPanelPrincipal(JPanel panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
    }

    public JPanel getPanelIzquierdoPrincipal() {
        return panelIzquierdoPrincipal;
    }

    public void setPanelIzquierdoPrincipal(JPanel panelIzquierdoPrincipal) {
        this.panelIzquierdoPrincipal = panelIzquierdoPrincipal;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JMenuBar getBarraMenu() {
        return barraMenu;
    }

    public void setBarraMenu(JMenuBar barraMenu) {
        this.barraMenu = barraMenu;
    }

    public JMenu getMenuAcercaDe() {
        return menuAcercaDe;
    }

    public void setMenuAcercaDe(JMenu menuAcercaDe) {
        this.menuAcercaDe = menuAcercaDe;
    }

    public JMenuItem getItemAcercaDe() {
        return itemAcercaDe;
    }

    public void setItemAcercaDe(JMenuItem itemAcercaDe) {
        this.itemAcercaDe = itemAcercaDe;
    }

    public ChatIndividual getPanelchat() {
        return panelchat;
    }

    public void setPanelchat(ChatIndividual panelchat) {
        this.panelchat = panelchat;
    }

    public ArrayList<JButton> getBotonesNombresUsuarios() {
        return botonesNombresUsuarios;
    }

    public void setBotonesNombresUsuarios(ArrayList<JButton> botonesNombresUsuarios) {
        this.botonesNombresUsuarios = botonesNombresUsuarios;
    }

    public ArrayList<ChatIndividual> getChats() {
        return chats;
    }

    public void setChats(ArrayList<ChatIndividual> chats) {
        this.chats = chats;
    }

}
