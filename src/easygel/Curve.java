package easygel;

import java.awt.*;
import easygel.*;
import ij.process.*;
import easygel.layer.*;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */
public class Curve{
  // 窗口坐标
  private Point winPointO,winPointX,winPointY;   //窗口坐标值左下角，右下角，左上角
  private double ampX,ampY;                     //纵横向放大倍数

  // 物理坐标轴的开始与结束
  public double phyCoordinateXBegin,phyCoordinateXEnd;
  public double phyCoordinateYBegin,phyCoordinateYEnd;

  // 曲线性质
  public double curveBeginX,curveEndX;      //开始画线的x坐标的开始与结束
  public int curveFunction;                  //1-5为1-5次方程,6为log,7为ln
  public boolean isCurveVisible;            //是否显示曲线
  public Color curveColor;                   //曲线的颜色
  public  int curveThick;                   //曲线的厚度
  private double []curveCoefficient;       //曲线方程的系数

  // 各点
  private SerialPoint  stdPoint;                //标准点物理坐标
  private int polyfitNum;                      //非标准点个数
  private SerialPoint[] polyfitPoint;

  // 默认颜色与形状
  private Color[] color;
  private int[]shape;

  public Curve(){
    isCurveVisible=true;
    curveColor=Color.yellow;
    curveThick=2;
    //
    color=new Color[10];
    shape=new int[10];
    color[0]=Color.red ;   shape[0]=0;
    color[1]=Color.orange;   shape[1]=2;
    color[2]=Color.green  ;   shape[2]=0;
    color[3]=Color.pink  ;   shape[3]=2;
    color[4]=Color.yellow  ;   shape[4]=0;
    color[5]=Color.red   ;   shape[5]=2;
    color[6]=Color.orange  ;   shape[6]=0;
    color[7]=Color.green  ;   shape[7]=2;
    color[8]=Color.pink  ;   shape[8]=0;
    color[9]=Color.yellow  ;   shape[9]=2;
  }

  public double[] getCurveCoefficient(){
    return this.curveCoefficient ;
  }

  public void setCurveCoefficient(double[] coefficient){
    this.curveCoefficient =new double[coefficient  .length ];
    this.curveCoefficient =coefficient;
  }

  public void setStdPhy(double []stdPhyX,double []stdPhyY){
    stdPoint=new SerialPoint(stdPhyX,stdPhyY);
    stdPoint.color =Color.blue ;
    stdPoint.shape =1;
  }

  public void setPolyfitNum(int number){
    polyfitNum=number;
    polyfitPoint=new SerialPoint[number];
  }

  public int getPolyfitNum(){
    return this.polyfitNum ;
  }

  //之前必需调用setPolyfitNum以确定维数
  public void setPolyfitPoint(int index,double []polyPointX,
                              double []polyPointY,int No){
    polyfitPoint[index-1]=new SerialPoint(polyPointX,polyPointY);
    if(index<=10){
      polyfitPoint[index-1].color =this.color [index-1];
      polyfitPoint[index-1].shape =this.shape [index-1];
    }
    else{
      polyfitPoint[index-1].color =new Color(0,0,index);
      polyfitPoint[index-1].shape =1;
    }
    polyfitPoint[index-1].no=No;
    polyfitPoint[index-1].isVisible =false;
  }

  public SerialPoint getPolyfitPoint(int index){
    return this.polyfitPoint[index-1];
  }

  public SerialPoint getStdPhy(){
    return this.stdPoint ;
  }

  public  void setCoordinate(Point winPointO,Point winPointX,Point winPointY,
                             double phyCoordBeginX, double phyCoordEndX,
                             double phyCoordBeginY, double phyCoordEndY){
    this.winPointO =winPointO;
    this.winPointX =winPointX;
    this.winPointY =winPointY;
    this.phyCoordinateXBegin =phyCoordBeginX;
    this.phyCoordinateXEnd =phyCoordEndX;
    this.phyCoordinateYBegin =phyCoordBeginY;
    this.phyCoordinateYEnd =phyCoordEndY;
    this.curveBeginX =curveBeginX;
    this.curveEndX =curveEndX;
  }

