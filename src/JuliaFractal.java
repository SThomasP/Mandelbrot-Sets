import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Steffan on 03/03/2016.
 */
public class JuliaFractal extends FractalDrawer {

    private Complex baseNo;

    public JuliaFractal(double xStart, double yStart,double xEnd,double yEnd,int iterations, Complex baseNo ){
        setSize(400,320);
        setLocation(600,0);
        canvas = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        this.baseNo = baseNo;
        redrawFractal(xStart,yStart,xEnd,yEnd,iterations);

    }


    public void redrawFractal(double xStart, double yStart, double xEnd, double yEnd, int iterations) {
        this.xStart=xStart;
        this.yStart=yStart;
        this.xEnd=xEnd;
        this.yEnd=yEnd;
        this.iterations=iterations;
        generateJulias();
    }

    public void generateJulias(){
        for (int x = 0; x < canvas.getWidth(); x++){
            double realPart = xStart + (xEnd-xStart)*x/canvas.getWidth();
            for (int y = 0; y < canvas.getHeight(); y++){
                double iPart =yStart + (yEnd-yStart)*y/canvas.getHeight();
                Complex zOfZero = new Complex(realPart,iPart);
                Color pixelColour = colourPixel(zOfZero,baseNo);
                canvas.setRGB(x,y,pixelColour.getRGB());
            }
        }
        repaint();
    }
}
