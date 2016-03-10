import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Steffan on 10/03/2016.
 */
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
        int index = colorList.getSelectedIndex();
        colorList.clearSelection();
        int newIndex = (index + direction) % listModel.getSize();
        newIndex = (newIndex + listModel.getSize()) % listModel.getSize();
        listModel.remove(index);
        listModel.insertElementAt(selectedColor, newIndex);
        colorList.setSelectedIndex(newIndex);


    }
}
