import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Steffan on 28/02/2016.
 */
public class MandelFractal extends JPanel {

    protected BufferedImage canvas;
    protected double xStart;
    protected double yStart;
    protected double xEnd;
    protected double yEnd;
    protected int iterations;

    public MandelFractal(double xStart, double yStart,double xEnd,double yEnd,int iterations  ){
        setSize(600,480);
        canvas = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        redrawFractal(xStart,yStart,xEnd,yEnd,iterations);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }

    public void redrawFractal(double xStart, double yStart,double xEnd,double yEnd,int iterations ){
        this.xStart=xStart;
        this.yStart=yStart;
        this.xEnd=xEnd;
        this.yEnd=yEnd;
        this.iterations=iterations;
        generateMandlebrots();
    }

    public void generateMandlebrots(){
        for (int x = 0; x < canvas.getWidth(); x++){
            for (int y = 0; y < canvas.getHeight(); y++){
                double realPart = xStart + (xEnd-xStart)*x/canvas.getWidth();
                double iPart =yStart + (yEnd-yStart)*y/canvas.getHeight();
                Complex iOfZero = new Complex(realPart,iPart);
                Color pixelColour = colourPixel(iOfZero);
                canvas.setRGB(x,y,pixelColour.getRGB());
            }
        }
        repaint();
    }

    public Color colourPixel(Complex c) {
        Complex current = c;
        int deviatesAt=0;
        boolean deviates = false;
        for (int i = 0; i < iterations; i++) {
            double msquared = current.magnitudeSquared();
            if ((msquared > 4.0) && (!deviates)) {
                deviatesAt = i;
                deviates = true;
            }
            current = current.getSquare().add(c);
        }
        Color returnColour;
        if (deviates){
            int colourNo = deviatesAt*255/iterations;
            returnColour = new Color(0,0,255-colourNo);
        }
        else{
            returnColour = Color.black;
        }
        return returnColour;
    }
}
