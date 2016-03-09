import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

/**
 * Created by Steffan on 08/03/2016.
 */
public class SaveButtonClick implements ActionListener {

    private FractalDrawer drawer;

    public SaveButtonClick(FractalDrawer drawer){
        this.drawer=drawer;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showSaveDialog(drawer);
            if (returnValue==JFileChooser.APPROVE_OPTION) {
                PrintStream stream = new PrintStream(fileChooser.getSelectedFile());
                stream.println("<FractalType=|" + drawer.getType() + "|>");
                stream.println("<XRange=|" + drawer.getxStart() + "|" + drawer.getxEnd() + "|>");
                stream.println("<YRange=|" + drawer.getyStart() + "|" + drawer.getyEnd() + "|>");
                stream.println("<Iterations=|" + drawer.getIterations() + "|>");
                stream.println("<Constant=|" + drawer.getConstant().getReal() + "|" + drawer.getConstant().getImaginary() + "|>");
                stream.print("<GradientColours=|");
                for (Color c : drawer.getColors()) {
                    stream.print(c.getRGB() + "|");
                }
                stream.println(">");
                stream.println("<Loop=|" + drawer.getLoopCount() + "|>");
                stream.close();
            }
        }
        catch(Exception e1) {
        }
    }
}
