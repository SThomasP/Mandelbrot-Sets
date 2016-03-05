import javax.swing.*;
import java.awt.*;

/**
 * Created by Steffan on 28/02/2016.
 */
public class MandelbrotViewer extends JFrame {

    protected MandelFractal mandelbrotFractal;
    protected JuliaFractal juliaFractal;
    protected RedrawButtonsPanel buttonsPanel;
    protected FavouritesPanel favouritesPanel;
    public static  double X_START = -2.0;
    public static  double Y_START = -1.6;
    public static  double X_END = 2.0;
    public static  double Y_END = 1.6;
    public static  int ITERATIONS = 255;
    public static  Complex DEFAULT_C = new Complex(0,0);



    public  MandelbrotViewer(String t){
        super(t);
        setSize(1000,600);
        setLayout(new GridBagLayout());
        GridBagConstraints bagConstraints = new GridBagConstraints();
        mandelbrotFractal= new MandelFractal(X_START, Y_START, X_END, Y_END,ITERATIONS);
        juliaFractal = new JuliaFractal(X_START, Y_START, X_END, Y_END,ITERATIONS,new Complex(0,0));
        favouritesPanel = new FavouritesPanel(juliaFractal);
        mandelbrotFractal.addMouseListener(new MandelClickListener(juliaFractal,mandelbrotFractal));
        buttonsPanel = new RedrawButtonsPanel(mandelbrotFractal,juliaFractal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bagConstraints.gridheight=2;
        bagConstraints.weighty=0.8;
        bagConstraints.weightx=0.6;
        bagConstraints.fill=GridBagConstraints.BOTH;
        bagConstraints.gridx=0;
        bagConstraints.gridy=0;
        add(mandelbrotFractal,bagConstraints);
        bagConstraints.weighty=0.2;
        bagConstraints.gridheight=1;
        bagConstraints.gridy=2;
        add(buttonsPanel,bagConstraints);
        bagConstraints.gridx=1;
        bagConstraints.gridy=0;
        bagConstraints.weightx=0.4;
        bagConstraints.weighty=0.53;
        add(juliaFractal,bagConstraints);
        bagConstraints.gridy=1;
        bagConstraints.gridheight=2;
        bagConstraints.weightx=0.3;
        bagConstraints.weighty=0.47;
        add(favouritesPanel,bagConstraints);
        setResizable(false);
    }

    public static void main(String[] args){

        MandelbrotViewer mBV = new MandelbrotViewer("Mandelbrot Explorer");
        mBV.setVisible(true);
    }
}
