import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradientModifierAction implements ActionListener {

    private JList<Color> colorList;
    private int direction; //-1 for to the left, 1 for to the right

    public GradientModifierAction(JList<Color> colorList, int direction) {
        this.colorList = colorList;
        this.direction = direction;
    }

    public void actionPerformed(ActionEvent e) {
        Color selectedColor = colorList.getSelectedValue();
        DefaultListModel<Color> listModel = (DefaultListModel<Color>) colorList.getModel();
        //remove the colour from the list, move it in the direction, then add it in again
        int index = colorList.getSelectedIndex();
        colorList.clearSelection();
        //modulo the list, and deal with the pesky -1 integer by moving all the numbers, one loop along.
        //allowing the numbers to move around the list
        int newIndex = (index + direction + listModel.getSize()) % listModel.getSize();
        listModel.remove(index);
        listModel.insertElementAt(selectedColor, newIndex);
        colorList.setSelectedIndex(newIndex);
    }
}
