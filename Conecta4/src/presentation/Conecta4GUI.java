package presentation;

import domain.*;
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
    private JRadioButton[] columns;
    private JButton shootButton, changeBoardButton;
    private JColorChooser colorPalete;
    private final Color player1 = Color.red;
    private final Color player2 = Color.yellow;

    private Conecta4 game;

    /**
     * Constructor de la interfaz grafica del juego Conecta4
     */
    public Conecta4GUI() {
        setGame();
        prepareElements();
        prepareExtraButtons();
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
        board = new JPanel();
        board.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        board.setLayout(new GridLayout(game.board().length, game.board()[0].length, 5, 5));
        for (int i = 0; i < game.board().length * game.board()[0].length; i++) {
            board.add(new CircleLabel(30,Color.WHITE));
        }
        board.setBackground(new Color(70,165,162));
        board.setBackground(new Color(70, 165, 162));
        board.setOpaque(true);
        board.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        turno = new JLabel("Es el turno del Jugador 1", JLabel.CENTER);
        turno.setPreferredSize(new Dimension(WIDTH, 50));
        setContentPane(new JPanel());
        getContentPane().setLayout(new BorderLayout(5, 5));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        getContentPane().add(board, BorderLayout.CENTER);
        getContentPane().add(turno, BorderLayout.NORTH);
        prepareBoardButtons(game.board()[0].length);
       
    }

    private void prepareBoardButtons(int numColumns) {
        JPanel columnsButtons = new JPanel(new GridLayout(1, 7, 5, 5));
        columnsButtons.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), new TitledBorder("Columnas")));
        columns = new JRadioButton[numColumns];
        for (int i = 0; i < columns.length; i++) {
            columns[i] = new JRadioButton("C" + (i + 1));
            columnsButtons.add(columns[i]);
        }
        columnsButtons.setPreferredSize(new Dimension(WIDTH, 50));
        getContentPane().add(columnsButtons, BorderLayout.SOUTH);
    }

    private void prepareExtraButtons() {
        changeBoardButton = new JButton();
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
        getContentPane().add(options, BorderLayout.EAST);
    }

    private void refresh() {
        if(game.player()){
            turno.setText("Es el turno del Jugador 1");
        }else{
            turno.setText("Es el turno del Jugador 2");
        }
        Component component[] = board.getComponents();
        for(Component c: component){
            c.setVisible(false);
        }
        board.removeAll();
        for (int i = 0; i < game.board().length; i++) { 
            for (int j = 0; j < game.board()[0].length; j++) { 
                if(game.board()[i][j] == 'a'){
                    board.add(new CircleLabel(30,Color.BLUE));
                }else if(game.board()[i][j] == 'r'){
                    board.add(new CircleLabel(30,Color.RED));
                }else{
                    board.add(new CircleLabel(30,Color.WHITE));
                }
            }
        }
    }

    private void setGame(){
        int numRows = Integer
                .parseInt(JOptionPane.showInputDialog("Ingerese numero de filas que debe tener el tablero"));
        int numColumns = Integer
                .parseInt(JOptionPane.showInputDialog("Ingerese numero de columnas que debe tener el tablero"));
        try {
            game = new Conecta4(numRows, numColumns);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            setGame();
        }
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
                for (JRadioButton b : columns) {
                    if (!(e.getSource() == b)) {
                        b.setSelected(false);
                    }
                }
            }
        };
        for (JRadioButton b : columns) {
            b.addActionListener(justOne);
        }
        changeBoardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color colorChoose = colorPalete.showDialog(Conecta4GUI.this, "Paleta de colores", Color.black);
                board.setBackground(colorChoose);
            }
        });
        shootButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int selectColumn=0;
                for(JRadioButton b : columns){
                    if(b.isSelected()) break;
                    selectColumn++;
                }
                //System.out.println(selectColumn);
                try {
                    if(game.play(selectColumn)){
                        if(game.player()){
                            JOptionPane.showMessageDialog(Conecta4GUI.this, "Jugador 2 eres el ganador");
                        }else{
                            JOptionPane.showMessageDialog(Conecta4GUI.this, "Jugador 1 eres el ganador");
                        }
                    } 
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(Conecta4GUI.this, error.getMessage());
                }
                refresh();
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