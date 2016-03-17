import java.awt.image.BufferedImage;

public class MandelbrotFractal extends FractalDrawer {


    public MandelbrotFractal() {
    }

    public void init(int width, int height) {
        //create the threads based on processor count
        threadCount = (int) Math.sqrt(Runtime.getRuntime().availableProcessors());
        imageThreads = new FractalThread[threadCount][threadCount];
        setSize(width, height);
        canvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        super.init();
        setInitialFractal();
    }

    public void init() {
        //default initialisation size, used for when not exporting
        init(100, 80);
    }

    public FractalDrawer clone(int width, int height) {
        //clone method, used to export image
        MandelbrotFractal tempFractal = new MandelbrotFractal();
        //create a near identical fractal, with the desired export dimensions
        tempFractal.init(width, height);
        tempFractal.setColors(drawColors, loopCount);
        tempFractal.redrawFractal(xStart, yStart, xEnd, yEnd, iterations);
        return tempFractal;
    }

    public void redrawFractal(double xStart, double yStart, double xEnd, double yEnd, int iterations) {
        //set the values, then set correct the aspect ratio, then redraw the fractal
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.iterations = iterations;
        correctAspectRatio();
        generateMandelbrot();
        //update the values on the panel, as they may have been changed by the aspect ratio
        if (selected && rBP != null) {
            rBP.setValues(this.xStart, this.xEnd, this.yStart, this.yEnd, this.iterations);
        }
    }

    public String getType() {
        return "MANDELBROT";
    }

    public Complex getConstant() {
        //return the default complex number of 0,0 allowing save to file function avoiding if statements
        return DEFAULT_C;
    }

    public void generateMandelbrot() {
        try {
            for (int x = 0; x < threadCount; x++) {
                for (int y = 0; y < threadCount; y++) {
                    //create a set of threads and start them
                    imageThreads[x][y] = new MandelbrotThread(this, x, y, threadCount);
                    imageThreads[x][y].start();
                }
            }
            for (int x = 0; x < threadCount; x++) {
                for (int y = 0; y < threadCount; y++) {
                    //wait for all the threads to end
                    imageThreads[x][y].join(0);
                }
            }
            //redraw the fractal
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
