import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created by Steffan on 04/03/2016.
 */
public class FavouritesPanel extends JPanel {

    private JuliaFractal juliaFractal;
    private JList<Complex> favouriteNumbers;
    private DefaultListModel<Complex> listModel;
    private JLabel currentComplex;
    private JButton addButton;
    private JButton removeButton;


    public FavouritesPanel(JuliaFractal juliaFractal) {
        this.juliaFractal = juliaFractal;
        this.juliaFractal.setfP(this);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new GridBagLayout());
        //TODO get so currentConstant and favouriteNumbers doesn't expand all the time
        currentComplex = new JLabel();
        currentComplex.setHorizontalAlignment(JLabel.CENTER);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.0;
        c.gridwidth = 2;
        add(currentComplex, c);
        addButton = new JButton("+");
        c.fill = GridBagConstraints.NONE;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridwidth = 1;
        c.weightx = 0.5;
        add(addButton, c);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        removeButton = new JButton("-");
        add(removeButton, c);
        Font buttonFont = new Font("Arial", Font.BOLD, 12);
        listModel = new DefaultListModel<>();
        addButton.setFont(buttonFont);
        removeButton.setFont(buttonFont);
        removeButton.setForeground(Color.red);
        removeButton.setEnabled(false);
        addButton.setForeground(Color.green);
        ActionListener addListener = actionEvent -> {
            boolean inThere = false;
            for (int i = 0; i < listModel.getSize(); i++) {
                if (listModel.get(i).equals(juliaFractal.getConstant())) {
                    inThere = true;
                }
            }
            if (!inThere) {
                listModel.addElement(juliaFractal.getConstant());
            }
        };
        ActionListener removeListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int index = favouriteNumbers.getSelectedIndex();
                favouriteNumbers.clearSelection();
                listModel.remove(index);
                if (listModel.size() == 0) {
                    removeButton.setEnabled(false);
                }
            }
        };

        addButton.addActionListener(addListener);
        removeButton.addActionListener(removeListener);
        favouriteNumbers = new JList<>(listModel);
        favouriteNumbers.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                removeButton.setEnabled(true);
                if (!favouriteNumbers.isSelectionEmpty()) {
                    juliaFractal.changeConstant(favouriteNumbers.getSelectedValue());
                }
            }
        });
        favouriteNumbers.setCellRenderer(new ComplexCellRenderer());
        favouriteNumbers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 12;
        c.weighty = 1.0;
        c.gridwidth = 2;
        c.gridy = 2;
        c.gridx = 0;
        add(favouriteNumbers, c);
        updateLabel();
    }

    public void updateLabel() {
        currentComplex.setText(juliaFractal.getConstant().getStringValue());
    }

}