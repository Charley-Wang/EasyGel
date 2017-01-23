package easygel.layer;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.awt.event.*;
import easygel.*;
import javax.swing.event.*;
import java.beans.*;
import java.util.*;
import easygel.layer.*;
import easygel.image.*;
import easygel.setting.*;
import java.io.*;
import java.util.*;

public class DialogControlCell extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private TitledBorder titledBorder1;
  private TitledBorder titledBorder2;
  private JTabbedPane jTabbedPane1 = new JTabbedPane();
  private JPanel jPanel1 = new JPanel();
  private JPanel jPanel2 = new JPanel();
  private JPanel jPanel3 = new JPanel();
  private JButton jButton1 = new JButton();
  private JTextField j05SensitivityText = new JTextField();
  private XYLayout xYLayout2 = new XYLayout();
  private JButton jButtonAutoReco = new JButton();
  private FrameMain frameMain;
  private JLabel jLabel1 = new JLabel();
  private TitledBorder titledBorder3;
  private JComboBox jComboBoxCellNo = new JComboBox();
  private XYLayout xYLayout3 = new XYLayout();
  private JLabel jLabel2 = new JLabel();
  private JButton jButtonEdgeColor = new JButton();
  private JCheckBox jCheckBoxEdgeIsVisible = new JCheckBox();
  private JCheckBox jCheckBoxDispNo = new JCheckBox();
  private JButton jButtonGegometryColor = new JButton();
  private JCheckBox jCheckBoxGeometryIsVisible = new JCheckBox();
  private JButton jButtonGravityColor = new JButton();
  private JCheckBox jCheckBoxGravityIsVisible = new JCheckBox();
  private XYLayout xYLayout4 = new XYLayout();
  private JButton jButton4 = new JButton();
  private JButton jButton5 = new JButton();
  private JButton jButton7 = new JButton();
  private JButton jButton10 = new JButton();
  private TitledBorder titledBorder4;
  private TitledBorder titledBorder5;
  // 是否是配合Layer1D
  private boolean isLayer1D;
  private JButton jButton3 = new JButton();
  private JButton jButton8 = new JButton();
  private boolean isFirst;
  private JButton jButton2 = new JButton();
  private JPanel jPanel4 = new JPanel();
  private XYLayout xYLayout5 = new XYLayout();
  private JPanel jPanel5 = new JPanel();
  private TitledBorder titledBorder6;
  private XYLayout xYLayout6 = new XYLayout();
  private JRadioButton jRadioButtonOpNone = new JRadioButton();
  private JRadioButton jRadioButtonOpSobel = new JRadioButton();
  private JRadioButton jRadioButtonOpPrewitt = new JRadioButton();

  private ButtonGroup buttonGroup=new ButtonGroup();
  private ButtonGroup buttonGroup2=new ButtonGroup();
  private JRadioButton jRadioButtonOpRobert = new JRadioButton();
  private JRadioButton jRadioButtonOpLap = new JRadioButton();
  private JRadioButton jRadioButtonOpGuassLap = new JRadioButton();
  private TitledBorder titledBorder7;
  private JPanel jPanel6 = new JPanel();
  private TitledBorder titledBorder8;
  private JRadioButton j05OtsuRadio = new JRadioButton();
  private XYLayout xYLayout7 = new XYLayout();
  private JRadioButton j05WataRadio = new JRadioButton();
  private JRadioButton j05WeszRadio = new JRadioButton();
  private JLabel jLabel4 = new JLabel();
  private JTextField j05WidthText = new JTextField();
  private JScrollBar jScrollWidthBar1 = new JScrollBar();

  // added by wxs 20030823 -02
  private Vector set=new Vector();
  private int selectIndex;
  private boolean isVisible;
  private boolean isInit;
  private boolean isReport;
  private JPanel jPanel7 = new JPanel();
  private XYLayout xYLayout8 = new XYLayout();
  private JLabel jLabel5 = new JLabel();
  private JTextField jTextHNum = new JTextField();
  private JLabel jLabel6 = new JLabel();
  private JTextField jTextVNum = new JTextField();
  private JScrollBar jScrollHNum = new JScrollBar();
  private JScrollBar jScrollVNum = new JScrollBar();
  private JLabel jLabel7 = new JLabel();
  private JTextField jTextRadius = new JTextField();
  private JScrollBar jScrollRadius = new JScrollBar();
  private JButton jButton12 = new JButton();
  private JButton jButton13 = new JButton();
  /** 是否是阵列分析 */
  private boolean isArray;
  private JPanel jPanel8 = new JPanel();
  private TitledBorder titledBorder9;
  private TitledBorder titledBorder10;
  private XYLayout xYLayout9 = new XYLayout();
  private JLabel jLabel8 = new JLabel();
  private JTextField jTextW = new JTextField();
  private JScrollBar jScrollW = new JScrollBar();
  private JLabel jLabel9 = new JLabel();
  private JTextField jTextH = new JTextField();
  private JScrollBar jScrollH = new JScrollBar();
  private TitledBorder titledBorder11;
  private TitledBorder titledBorder12;
  private JLabel jLabel10 = new JLabel();
  private JLabel jLabel11 = new JLabel();
  private JScrollBar jScrollLocX = new JScrollBar();
  private JScrollBar jScrollLocY = new JScrollBar();
  private JCheckBox jCheckBox1 = new JCheckBox();
  private JTextField jTextX = new JTextField();
  private JTextField jTextY = new JTextField();
  private JRadioButton jRadioAddSimple = new JRadioButton();
  private JRadioButton jRadioAddReco = new JRadioButton();
  private ButtonGroup buttonGroup1 = new ButtonGroup();
  private ButtonGroup buttonGroup3 = new ButtonGroup();
  private JButton jButton11 = new JButton();
  private JButton jButton14 = new JButton();
  private JButton jButton15 = new JButton();
  private TitledBorder titledBorder13;
  private TitledBorder titledBorder14;
  private JPanel jPanel11 = new JPanel();
  private TitledBorder titledBorder15;
  private XYLayout xYLayout11 = new XYLayout();
  private JLabel jLabel12 = new JLabel();
  private JLabel jLabel13 = new JLabel();
  private JLabel jLabel14 = new JLabel();
  private JLabel jLabel15 = new JLabel();
  private JLabel jLabel16 = new JLabel();
  private JLabel jLabel17 = new JLabel();
  private JCheckBox jCheckAreaMin = new JCheckBox();
  private JCheckBox jCheckGrayMin = new JCheckBox();
  private JTextField jTextAreaMin = new JTextField();
  private JTextField jTextGrayMin = new JTextField();
  private JScrollBar jScrollAreaMin = new JScrollBar();
  private JScrollBar jScrollGrayMin = new JScrollBar();
  private JCheckBox jCheckAreaMax = new JCheckBox();
  private JCheckBox jCheckGrayMax = new JCheckBox();
  private JCheckBox jCheckShape = new JCheckBox();
  private JCheckBox jCheckDist = new JCheckBox();
  private JTextField jTextAreaMax = new JTextField();
  private JTextField jTextGrayMax = new JTextField();
  private JTextField jTextShape = new JTextField();
  private JTextField jTextDist = new JTextField();
  private JScrollBar jScrollAreaMax = new JScrollBar();
  private JScrollBar jScrollGrayMax = new JScrollBar();
  private JScrollBar jScrollShape = new JScrollBar();
  private JScrollBar jScrollDist = new JScrollBar();
  private JRadioButton jRadioModeDelete = new JRadioButton();
  private JRadioButton jRadioModeDetail = new JRadioButton();
  private JRadioButton jRadioModeDetailOne = new JRadioButton();
  private JRadioButton jRadioModeDetailMulti = new JRadioButton();
  private JButton jButton16 = new JButton();
  private ButtonGroup buttonGroup4 = new ButtonGroup();
  private ButtonGroup buttonGroup5 = new ButtonGroup();
  private JButton jButton17 = new JButton();
  private JCheckBox jCheckDispObj = new JCheckBox();
  private TitledBorder titledBorder16;
  private TitledBorder titledBorder17;
  private TitledBorder titledBorder18;
  private TitledBorder titledBorder19;
  private TitledBorder titledBorder20;
  private JButton jButton18 = new JButton();

  private boolean isAutoColor;
  private boolean isDispAllNo;
  private JButton jButton19 = new JButton();
  private JButton jButton20 = new JButton();
  private JLabel jLabel3 = new JLabel();
  private JComboBox jComboBoxCellNo22 = new JComboBox();
  private JButton jButton21 = new JButton();
  private JButton jButton22 = new JButton();
  private JButton jButtonGuass = new JButton();
  private JPanel jPanel12 = new JPanel();
  private TitledBorder titledBorder21;
  private JLabel jLabel18 = new JLabel();
  private JPanel jPanel9 = new JPanel();
  private TitledBorder titledBorder22;
  private JScrollBar jSliderSensitivity = new JScrollBar();
  private TitledBorder titledBorder23;
  private JPanel jPanel13 = new JPanel();
  private TitledBorder titledBorder24;
  private JPanel jPanel14 = new JPanel();
  private TitledBorder titledBorder25;
  private TitledBorder titledBorder26;
  private TitledBorder titledBorder27;

  public DialogControlCell(FrameMain frame, String title, boolean modal,
                           boolean isLayer1D,boolean isFirst,boolean isArray){
    super(frame, title, modal);
    frameMain=frame;
    this.isLayer1D =isLayer1D;
    this.isFirst =isFirst;
    this.isReport=false;
    this.isArray=isArray;
    this.isAutoColor=false;
    this.isDispAllNo=false;
    // added by wxs 20030823 -03
    this.isInit=true;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogControlCell() {
    this(null, "", false,false,false,false);
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    titledBorder4 = new TitledBorder("");
    titledBorder5 = new TitledBorder("");
    titledBorder6 = new TitledBorder("");
    titledBorder7 = new TitledBorder("");
    titledBorder8 = new TitledBorder("");
    titledBorder9 = new TitledBorder("");
    titledBorder10 = new TitledBorder("");
    titledBorder11 = new TitledBorder("");
    titledBorder12 = new TitledBorder("");
    titledBorder13 = new TitledBorder("");
    titledBorder14 = new TitledBorder("");
    titledBorder15 = new TitledBorder("");
    titledBorder16 = new TitledBorder("");
    titledBorder17 = new TitledBorder("");
    titledBorder18 = new TitledBorder("");
    titledBorder19 = new TitledBorder("");
    titledBorder20 = new TitledBorder("");
    titledBorder21 = new TitledBorder("");
    titledBorder22 = new TitledBorder("");
    titledBorder23 = new TitledBorder("");
    titledBorder24 = new TitledBorder("");
    titledBorder25 = new TitledBorder("");
    titledBorder26 = new TitledBorder("");
    titledBorder27 = new TitledBorder("");
    panel1.setLayout(xYLayout1);
    jButton1.setMargin(new Insets(0, 0, 0, 0));
    jButton1.setText("OK");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    j05SensitivityText.setToolTipText("Input 0~2");
    //j05SensitivityText.setEditable(false);
    j05SensitivityText.setText("1.0");
    jPanel2.setLayout(xYLayout2);
    jButtonAutoReco.setForeground(Color.blue);
    jButtonAutoReco.setText("Automatic Detection");
    jButtonAutoReco.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonAutoReco_actionPerformed(e);
      }
    });
    jLabel1.setText("Sensitivity");
    jPanel3.setLayout(xYLayout3);
    jLabel2.setText("Select Object No");
    jButtonEdgeColor.setMargin(new Insets(0, 0, 0, 0));
    jButtonEdgeColor.setText("Boundary Color");
    jButtonEdgeColor.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonEdgeColor_actionPerformed(e);
      }
    });
    jCheckBoxEdgeIsVisible.setMargin(new Insets(0, 0, 0, 0));
    jCheckBoxEdgeIsVisible.setSelected(true);
    jCheckBoxEdgeIsVisible.setText("Boundary Visible");
    jCheckBoxEdgeIsVisible.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxEdgeIsVisible_actionPerformed(e);
      }
    });
    jCheckBoxDispNo.setMargin(new Insets(0, 0, 0, 0));
    jCheckBoxDispNo.setText("Show No");
    jCheckBoxDispNo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxDispNo_actionPerformed(e);
      }
    });
    jButtonGegometryColor.setMargin(new Insets(0, 0, 0, 0));
    jButtonGegometryColor.setText("Center Color");
    jButtonGegometryColor.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonGegometryColor_actionPerformed(e);
      }
    });
    jCheckBoxGeometryIsVisible.setMargin(new Insets(0, 0, 0, 0));
    jCheckBoxGeometryIsVisible.setText("Center");
    jCheckBoxGeometryIsVisible.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxGeometryIsVisible_actionPerformed(e);
      }
    });
    jButtonGravityColor.setMargin(new Insets(0, 0, 0, 0));
    jButtonGravityColor.setText("Gray Center Color");
    jButtonGravityColor.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonGravityColor_actionPerformed(e);
      }
    });
    jCheckBoxGravityIsVisible.setMargin(new Insets(0, 0, 0, 0));
    jCheckBoxGravityIsVisible.setText("Gray Center");
    jCheckBoxGravityIsVisible.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxGravityIsVisible_actionPerformed(e);
      }
    });
    jComboBoxCellNo.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jComboBoxCellNo_itemStateChanged(e);
      }
    });
    jPanel1.setLayout(xYLayout4);
    jButton4.setMargin(new Insets(0, 0, 0, 0));
    jButton4.setText("Square");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton4_actionPerformed(e);
      }
    });
    jButton5.setForeground(Color.magenta);
    jButton5.setMargin(new Insets(0, 0, 0, 0));
    jButton5.setText("Create Object");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton5_actionPerformed(e);
      }
    });
    jButton7.setForeground(Color.blue);
    jButton7.setMargin(new Insets(0, 0, 0, 0));
    jButton7.setText("Application");
    jButton7.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton7_actionPerformed(e);
      }
    });
    jButton10.setMargin(new Insets(0, 0, 0, 0));
    jButton10.setText("Circle");
    jButton10.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton10_actionPerformed(e);
      }
    });
    jButton3.setText("Report");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jButton8.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton8_actionPerformed(e);
      }
    });
    jButton8.setText("Cancel");
    jButton8.setMargin(new Insets(0, 0, 0, 0));
    jButton8.setPreferredSize(new Dimension(43, 29));
    jButton8.setMinimumSize(new Dimension(43, 29));
    jButton8.setMaximumSize(new Dimension(43, 29));
    panel1.setMinimumSize(new Dimension(442, 265));
    panel1.setPreferredSize(new Dimension(442, 265));
    jButton2.setForeground(Color.blue);
    jButton2.setMargin(new Insets(0, 0, 0, 0));
    jButton2.setText("Automatic Color");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jPanel4.setLayout(xYLayout5);
    jPanel5.setBorder(titledBorder6);
    jPanel5.setLayout(xYLayout6);
    jRadioButtonOpNone.setSelected(true);
    jRadioButtonOpNone.setText("None");
    jRadioButtonOpSobel.setText("Sobel");
    jRadioButtonOpPrewitt.setText("Prewitt");
    jRadioButtonOpRobert.setText("Robert");
    jRadioButtonOpLap.setText("Laplacian");
    jRadioButtonOpGuassLap.setText("GuassLaplacian");
    jPanel6.setBorder(titledBorder8);
    jPanel6.setLayout(xYLayout7);
    j05OtsuRadio.setSelected(true);
    j05OtsuRadio.setText("Otsu");
    j05WeszRadio.setText("Wesz");
    jLabel4.setText("Band Width");
    //j05WidthText.setEditable(false);
    j05WidthText.setText("20");
    jScrollWidthBar1.setMaximum(80);
    jScrollWidthBar1.setMinimum(2);
    jScrollWidthBar1.setOrientation(JScrollBar.HORIZONTAL);
    jScrollWidthBar1.setValue(20);
    jScrollWidthBar1.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollWidthBar1_adjustmentValueChanged(e);
      }
    });
    jPanel7.setLayout(xYLayout8);
    jLabel5.setText("Horizontal Number");
    jTextHNum.setText("12");
    jTextHNum.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextHNum_focusLost(e);
      }
    });
    jLabel6.setText("Vertical Number");
    jTextVNum.setText("8");
    jTextVNum.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextVNum_focusLost(e);
      }
    });
    jLabel7.setText("R");
    jButton12.setMinimumSize(new Dimension(83, 29));
    jButton12.setPreferredSize(new Dimension(83, 29));
    jButton12.setText("Report");
    jButton12.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton12_actionPerformed(e);
      }
    });
    jButton13.setForeground(Color.blue);
    jButton13.setText("Dectioin");
    jButton13.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton13_actionPerformed(e);
      }
    });
    jScrollHNum.setMinimum(1);
    jScrollHNum.setOrientation(JScrollBar.HORIZONTAL);
    jScrollHNum.setValue(12);
    jScrollHNum.setVisibleAmount(0);
    jScrollHNum.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollHNum_adjustmentValueChanged(e);
      }
    });
    jScrollVNum.setMinimum(1);
    jScrollVNum.setOrientation(JScrollBar.HORIZONTAL);
    jScrollVNum.setValue(8);
    jScrollVNum.setVisibleAmount(0);
    jScrollVNum.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollVNum_adjustmentValueChanged(e);
      }
    });
    jScrollRadius.setMaximum(500);
    jScrollRadius.setMinimum(1);
    jScrollRadius.setOrientation(JScrollBar.HORIZONTAL);
    jScrollRadius.setValue(10);
    jScrollRadius.setVisibleAmount(0);
    jScrollRadius.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollRadius_adjustmentValueChanged(e);
      }
    });
    jTextRadius.setText("10");
    jTextRadius.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextRadius_focusLost(e);
      }
    });
    jPanel8.setBorder(titledBorder9);
    jPanel8.setLayout(xYLayout9);
    jLabel8.setText("Horizontal Distance");
    jTextW.setText("0");
    jTextW.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextW_focusLost(e);
      }
    });
    jLabel9.setText("Vertical Distance");
    jTextH.setText("0");
    jTextH.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextH_focusLost(e);
      }
    });
    jScrollW.setMaximum(200);
    jScrollW.setMinimum(-200);
    jScrollW.setOrientation(JScrollBar.HORIZONTAL);
    jScrollW.setVisibleAmount(0);
    jScrollW.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollW_adjustmentValueChanged(e);
      }
    });
    jScrollH.setMaximum(200);
    jScrollH.setMinimum(-200);
    jScrollH.setOrientation(JScrollBar.HORIZONTAL);
    jScrollH.setVisibleAmount(0);
    jScrollH.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollH_adjustmentValueChanged(e);
      }
    });
    jLabel10.setText("X：");
    jLabel11.setText("Y：");
    jScrollLocX.setMaximum(500);
    jScrollLocX.setMinimum(-500);
    jScrollLocX.setOrientation(JScrollBar.HORIZONTAL);
    jScrollLocX.setVisibleAmount(0);
    jScrollLocX.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollLocX_adjustmentValueChanged(e);
      }
    });
    jScrollLocY.setMaximum(500);
    jScrollLocY.setMinimum(-500);
    jScrollLocY.setOrientation(JScrollBar.HORIZONTAL);
    jScrollLocY.setVisibleAmount(0);
    jScrollLocY.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollLocY_adjustmentValueChanged(e);
      }
    });
    jCheckBox1.setForeground(Color.magenta);
    jCheckBox1.setText("Adjust Mode");
    jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBox1_actionPerformed(e);
      }
    });
    jTextX.setText("0");
    jTextX.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextX_focusLost(e);
      }
    });
    jTextY.setText("0");
    jTextY.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextY_focusLost(e);
      }
    });
    jRadioAddSimple.setToolTipText("Border as objects");
    jRadioAddSimple.setSelected(true);
    jRadioAddSimple.setText("Simple Mode");
    jRadioAddReco.setToolTipText("Automatically detect objects");
    jRadioAddReco.setText("Detection Mode");
    jButton11.setMargin(new Insets(0, 0, 0, 0));
    jButton11.setText("Oval");
    jButton11.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton11_actionPerformed(e);
      }
    });
    jButton14.setMargin(new Insets(0, 0, 0, 0));
    jButton14.setText("Polygon");
    jButton14.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton14_actionPerformed(e);
      }
    });
    jButton15.setMargin(new Insets(0, 0, 0, 0));
    jButton15.setText("Delete Image");
    jButton15.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton15_actionPerformed(e);
      }
    });
    jPanel11.setBorder(titledBorder15);
    jPanel11.setLayout(xYLayout11);
    jLabel12.setText("Min");
    jLabel13.setText("Max");
    jLabel14.setText("Area");
    jLabel15.setText("Gray Integral");
    jLabel16.setToolTipText("4 pi area / Perimeter");
    jLabel16.setText("Shape Factor");
    jLabel17.setToolTipText("Distance geometry center and gray center");
    jLabel17.setText("Distance");
    jScrollAreaMin.setMaximum(1000);
    jScrollAreaMin.setMinimum(1);
    jScrollAreaMin.setOrientation(JScrollBar.HORIZONTAL);
    jScrollAreaMin.setValue(5);
    jScrollAreaMin.setVisibleAmount(0);
    jScrollAreaMin.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollAreaMin_adjustmentValueChanged(e);
      }
    });
    jScrollGrayMin.setMaximum(50000);
    jScrollGrayMin.setMinimum(1);
    jScrollGrayMin.setOrientation(JScrollBar.HORIZONTAL);
    jScrollGrayMin.setVisibleAmount(0);
    jScrollGrayMin.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollGrayMin_adjustmentValueChanged(e);
      }
    });
    jCheckAreaMax.setSelected(true);
    jCheckAreaMax.setText("jCheckBox4");
    jCheckAreaMax.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckAreaMax_actionPerformed(e);
      }
    });
    jCheckGrayMax.setText("jCheckBox5");
    jCheckGrayMax.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckGrayMax_actionPerformed(e);
      }
    });
    jCheckShape.setText("jCheckBox6");
    jCheckShape.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckShape_actionPerformed(e);
      }
    });
    jCheckDist.setText("jCheckBox7");
    jCheckDist.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckDist_actionPerformed(e);
      }
    });
    jScrollAreaMax.setMaximum(2000);
    jScrollAreaMax.setMinimum(1);
    jScrollAreaMax.setOrientation(JScrollBar.HORIZONTAL);
    jScrollAreaMax.setValue(50);
    jScrollAreaMax.setVisibleAmount(0);
    jScrollAreaMax.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollAreaMax_adjustmentValueChanged(e);
      }
    });
    jScrollGrayMax.setMaximum(90000);
    jScrollGrayMax.setMinimum(1);
    jScrollGrayMax.setOrientation(JScrollBar.HORIZONTAL);
    jScrollGrayMax.setValue(90000);
    jScrollGrayMax.setVisibleAmount(0);
    jScrollGrayMax.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollGrayMax_adjustmentValueChanged(e);
      }
    });
    jScrollShape.setMaximum(500);
    jScrollShape.setMinimum(1);
    jScrollShape.setOrientation(JScrollBar.HORIZONTAL);
    jScrollShape.setValue(7);
    jScrollShape.setVisibleAmount(0);
    jScrollShape.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollShape_adjustmentValueChanged(e);
      }
    });
    jScrollDist.setMaximum(500);
    jScrollDist.setOrientation(JScrollBar.HORIZONTAL);
    jScrollDist.setValue(10);
    jScrollDist.setVisibleAmount(0);
    jScrollDist.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollDist_adjustmentValueChanged(e);
      }
    });
    jRadioModeDelete.setForeground(new Color(0, 79, 0));
    jRadioModeDelete.setToolTipText("Delete objects outside");
    jRadioModeDelete.setText("Delete Mode");
    jRadioModeDetail.setForeground(new Color(0, 79, 0));
    jRadioModeDetail.setToolTipText("Detection again");
    jRadioModeDetail.setSelected(true);
    jRadioModeDetail.setText("Divide Mode");
    jRadioModeDetailOne.setForeground(Color.blue);
    jRadioModeDetailOne.setToolTipText("Detection once");
    jRadioModeDetailOne.setSelected(true);
    jRadioModeDetailOne.setText("Divide Once");
    jRadioModeDetailMulti.setForeground(Color.blue);
    jRadioModeDetailMulti.setToolTipText("Unlimited Divided");
    jRadioModeDetailMulti.setText("Unlimited Divided");
    jButton16.setForeground(Color.blue);
    jButton16.setToolTipText("Divided from parameters");
    jButton16.setMargin(new Insets(0, 0, 0, 0));
    jButton16.setText("Limit objects");
    jButton16.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton16_actionPerformed(e);
      }
    });
    jButton17.setForeground(Color.blue);
    jButton17.setMargin(new Insets(0, 0, 0, 0));
    jButton17.setText("Show all numbers");
    jButton17.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton17_actionPerformed(e);
      }
    });
    jCheckDispObj.setForeground(Color.magenta);
    jCheckDispObj.setMargin(new Insets(0, 0, 0, 0));
    jCheckDispObj.setText("Highlight Objects");
    jCheckDispObj.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckDispObj_actionPerformed(e);
      }
    });
    jButton18.setForeground(Color.magenta);
    jButton18.setToolTipText("Automatic Detection");
    jButton18.setMargin(new Insets(0, 0, 0, 0));
    jButton18.setText("Divided Objects");
    jButton18.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton18_actionPerformed(e);
      }
    });
    jTextAreaMin.setText("5");
    jTextAreaMin.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextAreaMin_focusLost(e);
      }
    });
    jTextAreaMax.setText("50");
    jTextAreaMax.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextAreaMax_focusLost(e);
      }
    });
    jTextGrayMin.setText("1");
    jTextGrayMin.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextGrayMin_focusLost(e);
      }
    });
    jTextGrayMax.setText("90000");
    jTextGrayMax.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextGrayMax_focusLost(e);
      }
    });
    jTextShape.setText("7");
    jTextShape.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextShape_focusLost(e);
      }
    });
    jTextDist.setText("10");
    jTextDist.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextDist_focusLost(e);
      }
    });
    jCheckAreaMin.setSelected(true);
    jCheckAreaMin.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckAreaMin_actionPerformed(e);
      }
    });
    jButton19.setText("Report");
    jButton19.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton19_actionPerformed(e);
      }
    });
    jButton20.setText("Report");
    jButton20.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton20_actionPerformed(e);
      }
    });
    jLabel3.setText("Object No");
    jButton21.setToolTipText("Delete selected objects");
    jButton21.setMargin(new Insets(0, 0, 0, 0));
    jButton21.setMnemonic('0');
    jButton21.setText("Delete selected objects");
    jButton21.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton21_actionPerformed(e);
      }
    });
    jButton22.setForeground(Color.magenta);
    jButton22.setToolTipText("Delete objects");
    jButton22.setMargin(new Insets(0, 0, 0, 0));
    jButton22.setMnemonic('0');
    jButton22.setText("Delete objects with mouse");
    jButton22.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton22_actionPerformed(e);
      }
    });
    jButtonGuass.setForeground(Color.magenta);
    jButtonGuass.setMargin(new Insets(0, 0, 0, 0));
    jButtonGuass.setText("Gauss Fitting");
    jButtonGuass.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonGuass_actionPerformed(e);
      }
    });
    jPanel12.setBorder(titledBorder21);
    jLabel18.setForeground(Color.magenta);
    jLabel18.setText("Basic Parameters");
    jPanel9.setBorder(titledBorder22);
    j05WataRadio.setText("Wata");
    jSliderSensitivity.setOrientation(JScrollBar.HORIZONTAL);
    jSliderSensitivity.setVisibleAmount(0);
    jSliderSensitivity.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jSliderSensitivity_adjustmentValueChanged(e);
      }
    });
    jPanel13.setBorder(titledBorder24);
    jPanel14.setBorder(titledBorder25);
    jCheckGrayMin.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckGrayMin_actionPerformed(e);
      }
    });
    jPanel3.add(jCheckBoxDispNo, new XYConstraints(59, 83, -1, 15));
    jPanel3.add(jCheckBoxEdgeIsVisible, new XYConstraints(59, 48, -1, 15));
    jPanel3.add(jCheckDispObj, new XYConstraints(59, 66, -1, 15));
    jPanel3.add(jCheckBoxGravityIsVisible, new XYConstraints(59, 101, -1, 15));
    jPanel3.add(jCheckBoxGeometryIsVisible, new XYConstraints(59, 118, -1, 15));
    jPanel3.add(jButtonGravityColor,  new XYConstraints(150, 79, 81, 23));
    jPanel3.add(jButtonGegometryColor, new XYConstraints(150, 109, 81, 23));
    jPanel3.add(jButtonEdgeColor, new XYConstraints(150, 48, 81, 23));
    jPanel3.add(jButton2,  new XYConstraints(253, 79, 81, 23));
    jPanel3.add(jButton17, new XYConstraints(253, 109, 81, 23));
    jPanel3.add(jButton7, new XYConstraints(253, 48, 81, 23));
    jPanel3.add(jPanel12,   new XYConstraints(31, 36, 335, 110));
    jPanel3.add(jLabel2,  new XYConstraints(80, 12, 81, 19));
    jPanel3.add(jComboBoxCellNo,  new XYConstraints(167, 11, 134, 19));
    jPanel1.add(jButton15,  new XYConstraints(57, 96, 65, 22));
    jPanel1.add(jButton5, new XYConstraints(136, 96, 65, 22));
    jPanel1.add(jComboBoxCellNo22, new XYConstraints(246, 37, 89, 20));
    jPanel1.add(jLabel3, new XYConstraints(263, 14, -1, -1));
    jPanel1.add(jButton21, new XYConstraints(246, 67, 89, 22));
    jPanel1.add(jButton22, new XYConstraints(246, 99, 89, 22));
    jPanel1.add(jButton19, new XYConstraints(94, 136, 76, 23));
    jPanel1.add(jButtonGuass, new XYConstraints(234, 136, 76, 23));
    jPanel1.add(jRadioAddSimple, new XYConstraints(59, 14, 70, 21));
    jPanel1.add(jRadioAddReco, new XYConstraints(129, 14, 72, 21));
    jPanel1.add(jButton4, new XYConstraints(57, 39, 65, 22));
    jPanel1.add(jButton10, new XYConstraints(136, 39, 65, 22));
    jPanel1.add(jButton11, new XYConstraints(57, 68, 65, 22));
    jPanel1.add(jButton14, new XYConstraints(136, 68, 65, 22));
    jPanel1.add(jPanel13,  new XYConstraints(37, 10, 186, 119));
    jPanel1.add(jPanel14,   new XYConstraints(228, 11, 131, 118));
    jPanel4.add(jPanel11, new XYConstraints(11, 3, 371, 121));
    jPanel11.add(jLabel15,         new XYConstraints(6, 41, 53, 19));
    jPanel11.add(jCheckGrayMin,     new XYConstraints(61, 43, 13, 14));
    jPanel11.add(jScrollGrayMin,    new XYConstraints(118, 43, 38, 15));
    jPanel11.add(jTextGrayMax,        new XYConstraints(189, 42, 39, 16));
    jPanel11.add(jCheckGrayMax,       new XYConstraints(173, 42, 13, 14));
    jPanel11.add(jTextGrayMin,   new XYConstraints(77, 43, 39, 16));
    jPanel11.add(jTextAreaMax,  new XYConstraints(189, 20, 39, 16));
    jPanel11.add(jScrollAreaMax, new XYConstraints(230, 20, 38, 15));
    jPanel11.add(jLabel14, new XYConstraints(6, 18, 53, 19));
    jPanel11.add(jCheckAreaMin, new XYConstraints(61, 20, 13, 14));
    jPanel11.add(jTextAreaMin, new XYConstraints(77, 20, 39, 16));
    jPanel11.add(jScrollAreaMin, new XYConstraints(118, 20, 38, 15));
    jPanel11.add(jCheckAreaMax, new XYConstraints(173, 20, 13, 14));
    jPanel11.add(jScrollDist, new XYConstraints(173, 89, 97, 15));
    jPanel11.add(jLabel16, new XYConstraints(6, 64, 53, 19));
    jPanel11.add(jCheckShape, new XYConstraints(61, 66, 13, 14));
    jPanel11.add(jTextShape, new XYConstraints(77, 66, 78, 16));
    jPanel11.add(jScrollShape, new XYConstraints(173, 66, 97, 15));
    jPanel11.add(jLabel17, new XYConstraints(6, 87, 53, 19));
    jPanel11.add(jCheckDist, new XYConstraints(61, 89, 13, 14));
    jPanel11.add(jTextDist, new XYConstraints(77, 89, 78, 16));
    jPanel11.add(jLabel12,  new XYConstraints(80, 0, 38, 14));
    jPanel11.add(jLabel13, new XYConstraints(192, 0, 38, 14));
    jPanel11.add(jScrollGrayMax, new XYConstraints(230, 42, 38, 15));
    jPanel11.add(jRadioModeDetailOne,  new XYConstraints(283, 64, 68, 15));
    jPanel11.add(jRadioModeDetailMulti, new XYConstraints(283, 85, 68, 15));
    jPanel11.add(jRadioModeDelete, new XYConstraints(283, 22, 68, 15));
    jPanel11.add(jRadioModeDetail, new XYConstraints(283, 43, 68, 15));
    jPanel4.add(jButton20,  new XYConstraints(56, 130, 76, 26));
    jPanel4.add(jButton16, new XYConstraints(157, 130, 76, 26));
    jPanel4.add(jButton18, new XYConstraints(257, 130, 76, 26));
    buttonGroup.add(jRadioButtonOpNone);
    buttonGroup.add(jRadioButtonOpSobel);
    buttonGroup.add(jRadioButtonOpPrewitt);
    buttonGroup.add(jRadioButtonOpRobert);
    buttonGroup.add(jRadioButtonOpLap);
    buttonGroup.add(jRadioButtonOpGuassLap);
    jPanel5.add(jRadioButtonOpLap,  new XYConstraints(115, 22, 87, 13));
    jPanel5.add(jRadioButtonOpGuassLap, new XYConstraints(115, 40, 111, 19));
    jPanel5.add(jRadioButtonOpRobert, new XYConstraints(115, 2, 77, 15));
    jPanel5.add(jRadioButtonOpNone,  new XYConstraints(13, 1, 86, 17));
    jPanel5.add(jRadioButtonOpSobel, new XYConstraints(13, 20, 89, 17));
    jPanel5.add(jRadioButtonOpPrewitt, new XYConstraints(13, 41, 66, 17));
    jPanel2.add(jSliderSensitivity,  new XYConstraints(127, 21, 59, 17));
    jPanel2.add(jLabel1, new XYConstraints(42, 21, 53, -1));
    jPanel2.add(j05SensitivityText, new XYConstraints(82, 21, 40, 18));
    jPanel2.add(jScrollWidthBar1,  new XYConstraints(308, 22, 59, 17));
    jPanel2.add(jLabel4, new XYConstraints(215, 21, 53, -1));
    jPanel2.add(j05WidthText, new XYConstraints(265, 21, 40, 18));
    jPanel2.add(jButtonAutoReco,       new XYConstraints(61, 130, 95, 24));
    jPanel2.add(jButton3,      new XYConstraints(237, 130, 95, 24));
    jPanel6.add(j05OtsuRadio,  new XYConstraints(9, 2, 53, 16));
    jPanel6.add(j05WataRadio, new XYConstraints(9, 21, 64, 16));
    jPanel6.add(j05WeszRadio, new XYConstraints(9, 40, 64, 16));
    jPanel2.add(jPanel6,           new XYConstraints(27, 50, 83, 70));
    jPanel2.add(jPanel5,       new XYConstraints(124, 50, 256, -1));
    buttonGroup2.add(j05OtsuRadio);
    buttonGroup2.add(j05WataRadio);
    buttonGroup2.add(j05WeszRadio);
    jPanel7.add(jPanel8,         new XYConstraints(172, 7, 201, 116));
    jPanel8.add(jCheckBox1,     new XYConstraints(59, 0, 87, 18));
    jPanel8.add(jLabel11,  new XYConstraints(5, 43, 21, 17));
    jPanel8.add(jLabel8,  new XYConstraints(4, 65, 53, 17));
    jPanel8.add(jLabel10, new XYConstraints(6, 22, 24, 17));
    jPanel8.add(jTextY,   new XYConstraints(30, 42, 57, 17));
    jPanel8.add(jTextX,   new XYConstraints(30, 22, 57, 17));
    jPanel8.add(jLabel9,     new XYConstraints(3, 86, 55, 17));
    jPanel8.add(jScrollLocX,  new XYConstraints(95, 22, 89, 17));
    jPanel8.add(jScrollLocY,    new XYConstraints(95, 42, 89, 17));
    jPanel8.add(jScrollW,  new XYConstraints(95, 84, 89, 17));
    jPanel8.add(jScrollH, new XYConstraints(95, 63, 89, 17));
    jPanel8.add(jTextH, new XYConstraints(57, 63, 30, 17));
    jPanel8.add(jTextW, new XYConstraints(57, 84, 30, 17));
    jPanel7.add(jButton13,  new XYConstraints(245, 132, 87, 24));
    jPanel7.add(jButton12, new XYConstraints(66, 132, 87, 24));
    jPanel7.add(jTextRadius,  new XYConstraints(73, 96, 32, 17));
    jPanel7.add(jScrollRadius, new XYConstraints(108, 95, -1, 17));
    jPanel7.add(jLabel5, new XYConstraints(22, 40, 49, 22));
    jPanel7.add(jTextHNum, new XYConstraints(73, 43, 32, 17));
    jPanel7.add(jScrollHNum, new XYConstraints(108, 42, -1, 17));
    jPanel7.add(jLabel6, new XYConstraints(22, 67, 49, 22));
    jPanel7.add(jTextVNum, new XYConstraints(73, 70, 32, 17));
    jPanel7.add(jScrollVNum, new XYConstraints(108, 69, -1, 17));
    jPanel7.add(jLabel7, new XYConstraints(22, 93, 49, 22));
    jPanel7.add(jLabel18,      new XYConstraints(58, 12, 66, -1));
    jPanel7.add(jPanel9,      new XYConstraints(14, 8, 151, 115));
    buttonGroup3.add(jRadioAddSimple);
    buttonGroup3.add(jRadioAddReco);
    buttonGroup4.add(jRadioModeDelete);
    buttonGroup4.add(jRadioModeDetail);
    buttonGroup5.add(jRadioModeDetailOne);
    buttonGroup5.add(jRadioModeDetailMulti);

    //----------------------------------------------------------------------

    /*
    jTabbedPane1.add(jPanel2,   "自动识别");
    if(this.isArray==true) jTabbedPane1.add(jPanel7,  "手动识别");
    jTabbedPane1.add(jPanel1,   "增加及删除");
    if(this.isLayer1D==false)  jTabbedPane1.add(jPanel4,  "限制与细分");
    jTabbedPane1.add(jPanel3,  "属性修改");
    */

    // for debug , DOT DELETE
    ///*
    jTabbedPane1.add(jPanel2,   "Automatic Detection");
    jTabbedPane1.add(jPanel7,  "Detection");
    jTabbedPane1.add(jPanel1,   "Add and Delete");
    jTabbedPane1.add(jPanel4,  "Limit and Divide");
    jTabbedPane1.add(jPanel3,  "Modify Properties");
    //*/

    //---------------------------------------------------------------------

    panel1.add(jButton8,  new XYConstraints(90, 232, 73, 25));
    panel1.add(jButton1,     new XYConstraints(273, 232, 73, 25));
    panel1.add(jTabbedPane1,   new XYConstraints(22, 20, 399, 193));
    this.getContentPane().add(panel1, BorderLayout.CENTER);
    this.jSliderSensitivity.setMinimum(0);
    this.jSliderSensitivity.setMaximum(20000);
    this.jSliderSensitivity.setValue(10000);

    if(this.frameMain .getExeMethod() ==0){
      this.jButton1 .setText("OK");
      this.jButton8 .setText("Cancel");
    }
    else{
      this.setLocation(this.frameMain .getWizardWinLocation()) ;
      this.jButton1 .setText("Next▼");
      if(isFirst==true) this.jButton8 .setText("OK");
      else this.jButton8 .setText("Last▲") ;
    }

    this.j05WidthText.setEnabled(isLayer1D);
    this.jScrollWidthBar1.setEnabled(isLayer1D);
    this.resetCombox();
    this.jCheckBox1.setSelected(false);
    this.jCheckBox1_actionPerformed(null);
    this.jButton12.setEnabled(false);

    if(this.isLayer1D==false) this.jButtonGuass.setEnabled(false);

    // set the Verifier
    j05SensitivityText.setInputVerifier(new Verifier(Verifier.FLOAT,true,0,true,2,this,true));
    j05WidthText.setInputVerifier(new Verifier(Verifier.INT,true,2,true,80,this,true));

    jTextHNum.setInputVerifier(new Verifier(Verifier.INT,true,1,true,100,this,true));
    jTextVNum.setInputVerifier(new Verifier(Verifier.INT,true,1,true,100,this,true));
    jTextRadius.setInputVerifier(new Verifier(Verifier.INT,true,1,true,500,this,true));
    jTextX.setInputVerifier(new Verifier(Verifier.INT,true,-500,true,500,this,true));
    jTextY.setInputVerifier(new Verifier(Verifier.INT,true,-500,true,500,this,true));
    jTextH.setInputVerifier(new Verifier(Verifier.INT,true,-200,true,200,this,true));
    jTextW.setInputVerifier(new Verifier(Verifier.INT,true,-200,true,200,this,true));

    jTextAreaMin.setInputVerifier(new Verifier(Verifier.INT,true,1,true,1000,this,true));
    jTextGrayMin.setInputVerifier(new Verifier(Verifier.INT,true,1,true,50000,this,true));
    jTextShape.setInputVerifier(new Verifier(Verifier.INT,true,1,true,500,this,true));
    jTextDist.setInputVerifier(new Verifier(Verifier.INT,true,0,true,500,this,true));
    jTextAreaMax.setInputVerifier(new Verifier(Verifier.INT,true,1,true,2000,this,true));
    jTextGrayMax.setInputVerifier(new Verifier(Verifier.INT,true,1,true,9000,this,true));

    jCheckAreaMin_actionPerformed(null);
    jCheckAreaMax_actionPerformed(null);
    jCheckGrayMin_actionPerformed(null);
    jCheckGrayMax_actionPerformed(null);
    jCheckShape_actionPerformed(null);
    jCheckDist_actionPerformed(null);

    // added by wxs 20030823 -04
    this.setInterfaceVFromFile();
    this.setInterfaceFromV();

    // added by wxs 20030823 -05
    if(this.frameMain.getExeMethod()==1) this.isVisible=true;
    this.setVisible(this.isVisible);
    this.isInit=false;

    // added by wxs 20030823 -06
    this.doExeMethodEasyKey();
    j05SensitivityText.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        j05SensitivityText_focusLost(e);
      }
    });
    j05WidthText.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        j05WidthText_focusLost(e);
      }
    });
    jScrollWidthBar1.setVisibleAmount(0);
    j05SensitivityText.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        j05SensitivityText_focusLost(e);
      }
    });
    j05WidthText.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        j05WidthText_focusLost(e);
      }
    });
  }

  void jButtonAutoReco_actionPerformed(ActionEvent e) {
    this.setCursor(this.frameMain.getCurrentImage().createWaitCursor());
    boolean bgBlack=this.frameMain .getCurrentImage() .getBackgroundBlack();
    double co=Double.parseDouble(this.j05SensitivityText.getText());
    int operatorMode=this.getEdgeMethod();
    int threshMode=this.getThreshMethod();

    if(this.isLayer1D ==false){
      frameMain.getCurrentImage().getLayerCell() .createAllElement(
          operatorMode,threshMode,frameMain.getCurrentImage().getROI() ,
          co,true);
    }
    else{
      this.recoLayer1D(threshMode,co,bgBlack);
    }
    this.resetCombox();
    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
  }

  private void recoLayer1D(int threshMode,double co,boolean bgBlack){
    Vector v;
    Vector v2=new Vector();
    DialogImage di=frameMain .getCurrentImage();
    v=di.getLayer1D() .getRectForCellV4();
    //int lines=v.size()/4;
    int lines=v.size()/6;
    Rectangle rect;
    // 条带宽
    int lineW=this.jScrollWidthBar1.getValue();
    Integer laneNo,lineNo;
    for(int ii=1;ii<=lines;ii++){
      Point pt1=new Point(0,0),pt2=new Point(0,0);
      Point pt3=new Point(0,0),pt4=new Point(0,0);
      Point ptLeft=new Point(0,0),ptRight=new Point(0,0);

      pt1.x=((Point)v.elementAt(ii*6-6)).x; // "左上"
      pt2.x=((Point)v.elementAt(ii*6-5)).x; // "右下"
      pt3.x=((Point)v.elementAt(ii*6-4)).x; // "中点"
      pt4.x=((Point)v.elementAt(ii*6-3)).x; // "左点,右点"

      pt1.y=((Point)v.elementAt(ii*6-6)).y; // "左上"
      pt2.y=((Point)v.elementAt(ii*6-5)).y; // "右下"
      pt3.y=((Point)v.elementAt(ii*6-4)).y; // "中点"
      pt4.y=((Point)v.elementAt(ii*6-3)).y; // "左点,右点"

      laneNo=(Integer)v.elementAt(ii*6-2);
      lineNo=(Integer)v.elementAt(ii*6-1);

      int up,down;
      int x1=pt1.x,x2=pt2.x;
      //System.out.println("before:"+pt1+","+pt2);
      if(pt3.y-pt1.y>lineW) pt1=new Point(x1,pt3.y-lineW);
      if(pt2.y-pt3.y>lineW) pt2=new Point(x2,pt3.y+lineW);
      //System.out.println("after:"+pt1+","+pt2);
      ptLeft=new Point(pt1.x,pt3.y);
      ptRight=new Point(pt2.x,pt3.y);
      // 条带的范围 pt1,pt2
      // 条带的左右边界点 ptLeft,ptRight
      // 泳道的左右边界限制点，相对坐标 pt4.x,pt4.y
      v2.addElement(pt1);
      v2.addElement(pt2);
      v2.addElement(ptLeft);
      v2.addElement(ptRight);
      v2.addElement(pt4);

      // add by wxs 20030923
      v2.addElement(laneNo);
      v2.addElement(lineNo);

    }
    di.getLayerCell().createAllElementFor1D(v2,threshMode,co);
    di.paintImage() ;

    String str;
    str="⊿ Boundary Detection━［Mode=";
    if(this.j05OtsuRadio.isSelected()==true) str+="Otsu";
    else if(this.j05WataRadio.isSelected()==true) str+="Wata";
    else if(this.j05WeszRadio.isSelected()==true) str+="Wesz";
    str+="］";
    str+="［Sensitivity="+this.j05SensitivityText.getText()+"］";
    str+="［Band Width="+this.j05WidthText.getText()+"］";
    di.setDoMethod(5,str);
  }

  void jSliderSensitivity_caretPositionChanged(InputMethodEvent e) {

  }

  void jSliderSensitivity_ancestorAdded(AncestorEvent e) {

  }

  void jSliderSensitivity_stateChanged(ChangeEvent e) {
    double text1=this.jSliderSensitivity .getValue() /10000.0f;
    String text2=String.valueOf(text1);
    if(text2.length() >=6) text2=text2.substring(0,6);
    this.j05SensitivityText.setText(text2);
  }

  private void dispACell(int cellNo){
    InfoCell cell=null;
    int index;
    Vector v=this.frameMain.getCurrentImage().getLayerCell().current;
    for(int iii=1;iii<=v.size() ;iii++){
      cell=(InfoCell)v.elementAt(iii-1);
      cell.isSelected=false;
    }
    for(int iii=1;iii<=v.size() ;iii++){
      cell=(InfoCell)v.elementAt(iii-1);
      index=cell.cellNo;
      if(cellNo==index) break;
    }
    if(v.size()<=0) return;
    if(jCheckDispObj.isSelected()==true) cell.isSelected=true;
    this.jButtonEdgeColor.setForeground(cell.edgeColor );
    this.jButtonGegometryColor .setForeground(cell.geometryColor );
    this.jButtonGravityColor .setForeground(cell.gravityColor );

    cell.isVisibleNo=this.jCheckBoxDispNo.isSelected();
    cell.visible=this.jCheckBoxEdgeIsVisible.isSelected();
    cell.isVisibleGeometry=this.jCheckBoxGeometryIsVisible.isSelected();
    cell.isVisibleGravity=this.jCheckBoxGravityIsVisible.isSelected();

    //this.jCheckBoxDispNo .setSelected(cell.isVisibleNo );
    //this.jCheckBoxEdgeIsVisible .setSelected(cell.visible );
    //this.jCheckBoxGeometryIsVisible .setSelected(cell.isVisibleGeometry);
    //this.jCheckBoxGravityIsVisible .setSelected(cell.isVisibleGravity );
    this.frameMain.getCurrentImage().paintImage();
  }

  void jComboBoxCellNo_itemStateChanged(ItemEvent e) {
    int index=Integer.parseInt(( String)e.getItem());
    selectIndex=index;
    this.dispACell(index);
  }

  private void modifyACell(){
    InfoCell oldCell,newCell;
    LayerCell layerCell=this.frameMain.getCurrentImage().getLayerCell();
    //int index=this.jComboBoxCellNo.getSelectedIndex()+1;
    int index=Integer.parseInt((String)jComboBoxCellNo.getSelectedItem());
    int cellNo=0;
    oldCell=null;
    for(int iii=1;iii<=layerCell.current.size();iii++){
      oldCell=(InfoCell)layerCell.current.elementAt(iii-1);
      cellNo=oldCell.cellNo;
      if(cellNo==index) break;
    }

    newCell=oldCell.duplicate();
    newCell.cellNo =oldCell.cellNo;
    newCell.edgeColor =this.jButtonEdgeColor.getForeground();
    newCell.geometryColor =this.jButtonGegometryColor.getForeground();
    newCell.gravityColor =this.jButtonGravityColor.getForeground();
    newCell.visible =this.jCheckBoxEdgeIsVisible.isSelected();
    newCell.isVisibleGeometry =this.jCheckBoxGeometryIsVisible.isSelected();
    newCell.isVisibleGravity =this.jCheckBoxGravityIsVisible.isSelected();
    newCell.isVisibleNo =this.jCheckBoxDispNo.isSelected();

    if(oldCell.edgeColor.equals(newCell.edgeColor)==true){
      if(oldCell.geometryColor.equals(newCell.geometryColor)==true){
        if(oldCell.gravityColor.equals(newCell.gravityColor)==true){
          if(oldCell.visible==newCell.visible){
            if(oldCell.isVisibleGeometry==newCell.isVisibleGeometry){
              if(oldCell.isVisibleGravity==newCell.isVisibleGravity){
                if(oldCell.isVisibleNo==newCell.isVisibleNo){
                  return;
                }
              }
            }
          }
        }
      }
    }

    layerCell.removeElement(oldCell,true,"Cell",1,false);
    layerCell.addElement(newCell,true,"Cell",2);
    this.frameMain.getCurrentImage() .paintImage() ;
  }


  void jButton4_actionPerformed(ActionEvent e){
    this.frameMain.setCmmStatus("selectRectangle");
    this.frameMain.getCurrentImage().mySetCursor(true,DialogImage.CURRECT);
    this.frameMain.getCurrentImage().initSelectPolygon();
  }

  void jButtonGegometryColor_actionPerformed(ActionEvent e) {
    Color c;
    JColorChooser jcc=new JColorChooser();
    c=jcc.showDialog(this,"Please select geometry center color:", jButtonGegometryColor.getForeground() ) ;
    if(c==null) return;
    jButtonGegometryColor.setForeground(c);
  }

  void jButtonGravityColor_actionPerformed(ActionEvent e) {
    Color c;
    JColorChooser jcc=new JColorChooser();
    c=jcc.showDialog(this,"Please select gray center color:", jButtonGravityColor.getForeground() ) ;
    if(c==null) return;
    jButtonGravityColor.setForeground(c);
  }

  void jButtonEdgeColor_actionPerformed(ActionEvent e) {
    Color c;
    JColorChooser jcc=new JColorChooser();
    c=jcc.showDialog(this,"Please select geometry border color:", jButtonEdgeColor.getForeground() ) ;
    if(c==null) return;
    jButtonEdgeColor.setForeground(c);
  }

  void jButton7_actionPerformed(ActionEvent e) {
    this.modifyACell();
  }

  void jButton3_actionPerformed(ActionEvent e) {
    if(this.isLayer1D==true) this.report1D();
    else this.reportNot1D();
  }

  private void report1D(){
    String[] colNames={"No","Land No","Band No","Geometry Center X","Geometry Center Y","Gray Center X","Gray Center Y",
                       "Area","Perimeter","Integral","Average Gray","Shape Factor","Distance"};
    Vector vector=this.frameMain .getCurrentImage() .getLayerCell().current  ;
    int size=vector.size() ;
    String[][] dataTable=new String[size][colNames.length];
    InfoCell cell;
    double pi=3.1415926536;
    double shape;
    double dt;
    for(int ii=1;ii<=size;ii++){
      cell=(InfoCell)vector.elementAt(ii-1);
      dataTable[ii-1][0]=String.valueOf(cell.cellNo);
      dataTable[ii-1][1]=String.valueOf(cell.laneNo);
      dataTable[ii-1][2]=String.valueOf(cell.lineNo);
      dataTable[ii-1][3]=setPrecision(String.valueOf(cell.m_centrex),2);
      dataTable[ii-1][4]=setPrecision(String.valueOf(cell.m_centrey),2);
      dataTable[ii-1][5]=setPrecision(String.valueOf(cell.m_grayx),2);
      dataTable[ii-1][6]=setPrecision(String.valueOf(cell.m_grayy),2);
      dataTable[ii-1][7]=setPrecision(String.valueOf(cell.m_area),2);
      dataTable[ii-1][8]=setPrecision(String.valueOf(cell.m_circlement),2);
      dataTable[ii-1][9]=setPrecision(String.valueOf(cell.m_graySum),2);
      dataTable[ii-1][10]=setPrecision(String.valueOf(cell.m_graySum/cell.m_area),2);
      if(cell.m_area==0) shape=0;
      else shape=(cell.m_circlement*cell.m_circlement)/(4*pi*cell.m_area);
      dt=Math.sqrt(Math.abs((cell.m_centrex-cell.m_grayx)*(cell.m_centrex-cell.m_grayx)+
                   (cell.m_centrey-cell.m_grayy)*(cell.m_centrey-cell.m_grayy)));
      if(dt<0.1) dt=0;
      dataTable[ii-1][11]=setPrecision(String.valueOf(shape),4);
      dataTable[ii-1][12]=setPrecision(String.valueOf(dt),2);
    }
    String []text=new String[3];
    text[0]=this.getTitle();
    Date date=new Date(System.currentTimeMillis());
    text[1]="Date: "+date.toString();
    text[2]="Total number bands:"+String.valueOf(size);
    // 显示之
    DialogReport di=new DialogReport(this.frameMain ,"",false);
    di.setContext(this.getTitle() +" reslut ",text,colNames,dataTable,false);
    di.show() ;
  }

  private void reportNot1D(){
    String[] colNames={"No","Geometry Center X","Geometry Center Y","Gray Center X","Gray Center Y",
                       "Area","Perimeter","Integral","Average Gray","Shape Factor","Distance"};
    Vector vector=this.frameMain .getCurrentImage() .getLayerCell().current  ;
    int size=vector.size() ;
    String[][] dataTable=new String[size][colNames.length];
    InfoCell cell;
    double pi=3.1415926536;
    double shape;
    double dt;
    for(int ii=1;ii<=size;ii++){
      cell=(InfoCell)vector.elementAt(ii-1);
      dataTable[ii-1][0]=String.valueOf(cell.cellNo);
      dataTable[ii-1][1]=setPrecision(String.valueOf(cell.m_centrex),2);
      dataTable[ii-1][2]=setPrecision(String.valueOf(cell.m_centrey),2);
      dataTable[ii-1][3]=setPrecision(String.valueOf(cell.m_grayx),2);
      dataTable[ii-1][4]=setPrecision(String.valueOf(cell.m_grayy),2);
      dataTable[ii-1][5]=setPrecision(String.valueOf(cell.m_area),2);
      dataTable[ii-1][6]=setPrecision(String.valueOf(cell.m_circlement),2);
      dataTable[ii-1][7]=setPrecision(String.valueOf(cell.m_graySum),2);
      dataTable[ii-1][8]=setPrecision(String.valueOf(cell.m_graySum/cell.m_area),2);
      if(cell.m_area==0) shape=0;
      else shape=(cell.m_circlement*cell.m_circlement)/(4*pi*cell.m_area);
      dt=Math.abs((cell.m_centrex-cell.m_grayx)*(cell.m_centrex-cell.m_grayx)+
                   (cell.m_centrey-cell.m_grayy)*(cell.m_centrey-cell.m_grayy));
      dt=Math.sqrt(dt);
      if(dt<0.1) dt=0;
      dataTable[ii-1][9]=setPrecision(String.valueOf(shape),4);
      dataTable[ii-1][10]=setPrecision(String.valueOf(dt),2);
    }
    String []text=new String[3];
    text[0]=this.getTitle();
    Date date=new Date(System.currentTimeMillis());
    text[1]="Date: "+date.toString();
    text[2]="Total number objects: "+String.valueOf(size);
    // 显示之
    DialogReport di=new DialogReport(this.frameMain ,"",false);
    di.setContext(this.getTitle() +" result ",text,colNames,dataTable,false);
    di.show() ;
  }

  public String setPrecision(String str,int precision){
    int indexDot=str.indexOf(".");
    String returnStr="";
    if(indexDot>=0){
      int strLen=str.length();
      if(indexDot+1+precision>strLen) returnStr=str;
      else returnStr=str.substring(0,indexDot+1+precision);
    }
    else returnStr=str;
    return returnStr;
  }

  public void resetCombox(){
    this.jComboBoxCellNo.removeAllItems() ;
    this.jComboBoxCellNo22.removeAllItems() ;
    // modified by wxs 20030806
    if(this.frameMain.getCurrentImage()==null) return;
    Vector layerCell=this.frameMain .getCurrentImage() .getLayerCell().current ;
    int size=layerCell.size();
    String no;
    InfoCell cell;
    for(int ii=1;ii<=size;ii++){
      cell=(InfoCell)layerCell.elementAt(ii-1);
      no=String.valueOf(cell.cellNo);
      this.jComboBoxCellNo.addItem(no);
      this.jComboBoxCellNo22.addItem(no);
      this.jComboBoxCellNo .setSelectedIndex(0);
      this.jComboBoxCellNo22 .setSelectedIndex(0);
    }
  }


  void jButton5_actionPerformed(ActionEvent e){
    DialogImage di=this.frameMain.getCurrentImage();
    if(di.getSelectPolygon()==null){
      JOptionPane.showMessageDialog(this.frameMain,"Please select a region",
                                    "Please select a region",JOptionPane.ERROR_MESSAGE);
      return;
    }
    if(this.jRadioAddSimple.isSelected()==true){
      Polygon polygon=di.getSelectPolygon();
      if(polygon.npoints<=3) return;
      int cellNo=di.getLayerCell().current.size()+1;
      InfoCell cell=di.getLayerCell().createCell(polygon,di.getIP(),
          di.getBackgroundBlack(),di.getGrayBits(),cellNo);
      if(this.isLayer1D==true){
        if(di.setLaneLineForCell(cell)==true){
          di.getLayerCell().addElement(cell,true,"Cell",1,-1);
        }
      }
      else{
        di.getLayerCell().addElement(cell,true,"Cell",1,-1);
      }
      di.setSelectPolygonNull();
      di.paintImage();
    }
    else{
      Polygon polygon=di.getSelectPolygon();
      int cellNo=di.getLayerCell().current.size()+1;
      double co=Double.parseDouble(this.j05SensitivityText.getText());
      di.getLayerCell().createLayerCellObjects(polygon,0,di.getIP(),
          this.getEdgeMethod(),this.getThreshMethod(),co,
          di.getBackgroundBlack(),di.getGrayBits(),cellNo,this.isLayer1D);
      di.setSelectPolygonNull();
      di.paintImage();
    }
    this.resetCombox();
  }

  private int getEdgeMethod(){
    int operatorMode=AnaImage.edgeNone;
    if(this.jRadioButtonOpNone .isSelected() ==true)
      operatorMode=AnaImage.edgeNone ;
    else if(this.jRadioButtonOpSobel .isSelected() ==true)
      operatorMode=AnaImage.edgeSobel ;
    else if(this.jRadioButtonOpPrewitt .isSelected() ==true)
      operatorMode=AnaImage.edgePrewitt;
    else if(this.jRadioButtonOpRobert .isSelected() ==true)
      operatorMode=AnaImage.edgeRobert ;
    else if(this.jRadioButtonOpLap .isSelected() ==true)
      operatorMode=AnaImage.edgeLap;
    else if(this.jRadioButtonOpGuassLap .isSelected() ==true)
      operatorMode=AnaImage.edgeGuassLap;
    return operatorMode;
  }

  private int getThreshMethod(){
    int threshMode=AnaImage.threshModeOtsu;
    if(this.j05OtsuRadio .isSelected() ==true)
      threshMode=AnaImage.threshModeOtsu;
    else if(this.j05WataRadio.isSelected() ==true)
      threshMode=AnaImage.threshModeWata;
    else if(this.j05WeszRadio.isSelected() ==true)
      threshMode=AnaImage.threshModeWesz;
    return threshMode;
  }

  void jButton10_actionPerformed(ActionEvent e) {
    this.frameMain .setCmmStatus("selectCircle");
    this.frameMain.getCurrentImage().mySetCursor(true,DialogImage.CURCIRCLE);
    this.frameMain .getCurrentImage().initSelectPolygon();
  }

  void jButton2_actionPerformed(ActionEvent e) {
    if(this.isAutoColor==false){
      this.jButton2.setText("Uniform Color");
      Color []c=new Color[125];
      int noc=0;
      int seed[]=new int[5];
      seed[0]=0;
      seed[1]=255;
      seed[2]=127;
      seed[3]=64;
      seed[4]=191;
      for(int ii=0;ii<5;ii++){
        for(int jj=0;jj<5;jj++){
          for(int pp=0;pp<5;pp++){
            if(ii==jj && ii==pp){ }
            else{
              c[noc]=new Color(seed[ii],seed[jj],seed[pp]);
              noc++;
            }
          }
        }
      }
      InfoCell cell;
      Vector layerCell=this.frameMain .getCurrentImage().getLayerCell().current;
      int cn=0;
      for(int iii=1;iii<=layerCell.size() ;iii++){
        cell=(InfoCell)layerCell.elementAt(iii-1);
        cell.autoColor=true;
        cell.edgeColor=c[cn];
        if(cn>noc) cn=0;
        else cn++;
      }
    }
    else{
      this.jButton2.setText("Automatic Color");
      Color c=JColorChooser.showDialog(this.frameMain,"Please select border color:",Color.green);
      InfoCell cell;
      Vector layerCell=this.frameMain .getCurrentImage().getLayerCell().current;
      for(int iii=1;iii<=layerCell.size() ;iii++){
        cell=(InfoCell)layerCell.elementAt(iii-1);
        cell.autoColor=false;
        cell.edgeColor=c;
      }
    }
    this.isAutoColor=!this.isAutoColor;
    this.frameMain.getCurrentImage().paintImage();
  }

  void jButton1_actionPerformed(ActionEvent e) {
    this.dispose();
    if(this.frameMain .getExeMethod()>=1){
      this.frameMain .setWizardWinLocation(this.getLocation() ) ;
      this.frameMain.doStep(true);
    }
  }

  void jButton8_actionPerformed(ActionEvent e) {
    this.dispose() ;
    if(this.frameMain .getExeMethod() >=1){
      this.frameMain .setWizardWinLocation(this.getLocation() ) ;
      this.frameMain.doStep(false);
    }
  }

  void jScrollWidthBar1_adjustmentValueChanged(AdjustmentEvent e) {
    j05WidthText.setText(Integer.toString(jScrollWidthBar1.getValue()));
  }

  // added by wxs 20030823 -07
  private void setInterfaceVFromFile(){
    String pathName=this.frameMain.getSystemDir()+"\\database\\"+
                    this.frameMain.getCurrentParaPre()+".1d";
    try{
      File file=new File(pathName);
      if(file.exists() ==true){
        ObjectInputStream in= new ObjectInputStream(new FileInputStream(file));
        int num=in.readInt();
        for(int ii=0;ii<num;ii++){
          Settings st=new Settings();
          st=(Settings)in.readObject();
          if(st.type.substring(0,2).equals("05"))  set.addElement(st);
        }
        in.close();
      }
    }
    catch(IOException e){
      e.printStackTrace();
    }
    catch(ClassNotFoundException e){
      e.printStackTrace();
    }
  }

  // added by wxs 20030823 -08
  private void setInterfaceFromV(){
    int exeMethod=this.frameMain.getExeMethod();
    boolean bln;
    int it;
    String str;
    if(exeMethod==2)  this.isVisible =getSettings("05Visible").paraBoolean;
    else this.isVisible=true;
    str=getSettings("05Method").paraString;
    if(str.equals("05Otsu")) this.j05OtsuRadio.setSelected(true);
    else if(str.equals("05Wata")) this.j05WataRadio.setSelected(true);
    else if(str.equals("05Wesz")) this.j05WeszRadio.setSelected(true);
    this.j05SensitivityText.setText(getSettings("05Sensitivity").paraString);
    this.j05WidthText.setText(getSettings("05Width").paraString);
    int st=(int)((Double.parseDouble(getSettings("05Sensitivity").paraString))*10000.0f);
    if(st<this.jSliderSensitivity.getMinimum()) st=this.jSliderSensitivity.getMinimum();
    if(st>this.jSliderSensitivity.getMaximum()) st=this.jSliderSensitivity.getMaximum();
    this.jScrollWidthBar1.setValue(Integer.parseInt(getSettings("05Width").paraString));
    this.isReport=getSettings("05Report").paraBoolean;
  }

  // added by wxs 20030823 -09
  public Settings getSettings(String type){
    Settings st=new Settings();
    for(int ii=1;ii<=set.size();ii++){
      st=(Settings)set.elementAt(ii-1);
      if(st.type.equals(type)) break;
    }
    return st;
  }

  // added by wxs 20030823 -10
  public void doExeMethodEasyKey(){
    if(this.frameMain.getExeMethod()!=2) return;
    if(this.isVisible==true) return;
    this.frameMain.getCurrentImage().setTitle("Dection boundary......");
    jButtonAutoReco_actionPerformed(null);
    if(this.isReport==true) jButton3_actionPerformed(null);
    this.frameMain.getCurrentImage().setTitle("Done");
    // donext
    this.frameMain.setWizardWinLocation(this.getLocation() ) ;
    this.frameMain.doStep(true)  ;
  }


  void jScrollHNum_adjustmentValueChanged(AdjustmentEvent e) {
    this.jTextHNum.setText(String.valueOf(this.jScrollHNum.getValue()));
    this.setEdgeForArray();
  }

  private void setEdgeForArray(){
    this.frameMain.getCurrentImage().getLayerCell().setEdgeForArray(
        this.jScrollHNum.getValue(),this.jScrollVNum.getValue(),
        this.jScrollRadius.getValue(),this.jCheckBox1.isSelected(),
        this.jScrollW.getValue(),this.jScrollH.getValue(),
        this.jScrollLocX.getValue(),this.jScrollLocY.getValue());
    this.frameMain.getCurrentImage().paintImage();
    this.jButton12.setEnabled(false);
  }

  void jScrollVNum_adjustmentValueChanged(AdjustmentEvent e) {
    this.jTextVNum.setText(String.valueOf(this.jScrollVNum.getValue()));
    this.setEdgeForArray();
  }

  void jScrollRadius_adjustmentValueChanged(AdjustmentEvent e) {
    this.jTextRadius.setText(String.valueOf(this.jScrollRadius.getValue()));
    this.setEdgeForArray();
  }

  void jScrollW_adjustmentValueChanged(AdjustmentEvent e) {
    this.jTextW.setText(String.valueOf(this.jScrollW.getValue()));
    this.setEdgeForArray();
  }

  void jScrollH_adjustmentValueChanged(AdjustmentEvent e) {
    this.jTextH.setText(String.valueOf(this.jScrollH.getValue()));
    this.setEdgeForArray();
  }

  void jScrollLocX_adjustmentValueChanged(AdjustmentEvent e) {
    this.jTextX.setText(String.valueOf(this.jScrollLocX.getValue()));
    this.setEdgeForArray();
  }

  void jScrollLocY_adjustmentValueChanged(AdjustmentEvent e) {
    this.jTextY.setText(String.valueOf(this.jScrollLocY.getValue()));
    this.setEdgeForArray();
  }

  void jCheckBox1_actionPerformed(ActionEvent e) {
    boolean bl=this.jCheckBox1.isSelected();
    this.jTextH.setEnabled(bl);
    this.jTextW.setEnabled(bl);
    this.jTextX.setEnabled(bl);
    this.jTextY.setEnabled(bl);
    this.jScrollW.setValue(0);
    this.jScrollLocX.setValue(0);
    this.jScrollH.setValue(0);
    this.jScrollLocY.setValue(0);
    this.jScrollW.setEnabled(bl);
    this.jScrollLocX.setEnabled(bl);
    this.jScrollH.setEnabled(bl);
    this.jScrollLocY.setEnabled(bl);
  }

  void jButton13_actionPerformed(ActionEvent e) {
    this.jButton12.setEnabled(true);
    this.frameMain.getCurrentImage().getLayerCell().initLayer();
    this.frameMain.getCurrentImage().getLayerCell().findEdgeForArray(this.jCheckBox1.isSelected());
    this.frameMain.getCurrentImage().getLayerCell().isArray=false;
    this.frameMain.getCurrentImage().paintImage();
    this.resetCombox();
  }

  void jButton12_actionPerformed(ActionEvent e) {
    jButton3_actionPerformed(e);
  }

  void jButton11_actionPerformed(ActionEvent e) {
    this.frameMain .setCmmStatus("selectEllipse");
    this.frameMain.getCurrentImage().mySetCursor(true,DialogImage.CURELLIPSE);
    this.frameMain .getCurrentImage().initSelectPolygon();
  }

  void jButton14_actionPerformed(ActionEvent e) {
    this.frameMain .setCmmStatus("selectPolygon");
    this.frameMain.getCurrentImage().mySetCursor(true,DialogImage.CURPOLYGON);
    this.frameMain .getCurrentImage().initSelectPolygon();
  }

  void jButton15_actionPerformed(ActionEvent e) {
    DialogImage di=this.frameMain.getCurrentImage();
    di.setSelectPolygonNull();
    this.frameMain.setCmmStatusDefault();
    di.paintImage();
  }

  void jButton17_actionPerformed(ActionEvent e) {
    InfoCell cell;
    Vector layerCell=this.frameMain .getCurrentImage().getLayerCell().current;
    if(this.isDispAllNo==false)  this.jButton17.setText("Hide all No");
    else                         this.jButton17.setText("Show all No");
    for(int iii=1;iii<=layerCell.size() ;iii++){
      cell=(InfoCell)layerCell.elementAt(iii-1);
      cell.isVisibleNo=!this.isDispAllNo;
    }
    this.isDispAllNo=!this.isDispAllNo;
    this.frameMain .getCurrentImage() .paintImage() ;
  }

  void jCheckDispObj_actionPerformed(ActionEvent e) {
    this.dispACell(selectIndex);
  }

  void jScrollAreaMin_adjustmentValueChanged(AdjustmentEvent e) {
    this.jTextAreaMin.setText(String.valueOf(this.jScrollAreaMin.getValue()));
  }

  void jScrollAreaMax_adjustmentValueChanged(AdjustmentEvent e) {
    this.jTextAreaMax.setText(String.valueOf(this.jScrollAreaMax.getValue()));
  }

  void jScrollGrayMin_adjustmentValueChanged(AdjustmentEvent e) {
    this.jTextGrayMin.setText(String.valueOf(this.jScrollGrayMin.getValue()));
  }

  void jScrollGrayMax_adjustmentValueChanged(AdjustmentEvent e) {
    this.jTextGrayMax.setText(String.valueOf(this.jScrollGrayMax.getValue()));
  }

  void jScrollShape_adjustmentValueChanged(AdjustmentEvent e) {
    this.jTextShape.setText(String.valueOf(this.jScrollShape.getValue()));
  }

  void jScrollDist_adjustmentValueChanged(AdjustmentEvent e) {
    this.jTextDist.setText(String.valueOf(this.jScrollDist.getValue()));
  }

  void jButton16_actionPerformed(ActionEvent e){
    DialogImage di=this.frameMain.getCurrentImage();
    LayerCell layerCell=di.getLayerCell();
    InfoCell cell=null;
    int sz;
    int total=0;
    for(int iii=1;iii<=layerCell.current.size() ;iii++){
      cell=(InfoCell)layerCell.current.elementAt(iii-1);
      cell.isDetailed=false;
      total++;
    }
    boolean isNotOK=true;
    int now;
    String title=this.getTitle();
    while(isNotOK==true){
      sz=layerCell.current.size();
      now=0;
      for(int ii=1;ii<=sz;ii++){
        cell=(InfoCell)layerCell.current.elementAt(ii-1);
        if(cell.isDetailed==false) now++;
      }
      this.setTitle("RUNNING ...  "+(total-now)+ "  OF  "+ total);
      for(int ii=1;ii<=sz;ii++){
        cell=(InfoCell)layerCell.current.elementAt(ii-1);
        if(cell==null) continue;
        if(cell.isDetailed==false){
          cell.isDetailed=true;
          detailCell(cell,false);
          break;
        }
      }
      sz=layerCell.current.size();
      isNotOK=false;
      for(int ii=1;ii<=sz;ii++){
        cell=(InfoCell)layerCell.current.elementAt(ii-1);
        if(cell==null) continue;
        if(cell.isDetailed==false){
          isNotOK=true;
          break;
        }
      }
    }
    this.setTitle("Success !");
    this.resetCombox();
    this.setTitle(title);
  }

  public void detailCell(InfoCell cell,boolean resetInterface){
    boolean isMinArea,isMaxArea,isMinGray,isMaxGray,isShape,isDist;
    int minArea,maxArea,minGray,maxGray;
    double shape,dist;
    int edgeMode,threshMode;
    boolean isDelete;
    int layers,maxLayers;

    DialogImage di=this.frameMain.getCurrentImage();
    LayerCell layerCell=di.getLayerCell();

    isMinArea=this.jCheckAreaMin.isSelected();
    isMaxArea=this.jCheckAreaMax.isSelected();
    isMinGray=this.jCheckGrayMin.isSelected();
    isMaxGray=this.jCheckGrayMax.isSelected();
    isShape=this.jCheckShape.isSelected();
    isDist=this.jCheckDist.isSelected();
    isDelete=this.jRadioModeDelete.isSelected();

    if(this.jRadioModeDetailOne.isSelected()==true)  maxLayers=1;
    else                                             maxLayers=50;

    minArea=Integer.parseInt(this.jTextAreaMin.getText());
    maxArea=Integer.parseInt(this.jTextAreaMax.getText());
    minGray=Integer.parseInt(this.jTextGrayMin.getText());
    maxGray=Integer.parseInt(this.jTextGrayMax.getText());
    shape=Integer.parseInt(this.jTextShape.getText());
    dist=Integer.parseInt(this.jTextDist.getText());

    edgeMode=this.getEdgeMethod();
    threshMode=this.getThreshMethod();

    isDelete=this.jRadioModeDelete.isSelected();

    layerCell.detailObject(cell,isDelete,1,maxLayers,
                           isMinArea,minArea,isMaxArea,maxArea,
                           isMinGray,minGray,isMaxGray,maxGray,
                           isShape,shape,isDist,dist,
                           di.getIP(),edgeMode,threshMode,
                           1,di.getBackgroundBlack(),
                           di.getGrayBits());
    if(resetInterface==true) this.resetCombox();
    di.paintImage();
  }

  void jButton18_actionPerformed(ActionEvent e){
    this.frameMain.setCmmStatus("DetailObject");
    this.frameMain.getCurrentImage().mySetCursor(true,DialogImage.CURDETAIL);
  }

  void jCheckBoxEdgeIsVisible_actionPerformed(ActionEvent e) {
    this.dispACell(selectIndex);
  }

  void jCheckBoxDispNo_actionPerformed(ActionEvent e) {
    this.dispACell(selectIndex);
  }

  void jCheckBoxGravityIsVisible_actionPerformed(ActionEvent e) {
    this.dispACell(selectIndex);
  }

  void jCheckBoxGeometryIsVisible_actionPerformed(ActionEvent e) {
    this.dispACell(selectIndex);
  }

  void jButton19_actionPerformed(ActionEvent e) {
    if(this.isLayer1D==true) this.report1D();
    else this.reportNot1D();
  }

  void jButton20_actionPerformed(ActionEvent e) {
    if(this.isLayer1D==true) this.report1D();
    else this.reportNot1D();
  }

  void jButtonGuass_actionPerformed(ActionEvent e) {
    int cellNo=Integer.parseInt((String)jComboBoxCellNo22.getSelectedItem());
    int laneNo,lineNo,cellNum;
    Layer1D layer1D=this.frameMain.getCurrentImage().getLayer1D();
    LayerCell layerCell=this.frameMain.getCurrentImage().getLayerCell();
    cellNum=layerCell.current.size();
    InfoCell cell=null;
    for(int ii=1;ii<=cellNum;ii++){
      cell=(InfoCell)layerCell.current.elementAt(ii-1);
      if(cell.cellNo==cellNo) break;
    }
    if(cell==null) return;
    String tip;
    tip="Fitting Lane No: "+cell.laneNo+", Band No: "+cell.lineNo+" with Gauss";
    DialogGuass dialogGauss=new DialogGuass(this.frameMain,tip,false);
    dialogGauss.setFitContent(cell);
    dialogGauss.show();
  }

  void jButton21_actionPerformed(ActionEvent e) {
    InfoCell oldCell,newCell;
    int index=Integer.parseInt((String)jComboBoxCellNo.getSelectedItem());
    int cellNo=0;
    oldCell=null;
    for(int iii=1;iii<=this.frameMain .getCurrentImage() .getLayerCell() .current .size() ;iii++){
      oldCell=(InfoCell)this.frameMain .getCurrentImage() .getLayerCell() .current.elementAt(iii-1);
      cellNo=oldCell.cellNo ;
      if(cellNo==index) break;
   }
   this.frameMain .getCurrentImage() .getLayerCell() .removeElement(oldCell,true,"Cell",1,true);
   this.resetCombox() ;
   this.frameMain .getCurrentImage() .paintImage() ;
  }

  void jButton22_actionPerformed(ActionEvent e) {
    this.frameMain .setCmmStatus("DelObject");
    this.frameMain.getCurrentImage().mySetCursor(true,DialogImage.CURDELETE);
  }

  void jSliderSensitivity_adjustmentValueChanged(AdjustmentEvent e) {
    double text1=this.jSliderSensitivity.getValue() /10000.0f;
    String text2=String.valueOf(text1);
    if(text2.length() >=6) text2=text2.substring(0,6);
    this.j05SensitivityText.setText(text2);
  }

  void j05SensitivityText_focusLost(FocusEvent e) {
    try{
      int sen=(int)(Double.parseDouble(j05SensitivityText.getText())*10000.0f+0.5);
      jSliderSensitivity.setValue(sen);
    }
    catch(NumberFormatException e2){

    }
  }

  void j05WidthText_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(j05WidthText.getText());
      jScrollWidthBar1.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void jTextHNum_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(this.jTextHNum.getText());
      this.jScrollHNum.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void jTextVNum_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(this.jTextVNum.getText());
      this.jScrollVNum.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void jTextRadius_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(jTextRadius.getText());
      this.jScrollRadius.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void jTextX_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(jTextX.getText());
      this.jScrollLocX.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void jTextY_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(jTextY.getText());
      this.jScrollLocY.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void jTextH_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(jTextH.getText());
      this.jScrollH.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void jTextW_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(jTextW.getText());
      this.jScrollW.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void jCheckAreaMin_actionPerformed(ActionEvent e) {
    boolean bl=jCheckAreaMin.isSelected();
    this.jTextAreaMin.setEnabled(bl);
    this.jScrollAreaMin.setEnabled(bl);
  }

  void jCheckAreaMax_actionPerformed(ActionEvent e) {
    boolean bl=jCheckAreaMax.isSelected();
    this.jTextAreaMax.setEnabled(bl);
    this.jScrollAreaMax.setEnabled(bl);
  }

  void jCheckGrayMin_actionPerformed(ActionEvent e) {
    boolean bl=jCheckGrayMin.isSelected();
    this.jTextGrayMin.setEnabled(bl);
    this.jScrollGrayMin.setEnabled(bl);
  }

  void jCheckGrayMax_actionPerformed(ActionEvent e) {
    boolean bl=jCheckGrayMax.isSelected();
    this.jTextGrayMax.setEnabled(bl);
    this.jScrollGrayMax.setEnabled(bl);
  }

  void jCheckShape_actionPerformed(ActionEvent e) {
    boolean bl=jCheckShape.isSelected();
    this.jTextShape.setEnabled(bl);
    this.jScrollShape.setEnabled(bl);
  }

  void jCheckDist_actionPerformed(ActionEvent e) {
    boolean bl=jCheckDist.isSelected();
    this.jTextDist.setEnabled(bl);
    this.jScrollDist.setEnabled(bl);
  }

  void jTextAreaMin_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(jTextAreaMin.getText());
      this.jScrollAreaMin.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void jTextAreaMax_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(jTextAreaMax.getText());
      this.jScrollAreaMax.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void jTextGrayMin_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(jTextGrayMin.getText());
      this.jScrollGrayMin.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void jTextGrayMax_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(jTextGrayMax.getText());
      this.jScrollGrayMax.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void jTextShape_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(jTextShape.getText());
      this.jScrollShape.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void jTextDist_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(jTextDist.getText());
      this.jScrollDist.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  // end of the class
}

