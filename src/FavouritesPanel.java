import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    public FavouritesPanel(JuliaFractal juliaFractal){
        this.juliaFractal = juliaFractal;
        this.juliaFractal.setfP(this);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new GridBagLayout());
        currentComplex = new JLabel();
        currentComplex.setHorizontalAlignment(JLabel.CENTER);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor=GridBagConstraints.CENTER;
        c.gridx=0;
        c.gridy=0;
        c.weighty = 0.0;
        c.gridwidth=2;
        add(currentComplex,c);
        addButton = new JButton("+");
        c.fill=GridBagConstraints.NONE;
        c.gridy=1;
        c.anchor=GridBagConstraints.LINE_END;
        c.gridwidth=1;
        c.weightx=0.5;
        add(addButton,c);
        c.gridx=1;
        c.anchor=GridBagConstraints.LINE_START;
        removeButton = new JButton("-");
        add(removeButton,c);
        Font buttonFont = new Font("Arial",Font.BOLD,12);
        listModel = new DefaultListModel<>();
        addButton.setFont(buttonFont);
        removeButton.setFont(buttonFont);
        removeButton.setForeground(Color.red);
        removeButton.setEnabled(false);
        addButton.setForeground(Color.green);
        ActionListener addListener = actionEvent -> {
            boolean inThere = false;
            for (int i = 0; i<listModel.getSize();i++){
                if (listModel.get(i).equals(juliaFractal.getConstant())){
                    inThere = true;
                }
            }
            if(!inThere){
                listModel.addElement(juliaFractal.getConstant());
            }
        };
<<<<<<< HEAD
        ActionListener removeListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int index = favouritedNumbers.getSelectedIndex();
                favouritedNumbers.clearSelection();
                listModel.remove(index);
                if (listModel.size()==0){
                    removeButton.setEnabled(false);
                }
=======
        ActionListener removeListener = actionEvent -> {
            int index = favouriteNumbers.getSelectedIndex();
            listModel.remove(index);
            if (listModel.size()==0){
                removeButton.setEnabled(false);
>>>>>>> LayOutManager
            }
        };
        addButton.addActionListener(addListener);
        removeButton.addActionListener(removeListener);
<<<<<<< HEAD
        currentComplex = new JLabel();
        currentComplex.setSize(getWidth(),50);
        currentComplex.setVerticalAlignment(SwingConstants.CENTER);
        currentComplex.setHorizontalAlignment(SwingConstants.CENTER);
        favouritedNumbers = new JList<>(listModel);
        favouritedNumbers.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                removeButton.setEnabled(true);
                if (!favouritedNumbers.isSelectionEmpty()) {
                    juliaFractal.changeConstant(favouritedNumbers.getSelectedValue());
                }
            }
        });
        favouritedNumbers.setCellRenderer(new ComplexCellRenderer());
        favouritedNumbers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(currentComplex);
        add(addButton);
        add(removeButton);
        add(favouritedNumbers);
=======
        favouriteNumbers = new JList<>(listModel);
        c.fill=GridBagConstraints.BOTH;
        c.ipady=12;
        c.weighty=1.0;
        c.gridwidth=2;
        c.gridy=2;
        c.gridx=0;
        add(favouriteNumbers,c);
        favouriteNumbers.setCellRenderer(new ComplexCellRenderer());
        favouriteNumbers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
>>>>>>> LayOutManager
        updateLabel();
    }

    public void updateLabel(){
        currentComplex.setText(juliaFractal.getConstant().getStringValue());
    }

}
