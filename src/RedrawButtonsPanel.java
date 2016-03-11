import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Arc2D;

/**
 * Created by Steffan on 04/03/2016.
 */
public class RedrawButtonsPanel extends JPanel {

    private JButton redrawButton, resetButton, addColourButton, removeColourButton, moveColourUp, moveColourDown;
    private JLabel xRange, yRange, iterationsLabel, loopLabel;
    private JSpinner xMin, xMax, yMin, yMax, iterationsField, loopField;
    private JRadioButton mButton, jButton;
    private JList<Color> gradientColors;
    private DefaultListModel<Color> gradientModel;

    public RedrawButtonsPanel(){
        setBorder(BorderFactory.createLineBorder(Color.black));


    }

    public  void init(MandelFractal mF, JuliaFractal jF){
        //allow the fractals to adjust the values of the the spinners in this panel
        mF.setRBP(this);
        jF.setRBP(this);
        //tell the mandelbrot fractal that it is selected
        mF.setSelected(true);
        redrawButton = new JButton("Redraw");
        redrawButton.setToolTipText("Redraw the fractal with the selected text");
        resetButton = new JButton("Reset");
        resetButton.setToolTipText("Reset the fractal to the default values");
        loopLabel = new JLabel("Gradient Loops");
        loopField = new JSpinner(new SpinnerNumberModel(mF.getLoopCount(), 1, 20, 1));
        loopField.setToolTipText("The number of times the gradient of the fractal loops in the image");
        xRange = new JLabel("X Range");
        xRange.setHorizontalAlignment(JLabel.RIGHT);
        yRange = new JLabel("Y Range");
        yRange.setHorizontalAlignment(JLabel.RIGHT);
        iterationsLabel = new JLabel("Iterations");
        addColourButton = new JButton("+");
        addColourButton.setToolTipText("Add a colour to the gradient");
        Font buttonFont = new Font("Arial", Font.BOLD, 12);
        addColourButton.setFont(buttonFont);
        removeColourButton = new JButton("-");
        removeColourButton.setToolTipText("Remove the selected colour from the gradient");
        removeColourButton.setFont(buttonFont);
        moveColourDown = new JButton("►");
        moveColourDown.setToolTipText("Change the selected colour's position in the gradient");
        moveColourDown.setFont(buttonFont);
        moveColourUp = new JButton("◄");
        moveColourUp.setToolTipText("Change the selected colour's position in the gradient");
        moveColourUp.setFont(buttonFont);
        iterationsLabel.setHorizontalAlignment(JLabel.RIGHT);
        //round these values to 4 decimal places, keeping the display format nice
        xMin = new JSpinner(new SpinnerNumberModel(mF.getxStart(), -3.0, 3.0, 0.0001));
        xMin.setEditor(new JSpinner.NumberEditor(xMin, "0.0000"));
        xMin.setToolTipText("The position of the left bound of the fractal");
        xMax = new JSpinner(new SpinnerNumberModel(mF.getxEnd(), -3.0, 3.0, 0.0001));
        xMax.setToolTipText("The position of the right bound of the fractal");
        xMax.setEditor(new JSpinner.NumberEditor(xMax, "0.0000"));
        yMin = new JSpinner(new SpinnerNumberModel(mF.getyStart(), -3.0, 3.0, 0.0001));
        yMin.setToolTipText("The position of the top the image");
        yMin.setEditor(new JSpinner.NumberEditor(yMin, "0.0000"));
        yMax = new JSpinner(new SpinnerNumberModel(mF.getyEnd(), -3.0, 3.0, 0.0001));
        yMax.setEditor(new JSpinner.NumberEditor(yMax, "0.0000"));
        yMax.setToolTipText("The position of the bottom of the image");
        ButtonGroup bGroup = new ButtonGroup();
        mButton = new JRadioButton("Mandelbrot Set");
        mButton.setSelected(true);
        mButton.setToolTipText("Select the Mandelbrot set for editing");
        jButton = new JRadioButton("Julia Set");
        jButton.setToolTipText("Select the Julia set for editing");
        bGroup.add(jButton);
        bGroup.add(mButton);
        gradientModel = new DefaultListModel<>();
        Color[] colors = mF.getColors();
        for (int i=0;i<colors.length;i++){
            gradientModel.add(i,colors[i]);
        }
        gradientColors = new JList<>(gradientModel);
        gradientColors.setToolTipText("The colours in the gradient in the fractal");
        gradientColors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gradientColors.setVisibleRowCount(1);
        gradientColors.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        //fixes the width of gradientColors, making it look nicer.
        Dimension d = new Dimension(100 * 5, 16);
        gradientColors.setSize(d);
        gradientColors.setFixedCellWidth(100);
        FavouritesPanel.setAllDimensions(d, gradientColors);
        gradientColors.setSelectedIndex(0);
        gradientColors.setCellRenderer(new ListCellRenderer<Color>() {
            @Override
            //set the background of the cell to the same value of the colour in the list, and give it a border if it has been selected
            public Component getListCellRendererComponent(JList<? extends Color> list, Color value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel colourThing = new JLabel(" ");
                colourThing.setOpaque(true);
                colourThing.setBackground(value);
                if (isSelected){
                    colourThing.setBorder(BorderFactory.createLineBorder(Color.black));
                }
                else{
                    colourThing.setBorder(null);
                }
                return colourThing;
            }
        });
        //resizes the width of the cells when elements are added and removed from the list
        gradientModel.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {
                gradientColors.setFixedCellWidth(gradientColors.getWidth() / gradientModel.getSize());
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
                //avoid throwing errors when the list is emptied switching between fractals
                if (gradientModel.getSize() > 0) {
                    gradientColors.setFixedCellWidth(gradientColors.getWidth() / gradientModel.getSize());
                }
            }

            @Override
            public void contentsChanged(ListDataEvent e) {

            }
        });
        setLayout(new GridBagLayout());
        GridBagConstraints gBC = new GridBagConstraints();
        iterationsField = new JSpinner(new SpinnerNumberModel(mF.getIterations(), 50, 1000, 1));
        iterationsField.setToolTipText("The number of times the formula will be iterated over before a colour is chosen");
        ResetButtonAction resetAction = new ResetButtonAction(this, mF);
        RedrawButtonAction redrawAction = new RedrawButtonAction(this, mF);
        mButton.addItemListener(new FractalSelector(mF,redrawAction,resetAction));
        jButton.addItemListener(new FractalSelector(jF,redrawAction,resetAction));
        resetButton.addActionListener(resetAction);
        redrawButton.addActionListener(redrawAction);
        moveColourUp.addActionListener(new GradientModifierAction(gradientColors, -1));
        moveColourDown.addActionListener(new GradientModifierAction(gradientColors, 1));
        addColourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //show a colour chooser then add the selected colour to the list, just in front of the currently selected one
                Color tempColor = JColorChooser.showDialog(addColourButton,"Add a new colour for the gradient",gradientColors.getSelectedValue());
                if(tempColor != null){
                    gradientModel.insertElementAt(tempColor,gradientColors.getSelectedIndex());
                }
            }
        });
        removeColourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //if the list only has one colour in it, do not allow removal of the colour from the list
                if(gradientModel.getSize()>1){
                    //remove the colour from the list then select the previous one
                    int index = gradientColors.getSelectedIndex();
                    gradientColors.clearSelection();
                    gradientModel.remove(index);
                    int newIndex = index-1;
                    newIndex = (newIndex + gradientModel.getSize()) % gradientModel.getSize();
                    gradientColors.setSelectedIndex(newIndex);
                }
                else{
                    JOptionPane.showMessageDialog(jF,"Cannot have an empty gradient");
                }
            }
        });
        //sets the constraints of everything and add it to the panel's layout then adds it to the panel, layout manager then puts it in the right place;
        // the layout is far from perfect
        gBC.anchor=GridBagConstraints.LINE_END;
        gBC.insets= new Insets(5,2,2,5);
        gBC.fill=GridBagConstraints.HORIZONTAL;
        gBC.gridheight=2;
        gBC.gridx=0;
        gBC.weightx = 0.5;
        gBC.gridy=0;
        add(xRange,gBC);
        gBC.gridx=2;
        add(yRange,gBC);
        gBC.gridx=4;
        gBC.ipadx=10;
        add(iterationsLabel,gBC);
        gBC.gridwidth = 1;
        gBC.ipadx=0;
        gBC.gridx=5;
        add(iterationsField,gBC);
        gBC.gridheight=1;
        gBC.anchor =GridBagConstraints.PAGE_END;
        gBC.gridx=1;
        gBC.ipadx=10;
        add(xMin,gBC);
        gBC.gridx=3;
        add(yMin,gBC);
        gBC.gridx=6;
        gBC.ipadx=0;
        gBC.gridheight = 2;
        add(mButton,gBC);
        gBC.gridy = 0;
        gBC.gridx=7;
        add(redrawButton,gBC);
        gBC.gridy = 2;
        gBC.anchor =GridBagConstraints.PAGE_START;
        add(resetButton,gBC);
        gBC.gridheight = 1;
        gBC.gridy = 1;
        gBC.gridx=1;
        gBC.ipadx=10;
        add(xMax,gBC);
        gBC.gridx=3;
        add(yMax,gBC);
        gBC.gridheight = 2;
        gBC.gridx=6;
        gBC.gridy = 2;
        gBC.ipadx = 0;
        add(jButton,gBC);
        gBC.gridheight = 1;
        gBC.gridx=0;
        gBC.gridy=2;
        gBC.anchor = GridBagConstraints.CENTER;
        gBC.fill = GridBagConstraints.NONE;
        gBC.gridwidth = 4;
        add(gradientColors,gBC);
        gBC.fill = GridBagConstraints.NONE;
        gBC.gridheight = 1;
        gBC.gridwidth = 1;
        gBC.gridy = 3;
        gBC.anchor = GridBagConstraints.LINE_END;
        add(moveColourUp, gBC);
        gBC.gridx = 1;
        add(addColourButton, gBC);
        gBC.anchor = GridBagConstraints.LINE_START;
        gBC.gridx = 2;
        add(removeColourButton, gBC);
        gBC.gridx = 3;
        add(moveColourDown, gBC);
        gBC.gridx = 4;
        gBC.gridheight = 2;
        gBC.gridy = 2;
        gBC.anchor = GridBagConstraints.LINE_END;
        add(loopLabel, gBC);
        gBC.gridx = 5;
        gBC.anchor = GridBagConstraints.LINE_START;
        gBC.fill = GridBagConstraints.HORIZONTAL;
        add(loopField, gBC);
    }

    //allows another class to set the values of the fields invoked when the fractals are being redrawn by resizing of the image or the mouse exploration
    public void setValues(double xStart, double xEnd, double yStart, double yEnd, int iterate){
        xMin.setValue(new Double(xStart));
        xMax.setValue(new Double(xEnd));
        yMin.setValue(new Double(yStart));
        yMax.setValue(new Double(yEnd));
        iterationsField.setValue(new Integer(iterate));
    }

    public double getXMin() {
        return (double) xMin.getValue();
    }

    public double getYMin() {
        return (double) yMin.getValue();
    }

    public double getYMax() {
        return (double) yMax.getValue();
    }

    public double getXMax() {
        return (double) xMax.getValue();
    }

    public int getIterations() {
        return (int) iterationsField.getValue();
    }

    public int getLoopCount() {
        return (int) loopField.getValue();
    }

    public Color[] getColours() {
        //Copies all the colours in gradientModel into an array
        Color[] tempColours = new Color[gradientModel.getSize()];
        for (int i = 0; i < gradientModel.getSize(); i++) {
            tempColours[i] = gradientModel.get(i);
        }
        return tempColours;
    }

    public void setColors(Color[] colors, int loopCount) {
        //sets all the colours in from the inputted array, into gradientModel, then sets the value of the loopCount
        gradientModel.clear();
        for (Color c : colors) {
            gradientModel.addElement(c);
        }
        loopField.setValue(new Integer(loopCount));
        gradientColors.setSelectedIndex(0);
    }



}
