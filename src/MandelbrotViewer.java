import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by Steffan on 28/02/2016.
 */
public class MandelbrotViewer extends JFrame {

    protected MandelFractal mandelbrotFractal;
    protected JuliaFractal juliaFractal;
    protected RedrawButtonsPanel buttonsPanel;
    protected FavouritesPanel favouritesPanel;
    public static  double XSTART = -2.0;
    public static  double YSTART = -1.6;
    public static  double XEND = 2.0;
    public static  double YEND = 1.6;
    public static  int ITERATIONS = 255;
    public static  Complex DEFAULT_C = new Complex(0,0);



    public  MandelbrotViewer(String t){
        super(t);
        setSize(1000,600);
        mandelbrotFractal= new MandelFractal(XSTART,YSTART,XEND,YEND,ITERATIONS);
        juliaFractal = new JuliaFractal(XSTART,YSTART,XEND,YEND,ITERATIONS,new Complex(0,0));
        favouritesPanel = new FavouritesPanel(juliaFractal);
        mandelbrotFractal.addMouseListener(new MandelClickListener(juliaFractal,mandelbrotFractal));
        buttonsPanel = new RedrawButtonsPanel(mandelbrotFractal,juliaFractal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = this.getContentPane();
        pane.add(mandelbrotFractal);
        pane.add(buttonsPanel);
        pane.add(juliaFractal);
        pane.add(favouritesPanel);
        pane.setLayout(new BorderLayout());
        setResizable(false);
    }

    public static void main(String[] args){

        MandelbrotViewer mBV = new MandelbrotViewer("Mandelbrot Explorer");
        mBV.setVisible(true);
    }
}
