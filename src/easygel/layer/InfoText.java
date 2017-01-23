package easygel.layer;

import java.awt .*;
import java.awt.geom.*;
import easygel.*;
import java.io.Externalizable;
import java.io.ObjectOutput;
import java.io.IOException;
import java.io.ObjectInput;

/**
 * <p>Title:标注文字及外框属性 </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * <p>point为框左上角坐标（横向、上切） </p>
 * <p>point为框左下角坐标（下切） </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

/*
 应改为：多边形以显示之，其坐标是旋转后的坐标
 rect仍要，表示框的大小，非旋转坐标
 string，由roationPoint,rotation最终记录位置
 */

public class InfoText extends Information implements Externalizable{
  public Point point;

  // 为其他操作的备用点
  public Point pp_point;

  public String text;
  public Font font;
  public Color colorText,colorRect;
  //public  static Polygon polygon=new Polygon(new int[4],new int[4],4);
  private Rectangle rect;
  /**将绘制的设备对象*/
  public Dialog dialog;
  /**外框是否可见*/
  public boolean visibleRect;
  /**外框离文字的距离（横向）*/
  //public static byte DISTANCELEFT=6;
  public int distanceLeft,distanceRight;
  /**外框离文字的距离（纵向）*/
  public int distanceTop,distanceBottom;
  /**外框是否是虚线（false为实线）*/
  public boolean isDot;
  /**外框是否是圆角（false为方角）*/
  public boolean isRound;
  /**旋转角度*/
  public double rotation;
  public Point rotationPoint;
  private static double PI=3.1415926535;
  /**外框的厚度，向外扩展*/
  public int rectThick;

  public InfoText(){
    super();
  }

  public InfoText duplicate(){
    InfoText it=new InfoText();
    if(this.pp_point==null) ;
    else it.pp_point =new Point(this.pp_point.x,this.pp_point.y);
    it.colorRect=this.cloneColor(this.colorRect);
    it.colorText=this.cloneColor(this.colorText);
    it.dialog=this.dialog;
    it.distanceBottom=this.distanceBottom;
    it.distanceLeft=this.distanceLeft;
    it.distanceRight=this.distanceRight;
    it.distanceTop=this.distanceTop;
    it.font=new Font(this.font.getName(),this.font.getStyle(),this.font.getSize());
    it.ID=this.ID;
    it.isDot=this.isDot;
    it.isRound=this.isRound;
    it.isSelected=this.isSelected;
    it.PI=this.PI;
    it.point=this.clonePoint(this.point);
    it.rect=this.closeRectangle(this.rect);
    it.rectThick=this.rectThick;
    it.rotation=this.rotation;
    it.rotationPoint=this.clonePoint(this.rotationPoint);
    it.text=new String(this.text);

    it.visible=this.visible;
    it.visibleRect=this.visibleRect;
    return it;
  }

  public String toString(){
    //String str=this.toString();
    String str="Text=";
    str+=this.text;
    str+=","+this.point.toString();
    str+=",status="+this.status;
    return str;
  }

  public InfoText(int id,DialogImage di, String ttext,Point tpoint){
    super();

    this.pp_point =null;

    dialog=di;
    ID=id;
    point=tpoint;
    text=ttext;

    font=di.getFont();
    distanceTop=-3;
    distanceBottom=2;
    distanceLeft=2;
    distanceRight=2;
    colorText=new Color(0,0,0);
    colorRect=new Color(0,0,0);
    visible=true;
    visibleRect=true;
    rectThick=1;

    //设置状态
    isSelected=false;
    isRound=false;
    isDot=false;

    //设置字旋转属性
    rotation=0;
    rotationPoint=tpoint;

    this.adjust(tpoint);

    /* ！！！字体的高宽设置
    System.out.println(fm.getLeading() );
    System.out.println(fm.getAscent() );
    System.out.println(fm.getDescent() );
    System.out.println(fm.getMaxDescent());
    System.out.println(fm.getMaxAscent() );
    System.out.println(fm.getHeight() );
    System.out.println(fm.stringWidth(ttext) );
    */
  }

