import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Steffan on 12/03/2016.
 */
public class ExportButtonClick extends SaveButtonClick {


    public ExportButtonClick(FractalDrawer fractal, JFileChooser fileChooser) {
        super(fractal, fileChooser);
    }

    @Override
    public void saveToFile() {
        String exportType = f.getName().substring(f.getName().lastIndexOf('.') + 1);
        JComponent[] sizeCustomization = {new JLabel("width:"), new JSpinner(new SpinnerNumberModel(drawer.getWidth(), 1, 4000, 1)), new JLabel("height:"), new JSpinner(new SpinnerNumberModel(drawer.getHeight(), 1, 4000, 1))};
        JOptionPane.showMessageDialog(drawer, sizeCustomization, "Set Image size", JOptionPane.PLAIN_MESSAGE);
        int tempWidth = ((int) ((JSpinner) sizeCustomization[1]).getValue());
        int tempHeight = ((int) ((JSpinner) sizeCustomization[3]).getValue());
        FractalDrawer drawerForExport = drawer.clone(tempWidth, tempHeight);
        BufferedImage exportImage = drawerForExport.getCanvas();
        try {
            ImageIO.write(exportImage, exportType, f);
            JOptionPane.showMessageDialog(drawer, "DONE!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(drawer, e.getMessage());
        }
    }
}
