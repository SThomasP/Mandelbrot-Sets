import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Steffan on 28/02/2016.
 */
public class MandelFractal extends JPanel {

    protected BufferedImage canvas;
    protected double xOffset;
    protected double yOffset;

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
                double realPart = (x+xOffset)/200.0;
                double iPart = (y+yOffset)/200.0;
                Complex iOfZero = new Complex(realPart,iPart);
                Color pixelColour = colourPixel(iOfZero);
                canvas.setRGB(x,y,pixelColour.getRGB());
            }
        }
        repaint();
    }

    public Color colourPixel(Complex c) {
        Complex current = c;
        int n = 100;
        int deviatesAt=0;
        boolean deviates = false;

        for (int i = 0; i < n; i++) {
            double msquared = current.magnitudeSquared();
            if ((msquared > 4.0) && (!deviates)) {
                deviatesAt = i;
                deviates = true;
            }
            current = current.getSquare().add(c);
        }
        Color returnColour;
        if (deviates){
            int colorNo = deviatesAt*25/10;
            int colourNo = deviatesAt*50/20;
            returnColour = new Color(255-colorNo,0,255-colorNo);
        }
        else{
            returnColour = Color.black;
        }
        return returnColour;
    }
}
