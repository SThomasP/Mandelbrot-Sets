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
        xMin.setText(Double.toString(MandelbrotViewer.X_START));
        xMax.setText(Double.toString(MandelbrotViewer.X_END));
        yMin.setText(Double.toString(MandelbrotViewer.Y_START));
        yMax.setText(Double.toString(MandelbrotViewer.Y_END));
        iterations.setText(Integer.toString(MandelbrotViewer.ITERATIONS));
    }

    public void changeFractal(FractalDrawer fractal){
        mandelFractal = fractal;
    }

    public ResetButtonAction(JTextField xMin, JTextField xMax, JTextField yMin, JTextField yMax,JTextField iterations, FractalDrawer fD){
        mandelFractal =fD;
        this.xMin=xMin;
        this.xMax=xMax;
        this.yMin=yMin;
        this.yMax=yMax;
        this.iterations=iterations;
    }
}
