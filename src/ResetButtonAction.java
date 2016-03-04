import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Steffan on 04/03/2016.
 */
public class ResetButtonAction implements ActionListener {


    protected JTextField xMin;
    protected JTextField xMax;
    protected JTextField yMin;
    protected JTextField yMax;
    protected JTextField iterations;
    protected FractalDrawer mandelFractal;

    public void actionPerformed(ActionEvent e) {
        mandelFractal.resetToDefault();
        xMin.setText(Double.toString(MandelbrotViewer.XSTART));
        xMax.setText(Double.toString(MandelbrotViewer.XEND));
        yMin.setText(Double.toString(MandelbrotViewer.YSTART));
        yMax.setText(Double.toString(MandelbrotViewer.YEND));
        iterations.setText(Integer.toString(MandelbrotViewer.ITERATIONS));
    }

    public void changeFractal(FractalDrawer fractal){
        mandelFractal = fractal;
    }

    public ResetButtonAction(JTextField xMin, JTextField xMax, JTextField yMin, JTextField yMax,JTextField iterations, FractalDrawer fD){
        mandelFractal =fD;
        this.xMin=xMin;
        this.xMax=xMax;
        this.yMin=yMax;
        this.yMax=yMax;
        this.iterations=iterations;
    }
}
