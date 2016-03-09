import javax.swing.*;
import java.awt.*;

/**
 * Created by Steffan on 04/03/2016.
 */
public class RedrawButtonsPanel extends JPanel {

    private JButton redrawButton, resetButton, addColourButton, removeColourButton;
    private JLabel xRange,yRange, iterationsLabel;
    private JTextField xMin,xMax,yMin,yMax,iterationsField;
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
        xRange = new JLabel("X Range");
        yRange = new JLabel("Y Range");
        iterationsLabel = new JLabel("Iterations");
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
        gradientColors.setDragEnabled(true);
        gradientColors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gradientColors.setDropMode(DropMode.INSERT);
        gradientColors.setCellRenderer(new ListCellRenderer<Color>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Color> list, Color value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel colourThing = new JLabel("    ");
                colourThing.setSize(50,50);
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
        setLayout(new GridBagLayout());
        GridBagConstraints gBC = new GridBagConstraints();
        iterationsField = new JTextField(Integer.toString(mF.getIterations()));
        ResetButtonAction resetAction = new ResetButtonAction(xMin,xMax,yMin,yMax,iterationsField,mF);
        RedrawButtonAction redrawAction = new RedrawButtonAction(xMin,xMax,yMin,yMax,iterationsField,mF);
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
        add(mButton,gBC);
        gBC.gridx=7;
        add(redrawButton,gBC);
        gBC.gridy=1;
        gBC.anchor =GridBagConstraints.PAGE_START;
        add(resetButton,gBC);
        gBC.gridx=1;
        gBC.ipadx=10;
        add(xMax,gBC);
        gBC.gridx=3;
        add(yMax,gBC);
        gBC.gridx=6;
        gBC.ipadx =0;
        add(jButton,gBC);
        gBC.gridx=0;
        gBC.gridy=2;
        gBC.gridwidth=GridBagConstraints.REMAINDER;
        add(gradientColors,gBC);
    }

    public void setValues(double xStart, double xEnd, double yStart, double yEnd, int iterate){
        xMin.setText(Complex.toFourDP(xStart));
        xMax.setText(Complex.toFourDP(xEnd));
        yMin.setText(Complex.toFourDP(yStart));
        yMax.setText(Complex.toFourDP(yEnd));
        iterationsField.setText(Integer.toString(iterate));
    }



}
