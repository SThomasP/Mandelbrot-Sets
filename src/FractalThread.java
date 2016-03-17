import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class FractalThread extends Thread {

    protected FractalDrawer fractalDrawer;
    protected int xPos, yPos, count;
    protected double xStart, xEnd, yStart, yEnd;
    protected int iterations, loopCount, width, height;
    protected Color[] colours;
    protected BufferedImage canvas;

    public void setValues() {
        synchronized (fractalDrawer){
            //pull the values from the drawer
            xStart = fractalDrawer.getXStart();
            xEnd = fractalDrawer.getXEnd();
            width = fractalDrawer.getWidth();
            height = fractalDrawer.getHeight();
            yStart = fractalDrawer.getYStart();
            yEnd = fractalDrawer.getYEnd();
            iterations =fractalDrawer.getIterations();
            loopCount = fractalDrawer.getLoopCount();
            colours = fractalDrawer.getColors();
            canvas =fractalDrawer.getCanvas();
        }
        //adjust the values so that the thread only works its part of the image
        double tempStart = xStart + (xEnd - xStart) * xPos / count;
        double tempEnd = xStart + (xEnd - xStart) * (xPos + 1) / count;
        width = width / count;
        height = height / count;
        xStart = tempStart;
        xEnd = tempEnd;
        tempStart = yStart + (yEnd - yStart) * yPos / count;
        tempEnd = yStart + (yEnd - yStart) * (yPos + 1) / count;
        yStart = tempStart;
        yEnd = tempEnd;
    }

    public Color colourPixel(Complex zOfZero, Complex constant) {
        //colours the pixel based on the constant and zOfZero
        int deviatesAt = iterations;
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
        if (deviates) {
            synchronized (colours) {
                //divides iterations into loopCount sets
                int loopLength = iterations / loopCount;
                deviatesAt = deviatesAt % loopLength;
                int range = loopLength / (colours.length);
                int n = (deviatesAt / range);
                Color c1 = colours[n % (colours.length)];
                Color c2 = colours[(n + 1) % colours.length];
                //interpolates the rgb values of the two colours based on the iteration count and deviance
                int red = c2.getRed() - ((n + 1) * range - deviatesAt) * (c2.getRed() - c1.getRed()) / range;
                int green = c2.getGreen() - ((n + 1) * range - deviatesAt) * (c2.getGreen() - c1.getGreen()) / range;
                int blue = c2.getBlue() - ((n + 1) * range - deviatesAt) * (c2.getBlue() - c1.getBlue()) / range;
                returnColour = new Color(red, green, blue);
            }
        } else {
            returnColour = Color.black;
        }
        return returnColour;
    }

    public FractalThread(FractalDrawer fractalDrawer, int xPos, int yPos, int count) {
        this.fractalDrawer = fractalDrawer;
        this.xPos= xPos;
        this.yPos= yPos;
        this.count = count;
    }
}
