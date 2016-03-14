import java.awt.image.BufferedImage;

/**
 * Created by Steffan on 28/02/2016.
 */
public class MandelbrotFractal extends FractalDrawer {


    public MandelbrotFractal() {
    }

    public void init(int width, int height) {
        threadCount = (int) Math.sqrt(Runtime.getRuntime().availableProcessors());
        imageThreads = new FractalThread[threadCount][threadCount];
        setSize(width, height);
        canvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        super.init();
        setInitialFractal();
    }

    public void init() {
        init(100, 80);
    }

    public FractalDrawer clone(int width, int height) {
        MandelbrotFractal tempFractal = new MandelbrotFractal();
        tempFractal.init(width, height);
        tempFractal.setColors(drawColors, loopCount);
        tempFractal.redrawFractal(xStart, yStart, xEnd, yEnd, iterations);
        return tempFractal;
    }

    public void redrawFractal(double xStart, double yStart, double xEnd, double yEnd, int iterations) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.iterations = iterations;
        correctAspectRatio();
        generateMandelbrot();
        if (selected && rBP != null) {
            rBP.setValues(this.xStart, this.xEnd, this.yStart, this.yEnd, this.iterations);
        }
    }

    public String getType() {
        return "MANDELBROT";
    }

    public Complex getConstant() {
        return DEFAULT_C;
    }

    public void generateMandelbrot() {
        try {
            for (int x = 0; x < threadCount; x++) {
                for (int y = 0; y < threadCount; y++) {
                    imageThreads[x][y] = new MandelbrotThread(this, x, y, threadCount);
                    imageThreads[x][y].start();
                }
            }
            for (int x = 0; x < threadCount; x++) {
                for (int y = 0; y < threadCount; y++) {
                    imageThreads[x][y].join(0);
                }
            }
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
