import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Rectangle2D;

public class FractalDragListener extends MouseAdapter {

    private FractalDrawer fractalDrawer;
    private Point p1;
    private Point p2;
    private Rectangle2D r;


    @Override
    public void mouseReleased(MouseEvent e) {
        if (r != null && (r.getWidth() > 5 && r.getHeight() > 5)) {
            double x1 = fractalDrawer.getXStart() + (fractalDrawer.getXEnd() - fractalDrawer.getXStart()) * (r.getX()) / fractalDrawer.getWidth();
            double x2 = fractalDrawer.getXStart() + (fractalDrawer.getXEnd() - fractalDrawer.getXStart()) * (r.getX() + r.getWidth()) / fractalDrawer.getWidth();
            double y1 = fractalDrawer.getYStart() + (fractalDrawer.getYEnd() - fractalDrawer.getYStart()) * (r.getY()) / fractalDrawer.getHeight();
            double y2 = fractalDrawer.getYStart() + (fractalDrawer.getYEnd() - fractalDrawer.getYStart()) * (r.getY() + r.getHeight()) / fractalDrawer.getHeight();
            fractalDrawer.redrawFractal(x1, y1, x2, y2, fractalDrawer.getIterations());
            //set the rectangles to null afterwards, to prevent a bug where the fractal zooms in upon click
            r = null;
            fractalDrawer.setRectangle(null);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //calculates a rectangle based on the mouses current point and the start point
        p2 = e.getPoint();
        Point startPoint;
        Dimension rectDimensions;
        //the start and end point of the rectangle move based on where p2 is in relation to p1
        if ((p1.getX() >= p2.getX()) && (p1.getY() >= p2.getY())) {
            startPoint = p2;
            rectDimensions = new Dimension((int) (p1.getX() - p2.getX()), (int) (p1.getY() - p2.getY()));
        } else if ((p1.getX() < p2.getX()) && (p1.getY() < p2.getY())) {
            startPoint = p1;
            rectDimensions = new Dimension((int) (p2.getX() - p1.getX()), (int) (p2.getY() - p1.getY()));
        } else if ((p1.getX() < p2.getX()) && (p1.getY() >= p2.getY())) {
            startPoint = new Point((int) p1.getX(), (int) p2.getY());
            rectDimensions = new Dimension((int) (p2.getX() - p1.getX()), (int) (p1.getY() - p2.getY()));

        } else {
            startPoint = new Point((int) p2.getX(), (int) p1.getY());
            rectDimensions = new Dimension((int) (p1.getX() - p2.getX()), (int) (p2.getY() - p1.getY()));
        }
        r = new Rectangle(startPoint, rectDimensions);
        //pass the rectangle back to the panel, if its size is greater that 3
        if (r.getWidth() > 5 && r.getHeight() > 5) {
            fractalDrawer.setRectangle(r);
            fractalDrawer.repaint();
        } else {
            r = null;
            fractalDrawer.setRectangle(null);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        p1 = e.getPoint();
    }


    @Override

    //zooms in if scrolled forward and out if scrolled back
    public void mouseWheelMoved(MouseWheelEvent e) {
        double scaleConstant;
        if (e.getWheelRotation() == 1) {
            //zooms out to 1.5 times
            scaleConstant = 1.5;
        } else {
            //zooms in to 1.5 times
            scaleConstant = 0.75;
        }
        //finds the location of the mouse and then scales the image, based on that
        double mousePoint = fractalDrawer.getXStart() + (fractalDrawer.getXEnd() - fractalDrawer.getXStart()) * e.getX() / fractalDrawer.getWidth();
        double range = (mousePoint - fractalDrawer.getXStart()) * scaleConstant;
        double xStart = mousePoint - range;
        range = (fractalDrawer.getXEnd() - mousePoint) * scaleConstant;
        double xEnd = mousePoint + range;
        mousePoint = fractalDrawer.getYStart() + (fractalDrawer.getYEnd() - fractalDrawer.getYStart()) * e.getY() / fractalDrawer.getHeight();
        range = (mousePoint - fractalDrawer.getYStart()) * scaleConstant;
        double yStart = mousePoint - range;
        range = (fractalDrawer.getYEnd() - mousePoint) * scaleConstant;
        double yEnd = mousePoint + range;
        fractalDrawer.redrawFractal(xStart, yStart, xEnd, yEnd, fractalDrawer.getIterations());
    }

    public FractalDragListener(FractalDrawer fractalDrawer) {
        this.fractalDrawer = fractalDrawer;
    }
}
