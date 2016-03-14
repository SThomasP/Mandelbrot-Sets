import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by stp1g15 on 14/03/2016.
 */
public class FractalThread extends Thread{

    FractalDrawer fractalDrawer;
    int xPos, yPos;

    public void run(){
        double xStart,xEnd,yStart,yEnd;
        int iterations, loopCount;
        Color[] colors;
        BufferedImage canvas;
        synchronized (fractalDrawer){
            xStart = fractalDrawer.getxStart();
            xEnd = fractalDrawer.getxEnd();
            yStart = fractalDrawer.getyStart();
            yEnd = fractalDrawer.getyEnd();
            iterations =fractalDrawer.getIterations();
            loopCount = fractalDrawer.getLoopCount();
            colors = fractalDrawer.getColors();
            canvas =fractalDrawer.getCanvas();
        }

    }

    public FractalThread(FractalDrawer fractalDrawer, int xPos, int yPos){
        this.fractalDrawer = fractalDrawer;
        this.xPos= xPos;
        this.yPos= yPos;
    }
}

/*
0                 1             2
xStart____________|_____________xEnd
xStart____________xEnd
		xStart__________xEnd

 */
