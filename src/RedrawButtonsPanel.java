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
        setLayout(new GridBagLayout());
        GridBagConstraints gBC = new GridBagConstraints();
        iterationsField = new JTextField("255");
        ResetButtonAction resetAction = new ResetButtonAction(xMin,xMax,yMin,yMax,iterationsField,mF);
        RedrawButtonAction redrawAction = new RedrawButtonAction(xMin,xMax,yMin,yMax,iterationsField,mF);
        mButton.addItemListener(new FractalSelector(mF,redrawAction,resetAction,xMin,xMax,yMin,yMax,iterationsField));
        jButton.addItemListener(new FractalSelector(jF,redrawAction,resetAction,xMin,xMax,yMin,yMax,iterationsField));
        resetButton.addActionListener(resetAction);
        redrawButton.addActionListener(redrawAction);
        gBC.anchor=GridBagConstraints.LINE_END;
        gBC.insets= new Insets(5,2,2,5);
        gBC.fill=GridBagConstraints.HORIZONTAL;

        gBC.gridheight=2;
        gBC.gridx=0;
        gBC.weightx = 0.5;
        gBC.gridy=0;
        add(xRange,gBC);
        gBC.gridx=2;
        add(yRange,gBC);
        gBC.gridx=4;
        gBC.ipadx=10;
        add(iterationsLabel,gBC);
        gBC.ipadx=0;
        gBC.gridx=5;
        add(iterationsField,gBC);
        gBC.gridheight=1;
        gBC.anchor =GridBagConstraints.PAGE_END;
        gBC.gridx=1;
        gBC.ipadx=10;
        add(xMin,gBC);
        gBC.gridx=3;
        add(yMin,gBC);
        gBC.gridx=6;
        gBC.ipadx=0;
        add(mButton,gBC);
        gBC.gridx=7;
        add(redrawButton,gBC);
        gBC.gridy=1;
        gBC.anchor =GridBagConstraints.PAGE_START;
        add(resetButton,gBC);
        gBC.gridx=1;
        gBC.ipadx=10;
        add(xMax,gBC);
        gBC.gridx=3;
        add(yMax,gBC);
        gBC.gridx=6;
        gBC.ipadx =0;
        add(jButton,gBC);

    }



}
