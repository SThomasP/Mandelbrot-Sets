import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Steffan on 29/02/2016.
 */
public class MandelClickListener extends MouseAdapter {

    private JuliaFractal jF;
    private MandelFractal mF;
    private FavouritesPanel fP;

    public MandelClickListener(JuliaFractal jF, MandelFractal mF, FavouritesPanel fP){
        this.jF=jF;
        this.mF=mF;
        this.fP=fP;
    }

    public void mousePressed(MouseEvent e) {
        double realPart = mF.getxStart() + (mF.getxEnd()-mF.getxStart())*e.getX()/mF.getWidth();
        double iPart = mF.getyStart() + (mF.getyEnd()-mF.getyStart())*e.getY()/mF.getHeight();
        jF.changeConstant(new Complex(realPart,iPart));
        fP.updateLabel();
    }
}
