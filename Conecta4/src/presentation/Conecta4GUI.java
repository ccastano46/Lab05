package presentation;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

public class Conecta4GUI extends JFrame{
    private JMenuBar menu;
    private JMenu menu1;
    /**
     * Constructor de la interfaz grafica del juego Conecta4
     */
    public Conecta4GUI(){
        prepareElements();
        prepareActions();
       
    }

    /**
     * Metodo que prepara los componentes dentro del Frame
     */
    private void prepareElements(){
        setTitle("Conecta4");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width/2,screenSize.height/2);
        Dimension frameSize = this.getSize();
        this.setLocation((screenSize.width - frameSize.width)/2, 
        (screenSize.height - frameSize.height)/2);
        menu = new JMenuBar();
        setJMenuBar(menu);
        menu1 = new JMenu("Menu");
        menu.add(menu1);
    }
    /**
     * Metodo que prepara las acciones que se van a realizar.
     */
    private void prepareActions(){
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int siNo = JOptionPane.showConfirmDialog(Conecta4GUI.this, "¿Esta seguro de jterminar el juego?");
                if(siNo == 0) setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                else setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        });
    }
    /**
     * Metodo principal del GUI
     */
    public static void main(String[] args){
        Conecta4GUI conecta = new Conecta4GUI();
         conecta.setVisible(true);
        

    }
}