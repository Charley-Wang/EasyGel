package easygel.image;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import ij.process.*;
import java.awt.event.*;
import easygel.*;
import javax.swing.border.*;
import easygel.uiti.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogDrawHist extends JDialog {
  private BorderLayout borderLayout1 = new BorderLayout();
  private TitledBorder titledBorder1;
  private JPanel jPanel1 = new JPanel();
  private TitledBorder titledBorder2;
  private JPanel jPanel2 = new JPanel();
  private GridBagLayout gridBagLayout1 = new GridBagLayout();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  private JButton jButton4 = new JButton();
  private JButton jButton5 = new JButton();
  private FrameMain frameMain;
  // 画线之设置
  private boolean isDrawLine;
  private Point linePt1,linePt2;
  //
  private Point drawBeginPt;
  // 绘制图像的区域
  private DialogImage dialogImage;
  private ImageProcessor ip;
  private Point drawPt1,drawPt2;
  private boolean drawDirectionX;
  private int currentLaneNo;
  //
  private Image image;

  public DialogDrawHist(FrameMain frame, String title, boolean modal) {
    super(frame, title, modal);
    this.frameMain =frame;
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    isDrawLine=true;
    linePt1=new Point(-1,-1);
    linePt2=new Point(-1,-1);
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    this.setSize(new Dimension(513, 339));
    this.getContentPane().setLayout(borderLayout1);
    this.setBackground(new Color(255,255,255));
    jPanel1.setBorder(titledBorder2);
    jPanel1.setMinimumSize(new Dimension(40, 40));
    jPanel1.setPreferredSize(new Dimension(40, 40));
    jPanel1.setLayout(gridBagLayout1);
    jPanel2.setBorder(BorderFactory.createLoweredBevelBorder());
    jPanel2.setPreferredSize(new Dimension(500, 360+100));
    jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jPanel2_mouseClicked(e);
      }
    });
    jPanel2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        jPanel2_mouseMoved(e);
      }
    });
    jPanel2.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(FocusEvent e) {
        jPanel2_focusGained(e);
      }
      public void focusLost(FocusEvent e) {
        jPanel2_focusLost(e);
      }
    });
    jButton1.setText("Draw ROI");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setText("Select Object");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton3.setText("Draw Lines");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jButton4.setText("Draw Gray");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton4_actionPerformed(e);
      }
    });
    jButton5.setText("Exit");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton5_actionPerformed(e);
      }
    });
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(jButton1,     new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton2,     new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton3,     new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton4,     new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    jPanel1.add(jButton5,     new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    this.getContentPane().add(jPanel2, BorderLayout.CENTER);
    // 初始化
    dialogImage=frameMain.getCurrentImage();
    ip=dialogImage.getIP();
    drawPt1=new Point(0,0);
    drawPt2=new Point(this.dialogImage .getImageWidth()-1,
                    this.dialogImage .getImageHeight()-1);
    drawDirectionX=true;
    currentLaneNo=0;
  }

  /**
   * 根据图像的两点（左上角和右下角），得出其平均灰度图数组
   * @param p1 左上角点
   * @param p2 右下角点
   * @param directionX true,沿着X方向计算（否则沿着Y方向）
   * @return 平均灰度图数组
   */
  public static int[] histgram(ImageProcessor ip,Point p1,Point p2
                               ,boolean directionX,DialogImage di){
    int sum;
    int [] data;
    sum=0;
    int v;
    if(directionX==true){
      data =new int[p2.x-p1.x+1];
      for(int jj=p1.x;jj<=p2.x;jj++) {
        sum=0;
        for(int ii=p1.y;ii<=p2.y;ii++) {
          //sum+=ip.getPixelValue(jj,ii);
          v=di.getPixel8GrayBgAdjust(jj,ii);
          sum+=v;
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
          //sum+=ip.getPixelValue(jj,ii);
          v=di.getPixel8GrayBgAdjust(jj,ii);
          sum+=v;
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
      pointy[iii-begin]=drawBeginPt.y-hisg[iii-1];
    }
    g2d.setColor(c);
    g2d.drawPolyline(pointx,pointy,end-begin+1);
  }

  /**
   * 根据图像的两点（左上角和右下角），画出平均灰度图
   * @param imagePt1 左上角
   * @param imagePt2 右下角
   * @param g2d 画板g2d
   * @param drawBeginPt 开始在画板画点
   * @param jPanel 画板
   * @param directionX true,沿着X方向计算（否则沿着Y方向）
   */
  public void drawROIHistgram(Point imagePt1,Point imagePt2,Graphics2D g2d,
                              Point drawBeginPt,JPanel jPanel,
                              boolean directionX){
    int data[],data2[];
    data=this.histgram(this.ip,imagePt1,imagePt2,directionX,this.frameMain.getCurrentImage());
    drawScale(10,5,drawBeginPt,Color.red,g2d,jPanel);
    drawHistgram(data,g2d,drawBeginPt,1,data.length ,Color.blue ,jPanel);
    /*
    // DOT DELETE !!!
    // draw the rolling ball
    int r=Integer.parseInt(textr.getText() );
    data2=WxsUiti.rollingBall(data,r);
    drawHistgram(data2,g2d,drawBeginPt,1,data.length ,Color.red  ,jPanel);
    for(int ii=1;ii<=data.length ;ii++){
      data[ii-1]-=data2[ii-1];
    }
    drawHistgram(data,g2d,drawBeginPt,1,data.length,Color.green,jPanel);
    */
  }

  /**
   * 在g2D上，开始点drawBeginPt处，绘制标尺，标尺间距为scalL，标尺长为scaleH
   * @param scaleL 标尺间距
   * @param scaleH 标尺长
   * @param drawBeginPt 开始点drawBeginPt处
   * @param c 标尺颜色
   * @param g2d 画板g2d
   * @param drawPanel 画板
   */
  public void drawScale(int scaleL,int scaleH,Point  drawBeginPt,
                          Color c,Graphics2D g2d,JPanel drawPanel){
    g2d.setColor(c);
    g2d.drawLine(drawBeginPt.x ,drawBeginPt.y,drawBeginPt.x,0);
    g2d.drawLine(drawBeginPt.x ,drawBeginPt.y,drawPanel.getWidth()-1,drawBeginPt.y-1);
    for(int ii=1;ii<=(drawPanel.getWidth() -1-drawBeginPt.x )/scaleL;ii++){
      g2d.drawLine(drawBeginPt.x+(ii-1)*scaleL,drawBeginPt.y,
                   drawBeginPt.x+(ii-1)*scaleL,drawBeginPt.y+scaleH);
    }
    for(int ii=1;ii<=(drawBeginPt.y )/scaleL;ii++){
      g2d.drawLine(drawBeginPt.x,drawBeginPt.y-(ii-1)*scaleL,
                   drawBeginPt.x-scaleH,drawBeginPt.y-(ii-1)*scaleL);
    }
    int lineLastY=drawBeginPt.y-255;
    int lineLastX=drawBeginPt.x+((int)((drawPanel.getWidth() -1-drawBeginPt.x )/scaleL)-1)*scaleL;
    g2d.drawLine(drawBeginPt.x,lineLastY,lineLastX,lineLastY);
    g2d.drawLine(lineLastX,drawBeginPt.y,lineLastX,lineLastY);
  }

  void jButton4_actionPerformed(ActionEvent e) {
    reDraw();
  }

  private void reDraw(){
     int width,height;
     Graphics g;
     Graphics2D g2d;
     g=this.jPanel2 .getGraphics() ;
     g2d=(Graphics2D)g;
     width=10;
     height=this.jPanel2 .getHeight()-1-10;
     g2d.clearRect(0,0,jPanel2.getWidth()-1,jPanel2.getHeight()-1);
     drawROIHistgram(drawPt1,drawPt2,g2d, new Point(width-1,height-1),jPanel2 ,drawDirectionX);
     if(isDrawLine==true ){
       g2d.setColor(Color.orange);
       if(linePt1.x >=0 && linePt2.x >=0)
          g2d.drawLine(linePt1.x,linePt1.y,linePt2.x,linePt2.y);
       g2d.setColor(Color.green);
       if(linePt1.x>0) g2d.drawOval(linePt1.x,linePt1.y,1,1);
       if(linePt2.x>0) g2d.drawOval(linePt2.x,linePt2.y,1,1);
     }
  }

  void jPanel2_focusGained(FocusEvent e) {
    this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR ));
  }

  void jPanel2_focusLost(FocusEvent e) {
    this.setCursor(new Cursor(Cursor.CUSTOM_CURSOR ));
  }

  void jPanel2_mouseMoved(MouseEvent e) {
    Point Pt=new Point(10,this.jPanel2 .getHeight()-10 -1);
    String tip;
    tip="灰度["+Integer.toString(e.getPoint().x-Pt.x+1) +"]="+
        Integer.toString((e.getPoint().y-Pt.y+1)*(-1));
    this.setTitle(tip);
    if(isDrawLine==true){
      int scaleL=10;
      int width,height;
       width=10;
       height=this.jPanel2 .getHeight()-10-1;
       drawBeginPt=new Point(width-1,height-1);
       Cursor cursor;
       int lineLastY=drawBeginPt.y-255;
       int lineLastX=drawBeginPt.x+((int)((jPanel2.getWidth() -1-drawBeginPt.x )/scaleL)-1)*scaleL;
      if(e.getPoint() .x==drawBeginPt.x||e.getPoint() .x==lineLastX ||
          e.getPoint() .y==drawBeginPt.y||e.getPoint() .y==lineLastY){
        cursor=new Cursor(Cursor.CROSSHAIR_CURSOR);
      }
      else{
        cursor=new Cursor(Cursor.DEFAULT_CURSOR);
      }
      jPanel2.setCursor(cursor);
    }
  }

  void jCheckBox1_actionPerformed(ActionEvent e) {
    reDraw();
  }

  public void paint(Graphics  g){
    super.paint(g);
    reDraw();
  }

  void jButton3_actionPerformed(ActionEvent e) {
    if(isDrawLine==true){
      isDrawLine=false;
      jButton3.setText("Do not draw a line");
    }
    else{
      isDrawLine=true;
      jButton3.setText("Draw Lines");
    }
    this.reDraw();
  }

  void jPanel2_mouseClicked(MouseEvent e) {
    if(isDrawLine==true){
      int scaleL=10;
      int width,height;
       width=10;
       height=this.jPanel2 .getHeight()-10-1;
       drawBeginPt=new Point(width-1,height-1);
      int lineLastY=drawBeginPt.y-255;
      int lineLastX=drawBeginPt.x+((int)((jPanel2.getWidth()-1 -drawBeginPt.x )/scaleL)-1)*scaleL;
      if(e.getPoint() .x==drawBeginPt.x){
        linePt1=e.getPoint();
      }
      else if(e.getPoint() .x==lineLastX){
        linePt2=e.getPoint();
      }
      else if(e.getPoint() .y==drawBeginPt.y){
        linePt2=e.getPoint();
      }
      else if(e.getPoint() .y==lineLastY){
        linePt1=e.getPoint();
      }
      this.reDraw();
    }
  }

  void jButton1_actionPerformed(ActionEvent e) {
    dialogImage=frameMain.getCurrentImage();
    if(dialogImage.existROI() ==false) return;
    ip=dialogImage.getIP();
    Rectangle rect;
    rect=dialogImage.getROI();
    drawPt1=rect.getLocation();
    drawPt2=new Point(drawPt1.x+rect.width +1-1,
                      drawPt1.y+rect.height +1-1);
    drawDirectionX=true;
    this.reDraw();
  }

  void jButton5_actionPerformed(ActionEvent e) {
    this.dispose() ;
  }

  void jButton2_actionPerformed(ActionEvent e) {
    this.frameMain .setCmmStatus("histsellane");
  }

  public void setObjectImage(Image img){
    this.image =img;
  }

  public  void drawRectYHist(Point Pt1,Point Pt2){
    int width,height;
    Graphics g;
    Graphics2D g2d;
    this.drawPt1 =Pt1;
    this.drawPt2=Pt2;
    this.drawDirectionX =false;
    g=this.jPanel2 .getGraphics() ;
    g2d=(Graphics2D)g;
    width=10;
    height=this.jPanel2 .getHeight()-1-10;
    g2d.clearRect(0,0,jPanel2.getWidth()-1,jPanel2.getHeight()-1);
    drawROIHistgram(drawPt1,drawPt2,g2d, new Point(width-1,height-1),jPanel2 ,drawDirectionX);
  }

  public boolean drawLined(){
    Point p=new Point(-1,-1);
    if(p==linePt1 || p==linePt2) return false;
    else return true;
  }
  public Point getLinePt1(){
    Point Pt=new Point(10,this.jPanel2 .getHeight()-1-10 );
    return new Point(linePt1.x,(linePt1.y-Pt.y+1)*(-1));
  }
  public Point getLinePt2(){
    Point Pt=new Point(10,this.jPanel2 .getHeight()-1-10 );
    return new Point(linePt2.x,(linePt2.y-Pt.y+1)*(-1));
  }
  public Point getSpROIPt1(){
    return this.drawPt1 ;
  }
  public Point getSpROIPt2(){
    return this.drawPt2 ;
  }
}