  /**
   * 当设置以下参数后必须调用此方法：dialog,font,text,point,
   * distanceTop,distanceBottom,distanceLeft,distanceRight,
   * rotation,rotationPoint
   */
  public void adjust(Point p){
    point=p;
    //获取文字的宽高属性
    FontMetrics fm;
    fm=dialog.getFontMetrics(font);
    int fontWidth,fontHeight;
    fontWidth=fm.stringWidth(text);
    fontHeight=fm.getHeight();

    //将鼠标点击点改为文字的左上角位置
    point.y=point.y+fm.getAscent()-fm.getLeading();

    //设置框
    int rectHeight;
    rectHeight=fontHeight-fm.getLeading()+distanceTop+distanceBottom;
    rect=new Rectangle(point.x - distanceLeft,
                       point.y - distanceTop-rectHeight,
                       fontWidth+distanceLeft+distanceRight,
                       rectHeight);
  }

  public void adjust(){
    //获取文字的宽高属性
    FontMetrics fm;
    fm=dialog.getFontMetrics(font);
    int fontWidth,fontHeight;
    fontWidth=fm.stringWidth(text);
    fontHeight=fm.getHeight();
    //将鼠标点击点改为文字的左上角位置
    //point.y=point.y+fm.getAscent()-fm.getLeading();
    //设置框
    int rectHeight;
    rectHeight=fontHeight-fm.getLeading()+distanceTop+distanceBottom;
    if(this.rotation ==0){
      rect=new Rectangle(point.x - distanceLeft,
                         point.y - distanceTop-rectHeight,
                         fontWidth+distanceLeft+distanceRight,
                         rectHeight);
    }
    else if(this.rotation ==PI/2){
      rect=new Rectangle(point.x - distanceBottom ,
                         point.y - distanceLeft ,
                         distanceTop+fontHeight,
                         fontWidth+distanceRight);
    }
    else if(this.rotation ==-1*PI/2){
      rect=new Rectangle(point.x -fontHeight+fm.getLeading()+distanceBottom,
                         point.y - distanceLeft-(fontWidth+distanceRight) ,
                         distanceTop+fontHeight,
                         fontWidth+distanceRight);
    }
  }

  public Rectangle getRect(){
    return rect;
  }

  public void writeExternal(ObjectOutput out) throws IOException {
    /**@todo Implement this java.io.Externalizable method*/
    try{
      out.writeObject(this.colorRect);
      out.writeObject(this.colorText);
      out.writeInt(this.distanceBottom);
      out.writeInt(this.distanceLeft);
      out.writeInt(this.distanceRight);
      out.writeInt(this.distanceTop);
      out.writeObject(this.font);
      //not write:  this.dialog, this.ID ,isSelected
      out.writeBoolean(this.isDot ) ;
      out.writeBoolean(this.isRound );
      out.writeObject( this.point );
      out.writeObject(this.rect );
      out.writeInt(this.rectThick );
      out.writeDouble( this.rotation );
      out.writeObject( this.rotationPoint );
      out.writeObject( this.text );
      out.writeBoolean( this.visible );
      out.writeBoolean(this.visibleRect );
    }
    catch(IOException e){
      throw new java.lang.UnsupportedOperationException("Method writeExternal() not yet implemented.");
    }
  }

  public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
     /**@todo Implement this java.io.Externalizable method*/
   try{
       this.colorRect =(Color)in.readObject() ;
       this.colorText=(Color)in.readObject() ;
       this.distanceBottom=in.readInt() ;
       this.distanceLeft=in.readInt() ;
       this.distanceRight=in.readInt() ;
       this.distanceTop=in.readInt() ;
       this.font=(Font)in.readObject() ;
       //set without selected
       this.isSelected =false;
       //not read:  this.dialog, this.ID
       this.isDot =in.readBoolean() ;
       this.isRound =in.readBoolean() ;
       this.point =(Point)in.readObject() ;
       this.rect =(Rectangle)in.readObject() ;
       this.rectThick =in.readInt() ;
       this.rotation =in.readDouble() ;
       this.rotationPoint =(Point)in.readObject() ;
       this.text =(String)in.readObject() ;
       this.visible =in.readBoolean() ;
       this.visibleRect =in.readBoolean() ;
    }
   catch(IOException e){
       throw new java.lang.UnsupportedOperationException("Method readExternal() not yet implemented.");
   }
  }
}