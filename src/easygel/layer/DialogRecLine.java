package easygel.layer;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import easygel.*;
import javax.swing.border.*;
// added by wxs 20030823 -01
import easygel.setting.*;
import java.io.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogRecLine extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JScrollBar jScrollBarwavew = new JScrollBar();
  private JTextField j04HeightText = new JTextField();
  private JPanel jPanel1 = new JPanel();
  private JTextField j04SlopeText = new JTextField();
  private JScrollBar jScrollBarwaveh = new JScrollBar();
  private JScrollBar jScrollBarslope = new JScrollBar();
  private JScrollBar jScrollBarnoise = new JScrollBar();
  private JLabel jLabel8 = new JLabel();
  private JLabel jLabel7 = new JLabel();
  private JTextField j04WidthText = new JTextField();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private XYLayout xYLayout2 = new XYLayout();
  private JTextField j04NoiseText = new JTextField();
  private JButton jButton1 = new JButton();
  private JButton jButtoninsline = new JButton();
  private JButton jButtondelline = new JButton();
  private FrameMain frameMain;
  private JButton jButton2 = new JButton();
  private JButton jButton5 = new JButton();
  private TitledBorder titledBorder1;
  private TitledBorder titledBorder2;
  private TitledBorder titledBorder3;
  private XYLayout xYLayout3 = new XYLayout();
  private JButton jButton6 = new JButton();
  private JCheckBox j04OnlyheightCheck = new JCheckBox();

  // added by wxs 20030823 -02
  private Vector set=new Vector();
  private boolean isVisible;
  private boolean isInit;

  public DialogRecLine(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    // added by wxs 20030823 -03
    this.isInit=true;
    try {
      frameMain=(FrameMain)frame;
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogRecLine() {
    this(null, "", false);
  }

  private void jbInit() throws Exception {
    j04WidthText.setEnabled(false);
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    panel1.setLayout(xYLayout1);
    jScrollBarwavew.setMinimum(1);
    jScrollBarwavew.setOrientation(JScrollBar.HORIZONTAL);
    jScrollBarwavew.setMaximum(100);
    jScrollBarwavew.setUnitIncrement(1);
    jScrollBarwavew.setValue(12);
    jScrollBarwavew.setVisibleAmount(0);
    jScrollBarwavew.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollBarwavew_adjustmentValueChanged(e);
      }
    });
    j04HeightText.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j04HeightText_actionPerformed(e);
      }
    });
    j04HeightText.setText("50");
    jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
    jPanel1.setToolTipText("Please select two points with mouse");
    jPanel1.setLayout(xYLayout2);
    j04SlopeText.setText("60");
    j04SlopeText.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        j04SlopeText_focusLost(e);
      }
    });
    j04SlopeText.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j04SlopeText_actionPerformed(e);
      }
    });
    jScrollBarwaveh.setMinimum(1);
    jScrollBarwaveh.setOrientation(JScrollBar.HORIZONTAL);
    jScrollBarwaveh.setMaximum(100);
    jScrollBarwaveh.setUnitIncrement(1);
    jScrollBarwaveh.setValue(60);
    jScrollBarwaveh.setBackground(Color.white);
    jScrollBarwaveh.setForeground(Color.red);
    jScrollBarwaveh.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollBarwaveh_adjustmentValueChanged(e);
      }
    });
    jScrollBarwaveh.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollBarwaveh_adjustmentValueChanged(e);
      }
    });
    jScrollBarslope.setMinimum(1);
    jScrollBarslope.setOrientation(JScrollBar.HORIZONTAL);
    jScrollBarslope.setMaximum(100);
    jScrollBarslope.setUnitIncrement(1);
    jScrollBarslope.setValue(60);
    jScrollBarslope.setVisibleAmount(0);
    jScrollBarslope.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollBarslope_adjustmentValueChanged(e);
      }
    });
    jScrollBarnoise.setMinimum(0);
    jScrollBarnoise.setOrientation(JScrollBar.HORIZONTAL);
    jScrollBarnoise.setMaximum(20);
    jScrollBarnoise.setUnitIncrement(1);
    jScrollBarnoise.setValue(2);
    jScrollBarnoise.setVisibleAmount(0);
    jScrollBarnoise.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollBarnoise_adjustmentValueChanged(e);
      }
    });
    jLabel8.setText("noise");
    jLabel7.setText("slope");
    j04WidthText.setText("12");
    j04WidthText.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        j04WidthText_focusLost(e);
      }
    });
    j04WidthText.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j04WidthText_actionPerformed(e);
      }
    });
    jLabel3.setText("width");
    jLabel2.setText("height");
    j04NoiseText.setToolTipText("");
    j04NoiseText.setText("0");
    j04NoiseText.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        j04NoiseText_focusLost(e);
      }
    });
    j04NoiseText.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j04NoiseText_actionPerformed(e);
      }
    });
    jButton1.setForeground(Color.blue);
    jButton1.setMargin(new Insets(0, 0, 0, 0));
    jButton1.setText("Bands Detection");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButtoninsline.setMargin(new Insets(0, 0, 0, 0));
    jButtoninsline.setText("Insert Band");
    jButtoninsline.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtoninsline_actionPerformed(e);
      }
    });
    jButtondelline.setMargin(new Insets(0, 0, 0, 0));
    jButtondelline.setText("Delete Band");
    jButtondelline.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtondelline_actionPerformed(e);
      }
    });
    jButton2.setMargin(new Insets(0, 0, 0, 0));
    jButton2.setText("OK");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    //this.setTitle("");
    jButton5.setMargin(new Insets(0, 0, 0, 0));
    jButton5.setText("Reset Parameters");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton5_actionPerformed(e);
      }
    });
    this.getContentPane().setLayout(xYLayout3);
    panel1.setMinimumSize(new Dimension(442, 265));
    panel1.setPreferredSize(new Dimension(442, 265));
    jButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton6_actionPerformed(e);
      }
    });
    jButton6.setText("Cancle");
    jButton6.setMargin(new Insets(0, 0, 0, 0));
    jButton6.setPreferredSize(new Dimension(43, 25));
    jButton6.setMinimumSize(new Dimension(43, 25));
    j04OnlyheightCheck.setToolTipText("Use height only");
    j04OnlyheightCheck.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j04OnlyheightCheck_actionPerformed(e);
      }
    });
    j04OnlyheightCheck.setSelected(true);
    jPanel1.add(jButtoninsline,  new XYConstraints(121, 146, 72, 23));
    jPanel1.add(jButtondelline, new XYConstraints(217, 146, 72, 23));
    jPanel1.add(jButton5, new XYConstraints(312, 146, 72, 23));
    jPanel1.add(jButton1, new XYConstraints(26, 146, 72, 23));
    jPanel1.add(jLabel8,  new XYConstraints(31, 110, 45, 22));
    jPanel1.add(j04NoiseText, new XYConstraints(67, 111, 61, 17));
    jPanel1.add(jScrollBarnoise,  new XYConstraints(138, 111, 206, 17));
    jPanel1.add(jLabel2, new XYConstraints(31, 16, 45, 22));
    jPanel1.add(j04HeightText, new XYConstraints(67, 17, 61, 17));
    jPanel1.add(jScrollBarwaveh,  new XYConstraints(138, 16, 206, 17));
    jPanel1.add(jLabel3, new XYConstraints(31, 47, 45, 22));
    jPanel1.add(j04WidthText, new XYConstraints(67, 49, 61, 17));
    jPanel1.add(jScrollBarwavew,  new XYConstraints(138, 48, 206, 17));
    jPanel1.add(jLabel7, new XYConstraints(31, 79, 45, 22));
    jPanel1.add(j04SlopeText, new XYConstraints(67, 81, 61, 17));
    jPanel1.add(jScrollBarslope,  new XYConstraints(138, 80, 206, 17));
    jPanel1.add(j04OnlyheightCheck,                          new XYConstraints(361, 10, 16, 26));
    panel1.add(jButton6,     new XYConstraints(90, 232, 73, 25));
    panel1.add(jButton2,  new XYConstraints(273, 232, 73, 25));
    panel1.add(jPanel1,   new XYConstraints(22, 20, 399, 193));

    // Verifier
    j04HeightText.setInputVerifier(new Verifier(Verifier.INT,true,1,true,100,this,true));
    j04WidthText.setInputVerifier(new Verifier(Verifier.INT,true,1,true,100,this,true));
    j04SlopeText.setInputVerifier(new Verifier(Verifier.INT,true,1,true,100,this,true));
    j04NoiseText.setInputVerifier(new Verifier(Verifier.INT,true,0,true,20,this,true));

    this.getContentPane().add(panel1,  new XYConstraints(0, 0, -1, -1));
    if(this.frameMain .getExeMethod() ==0){
      this.jButton2 .setText("OK");
      this.jButton6 .setText("Cancel");
    }
    else{
     this.setLocation(this.frameMain .getWizardWinLocation()) ;
     this.jButton2 .setText("Next▼");
     this.jButton6 .setText("Last▲") ;
    }
    this.j04OnlyheightCheck.setSelected(true);
    setInterface();

    // added by wxs 20030823 -04
    this.setInterfaceVFromFile();
    this.setInterfaceFromV();

    // added by wxs 20030823 -05
    if(this.frameMain.getExeMethod()==1) this.isVisible=true;
    this.setVisible(this.isVisible);
    this.isInit=false;

    // added by wxs 20030823 -06
    this.doExeMethodEasyKey();
    jScrollBarwaveh.setVisibleAmount(0);
    jScrollBarwavew.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollBarwavew_adjustmentValueChanged(e);
      }
    });
    jScrollBarslope.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollBarslope_adjustmentValueChanged(e);
      }
    });
    j04HeightText.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        j04HeightText_focusLost(e);
      }
    });
  }

  private void setInterface(){
    if(this.j04OnlyheightCheck.isSelected()==true){
      this.jScrollBarnoise.setEnabled(false);
      this.jScrollBarwavew.setEnabled(false);
      this.jScrollBarslope.setEnabled(false);
      this.j04NoiseText.setEnabled(false);
      this.j04SlopeText.setEnabled(false);
      this.j04WidthText.setEnabled(false);
    }
    else{
      this.jScrollBarnoise.setEnabled(true);
      this.jScrollBarwavew.setEnabled(true);
      this.jScrollBarslope.setEnabled(true);
      this.j04NoiseText.setEnabled(true);
      this.j04SlopeText.setEnabled(true);
      this.j04WidthText.setEnabled(true);
    }
  }

  void jScrollBarwavew_adjustmentValueChanged(AdjustmentEvent e) {
    j04WidthText.setText(Integer.toString(jScrollBarwavew.getValue()));
    recoLine();
  }
  void j04HeightText_actionPerformed(ActionEvent e) {

  }
  void j04SlopeText_actionPerformed(ActionEvent e) {

  }

  void jScrollBarwaveh_adjustmentValueChanged(AdjustmentEvent e){
    if(this.isInit==true) return;
    j04HeightText.setText(Integer.toString(this.jScrollBarwaveh.getValue()));
    recoLine();
  }

  void jScrollBarslope_adjustmentValueChanged(AdjustmentEvent e) {
     this.j04SlopeText.setText(Integer.toString(this.jScrollBarslope.getValue()));
     recoLine();
  }
  void jScrollBarnoise_adjustmentValueChanged(AdjustmentEvent e) {
   this.j04NoiseText.setText(Integer.toString(jScrollBarnoise.getValue()));
   recoLine();
  }
  void jTextFieldsensi_focusGained(FocusEvent e) {

  }
  void jTextFieldsensi_keyTyped(KeyEvent e) {

  }
  void jTextFieldsensi_keyReleased(KeyEvent e) {

  }
  void j04WidthText_actionPerformed(ActionEvent e) {

  }
  void j04NoiseText_actionPerformed(ActionEvent e) {

  }

  void jButtoninsline_actionPerformed(ActionEvent e) {
    if(frameMain.getCurrentImage().getLayer1D().current.size() <4){
      return;
    }
    String cmm;
    cmm=this.frameMain.cmmStatus;
    cmm=this.frameMain.cmmStatus;
    this.frameMain.cmmStatus="insert line";
    this.frameMain.getCurrentImage().mySetCursor(true,DialogImage.CURADD);
  }

  void jButtondelline_actionPerformed(ActionEvent e) {
    if(frameMain.getCurrentImage().getLayer1D().current.size() <4){
      return;
    }
    String cmm;
    cmm=this.frameMain.cmmStatus;
    if(this.jButtondelline.getText()=="Delete Band") {
      cmm=this.frameMain.cmmStatus;
      this.frameMain.cmmStatus="delete line";
      this.frameMain.getCurrentImage().mySetCursor(true,DialogImage.CURDELETE);
    }
  }

  void jButton1_actionPerformed(ActionEvent e) {
    recoLine();
  }

  private void recoLine(){
    /*
    int s=frameMain.getCurrentImage().getLayer1D().current.size();
    for(int ii=1;ii<=s;ii++){
      Info1D id=(Info1D)frameMain.getCurrentImage().getLayer1D().current.elementAt(ii-1);
      System.out.println("No."+ii+" : "+id.toString());
    }
    */
    if(frameMain.getCurrentImage().getLayer1D().current.size() <4){
      return;
    }
    String str="⊿ Band Detection--";
    int waveh=jScrollBarwaveh.getValue();
    if(this.j04OnlyheightCheck.isSelected()==false){
      frameMain.getCurrentImage().getLayer1D().getline(
          jScrollBarwavew.getValue(),
          waveh,
          jScrollBarslope.getValue(),
          jScrollBarnoise.getValue());
      str+="［Height="+this.j04HeightText.getText()+"］";
      str+="［Width="+this.j04WidthText.getText()+"］";
      str+="［Slope="+this.j04SlopeText.getText()+"］";
      str+="［Noise="+this.j04NoiseText.getText()+"］";
    }
    else{
      str+="［Height="+this.j04HeightText.getText()+"］";
      frameMain.getCurrentImage().getLayer1D().getlineH(jScrollBarwaveh.getValue());
    }
    this.frameMain.getCurrentImage().setDoMethod(4,str);
    frameMain.getCurrentImage().paintImage();
  }

  void jButton2_actionPerformed(ActionEvent e) {
    this.dispose() ;
    if(this.frameMain .getExeMethod() >=1){
      this.frameMain .setWizardWinLocation(this.getLocation() ) ;
      this.frameMain.doStep(true);
    }
  }

  void jButton5_actionPerformed(ActionEvent e) {
    jScrollBarwaveh.setValue(50);
    j04HeightText.setText(Integer.toString(this.jScrollBarwaveh.getValue()));
    jScrollBarwavew.setValue(12);
    j04WidthText.setText(Integer.toString(jScrollBarwavew.getValue()));
    jScrollBarslope.setValue(60);
    j04SlopeText.setText(Integer.toString(this.jScrollBarslope.getValue()));
    jScrollBarnoise.setValue(0);
    j04NoiseText.setText(Integer.toString(jScrollBarnoise.getValue()));
  }

  void jButton3_actionPerformed(ActionEvent e) {
    this.frameMain.getCurrentImage().getLayer1D().getvirtuallane(10);
    this.frameMain.getCurrentImage().getContentPane().repaint();
  }

  void jButton6_actionPerformed(ActionEvent e) {
    this.dispose() ;
    if(this.frameMain .getExeMethod() >=1){
      this.frameMain .setWizardWinLocation(this.getLocation() ) ;
      this.frameMain.doStep(false);
    }
  }

  void j04OnlyheightCheck_actionPerformed(ActionEvent e) {
    this.setInterface();
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
          if(st.type.substring(0,2).equals("04"))  set.addElement(st);
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
    int exeMethod=frameMain.getExeMethod();
    if(exeMethod==2)  isVisible =getSettings("04Visible").paraBoolean;
    else isVisible=true;
    this.j04OnlyheightCheck.setSelected(getSettings("04OnlyHeight").paraBoolean);
    String heightV=getSettings("04Height").paraString;
    this.j04HeightText.setText(heightV);
    this.jScrollBarwaveh.setValue(Integer.parseInt(heightV));
    if(getSettings("04OnlyHeight").paraBoolean==false){
      this.j04NoiseText.setEnabled(true);
      this.j04NoiseText.setText(getSettings("04Noise").paraString);
      this.jScrollBarnoise.setValue(Integer.parseInt(getSettings("04Noise").paraString));
      this.j04SlopeText.setEnabled(true);
      this.j04SlopeText.setText(getSettings("04Slope").paraString);
      this.jScrollBarslope.setValue(Integer.parseInt(getSettings("04Slope").paraString));
      this.j04WidthText.setEnabled(true);
      this.j04WidthText.setText(getSettings("04Width").paraString);
      this.jScrollBarwavew.setValue(Integer.parseInt(getSettings("04Width").paraString));
    }
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
    this.frameMain.getCurrentImage().setTitle("Band Detection......");
    recoLine();
    this.frameMain.getCurrentImage().setTitle("Done");
    // donext
    this.frameMain.setWizardWinLocation(this.getLocation() ) ;
    this.frameMain.doStep(true)  ;
  }

  void j04HeightText_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(j04HeightText.getText());
      this.jScrollBarwaveh.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void j04WidthText_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(j04WidthText.getText());
      this.jScrollBarwavew.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void j04SlopeText_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(j04SlopeText.getText());
      this.jScrollBarslope.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

  void j04NoiseText_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(j04NoiseText.getText());
      this.jScrollBarnoise.setValue(tmp);
    }
    catch(NumberFormatException e2){

    }
  }

}
