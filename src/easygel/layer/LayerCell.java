package easygel.layer;

import easygel.image.*;
import easygel.*;
import ij.process.*;
import java.awt .*;
import java.util.*;
import java.awt.image.*;
import javax.swing.*;

import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.IOException;
import java.io.ObjectInput;

import java.awt.geom.*;

public class LayerCell extends Layer  implements Externalizable {
  /** 是否是阵列分析 */
  public boolean isArray=false;
  private boolean isInnerMethod;
  private int hnum,vnum,radius;
  private int cellh,cellw;
  private int locxx,locyy;

  public  LayerCell(DialogImage di) {
    super(di);
  }

  public  LayerCell(){
    super();
  }

  public void removeElement(Information o,boolean setMainDone,
                            String layerSign,int step,boolean adjustNo){
    super.removeElement(o,setMainDone,layerSign,step,true);
    // 调整序号
    if(adjustNo==true) this.adjustNo() ;
  }

  private void adjustNo(){
    int size=this.current .size() ;
    InfoCell cell;
    for(int ii=1;ii<=size;ii++){
      cell=(InfoCell)current.elementAt(ii-1);
      cell.cellNo =ii;
    }
  }

  public void deleteObject(Point p){
    InfoCell cell;
    cell=hitElement(p);
    if(cell==null) return;
    this.removeElement(cell,true,"Cell",1,true);
  }

  public InfoCell hitElement(Point p){
    InfoCell cell=null;
    for(int ii=1;ii<=this.current .size() ;ii++){
      cell=(InfoCell)this.current .elementAt(ii-1) ;
      if(cell.m_polygon.contains(p) ==true) break;
      else cell=null;
    }
    return cell;
  }

  public void setEdgeForArray(int hnum,int vnum,int radius,
                              boolean isInnerMethod,int cellw,int cellh,
                              int locx,int locy){
    this.isArray=true;
    this.hnum=hnum;
    this.vnum=vnum;
    this.radius=radius;
    this.isInnerMethod=isInnerMethod;
    this.cellw=cellh;
    this.cellh=cellw;
    this.locxx=locx;
    this.locyy=locy;
  }

  /**
   *
   * @param adjust 是否微调
   */
  public void findEdgeForArray(boolean adjust){
    Rectangle rect=this.dialogImage.getROI();
    int locx=rect.getLocation().x;
    int locy=rect.getLocation().y;
    int detx=(int)(rect.getWidth()/this.hnum+0.5);
    int dety=(int)(rect.getHeight()/this.vnum+0.5);
    int cx,cy;
    int []x;
    int []y;
    Polygon pg;
    InfoCell cell;
    int cellNo=1;

    if(adjust==true){
      locx+=locxx;
      locy+=locyy;
      detx+=cellw;
      dety+=cellh;
    }

    for(int ii=1;ii<=this.hnum;ii++){
      cx=(int)(locx+(ii-1)*detx+detx/2+0.5);
      x=this.getPointXFromOval(cx,radius);
      for(int jj=1;jj<=this.vnum;jj++){
        cy=(int)(locy+(jj-1)*dety+dety/2+0.5);
        y=this.getPointYFromOval(cx,cy,radius);
        int xx[]=new int[x.length];
        int yy[]=new int[y.length];
        for(int ss=0;ss<xx.length;ss++) xx[ss]=x[ss];
        for(int ss=0;ss<yy.length;ss++) yy[ss]=y[ss];
        pg=new Polygon(xx,yy,xx.length);
        /*
        cell=this.createCell(pg,this.dialogImage.getIP(),
                             this.dialogImage.getBackgroundBlack(),
                             this.dialogImage.getGrayBits(),cellNo);
        */
        cell=new InfoCell(pg,0,this.dialogImage);
        if(cell.m_area <=0) continue;
        cell.cellNo =cellNo;
        cell.ID =this.ID;
        // 重要性质！！！
        //cell.adjustCoordinate(rect.getLocation(),true);

        if(cell!=null){
          cellNo++;
          addElement(cell,true,"Cell",1,-1);
        }
      }
    }
  }

  /**
   *
   * @param cx 中心点x
   * @param cy 中心点y
   * @param radius 半径
   * @return
   */
  public static int[] getPointYFromOval(double cx,double cy,double radius){
    /*
    int []yy=new int[((int)radius*2+1)*2];
    for(int xx=(int)cx-(int)radius;xx<=cx+radius;xx++){
      yy[xx-(int)cx+(int)radius]=(int)(cy-Math.sqrt(radius*radius-(xx-cx)*(xx-cx))-0.5);
    }
    for(int xx=(int)cx+(int)radius;xx>=cx-radius;xx--){
      yy[3*((int)radius)+1+(int)cx-xx]=(int)(cy+Math.sqrt(radius*radius-(xx-cx)*(xx-cx))+0.5);
    }
    */
    int r=(int)radius;
    int []yy=new int[(r*2+1)*2-2];
    int y;
    int c=(int)cy;
    for(int x=0;x<=r;x++){
      y=(int)(Math.sqrt(r*r-(r-x)*(r-x))+0.5);
      yy[x]=c-y;
      yy[2*r-x]=c-y;
      if(x!=0){
        yy[2*r+x]=c+y;
        yy[4*r-x]=c+y;
      }
    }
    return yy;
  }

  public static int[] getPointXFromOval(int cx,int radius){
    /*
    int []xx=new int[(radius*2+1)*2];
    for(int yy=cx-radius;yy<=cx+radius;yy++){
      xx[yy-cx+radius]=yy;
    }
    for(int yy=cx+radius;yy>=cx-radius;yy--){
      xx[3*radius+1+cx-yy]=yy;
    }
    */
    int r=(int)radius;
    int []xx=new int[(r*2+1)*2-2];
    for(int x=-1*r;x<=r;x++) xx[x+r]=x+cx;
    for(int x=1-r;x<=r-1;x++) xx[3*r+x]=cx-x;
    return xx;
  }

