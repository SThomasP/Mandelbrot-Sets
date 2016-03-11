import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

/**
 * Created by Steffan on 08/03/2016.
 */
public class SaveButtonClick implements ActionListener {

    private  FractalDrawer drawer;
    JFileChooser fileChooser;

    public SaveButtonClick(FractalDrawer drawer, JFileChooser fileChooser){
        this.drawer=drawer;
        this.fileChooser=fileChooser;
    }

    public void saveToFile(){
        try {
            PrintStream stream = new PrintStream(fileChooser.getSelectedFile());
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

    public void actionPerformed(ActionEvent e) {
        fileChooser.resetChoosableFileFilters();
        fileChooser.setFileFilter(new ExtensionFileFilter("ftl"));
        int returnValue = fileChooser.showSaveDialog(drawer);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            //ask the user to confirm the overwriting of an existing file
            if (fileChooser.getSelectedFile().exists()){
                String[] options={"Yes","No"};
                int n = JOptionPane.showOptionDialog(drawer,(fileChooser.getSelectedFile().getName()+" Already exists, \n"
                +"would you like to overwrite it"),"File exists", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE,null
                        ,options,options[0]);
                if(n==JOptionPane.YES_OPTION){
                    saveToFile();
                }
            }
            else {
                saveToFile();
            }
        }
    }
}
