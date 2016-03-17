import java.awt.image.BufferedImage;

public class JuliaFractal extends FractalDrawer {

    private Complex constantComplex;
    private FavouritesPanel fP;

    public JuliaFractal() {

    }

    //get the type of fractal for the export image
    public String getType() {
        return "JULIA";
    }

    public void init(int width, int height) {
        //create the threads for the of the image
        threadCount = (int) Math.sqrt(Runtime.getRuntime().availableProcessors()) + 1;
        imageThreads = new FractalThread[threadCount][threadCount];
        setSize(width, height);
        super.init();
        //create the buffered image for the fractal
        canvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        constantComplex = DEFAULT_C;
        setInitialFractal();
    }

    public void init() {
        init(100, 80);
    }

    public FractalDrawer clone(int width, int height) {
        //clone method, creates a new panel of a specified dimension, to export
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
        //set the values of the fractal
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.iterations = iterations;
        correctAspectRatio();
        generateJulia();
        //if this fractal is selected, and there is a designated rBP, set the values of the rBP
        if (selected && rBP != null) {
            rBP.setValues(this.xStart, this.xEnd, this.yStart, this.yEnd, this.iterations);
        }
    }

    public void changeConstant(Complex baseNo) {
        //set the constant of the fractal, and then redraw the fractal and update the label of the favourites panel
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
                    //remakes and run the threads, allowing
                    imageThreads[x][y] = new JuliaThread(this, x, y, threadCount);
                    imageThreads[x][y].start();
                }
            }
            for (int x = 0; x < threadCount; x++) {
                for (int y = 0; y < threadCount; y++) {
                    //wait for the threads to end
                    imageThreads[x][y].join(0);
                }
            }
            //then redraw the image
            repaint();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
