import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RedrawButtonAction implements ActionListener {

    protected RedrawButtonsPanel buttonsPanel;
    protected FractalDrawer mandelFractal;

    public void actionPerformed(ActionEvent e) {
        //set the Colours and the variables in the selected fractal
        mandelFractal.setColors(buttonsPanel.getColours(), buttonsPanel.getLoopCount());
        mandelFractal.redrawFractal(buttonsPanel.getXMin(), buttonsPanel.getYMin(), buttonsPanel.getXMax(), buttonsPanel.getYMax(), buttonsPanel.getIterations());

    }


    public void changeFractal(FractalDrawer f) {
        //used to swap the fractal to the currently selected fractal when the radio buttons are changed
        mandelFractal = f;
    }

    public RedrawButtonAction(RedrawButtonsPanel buttonsPanel, FractalDrawer mandelFractal) {
        this.buttonsPanel = buttonsPanel;
        this.mandelFractal = mandelFractal;
    }
}


