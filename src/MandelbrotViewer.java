import javax.swing.*;
import java.awt.*;

/**
 * Created by Steffan on 28/02/2016.
 */
public class MandelbrotViewer extends JFrame {

    protected MandelFractal mandelbrotsPanel;
    protected JButton redrawButton;
    protected JLabel xRange;
    protected JLabel yRange;
    protected JLabel iterationsLabel;
    protected JTextField xMin;
    protected JTextField xMax;
    protected JTextField yMin;
    protected JTextField yMax;
    protected JTextField iterationsField;



    public  MandelbrotViewer(String t){
        super(t);
        setSize(600,600);
        mandelbrotsPanel= new MandelFractal(-2,-1.6,2,1.6,255);
        redrawButton = new JButton("Redraw");
        xRange = new JLabel("X Range");
        yRange = new JLabel("Y Range");
        iterationsLabel = new JLabel("Iterations count");
        xMin = new JTextField("-2");
        xMax = new JTextField("2");
        yMin = new JTextField("-1.6");
        yMax = new JTextField("1.6");
        iterationsField = new JTextField("255");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = this.getContentPane();
        pane.add(xMax);
        xMax.setLocation(60,530);
        xMax.setSize(50,25);
        pane.add(xMin);
        xMin.setLocation(60,500);
        xMin.setSize(50,25);
        pane.add(yMin);
        yMin.setLocation(170,500);
        yMin.setSize(50,25);
        pane.add(yMax);
        yMax.setLocation(170,530);
        yMax.setSize(50,25);
        pane.add(xRange);
        xRange.setLocation(10,510);
        xRange.setSize(50,25);
        pane.add(yRange);
        yRange.setLocation(120,510);
        yRange.setSize(50,25);
        pane.add(iterationsField);
        pane.add(iterationsLabel);
        pane.add(redrawButton);
        redrawButton.addActionListener(new RedrawButtonAction(xMin,xMax,yMin,yMax,iterationsField,mandelbrotsPanel));
        redrawButton.setLocation(475,510);
        redrawButton.setSize(100,25);
        pane.add(mandelbrotsPanel);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args){

        MandelbrotViewer mBV = new MandelbrotViewer("Mandelbrot Set");
    }
}
