import javax.swing.*;

/**
 * Created by Steffan on 28/02/2016.
 */
public class MandelbrotViewer extends JFrame {


    public int countDeviations(Complex c){
        Complex current = c;
        int deviatesAt = 101;
        for (int i = 0; i<100; i++){
            if ((current.getMagnitude() < 2) && (deviatesAt == 101)){
                deviatesAt = i;
            }
            current = current.getSquare().add(c);
        }
        return deviatesAt;
    }

    public static void main(String[] args){

    }
}
