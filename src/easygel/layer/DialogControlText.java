package easygel.layer;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.util.*;
import easygel.*;
import javax.swing.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogControlText extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JComboBox jComboBoxFont = new JComboBox();
  private JTextArea jTextArea1 = new JTextArea();
  private ButtonGroup buttonGroupFont = new ButtonGroup();
  private JButton jButtonBold = new JButton();
  private JButton jButtonItalic = new JButton();
  private JComboBox jComboBoxFontSize = new JComboBox();
  private JButton jButtonNormal = new JButton();
  private JButton jButtonUp = new JButton();
  private JButton jButtonDown = new JButton();

  private LayerText userName;
  private InfoText textInfo;

  private boolean isBold;
  private boolean isItalic;
  private boolean isRect;
  private boolean isDotRect;
  private boolean isRoundAngle;
  private int fontDirection;
  private static byte FONTNORMAL=0;
  private static byte FONTUP=1;
  private static byte FONTDOWN=2;
  private static double PI=3.1415926535;

  private boolean inited;
  private boolean createMode;
  private boolean isChanging;

  private JButton jButtonApply = new JButton();
  private JButton jButtonTextColor = new JButton();
  private JButton jButtonRectColor = new JButton();
  private JButton jButtonRectRound = new JButton();
  private JButton jButtonRectRect = new JButton();
  private JButton jButtonRectSolid = new JButton();
  private JButton jButtonRectDot = new JButton();
  private JCheckBox jCheckBoxRectVisible = new JCheckBox();
  private JLabel jLabel1 = new JLabel();
  private JTextField jTextFieldRectUp = new JTextField();
  private JTextField jTextFieldRectDown = new JTextField();
  private JTextField jTextFieldRectLeft = new JTextField();
  private JTextField jTextFieldRectRight = new JTextField();
  private JLabel jLabel2 = new JLabel();
  private JTextField jTextFieldRectThick = new JTextField();
  private JButton jButtonCreate = new JButton();
  private FrameMain frameMain;
  private JButton jButton1 = new JButton();
  private JPanel jPanel1 = new JPanel();

  public DialogControlText(Frame frame, String title, boolean modal,
                           boolean createMode) {
    super(frame, title, modal);
    inited=false;
    this.isChanging =false;
    this.frameMain=(FrameMain)frame;
    this.createMode =createMode;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    jCheckBoxRectVisible.setFont(new java.awt.Font("Dialog", 0, 13));
    panel1.setLayout(xYLayout1);
    jTextArea1.setBorder(BorderFactory.createLoweredBevelBorder());
    if(createMode==true){
      this.setTitle("Create Labels");
      this.jButtonCreate .setEnabled(true);
    }
    else{
      this.setTitle("Modify Labels");
      this.jButtonCreate .setEnabled(false);
    }
    jButtonApply.setEnabled(false);
    this.jButtonCreate .setEnabled(false);
    jTextArea1.setText("EasyGel Welcome To You.");
    jTextArea1.addAncestorListener(new javax.swing.event.AncestorListener() {
      public void ancestorAdded(AncestorEvent e) {
        jTextArea1_ancestorAdded(e);
      }
      public void ancestorRemoved(AncestorEvent e) {
      }
      public void ancestorMoved(AncestorEvent e) {
      }
    });
    jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        jTextArea1_keyPressed(e);
      }
    });
    jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        jTextArea1_keyPressed(e);
      }
    });
    jButtonBold.setFont(new java.awt.Font("Dialog", 1, 16));
    jButtonBold.setMargin(new Insets(0, 0, 0, 0));
    jButtonBold.setText("B");
    jButtonBold.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonBold_actionPerformed(e);
      }
    });
    jButtonItalic.setFont(new java.awt.Font("Dialog", 3, 16));
    jButtonItalic.setMargin(new Insets(0, 0, 0, 0));
    jButtonItalic.setSelected(true);
    jButtonItalic.setText("I");
    jButtonItalic.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonItalic_actionPerformed(e);
      }
    });
    jButtonNormal.setMargin(new Insets(0, 0, 0, 0));
    jButtonNormal.setText("ABC");
    jButtonNormal.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonNormal_actionPerformed(e);
      }
    });
    jButtonUp.setMargin(new Insets(0, 0, 0, 0));
    jButtonUp.setText("Up");
    jButtonUp.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonUp_actionPerformed(e);
      }
    });
    jButtonDown.setMargin(new Insets(0, 0, 0, 0));
    jButtonDown.setText("Dn");
    jButtonDown.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonDown_actionPerformed(e);
      }
    });
    jButtonApply.setForeground(Color.blue);
    this.jButtonCreate .setForeground(Color.blue );
    jButtonApply.setMargin(new Insets(0, 0, 0, 0));
    jButtonApply.setText("Application");
    jButtonApply.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonApply_actionPerformed(e);
      }
    });
    jButtonTextColor.setMargin(new Insets(0, 0, 0, 0));
    jButtonTextColor.setText("Text Color");
    jButtonTextColor.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonTextColor_actionPerformed(e);
      }
    });
    jButtonRectColor.setMargin(new Insets(0, 0, 0, 0));
    jButtonRectColor.setText("Frame Color");
    jButtonRectColor.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonRectColor_actionPerformed(e);
      }
    });
    jComboBoxFont.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jComboBoxFont_itemStateChanged(e);
      }
    });
    jComboBoxFontSize.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jComboBoxFontSize_itemStateChanged(e);
      }
    });
    panel1.setForeground(SystemColor.desktop);
    jButtonRectRound.setMargin(new Insets(0, 0, 0, 0));
    jButtonRectRound.setText("Round Corners");
    jButtonRectRound.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonRectRound_actionPerformed(e);
      }
    });
    jButtonRectRect.setMargin(new Insets(0, 0, 0, 0));
    jButtonRectRect.setText("Square Corners");
    jButtonRectRect.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonRectRect_actionPerformed(e);
      }
    });
    jButtonRectSolid.setMargin(new Insets(0, 0, 0, 0));
    jButtonRectSolid.setText("Solid Line");
    jButtonRectSolid.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonRectSolid_actionPerformed(e);
      }
    });
    jButtonRectDot.setMargin(new Insets(0, 0, 0, 0));
    jButtonRectDot.setText("Dotted Line");
    jButtonRectDot.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonRectDot_actionPerformed(e);
      }
    });
    jCheckBoxRectVisible.setText("Visible Frame");
    jCheckBoxRectVisible.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxRectVisible_actionPerformed(e);
      }
    });
    jCheckBoxRectVisible.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxRectVisible_actionPerformed(e);
      }
    });
    jCheckBoxRectVisible.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxRectVisible_actionPerformed(e);
      }
    });
    jCheckBoxRectVisible.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxRectVisible_actionPerformed(e);
      }
    });
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel1.setText("Padding");
    jLabel2.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel2.setText("Border");
    jButtonCreate.setMargin(new Insets(0, 0, 0, 0));
    jButtonCreate.setText("Create");
    jButtonCreate.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonCreate_actionPerformed(e);
      }
    });
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
    panel1.add(jTextFieldRectDown,  new XYConstraints(261, 137, 30, 16));
    panel1.add(jTextFieldRectLeft, new XYConstraints(296, 137, 30, 16));
    panel1.add(jTextFieldRectRight, new XYConstraints(330, 137, 30, 16));
    panel1.add(jButtonTextColor, new XYConstraints(2, 2, 46, 22));
    panel1.add(jButtonRectColor, new XYConstraints(54, 2, 46, 22));
    panel1.add(jButtonItalic, new XYConstraints(106, 2, 46, 22));
    panel1.add(jButtonBold, new XYConstraints(158, 2, 46, 22));
    panel1.add(jButtonApply, new XYConstraints(210, 2, 46, 22));
    panel1.add(jButtonCreate, new XYConstraints(262, 2, 46, 22));
    panel1.add(jButton1, new XYConstraints(314, 2, 46, 22));
    panel1.add(jButtonRectDot, new XYConstraints(314, 31, 46, 22));
    panel1.add(jButtonRectSolid, new XYConstraints(262, 31, 46, 22));
    panel1.add(jButtonRectRect, new XYConstraints(210, 31, 46, 22));
    panel1.add(jButtonRectRound, new XYConstraints(158, 31, 46, 22));
    panel1.add(jButtonDown, new XYConstraints(106, 31, 46, 22));
    panel1.add(jButtonUp, new XYConstraints(54, 31, 46, 22));
    panel1.add(jButtonNormal, new XYConstraints(2, 31, 46, 22));
    panel1.add(jComboBoxFont, new XYConstraints(3, 60, 223, 20));
    panel1.add(jComboBoxFontSize, new XYConstraints(242, 60, 118, 20));
    panel1.add(jTextArea1, new XYConstraints(3, 87, 357, 45));
    panel1.add(jCheckBoxRectVisible, new XYConstraints(3, 135, 58, 20));
    panel1.add(jLabel2, new XYConstraints(64, 136, 51, -1));
    panel1.add(jLabel1, new XYConstraints(143, 133, 107, 24));
    panel1.add(jTextFieldRectUp, new XYConstraints(227, 137, 30, 16));
    panel1.add(jTextFieldRectThick, new XYConstraints(111, 138, 30, 16));
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jButtonApply.setFont(new java.awt.Font("Dialog", 0, 12));

    //开始配置初始化参数
    Locale locale = Locale.getDefault();
    Font fonts[];
    GraphicsEnvironment gen=GraphicsEnvironment.getLocalGraphicsEnvironment();
    fonts=gen.getAllFonts();
    String str="";
    String fontName;
    for(int iii=1;iii<=fonts.length ;iii++){
      fontName=fonts[iii-1].getFamily(locale);
      if(str.indexOf(fontName)<0) {
        jComboBoxFont.addItem(fontName);
        str=str+fontName;
      }
    }
    for(int fontSize=1;fontSize<=72;fontSize++){
      jComboBoxFontSize.addItem(new Integer(fontSize));
    }
    buttonGroupFont.add(jButtonNormal);
    buttonGroupFont.add(jButtonUp);
    buttonGroupFont.add(jButtonDown);
    this.getContentPane().add(panel1, BorderLayout.NORTH);

    inited=true;
    userName=null;
    this.noUserInit();
    jComboBoxFontSize.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jComboBoxFontSize_itemStateChanged(e);
      }
    });
    jComboBoxFontSize.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jComboBoxFontSize_actionPerformed(e);
      }
    });

    // Verifier
    jTextFieldRectThick.setInputVerifier(new Verifier(Verifier.INT,true,1,true,10,this,true));
    jTextFieldRectUp.setInputVerifier(new Verifier(Verifier.INT,true,-50,true,50,this,true));
    jTextFieldRectDown.setInputVerifier(new Verifier(Verifier.INT,true,-50,true,50,this,true));
    jTextFieldRectLeft.setInputVerifier(new Verifier(Verifier.INT,true,-50,true,50,this,true));
    jTextFieldRectRight.setInputVerifier(new Verifier(Verifier.INT,true,-50,true,50,this,true));
  }

  /**
   * 当无对象与之关连时的设置
   */
  private void noUserInit(){
    isBold=false;
    isItalic=false;
    isRect=true;
    isDotRect=false;
    isRoundAngle=false;
    fontDirection=this.FONTNORMAL;

    jComboBoxFontSize.setSelectedIndex(15);
    jButtonNormal.setBorder(BorderFactory.createLoweredBevelBorder());
    this.jTextFieldRectThick .setText("1");
    jTextFieldRectThick.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        jTextFieldRectThick_keyPressed(e);
      }
    });
    this.jCheckBoxRectVisible .setSelected(true) ;
    this.jButtonRectRect .setBorder(BorderFactory.createLoweredBevelBorder());
    this.jButtonRectSolid .setBorder(BorderFactory.createLoweredBevelBorder());
    this.jTextFieldRectUp .setText("-3");
    jTextFieldRectUp.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        jTextFieldRectUp_keyPressed(e);
      }
    });
    this.jTextFieldRectDown .setText("2");
    jTextFieldRectDown.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        jTextFieldRectDown_keyPressed(e);
      }
    });
    this.jTextFieldRectLeft .setText("2") ;
    jTextFieldRectLeft.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        jTextFieldRectLeft_keyPressed(e);
      }
    });
    this.jTextFieldRectRight .setText("2");
    jTextFieldRectRight.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        jTextFieldRectRight_keyPressed(e);
      }
    });
    jTextArea1.setAutoscrolls(true);
  }

  void jButtonBold_actionPerformed(ActionEvent e) {
    if(this.isChanging ==true) return;
    if(inited=false) return;
    if(isBold==true){
      jButtonBold.setBorder(BorderFactory.createRaisedBevelBorder());
      isBold=false;
    }
    else{
      jButtonBold.setBorder(BorderFactory.createLoweredBevelBorder());
      isBold=true;
    }
    drawString(true);
  }

  void jButtonItalic_actionPerformed(ActionEvent e) {
    if(this.isChanging ==true) return;
    if(inited=false) return;
    if(isItalic==true){
      jButtonItalic.setBorder(BorderFactory.createRaisedBevelBorder());
      isItalic=false;
    }
    else{
      jButtonItalic.setBorder(BorderFactory.createLoweredBevelBorder());
      isItalic=true;
    }
    drawString(true);
  }

  public void drawString(boolean isChangeImage){
    if(inited=false) return;

    this.isChanging =true;

    int fontStyle;
    if(isBold==true && isItalic==true) fontStyle=Font.BOLD |Font.ITALIC ;
    else if(isBold==true && isItalic==false)  fontStyle=Font.BOLD;
    else if(isBold==false && isItalic==true)  fontStyle=Font.ITALIC ;
    else fontStyle=Font.PLAIN ;

    Font font=new Font((String)jComboBoxFont.getSelectedItem(),fontStyle,
                       jComboBoxFontSize.getSelectedIndex()+1);
    String str=jTextArea1.getText();
    jTextArea1.setForeground(jButtonTextColor.getForeground());
    jTextArea1.setFont(font);
    jTextArea1.setText(str);

    if(textInfo!=null && isChangeImage==true)  changeTextInfo();

    this.isChanging =false;

    userName.rePaintAll();
  }

  private void changeTextInfo(){
    InfoText oldText=textInfo.duplicate();
    InfoText newText=textInfo.duplicate();

    newText.colorRect =jButtonRectColor.getForeground();
    newText.colorText =jButtonTextColor.getForeground();
    newText.distanceBottom =(new Integer((jTextFieldRectDown.getText()))).intValue();
    newText.distanceTop  =(new Integer((jTextFieldRectUp.getText()))).intValue();
    newText.distanceLeft =(new Integer((jTextFieldRectLeft.getText()))).intValue();
    newText.distanceRight =(new Integer((jTextFieldRectRight.getText()))).intValue();
    newText.font=jTextArea1.getFont();
    newText.rectThick =(new Integer((jTextFieldRectThick.getText()))).intValue();
    // 设置字体的方向
    if(this.fontDirection ==this.FONTNORMAL )  newText.rotation =0;
    else if(this.fontDirection ==this.FONTUP ) newText.rotation =PI/2;
    else newText.rotation =-1*PI/2;
    newText.text=jTextArea1.getText();
    if(jCheckBoxRectVisible.isSelected() ==true)  newText.visibleRect= true;
    else newText.visibleRect=false;
    if(this.isDotRect ==true)   newText.isDot =true;
    else                      newText.isDot =false;
    if(this.isRect ==true) newText.isRound =false;
    else                 newText.isRound =true;
    //newText.adjust(new Point(newText.point.x,newText.point.y));
    newText.adjust();
    textInfo=newText.duplicate();

    userName.removeElement(oldText,true,"Text",1,true);
    // by wxs 20031003-2
    //userName.addElement(newText,true,"Text",2,false,-1);
    userName.addElement(newText,true,"Text",2,-1);
  }

  void jButtonApply_actionPerformed(ActionEvent e) {
    if(this.isChanging ==true) return;
    jButtonApply.setEnabled(false);
    jButtonCreate .setEnabled(false);
    drawString(true);
  }

  void jButtonTextColor_actionPerformed(ActionEvent e) {
    if(this.isChanging ==true) return;
    Color c;
    JColorChooser jcc=new JColorChooser();
    c=jcc.showDialog(this,"Please select text color: ", jButtonTextColor.getForeground() ) ;
    if(c==null) return;
    jButtonTextColor.setForeground(c);
    drawString(true);
  }

  void jButtonRectColor_actionPerformed(ActionEvent e) {
    if(this.isChanging ==true) return;
    Color c;
    JColorChooser jcc=new JColorChooser();
    c=jcc.showDialog(this,"Plase select frame color:", jButtonRectColor.getForeground() ) ;
    if(c==null) return;
    jButtonRectColor.setForeground(c);
    drawString(true);
  }

  void jComboBoxFont_itemStateChanged(ItemEvent e) {
    if(this.isChanging ==true) return;
    jButtonApply.setEnabled(true);
    jButtonCreate .setEnabled(true);
  }

  void jComboBoxFontSize_itemStateChanged(ItemEvent e) {
    if(this.isChanging ==true) return;
    jButtonApply.setEnabled(true);
    jButtonCreate .setEnabled(true);
  }

  public void setUser(LayerText layerText,InfoText ti){
    userName=layerText;
    textInfo=ti;
    this.setInterface();
    this.toFront() ;
    drawString(false);
  }

  /**
   * 根据textInfo设置界面。
   */
  private void setInterface(){
    int fontNum;
    fontNum=jComboBoxFont.getItemCount();
    int fontIndex=0;
    for(int iii=1;iii<=fontNum;iii++){
      if(((String)jComboBoxFont.getItemAt(iii-1))==textInfo.font.getName() ){
        fontIndex=iii;
      }
    }
    if(fontIndex==0){
      jComboBoxFont.addItem(textInfo.font.getName());
      jComboBoxFont.setSelectedIndex(fontNum);
    }
    else{
      jComboBoxFont.setSelectedIndex(fontIndex-1);
    }
    jComboBoxFontSize.setSelectedIndex(textInfo.font.getSize()-1);
    if(textInfo.font.isBold()==true){
      jButtonBold.setBorder(BorderFactory.createLoweredBevelBorder());
    }
    else{
      jButtonBold.setBorder(BorderFactory.createRaisedBevelBorder() );
    }
    if(textInfo.font.isItalic() ==true){
      jButtonItalic.setBorder(BorderFactory.createLoweredBevelBorder());
    }
    else{
      jButtonItalic.setBorder(BorderFactory.createRaisedBevelBorder() );
    }
    jButtonTextColor.setForeground(textInfo.colorText);
    jTextArea1.setFont(textInfo.font);
    jTextArea1.setText(textInfo.text);
    if(this.fontDirection ==this.FONTNORMAL ){
      jButtonNormal.setBorder(BorderFactory.createLoweredBevelBorder());
      jButtonUp.setBorder(BorderFactory.createRaisedBevelBorder());
      jButtonDown.setBorder(BorderFactory.createRaisedBevelBorder());
    }
    else if(this.fontDirection ==this.FONTUP ){
      jButtonNormal.setBorder(BorderFactory.createRaisedBevelBorder());
      jButtonUp.setBorder(BorderFactory.createLoweredBevelBorder());
      jButtonDown.setBorder(BorderFactory.createRaisedBevelBorder());
    }
    else if(this.fontDirection ==this.FONTDOWN ){
      jButtonNormal.setBorder(BorderFactory.createRaisedBevelBorder());
      jButtonUp.setBorder(BorderFactory.createRaisedBevelBorder());
      jButtonDown.setBorder(BorderFactory.createLoweredBevelBorder());
    }
    else{
      JOptionPane.showMessageDialog(null,"without consideration");
    }

    jTextFieldRectThick.setText(String.valueOf(textInfo.rectThick));
    jCheckBoxRectVisible .setSelected(textInfo.visibleRect);
    if(textInfo.isRound ==true){
      jButtonRectRound .setBorder(BorderFactory.createLoweredBevelBorder());
      jButtonRectRect .setBorder(BorderFactory.createRaisedBevelBorder() );
    }
    else {
      jButtonRectRound .setBorder(BorderFactory.createRaisedBevelBorder() );
      jButtonRectRect .setBorder(BorderFactory.createLoweredBevelBorder() );
    }
    if(textInfo.isDot  ==true){
      jButtonRectDot .setBorder(BorderFactory.createLoweredBevelBorder());
      jButtonRectSolid .setBorder(BorderFactory.createRaisedBevelBorder());
    }
    else{
      jButtonRectDot .setBorder(BorderFactory.createRaisedBevelBorder());
      jButtonRectSolid .setBorder(BorderFactory.createLoweredBevelBorder());
    }
    jButtonRectColor.setForeground(textInfo.colorRect);
    jTextFieldRectUp .setText(String.valueOf(textInfo.distanceTop ) ) ;
    jTextFieldRectDown .setText(String.valueOf(textInfo.distanceBottom ) ) ;
    jTextFieldRectLeft .setText(String.valueOf(textInfo.distanceLeft ) ) ;
    jTextFieldRectRight .setText(String.valueOf(textInfo.distanceRight ) ) ;

    //设置本类的变量
    isBold=textInfo.font.isBold() ;
    isItalic=textInfo.font.isItalic() ;
    isRect=textInfo.visibleRect ;
    isDotRect=textInfo.isDot;
    isRoundAngle=textInfo.isRound ;
  }

  void setTextInfo(){
    this.jTextArea1 .setText(textInfo.text);
    drawString(true);
  }

  void jButtonNormal_actionPerformed(ActionEvent e) {
    if(this.isChanging ==true) return;
    //jButtonNormal.setBorder(BorderFactory.createLoweredBevelBorder());
    //jButtonUp.setBorder(BorderFactory.createRaisedBevelBorder());
    //jButtonDown.setBorder(BorderFactory.createRaisedBevelBorder());
    this.fontDirection =this.FONTNORMAL;
    this.setInterface();
    this.changeTextInfo() ;
  }

  void jButtonUp_actionPerformed(ActionEvent e) {
    if(this.isChanging ==true) return;
    //jButtonNormal.setBorder(BorderFactory.createRaisedBevelBorder());
    //jButtonUp.setBorder(BorderFactory.createLoweredBevelBorder());
    //jButtonDown.setBorder(BorderFactory.createRaisedBevelBorder());
    this.fontDirection =this.FONTUP ;
    this.setInterface();
    this.changeTextInfo() ;
  }

  void jButtonDown_actionPerformed(ActionEvent e) {
    if(this.isChanging ==true) return;
    //jButtonNormal.setBorder(BorderFactory.createRaisedBevelBorder());
    //jButtonUp.setBorder(BorderFactory.createRaisedBevelBorder());
    //jButtonDown.setBorder(BorderFactory.createLoweredBevelBorder());
    this.fontDirection =this.FONTDOWN ;
    this.setInterface();
    this.changeTextInfo() ;
  }

  void jButtonRectRound_actionPerformed(ActionEvent e) {
    if(this.isChanging ==true) return;
    jButtonRectRound.setBorder(BorderFactory.createLoweredBevelBorder());
    jButtonRectRect.setBorder(BorderFactory.createRaisedBevelBorder());
    this.isRect =false;
    this.changeTextInfo() ;
  }

  void jButtonRectRect_actionPerformed(ActionEvent e) {
    if(this.isChanging ==true) return;
    jButtonRectRound.setBorder(BorderFactory.createRaisedBevelBorder());
    jButtonRectRect.setBorder(BorderFactory.createLoweredBevelBorder());
    this.isRect =true;
    this.changeTextInfo() ;
  }

  void jButtonRectSolid_actionPerformed(ActionEvent e) {
    if(this.isChanging ==true) return;
    jButtonRectSolid.setBorder(BorderFactory.createLoweredBevelBorder());
    jButtonRectDot.setBorder(BorderFactory.createRaisedBevelBorder());
    this.isDotRect =false;
    this.changeTextInfo() ;
  }

  void jButtonRectDot_actionPerformed(ActionEvent e) {
    if(this.isChanging ==true) return;
    jButtonRectSolid.setBorder(BorderFactory.createRaisedBevelBorder());
    jButtonRectDot.setBorder(BorderFactory.createLoweredBevelBorder());
    this.isDotRect =true;
    this.changeTextInfo() ;
  }

  void jButtonCreate_actionPerformed(ActionEvent e) {
    if(this.isChanging ==true) return;
    jButtonApply.setEnabled(false);
    jButtonCreate .setEnabled(false);
    drawString(true);
  }

  void jTextArea1_keyPressed(KeyEvent e) {
    if(this.isChanging ==true) return;
    jButtonApply.setEnabled(true);
    jButtonCreate.setEnabled(true);
  }

  void jTextFieldRectThick_keyPressed(KeyEvent e) {
    if(this.isChanging ==true) return;
    jButtonApply.setEnabled(true);
    jButtonCreate .setEnabled(true);
  }

  void jCheckBoxRectVisible_actionPerformed(ActionEvent e) {
    if(this.isChanging ==true) return;
    drawString(true);
  }

  void jTextFieldRectUp_keyPressed(KeyEvent e) {
    jButtonApply.setEnabled(true);
    jButtonCreate .setEnabled(true);
  }

  void jTextFieldRectLeft_keyPressed(KeyEvent e) {
    jButtonApply.setEnabled(true);
    jButtonCreate .setEnabled(true);
  }

  void jTextFieldRectRight_keyPressed(KeyEvent e) {
    jButtonApply.setEnabled(true);
    jButtonCreate .setEnabled(true);
  }

  void jTextFieldRectDown_keyPressed(KeyEvent e) {
    jButtonApply.setEnabled(true);
    jButtonCreate .setEnabled(true);
  }

  void jButton1_actionPerformed(ActionEvent e) {
    this.setVisible(false) ;
  }

  void jComboBoxFontSize_actionPerformed(ActionEvent e) {

  }

  void jTextArea1_ancestorAdded(AncestorEvent e) {

  }

}