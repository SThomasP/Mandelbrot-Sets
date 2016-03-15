import java.awt.image.BufferedImage;

/**
 * Created by Steffan on 03/03/2016.
 */
public class JuliaFractal extends FractalDrawer {

    private Complex constantComplex;
    private FavouritesPanel fP;

    public JuliaFractal() {

    }

    public String getType() {
        return "JULIA";
    }

    public void init(int width, int height) {
        threadCount = (int) Math.sqrt(Runtime.getRuntime().availableProcessors()) + 1;
        imageThreads = new FractalThread[threadCount][threadCount];
        setSize(width, height);
        super.init();
        canvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        constantComplex = DEFAULT_C;
        setInitialFractal();
    }

    public void init() {
        init(100, 80);
    }

    public FractalDrawer clone(int width, int height) {
        JuliaFractal tempFractal = new JuliaFractal();
        tempFractal.init(width, height);
        tempFractal.setColors(drawColors, loopCount);
        tempFractal.redrawFractal(xStart, yStart, xEnd, yEnd, iterations);
        tempFractal.changeConstant(constantComplex);
        return tempFractal;
    }

    public void setFP(FavouritesPanel fP) {
        this.fP = fP;
    }


    public void redrawFractal(double xStart, double yStart, double xEnd, double yEnd, int iterations) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.iterations = iterations;
        correctAspectRatio();
        generateJulia();
        if (selected && rBP != null) {
            rBP.setValues(this.xStart, this.xEnd, this.yStart, this.yEnd, this.iterations);
        }
    }

    public void changeConstant(Complex baseNo) {
        this.constantComplex = baseNo;
        generateJulia();
        if (fP != null) {
            fP.updateLabel();
        }
    }

    public Complex getConstant() {
        return constantComplex;
    }

    public void generateJulia() {
        try {
            for (int x = 0; x < threadCount; x++) {
                for (int y = 0; y < threadCount; y++) {
                    imageThreads[x][y] = new JuliaThread(this, x, y, threadCount);
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
