package easygel;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.awt.event.*;
import easygel.layer.*;
import ij.process.*;
import easygel.image.*;

/**
 *
 * <p>Title: 多项式拟合显示框口</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogPolyfit extends JDialog {
  private TitledBorder titledBorder1;
  private TitledBorder titledBorder2;
  private TitledBorder titledBorder3;
  private TitledBorder titledBorder4;
  private BorderLayout borderLayout1 = new BorderLayout();
  private JPanel jPanel1 = new JPanel();
  private TitledBorder titledBorder5;
  private JPanel jPanel2 = new JPanel();
  private TitledBorder titledBorder6;
  private JPanel jPanel3 = new JPanel();
  private Axis axisX,axisY;
  private  boolean selectAxisX;
  private Curve curve;
  private boolean isInit;
  private FrameMain frameMain;

  public DialogPolyfit(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    isInit=true;
    frameMain=(FrameMain)frame;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogPolyfit() {
    this(null, "", false);
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    titledBorder4 = new TitledBorder("");
    titledBorder5 = new TitledBorder("");
    titledBorder6 = new TitledBorder("");
    titledBorder7 = new TitledBorder("");
    titledBorder8 = new TitledBorder("");
    this.getContentPane().setLayout(borderLayout1);
    jPanel1.setBorder(titledBorder5);
    jPanel1.setMinimumSize(new Dimension(50, 50));
    jPanel1.setPreferredSize(new Dimension(50, 50));
    jPanel1.setLayout(xYLayout1);
    jPanel2.setBorder(titledBorder6);
    jPanel2.setMinimumSize(new Dimension(60, 60));
    jPanel2.setPreferredSize(new Dimension(60, 60));
    jPanel2.setLayout(xYLayout2);
    jPanel3.setBorder(BorderFactory.createLoweredBevelBorder());
    jPanel3.setMinimumSize(new Dimension(370, 280));
    jPanel3.setPreferredSize(new Dimension(370, 280));
    jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jPanel3_mouseClicked(e);
      }
    });
    jLabel1.setText("Begin value");
    jTextFieldBeginNum.setText("120.0");
    jLabel2.setText("End value");
    jTextFieldEndNum.setText("250.0");
    jLabel3.setText("Distance");
    jTextFieldInterval.setText("50.0");
    jLabel4.setText("Height");
    jTextFieldAxisThick.setText("2");
    jButtonAxisColor.setMargin(new Insets(0, 0, 0, 0));
    jButtonAxisColor.setText("Color");
    jButtonAxisColor.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonAxisColor_actionPerformed(e);
      }
    });
    jRadioButtonAxisVisible.setMargin(new Insets(0, 0, 0, 0));
    jRadioButtonAxisVisible.setText("Visible");
    jButtonTextColor.setMargin(new Insets(0, 0, 0, 0));
    jButtonTextColor.setText("Color");
    jButtonTextColor.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonTextColor_actionPerformed(e);
      }
    });
    jButtonTextFont.setMargin(new Insets(0, 0, 0, 0));
    jButtonTextFont.setText("Font");
    jButtonTextFont.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonTextFont_actionPerformed(e);
      }
    });
    jButton5.setMargin(new Insets(0, 0, 0, 0));
    jButton5.setText("Exit");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton5_actionPerformed(e);
      }
    });
    jButton1.setForeground(Color.blue);
    jButton1.setMargin(new Insets(0, 0, 0, 0));
    jButton1.setText("Redraw");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jRadioButtonCurve.setMargin(new Insets(0, 0, 0, 0));
    jRadioButtonCurve.setText("Curve");
    jRadioButtonCurve.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jRadioButtonCurve_itemStateChanged(e);
      }
    });
    jRadioButtonDotVisible.setText("Visible");
    jButtonDotColor.setBorder(BorderFactory.createRaisedBevelBorder());
    jButtonDotColor.setMargin(new Insets(0, 0, 0, 0));
    jButtonDotColor.setText("Color");
    jButtonDotColor.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonDotColor_actionPerformed(e);
      }
    });
    jButton3.setMargin(new Insets(0, 0, 0, 0));
    jButton3.setText("Save");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jPanel4.setBorder(titledBorder7);
    jButton6.setForeground(Color.blue);
    jButton6.setMargin(new Insets(0, 0, 0, 0));
    jButton6.setText("X axis");
    jButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton6_actionPerformed(e);
      }
    });
    jComboBoxDot.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jComboBoxDot_itemStateChanged(e);
      }
    });
    jComboBoxShape.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jComboBoxShape_itemStateChanged(e);
      }
    });
    jComboBoxRadius.addItemListener(new java.awt.event.ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        jComboBoxRadius_itemStateChanged(e);
      }
    });
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(jTextFieldAxisThick,  new XYConstraints(371, 1, 48, 16));
    jPanel1.add(jLabel4,    new XYConstraints(324, 1, 39, 17));
    jPanel1.add(jRadioButtonAxisVisible,  new XYConstraints(7, 25, -1, 13));
    jPanel1.add(jButton6,    new XYConstraints(92, 22, 64, 18));
    jPanel1.add(jLabel1, new XYConstraints(8, 1, 37, 15));
    jPanel1.add(jTextFieldBeginNum,   new XYConstraints(53, 1, 48, 16));
    jPanel1.add(jLabel2,   new XYConstraints(110, 1, 38, 14));
    jPanel1.add(jTextFieldEndNum,   new XYConstraints(156, 1, 48, 16));
    jPanel1.add(jButtonTextFont, new XYConstraints(357, 21, 64, 18));
    jPanel1.add(jButtonAxisColor, new XYConstraints(184, 22, 64, 18));
    jPanel1.add(jButtonTextColor, new XYConstraints(272, 22, 64, 18));
    jPanel1.add(jTextFieldInterval, new XYConstraints(269, 0, 48, 16));
    jPanel1.add(jLabel3, new XYConstraints(213, 2, 46, 13));
    this.getContentPane().add(jPanel2, BorderLayout.EAST);
    jPanel2.add(jRadioButtonCurve, new XYConstraints(1, 1, 49, 17));
    jPanel2.add(jComboBoxDot, new XYConstraints(0, 18, 50, 16));
    jPanel2.add(jRadioButtonDotVisible, new XYConstraints(2, 41, 41, 16));
    jPanel2.add(jComboBoxShape,  new XYConstraints(0, 65, 50, 16));
    jPanel2.add(jComboBoxRadius, new XYConstraints(0, 88, 50, 16));
    jPanel2.add(jButtonDotColor, new XYConstraints(0, 112, 49, 16));
    jPanel2.add(jPanel4,     new XYConstraints(-6, 135, 66, 6));
    jPanel2.add(jButton3,  new XYConstraints(0, 198, 48, 18));
    jPanel2.add(jButton1,      new XYConstraints(0, 156, 48, 18));
    jPanel2.add(jButton5,     new XYConstraints(0, 240, 48, 18));
    this.getContentPane().add(jPanel3, BorderLayout.CENTER);

    // Verifier
    jTextFieldBeginNum.setInputVerifier(new Verifier(Verifier.FLOAT,false,1,false,0,this,false));
    jTextFieldEndNum.setInputVerifier(new Verifier(Verifier.FLOAT,false,1,false,0,this,false));
    jTextFieldInterval.setInputVerifier(new Verifier(Verifier.FLOAT,false,1,false,0,this,false));
    jTextFieldAxisThick.setInputVerifier(new Verifier(Verifier.INT,true,1,true,10,this,true));

    jComboBoxRadius.removeAllItems() ;
    jComboBoxShape.removeAllItems() ;
    for(int ii=1;ii<=10;ii++){
      this.jComboBoxRadius .addItem(String.valueOf(ii) );
    }
    this.jComboBoxShape .addItem("Circle");
    this.jComboBoxShape .addItem("Triangle");
    this.jComboBoxShape .addItem("Rectangle");

    this.selectAxisX =true;
  }

  public void setCurve(Curve cv){
    curve =cv;
    //
    isInit =true;
    jComboBoxDot.removeAllItems();
    jComboBoxDot.addItem("Standard point") ;
    for(int ii=1;ii<=cv.getPolyfitNum() ;ii++){
      jComboBoxDot.addItem(String.valueOf(cv.getPolyfitPoint(ii).no));
    }
    isInit =false;
  }

  public void setAxisBeginEndNum(double begin,double end,
                                 double interval,boolean isAxis){
    if(isAxis==true){
      axisX=new Axis(true,new Point(20,this.jPanel3.getHeight()  -20),
                     this.jPanel3 .getWidth() -40,100.0f,200.0f,20);
      axisX.beginNum =begin;
      axisX.endNum =end;
      axisX.interval =interval;
    }
    else{
      axisY=new Axis(false,new Point(20,this.jPanel3.getHeight()  -20),
                    this.jPanel3 .getHeight() -40,50,90,10);
      axisY.beginNum =begin;
      axisY.endNum =end;
      axisY.interval =interval;
    }
  }

  private void setDot(){
    SerialPoint sp;
    int index=this.jComboBoxDot.getSelectedIndex();
    if(index==0){
      sp=this.curve .getStdPhy() ;
    }
    else{
      sp=this.curve .getPolyfitPoint(index);
    }
    sp.color =this.jButtonDotColor .getForeground() ;
    sp.isVisible =this.jRadioButtonDotVisible.isSelected() ;
    sp.radius =this.jComboBoxRadius .getSelectedIndex() +1;
    sp.shape =this.jComboBoxShape .getSelectedIndex() ;
  }

  private void setCurve(){
    curve.curveColor  =this.jButtonDotColor .getForeground() ;
    curve.isCurveVisible  =this.jRadioButtonDotVisible.isSelected() ;
    curve.curveThick  =this.jComboBoxRadius .getSelectedIndex() +1;
  }

  public  void paint(Graphics g){
    super.paint(g);
    if(isInit==true) return;
    //设置点与曲线
    if(this.jRadioButtonCurve .isSelected() ==false) setDot();
    else setCurve();
    //准备画线板
    Graphics gg;
    gg=this.jPanel3 .getGraphics() ;
    //准备画数轴
    axisX.location =new Point(20,this.jPanel3.getHeight()  -20);
    axisY.location =new Point(20,this.jPanel3.getHeight()  -20);
    axisX.axisLength = this.jPanel3 .getWidth() -40;
    axisY.axisLength = this.jPanel3 .getHeight() -40;
    axisX.drawAxis((Graphics2D)gg);
    axisY.drawAxis((Graphics2D)gg);
    //准备画曲线与点
    Point xP=new Point(axisX.location.x+axisX.axisLength-1,axisX.location.y);
    Point yP=new Point(axisY.location .x,axisY.location .y-axisY.axisLength +1);
    curve.setCoordinate(axisX.location ,xP,yP,
                        axisX.beginNum ,axisX.endNum ,
                        axisY.beginNum ,axisY.endNum );
    curve.drawCurve((Graphics2D)gg,false,null);
  }

  /**
   * 开始在屏幕中绘图,之前必需操作:
   * 1.初始画此类
   * 2.调用setCurve()
   * 3.调用setAxisBeginEndNum()
   */
  public void beginDrawCurve(){
    this.isInit =false;
    this.dispAxisPara(axisY);
    this.dispAxisPara(axisX);
    this.repaint() ;
  }

  void jPanel3_mouseClicked(MouseEvent e) {
    axisX.mouseClicked(e);
  }
  private XYLayout xYLayout1 = new XYLayout();
  private JLabel jLabel1 = new JLabel();
  private JTextField jTextFieldBeginNum = new JTextField();
  private JLabel jLabel2 = new JLabel();
  private JTextField jTextFieldEndNum = new JTextField();
  private JLabel jLabel3 = new JLabel();
  private JTextField jTextFieldInterval = new JTextField();
  private JLabel jLabel4 = new JLabel();
  private JTextField jTextFieldAxisThick = new JTextField();
  private JButton jButtonAxisColor = new JButton();
  private JRadioButton jRadioButtonAxisVisible = new JRadioButton();
  private JButton jButtonTextColor = new JButton();
  private JButton jButtonTextFont = new JButton();
  private XYLayout xYLayout2 = new XYLayout();
  private JButton jButton5 = new JButton();
  private JButton jButton1 = new JButton();
  private JRadioButton jRadioButtonCurve = new JRadioButton();
  private JRadioButton jRadioButtonDotVisible = new JRadioButton();
  private JButton jButtonDotColor = new JButton();
  private JComboBox jComboBoxShape = new JComboBox();
  private JButton jButton3 = new JButton();
  private JPanel jPanel4 = new JPanel();
  private TitledBorder titledBorder7;
  private JComboBox jComboBoxRadius = new JComboBox();
  private TitledBorder titledBorder8;
  private JButton jButton6 = new JButton();
  private JComboBox jComboBoxDot = new JComboBox();

  private void dispDots(SerialPoint sp){
    this.jButtonDotColor .setForeground(sp.color );
    this.jComboBoxRadius .setSelectedIndex(sp.radius -1);
    this.jComboBoxShape .setSelectedIndex(sp.shape );
    this.jRadioButtonDotVisible .setSelected(sp.isVisible );
  }

  private void dispAxisPara(Axis axis){
    this.jTextFieldBeginNum .setText(String.valueOf( axis.beginNum ));
    this.jTextFieldEndNum .setText(String.valueOf(axis.endNum ));
    this.jTextFieldInterval .setText(String.valueOf(axis.interval ));
    this.jTextFieldAxisThick .setText(String.valueOf(axis.axisThickness ));
    this.jButtonAxisColor .setForeground(axis.axisColor );
    this.jButtonTextColor .setForeground(axis.textColor );
    this.jButtonTextFont .setFont(axis.textFont );
    this.jRadioButtonAxisVisible .setSelected(axis.textVisible);
  }

  private void setAxisPara(Axis axis){
    axis.beginNum =Double.parseDouble(this.jTextFieldBeginNum.getText());
    axis.endNum =Double.parseDouble(this.jTextFieldEndNum .getText() );
    axis.interval =Double.parseDouble(this.jTextFieldInterval.getText());
    axis.axisThickness =Integer.parseInt(this.jTextFieldAxisThick.getText());
    axis.axisColor=this.jButtonAxisColor .getForeground() ;
    axis.textColor =this.jButtonTextColor .getForeground() ;
    axis.textVisible =this.jRadioButtonAxisVisible.isSelected() ;
  }

  private void selectAxis(){
    if(this.selectAxisX ==true){
      this.dispAxisPara(axisX);
    }
    else{
      this.dispAxisPara(axisY);
    }
  }


  void jButton1_actionPerformed(ActionEvent e) {
    if(this.selectAxisX ==true) this.setAxisPara(axisX);
    else this.setAxisPara(axisY);
    this.repaint() ;
  }

  void jButtonTextFont_actionPerformed(ActionEvent e) {
    DialogFontChooser fontChooser;
    if(this.selectAxisX ==true){
      fontChooser=new DialogFontChooser(frameMain,"",true,axisX.textFont );
      axisX.textFont =fontChooser.getFont() ;
    }
    else{
      fontChooser=new DialogFontChooser(frameMain,"",true,axisY.textFont );
      axisY.textFont =fontChooser.getFont() ;
    }
    this.repaint() ;
  }

  public int getDrawXLen(){
    return this.jPanel3 .getWidth() -40;
  }

  public int getDrawYLen(){
    return this.jPanel3 .getHeight() -40;
  }

  void jButtonAxisColor_actionPerformed(ActionEvent e) {
    Color c;
    JColorChooser jcc=new JColorChooser();
    c=jcc.showDialog(this,"Please select a color: ", jButtonAxisColor.getForeground() ) ;
    if(c==null) return;
    jButtonAxisColor.setForeground(c);
  }

  void jButtonTextColor_actionPerformed(ActionEvent e) {
    Color c;
    JColorChooser jcc=new JColorChooser();
    c=jcc.showDialog(this,"Please select a color", jButtonTextColor.getForeground() ) ;
    if(c==null) return;
    jButtonTextColor.setForeground(c);
  }

  void jButton6_actionPerformed(ActionEvent e) {
    if(this.selectAxisX ==true){
      this.selectAxisX =false;
      jButton6.setText("Y axis");
      this.dispAxisPara(this.axisY );
    }
    else{
      this.selectAxisX =true;
      jButton6.setText("X axis");
      this.dispAxisPara(this.axisX );
    }
  }

  void jComboBoxDot_itemStateChanged(ItemEvent e) {
    SerialPoint sp;
    if(this.jComboBoxDot .getSelectedIndex() ==0){
      sp=curve.getStdPhy() ;
    }
    else {
      sp=curve.getPolyfitPoint(this.jComboBoxDot .getSelectedIndex() );
    }
    this.dispDots(sp);
    this.repaint();
  }

  void jRadioButtonCurve_itemStateChanged(ItemEvent e) {
    if(this.jRadioButtonCurve .isSelected() ==true){
      this.jComboBoxDot .setEnabled(false) ;
      this.jButtonDotColor .setForeground(curve.curveColor  );
      this.jComboBoxRadius .setSelectedIndex(curve.curveThick -1);
      this.jComboBoxShape .setEnabled(false) ;
      this.jRadioButtonDotVisible .setSelected(curve.isCurveVisible );
    }
    else{
      this.jComboBoxDot .setEnabled(true) ;
      this.jComboBoxShape .setEnabled(true) ;
    }
  }

  void jButtonDotColor_actionPerformed(ActionEvent e) {
    Color c;
    JColorChooser jcc=new JColorChooser();
    c=jcc.showDialog(this,"Please select color", jButtonDotColor.getForeground() ) ;
    if(c==null) return;
    jButtonDotColor.setForeground(c);
  }

  void jButton5_actionPerformed(ActionEvent e) {
    this.setVisible(false);
  }

  void jButton3_actionPerformed(ActionEvent e) {
    save();
  }

  private void save(){
    int format=OpenFile.JPEG;
    String pathName[]=ImageSaver.selectFileToSave(format,this.frameMain,"Please select a file");
    int width=this.jPanel3.getWidth();
    int height=this.jPanel3.getHeight();
    OpenFileInformation infor=ImageSaver.createIPFromTmp(format,this.frameMain,
        true,Color.white,width,height,pathName[0],pathName[1]);
    ImageProcessor ip=infor.ip;

    //super.paint(g);
    //if(isInit==true) return;
    //设置点与曲线
    if(this.jRadioButtonCurve .isSelected() ==false) setDot();
    else setCurve();
    //准备画线板
    Graphics gg;
    gg=this.jPanel3 .getGraphics() ;
    //准备画数轴
    axisX.location =new Point(20,this.jPanel3.getHeight()  -20);
    axisY.location =new Point(20,this.jPanel3.getHeight()  -20);
    axisX.axisLength = this.jPanel3 .getWidth() -40;
    axisY.axisLength = this.jPanel3 .getHeight() -40;
    //axisX.drawAxis((Graphics2D)gg);
    //axisY.drawAxis((Graphics2D)gg);
    axisX.saveAxis(ip,this);
    axisY.saveAxis(ip,this);
    //准备画曲线与点
    Point xP=new Point(axisX.location.x+axisX.axisLength-1,axisX.location.y);
    Point yP=new Point(axisY.location .x,axisY.location .y-axisY.axisLength +1);
    curve.setCoordinate(axisX.location ,xP,yP,
                        axisX.beginNum ,axisX.endNum ,
                        axisY.beginNum ,axisY.endNum );
    curve.drawCurve((Graphics2D)gg,true,ip);

    ImageSaver.saveIPAsImage(format,infor.fileInfo.directory,
                         infor.fileInfo.fileName,infor.ip,
                         infor.fileInfo,infor.bmp,true,this.frameMain);
  }

  void jComboBoxShape_itemStateChanged(ItemEvent e) {
    this.repaint();
  }

  void jComboBoxRadius_itemStateChanged(ItemEvent e) {
    this.repaint();
  }

  // end of the class
}

