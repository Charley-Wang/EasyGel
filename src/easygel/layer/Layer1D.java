package easygel.layer;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

//Liusheng 2003-3-22
import java.lang.Object.*;
import easygel.image.*;
import easygel.*;
import easygel.DialogImage;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import ij.process.*;
import easygel.uiti.*;

import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.IOException;
import java.io.ObjectInput;

public class Layer1D extends Layer  implements Externalizable {
  ///*
  private boolean isMousePressed;
  //type define image type, for example gray,rgb
  //draglaneN is num of lane, selectlanen is pos of lane in current
  private int height,width,lanelr,type,draglaneN,selectlaneN;
  private float lanemax,lanemin;
  private int lanetotalN;
  private ImageProcessor ip;
  //private DialogImage dialogimage;
  public Vector point;
  private float grayImage[][];
  private int pixel[][];
  private int orgpix[][];
  public boolean modify,draglane;
  public int totallane;   //不要了
  public Vector  virtlane;

  //Liusheng add later
  private int lanepos;
  private int bottom;
  boolean reviseforwd;
  boolean ishorizontal;
  Info1D infodraglane;
  Point clickedpt;
  //*/

  public Layer1D() {
    super();
    this.isMousePressed =false;
    draglane=false;
    modify=false;
    infodraglane=null;
    draglaneN=0;
    lanetotalN=0;
    width=0;
    lanemax=0;
    lanemin=0;
    lanepos=0;
    bottom=0;
    ishorizontal=true;
    point=new Vector();
    virtlane=new Vector();
    grayImage=new float[height][width];
  }

  public  Layer1D(DialogImage di) {
     super(di);
     this.isMousePressed =false;
     dialogImage=di;
     ip=di.getIP();
     draglane=false;
     infodraglane=null;
     modify=false;
     width=dialogImage.getImageWidth();
     height=dialogImage.getImageHeight();
     draglaneN=0;
     lanetotalN=0;
     lanemax=0;
     lanemin=0;
     bottom=0;
     ishorizontal=true;
     lanepos=0;
     point=new Vector();
     virtlane=new Vector();
     grayImage=new float[height][width];
  }

  public  void initForSerial(DialogImage di) {
    // delete by wxs 20031003
     //ID=-1000000;
     this.isMousePressed =false;
     dialogImage=di;
     ip=di.getIP();
     draglane=false;
     infodraglane=null;
     modify=false;
     //width=dialogImage.getImageWidth();
     //height=dialogImage.getImageHeight();
     draglaneN=0;
     //lanetotalN=0;
     //lanemax=0;
     //lanemin=0;
     bottom=0;
     ishorizontal=true;
     lanepos=0;
     point=new Vector();
     virtlane=new Vector();
     grayImage=new float[height][width];
  }

  public void addElement(Point point1,Point point2,String character,
                         int laneN,int lineN,
                         boolean modifyControl,
                         String layerSign,int index) {
    //ID++;
    int laneNu=index;
    Info1D info1D=new Info1D(point1,point2,character,laneN,lineN,ID);
    if(modify==false) current.add(info1D);
    else              current.insertElementAt(info1D,laneNu);
    ID++;
  }

  /**插入泳道或条带
   *point1,point2为泳道或条带的二哥端点
   * character表示对象的意义为泳道还是条带
   * laneN为泳道的序号
   * lineN为条带的序号
   * mdifyControl为是否允许修改
   * layerSign层的意义
   * index 为插入的位置
  **/
  public void insertElement(Point point1,Point point2,String character,
                            int laneN,int lineN,boolean modifyControl,
                            String layerSign,int index){
    //ID++;
    Info1D info1D=new Info1D(point1,point2,character,laneN,lineN,ID);
    super.current.insertElementAt(info1D,index);
    ID++;
  }
  /**移动泳道
  //selectlaneN
  **/
 public void mouseReleased(MouseEvent e){
  this.draglane=false;
 }

 public void mouseDragged(Point p){
   Info1D infoleft;
   Info1D inforight;
   Point p1,p2;
   int total=this.getlanenum();
   if(draglane==true){
     if( draglaneN==0&&(infodraglane.character.equals("up")||
                         infodraglane.character.equals("down"))){
       if(p.y<4||p.y>this.height-5)
       return;


       if(infodraglane.character.equals("up")){
       Info1D  infdown=(Info1D)current.elementAt(lanepos+1);
       if(p.y >infdown.point1.y-2)  return;
       }
       if(infodraglane.character.equals("down")){
       Info1D  infdown=(Info1D)current.elementAt(lanepos-1);
       if(p.y <infdown.point1.y+2)  return;
       }
       infodraglane.point1.y=p.y;
       infodraglane.point2.y=p.y;
   /*    current.removeElementAt(lanepos);
       current.insertElementAt(infodraglane,lanepos);*/
       for(int ii=0;ii<this.getlanenum();ii++){
        Info1D info;
        info=null;
        info=(Info1D)current.elementAt(ii);
       if(infodraglane.character.equals("up"))   {
         info.point1.y=p.y;
       }
       else if(infodraglane.character.equals("down"))
       info.point2.y=p.y;
       current.removeElementAt(ii);
       current.insertElementAt(info,ii);
       }
     }
     else{
     if(selectlaneN>0&&selectlaneN<total-1){
       infoleft=(Info1D)current.elementAt(selectlaneN-1);
       inforight=(Info1D)current.elementAt(selectlaneN+1);
       p1=infoleft.point1;
       p2=inforight.point1;
       if((p.x<p2.x-3)&&((p1.x)<p.x-3)){
         infodraglane.point1.x=p.x;
         infodraglane.point2.x=p.x;
         current.removeElementAt(selectlaneN);
         current.insertElementAt(infodraglane,selectlaneN);
       }
     else{
      if(p.x<p1.x+3) {
        infodraglane.point1.x=p1.x+3;
        infodraglane.point2.x=p1.x+3;
        current.removeElementAt(selectlaneN);
        current.insertElementAt(infodraglane,selectlaneN);
      }
     else{
       infodraglane.point1.x=p2.x-3;
       infodraglane.point2.x=p2.x-3;
       dialogImage.getLayer1D().current.removeElementAt(selectlaneN);
       dialogImage.getLayer1D().current.insertElementAt(infodraglane,selectlaneN);
     }
    }
   }
   else if(selectlaneN==0){
     inforight=(Info1D)current.elementAt(selectlaneN+1);
     p1=new Point(1,5);
     p2=inforight.point2;
    if((p.x<p2.x-2)&&((p1.x+2)<p.x)){
      infodraglane.point1.x=p.x;
      infodraglane.point2.x=p.x;
      current.removeElementAt(selectlaneN);
      current.insertElementAt(infodraglane,selectlaneN);
    }
   }
   else if(selectlaneN==total-1){
     infoleft=(Info1D)current.elementAt(selectlaneN-1);
     p1=infoleft.point1;
     p2=new Point(width-1,5);

    if((p.x<p2.x-2)&&((p1.x+2)<p.x)){
      infodraglane.point1.x=p.x;
      infodraglane.point2.x=p.x;
      current.removeElementAt(selectlaneN);
      current.insertElementAt(infodraglane,selectlaneN);
    }
   }
   /*********/
     }
   if(current.size()>getlanenum() +2) getline(8,60,20,0);
   }
   this.rearragelane();
   //this.draglane=false;
 }

 //added by wxs : Edition2
 public int getLanes(){
   return this.getlanenum() /2;
 }

  //added by wxs : Edition2
  public int getLines(int laneNo){
    int lines=0;
    Info1D info1D;
    for(int ii=1;ii<=this.current .size() ;ii++){
      info1D=(Info1D)current.elementAt(ii-1);
      if(info1D.laneN ==laneNo && info1D.character .equals("line" ))
        lines++;
    }
    return lines;
  }

  // added by wxs
  public int getTotalLines(){
    int totalLiens=0;
    int lanes=this.getLanes();
    for(int ii=1;ii<=lanes;ii++) totalLiens+=this.getLines(ii);
    return totalLiens;
  }

  //added by wxs : Edition2
public Point getLineCenterPoint(int laneNo,int lineNo){
  Point pt=null;
  Info1D info1D;
  for(int ii=1;ii<=this.current .size() ;ii++){
    info1D=(Info1D)current.elementAt(ii-1);
    if(info1D.laneN ==laneNo && info1D.lineN ==lineNo)
      return new Point((info1D.point1 .x+info1D.point2 .x)/2,
                       (info1D.point1 .y+info1D.point2 .y)/2);
  }
  return pt;
}

//added by wxs : Edition2
public double []getLinesLocation(int laneNum){
  double lines[];
  int lineNo=0;
  lines=null;

  //计算线数
  Info1D info1D;
  for(int ii=1;ii<=current.size() ;ii++){
    info1D=(Info1D)current.elementAt(ii-1);
    if(info1D.character .equals("line")
        && info1D.laneN ==laneNum)  lineNo++;
  }
  lines=new double[lineNo];

  //填线
  lineNo=0;
  for(int ii=1;ii<=current.size() ;ii++){
    info1D=(Info1D)current.elementAt(ii-1);
    if(info1D.character.equals("line")
       && info1D.laneN ==laneNum) {
      lines[lineNo]=info1D.point1.y;
      lineNo++;
    }
  }

  return lines;
}


