import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Steffan on 29/02/2016.
 */
public class RedrawButtonAction implements ActionListener {

    protected JTextField xMin;
    protected JTextField xMax;
    protected JTextField yMin;
    protected JTextField yMax;
    protected JTextField iterations;
    protected FractalDrawer mandelFractal;

    public void actionPerformed(ActionEvent e) {
        Double x0 = new Double(xMin.getText());
        Double x1 = new Double(xMax.getText());
        Double y0 = new Double(yMin.getText());
        Double y1 = new Double(yMax.getText());
        Integer iterationCount = new Integer(iterations.getText());
        mandelFractal.redrawFractal(x0,y0,x1,y1,iterationCount);
        xMin.setText(Double.toString(mandelFractal.getxStart()));
        xMax.setText(Double.toString(mandelFractal.getxEnd()));
        yMin.setText(Double.toString(mandelFractal.getyStart()));
        yMax.setText(Double.toString(mandelFractal.getyEnd()));
    }

    public void changeFractal(FractalDrawer f){
        mandelFractal = f;
    }

    public RedrawButtonAction(JTextField xMin, JTextField xMax, JTextField yMin, JTextField yMax, JTextField iterations, MandelFractal mandelFractal){
        this.xMax=xMax;
        this.xMin=xMin;
        this.yMax=yMax;
        this.yMin=yMin;
        this.iterations = iterations;
        this.mandelFractal = mandelFractal;
    }
}


