package easygel;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import ij.process.*;
import java.awt.event.*;
import javax.swing.event.*;
//import java.beans.*;

public class DialogColor extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private TitledBorder titledBorder1;
  private JPanel jPanel2 = new JPanel();
  private TitledBorder titledBorder2;
  private XYLayout xYLayout3 = new XYLayout();
  private JLabel jLabel4 = new JLabel();
  private JComboBox jComboBox1 = new JComboBox();
  private JTextField jTextField4 = new JTextField();
  private JLabel jLabel5 = new JLabel();
  private JLabel jLabel6 = new JLabel();
  private JTextField jTextField5 = new JTextField();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();

  private FrameMain frameMain;
  private DialogImage dialogImage;
  private ImageProcessor ip;
  private boolean isInit;
  private  int colors=8;
  private int selColor; //0-7
  private String []colorMethod;
  private Color []colorBegin;
  private Color []colorEnd;

  public DialogColor(FrameMain frame, String title, boolean modal) {
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

  public DialogColor() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    panel1.setLayout(xYLayout1);
    jPanel2.setBorder(titledBorder2);
    jPanel2.setLayout(xYLayout3);
    jLabel4.setText("Dim Lisht Color:");
    jLabel5.setText("Begin Color:");
    jLabel6.setText("End Color:");
    jButton2.setMargin(new Insets(2, 10, 2, 10));
    jButton2.setText("Dim Lisht Color");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton3.setText("OK");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    this.setTitle("Image Optimization");
    jTextField4.setEnabled(false);
    jTextField4.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        jTextField4_mousePressed(e);
      }
    });
    jTextField5.setEnabled(false);
    jTextField5.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        jTextField5_mousePressed(e);
      }
    });
    jComboBox1.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jComboBox1_itemStateChanged(e);
      }
    });
    getContentPane().add(panel1);
    jPanel2.add(jLabel4,  new XYConstraints(23, 9, 90, 19));
    jPanel2.add(jComboBox1,  new XYConstraints(124, 8, 194, 20));
    jPanel2.add(jLabel5, new XYConstraints(24, 53, 84, 17));
    jPanel2.add(jLabel6,  new XYConstraints(178, 50, 75, 20));
    jPanel2.add(jTextField4, new XYConstraints(294, 50, 22, 21));
    jPanel2.add(jTextField5, new XYConstraints(124, 49, 22, 21));
    panel1.add(jButton2,   new XYConstraints(73, 98, 65, 23));
    panel1.add(jButton3,  new XYConstraints(227, 98, 65, 23));
    panel1.add(jPanel2,         new XYConstraints(1, 1, 358, 89));

    //

    dialogImage=frameMain.getCurrentImage();
    ip=dialogImage.getIP();

    colorMethod=new String[colors];
    colorBegin=new Color[colors];
    colorEnd=new Color[colors];
    colorMethod[0]="Ethidium Bromide (EB)";
    colorMethod[1]="Coomassie Brilliant Blue";
    colorMethod[2]="Silver Staining";
    colorMethod[3]="Sybr Green";
    colorMethod[4]="Red";
    colorMethod[5]="Green";
    colorMethod[6]="Blue";
    colorMethod[7]="Custom";
    colorBegin[0]=new Color(0,0,0);
    colorEnd[0]=new Color(255,255,160);
    colorBegin[1]=new Color(0,0,128);
    colorEnd[1]=new Color(236,236,255);
    colorBegin[2]=new Color(102,51,0);
    colorEnd[2]=new Color(255,255,236);
    colorBegin[3]=new Color(0,0,0);
    colorEnd[3]=new Color(0,255,18);
    colorBegin[4]=new Color(255,0,0);
    colorEnd[4]=new Color(255,255,255);
    colorBegin[5]=new Color(0,255,0);
    colorEnd[5]=new Color(255,255,255);
    colorBegin[6]=new Color(0,0,255);
    colorEnd[6]=new Color(255,255,255);
    colorBegin[7]=new Color(255,255,255);
    colorEnd[7]=new Color(0,0,0);
    String str;
    for(int ii=1;ii<=colors;ii++){
       str=colorMethod[ii-1];
      this.jComboBox1.addItem(str);
    }
    this.jTextField4 .setBackground(colorBegin[0]);
    this.jTextField5 .setBackground(colorEnd[0]);
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
    this.dialogImage.saveIP(ip.duplicate(),1);

    /** 转化前图象格式是否是ColorRGB24格式 */
    boolean isRGB24BeforeConvert;
    if(this.frameMain.getCurrentImage().getImageFormat()==DialogImage.COLOR_RGB)
      isRGB24BeforeConvert=true;
    else isRGB24BeforeConvert=false;

    // convert to RGB 24 bits
    // added by wxs 20030928
    this.dialogImage.convertToRGB24();
    this.ip=this.frameMain.getCurrentImage().getIP();

    Image img;
    int width,height;
    Color c;
    int process;
    int cint=0;
    width=dialogImage.getImageWidth();
    height=dialogImage.getImageHeight();
    int temp,r1,r2,g1,g2,b1,b2;
    r1=colorBegin[selColor].getRed();
    r2=colorEnd[selColor].getRed();
    g1=colorBegin[selColor].getGreen();
    g2=colorEnd[selColor].getGreen();
    b1=colorBegin[selColor].getBlue();
    b2=colorEnd[selColor].getBlue();
    if(r1>r2){ temp=r1;      r1=r2;      r2=temp;    }
    if(g1>g2){ temp=g1;      g1=g2;      g2=temp;    }
    if(b1>b2){ temp=b1;      b1=b2;      b2=temp;    }
    process=0;
    //ip.convertToRGB();
    int tmp;
    for(int ii=1;ii<=width;ii++){
      this.setTitle("Now is processing "+String.valueOf(process*100*ii/width) +"%") ;
      for(int jj=1;jj<=height;jj++){
        if(isRGB24BeforeConvert==true){
          c=new Color(ip.getPixel(ii-1,jj-1));
          cint=cc(r1,r2,c.getRed())*256*256+cc(g1,g2,c.getGreen())*256+cc(b1,b2,c.getBlue());
        }
        else{
          tmp=(int)(ip.getPixelValue(ii-1,jj-1)+0.5);
          c=new Color(cc(r1,r2,tmp),cc(g1,g2,tmp),cc(b1,b2,tmp));
          //cint=cc(r1,r2,tmp)*256*256+cc(g1,g2,tmp)*256+cc(b1,b2,tmp);
          //c=new Color(200,0,0);
          cint=c.getRGB();
        }
        ip.putPixel(ii-1,jj-1,cint);
      }
    }
    this.setTitle("OK!");
    img=ip.createImage();
    dialogImage.setImage2(img);
    dialogImage.paintImage();
  }


  void jButton4_actionPerformed(ActionEvent e) {
    Image img;
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
    this.jTextField4 .setBackground(colorBegin[id-1]);
    this.jTextField5 .setBackground(colorEnd[id-1]);
    selColor=id-1;
  }

  void jTextField4_mousePressed(MouseEvent e) {
    if(selColor==7){
      Color c;
      JColorChooser jcc=new JColorChooser();
      c=jcc.showDialog(this,"Please select begin color:",colorBegin[7]) ;
      colorBegin[7]=c;
      this.jTextField4 .setBackground(c);
    }
  }

  void jTextField5_mousePressed(MouseEvent e) {
    if(selColor==7){
      Color c;
      JColorChooser jcc=new JColorChooser();
      c=jcc.showDialog(this,"Please select end color:",colorEnd[7]) ;
      colorEnd[7]=c;
      this.jTextField5 .setBackground(c);
    }
  }

  void jButton3_actionPerformed(ActionEvent e) {
    this.dispose() ;
  }
}