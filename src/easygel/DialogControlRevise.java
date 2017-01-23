package easygel;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import easygel.*;
import ij.process.*;
import javax.swing.border.*;
// added by wxs 20030823 -01
import easygel.setting.*;
import java.util.*;
import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogControlRevise extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JCheckBox jCheckBox1 = new JCheckBox();
  private JCheckBox jCheckBox2 = new JCheckBox();
  private JCheckBox jCheckBox3 = new JCheckBox();
  private FrameMain frameMain;
  private JButton jButton2 = new JButton();
  private JButton jButton1 = new JButton();
  private XYLayout xYLayout2 = new XYLayout();
  private TitledBorder titledBorder1;
  private TitledBorder titledBorder2;
  private JButton jButton5 = new JButton();
  private JButton jButton6 = new JButton();
  private JLabel jLabel1 = new JLabel();

  // added by wxs 20030823 -02
  private Vector set=new Vector();
  private boolean isVisible;
  private boolean isInit;

  public DialogControlRevise(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      frameMain=(FrameMain)frame;
      // added by wxs 20030823 -03
      this.isInit=true;
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogControlRevise() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    panel1.setLayout(xYLayout1);
    jCheckBox1.setText("Rectify to the Origin");
    jCheckBox2.setText("Rectify to the Front");
    jCheckBox3.setText("Not Rectify");
    jButton2.setMargin(new Insets(0, 0, 0, 0));
    jButton2.setText("Connect");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton1.setMargin(new Insets(0, 0, 0, 0));
    jButton1.setText("Execute");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    this.getContentPane().setLayout(xYLayout2);
    xYLayout2.setWidth(442);
    xYLayout2.setHeight(265);
    panel1.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton5.setMinimumSize(new Dimension(43, 25));
    jButton5.setPreferredSize(new Dimension(43, 25));
    jButton5.setMargin(new Insets(0, 0, 0, 0));
    jButton5.setText("OK");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton5_actionPerformed(e);
      }
    });
    jButton6.setMaximumSize(new Dimension(43, 29));
    jButton6.setMinimumSize(new Dimension(43, 29));
    jButton6.setPreferredSize(new Dimension(43, 29));
    jButton6.setMargin(new Insets(0, 0, 0, 0));
    jButton6.setText("Cancel");
    jButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton6_actionPerformed(e);
      }
    });
    jLabel1.setText("Rectify Lanes");
    panel1.add(jCheckBox1, new XYConstraints(42, 77, 83, 16));
    panel1.add(jCheckBox2,   new XYConstraints(161, 77, 83, 16));
    panel1.add(jLabel1, new XYConstraints(141, 16, 134, 24));
    panel1.add(jCheckBox3,  new XYConstraints(279, 77, 83, 16));
    panel1.add(jButton2,  new XYConstraints(87, 132, 72, 24));
    panel1.add(jButton1, new XYConstraints(224, 132, 72, 24));
    this.getContentPane().add(jButton5,    new XYConstraints(273, 232, 73, 25));
    this.getContentPane().add(jButton6,      new XYConstraints(90, 232, 73, 25));
    this.getContentPane().add(panel1,                     new XYConstraints(22, 20, 399, 193));

    if(this.frameMain .getExeMethod() ==0){
      this.jButton5 .setText("OK");
      this.jButton6 .setText("Cancel");
    }
    else{
      this.setLocation(this.frameMain .getWizardWinLocation()) ;
      this.jButton5 .setText("Next▼");
      this.jButton6 .setText("Last▲") ;
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

  //连线
  void jButton1_actionPerformed(ActionEvent e) {
    if(frameMain.getCurrentImage() .getLayer1D() .isRevised() ==false)
      return;
    if(this.frameMain.getCurrentImage().getLayer1D().point.size()==0)
     return;
    this.frameMain.getCurrentImage().getLayer1D().arrangept();
    this.frameMain.getCurrentImage().getLayer1D().revise();
    ImageProcessor ip;
    DialogImage img=frameMain.getCurrentImage();
    ip=img.getIP();
    Image image;
    image=ip.createImage();
    img.setImage2(image);
    this.frameMain.setCmmStatusDefault();
    img.paintImage();

    // added by wxs 20030825
    String str="⊿ Rectify Lane -［Origin=";
    Vector v=this.frameMain.getCurrentImage().getLayer1D().point;
    Point pt=new Point(0,0);
    for(int ii=1;ii<=v.size();ii++){
      pt=(Point)v.elementAt(ii-1);
      str+="("+pt.x+","+pt.y+")";
      if(ii!=v.size()) str+="-";
    }
    str+="］";
    this.frameMain.getCurrentImage().setDoMethod(2,str);

    frameMain.getCurrentImage().getLayer1D().point.removeAllElements();
    frameMain.getCurrentImage() .getLayer1D() .setRevisedZero() ;
  }

  void jButton2_actionPerformed(ActionEvent e) {
     this.frameMain.cmmStatus="revise";
     this.frameMain.getCurrentImage().mySetCursor(false,Cursor.CROSSHAIR_CURSOR);
     this.frameMain .getCurrentImage() .getLayer1D() .setRevisedZero() ;
  }

  void jButton3_actionPerformed(ActionEvent e) {
   if(frameMain.getCurrentImage() .getLayer1D() .isRevised() ==false) return;
    frameMain.getCurrentImage().getLayer1D().recoverrev();
     DialogImage img=frameMain.getCurrentImage();
    frameMain.setCurrentImage(img);
  }

  void jButton5_actionPerformed(ActionEvent e) {
    this.dispose() ;
    if(this.frameMain .getExeMethod() >=1){
      this.frameMain .setWizardWinLocation(this.getLocation() ) ;
      this.frameMain.doStep(true);
    }
  }

  void jButton6_actionPerformed(ActionEvent e) {
    this.dispose() ;
    if(this.frameMain .getExeMethod()!=0){
      this.frameMain .setWizardWinLocation(this.getLocation() ) ;
      this.frameMain.doStep(false);
    }
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
          if(st.type.substring(0,2).equals("02"))  set.addElement(st);
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

  // added by wxs 20030823 -09
  public Settings getSettings(String type){
    Settings st=new Settings();
    for(int ii=1;ii<=set.size();ii++){
      st=(Settings)set.elementAt(ii-1);
      if(st.type.equals(type)) break;
    }
    return st;
  }

  // added by wxs 20030823 -08
  private void setInterfaceFromV(){
    int exeMethod=this.frameMain.getExeMethod();
    if(exeMethod==2)  this.isVisible =getSettings("02Need").paraBoolean;
    else this.isVisible=true;
  }

  // added by wxs 20030823 -10
  public void doExeMethodEasyKey(){
    // donext
    if(this.frameMain.getExeMethod()!=2) return;
    if(this.isVisible==true) return;
    this.frameMain.setWizardWinLocation(this.getLocation() ) ;
    this.frameMain.doStep(true)  ;
  }

}