class Axis{
  public boolean isXAxis;
  public Point location;
  public int axisLength;
  public double beginNum;
  public double endNum;
  public double interval;
  public int axisThickness;
  public Color axisColor;
  public boolean textVisible;
  public Color textColor;
  public Font textFont;
  public Axis(){}
  public Axis(boolean isX,Point loc,int axisLen,
              double beginN,double endN,double inter){
    isXAxis=isX;
    location=loc;
    axisLength=axisLen;
    beginNum=beginN;
    endNum=endN;
    interval=inter;
    //默认参数
    this.axisThickness =2;
    this.axisColor =Color.red ;
    this.textVisible =true;
    this.textColor =Color.black ;
    this.textFont =new Font("Dialog",Font.PLAIN,12);
  }

  public  void drawAxis(Graphics2D g2d){
    BasicStroke basicStroke;
    basicStroke= new BasicStroke(this.axisThickness);
    g2d.setStroke(basicStroke);
    g2d.setColor(this.axisColor);
    int scaleNum,scaleLen;
    scaleNum=(int)((this.endNum -this.beginNum )/this.interval)+1;
    scaleLen=(int)(this.axisLength /scaleNum);
    int precision=this.getPrecision() ;
    if(isXAxis==true){
      g2d.drawLine(location.x,location.y,
                   location.x+this.axisLength,location.y);
      for(int ii=1;ii<=scaleNum;ii++){
        int xx=(ii-1)*scaleLen+this.location .x ;
        int yy1=this.location .y;
        int yy2=yy1+5;
        g2d.drawLine(xx,yy1,xx,yy2);
      }
      if(this.textVisible ==true){
        g2d.setColor(this.textColor);
        g2d.setFont(this.textFont);
        for(int ii=1;ii<=scaleNum;ii++){
          int xx=(ii-1)*scaleLen+this.location .x;
          int yy=this.location .y+12;
          double num=this.beginNum +this.interval *(ii-1);
          String str=String.valueOf(num);
            String str2;
            if(str.length() >precision) str2=str.substring(0,precision);
           else str2=str;
           g2d.drawString(str2,xx,yy);
        }
      }
    }
    else{
      g2d.drawLine(location.x,location.y,
                   location.x,location.y-this.axisLength);
      for(int ii=1;ii<=scaleNum;ii++){
        int xx1=20-5;
        int xx2=20;
        int yy=this.location .y-(ii-1)*scaleLen;
         g2d.drawLine(xx1,yy,xx2,yy);
      }
      if(this.textVisible ==true){
        g2d.setColor(this.textColor);
        g2d.setFont(this.textFont);
        for(int ii=1;ii<=scaleNum;ii++){
          int xx=20;
          int yy=this.location .y-(ii-1)*scaleLen;
           double num=this.beginNum +this.interval *(ii-1);
           String str=String.valueOf(num);
           String str2;
           if(str.length() >precision) str2=str.substring(0,precision);
           else str2=str;
           g2d.rotate(-3.1415926/2,xx,yy);
           g2d.drawString(str2,xx,yy-8);
           g2d.rotate(3.1415926/2,xx,yy);
        }
      }
    }
  }

