import javax.swing.*;
import java.awt.*;

/**
 * Created by stp1g15 on 04/03/2016.
 */
public class ComplexCellRenderer extends JLabel implements ListCellRenderer<Complex> {

    //renders the labels in the JList to actually show the values of the complex numbers, not just their location in memory
    public  ComplexCellRenderer(){
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }

    public Component getListCellRendererComponent(JList<? extends Complex> jList, Complex complex, int index, boolean isSelected, boolean hasFocus) {
        //if this value is selected, set the text colour to blue, if not the text is black
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
