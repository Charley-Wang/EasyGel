package easygel;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.event.*;
import easygel.image.*;
import easygel.layer.*;
// added by wxs 20030823 -01
import easygel.setting.*;
import java.io.*;
import java.util.*;

public class DialogDelBackGround extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  private JButton jButton5 = new JButton();
  private JPanel jPanel7 = new JPanel();
  private TitledBorder titledBorder1;
  private XYLayout xYLayout7 = new XYLayout();
  private JRadioButton j03MethodAvgRadio = new JRadioButton();
  private JRadioButton j03MethodMinRadio = new JRadioButton();
  private JRadioButton jRadioButton3 = new JRadioButton();
  private JRadioButton jRadioButton4 = new JRadioButton();
  private JRadioButton jRadioButton5 = new JRadioButton();
  private ButtonGroup buttonGroup=new ButtonGroup();

  //参数
  private FrameMain frameMain;
  private int methodNo;
  private boolean isSet;

  //对应于各种方法的变量定义
  private  int exeMethod;       //0--单独执行方式
                                //1--Step by step方式
                                //2--全部默认方式
  private int totalNo=4;        //共有几个要去本底内容4,即4种分析
  private int currentNo;        //当前执行序分析序号，为1-4
  private String[] winTitle=new String[totalNo];     //窗口标题
  private int[] defaultDelBGMethod=new int[totalNo];
  private BorderLayout borderLayout1 = new BorderLayout();
  private TitledBorder titledBorder2;
  private JRadioButton j03MethodRollballRadio = new JRadioButton();
  private JComboBox jComboBoxObject = new JComboBox();
  private JTextField j03RadiusText = new JTextField();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel1 = new JLabel();
  private ButtonGroup buttonGroup1 = new ButtonGroup();//默认方法 1-6

  // added by wxs 20030823 -02
  private Vector set=new Vector();
  private boolean isVisible;
  private boolean isInit;
  private JRadioButton jRadioAdNone = new JRadioButton();
  private JRadioButton jRadioAdAvg = new JRadioButton();
  private JRadioButton jRadioAdMax = new JRadioButton();
  private JRadioButton jRadioAd256 = new JRadioButton();
  private ButtonGroup buttonGroup2 = new ButtonGroup();
  private JTextField jTextAdCo = new JTextField();
  private JLabel jLabel3 = new JLabel();
  private JPanel jPanel1 = new JPanel();
  private TitledBorder titledBorder3;
  private JPanel jPanel2 = new JPanel();
  private TitledBorder titledBorder4;

  public DialogDelBackGround(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    isSet=true;
    frameMain=(FrameMain)frame;
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

  public DialogDelBackGround() {
    //this(null, "", false);
    this(null, "", true);
  }
  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    titledBorder4 = new TitledBorder("");
    panel1.setLayout(xYLayout1);
    //this.setTitle("去本底");
    jButton1.setText("Remove background");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setMargin(new Insets(0, 0, 0, 0));
    jButton2.setText("Cancel");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton3.setMargin(new Insets(0, 0, 0, 0));
    jButton3.setText("OK");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jButton5.setText("No removing background");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton5_actionPerformed(e);
      }
    });
    jPanel7.setBorder(BorderFactory.createRaisedBevelBorder());
    jPanel7.setLayout(xYLayout7);
    j03MethodAvgRadio.setToolTipText("Remove background, average for ROI");
    j03MethodAvgRadio.setText("ROI average");
    j03MethodAvgRadio.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        j03MethodAvgRadio_mouseClicked(e);
      }
    });
    j03MethodMinRadio.setToolTipText("Remove backgroud, minimum of ROI");
    j03MethodMinRadio.setText("ROI minimum");
    j03MethodMinRadio.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        j03MethodMinRadio_mouseClicked(e);
      }
    });
    jRadioButton3.setToolTipText("Please select a ROI afeter clicking");
    jRadioButton3.setText("ROI selection method");
    jRadioButton3.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jRadioButton3_mouseClicked(e);
      }
    });
    jRadioButton4.setToolTipText("Please select a ROI afeter clicking");
    jRadioButton4.setText("Negtive method");
    jRadioButton4.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jRadioButton4_mouseClicked(e);
      }
    });
    jRadioButton5.setToolTipText("Please drawing lines after clicking");
    jRadioButton5.setText("Draw lines");
    jRadioButton5.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jRadioButton5_mouseClicked(e);
      }
    });
    panel1.setMinimumSize(new Dimension(442, 265));
    panel1.setPreferredSize(new Dimension(442, 265));
    this.getContentPane().setLayout(borderLayout1);
    j03MethodRollballRadio.setToolTipText("Please select an object for removing background afeter clicking");
    j03MethodRollballRadio.setText("Parameters for rolling ball");
    j03MethodRollballRadio.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        j03MethodRollballRadio_mouseClicked(e);
      }
    });
    j03RadiusText.setToolTipText("1-500");
    j03RadiusText.setText("120");
    jLabel2.setText("R of rolling ball");
    jLabel1.setText("Select object");
    jRadioAdNone.setSelected(true);
    jRadioAdNone.setText("w/o adjust");
    jRadioAdAvg.setText("average");
    jRadioAdMax.setText("max");
    jRadioAd256.setText("fixed value");
    jTextAdCo.setText("0.618");
    jLabel3.setText("coefficent");
    jPanel1.setBorder(titledBorder3);
    jPanel2.setBorder(titledBorder4);
    jPanel7.add(jButton5,  new XYConstraints(236, 152, 87, 25));
    jPanel7.add(jButton1, new XYConstraints(64, 152, 87, 25));
    panel1.add(jButton2,       new XYConstraints(90, 232, 73, -1));
    panel1.add(jButton3,      new XYConstraints(273, 232, 73, -1));
    panel1.add(jPanel7,               new XYConstraints(22, 20, 399, 193));
    this.getContentPane().add(panel1, BorderLayout.CENTER);
    isSet=true;

    // Verifier
    j03RadiusText.setInputVerifier(new Verifier(Verifier.INT,true,1,true,1000,this,true));
    this.jTextAdCo.setInputVerifier(new Verifier(Verifier.FLOAT,true,0.1,true,1.0,this,false));

    if(this.frameMain .getExeMethod() ==0){
      this.jButton3 .setText("OK");
      this.jButton2 .setText("Cancel");
    }
   else{
      this.setLocation(this.frameMain .getWizardWinLocation()) ;
      this.jButton3 .setText("Next▼");
      this.jButton2 .setText("Last▲") ;
   }
    buttonGroup1.add(j03MethodAvgRadio);
    buttonGroup1.add(j03MethodMinRadio);
    buttonGroup1.add(jRadioButton3);
    buttonGroup1.add(jRadioButton4);
    buttonGroup1.add(jRadioButton5);
    buttonGroup1.add(j03MethodRollballRadio);
    buttonGroup2.add(jRadioAdNone);
    buttonGroup2.add(jRadioAdAvg);
    buttonGroup2.add(jRadioAdMax);
    buttonGroup2.add(jRadioAd256);
    jPanel7.add(j03MethodRollballRadio,   new XYConstraints(110, 23, -1, -1));
    jPanel7.add(jLabel1, new XYConstraints(111, 52, -1, -1));
    jPanel7.add(jLabel2,  new XYConstraints(111, 73, -1, -1));
    jPanel7.add(j03MethodAvgRadio,  new XYConstraints(26, 44, -1, 22));
    jPanel7.add(jRadioButton5, new XYConstraints(26, 65, 82, 22));
    jPanel7.add(jRadioButton3, new XYConstraints(26, 86, 82, 22));
    jPanel7.add(j03MethodMinRadio, new XYConstraints(26, 107, 106, 22));
    jPanel7.add(jRadioButton4, new XYConstraints(26, 23, 82, 22));
    jPanel7.add(jComboBoxObject,      new XYConstraints(167, 52, 51, 18));
    jPanel7.add(j03RadiusText,       new XYConstraints(166, 74, 50, 18));
    jPanel7.add(jRadioAdNone,  new XYConstraints(256, 24, 75, 24));
    jPanel7.add(jRadioAdAvg, new XYConstraints(256, 46, 98, 20));
    jPanel7.add(jRadioAdMax, new XYConstraints(256, 63, 95, 24));
    jPanel7.add(jRadioAd256, new XYConstraints(256, 85, 96, 24));
    jPanel7.add(jLabel3, new XYConstraints(256, 108, 50, 23));
    jPanel7.add(jTextAdCo, new XYConstraints(314, 108, 46, 19));
    jPanel7.add(jPanel1,  new XYConstraints(21, 19, 205, 121));
    jPanel7.add(jPanel2,  new XYConstraints(234, 20, 145, 120));
  }

  void selButton(int v){
    this.j03MethodAvgRadio.setForeground(Color.black );
    this.j03MethodMinRadio.setForeground(Color.black );
    this.jRadioButton3 .setForeground(Color.black );
    this.jRadioButton4 .setForeground(Color.black );
    this.jRadioButton5 .setForeground(Color.black );
    this.j03MethodRollballRadio.setForeground(Color.black );
    this.j03RadiusText.setEnabled(false) ;
    this.jComboBoxObject .setEnabled(false);
    if(v==1) {
      this.j03MethodAvgRadio.setForeground(Color.blue);
      this.j03MethodAvgRadio.setSelected(true);
    }
    else if(v==2) {
      this.j03MethodMinRadio.setForeground(Color.blue);
      this.j03MethodMinRadio.setSelected(true);
    }
    else if(v==3) {
      this.jRadioButton3 .setForeground(Color.blue);
      this.jRadioButton3.setSelected(true);
    }
    else if(v==4) {
      this.jRadioButton4 .setForeground(Color.blue);
      this.jRadioButton4.setSelected(true);
    }
    else if(v==5) {
      this.jRadioButton5 .setForeground(Color.blue);
      this.jRadioButton5.setSelected(true);
      frameMain.showHistgram() ;
    }
    else if(v==6) {
      this.j03MethodRollballRadio.setForeground(Color.blue);
      this.j03MethodRollballRadio.setSelected(true);
      this.jLabel2 .setEnabled(true);
      this.j03RadiusText.setEnabled(true) ;
      this.j03RadiusText.setText("120");
      this.jComboBoxObject .setEnabled(true);
    }
    this.methodNo=v;
    isSet=true;
  }

  void j03MethodAvgRadio_mouseClicked(MouseEvent e) {
    if(isSet==false) return;
    isSet=false;
    selButton(1);
  }

  void j03MethodMinRadio_mouseClicked(MouseEvent e) {
    if(isSet==false) return;
    isSet=false;
    selButton(2);
  }

  void jRadioButton3_mouseClicked(MouseEvent e) {
    if(isSet==false) return;
    this.frameMain .setCmmStatus("basicSelROI_2");
    this.frameMain .getCurrentImage() .setSelROIPoints_2(0);
    isSet=false;
    selButton(3);
  }

  void jRadioButton4_mouseClicked(MouseEvent e) {
    if(isSet==false) return;
    this.frameMain .setCmmStatus("basicSelROI_2");
    this.frameMain .getCurrentImage() .setSelROIPoints_2(0);
    isSet=false;
    selButton(4);
  }

  void jRadioButton5_mouseClicked(MouseEvent e) {
    if(isSet==false) return;
    isSet=false;
    selButton(5);
  }

  void j03MethodRollballRadio_mouseClicked(MouseEvent e) {
    if(isSet==false) return;
    isSet=false;
    selButton(6);
  }

  void jButton1_actionPerformed(ActionEvent e) {
    delBack(getAdjustMode(),getCofi());
  }

  private double getCofi(){
    double cofi=Double.parseDouble(this.jTextAdCo.getText());
    return cofi;
  }

  private int getAdjustMode(){
    int mode=DialogImage.ADJUST_BACK_NONE;
    if(this.jRadioAd256.isSelected()==true) mode=DialogImage.ADJUST_BACK_256;
    else if(this.jRadioAdAvg.isSelected()==true) mode=DialogImage.ADJUST_BACK_AVG;
    else if(this.jRadioAdMax.isSelected()==true) mode=DialogImage.ADJUST_BACK_MAX;
    return mode;
  }

  void delBack(int adjustMode,double cofi){
    String str="⊿ Remove background -［Method=";
    if(methodNo==1)  {
      frameMain.getCurrentImage().delBackgroundROIAverage(1,adjustMode,cofi) ;
      str+="ROI average］";
    }
    else if(methodNo==2) {
      frameMain.getCurrentImage() .delBackgroundROIMinValue(1,adjustMode,cofi);
      str+="ROI min］";
    }
    else if(methodNo==3) {
      if(frameMain.getCurrentImage().existROI_2()==false){
        JOptionPane myOptionPane=new JOptionPane();
        myOptionPane.showMessageDialog(this,"Please select ROI afeter clicking select button",
                                       "",JOptionPane.WARNING_MESSAGE );
      }
      else {
        frameMain.getCurrentImage().delBackgroundSelect(1,adjustMode,cofi);
        Rectangle rect=frameMain.getCurrentImage().getROI_2();
        str+="ROI］";
        str+="［region=("+rect.getLocation().x+","+rect.getLocation().y+")-(";
        str+=(rect.getLocation().x+(int)rect.getWidth()-1)+",";
        str+=(rect.getLocation().y+(int)rect.getHeight()-1)+")］";
      }
    }
    else if(methodNo==4) {
      if(frameMain.getCurrentImage().existROI_2() ==false){
        JOptionPane myOptionPane=new JOptionPane();
        myOptionPane.showMessageDialog(this,"Please select an area after clicking negtive button",
                                       "",JOptionPane.WARNING_MESSAGE );
      }
      else {
        frameMain.getCurrentImage() .delBackgroundSelectRevert(1,adjustMode,cofi);
        Rectangle rect=frameMain.getCurrentImage().getROI_2();
        str+="Negtive］";
        str+="［region=("+rect.getLocation().x+","+rect.getLocation().y+")-(";
        str+=(rect.getLocation().x+(int)rect.getWidth()-1)+",";
        str+=(rect.getLocation().y+(int)rect.getHeight()-1)+")］";
      }
    }
    else if(methodNo==5){
      frameMain.getCurrentImage().delBackgroundLine(1,adjustMode,cofi) ;
      str+="Draw lines］";
    }
    else{
      int radius=Integer.parseInt(j03RadiusText.getText());
      if(jComboBoxObject.getSelectedIndex()==0){
        int num=this.jComboBoxObject .getItemCount()-1;
        for(int ii=1;ii<=num;ii++){
          frameMain.getCurrentImage().delBackgroundRollingBall(radius,ii,ii,adjustMode,cofi) ;
        }
      }
      else{
        frameMain.getCurrentImage() .delBackgroundRollingBall(radius
          ,this.jComboBoxObject .getSelectedIndex(),1,adjustMode,cofi);
      }
      str+="Parameters for rolling ball］［R of rolling ball="+radius+"］";
    }
    this.frameMain.getCurrentImage().setDoMethod(3,str);
  }

  void jButton5_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void jButton2_actionPerformed(ActionEvent e) {
    this.dispose() ;
    if(this.frameMain .getExeMethod() >=1){
      this.frameMain .setWizardWinLocation(this.getLocation() ) ;
      this.frameMain.doStep(false);
    }
    //frameMain.getCurrentImage().docmmUndo();
  }

  void jButton3_actionPerformed(ActionEvent e) {
    this.dispose() ;
    if(this.frameMain .getExeMethod() !=0){
      this.frameMain .setWizardWinLocation(this.getLocation() ) ;
      this.frameMain.doStep(true);
    }
  }

  //设置各种方法的变量
  public void setExeMethod(int exeMethod){
    this.exeMethod =exeMethod;
  }
  public void setCurrentNo(int currentNo){
    this.currentNo =currentNo;
    if(currentNo==1) this.jRadioButton4 .setEnabled(false);
  }

  private void setParaV(){
     defaultDelBGMethod[0]=6;
     defaultDelBGMethod[1]=2;
     defaultDelBGMethod[2]=2;
     defaultDelBGMethod[3]=2;
     this.selButton(defaultDelBGMethod[currentNo-1]);
  }

  public void setInterface(){
    // deleted by wxs 20030825
    // setParaV();
    if(frameMain.getCurrentImage()==null) return;
    if(this.currentNo ==1 ){
      Layer1D layer1D;
      layer1D=frameMain.getCurrentImage().getLayer1D();
      if(layer1D!=null){
        int laneNum=layer1D.getlanenum();
        this.jComboBoxObject .addItem("All lanes");
        for(int ii=1;ii<=laneNum/2;ii++){
          this.jComboBoxObject .addItem("Lane No: "+ii);
         }
      }
    }
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
          if(st.type.substring(0,2).equals("03"))  set.addElement(st);
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
    if(exeMethod==2)  this.isVisible =getSettings("03Visible").paraBoolean;
    else this.isVisible=true;
    str=getSettings("03Method").paraString;

    if(str.equals("03MethodAvg")==true) this.j03MethodAvgRadio.setSelected(true);
    else if(str.equals("03MethodMin")==true) this.j03MethodMinRadio.setSelected(true);
    else if(str.equals("03MethodRollball")){
      this.j03MethodRollballRadio.setSelected(true);
      this.j03RadiusText.setText(getSettings("03MethodRollballR").paraString);
    }
    else setParaV();
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
    this.frameMain.getCurrentImage().setTitle("Removing background......");
    str=getSettings("03Method").paraString;
    if(str.equals("03MethodAvg")){
      this.methodNo=1;
      delBack(getAdjustMode(),getCofi());
    }
    else if(str.equals("03MethodMin")){
      this.methodNo=2;
      delBack(getAdjustMode(),getCofi());
    }
    else if(str.equals("03MethodRollball")){
      this.methodNo=6;
      delBack(getAdjustMode(),getCofi());
    }
    this.frameMain.getCurrentImage().setTitle("Finsh");
    // donext
    this.frameMain.setWizardWinLocation(this.getLocation() ) ;
    this.frameMain.doStep(true)  ;
  }

  void j03MethodRollballRadio_mousePressed(MouseEvent e) {
  }
  void j03MethodRollballRadio_mouseReleased(MouseEvent e) {
  }
  void j03MethodRollballRadio_mouseEntered(MouseEvent e) {

  }
  void j03MethodRollballRadio_mouseExited(MouseEvent e) {

  }

}