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
    protected JPanel exportButtons;
    public static  double X_START = -2.0;
    public static  double Y_START = -1.6;
    public static  double X_END = 2.0;
    public static  double Y_END = 1.6;
    public static  int ITERATIONS = 255;
    public static  Complex DEFAULT_C = new Complex(0,0);



    public  MandelbrotViewer(String t){
        super(t);
        setSize(1366,768);
        setLayout(new GridBagLayout());
        GridBagConstraints bagConstraints = new GridBagConstraints();
        mandelbrotFractal= new MandelFractal(getWidth());
        exportButtons = new JPanel();
        juliaFractal = new JuliaFractal(getWidth());
        favouritesPanel = new FavouritesPanel(juliaFractal);
        mandelbrotFractal.addMouseListener(new MandelClickListener(juliaFractal,mandelbrotFractal));
        buttonsPanel = new RedrawButtonsPanel(mandelbrotFractal,juliaFractal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bagConstraints.gridheight=2;
        bagConstraints.weighty=0.8;
        bagConstraints.weightx=0.5;
        bagConstraints.fill=GridBagConstraints.BOTH;
        bagConstraints.gridx=0;
        bagConstraints.gridy=0;
        add(mandelbrotFractal,bagConstraints);
        bagConstraints.weighty=0.2;
        bagConstraints.gridheight=1;
        bagConstraints.gridy=2;
        add(buttonsPanel,bagConstraints);
        bagConstraints.gridx=1;
        bagConstraints.gridwidth=2;
        bagConstraints.gridy=0;
        bagConstraints.weightx=0.4;
        bagConstraints.weighty=0.5;
        add(juliaFractal,bagConstraints);
        bagConstraints.gridy=1;
        bagConstraints.gridwidth=1;
        bagConstraints.gridheight=2;
        bagConstraints.weightx=0.3;
        bagConstraints.weighty=0.4;
        add(favouritesPanel,bagConstraints);
        bagConstraints.gridx=2;
        bagConstraints.gridy=1;
        bagConstraints.gridheight=1;
        bagConstraints.weightx=0.2;
        bagConstraints.weighty=0.3;
        setResizable(true);
        add(exportButtons,bagConstraints);
    }

    public static void main(String[] args){

        MandelbrotViewer mBV = new MandelbrotViewer("Mandelbrot Explorer");
        mBV.setVisible(true);
    }
}
