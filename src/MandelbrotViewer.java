import javax.swing.*;
import java.awt.*;

public class MandelbrotViewer extends JFrame {


    protected MandelbrotFractal mandelbrotFractal;
    protected JuliaFractal juliaFractal;
    protected RedrawButtonsPanel buttonsPanel;
    protected FavouritesPanel favouritesPanel;
    //static file extension for saving and loading to file
    public static String fileExtension = "ftl";


    public MandelbrotViewer(String t) {
        super(t);
    }

    public void init() {
        //set the initial size of the window
        setSize(1300, 700);
        //set up a new layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints bagConstraints = new GridBagConstraints();
        //create and initialise the 4 different panels in the window
        mandelbrotFractal = new MandelbrotFractal();
        mandelbrotFractal.setToolTipText("The Mandelbrot fractal");
        juliaFractal = new JuliaFractal();
        juliaFractal.setToolTipText("The Julia fractal");
        favouritesPanel = new FavouritesPanel(juliaFractal);
        buttonsPanel = new RedrawButtonsPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add everything to the layout
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
        //lock the favourites panel into it's current size and shape
        Dimension d = new Dimension(favouritesPanel.getWidth(), favouritesPanel.getHeight());
        FavouritesPanel.setAllDimensions(d, favouritesPanel);
        bagConstraints.gridx = 0;
        bagConstraints.gridy = 1;
        bagConstraints.gridheight = 1;
        bagConstraints.gridwidth = 2;
        bagConstraints.weighty = 0.2;
        add(buttonsPanel, bagConstraints);
        d = new Dimension(buttonsPanel.getWidth(), buttonsPanel.getHeight());
        //lock the buttons panel to it's current size and shape
        FavouritesPanel.setAllDimensions(d, buttonsPanel);
        mandelbrotFractal.init();
        juliaFractal.init();
        favouritesPanel.init(mandelbrotFractal);
        buttonsPanel.init(mandelbrotFractal, juliaFractal);
        //finally add the click to change julia constant listener to the mandelbrot fractal
        mandelbrotFractal.addMouseListener(new MandelbrotClickListener(juliaFractal, mandelbrotFractal));
        setResizable(true);
        setVisible(true);
    }

    public static void main(String[] args) {
        //Ensuring that a GUI component is not called by the main thread
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
