import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Steffan on 03/03/2016.
 */
public class JuliaFractal extends FractalDrawer {

    private Complex baseNo;
    private FavouritesPanel fP;

    public JuliaFractal(double xStart, double yStart,double xEnd,double yEnd,int iterations, Complex baseNo){
        setSize(400,320);
        setLocation(600,0);
        canvas = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        this.baseNo = baseNo;
        redrawFractal(xStart,yStart,xEnd,yEnd,iterations);

    }

    public void setfP(FavouritesPanel fP) {
        this.fP = fP;
    }

    public void resetToDefault(){
        redrawFractal(MandelbrotViewer.XSTART,MandelbrotViewer.YSTART,MandelbrotViewer.XEND,MandelbrotViewer.YEND,MandelbrotViewer.ITERATIONS);
        changeConstant(MandelbrotViewer.DEFAULT_C);
    }

    public void redrawFractal(double xStart, double yStart, double xEnd, double yEnd, int iterations) {
        this.xStart=xStart;
        this.yStart=yStart;
        this.xEnd=xEnd;
        this.yEnd=yEnd;
        this.iterations=iterations;
        generateJulias();
    }

    public void changeConstant(Complex baseNo){
        this.baseNo = baseNo;
        generateJulias();
        fP.updateLabel();
    }

    public Complex getConstant(){
        return baseNo;
    }

    public void generateJulias(){
        //TODO: thread this method to make it run quicker for larger monitors
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
