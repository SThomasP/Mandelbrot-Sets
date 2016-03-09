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
            redrawButtonAction.changeFractal(fractalDrawer);
            resetButtonAction.changeFractal(fractalDrawer);
            fractalDrawer.setSelected(true);
            redrawButtonsPanel.setValues(fractalDrawer.getxStart(), fractalDrawer.getxEnd(), fractalDrawer.getyStart(), fractalDrawer.getyEnd(), fractalDrawer.getIterations());
            redrawButtonsPanel.setColors(fractalDrawer.getColors(), fractalDrawer.getLoopCount());
        }
        else{
            fractalDrawer.setSelected(false);
        }
    }

    public FractalSelector(FractalDrawer fractalDrawer, RedrawButtonAction redrawButtonAction, ResetButtonAction resetButtonAction){
        this.fractalDrawer = fractalDrawer;
        this.resetButtonAction =resetButtonAction;
        this.redrawButtonAction = redrawButtonAction;
        this.redrawButtonsPanel = fractalDrawer.getrBP();
    }
}
