package easygel.layer;

import java.awt.*;
import ij.*;
import easygel.image.*;
import easygel.layer.*;
import java.io.*;
import ij.process.*;
import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.IOException;
import java.io.ObjectInput;
import easygel.*;

/**
 *
 * <p>Title: </p>
 * <p>Description: 总体设计 </p>
 * <p>1、用InfoCell将其实例化</p>
 * <p>---其中实现对public量的设置及其相应的计算</p>
 * <p>---calgeom()函数什么都不做，只是与刘勇相接口</p>
 * <p>2、实例化后应该再调整坐标adjustCoordinate</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class  InfoCell extends Information  implements Externalizable {

  public Polygon m_polygon;
  public int  m_area;
  public double m_circlement;
  public double m_centrex;
  public double m_centrey;
  public double m_grayx;
  public double m_grayy;
  public long m_graySum;

  // 对应的泳道和条带
  public int laneNo;
  public int lineNo;

  // 是否自动配色
  public boolean autoColor;

  //added by wxs 2003/06/01
  //已有可见指的是边界
  public int cellNo;
  public Color  edgeColor;
  public boolean isVisibleNo;
  public boolean isVisibleGeometry;
  public Color geometryColor;
  public boolean isVisibleGravity;
  public Color gravityColor;

  public boolean isDetailed;

  public InfoCell(){
    super();
    visible =true;
    edgeColor=Color.green;
    isVisibleNo=false;
    isVisibleGeometry=false;
    geometryColor=Color.blue;
    isVisibleGravity=false;
    gravityColor=Color.magenta;
    autoColor=false;
    this.isSelected=false;
  }

  public InfoCell duplicate(){
    InfoCell ic=new InfoCell();
    ic.autoColor=this.autoColor;
    ic.cellNo=this.cellNo;
    ic.edgeColor=this.cloneColor(this.edgeColor);
    ic.geometryColor=this.cloneColor(this.edgeColor);
    ic.gravityColor=this.cloneColor(this.geometryColor);
    ic.ID=this.ID;
    ic.isSelected=this.isSelected;
    ic.isVisibleGeometry=this.isVisibleGeometry;
    ic.isVisibleGravity=this.isVisibleGravity;
    ic.isVisibleNo=this.isVisibleNo;
    ic.m_area=this.m_area;
    ic.m_centrex=this.m_centrex;
    ic.m_centrey=this.m_centrey;
    ic.m_circlement=this.m_circlement;
    ic.m_graySum=this.m_graySum;
    ic.m_grayx=this.m_grayx;
    ic.m_grayy=this.m_grayy;
    if(this.m_polygon==null) ic.m_polygon=null;
    else{
      int size=this.m_polygon.npoints;
      int px[]=new int[size];
      int py[]=new int[size];
      for(int ii=1;ii<=size;ii++){
        px[ii-1]=this.m_polygon.xpoints[ii-1];
        py[ii-1]=this.m_polygon.ypoints[ii-1];
      }
      ic.m_polygon=new Polygon(px,py,size);
    }
    ic.visible=this.visible;
    return ic;
  }

  /**
   *
   * @param polygon （0,0）点开始的值
   * @param no
   * @param di
   */
  public InfoCell(Polygon polygon,int no,DialogImage di){
    super(no);

    // added by wxs 2003/06/01
    visible =true;
    edgeColor=Color.green;
    isVisibleNo=false;
    isVisibleGeometry=false;
    geometryColor=Color.blue;
    isVisibleGravity=false;
    gravityColor=Color.magenta;
    this.isSelected=false;

    //
    m_polygon=polygon;

    // 计算各参数
    Rectangle rect;
    rect=m_polygon.getBounds();
    int rectX=rect.getLocation().x;
    int rectY=rect.getLocation().y;
    int rectW=(int)rect.getWidth()+1;
    int rectH=(int)rect.getHeight()+1;

    m_area=0;
    m_centrex=0;
    m_centrey=0;
    m_grayx=0;
    m_grayy=0;
    m_graySum=0;

    this.autoColor=false;

    double gray;
    for(int xx=rectX;xx<=rectX+rectW-1;xx++){
      for(int yy=rectY;yy<=rectY+rectH-1;yy++){
        //if(m_polygon.contains(xx,yy)==true){
        if(isIn(m_polygon,xx,yy)==true){
          gray=di.getPixel8GrayBgAdjust(xx,yy);
          m_area++;
          m_centrex+=(double)xx;
          m_centrey+=(double)yy;
          m_grayx+=xx*gray;
          m_grayy+=yy*gray;
          m_graySum+=gray;
        }
      }
    }

    m_centrex/=m_area;
    m_centrey/=m_area;
    if(m_graySum==0){
      m_grayx=m_centrex;
      m_grayy=m_centrey;
    }
    else{
      m_grayx/=m_graySum;
      m_grayy/=m_graySum;
    }

    m_circlement=0;
    int xx1,xx2,yy1,yy2;
    for(int ii=1;ii<=m_polygon.npoints -1;ii++){
      xx1=m_polygon.xpoints [ii-1];
      yy1=m_polygon.ypoints [ii-1];
      xx2=m_polygon.xpoints [ii];
      yy2=m_polygon.ypoints [ii];
      m_circlement+=Math.sqrt((xx1-xx2)*(xx1-xx2)+(yy1-yy2)*(yy1-yy2));
    }
    xx1=m_polygon.xpoints [0];
    yy1=m_polygon.ypoints [0];
    xx2=m_polygon.xpoints [m_polygon.npoints -1];
    yy2=m_polygon.ypoints [m_polygon.npoints -1];
    m_circlement+=Math.sqrt((xx1-xx2)*(xx1-xx2)+(yy1-yy2)*(yy1-yy2));
  }

  public void calgeom(){
  }

  public static boolean isIn(Polygon pg,int x,int y){
    boolean in=pg.contains(x,y);
    if(in==false){
      int xx[]=pg.xpoints;
      int yy[]=pg.ypoints;
      for(int ii=1;ii<=xx.length;ii++){
        if(xx[ii-1]==x && yy[ii-1]==y){
          return true;
        }
      }
    }
    return in;
  }

  //* 根据起始点调整内部点的坐标
  //* added by wxs 2003/06/01
  //* @param oriPoint: 起始点坐标
  public void adjustCoordinate(Point oriPoint,boolean adjustPolygon){
    m_centrex +=oriPoint.x;
    m_centrey +=oriPoint.y;
    m_grayx +=oriPoint.x;
    m_grayy +=oriPoint.y;
    if(adjustPolygon==true){
      Polygon polygon=m_polygon;
      int polygonPoints=polygon.npoints;
      int []xx=m_polygon.xpoints ;
      int []yy=m_polygon.ypoints ;
      for(int ii=1;ii<=polygonPoints;ii++){
        xx[ii-1]+=oriPoint.x;
        yy[ii-1]+=oriPoint.y;
      }
      m_polygon=new Polygon(xx,yy,polygonPoints);
    }
  }

  //this function fills the polygon contour
  //notice:this polygon SHOULD be pixels ..
  public static CellFillReturn fillCell(Polygon pg,short bgnd){
    if(pg==null)  return null;
    int []xpts=pg.xpoints;
    int []ypts=pg.ypoints;
    if( (xpts==null) ||(ypts==null) )
    {
      return null;
    }
    Point topleft=new Point(xpts[0],ypts[0]);
    Point btmright=new Point(topleft);
    //get the minimum rectangle containing the contour.
    for(int i=0;i<pg.npoints;i++)
    {
      topleft.x=(xpts[i]<topleft.x)?xpts[i]:topleft.x;
      topleft.y=(ypts[i]<topleft.y)?ypts[i]:topleft.y;
      btmright.x=(xpts[i]>btmright.x)?xpts[i]:btmright.x;
      btmright.y=(ypts[i]>btmright.y)?ypts[i]:btmright.y;
    }
    int nwide=btmright.x-topleft.x+3;//
    int nhigh=btmright.y-topleft.y+3;//
    short [][]mask=new short[nhigh][nwide];
    for(int y=0;y<nhigh;y++)
      for(int x=0;x<nwide;x++)
      {
        mask[y][x]=0;
      }
    for(int i=0;i<pg.npoints;i++)
    {
      int x=xpts[i]-topleft.x+1;
      int y=ypts[i]-topleft.y+1;
      mask[y][x]=-1;
    }

    //begin to fill
    int total=pixelfill(mask,nwide,nhigh,(short)-1,(short)bgnd);
    short[][]mask2=new short[nhigh-2][nwide-2];
    for(int y=0;y<nhigh-2;y++)
      for(int x=0;x<nwide-2;x++)
         mask2[y][x]=mask[y+1][x+1];

    //erase the contour.
    for(int i=0;i<pg.npoints;i++)
    {
      int x=xpts[i]-topleft.x;
      int y=ypts[i]-topleft.y;
       mask2[y][x]=0;
    }
    dumpbin(mask2,nwide-2,nhigh-2,"tet.txt");
    return new CellFillReturn(mask2,new Rectangle(topleft.x,topleft.y,nwide-2,nhigh-2),total);
  }

  //mask is the image to be filled. cntrlval is the pixel value of the contour.
  //NOTICE: the image's border is discarded.
  //bgval is the pixel value of the background.
  //fillval is the value used to fill the image.
  //return value is the filled pixels number
  //-1 means error occured during the fill operation due to the input parameters.
  public static int pixelfill(short[][]mask,int nwide,int nhigh,short cntrval, short fillval){
    if( (mask==null) )
    {
      return -1;
    }
    dumpbin((Object)mask,nwide,nhigh,"test3.txt");
    int nfilled=0;
    int ncount=0;
    int x=1;
    //NOTICE:here scaning discards the image border
    for(int y=1;y<nhigh-1;y++)
    {
      ncount=0;
      x=1;
      while(x<nwide-1)
      {
        if(mask[y][x]!=cntrval)
        {
          if(ncount%2==1)
          {
            mask[y][x]=fillval;
            nfilled++;
          }
          x++;
        }
        else
        {
          Point pt=new Point();
          x=link(pt,mask,x,y,cntrval);
         if( (pt.x==1)&&(pt.y==1))
           ncount++;
         if( ( (pt.x+pt.y)!=0 ) && ( (pt.x+pt.y)!=2 ) )
           return -1;
        }
      }
    }
    dumpbin(mask,nwide,nhigh,"test.txt");
    return nfilled;
  }

  //return value means the next pixel to scan.
  //lag.x is the lower lagvalue;
  //lag.y is the upper lagvalue;
  public static int link(Point lag,short[][]mask,int x,int y,short cntrval){
    if(mask==null)
      return -1;
    int lower=0,upper=0;
    if(mask[y+1][x-1]==cntrval)
      upper++;
    if(mask[y-1][x-1]==cntrval)
      lower++;
    while(mask[y][x]==cntrval)
    {
      if( (mask[y+1][x]==cntrval) && (mask[y+1][x-1]!=cntrval) )
        upper++;
      if( (mask[y-1][x]==cntrval) && (mask[y-1][x-1]!=cntrval) )
        lower++;
      x++;
    }
    if( (mask[y+1][x-1]!=cntrval) && (mask[y+1][x]==cntrval) )
      upper++;
    if( (mask[y-1][x-1]!=cntrval) && (mask[y-1][x]==cntrval) )
      lower++;
    if(lag!=null)
    {
      lag.x=lower;
      lag.y=upper;
    }
    return x;
  }

  public static void dumpbin(Object x,int nwide,int nhight,String filename){
    short[][]y=(short[][])x;
    try
   {
     FileOutputStream sout=new FileOutputStream(filename);
     PrintStream ps=new PrintStream(sout);
     for(int i=0;i<nhight;i++)
     {
       ps.print('\n');
       for(int j=0;j<nwide;j++)
         ps.print(y[i][j]&0xff);
     }
     ps.close();
   }
   catch(java.io.IOException e)
   {
     System.out.print(e.toString());
   }
  }

  public void writeExternal(ObjectOutput out) throws IOException {
    /**@todo Implement this java.io.Externalizable method*/
    try{
      out.writeBoolean(visible);
      // isSelected=false;
      out.writeObject(m_polygon);
      out.writeInt(m_area);
      out.writeDouble(m_circlement);
      out.writeDouble(m_centrex);
      out.writeDouble(m_centrey);
      out.writeDouble(m_grayx);
      out.writeDouble(m_grayy) ;
      out.writeLong(m_graySum);
      out.writeInt(cellNo);
      out.writeObject(edgeColor);
      out.writeBoolean(isVisibleNo);
      out.writeBoolean(isVisibleGeometry);
      out.writeObject(geometryColor);
      out.writeBoolean(isVisibleGravity);
      out.writeObject(gravityColor);
      out.writeBoolean(autoColor);
    }
    catch(IOException e){
      throw new java.lang.UnsupportedOperationException("Method writeExternal() not yet implemented.");
    }
  }

  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    /**@todo Implement this java.io.Externalizable method*/
    try{
      visible=in.readBoolean();
      isSelected=false;
      m_polygon=(Polygon)in.readObject();
      m_area=in.readInt();
      m_circlement=in.readDouble();
      m_centrex=in.readDouble();
      m_centrey=in.readDouble();
      m_grayx=in.readDouble();
      m_grayy=in.readDouble() ;
      m_graySum=in.readLong();
      cellNo=in.readInt();
      edgeColor=(Color)in.readObject();
      isVisibleNo=in.readBoolean();
      isVisibleGeometry=in.readBoolean();
      geometryColor=(Color)in.readObject();
      isVisibleGravity=in.readBoolean();
      gravityColor=(Color)in.readObject();
      autoColor=in.readBoolean();

      // not read
      isSelected=false;
    }
    catch(IOException e){
      throw new java.lang.UnsupportedOperationException("Method readExternal() not yet implemented.");
    }
  }

}