  public void createAllElement(int operatorMode,int threshMode,Rectangle ROI,
                               double sensitivity,boolean removeAllElement){
    // 准备各像素
    ImageProcessor ip2=this.dialogImage.getIP();
    int w=(int)ROI.getWidth();
    int h=(int)ROI.getHeight();
    byte []pixels=new byte[w*h];
    int n=0;
    for(int ii=ROI.getLocation() .y;ii<=ROI.getLocation().y+h-1;ii++){
      for(int jj=ROI.getLocation() .x;jj<=w+ROI.getLocation().x-1;jj++){
        pixels[n]=this.dialogImage.getPixel8GrayBgAdjust_Byte(jj,ii);
        n++;
      }
    }

    // 初始化处理器
    AnaImage anaImage=new AnaImage(w,h,pixels);
    anaImage.setbinary((byte[])anaImage.getPixels());
    int thresh=(int) (sensitivity*anaImage.calThreshold(threshMode));

    // 找边缘
    if(operatorMode==AnaImage.edgeNone){
      anaImage.binary((byte[])anaImage.getPixels(),anaImage.getwidth(),
                      anaImage.getheight(),thresh);
      anaImage.setbinary((byte[])anaImage.getPixels());
    }
    else if(operatorMode==AnaImage.edgeSobel){
      anaImage.edgeSobel(thresh,false,null);
    }
    else if(operatorMode==AnaImage.edgePrewitt ){
      anaImage.edgePrewitt(thresh,false,null);
    }
    else if(operatorMode==AnaImage.edgeRobert ){
      anaImage.edgeRobert(thresh,false,null);
    }
    else if(operatorMode==AnaImage.edgeLap ){
      anaImage.edgeLaplacian(thresh,false,null);
    }
    else if(operatorMode==AnaImage.edgeGuassLap ){
      anaImage.edgeGuassLaplacian(thresh,false,null);
    }

    //20031011
    //if(bgBlack==false) anaImage.invert();
    anaImage.signInPoint();
    anaImage.goodBoundary();

    Vector vv=anaImage.searchBoundary();
    if(vv==null) return;
    int size=vv.size() ;
    Graphics g;
    g=this.dialogImage .getImageGraphics() ;
    g.setColor(Color.red);
    Polygon pg;
    InfoCell cell;
    if(removeAllElement==true) this.initLayer();
    for(int iii=0;iii<size;iii++){
      pg=(Polygon)vv.elementAt(iii);
      cell=new InfoCell(pg,iii,this.dialogImage);
      if(cell.m_area <=0) continue;
      cell.cellNo =iii+1;
      cell.ID =this.ID;
      // 重要性质！！！
      cell.adjustCoordinate(ROI.getLocation(),true);
      this.addElement(cell,true,"Cell",iii+1,-1);
      this.ID++;
    }
    if(removeAllElement==true) this.dialogImage .paintImage() ;
    // for debug only
    // anaImage.oneTo255((byte[])anaImage.getbinary()) ;
    // anaImage.drawArray((byte[])anaImage.getbinary(),this.dialogImage) ;
    // if(true) return;
  }

  public  void createAllElementFor1D(Vector lines,int threshMode,double sensitivity) {
    int num=lines.size()/7;
    ImageProcessor ip2=this.dialogImage .getIP();
    Rectangle ROI;
    int w,h,n;
    this.initLayer();
    int laneNo,lineNo;
    int No=0;

    for(int lll=1;lll<=num;lll++){
      Point pt1=new Point(0,0),pt2=new Point(0,0);
      Point pt3=new Point(0,0),pt4=new Point(0,0),pt5=new Point(0,0);
      // 准备各像素
      pt1.x=((Point)lines.elementAt(lll*7-7)).x;
      pt2.x=((Point)lines.elementAt(lll*7-6)).x;
      pt3.x=((Point)lines.elementAt(lll*7-5)).x;
      pt4.x=((Point)lines.elementAt(lll*7-4)).x;
      pt5.x=((Point)lines.elementAt(lll*7-3)).x;

      pt1.y=((Point)lines.elementAt(lll*7-7)).y;
      pt2.y=((Point)lines.elementAt(lll*7-6)).y;
      pt3.y=((Point)lines.elementAt(lll*7-5)).y;
      pt4.y=((Point)lines.elementAt(lll*7-4)).y;
      pt5.y=((Point)lines.elementAt(lll*7-3)).y;

      laneNo=((Integer)lines.elementAt(lll*7-2)).intValue();
      lineNo=((Integer)lines.elementAt(lll*7-1)).intValue();

      /*
      System.out.println("No:"+lll+","+"左上:["+pt1.x+","+pt1.y+"],"+
                  "右下:["+pt2.x+","+pt2.y+"],"+"左界:["+pt3.x+","+pt3.y+"],"+
                  "右界:["+pt4.x+","+pt4.y+"],"+"左值:"+pt5.x+",右值"+pt5.y);
      */

      // 条带的范围 pt1,pt2
      // 条带的左右边界点 pt3,pt4
      // 泳道的左右边界限制点，相对坐标 pt5.x,pt5.y

      ROI=new Rectangle(pt1.x,pt1.y,pt2.x-pt1.x+1,pt2.y-pt1.y+1);
      w=(int)ROI.getWidth();
      h=(int)ROI.getHeight();
      if(w<=0 || h<=0) continue;
      byte []pixels=new byte[w*h];
      n=0;
      for(int ii=ROI.getLocation().y;ii<=ROI.getLocation().y+h-1;ii++){
        for(int jj=ROI.getLocation().x;jj<=ROI.getLocation().x+w-1;jj++){
          pixels[n]=this.dialogImage.getPixel8GrayBgAdjust_Byte(jj,ii);
          n++;
        }
      }

      // 初始化处理器
      AnaImage anaImage=new AnaImage(w,h,pixels);
      anaImage.setbinary((byte[])anaImage.getPixels());
      int thresh=(int) (sensitivity*anaImage.calThreshold(threshMode));

      // 找边缘
      int y0=pt3.y-pt1.y;
      anaImage.binaryFor1D((byte[])anaImage.getPixels(),
                           anaImage.getwidth(),anaImage.getheight(),
                           thresh,y0,pt5.x,pt5.y);
      anaImage.signInPoint();
      anaImage.goodBoundary();
      anaImage.deleteNotEdge();

      Vector vv=anaImage.searchBoundary();
      if(vv==null) continue;
      int size=vv.size() ;
      Polygon pg,pgAdjust;
      InfoCell cell;
      int xx,yy;

      for(int iii=0;iii<size;iii++){
        pg=(Polygon)vv.elementAt(iii);
        pgAdjust=new Polygon();
        for(int vvv=1;vvv<=pg.npoints;vvv++){
          xx=pg.xpoints[vvv-1]+pt1.x;
          yy=pg.ypoints[vvv-1]+pt1.y;
          pgAdjust.addPoint(xx,yy);
        }
        cell=new InfoCell(pgAdjust,No+1,this.dialogImage);
        if(cell.m_area <=0) continue;
        No++;
        cell.cellNo=No;
        cell.laneNo=laneNo;
        cell.lineNo=lineNo;
        cell.ID =this.ID ;
        // 重要性质！！！
        cell.adjustCoordinate(this.dialogImage.getROI().getLocation(),false);
        this.addElement(cell,true,"Cell",iii+1,-1);
        this.ID++;
      }

      // only for debug
      // anaImage.oneTo255((byte[])anaImage.getbinary()) ;
      // anaImage.drawArray((byte[])anaImage.getbinary(),this.dialogImage) ;
      // if(true) return;
    }
  }

