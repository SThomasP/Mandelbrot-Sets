import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Steffan on 04/03/2016.
 */
public class ResetButtonAction implements ActionListener {


    protected FractalDrawer mandelFractal;
    protected RedrawButtonsPanel buttonsPanel;

    public void actionPerformed(ActionEvent e) {
        mandelFractal.resetToDefault();
        buttonsPanel.setColors(mandelFractal.getColors(), mandelFractal.getLoopCount());
        buttonsPanel.setValues(mandelFractal.getxStart(), mandelFractal.getxEnd(), mandelFractal.getyStart(), mandelFractal.getyEnd(), mandelFractal.getIterations());

    }

    public void changeFractal(FractalDrawer fractal) {
        mandelFractal = fractal;
    }

    public ResetButtonAction(RedrawButtonsPanel buttonsPanel, FractalDrawer fD) {
        mandelFractal = fD;
        this.buttonsPanel = buttonsPanel;
    }
}
