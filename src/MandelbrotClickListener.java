import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MandelbrotClickListener extends MouseAdapter {

    private JuliaFractal jF;
    private MandelbrotFractal mF;

    public MandelbrotClickListener(JuliaFractal jF, MandelbrotFractal mF) {
        this.jF = jF;
        this.mF = mF;
    }

    public void mouseClicked(MouseEvent e) {
        //get the real and imaginary  of the point clicked, then set the constant of the Julia to the clicked point
        double realPart = mF.getXStart() + (mF.getXEnd() - mF.getXStart()) * e.getX() / mF.getWidth();
        double iPart = mF.getYStart() + (mF.getYEnd() - mF.getYStart()) * e.getY() / mF.getHeight();
        jF.changeConstant(new Complex(realPart, iPart));
    }
}
