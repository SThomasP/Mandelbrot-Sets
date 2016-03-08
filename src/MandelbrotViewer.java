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
    public static double X_START = -2.0;
    public static double Y_START = -1.6;
    public static double X_END = 2.0;
    public static double Y_END = 1.6;
    public static int ITERATIONS = 100;
    public static Complex DEFAULT_C = new Complex(0, 0);


    public MandelbrotViewer(String t) {
        super(t);
    }

    public void init() {
        setSize(1300, 700);
        setLayout(new GridBagLayout());
        GridBagConstraints bagConstraints = new GridBagConstraints();
        mandelbrotFractal = new MandelFractal();
        mandelbrotFractal.init();
        juliaFractal = new JuliaFractal();
        juliaFractal.init();
        favouritesPanel = new FavouritesPanel(juliaFractal);
        favouritesPanel.init();
        mandelbrotFractal.addMouseListener(new MandelClickListener(juliaFractal, mandelbrotFractal));
        buttonsPanel = new RedrawButtonsPanel();
        buttonsPanel.init(mandelbrotFractal,juliaFractal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bagConstraints.weighty = 0.8;
        bagConstraints.weightx = 0.5;
        bagConstraints.fill = GridBagConstraints.BOTH;
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 0;
        add(mandelbrotFractal, bagConstraints);
        bagConstraints.gridx = 1;
        add(juliaFractal, bagConstraints);
        bagConstraints.gridy = 0;
        bagConstraints.gridx = 2;
        bagConstraints.gridheight = 2;
        bagConstraints.weightx = 0.3;
        bagConstraints.weighty = 1.0;
        add(favouritesPanel, bagConstraints);
        Dimension d = new Dimension(favouritesPanel.getWidth(), favouritesPanel.getHeight());
        FavouritesPanel.setAllDimensions(d, favouritesPanel);
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        bagConstraints.gridheight = 0;
        bagConstraints.gridwidth = 2;
        bagConstraints.weighty = 0.3;
        add(buttonsPanel, bagConstraints);
        d = new Dimension(buttonsPanel.getWidth(), buttonsPanel.getHeight());
        FavouritesPanel.setAllDimensions(d, buttonsPanel);
        setResizable(true);
        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        MandelbrotViewer window = new MandelbrotViewer("Fractal Explorer");
                        window.init();
                    }
                }
        );
    }
}
