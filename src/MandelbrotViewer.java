import com.sun.glass.ui.Screen;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Steffan on 28/02/2016.
 */
public class MandelbrotViewer extends JFrame {

    protected MandelFractal mandelbrotsPanel;
    protected JuliaFractal juliaFractal;
    protected JPanel buttonsPanel;


    //setup the buttonsPanel,keep things neat and tidy
   protected void setupButtons(){
       //TODO: Make the buttons panel, with everything in it, its own class
       buttonsPanel = new JPanel();
       buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
       buttonsPanel.setLocation(0,480);
       buttonsPanel.setSize(600,120);
       JButton  redrawButton = new JButton("Redraw");
       JLabel xRange = new JLabel("X Range");
       JLabel yRange = new JLabel("Y Range");
       JLabel iterationsLabel = new JLabel("Iterations");
       JTextField xMin = new JTextField("-2");
       JTextField xMax = new JTextField("2");
       JTextField yMin = new JTextField("-1.6");
       JTextField yMax = new JTextField("1.6");
       ButtonGroup bGroup = new ButtonGroup();
       JRadioButton mButton = new JRadioButton("Mandelbrot Set");
       mButton.setSelected(true);
       JRadioButton jButton = new JRadioButton("Julia Set");
       bGroup.add(jButton);
       bGroup.add(mButton);
       JTextField iterationsField = new JTextField("255");
       buttonsPanel.add(redrawButton);
       redrawButton.setLocation(475,30);
       redrawButton.setSize(100,25);
       RedrawButtonAction redrawAction = new RedrawButtonAction(xMin,xMax,yMin,yMax,iterationsField,mandelbrotsPanel);
       mButton.addItemListener(new FractalSelector(mandelbrotsPanel,redrawAction));
       redrawButton.addActionListener(redrawAction);
       buttonsPanel.add(xMax);
       xMax.setLocation(60,50);
       xMax.setSize(50,25);
       buttonsPanel.add(xMin);
       xMin.setLocation(60,20);
       xMin.setSize(50,25);
       buttonsPanel.add(yMin);
       yMin.setLocation(170,20);
       yMin.setSize(50,25);
       buttonsPanel.add(yMax);
       yMax.setLocation(170,50);
       yMax.setSize(50,25);
       buttonsPanel.add(xRange);
       xRange.setLocation(10,30);
       xRange.setSize(50,25);
       buttonsPanel.add(iterationsLabel);
       iterationsLabel.setLocation(230,30);
       iterationsLabel.setSize(100,25);
       buttonsPanel.add(iterationsField);
       iterationsField.setLocation(290,30);
       iterationsField.setSize(50,25);
       buttonsPanel.add(yRange);
       yRange.setLocation(120,30);
       yRange.setSize(50,25);
       buttonsPanel.setLayout(new BorderLayout());
   }

    public  MandelbrotViewer(String t){
        super(t);
        setSize(1000,600);
        mandelbrotsPanel= new MandelFractal(-2,-1.6,2,1.6,255);
        juliaFractal = new JuliaFractal(-2,-1.6,2,1.6,255,new Complex(0,0));
        mandelbrotsPanel.addMouseListener(new MandelClickListener(juliaFractal,mandelbrotsPanel));
        setupButtons();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = this.getContentPane();
        pane.add(mandelbrotsPanel);
        pane.add(buttonsPanel);
        pane.add(juliaFractal);
        buttonsPanel.setLocation(0,480);
        pane.setLayout(new BorderLayout());
        setResizable(false);
    }

    public static void main(String[] args){

        MandelbrotViewer mBV = new MandelbrotViewer("Mandelbrot Set Stuff");
        mBV.setVisible(true);
    }
}
