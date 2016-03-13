import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintStream;

/**
 * Created by Steffan on 08/03/2016.
 */
public class SaveButtonClick implements ActionListener {

    protected FractalDrawer drawer;
    protected JFileChooser fileChooser;
    protected File f;

    public SaveButtonClick(FractalDrawer drawer, JFileChooser fileChooser){
        this.drawer=drawer;
        this.fileChooser=fileChooser;
    }

    public void saveToFile(){
        try {

            //Writes the Fractal to a text file using a makeup language, allows compatibility without using binary
            PrintStream stream = new PrintStream(f);
            stream.println("<FractalType='" + drawer.getType() + "'>");
            stream.println("<XRange='" + drawer.getxStart() + "'" + drawer.getxEnd() + "'>");
            stream.println("<YRange='" + drawer.getyStart() + "'" + drawer.getyEnd() + "'>");
            stream.println("<Iterations='" + drawer.getIterations() + "'>");
            stream.println("<Constant='" + drawer.getConstant().getReal() + "'" + drawer.getConstant().getImaginary() + "'>");
            stream.print("<GradientColours='");
            for (Color c : drawer.getColors()) {
                stream.print(c.getRGB() + "'");
            }
            stream.println(">");
            stream.println("<Loop='" + drawer.getLoopCount() + "'>");
            stream.close();
        }
        catch(Exception ignored) {
        }
    }

    private boolean confirmOverwrite() {
        //ask the user to confirm the overwriting of an existing file
        if (f.exists()) {
            String[] options = {"Yes", "No"};
            int n = JOptionPane.showOptionDialog(drawer, (f.getName() + " Already exists, \n"
                            + "would you like to overwrite it"), "File exists", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null
                    , options, options[0]);
            return n == JOptionPane.YES_OPTION;
        } else {
            return true;
        }
    }

    public void actionPerformed(ActionEvent e) {
        int returnValue = fileChooser.showSaveDialog(drawer);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            f = fileChooser.getSelectedFile();
            if (isOfExtension() && confirmOverwrite()) {
                saveToFile();
            }
        }
    }

    public boolean isOfExtension() {
        //checks if the file has the correct extension, adds it if it doesn't then return the values
        String extension = ((ExtensionFileFilter) fileChooser.getFileFilter()).getExtension();
        if (fileChooser.getFileFilter().accept(f)) {
            return true;
        } else if (f.getName().lastIndexOf('.') == -1) {
            f = new File(f.getPath() + "." + extension);
            return true;
        } else {
            JOptionPane.showMessageDialog(drawer, "Not a ." + extension + " file");
            return false;
        }
    }
}