  public InfoCell getInfoCell(Point pt){
    InfoCell cell;
    int size=this.current .size() ;
    for(int ii=1;ii<=size;ii++){
      cell=(InfoCell)this.current.elementAt(ii-1);
      if(cell.m_polygon .contains(pt.x,pt.y)==true) return cell;
    }
    return null;
  }

  public InfoCell getInfoCell(int index){
    InfoCell cell;
    int size=this.current .size() ;
    for(int ii=1;ii<=size;ii++){
      cell=(InfoCell)this.current.elementAt(ii-1);
      if(cell.cellNo ==index) return cell;
    }
    return null;
  }

  /*
  private void drawArrayOuterMethod(Graphics2D g2d){
    Rectangle rect=this.dialogImage.getROI();
    int locx=rect.getLocation().x;
    int locy=rect.getLocation().y;
    int detx=(int)(rect.getWidth()/this.hnum+0.5);
    int dety=(int)(rect.getHeight()/this.vnum+0.5);
    int cx,cy;
    g2d.setColor(Color.red);
    for(int ii=1;ii<=this.vnum-1;ii++){
      g2d.drawLine(locx,locy+ii*dety,(int)(locx+rect.getWidth()),locy+ii*dety);
    }
    for(int ii=1;ii<=this.hnum-1;ii++){
      g2d.drawLine(locx+ii*detx,locy,locx+ii*detx,(int)(locy+rect.getHeight()));
    }
    for(int ii=1;ii<=this.hnum;ii++){
      cx=(int)(locx+(ii-1)*detx+detx/2+0.5)-this.radius;
      for(int jj=1;jj<=this.vnum;jj++){
        cy=(int)(locy+(jj-1)*dety+dety/2+0.5)-this.radius;
        g2d.drawOval(cx,cy,this.radius*2,this.radius*2);
      }
    }
  }
  */

  private void drawArray(boolean isInnerMethod,Graphics2D g2d,Point oriPoint){
    Rectangle rect=this.dialogImage.getROI();
    int locx=rect.getLocation().x+oriPoint.x;
    int locy=rect.getLocation().y+oriPoint.y;
    int detx=(int)(rect.getWidth()/this.hnum+0.5);
    int dety=(int)(rect.getHeight()/this.vnum+0.5);
    int cx,cy;
    g2d.setColor(Color.red);
    for(int ii=1;ii<=this.vnum-1;ii++){
      g2d.drawLine(locx,locy+ii*dety,(int)(locx+rect.getWidth()),locy+ii*dety);
    }
    for(int ii=1;ii<=this.hnum-1;ii++){
      g2d.drawLine(locx+ii*detx,locy,locx+ii*detx,(int)(locy+rect.getHeight()));
    }
    if(isInnerMethod==true){
      locx+=locxx;
      locy+=locyy;
      detx+=cellw;
      dety+=cellh;
    }
    for(int ii=1;ii<=this.hnum;ii++){
      cx=(int)(locx+(ii-1)*detx+detx/2+0.5)-this.radius;
      for(int jj=1;jj<=this.vnum;jj++){
        cy=(int)(locy+(jj-1)*dety+dety/2+0.5)-this.radius;
        g2d.drawOval(cx,cy,this.radius*2,this.radius*2);
      }
    }
  }

  public static Polygon extendPolygon(Polygon pg,int extend){
    Polygon pg2=new Polygon();
    int xx,yy;
    for(int ii=1;ii<=pg.npoints;ii++){
      xx=pg.xpoints[ii-1];
      yy=pg.ypoints[ii-1];
      if(pg.contains(xx,yy-extend)==false){
        pg2.addPoint(xx,yy-extend);
      }
      else if(pg.contains(xx,yy+extend)==false){
        pg2.addPoint(xx,yy+extend);
      }
      else if(pg.contains(xx-extend,yy)==false){
        pg2.addPoint(xx-extend,yy);
      }
      else if(pg.contains(xx+extend,yy)==false){
        pg2.addPoint(xx+extend,yy);
      }
    }
    return pg2;
  }

