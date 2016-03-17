import java.awt.*;

public class MandelbrotThread extends FractalThread {

    public MandelbrotThread(FractalDrawer fractalDrawer, int xPos, int yPos, int count) {
        super(fractalDrawer, xPos, yPos, count);
    }

    public void run() {
        setValues();
        for (int x = 0; x < width; x++) {
            //calculate the two parts of the complex number, doing the real part only when the x pixel changes
            double realPart = xStart + (xEnd - xStart) * x / width;
            for (int y = 0; y < height; y++) {
                double iPart = yStart + (yEnd - yStart) * y / height;
                Complex zOfZero = new Complex(realPart, iPart);
                Color pixelColour = colourPixel(zOfZero, zOfZero);
                synchronized (canvas) {
                    //offset the x pixel by width*xPos, so the correct pixel in the canvas is coloured
                    canvas.setRGB(x + width * xPos, y + height * yPos, pixelColour.getRGB());
                }
            }
        }
    }
}