  //之前必需已调用setCoordinate
  //public void drawCurve(Graphics2D g2d){
  public void drawCurve(Graphics2D g2d,boolean isSave,ImageProcessor ip){
    //绘制曲线
    double detx=(this.phyCoordinateXEnd  -this.phyCoordinateXBegin )
                  /((double)(winPointX.x-winPointO.x));
    ampX=((double)(winPointX.x-winPointO.x))
                  /(this.phyCoordinateXEnd  -this.phyCoordinateXBegin  );
    ampY=((double)(winPointO.y-winPointY.y))
                  /(this.phyCoordinateYEnd  -this.phyCoordinateYBegin );

    //计算曲线点个数
    int number=0;
    for(double ii=this.curveBeginX  ;ii<=this.curveEndX  ;ii+=detx){
      number++;
    }

    //计算曲线点物理坐标(xx[ii],yy[ii])
    double []xx=new double[number];
    double []yy=new double[number];
    int index=0;
    for(double ii=this.curveBeginX  ;ii<=this.curveEndX  ;ii+=detx){
      xx[index]=ii;
      index++;
    }

    if(this.curveFunction ==6){
      yy=MathEx.logValue(this.curveCoefficient ,xx);
    }
    else if(this.curveFunction ==7){
      yy=MathEx.lnValue(this.curveCoefficient ,xx);
    }
    else{
      yy=MathEx.PolyVal(this.curveCoefficient ,xx);
    }

    //计算曲线上的点窗口坐标(xxx[ii],yyy[ii])
    if(this.isCurveVisible ==true){
      int []xxx=new int[number];
      int []yyy=new int[number];
      for(int ii=1;ii<=number;ii++){
        xxx[ii-1]=(int)((xx[ii-1]-this.phyCoordinateXBegin )*ampX+winPointO.x);
        yyy[ii-1]=(int)(winPointO.y-(yy[ii-1]-this.phyCoordinateYBegin )*ampY);
        //System.out.println("x="+xxx[ii-1]+",y="+yyy[ii-1]);
      }
      if(isSave==false) {
        g2d.setColor(this.curveColor);
        g2d.drawPolyline(xxx,yyy,number);
      }
      else{
        ip.setColor(this.curveColor);
        for(int pp=1;pp<number;pp++) ip.drawLine(xxx[pp-1],yyy[pp-1],xxx[pp],yyy[pp]);
      }
    }

    //绘制标准点
    if(stdPoint.isVisible ==true){
      int points=stdPoint.getValue() .length ;
      for(int qqqq=1;qqqq<=points;qqqq++){
        if(isSave==false) g2d.setColor(stdPoint.color );
        else ip.setColor(stdPoint.color );
        if(stdPoint.shape ==0)  drawOval(g2d,stdPoint.getValue()[qqqq-1],
            stdPoint.getValueY()[qqqq-1],stdPoint.radius,isSave,ip);
        else if (stdPoint.shape ==1) drawTriangle(g2d,stdPoint.getValue()[qqqq-1],
            stdPoint.getValueY()[qqqq-1],stdPoint.radius,isSave,ip);
        else drawRect(g2d,stdPoint.getValue()[qqqq-1],
                      stdPoint.getValueY()[qqqq-1],stdPoint.radius,isSave,ip);
      }
    }

    //绘制非标准点
    for(int ii=1;ii<=this.polyfitNum ;ii++){
      SerialPoint sp=this.polyfitPoint [ii-1];
      if(sp.isVisible ==false) continue;
      for(int jj=1; jj<=sp.getNumber() ;jj++){
        if(isSave==false) g2d.setColor(sp.color);
        else ip.setColor(sp.color);
        if(sp.shape ==0)  drawOval(g2d,sp.getValue()[jj-1],sp.getValueY()[jj-1],sp.radius,isSave,ip);
        else if (sp.shape ==1) drawTriangle(g2d,sp.getValue()[jj-1],sp.getValueY()[jj-1],sp.radius,isSave,ip);
        else drawRect(g2d,sp.getValue()[jj-1],sp.getValueY()[jj-1],sp.radius,isSave,ip);
      }
    }

  }

  private void drawTriangle(Graphics2D g2d,double cx,double cy,
                            int radius,boolean isSave,ImageProcessor ip){
    int cpx,cpy;
    cpx=(int)((cx-this.phyCoordinateXBegin )*ampX+winPointO.x);
    cpy=(int)(winPointO.y-(cy-this.phyCoordinateYBegin )*ampY);
    int xxx[]=new int[3];
    int yyy[]=new int[3];
    xxx[0]=cpx;
    yyy[0]=cpy-radius;
    xxx[1]=cpx-(int)(0.866*(double)radius);
    yyy[1]=cpy+radius;
    xxx[2]=cpx+(int)(0.866*(double)radius);
    yyy[2]=cpy+radius;
    Polygon polygon=new Polygon(xxx,yyy,3);
    if(isSave==false) g2d.fillPolygon(polygon);
    else this.fillEqualEdgeTriangle(ip,cpx,cpy,radius);
  }