  public void draw(Graphics g,Point oriPoint){
    InfoCell acell;
    int []xx;
    int []yy;
    int polygonPoints;
    int []xxx;
    int []yyy;
    int maxPx=-10000,maxPy=-10000;

    Graphics2D g2d=(Graphics2D)g;

    // draw array range
    if(this.isArray==true){
      this.drawArray(this.isInnerMethod,g2d,oriPoint);
    }

    Stroke oldStroke=g2d.getStroke();
    BasicStroke dotStroke;
    int cap=BasicStroke.CAP_SQUARE ;
    int join=BasicStroke.JOIN_BEVEL;
    float miterlimit=10.0f;
    float dash_phase=0;
    float []dash={2,2,2,2};
    dotStroke= new BasicStroke(1, cap, join, miterlimit, dash,  dash_phase);

    for(int ii=0;ii<current.size();ii++){
      maxPx=-10000;
      maxPy=-10000;
      acell=(InfoCell)current.elementAt(ii);
      Polygon polygon=acell.m_polygon;
      polygonPoints=polygon.npoints;
      xx=acell.m_polygon.xpoints ;
      yy=acell.m_polygon.ypoints ;
      xxx=new int[polygonPoints];
      yyy=new int[polygonPoints];
      for(int iii=1;iii<=polygonPoints;iii++){
        xxx[iii-1]=xx[iii-1]+oriPoint.x;
        yyy[iii-1]=yy[iii-1]+oriPoint.y;
       if(xxx[iii-1]>=maxPx && yyy[iii-1]>=maxPy){
          maxPx=xxx[iii-1];
          maxPy=yyy[iii-1];
        }
      }
      if(acell.visible ==true){
        //g2d.setStroke(dotStroke);
        g.setColor(acell.edgeColor);
        g.drawPolygon(xxx,yyy,polygonPoints);
        g2d.setStroke(oldStroke);
        if(acell.isSelected==true){
          Color c=acell.edgeColor;
          g2d.setColor(new Color(255-c.getRed(),255-c.getGreen(),255-c.getBlue()));
          Polygon pg=this.extendPolygon(new Polygon(xxx,yyy,polygonPoints),1);
          g2d.drawPolygon(pg);
          //pg=this.extendPolygon(pg,1);
          //g2d.drawPolygon(pg);
          /*
          g2d.setColor(Color.white);
          pg=this.extendPolygon(pg,1);
          g2d.drawPolygon(pg);
          */
        }
      }
      if(acell.isVisibleGeometry ==true){
        g2d.setColor(acell.geometryColor);
        g2d.drawOval((int)acell.m_centrex+oriPoint.x-2,(int)acell.m_centrey+oriPoint.y-2,4,4);
      }
      if(acell.isVisibleGravity ==true){
        g.setColor(acell.gravityColor);
        g.drawOval((int)acell.m_grayx +oriPoint.x-2,(int)acell.m_grayy +oriPoint.y-2,4,4);
      }
      if(acell.isVisibleNo ==true){
        if(acell.autoColor==false)  g.setColor(Color.red);
        else g.setColor(acell.edgeColor);
        Font font=new Font("Arial",Font.PLAIN,8);
        g.setFont(font);
        String text=String.valueOf(acell.cellNo );
        g.drawString(text,maxPx+3,maxPy+3);
      }
    }
  }

  public void save(ImageProcessor ipSave,Point oriPoint){
    InfoCell acell;
    int []xx;
    int []yy;
    int polygonPoints;
    int []xxx;
    int []yyy;
    int maxPx=-10000,maxPy=-10000;

    for(int ii=0;ii<current.size();ii++){
      maxPx=-10000;
      maxPy=-10000;
      acell=(InfoCell)current.elementAt(ii);
      Polygon polygon=acell.m_polygon;
      polygonPoints=polygon.npoints;
      xx=acell.m_polygon.xpoints ;
      yy=acell.m_polygon.ypoints ;
      xxx=new int[polygonPoints];
      yyy=new int[polygonPoints];
      for(int iii=1;iii<=polygonPoints;iii++){
        xxx[iii-1]=xx[iii-1]+oriPoint.x;
        yyy[iii-1]=yy[iii-1]+oriPoint.y;
       if(xxx[iii-1]>=maxPx && yyy[iii-1]>=maxPy){
          maxPx=xxx[iii-1];
          maxPy=yyy[iii-1];
        }
      }

      ipSave.setLineWidth(1);
//      ipSave.setDotsMode(true,2,1);

      if(acell.visible ==true){
        ipSave.setColor(acell.edgeColor);
        for(int jj=0;jj<xxx.length-1;jj++)
          ipSave.drawLine(xxx[jj],yyy[jj],xxx[jj+1],yyy[jj+1]);
        ipSave.drawLine(xxx[0],yyy[0],xxx[xxx.length-1],yyy[xxx.length-1]);
      }

    //  ipSave.setDotsMode(false,0,0);
      if(acell.isVisibleGeometry ==true){
        ipSave.setColor(acell.geometryColor);
        ipSave.drawRect((int)acell.m_centrex+oriPoint.x-1,(int)acell.m_centrey-1+oriPoint.y,3,3);
      }
      if(acell.isVisibleGravity ==true){
        ipSave.setColor(acell.gravityColor);
        ipSave.drawRect((int)acell.m_grayx +oriPoint.x-1,(int)acell.m_grayy-1 +oriPoint.y,3,3);
      }

      if(acell.isVisibleNo ==true){
        ipSave.setColor(Color.pink );
        Font font=new Font("Arial",Font.PLAIN,8);
        ipSave.setFont(font);
        String text=String.valueOf(acell.cellNo );
        ipSave.drawString(text,maxPx+3,maxPy+3);
      }
    }
  }


  public void writeExternal(ObjectOutput out) throws IOException {
    /**@todo Implement this java.io.Externalizable method*/
    try{
      InfoCell ic=new InfoCell();
      int cellNum;
      cellNum=this.current.size();
      out.writeInt(cellNum);
      for(int iii=1;iii<=cellNum;iii++){
        ic=(InfoCell)this.current.elementAt(iii-1);
        out.writeObject(ic);
      }
    }
    catch(IOException e){
      throw new java.lang.UnsupportedOperationException("Method writeExternal() not yet implemented.");
    }
  }

