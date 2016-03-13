import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by stp1g15 on 11/03/2016.
 */
public class LoadButtonClick implements ActionListener {


    private MandelFractal mandel;
    private JuliaFractal julia;
    private JFileChooser fileChooser;

    public LoadButtonClick(MandelFractal mandel, JuliaFractal julia, JFileChooser fileChooser) {
        this.mandel = mandel;
        this.julia = julia;
        this.fileChooser = fileChooser;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        try {
            FractalDrawer newImage = null;
            int selected = fileChooser.showOpenDialog(mandel);
            if (selected == JFileChooser.APPROVE_OPTION) {
                File fileToLoad = fileChooser.getSelectedFile();
                if (fileChooser.accept(fileToLoad)) {
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
                            if (parts[1].equals("MANDELBROT")) {
                                newImage = mandel;
                            } else if (parts[1].equals("JULIA")) {
                                newImage = julia;
                            } else {
                                throw new Exception("Not a valid fractal type");
                            }
                        } else if (parts[0].charAt(1) == 'X') {
                            xMin = Double.valueOf(parts[1]);
                            xMax = Double.valueOf(parts[2]);
                        } else if (parts[0].charAt(1) == 'Y') {
                            yMin = Double.valueOf(parts[1]);
                            yMax = Double.valueOf(parts[2]);
                        } else if (parts[0].charAt(1) == 'I') {
                            iterations = Integer.valueOf(parts[1]);
                        } else if (parts[0].charAt(1) == 'C') {
                            constant = new Complex(Double.valueOf(parts[1]), Double.valueOf(parts[2]));
                        } else if (parts[0].charAt(1) == 'G') {
                            colours = new Color[parts.length - 2];
                            for (int i = 0; i < colours.length; i++) {
                                colours[i] = new Color(Integer.valueOf(parts[i + 1]));
                            }
                        } else if (parts[0].charAt(1) == 'L') {
                            loopCount = Integer.valueOf(parts[1]);
                        } else {
                            throw new Exception("Unrecognized file tag");
                        }

                    }
                    br.close();
                    if (newImage instanceof JuliaFractal) {
                        ((JuliaFractal) newImage).changeConstant(constant);
                    }
                    newImage.setColors(colours, loopCount);
                    if (newImage.isSelected()) {
                        newImage.getrBP().setColors(colours, loopCount);
                    }
                    newImage.redrawFractal(xMin, yMin, xMax, yMax, iterations);
                    JOptionPane.showMessageDialog(newImage, "Fractal loaded");

                } else {
                    throw new Exception("Not a ." + MandelbrotViewer.fileExtension + " file");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(mandel, e.getMessage());

        }

    }
}
