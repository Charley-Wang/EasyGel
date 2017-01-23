package easygel;

import java.awt.*;
import javax.swing.*;
import easygel.image.*;
import ij.process.*;
import easygel.image.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.event.*;
import javax.*;
import java.awt.image.*;
import java.awt.geom.*;
import easygel.layer.*;
import java.util.*;
import ij.io.*;
import java.io.*;
import easygel.image.*;
import easygel.file.*;
import java.io.*;
import java.lang.*;
import easygel.uiti.*;
import java.lang.reflect.*;
import ij.process.*;
import ij.measure.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogImage extends JDialog{
  private BorderLayout borderLayout1 = new BorderLayout();
  private ImagePanel imagePanel;
  private boolean isClosed;

  //初始化变量
  private String path,name;
  private int width,height,format;
  private FrameMain frameMain;

  //图像层设置
  private Image image;
  private ImageProcessor ip;
  private Bmp bmp;
  private FileInfo fileInfo;

  //打开模式
  private int processorMode;
  public  static final int PROCESSOR_BYTE=0,PROCESSOR_SHORT=1,
                           PROCESSOR_COLOR=2,PROCESSOR_FLOAT=3;
  //颜色模式
  private int colorMode;
  public  static final int COLOR_RGB=0,COLOR_INDEX=1,COLOR_GRAY=2,COLOR_UNKNOWN=4;
  //比特深度
  private int bitDepth;
  // 灰度位数，本软件为8 or 16
  public int grayBits;

  //包含元素，层
  private LayerText layerText;
  //private LayerLine layerLine;
  private Layer1D layer1D;
  private LayerCell layerCell;

  // for encrypt
  private Object layerLine;
  private Class classLayerLine;

  // 连线的情况
  private Point linePt1,linePt2;
  private int linePoints;
  private boolean isFinishedLine;

  // 区域的选择
  private Point rectROIPt1,rectROIPt2;
  private Point  oldRectROIPt1,oldRectROIPt2;
  // "0" 无点,"1" 一点, "2" 两点,setSelROIPoints()
  private int selROIPoints;
  // _2 for delete background of Select
  private Point rectROIPt1_2,rectROIPt2_2;
  private Point  oldRectROIPt1_2,oldRectROIPt2_2;
  // "0" 无点,"1" 一点, "2" 两点,setSelROIPoints()
  private int selROIPoints_2;

  // 多边形区域,为小范围目标的识别
  // c-圆形，r-方形，e-椭圆形，p-折线
  private char selectShape;
  private int selectPGPoints;
  private Polygon selectPolygon;

  //undo之操作,mainDoneStep记录的同时操作的步数,为1时再也没有了
  private Vector mainDone =new Vector();
  private Vector mainDoneStep =new Vector();
  private Vector IPs=new Vector();
  private Vector ImageInformation =new Vector();

  //操作状态
  private  boolean pressedShift;

  //保存状态
  private boolean isChangeImage;
  private boolean isChangeLayers;

  //图像放大与移动
  private double scale;
  private Point oriPoint;
  private Point oldOriPoint;
  private Point moveImageFirstPoint;
  private boolean hitFirstPoint;
  private GridLayout gridLayout1 = new GridLayout();

  private boolean bgBlack;

  private boolean saveAsTiff;
  private int tiffWidht;
  private int tiffHeight;
  private String tiffName;
  private String tiffPath;

  private Color d3Color;

  private String []doMethod;

  // 斑点
  Vector whiteBacterial=new Vector();
  Vector blueBacterial=new Vector();

  // DialogCount之2D分析的MW方向的设置
  public boolean MW_2D_isDirection;
  public boolean MW_2D_YDirection;

  // 是否用手动方式up/down操作
  public boolean isManualUpDown;

  // 光标
  public static int CURBIG=1;
  public static int CURSMALL=2;
  public static int CURMOVE=3;
  public static int CURSELECT=4;
  public static int CURRECT=5;
  public static int CURCIRCLE=6;
  public static int CURELLIPSE=7;
  public static int CURPOLYGON=8;
  public static int CURDETAIL=9;
  public static int CURADD=10;
  public static int CURDELETE=11;
  public static int CURMOVELANE=12;
  public static int CURMOVELANE2=13;

  // for the deleting the backgraound
  public static int ADJUST_BACK_NONE=1;
  public static int ADJUST_BACK_256=2;
  public static int ADJUST_BACK_MAX=3;
  public static int ADJUST_BACK_AVG=4;

  public DialogImage(String filePath,String fileName,
                     int imageWidth,int imageHeight,
                     int imageFormat,FrameMain frame){
    super(frame, "", false);
    isClosed=false;
    saveAsTiff=false;
    path=filePath;
    name=fileName;
    width=imageWidth;
    height=imageHeight;
    frameMain=frame;
    format=imageFormat;
    selectPGPoints=0;
    this.selectPolygon=new Polygon();
    d3Color=new Color(0,255,255);
    this.doMethod=new String[10];
    this.MW_2D_isDirection=false;

    this.classLayerLine=frameMain.getClassLayerLine();


    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * 返回白斑点和蓝斑点的个数
   * @return
   * Point.x=白斑点的个数
   * Point.y=蓝斑点的个数
   */
  public Point getWhiteBlueBacterialNum(){
    int w=this.whiteBacterial.size();
    int b=this.blueBacterial.size();
    return new Point(w,b);
  }

  public void setDoMethod(int no,String method){
    this.doMethod[no-1]=method;
  }

  public String[] getDoMethods(){
    return this.doMethod;
  }

  public Graphics getImageGraphics(){
    return this.imagePanel .getGraphics() ;
  }

  public boolean getSaveAsTiff(){
    return this.saveAsTiff;
  }

  public void judgeBackground(){
    float min,max;
    float thd;
    int nlight=0;
    int nhighlight=0;
    float grayImage[][]=new float[height][width];
    for(int ii=0;ii<height;ii++){
      for(int jj=0;jj<width;jj++){
        grayImage[ii][jj]=ip.getPixelValue(jj,ii);
      }
    }
    max=min=grayImage[height/4][width/4];
    for(int ii=height/4;ii<height*3/4;ii=ii+1){
      for(int jj=width/4;jj<width*3/4;jj++){
        if(max<grayImage[ii][jj])
          max=grayImage[ii][jj];
        if(min>grayImage[ii][jj])
          min=grayImage[ii][jj];
      }
    }
    thd=(float)((max-min)/4+min);
    // To determine the type of lane,select processing way
    for(int ii=width/5;ii<width*4/5;ii++){
      for(int jj=height/8;jj<height*7/8;jj++){
        if(grayImage[jj][ii]<thd){
          nhighlight++;
        }
      }
      if(nhighlight>height/2) nlight++;
      if(nlight>=10) break;
      nhighlight=0;
    }
    if(nlight>=10) bgBlack=true;
    else bgBlack=false;;
  }

  public boolean getBackgroundBlack(){
    return bgBlack;
  }

  public void setBackgroundBlank(boolean bg){
    this.bgBlack=bg;
    this.ip.isBGBlack=bg;
  }

  public Graphics getMyImageGraphics(){
    return this.imagePanel.getGraphics();
  }

  public boolean setLaneLineForCell(InfoCell cell){
    boolean success=true;
    int totalLines=this.layer1D.getTotalLines();
    int lane[]=new int[totalLines];
    int line[]=new int[totalLines];
    int lanes=this.layer1D.getLanes();
    int lines;
    int num=0;
    Point pt;
    Polygon pg=cell.m_polygon;
    for(int ii=1;ii<=lanes;ii++){
      lines=this.layer1D.getLines(ii);
      for(int jj=1;jj<=lines;jj++){
        pt=this.layer1D.getLineCenterPoint(ii,jj);
        if(pg.contains(pt)){
          lane[num]=ii;
          line[num]=jj;
          num++;
        }
      }
    }
    if(num==0){
      //JOptionPane.showMessageDialog(this.frameMain,"识别的目标不包含任何条带！","ERROR",JOptionPane.INFORMATION_MESSAGE);
      success=false;
    }
    else if(num==1){
      cell.laneNo=lane[num-1];
      cell.lineNo=line[num-1];
      success=true;
    }
    else{
      JOptionPane myOptionPane=new JOptionPane();
      Object[] options = new String[num];
      int  selectOption;
      String tip;
      for(int ii=1;ii<=num;ii++){
        options[ii-1]="Lane No: "+lane[ii-1]+"Band No: "+line[ii-1];
      }
      tip="Band number is "+num+", please select: ";
      selectOption=myOptionPane.showOptionDialog(null, tip,tip,
          JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
      if(selectOption>=0){
        cell.laneNo=lane[selectOption];
        cell.lineNo=line[selectOption];
        success=true;
      }
      else{
        success=false;
      }
    }
    return success;
  }

  private void jbInit() throws Exception {
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setTitle("Image file name: "+path+name+", width X height = "+width+" X "+height);
    this.getContentPane().setLayout(gridLayout1);
    this.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        this_keyPressed(e);
      }
      public void keyReleased(KeyEvent e) {
        this_keyReleased(e);
      }
    });
    int oldw,oldh;
    oldw=8;
    oldh=29;
    this.addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowActivated(WindowEvent e) {
        this_windowActivated(e);
      }
      public void windowClosed(WindowEvent e) {
        this_windowClosed(e);
      }
      public void windowDeactivated(WindowEvent e) {
        this_windowDeactivated(e);
      }
    });

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double cofactor=0.628;
    if(screenSize.getWidth() *cofactor<width ||
       screenSize.getHeight() *cofactor<height){
       double wscale,hscale;
       wscale=screenSize.getWidth() *cofactor/width;
       hscale=screenSize.getHeight() *cofactor/height;
       if(wscale<hscale) scale=wscale;
       else scale=hscale;
       int w,h;
       w=(int)(scale*width);
       h=(int)(scale*height);
       easygel.uiti.WxsUiti.centerDialog(this,w+oldw,h+oldh);
    }
    else
    {
      scale=1;
      easygel.uiti.WxsUiti.centerDialog(this,width+oldw,height+oldh);
    }
    /*
    String tmpPath=frameMain.getSystemDir()+"\\DATABASE\\";
    String name="";
    int fileFormat =OpenFile.JPEG;
    name="JpgTmp.jpg";
    OpenFile openFile;
    Bmp bmp2;
    bmp2=new Bmp();
    openFile=new OpenFile(fileFormat,tmpPath,name,bmp2);
    */
    /*
    String tmpPath=frameMain.getSystemDir()+"\\DATABASE\\";
    String name2="";
    int fileFormat =OpenFile.JPEG;
    name2="JpgTmp.jpg";
    OpenFile openFile;
    Bmp bmp2=new Bmp();
    openFile=new OpenFile(fileFormat,tmpPath,name2,bmp2);
    */

    OpenFile openFile;
    bmp=new Bmp();
    openFile=new OpenFile(format,path,name,bmp);
    ip=openFile.getImageProcessor();
    image=ip.createImage();
    fileInfo=openFile.getFileInfo();
    bmp=openFile.getBmp();

    this.colorMode=openFile.getColorMode();
    this.bitDepth=openFile.getBitDepth();
    this.processorMode=openFile.getProcessorMode();
    if(this.format==this.COLOR_GRAY) this.grayBits=this.bitDepth;
    else this.grayBits=8;

    //内部类----图像板的声明以及放置事件
    imagePanel =new ImagePanel(this.classLayerLine);
    imagePanel.setAutoscrolls(true);
    getContentPane().add(imagePanel);
    imagePanel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        imagePanel_mouseClicked(e);
      }
      public void mousePressed(MouseEvent e) {
        imagePane1_mousePressed(e);
      }
      public void mouseReleased(MouseEvent e) {
        imagePanel_mouseReleased(e);
      }
    });
    imagePanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        imagePanel_mouseMoved(e);
      }
      public void mouseDragged(MouseEvent e) {
        imagePanel_mouseDragged(e);
      }

    });

    //初始化内部元素（层）
    layerText=new LayerText(this);

    //layerLine=new LayerLine(this);
    // Encrypt
    layerLine=this.classLayerLine.newInstance();
    //Method method=this.classLayerLine.getMethod("setDialogImage",new Class[]{this.getClass()});
    //Method method=this.getClassMethod(this.classLayerLine,"setDialogImage");
    call(this.classLayerLine,layerLine,"setDialogImage",new Object[]{this});


    this.setSelectPolygonNull();

    layer1D=new Layer1D(this);
    layerCell=new LayerCell(this);
    pressedShift=false;
    linePoints=0;
    linePt1=new Point(0,0);
    linePt2=new Point(0,0);
    this.isFinishedLine =false;

    isChangeImage=false;
    isChangeLayers=false;
    oriPoint=new Point(0,0);
    oldOriPoint=new Point(0,0);
    moveImageFirstPoint=new Point(0,0);
    hitFirstPoint=false;

    //new ！！！
    selROIPoints=0;
    this.rectROIPt1 =new Point(0,0);
    this.rectROIPt2=new Point(0,0);
    this.oldRectROIPt1 =new Point(0,0);
    this.oldRectROIPt2 =new Point(0,0);
    selROIPoints_2=0;
    this.rectROIPt1_2=new Point(0,0);
    this.rectROIPt2_2=new Point(0,0);
    this.oldRectROIPt1_2 =new Point(0,0);
    this.oldRectROIPt2_2 =new Point(0,0);
  }

  protected void imagePanel_mouseDragged(MouseEvent e){
    Point p=new Point(0,0);
    p.x =(int)((e.getPoint().getX())/scale-oriPoint.x);
    p.y =(int)((e.getPoint().getY())/scale-oriPoint.y);

    String cmmStatus;
    cmmStatus=((FrameMain)frameMain).getCmmStatus();
    if(cmmStatus=="basicSelROI"){
      if(selROIPoints>=1){
        rectROIPt2.x=p.x;
        rectROIPt2.y=p.y;
        selROIPoints=2;
        this.paintImage() ;
      }
    }
    else if(cmmStatus=="moveLane"){
      layer1D.mouseDragged(p);
      this.paintImage() ;
    }
    else if(cmmStatus=="basicSelROI_2"){
      if(selROIPoints_2>=1){
         rectROIPt2_2.x=p.x;
         rectROIPt2_2.y=p.y;
         selROIPoints_2=2;
        this.paintImage() ;
      }
    }
    else if(cmmStatus=="insertLine") docmmInsertLine(p,false);    //basic
    else if(cmmStatus=="basicMoveImage"){
       if(this.hitFirstPoint ==false) return;
        // this.setTitle("drag "+e.getPoint() );
       int   detx,dety;
        detx=(int)((e.getPoint().x -moveImageFirstPoint.x));
        dety=(int)((e.getPoint().y -moveImageFirstPoint.y));
        oriPoint.x=oldOriPoint.x+detx;
        oriPoint.y=oldOriPoint.y+dety;
       this.paintImage();
    }
    else if(cmmStatus=="selectRectangle" || cmmStatus=="selectCircle" || cmmStatus=="selectEllipse"){
      if(this.selectPGPoints==1){
        this.selectPolygon.addPoint(p.x,p.y);
        this.selectPGPoints=2;
      }
      else{
        this.selectPolygon.xpoints[1]=p.x;
        this.selectPolygon.ypoints[1]=p.y;
      }
      this.paintImage();
    }
    else{
      // for layerText and layerLine
      if(this.hitFirstPoint ==false) return;
      int   detx,dety;
      detx=(int)((e.getPoint().x -moveImageFirstPoint.x));
      dety=(int)((e.getPoint().y -moveImageFirstPoint.y));
      this.moveImageFirstPoint.x =e.getPoint() .x;
      this.moveImageFirstPoint.y =e.getPoint() .y;
      layerText.moveText(detx,dety,false);

      //layerLine.moveLine(detx,dety,false);
      // Encrypt
      call(this.classLayerLine,layerLine,"moveLine",new Object[]{new Integer(detx),new Integer(dety),new Boolean(false)});

      this.paintImage();
    }
  }

  public void importRsult(){
    //create layers from the externalizable
    try{
        //FileDialog filedlg=new FileDialog(this.frameMain ,"请选择导入文件名称：");
        //filedlg.show();
        //File file=new File(filedlg.getDirectory() +filedlg.getFile());
        //System.out.println(file);

        int dotLoc=name.indexOf(".");
        String beforeName;
        if(dotLoc==-1) beforeName=name;
        else beforeName=name.substring(0,dotLoc);

        String fileName=path +beforeName;
        if(this.format==OpenFile.BMP) fileName+=".BGS";
        else if(this.format ==OpenFile.TIFF) fileName+=".TGS";
        else if(this.format ==OpenFile.JPEG) fileName+=".JGS";
        else fileName+=".GGS";

        File file=new File(fileName);
        //System.out.println(file);

        if(file.exists() ==true){
          ObjectInputStream in= new ObjectInputStream(new FileInputStream(file));

          String sourceFileName;
          sourceFileName=(String)in.readObject();
          if(name.equals(sourceFileName)==true) {;}
          else return;

          // 本层元素
          this.selROIPoints =in.readInt();
          Rectangle rect=new Rectangle();
          rect=(Rectangle)in.readObject();
          this.setROI(rect);

          //this is very important! ！！！
          LayerText layerText2=(LayerText)in.readObject();
          this.layerText =layerText2.getLayerText() ;

          //LayerLine layerLine2=(LayerLine)in.readObject();
          //this.layerLine=layerLine2;
          // Encrypt
          this.call(this.classLayerLine,this.layerLine,"myReadExternal",new Object[]{in});

          Layer1D layer1D2=(Layer1D)in.readObject();
          this.layer1D =layer1D2.getLayer1D();

          LayerCell layerCell2=(LayerCell)in.readObject();
          this.layerCell =layerCell2.getLayerCell();

          layerText.setDialogImage(this);

          //layerLine.setDialogImage(this);
          // Encrypt
          call(this.classLayerLine,layerLine,"setDialogImage",new Object[]{this});

          layer1D.setDialogImage(this);
          layer1D.initForSerial(this);
          layerCell.setDialogImage(this);

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

  public void exportResult(){
    /*
    FileDialog filedlg=new FileDialog(this.frameMain ,"导出结果文件选择：");
    filedlg.show();
    if(filedlg.getFile().length() ==0) return;
    File file=new File(filedlg.getDirectory() +filedlg.getFile());
    if(file.exists() ==true) {
      JOptionPane myOptionPane=new JOptionPane();
      Object[] options = { "覆盖之", "不覆盖"};
      int  selectOption;
      selectOption=myOptionPane.showOptionDialog(null,
          "此文件已存在，将覆盖吗？", "Warning",
          JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE ,
          null, options, options[0]);
      if(selectOption==1) return;
    }
    */

    int dotLoc=name.indexOf(".");
    String beforeName;
    if(dotLoc==-1) beforeName=name;
    else beforeName=name.substring(0,dotLoc);
    String fileName=path +beforeName;
    if(this.format==OpenFile.BMP) fileName+=".BGS";
    else if(this.format ==OpenFile.TIFF) fileName+=".TGS";
    else if(this.format ==OpenFile.JPEG) fileName+=".JGS";
    else fileName+=".GGS";
    File file=new File(fileName);
    //System.out.println(file);

    try{
      //ObjectOutputStream o=
      //  new ObjectOutputStream(
      //  new FileOutputStream(filedlg.getDirectory() +filedlg.getFile()));
      ObjectOutputStream o=
        new ObjectOutputStream(
        new FileOutputStream(fileName));

      o.writeObject(this.name);

      // 本层元素
      o.writeInt(this.selROIPoints);
      o.writeObject(this.getROI());

      o.writeObject(layerText);

      //o.writeObject(layerLine);
      //Encrypt
      //myWriteExternal(ObjectOutput out){
      call(classLayerLine,layerLine,"myWriteExternal",new Object[]{o});

      o.writeObject(layer1D);
      o.writeObject(layerCell);

      o.close();
      isChangeLayers=false;
    }
   catch(Exception e3){
      e3.printStackTrace() ;
   }
  }

  public void setSelROIPoints(int points){
    this.selROIPoints =points;
  }

  public void setSelROIPoints_2(int points){
    this.selROIPoints_2 =points;
  }

  public void clearROI(){
    this.selROIPoints =0;
    this.imagePanel .repaint() ;
  }

  public Rectangle getROI(){
    if(selROIPoints==0){
      return new Rectangle(0,0,getImageWidth() -1 ,getImageHeight() -1);
    }
    else{
      return new Rectangle(rectROIPt1.x,rectROIPt1.y,
                  Math.abs(rectROIPt1.x-rectROIPt2.x+1),
                  Math.abs(rectROIPt1.y-rectROIPt2.y+1));
    }
  }

  public Polygon getSelectPolygon(){
    if(this.selectPGPoints<0) return null;
    return this.selectPolygon;
  }

  public void setROI(Rectangle rect){
    rectROIPt1.x=rect.getLocation().x;
    rectROIPt1.y=rect.getLocation().y;
    rectROIPt2.x=rect.getLocation().x+(int)rect.getWidth()-1;
    rectROIPt2.y=rect.getLocation().y+(int)rect.getHeight()-1;
  }

  public Rectangle getROI_2(){
    if(selROIPoints_2==0){
      //return new Rectangle(0,0,this.getImageWidth()  ,this.getImageHeight() );
      return new Rectangle(0,0,this.getImageWidth()-1,this.getImageHeight()-1);
    }
    else{
      /*
      return new Rectangle(rectROIPt1_2.x,rectROIPt1_2.y,
                           Math.abs(rectROIPt1_2.x-rectROIPt2_2.x),
                           Math.abs(rectROIPt1_2.y-rectROIPt2_2.y));
      */
      return new Rectangle(rectROIPt1_2.x,rectROIPt1_2.y,
                           Math.abs(rectROIPt1_2.x-rectROIPt2_2.x+1),
                           Math.abs(rectROIPt1_2.y-rectROIPt2_2.y+1));
    }
  }

  public boolean existROI(){
    if (selROIPoints==2 ) return true;
    else return false;
  }

  public boolean existROI_2(){
    if (selROIPoints_2==2 ) return true;
    else return false;
  }

  public void loadMainImage(){
  }

  public Image getImage2(){
    return this.image;
  }

  public void setImage2(Image img){
    this.image =img;
  }

  public Frame getframeMain(){
    return  frameMain;
  }

  /**
   * <p>Title: 内部类：图像放置板及绘图</p>
   * <p>Description: </p>
   * <p>Copyright: Copyright (c) 2003</p>
   * <p>Company: </p>
   * @author 王兴胜、易厚富
   * @version 1.0
   */
  class ImagePanel  extends  JPanel{
    Class cls;
    public ImagePanel(Class cls){
      this.cls =cls;
    }
    /**
     * 绘制各层图像、图形
     * @param g
     */
    public void paint(Graphics g){
      super.paint(g);      // very important!!!!
      Graphics2D g2d;
      g2d=(Graphics2D)g;
      g2d.scale(scale,scale);
      g2d.drawImage(image,oriPoint.x,oriPoint.y,this);
      layerText.draw(g,oriPoint);

      //layerLine.draw(g,oriPoint);
      // Encrypt
      try{
        Method md[]=this.cls.getMethods();
        String name;
        Method method=null;
        for(int ii=1;ii<=md.length;ii++){
          name=md[ii-1].getName();
          if(name.equals("draw")){
            method=md[ii-1];
            break;
          }
        }
        //Method method=cls.getMethod("draw", new Class[]{g.getClass(),oriPoint.getClass()});
        method.invoke(layerLine,new Object[]{g,oriPoint});
      }
      catch(InvocationTargetException e) {
        e.printStackTrace();
      }
      catch(IllegalAccessException e) {
        e.printStackTrace();
      }
      //catch(NoSuchMethodException e){
      //  e.printStackTrace();
      //}

      layer1D.draw(g,oriPoint);
      layerCell.draw(g,oriPoint);
      drawBasic(g2d);
    }
  }

  public void imageBigger(){
    this.frameMain.setCmmStatus("imageBigger");
  }

  public void imageSmaller(){
    this.frameMain.setCmmStatus("imageSmaller");
  }

  public void imageOrigin(){
     scale=1;
     oriPoint.x=0;
     oriPoint.y=0;
     this.oldOriPoint .x =0;
     this.oldOriPoint .y =0;
     paintImage();
  }

  public void paintImage(){
    this.imagePanel .repaint() ;
  }

  private void drawBasic(Graphics2D g2d){
    //画框选的东西
    if(selROIPoints==1){
      g2d.setColor(new Color(255,0,0));
      g2d.drawOval((int)(rectROIPt1.x+oriPoint.x),
                   (int)(rectROIPt1.y+oriPoint.y),1,1);
    }
    else if(selROIPoints==2){
      g2d.setColor(new Color(0,255,255));
      int tempint;
      if(rectROIPt2.x<rectROIPt1.x){
        tempint=rectROIPt1.x;
        rectROIPt1.x=rectROIPt2.x;
        rectROIPt2.x=tempint;
      }
      if(rectROIPt2.y<rectROIPt1.y){
        tempint=rectROIPt1.y;
        rectROIPt1.y=rectROIPt2.y;
        rectROIPt2.y=tempint;
      }
      g2d.drawRect(rectROIPt1.x+oriPoint.x ,
                   rectROIPt1.y+oriPoint.y ,
                   Math.abs(rectROIPt1.x-rectROIPt2.x),
                   Math.abs(rectROIPt1.y-rectROIPt2.y));
    }

    //画框选的东西
    if(selROIPoints_2==1){
      g2d.setColor(new Color(255,0,0));
      g2d.drawOval((int)(rectROIPt1_2.x+oriPoint.x),
                   (int)(rectROIPt1_2.y+oriPoint.y),1,1);
    }
    else if(selROIPoints_2==2){
      g2d.setColor(new Color(255,0,0));
      int tempint;
      if(rectROIPt2_2.x<rectROIPt1_2.x){
        tempint=rectROIPt1_2.x;
        rectROIPt1_2.x=rectROIPt2_2.x;
        rectROIPt2_2.x=tempint;
      }
      if(rectROIPt2_2.y<rectROIPt1_2.y){
        tempint=rectROIPt1_2.y;
        rectROIPt1_2.y=rectROIPt2_2.y;
        rectROIPt2_2.y=tempint;
      }
      g2d.drawRect(rectROIPt1_2.x+oriPoint.x ,
                   rectROIPt1_2.y+oriPoint.y ,
                   Math.abs(rectROIPt1_2.x-rectROIPt2_2.x),
                   Math.abs(rectROIPt1_2.y-rectROIPt2_2.y));
    }

    // 画白蓝斑点
    int sz=this.whiteBacterial.size();
    Point pt,pt1,pt2;
    int lineLen=5;
    for(int ii=1;ii<=sz;ii++){
      pt=(Point)this.whiteBacterial.elementAt(ii-1);
      g2d.setColor(Color.yellow);
      g2d.drawLine(pt.x-lineLen+oriPoint.x,pt.y+oriPoint.y,pt.x-1+oriPoint.x,pt.y+oriPoint.y);
      g2d.drawLine(pt.x+1+oriPoint.x,pt.y+oriPoint.y,pt.x+lineLen+oriPoint.x,pt.y+oriPoint.y);
      g2d.drawLine(pt.x+oriPoint.x,pt.y+1+oriPoint.y,pt.x+oriPoint.x,pt.y+lineLen+oriPoint.y);
      g2d.drawLine(pt.x+oriPoint.x,pt.y-lineLen+oriPoint.y,pt.x+oriPoint.x,pt.y-1+oriPoint.y);
      g2d.setColor(Color.magenta);
      g2d.drawOval(pt.x+oriPoint.x-lineLen,pt.y+oriPoint.y-lineLen,lineLen*2,lineLen*2);
    }

    sz=this.blueBacterial.size();
    for(int ii=1;ii<=sz;ii++){
      pt=(Point)this.blueBacterial.elementAt(ii-1);
      g2d.setColor(Color.blue);
      g2d.drawLine(pt.x-lineLen+oriPoint.x,pt.y+oriPoint.y,pt.x-1+oriPoint.x,pt.y+oriPoint.y);
      g2d.drawLine(pt.x+1+oriPoint.x,pt.y+oriPoint.y,pt.x+lineLen+oriPoint.x,pt.y+oriPoint.y);
      g2d.drawLine(pt.x+oriPoint.x,pt.y+1+oriPoint.y,pt.x+oriPoint.x,pt.y+lineLen+oriPoint.y);
      g2d.drawLine(pt.x+oriPoint.x,pt.y-lineLen+oriPoint.y,pt.x+oriPoint.x,pt.y-1+oriPoint.y);
      g2d.setColor(Color.magenta);
      g2d.drawOval(pt.x+oriPoint.x-lineLen,pt.y+oriPoint.y-lineLen,lineLen*2,lineLen*2);
    }

    //画连线
    if(linePoints==1){
      g2d.setColor(new Color(255,0,0));
      g2d.drawLine(linePt1.x+oriPoint.x,linePt1.y+oriPoint.y ,
                   linePt2.x+oriPoint.x,linePt2.y+oriPoint.y);
    }

    // draw the select Polygon
    if(this.selectPGPoints>0){
      g2d.setColor(Color.magenta);
      if(this.selectShape=='p' || this.selectPGPoints==100){
        if(this.selectPGPoints==1){
          g2d.drawOval(this.selectPolygon.xpoints[0]+oriPoint.x-2,
                       this.selectPolygon.ypoints[0]+oriPoint.y-2,4,4);
        }
        else{
          int xx[]=new int[this.selectPolygon.npoints];
          int yy[]=new int[this.selectPolygon.npoints];
          for(int ii=1;ii<=xx.length;ii++){
            xx[ii-1]=this.selectPolygon.xpoints[ii-1]+oriPoint.x;
            yy[ii-1]=this.selectPolygon.ypoints[ii-1]+oriPoint.y;
          }
          if(this.selectShape=='p') g2d.drawPolyline(xx,yy,xx.length);
          else g2d.drawPolygon(new Polygon(xx,yy,xx.length));
        }
      }
      else {
        int x1=this.selectPolygon.xpoints[0];
        int y1=this.selectPolygon.ypoints[0];
        int x2=this.selectPolygon.xpoints[1];
        int y2=this.selectPolygon.ypoints[1];
        int tmp;
        if(x1>x2) {tmp=x1;x1=x2;x2=tmp;}
        if(y1>y2) {tmp=y1;y1=y2;y2=tmp;}
        if(this.selectShape=='r'){
          g2d.drawRect(x1+oriPoint.x,y1+oriPoint.y,x2-x1,y2-y1);
        }
        else{
          int cx=(int)((x1+x2)/2+0.5);
          int cy=(int)((y1+y2)/2+0.5);
          int a=(int)((x2-x1)/2+0.5);
          int b=(int)((y2-y1)/2+0.5);
          if(this.selectShape=='c')
            g2d.drawOval(x1+oriPoint.x-a,y1+oriPoint.y-a,2*a,2*a);
          else
            g2d.drawOval(x1+oriPoint.x-a,y1+oriPoint.y-b,2*a,2*b);
        }
      }
    }

  }

  public String getImageName(){
    return name;
  }

  void this_windowActivated(WindowEvent e) {
    ((FrameMain)frameMain).setCurrentImage(this);
    this.setBasicInformation();
  }

  void imagePanel_mouseClicked(MouseEvent e) {
    Point p=new Point(0,0);
    p.x =(int)((e.getPoint().getX())/scale-oriPoint.x);
    p.y =(int)((e.getPoint().getY())/scale-oriPoint.y);

    String cmmStatus;
    cmmStatus=((FrameMain)frameMain).getCmmStatus();
    if(cmmStatus=="insertText") {
      // by wxs 20031003-2
      //layerText.addElement("Welcome To EasyGels",p,true,"Text",1,true);
      int id=layerText.ID;
      layerText.addElement("Welcome To EasyGels",p,true,"Text",1,id);
      layerText.ID=id+1;
      this.frameMain.createControlText() ;
      layerText.mouseClicked(p);
    }
    else if(cmmStatus=="basicSelText"){
      layerText.mouseClicked(p);

      //layerLine.mouseClicked(p);
      // Encrypt
      call(this.classLayerLine,layerLine,"mouseClicked",new Object[]{p});

      imagePanel.repaint();
    }
    else if(cmmStatus=="histsellane"){
      Rectangle rect;
      rect=this.layer1D.getLaneRectangle(p);
      if(rect==null) return;
      Point Pt1,Pt2;
      Pt2=new Point(0,0);
      Pt1=rect.getLocation();
      Pt2.x=Pt1.x+(int)rect.getWidth()-1;
      Pt2.y=Pt1.y+(int)rect.getHeight()-1;
      //System.out.println("Pt1,Pt2\n"+Pt1+"\n"+Pt2);
      frameMain .dialogDrawHist .drawRectYHist(Pt1,Pt2);
    }
    else if (cmmStatus=="pasteImage"){
      this.pasteImage(p,frameMain.getCopyData() ,
                       frameMain.getCopyWidht(),
                       frameMain.getCopyHeight(),1);
    }
    else if(cmmStatus=="selectPolygon"){
      this.selectShape='p';
      this.selectPGPoints++;
      this.selectPolygon.addPoint(p.x,p.y);
      this.paintImage();
    }
    else if(cmmStatus=="DelObject"){
      this.layerCell .deleteObject(p);
      this.frameMain .getDialogControlCell() .resetCombox() ;
    }
    else if(cmmStatus=="imageBigger"){
      if(scale<1) scale+=0.1;
      else scale+=0.25;
      if(scale>=0.95 && scale<=1.05) scale=1;
      String tip;
      tip="（"+((int)(scale*10000))/100+"％）";
      this.setTitle(tip);
      // 移动原点
      this.oriPoint=new Point((int)(p.x-e.getPoint().x/scale)*(-1),
                              (int)(p.y-e.getPoint().y/scale)*(-1));
      this.paintImage();
    }
    else if(cmmStatus=="imageSmaller"){
      if(scale>=1) scale-=0.25;
      else scale-=0.1;
      if(scale>=0.95 && scale<=1.05) scale=1;
      String tip;
      tip="（"+((int)(scale*10000))/100+"％）";
      this.setTitle(tip);
      // 移动原点
      this.oriPoint=new Point((int)(p.x-e.getPoint().x/scale)*(-1),
                              (int)(p.y-e.getPoint().y/scale)*(-1));
      this.paintImage();
    }
    else if(cmmStatus=="AddBacterial"){
      if(e.getModifiers()==e.BUTTON1_MASK){
        this.whiteBacterial.addElement(p);
      }
      else if(e.getModifiers()==e.BUTTON3_MASK){
        this.blueBacterial.addElement(p);
      }
      this.paintImage();
    }
    else if(cmmStatus=="DeleteBacterial"){
      this.deleteBacterial(p);
      this.paintImage();
    }
    else if(cmmStatus=="selectPolygon"){
      this.selectPolygon.addPoint(p.x,p.y);
      this.selectPGPoints=this.selectPolygon.npoints;
      this.paintImage();
    }
    else if(cmmStatus=="DetailObject"){
      this.detailObject(p);
    }
    else {
    }
  }

  // 细分目标
  private void detailObject(Point pt){
    InfoCell cell=this.layerCell.getInfoCell(pt);
    this.frameMain.getDialogControlCell().detailCell(cell,true);
  }

  private void deleteBacterial(Point pt){
    int sz=this.whiteBacterial.size();
    Point p;
    for(int ii=1;ii<=sz;ii++){
      p=(Point)this.whiteBacterial.elementAt(ii-1);
      if(p.equals(pt)){
        this.whiteBacterial.removeElement(p);
        return;
      }
    }

    sz=this.blueBacterial.size();
    for(int ii=1;ii<=sz;ii++){
      p=(Point)this.blueBacterial.elementAt(ii-1);
      if(p.equals(pt)){
        this.blueBacterial.removeElement(p);
        return;
      }
    }
  }

  public void initSelectPolygon(){
    this.selectPGPoints=0;
    this.selectPolygon=new Polygon();
  }

  public void setSelectPolygonNull(){
    this.selectPGPoints=-1;
    this.selectPolygon=null;
  }

  public void delSelectedObjects(){
    this.layerText .deleteSelectedElement(true,"Text");

    //this.layerLine .deleteSelectedElement(true,"Line");
    // Encrypt
    call(this.classLayerLine,layerLine,"deleteSelectedElement",new Object[]{new Boolean(true),new String("Line")});

  }

  public void docmmUndo(){
    if(this.mainDone==null) return;
    else{
      if(this.mainDone.size()==0) return;
    }

    int doneSize;
    String type;
    int step=99;
    //System.out.println("maindo size="+mainDone.size()+"="
    //                   +mainDone.elementAt(mainDone.size()-1));
    while(step>1){
      //System.out.println("be step"+step);
      type=this.getMainDoneKeyString();

      doneSize=mainDone.size();
      //mainDone.removeElementAt(doneSize-1);
      mainDone.remove(doneSize-1);
      step=((Integer)mainDoneStep.elementAt(doneSize-1)).intValue();
      //mainDoneStep.removeElementAt(doneSize-1);
      mainDoneStep.remove(doneSize-1);

      if(type.equals("None")) return;
      else if(type.equals("Text"))  layerText.undo();
      else if(type.equals("Line"))  {
        //layerLine.undo();
        // Encrypt
        call(this.classLayerLine,layerLine,"undo",null);
      }
      else if(type.equals("Image")) undoImage();
      else if(type.equals("Cell"))  layerCell.undo();

      //System.out.println("af type:"+type+",step"+step);
    }
    imagePanel.repaint();
  }

  public void printUndoStatus(String auxString){
    String str="\nTotal Status -- "+auxString+":";
    String aaa;
    for(int ii=1;ii<=mainDone.size();ii++){
      aaa=((Integer)this.mainDoneStep.elementAt(ii-1)).toString();
      str+="\n                ["+ii+"] "+(String)this.mainDone.elementAt(ii-1)+",No="+aaa;
    }
    System.out.println(str);
  }

  public String getMainDoneKeyString(){
    int doneSize;
    doneSize=mainDone.size();
    if(doneSize<=0) return "None";
    else return (String)mainDone.elementAt(doneSize-1);
  }

  public void setMainDone(String doneKeyString,int step){
    mainDone.addElement(doneKeyString);
    mainDoneStep.addElement(new Integer(step));
  }

  private void docmmInsertLine(Point point,boolean isReleased){
    this.isFinishedLine =isReleased;
    if(linePoints==0){
      linePt1.x=point.x;
      linePt1.y=point.y;
      linePt2.x=point.x;
      linePt2.y=point.y;
      linePoints=1;
      this.isFinishedLine =false;
    }
    else if(linePoints==1){
      if(isReleased==true){
          linePt2.x=point.x;
          linePt2.y=point.y;
          linePoints=0;

          //layerLine.addElement(linePt1,linePt2,true,"Line",1,true);
          // Encrypt
          //call(this.classLayerLine,layerLine,"addElement",new Object[]{linePt1,linePt2,
          //    new Boolean(true),new String("Line"),new Integer(1),new Boolean(true)});
          call(this.classLayerLine,layerLine,"addElement",new Object[]{linePt1,linePt2,
              new Boolean(true),new String("Line"),new Integer(1)});

          this.frameMain .createControlLine() ;
          //layerLine.mouseClicked(linePt1);
          // Encrypt
          call(this.classLayerLine,layerLine,"mouseClicked",new Object[]{linePt1});
      }
      else{
          linePt2.x=point.x;
          linePt2.y=point.y;
      }
    }
    imagePanel.repaint();
    /* ！！！文字的旋转
    ip.drawString("asjdflasdjfopasdfiwejflasdfjlas;diwreu",10,50);
    ip.rotate(3.14/4);
    image2=ip.createImage();
    imagePanel.repaint();
    */
  }

  void imagePanel_mouseMoved(MouseEvent e) {
    String tip;
    int gray=0;
    Color color=Color.black;

    tip="（"+((int)(scale*10000))/100+"％）";
    tip+=this.name;
    this.setTitle(tip);

    // 转换为图像的上的点
    Point p=new Point(0,0);
    p.x =(int)((e.getPoint().getX())/scale-oriPoint.x);
    p.y =(int)((e.getPoint().getY())/scale-oriPoint.y);

    String cmmStatus;
    cmmStatus=((FrameMain)frameMain).getCmmStatus();
    Cursor cursor;
    if(cmmStatus=="basicMoveImage"){
    }
    else if(cmmStatus=="moveLane"){
      layer1D.mouseMoved(p);
    }
    else{
    }

    if(this.frameMain.getDialogInforamtion()==null) return;
    if(this.frameMain.getDialogInforamtion().isVisible()==false) return;

    int[] v = getPixel(p.x, p.y);
    if(this.colorMode==this.COLOR_GRAY){
      gray=v[0];
      color=null;
    }
    else{
      color=new Color(v[0],v[1],v[2]);
      gray=v[3];
    }

    if(this.bgBlack==false){
      if(this.bitDepth==16 && this.colorMode==this.COLOR_GRAY) gray=65535-gray;
      else gray=255-gray;
    }

    this.frameMain.getDialogInforamtion().setImageLocRGBGray(p,color,gray,e.getPoint());
  }

  public boolean isPressedShift(){
    return pressedShift;
  }

  void this_keyPressed(KeyEvent e) {
    if(e.getKeyCode() ==KeyEvent.VK_SHIFT ) pressedShift=true;
    if(e.getKeyCode() ==KeyEvent.VK_DELETE)
    {
      ((Layer)layerText).deleteSelectedElement(true,"Text");
      ((Layer)layerLine).deleteSelectedElement(true,"Line");
    }
    if(e.getKeyCode() ==KeyEvent.VK_ESCAPE ){
      selROIPoints=0;
    }
  }
  void this_keyReleased(KeyEvent e) {
    if(e.getKeyCode() ==KeyEvent.VK_SHIFT ) pressedShift=false;
  }

  public void docmmFilter(String cmmStatus,int step){
    if(this.isVisible() ==false) return;
    ImageProcessor oldip;
    oldip=ip.duplicate();
    if(cmmStatus=="smooth") ip.smooth() ;
    else if(cmmStatus=="sharpen") ip.sharpen() ;
    else if(cmmStatus=="findedge") ip.findEdges() ;
    else if(cmmStatus=="medium") ip.medianFilter() ;
    else if(cmmStatus=="addnoise"){
      String str=JOptionPane.showInputDialog("R is: ");
      if(str.length() !=0){
        int r=(Integer.valueOf(str) ).intValue() ;
        ip.noise(r);
      }
    }
    else if(cmmStatus=="rotation180") ip.rotate(180) ;
    else if(cmmStatus=="rotation90") ip.rotate(90) ;
    else if(cmmStatus=="rotation-90") ip.rotate(-90) ;
    else if(cmmStatus=="mirror") ip.flipHorizontal() ;
    else if(cmmStatus=="vmirror") ip.flipVertical() ;
    else if(cmmStatus=="gamma"){
      String str=JOptionPane.showInputDialog("R is: ");
      if(str.length() !=0){
        int r=(Integer.valueOf(str) ).intValue() ;
        ip.gamma(r);
      }
    }
    else if(cmmStatus=="rotationRam"){
      String str=JOptionPane.showInputDialog("Rotating angles is: ");
      if(str.length() !=0){
        int r=(Integer.valueOf(str) ).intValue() ;
         ip.rotate(r);
      }
    }
    //--------------------------
    this.saveIP(oldip,step);
    image=ip.createImage();
    imagePanel.repaint();
  }

  public void imageRevert(int step){
    if(this.isVisible() ==false) return;
    ImageProcessor oldip;
    oldip=ip.duplicate();
    ip.invert();
    this.bgBlack =!this.bgBlack;
    this.ip.isBGBlack=this.bgBlack;
    //--------------------------
    this.saveIP(oldip,step);
    image=ip.createImage();
    imagePanel.repaint();
  }

  public void undoImage(){
    ImageProcessor newip;
    int size;
    size=IPs.size();
    if(size<1) return;
    //undoImageInformation();
    newip=(ImageProcessor)IPs.lastElement();
    ip=newip.duplicate();
    IPs.removeElement(newip);
    image=ip.createImage();
    imagePanel.repaint();

  }

  /*
  private String getStringFromVAndDel(Vector v){
    String string,rt;
    string=(String)v.lastElement();
    rt=new String(string);
    v.removeElement(string);
    return rt;
  }

  private int getIntFromVAndDel(Vector v){
    Integer integer;
    int rt;
    integer=(Integer)v.lastElement();
    rt=integer.intValue();
    v.removeElement(integer);
    return rt;
  }

  private boolean getBooleanFromVAndDel(Vector v){
    Boolean bool;
    boolean rt;
    bool=(Boolean)v.lastElement();
    rt=bool.booleanValue();
    v.removeElement(bool);
    return rt;
  }

  private void setFileInfoFromUndo(Vector v){
    FileInfo info;
    info=(FileInfo)v.lastElement();
    if(info!=null) this.duplicateFileInfo(this.fileInfo,info);
    else this.fileInfo=null;

    v.removeElement(info);
  }

  private void setBmpFromUndo(Vector v){
    Bmp bmp0;
    bmp0=(Bmp)v.lastElement();
    if(bmp0!=null) this.bmp=bmp0.duplicate();
    else this.bmp=null;
    v.removeElement(bmp0);
  }

  private void duplicateFileInfo(FileInfo n,FileInfo o){
    if(o.blues==null) n.blues=null;
    else  duplicateBytes(n.blues,o.blues);
    if(o.reds==null) n.reds=null;
    else  duplicateBytes(n.reds,o.reds);
    if(o.greens==null) n.greens=null;
    else duplicateBytes(n.greens,o.greens);
    if(o.coefficients==null) n.coefficients=null;
    else duplicateDoubles(n.coefficients,o.coefficients);
    n.calibrationFunction=o.calibrationFunction;
    n.description=new String(o.description);
    n.directory=new String(o.directory);
    n.fileFormat=o.fileFormat;
    n.fileName=new String(o.fileName);
    n.fileType=o.fileType;
    n.frameInterval=o.frameInterval;
    n.gapBetweenImages=o.gapBetweenImages;
    n.height=o.height;
    n.info=new String(o.info);
    n.intelByteOrder=o.intelByteOrder;
    n.lutSize=o.lutSize;
    n.nImages=o.nImages;
    n.offset=o.offset;
    n.pixelDepth=o.pixelDepth;
    n.pixelHeight=o.pixelHeight;
    n.pixelWidth=o.pixelWidth;
    // ???  n.pixels
    n.unit=new String(o.unit);
    n.url=new String(o.url);
    n.valueUnit=new String(o.valueUnit);
    n.whiteIsZero=o.whiteIsZero;
    n.width=o.width;
  }

  private void duplicateBytes(byte []n,byte []o){
    int sz=o.length;
    if(sz>=1) n=new byte[sz];
    else n=null;
    for(int ii=1;ii<=sz;ii++) n[ii-1]=o[ii-1];
  }

  private void duplicateDoubles(double []n,double []o){
    int sz=o.length;
    if(sz>=1) n=new double[sz];
    else n=null;
    for(int ii=1;ii<=sz;ii++) n[ii-1]=o[ii-1];
  }

  private void undoImageInformation(){
    // 注意：顺序是反的！！！
    bgBlack=this.getBooleanFromVAndDel(ImageInformation);
    grayBits=this.getIntFromVAndDel(ImageInformation);
    bitDepth=this.getIntFromVAndDel(ImageInformation);
    colorMode=this.getIntFromVAndDel(ImageInformation);
    processorMode=this.getIntFromVAndDel(ImageInformation);
    this.setFileInfoFromUndo(ImageInformation);
    this.setBmpFromUndo(ImageInformation);
    format=this.getIntFromVAndDel(ImageInformation);
    height=this.getIntFromVAndDel(ImageInformation);
    width=this.getIntFromVAndDel(ImageInformation);
    name=this.getStringFromVAndDel(ImageInformation);
    path=this.getStringFromVAndDel(ImageInformation);
  }
  */

  public void saveIP(ImageProcessor ip,int step){
    mainDone.addElement("Image");
    mainDoneStep.addElement(new Integer(step));
    ImageProcessor newip;
    newip=ip.duplicate();
    IPs.addElement(newip);

    // 图象层的信息
    /*
    ImageInformation.addElement(path);
    ImageInformation.addElement(name);
    ImageInformation.addElement(new Integer(width));
    ImageInformation.addElement(new Integer(height));
    ImageInformation.addElement(new Integer(format));
    ImageInformation.addElement(bmp);
    ImageInformation.addElement(fileInfo);
    ImageInformation.addElement(new Integer(processorMode));
    ImageInformation.addElement(new Integer(colorMode));
    ImageInformation.addElement(new Integer(bitDepth));
    ImageInformation.addElement(new Integer(grayBits));
    ImageInformation.addElement(new Boolean(bgBlack));
    */
    /*
    private String path,name;
    private int width,height,format;
    private Bmp bmp;
    private FileInfo fileInfo;
    private int processorMode;
    private int colorMode;
    private int bitDepth;
    public int grayBits;
    private boolean bgBlack;
    */
  }

  public void saveImageOnly(){
    this.frameMain.setCursor(createWaitCursor());
    String saveFile=path+name;
    ImageSaver imageSaver=new ImageSaver(ip,fileInfo);
    if(format ==OpenFile.TIFF ){
      imageSaver.saveAsTiff(saveFile);
    }
    else if(format ==OpenFile.GIF ){
      try{
        GifEncoder gif=new GifEncoder(ip.createImage());
        FileOutputStream out=new FileOutputStream(saveFile);
        gif.write(out);
        out.close();
      }
      catch(IOException e2){
        e2.printStackTrace() ;
      }
    }
    else if(format ==OpenFile.JPEG ){
      imageSaver.saveAsJpeg(saveFile);
    }
    else if(format ==OpenFile.BMP ){
      if((this.colorMode ==this.COLOR_INDEX && this.bitDepth ==4) ||
         (this.colorMode ==this.COLOR_INDEX && this.bitDepth ==1)){
        this.frameMain.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        JOptionPane.showMessageDialog(this.frameMain,"No supported image format","No supported image format",
                                      JOptionPane.WARNING_MESSAGE);
        return;
      }
      SaveBMP saveBMP=new SaveBMP(bmp,ip,saveFile);
    }

    this.exportResult();

    this.frameMain.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    JOptionPane.showMessageDialog(this.frameMain,"Image saved","Image saved",
                                  JOptionPane.INFORMATION_MESSAGE);
  }

  public void saveImageAndLayers(){
    int v,v3,v2,v1;
    Color c,c0;
    String pathName[]=ImageSaver.selectFileToSave(this.format,this.frameMain,"Select a file for saving");
    if(pathName==null) return;
    this.frameMain.setCursor(createWaitCursor());
    OpenFileInformation infor=ImageSaver.createIPFromTmp(format,frameMain,
        false,null,width,height,pathName[0],pathName[1]);
    this.convertIPTo24RGB(infor.ip);
    this.allLayerProjectToIP(infor.ip);
    if(this.format==OpenFile.TIFF && this.colorMode ==this.COLOR_GRAY)
      infor.fileInfo.pixels=infor.ip.getPixels();
    ImageSaver.saveIPAsImage(this.format,infor.fileInfo.directory,
                             infor.fileInfo.fileName,infor.ip,
                             infor.fileInfo,infor.bmp,true,this.frameMain);
    this.frameMain.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
  }

  /**
   * 转化为RGB24模式,GIF文件被转化为TIF文件,转化后均无颜色了,只是灰度值
   */
  public void convertToRGB24(){
    // create new ip from image file
    OpenFileInformation infor=ImageSaver.createIPFromTmp(format,frameMain,
        false,null,width,height,this.path,this.name);

    this.convertIPTo24RGB(infor.ip);

    // set the current values
    this.fileInfo=infor.fileInfo;
    this.colorMode=COLOR_RGB;
    this.bitDepth=24;
    this.ip=infor.ip.duplicate();
    this.image =ip.createImage() ;
    this.paintImage() ;
  }

  public void saveAll2(){
    String end;
    end="";
    if(this.format ==OpenFile.TIFF) end=".tif";
    else if(this.format ==OpenFile.GIF) end=".gif";
    else if(this.format ==OpenFile.JPEG) end=".jpg";
    else if(this.format ==OpenFile.BMP) end=".bmp";

    FileDialog filedlg=new FileDialog(this.frameMain ,"Select a file：???"+end);
    if(this.frameMain .getUser() .hira !=0){
      if(this.frameMain.getUser() .rootWorkDir .length() !=0  ){
        filedlg.setDirectory(this.frameMain .getUser() .rootWorkDir ) ;
      }
    }
    filedlg.setMode(filedlg.SAVE);
    filedlg.show();
    String saveFile;
    if(filedlg.getName().length() !=0){
      saveFile=filedlg.getDirectory() + filedlg.getFile();
      saveFile+=end;
    }
    else  return;

    boolean checked=true;
    if(checked==true){
      File file=new File(saveFile);
      if(file.exists() ==true){
        JOptionPane myOptionPane=new JOptionPane();
        Object[] options = { "Overwrite", "No"};
        int  selectOption;
        selectOption=myOptionPane.showOptionDialog(null,
            "Overwrite?", "Warning",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE ,
            null, options, options[0]);
        if(selectOption==1) return;
        file.delete();
      }
    }

    ImageProcessor oldip=ip.duplicate();
    //layerLine.save(ip,this.oriPoint);
    // Encrypt
    call(this.classLayerLine,layerLine,"save",new Object[]{ip,oriPoint});

    ImageSaver imageSaver=new ImageSaver(ip,fileInfo);
    if(this.format ==OpenFile.TIFF ){
      imageSaver.saveAsTiff(saveFile);
    }
    else if(this.format ==OpenFile.GIF ){
      try{
        GifEncoder gif=new GifEncoder(image);
        FileOutputStream out=new FileOutputStream(saveFile);
        gif.write(out);
        out.close();
      }
      catch(IOException e2){
        e2.printStackTrace() ;
      }
    }
    else if(this.format ==OpenFile.JPEG ){
      imageSaver.saveAsJpeg(saveFile);
    }
    else if(this.format ==OpenFile.BMP ){
      SaveBMP saveBMP=new SaveBMP(bmp,ip,saveFile);
    }

    this.ip=oldip.duplicate();
    this.image=this.ip.createImage();
    this.paintImage();

    // save ok
  }

  public void saveFloat(ImageProcessor ipSave){
    ipSave.setLineWidth(1);
    //ipSave.setDotsMode(true,2,1);
    ipSave.setColor(new Color(0,255,255));
    ipSave.drawRect(this.rectROIPt1.x,this.rectROIPt1.y,
                    this.rectROIPt2.x-this.rectROIPt1.x+1,
                    this.rectROIPt2.y-this.rectROIPt1.y+1);
  }

  public void allLayerProjectToIP(ImageProcessor ipSave){
    Point pt=new Point(0,0);
    this.layerText.save(ipSave,pt);
    //this.layerLine.save(ipSave,pt);
    // Encrypt
    call(this.classLayerLine,layerLine,"save",new Object[]{ipSave,pt});

    this.layer1D.save(ipSave,pt);
    this.layerCell.save(ipSave,pt);
    this.saveFloat(ipSave);
  }

  public void saveAll(){
    /*
    double saveScale=scale;
    Point saveOriPoint=new Point(oriPoint.x,oriPoint.y);
    Point saveOldOriPoint=new Point(oldOriPoint .x,this.oldOriPoint .y);

    scale=1;
    oriPoint.x=0;
    oriPoint.y=0;
    this.oldOriPoint .x =0;
    this.oldOriPoint .y =0;
    paintImage();
    for(int ii=1;ii<=100000;ii++){
      int aaa=0;
      for(int jj=1;jj<=50;jj++){
        aaa++;
      }
    }
    */

    //String end;
    //end=".tif";

    /*
    FileDialog filedlg=new FileDialog(this.frameMain ,"选择保存文件名称：???"+end);
    if(this.frameMain .getUser() .hira !=0){
      if(this.frameMain.getUser() .rootWorkDir .length() !=0  ){
        filedlg.setDirectory(this.frameMain .getUser() .rootWorkDir ) ;
      }
    }
    filedlg.setMode(filedlg.SAVE);
    filedlg.show();
    String saveFile;
    if(filedlg.getName().length() !=0){
      saveFile=filedlg.getDirectory() + filedlg.getFile();
      saveFile+=end;
    }
    else  return;
    boolean checked=true;
    if(checked==true){
      File file=new File(saveFile);
      if(file.exists() ==true){
        JOptionPane myOptionPane=new JOptionPane();
        Object[] options = { "覆盖之", "不覆盖"};
        int  selectOption;
        selectOption=myOptionPane.showOptionDialog(null,
            "此文件已存在，将覆盖吗？", "Warning",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE ,
            null, options, options[0]);
        if(selectOption==1) return;
        file.delete();
      }
    }
    this.paintImage();
    */

    OpenFile openFile2;
    Bmp bmp2=null;
    String tmpPath=frameMain.getSystemDir()+"\\database\\";
    openFile2=new OpenFile(OpenFile.TIFF,tmpPath,"tiftmp.tif",bmp2);
    ImageProcessor ip2=openFile2.getImageProcessor();
    FileInfo fileInfo2=openFile2.getFileInfo();
    //int imageType2=openFile2.getImageType();

    int detw=3;
    int deth=22;
    int w=this.imagePanel.getWidth();
    int h=this.imagePanel.getHeight();
    //int w=this.getImageWidth();
    //int h=this.getImageHeight();
    ip2=ip2.createProcessor(w,h);
    try{
      Robot robot=new Robot(this.imagePanel.getGraphicsConfiguration().getDevice());
      Color c;
      for(int jj=0;jj<h;jj++){
        for(int ii=0;ii<w;ii++){
          c=robot.getPixelColor(ii+this.getLocation().x+detw,
                                jj+this.getLocation().y+deth);
          ip2.putPixel(ii,jj,c.getRGB());
        }
      }
    }
    catch(Exception e2){
      e2.printStackTrace();
    }

    /*
    scale=saveScale;
    oriPoint=saveOriPoint;
    oldOriPoint=saveOldOriPoint;
    this.paintImage();
    */

    String saveFile;
    String tempname;
    int dotloc=name.lastIndexOf(".");
    if(dotloc>=0) tempname=name.substring(0,dotloc);
    else tempname=name;
    saveFile="TIFF_"+tempname+".tif";

    fileInfo2.directory=path;
    fileInfo2.fileName=saveFile;
    fileInfo2.pixels=ip2.getPixels();
    fileInfo2.width=w;
    fileInfo2.height=h;

    ImageSaver imageSaver=new ImageSaver(ip2,fileInfo2);
    imageSaver.saveAsTiff(path+saveFile);

    JOptionPane.showMessageDialog(this.frameMain,"Image and objects saved",
                                  "Image and objects saved",
                                  JOptionPane.INFORMATION_MESSAGE);
    //this.setTitle("图像及元素均保存！");
    this.saveAsTiff=true;
    this.tiffHeight=h;
    this.tiffName="TIFF_"+tempname+".tif";
    this.tiffPath=path;
    this.tiffWidht=w;
  }

  public int getTiffHeight(){
    return this.tiffHeight;
  }
  public int getTiffWidth(){
    return this.tiffWidht;
  }
  public String getTiffPath(){
    return this.tiffPath;
  }
  public String getTiffName(){
    return this.tiffName;
  }

  void this_windowClosed(WindowEvent e) {
    if(isChangeLayers==true) this.exportResult() ;
    this.isClosed =true;
    // this.frameMain .setTitle("closed the " +this.getClass() .getName() );
    // this.dispose() ;
  }

  public boolean isClosed(){
    return this.isClosed ;
  }


  void this_windowDeactivated(WindowEvent e) {
  }

  public LayerText getLayerText(){
    return this.layerText;
  }

  //public LayerLine getLayerLine(){
  // Encrypt
  public Object getLayerLine(){
    return this.layerLine;
  }

  //LiuSheng
   public Layer1D getLayer1D(){
     return this.layer1D ;
  }

  //Liusheng 2003-04-11
  public void autoReco(){
    layer1D.reclane(60,1);
    layer1D.getline(8,60,20,0);
    this.imagePanel .repaint() ;
  }

  public ImageProcessor getIP(){
    return this.ip;
  }

  public int getImageWidth(){
    return this.image .getWidth(this);
  }

  public int getImageHeight(){
    return this.image .getHeight(this);
  }

  public String getImagePath(){
    return this.path ;
  }

  public int getImageFormat(){
    return this.format ;
  }

  //LiuSheng
  void imagePane1_mousePressed(MouseEvent e){
    String cmmStatus;
    cmmStatus=((FrameMain)frameMain).getCmmStatus();

    if(e.getModifiers()==e.BUTTON3_MASK && cmmStatus.equals("AddBacterial")==false
       && cmmStatus.equals("DeleteBacterial")==false) {
      frameMain.showPopMenu(this.getContentPane() ,e.getPoint().x,e.getPoint().y);
      return;
    }

    Point p=new Point(0,0);
    p.x =(int)((e.getPoint().getX())/scale-oriPoint.x);
    p.y =(int)((e.getPoint().getY())/scale-oriPoint.y);

    if(cmmStatus=="moveLane"){
      layer1D.mousepressed(p);
    }
    else if(cmmStatus=="insert line")  {
      layer1D.insline(p);
      imagePanel.repaint();
    }
    else if(cmmStatus=="insertLine")
      {docmmInsertLine(p,false);}             //basic
    else if(cmmStatus=="delete line")  {
      layer1D.delline(p);
      imagePanel.repaint();
    }
    else if(cmmStatus=="revise") {
      //by wxs holiday 2003/07/06
      this.getLayer1D().getrevisept(p);      //Liusheng add later
      imagePanel.repaint();
    }
    else if(cmmStatus=="basicSelROI"){
      if(selROIPoints==0){
         rectROIPt1.x=p.x;
         rectROIPt1.y=p.y;
         selROIPoints =1;
      }
      imagePanel.repaint();
    }
    else if(cmmStatus=="basicSelROI_2"){
      if(selROIPoints_2==0){
         rectROIPt1_2.x=p.x;
         rectROIPt1_2.y=p.y;
         selROIPoints_2 =1;
      }
      imagePanel.repaint();
    }
    else if(cmmStatus=="basicMoveImage"){
      if(this.hitFirstPoint ==true) return;
      Cursor cursor;
      cursor=new Cursor(Cursor.MOVE_CURSOR);
      //setCursor(cursor);
      moveImageFirstPoint=e.getPoint();
      hitFirstPoint=true;
    }
    else if(cmmStatus=="selectRectangle"){
      this.selectShape='r';
      this.selectPGPoints=1;
      this.selectPolygon.addPoint(p.x,p.y);
    }
    else if(cmmStatus=="selectCircle"){
      this.selectShape='c';
      this.selectPGPoints=1;
      this.selectPolygon.addPoint(p.x,p.y);
    }
    else if(cmmStatus=="selectEllipse"){
      this.selectShape='e';
      this.selectPGPoints=1;
      this.selectPolygon.addPoint(p.x,p.y);
    }
    else{
      //  为移动Text/Line之用
      if(this.hitFirstPoint ==true) return;
      moveImageFirstPoint=e.getPoint();
      hitFirstPoint =true;
      //layerLine.setPreparePoint();
      // Encrypt
      call(this.classLayerLine,layerLine,"setPreparePoint",null);

      layerText.setPreparePoint();
    }
  }

  //LiuSheng
  void imagePanel_mouseReleased(MouseEvent e){
    Point p=new Point(0,0);
    p.x =(int)((e.getPoint().getX())/scale-oriPoint.x);
    p.y =(int)((e.getPoint().getY())/scale-oriPoint.y);

    String cmmStatus;
    cmmStatus=((FrameMain)frameMain).getCmmStatus();
    if(cmmStatus=="moveLane"){
      layer1D.mouseReleased(e);
    }
    else if(cmmStatus=="insertLine") docmmInsertLine(p,true);  //basic
    else if(cmmStatus=="basicSelROI"){
       oldRectROIPt1.x  =rectROIPt1.x ;
       oldRectROIPt1.y  =rectROIPt1.y ;
       oldRectROIPt2.x  =rectROIPt2.x;
       oldRectROIPt2.y  =rectROIPt2.y;
       ((FrameMain)frameMain).setCmmStatusDefault();
    }
    else if(cmmStatus=="basicSelROI_2"){
       oldRectROIPt1_2.x  =rectROIPt1_2.x ;
       oldRectROIPt1_2.y  =rectROIPt1_2.y ;
       oldRectROIPt2_2.x  =rectROIPt2_2.x;
       oldRectROIPt2_2.y  =rectROIPt2_2.y;
       ((FrameMain)frameMain).setCmmStatusDefault();
    }
    else if(cmmStatus=="basicMoveImage"){
      if(this.hitFirstPoint ==false)return;
      Cursor cursor;
      cursor=new Cursor(Cursor.DEFAULT_CURSOR );
      //this.setCursor(cursor);
      int   detx,dety;
      detx=(int)((e.getPoint().x -moveImageFirstPoint.x));
      dety=(int)((e.getPoint().y -moveImageFirstPoint.y));
      oriPoint.x=oldOriPoint.x+detx;
      oriPoint.y=oldOriPoint.y+dety;
      oldOriPoint.x =oriPoint.x ;
      oldOriPoint.y =oriPoint.y ;
      this.hitFirstPoint =false;
    }
    else if(cmmStatus=="selectRectangle" || cmmStatus=="selectCircle" || cmmStatus=="selectEllipse"){
      if(this.selectPGPoints==1){
        this.selectPolygon.addPoint(p.x,p.y);
        this.selectPGPoints=2;
      }
      else{
        this.selectPolygon.xpoints[1]=p.x;
        this.selectPolygon.ypoints[1]=p.y;
      }
      this.createSelectPolygon();
      this.paintImage();
    }
    else{
      if(this.hitFirstPoint ==false)return;
      int   detx,dety;
      detx=(int)((e.getPoint().x -moveImageFirstPoint.x));
      dety=(int)((e.getPoint().y -moveImageFirstPoint.y));
      this.hitFirstPoint =false;
      layerText.moveText(detx,dety,true);

      //layerLine.moveLine(detx,dety,true);
      try{
        Method method=this.classLayerLine.getMethod("moveLine",
            new Class[]{int.class,int.class,boolean.class});
        method.invoke(layerLine,new Object[]{new Integer(detx),new Integer(dety),new Boolean(true)});
      }
      catch(NoSuchMethodException e2){}
      catch(InvocationTargetException e2) {}
      catch(IllegalAccessException e2) {}
    }
    imagePanel.repaint();
  }

  private void createSelectPolygon(){
    int x1=this.selectPolygon.xpoints[0];
    int y1=this.selectPolygon.ypoints[0];
    int x2=this.selectPolygon.xpoints[1];
    int y2=this.selectPolygon.ypoints[1];
    int tmp;
    if(x1==x2 && y1==y2){
      this.setSelectPolygonNull();
      return;
    }
    if(x1>x2) {tmp=x1;x1=x2;x2=tmp;}
    if(y1>y2) {tmp=y1;y1=y2;y2=tmp;}
    this.selectPGPoints=100;
    if(this.selectShape=='r'){
      this.selectPolygon=new Polygon();
      /*
      this.selectPolygon.addPoint(x1,y1);
      this.selectPolygon.addPoint(x2,y1);
      this.selectPolygon.addPoint(x2,y2);
      this.selectPolygon.addPoint(x1,y2);
      */
      for(int xx=x1;xx<=x2;xx++) this.selectPolygon.addPoint(xx,y1);
      for(int yy=y1+1;yy<=y2;yy++) this.selectPolygon.addPoint(x2,yy);
      for(int xx=x2-1;xx>=x1;xx--) this.selectPolygon.addPoint(xx,y2);
      for(int yy=y2-1;yy<=y1+1;yy--) this.selectPolygon.addPoint(x1,yy);
    }
    else{
      int a=(int)((x2-x1)/2-1);
      int b=(int)((y2-y1)/2-1);
      int cx=(int)((x1+x2)/2-a-1);
      int cy=(int)((y1+y2)/2-b-1);
      int xx[]=this.getPointXFromOval(cx,a);
      int yy[];
      if(this.selectShape=='c') yy=this.getPointYFromOval(cx,cy,a,a);
      else                      yy=this.getPointYFromOval(cx,cy,a,b);
      this.selectPolygon=new Polygon(xx,yy,xx.length);
    }
    this.frameMain.setCmmStatusDefault();
  }

  private int[] getPointYFromOval(double cx,double cy,double a,double b){
    int []yy=new int[((int)a*2+1)*2];
    for(int xx=(int)cx-(int)a;xx<=cx+a;xx++){
      yy[xx-(int)cx+(int)a]=(int)(cy-b*Math.sqrt(1-(xx-cx)*(xx-cx)/(a*a))+0.5);
    }
    for(int xx=(int)cx+(int)a;xx>=cx-a;xx--){
      yy[3*((int)a)+1+(int)cx-xx]=(int)(cy+b*Math.sqrt(1-(xx-cx)*(xx-cx)/(a*a))+0.5);
    }
    return yy;
  }

  private int[] getPointXFromOval(int cx,int a){
    int []xx=new int[(a*2+1)*2];
    for(int yy=cx-a;yy<=cx+a;yy++){
      xx[yy-cx+a]=yy;
    }
    for(int yy=cx+a;yy>=cx-a;yy--){
      xx[3*a+1+cx-yy]=yy;
    }
    return xx;
  }

  public int[][] cutImage(int step){
    if(this.selROIPoints !=2) return null;
    int[][] pixels;
     pixels=null;
     ImageProcessor oldip;
     oldip=ip.duplicate();
     pixels=new int[rectROIPt2.x-rectROIPt1.x+1][rectROIPt2.y-rectROIPt1.y+1];
     for(int ii=this.rectROIPt1 .x ;ii<=this.rectROIPt2 .x ;ii++){
         for(int jj=this.rectROIPt1 .y ;jj<=this.rectROIPt2 .y ;jj++){
              pixels[ii-this.rectROIPt1.x][jj-this.rectROIPt1 .y ]= ip.getPixel(ii,jj);
              ip.putPixelValue(ii,jj,0.0);
         }
     }
     this.saveIP(oldip,step);
     image=ip.createImage();
     imagePanel.repaint();
     return pixels;
  }

  public void clearROIIn(int step){
    if(this.selROIPoints !=2) return ;
     ImageProcessor oldip;
     oldip=ip.duplicate();
     for(int ii=this.rectROIPt1 .x ;ii<=this.rectROIPt2 .x ;ii++){
         for(int jj=this.rectROIPt1 .y ;jj<=this.rectROIPt2 .y ;jj++){
              ip.putPixelValue(ii,jj,0.0);
         }
     }
     this.saveIP(oldip,step);
     image=ip.createImage();
     imagePanel.repaint();
  }

  public void clipROI(){
    //this.ip.setRoi(this.getROI());
    int w=(int)(this.getROI().getWidth()+0.5);
    int h=(int)(this.getROI().getHeight()+0.5);
    Point pt=this.getROI().getLocation();
    int []pixels=(int [])this.ip.getPixels();
    this.ip=this.ip.createProcessor(w,h);
    for(int ii=0+pt.x;ii<w+pt.x;ii++){
      for(int jj=0+pt.y;jj<h+pt.y;jj++){
        this.ip.putPixel(ii-pt.x,jj-pt.y,pixels[this.width*jj+ii]);
      }
    }
    // 调整宽于高
    this.width=w;
    this.height=h;
    // 调整图象信息
    if(this.fileInfo!=null){
      this.fileInfo.width=w;
      this.fileInfo.height=h;
    }
    // 调整ROI
    Rectangle rect=this.getROI();
    rect.setLocation(0,0);
    this.setROI(rect);
    // 图象位置复原
    this.imageOrigin();
    // 重新显示图象
    this.image=this.ip.createImage();
    this.paintImage();
  }

  public void clearROIOut(int step){
    if(this.selROIPoints !=2) return ;
     ImageProcessor oldip;
     oldip=ip.duplicate();
     for(int ii=1;ii<=width ;ii++){
         for(int jj=1;jj<=height;jj++){
           if(ii>=this.rectROIPt1 .x  && ii<=this.rectROIPt2 .x  &&
                jj>=this.rectROIPt1 .y && jj<=this.rectROIPt2 .y )
               continue;
            ip.putPixelValue(ii-1,jj-1,0.0);
         }
     }
     this.saveIP(oldip,step);
     image=ip.createImage();
     imagePanel.repaint();
  }

  public void setBasicInformation(){
    if(this.frameMain.getDialogInforamtion()==null) return;
    if(this.frameMain.getDialogInforamtion().isVisible()==false) return;
    this.frameMain.getDialogInforamtion().setFileInformation(name,path,
        this.format,this.width,this.height,this.colorMode,this.bitDepth);
  }

  public int getColorMode(){
    return this.colorMode;
  }

  public int getBitDepth(){
    return this.bitDepth;
  }

  public int getGrayBits(){
    return this.grayBits;
  }

  public void set3DColor(){
    JColorChooser jcc=new JColorChooser(this.d3Color);
    Color c=jcc.showDialog(this.frameMain,"Select base color for 3D",this.d3Color);
    if(c==null) return;
    this.d3Color=c;
  }

  public void disp3D(){
    FileOutputStream out;
    try{
      int v;
      // ???
      out=new FileOutputStream("c:\\d3d.txt");

      Rectangle rect=this.getROI();
      int w=(int)rect.getWidth();
      int h=(int)rect.getHeight();

      int w0,w1,h0,h1;
      w0=w/255; w1=w-w0*255;
      h0=h/255; h1=h-h0*255;
      out.write(w0);out.write(w1);
      out.write(h0);out.write(h1);

      v=this.d3Color.getRed();out.write(v);
      v=this.d3Color.getGreen();out.write(v);
      v=this.d3Color.getBlue();out.write(v);

      //for(int jj=rect.y;jj<=rect.y+h-1;jj++){
      for(int jj=rect.y+h-1;jj>=rect.y;jj--){
        for(int ii=rect.x;ii<=rect.x+w-1;ii++){
          v=(int)ip.getPixelValue(ii,jj);
          if(this.bgBlack==false) v=255-v;
          out.write(v);
        }
      }
      out.close() ;
    }
    catch(IOException e2){
      e2.printStackTrace() ;
    }
    catch(Exception e3){
      e3.printStackTrace() ;
    }

    Process process;
        try{
          process=Runtime.getRuntime().exec(this.frameMain.getSystemDir()+"\\bin\\Disp3D.exe");
          /*
          try{
            process.waitFor();
          }
          catch(InterruptedException e5){
            e5.printStackTrace();
          }
          */
        }
        catch(IOException e2){
          e2.printStackTrace();
    }
  }

  public void ampROI(){
    if(this.selROIPoints !=2) return ;
    double sx,sy;
    double w,h;
    w=this.rectROIPt2 .x-this.rectROIPt1 .x ;
    h=this.rectROIPt2 .y-this.rectROIPt1 .y ;
    sx=this.imagePanel.getWidth() /(w);
    sy=this.imagePanel.getHeight() /(h);
    if(sx>sy) scale=sy;
    else scale=sx;
    this.setSize((int)((w)*scale+8),(int)((h)*scale+29));

    this.oriPoint .x=this.rectROIPt1 .x *-1;
    this.oriPoint .y=this.rectROIPt1 .y *-1;
    this.oldOriPoint .x =this.oriPoint .x ;
    this.oldOriPoint .y =this.oriPoint .y ;

    this.paintImage() ;
  }

  public int[][] copyImage(){
     if(this.selROIPoints !=2) return null;
     int[][] pixels;
      pixels=null;
      pixels=new int[rectROIPt2.x-rectROIPt1.x+1][rectROIPt2.y-rectROIPt1.y+1];
      for(int ii=this.rectROIPt1 .x ;ii<=this.rectROIPt2 .x ;ii++){
          for(int jj=this.rectROIPt1 .y ;jj<=this.rectROIPt2 .y ;jj++){
               pixels[ii-this.rectROIPt1.x][jj-this.rectROIPt1 .y ]= ip.getPixel(ii,jj);
          }
      }
     return pixels;
  }

  private void pasteImage(Point oriPoint,int [][]pixels,int width,int height,int step){
     ImageProcessor oldip;
     oldip=ip.duplicate();
     for(int ii=1;ii<=width;ii++){
         for(int jj=1;jj<=height;jj++){
           ip.putPixel(oriPoint.x+ii-1,oriPoint.y+jj-1,pixels[ii-1][jj-1]);
         }
     }
     this.saveIP(oldip,step);
     image=ip.createImage();
     imagePanel.repaint();
  }

  /**
   *
   * @param rect
   * @return 如果背景为白色,将作调整
   */
  private double getRectIntegralGray(Rectangle rect){
    double gray=0;
    int x1=rect.getLocation().x;
    int y1=rect.getLocation().y;
    int x2=x1+(int)rect.getWidth()-1;
    int y2=y1+(int)rect.getHeight()-1;
    int tmp;
    if(this.bgBlack==true){
      for(int ii=x1;ii<=x2;ii++){
        for(int jj=y1;jj<=y2;jj++){
          gray+=(this.getPixel8Gray(ii,jj)&0xFF);
        }
      }
    }
    else{
      for(int ii=x1;ii<=x2;ii++){
        for(int jj=y1;jj<=y2;jj++){
          tmp=getPixel8Gray(ii,jj) & 0xFF;
          gray+=(255-tmp);
        }
      }
    }
    return gray;
  }

  /**
   *
   * @param rect
   * @return 如果背景为白色,将作调整
   */
  private int getRectMaxGray(Rectangle rect){
    int max=0;
    int gray;
    int x1=rect.getLocation().x;
    int y1=rect.getLocation().y;
    int x2=x1+(int)rect.getWidth()-1;
    int y2=y1+(int)rect.getHeight()-1;
    if(this.bgBlack==true){
      for(int ii=x1;ii<=x2;ii++){
        for(int jj=y1;jj<=y2;jj++){
          gray=this.getPixel8Gray(ii,jj)&0xFF;
          if(gray>max) max=gray;
        }
      }
    }
    else{
      for(int ii=x1;ii<=x2;ii++){
        for(int jj=y1;jj<=y2;jj++){
          gray=255-(this.getPixel8Gray(ii,jj)&0xFF);
          if(gray>max) max=gray;
        }
      }
    }
    return max;
  }

  private int getRectMinGray(Rectangle rect){
    int min=255;
    int gray;
    int x1=rect.getLocation().x;
    int y1=rect.getLocation().y;
    int x2=x1+(int)rect.getWidth()-1;
    int y2=y1+(int)rect.getHeight()-1;
    if(this.bgBlack==true){
      for(int ii=x1;ii<=x2;ii++){
        for(int jj=y1;jj<=y2;jj++){
          gray=this.getPixel8Gray(ii,jj)&0xFF;
          if(gray<min) min=gray;
        }
      }
    }
    else{
      for(int ii=x1;ii<=x2;ii++){
        for(int jj=y1;jj<=y2;jj++){
          gray=255-(this.getPixel8Gray(ii,jj)&0xFF);
          if(gray<min) min=gray;
        }
      }
    }
    return min;
  }

  private void delBackAndAjust(int x,int y,int adjustMode,double avgORmax,double bg,double cofi){
    double gray=this.getPixel8Gray(x,y)&0xFF;
    double oldgray=gray;

    // 计算新灰度值
    if(this.bgBlack==false) gray=255-gray;
    if(adjustMode==this.ADJUST_BACK_NONE)
      gray=(int)(gray-bg+0.5);
    else if(adjustMode==this.ADJUST_BACK_256)
      gray=(int)((gray-bg)*(1.0f+bg/256.0f)+0.5);
    else{
      if(bg>=avgORmax){
        if(gray>=bg) gray=255;
        else gray=0;
      }
      else{
        gray=(int)((gray-bg)*avgORmax/(avgORmax-bg)+0.5);
      }
    }
    if(gray<0) gray=0;
    if(gray>255) gray=255;
    if(this.bgBlack==false) gray=255-gray;

    // 调整数据
    double per;
    gray+=1;
    oldgray+=1;
    per=(gray-oldgray)*cofi/oldgray;
    int r,g,b;
    Color c;
    c=new Color(this.getPixelColorInt(x,y));
    r=c.getRed();
    g=c.getGreen();
    b=c.getBlue();
    r*=(1.0f+per);
    g*=(1.0f+per);
    b*=(1.0f+per);
    if(r<0) r=0; if(r>255) r=255;
    if(g<0) g=0; if(g>255) g=255;
    if(b<0) b=0; if(b>255) b=255;
    c=new Color(r,g,b);

    ip.putPixel(x,y,c.getRGB());
  }

  public void  delBackgroundROIAverage(int step,int adjustMode,double cofi){
    // for undo
    this.saveIP(ip,step);

    // change the data to COLOR_RGB
    if(this.colorMode!=this.COLOR_RGB) this.convertToRGB24();

    // count the averageGray
    Rectangle rect=this.getROI();
    double avg=getRectIntegralGray(rect)/(rect.getWidth()*rect.getHeight());
    double avgORmax=avg;
    if(adjustMode==this.ADJUST_BACK_MAX) avgORmax=this.getRectMaxGray(rect);

    // delete the background
    int x1=rect.getLocation().x;
    int x2=x1+(int)rect.getWidth() -1;
    int y1=rect.getLocation().y;
    int y2=y1+(int)rect.getHeight() -1;
    for(int ii=x1;ii<=x2;ii++){
      for(int jj=y1;jj<=y2;jj++){
        this.delBackAndAjust(ii,jj,adjustMode,avgORmax,avg,cofi);
      }
    }

    this.image =ip.createImage() ;
    this.paintImage();
  }

  public void  delBackgroundRollingBall(int radius,int lanes,int step,
                                        int adjustMode,double cofi){
    this.saveIP(ip,step);

    // get the rectangle will to be dealed with
    Rectangle rect;
    rect=this.layer1D.getLaneRectangleRollBall(lanes);
    if(rect==null) return;

    // change the data to COLOR_RGB
    if(this.colorMode!=this.COLOR_RGB) this.convertToRGB24();

    // count the averageGray
    double avg=getRectIntegralGray(rect)/(rect.getWidth()*rect.getHeight());
    double avgORmax=avg;
    if(adjustMode==this.ADJUST_BACK_MAX) avgORmax=this.getRectMaxGray(rect);

    Point point2=new Point((int)(rect.getLocation().x+rect.getWidth() -1),
                           (int)(rect.getLocation().y+rect.getHeight() -1));

    int []hist=DialogDrawHist.histgram(ip,rect.getLocation(),point2,false,this);
    byte []hist2=new byte[hist.length];
    for(int ii=0;ii<hist2.length ;ii++){
      hist2[ii]=(byte)hist[ii];
    }
    int []bg=WxsUiti.rollingBall(hist,radius);

    Color c;
    int r,g,b,bgc;

    for(int ii=rect.getLocation() .y;ii<=point2.y;ii++){
      bgc=bg[ii-rect.getLocation().y];
      for(int jj=rect.getLocation() .x;jj<=point2.x;jj++){
        this.delBackAndAjust(jj,ii,adjustMode,avgORmax,bgc,cofi);
      }
    }

    this.image =ip.createImage() ;
    this.paintImage();
  }


  public void  delBackgroundSelect(int step,int adjustMode,double cofi){
    // for undo
    this.saveIP(ip,step);

    // change the data to COLOR_RGB
    if(this.colorMode!=this.COLOR_RGB) this.convertToRGB24();

    // count the averageGray
    Rectangle rect=this.getROI();
    double avg=getRectIntegralGray(rect)/(rect.getWidth()*rect.getHeight());
    double avgORmax=avg;
    if(adjustMode==this.ADJUST_BACK_MAX) avgORmax=this.getRectMaxGray(rect);

    // get the background from ROI_2
    Rectangle rect_2=this.getROI_2();
    double bg=this.getRectIntegralGray(rect_2)/(rect_2.getWidth()*rect_2.getHeight());

    // delete the background
    int x1=rect.getLocation().x;
    int x2=x1+(int)rect.getWidth() -1;
    int y1=rect.getLocation().y;
    int y2=y1+(int)rect.getHeight() -1;
    for(int ii=x1;ii<=x2;ii++){
      for(int jj=y1;jj<=y2;jj++){
        this.delBackAndAjust(ii,jj,adjustMode,avgORmax,bg,cofi);
      }
    }

    this.image =ip.createImage() ;
    this.paintImage();
  }

  public void  delBackgroundSelectRevert(int step,int adjustMode,double cofi){
    // for undo
    this.saveIP(ip,step);

    // change the data to COLOR_RGB
    if(this.colorMode!=this.COLOR_RGB) this.convertToRGB24();

    // count the averageGray
    Rectangle rect=this.getROI();
    double avg=getRectIntegralGray(rect)/(rect.getWidth()*rect.getHeight());
    double avgORmax=avg;
    if(adjustMode==this.ADJUST_BACK_MAX) avgORmax=this.getRectMaxGray(rect);

    // get the background from ROI_2
    Rectangle rect_2=this.getROI_2();
    double bg=(getRectIntegralGray(rect)-getRectIntegralGray(rect_2))/
      (rect.getWidth()*rect.getHeight()-rect_2.getWidth()*rect_2.getHeight());

    // delete the background
    int x1=rect.getLocation().x;
    int x2=x1+(int)rect.getWidth() -1;
    int y1=rect.getLocation().y;
    int y2=y1+(int)rect.getHeight() -1;
    for(int ii=x1;ii<=x2;ii++){
      for(int jj=y1;jj<=y2;jj++){
        this.delBackAndAjust(ii,jj,adjustMode,avgORmax,bg,cofi);
      }
    }

    this.image =ip.createImage() ;
    this.paintImage();
  }

  public void delBackgroundROIMinValue(int step,int adjustMode,double cofi){
    // for undo
    this.saveIP(ip,step);

    // change the data to COLOR_RGB
    if(this.colorMode!=this.COLOR_RGB) this.convertToRGB24();

    // count the averageGray
    Rectangle rect=this.getROI();
    double avg=getRectIntegralGray(rect)/(rect.getWidth()*rect.getHeight());
    double avgORmax=avg;
    if(adjustMode==this.ADJUST_BACK_MAX) avgORmax=this.getRectMaxGray(rect);

    // get the bg of min gray
    double bg=this.getRectMinGray(rect);

    // delete the background
    int x1=rect.getLocation().x;
    int x2=x1+(int)rect.getWidth() -1;
    int y1=rect.getLocation().y;
    int y2=y1+(int)rect.getHeight() -1;
    for(int ii=x1;ii<=x2;ii++){
      for(int jj=y1;jj<=y2;jj++){
        this.delBackAndAjust(ii,jj,adjustMode,avgORmax,bg,1.0+cofi);
      }
    }

    this.image =ip.createImage() ;
    this.paintImage();
  }

  public void delBackgroundLine(int step,int adjustMode,double cofi){
    this.saveIP(ip,step);

    // change the data to COLOR_RGB
    if(this.colorMode!=this.COLOR_RGB) this.convertToRGB24();

    Color c;
    int r,g,b,i;
    int x1=this.frameMain .getHistgram() .getSpROIPt1().x;
    int y1=this.frameMain .getHistgram() .getSpROIPt1().y;
    int x2=this.frameMain .getHistgram() .getSpROIPt2().x;
    int y2=this.frameMain .getHistgram() .getSpROIPt2().y;
    Point linePt1=this.frameMain .getHistgram() .getLinePt1() ;
    Point linePt2=this.frameMain .getHistgram() .getLinePt2() ;
    double bg;
    double k;

    // count the averageGray
    Rectangle rect=new Rectangle(x1,y1,x2-x1+1,y2-y1+1);
    double avg=getRectIntegralGray(rect)/(rect.getWidth()*rect.getHeight());
    double avgORmax=avg;
    if(adjustMode==this.ADJUST_BACK_MAX) avgORmax=this.getRectMaxGray(rect);

    for(int ii=x1;ii<=x2;ii++){
      if(linePt1.x==linePt2.x){
        return;
      }
      else{
        k=((double)linePt2.y-(double)linePt1.y)/
          ((double)linePt2.x-(double)linePt1.x);
        bg=k*(ii-linePt1.x)+linePt1.y;
      }
      for(int jj=y1;jj<=y2;jj++){
        this.delBackAndAjust(ii,jj,adjustMode,avgORmax,bg,cofi);
      }
    }
    this.image =ip.createImage() ;
    this.paintImage();
  }

  public Image createImageFrom2Points(Point pt1,Point pt2,boolean directionX){
    Image img=null;
    MemoryImageSource mis;
    int width,height;
    if(directionX==true){
      width=pt2.x-pt1.x+1;
      height=pt2.y-pt1.y+1;
    }
    else{
      width=pt2.y-pt1.y+1;
      height=pt2.x-pt1.x+1;
    }
    int pix[]=new int[width*height];
    int n=0;
    for(int ii=1;ii<=width;ii++){
      for(int jj=1;jj<=height;jj++){
        if(directionX==true) pix[n]=ip.getPixel(ii-1,jj-1);
        else pix[n]=ip.getPixel(jj-1,ii-1) ;
         n++;
      }
    }
    mis = new MemoryImageSource(width, height, pix, 0, width);
    mis.setAnimated(true);
    img=this.createImage(mis);
    //System.out.println(img);
    return img;
  }

  public LayerCell getLayerCell(){
    return  this.layerCell ;
  }

  private void convertIPTo8Gray(ImageProcessor newip){
    int w=this.getWidth();
    int h=this.getHeight();
    int v;
    Color c;
    for(int ii=0;ii<w;ii++){
      for(int jj=0;jj<h;jj++){
        v=(int)(getPixel8Gray(ii,jj)&0xFF);
        c=new Color(v,v,v);
        newip.putPixel(ii,jj,c.getRGB());
      }
    }
  }

  private void convertIPTo24RGB(ImageProcessor newip){
    int w=this.getWidth();
    int h=this.getHeight();
    int v;
    for(int ii=0;ii<w;ii++){
      for(int jj=0;jj<h;jj++){
        v=getPixelColorInt(ii,jj);
        newip.putPixel(ii,jj,v);
      }
    }
  }

  public void convertToByte(int step){
    OpenFileInformation infor=ImageSaver.createIPFromTmp(format,frameMain,
        false,null,width,height,this.path,this.name);
    this.convertIPTo8Gray(infor.ip);

    //
    if(this.format==OpenFile.GIF && this.colorMode==COLOR_INDEX) this.format=OpenFile.JPEG;
    this.fileInfo=infor.fileInfo;
    this.colorMode=COLOR_RGB;
    this.bitDepth=24;
    this.ip=infor.ip.duplicate();
    this.image =ip.createImage();
    this.paintImage();
  }

  public void convertToRGB(int step){
    this.saveIP(ip,step);
    this.convertToRGB24();
    this.paintImage() ;
  }

  private Method getClassMethod(Class className,String methodName,Object []para){
    Method md[]=className.getMethods();
    Class cls[],cls1,cls2;
    String c1,c2;
    boolean ok;
    for(int ii=1;ii<=md.length;ii++){
      if(md[ii-1].getName().equals(methodName)) {
        cls=md[ii-1].getParameterTypes();
        if(para==null && cls==null) return md[ii-1];
        else{
          ok=true;
          for(int jj=1;jj<=cls.length;jj++){
            cls1=para[jj-1].getClass();
            cls2=changeClass(cls[jj-1]);
            c1=cls1.getName();
            c2=cls2.getName();
            c1=this.changeString(c1);
            c2=this.changeString(c2);
            /*
            if(cls1.equals(cls2)==false) {
              ok=false;
              break;
            }
            */
            if(c1.equals(c2)==false) {
              ok=false;
              break;
            }
          }
          if(ok==true) return md[ii-1];
        }
      }
    }
    JOptionPane.showMessageDialog(null,"NotFoundMethod"+methodName+" in Class"+className);
    return null;
  }

  private String changeString(String str){
    if(str.equals("ij.process.ColorProcessor")==true ||
       str.equals("ij.process.ByteProcessor")==true ||
       str.equals("ij.process.FloatProcessor")==true ||
       str.equals("ij.process.ShortProcessor")==true )
      str="ij.process.ImageProcessor";
    return str;
  }

  private Class changeClass(Class cl){
    /*
    Class classColorProcessor;
    ColorProcessor cp=new ColorProcessor(1,1);
    classColorProcessor=cp.getClass();

    Class classByteProcessor;
    ByteProcessor bp=new ByteProcessor(1,1);
    classByteProcessor=bp.getClass();
    */

    if (cl == int.class)           cl = Integer.class;
    else if (cl == boolean.class)  cl = Boolean.class;
    else if (cl == double.class)   cl = Double.class;
    else if (cl == float.class)    cl = Float.class;
    else if (cl == byte.class)     cl = Byte.class;
    else if (cl == char.class)     cl = Character.class;
    else if (cl == short.class)    cl = Short.class;
    else if (cl == long.class)     cl = Long.class;
    return cl;
  }

  private Object call(Class className,Object classObject,String methodName,Object []para){
    Object obj=null;
    try{
      Method method=this.getClassMethod(className,methodName,para);
      obj=method.invoke(classObject,para);
    }
    catch(InvocationTargetException e2) {
      e2.printStackTrace();
    }
    catch(IllegalAccessException e2) {
      e2.printStackTrace();
    }
    return obj;
  }

  public Cursor createWaitCursor(){
    String path2=this.frameMain.getSystemDir()+"\\icon\\wait.gif";
    return this.myCreateCursor(path2,1,new Point(5,5));
  }

  public void mySetCursor(boolean isMyCursor,int index){
    Cursor cursor=new Cursor(Cursor.DEFAULT_CURSOR);
    if(isMyCursor==false){
      cursor=new Cursor(index);
      this.setCursor(cursor);
      return;
    }
    String path2=this.frameMain.getSystemDir()+"\\icon\\";

    if(index==this.CURMOVE){
      path2+="move.gif";
      cursor=this.myCreateCursor(path2,1,new Point(11,11));
    }
    else if(index==this.CURSELECT){
      path2+="selectobj.gif";
      cursor=this.myCreateCursor(path2,1,new Point(17,10));
    }
    else if(index==this.CURRECT){
      path2+="rectangle.gif";
      cursor=this.myCreateCursor(path2,1,new Point(5,9));
    }
    else if(index==this.CURADD){
      path2+="add.gif";
      cursor=this.myCreateCursor(path2,1,new Point(4,9));
    }
    else if(index==this.CURBIG){
      path2+="big.gif";
      cursor=this.myCreateCursor(path2,1,new Point(6,7));
    }
    else if(index==this.CURSMALL){
      path2+="small.gif";
      cursor=this.myCreateCursor(path2,1,new Point(9,9));
    }
    else if(index==this.CURMOVELANE){
      path2+="movelane.gif";
      cursor=this.myCreateCursor(path2,1,new Point(15,11));
    }
    else if(index==this.CURMOVELANE2){
      path2+="movelane2.gif";
      cursor=this.myCreateCursor(path2,1,new Point(11,15));
    }
    else if(index==this.CURDELETE){
      path2+="delete.gif";
      cursor=this.myCreateCursor(path2,1,new Point(4,9));
    }
    else if(index==this.CURCIRCLE){
      path2+="circle.gif";
      cursor=this.myCreateCursor(path2,1,new Point(5,9));
    }
    else if(index==this.CURELLIPSE){
      path2+="ellipse.gif";
      cursor=this.myCreateCursor(path2,1,new Point(5,9));
    }
    else if(index==this.CURPOLYGON){
      path2+="polygon.gif";
      cursor=this.myCreateCursor(path2,1,new Point(5,9));
    }
    else if(index==this.CURDETAIL){
      path2+="detail.gif";
      cursor=this.myCreateCursor(path2,1,new Point(4,6));
    }
    this.setCursor(cursor);
  }

  /**
   *
   * @param imageFile 光标图象名称
   * @param scale 放大倍数
   * @param point 光标中心点
   * @return
   */
  private Cursor myCreateCursor(String imageFile,int scale,Point point) {
    String gifFile = null;
    ImageIcon icon;
    icon = new ImageIcon(imageFile);
    int width = icon.getIconWidth();
    int height = icon.getIconHeight();
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension d = toolkit.getBestCursorSize(width, height);
    Image bimage = new BufferedImage(d.width*scale, d.height*scale, BufferedImage.TYPE_INT_ARGB);
    bimage.getGraphics().drawImage(icon.getImage(), 0, 0,d.width*scale,d.height*scale, icon.getImageObserver());
    return toolkit.createCustomCursor(bimage, point, "");
  }

  /**
   * 已经+0.5调整
   * @param x
   * @param y
   * @return
   * 灰度模式: 灰度=pvalue[0]
   * 其他模式: r=pvalue[0]
   *          g=pvalue[1]
   *          b=pvalue[2]
   *          灰度=pvalue[3]
   *              8/16 bits for gray
   *              or Gray=(int)(r*0.299 + g*0.587 + b*0.114 + 0.5) for color rgb or index 8;
   */
  public int[] getPixel(int x, int y){
    int[] pvalue = new int[4];
    pvalue[0]=pvalue[1]=pvalue[2]=pvalue[3]=0;
    if(colorMode==this.COLOR_GRAY){
      if(this.bitDepth==8)  pvalue[0]=ip.getPixel(x, y)&0xFF;
      else if(this.bitDepth==16) pvalue[0]=ip.getPixel(x, y)&0xFFFF;
      return pvalue;
    }
    int[] pixels32 = new int[1];
    PixelGrabber pg = new PixelGrabber(image, x, y, 1, 1, pixels32, 0, width);
    try {pg.grabPixels();}
    catch (InterruptedException e){return pvalue;};
    int c = pixels32[0];
    int r = (c&0xff0000)>>16;
    int g = (c&0xff00)>>8;
    int b = c&0xff;
    pvalue[0] = r;
    pvalue[1] = g;
    pvalue[2] = b;
    pvalue[3] = (int)(r*0.299 + g*0.587 + b*0.114 + 0.5);
    return pvalue;
  }

  public int getPixelColorInt(int x, int y){
    int cint=0;
    int v=0;
    Color c;
    if(colorMode==this.COLOR_GRAY){
      if(this.bitDepth==8)  v=ip.getPixel(x, y)&0xFF;
      else if(this.bitDepth==16) v=((int)(ip.getPixel(x, y)/256.0f))&0xFF;
      c=new Color(v,v,v);
      cint=c.getRGB();
      return cint;
    }
    int[] pixels32 = new int[1];
    PixelGrabber pg = new PixelGrabber(image, x, y, 1, 1, pixels32, 0, width);
    try {pg.grabPixels();}
    catch (InterruptedException e){return cint;};
    int c2 = pixels32[0];
    int r = (c2&0xff0000)>>16;
    int g = (c2&0xff00)>>8;
    int b = c2&0xff;
    c=new Color(r,g,b);
    cint=c.getRGB();
    return cint;
  }


  public byte getPixel8Gray(int x, int y){
    byte bt=0;
    int it;
    if(colorMode==this.COLOR_GRAY){
      if(this.bitDepth==8)  bt=(byte)(ip.getPixel(x, y)&0xFF);
      else if(this.bitDepth==16) bt=(byte)(((int)(ip.getPixel(x, y)/256.0f))&0xFF);
      return bt;
    }
    int[] pixels32 = new int[1];
    PixelGrabber pg = new PixelGrabber(image, x, y, 1, 1, pixels32, 0, width);
    try {pg.grabPixels();}
    catch (InterruptedException e){return bt;};
    int c = pixels32[0];
    int r = (c&0xff0000)>>16;
    int g = (c&0xff00)>>8;
    int b = c&0xff;
    it = (int)(r*0.299 + g*0.587 + b*0.114 + 0.5);
    if(it<0) it=0;    if(it>255) it=255;
    bt=(byte)(it&0xFF);
    return bt;
  }

  public int getPixel8GrayBgAdjust(int x,int y){
    int gray=this.getPixel8Gray(x,y)&0xFF;
    if(bgBlack==false) gray=255-gray;
    return gray;
  }

  public byte getPixel8GrayBgAdjust_Byte(int x,int y){
    int gray=this.getPixel8Gray(x,y)&0xFF;
    if(bgBlack==false) gray=255-gray;
    return (byte)(gray&0xFF);
  }

  // end of the class
}
