package easygel.setting;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.*;
import java.io.*;
import javax.swing.event.*;
import easygel.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogSet1D extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JTabbedPane jTabbedPane1 = new JTabbedPane();
  private JPanel jPanel1 = new JPanel();
  private XYLayout xYLayout2 = new XYLayout();
  private JTextField j01RecoSensitivityText = new JTextField();
  private JRadioButton j01RecoMethod2Radio = new JRadioButton();
  private JRadioButton j01RecoMethod1Radio = new JRadioButton();
  private JLabel t011 = new JLabel();
  private ButtonGroup buttonGroup1D = new ButtonGroup();
  private JLabel t012 = new JLabel();
  private JTextField j01HandLanesText = new JTextField();
  private JLabel t013 = new JLabel();
  private JTextField j01HandDistText = new JTextField();
  private JPanel jPanel2 = new JPanel();
  private JRadioButton j02NeedRadio = new JRadioButton();
  private JRadioButton j02NoNeedRadio = new JRadioButton();
  private XYLayout xYLayout3 = new XYLayout();
  private JPanel jPanel3 = new JPanel();
  private JButton jButton2 = new JButton();
  private TitledBorder titledBorder1;
  private XYLayout xYLayout4 = new XYLayout();
  private JPanel jPanel4 = new JPanel();
  private XYLayout xYLayout5 = new XYLayout();
  private JCheckBox j01VisibleCheck = new JCheckBox();
  private ButtonGroup buttonGroup1 = new ButtonGroup();

  private Vector set=new Vector();
  private JRadioButton j01OptRadio = new JRadioButton();
  private JRadioButton j01RecoRadio = new JRadioButton();
  private JRadioButton j01HandRadio = new JRadioButton();
  private String saveFilePathName;
  private String sourceName;
  private int showType;
  public static int TYPE_ADD=0,TYPE_BROWSE=1,TYPE_CHANGE=3;
  private boolean isOK;
  private ButtonGroup buttonGroup2 = new ButtonGroup();
  private JRadioButton j03MethodAvgRadio = new JRadioButton();
  private JRadioButton j03MethodRollballRadio = new JRadioButton();
  private JTextField j03RadiusText = new JTextField();
  private JLabel jLabel2 = new JLabel();
  private JRadioButton j03MethodMinRadio = new JRadioButton();
  private JRadioButton j03MethodNoneRadio = new JRadioButton();
  private JCheckBox j03VisibleCheck = new JCheckBox();
  private ButtonGroup buttonGroup3 = new ButtonGroup();
  private JTextField j04NoiseText = new JTextField();
  private JTextField j04SlopeText = new JTextField();
  private JTextField j04HeightText = new JTextField();
  private JCheckBox j04OnlyheightCheck = new JCheckBox();
  private JTextField j04WidthText = new JTextField();
  private JLabel t043 = new JLabel();
  private JLabel t042 = new JLabel();
  private JLabel t041 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JCheckBox j04VisibleCheck = new JCheckBox();
  private JPanel jPanel5 = new JPanel();
  private JRadioButton j05OtsuRadio = new JRadioButton();
  private JRadioButton j05WataRadio = new JRadioButton();
  private JTextField j05SensitivityText = new JTextField();
  private JLabel jLabel5 = new JLabel();
  private JRadioButton j05WeszRadio = new JRadioButton();
  private JTextField j05WidthText = new JTextField();
  private JLabel jLabel1 = new JLabel();
  private XYLayout xYLayout6 = new XYLayout();
  private JCheckBox j05VisibleCheck = new JCheckBox();
  private ButtonGroup buttonGroup4 = new ButtonGroup();
  private JCheckBox j05ReportCheck = new JCheckBox();
  private JPanel jPanel6 = new JPanel();
  private XYLayout xYLayout7 = new XYLayout();
  private JCheckBox j06NeedCheck = new JCheckBox();
  private JCheckBox j06VisibleCheck = new JCheckBox();
  private JLabel t061 = new JLabel();
  private JLabel t062 = new JLabel();
  private JLabel t063 = new JLabel();
  private JButton j06ImportButton1 = new JButton();
  private JTextField j06ImportText = new JTextField();
  private JCheckBox j06ReportCheck = new JCheckBox();
  private JPanel jPanel7 = new JPanel();
  private JPanel jPanel8 = new JPanel();
  private JPanel jPanel9 = new JPanel();
  private JPanel jPanel10 = new JPanel();

  //private int stepNo;
  //private String stepStr;
  //private boolean isInit;

  // for 06,08,10
  private String instruction;
  private String unit;
  private double data[];
  private String instruction08;
  private String unit08;
  private double data08[];
  private String instruction10;
  private String unit10;
  private double data10[];

  //
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JScrollPane jScrollPane2 = new JScrollPane();
  private JList j06SellaneList = new JList();
  private JList j06PolyfitList = new JList();
  private XYLayout xYLayout8 = new XYLayout();
  private TitledBorder titledBorder2;
  private JCheckBox jCheckBox1 = new JCheckBox();
  private JCheckBox jCheckBox2 = new JCheckBox();
  private JTextField j07ErrorText = new JTextField();
  private JLabel t071 = new JLabel();
  private JLabel t074 = new JLabel();
  private JLabel t072 = new JLabel();
  private JCheckBox j07NeedCheck = new JCheckBox();
  private JCheckBox j07VisibleCheck = new JCheckBox();
  private JCheckBox j07SelallCheck = new JCheckBox();
  private JCheckBox j07TreeCheck = new JCheckBox();
  private JCheckBox j07DistCheck = new JCheckBox();
  private JCheckBox j07SameCheck = new JCheckBox();
  private JScrollPane jScrollPane3 = new JScrollPane();
  private JScrollPane jScrollPane4 = new JScrollPane();
  private JList j07ModelList = new JList();
  private JList j07CofiList = new JList();
  private JLabel t073 = new JLabel();
  private XYLayout xYLayout9 = new XYLayout();
  private JCheckBox j08ReportCheck = new JCheckBox();
  private JTextField j08ImportText = new JTextField();
  private JCheckBox j08NeedCheck = new JCheckBox();
  private JCheckBox j08VisibleCheck = new JCheckBox();
  private JButton j08ImportButton1 = new JButton();
  private JLabel t081 = new JLabel();
  private JLabel j08Label10 = new JLabel();
  private JList j08SellaneList = new JList();
  private JScrollPane jScrollPane5 = new JScrollPane();
  private XYLayout xYLayout10 = new XYLayout();
  private JCheckBox j09NeedCheck = new JCheckBox();
  private JCheckBox j09VisibleCheck = new JCheckBox();
  private JLabel t091 = new JLabel();
  private JTextField j09ErrorText = new JTextField();
  private JCheckBox j09CompareallCheck = new JCheckBox();
  private JLabel t093 = new JLabel();
  private JScrollPane jScrollPane6 = new JScrollPane();
  private JList j09LaneList = new JList();
  private JCheckBox j09LineCheck = new JCheckBox();
  private JLabel t092 = new JLabel();
  private XYLayout xYLayout11 = new XYLayout();
  private JLabel j10Label10 = new JLabel();
  private JList j10SellaneList = new JList();
  private JButton j10ImportButton1 = new JButton();
  private JCheckBox j10ReportCheck = new JCheckBox();
  private JCheckBox j10NeedCheck = new JCheckBox();
  private JCheckBox j10VisibleCheck = new JCheckBox();
  private JTextField j10ImportText = new JTextField();
  private JLabel j10Label14 = new JLabel();
  private JScrollPane jScrollPane7 = new JScrollPane();
  private JButton jButton1 = new JButton();

  public DialogSet1D(Frame frame, String title, boolean modal,
                     String saveFilePathName,int showType,String scrName) {
    super(frame, title, modal);
    try {
      this.saveFilePathName=saveFilePathName;
      this.showType =showType;
      this.sourceName=scrName;
      isOK=false;
      //isInit=false;
      //stepNo=0;
      //stepStr="";
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    panel1.setLayout(xYLayout1);
    jPanel1.setLayout(xYLayout2);
    j01RecoSensitivityText.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j01RecoSensitivityText_actionPerformed(e);
      }
    });
    j01RecoSensitivityText.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        j01RecoSensitivityText_focusLost(e);
      }
    });
    j01RecoSensitivityText.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        j01RecoSensitivityText_keyPressed(e);
      }
    });
    j01RecoSensitivityText.setText("100");
    j01RecoMethod2Radio.setText("Method 2");
    j01RecoMethod1Radio.setText("Method 1");
    j01RecoMethod1Radio.setSelected(true);
    t011.setDisplayedMnemonic('0');
    t011.setHorizontalTextPosition(SwingConstants.LEFT);
    t011.setText("Sensitivity");
    t012.setText("Total number of lanes");
    j01HandLanesText.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j01HandLanesText_actionPerformed(e);
      }
    });
    j01HandLanesText.setText("10");
    t013.setText("Lane Distance");
    j01HandDistText.setText("1");
    j02NeedRadio.setText("Need correct lanes");
    j02NoNeedRadio.setSelected(true);
    j02NoNeedRadio.setText("Need not correct lanes");
    jPanel2.setLayout(xYLayout3);
    jButton2.setText("OK");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jPanel3.setLayout(xYLayout4);
    jPanel4.setLayout(xYLayout5);
    j01VisibleCheck.setForeground(Color.blue);
    j01VisibleCheck.setText("Show Interface");
    j01OptRadio.setSelected(true);
    j01OptRadio.setText("Best Search");
    j01OptRadio.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j01OptRadio_actionPerformed(e);
      }
    });
    j01RecoRadio.setText("Automatic Detection");
    j01RecoRadio.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j01RecoRadio_actionPerformed(e);
      }
    });
    j01HandRadio.setText("Detection by hand");
    j01HandRadio.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j01HandRadio_actionPerformed(e);
      }
    });
    j03MethodAvgRadio.setToolTipText("Remove background and select average value of ROI");
    j03MethodAvgRadio.setText("Average ROI");
    j03MethodAvgRadio.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        j03MethodAvgRadio_mouseClicked(e);
      }
    });
    j03MethodRollballRadio.setToolTipText("Select object to remove background after clicking");
    j03MethodRollballRadio.setText("Parameters for rolling ball");
    j03MethodRollballRadio.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        j03MethodRollballRadio_mouseClicked(e);
      }
    });
    j03RadiusText.setToolTipText("1-500");
    j03RadiusText.setText("120");
    jLabel2.setText("R of rolling ball: ");
    j03MethodMinRadio.setToolTipText("Select minmial value to remove background");
    j03MethodMinRadio.setText("Minimal ROI");
    j03MethodMinRadio.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        j03MethodMinRadio_mouseClicked(e);
      }
    });
    j03MethodNoneRadio.setSelected(true);
    j03MethodNoneRadio.setText("Not to remove background");
    j03VisibleCheck.setForeground(Color.blue);
    j03VisibleCheck.setText("Show Interface");
    j04NoiseText.setEnabled(false);
    j04NoiseText.setText("0");
    j04SlopeText.setEnabled(false);
    j04SlopeText.setText("60");
    j04HeightText.setText("50");
    j04HeightText.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j04HeightText_actionPerformed(e);
      }
    });
    j04OnlyheightCheck.setSelected(true);
    j04OnlyheightCheck.setSelected(true);
    j04OnlyheightCheck.setText("Use height");
    j04OnlyheightCheck.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j04OnlyheightCheck_actionPerformed(e);
      }
    });
    j04OnlyheightCheck.setToolTipText("Use height");
    j04WidthText.setEnabled(false);
    j04WidthText.setText("12");
    t043.setText("Noise");
    t042.setText("Slope");
    t041.setText("Width");
    jLabel4.setText("Height");
    j04VisibleCheck.setForeground(Color.blue);
    j04VisibleCheck.setText("Show Interface");
    j05OtsuRadio.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        j05OtsuRadio_stateChanged(e);
      }
    });
    j05OtsuRadio.setText("Otsu");
    j05OtsuRadio.setSelected(true);
    j05WataRadio.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        j05WataRadio_stateChanged(e);
      }
    });
    j05WataRadio.setText("Wata");
    j05SensitivityText.setText("1.0");
    j05SensitivityText.setToolTipText("Input 0~2");
    jLabel5.setText("Width of Band: ");
    j05WeszRadio.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        j05WeszRadio_stateChanged(e);
      }
    });
    j05WeszRadio.setText("Wesz");
    //j05WidthText.setEnabled(isLayer1D);
    j05WidthText.setText("20");
    jLabel1.setText("Sensitivity:");
    jPanel5.setLayout(xYLayout6);
    j05VisibleCheck.setForeground(Color.blue);
    j05VisibleCheck.setText("Show Interface");
    j05ReportCheck.setSelected(true);
    j05ReportCheck.setText("Report");
    jPanel6.setLayout(xYLayout7);
    j06NeedCheck.setForeground(Color.magenta);
    j06NeedCheck.setSelected(true);
    j06NeedCheck.setText("Need to be executed");
    j06NeedCheck.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j06NeedCheck_actionPerformed(e);
      }
    });
    j06VisibleCheck.setForeground(Color.blue);
    j06VisibleCheck.setSelected(true);
    j06VisibleCheck.setText("Show Interface");
    t061.setText("Marker Lane");
    t062.setText("fitting mode");
    t063.setText("MW/pI/length");
    j06ImportButton1.setMargin(new Insets(0, 0, 0, 0));
    j06ImportButton1.setText("import...");
    j06ImportButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j06ImportButton1_actionPerformed(e);
      }
    });
    j06ReportCheck.setText("Report");
    jPanel7.setLayout(xYLayout8);
    jCheckBox1.setText("jCheckBox1");
    jCheckBox2.setText("jCheckBox2");
    j07ErrorText.setText("3");
    t071.setText("Cluster Mode");
    t074.setText("Coefficient");
    t072.setText("Tolerance");
    j07NeedCheck.setForeground(Color.magenta);
    j07NeedCheck.setSelected(true);
    j07NeedCheck.setText("Need to be executed");
    j07NeedCheck.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j07NeedCheck_actionPerformed(e);
      }
    });
    j07VisibleCheck.setForeground(Color.blue);
    j07VisibleCheck.setToolTipText("");
    j07VisibleCheck.setSelected(true);
    j07VisibleCheck.setText("Show Interface");
    j07SelallCheck.setSelected(true);
    j07SelallCheck.setText("Refer lanes - select all");
    j07SelallCheck.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j07SelallCheck_actionPerformed(e);
      }
    });
    j07TreeCheck.setText("Show tree");
    j07DistCheck.setText("Show distance");
    j07SameCheck.setText("Show homology");
    t073.setText("%");
    jPanel8.setLayout(xYLayout9);
    j08ReportCheck.setText("Report");
    j08NeedCheck.setForeground(Color.magenta);
    j08NeedCheck.setSelected(true);
    j08NeedCheck.setText("Need to executed");
    j08NeedCheck.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j08NeedCheck_actionPerformed(e);
      }
    });
    j08VisibleCheck.setForeground(Color.blue);
    j08VisibleCheck.setSelected(true);
    j08VisibleCheck.setText("Show Interface");
    j08ImportButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j08ImportButton1_actionPerformed(e);
      }
    });
    j08ImportButton1.setText("Import...");
    j08ImportButton1.setMargin(new Insets(0, 0, 0, 0));
    t081.setText("Marker Lane");
    j08Label10.setText("MW/pI/length");
    jPanel9.setLayout(xYLayout10);
    j09NeedCheck.setForeground(Color.magenta);
    j09NeedCheck.setSelected(true);
    j09NeedCheck.setText("Need to be executed");
    j09NeedCheck.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j09NeedCheck_actionPerformed(e);
      }
    });
    j09VisibleCheck.setForeground(Color.blue);
    j09VisibleCheck.setSelected(true);
    j09VisibleCheck.setText("Show Interface");
    t091.setText("Rf");
    j09ErrorText.setText("3");
    j09CompareallCheck.setSelected(true);
    j09CompareallCheck.setText("Compare all");
    j09CompareallCheck.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j09CompareallCheck_actionPerformed(e);
      }
    });
    t093.setText("Major matching lane");
    j09LineCheck.setSelected(true);
    j09LineCheck.setText("All bands");
    j09LineCheck.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j09LineCheck_actionPerformed(e);
      }
    });
    t092.setText("%");
    jPanel10.setLayout(xYLayout11);
    j10Label10.setText("MW/pI/length");
    j10ImportButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j10ImportButton1_actionPerformed(e);
      }
    });
    j10ImportButton1.setText("import...");
    j10ImportButton1.setMargin(new Insets(0, 0, 0, 0));
    j10ReportCheck.setText("Report");
    j10NeedCheck.setForeground(Color.magenta);
    j10NeedCheck.setSelected(true);
    j10NeedCheck.setText("Need to be executed");
    j10NeedCheck.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j10NeedCheck_actionPerformed(e);
      }
    });
    j10VisibleCheck.setForeground(Color.blue);
    j10VisibleCheck.setSelected(true);
    j10VisibleCheck.setText("Show Interface");
    j10Label14.setText("Marker Lane");
    jButton1.setText("Cancel");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    panel1.setMinimumSize(new Dimension(519, 305));
    panel1.setPreferredSize(new Dimension(519, 305));
    easygel.uiti.WxsUiti.centerDialog(this,519,305);
    j06ImportText.setEditable(false);
    j08ImportText.setEditable(false);
    j10ImportText.setEditable(false);
    jPanel1.add(t011,  new XYConstraints(330, 106, 51, 24));
    jPanel1.add(j01RecoSensitivityText,  new XYConstraints(388, 110, 58, 16));
    jPanel1.add(t012, new XYConstraints(177, 152, -1, 14));
    jPanel1.add(j01HandLanesText,  new XYConstraints(245, 150, 50, 17));
    jPanel1.add(t013, new XYConstraints(330, 154, -1, 12));
    jPanel1.add(j01HandDistText,  new XYConstraints(388, 151, 58, 17));
    jPanel1.add(j01RecoMethod1Radio, new XYConstraints(176, 110, 49, 14));
    jPanel1.add(j01RecoMethod2Radio, new XYConstraints(244, 110, 49, 14));
    jPanel1.add(j01VisibleCheck,  new XYConstraints(59, 26, 75, 22));
    jPanel1.add(j01OptRadio, new XYConstraints(58, 66, -1, 19));
    jPanel1.add(j01RecoRadio, new XYConstraints(58, 108, -1, 19));
    jPanel1.add(j01HandRadio, new XYConstraints(58, 150, -1, 19));
    jTabbedPane1.add(jPanel1, "［1］Lane Detection");
    jTabbedPane1.add(jPanel2, "［2］Lane Correction");
    jPanel2.add(j02NoNeedRadio,  new XYConstraints(279, 89, -1, -1));
    jPanel2.add(j02NeedRadio, new XYConstraints(119, 89, -1, -1));
    jTabbedPane1.add(jPanel3,   "［3］Remove Background");
    jPanel3.add(j03VisibleCheck,  new XYConstraints(66, 27, 111, 22));
    jPanel3.add(j03MethodNoneRadio, new XYConstraints(66, 60, 120, 21));
    jPanel3.add(j03MethodMinRadio, new XYConstraints(66, 90, 120, 21));
    jPanel3.add(j03MethodAvgRadio, new XYConstraints(66, 119, 120, 21));
    jPanel3.add(j03MethodRollballRadio, new XYConstraints(66, 149, 120, 21));
    jPanel3.add(jLabel2, new XYConstraints(229, 149, 67, 21));
    jPanel3.add(j03RadiusText, new XYConstraints(305, 151, 123, 18));
    jTabbedPane1.add(jPanel4,  "［4］Band Detection");
    jPanel4.add(j04NoiseText,  new XYConstraints(150, 156, 61, 17));
    jPanel4.add(j04VisibleCheck, new XYConstraints(99, 24, 180, 22));
    jPanel4.add(jLabel4, new XYConstraints(100, 60, 45, 22));
    jPanel4.add(j04HeightText, new XYConstraints(150, 62, 61, 17));
    jPanel4.add(j04OnlyheightCheck, new XYConstraints(268, 57, 133, -1));
    jPanel4.add(j04WidthText, new XYConstraints(150, 94, 61, 17));
    jPanel4.add(t041, new XYConstraints(100, 91, 45, 22));
    jPanel4.add(t042, new XYConstraints(100, 123, 45, 22));
    jPanel4.add(j04SlopeText, new XYConstraints(150, 126, 61, 17));
    jPanel4.add(t043, new XYConstraints(100, 154, 45, 22));
    jTabbedPane1.add(jPanel5,  "［5］Bound Detection");
    jPanel5.add(j05SensitivityText,  new XYConstraints(209, 109, 166, 19));
    jPanel5.add(j05VisibleCheck, new XYConstraints(118, 39, 79, 24));
    jPanel5.add(jLabel5, new XYConstraints(118, 78, -1, -1));
    jPanel5.add(j05WidthText, new XYConstraints(209, 77, 166, 19));
    jPanel5.add(jLabel1, new XYConstraints(118, 113, -1, -1));
    jPanel5.add(j05ReportCheck,                                 new XYConstraints(299, 39, 79, 24));
    jPanel5.add(j05WeszRadio,  new XYConstraints(321, 143, 51, -1));
    jPanel5.add(j05OtsuRadio, new XYConstraints(126, 143, -1, -1));
    jPanel5.add(j05WataRadio, new XYConstraints(224, 143, 51, -1));
    jTabbedPane1.add(jPanel6,  "［6］MW-pI");
    jPanel6.add(j06ImportText,  new XYConstraints(275, 166, 171, 20));
    jPanel6.add(jScrollPane1,   new XYConstraints(45, 67, 170, 87));
    jPanel6.add(jScrollPane2,   new XYConstraints(277, 66, 168, 86));
    jPanel6.add(j06ImportButton1, new XYConstraints(159, 167, 55, 20));
    jPanel6.add(t063, new XYConstraints(46, 166, 85, 21));
    jPanel6.add(t061,  new XYConstraints(86, 45, 92, 19));
    jPanel6.add(t062, new XYConstraints(324, 42, 84, 26));
    jPanel6.add(j06ReportCheck,  new XYConstraints(351, 14, 70, 23));
    jPanel6.add(j06NeedCheck, new XYConstraints(65, 14, 70, 23));
    jPanel6.add(j06VisibleCheck, new XYConstraints(208, 14, 70, 23));
    jScrollPane2.getViewport().add(j06PolyfitList, null);
    jScrollPane1.getViewport().add(j06SellaneList, null);
    jTabbedPane1.add(jPanel7, "［7］Compare");
    jPanel7.add(jScrollPane4, new XYConstraints(288, 88, 166, 84));
    jPanel7.add(jScrollPane3,    new XYConstraints(48, 88, 174, 85));
    jPanel7.add(t071,                       new XYConstraints(94, 69, 76, 16));
    jPanel7.add(t074,  new XYConstraints(337, 67, 67, 20));
    jPanel7.add(j07TreeCheck,                         new XYConstraints(72, 178, 95, 21));
    jPanel7.add(j07SameCheck,                                       new XYConstraints(341, 178, 95, 21));
    jPanel7.add(j07DistCheck,     new XYConstraints(212, 178, 95, 21));
    jPanel7.add(j07VisibleCheck,  new XYConstraints(281, 12, 68, 21));
    jPanel7.add(t072,  new XYConstraints(281, 38, 54, 25));
    jPanel7.add(j07NeedCheck,     new XYConstraints(52, 12, 68, 21));
    jPanel7.add(j07SelallCheck,     new XYConstraints(52, 38, 106, 23));
    jPanel7.add(j07ErrorText,       new XYConstraints(340, 40, 69, 18));
    jPanel7.add(t073,       new XYConstraints(419, 42, 22, 17));
    jScrollPane3.getViewport().add(j07ModelList, null);
    jScrollPane4.getViewport().add(j07CofiList, null);
    jTabbedPane1.add(jPanel8, "［8］Norm");
    jPanel8.add(jScrollPane5, new XYConstraints(58, 84, 177, 87));
    jPanel8.add(t081,   new XYConstraints(102, 64, 92, 19));
    jPanel8.add(j08NeedCheck, new XYConstraints(73, 29, 70, 23));
    jPanel8.add(j08ImportText, new XYConstraints(279, 143, 128, 20));
    jPanel8.add(j08Label10, new XYConstraints(279, 84, 85, 21));
    jPanel8.add(j08ImportButton1, new XYConstraints(279, 110, 105, 20));
    jPanel8.add(j08ReportCheck,  new XYConstraints(326, 30, 70, 23));
    jPanel8.add(j08VisibleCheck,    new XYConstraints(203, 29, 70, 23));
    jScrollPane5.getViewport().add(j08SellaneList, null);
    jTabbedPane1.add(jPanel9, "［9］Compare");
    jPanel9.add(jScrollPane6, new XYConstraints(279, 80, 161, 84));
    jPanel9.add(t093,   new XYConstraints(331, 55, 67, 21));
    jPanel9.add(t092,  new XYConstraints(238, 97, 32, 20));
    jPanel9.add(t091, new XYConstraints(64, 94, 77, 29));
    jPanel9.add(j09ErrorText, new XYConstraints(174, 98, 58, 18));
    jPanel9.add(j09CompareallCheck, new XYConstraints(64, 133, 88, 21));
    jPanel9.add(j09LineCheck, new XYConstraints(176, 133, 88, 21));
    jPanel9.add(j09VisibleCheck,  new XYConstraints(176, 38, 90, 22));
    jPanel9.add(j09NeedCheck, new XYConstraints(64, 38, 90, 22));
    jScrollPane6.getViewport().add(j09LaneList, null);
    jTabbedPane1.add(jPanel10, "［10］Rectify");
    jPanel10.add(jScrollPane7,  new XYConstraints(67, 83, 167, 96));
    jPanel10.add(j10Label14,  new XYConstraints(107, 60, 92, 19));
    jPanel10.add(j10ReportCheck,      new XYConstraints(337, 28, 70, 23));
    jPanel10.add(j10NeedCheck, new XYConstraints(81, 28, 70, 23));
    jPanel10.add(j10VisibleCheck,     new XYConstraints(210, 28, 70, 23));
    jPanel10.add(j10ImportButton1,  new XYConstraints(283, 114, 100, 20));
    jPanel10.add(j10Label10,  new XYConstraints(283, 84, 85, 21));
    jPanel10.add(j10ImportText, new XYConstraints(283, 150, 128, 20));
    jScrollPane7.getViewport().add(j10SellaneList, null);
    panel1.add(jButton1,  new XYConstraints(101, 276, 88, 26));
    panel1.add(jButton2, new XYConstraints(357, 276, 88, 26));
    panel1.add(jTabbedPane1,       new XYConstraints(10, 7, 497, 259));
    buttonGroup1.add(j01RecoMethod1Radio);
    buttonGroup1.add(j01RecoMethod2Radio);
    buttonGroup1D.add(j01OptRadio);
    buttonGroup1D.add(j01RecoRadio);
    buttonGroup1D.add(j01HandRadio);
    buttonGroup2.add(j02NeedRadio);
    buttonGroup2.add(j02NoNeedRadio);
    buttonGroup3.add(j03MethodNoneRadio);
    buttonGroup3.add(j03MethodMinRadio);
    buttonGroup3.add(j03MethodAvgRadio);
    buttonGroup3.add(j03MethodRollballRadio);
    buttonGroup4.add(j05OtsuRadio);
    buttonGroup4.add(j05WataRadio);
    buttonGroup4.add(j05WeszRadio);
    this.getContentPane().add(panel1, BorderLayout.CENTER);

    // Verifier
    j01RecoSensitivityText.setInputVerifier(new Verifier(Verifier.INT,true,1,true,200,this,true));
    j01HandLanesText.setInputVerifier(new Verifier(Verifier.INT,true,1,false,0,this,true));
    j01HandDistText.setInputVerifier(new Verifier(Verifier.INT,true,0,false,0,this,true));
    j03RadiusText.setInputVerifier(new Verifier(Verifier.INT,true,1,true,1000,this,true));
    j04HeightText.setInputVerifier(new Verifier(Verifier.INT,true,1,true,100,this,true));
    j04WidthText.setInputVerifier(new Verifier(Verifier.INT,true,1,true,100,this,true));
    j04SlopeText.setInputVerifier(new Verifier(Verifier.INT,true,1,true,100,this,true));
    j04NoiseText.setInputVerifier(new Verifier(Verifier.INT,true,0,true,20,this,true));
    j05SensitivityText.setInputVerifier(new Verifier(Verifier.FLOAT,true,0,true,2,this,true));
    j05WidthText.setInputVerifier(new Verifier(Verifier.INT,true,2,true,80,this,true));
    j07ErrorText.setInputVerifier(new Verifier(Verifier.INT,true,0,true,100,this,true));
    j09ErrorText.setInputVerifier(new Verifier(Verifier.INT,true,0,true,100,this,true));

    setStaticInterface1();
    if(this.sourceName.length()!=0){
      this.readSettings(this.sourceName);
      this.setInterface();
    }
  }

  private void setStaticInterface1(){
    // 静态设置
    String [] empty=new String[0];
    String str1[]=new String[3+30];
    str1[0]="Lane No 1";
    str1[1]="Lane No 2";
    str1[2]="Lane No 3";
    str1[3]="Lane No last but 1";
    str1[4]="Lane No last but 2";
    str1[5]="Lane No last but 3";
    for(int ii=6;ii<33;ii++) str1[ii]="Lane No "+(ii-2);

    this.j06SellaneList.setListData(empty);
    this.j06SellaneList.setListData(str1);

    j08SellaneList.setListData(empty);
    j08SellaneList.setListData(str1);

    j10SellaneList.setListData(empty);
    j10SellaneList.setListData(str1);

    String str2[]=new String[30+1];
    str2[0]="Virtual Lane";
    for(int ii=1;ii<31;ii++) str2[ii]="Lane No: "+ii;
    this.j09LaneList.setListData(empty);
    this.j09LaneList.setListData(str2);

    String fitMethods[]=new String[8];
    
    fitMethods[0]="";
    fitMethods[1]="Linear Regression";
    fitMethods[2]="Quadratic Fit";
    fitMethods[3]="Cubic Fit";
    fitMethods[4]="Quartic Fit";
    fitMethods[5]="Quintic Fit";
    fitMethods[6]="Log Fit";
    fitMethods[7]="Ln Fit";
    
    this.j06PolyfitList.setListData(empty);
    this.j06PolyfitList.setListData(fitMethods);

    //
    String cofi[]=new String[4];
    cofi[0]="Nei&Li(Dice) Coefficient";
    cofi[1]="Jaccard Coefficient";
    cofi[2]="Cosine Coefficient";
    cofi[3]="Overlap Coefficient";
    this.j07CofiList.setListData(empty);
    this.j07CofiList.setListData(cofi);

    //
    String model[]=new String[8];
    model[0]="UPGMA";
    model[1]="Neighbor Joining";
    model[2]="Single Linkage";
    model[3]="Complete Linkage";
    model[4]="WPGMA";
    model[5]="Centroid";
    model[6]="Median";
    model[7]="Ward's";
    this.j07ModelList.setListData(empty);
    this.j07ModelList.setListData(model);
  }

  void j01RecoSensitivityText_actionPerformed(ActionEvent e) {

  }
  void j01RecoSensitivityText_keyTyped(KeyEvent e) {

  }
  void j01RecoSensitivityText_keyPressed(KeyEvent e) {

  }
  void j01RecoSensitivityText_keyReleased(KeyEvent e) {

  }
  void j01RecoSensitivityText_focusGained(FocusEvent e) {

  }
  void j01RecoSensitivityText_focusLost(FocusEvent e) {

  }
  void j01HandLanesText_actionPerformed(ActionEvent e) {

  }

  public void readSettings(String pathName){
    try{
      File file=new File(pathName);
      if(file.exists() ==true){
        ObjectInputStream in= new ObjectInputStream(new FileInputStream(file));
        int num=in.readInt();
        for(int ii=0;ii<num;ii++){
          Settings st=new Settings();
          st=(Settings)in.readObject();
          set.addElement(st);
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

  public void setSetV(){
    if(set==null) {;}
    else set.removeAllElements();
    this.setSetV01();
    this.setSetV02();
    this.setSetV03();
    this.setSetV04();
    this.setSetV05();
    this.setSetV06();
    this.setSetV07();
    this.setSetV08();
    this.setSetV09();
    this.setSetV10();
  }

  public void setInterface(){
    if(set==null) return;
    this.setInterface01();
    this.setInterface02();
    this.setInterface03();
    this.setInterface04();
    this.setInterface05();
    this.setInterface06();
    this.setInterface07();
    this.setInterface08();
    this.setInterface09();
    this.setInterface10();
    set01Enable();
    j04OnlyheightCheck_actionPerformed(null);
    j06NeedCheck_actionPerformed(null);
    j07NeedCheck_actionPerformed(null);
    j08NeedCheck_actionPerformed(null);
    j09NeedCheck_actionPerformed(null);
    j10NeedCheck_actionPerformed(null);
  }

  public void saveFile(String saveFile){
    this.setSetV();
    this.writeSettings(saveFile);
  }

  private void setInterface01(){
    boolean bln;
    int it;
    String str;
    // ［1］泳道识别
    this.j01VisibleCheck.setSelected(getSettings("01Visible").paraBoolean);
    str=getSettings("01Method").paraString;
    if(str.equals("01MethodOpt")) {
      this.j01OptRadio.setSelected(true);
    }
    else if(str.equals("01MethodHand")) {
      this.j01HandRadio.setSelected(true);
      this.j01HandLanesText.setText(getSettings("01MethodHandLanes").paraString);
      this.j01HandDistText.setText(getSettings("01MethodHandDist").paraString);
    }
    else {
      this.j01RecoRadio.setSelected(true);
      this.j01RecoMethod1Radio.setSelected(getSettings("01MethodRecoMethod1").paraBoolean);
      str=getSettings("01MethodRecoSen").paraString;
      this.j01RecoSensitivityText.setText(str);
    }
  }

  private void setSetV01(){
    boolean bln;
    int it;
    String str;
    Settings st;
    // ［1］泳道识别
    st=new Settings();                  st.paraBoolean=this.j01VisibleCheck.isSelected();
    st.type="01Visible";                set.addElement(st);
    if(this.j01OptRadio.isSelected()==true){
      st=new Settings();                st.paraString="01MethodOpt";
      st.type ="01Method";              set.addElement(st);
    }
    else if(this.j01RecoRadio.isSelected()==true){
      st=new Settings();                st.paraString="01MethodReco";
      st.type ="01Method";              set.addElement(st);
      st=new Settings();                st.paraBoolean=j01RecoMethod1Radio.isSelected();
      st.type ="01MethodRecoMethod1";   set.addElement(st);
      st=new Settings();                st.paraString=j01RecoSensitivityText.getText();
      st.type="01MethodRecoSen";        set.addElement(st);
    }
    else{
      st=new Settings();                st.paraString="01MethodHand";
      st.type ="01Method";              set.addElement(st);
      st=new Settings();                st.paraString=this.j01HandLanesText.getText();
      st.type ="01MethodHandLanes";     set.addElement(st);
      st=new Settings();                st.paraString=this.j01HandDistText.getText();
      st.type ="01MethodHandDist";      set.addElement(st);
    }
  }

  private void setInterface02(){
    // ［2］泳道校正
    this.j02NeedRadio.setSelected(getSettings("02Need").paraBoolean);
  }

  private void setSetV02(){
    Settings st;
    // ［2］泳道校正
    st=new Settings();                  st.paraBoolean=this.j02NeedRadio.isSelected();
    st.type="02Need";                   set.addElement(st);
  }

  private void setInterface03(){
    boolean bln;
    int it;
    String str;
    // ［3］去本底
    this.j03VisibleCheck.setSelected(getSettings("03Visible").paraBoolean);
    str=getSettings("03Method").paraString;
    if(str.equals("03MethodAvg")==true) this.j03MethodAvgRadio.setSelected(true);
    else if(str.equals("03MethodMin")==true) this.j03MethodMinRadio.setSelected(true);
    else if(str.equals("03MethodNone")==true) this.j03MethodNoneRadio.setSelected(true);
    else{
      this.j03MethodRollballRadio.setSelected(true);
      this.j03RadiusText.setText(getSettings("03MethodRollballR").paraString);
    }
  }

  private void setSetV03(){
    Settings st;
    // ［3］去本底
    st=new Settings();                st.paraBoolean=this.j03VisibleCheck.isSelected();
    st.type="03Visible";              set.addElement(st);

    st=new Settings();                st.type="03Method";
    if(this.j03MethodAvgRadio.isSelected()==true) st.paraString="03MethodAvg";
    else if(this.j03MethodMinRadio.isSelected()==true) st.paraString="03MethodMin";
    else if(this.j03MethodNoneRadio.isSelected()==true) st.paraString="03MethodNone";
    else st.paraString="03MethodRollball";
    set.addElement(st);

    if(this.j03MethodRollballRadio.isSelected()==true){
      st=new Settings();                st.paraString=this.j03RadiusText.getText();
      st.type="03MethodRollballR";      set.addElement(st);
    }
  }

  private void setInterface04(){
    boolean bln;
    int it;
    String str;
    // ［4］条带识别
    this.j04VisibleCheck.setSelected(getSettings("04Visible").paraBoolean);
    this.j04OnlyheightCheck.setSelected(getSettings("04OnlyHeight").paraBoolean);
    this.j04HeightText.setText(getSettings("04Height").paraString);
    if(getSettings("04OnlyHeight").paraBoolean==false){
      this.j04NoiseText.setEnabled(true);
      this.j04NoiseText.setText(getSettings("04Noise").paraString);
      this.j04SlopeText.setEnabled(true);
      this.j04SlopeText.setText(getSettings("04Slope").paraString);
      this.j04WidthText.setEnabled(true);
      this.j04WidthText.setText(getSettings("04Width").paraString);
    }
  }

  private void setSetV04(){
    Settings st;
    // ［4］条带识别
    st=new Settings();                st.paraBoolean=this.j04VisibleCheck.isSelected();
    st.type="04Visible";              set.addElement(st);
    st=new Settings();                st.paraBoolean=this.j04OnlyheightCheck.isSelected();
    st.type="04OnlyHeight";           set.addElement(st);
    st=new Settings();                st.paraString=this.j04HeightText.getText();
    st.type="04Height";               set.addElement(st);
    if(this.j04OnlyheightCheck.isSelected()==false){
      st=new Settings();                st.paraString=this.j04NoiseText.getText();
      st.type="04Noise";                set.addElement(st);
      st=new Settings();                st.paraString=this.j04SlopeText.getText();
      st.type="04Slope";                set.addElement(st);
      st=new Settings();                st.paraString=this.j04WidthText.getText();
      st.type="04Width";                set.addElement(st);
    }
  }

  private void setInterface05(){
    String str;
    // ［5］边界识别
    this.j05VisibleCheck.setSelected(getSettings("05Visible").paraBoolean);
    str=getSettings("05Method").paraString;
    if(str.equals("05Otsu")) this.j05OtsuRadio.setSelected(true);
    else if(str.equals("05Wata")) this.j05WataRadio.setSelected(true);
    else if(str.equals("05Wesz")) this.j05WeszRadio.setSelected(true);
    this.j05SensitivityText.setText(getSettings("05Sensitivity").paraString);
    this.j05WidthText.setText(getSettings("05Width").paraString);
    this.j05ReportCheck.setSelected(getSettings("05Report").paraBoolean);
  }

  private void setSetV05(){
    Settings st;
    // ［5］边界识别
    st=new Settings();                st.paraBoolean=this.j05VisibleCheck.isSelected();
    st.type="05Visible";              set.addElement(st);
    st=new Settings();                st.type="05Method";
    if(this.j05OtsuRadio.isSelected()==true) st.paraString="05Otsu";
    else if(this.j05WataRadio.isSelected()==true) st.paraString="05Wata";
    else st.paraString="05Wesz";
    set.addElement(st);
    st=new Settings();                st.paraString=this.j05SensitivityText.getText();
    st.type="05Sensitivity";          set.addElement(st);
    st=new Settings();                st.paraString=this.j05WidthText.getText();
    st.type="05Width";                set.addElement(st);
    st=new Settings();                st.paraBoolean=this.j05ReportCheck.isSelected();
    st.type="05Report";                set.addElement(st);
  }

  private void setInterface06(){
    boolean bln;
    int it;
    String str;
    // ［6］MW-pI
    bln=getSettings("06Need").paraBoolean;
    this.j06NeedCheck.setSelected(bln);
    if(bln==true){
      this.j06VisibleCheck.setSelected(getSettings("06Visible").paraBoolean);
      // select lane
      it=getSettings("06Sellane").paraInt;
      if(it>=7) it+=3;
      else if(it>=4 && it<=6) it=it*(-1)+3;
      this.j06SellaneList.setSelectedIndex(it-1);
      // polyfit
      it=getSettings("06Polyfit").paraInt;
      this.j06PolyfitList.setSelectedIndex(it-1);
      // import data
      this.instruction=getSettings("06Polyfit_Instruction").paraString;
      this.unit=getSettings("06Polyfit_Unit").paraString;
      str=getSettings("06Polyfit_Instruction").paraString  +
          "("+getSettings("06Polyfit_Unit").paraString +"):";
      int datalength=getSettings("06Polyfit_Datas").paraInt;
      data=new double[datalength];
      for(int ii=1;ii<=datalength ;ii++){
        str+=getSettings("06Polyfit_Data"+ii).paraDouble+" ";
        data[ii-1]=getSettings("06Polyfit_Data"+ii).paraDouble;
      }
      this.j06ImportText.setText(str);
      //
      this.j06ReportCheck.setSelected(getSettings("06Report").paraBoolean);
    }
  }

  private void setSetV06(){
    Settings st;
    int ii;
    // ［6］MW-pI
    st=new Settings();                st.paraBoolean=this.j06NeedCheck.isSelected();
    st.type="06Need";                 set.addElement(st);
    if(j06NeedCheck.isSelected()==true){
      st=new Settings();                st.paraBoolean=this.j06VisibleCheck.isSelected();
      st.type="06Visible";              set.addElement(st);
      // select lanes
      int sel=1;
      int index;
      if(this.j06SellaneList.isSelectionEmpty()==false){
        index=this.j06SellaneList.getSelectedIndex()+1;
        if(index<=3) sel=index;
        else if(index>=7) sel=index-3;
        else sel=(index-3)*(-1);
      }
      st=new Settings();                st.paraInt=sel;
      st.type="06Sellane";              set.addElement(st);
      // select polyfit method
      sel=2;
      if(this.j06PolyfitList.isSelectionEmpty()==false){
        sel=this.j06PolyfitList.getSelectedIndex()+1;
      }
      st=new Settings();                st.paraInt=sel;
      st.type="06Polyfit";              set.addElement(st);
      // set import data
      if(this.j06ImportText.getText().length()==0){
        this.instruction="Default Values of Low Protein MW";
        this.unit="kD";
        double temp[]=new double[6];
        temp[0]=10;        temp[1]=20;        temp[2]=30;
        temp[3]=40;        temp[4]=50;        temp[5]=60;
        data=temp;
      }
      st=new Settings();                      st.paraString=this.instruction;
      st.type="06Polyfit_Instruction";        set.addElement(st);
      st=new Settings();                      st.paraString=this.unit;
      st.type="06Polyfit_Unit";               set.addElement(st);
      st=new Settings();                      st.paraInt=data.length;
      st.type="06Polyfit_Datas";              set.addElement(st);
      for(int jj=1;jj<=data.length;jj++){
        st=new Settings();                      st.paraDouble=data[jj-1];
        st.type="06Polyfit_Data"+jj;            set.addElement(st);
      }
      //
      st=new Settings();                st.paraBoolean=this.j06ReportCheck.isSelected();
      st.type="06Report";               set.addElement(st);
    }
  }

  private void setInterface07(){
    boolean bln;
    int it;
    String str;
    // ［7］
    bln=getSettings("07Need").paraBoolean;
    this.j07NeedCheck.setSelected(bln);
    if(bln==true){
      this.j07VisibleCheck.setSelected(getSettings("07Visible").paraBoolean);
      this.j07SelallCheck.setSelected(getSettings("07Selall").paraBoolean);
      this.j07ErrorText.setText(getSettings("07Error").paraString);
      this.j07CofiList.setSelectedIndex(getSettings("07Cofi").paraInt);
      this.j07ModelList.setSelectedIndex(getSettings("07Model").paraInt);
      this.j07DistCheck.setSelected(getSettings("07Dist").paraBoolean);
      this.j07SameCheck.setSelected(getSettings("07Same").paraBoolean);
      this.j07TreeCheck.setSelected(getSettings("07Tree").paraBoolean);
    }
  }

  private void setSetV07(){
    Settings st;
    int ii;
    // ［7］
    st=new Settings();                st.paraBoolean=this.j07NeedCheck.isSelected();
    st.type="07Need";                 set.addElement(st);
    if(j07NeedCheck.isSelected()==true){
      st=new Settings();                st.paraBoolean=this.j07VisibleCheck.isSelected();
      st.type="07Visible";              set.addElement(st);
      st=new Settings();                st.paraBoolean=this.j07SelallCheck.isSelected();
      st.type="07Selall";               set.addElement(st);
      st=new Settings();                st.paraString=this.j07ErrorText.getText();
      st.type="07Error";                set.addElement(st);
      if(this.j07CofiList.isSelectionEmpty()==true) ii=0;
      else ii=this.j07CofiList.getSelectedIndex();
      st=new Settings();                st.paraInt=ii;
      st.type="07Cofi";                 set.addElement(st);
      if(this.j07ModelList.isSelectionEmpty()==true) ii=0;
      else ii=this.j07ModelList.getSelectedIndex();
      st=new Settings();                st.paraInt=ii;
      st.type="07Model";                set.addElement(st);
      //
      st=new Settings();                st.paraBoolean=this.j07DistCheck.isSelected();
      st.type="07Dist";                 set.addElement(st);
      st=new Settings();                st.paraBoolean=this.j07SameCheck.isSelected();
      st.type="07Same";                 set.addElement(st);
      st=new Settings();                st.paraBoolean=this.j07TreeCheck.isSelected();
      st.type="07Tree";                 set.addElement(st);
    }
  }

  private void setInterface08(){
    boolean bln;
    int it;
    String str;
    // ［8］
    bln=getSettings("08Need").paraBoolean;
    this.j08NeedCheck.setSelected(bln);
    if(bln==true){
      this.j08VisibleCheck.setSelected(getSettings("08Visible").paraBoolean);
      // select lane
      it=getSettings("08Sellane").paraInt;
      if(it>=7) it+=3;
      else if(it>=4 && it<=6) it=it*(-1)+3;
      this.j08SellaneList.setSelectedIndex(it-1);
      // import data
      this.instruction08=getSettings("08Polyfit_Instruction").paraString;
      this.unit08=getSettings("08Polyfit_Unit").paraString;
      str=getSettings("08Polyfit_Instruction").paraString  +
          "("+getSettings("08Polyfit_Unit").paraString +"):";
      int datalength=getSettings("08Polyfit_Datas").paraInt;
      data08=new double[datalength];
      for(int ii=1;ii<=datalength ;ii++){
        str+=getSettings("08Polyfit_Data"+ii).paraDouble+" ";
        data08[ii-1]=getSettings("08Polyfit_Data"+ii).paraDouble;
      }
      this.j08ImportText.setText(str);
      //
      this.j08ReportCheck.setSelected(getSettings("08Report").paraBoolean);
    }
  }

  private void setSetV08(){
    Settings st;
    int ii;
    // ［8］
    st=new Settings();                st.paraBoolean=this.j08NeedCheck.isSelected();
    st.type="08Need";                 set.addElement(st);
    if(j08NeedCheck.isSelected()==true){
      st=new Settings();                st.paraBoolean=this.j08VisibleCheck.isSelected();
      st.type="08Visible";              set.addElement(st);
      // select lanes
      int sel=1;
      int index;
      if(this.j08SellaneList.isSelectionEmpty()==false){
        index=this.j08SellaneList.getSelectedIndex()+1;
        if(index<=3) sel=index;
        else if(index>=7) sel=index-3;
        else sel=(index-3)*(-1);
      }
      st=new Settings();                st.paraInt=sel;
      st.type="08Sellane";              set.addElement(st);
      // set import data
      if(this.j08ImportText.getText().length()==0){
        this.instruction08="Default Values of Low Protein MW";
        this.unit08="kD";
        double temp[]=new double[6];
        temp[0]=20;        temp[1]=20;        temp[2]=20;
        temp[3]=20;        temp[4]=20;        temp[5]=20;
        data08=temp;
      }
      st=new Settings();                      st.paraString=this.instruction08;
      st.type="08Polyfit_Instruction";        set.addElement(st);
      st=new Settings();                      st.paraString=this.unit08;
      st.type="08Polyfit_Unit";               set.addElement(st);
      st=new Settings();                      st.paraInt=data08.length;
      st.type="08Polyfit_Datas";              set.addElement(st);
      for(int jj=1;jj<=data08.length;jj++){
        st=new Settings();                      st.paraDouble=data08[jj-1];
        st.type="08Polyfit_Data"+jj;            set.addElement(st);
      }
      //
      st=new Settings();                st.paraBoolean=this.j08ReportCheck.isSelected();
      st.type="08Report";               set.addElement(st);
    }
  }

  private void setInterface09(){
    boolean bln;
    int it;
    String str;
    // ［9］
    bln=getSettings("09Need").paraBoolean;
    this.j09NeedCheck.setSelected(bln);
    if(bln==true){
      this.j09VisibleCheck.setSelected(getSettings("09Visible").paraBoolean);
      str=getSettings("09Error").paraString;
      this.j09ErrorText.setText(str);
      boolean bln2=getSettings("09Compareall").paraBoolean;
      this.j09CompareallCheck.setSelected(bln2);
      if(bln2==false){
        it=getSettings("09Lane").paraInt;
        this.j09LaneList.setSelectedIndex(it);
        this.j09LineCheck.setSelected(getSettings("09Line").paraBoolean);
      }
    }
  }

  private void setSetV09(){
    Settings st;
    int ii;
    // ［9］
    st=new Settings();                st.paraBoolean=this.j09NeedCheck.isSelected();
    st.type="09Need";                 set.addElement(st);
    if(j09NeedCheck.isSelected()==true){
      st=new Settings();                st.paraBoolean=this.j09VisibleCheck.isSelected();
      st.type="09Visible";              set.addElement(st);
      st=new Settings();                st.paraString=this.j09ErrorText.getText();
      st.type="09Error";                set.addElement(st);
      boolean bln2=this.j09CompareallCheck.isSelected();
      st=new Settings();                st.paraBoolean=bln2;
      st.type="09Compareall";           set.addElement(st);
      if(bln2==false){
        st=new Settings();
        if(this.j09LaneList.isSelectionEmpty()==true) st.paraInt=0;
        else st.paraInt=this.j09LaneList.getSelectedIndex();
        st.type="09Lane";                 set.addElement(st);
        st=new Settings();                st.paraBoolean=this.j09LineCheck.isSelected();
        st.type="09Line";                 set.addElement(st);
      }
    }
  }

  private void setInterface10(){
    boolean bln;
    int it;
    String str;
    // ［10］
    bln=getSettings("10Need").paraBoolean;
    this.j10NeedCheck.setSelected(bln);
    if(bln==true){
      this.j10VisibleCheck.setSelected(getSettings("10Visible").paraBoolean);
      // select lane
      it=getSettings("10Sellane").paraInt;
      if(it>=7) it+=3;
      else if(it>=4 && it<=6) it=it*(-1)+3;
      this.j10SellaneList.setSelectedIndex(it-1);
      // import data
      this.instruction10=getSettings("10Polyfit_Instruction").paraString;
      this.unit10=getSettings("10Polyfit_Unit").paraString;
      str=getSettings("10Polyfit_Instruction").paraString  +
          "("+getSettings("10Polyfit_Unit").paraString +"):";
      int datalength=getSettings("10Polyfit_Datas").paraInt;
      data10=new double[datalength];
      for(int ii=1;ii<=datalength ;ii++){
        str+=getSettings("10Polyfit_Data"+ii).paraDouble+" ";
        data10[ii-1]=getSettings("10Polyfit_Data"+ii).paraDouble;
      }
      this.j10ImportText.setText(str);
      //
      this.j10ReportCheck.setSelected(getSettings("10Report").paraBoolean);
    }
  }

  private void setSetV10(){
    Settings st;
    int ii;
    // ［10］
    st=new Settings();                st.paraBoolean=this.j10NeedCheck.isSelected();
    st.type="10Need";                 set.addElement(st);
    if(j10NeedCheck.isSelected()==true){
      st=new Settings();                st.paraBoolean=this.j10VisibleCheck.isSelected();
      st.type="10Visible";              set.addElement(st);
      // select lanes
      int sel=1;
      int index;
      if(this.j10SellaneList.isSelectionEmpty()==false){
        index=this.j10SellaneList.getSelectedIndex()+1;
        if(index<=3) sel=index;
        else if(index>=7) sel=index-3;
        else sel=(index-3)*(-1);
      }
      st=new Settings();                st.paraInt=sel;
      st.type="10Sellane";              set.addElement(st);
      // set import data
      if(this.j10ImportText.getText().length()==0){
        this.instruction10="Default Values of Low Protein MW";
        this.unit10="kD";
        double temp[]=new double[6];
        temp[0]=20;        temp[1]=20;        temp[2]=20;
        temp[3]=20;        temp[4]=20;        temp[5]=20;
        data10=temp;
      }
      st=new Settings();                      st.paraString=this.instruction10;
      st.type="10Polyfit_Instruction";        set.addElement(st);
      st=new Settings();                      st.paraString=this.unit10;
      st.type="10Polyfit_Unit";               set.addElement(st);
      st=new Settings();                      st.paraInt=data10.length;
      st.type="10Polyfit_Datas";              set.addElement(st);
      for(int jj=1;jj<=data10.length;jj++){
        st=new Settings();                      st.paraDouble=data10[jj-1];
        st.type="10Polyfit_Data"+jj;            set.addElement(st);
      }
      //
      st=new Settings();                st.paraBoolean=this.j10ReportCheck.isSelected();
      st.type="10Report";               set.addElement(st);
    }
  }

  public Settings getSettings(String type){
    Settings st=new Settings();
    for(int ii=1;ii<=set.size();ii++){
      st=(Settings)set.elementAt(ii-1);
      if(st.type.equals(type)) break;
    }
    return st;
  }

  public void writeSettings(String pathName){
    int num=set.size();
    File file=new File(pathName);
    if(file.exists()==true) file.delete();
    try{
      ObjectOutputStream o= new ObjectOutputStream(
          new FileOutputStream(pathName));
      o.writeInt(num);
      for(int ii=0;ii<num;ii++) o.writeObject(set.elementAt(ii));
      o.close();
    }
    catch(Exception e3){
     e3.printStackTrace() ;
   }
  }

  public boolean getOK(){
    return isOK;
  }

  void jButton2_actionPerformed(ActionEvent e){
    isOK=true;
    if(this.showType==this.TYPE_ADD ||
       this.showType==this.TYPE_CHANGE)
      this.saveFile(this.saveFilePathName);
    this.setVisible(false);
  }
  void j03MethodAvgRadio_mouseClicked(MouseEvent e) {

  }
  void j03MethodAvgRadio_mousePressed(MouseEvent e) {

  }
  void j03MethodAvgRadio_mouseReleased(MouseEvent e) {

  }
  void j03MethodAvgRadio_mouseEntered(MouseEvent e) {

  }
  void j03MethodAvgRadio_mouseExited(MouseEvent e) {

  }
  void j03MethodRollballRadio_mouseClicked(MouseEvent e) {

  }
  void j03MethodRollballRadio_mousePressed(MouseEvent e) {

  }
  void j03MethodRollballRadio_mouseReleased(MouseEvent e) {

  }
  void j03MethodRollballRadio_mouseEntered(MouseEvent e) {

  }
  void j03MethodRollballRadio_mouseExited(MouseEvent e) {

  }
  void j03MethodMinRadio_mouseClicked(MouseEvent e) {

  }
  void j03MethodMinRadio_mousePressed(MouseEvent e) {

  }
  void j03MethodMinRadio_mouseReleased(MouseEvent e) {

  }
  void j03MethodMinRadio_mouseEntered(MouseEvent e) {

  }
  void j03MethodMinRadio_mouseExited(MouseEvent e) {

  }
  void j04HeightText_actionPerformed(ActionEvent e) {

  }
  void j04OnlyheightCheck_actionPerformed(ActionEvent e) {
    if(this.j04OnlyheightCheck.isSelected()==true){
      this.j04NoiseText.setEnabled(false);
      this.j04SlopeText.setEnabled(false);
      this.j04WidthText.setEnabled(false);
      this.t041.setEnabled(false);
      this.t042.setEnabled(false);
      this.t043.setEnabled(false);
    }
    else{
      this.j04NoiseText.setEnabled(true);
      this.j04SlopeText.setEnabled(true);
      this.j04WidthText.setEnabled(true);
      this.t041.setEnabled(true);
      this.t042.setEnabled(true);
      this.t043.setEnabled(true);
    }
  }
  void j05OtsuRadio_stateChanged(ChangeEvent e) {

  }
  void j05WataRadio_stateChanged(ChangeEvent e) {

  }
  void j05WeszRadio_stateChanged(ChangeEvent e) {

  }

  void j06ImportButton1_actionPerformed(ActionEvent e) {
    DialogStdPhy di=new DialogStdPhy(null,"Select Standard",true);
    di.show();
    if(di.getInstrution() ==null)return;
    this.instruction =di.getInstrution() ;
    this.unit =di.getUnit() ;
    this.data =di.getData() ;
    String str;
    str=this.instruction +"("+this.unit +"):";
    for(int ii=1;ii<=data.length ;ii++) str+=this.data [ii-1]+" ";
    this.j06ImportText.setText(str);
  }

  void j07SelallCheck_actionPerformed(ActionEvent e) {
    if(j07SelallCheck.isSelected()==false)
      this.j07VisibleCheck.setSelected(true);
  }

  void j08ImportButton1_actionPerformed(ActionEvent e) {
    DialogStdPhy di=new DialogStdPhy(null,"Select Standard",true);
    di.show();
    if(di.getInstrution() ==null)return;
    this.instruction =di.getInstrution() ;
    this.unit =di.getUnit() ;
    this.data =di.getData() ;
    String str;
    str=this.instruction +"("+this.unit +"):";
    for(int ii=1;ii<=data.length ;ii++) str+=this.data [ii-1]+" ";
    this.j08ImportText.setText(str);
  }

  void j09LineCheck_actionPerformed(ActionEvent e) {
    if(this.j09LineCheck.isSelected()==false
       && this.j09CompareallCheck.isSelected()==false)
      this.j09VisibleCheck.setSelected(true);
  }

  void j09NeedCheck_actionPerformed(ActionEvent e) {
    boolean bln=this.j09NeedCheck.isSelected();
    this.j09CompareallCheck.setEnabled(bln);
    this.j09ErrorText.setEnabled(bln);
    this.j09VisibleCheck.setEnabled(bln);
    //j09CompareallCheck_actionPerformed(null);
    this.t091.setEnabled(bln);
    this.t092.setEnabled(bln);
    this.t093.setEnabled(!bln);
    this.j09LaneList.setEnabled(!bln);
    this.j09LineCheck.setEnabled(!bln);
  }

  void j09CompareallCheck_actionPerformed(ActionEvent e) {
    boolean bln=this.j09CompareallCheck.isSelected();
    bln=!bln;
    this.j09LaneList.setEnabled(bln);
    this.j09LineCheck.setEnabled(bln);
    this.t093.setEnabled(bln);
  }

  void j01RecoRadio_actionPerformed(ActionEvent e) {
    set01Enable();
  }

  void j01HandRadio_actionPerformed(ActionEvent e) {
    set01Enable();
  }

  private void set01Enable(){
    boolean bln;
    if(this.j01OptRadio.isSelected()==true){
      bln=false;
      this.j01RecoMethod1Radio.setEnabled(bln);
      this.j01RecoMethod2Radio.setEnabled(bln);
      this.j01RecoSensitivityText.setEnabled(bln);
      this.j01HandLanesText.setEnabled(bln);
      this.j01HandDistText.setEnabled(bln);
      this.t011.setEnabled(bln);
      this.t012.setEnabled(bln);
      this.t013.setEnabled(bln);
    }
    else if(this.j01HandRadio.isSelected()==true){
      bln=false;
      this.j01RecoMethod1Radio.setEnabled(bln);
      this.j01RecoMethod2Radio.setEnabled(bln);
      this.j01RecoSensitivityText.setEnabled(bln);
      this.t011.setEnabled(bln);
      bln=true;
      this.j01HandLanesText.setEnabled(bln);
      this.j01HandDistText.setEnabled(bln);
      this.t012.setEnabled(bln);
      this.t013.setEnabled(bln);
    }
    else{
      bln=true;
      this.j01RecoMethod1Radio.setEnabled(bln);
      this.j01RecoMethod2Radio.setEnabled(bln);
      this.j01RecoSensitivityText.setEnabled(bln);
      this.t011.setEnabled(bln);
      bln=false;
      this.j01HandLanesText.setEnabled(bln);
      this.j01HandDistText.setEnabled(bln);
      this.t012.setEnabled(bln);
      this.t013.setEnabled(bln);
    }
  }

  void j06NeedCheck_actionPerformed(ActionEvent e) {
    boolean bln=this.j06NeedCheck.isSelected();
    this.j06ImportButton1.setEnabled(bln);
    this.j06ImportText.setEnabled(bln);
    this.j06PolyfitList.setEnabled(bln);
    this.j06ReportCheck.setEnabled(bln);
    this.j06SellaneList.setEnabled(bln);
    this.j06VisibleCheck.setEnabled(bln);
    this.t061.setEnabled(bln);
    this.t062.setEnabled(bln);
    this.t063.setEnabled(bln);
  }

  void j07NeedCheck_actionPerformed(ActionEvent e) {
    boolean bln=this.j07NeedCheck.isSelected();
    this.j07CofiList.setEnabled(bln);
    this.j07DistCheck.setEnabled(bln);
    this.j07ErrorText.setEnabled(bln);
    this.j07ModelList.setEnabled(bln);
    this.j07SameCheck.setEnabled(bln);
    this.j07SelallCheck.setEnabled(bln);
    this.j07TreeCheck.setEnabled(bln);
    this.j07VisibleCheck.setEnabled(bln);
    this.t071.setEnabled(bln);
    this.t072.setEnabled(bln);
    this.t073.setEnabled(bln);
    this.t074.setEnabled(bln);
  }

  void j08NeedCheck_actionPerformed(ActionEvent e) {
    boolean bln=this.j08NeedCheck.isSelected();
    this.j08ImportButton1.setEnabled(bln);
    this.j08ImportText.setEnabled(bln);
    this.j08ReportCheck.setEnabled(bln);
    this.j08SellaneList.setEnabled(bln);
    this.j08VisibleCheck.setEnabled(bln);
    this.j08Label10.setEnabled(bln);
    this.t081.setEnabled(bln);
  }

  void j10ImportButton1_actionPerformed(ActionEvent e) {
    DialogStdPhy di=new DialogStdPhy(null,"Select Standard",true);
    di.show();
    if(di.getInstrution() ==null)return;
    this.instruction10 =di.getInstrution() ;
    this.unit10 =di.getUnit() ;
    this.data10 =di.getData() ;
    String str;
    str=this.instruction10 +"("+this.unit10 +"):";
    for(int ii=1;ii<=data10.length ;ii++) str+=this.data10[ii-1]+" ";
    this.j10ImportText.setText(str);
  }

  void j10NeedCheck_actionPerformed(ActionEvent e) {
    boolean bln=this.j10NeedCheck.isSelected();
    this.j10ImportButton1.setEnabled(bln);
    this.j10ImportText.setEnabled(bln);
    this.j10ReportCheck.setEnabled(bln);
    this.j10SellaneList.setEnabled(bln);
    this.j10VisibleCheck.setEnabled(bln);
    this.j10Label10.setEnabled(bln);
    this.j10Label14.setEnabled(bln);
  }

  void jButton1_actionPerformed(ActionEvent e) {
    this.isOK =false;
    this.setVisible(false);
  }

  void j01OptRadio_actionPerformed(ActionEvent e) {
    set01Enable();
  }
}
