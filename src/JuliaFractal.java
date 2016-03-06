import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Steffan on 03/03/2016.
 */
public class JuliaFractal extends FractalDrawer {

    private Complex baseNo;
    private FavouritesPanel fP;

    public JuliaFractal(){
        setSize(100,80);
        setPreferredSize(new Dimension(100,80));
        canvas =new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        baseNo = MandelbrotViewer.DEFAULT_C;
        redrawFractal(MandelbrotViewer.X_START,MandelbrotViewer.Y_START,MandelbrotViewer.X_END,MandelbrotViewer.Y_END,MandelbrotViewer.ITERATIONS);
    }

    public void setfP(FavouritesPanel fP) {
        this.fP = fP;
    }

    public void resetToDefault(){
        redrawFractal(MandelbrotViewer.X_START,MandelbrotViewer.Y_START,MandelbrotViewer.X_END,MandelbrotViewer.Y_END,MandelbrotViewer.ITERATIONS);
    }

    public void redrawFractal(double xStart, double yStart, double xEnd, double yEnd, int iterations) {
        this.xStart=xStart;
        this.yStart=yStart;
        this.xEnd=xEnd;
        this.yEnd=yEnd;
        this.iterations=iterations;
        correctAspectRatio();
        generateJulias();
        if (selected) {
            rBP.setValues(this.xStart, this.xEnd, this.yStart, this.yEnd, this.iterations);
        }
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
        for (int x = 0; x < getWidth(); x++){
            double realPart = xStart + (xEnd-xStart)*x/getWidth();
            for (int y = 0; y < getHeight(); y++){
                double iPart =yStart + (yEnd-yStart)*y/getHeight();
                Complex zOfZero = new Complex(realPart,iPart);
                Color pixelColour = colourPixel(zOfZero,baseNo);
                canvas.setRGB(x,y,pixelColour.getRGB());

            }
        }
        repaint();
    }

}
