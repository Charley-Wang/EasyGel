package easygel.layer;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.lang.reflect.*;
import easygel.uiti.*;
import easygel.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogControlLine extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private ButtonGroup buttonGroupFont = new ButtonGroup();

  //private LayerLine userName;
  private Object userName;
  private InfoLine lineInfo;

  private boolean isDotLine;
  private boolean inited;

  private JButton jButtonApply = new JButton();
  private JButton jButtonLineColor = new JButton();
  private JButton jButtonLineSolid = new JButton();
  private JButton jButtonLineDot = new JButton();
  private JCheckBox jCheckBoxLineVisible = new JCheckBox();
  private JLabel jLabel2 = new JLabel();
  private JTextField jTextFieldLineThick = new JTextField();
  private JButton jButtonCreate = new JButton();
  private JButton jButton1 = new JButton();
  private FrameMain frameMain;

  public DialogControlLine(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    frameMain=(FrameMain)frame;
    inited=false;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogControlLine() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    jCheckBoxLineVisible.setFont(new java.awt.Font("Dialog", 0, 13));
    panel1.setLayout(xYLayout1);
    jButtonApply.setForeground(Color.blue);
    jButtonApply.setMargin(new Insets(0, 0, 0, 0));
    jButtonApply.setText("Application");
    jButtonApply.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonApply_actionPerformed(e);
      }
    });
    jButtonLineColor.setMargin(new Insets(0, 0, 0, 0));
    jButtonLineColor.setText("Color");
    jButtonLineColor.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonLineColor_actionPerformed(e);
      }
    });
    panel1.setForeground(SystemColor.desktop);
    jButtonLineSolid.setMargin(new Insets(0, 0, 0, 0));
    jButtonLineSolid.setText("Solid Line");
    jButtonLineSolid.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonLineSolid_actionPerformed(e);
      }
    });
    jButtonLineDot.setMargin(new Insets(0, 0, 0, 0));
    jButtonLineDot.setText("Dotted Line");
    jButtonLineDot.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonLineDot_actionPerformed(e);
      }
    });
    jCheckBoxLineVisible.setText("Visible Line");
    jCheckBoxLineVisible.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxLineVisible_actionPerformed(e);
      }
    });
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel2.setText("Line width");
    this.setResizable(false);
    jButtonCreate.setMargin(new Insets(0, 0, 0, 0));
    jButtonCreate.setText("Create");
    jButtonCreate.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonCreate_actionPerformed(e);
      }
    });
    jButton1.setMargin(new Insets(0, 0, 0, 0));
    jButton1.setText("Exit");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    panel1.add(jButtonLineColor,  new XYConstraints(2, 2, 49, 20));
    panel1.add(jButtonApply,   new XYConstraints(2, 28, 49, 20));
    panel1.add(jButtonCreate,   new XYConstraints(60, 28, 49, 20));
    panel1.add(jButtonLineSolid,   new XYConstraints(60, 2, 49, 20));
    panel1.add(jButton1,   new XYConstraints(119, 28, 49, 20));
    panel1.add(jButtonLineDot,   new XYConstraints(119, 2, 49, 20));
    panel1.add(jTextFieldLineThick, new XYConstraints(119, 56, 48, 18));
    panel1.add(jCheckBoxLineVisible, new XYConstraints(3, 54, 60, 20));
    panel1.add(jLabel2, new XYConstraints(68, 55, 51, -1));

    // 开始配置初始化参数
    this.getContentPane().add(panel1, BorderLayout.CENTER);

    // Verifier
    this.jTextFieldLineThick.setInputVerifier(new Verifier(Verifier.INT,true,1,true,10,this,true));

    inited=true;
    userName=null;
    this.noUserInit();
  }

  /**
   * 当无对象与之关连时的设置
   */
  private void noUserInit(){
    isDotLine=false;

    this.jTextFieldLineThick.setText("1");
    jTextFieldLineThick.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        jTextFieldLineThick_keyPressed(e);
      }
    });
    this.jCheckBoxLineVisible.setSelected(true) ;
    this.jButtonLineSolid.setBorder(BorderFactory.createLoweredBevelBorder());
    this.jButtonLineDot .setBorder(BorderFactory.createRaisedBevelBorder());
    jButtonApply.setEnabled(false);

    jButtonCreate.setEnabled(false);
  }

  public void drawLine(){
    if(inited=false) return;
    if(lineInfo!=null)  changeTextInfo();
  }

  private void changeTextInfo(){
    InfoLine oldLine,newLine;
    oldLine=lineInfo;
    newLine=oldLine.duplicate();
    newLine.color =jButtonLineColor.getForeground();
    newLine.thick =(new Integer((jTextFieldLineThick.getText()))).intValue();
    if(this.isDotLine ==true) newLine.isDot =true;
    else newLine.isDot =false;
    if(jCheckBoxLineVisible.isSelected() ==true)  newLine.visible= true;
    else newLine.visible=false;
    oldLine.isSelected=false;
    //userName.removeElement(oldLine,true,"Line",1,true);
    //lineInfo=newLine;
    //userName.addElement(newLine,true,"Line",2,false,-1);
    //userName.rePaintAll();

    // Encrypt
    WxsUiti.call(frameMain.getClassLayerLine(),frameMain.getCurrentImage().getLayerLine(),"removeElement",
                 new Object[]{oldLine,new Boolean(true),new String("Line"),new Integer(1),new Boolean(true)});
    lineInfo=newLine;
    //WxsUiti.call(frameMain.getClassLayerLine(),frameMain.getCurrentImage().getLayerLine(),"addElement",
    //             new Object[]{newLine,new Boolean(true),new String("Line"),new Integer(2),new Boolean(false),new Integer(-1)});
    WxsUiti.call(frameMain.getClassLayerLine(),frameMain.getCurrentImage().getLayerLine(),"addElement",
                 new Object[]{newLine,new Boolean(true),new String("Line"),new Integer(2),new Integer(-1)});
    WxsUiti.call(frameMain.getClassLayerLine(),frameMain.getCurrentImage().getLayerLine(),"rePaintAll",null);
  }

  void jButtonApply_actionPerformed(ActionEvent e) {
    jButtonApply.setEnabled(false);
    drawLine();
  }

  void jButtonLineColor_actionPerformed(ActionEvent e) {
    Color c;
    JColorChooser jcc=new JColorChooser();
    c=jcc.showDialog(this,"Please select line color:", jButtonLineColor.getForeground() ) ;
    if(c==null) return;
    jButtonLineColor.setForeground(c);
    drawLine();
  }

  void jComboBoxFont_itemStateChanged(ItemEvent e) {
    jButtonApply.setEnabled(true);
  }

  void jComboBoxFontSize_itemStateChanged(ItemEvent e) {
    jButtonApply.setEnabled(true);
  }

  //public void setUser(LayerLine layerLine,InfoLine li){
  public void setUser(Object layerLine,InfoLine li){
    userName=layerLine;
    lineInfo=li;
    this.setInterface();
    this.toFront() ;
    //drawLine();
  }

  /**
   * 根据textInfo设置界面。
   */
  private void setInterface(){
    this.jButtonLineColor .setForeground(lineInfo.color);
    jTextFieldLineThick.setText(String.valueOf(lineInfo.thick));
    jCheckBoxLineVisible.setSelected(lineInfo.visible);
    if(lineInfo.isDot  ==true){
      jButtonLineDot.setBorder(BorderFactory.createLoweredBevelBorder());
      jButtonLineSolid.setBorder(BorderFactory.createRaisedBevelBorder());
    }
    else{
      jButtonLineDot.setBorder(BorderFactory.createRaisedBevelBorder());
      jButtonLineSolid.setBorder(BorderFactory.createLoweredBevelBorder());
    }
    //设置本类的变量
    isDotLine=lineInfo.isDot;
  }

  void jButtonLineSolid_actionPerformed(ActionEvent e) {
    jButtonLineSolid.setBorder(BorderFactory.createLoweredBevelBorder());
    jButtonLineDot.setBorder(BorderFactory.createRaisedBevelBorder());
    this.isDotLine =false;
    this.drawLine();
  }

  void jButtonLineDot_actionPerformed(ActionEvent e) {
    jButtonLineSolid.setBorder(BorderFactory.createRaisedBevelBorder());
    jButtonLineDot.setBorder(BorderFactory.createLoweredBevelBorder());
    this.isDotLine =true;
    this.drawLine();
  }

  void jButtonCreate_actionPerformed(ActionEvent e) {
    //create ...

  }

  void jTextFieldLineThick_keyPressed(KeyEvent e) {
    jButtonApply.setEnabled(true);
  }

  void jCheckBoxLineVisible_actionPerformed(ActionEvent e) {
    drawLine();
  }

  void jButton1_actionPerformed(ActionEvent e) {
    this.setVisible(false);
  }

}