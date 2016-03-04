import javax.swing.*;
import java.awt.*;

/**
 * Created by Steffan on 04/03/2016.
 */
public class RedrawButtonsPanel extends JPanel {

    private JButton redrawButton, resetButton;
    private JLabel xRange,yRange, iterationsLabel;
    private JTextField xMin,xMax,yMin,yMax,iterationsField;
    private JRadioButton mButton, jButton;

    public RedrawButtonsPanel(MandelFractal mF, JuliaFractal jF){
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLocation(0,480);
        setSize(600,120);
        redrawButton = new JButton("Redraw");
        resetButton = new JButton("Reset");
        xRange = new JLabel("X Range");
        yRange = new JLabel("Y Range");
        iterationsLabel = new JLabel("Iterations");
        xMin = new JTextField("-2.0");
        xMax = new JTextField("2.0");
        yMin = new JTextField("-1.6");
        yMax = new JTextField("1.6");
        ButtonGroup bGroup = new ButtonGroup();
        mButton = new JRadioButton("Mandelbrot Set");
        mButton.setSelected(true);
        jButton = new JRadioButton("Julia Set");
        bGroup.add(jButton);
        bGroup.add(mButton);
        iterationsField = new JTextField("255");
        add(redrawButton);
        add(resetButton);
        redrawButton.setLocation(475,20);
        resetButton.setLocation(475,50);
        resetButton.setSize(100,25);
        redrawButton.setSize(100,25);
        ResetButtonAction resetAction = new ResetButtonAction(xMin,xMax,yMin,yMax,iterationsField,mF);
        RedrawButtonAction redrawAction = new RedrawButtonAction(xMin,xMax,yMin,yMax,iterationsField,mF);
        mButton.addItemListener(new FractalSelector(mF,redrawAction,resetAction,xMin,xMax,yMin,yMax,iterationsField));
        jButton.addItemListener(new FractalSelector(jF,redrawAction,resetAction,xMin,xMax,yMin,yMax,iterationsField));
        resetButton.addActionListener(resetAction);
        redrawButton.addActionListener(redrawAction);
        add(xMax);
        add(jButton);
        jButton.setLocation(350, 50);
        jButton.setSize(100,25);
        add(mButton);
        mButton.setLocation(350,20);
        mButton.setSize(120,25);
        xMax.setLocation(60,50);
        xMax.setSize(50,25);
        add(xMin);
        xMin.setLocation(60,20);
        xMin.setSize(50,25);
        add(yMin);
        yMin.setLocation(170,20);
        yMin.setSize(50,25);
        add(yMax);
        yMax.setLocation(170,50);
        yMax.setSize(50,25);
        add(xRange);
        xRange.setLocation(10,30);
        xRange.setSize(50,25);
        add(iterationsLabel);
        iterationsLabel.setLocation(230,30);
        iterationsLabel.setSize(100,25);
        add(iterationsField);
        iterationsField.setLocation(290,30);
        iterationsField.setSize(50,25);
        add(yRange);
        yRange.setLocation(120,30);
        yRange.setSize(50,25);
        setLayout(new BorderLayout());
    }



}
