import java.awt.*;

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
                //colour pixel using the constant of the fractal, and the position in the image
                Color pixelColour = colourPixel(zOfZero, constant);
                synchronized (canvas) {
                    canvas.setRGB(x + xPos * width, y + yPos * height, pixelColour.getRGB());
                }

            }
        }
    }

    public void setValues() {
        super.setValues();
        //run the super value of the method, then get the constant of the fractal
        synchronized (fractalDrawer) {
            constant = fractalDrawer.getConstant();
        }
    }
}
