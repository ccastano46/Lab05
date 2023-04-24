package presentation;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class Conecta4GUI extends JFrame {
    private JMenuBar menuBar;
    private JMenuItem nuevo, abrir, salvar, salir;

    /**
     * Constructor de la interfaz grafica del juego Conecta4
     */
    public Conecta4GUI() {
        prepareElements();
        prepareActions();

    }

    /**
     * Metodo que prepara los componentes dentro del Frame
     */
    private void prepareElements() {
        setTitle("Conecta4");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        Dimension frameSize = this.getSize();
        this.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
        prepareElementsMenu();
    }

    /**
     * Metodo que instancia y organiza los componentes del menu
     */
    private void prepareElementsMenu() {
        // Se instancia el JMenuBar y se le asigna al Frame
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        // Declaramos e instanciamos el objeto de la clase JMenu y le añadimos sus items
        JMenu menu = new JMenu("Menu");
        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        salvar = new JMenuItem("Salvar");
        salir = new JMenuItem("Salir");
        menu.add(nuevo);
        menu.add(abrir);
        menu.add(salvar);
        menu.add(salir);
        menuBar.add(menu);
    }

    /**
     * Metodo que prepara las acciones que se van a realizar.
     */
    private void prepareActions() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int siNo = JOptionPane.showConfirmDialog(Conecta4GUI.this, "¿Esta seguro de terminar el juego?");
                if (siNo == 0)
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });

        prepareActionsMenu();
    }

    private void prepareActionsMenu() {
        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int siNo = JOptionPane.showConfirmDialog(Conecta4GUI.this, "¿Esta seguro de terminar el juego?");
                if (siNo == 0)
                    Conecta4GUI.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
    }

    /**
     * Metodo principal del GUI
     */
    public static void main(String[] args) {
        Conecta4GUI conecta = new Conecta4GUI();
        conecta.setVisible(true);

    }
}