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

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
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
            returnColour = new Color(255-colourNo,0,255-colourNo);
        }
        else{
            returnColour = Color.black;
        }
        return returnColour;
    }
}
