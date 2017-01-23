package easygel;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import ij.process.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.beans.*;

public class DialogOptimum extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JPanel jPanel1 = new JPanel();
  private TitledBorder titledBorder1;
  private XYLayout xYLayout2 = new XYLayout();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JSlider jSlider1 = new JSlider();
  private JSlider jSlider2 = new JSlider();
  private JSlider jSlider3 = new JSlider();
  private JTextField jTextField1 = new JTextField();
  private JTextField jTextField2 = new JTextField();
  private JTextField jTextField3 = new JTextField();
  private TitledBorder titledBorder2;
  private JButton jButton3 = new JButton();

  private FrameMain frameMain;
  private DialogImage dialogImage;
  private ImageProcessor ip;
  private ImageProcessor oldip;
  private JButton jButton4 = new JButton();
  private boolean isInit;
  private  int colors=8;
  private int selColor; //0-7
  private String []colorMethod;
  private Color []colorBegin;
  private Color []colorEnd;

  public DialogOptimum(FrameMain frame, String title, boolean modal) {
    super(frame, title, modal);
    frameMain=frame;
    isInit=true;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogOptimum() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    panel1.setLayout(xYLayout1);
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(xYLayout2);
    jLabel1.setText("Brightness: ");
    jLabel2.setText("Contrast: ");
    jLabel3.setText("gamma：");
    jTextField1.setText("jTextField1");
    jTextField2.setText("jTextField2");
    jTextField3.setText("jTextField3");
    jButton3.setText("OK");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jSlider1_stateChanged(e);
      }
    });
    jButton4.setText("Cancel");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton4_actionPerformed(e);
      }
    });
    jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jSlider2_stateChanged(e);
      }
    });
    jSlider3.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jSlider3_stateChanged(e);
      }
    });
    jTextField1.setEditable(false);
    jTextField2.setEditable(false);
    jTextField3.setEditable(false);
    this.setTitle("Image Optimization");
    getContentPane().add(panel1);
    panel1.add(jPanel1,     new XYConstraints(4, 5, 358, 130));
    jPanel1.add(jLabel1,  new XYConstraints(23, 6, 81, 26));
    jPanel1.add(jLabel3,   new XYConstraints(23, 82, 62, 23));
    jPanel1.add(jLabel2, new XYConstraints(23, 44, 70, 24));
    jPanel1.add(jSlider1, new XYConstraints(88, 7, 170, 22));
    jPanel1.add(jSlider2,   new XYConstraints(88, 45, 170, 22));
    jPanel1.add(jSlider3,   new XYConstraints(88, 83, 170, 22));
    jPanel1.add(jTextField1,           new XYConstraints(272, 10, 50, 18));
    jPanel1.add(jTextField2,                 new XYConstraints(272, 48, 50, 18));
    jPanel1.add(jTextField3,       new XYConstraints(272, 85, 50, 18));
    panel1.add(jButton3,   new XYConstraints(239, 150, 65, 23));
    panel1.add(jButton4, new XYConstraints(56, 150, 65, 23));

    //

    dialogImage=frameMain.getCurrentImage();
    ip=dialogImage.getIP();
    oldip=ip.duplicate();
    this.jSlider1 .setMinimum(-100);
    this.jSlider1 .setMaximum(100 );
    this.jSlider2 .setMinimum(-100);
    this.jSlider2 .setMaximum(100 );
    this.jSlider3 .setMinimum(-100);
    this.jSlider3 .setMaximum(100);
    this.jSlider1 .setValue(0);
    this.jSlider2 .setValue(0);
    this.jSlider3 .setValue(0);
    this.jTextField1 .setText("0");
    this.jTextField2 .setText("0");
    this.jTextField3 .setText("1");

    String str;
    selColor=0;
    isInit=false;
  }

  void jButton1_actionPerformed(ActionEvent e) {
    Image img;
    ip.invert();
    img=ip.createImage();
    dialogImage.setImage2(img);
    dialogImage.paintImage();
  }

  //begin must less than end !!!
  int cc(int begin,int end,int c){
    double v;
    int p;
    v=((c-1)*(end-begin)/255)+begin;
    p=(int)v;
    if(p>255) p=255;
    if(p<0) p=0;
    return p;
  }

  void jButton2_actionPerformed(ActionEvent e) {
    oldip=ip.duplicate();
    this.dialogImage .saveIP(ip,1) ;

    Image img;
    int width,height;
    Color c;
    int process;
    int cint;
    width=dialogImage.getImageWidth();
    height=dialogImage.getImageHeight();
    int temp,r1,r2,g1,g2,b1,b2;
    r1=colorBegin[selColor].getRed();
    r2=colorEnd[selColor].getRed();
    g1=colorBegin[selColor].getGreen();
    g2=colorEnd[selColor].getGreen();
    b1=colorBegin[selColor].getBlue();
    b2=colorEnd[selColor].getBlue();
    if(r1>r2){
      temp=r1;
      r1=r2;
      r2=temp;
    }
    if(g1>g2){
      temp=g1;
      g1=g2;
      g2=temp;
    }
    if(b1>b2){
      temp=b1;
      b1=b2;
      b2=temp;
    }
    process=0;
    ip.convertToRGB();
    for(int ii=1;ii<=width;ii++){
      this.setTitle("Processing "+String.valueOf(process*100*ii/width) +"%") ;
      for(int jj=1;jj<=height;jj++){
         c=new Color( ip.getPixel(ii,jj));
         cint=cc(r1,r2,c.getRed())*256*256+
              cc(g1,g2,c.getGreen())*256+
              cc(b1,b2,c.getBlue());
         ip.putPixel(ii,jj,cint);
      }
    }
    this.setTitle("OK!");
    img=ip.createImage();
    dialogImage.setImage2(img);
    dialogImage.paintImage();
  }

  void jSlider1_stateChanged(ChangeEvent e) {
    this.setBCG() ;
  }

  void jButton4_actionPerformed(ActionEvent e) {
    Image img;
    ip=oldip.duplicate();
    img=ip.createImage();
    dialogImage.setImage2(img);
    dialogImage.paintImage();
    this.jSlider1 .setValue(0);
    this.jSlider2 .setValue(0);
    this.jSlider3 .setValue(0);
    this.jTextField1 .setText("0");
    this.jTextField2 .setText("0");
    this.jTextField3 .setText("1");
  }

  void jSlider2_stateChanged(ChangeEvent e) {
    this.setBCG();
  }

  void jSlider3_stateChanged(ChangeEvent e) {
    this.setBCG();
  }

  void setBCG(){
    if(isInit==true) return;
    Image img;
    ip=oldip.duplicate();
    this.jTextField1 .setText(Integer.toString( this.jSlider1 .getValue() ));
    this.jTextField2.setText(Integer.toString( this.jSlider2.getValue() ));
    ip.add((this.jSlider1.getValue()));
    ip.multiply((this.jSlider2.getValue()+100)/100.0f);

    double gammav=1.0f;
    if(this.jSlider3 .getValue() <0){
      gammav=(this.jSlider3 .getValue() +100.0f)*(1.0f-0.2f)/100.0f+0.2f;
    }
    else if(this.jSlider3 .getValue() >0){
      gammav=(this.jSlider3 .getValue())*(5.0f-1.0f)/100.0f+1.0f;
    }
    int intv=(int)(gammav*100.0f);
    gammav=intv/100.0f;
    String str=Double.toString( gammav);
    if(str.length() >4) str=str.substring(0,4);
    this.jTextField3.setText(str);
    ip.gamma(gammav);
    img=ip.createImage();
    dialogImage.setImage2(img);
    dialogImage.paintImage();
  }

  void jComboBox1_itemStateChanged(ItemEvent e) {
    if(isInit==true) return;
    int id=0;
    for(int ii=1;ii<=colors;ii++){
      if(e.getItem() .equals(colorMethod[ii-1])){
        id=ii;
       break;
      }
    }
    selColor=id-1;
  }

  void jTextField4_mousePressed(MouseEvent e) {
    if(selColor==7){
      Color c;
      JColorChooser jcc=new JColorChooser();
      c=jcc.showDialog(this,"Please select begin color: ",colorBegin[7]) ;
      colorBegin[7]=c;
    }
  }

  void jTextField5_mousePressed(MouseEvent e) {
    if(selColor==7){
      Color c;
      JColorChooser jcc=new JColorChooser();
      c=jcc.showDialog(this,"Please select end color",colorEnd[7]) ;
      colorEnd[7]=c;
    }
  }

  void jButton3_actionPerformed(ActionEvent e) {
    this.dialogImage .saveIP(oldip,1);
    this.dispose() ;
  }
}