  /**判断点是不是在线上 定义infoleft， inforight是限制拖动的泳道线的，
   * 分别在选中线的左侧和右侧,拖动时会遇到四种情况，拖第一个泳道线，拖作后的泳道线，拖动上下泳道还没有提供
   */
  public void mouseMoved(Point p){
    Cursor cursor;
    cursor=new Cursor(Cursor.DEFAULT_CURSOR);
    if(getLane(p)==null){
      this.dialogImage.setCursor(cursor);
    }
    else if(ishorizontal==true){
      this.dialogImage.mySetCursor(true,DialogImage.CURMOVELANE);
    }
    else if(ishorizontal==false){
      this.dialogImage.mySetCursor(true,DialogImage.CURMOVELANE2);
    }
  }

  public void mousepressed(Point p){
    if(getLane(p)!=null){
       draglane=true;
       infodraglane=getLane(p);
       draglaneN=infodraglane.laneN;
       if(infodraglane.character.equals("left"))
         selectlaneN=draglaneN*2-2;
       else if(infodraglane.character.equals("right"))
         selectlaneN=draglaneN*2-1;
    }
  }

/**取得鼠标点击的条带
 * p为鼠标点击的点
 *
 **/
 private  Info1D getLane(Point p){
   for(int iii=1;iii<=current.size() ;iii++){
     Info1D info1D=((Info1D)current.elementAt(iii-1));
       Point p1,p2;
       p1=info1D.point1 ;
       p2=info1D.point2 ;
     if(info1D.character.equals("left") || info1D.character.equals("right")){
              //要改为k,k=@@ ,k=....
       if(p.x==p1.x&&p.y>p1.y+3&&p.y<p2.y-3){
          lanepos=iii-1;
          ishorizontal=true;
         return info1D ;

        }
       }
       else {
   if(info1D.character.equals("up")||info1D.character.equals("down")){
       if(p.y==p1.y||p.y==p2.y){
          lanepos=iii-1;
          ishorizontal=false;
         return info1D;
       }

   }
       }
   }
   return null;
 }
 /**
  *得到泳道的总数，除了上下泳道不包括在内,返回值除以2就是泳道的总数
  *
  **/
 public int getlanenum(){
   int laneN=0;
   for(int ii=0;ii<current.size();ii++)
   { Info1D inf;
     inf=(Info1D)current.elementAt(ii);
     if(inf.character.equals("left")||inf.character.equals("right"))
       laneN++;
    }
    return laneN;
 }

 /**
  * 函数是用来取得图像的灰度值
  * 并且把取得灰度反色，使白色最低，黑色最高
  **/
 public boolean getGray(){
   boolean bg=this.dialogImage .getBackgroundBlack();
   for(int ii=0;ii<height;ii++){
     for(int jj=0;jj<width;jj++){
       if(bg==true)   grayImage[ii][jj]=ip.getPixelValue(jj,ii);
       else           grayImage[ii][jj]=255-ip.getPixelValue(jj,ii);
     }
   }
   return true;
 }

/**
 *插入调带
 * pt 为插入条带的位置
 **/
 public void insline(Point pt){
   Info1D infL,infR;
   int x1,x2,laneN;
   x1=x2=laneN=0;
   int lanenum;
   lanenum=this.getlanenum();
   for(int ii=0;ii<lanenum;ii+=2){
     if(ii+1>lanenum-1)   break;
     infL=(Info1D)current.elementAt(ii);
     infR=(Info1D)current.elementAt(ii+1);
      if((pt.x<infR.point1.x-1)&&(pt.x>infL.point1.x+1)&&(pt.y>2)&&(pt.y<height))
     {  x1=infL.point1.x;
        x2=infR.point2.x;
        laneN=ii/2+1;
        break;
      }
     }
     if(laneN==0)
       return;
     this.insertElement(new Point(x1,pt.y),new Point(x2,pt.y),"line",laneN,0,false,"1D",current.size());
     this.rearrageline(laneN,true);
 }
 /**
  *手动识别泳道
  * pt1,pt2为所选泳道区的左上，和右下二点
  * lanenum为泳道总数
  * lanegap为泳道的间距
  **/
   public void manualrec(Point pt1,Point pt2,int lanenum,int lanegap){
    current.removeAllElements();
    int lanewid;
    if(lanegap*lanenum>(pt2.x-pt1.x-lanenum*4))
     return;
    lanewid=(pt2.x-pt1.x+lanegap)/lanenum-lanegap;

    for(int ii=0;ii<lanenum;ii++){
     addElement(new Point(pt1.x+(lanegap+lanewid)*ii,pt1.y),new Point(pt1.x+(lanegap+lanewid)*ii,pt2.y),"left",ii*2+1,0,false,"1D",0);
     addElement(new Point(pt1.x+(lanegap+lanewid)*ii+lanewid,pt1.y),new Point(pt1.x+(lanegap+lanewid)*ii+lanewid,pt2.y),"right",ii*2+2,0,false,"1D",0);
    }

    addElement(new Point(pt1.x,pt1.y),new Point(pt2.x,pt1.y),"top",0,0,false,"1D",0);
    addElement(new Point(pt1.x,pt2.y),new Point(pt2.x,pt2.y),"down",0,0,false,"1D",0);
  }

  /**
   *插入泳道
   * lanenum为要插入泳道的序号，
   * 系在该序号的泳道里插入泳道
   **/
   public void inslane(int lanenum){
   Info1D infL,infR;
   int gap;
   infL=(Info1D)current.elementAt((lanenum-1)*2);
   infR=(Info1D)current.elementAt((lanenum-1)*2+1);
   Point pt1,pt2,pt3,pt4;
   Point pt5,pt6,pt7,pt8;
   int x1,x2,y1,y2;
   pt1=infL.point1;
   pt2=infL.point2;
   pt3=infR.point1;
   pt4=infR.point2;
   gap=pt3.x-pt1.x;
   x1=pt1.x+gap/2-1;
   x2=pt3.x-gap/2+2;
   y1=pt1.y;
   y2=pt2.y;
   int laneN=this.getlanenum();
   this.insertElement(new Point(x1,y1),new Point(x1,y2),"right",lanenum,0,false,"1D",(lanenum-1)*2+1);
   this.insertElement(new Point(x2,y1),new Point(x2,y2),"left",lanenum+1,0,false,"1D",(lanenum-1)*2+2);
   this.rearragelane();


 }

 /**
  *删除泳道
  * lanenum为删除的泳道的序号
  **/
 public  void dellane(int lanenum){
 current.removeElementAt((lanenum-1)*2);
 current.removeElementAt((lanenum-1)*2);
 int lines;
 lines=current.size();
 for(int ii=0;ii<lines;ii++){
  Info1D inf;
  inf=(Info1D)current.elementAt(ii);
 // System.out.println(inf.character);
 // System.out.println(inf.laneN);
 /// System.out.println(inf.lineN);
 // System.out.println(inf.point1);
  if(inf.character.equals("line")&&inf.laneN==lanenum)
  {
  //  current.removeElement(inf);
 current.removeElementAt(ii);
 lines--;
 ii--;
  }
    }
 this.rearragelane();
 }

 // modeified by wxs 2003/08/01
 /**
  * 取得各个条带的范围，条带的中点坐标，扩展位置
  * @return 条带的范围 pt1(左上),pt2(右下)
  * 中点坐标 pt3
  * 扩展位置 pt4.x(左位置) , pt4.y(右位置)
  * 测试用例：图范围/加减泳道/加减条带/条带个数边界测试0-3/背景/泳道个数0-3
  */
 public Vector getRectForCellV4(){
   Vector v=new Vector();
   int lanes;
   lanes=this.getlanenum()/2;
   int topY,bottomY;
   int lines[]=new int[lanes];
   int leftX[]=new int[lanes];
   int rightX[]=new int[lanes];
   Info1D id;
   for(int ii=1;ii<=lanes;ii++){
     id=(Info1D)this.current.elementAt(2*ii-2);
     leftX[ii-1]=id.point1.x;
     id=(Info1D)this.current.elementAt(2*ii-1);
     rightX[ii-1]=id.point1.x;
     lines[ii-1]=0;
   }
   id=(Info1D)this.current.elementAt(2*lanes);
   topY=id.point1.y;
   id=(Info1D)this.current.elementAt(2*lanes+1);
   bottomY=id.point1.y;
   for(int ii=2*lanes+2;ii<current.size();ii++){
     id=(Info1D)this.current.elementAt(ii);
     lines[id.laneN-1]++;
   }

   /*
   for(int ii=1;ii<=this.current.size();ii++){
     id=(Info1D)this.current.elementAt(ii-1);
     System.out.println(id.toString());
   }
   for(int ii=1;ii<=lanes;ii++){
     System.out.println("No."+ii+"="+lines[ii-1]);
   }
   */
   //if(true) return v;

   //
   for(int ii=1;ii<=lanes;ii++){
     Point pt1=new Point(0,0);
     Point pt2=new Point(0,0);
     Point pt3=new Point(0,0);
     Point pt4=new Point(0,0);

     if(ii==1) pt1.x=leftX[ii-1];
     else pt1.x=rightX[ii-2];
     pt4.x=leftX[ii-1]-pt1.x;
     if(ii==lanes) pt2.x=rightX[ii-1];
     else pt2.x=leftX[ii];
     pt4.y=rightX[ii-1]-pt1.x;

     // 此为限定范围为泳道的范围内
     // pt1.x=leftX[ii-1];
     // pt2.x=rightX[ii-1];

     for(int jj=1;jj<=lines[ii-1];jj++){
       Point pt1S=new Point(0,0);
       Point pt2S=new Point(0,0);
       Point pt3S=new Point(0,0);
       Point pt4S=new Point(0,0);

       if(jj==1)  pt1.y=topY;
       else       pt1.y=getLinePointY(ii,jj-1);
       if(jj==lines[ii-1])  pt2.y=bottomY;
       else                 pt2.y=getLinePointY(ii,jj+1);
       id=getLine(ii,jj);
       pt3=new Point((id.point1.x+id.point2.x)/2,
                     (id.point1.y+id.point2.y)/2);

       pt1S.x=pt1.x;
       pt2S.x=pt2.x;
       pt3S.x=pt3.x;
       pt4S.x=pt4.x;

       pt1S.y=pt1.y;
       pt2S.y=pt2.y;
       pt3S.y=pt3.y;
       pt4S.y=pt4.y;

       v.addElement(pt1S);
       v.addElement(pt2S);
       v.addElement(pt3S);
       v.addElement(pt4S);

       // added by wxs 20030923
       Integer laneNo=new Integer(ii);
       Integer lineNo=new Integer(jj);
       v.addElement(laneNo);
       v.addElement(lineNo);

       System.out.println("Lane No:"+ii+",Band No:"+jj+","+
                          "Upper Left:["+pt1.x+","+pt1.y+"],"+
                          "Bottom Right:["+pt2.x+","+pt2.y+"],"+
                          "Middle:["+pt3.x+","+pt3.y+"],"+
                          "Left:"+pt4.x+",Right:"+pt4.y);

     }
   }
   return v;
 }