  private void drawRect(Graphics2D g2d,double cx,double cy,int radius,boolean isSave,ImageProcessor ip){
    int cpx,cpy;
    cpx=(int)((cx-this.phyCoordinateXBegin )*ampX+winPointO.x);
    cpy=(int)(winPointO.y-(cy-this.phyCoordinateYBegin )*ampY);
    if(isSave==false) g2d.fillRect(cpx-radius,cpy-radius,2*radius,2*radius);
    else{
      for(int ii=1;ii<=radius*2;ii++) ip.drawRect(cpx-radius,cpy-radius,ii,ii);
    }
  }

  private void drawOval(Graphics2D g2d,double cx,double cy,int radius,boolean isSave,ImageProcessor ip){
    int cpx,cpy;
    cpx=(int)((cx-this.phyCoordinateXBegin )*ampX+winPointO.x);
    cpy=(int)(winPointO.y-(cy-this.phyCoordinateYBegin )*ampY);
    if(isSave==false) g2d.fillOval(cpx-radius/2,cpy-radius/2,radius,radius);
    else this.fillOval(ip,cpx,cpy,radius);
  }

  public static void fillOval(ImageProcessor ip,double cx,double cy,int radius){
    ip.drawDot((int)cx,(int)cy);
    for(int ii=1;ii<=radius;ii++){
      int xx[]=LayerCell.getPointXFromOval((int)cx,ii);
      int yy[]=LayerCell.getPointYFromOval((int)cx,(int)cy,ii);
      int l=xx.length;
      for(int jj=0;jj<l;jj++) ip.drawDot(xx[jj],yy[jj]);
      ip.drawDot((xx[0]+xx[1])/2,(yy[0]+yy[1])/2);
      ip.drawDot((xx[l-2]+xx[l-1])/2,(yy[l-2]+yy[l-1])/2);
      ip.drawDot((xx[l/2-1]+xx[l/2-2])/2,(yy[l/2-1]+yy[l/2-2])/2);
      ip.drawDot((xx[l/2-1]+xx[l/2])/2,(yy[l/2-1]+yy[l/2])/2);
      ip.drawDot((xx[l/2]+xx[l/2+1])/2,(yy[l/2]+yy[l/2+1])/2);
      ip.drawDot((xx[l/2-2]+xx[l/2-1])/2,(yy[l/2-2]+yy[l/2])/2);
      ip.drawDot((xx[l/4-1]+xx[l/4])/2,(yy[l/4-1]+yy[l/4])/2);
      ip.drawDot((xx[l/4+1]+xx[l/4])/2,(yy[l/4+1]+yy[l/4])/2);
      ip.drawDot((xx[3*l/4-1]+xx[3*l/4])/2,(yy[3*l/4-1]+yy[3*l/4])/2);
      ip.drawDot((xx[3*l/4+1]+xx[3*l/4])/2,(yy[3*l/4+1]+yy[3*l/4])/2);
    }
  }

  public static void fillEqualEdgeTriangle(ImageProcessor ip,double cx,double cy,int radius){
    int h=(int)(0.5+3*(double)radius/2);
    int hs=(int)(0.5+(double)radius*0.866);
    cy-=(int)(0.5+(double)radius/2);
    int l;
    for(int ii=(int)cy-h;ii<=cy;ii++){
      l=ii-((int)cy-h);
      for(int jj=(int)cx-l;jj<=cx+l;jj++){
        ip.drawDot(jj,ii);
      }
    }

  }



}

class SerialPoint{
  // 所有点为真实的物理点 (value[ii],valueY[ii])
  private int number;
  public int no;          //条带的序号
  private double [] value;
  private double [] valueY;
  public boolean isVisible;
  public  Color color;
  public int shape;       // 0-圆形，1-三角，2-正方形
  public int radius;
  public SerialPoint(double []pointValue,double []pointValueY){
    number=pointValue.length ;
    value=new double[number];
    valueY=new double[number];
    value=pointValue;
    valueY=pointValueY;
    // 默认参数
    isVisible=true;
    radius=6;
  }
  public int getNumber(){
    return this.number ;
  }
  public double []getValue(){
    return this.value ;
  }
  public double []getValueY(){
    return this.valueY ;
  }
  public double getValue(int no){
    return value[no-1];
  }
  public double getValueY(int no){
    return valueY[no-1];
  }
}
