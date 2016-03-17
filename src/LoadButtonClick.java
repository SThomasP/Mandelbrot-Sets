import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LoadButtonClick implements ActionListener {


    private MandelbrotFractal mandel;
    private JuliaFractal julia;
    private JFileChooser fileChooser;

    public LoadButtonClick(MandelbrotFractal mandel, JuliaFractal julia, JFileChooser fileChooser) {
        this.mandel = mandel;
        this.julia = julia;
        this.fileChooser = fileChooser;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            FractalDrawer newImage = null;
            //shows a file chooser, to select the file to load
            int selected = fileChooser.showOpenDialog(mandel);
            if (selected == JFileChooser.APPROVE_OPTION) {
                //get the selected file from the file chooser
                File fileToLoad = fileChooser.getSelectedFile();
                if (fileChooser.accept(fileToLoad)) {
                    //if the file is acceptable to load, initializes the values to load to
                    double xMin = 0, xMax = 0, yMin = 0, yMax = 0;
                    int iterations = 0, loopCount = 0;
                    Complex constant = null;
                    Color[] colours = new Color[1];
                    BufferedReader br = new BufferedReader(new FileReader(fileToLoad));
                    String line;
                    String[] parts;
                    while ((line = br.readLine()) != null) {
                        parts = line.split("'");
                        if (parts[0].charAt(1) == 'F') {
                            //FractalType of the loading fractal
                            if (parts[1].equals("MANDELBROT")) {
                                newImage = mandel;
                            } else if (parts[1].equals("JULIA")) {
                                newImage = julia;
                                //set the type of the fractal getting the details
                            } else {
                                throw new Exception("Not a valid fractal type");
                            }
                        } else if (parts[0].charAt(1) == 'X') {
                            xMin = Double.valueOf(parts[1]);
                            xMax = Double.valueOf(parts[2]);
                            //The XRange tag of the file, which stores the xMin and xMax of the fractal
                        } else if (parts[0].charAt(1) == 'Y') {
                            yMin = Double.valueOf(parts[1]);
                            yMax = Double.valueOf(parts[2]);
                            //The YRange tag of the file, which stores the yMin and yMax of the fractal
                        } else if (parts[0].charAt(1) == 'I') {
                            //The iterations tag, store the iterations count of the fractal
                            iterations = Integer.valueOf(parts[1]);
                        } else if (parts[0].charAt(1) == 'C') {
                            //the Constant tag, store the real and imaginary parts of the complex number
                            constant = new Complex(Double.valueOf(parts[1]), Double.valueOf(parts[2]));
                        } else if (parts[0].charAt(1) == 'G') {
                            //the GradientColours tag, storing all the colours in the gradient,
                            // the array size is then calculated from the number of values stored
                            colours = new Color[parts.length - 2];
                            for (int i = 0; i < colours.length; i++) {
                                colours[i] = new Color(Integer.valueOf(parts[i + 1]));
                            }
                        } else if (parts[0].charAt(1) == 'L') {
                            //the LoopCount tag, which stores the number of times the image is implemented
                            loopCount = Integer.valueOf(parts[1]);
                        } else {
                            throw new Exception("Unrecognized file tag");
                        }
                    }
                    br.close();
                    if (newImage instanceof JuliaFractal) {
                        ((JuliaFractal) newImage).changeConstant(constant);
                        //if its a julia fractal set the constant
                    }
                    //then set the colours of the image
                    newImage.setColors(colours, loopCount);
                    if (newImage.isSelected()) {
                        //if the image is selected, set the colours of the redrawButtonsPanel
                        newImage.getRBP().setColors(colours, loopCount);
                    }
                    //redraw the fractal
                    // the range and itertions spinners, are then set at the end of this method
                    newImage.redrawFractal(xMin, yMin, xMax, yMax, iterations);
                    JOptionPane.showMessageDialog(newImage, "Fractal loaded");

                } else {
                    throw new Exception("Not a ." + MandelbrotViewer.fileExtension + " file");
                }
            }
            //catches the exceptions thrown during, and shows an error message dialog
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mandel, e.getMessage());

        }

    }
}
