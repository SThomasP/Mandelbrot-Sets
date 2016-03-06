import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by Steffan on 06/03/2016.
 */
public class FractalDragListener extends MouseAdapter{

    private FractalDrawer fractalDrawer;
    private Point p1;
    private Point p2;
    private Rectangle2D r;


    @Override
    public void mouseReleased(MouseEvent e) {
        if (!(r==null)) {
            double x1 = fractalDrawer.getxStart() + (fractalDrawer.getxEnd() - fractalDrawer.getxStart()) * (r.getX()) / fractalDrawer.getWidth();
            double x2 = fractalDrawer.getxStart() + (fractalDrawer.getxEnd() - fractalDrawer.getxStart()) * (r.getX() + r.getWidth()) / fractalDrawer.getWidth();
            double y1 = fractalDrawer.getyStart() + (fractalDrawer.getyEnd() - fractalDrawer.getyStart()) * (r.getY()) / fractalDrawer.getHeight();
            double y2 = fractalDrawer.getyStart() + (fractalDrawer.getyEnd() - fractalDrawer.getyStart()) * (r.getY() + r.getHeight()) / fractalDrawer.getHeight();
            fractalDrawer.redrawFractal(x1, y1, x2, y2, fractalDrawer.getIterations());
            r=null;
            fractalDrawer.setRectangle(null);
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        p2 =e.getPoint();
        Point startPoint;
        Dimension rectDimensions;
        if ((p1.getX() >= p2.getX()) && (p1.getY() >= p2.getY())){
            startPoint = p2;
            rectDimensions = new Dimension((int) (p1.getX()-p2.getX()),(int) (p1.getY()-p2.getY()));
        }
        else if((p1.getX() < p2.getX()) && (p1.getY() < p2.getY())){
            startPoint = p1;
            rectDimensions = new Dimension((int) (p2.getX()-p1.getX()),(int) (p2.getY()-p1.getY()));
        }
        else if ((p1.getX() < p2.getX()) && (p1.getY() >= p2.getY())){
            startPoint = new Point((int)p1.getX(), (int) p2.getY());
            rectDimensions = new Dimension((int) (p2.getX()-p1.getX()),(int) (p1.getY()-p2.getY()));

        }
        else{
            startPoint = new Point((int)p2.getX(), (int) p1.getY());
            rectDimensions = new Dimension((int) (p1.getX()-p2.getX()),(int) (p2.getY()-p1.getY()));
        }
        if (rectDimensions.getWidth()>5) {
            r = new Rectangle(startPoint, rectDimensions);
            fractalDrawer.setRectangle(r);
            fractalDrawer.repaint();
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        p1 =e.getPoint();
    }


    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        //TODO:Get fractal to scale based on mouse point rather than centre
        double scaleConstant;
        System.out.println(e.getX()+" "+e.getY());
        if (e.getWheelRotation()==1){
            scaleConstant=1.5;
        }
        else{
            scaleConstant=0.75;
        }

        double median = (fractalDrawer.getxStart()+fractalDrawer.getxEnd())/2.0;
        double range = (median-fractalDrawer.getxStart())*scaleConstant;
        double xStart = median-range;
        double xEnd = median+range;
        median = (fractalDrawer.getyStart()+fractalDrawer.getyEnd())/2.0;
        range =(median-fractalDrawer.getyStart())*scaleConstant;
        double yStart = median-range;
        double yEnd = median+range;
        fractalDrawer.redrawFractal(xStart,yStart,xEnd,yEnd,fractalDrawer.getIterations());
    }

    public FractalDragListener(FractalDrawer fractalDrawer){
        this.fractalDrawer=fractalDrawer;
    }
}
