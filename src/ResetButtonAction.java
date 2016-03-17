import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetButtonAction implements ActionListener {


    protected FractalDrawer mandelFractal;
    protected RedrawButtonsPanel buttonsPanel;

    public void actionPerformed(ActionEvent e) {
        //set the zoom of the image back to the default
        mandelFractal.resetToDefault();
        buttonsPanel.setValues(mandelFractal.getXStart(), mandelFractal.getXEnd(), mandelFractal.getYStart(), mandelFractal.getYEnd(), mandelFractal.getIterations());

    }


    //change fractals when the selected fractal is changes
    public void changeFractal(FractalDrawer fractal) {
        mandelFractal = fractal;
    }

    public ResetButtonAction(RedrawButtonsPanel buttonsPanel, FractalDrawer fD) {
        mandelFractal = fD;
        this.buttonsPanel = buttonsPanel;
    }
}
