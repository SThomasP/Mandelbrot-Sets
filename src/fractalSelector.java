import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Steffan on 04/03/2016.
 */
public class FractalSelector implements ItemListener {

    private FractalDrawer fractalDrawer;
    private RedrawButtonAction redrawButtonAction;
    private ResetButtonAction resetButtonAction;
    private JTextField xMin;
    private JTextField xMax;
    private JTextField yMin;
    private JTextField yMax;
    private JTextField iterations;

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            redrawButtonAction.changeFractal(fractalDrawer);
            resetButtonAction.changeFractal(fractalDrawer);
            xMin.setText(Double.toString(fractalDrawer.getxStart()));
            xMax.setText(Double.toString(fractalDrawer.getxEnd()));
            yMin.setText(Double.toString(fractalDrawer.getyStart()));
            yMax.setText(Double.toString(fractalDrawer.getyEnd()));
            iterations.setText(Integer.toString(fractalDrawer.getIterations()));
        }
    }

    public FractalSelector(FractalDrawer fractalDrawer, RedrawButtonAction redrawButtonAction, ResetButtonAction resetButtonAction, JTextField xMin,JTextField xMax, JTextField yMin, JTextField yMax, JTextField iterations ){
        this.fractalDrawer = fractalDrawer;
        this.resetButtonAction =resetButtonAction;
        this.redrawButtonAction = redrawButtonAction;
        this.xMax = xMax;
        this.xMin = xMin;
        this.yMax = yMax;
        this.yMin = yMin;
        this.iterations = iterations;
    }
}
