import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Steffan on 28/02/2016.
 */
public class MandelFractal extends FractalDrawer {


    public MandelFractal(){
    }
    public void init(){
        super.init();
        setSize(100,80);
        setPreferredSize(new Dimension(100,80));
        canvas = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        resetToDefault();
        setBackground(Color.blue);
    }


    public void redrawFractal(double xStart, double yStart,double xEnd,double yEnd,int iterations ){
        this.xStart=xStart;
        this.yStart=yStart;
        this.xEnd=xEnd;
        this.yEnd=yEnd;
        this.iterations=iterations;
        correctAspectRatio();
        generateMandlebrots();
        if (selected) {
            rBP.setValues(this.xStart, this.xEnd, this.yStart, this.yEnd, this.iterations);
        }
    }

    public String getType() {
        return "MANDELBROT";
    }

    public Complex getConstant(){
        return DEFAULT_C;
    }

    public void generateMandlebrots(){
        for (int x = 0; x < canvas.getWidth(); x++){
            double realPart = xStart + (xEnd-xStart)*x/canvas.getWidth();
            for (int y = 0; y < canvas.getHeight(); y++){
                double iPart =yStart + (yEnd-yStart)*y/canvas.getHeight();
                Complex zOfZero = new Complex(realPart,iPart);
                Color pixelColour = colourPixel(zOfZero,zOfZero);
                canvas.setRGB(x,y,pixelColour.getRGB());
            }
        }
        repaint();
    }


}