 private int getLinePointY(int lane,int line){
   int y=0;
   Info1D id;
   for(int ii=1;ii<=this.current.size();ii++){
     id=(Info1D)this.current.elementAt(ii-1);
     //System.out.println("ii="+ii+",char="+id.character+
     //                   ",lane="+id.laneN+",line="+id.lineN);
     if(id.character.equals("line")==true &&
        id.laneN ==lane && id.lineN ==line){
       y=id.point1.y;
       break;
     }
   }
   return y;
 }

 private Info1D getLine(int lane,int line){
   Info1D id=null;
   for(int ii=1;ii<=this.current.size();ii++){
     id=(Info1D)this.current.elementAt(ii-1);
     if(id.character.equals("line")==true &&
        id.laneN ==lane && id.lineN ==line){
       break;
     }
     else{
       id=null;
     }
   }
   return id;
 }

 /**xselect 系被删除的条带的序号
 *删除条带
  **/
  public void delline(Point pt){
   Info1D inf;
  int xselect,laneN;
  laneN=xselect=0;
  inf=null;
  int lines=current.size();
  for(int ii=0;ii<lines;ii++){
    inf=(Info1D)current.elementAt(ii);
     if((pt.x>inf.point1.x)&&(pt.x<inf.point2.x)&&
        (pt.y>inf.point1.y-2)&&(pt.y<inf.point1.y+2)&&
        inf.character.equals("line"))
    {
       xselect=ii;
       laneN=inf.laneN;
       break;
    }
    }

  if(laneN==0) return;
  current.removeElement(inf);
  this.rearrageline(laneN,false);
 }

 public Rectangle getLaneRectangle(Point pt){
   Info1D infL,infR;
   infL=null;
   infR=null;
   boolean exist=false;
   for(int ii=0;ii<current.size();ii+=2)
   {
    infL=(Info1D)current.elementAt(ii);
    infR=(Info1D)current.elementAt(ii+1);
    if(infL.character.equals("left")&&pt.x<infR.point1.x&&pt.x>infL.point1.x){
      exist=true;
      break;
    }
   }
   if(exist==true){
    return new Rectangle(infL.point1.x,infL.point1.y,
                         infR.point1.x-infL.point1.x+1,infR.point2.y-infR.point1.y+1);
   }
   else{
     return null;
   }

 }

 private int reclane2(int sensitivity){
   int []hist;
   int []bg;
   Point pt11,pt22;
   Rectangle rect;
   rect=this.dialogImage.getROI();
   pt11=rect.getLocation();
   pt22=new Point(pt11.x+(int)rect.getWidth()-1,pt11.y+(int)rect.getHeight()-1);
   hist=DialogDrawHist.histgram(this.ip,pt11,pt22,true,this.dialogImage);
   bg=WxsUiti.rollingBall(hist,120);
   int []res=new int[bg.length];
   for(int ii=1;ii<=hist.length;ii++){
     res[ii-1]=hist[ii-1]-bg[ii-1];
   }

   int []hh=new int[256];
   for(int ii=0;ii<=255;ii++) hh[ii]=0;
   for(int ii=1;ii<=hist.length ;ii++){
     int v=hist[ii-1];
     if(v>=0 && v<=255) hh[v]++;
   }
   int maxv=0;
   for(int ii=0;ii<=255;ii++){
     if(hh[ii]>maxv) maxv=ii;
   }

   //liusheng
   current.removeAllElements();
   float lanethd,k;
   int nLane,nLine,temp,laneWidth,nlight,nhighlight;
   int sensi=sensitivity;
   boolean justoverthd,rearrange;
   rearrange=false;
   nLane=nLine=laneWidth=laneWidth=nlight=nhighlight=0;
   getGray();
   lanemin=70000;
   lanemax=-70000;
   for(int ii=0;ii<hist.length;ii++){
     if(lanemin>hist[ii]) lanemin=hist[ii];
     if(lanemax<hist[ii]) lanemax=hist[ii];
   }
   //lanethd=(float)(lanemin+((lanemax-lanemin)*(100-sensi))/100);
   lanethd=(float)((float)lanemin+(((float)lanemax-(float)lanemin)*(100.0f-(float)sensi))/100.0f);
   //lanethd=(int)((float)maxv*(float)sensi/100.0f);

   Info1D infoleftx,inforightx;
   //System.out.println("pt11"+pt11.x+"2"+pt22.x);
   for(int jj=pt11.x+2;jj<pt22.x-2;jj++){
     //System.out.println("pt11"+pt11.x+"  2"+pt22.x);
     //System.out.println(res[jj-pt11.x]);
     //System.out.println("pos"+jj);
     if(res[jj-pt11.x]>lanethd){
       for(int kk=0;kk+jj<pt22.x-3&&res[jj+kk-pt11.x]>lanethd;kk++){
         laneWidth++;
       }
       //System.out.println("lanewid"+laneWidth);
       //System.out.println("2");
       if(laneWidth>4){
         nLane++;
         addElement(new Point(jj,pt11.y+1),new Point(jj,pt22.y-1),"left",nLane,0,false,"1D",0);
         addElement(new Point(jj+laneWidth,pt11.y+1),new Point(jj+laneWidth,pt22.y-1),"right",nLane,0,false,"1D",0);
         jj=jj+laneWidth+1;
         laneWidth=0;
         continue;
       }
       else
         laneWidth=0;
     }
   }
   if(this.isAddLane() ==true)  this.addlane(pt11,pt22);
   this.rearragelane();
   return 0;
 }

 private boolean isAddLane(){
   int maxDV=8;
   boolean isAdd=true;
   int lanes=this.getlanenum() /2;
   Info1D id;
   int x1,x2;
   int dd[]=new int[lanes];
   for(int ii=1;ii<=lanes;ii++){
     id=(Info1D)current.elementAt(ii*2-2);
     x1=id.point1.x;
     id=(Info1D)current.elementAt(ii*2-1);
     x2=id.point1.x;
     dd[ii-1]=Math.abs(x2-x1);
   }
   for(int ii=1;ii<=lanes;ii++){
     for(int jj=ii+1;jj<=lanes;jj++){
       if(Math.abs(dd[ii-1]-dd[jj-1])> maxDV){
         return false;
       }
     }
   }
   return isAdd;
 }

