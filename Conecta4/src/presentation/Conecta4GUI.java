package presentation;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;
import java.awt.event.*;

public class Conecta4GUI extends JFrame {
    private JMenuBar menuBar;
    private JFileChooser selectorArchivos;
    private JMenuItem nuevo, abrir, salvar, salir;
    private JPanel board;
    private JLabel turno;
    private JButton row1,row2,row3,row4,row5,row6;

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
        selectorArchivos = new JFileChooser();
        prepareElementsBoard();
        
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
     * Metodo que inicializa y organiza los componentes que hacen parte del tablero del juego
     */
    private void prepareElementsBoard(){
        board = new JPanel();
        board.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        board.setLayout(new GridLayout(6, 7, 15, 15));
        for(int i = 0; i < 6*7; i++){
            board.add(new BoardElement());
        }
        board.setBackground(new Color(70,165,162));
        board.setOpaque(true);
        turno = new JLabel("Es el turno del Jugador1", JLabel.CENTER);
        JPanel botones = new JPanel(new GridLayout(6,1,5,5));
        botones.setBorder(new CompoundBorder(new EmptyBorder(3,3,3,3), new TitledBorder("Filas")));
        row1 = new JButton("Fila1");
        botones.add(row1);
        row2 = new JButton("Fila2");
        botones.add(row2);
        row3 = new JButton("Fila3");
        botones.add(row3);
        row4 = new JButton("Fila4");
        botones.add(row4);
        row5 = new JButton("Fila5");
        botones.add(row5);
        row6 = new JButton("Fila6");
        botones.add(row6);
        setContentPane(new JPanel());
        getContentPane().setLayout(new BorderLayout(10,10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(5,5,15,15));
        getContentPane().add(board, BorderLayout.CENTER);
        getContentPane().add(turno, BorderLayout.NORTH);
        getContentPane().add(botones, BorderLayout.WEST);
    }

    private void refresh(){

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
                if (siNo == 0) System.exit(0);
            }
        });
        abrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                selectorArchivos.showOpenDialog(Conecta4GUI.this);
            }
        });
        salvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                selectorArchivos.showSaveDialog(Conecta4GUI.this);
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