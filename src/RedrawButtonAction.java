import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Steffan on 29/02/2016.
 */
public class RedrawButtonAction implements ActionListener {

    protected RedrawButtonsPanel buttonsPanel;
    protected FractalDrawer mandelFractal;

    public void actionPerformed(ActionEvent e) {
        mandelFractal.setColors(buttonsPanel.getColours(), buttonsPanel.getLoopCount());
        mandelFractal.redrawFractal(buttonsPanel.getXMin(), buttonsPanel.getYMin(), buttonsPanel.getXMax(), buttonsPanel.getYMax(), buttonsPanel.getIterations());

    }

    public void changeFractal(FractalDrawer f){
        mandelFractal = f;
    }

    public RedrawButtonAction(RedrawButtonsPanel buttonsPanel, FractalDrawer mandelFractal) {
        this.buttonsPanel = buttonsPanel;
        this.mandelFractal = mandelFractal;
    }
}


