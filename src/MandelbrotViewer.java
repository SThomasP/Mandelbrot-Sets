import javax.swing.*;
import java.awt.*;

/**
 * Created by Steffan on 28/02/2016.
 */
public class MandelbrotViewer extends JFrame {


    //TODO:comments for everything
    protected MandelbrotFractal mandelbrotFractal;
    protected JuliaFractal juliaFractal;
    protected RedrawButtonsPanel buttonsPanel;
    protected FavouritesPanel favouritesPanel;
    public static String fileExtension = "ftl";


    public MandelbrotViewer(String t) {
        super(t);
    }

    public void init() {
        setSize(1300, 700);
        setLayout(new GridBagLayout());
        GridBagConstraints bagConstraints = new GridBagConstraints();
        mandelbrotFractal = new MandelbrotFractal();
        mandelbrotFractal.setToolTipText("The Mandelbrot fractal");
        juliaFractal = new JuliaFractal();
        juliaFractal.setToolTipText("The Julia fractal");
        favouritesPanel = new FavouritesPanel(juliaFractal);
        buttonsPanel = new RedrawButtonsPanel();
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
        bagConstraints.gridheight = 1;
        bagConstraints.gridwidth = 2;
        bagConstraints.weighty = 0.2;
        add(buttonsPanel, bagConstraints);
        d = new Dimension(buttonsPanel.getWidth(), buttonsPanel.getHeight());
        FavouritesPanel.setAllDimensions(d, buttonsPanel);
        mandelbrotFractal.init();
        juliaFractal.init();
        favouritesPanel.init(mandelbrotFractal);
        buttonsPanel.init(mandelbrotFractal, juliaFractal);
        mandelbrotFractal.addMouseListener(new MandelbrotClickListener(juliaFractal, mandelbrotFractal));
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
