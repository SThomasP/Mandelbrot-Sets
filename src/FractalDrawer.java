import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                canvas = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
                redrawFractal(xStart,yStart,xEnd,yEnd,iterations);
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
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


    //sets the Aspect Ratio of the image to the ideal ratio
    public void correctAspectRatio(){
        double aspectRatio =((xEnd-xStart)/(yEnd-yStart));
        double idealRatio =((getWidth()/getHeight()));
        double median;
        if (aspectRatio>idealRatio){
            median = (yEnd+yStart)/2;
            yStart = median-(xEnd-xStart)/(2*idealRatio);
            yEnd = median+(xEnd-xStart)/(2*idealRatio);
        }
        else if (aspectRatio<idealRatio){
            median = (xEnd+xStart)/2;
            xStart = median -(yEnd-yStart)*(idealRatio/2);
            xEnd =median +(yEnd-yStart)*(idealRatio/2);
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
