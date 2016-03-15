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
    private JButton saveM, saveJ, loadFractal, exportM, exportJ;
    private JButton removeButton;


    public FavouritesPanel(JuliaFractal juliaFractal) {
        this.juliaFractal = juliaFractal;
        juliaFractal.setFP(this);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void init(MandelbrotFractal mandelbrotFractal) {
        //initialises the favourites panel (the panel on the right of the screen)
        setLayout(new GridBagLayout());
        currentComplex = new JLabel("-0.0000 + 0.0000i");
        currentComplex.setToolTipText("The constant of the Julia Fractal");
        currentComplex.setHorizontalAlignment(JLabel.CENTER);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.0;
        c.gridwidth = 2;
        add(currentComplex, c);
        //fixes the size of currentComplex, ensuring that it doesn't unnecessarily resize the fractals
        setAllDimensions(currentComplex.getPreferredSize(), currentComplex);
        addButton = new JButton("+");
        addButton.setToolTipText("Add this constant to a favourites list");
        c.fill = GridBagConstraints.NONE;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        c.gridwidth = 1;
        c.weightx = 0.5;
        add(addButton, c);
        c.gridx = 1;
        c.anchor = GridBagConstraints.LINE_START;
        removeButton = new JButton("-");
        removeButton.setToolTipText("Remove this constant from the favourites list");
        add(removeButton, c);
        Font buttonFont = new Font("Arial", Font.BOLD, 15);
        listModel = new DefaultListModel<>();
        addButton.setFont(buttonFont);
        removeButton.setFont(buttonFont);
        removeButton.setForeground(Color.red);
        //disable the remove to avoid errors, button gets enabled when a item in the list is selected
        removeButton.setEnabled(false);
        addButton.setForeground(Color.green);
        ActionListener addListener = new ActionListener() {
            //checks to see if a complex number is already in the list of favourites before its added
            public void actionPerformed(ActionEvent actionEvent) {
                boolean inThere = false;
                for (int i = 0; i < listModel.getSize(); i++) {
                    if (listModel.get(i).equals(juliaFractal.getConstant())) {
                        inThere = true;
                    }
                }
                //if the number isn't already in there it gets added to the list
                if (!inThere) {
                    listModel.addElement(juliaFractal.getConstant());
                }
            }
        };
        //removes an element from the list, clears the selection of the list, and disables the remove button again
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
        favouriteNumbers.setToolTipText("A list of your favourite Julia constants");
        favouriteNumbers.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!favouriteNumbers.isSelectionEmpty()) {
                    //if a fractal is selected, enable the remove button and switch the Julia fractals constant to the selected number
                    removeButton.setEnabled(true);
                    juliaFractal.changeConstant(favouriteNumbers.getSelectedValue());
                }
            }
        });
        //sets favouriteNumbers up to display the Complex numbers correctly,
        favouriteNumbers.setCellRenderer(new ComplexCellRenderer());
        favouriteNumbers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        c.gridwidth = 2;
        c.gridy = 2;
        c.gridx = 0;
        add(new JScrollPane(favouriteNumbers), c);
        Dimension d = new Dimension(favouriteNumbers.getWidth(), favouriteNumbers.getHeight());
        setAllDimensions(d, favouriteNumbers);
        //TODO: export Julia and Export Mandelbrot buttons
        copyButton = new JButton("Copy Julia Constant");
        copyButton.setToolTipText("Copy the constant of the Julia fractal to the clipboard");
        ActionListener copyListener = new ActionListener() {
            @Override
            //add the value of the julia constant to the clipboard
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(juliaFractal.getConstant().getFullStringValue()), null);
                JOptionPane.showMessageDialog(juliaFractal, "Copied to Clipboard");
            }
        };
        copyButton.addActionListener(copyListener);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 3;
        add(copyButton, c);
        exportM = new JButton("Export Mandelbrot");
        exportM.setToolTipText("Export the Mandelbrot fractal to an image file");
        exportJ = new JButton("Export Julia");
        exportJ.setToolTipText("Export the Julia fractal to an image file");
        JFileChooser saveChooser = new JFileChooser();
        //there's a problem with the jpg export format, so only pngs and gifs
        saveChooser.setFileFilter(new ExtensionFileFilter("png", "image"));
        saveChooser.addChoosableFileFilter(new ExtensionFileFilter("gif", "image"));
        saveChooser.setAcceptAllFileFilterUsed(false);
        //allow both export buttons to use the same file chooser
        exportM.addActionListener(new ExportButtonClick(mandelbrotFractal, saveChooser));
        exportJ.addActionListener(new ExportButtonClick(juliaFractal, saveChooser));
        saveJ = new JButton("Save Julia");
        saveJ.setToolTipText("Save the Julia Fractal to an ftl file");
        saveM = new JButton("Save Mandelbrot");
        saveM.setToolTipText("Save the Mandelbrot Fractal to an ftl file");
        //allow the two save buttons and the load button to use the same file choosers
        saveChooser = new JFileChooser();
        saveChooser.setFileFilter(new ExtensionFileFilter(MandelbrotViewer.fileExtension, "file"));
        saveChooser.setAcceptAllFileFilterUsed(false);
        saveJ.addActionListener(new SaveButtonClick(juliaFractal, saveChooser));
        saveM.addActionListener(new SaveButtonClick(mandelbrotFractal, saveChooser));
        loadFractal = new JButton("Load a Fractal");
        loadFractal.addActionListener(new LoadButtonClick(mandelbrotFractal, juliaFractal, saveChooser));
        loadFractal.setToolTipText("Load a fractal from an ftl file");
        c.weightx = 0.5;
        c.gridy = 4;
        c.gridwidth = 1;
        add(exportM, c);
        c.gridx = 1;
        add(exportJ, c);
        c.gridx = 0;
        c.gridy = 5;
        add(saveM, c);
        c.gridx = 1;
        add(saveJ, c);
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 6;
        add(loadFractal, c);
        updateLabel();
    }

    //sets the maximum size, preferred size and minimum size to the dimensions specified, used to deal with some resizing issues that I was having
    public static void setAllDimensions(Dimension d, Component c) {
        c.setMaximumSize(d);
        c.setMinimumSize(d);
        c.setPreferredSize(d);
    }


    //updates the User selected point label to the one currently represented by the Julia fractal
    public void updateLabel() {
        currentComplex.setText(juliaFractal.getConstant().getStringValue());
    }

}