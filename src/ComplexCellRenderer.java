import javax.swing.*;
import java.awt.*;

public class ComplexCellRenderer extends JLabel implements ListCellRenderer<Complex> {

    //renders the labels in the JList to actually show the values of the complex numbers, not just their location in memory
    public ComplexCellRenderer() {
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }

    public Component getListCellRendererComponent(JList<? extends Complex> jList, Complex complex, int index, boolean isSelected, boolean hasFocus) {
        //Set the colours of the JLabels to match the JList
        setOpaque(true);
        if (isSelected) {
            setForeground(jList.getSelectionForeground());
            setBackground(jList.getSelectionBackground());
        } else {
            setForeground(jList.getForeground());
            setBackground(jList.getBackground());
        }
        //set the text of the label to the value of the constant
        setFont(jList.getFont());
        setText(complex.getStringValue());
        return this;
    }
}
