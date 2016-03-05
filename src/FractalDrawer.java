import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Steffan on 03/03/2016.
 */
public abstract class FractalDrawer extends JPanel {

    protected BufferedImage canvas;
    protected double xStart;
    protected double yStart;
    protected double xEnd;
    protected double yEnd;
    protected int iterations;

    public FractalDrawer(){
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public double getxStart() {
        return xStart;
    }

    public double getyStart() {
        return yStart;
    }

    public double getxEnd() {
        return xEnd;
    }

    public double getyEnd() {
        return yEnd;
    }

    public int getIterations(){
        return iterations;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(canvas, null, null);
    }

    public abstract void resetToDefault();


    //sets the Aspect Ratio of the image to the correct 1.25, keeping the fractal looking nice
    public void correctAspectRatio(){
        double aspectRatio =((xEnd-xStart)/(yEnd-yStart));
        double idealRation =((getWidth()/getHeight()));
        double median;
        if (aspectRatio>1.25){
            median = (yEnd+yStart)/2;
            yStart = median-(xEnd-xStart)/2.5;
            yEnd = median+(xEnd-xStart)/2.5;
        }
        else if (aspectRatio<1.25){
            median = (xEnd+xStart)/2;
            xStart = median -(yEnd-yStart)*0.625;
            xEnd =median +(yEnd-yStart)*0.625;
        }
    }
    public abstract void redrawFractal(double xStart, double yStart,double xEnd,double yEnd,int iterations );

    public Color colourPixel(Complex zOfZero, Complex constant) {
        int deviatesAt=0;
        boolean deviates = false;
        for (int i = 0; i < iterations; i++) {
            double mSquared = zOfZero.magnitudeSquared();
            if ((mSquared > 4.0) && (!deviates)) {
                deviatesAt = i;
                deviates = true;
            }
            zOfZero = zOfZero.getSquare().add(constant);
        }
        Color returnColour;
        if (deviates){
            int colourNo = deviatesAt*255/iterations;
            returnColour = new Color(0,colourNo,100);
        }
        else{
            returnColour = Color.black;
        }
        return returnColour;
    }
}
