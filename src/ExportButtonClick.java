import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ExportButtonClick extends SaveButtonClick {


    public ExportButtonClick(FractalDrawer fractal, JFileChooser fileChooser) {
        super(fractal, fileChooser);
    }

    @Override
    public void saveToFile() {
        //get the export type (extension) of the file
        String exportType = f.getName().substring(f.getName().lastIndexOf('.') + 1);
        //create a dialog allow the user to customize the size of the exported image
        JComponent[] sizeCustomization = {new JLabel("width:"), new JSpinner(new SpinnerNumberModel(drawer.getWidth(), 1, 4000, 1)), new JLabel("height:"), new JSpinner(new SpinnerNumberModel(drawer.getHeight(), 1, 4000, 1))};
        JOptionPane.showMessageDialog(drawer, sizeCustomization, "Set Image size", JOptionPane.PLAIN_MESSAGE);
        int tempWidth = ((int) ((JSpinner) sizeCustomization[1]).getValue());
        int tempHeight = ((int) ((JSpinner) sizeCustomization[3]).getValue());
        //creates a clone of the image, with the specified dimensions
        FractalDrawer drawerForExport = drawer.clone(tempWidth, tempHeight);
        //get the buffered image from the fractal
        BufferedImage exportImage = drawerForExport.getCanvas();
        try {
            //export the image to specified types, using prebuilt libraries
            ImageIO.write(exportImage, exportType, f);
            JOptionPane.showMessageDialog(drawer, "DONE!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
