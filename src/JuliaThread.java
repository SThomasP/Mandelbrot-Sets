import java.awt.*;

/**
 * Created by Steffan on 14/03/2016.
 */
public class JuliaThread extends FractalThread {

    protected Complex constant;

    public JuliaThread(FractalDrawer fractalDrawer, int xPos, int yPos, int count) {
        super(fractalDrawer, xPos, yPos, count);
    }

    public void run() {
        setValues();
        for (int x = 0; x < width; x++) {
            double realPart = xStart + (xEnd - xStart) * x / width;
            for (int y = 0; y < height; y++) {
                double iPart = yStart + (yEnd - yStart) * y / height;
                Complex zOfZero = new Complex(realPart, iPart);
                Color pixelColour = colourPixel(zOfZero, constant);
                synchronized (canvas) {
                    canvas.setRGB(x + xPos * width, y + yPos * height, pixelColour.getRGB());
                }

            }
        }
    }

    public void setValues() {
        super.setValues();
        synchronized (fractalDrawer) {
            constant = fractalDrawer.getConstant();
        }
    }
}
