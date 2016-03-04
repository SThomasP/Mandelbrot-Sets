import javax.swing.*;
import java.awt.*;

/**
 * Created by stp1g15 on 04/03/2016.
 */
public class ComplexCellRenderer extends JLabel implements ListCellRenderer<Complex> {


    public  ComplexCellRenderer(){
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }
//TODO Fix CellRender so cells appear selected when selected, somthing to do
    public Component getListCellRendererComponent(JList<? extends Complex> jList, Complex complex, int index, boolean isSelected, boolean hasFocus) {

        if (isSelected) {
            setForeground(Color.blue);
        }
        else {
            setForeground(Color.black);
        }
        setFont(jList.getFont());
        setText(complex.getStringValue());
        return this;
    }
}
