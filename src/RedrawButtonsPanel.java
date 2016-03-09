import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Created by Steffan on 04/03/2016.
 */
public class RedrawButtonsPanel extends JPanel {

    private JButton redrawButton, resetButton, addColourButton, removeColourButton, moveColourUp, moveColourDown;
    private JLabel xRange, yRange, iterationsLabel, loopLabel;
    private JTextField xMin, xMax, yMin, yMax, iterationsField, loopField;
    private JRadioButton mButton, jButton;
    private JList<Color> gradientColors;
    private DefaultListModel<Color> gradientModel;

    public RedrawButtonsPanel(){
        setBorder(BorderFactory.createLineBorder(Color.black));


    }

    public  void init(MandelFractal mF, JuliaFractal jF){
        mF.setRBP(this);
        jF.setRBP(this);
        mF.setSelected(true);
        /*
        TODO: Gradient customizer,
         JList, horizontal shows colored squares, squares can be dragged around, add button loads color selector
         which adds a color to the list,
         */
        redrawButton = new JButton("Redraw");
        resetButton = new JButton("Reset");
        loopLabel = new JLabel("Gradient Loops");
        loopField = new JTextField(Integer.toString(mF.getLoopCount()));
        xRange = new JLabel("X Range");
        xRange.setHorizontalAlignment(JLabel.RIGHT);
        yRange = new JLabel("Y Range");
        yRange.setHorizontalAlignment(JLabel.RIGHT);
        iterationsLabel = new JLabel("Iterations");
        addColourButton = new JButton("+");
        Font buttonFont = new Font("Arial", Font.BOLD, 12);
        addColourButton.setFont(buttonFont);
        removeColourButton = new JButton("-");
        removeColourButton.setFont(buttonFont);
        moveColourDown = new JButton("►");
        moveColourDown.setFont(buttonFont);
        moveColourUp = new JButton("◄");
        moveColourUp.setFont(buttonFont);
        iterationsLabel.setHorizontalAlignment(JLabel.RIGHT);
        xMin = new JTextField(Complex.toFourDP(mF.getxStart()));
        xMax = new JTextField(Complex.toFourDP(mF.getxEnd()));
        yMin = new JTextField(Complex.toFourDP(mF.getyStart()));
        yMax = new JTextField(Complex.toFourDP(mF.getyEnd()));
        ButtonGroup bGroup = new ButtonGroup();
        mButton = new JRadioButton("Mandelbrot Set");
        mButton.setSelected(true);
        jButton = new JRadioButton("Julia Set");
        bGroup.add(jButton);
        bGroup.add(mButton);
        gradientModel = new DefaultListModel<>();
        Color[] colors = mF.getColors();
        for (int i=0;i<colors.length;i++){
            gradientModel.add(i,colors[i]);
        }
        gradientColors = new JList<>(gradientModel);
        gradientColors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gradientColors.setVisibleRowCount(1);
        gradientColors.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        Dimension d = new Dimension(75 * 5, 16);
        gradientColors.setSize(d);
        gradientColors.setFixedCellWidth(75);
        FavouritesPanel.setAllDimensions(d, gradientColors);
        gradientColors.setCellRenderer(new ListCellRenderer<Color>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Color> list, Color value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel colourThing = new JLabel(" ");
                colourThing.setOpaque(true);
                colourThing.setBackground(value);
                list.setFixedCellWidth(list.getWidth() / list.getModel().getSize());
                if (isSelected){
                    colourThing.setBorder(BorderFactory.createLineBorder(Color.black));
                }
                else{
                    colourThing.setBorder(null);
                }
                return colourThing;
            }
        });
        setLayout(new GridBagLayout());
        GridBagConstraints gBC = new GridBagConstraints();
        iterationsField = new JTextField(Integer.toString(mF.getIterations()));
        ResetButtonAction resetAction = new ResetButtonAction(this, mF);
        RedrawButtonAction redrawAction = new RedrawButtonAction(this, mF);
        mButton.addItemListener(new FractalSelector(mF,redrawAction,resetAction));
        jButton.addItemListener(new FractalSelector(jF,redrawAction,resetAction));
        resetButton.addActionListener(resetAction);
        redrawButton.addActionListener(redrawAction);
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
        xMin.setText(Complex.toFourDP(xStart));
        xMax.setText(Complex.toFourDP(xEnd));
        yMin.setText(Complex.toFourDP(yStart));
        yMax.setText(Complex.toFourDP(yEnd));
        iterationsField.setText(Integer.toString(iterate));
    }

    public double getXMin() {
        return new Double(xMin.getText());
    }

    public double getYMin() {
        return new Double(yMin.getText());
    }

    public double getYMax() {
        return new Double(yMax.getText());
    }

    public double getXMax() {
        return new Double(xMax.getText());
    }

    public int getIterations() {
        return new Integer(iterationsField.getText());
    }

    public int getLoopCount() {
        return new Integer(loopField.getText());
    }

    public Color[] getColours() {
        Color[] tempColours = new Color[gradientModel.getSize()];
        for (int i = 0; i < gradientModel.getSize(); i++) {
            tempColours[i] = gradientModel.get(i);
        }
        return tempColours;
    }

    public void setColors(Color[] colors, int loopCount) {
        gradientModel.clear();
        for (Color c : colors) {
            gradientModel.addElement(c);
        }
        loopField.setText(Integer.toString(loopCount));
    }



}
