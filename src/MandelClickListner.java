import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Steffan on 29/02/2016.
 */
public class MandelClickListner extends MouseAdapter {

    private JuliaFractal jF;
    private MandelFractal mF;

    public MandelClickListner(JuliaFractal jF, MandelFractal mF){
        this.jF=jF;
        this.mF=mF;
    }

    public void mousePressed(MouseEvent e) {
        double realPart = mF.getxStart() + (mF.getxEnd()-mF.getxStart())*e.getX()/mF.getWidth();
        double iPart = mF.getyStart() + (mF.getyEnd()-mF.getyStart())*e.getY()/mF.getHeight();
        jF.changeConstant(new Complex(realPart,iPart));
    }
}
