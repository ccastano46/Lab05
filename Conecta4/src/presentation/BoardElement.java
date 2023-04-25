package presentation;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.AbstractBorder;
/**
 * Clase que crea Label circular, basado en el siguiente enlace https://www.jc-mouse.net/java/jlabel-circular
 * @web htpp://www.jc-mouse.net
 * @author Mouse
 */
public class BoardElement extends JLabel {

   private AbstractBorder circleBorder = new CircleBorder();       
   private int lineBorder=1; 
   private Color lineColor= Color.BLACK;

    /** Constructor */
     public BoardElement()
     {
        Dimension d = new Dimension(5,5);
        setPreferredSize(d);
        setOpaque(true);
        setBorder(circleBorder); 
     }

    //Color de borde
    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color color) {
        circleBorder = new CircleBorder(color, lineBorder);
        lineColor = color;
        setBorder(circleBorder); 
    }

    //Grosor de borde
    public int getLineBorder() {
        return lineBorder;        
    }

    public void setLineBorder(int lineBorder) {
        circleBorder = new CircleBorder(lineColor, lineBorder);
        this.lineBorder = lineBorder;        
        setBorder(circleBorder); 
    }
}
