import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Steffan on 04/03/2016.
 */
public class FractalSelector implements ItemListener {

    private FractalDrawer fractalDrawer;
    private RedrawButtonsPanel redrawButtonsPanel;
    private RedrawButtonAction redrawButtonAction;
    private ResetButtonAction resetButtonAction;

    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            //changes the referenced fractal in the redraw and reset buttons
            redrawButtonAction.changeFractal(fractalDrawer);
            resetButtonAction.changeFractal(fractalDrawer);
            //tell the fractal that it is selected
            fractalDrawer.setSelected(true);
            //update the buttons panel, to list the values of the current fractal
            redrawButtonsPanel.setValues(fractalDrawer.getXStart(), fractalDrawer.getXEnd(), fractalDrawer.getYStart(), fractalDrawer.getYEnd(), fractalDrawer.getIterations());
            redrawButtonsPanel.setColors(fractalDrawer.getColors(), fractalDrawer.getLoopCount());
        } else {
            //tell the fractal that it is no longer selected
            fractalDrawer.setSelected(false);
        }
    }

    public FractalSelector(FractalDrawer fractalDrawer, RedrawButtonAction redrawButtonAction, ResetButtonAction resetButtonAction) {
        this.fractalDrawer = fractalDrawer;
        this.resetButtonAction = resetButtonAction;
        this.redrawButtonAction = redrawButtonAction;
        this.redrawButtonsPanel = fractalDrawer.getRBP();
    }
}
