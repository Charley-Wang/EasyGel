package easygel.layer;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.util.*;
import easygel.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogFontChooser extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JComboBox jComboBoxFont = new JComboBox();
  private JTextArea jTextArea1 = new JTextArea();
  private ButtonGroup buttonGroupFont = new ButtonGroup();
  private JButton jButtonBold = new JButton();
  private JButton jButtonItalic = new JButton();
  private JComboBox jComboBoxFontSize = new JComboBox();
  private JButton jButtonCreate = new JButton();
  private JButton jButton1 = new JButton();

  private Font font;
  private boolean isBold;
  private boolean isItalic;
  private boolean inited;

  public DialogFontChooser(Frame frame, String title, boolean modal,
                            Font defaultFont) {
    super(frame, title, modal);
    inited=false;
    this.font =defaultFont;
    try {
      jbInit();
      //pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    panel1.setLayout(xYLayout1);
    jTextArea1.setBorder(BorderFactory.createLoweredBevelBorder());
    jTextArea1.setText("EasyGel Welcome To You.");
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
    panel1.setForeground(SystemColor.desktop);
    jButtonCreate.setMargin(new Insets(0, 0, 0, 0));
    jButtonCreate.setText("Application");
    jButtonCreate.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonCreate_actionPerformed(e);
      }
    });
    jButton1.setMargin(new Insets(0, 0, 0, 0));
    jButton1.setText("OK");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jComboBoxFont.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jComboBoxFont_itemStateChanged(e);
      }
    });
    panel1.add(jButtonItalic, new XYConstraints(2, 2, 46, 22));
    panel1.add(jButtonBold,    new XYConstraints(54, 2, 46, 22));
    panel1.add(jButtonCreate,    new XYConstraints(105, 2, 46, 22));
    panel1.add(jButton1,   new XYConstraints(157, 2, 46, 22));
    panel1.add(jComboBoxFontSize,  new XYConstraints(3, 56, 199, 20));
    panel1.add(jComboBoxFont,        new XYConstraints(3, 28, 199, 20));
    panel1.add(jTextArea1,    new XYConstraints(3, 83, 198, 45));

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
    this.getContentPane().add(panel1, BorderLayout.NORTH);
    this.setInterface() ;
    this.setVisible(true) ;
    this.inited =false;
  }

  void jButtonBold_actionPerformed(ActionEvent e) {
    if(inited=true) return;
    if(isBold==true){
      jButtonBold.setBorder(BorderFactory.createRaisedBevelBorder());
      isBold=false;
    }
    else{
      jButtonBold.setBorder(BorderFactory.createLoweredBevelBorder());
      isBold=true;
    }
    drawString();
  }

  void jButtonItalic_actionPerformed(ActionEvent e) {
    if(inited=true) return;
    if(isItalic==true){
      jButtonItalic.setBorder(BorderFactory.createRaisedBevelBorder());
      isItalic=false;
    }
    else{
      jButtonItalic.setBorder(BorderFactory.createLoweredBevelBorder());
      isItalic=true;
    }
    drawString();
  }

  private void drawString(){
    if(inited=true) return;
    int fontStyle;
    if(isBold==true && isItalic==true) fontStyle=Font.BOLD |Font.ITALIC ;
    else if(isBold==true && isItalic==false)  fontStyle=Font.BOLD;
    else if(isBold==false && isItalic==true)  fontStyle=Font.ITALIC ;
    else fontStyle=Font.PLAIN ;
    Font font1=new Font((String)jComboBoxFont.getSelectedItem(),fontStyle,
                       jComboBoxFontSize.getSelectedIndex()+1);
    String str=jTextArea1.getText();
    jTextArea1.setFont(font1);
    jTextArea1.setText(str);
    font=font1;
  }

  void jButtonApply_actionPerformed(ActionEvent e) {
    drawString();
  }

  private void setInterface(){
    int fontNum;
    fontNum=jComboBoxFont.getItemCount();
    int fontIndex=0;
    for(int iii=1;iii<=fontNum;iii++){
      if(((String)jComboBoxFont.getItemAt(iii-1)).equals(font.getName() )){
          fontIndex=iii;
          break;
      }
    }
    if(fontIndex==0){
      jComboBoxFont.setSelectedIndex(fontNum-1);
    }
    else{
      jComboBoxFont.setSelectedIndex(fontIndex-1);
    }
    this.isItalic =font.isItalic() ;
    this.isBold =font.isBold() ;
    if(isBold==true){
      jButtonBold.setBorder(BorderFactory.createLoweredBevelBorder() );
    }
    else{
      jButtonBold.setBorder(BorderFactory.createRaisedBevelBorder() );
    }
    if(isItalic==true){
      jButtonItalic.setBorder(BorderFactory.createLoweredBevelBorder() );
    }
    else{
      jButtonItalic.setBorder(BorderFactory.createRaisedBevelBorder() );
    }
    jComboBoxFontSize.setSelectedIndex(font.getSize()-1);
    jComboBoxFontSize.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jComboBoxFontSize_itemStateChanged(e);
      }
    });
    /*
    jComboBoxFontSize.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jComboBoxFontSize_itemStateChanged(e);
      }
    });
    */
  }

  void jButton1_actionPerformed(ActionEvent e) {
    this.setVisible(false) ;
  }

  void jButtonCreate_actionPerformed(ActionEvent e) {
    this.drawString() ;
  }

  void jComboBoxFont_itemStateChanged(ItemEvent e) {
    if(this.inited ==true) return;
    this.drawString() ;
  }

  public Font getFont(){
    return font;
  }

  void jComboBoxFontSize_itemStateChanged(ItemEvent e) {
    if(this.inited ==true) return;
    this.drawString() ;
  }

  public void paint(Graphics g){
    super.repaint() ;
  }
}