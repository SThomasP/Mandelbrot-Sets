import java.awt.*;

/**
 * Created by Steffan on 14/03/2016.
 */
public class MandelbrotThread extends FractalThread {

    public MandelbrotThread(FractalDrawer fractalDrawer, int xPos, int yPos, int count) {
        super(fractalDrawer, xPos, yPos, count);
    }

    public void run() {
        setValues();
        for (int x = 0; x < width; x++) {
            double realPart = xStart + (xEnd - xStart) * x / width;
            for (int y = 0; y < height; y++) {
                double iPart = yStart + (yEnd - yStart) * y / height;
                Complex zOfZero = new Complex(realPart, iPart);
                Color pixelColour = colourPixel(zOfZero, zOfZero);
                synchronized (canvas) {
                    canvas.setRGB(x + width * xPos, y + height * yPos, pixelColour.getRGB());
                }
            }
        }
    }
}
