package easygel.layer;

// whole package need change to English

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.awt.event.*;
import easygel.*;
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

//LiuSheng  2003- 03-22

public class DialogControl1D extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JButton manulRec = new JButton();
  private JLabel sensitivitylabel = new JLabel();
  private JTextField j01RecoSensitivityText = new JTextField();
  private TitledBorder titledBorder1;
  private JPanel jPanel1 = new JPanel();
  private XYLayout xYLayout2 = new XYLayout();
  private TitledBorder titledBorder2;
  private JPanel jPanel3 = new JPanel();
  private XYLayout xYLayout4 = new XYLayout();
  private TitledBorder titledBorder3;
  private JButton jButton7 = new JButton();
  private JButton jButton8 = new JButton();
  private FrameMain frameMain;
  private boolean rereclane;
  private JScrollBar j01RecoSenBar = new JScrollBar();
  private JScrollBar jScrollBar4 = new JScrollBar();
  private String cmm;
  private boolean autorec;
  private boolean rerecline;
  private JButton autoRec = new JButton();
  private JTextField j01HandLanesText = new JTextField();
  private JTextField j01HandDistText = new JTextField();
  private JLabel jLabellanegap = new JLabel();
  private JLabel jLabellanetital = new JLabel();
  private JPanel jPanel2 = new JPanel();
  private TitledBorder titledBorder4;
  public JComboBox jComboBoxlane = new JComboBox();
  private JButton jButtonInslane = new JButton();
  private JButton jButtondellane = new JButton();
  private JButton jButton2 = new JButton();
  private JLabel jLabel1 = new JLabel();
  private JButton jButton6 = new JButton();
  private XYLayout xYLayout3 = new XYLayout();
  private TitledBorder titledBorder5;
  private TitledBorder titledBorder6;
  private TitledBorder titledBorder7;
  private JRadioButton j01RecoMethod1Radio = new JRadioButton();
  private JRadioButton jRadioButton2 = new JRadioButton();
  private ButtonGroup buttonGroup=new ButtonGroup();
  private JButton jButton1 = new JButton();

  // added by wxs 20030823 -02
  private Vector set=new Vector();
  private boolean isVisible;
  private boolean isInit;

  public DialogControl1D(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    this.frameMain =(FrameMain)frame;
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

  public DialogControl1D() {
    this(null, "", false);
  }

  public int getSensitivitylabel(){
    String sens;
    sens=this.j01RecoSensitivityText.getText() ;
    return  Integer.parseInt(sens);
  }

  public void setSensitivitylabel(int sens){
    this.j01RecoSensitivityText.setText(Integer.toString(sens));
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    titledBorder4 = new TitledBorder("");
    titledBorder5 = new TitledBorder("");
    titledBorder6 = new TitledBorder("");
    titledBorder7 = new TitledBorder("");
    panel1.setLayout(xYLayout1);
    manulRec.setForeground(Color.blue);
    manulRec.setHorizontalAlignment(SwingConstants.LEFT);
    manulRec.setHorizontalTextPosition(SwingConstants.LEFT);
    manulRec.setText("Detection");
    manulRec.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        manulRec_actionPerformed(e);
      }
    });
    sensitivitylabel.setDisplayedMnemonic('0');
    sensitivitylabel.setHorizontalTextPosition(SwingConstants.LEFT);
    sensitivitylabel.setText("Sensitivity: ");
    //Liusheng add
    this.rereclane=true;
    j01RecoSenBar.setOrientation(JScrollBar.HORIZONTAL);
    this.j01RecoSenBar.setMaximum(200);
    j01RecoSenBar.setMinimum(1);
    this.j01RecoSenBar.setValue(100);
    j01RecoSenBar.setVisibleAmount(0);
    autorec=true;
    rerecline=true;
    //end
    jPanel1.setBorder(titledBorder6);
    jPanel1.setToolTipText("");
    jPanel1.setLayout(xYLayout2);
    jPanel3.setLayout(xYLayout4);
    jPanel3.setBorder(titledBorder3);
    jButton7.setMinimumSize(new Dimension(43, 25));
    jButton7.setPreferredSize(new Dimension(43, 25));
    jButton7.setMargin(new Insets(0, 0, 0, 0));
    jButton7.setText("OK");
    jButton7.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Ok_actionPerformed(e);
      }
    });
    jButton8.setMargin(new Insets(0, 0, 0, 0));
    jButton8.setText("Cancel");
    jButton8.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton8_actionPerformed(e);
      }
    });
    this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        this_mouseMoved(e);
      }
    });
    this.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        this_mousePressed(e);
      }
      public void mouseReleased(MouseEvent e) {
        this_mouseReleased(e);
      }
    });
    j01RecoSensitivityText.setText("100");
    j01RecoSensitivityText.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        j01RecoSensitivityText_keyPressed(e);
      }
    });
    j01RecoSensitivityText.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        j01RecoSensitivityText_focusLost(e);
      }
    });
    j01RecoSensitivityText.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j01RecoSensitivityText_actionPerformed(e);
      }
    });
    j01RecoSenBar.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        j01RecoSenBar_adjustmentValueChanged(e);
      }
    });
    autoRec.setForeground(Color.blue);
    autoRec.setHorizontalAlignment(SwingConstants.LEFT);
    autoRec.setHorizontalTextPosition(SwingConstants.LEFT);
    autoRec.setText("Automatic Detection");
    autoRec.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        autoRec_actionPerformed(e);
      }
    });
    jLabellanegap.setText("Lane Distance");
    jLabellanetital.setText("Total number lanes");
    jPanel2.setBorder(titledBorder5);
    jPanel2.setLayout(xYLayout3);
    jComboBoxlane.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jComboBoxlane_actionPerformed(e);
      }
    });
    jButtonInslane.setText("Insert Lane");
    jButtonInslane.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonInslane_actionPerformed(e);
      }
    });
    jButtondellane.setToolTipText("");
    jButtondellane.setText("Delete Lane");
    jButtondellane.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtondellane_actionPerformed(e);
      }
    });
    jButton2.setText("Move Lane");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jLabel1.setText("Select Lane");
    jButton6.setForeground(Color.blue);
    jButton6.setText("Modify Lane");
    jButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton6_actionPerformed(e);
      }
    });
    j01HandLanesText.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j01HandLanesText_actionPerformed(e);
      }
    });
    panel1.setMinimumSize(new Dimension(442, 265));
    panel1.setPreferredSize(new Dimension(442, 265));
    j01HandLanesText.setText("10");
    j01HandDistText.setText("1");
    j01RecoMethod1Radio.setSelected(true);
    j01RecoMethod1Radio.setText("Method 1");
    jRadioButton2.setText("Method 2");
    jButton1.setForeground(Color.magenta);
    jButton1.setText("Search");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jPanel1.add(j01RecoSenBar, new XYConstraints(103, 41, 185, -1));
    j01RecoSenBar.add(jScrollBar4, null);
    jPanel1.add(j01RecoSensitivityText,   new XYConstraints(55, 41, 41, 16));
    jPanel1.add(autoRec, new XYConstraints(297, 38, 85, 23));
    jPanel1.add(sensitivitylabel,   new XYConstraints(2, 36, 51, 24));
    jPanel1.add(jButton1,      new XYConstraints(297, 4, 85, 23));
    jPanel1.add(j01RecoMethod1Radio, new XYConstraints(65, 8, 49, 14));
    jPanel1.add(jRadioButton2, new XYConstraints(177, 8, 49, 14));
    panel1.add(jPanel3,           new XYConstraints(23, 98, 397, 38));
    jPanel3.add(jLabellanetital,   new XYConstraints(15, 6, -1, 14));
    jPanel3.add(j01HandLanesText,     new XYConstraints(86, 5, 45, 17));
    jPanel3.add(jLabellanegap,      new XYConstraints(150, 7, 58, 12));
    jPanel3.add(j01HandDistText,             new XYConstraints(222, 5, 45, 17));
    jPanel3.add(manulRec,      new XYConstraints(298, 1, -1, 22));
    panel1.add(jPanel1,     new XYConstraints(23, 12, 398, 80));
    panel1.add(jButton7, new XYConstraints(273, 232, 73, -1));
    panel1.add(jButton8,    new XYConstraints(90, 232, 73, -1));
    panel1.add(jPanel2,   new XYConstraints(23, 143, 398, 78));
    jPanel2.add(jButtonInslane,   new XYConstraints(200, 35, 85, 26));
    jPanel2.add(jButtondellane,   new XYConstraints(103, 35, 85, 26));
    jPanel2.add(jLabel1,     new XYConstraints(42, 8, -1, -1));
    jPanel2.add(jComboBoxlane,   new XYConstraints(147, 6, 196, 19));
    jPanel2.add(jButton2,     new XYConstraints(4, 35, 85, 26));
    jPanel2.add(jButton6,       new XYConstraints(298, 35, 85, 26));
    this.getContentPane().add(panel1, BorderLayout.CENTER);

    // add verifier
    j01RecoSensitivityText.setInputVerifier(new Verifier(Verifier.INT,true,1,true,200,this,true));
    j01HandLanesText.setInputVerifier(new Verifier(Verifier.INT,true,1,false,0,this,true));
    j01HandDistText.setInputVerifier(new Verifier(Verifier.INT,true,0,false,0,this,true));

    jComboBoxlane.removeAllItems();
    resetcombobox();
    if(this.frameMain .getExeMethod() ==0)
      this.jButton7 .setText("OK");
    else {
      this.setLocation(this.frameMain .getWizardWinLocation()) ;
      this.jButton7 .setText("Next▼");
    }
    buttonGroup.add(j01RecoMethod1Radio);
    buttonGroup.add(jRadioButton2);

    // added by wxs 20030823 -04
    this.setInterfaceVFromFile();
    this.setInterfaceFromV();

    // added by wxs 20030823 -05
    if(this.frameMain.getExeMethod()==1) this.isVisible=true;
    this.setVisible(this.isVisible);
    this.isInit=false;

    // added by wxs 20030823 -06
    this.doExeMethodEasyKey();
  }

  private void setOptimun(){
    int st=this.frameMain.getCurrentImage().getLayer1D().recLaneScan();
    if(st==-1) this.jRadioButton2.setSelected(true);
    else{
      this.j01RecoMethod1Radio.setSelected(true);
      if(st>=0 && st<=200){
        this.j01RecoSensitivityText.setText(String.valueOf(st));
        this.j01RecoSenBar.setValue(st);
        autoRec_actionPerformed(null);
        this.frameMain.getCurrentImage().setDoMethod(1,
            "⊿ Lane Detection━［Method=search］［Best Sensitivity="+st+"］");
      }
    }
  }

  void manulRec_actionPerformed(ActionEvent e) {
    this.j01HandLanesText.enable();
    this.j01HandDistText.enable();
    if(j01HandLanesText.getText().length() ==0||j01HandDistText.getText().length() ==0)
      return;
    Rectangle rectangle;
    rectangle=frameMain.getCurrentImage().getROI();
    Point pt1,pt2;
    pt1=new Point(0,0);
    pt2=new Point(0,0);
    pt1=rectangle.getLocation();
    pt2.x=(int)(rectangle.getLocation().x+rectangle.getWidth()-1);
    pt2.y=(int)(rectangle.getLocation().y+rectangle.getHeight()-1);
    frameMain.getCurrentImage().getLayer1D().manualrec(pt1,pt2,
        Integer.parseInt(j01HandLanesText.getText()),
        Integer.parseInt(j01HandDistText.getText()));
    frameMain.getCurrentImage().getLayer1D().rearragelane();
    resetcombobox();
    this.frameMain .getCurrentImage() .paintImage() ;
    this.frameMain.getCurrentImage().setDoMethod(1,
        "⊿ Lane Detection━［Method=Manu］"+
        "［Total number lanes="+j01HandLanesText.getText()+"］"+
        "［Lane distance="+j01HandDistText.getText()+"］");
  }

  /**
   *自动识别
   **/
  private void autoRec_actionPerformed(ActionEvent e) {
    j01RecoSensitivityText.setText(Integer.toString(j01RecoSenBar.getValue()));
    int mode;
    if(this.j01RecoMethod1Radio.isSelected() ==true) mode=1;
    else mode=2;
    frameMain.getCurrentImage().getLayer1D().reclane(
        Integer.parseInt(j01RecoSensitivityText.getText()),mode);
    resetcombobox();
    frameMain.getCurrentImage().paintImage();
    this.frameMain.getCurrentImage().setDoMethod(1,
        "⊿ Lane Detection━［Method=Auto］［Sensitivity="+j01RecoSensitivityText.getText()+"］"+
        "［Detection=Method"+mode+"］");
  }

  void this_mouseMoved(MouseEvent e) {

  }

  void this_mousePressed(MouseEvent e) {

  }

  void this_mouseReleased(MouseEvent e) {

  }

  /**
   * 为删除泳道
   * @param e
   *
   */
  void jButtondellane_actionPerformed(ActionEvent e) {
    //frameMain .setCmmStatus("delete1DLane");
    //Button6是修饰按钮
    //if(jButton6.getText()=="停止修饰")
    int laneN;
    this.rereclane=false;
    laneN=jComboBoxlane.getSelectedIndex()+1;
    if(laneN==0)  return;
    frameMain.getCurrentImage().getLayer1D().dellane(laneN);
    this.resetcombobox();
    // frameMain.getCurrentImage().getLayer1D().getline(jScrollBarwavew.getValue(),jScrollBarwaveh.getValue(),jScrollBarslope.getValue(),jScrollBarnoise.getValue());
    frameMain.getCurrentImage().getContentPane().repaint();
  }

  void j01RecoSenBar_adjustmentValueChanged(AdjustmentEvent e) {
    if(isInit==true) return;
    autoRec_actionPerformed(null);
  }

  void j01RecoSensitivityText_actionPerformed(ActionEvent e) {
    // this.jTextFieldsensi.setText(Integer.toString(jScrollBarsensi.getValue() ));
    j01RecoSenBar.setValue(Integer.parseInt(this.j01RecoSensitivityText.getText()));
  }

  void Ok_actionPerformed(ActionEvent e) {
    if(frameMain.getCurrentImage()==null)  {
      this.dispose();
    }
    else{
      frameMain.getCurrentImage().getLayer1D().rearragelane();
      frameMain.getCurrentImage().getContentPane().repaint();
      this.dispose();
    }
    if(this.frameMain.getExeMethod()!=0){
      // changed by wxs 20030825 -11
      this.frameMain.setWizardWinLocation(this.getLocation() ) ;
      this.frameMain.doStep(true)  ;
    }
  }

  /**reset all data
   *把各个参数恢复默认值
   **/
  void jButton5_actionPerformed(ActionEvent e) {
    j01RecoSenBar.setValue(60);
    j01RecoSensitivityText.setText(Integer.toString(j01RecoSenBar.getValue() ));
    this.jComboBoxlane.removeAllItems();
  }

  /**
   *对泳道进行修饰，按了此键后就可以得到泳道的排列
   * 对泳道增加，或删除了
   *
   **/
  void jButton6_actionPerformed(ActionEvent e) {
    int laneN;
    this.frameMain.cmmStatus="moveLane";
    if(jButton6.getText()=="Modify Lane")
                          {//jButton6.setText("停止修饰");
      laneN=this.frameMain.getCurrentImage().getLayer1D().totallane;
      //this.setTitle("手动识别");
      rereclane=false;
    }
    this.resetcombobox();
  }

  void resetcombobox(){
    int laneN;
    this.jComboBoxlane.removeAllItems();
    // modified by wxs 20030806
    if(frameMain.getCurrentImage()==null) return;
    laneN=frameMain.getCurrentImage().getLayer1D().getlanenum();
    for(int ii=0;ii<laneN;ii+=2)
      this.jComboBoxlane.addItem(Integer.toString(ii/2+1));
  }

  /**为取消
   **/
  void jButton8_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void j01RecoSensitivityText_focusLost(FocusEvent e) {
    try{
      double sen=Double.parseDouble(j01RecoSensitivityText.getText());
      this.j01RecoSenBar.setValue((int)sen);
    }
    catch(NumberFormatException e2){

    }
  }

  void j01RecoSensitivityText_keyPressed(KeyEvent e) {
    //  if(isDigital(e.getKeyCode())==false)
    // this.jTextFieldsensi.setVerifyInputWhenFocusTarget();
  }

  private boolean isDigital(int current){
    if(current<=9 && current>=0) return true;
    else return false;
  }

  /**
   *插入泳道
   **/
  void jButtonInslane_actionPerformed(ActionEvent e) {
    int laneNum;
    laneNum=jComboBoxlane.getSelectedIndex()+1;
    if(laneNum==0) return;
    this.rereclane=false;
    this.frameMain.getCurrentImage().getLayer1D().inslane(laneNum);
    resetcombobox();
    // frameMain.getCurrentImage().getLayer1D().rearragelane();
    if(frameMain.getCurrentImage().getLayer1D().current.size()>
       frameMain.getCurrentImage().getLayer1D().getlanenum()+2)
      frameMain.getCurrentImage().getLayer1D().getline(8,60,20,0);

    // frameMain.getCurrentImage().getLayer1D().getline(jScrollBarwavew.getValue(),jScrollBarwaveh.getValue(),jScrollBarslope.getValue(),jScrollBarnoise.getValue());
    frameMain.getCurrentImage().getContentPane().repaint();
  }

  /**
   *插入条带
   **/
  void jButtoninsline_actionPerformed(ActionEvent e) {
    cmm=this.frameMain.cmmStatus;
    //this.jButtoninsline.setText("停止");
    cmm=this.frameMain.cmmStatus;
    this.frameMain.cmmStatus="insert line";
    rerecline=false;
   /*else
   {this.jButtoninsline.setText("插入条带");
   this.frameMain.cmmStatus=null;
   }*/
  }

  /**
   *为删除条带
   **/
  void jButtondelline_actionPerformed(ActionEvent e) {
    cmm=this.frameMain.cmmStatus;
    {//this.jButtondelline.setText("停止");
      cmm=this.frameMain.cmmStatus;
      //System.out.println("dasdasdfadffffdfadfsdf");
      this.frameMain.cmmStatus="delete line";
      rerecline=false;
    }
    /* else
    {this.jButtondelline.setText("删除条带");
    this.frameMain.cmmStatus=cmm;
    }*/
  }

  /**
   *选择手动识别
   **/
  void jButtonsellane_actionPerformed(ActionEvent e) {
    this.frameMain.cmmStatus="select region";
  }


  void jButton2_actionPerformed(ActionEvent e) {
    this.frameMain.cmmStatus = "moveLane";
  }

  void jComboBoxlane_actionPerformed(ActionEvent e) {
  }

  void j01HandLanesText_actionPerformed(ActionEvent e) {
  }

  void jButton1_actionPerformed(ActionEvent e) {
    this.setCursor(this.frameMain.getCurrentImage().createWaitCursor());
    setOptimun();
    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
          if(st.type.substring(0,2).equals("01"))  set.addElement(st);
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
    if(exeMethod==2)  this.isVisible =getSettings("01Visible").paraBoolean;
    else this.isVisible=true;
    str=getSettings("01Method").paraString;
    if(str.equals("01MethodOpt")) return;
    else if(str.equals("01MethodHand")) {
      this.j01HandLanesText.setText(getSettings("01MethodHandLanes").paraString);
      this.j01HandDistText.setText(getSettings("01MethodHandDist").paraString);
    }
    else {
      this.j01RecoMethod1Radio.setSelected(getSettings("01MethodRecoMethod1").paraBoolean);
      str=getSettings("01MethodRecoSen").paraString;
      this.j01RecoSensitivityText.setText(str);
      this.j01RecoSenBar.setValue(Integer.parseInt(str));
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
    String str;
    this.frameMain.getCurrentImage().setTitle("Detect Lane......");
    str=getSettings("01Method").paraString;
    if(str.equals("01MethodHand")){
      manulRec_actionPerformed(null);
    }
    else if(str.equals("01MethodOpt")){
      setOptimun();
    }
    else{
      autoRec_actionPerformed(null);
    }
    this.frameMain.getCurrentImage().setTitle("Done");
    // donext
    this.frameMain.setWizardWinLocation(this.getLocation() ) ;
    this.frameMain.doStep(true)  ;
  }
}
