import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Steffan on 04/03/2016.
 */
public class FavouritesPanel extends JPanel {

    private JuliaFractal juliaFractal;
    private JList<Complex> favouritedNumbers;
    private DefaultListModel<Complex> listModel;
    private JLabel currentComplex;
    private JButton addButton;
    private JButton removeButton;


    public FavouritesPanel(JuliaFractal juliaFractal){
        this.juliaFractal = juliaFractal;
        setSize(300,280);
        setLocation(600,320);
        setBorder(BorderFactory.createLineBorder(Color.black));
        addButton = new JButton("+");
        removeButton = new JButton("-");
        Font buttonFont = new Font("Arial",Font.BOLD,18);
        listModel = new DefaultListModel<>();
        addButton.setFont(buttonFont);
        removeButton.setFont(buttonFont);
        removeButton.setForeground(Color.red);
        removeButton.setEnabled(false);
        addButton.setForeground(Color.green);
        ActionListener addListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (!listModel.contains(juliaFractal.getConstant())) {
                    listModel.addElement(juliaFractal.getConstant());
                }
            }
        };
        ActionListener removeListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int index = favouritedNumbers.getSelectedIndex();
                listModel.remove(index);
                if (listModel.size()==0){
                    removeButton.setEnabled(false);
                }
            }
        };
        addButton.addActionListener(addListener);
        removeButton.addActionListener(removeListener);
        currentComplex = new JLabel();
        favouritedNumbers = new JList<>(listModel);
        favouritedNumbers.setCellRenderer(new ComplexCellRenderer());
        favouritedNumbers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(currentComplex);
        add(addButton);
        add(removeButton);
        add(favouritedNumbers);
        updateLabel();
    }

    public void updateLabel(){
        currentComplex.setText(juliaFractal.getConstant().getStringValue());
    }

}
