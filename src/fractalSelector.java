import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Steffan on 04/03/2016.
 */
public class FractalSelector implements ItemListener {

    private FractalDrawer fractalDrawer;
    private RedrawButtonAction redrawButtonAction;
    private JTextField xMin;
    private JTextField xMax;
    private JTextField yMin;
    private JTextField yMax;
    private JTextField iterations;

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            redrawButtonAction.changeFractal(fractalDrawer);
            xMin.setText(Double.toString(fractalDrawer.getxStart()));
        }
    }

    public FractalSelector(FractalDrawer fractalDrawer, RedrawButtonAction redrawButtonAction, JTextField xMin,JTextField xMax, JTextField yMin, JTextField yMax, JTextField iterations ){
        this.fractalDrawer = fractalDrawer;
        this.redrawButtonAction = redrawButtonAction;
        this.xMax = xMax;
        this.xMin = xMin;
        this.yMax = yMax;
        this.yMin = yMin;
        this.iterations = iterations;
    }
}
