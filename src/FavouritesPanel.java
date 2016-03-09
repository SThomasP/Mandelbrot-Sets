import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
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
    private JButton copyButton;
    private JButton saveM, saveJ;
    private JButton removeButton;


    public FavouritesPanel(JuliaFractal juliaFractal) {
        this.juliaFractal = juliaFractal;
        this.juliaFractal.setfP(this);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void init(MandelFractal mandelFractal){
        //initialises the favourites panel (the panel on the right of the screen)
        setLayout(new GridBagLayout());
        currentComplex = new JLabel("-0.0000 + 0.0000i");
        currentComplex.setHorizontalAlignment(JLabel.CENTER);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.0;
        c.gridwidth = 2;
        add(currentComplex, c);
        setAllDimensions(currentComplex.getPreferredSize(),currentComplex);
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
        //disable the remove to avoid errors, button gets enabled when a item in the list is selected
        removeButton.setEnabled(false);
        addButton.setForeground(Color.green);
        ActionListener addListener = actionEvent -> {
            //checks to see if a complex number is alread in the list of favourties before its added
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
        //
        ActionListener removeListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int index = favouriteNumbers.getSelectedIndex();
                favouriteNumbers.clearSelection();
                listModel.remove(index);
                removeButton.setEnabled(false);
            }
        };

        addButton.addActionListener(addListener);
        removeButton.addActionListener(removeListener);
        favouriteNumbers = new JList<>(listModel);
        favouriteNumbers.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!favouriteNumbers.isSelectionEmpty()) {
                    removeButton.setEnabled(true);
                    juliaFractal.changeConstant(favouriteNumbers.getSelectedValue());
                }
            }
        });
        favouriteNumbers.setCellRenderer(new ComplexCellRenderer());
        favouriteNumbers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        c.gridwidth = 2;
        c.gridy = 2;
        c.gridx = 0;
        add(favouriteNumbers, c);
        Dimension d = new Dimension(favouriteNumbers.getWidth(),favouriteNumbers.getHeight());
        setAllDimensions(d, favouriteNumbers);
        //TODO: export Julia and Export Mandelbrot buttons
        //TODO: save and load fractal buttons
        copyButton = new JButton("Copy Julia Constant");
        ActionListener copyListener = new ActionListener() {
            @Override
            //add the value of the julia constant to the clipboard
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(juliaFractal.getConstant().getFullStringValue()),null);
                JOptionPane.showMessageDialog(juliaFractal, "Copied to Clipboard");
            }
        };
        copyButton.addActionListener(copyListener);
        c.fill= GridBagConstraints.NONE;
        c.weighty=0;
        c.anchor=GridBagConstraints.CENTER;
        c.gridy=3;
        add(copyButton,c);
        saveJ = new JButton("Save Julia");
        saveM = new JButton("Save Mandelbrot");
        saveJ.addActionListener(new SaveButtonClick(juliaFractal));
        saveM.addActionListener(new SaveButtonClick(mandelFractal));
        c.gridwidth=1;
        c.gridy=4;
        add(saveM,c);
        c.gridx=1;
        add(saveJ,c);
        updateLabel();
    }
    //sets the maximum size, preferred size and minimum size to the dimensions specified, used to deal with some resizing issues that I was having
    public static void  setAllDimensions(Dimension d, Component c){
        c.setMaximumSize(d);
        c.setMinimumSize(d);
        c.setPreferredSize(d);
    }


    //updates the User selected point label to the one currently represented by the Julia fractal
    public void updateLabel() {
        currentComplex.setText(juliaFractal.getConstant().getStringValue());
    }

}