  public LayerCell getLayerCell(){
    return this;
  }

  public void setDialogImage(DialogImage di){
    this.dialogImage =di;
  }

  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    /**@todo Implement this java.io.Externalizable method*/
    try{
      // first init the layer
      this.initLayer();
      InfoCell ic=new InfoCell();
      int cellNum;
      cellNum=in.readInt() ;
      for(int iii=1;iii<=cellNum;iii++){
        ic=(InfoCell)in.readObject();
        ic.ID=this.ID;
        //this.addElement(ic,false,"",1,true);
        this.addElement(ic,false,"Cell",1);
        this.ID++;
      }
    }
    catch(IOException e){
      throw new java.lang.UnsupportedOperationException("Method readExternal() not yet implemented.");
    }
  }

  public void addElement(InfoCell cell,boolean setMainDone,
                         String layerSign,int step) {
//                         String layerSign,int step,boolean isIncID) {
    //if(isIncID==true) ID++;
    super.addElement(cell,setMainDone,layerSign,step,-1) ;
  }

  //===================== 以下代码为小范围识别目标、生成目标程序 ====================
  /**
   * 将图象ip2，背景为bgBlack，位声深为grayBits，范围为pg，扩展范围为extendRange转化为
   * 数组，pg之外为0，背景为白则反相
   * @param ip2
   * @param bgBlack 背景为黑=true,否则为false
   * @param grayBits =8/16
   * @param pg
   * @param extendRange >=0
   * @return
   */
  private byte[] convertTo8BitsGray(ImageProcessor ip2,boolean bgBlack,int grayBits,
                                    Polygon pg,int extendRange){
    // 1  2  3  4  5  6  7  8
    // .  .  .  .  .  .  .  .
    //       |  |  |  |
    //       x0
    //         width=3
    //                x0+3
    // extendRange=2
    // beginx=x0-2
    //                      endx=x0+3+2
    // data x len =(3+1)+2*2
    //             w     r
    Rectangle rect=pg.getBounds();
    int x0=rect.getLocation().x;
    int y0=rect.getLocation().y;
    int beginx=x0-extendRange;
    int endx=x0+(int)rect.getWidth()+extendRange;
    int beginy=y0-extendRange;
    int endy=y0+(int)rect.getHeight()+extendRange;
    byte []data=new byte[((int)rect.getWidth()+1+extendRange*2)*
                         ((int)rect.getHeight()+1+extendRange*2)];
    double gray;
    int grayInt;
    for(int ii=1;ii<=data.length;ii++) data[ii-1]=0;
    for(int xx=beginx;xx<=endx;xx++){
      for(int yy=beginy;yy<=endy;yy++){
        /*
        if(pg.contains(xx,yy)==true){
          gray=ip2.getPixelValue(xx,yy);
          if(grayBits==16) gray/=256;
          grayInt=(int)(gray+0.5);
          if(grayInt<0) grayInt=0;
          if(grayInt>255) grayInt=255;
          if(bgBlack==false) grayInt=255-grayInt;
          data[xx-beginx+(yy-beginy)*(endx-beginx+1)]=(byte)(grayInt&0xFF);
        }
        */
        if(InfoCell.isIn(pg,xx,yy)==true){
          data[xx-beginx+(yy-beginy)*(endx-beginx+1)]=
              dialogImage.getPixel8GrayBgAdjust_Byte(xx,yy);
        }
      }
    }
    return data;
  }

  /**
   * 将图象ip2，背景为bgBlack，位声深为grayBits，范围为rt，扩展范围为extendRange转化为
   * 数组，pg之外为0，背景为白则反相
   * @param ip2
   * @param bgBlack  背景为黑=true,否则为false
   * @param grayBits =8/16
   * @param rt
   * @param extendRange >=0
   * @return
   */
  private byte[] convertTo8BitsGray(ImageProcessor ip2,boolean bgBlack,int grayBits,
                                    Rectangle rt,int extendRange){
    Rectangle rect=rt;
    int x0=rect.getLocation().x;
    int y0=rect.getLocation().y;
    int beginx=x0-extendRange;
    int endx=x0+(int)rect.getWidth()+extendRange;
    int beginy=y0-extendRange;
    int endy=y0+(int)rect.getHeight()+extendRange;
    byte []data=new byte[((int)rect.getWidth()+1+extendRange*2)*
                         ((int)rect.getHeight()+1+extendRange*2)];
    double gray;
    int grayInt;
    for(int ii=1;ii<=data.length;ii++) data[ii-1]=0;
    for(int xx=beginx;xx<=endx;xx++){
      for(int yy=beginy;yy<=endy;yy++){
        if(rt.contains(xx,yy)==true){
          gray=ip2.getPixelValue(xx,yy);
          if(grayBits==16) gray/=256;
          grayInt=(int)(gray+0.5);
          if(grayInt<0) grayInt=0;
          if(grayInt>255) grayInt=255;
          if(bgBlack==false) grayInt=255-grayInt;
          data[xx-beginx+(yy-beginy)*(endx-beginx+1)]=(byte)(grayInt&0xFF);
        }
      }
    }
    return data;
  }

  /**
   *
   * @param pgOrigin
   * @param extendRange
   * @param ip2
   * @param operatorMode
   * @param threshMode
   * @param sensitivity
   * @param bgBlack
   * @param grayBits
   * @param cellBeginNo
   * @param isCheck1D 是否对1D进行检查
   */
  public void createLayerCellObjects(Polygon pgOrigin,int extendRange,ImageProcessor ip2,
                                     int operatorMode, int threshMode,double sensitivity,
                                     boolean bgBlack,int grayBits,int cellBeginNo,boolean isCheck1D){
    Vector cells=this.createCells(pgOrigin,extendRange,ip2,operatorMode,threshMode,
                                  sensitivity,bgBlack,grayBits,cellBeginNo);
    int size=cells.size();
    InfoCell cell;
    boolean ok=true;
    for(int ii=1;ii<=size;ii++){
      cell=(InfoCell)cells.elementAt(ii-1);
      if(isCheck1D==true){
        if(this.dialogImage.setLaneLineForCell(cell)==false) ok=false;
      }
      if(ok==true) this.addElement(cell,true,"Cell",ii);
    }
    this.adjustNo();
  }

  public Vector createCells(Polygon pgOrigin,int extendRange,ImageProcessor ip2,
                            int operatorMode, int threshMode,
                            double sensitivity, boolean bgBlack,int grayBits,
                            int cellBeginNo){
    // 准备各像素
    Rectangle rect=pgOrigin.getBounds();
    int w=(int)rect.getWidth()+2*extendRange+1;
    int h=(int)rect.getHeight()+2*extendRange+1;
    byte []pixels=this.convertTo8BitsGray(ip2,bgBlack,grayBits,pgOrigin,extendRange);
    // copy a copy, for future calculation
    byte []gray=new byte[pixels.length];
    for(int ii=1;ii<=gray.length;ii++) gray[ii-1]=pixels[ii-1];

    // 调整pgOrigin
    int x0=rect.getLocation().x;
    int y0=rect.getLocation().y;
    int xx[]=new int[pgOrigin.npoints];
    int yy[]=new int[pgOrigin.npoints];
    for(int ii=1;ii<=xx.length;ii++){
      xx[ii-1]=pgOrigin.xpoints[ii-1]-x0+extendRange;
      yy[ii-1]=pgOrigin.ypoints[ii-1]-y0+extendRange;
    }
    Polygon pgOriginToSmall=new Polygon(xx,yy,xx.length);

    // 初始化处理器
    Graphics g=this.dialogImage.getMyImageGraphics();
    AnaImage anaImage=new AnaImage(w,h,pixels);                           // set pixels
    anaImage.setbinary((byte[])anaImage.getPixels());                     // set binary,edgegray
    int thresh=(int)(sensitivity*anaImage.calThreshold(threshMode,pgOriginToSmall));   // accord edgegray

    // 找边缘
    if(operatorMode==AnaImage.edgeNone){
                                                                          // ch  pixels
      anaImage.binary((byte[])anaImage.getPixels(),anaImage.getwidth(),anaImage.getheight(),thresh);
      anaImage.setbinary((byte[])anaImage.getPixels());                   // set binary,edgegray
    }
    else if(operatorMode==AnaImage.edgeSobel){anaImage.edgeSobel(thresh,true,pgOriginToSmall);}
    else if(operatorMode==AnaImage.edgePrewitt){anaImage.edgePrewitt(thresh,true,pgOriginToSmall);}
    else if(operatorMode==AnaImage.edgeRobert){anaImage.edgeRobert(thresh,true,pgOriginToSmall);}
    else if(operatorMode==AnaImage.edgeLap){anaImage.edgeLaplacian(thresh,true,pgOriginToSmall);}
    else if(operatorMode==AnaImage.edgeGuassLap){anaImage.edgeGuassLaplacian(thresh,true,pgOriginToSmall);}

    // 需要优化的边界提取......
    anaImage.signInPoint();
    anaImage.goodBoundary();

    Vector edgeOriginToSmall=new Vector();
    edgeOriginToSmall=anaImage.searchBoundary();
    Vector edgeOrigin=new Vector();
    Polygon pgSmall,pgBig;
    g.setColor(Color.blue);
    for(int ii=1;ii<=edgeOriginToSmall.size();ii++){
      pgSmall=(Polygon)edgeOriginToSmall.elementAt(ii-1);
      xx=new int[pgSmall.npoints];
      yy=new int[pgSmall.npoints];
      for(int jj=1;jj<=xx.length;jj++){
        xx[jj-1]=pgSmall.xpoints[jj-1]+x0-extendRange;
        yy[jj-1]=pgSmall.ypoints[jj-1]+y0-extendRange;
      }
      pgBig=new Polygon(xx,yy,xx.length);
      edgeOrigin.addElement(pgBig);
    }

    // 生成LayerCell层元素
    Vector cells=new Vector();
    InfoCell cell;
    int size=edgeOrigin.size();
    Point pt;
    Polygon pgSmall2,pgOrigin2;
    for(int iii=1;iii<=size;iii++){
      pgSmall2=(Polygon)edgeOriginToSmall.elementAt(iii-1);
      pgOrigin2=(Polygon)edgeOrigin.elementAt(iii-1);

      cell=new InfoCell(pgOrigin2,0,this.dialogImage);
      cell.cellNo=cellBeginNo;
      cellBeginNo++;
      cell.ID=this.ID;
      this.ID++;

      cells.addElement(cell);

      /*
      cell=new InfoCell();

      cell.cellNo=cellBeginNo;
      cellBeginNo++;
      cell.ID=this.ID;
      this.ID++;

      cell.m_area=this.calArea(pgSmall2);
      cell.m_graySum=this.calGrayIntigral(pgSmall2,gray,w);
      if(cell.m_area<=0){
        cellBeginNo--;
        this.ID--;
        continue;
      }

      pt=this.calGeometryCenter(pgOrigin2,cell.m_area);
      cell.m_centrex=pt.x;
      cell.m_centrey=pt.y;
      if(cell.m_graySum>0){
        pt=this.calGrayCenter(pgSmall2,gray,w,x0,y0,cell.m_graySum);
        cell.m_grayx=pt.x-extendRange;
        cell.m_grayy=pt.y-extendRange;
      }
      else{
        cell.m_grayx=cell.m_centrex;
        cell.m_grayy=cell.m_centrey;
      }
      cell.m_circlement=this.calPerimeter(pgOrigin2);
      cell.m_polygon=pgOrigin2;

      cells.addElement(cell);
      */
    }

    return cells;
  }

  private int calArea(Polygon pg){
    int area=0;
    Rectangle rect=pg.getBounds();
    for(int ii=rect.getLocation().x-1;ii<=rect.getLocation().x+rect.getWidth()+1;ii++){
      for(int jj=rect.getLocation().y-1;jj<=rect.getLocation().y+rect.getHeight()+1;jj++){
        //if(pg.contains(ii,jj)==true) area++;
        if(InfoCell.isIn(pg,ii,jj)==true) area++;
      }
    }
    if(area==0 && pg.npoints!=0) area=pg.npoints;
    return area;
  }

  private double calPerimeter(Polygon pg){
    double perimeter=0;
    double x1,y1,x2,y2;
    for(int ii=1;ii<pg.npoints;ii++){
      x1=pg.xpoints[ii-1];
      y1=pg.ypoints[ii-1];
      x2=pg.xpoints[ii];
      y2=pg.ypoints[ii];
      perimeter+=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    }
    x1=pg.xpoints[pg.npoints-1];
    y1=pg.ypoints[pg.npoints-1];
    x2=pg.xpoints[0];
    y2=pg.ypoints[0];
    perimeter+=Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
    return perimeter;
  }

  private int calGrayIntigral(Polygon pg,byte []pixels,int width){
    int intigral=0;
    Rectangle rect=pg.getBounds();
    for(int ii=rect.getLocation().x;ii<=rect.getLocation().x+rect.getWidth();ii++){
      for(int jj=rect.getLocation().y;jj<=rect.getLocation().y+rect.getHeight();jj++){
        //if(pg.contains(ii,jj)==true) intigral+=pixels[ii+jj*width]&0xFF;
        if(InfoCell.isIn(pg,ii,jj)==true) intigral+=pixels[ii+jj*width]&0xFF;
      }
    }
    if(intigral==0 && pg.npoints!=0){
      for(int ii=1;ii<=pg.npoints;ii++){
        int x=pg.xpoints[ii-1];
        int y=pg.ypoints[ii-1];
        if(x<width && x>=0 && y>=0 && x+y*width<pixels.length){
          intigral+=pixels[x+y*width]&0xFF;
        }
      }
    }
    return intigral;
  }

  private Point calGeometryCenter(Polygon pg,double area){
    double cx=0;
    double cy=0;
    Rectangle rect=pg.getBounds();
    for(int ii=rect.getLocation().x;ii<=rect.getLocation().x+rect.getWidth();ii++){
      for(int jj=rect.getLocation().y;jj<=rect.getLocation().y+rect.getHeight();jj++){
        //if(pg.contains(ii,jj)==true){
        if(InfoCell.isIn(pg,ii,jj)==true){
          cx+=ii;
          cy+=jj;
        }
      }
    }
    if(cx==0 && cy==0 && pg.npoints!=0){
      for(int ii=1;ii<=pg.npoints;ii++){
        cx+=pg.xpoints[ii-1];
        cy+=pg.ypoints[ii-1];
      }
    }
    cx/=area;
    cy/=area;
    return new Point((int)(cx+0.5),(int)(cy+0.5));
  }

  private Point calGrayCenter(Polygon pg,byte[] pixels,int width,
                              int orix,int oriy,double grayIntegral){
    double cx=0;
    double cy=0;
    double gray;
    Rectangle rect=pg.getBounds();
    int x0=rect.getLocation().x+orix;
    int y0=rect.getLocation().y+oriy;
    for(int ii=rect.getLocation().x;ii<=rect.getLocation().x+rect.getWidth();ii++){
      for(int jj=rect.getLocation().y;jj<=rect.getLocation().y+rect.getHeight();jj++){
        //if(pg.contains(ii,jj)==true){
        if(InfoCell.isIn(pg,ii,jj)==true){
          gray=(pixels[ii+jj*width]&0xFF);
          cx+=((ii+x0)*gray);
          cy+=((jj+y0)*gray);
        }
      }
    }
    if(cx==0 && cy==0 && pg.npoints!=0){
      for(int ii=1;ii<=pg.npoints;ii++){
        int x=pg.xpoints[ii-1];
        int y=pg.ypoints[ii-1];
        if(x<width && x>=0 && y>=0 && x+y*width<pixels.length){
          gray=(pixels[x+y*width]&0xFF);
          cx+=((x+x0)*gray);
          cy+=((y+y0)*gray);
        }
      }
    }
    cx=cx/grayIntegral+0.5;
    cy=cy/grayIntegral+0.5;
    return new Point((int)cx,(int)cy);
  }

  public InfoCell createCell(Polygon pgOrigin,ImageProcessor ip2,
                             boolean bgBlack,int grayBits,int cellNo){
    // 准备各像素
    //System.out.println(pgOrigin);
    Rectangle rect=pgOrigin.getBounds();
    int w=(int)rect.getWidth()+1;
    int h=(int)rect.getHeight()+1;
    byte []gray=this.convertTo8BitsGray(ip2,bgBlack,grayBits,pgOrigin,0);

    // 调整pgOrigin
    int x0=rect.getLocation().x;
    int y0=rect.getLocation().y;
    int xx[]=new int[pgOrigin.npoints];
    int yy[]=new int[pgOrigin.npoints];
    for(int ii=1;ii<=xx.length;ii++){
      xx[ii-1]=pgOrigin.xpoints[ii-1]-x0;
      yy[ii-1]=pgOrigin.ypoints[ii-1]-y0;
    }
    Polygon pgSmall=new Polygon(xx,yy,xx.length);

    InfoCell cell=new InfoCell();
    cell.cellNo=cellNo;
    cell.ID=this.ID;
    this.ID++;

    //cell.m_area=this.calArea(pgSmall);
    cell.m_area=this.calArea(pgOrigin);
    cell.m_graySum=this.calGrayIntigral(pgSmall,gray,w);
    if(cell.m_area<=0){
      this.ID--;
      return null;
    }

    Point pt;
    pt=this.calGeometryCenter(pgOrigin,cell.m_area);
    cell.m_centrex=pt.x;
    cell.m_centrey=pt.y;
    if(cell.m_graySum>0){
      pt=this.calGrayCenter(pgSmall,gray,w,x0,y0,cell.m_graySum);
      cell.m_grayx=pt.x;
      cell.m_grayy=pt.y;
    }
    else{
      cell.m_grayx=cell.m_centrex;
      cell.m_grayy=cell.m_centrey;
    }
    cell.m_circlement=this.calPerimeter(pgOrigin);
    cell.m_polygon=pgOrigin;

    return cell;
  }

  /**
   * 目标细分
   * @param cell
   * @param isDelete
   * @param isRecoOnceAgain
   * @param isMinArea
   * @param minArea
   * @param isMaxArea
   * @param maxArea
   * @param isMinGray
   * @param minGray
   * @param isMaxGray
   * @param maxGray
   * @param isShape
   * @param shape
   * @param isDist
   * @param dist
   * @param layers 已递归运行的层数
   * @param maxLayers 递归运行的总层数
   * 初试状态：
   *        layers=1;
   *        maxLayers>=1;
   */
  public void detailObject(InfoCell cell,boolean isDelete,int layers,int maxLayers,
                           boolean isMinArea,int minArea,boolean isMaxArea,int maxArea,
                           boolean isMinGray,int minGray,boolean isMaxGray,int maxGray,
                           boolean isShape,double shape,boolean isDist,double dist,
                           ImageProcessor ip2,int operatorMode, int threshMode,
                           double sensitivity, boolean bgBlack,int grayBits){
    // 取得状态与返回
    if(layers>maxLayers) return;

    // added later 20031008
    if(cell==null) return;

    // 判断cell是否在规定的只值范围之间
    // 如果没有任何限制，则返回true
    // 如果cell.m_area==0，则返回false
    boolean isOK=this.isInRange(cell,isMinArea,minArea,isMaxArea,maxArea,
                                isMinGray,minGray,isMaxGray,maxGray,
                                isShape,shape,isDist,dist);

    // 符合标准则返回
    if(isOK==true) return;
    // 直接删除模式
    if(isDelete==true){
      this.removeElement(cell,true,"Cell",1,true);
      this.adjustNo();
      return;
    }

    //--------------------------------------------------------------------------
    // shape与dist只做为判断合法的依据，不做为是否细分的依据
    // area/gray作为是否细分的依据
    // 如果当前值有在标准之下（有一即可），则删除；否则，细分
    //--------------------------------------------------------------------------
    boolean isLess=this.isLessRange(cell,isMinArea,minArea,isMinGray,minGray);
    // 如果要“删除”，则删之
    if(isLess==true){
      this.removeElement(cell,true,"Cell",1,true);
      this.adjustNo();
      return;
    }

    // 否则，先“删除”，再细分
    // 但，事先要复制边界，否则，就找不到边界了......
    int xx[]=new int[cell.m_polygon.npoints];
    int yy[]=new int[xx.length];
    for(int ii=1;ii<=xx.length;ii++){
      xx[ii-1]=cell.m_polygon.xpoints[ii-1];
      yy[ii-1]=cell.m_polygon.ypoints[ii-1];
    }
    Polygon pg=new Polygon(xx,yy,xx.length);
    this.removeElement(cell,true,"Cell",1,true);
    this.adjustNo();

    // 自动识别一次
    Vector cells=new Vector();
    cells=this.createCells(pg,0,ip2,operatorMode,threshMode,
                           sensitivity,bgBlack,grayBits,0);
    if(cells==null) return;
    InfoCell cell2;
    for(int ii=1;ii<=cells.size();ii++){
      cell2=(InfoCell)cells.elementAt(ii-1);
      cell2.isDetailed=true;                  // 没啥意思？，No,for 限制对象之用
      this.addElement(cell2,true,"Cell",ii);
    }
    this.adjustNo();

    // 递归细分吧......
    layers++;
    for(int ii=1;ii<=cells.size();ii++){
      cell2=(InfoCell)cells.elementAt(ii-1);
      if(cell2==null) continue;
      // 如果生成的对象同原对象，则不细分了
      if(cell2.m_polygon.equals(pg)==false){
        detailObject(cell2,isDelete,layers,maxLayers,isMinArea,minArea,
                     isMaxArea,maxArea,isMinGray,minGray,isMaxGray,maxGray,
                     isShape,shape,isDist,dist,ip2,operatorMode,threshMode,
                     sensitivity,bgBlack,grayBits);
      }
    }
  }

  private boolean isInRange(InfoCell cell,boolean isMinArea,int minArea,boolean isMaxArea,int maxArea,
                           boolean isMinGray,int minGray,boolean isMaxGray,int maxGray,
                           boolean isShape,double shape,boolean isDist,double dist){
    double pi=3.1415926536;
    boolean in=true;

    if(isMinArea==true) {if(cell.m_area<minArea) in=false;}
    if(in==true){if(isMaxArea==true) {if(cell.m_area>maxArea) in=false;}}
    if(in==true){if(isMinGray==true) {if(cell.m_area<minGray) in=false;}}
    if(in==true){if(isMaxGray==true) {if(cell.m_area>maxGray) in=false;}}
    if(in==true && isShape==true){
      if(cell.m_area==0) in=false;
      else{
        double sp=(cell.m_circlement*cell.m_circlement)/(4*pi*cell.m_area);
        if(sp>shape) in=false;
      }
    }
    if(in==true && isDist==true){
      double dt=Math.sqrt((cell.m_centrex-cell.m_grayx)*(cell.m_centrex-cell.m_grayx)+
                          (cell.m_centrey-cell.m_grayy)*(cell.m_centrey-cell.m_grayy));
      if(dt>dist) in=false;
    }
    return in;
  }

  private boolean isLessRange(InfoCell cell,boolean isMinArea,int minArea,
                              boolean isMinGray,int minGray){
    boolean less=false;
    if(isMinArea==true) {if(cell.m_area<minArea) less=true;}
    if(isMinGray==true) {if(cell.m_area<minGray) less=true;}
    return less;
  }

  // end of the class
}

















