import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Steffan on 03/03/2016.
 */
public class JuliaFractal extends FractalDrawer {

    private Complex baseNo;
    private FavouritesPanel fP;

    public JuliaFractal(int parentWidth){
        Dimension d = new Dimension((int) Math.round(parentWidth*0.4), (int) Math.round(parentWidth*0.4/1.25));
        setPreferredSize(d);
        setSize(d);
        baseNo = MandelbrotViewer.DEFAULT_C;
        canvas = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        redrawFractal(MandelbrotViewer.X_START,MandelbrotViewer.Y_START,MandelbrotViewer.X_END,MandelbrotViewer.Y_END,MandelbrotViewer.ITERATIONS);
    }

    public void setfP(FavouritesPanel fP) {
        this.fP = fP;
    }

    public void resetToDefault(){
        redrawFractal(MandelbrotViewer.X_START,MandelbrotViewer.Y_START,MandelbrotViewer.X_END,MandelbrotViewer.Y_END,MandelbrotViewer.ITERATIONS);
        changeConstant(MandelbrotViewer.DEFAULT_C);
    }

    public void regenerateCanvas(){
        canvas = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
        resetToDefault();
    }

    public void redrawFractal(double xStart, double yStart, double xEnd, double yEnd, int iterations) {
        this.xStart=xStart;
        this.yStart=yStart;
        this.xEnd=xEnd;
        this.yEnd=yEnd;
        this.iterations=iterations;
        correctAspectRatio();
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