  /**
   *  自动识别泳道
   * sensitivity 是灵敏度，其具体意义是识别泳道时，需先识别一个有代表性的条带
   * 其条带需大于该参数限制的域值
   **/
  // 1--liusheng 2--rolling
  public int reclane(int sensitivity,int mode){
    if(mode==2) return reclane2(sensitivity);
    // define lane maximum and minimum gray value, threshold of lane,num of lane
    // nLight to see whether is high line by seeing the num
    // if lane has fault ,rearrange it ,so define rearrange   current.removeAllElements();

    // 删除以前条泳道和条带 by wxs
    current.removeAllElements();
    Point pt11,pt22;
    Rectangle rect;
    rect=(Rectangle)this.dialogImage.getROI();
    pt11=new  Point(0,0);
    pt22=new  Point(0,0);
    pt11=rect.getLocation();
    pt22.x=(int)rect.getWidth()+pt11.x;
    pt22.y=(int)rect.getHeight()+pt11.y;

    float lanethd,k;
    int nLane,nLine,temp,laneWidth,nlight,nhighlight;
    int sensi=sensitivity;
    boolean justoverthd,rearrange;
    rearrange=false;
    nLane=nLine=laneWidth=laneWidth=nlight=nhighlight=0;
    getGray();

    //get max,min,threshold
    lanemax=lanemin=grayImage[height/4][width/4];
    for(int ii=height/4;ii<height*1/2;ii=ii+1){
      for(int jj=width/4;jj<width*1/2;jj=jj+2){
        if(lanemax<grayImage[ii][jj])
          lanemax=grayImage[ii][jj];
        if(lanemin>grayImage[ii][jj])
          lanemin=grayImage[ii][jj];
      }
    }
    //lanethd=(float)(lanemin+(lanemax-lanemin)*0.1+((lanemax-lanemin)*(100-sensi))/100);
    float det=(((float)lanemax-1.0f)-((float)lanemin+1.0f)+1.0f)/200.0f;
    lanethd=((float)lanemin+1.0f)+det*(float)sensi;

    /*
    // modified by wxs 2003/08/03
    AnaImage anaImage=new AnaImage();
    lanethd=anaImage.getOptiumnThreshold(this.ip,
    this.dialogImage.getROI(),AnaImage.threshModeOtsu);
    lanethd=(int)((float)sensi/100.0f);
    */

    //get lane
    Info1D infoleftx,inforightx;
    for(int jj=pt11.x+2;jj<pt22.x-2;jj++){
      for(int ii=height/8;ii<height*7/8;ii++){
        if(grayImage[ii][jj]>lanethd){
          for(int kk=0;kk+jj<pt22.x-2&&grayImage[ii][jj+kk]>lanethd;kk++){
            laneWidth++;
          }
          if(laneWidth>10){
            nLane++;
            //加左、右泳道 by wxs
            addElement(new Point(jj,pt11.y+1),new Point(jj,pt22.y-1),"left",nLane,0,false,"1D",0);
            addElement(new Point(jj+laneWidth,pt11.y+1),new Point(jj+laneWidth,pt22.y-1),"right",nLane,0,false,"1D",0);
            jj=jj+laneWidth+3;
            laneWidth=0;
            break;
          }
          else  laneWidth=0;
        }
      }
    }

    //go on discriminate lane, find out fault of rec
    //if lanewidth is longer than 1.5*avelength or shorter than 0.5*av, it is suspicious.
    //but it doesn't concern about 3* or 1/3 width

    //对泳道的修饰,太大，太小 by wxs
    //太大，加一个，中间为4

    int  lanenum,avelane,lanewidth2;
    Point pt1,pt2;
    Info1D infoleft,inforight;
    boolean getlane;
    getlane=false;
    avelane=0;
    lanenum=current.size();
    int []lanewid=new int[lanenum/2];
    int []lanepos=new int[lanenum];

    for (int ii=0;ii<lanenum;ii+=2){
      infoleft=(Info1D)current.elementAt(ii);
      if(ii+1>lanenum-2)  break;
      inforight=(Info1D)current.elementAt(ii+1);
      pt1=infoleft.point1;
      pt2=inforight.point2;
      lanewid[ii/2]=pt2.x-pt1.x;
      avelane+=lanewid[ii/2];
      lanepos[ii]=pt1.x;
      lanepos[ii+1]=pt2.x;
    }

    if(lanenum!=0)   avelane/=lanenum/2;
    for(int ii=0;ii<lanenum-2;ii+=2){
      if(lanewid[ii/2]>2*avelane){
        modify=true;
        rearrange=true;
        insertElement(new Point(lanepos[ii]+lanewid[ii/2]*1/2-2,pt11.y+1),
                      new Point(lanepos[ii]+lanewid[ii/2]*1/2-2,pt22.y-1),"right",ii/2+1,0,false,"1D",ii+1);
        insertElement(new Point(lanepos[ii]+lanewid[ii/2]*1/2+1,pt11.y+1),
                      new Point(lanepos[ii]+lanewid[ii/2]*1/2+1,pt22.y-1),"left",ii/2+1,0,false,"1D",ii+2);
        modify=false;
      }
    }

    for(int ii=0;ii<lanenum-3;ii+=2){
      if(lanewid[ii/2]<0.6*avelane){
        modify=true;
        //???  Exception occurred during event dispatching:
        inforight=(Info1D)current.elementAt(ii+2);
        removeElement(inforight,false,"1D",ii*2+1,true);
        infoleft=(Info1D)current.elementAt(ii+1);
        removeElement(infoleft,false,"1D",ii*2+2,true);
        modify=false;
        rearrange=true;
        lanenum+=-2;                  //added by wxs
      }
    }

    rearrange=true;
    if(rearrange==true){
      this.rearragelane();
    }
    rearrange=false;
    return 1;
  }

  public int recLaneScan(){
    int sensitivity;

    Point pt11,pt22;
    Rectangle rect;
    rect=(Rectangle)this.dialogImage.getROI();
    pt11=new  Point(0,0);
    pt22=new  Point(0,0);
    pt11=rect.getLocation();
    pt22.x=(int)rect.getWidth()+pt11.x;
    pt22.y=(int)rect.getHeight()+pt11.y;
    float lanethd,k;
    int nLane,nLine,temp,laneWidth,nlight,nhighlight;
    boolean justoverthd,rearrange;
    rearrange=false;
    nLane=nLine=laneWidth=laneWidth=nlight=nhighlight=0;
    getGray();
    //get max,min,threshold
    lanemax=lanemin=grayImage[height/4][width/4];
    for(int ii=height/4;ii<height*1/2;ii=ii+1){
      for(int jj=width/4;jj<width*1/2;jj=jj+2){
        if(lanemax<grayImage[ii][jj])
          lanemax=grayImage[ii][jj];
        if(lanemin>grayImage[ii][jj])
          lanemin=grayImage[ii][jj];
      }
    }
    int min=(int)lanemin;
    int max=(int)lanemax;
    //lanethd=(float)(lanemin+(lanemax-lanemin)*0.1+((lanemax-lanemin)*(100-sensi))/100);

    int num=(max-1)-(min+1)+1;
    float v,maxv=-1000;
    int thresh,optThresh=-1;
    for(int ii=0;ii<num;ii++){
      thresh=min+1+ii;
      createLane(thresh);
      v=judgeLane();
      if(v>maxv){
        maxv=v;
        optThresh=thresh;
      }
    }

    if(optThresh==-1) sensitivity=-1;
    else{
      float det=(((float)lanemax-1.0f)-((float)lanemin+1.0f)+1.0f)/200.0f;
      sensitivity=(int)((optThresh-((float)lanemin+1.0f))/det);
    }
    return sensitivity;
  }

  private float judgeLane(){
    float judgeV;
    Info1D id;
    float x1,x2;
    int lanes=this.getlanenum()/2;
    if(lanes<=3) return (4-lanes)*-1;

    float dd[]=new float[lanes];
    for(int ii=1;ii<=lanes;ii++){
      id=(Info1D)current.elementAt(2*ii-2);
      x1=id.point1.x;
      id=(Info1D)current.elementAt(2*ii-1);
      x2=id.point1.x;
      dd[ii-1]=Math.abs(x2-x1);
    }

    float deviate=0;
    int n=0;
    for(int ii=1;ii<=lanes;ii++){
      for(int jj=ii+1;jj<=lanes;jj++){
        n++;
        deviate+=Math.abs(dd[ii-1]-dd[jj-1]);
      }
    }

    // 空隙
    int gap=lanes-1;
    float gg[]=new float[gap];
    for(int ii=1;ii<=gap;ii++){
      id=(Info1D)current.elementAt(2*ii-1);
      x1=id.point1.x;
      id=(Info1D)current.elementAt(2*ii);
      x2=id.point1.x;
      gg[ii-1]=Math.abs(x2-x1);
    }
    for(int ii=1;ii<=gap;ii++){
      for(int jj=ii+1;jj<=gap;jj++){
        n++;
        deviate+=Math.abs(gg[ii-1]-gg[jj-1]);
      }
    }

    deviate/=n;

    if(deviate==0) judgeV=lanes*1000000;
    else judgeV=lanes/deviate;

    return judgeV;
  }

