import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Rectangle2D;
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
    protected Rectangle2D rectangle;
    protected boolean selected;
    protected RedrawButtonsPanel rBP;
    protected Color[] drawColors;


    public void setRBP(RedrawButtonsPanel rBP) {
        this.rBP = rBP;
    }

    public RedrawButtonsPanel getrBP(){
        return rBP;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setRectangle(Rectangle2D rectangle) {
        this.rectangle = rectangle;
    }

    public FractalDrawer(){

    }

    public void init(){
        setBorder(BorderFactory.createLineBorder(Color.black));
        addComponentListener(new ComponentListener() {
            @Override
            //resize the buffered image when the panel gets resized
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
        FractalDragListener dragListener = new FractalDragListener(this);
        addMouseListener(dragListener);
        addMouseMotionListener(dragListener);
        addMouseWheelListener(dragListener);
        drawColors = new Color[5];
        drawColors[0] = new Color(12288);
        drawColors[1] = new Color(1591320);
        drawColors[2] =  new Color(4749312);
        drawColors[3] = new Color(10863104);
        drawColors[4] = new Color(12768847);
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
        if(!(rectangle==null)){
            g2.setColor(Color.white);
            g2.draw(rectangle);
        }
    }

    public abstract void resetToDefault();


    //sets the Aspect Ratio of the image to the ideal ratio
    public void correctAspectRatio(){
        double aspectRatio =( (xEnd-xStart)/(yEnd-yStart));
        double width = getWidth();
        double height = getHeight();
        double idealRatio =(width/height);
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


    //calculates the color of the pixel based on the constant and the complex point
    public Color colourPixel(Complex zOfZero, Complex constant) {
        int deviatesAt=iterations;
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
            int range = iterations / (drawColors.length - 1);
            int n = deviatesAt / range;
            if (n==drawColors.length-1){
                n=n-1;
            }
            Color c1 = drawColors[n];
            Color c2 = drawColors[n + 1];
            int red = c2.getRed()-((n+1)*range-deviatesAt)*(c2.getRed()-c1.getRed())/range;
            int green = c2.getGreen()-((n+1)*range-deviatesAt)*(c2.getGreen()-c1.getGreen())/range;
            int blue = c2.getBlue()-((n+1)*range-deviatesAt)*(c2.getBlue()-c1.getBlue())/range;
            returnColour = new Color(red,green,blue);
        }
        else{
            returnColour = Color.black;
        }
        return returnColour;
    }
}
