import javax.swing.*;
import java.awt.*;

/**
 * Created by Steffan on 28/02/2016.
 */
public class MandelbrotViewer extends JFrame {

    protected JPanel mandelbrotsPanel;



    public  MandelbrotViewer(String t){
        super(t);
        setSize(500,600);
        mandelbrotsPanel= new MandelFractal(0,0,500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = this.getContentPane();
        pane.add(mandelbrotsPanel);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args){
        MandelbrotViewer mBV = new MandelbrotViewer("Mandelbrot Set");
    }
}