  private void createLane(float lanethd){
    //删除以前条泳道和条带 by wxs
    current.removeAllElements();

    Point pt11,pt22;
    Rectangle rect;
    rect=(Rectangle)this.dialogImage.getROI();
    pt11=new  Point(0,0);
    pt22=new  Point(0,0);
    pt11=rect.getLocation();
    pt22.x=(int)rect.getWidth()+pt11.x;
    pt22.y=(int)rect.getHeight()+pt11.y;
    float k;
    int nLane,nLine,temp,laneWidth,nlight,nhighlight;
    boolean justoverthd,rearrange;
    rearrange=false;
    nLane=nLine=laneWidth=laneWidth=nlight=nhighlight=0;
    //getGray();
    //get max,min,threshold
    //lanemax=lanemin=grayImage[width/4][height/4];
    //for(int ii=height/4;ii<height*1/2;ii=ii+1){
    //  for(int jj=width/4;jj<width*1/2;jj=jj+2){
    //    if(lanemax<grayImage[ii][jj])
    //      lanemax=grayImage[ii][jj];
    //    if(lanemin>grayImage[ii][jj])
    //      lanemin=grayImage[ii][jj];
    //  }
    //}
    //lanethd=(float)(lanemin+(lanemax-lanemin)*0.1+((lanemax-lanemin)*(100-sensi))/100);

    //get lane
    Info1D infoleftx,inforightx;
    for(int jj=pt11.x+2;jj<pt22.x-2;jj++){
      for(int ii=height/8;ii<height*7/8;ii++){
        if(grayImage[ii][jj]>lanethd){
          for(int kk=0;kk+jj<pt22.x-2&&grayImage[ii][jj+kk]>lanethd;kk++){
            laneWidth++;
          }
          if(laneWidth>10) {
            nLane++;
            //加左、右泳道 by wxs
            addElement(new Point(jj,pt11.y+1),new Point(jj,pt22.y-1),"left",nLane,0,false,"1D",0);
            addElement(new Point(jj+laneWidth,pt11.y+1),new Point(jj+laneWidth,pt22.y-1),"right",nLane,0,false,"1D",0);
            jj=jj+laneWidth+3;
            laneWidth=0;
            break;
          }
          else  laneWidth=0;
        }
      }
    }
    //go on discriminate lane, find out fault of rec
    //if lanewidth is longer than 1.5*avelength or shorter than 0.5*av, it is suspicious.
    //but it doesn't concern about 3* or 1/3 width

    //对泳道的修饰,太大，太小 by wxs
    //太大，加一个，中间为4

    int  lanenum,avelane,lanewidth2;
    Point pt1,pt2;
    Info1D infoleft,inforight;
    boolean getlane;
    getlane=false;
    avelane=0;
    lanenum=current.size();
    int []lanewid=new int[lanenum/2];
    int []lanepos=new int[lanenum];

    for (int ii=0;ii<lanenum;ii+=2){
      infoleft=(Info1D) current.elementAt(ii);
      if(ii+1>lanenum-2)  break;
      inforight=(Info1D)current.elementAt(ii+1);
      pt1=infoleft.point1;
      pt2=inforight.point2;
      lanewid[ii/2]=pt2.x-pt1.x;
      avelane+=lanewid[ii/2];
      lanepos[ii]=pt1.x;
      lanepos[ii+1]=pt2.x;
    }
    if(lanenum!=0)  avelane/=lanenum/2;
    for(int ii=0;ii<lanenum-2;ii+=2){
      if(lanewid[ii/2]>2*avelane){
        modify=true;
        rearrange=true;
        insertElement(new Point(lanepos[ii]+lanewid[ii/2]*1/2-2,pt11.y+1),
                      new Point(lanepos[ii]+lanewid[ii/2]*1/2-2,pt22.y-1),
                      "right",ii/2+1,0,false,"1D",ii+1);
        insertElement(new Point(lanepos[ii]+lanewid[ii/2]*1/2+1,pt11.y+1),
                      new Point(lanepos[ii]+lanewid[ii/2]*1/2+1,pt22.y-1),
                      "left",ii/2+1,0,false,"1D",ii+2);
        modify=false;
      }
    }

    int no=0;
    for(int ii=0;ii<lanenum-3;ii+=2){
      if(lanewid[ii/2]<0.6*avelane){
        modify=true;
        //???  Exception occurred during event dispatching:
        inforight=(Info1D)current.elementAt(ii+2);
        no++;
        removeElement(inforight,false,"1D",no,true);
        infoleft=(Info1D)current.elementAt(ii+1);
        no++;
        removeElement(infoleft,false,"1D",no,true);
        modify=false;
        rearrange=true;
        lanenum+=-2;                  //added by wxs
      }
    }
    rearrange=true;
    if(rearrange==true)  this.rearragelane();
    rearrange=false;

    //return 1;
  }


/**重新排列条带
 *laneN是要被重新排列条带的泳道号
 * 系laneN上的所有条带按顺序被重排
 **/
// Liusheng2 revise later
 public void rearrageline(int laneN,boolean insline){
   Vector currentNew=new Vector();
   int start=0,end=0,totalline=0;
   Point pt1,pt2;
   if(laneN<1||laneN>this.getlanenum())  return;
   boolean startB=true;

   //插入条带时候，条带所在的位置要在该泳道第一条条带的后面，否则start就不对了
   for(int ii=0;ii<current.size();ii++){
       Info1D infore;
       infore=(Info1D)current.elementAt(ii);
       if(infore.character.equals("line")&&infore.laneN==laneN&&startB==true)
       { start=ii;
         startB=false;
       }

          if(infore.character.equals("line")&&infore.laneN==laneN) {
            currentNew.addElement(infore);
            totalline++;

          }
          }
          if(insline==true){
          current.removeElementAt(current.size()-1);
          }
        for(int ii=0;ii<currentNew.size();ii++){
        Info1D infore1;
        infore1=(Info1D)currentNew.elementAt(ii);
       }
          Info1D infore1,infore2;
          int len=currentNew.size();
         for(int ii=0;ii<len;ii++){


          for(int jj=ii+1;jj<len; jj++)    {
             infore1=(Info1D)currentNew.elementAt(ii);
             infore2=(Info1D)currentNew.elementAt(jj);
            if(infore1.point1.y>infore2.point1.y)
             { currentNew.removeElementAt(ii);
               currentNew.insertElementAt(infore2,ii);
               currentNew.removeElementAt(jj);
               currentNew.insertElementAt(infore1,jj);
            }
            }
       }

       for(int ii=0;ii<currentNew.size();ii++){
           infore1=(Info1D)currentNew.elementAt(ii);
       }

         int tt=0;
         for(int ii=0;ii<totalline ;ii++){
          Info1D inf;
          inf=(Info1D)currentNew.elementAt(ii);
          if(insline==false||(ii!=totalline-1))
          {current.removeElementAt(ii+start);


          }
          pt1=inf.point1;
          pt2=inf.point2;
          tt++;
          insertElement(pt1,pt2,"line",laneN,tt,false,"1D",ii+start);

       }
 }


  /**重新排列泳道
   *泳道因为插入了元素，使顺序被打乱，重新排列泳道
   * 不需要传入参数
   **/
  public void rearragelane(){
    Vector currentNew=new Vector();
    Vector currentLine=new Vector();
    int lanenum=this.getlanenum();
    for(int ii=0;ii<current.size();ii++){
      Info1D infore;
      infore=(Info1D)current.elementAt(ii);
      if(infore.character.equals("left")||infore.character.equals("right")) currentNew.addElement(infore);
      if(infore.character.equals("line"))  currentLine.addElement(infore);
    }

    Info1D infore1,infore2,infexchange;
    for(int ii=1;ii<currentNew.size()-1;ii++){
      infore1=(Info1D)currentNew.elementAt(ii);
      for(int jj=ii+1;jj<currentNew.size();jj++) {
        infore2=(Info1D)currentNew.elementAt(jj);
        if(infore1.point1.x>infore2.point1.x)  {
          currentNew.removeElementAt(ii);
          currentNew.insertElementAt(infore2,ii);
          currentNew.removeElementAt(jj);
          currentNew.insertElementAt(infore1,jj);
        }
        if(infore1.point1.x==infore2.point1.x) {
          infore1.point1.x-=1;
          infore1.point2.x-=1;
          infore2.point1.x+=1;
          infore2.point2.x+=1;
          currentNew.removeElementAt(ii);
          currentNew.insertElementAt(infore1,ii);
          currentNew.removeElementAt(jj);
          currentNew.insertElementAt(infore2,jj);
        }
      }
    }

    current.removeAllElements();
    //  从小到大，重排泳道的次序
    for(int ii=0;ii<currentNew.size();ii+=2){
      infore1=(Info1D)currentNew.elementAt(ii);
      infore2=(Info1D)currentNew.elementAt(ii+1);
      //current.removeElementAt(ii);
      //   this.insertElement(infore1.point1,infore1.point2,"left",ii/2+1,0,false,"1D",ii);
      addElement(infore1.point1,infore1.point2,"left",ii/2+1,0,false,"1D",0);
      // current.removeElementAt(ii+1);
      addElement(infore2.point1,infore2.point2,"right",ii/2+1,0,false,"1D",0);
      // this.insertElement(infore2.point1,infore2.point2,"right",ii/2+1,0,false,"1D",ii+1);
    }
    totallane=current.size();
    if(current.size()==0) return;
    Info1D first=(Info1D)current.elementAt(0);
    Info1D last=(Info1D)current.lastElement();
    Info1D ifleftlane,ifrightlane;
    Point point11,point12,point21,point22,ptllane,ptrlane;
    point11=first.point1;
    point12=last.point1;
    point21=first.point2;
    point22=last.point2;
    addElement(point11,point12,"up",0,0,false,"1D",0);
    addElement(point21,point22,"down",0,0,false,"1D",0);
    lanetotalN=current.size();

    //System.out.println("total lane:"+lanetotalN);
    // enabled by wxs on 20030806
    for(int ii=0;ii<currentLine.size();ii++){
      Info1D inf;
      inf=(Info1D)currentLine.elementAt(ii);
      addElement(inf.point1,inf.point2,"line",inf.laneN,inf.lineN,false,"1D",0);
    }

    Info1D firstx;
    Point pt5,pt6;
    for(int ii=0;ii<this.getlanenum();ii++) {
      firstx=(Info1D)current.elementAt(ii);
      pt5=firstx.point1;
      pt6=firstx.point2;
      // System.out.println(firstx.character);
      //  System.out.println(firstx.laneN);
      //  System.out.println(pt5);
      //  System.out.println(pt6);
    }
  }

