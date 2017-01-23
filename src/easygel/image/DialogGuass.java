package easygel.image;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import ij.process.*;
import java.awt.event.*;
import easygel.*;
import javax.swing.border.*;
import easygel.uiti.*;
import easygel.layer.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogGuass extends JDialog {
  private BorderLayout borderLayout1 = new BorderLayout();
  private TitledBorder titledBorder1;
  private JPanel jPanel1 = new JPanel();
  private TitledBorder titledBorder2;
  private JPanel jPanel2 = new JPanel();
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private FrameMain frameMain;
  private JButton jButton11 = new JButton();
  private JButton jButton1 = new JButton();
  private JButton jButton12 = new JButton();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  private JButton jButton4 = new JButton();
  private JButton jButton5 = new JButton();
  private JButton jButton6 = new JButton();
  private JButton jButton7 = new JButton();
  private JButton jButton8 = new JButton();
  private JButton jButton9 = new JButton();
  private JButton jButton10 = new JButton();
  private JButton jButton13 = new JButton();
  private JButton jButton14 = new JButton();
  private JButton jButton15 = new JButton();
  private JButton jButton16 = new JButton();
  private JButton jButton17 = new JButton();
  private JButton jButton18 = new JButton();
  private JButton jButton19 = new JButton();
  private JButton jButton20 = new JButton();

  /** only for painting once while initailing */
  private int drawNum;

  /** 被处理的对象 */
  private InfoCell cell;
  /** 是否显示标识 */
  private boolean isDispID;
  private Color colorHist;
  private Color colorFit;
  private int colorLine;
  /** 条带的微彩色,1=Red,2=Green,3=Blue,4=Gray */
  private int colorLane;
  private Color colorLeftRight;
  private Color colorGround;

  private double laneScaleV;
  private double histScaleV;
  private double scaleH;

  /** 泳道原左右上下边界坐标 */
  private int leftX=0,rightX=0,upY=0,downY=0;

  private int []hist;
  private int paintBegin;
  private int paintEnd;

  /** 条带的中线,左右范围值 */
  private int middleLoc;
  private int leftV;
  private int rightV;

  private int groundV;
  private int lineMinGray;
  private int lineMaxGray;

  private int xInit=5;

  private boolean isMoving;
  private int oriV;
  private Point oriPoint;
  /** moveSign==1 is ground, moveSign==2 is left, moveSign==3 is right */
  private int moveSign;

  private boolean isFit;
  private double []formula;
  private double []result;
  public double phi,mu,oriGray,newGray;

  public DialogGuass(FrameMain frame, String title, boolean modal) {
    super(frame, title, modal);
    this.drawNum=0;
    this.frameMain = frame;
    this.isMoving = false;
    this.isFit=false;
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    this.setSize(new Dimension(726, 472));
    this.getContentPane().setLayout(borderLayout1);
    this.setBackground(new Color(255,255,255));
    jPanel1.setBorder(titledBorder2);
    jPanel1.setMinimumSize(new Dimension(60, 60));
    jPanel1.setPreferredSize(new Dimension(60, 60));
    jPanel1.setLayout(gridBagLayout1);
    jPanel2.setBackground(Color.white);
    jPanel2.setBorder(BorderFactory.createLoweredBevelBorder());
    jPanel2.setPreferredSize(new Dimension(500, 360+100));
    jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseReleased(MouseEvent e) {
        jPanel2_mouseReleased(e);
      }
      public void mousePressed(MouseEvent e) {
        jPanel2_mousePressed(e);
      }
    });
    jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        jPanel2_mouseMoved(e);
      }
      public void mouseDragged(MouseEvent e) {
        jPanel2_mouseDragged(e);
      }
    });
    jButton11.setBackground(Color.orange);
    jButton11.setForeground(Color.blue);
    jButton11.setMargin(new Insets(0, 0, 0, 0));
    jButton11.setText("Gaussian Fitting");
    jButton11.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton11_actionPerformed(e);
      }
    });
    jButton1.setMargin(new Insets(0, 0, 0, 0));
    jButton1.setText("Fitting Information");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton12.setMargin(new Insets(0, 0, 0, 0));
    jButton12.setText("Zoom in");
    jButton12.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton12_actionPerformed(e);
      }
    });
    jButton2.setMargin(new Insets(0, 0, 0, 0));
    jButton2.setText("Zoom out");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton3.setMargin(new Insets(0, 0, 0, 0));
    jButton3.setText("Exit");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jButton4.setBackground(Color.orange);
    jButton4.setForeground(Color.blue);
    jButton4.setMargin(new Insets(0, 0, 0, 0));
    jButton4.setText("Save results");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton4_actionPerformed(e);
      }
    });
    jButton5.setMargin(new Insets(0, 0, 0, 0));
    jButton5.setText("Overlap Images");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton5_actionPerformed(e);
      }
    });
    jButton6.setMargin(new Insets(0, 0, 0, 0));
    jButton6.setText("Not Overlap");
    jButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton6_actionPerformed(e);
      }
    });
    jButton7.setMargin(new Insets(0, 0, 0, 0));
    jButton7.setText("Show Labels");
    jButton7.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton7_actionPerformed(e);
      }
    });
    jButton8.setMargin(new Insets(0, 0, 0, 0));
    jButton8.setText("Hide Lables");
    jButton8.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton8_actionPerformed(e);
      }
    });
    jButton9.setMargin(new Insets(0, 0, 0, 0));
    jButton9.setText("Zoom in histgram");
    jButton9.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton9_actionPerformed(e);
      }
    });
    jButton10.setMargin(new Insets(0, 0, 0, 0));
    jButton10.setText("Zoom out histgram");
    jButton10.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton10_actionPerformed(e);
      }
    });
    jButton13.setMargin(new Insets(0, 0, 0, 0));
    jButton13.setText("Color");
    jButton13.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton13_actionPerformed(e);
      }
    });
    jButton14.setMargin(new Insets(0, 0, 0, 0));
    jButton14.setText("Color");
    jButton14.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton14_actionPerformed(e);
      }
    });
    jButton15.setMargin(new Insets(0, 0, 0, 0));
    jButton15.setText("Band Color");
    jButton15.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton15_actionPerformed(e);
      }
    });
    jButton16.setMargin(new Insets(0, 0, 0, 0));
    jButton16.setText("Fitting curver color");
    jButton16.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton16_actionPerformed(e);
      }
    });
    jButton17.setMargin(new Insets(0, 0, 0, 0));
    jButton17.setText("Zoom in lanes");
    jButton17.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton17_actionPerformed(e);
      }
    });
    jButton18.setMargin(new Insets(0, 0, 0, 0));
    jButton18.setText("Zoom out lanes");
    jButton18.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton18_actionPerformed(e);
      }
    });
    jButton19.setMargin(new Insets(0, 0, 0, 0));
    jButton19.setText("Label Color");
    jButton19.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton19_actionPerformed(e);
      }
    });
    jButton20.setMargin(new Insets(0, 0, 0, 0));
    jButton20.setText("Background Lable Color");
    jButton20.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton20_actionPerformed(e);
      }
    });
    jPanel1.add(jButton11,         new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton1,        new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton12,        new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton2,  new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton3,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton4,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton5,  new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton6,  new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton7,  new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton8,  new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton9,  new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton10,  new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton13,  new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton14,  new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton15,  new GridBagConstraints(7, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton16,  new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton17,  new GridBagConstraints(8, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton18,  new GridBagConstraints(8, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton19, new GridBagConstraints(9, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton20, new GridBagConstraints(9, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    this.getContentPane().add(jPanel2, BorderLayout.CENTER);
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);

    // 初始化
    laneScaleV=2.25;
    histScaleV=1;
    scaleH=1;
    this.isDispID=true;
    colorHist=Color.blue;
    colorFit=Color.green;
    colorLine=4;
    colorLane=4;
    colorLeftRight=Color.red;
    colorGround=Color.red;
  }

  /**
   * 根据图像的两点（左上角和右下角），得出其平均灰度图数组
   * @param p1 左上角点
   * @param p2 右下角点
   * @param directionX true,沿着X方向计算（否则沿着Y方向）
   * @return 平均灰度图数组
   */
  public static int[] histgram(ImageProcessor ip,Point p1,Point p2,boolean directionX){
    int sum;
    int [] data;
    sum=0;
    if(directionX==true){
      data =new int[p2.x-p1.x+1];
      for(int jj=p1.x;jj<=p2.x;jj++) {
        sum=0;
        for(int ii=p1.y;ii<=p2.y;ii++) {
          sum+=ip.getPixelValue(jj,ii);
        }
        if(p2.y!=p1.y) data[jj-p1.x]=(int)(((double)sum)/((double)(p2.y-p1.y+1)));
      }
      return data;
    }
    else{
      data=new int[p2.y-p1.y+1];
      for(int ii=p1.y ;ii<=p2.y ;ii++){
        sum=0;
        for(int jj=p1.x ;jj<=p2.x ;jj++){
          sum+=ip.getPixelValue(jj,ii);
        }
        if(p1.x!=p2.x) data[ii-p1.y ]=sum/(p2.x-p1.x+1);
      }
      return data;
    }
  }

  /**
   * 根据灰度图数组，在画板g2d的drawBeginPt点，画数组从begin-end灰度图
   * @param hisg 灰度图数组
   * @param g2d 画板g2d
   * @param drawBeginPt 开始在画板何处画
   * @param begin 从数组何处开始画,从1开始计数!!
   * @param end 从数组何处结束画
   * @param c 线的颜色
   * @param jPanel 画板
   */
  private void drawHistgram(int[]hisg,Graphics g2d,Point drawBeginPt,
                            int begin,int end,Color c,JPanel jPane){
    int points;         //点数
    points=hisg.length ;
    int []pointx=new int[points];
    int []pointy=new int[points];
    for(int iii=begin;iii<=end;iii++) {
      pointx[iii-begin]=iii-begin+drawBeginPt.x;
      //pointy[iii-begin]=drawBeginPt.y-hisg[iii-1];
      pointy[iii-begin]=drawBeginPt.y-hisg[iii];
    }
    g2d.setColor(c);
    g2d.drawPolyline(pointx,pointy,end-begin+1);

    if(this.isFit==true){
      int []x=new int[result.length];
      int []y=new int[result.length];
      for(int ii=0;ii<x.length;ii++){
        x[ii]=ii-begin+drawBeginPt.x+this.leftV;
        y[ii]=drawBeginPt.y-(int)result[ii];
      }
      g2d.setColor(this.colorFit);
      g2d.drawPolyline(x,y,x.length);
    }
  }

  public void paint(Graphics  g){
    super.paint(g);
    if(drawNum==0){
      drawNum++;
      return;
    }
    else{

      //
      //this.scaleH =5;
      if(isMoving==false){
        setPaintPara();
        Graphics2D g2d_0=(Graphics2D)g;
        g2d_0.scale(this.scaleH,this.laneScaleV);
        drawLaneImage(g2d_0,new Point(this.jPanel2.getLocation().x+4+xInit,
                                      this.jPanel2.getLocation().y),
                                      paintBegin,paintEnd);
      }

      Graphics2D g2d=(Graphics2D)this.jPanel2.getGraphics();
      g2d.scale(this.scaleH,this.histScaleV);
      drawHistgram(hist,g2d,new Point(0+xInit,255),
                   paintBegin,this.paintEnd,this.colorHist,this.jPanel2);

      if(this.isDispID==true) drawLines(g2d,xInit);

      if(drawNum==1) drawNum=2;
    }
  }

  private void setPaintPara(){
    int oriCenterX,oriCenterY;
    oriCenterX=(int)((this.jPanel2.getWidth()/2)/this.scaleH);
    //oriCenterY=(int)(this.jPanel2.getHeight()/this.histScaleV);
    if(this.middleLoc<oriCenterX) this.paintBegin=0;
    else this.paintBegin=middleLoc-oriCenterX;
    if(this.paintBegin<0) this.paintBegin=0;
    if(this.paintBegin>hist.length-1) this.paintBegin=hist.length-1;
    paintEnd=hist.length-1;
  }

  private void drawLines(Graphics2D g,int xInit){
    g.setColor(this.colorGround);
    g.drawLine(0,255-this.groundV,this.jPanel2.getWidth(),255-this.groundV);
    g.drawLine(this.middleLoc+xInit-paintBegin,0,this.middleLoc+xInit-paintBegin,this.jPanel2.getHeight());
    g.setColor(this.colorLeftRight);
    g.drawLine(this.leftV+xInit-paintBegin,0,this.leftV+xInit-paintBegin,this.jPanel2.getHeight());
    g.drawLine(this.rightV+xInit-paintBegin,0,this.rightV+xInit-paintBegin,this.jPanel2.getHeight());
  }

  void jPanel2_mouseClicked(MouseEvent e) {
  }


  void jButton5_actionPerformed(ActionEvent e) {
    this.laneScaleV=this.jPanel2.getHeight()*0.95/(this.rightX-this.leftX);
    this.repaint();
  }

  public void setFitContent(InfoCell cell){
    this.cell=cell;
    hist=this.laneHistgram(cell.laneNo,cell.lineNo);
    // set left and right range
    countLeftRightV(this.groundV);
  }

  private void countLeftRightV(int ground){
    this.leftV=0;
    this.rightV=hist.length-1;
    for(int ii=this.middleLoc;ii>=0;ii--){
      if(hist[ii]<=ground){
        this.leftV=ii;
        break;
      }
    }
    for(int ii=this.middleLoc;ii<=hist.length-1;ii++){
      if(hist[ii]<ground){
        this.rightV=ii;
        break;
      }
    }
  }

  private void drawLaneImage(Graphics2D g,Point pt,int begin,int end){
    int v;
    ImageProcessor ip;
    ip=this.frameMain.getCurrentImage().getIP();
    Color c=new Color(255,0,0);
    for(int ii=leftX; ii<=rightX; ii++){
      for(int jj=upY+begin; jj<=downY; jj++){
        v=(int)ip.getPixelValue(ii,jj);
        if(cell.m_polygon.contains(ii,jj)==true){
          if(this.colorLine==1) c=new Color(v,0,0);
          else if(this.colorLine==2) c=new Color(0,v,0);
          else if(this.colorLine==3) c=new Color(0,0,v);
          else if(this.colorLine==4) c=new Color(v,v,v);
        }
        else{
          if(this.colorLane==1) c=new Color(v,0,0);
          else if(this.colorLane==2) c=new Color(0,v,0);
          else if(this.colorLane==3) c=new Color(0,0,v);
          else if(this.colorLane==4) c=new Color(v,v,v);
        }
        g.setColor(c);
        g.drawOval(pt.x+jj-upY-begin,pt.y+ii-leftX,1,1);
      }
    }
  }

  private int[] laneHistgram(int laneNo,int lineNo){
    int []hist=null;
    Rectangle rectROI=this.frameMain.getCurrentImage().getROI();
    Layer1D layer1D=this.frameMain.getCurrentImage().getLayer1D();
    Vector info1DV=layer1D.getCurrent();
    Info1D info1D;
    int lineY=0;
    for(int ii=1;ii<=info1DV.size();ii++){
      info1D=(Info1D)info1DV.elementAt(ii-1);
      if(info1D.laneN==laneNo){
        if(info1D.character.equals("left")){
          leftX=info1D.point1.x;
        }
        else if(info1D.character.equals("right")){
          rightX=info1D.point1.x;
        }
        if(info1D.character.equals("line") && info1D.lineN==lineNo){
          lineY=info1D.point1.y;
        }
      }
      if(info1D.character.equals("up")){
        upY=info1D.point1.y;
      }
      if(info1D.character.equals("down")){
        downY=info1D.point1.y;
      }
    }
    middleLoc=lineY-upY;
    Point pt1=new Point(leftX,upY);
    Point pt2=new Point(rightX,downY);
    hist=this.histgram(frameMain.getCurrentImage().getIP(),
                       pt1,pt2,false);

    // count min and max value
    this.lineMinGray=5000000;
    this.lineMaxGray=0;
    for(int ii=0;ii<hist.length;ii++){
      if(hist[ii]>lineMaxGray) lineMaxGray=hist[ii];
      if(hist[ii]<lineMinGray) lineMinGray=hist[ii];
    }

    // set background
    this.groundV=(int)(cell.m_graySum/cell.m_area);
    this.groundV*=1.2;
    if(this.groundV<0) this.groundV=0;
    if(this.groundV>255) this.groundV=255;

    return hist;
  }

  void jButton3_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void jButton12_actionPerformed(ActionEvent e) {
    if(scaleH<1) scaleH+=0.1;
    else scaleH+=0.25;
    if(scaleH>=0.95 && scaleH<=1.05) scaleH=1;
    this.repaint();
  }

  void jButton2_actionPerformed(ActionEvent e) {
    if(scaleH>=1) scaleH-=0.25;
    else scaleH-=0.1;
    if(scaleH>=0.95 && scaleH<=1.05) scaleH=1;
    this.repaint();
  }

  void jButton7_actionPerformed(ActionEvent e) {
    this.isDispID=true;
    this.repaint();
  }

  void jButton8_actionPerformed(ActionEvent e) {
    this.isDispID=false;
    this.repaint();
  }

  void jButton9_actionPerformed(ActionEvent e) {
    if(histScaleV<1) histScaleV+=0.1;
    else histScaleV+=0.25;
    if(histScaleV>=0.95 && histScaleV<=1.05) histScaleV=1;
    this.repaint();
  }

  void jButton10_actionPerformed(ActionEvent e) {
    if(histScaleV>=1) histScaleV-=0.25;
    else histScaleV-=0.1;
    if(histScaleV>=0.95 && histScaleV<=1.05) histScaleV=1;
    this.repaint();
  }

  void jButton6_actionPerformed(ActionEvent e) {
    this.laneScaleV=2.25;
    this.repaint();
  }

  void jButton17_actionPerformed(ActionEvent e) {
    if(laneScaleV<1) laneScaleV+=0.1;
    else laneScaleV+=0.25;
    if(laneScaleV>=0.95 && laneScaleV<=1.05) laneScaleV=1;
    this.repaint();
  }

  void jButton18_actionPerformed(ActionEvent e) {
    if(laneScaleV>=1) laneScaleV-=0.25;
    else laneScaleV-=0.1;
    if(laneScaleV>=0.95 && laneScaleV<=1.05) laneScaleV=1;
    this.repaint();
  }

  void jButton13_actionPerformed(ActionEvent e) {
    JColorChooser jcc=new JColorChooser();
    this.colorHist=jcc.showDialog(this.frameMain,"Histgram Color:",this.colorHist);
    this.repaint();
  }

  void jButton14_actionPerformed(ActionEvent e) {
    JOptionPane myOptionPane=new JOptionPane();
    Object[] options = { "  Red  ", "  Green  ","  Blue  ","  W/B  " };
    int  selectOption;
    selectOption=myOptionPane.showOptionDialog(
        this.frameMain, "Please select lane color:", "Lane color",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[colorLane-1]);
    if(selectOption!=0 && selectOption!=1 && selectOption!=2 && selectOption!=3) return;
    this.colorLane=selectOption+1;
    this.repaint();
  }

  void jButton15_actionPerformed(ActionEvent e) {
    JOptionPane myOptionPane=new JOptionPane();
    Object[] options = { "  Red  ", "  Green  ","  Blue  ","  W/B  " };
    int  selectOption;
    selectOption=myOptionPane.showOptionDialog(
        this.frameMain, "Please select band color:", "Band Color",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[colorLine-1]);
    if(selectOption!=0 && selectOption!=1 && selectOption!=2 && selectOption!=3) return;
    this.colorLine=selectOption+1;
    this.repaint();
  }

  void jButton16_actionPerformed(ActionEvent e) {
    JColorChooser jcc=new JColorChooser();
    this.colorFit=jcc.showDialog(this.frameMain,"Fitting curver color:",this.colorFit);
    this.repaint();
  }

  void jButton19_actionPerformed(ActionEvent e) {
    JColorChooser jcc=new JColorChooser();
    this.colorLeftRight=jcc.showDialog(this.frameMain,"Histgram Color:",this.colorLeftRight);
    this.repaint();
  }

  void jButton20_actionPerformed(ActionEvent e) {
    JColorChooser jcc=new JColorChooser();
    this.colorGround=jcc.showDialog(this.frameMain,"Histgram Color:",this.colorGround);
    this.repaint();
  }

  void jPanel2_mouseMoved(MouseEvent e) {
    Point p=new Point((int)(e.getPoint().x/this.scaleH),(int)(e.getPoint().y/this.histScaleV));
    double dis=1;
    if(p.y>=(255-this.groundV)-dis && p.y<=(255-this.groundV)+dis)
      this.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
    else if(p.x>=(leftV+xInit-paintBegin)-dis && p.x<=(leftV+xInit-paintBegin)+dis)
      this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
    else if(p.x>=(rightV+xInit-paintBegin)-dis && p.x<=(rightV+xInit-paintBegin)+dis)
      this.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));
    else this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
  }

  void jPanel2_mouseDragged(MouseEvent e) {
    Point p=new Point((int)(e.getPoint().x/this.scaleH),(int)(e.getPoint().y/this.histScaleV));
    if(this.moveSign==1){
      this.groundV=this.oriV+(oriPoint.y-p.y);
      this.countLeftRightV(this.groundV);
    }
    else if(this.moveSign==2){
      this.leftV=this.oriV+(p.x-oriPoint.x);
    }
    else if(this.moveSign==3){
      this.rightV=this.oriV+(p.x-oriPoint.x);
    }
    this.repaint();
  }

  void jPanel2_mouseReleased(MouseEvent e) {
    this.isMoving=false;
    this.repaint();
  }

  void jPanel2_mousePressed(MouseEvent e) {
    oriPoint=new Point((int)(e.getPoint().x/this.scaleH),(int)(e.getPoint().y/this.histScaleV));
    Point p=oriPoint;
    double dis=1;
    if(p.y>=(255-this.groundV)-dis && p.y<=(255-this.groundV)+dis){
      this.moveSign=1;
      this.oriV=this.groundV;
    }
    else if(p.x>=(leftV+xInit-paintBegin)-dis && p.x<=(leftV+xInit-paintBegin)+dis){
      this.moveSign=2;
      this.oriV=this.leftV;
    }
    else if(p.x>=(rightV+xInit-paintBegin)-dis && p.x<=(rightV+xInit-paintBegin)+dis){
      this.moveSign=3;
      this.oriV=this.rightV;
    }
    this.isMoving=true;
  }

  void jButton11_actionPerformed(ActionEvent e) {
    //this.leftV

    if(this.rightV-this.leftV +1<3){
      JOptionPane.showMessageDialog(this.frameMain
                                    ,"Data is too small and please adjust background",
                                    "Error",JOptionPane.ERROR_MESSAGE);
      return;
    }

    double []x=new double[this.rightV -this.leftV +1];
    double []y=new double[x.length];

    int xx;
    for(int ii=0;ii<x.length;ii++){
      x[ii]=this.leftV+ii;
      if(hist[(int)x[ii]]==0) y[ii]=0.0000000001;
      else y[ii]=Math.log(hist[(int)x[ii]]);
    }
    this.formula=(double[])easygel.MathEx.polyfit(x,y,2);
    this.result=new double[x.length];
    this.newGray=0;
    for(int ii=0;ii<x.length;ii++){
      xx=leftV+ii;
      result[ii]=formula[0]*xx*xx+formula[1]*xx+formula[2];
      result[ii]=Math.exp(result[ii]);
      this.newGray+=result[ii]*(this.rightX-this.leftX+1);
    }
    this.isFit=true;

    //
    phi=Math.sqrt(-1/(2*formula[0]));
    mu=formula[1]*phi*phi;
    this.oriGray=cell.m_graySum;

    this.repaint();
  }

  void jButton1_actionPerformed(ActionEvent e) {
    DialogGuassFitReport fit=new DialogGuassFitReport(this.frameMain,"",false,this);
    fit.show();
  }

  void jButton4_actionPerformed(ActionEvent e) {
    cell.m_graySum=(int)this.newGray;
    JOptionPane.showMessageDialog(this.frameMain,"OK!");
    this.dispose();
  }
  // end of the class
}



