import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Steffan on 28/02/2016.
 */
public class MandelFractal extends JPanel {

    protected BufferedImage canvas;
    protected int xOffset;
    protected int yOffset;

    public MandelFractal(int xOffset, int yOffset, int x, int y){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        setSize(x,y);
        canvas = new BufferedImage(x,y,BufferedImage.TYPE_INT_ARGB);
       generateMandlebrots();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }

    public void colourBackGround(Color c){
        for (int x =0; x <canvas.getWidth(); x++){
            for (int y = 0; y<canvas.getHeight();y++){
                canvas.setRGB(x,y,c.getRGB());
            }
        }
        repaint();
    }

    public void generateMandlebrots(){
        for (int x = 0; x < canvas.getWidth(); x++){
            for (int y = 0; y < canvas.getHeight(); y++){
                Color pixelColour = colourPixel(new Complex(x+xOffset,y+yOffset));
                canvas.setRGB(x,y,pixelColour.getRGB());
            }
        }
        repaint();
    }

    public Color colourPixel(Complex c) {
        Complex current = c;
        int n = 255;
        int deviatesAt = n;
        for (int i = 0; i < n; i++) {
            if ((current.getMagnitude() < 2) && (deviatesAt == n)) {
                deviatesAt = i;
            }
            current = current.getSquare().add(c);
        }
        Color returnColour = new Color(deviatesAt, 255, 255);
        return returnColour;
    }
}