  /**
   *   linewid 是条带的宽的限制，条带不能大于它
   *   lineheigh是条带的峰高，超过它，才能是条带
   *   increment是斜率，意义是条带的峰的尖锐程度，低于它，不能算作峰
   *   noise 是躁音，意义是带的最小宽度
   * */
  public boolean getline(int linewid,int lineheight,int increment,int noise){
    Point pt1,pt2,ptllane,ptrlane;
    Point pt11,pt22;
    Rectangle rect;
    int prepos=0;
    this.getGray();
    int up=this.gettop();
    int down=this.getbottom();
    rect=(Rectangle)this.dialogImage.getROI();
    pt11=new  Point(0,0);
    pt22=new  Point(0,0);
    pt11=rect.getLocation();
    pt22.x=(int)rect.getWidth()+pt11.x;
    pt22.y=(int)rect.getHeight()+pt11.y;
    pt1=pt2=new Point(0,0);
    int   linewidth;
    linewidth=linewid;
    float linethd,maxgray;
    Info1D ifleftlane,ifrightlane;
    int totalline=current.size();
   for(int ii=this.getlanenum();ii<totalline;ii++)
   {Info1D inf;
   inf=(Info1D)current.elementAt(ii);
   if(inf.character.equals("line")){
    current.removeElementAt(ii);
    ii--;
    //System.out.println("del");
    totalline--;}
   }
     //delete remain line
    //get line
      int sumline,tt,lineup,linedown,maxpos;
      boolean overthdline=true;
      lineup=linedown=sumline=tt=maxpos=0;
      maxgray=(float)0;
      linethd=lanemin+(lanemax-lanemin)*lineheight/100;
      float []avegray=new float[down-up];
      for(int ii=0;ii<totallane;ii+=2){
       ifleftlane=(Info1D)current.elementAt(ii);
       ifrightlane=(Info1D)current.elementAt(ii+1);
       ptllane=ifleftlane.point1;
       ptrlane=ifrightlane.point1;
       tt=0;
       //if 斜率change ,may modify
    /*   if(pt22.y<down)
         down=pt22.y;
       if(up<pt11.y)
         up=pt11.y;*/
         for(int jj=up;jj<down;jj++){
          for(int kk=ptllane.x;kk<ptrlane.x;kk++){
            sumline+=grayImage[jj][kk];
          }
          if(ptrlane.x-ptllane.x==0){
            break;
          }
          avegray[jj-up]=sumline/(ptrlane.x-ptllane.x);
          sumline=0;

       }
     for(int jj=up;jj<down-5;jj++){
       if(avegray[jj-up]>linethd&&overthdline){
         overthdline=false;
         lineup=jj;
       }
       if(overthdline==false&&(maxgray<avegray[jj-up]))  {
         maxgray=avegray[jj-up];
         maxpos=jj;
       }
       //??? || and && ,which is better?
        if((avegray[jj-up]<linethd)&&(!overthdline)){
      // if((avegray[jj-up]<linethd||(jj-lineup>linewidth))&&(!overthdline)){
      //if((avegray[jj-up]<linethd&&(jj-lineup>linewidth))&&(!overthdline)){
        overthdline=true;
        linedown=jj;
        pt1.x=ptllane.x;
        pt1.y=maxpos;
        pt2.x=ptrlane.x;
        pt2.y=maxpos;

        if((linedown-lineup>noise)&&(Math.abs(maxpos-prepos)>linewid)){
        tt++;
        prepos=maxpos;
        addElement(new Point(ptllane.x,pt1.y),new Point(ptrlane.x,pt1.y),"line",ii/2+1,tt,false,"1D",0);
        maxpos=0;
        maxgray=(float)0;
        jj+=2;
        }

      }
      if(overthdline==true){
       if(jj-up<6||down-jj<6)  continue;
        if((avegray[jj-up]>avegray[jj-up-4])&&(avegray[jj-up]>avegray[jj-up+4])&&(avegray[jj-up]>avegray[jj-up-5])&&(avegray[jj-up]>avegray[jj-up+5]))
        {if((avegray[jj]-lanemin)>(((lanemax-lanemin)*increment*3)/400))
        {
          //*****
         if(Math.abs(jj-prepos)<linewid)
           continue;
          pt1.x=ptllane.x;
          pt1.y=jj;
          pt2.x=ptrlane.x;
          pt2.y=jj;
            tt++;
            addElement(new Point(ptllane.x,pt1.y),new Point(ptrlane.x,pt1.y),"line",ii/2+1,tt,false,"1D",0);
            maxpos=0;
            maxgray=(float)0;
            jj+=2;
            prepos=jj;
          //*******
        }
        }
          }
     }
       }
      return true;
}

  /**
   * 只考虑高度限制，识别条带
   * @param lineheight 高度限制
   * @return
   */
  public boolean getlineH(int lineheight){
    Point pt1,pt2,ptllane,ptrlane;
    Point pt11,pt22;
    Rectangle rect;
    int prepos=0;
    this.getGray();
    int up=this.gettop();
    int down=this.getbottom();
    rect=(Rectangle)this.dialogImage.getROI();
    pt11=new  Point(0,0);
    pt22=new  Point(0,0);
    pt11=rect.getLocation();
    pt22.x=(int)rect.getWidth()+pt11.x;
    pt22.y=(int)rect.getHeight()+pt11.y;
    pt1=pt2=new Point(0,0);
    float linethd,maxgray;
    Info1D ifleftlane,ifrightlane;
    int totalline=current.size();
    for(int ii=this.getlanenum();ii<totalline;ii++) {
      Info1D inf;
      inf=(Info1D)current.elementAt(ii);
      if(inf.character.equals("line")){
        current.removeElementAt(ii);
        ii--;
        totalline--;
      }
    }

    //delete remain line
    //get line
    int sumline,tt,lineup,linedown,maxpos;
    boolean overthdline=true;
    lineup=linedown=sumline=tt=maxpos=0;
    maxgray=(float)0;
    linethd=lanemin+(lanemax-lanemin)*lineheight/100;
    float []avegray=new float[down-up];
    for(int ii=0;ii<totallane;ii+=2){
      ifleftlane=(Info1D)current.elementAt(ii);
      ifrightlane=(Info1D)current.elementAt(ii+1);
      ptllane=ifleftlane.point1;
      ptrlane=ifrightlane.point1;
      tt=0;

      for(int jj=up;jj<down;jj++){
        for(int kk=ptllane.x;kk<ptrlane.x;kk++){
          sumline+=grayImage[jj][kk];
        }
        if(ptrlane.x-ptllane.x==0){
          break;
        }
        avegray[jj-up]=sumline/(ptrlane.x-ptllane.x);
        //System.out.println("avg--"+(jj-up)+","+avegray[jj-up]);
        sumline=0;
      }

      //System.out.println("up="+up+",down="+down+",totallane="+totallane);

      for(int jj=up;jj<down-5;jj++){
        if(avegray[jj-up]>linethd&&overthdline){
          overthdline=false;
          lineup=jj;
        }
        if(overthdline==false&&(maxgray<avegray[jj-up]))  {
          maxgray=avegray[jj-up];
          maxpos=jj;
        }
        if((avegray[jj-up]<linethd)&&(!overthdline)){
          overthdline=true;
          linedown=jj;
          pt1.x=ptllane.x;
          pt1.y=maxpos;
          pt2.x=ptrlane.x;
          pt2.y=maxpos;
          tt++;
          prepos=maxpos;

          addElement(new Point(ptllane.x,pt1.y),
                     new Point(ptrlane.x,pt1.y),
                     "line",ii/2+1,tt,false,"1D",0);
          maxpos=0;
          maxgray=(float)0;
          jj+=2;

          //System.out.println("addEle"+pt1+","+ptllane+"current size:"+current.size());
        }
      }

    }
    return true;
  }

  //modified by wxs 2003/06/08
  public void draw(Graphics g,Point oriPoint){
    Graphics2D g2d;
    g2d=(Graphics2D)g;

    Stroke oldStroke=g2d.getStroke();
    BasicStroke dotStroke;
    int cap=BasicStroke.CAP_SQUARE ;
    int join=BasicStroke.JOIN_BEVEL;
    float miterlimit=10.0f;
    float dash_phase=0;
    float []dash={2,2,2,2};
    dotStroke= new BasicStroke(1, cap, join, miterlimit, dash,  dash_phase);

    for(int iii=1;iii<=current.size() ;iii++){
      Info1D info1D=((Info1D)current.elementAt(iii-1));
      if(info1D.visible ==false) continue;
      if(info1D.character .equals("line")==true){
        g2d.setStroke(dotStroke);
        g2d.setColor(Color.red) ;
      }
      else{
        g2d.setStroke(oldStroke);
        g2d.setColor(Color.blue) ;
      }
      g2d.drawLine(info1D.point1.x+oriPoint.x,info1D.point1.y+oriPoint.y ,
                  info1D.point2.x+oriPoint.x,info1D.point2.y+oriPoint.y);
    }

    g2d.setStroke(oldStroke);
    g2d.setColor(Color.red) ;


    int laneN=this.getlanenum();
    Info1D infleft,infright;
    for(int ii=0;ii<laneN;ii+=2){
      if(ii+1>laneN-1) return;
      Point pt1,pt2;
      infleft=(Info1D)current.elementAt(ii);
      infright=(Info1D)current.elementAt(ii+1);
      pt1=infleft.point1;
      pt2=infright.point1;
      g2d.drawString(Integer.toString(ii/2+1),(pt1.x+pt2.x)/2-2+oriPoint.x,15);
    }

    // draw the points or lines for the revising the image
    g2d.setColor(new Color(255,0,255));
    if(point.size()==1){
      Point pt1;
      pt1=(Point)point.elementAt(0);
      g2d.drawOval(pt1.x+oriPoint.x-2,pt1.y+oriPoint.y-2,4,4);
    }
    for(int ii=0;ii<point.size()-1;ii++){
      Point pt1,pt2;
      pt1=(Point)point.elementAt(ii);
      pt2=(Point)point.elementAt(ii+1);
      g2d.drawLine(pt1.x+oriPoint.x,pt1.y+oriPoint.y,
                   pt2.x+oriPoint.x,pt2.y+oriPoint.y);
    }
  }

