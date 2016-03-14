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
    protected int loopCount;
    protected int threadCount;
    protected FractalThread[][] imageThreads;
    //store the default loading information as static allowing it to be easily accessed by all methods
    public static double X_START = -2.0;
    public static double Y_START = -1.6;
    public static double X_END = 2.0;
    public static double Y_END = 1.6;
    public static int ITERATIONS = 100;
    public static Complex DEFAULT_C = new Complex(0, 0);
    public static Color[] DEFAULT_COLOURS = {new Color(-11181456), new Color(-11612732), new Color(-3672988), new Color(-38037), new Color(-3912360)};
    public static int LOOP_COUNT = 5;


    public void setRBP(RedrawButtonsPanel rBP) {
        this.rBP = rBP;
    }

    public RedrawButtonsPanel getrBP() {
        return rBP;
    }

    public boolean isSelected() {
        return selected;
    }

    public abstract FractalDrawer clone(int width, int height);

    public BufferedImage getCanvas() {
        return canvas;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setRectangle(Rectangle2D rectangle) {
        this.rectangle = rectangle;
    }

    public FractalDrawer() {

    }

    public void init() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        addComponentListener(new ComponentListener() {
            @Override
            //resize the buffered image when the panel gets resized
            public void componentResized(ComponentEvent e) {
                canvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                redrawFractal(xStart, yStart, xEnd, yEnd, iterations);
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
    }


    public Color[] getColors() {
        return drawColors;
    }

    public void setColors(Color[] tempColors, int loopCount) {
        drawColors = tempColors;
        setBackground(drawColors[0]);
        this.loopCount = loopCount;
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

    public int getIterations() {
        return iterations;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        synchronized (canvas) {
            g2.drawImage(canvas, null, null);
        }
        if (!(rectangle == null)) {
            g2.setColor(Color.white);
            g2.draw(rectangle);
        }
    }

    public void setInitialFractal() {
        setColors(DEFAULT_COLOURS, LOOP_COUNT);
        redrawFractal(X_START, Y_START, X_END, Y_END, ITERATIONS);
    }

    public void resetToDefault() {
        redrawFractal(X_START, Y_START, X_END, Y_END, iterations);
    }


    //sets the Aspect Ratio of the image to the ideal ratio
    public void correctAspectRatio() {
        double aspectRatio = ((xEnd - xStart) / (yEnd - yStart));
        double width = getWidth();
        double height = getHeight();
        double idealRatio = (width / height);
        double median;
        if (aspectRatio > idealRatio) {
            median = (yEnd + yStart) / 2;
            yStart = median - (xEnd - xStart) / (2 * idealRatio);
            yEnd = median + (xEnd - xStart) / (2 * idealRatio);
        } else if (aspectRatio < idealRatio) {
            median = (xEnd + xStart) / 2;
            xStart = median - (yEnd - yStart) * (idealRatio / 2);
            xEnd = median + (yEnd - yStart) * (idealRatio / 2);
        }
    }

    public abstract void redrawFractal(double xStart, double yStart, double xEnd, double yEnd, int iterations);

    public abstract String getType();

    public abstract Complex getConstant();

    public int getLoopCount() {
        return loopCount;
    }

    //calculates the color of the pixel based on the constant and the complex point
}
