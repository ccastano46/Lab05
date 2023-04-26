package presentation;

import java.awt.*;
import java.awt.Insets;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.Border;
import javax.swing.event.*;
import java.awt.event.*;

public class Conecta4GUI extends JFrame {
    private JMenuBar menuBar;
    private JFileChooser selectorArchivos;
    private JMenuItem nuevo, abrir, salvar, salir;
    private JPanel board;
    private JLabel turno;
    private JRadioButton[] columnas;
    private JRadioButton column1, column2, column3, column4, column5, column6, column7;
    private JButton shootButton, changeBoardButton;
    private JColorChooser colorPalete;

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
        colorPalete = new JColorChooser();
        prepareElementsBoard();
    }

    /**
     * Metodo que instancia y organiza los componentes del menu
     */
    private void prepareElementsMenu() {
        // Se instancia el JMenuBar y se le asigna al Frame
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        // Declaramos e instanciamos el objeto de la clase JMenu y le aniadimos sus
        // items
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
     * Metodo que inicializa y organiza los componentes que hacen parte del tablero
     * del juego
     */
    private void prepareElementsBoard() {
        int numFilas = Integer
                .parseInt(JOptionPane.showInputDialog("Ingerese numero de filas que debe tener el tablero"));
        int numColumnas = Integer
                .parseInt(JOptionPane.showInputDialog("Ingerese numero de columnas que debe tener el tablero"));
        board = new JPanel();
        board.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        board.setLayout(new GridLayout(numFilas, numColumnas, 5, 5));
        for (int i = 0; i < numFilas * numColumnas; i++) {
            board.add(new CircleLabel(10));
        }
        board.setBackground(new Color(70, 165, 162));
        board.setOpaque(true);
        board.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        turno = new JLabel("Es el turno del Jugador1", JLabel.CENTER);
        turno.setPreferredSize(new Dimension(WIDTH, 50));
        setContentPane(new JPanel());
        getContentPane().setLayout(new BorderLayout(5, 5));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        getContentPane().add(board, BorderLayout.CENTER);
        getContentPane().add(turno, BorderLayout.NORTH);
        prepareBoardButtons();
    }

    private void prepareBoardButtons() {
        JPanel columnsButtons = new JPanel(new GridLayout(1, 7, 5, 5));
        columnsButtons.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), new TitledBorder("Columnas")));
        columnas = new JRadioButton[7];
        for (int i = 0; i < columnas.length; i++) {
            columnas[i] = new JRadioButton("C" + (i + 1));
            columnsButtons.add(columnas[i]);
        }
        columnsButtons.setPreferredSize(new Dimension(WIDTH, 50));
        JPanel options = new JPanel(new GridLayout(3, 1, 5, 5));
        options.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), new TitledBorder("Opciones")));
        options.setPreferredSize(new Dimension(100, HEIGHT));
        shootButton = new JButton("Shoot");
        options.add(shootButton);
        changeBoardButton = new JButton();
        changeBoardButton.setLayout(new GridLayout(3, 1, 0, 0));
        changeBoardButton.add(new JLabel("Cambiar", JLabel.CENTER));
        changeBoardButton.add(new JLabel("Color", JLabel.CENTER));
        changeBoardButton.add(new JLabel("Tablero", JLabel.CENTER));
        options.add(changeBoardButton);

        getContentPane().add(columnsButtons, BorderLayout.SOUTH);
        getContentPane().add(options, BorderLayout.EAST);
    }

    private void refresh() {

    }

    /**
     * Metodo que prepara las acciones que se van a realizar.
     */
    private void prepareActions() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int siNo = JOptionPane.showConfirmDialog(Conecta4GUI.this, "Esta seguro de terminar el juego?");
                if (siNo == 0)
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
        prepareActionsMenu();
        prepareActionsBoard();
    }

    private void prepareActionsMenu() {
        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int siNo = JOptionPane.showConfirmDialog(Conecta4GUI.this, "¿Esta seguro de terminar el juego?");
                if (siNo == 0)
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
        abrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectorArchivos.showOpenDialog(Conecta4GUI.this);
            }
        });
        salvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectorArchivos.showSaveDialog(Conecta4GUI.this);
            }
        });
    }

    private void prepareActionsBoard() {

        ActionListener justOne = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (JRadioButton b : columnas) {
                    if (!(e.getSource() == b)) {
                        b.setSelected(false);
                    }
                }
            }
        };

        for (JRadioButton b : columnas) {
            b.addActionListener(justOne);
        }
        changeBoardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color colorChoose = colorPalete.showDialog(Conecta4GUI.this, "Paleta de colores", Color.black);
                board.setBackground(colorChoose);
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