  // added by wxs 2003/08/14
  public void save(ImageProcessor ipSave,Point oriPoint){
    Info1D info1D;
    ipSave.setLineWidth(1);
  //  ipSave.setDotsMode(true,2,1);
    for(int iii=1;iii<=current.size() ;iii++){
      info1D=((Info1D)current.elementAt(iii-1));
      if(info1D.visible ==false) continue;
      if(info1D.character.equals("line")==true) ipSave.setColor(Color.red);
      else ipSave.setColor(Color.blue);
      ipSave.drawLine(info1D.point1.x+oriPoint.x,info1D.point1.y+oriPoint.y ,
                      info1D.point2.x+oriPoint.x,info1D.point2.y+oriPoint.y);
    }

    ipSave.setColor(Color.red) ;
 //   ipSave.setDotsMode(false,0,0);

    int laneN=this.getlanenum();
    Info1D infleft,infright;
    for(int ii=0;ii<laneN;ii+=2){
      if(ii+1>laneN-1) return;
      Point pt1,pt2;
      infleft=(Info1D)current.elementAt(ii);
      infright=(Info1D)current.elementAt(ii+1);
      pt1=infleft.point1;
      pt2=infright.point1;
      ipSave.drawString(Integer.toString(ii/2+1),(pt1.x+pt2.x)/2-2+oriPoint.x,15);
    }

    /* ???
    //Liusheng add later
    g2d.setBackground(new Color(0,255,0));
    for(int ii=0;ii<point.size()-1;ii++){
      Point pt1,pt2;
      pt1=(Point)point.elementAt(ii);
      pt2=(Point)point.elementAt(ii+1);
      g2d.drawLine(pt1.x+oriPoint.x,pt1.y+oriPoint.y,
                   pt2.x+oriPoint.x,pt2.y+oriPoint.y);
    }
    */
  }


/*********************************************************************************
 **********************************************************************************/
/**
 * 该函数可以实现校正
 *
 */
public void revise(){
  Point pt1,pt2,pt0;
  Point Rpt1,Rpt2;
  int kk;
  pixel=new int[width][height];
  orgpix=new int[width][height];
 for(int ii=0;ii<width;ii++)
   for(int jj=0;jj<height;jj++)
     pixel[ii][jj]=0;

  Rpt2=new Point(0,0);
  Rpt1=dialogImage.getROI().getLocation();
  Rpt2.x=dialogImage.getROI().getLocation().x+(int)dialogImage.getROI().getWidth();
  Rpt2.y=dialogImage.getROI().getLocation().y+(int)dialogImage.getROI().getHeight();
  pt1=new Point(0,0);
  pt2=new Point(0,0);
  pt0=new Point(0,0);
  if(point.size()>2)
  {pt1=(Point)point.elementAt(0);
   pt2=(Point)point.elementAt(1);
  }
  if(pt1.y<pt2.y)  reviseforwd=true;
  else reviseforwd=false;
  //get bottom point.y;
  pt0=(Point)point.elementAt(0);
  int y=pt0.y;
  if(reviseforwd==true) {
  for(int ii=0;ii<point.size();ii++)
  { pt1=(Point)point.elementAt(ii);

  {if(y<pt1.y) y=pt1.y;}
  }
  }
  else
  {for(int ii=0;ii<point.size();ii++)
  { pt1=(Point)point.elementAt(ii);

  {if(y>pt1.y) y=pt1.y;}
  }

  }
  //revise
  for(int ii=0;ii<width;ii++)
  for(int jj=0;jj<height;jj++)
  { pixel[ii][jj]=ip.getPixel(ii,jj);
    orgpix[ii][jj]=ip.getPixel(ii,jj);
   }
  for(int tt=0;tt<point.size()-1;tt++)
  {     pt1=(Point)point.elementAt(tt);
        pt2=(Point)point.elementAt(tt+1);
    for(int ii=pt1.x;ii<pt2.x;ii++){
         kk=(int)(y-pt2.y+((pt2.x-ii)*(pt2.y-pt1.y)/(pt2.x-pt1.x)));
      kk=Math.abs(kk);
         for(int jj=Rpt1.y;jj<Rpt2.y;jj++)
        {

       if(jj<=kk&&reviseforwd) continue;
       if(jj>height+kk&&(reviseforwd==false))  continue;
       if(reviseforwd==true)
       pixel[ii][jj]=ip.getPixel(ii,jj-kk);
         else
         pixel[ii][jj]=ip.getPixel(ii,jj+kk);
       }
 }

 }
 for(int ii=0;ii<width;ii++)
   for(int jj=0;jj<height;jj++)
   {ip.putPixel(ii,jj,pixel[ii][jj]);
   }

  dialogImage.repaint();
}

//added by wxs 2003/06/01
public Rectangle getLaneRectangle(int laneNo){
  Info1D infL,infR;
  infL=null;
  infR=null;
  boolean exist=false;
  for(int ii=0;ii<current.size();ii+=2)
  {
   infL=(Info1D)current.elementAt(ii);
   infR=(Info1D)current.elementAt(ii+1);
   if(infL.character.equals("left")&&infL.laneN ==laneNo){
     exist=true;
     break;
   }
  }
  if(exist==true){
  return new Rectangle(infL.point1.x,infL.point1.y,
                         infR.point1.x-infL.point1.x+1,infR.point2.y-infR.point1.y+1);
 }
 else{
   return null;
 }
}

  /**
  * 用于RollingBall之用
  * @param laneNo
  * @return
  */
  public Rectangle getLaneRectangleRollBall(int laneNo){
    Info1D infL,infR;
    infL=null;
    infR=null;
    boolean exist=false;
    Info1D infL2,infR2;
    infL2=null;
    infR2=null;
    boolean exist2=false;

    for(int ii=0;ii<current.size();ii+=2) {
      infL=(Info1D)current.elementAt(ii);
      infR=(Info1D)current.elementAt(ii+1);
      if(infL.character.equals("left")&&infL.laneN ==laneNo){
        exist=true;
        break;
      }
    }
    if(laneNo!=(this.lanetotalN-2)/2){
      for(int ii=0;ii<current.size();ii+=2) {
        infL2=(Info1D)current.elementAt(ii);
        infR2=(Info1D)current.elementAt(ii+1);
        if(infL2.character.equals("left")&&infL2.laneN ==laneNo+1){
          exist2=true;
          break;
        }
      }
    }
    if(exist==true){
      if(exist2==false){
        return new Rectangle(infL.point1.x,infL.point1.y,
                             infR.point1.x-infL.point1.x+1,
                             infR.point2.y-infR.point1.y+1);
      }
      else{
        return new Rectangle(infL.point1.x,infL.point1.y,
                             infL2.point1.x-infL.point1.x+1,
                             infR.point2.y-infR.point1.y+1);
      }
    }
    else{
      return null;
    }
  }

public void arrangept(){
  Point pt1,pt2;
  for(int ii=0;ii<point.size()-1;ii++)
  {  pt1=(Point)point.elementAt(ii);
    for(int jj=ii+1;jj<point.size();jj++)
    { pt2=(Point)point.elementAt(jj);
     if(pt1.x>pt2.x){
       point.removeElementAt(ii);
       point.insertElementAt(pt2,ii);
       point.removeElementAt(jj);
       point.insertElementAt(pt1,jj);
     }
    }
  }

for(int ii=0;ii<point.size();ii++)
{Point pt;
 pt=(Point)point.elementAt(ii);
 //System.out.println("sdhlkahflakjfl");
// System.out.println(ii);
// System.out.println(pt);

}
}


  public  void getrevisept(Point pt){
    if(point.size()>0){
      for(int ii=0;ii<point.size();ii++){
        Point ptS;
        ptS=(Point)point.elementAt(ii);
        if(pt.x <ptS.x+1&&pt.x>ptS.x-1)  point.removeElementAt(ii);
      }
    }
    point.addElement(pt);
  }

public boolean isRevised(){
  if(point.size() >=1) return true;
  else return false;
}

public void setRevisedZero(){
  point.removeAllElements() ;
}

