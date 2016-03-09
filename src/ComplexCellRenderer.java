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
        //Set the colors of the JLabels to match the JList
        setOpaque(true);
        if (isSelected) {
            setForeground(jList.getSelectionForeground());
            setBackground(jList.getSelectionBackground());
        }
        else {
            setForeground(jList.getForeground());
            setBackground(jList.getBackground());
        }
        setFont(jList.getFont());
        setText(complex.getStringValue());
        return this;
    }
}