  public void saveAxis(ImageProcessor ip,DialogPolyfit di){
    //BasicStroke basicStroke;
    //basicStroke= new BasicStroke(this.axisThickness);
    ip.setLineWidth(this.axisThickness);
    //g2d.setStroke(basicStroke);
    ip.setColor(this.axisColor);
    int scaleNum,scaleLen;
    scaleNum=(int)((this.endNum -this.beginNum )/this.interval)+1;
    scaleLen=(int)(this.axisLength /scaleNum);
    int precision=this.getPrecision() ;
    if(isXAxis==true){
      ip.drawLine(location.x,location.y,
                   location.x+this.axisLength,location.y);
      for(int ii=1;ii<=scaleNum;ii++){
        int xx=(ii-1)*scaleLen+this.location .x ;
        int yy1=this.location .y;
        int yy2=yy1+5;
        ip.drawLine(xx,yy1,xx,yy2);
      }
      if(this.textVisible ==true){
        ip.setColor(this.textColor);
        ip.setFont(this.textFont);
        for(int ii=1;ii<=scaleNum;ii++){
          int xx=(ii-1)*scaleLen+this.location .x;
          int yy=this.location .y+12;
          double num=this.beginNum +this.interval *(ii-1);
          String str=String.valueOf(num);
            String str2;
            if(str.length() >precision) str2=str.substring(0,precision);
           else str2=str;
           ip.drawString(str2,xx,yy);
        }
      }
    }
    else{
      ip.drawLine(location.x,location.y,
                   location.x,location.y-this.axisLength);
      for(int ii=1;ii<=scaleNum;ii++){
        int xx1=20-5;
        int xx2=20;
        int yy=this.location .y-(ii-1)*scaleLen;
         ip.drawLine(xx1,yy,xx2,yy);
      }
      if(this.textVisible ==true){
        ip.setColor(this.textColor);
        ip.setFont(this.textFont);
        FontMetrics fm;
        fm=di.getFontMetrics(this.textFont);
        for(int ii=1;ii<=scaleNum;ii++){
          int xx=20;
          int yy=this.location .y-(ii-1)*scaleLen;
           double num=this.beginNum +this.interval *(ii-1);
           String str=String.valueOf(num);
           String str2;
           if(str.length() >precision) str2=str.substring(0,precision);
           else str2=str;
           //g2d.rotate(-3.1415926/2,xx,yy);
           ip.drawString(str2,xx,yy-8+fm.getLeading());
           //g2d.rotate(3.1415926/2,xx,yy);
        }
      }
    }
  }


  private int getPrecision(){
    String str1=String.valueOf(this.beginNum);
    String str2=String.valueOf(this.endNum );
    String str3=String.valueOf(this.interval);
    int len;
    len=str1.length() ;
    if(len<str2.length() ) len=str2.length() ;
    if(len<str3.length() ) len=str3.length() ;
    int len2=(int)(Math.log(( this.endNum -this.beginNum )/
                            this.interval )/Math.log(10.0f));
    return len+len2+1;
  }

  public void mouseClicked(MouseEvent e){

  }
}