   public void recoverrev(){
  for(int ii=0;ii<width;ii++)
  for(int jj=0;jj<height;jj++)
  {ip.putPixel(ii,jj,orgpix[ii][jj]);
   }
}
//get line num
/**
 *
 * @param lanenum 泳道序号
 * @return  该泳道的条带数
 */
    public int getlinenum(int lanenum){
      int line=0;
      for(int ii=4;ii<current.size();ii++){
       Info1D inf;
       inf=(Info1D)current.elementAt(ii);
       if(inf.laneN==lanenum&&inf.character.equals("line"))
               line++;
     }

     return line;
    }
/*************************************************************************/
//匹配比较部分代码
//Liusheng add later
/**
 * @调用该函数可以取得虚拟泳道
 * @虚拟泳道以点的形式存在virtlane这个向量里
 * @它是个公有变量，可以被其他类调用
 * @param err 允许的误差
 */
public void getvirtuallane(float err){
  int allowerr;
  Point Rpt1,Rpt2;
  Rpt2=new Point(0,0);
  Rpt1=dialogImage.getROI().getLocation();
  Rpt2.x=dialogImage.getROI().getLocation().x+(int)dialogImage.getROI().getWidth();
  Rpt2.y=dialogImage.getROI().getLocation().y+(int)dialogImage.getROI().getHeight();
  int lanenum=this.getlanenum();
  Vector currentnew=new Vector();
  for(int ii=lanenum+2;ii<current.size();ii++){
    Info1D inf;
    inf=(Info1D)current.elementAt(ii);
    currentnew.addElement(inf);
  }
  int total=currentnew.size();
  Vector comline=new Vector();


  for(int kk=0;kk<total;kk++)
  { Info1D inf1=(Info1D)currentnew.elementAt(kk);
    float infup1,infdown1;
    boolean findcom=false;
  //  infup1=((1-err)*(inf1.point1.y-Rpt1.y)/(Rpt2.y-Rpt1.y));
  //  infdown1=((1+err)*(inf1.point1.y-Rpt1.y)/(Rpt2.y-Rpt1.y));
    infup1=inf1.point1.y-100*err;
    infdown1=inf1.point1.y+100*err;
    for(int jj=kk+1;jj<total;jj++){
      Info1D inf2=(Info1D)currentnew.elementAt(jj);
      float infup2,infdown2;
      infup2=inf2.point1.y-100*err;
      infdown2=inf2.point1.y+100*err;
      //infup2=((1-err)*(inf2.point1.y-Rpt1.y)/(Rpt2.y-Rpt1.y));
      //infdown2=((1+err)*(inf2.point1.y-Rpt1.y)/(Rpt2.y-Rpt1.y));
    if(infdown1>infup2&&infup1<infdown2&&(inf1.laneN!=inf2.laneN)){
      currentnew.removeElement(inf2);
     // System.out.println("第"+inf2.laneN+"泳道");
     // System.out.println("第"+inf2.lineN+"条带");
      //System.out.println("Rf的上限"+infup2);
    //  System.out.println("Rf的下限"+infdown2);
     //System.out.println("该条带的点"+inf2.point1);

      total--;
      jj--;
      if(infup1<infup2)
      {
        infup1=infup2;
      }
        if(infdown1>infdown2)
        {
          infdown1=infdown2;
        }
        findcom=true;
    }
    }
     if(findcom==true)
        {
      // System.out.println("第"+inf1.laneN+"泳道");
     // System.out.println("第"+inf1.lineN+"条带");
     //System.out.println("Rf的上限"+infup1);
   //  System.out.println("Rf的下限"+infdown1);
     // System.out.println("该条带的点"+inf1.point1);
         //int val=(int)((infup1+infdown1)*(Rpt2.y-Rpt1.y)/2+Rpt1.y);
         int val=(int)((infup1+infdown1)/2);
      this.virtlane.addElement(new Point(0,val));
        }
       }
}
//******************************************************
//比较条带的相似性
public int []compareline(int mainlane,int lane,int []line,float err){
  Info1D inf;
 // FrameMain frm;
//  frm=(FrameMain)this.dialogimage.getMotherFrame();
  //frm.getd
  Point pt1,pt2;
  int upmain,downmain,up,down;
  upmain=downmain=0;up=down=1;
  inf=null;
  int kk=0;
  int length=line.length;
  int []result=new int[length];
  for(int ii=0;ii<length;ii++)
   result[ii]=0;
  for(int ii=this.getlanenum()+2;ii<current.size();ii++){
    inf=(Info1D)current.elementAt(ii);
    if(inf.laneN==lane&&inf.lineN==line[kk]) {
             if(inf!=null){
              pt1=inf.point1;
              up=inf.point1.y-(int)(100*err);
              down=inf.point1.y+(int)(100*err);
        //      System.out.println(pt1);
        }
             if(mainlane!=0){
                 Info1D infmain;
                 infmain=null;
                for(int jj=this.getlanenum()+2;jj<current.size();jj++){
                 infmain=(Info1D)current.elementAt(jj);
                 if(infmain.laneN==mainlane&&infmain.lineN==line[kk])

                {upmain=infmain.point1.y-(int)(100*err);;
                 downmain=infmain.point1.y+(int)(100*err);;
                 pt2=infmain.point1;
         //       System.out.println(pt2);
                break;
                 }

                    }

                 }
               else
                {Point pt;
                 pt=(Point)virtlane.elementAt(line[kk]-1);
                 upmain=pt.y-(int)(100*err);;
                 downmain=pt.y+(int)(100*err);;
                 }
                if(upmain<down&&downmain>up)
                 result[kk]=1;
                  else
                    result[kk]=0;
                  kk++;
               if(kk>length-1) break;
    }
  }
//  System.out.println("start result");
  for(int ii=0;ii<result.length;ii++)
     {
   //  System.out.println(result[ii]);

     }
  //    System.out.println("end");
 /*  for(int ii=this.getlanenum();ii<current.size();ii++)
   { Info1D infs=(Info1D)current.elementAt(ii);
     System.out.println(infs.character);
     System.out.println(infs.laneN);
     System.out.println(infs.lineN);
     System.out.println(infs.point1);
     System.out.println(infs.point2);
   }*/

  /* System.out.println(up);
   System.out.println(down);
   System.out.println(upmain);
   System.out.println(downmain);*/
   return result;
}
 //***
 /**
  *
  * @param laneA 泳道的一个序号
  * @param lineB  泳道的另一个序号
  * @param err 允许的误差
  * @return  二泳道相似的条带数
  */

  public int getresemblelinenum(int laneA,int laneB, float err){
       Info1D inf;
      int upa,downa,upb,downb;
      upa=downa=0;upb=downb=1;
      inf=null;
      int kk=0;
      int lineres=0;
      int length=this.getlinenum(laneA);
      int bbpos=getlanenum()+2;
      for(int ii=getlanenum()+2;ii<this.current.size();ii++)
      {
        inf=(Info1D)current.elementAt(ii);
        if(inf.laneN==laneA&&inf.character.equals("line")) {
         kk++;
         Point  pt1=inf.point1;
          upa=inf.point1.y-(int)(100*err);
          downa=inf.point1.y+(int)(100*err);;

          Info1D infB;
          infB=null;

           for(int jj=bbpos;jj<current.size();jj++){
             infB=(Info1D)current.elementAt(jj);
              if(infB.laneN==laneB&&infB.character.equals("line"))
               {upb=infB.point1.y-(int)(100*err);
                  downb=infB.point1.y+(int)(100*err);
                  Point pt2=infB.point1;


         if(upa<downb&&downa>upb)   {
           lineres++;
           bbpos=jj+1;
           break;
         }
         }
           }
      }
      if(kk>=length)  break;
      }
      return lineres;
  }

  private int getbottom(){
    int botN=0;
    for(int ii=0;ii<current.size();ii++){
      Info1D inf=(Info1D)current.elementAt(ii);
      if(inf.character.equals("down"))   botN=inf.point2.y;
    }
    return botN;
  }

  private int gettop(){
    int N=0;
    for(int ii=0;ii<current.size();ii++){
      Info1D inf=(Info1D)current.elementAt(ii);
      if(inf.character.equals("up"))  N=inf.point2.y;
    }
    return N;
  }

  //liusheng
  private void addlane(Point pt1,Point pt2){
    Info1D infl,infr;
    int gap=4;
    int sum,kk,gaps,gapk;
    int avewid,avegap;
     avegap=3;
     sum=kk=gaps=gapk=0;
     //System.out.print("good");
    for(int ii=0;ii<current.size()-1;ii+=2){
      infl=(Info1D)current.elementAt(ii);
      infr=(Info1D)current.elementAt(ii+1);
      if(infl.character!="left"||infr.character!="right")
        break;
       sum+=infr.point1.x-infl.point1.x;
       System.out.print("residue"+(infr.point1.x-infl.point1.x));
       kk++;
       System.out.print("test1");
       if(ii>1){
       Info1D infprer=(Info1D)current.elementAt(ii-1);
       if(infl.point1.x-infprer.point1.x<(infr.point1.x-infl.point1.x)*3/4){
         gaps+=infl.point1.x-infprer.point1.x;
         gapk++;
       }
       }
    }
        if(kk!=0)
        avewid=sum/kk;
        else
          avewid=10;
        if(gapk!=0)
        avegap=gaps/gapk;
        else
          avegap=3;
        for(int ii=2;ii<current.size()-1;ii+=2){
          infl=(Info1D)current.elementAt(ii);
          infr=(Info1D)current.elementAt(ii+1);
         if(infl.character!="left"||infr.character!="right")   break;
          Info1D infprer=(Info1D)current.elementAt(ii-1);
          int N=0;
          N=(infl.point1.x-infprer.point1.x)/(avewid+avegap);
          System.out.print("avewid"+avewid+"avegap="+avegap);
          System.out.print("test2");
          System.out.print("test2");
           System.out.print("N ="+N);
          for(int jj=0;jj<N;jj++){
            System.out.println("addline"+new Point(infprer.point1.x+avegap+(avewid+avegap)*(N-1),infprer.point1.y));
            insertElement(new Point(infprer.point1.x+avegap+(avewid+avegap)*(N-1),infprer.point1.y),
                  new Point(infprer.point1.x+avegap+(avewid+avegap)*(N-1),infprer.point2.y),"left",ii/2+1,0,false,"1D",ii+1);
            insertElement(new Point(infprer.point1.x+(avewid+avegap)*N,infprer.point1.y),
                  new Point(infprer.point1.x+avegap+(avewid+avegap)*N,infprer.point2.y),"right",ii/2+1,0,false,"1D",ii+1);

          }
        }
  }

  public Layer1D getLayer1D(){
    return this;
  }

  public void setDialogImage(DialogImage di){
    this.dialogImage =di;
  }

  public void writeExternal(ObjectOutput out) throws IOException {
     /**@todo Implement this java.io.Externalizable method*/
    try{
       out.writeInt(this.totallane);
       out.writeInt(this.width);
       out.writeInt(this.height);

       out.writeFloat(this.lanemax);
       out.writeFloat(this.lanemin);
       //out.writeInt(this.bottom);
       //out.writeInt(this.lanepos);

       Info1D id=new Info1D();
       int d1Num;
       d1Num=this.current.size();
       out.writeInt(d1Num);
       for(int iii=1;iii<=d1Num;iii++){
         id=(Info1D)this.current.elementAt(iii-1);
         out.writeObject(id);
       }
     }
     catch(IOException e){
       throw new java.lang.UnsupportedOperationException("Method writeExternal() not yet implemented.");
     }
  }

  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    /**@todo Implement this java.io.Externalizable method*/
    try{
      // first init the layer
      this.initLayer();
      this.totallane=in.readInt();
      this.width =in.readInt();
      this.height =in.readInt();
      this.lanemax =in.readFloat();
      this.lanemin =in.readFloat();
      //this.bottom =in.readInt();
      //this.lanepos =in.readInt();

      Info1D id;
      int d1Num;
      d1Num=in.readInt() ;
      for(int iii=1;iii<=d1Num;iii++){
        id=new Info1D();
        id=(Info1D)in.readObject();
        id.ID=this.ID;
        this.addElement(id,false,"",1);
        this.ID++;
      }
    }
    catch(IOException e){
      throw new java.lang.UnsupportedOperationException("Method readExternal() not yet implemented.");
    }
  }

  public void addElement(Info1D text1D,boolean setMainDone,
                         String layerSign,int step) {
    // ID++;
    super.addElement(text1D,setMainDone,layerSign,step,-1);
  }

}
