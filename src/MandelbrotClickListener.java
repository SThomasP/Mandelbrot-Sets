import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Steffan on 29/02/2016.
 */
public class MandelbrotClickListener extends MouseAdapter {

    private JuliaFractal jF;
    private MandelbrotFractal mF;

    public MandelbrotClickListener(JuliaFractal jF, MandelbrotFractal mF) {
        this.jF = jF;
        this.mF = mF;
    }

    public void mouseClicked(MouseEvent e) {
        double realPart = mF.getxStart() + (mF.getxEnd() - mF.getxStart()) * e.getX() / mF.getWidth();
        double iPart = mF.getyStart() + (mF.getyEnd() - mF.getyStart()) * e.getY() / mF.getHeight();
        jF.changeConstant(new Complex(realPart, iPart));
    }
}
