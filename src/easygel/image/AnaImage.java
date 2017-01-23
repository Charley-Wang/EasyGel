package easygel.image;

import java.util.*;
import ij.process.*;
import java.awt.*;
import easygel.*;
import easygel.layer.*;
import easygel.image.*;
import java.awt.image.*;
import java.io.*;

import java.awt.geom.*;
import java.awt.image.*;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class AnaImage {
  // the methods to find the threshold for binarilizition.
  public static final int threshModeUser=-1;
  public static final int threshModeOtsu=0;
  public static final int threshModeWesz=1;
  public static final int threshModeWata=2;

  // method of the search edge
  public static final int edgeSobel=0;
  public static final int edgeRobert=1;
  public static final int edgeLap=2;
  public static final int edgePrewitt=3;
  //public static final int edgeRobinson=4;
  //public static final int edgeKirsch=5;
  //public static final int edgeFrei=6;
  public static final int edgeNone=7;
  public static final int edgeGuassLap=8;

  protected  byte threshold;
  private int width,height;
  private byte pixels[];
  protected  byte []binary;
  protected  byte []edgegray;

  private static final int out=0;
  private static final int edge=1;
  private static final int in=2;
  private static final int newedge=3;
  private static final int notedgein=4;
  private static final int notedgeout=5;
  private static final int dealed=127;
  private static final int dealing=7;

  /**
   * constructor,modified by wxs
   * @param width 图像的宽
   * @param height 图像的高
   * @param pixels 图像的灰度值,按[1,1],[2,1],[3,1];[1,2],[2,2],[3,2];...排列
   * @param ip2 用于InfoCell计算各点的灰度之用
   */
  public AnaImage(int width,int height,byte []pixels){
    this.width =width;
    this.height =height;
    this.pixels =pixels;
  }

  public AnaImage(){

  }

  //simple data members get/set functions.
  public int getwidth(){
    return width;
  }

  public int getheight(){
    return height;
  }

  public Object getEdgeGray(){
    return this.edgegray ;
  }

  public Object getbinary(){
    return binary;
  }

  public void setbinary(byte[]x){
    binary=x;
    edgegray=x;
  }

  public Object getPixels(){
    return pixels;
  }

  public void drawArray(byte[] array,DialogImage di){
    int px[]=new int[width*height];
    byte bytev;
    int intv,tempint;
    for(int ii=1;ii<=height*width;ii++){
      bytev=array[ii-1];
      tempint=bytev>>7;
      if(tempint==1) intv=256+bytev;
      else intv=bytev;
      if(intv<0) intv=256+intv;
      px[ii-1]=(new Color(intv,intv,intv)).getRGB() ;
    }
    MemoryImageSource src=new MemoryImageSource(width,height,px,0,width);
    Image img = Toolkit.getDefaultToolkit().createImage(src);
    di.setImage2(img);
    di.paintImage();
  }

  public int getThreshold( ){
    return threshold&0xff;
  }

  public void setThreshold( int thresh){
    threshold=(byte)thresh;
  }

  public int calThreshold(int Mode){
    switch(Mode) {
      case threshModeOtsu:
        return Otsu();
      case threshModeWata:
        return Wata();
      case threshModeWesz:
        return Wesz();
      default:
        return Otsu();
    }
  }

  public int calThreshold(int Mode,Polygon pg){
    switch(Mode) {
      case threshModeOtsu:
        return Otsu(pg);
      case threshModeWata:
        return Wata(pg);
      case threshModeWesz:
        return Wesz(pg);
      default:
        return Otsu(pg);
    }
  }

  private int Otsu(){
    double[]level=new double[256];
    int []temp=graylevel();
    for(int t=0;t<256;t++)
    {
      level[t]=temp[t];
    }
    double graymean=0;
    double eps=1e-6;
    for(int i=0;i<256;i++)
    {
       graymean=graymean+i*level[i];
       level[i]=level[i]/(width*height);
    }
    graymean=graymean/(width*height);//to get the gray mean of the image.
    double muk=0;
    double wk=level[0];
    double thetak=0;
    int nthresh=0;
    for(int k=1;k<256;k++)
    {
      muk=muk+k*level[k];
      wk=wk+level[k];
      if(wk*(1-wk)<eps){
        continue;
      }
      double a=graymean*wk-muk;
      a=a*a;
      a=a/(wk*(1-wk));
      if(a>thetak)
      {
        thetak=a;
        nthresh=k;
      }
   }
   return nthresh;
  }

  private int getArea(Polygon pg){
    int area=0;
    Rectangle rect=pg.getBounds();
    for(int ii=1;ii<=rect.getWidth();ii++){
      for(int jj=1;jj<=rect.getHeight();jj++){
        if(pg.contains(ii-1,jj-1)==true)
          area++;
      }
    }
    //System.out.println("area="+area);
    return area;
  }

  private void drawGray(Graphics g,double data[],int cofi,Color c,int detx){
    int xx[]=new int[data.length];
    int yy[]=new int[data.length];
    for(int ii=1;ii<=xx.length;ii++){
      xx[ii-1]=ii+detx;
      yy[ii-1]=(int)(data[ii-1]*(double)cofi/20.0+0.5);
    }
    //PolyLine pg=new PolyLine(xx,yy,xx.length);
    g.setColor(c);
    //g.drawPolygon(pg);
    g.drawPolyline(xx,yy,xx.length);
  }

  private int Otsu(Polygon pg){
    double[]level=new double[256];
    int []temp=grayLevel(pg);
    int area=this.getArea(pg);
    for(int t=0;t<256;t++)
    {
      level[t]=temp[t];
    }
    double graymean=0;
    double eps=1e-6;
    for(int i=0;i<256;i++)
    {
       graymean=graymean+i*level[i];
       level[i]=level[i]/area;
    }

    graymean=graymean/area;//to get the gray mean of the image.
    double muk=0;
    double wk=level[0];
    double thetak=0;
    int nthresh=0;
    for(int k=1;k<256;k++)
    {
      muk=muk+k*level[k];
      wk=wk+level[k];
      if(wk*(1-wk)<eps){
        continue;
      }
      double a=graymean*wk-muk;
      a=a*a;
      a=a/(wk*(1-wk));
      if(a>thetak)
      {
        thetak=a;
        nthresh=k;
      }
   }
   return nthresh;
  }

  private int Wata(){
    int []cx=new int[256];//to store the difference of gray level of the image.
    int []cx2=new int[256];
    for(int x=1;x<height-1;x++)
      for(int y=1;y<width-1;y++)
      {
        int d=0;
        int b=0;
        for(int i=-1;i<2;i++)
          for(int j=-1;j<2;j++)
          {
            int temp=edgegray[x*width+y]&0xff-edgegray[(x+i)*width+y+j]&0xff;

             d=(temp>0)?d+temp:d;
             b=(temp<0)?b-temp:b;
          }
        int idx=edgegray[x*width+y]&0xff;
        cx[idx]=cx[idx]+d;
        cx2[idx]=cx2[idx]+b;
      }
    int nthresh1=0;
    int nthresh2=0;
    int max1=0;
    int max2=0;
    for(int i=0;i<256;i++)
    {
      if(cx[i]>max1)
      {
        max1=cx[i];
        nthresh1=i;
      }
      if(cx2[i]>max2)
      {
        max2=cx2[i];
        nthresh2=i;
      }
    }
    return (nthresh1+nthresh2)/2;
  }

  private int Wata(Polygon pg){
    int []cx=new int[256];//to store the difference of gray level of the image.
    int []cx2=new int[256];
    for(int x=1;x<height-1;x++){
      for(int y=1;y<width-1;y++){
        if(pg.contains(y,x)==false) continue;
        int d=0;
        int b=0;
        for(int i=-1;i<2;i++){
          for(int j=-1;j<2;j++){
            int temp=edgegray[x*width+y]&0xff-edgegray[(x+i)*width+y+j]&0xff;
            d=(temp>0)?d+temp:d;
            b=(temp<0)?b-temp:b;
          }
        }
        int idx=edgegray[x*width+y]&0xff;
        cx[idx]=cx[idx]+d;
        cx2[idx]=cx2[idx]+b;
      }
    }
    int nthresh1=0;
    int nthresh2=0;
    int max1=0;
    int max2=0;
    for(int i=0;i<256;i++){
      if(cx[i]>max1){
        max1=cx[i];
        nthresh1=i;
      }
      if(cx2[i]>max2){
        max2=cx2[i];
        nthresh2=i;
      }
    }
    return (nthresh1+nthresh2)/2;
  }

  private int Wesz(){
    return 127;
  }

  private int Wesz(Polygon pg){
    return 127;
  }

  private int []graylevel(){
    int[]level=new int[256];
    for(int i=0;i<256;i++)
    {
      level[i]=0;
    }
    int temp=0;
    for(int x=0;x<height;x++)
      for(int y=0;y<width;y++)
      {
        temp=edgegray[x*width+y]&0xff;
        level[temp]++;
      }
      return level;
  }

  /**
   * 计算pg内图象的灰度图
   * @param pg
   * @return
   */
  private int []grayLevel(Polygon pg){
    int[]level=new int[256];
    for(int i=0;i<256;i++) level[i]=0;
    int temp=0;
    for(int x=0;x<height;x++){
      for(int y=0;y<width;y++){
        if(pg.contains(y,x)==true){
          temp=edgegray[x*width+y]&0xff;
          level[temp]++;
        }
      }
    }
    return level;
  }

  public void oneTo255(byte []array){
    for(int ii=1;ii<=width*height;ii++){
      if(array[ii-1]==1) array[ii-1]=(byte)255;
    }
  }

 public static void binary(byte []image,int nwidth,int nheight,int nthreshold){
   if(image.length!=nwidth*nheight) return;
   int tmp=0;
   for(int x=0;x<nheight;x++)
     for(int y=0;y<nwidth;y++)
     {
       tmp=image[x*nwidth+y]&0xff;
       image[x*nwidth+y]=(byte)((tmp>=nthreshold)?1:0);
     }
 }

 public void binaryFor1D(byte []image,int nwidth,int nheight,
                         int nthreshold,int y0,int leftD,int rightD){
   if(image.length!=nwidth*nheight) return;
   int tmp=0,v1,v2;

   /*
   int left=-1;
   for(int x=0;x<nwidth;x++){
     //if(x<=leftD && image[y0*nwidth+x]<nthreshold) continue;
     if(y0-1>=0) v1=image[(y0-1)*nwidth+x]&0xff;
     else v1=-1;
     if(y0+1<nheight) v2=image[(y0+1)*nwidth+x]&0xff;
     else v2=-1;
     if(v1>=nthreshold || v2>=nthreshold){
       left=x;
       break;
     }
   }
   int right=-1;
   for(int x=nwidth-1;x>=0;x--){
     //if(x>=rightD && image[y0*nwidth+x]<nthreshold) continue;
     if(y0-1>=0) v1=image[(y0-1)*nwidth+x]&0xff;
     else v1=-1;
     if(y0+1<nheight) v2=image[(y0+1)*nwidth+x]&0xff;
     else v2=-1;
     if(v1>=nthreshold || v2>=nthreshold){
       right=x;
       break;
     }
   }
   */

   // |      |             |        |
   // |      |             |        |
   // |    |||||||||||||||||||||    |  nheight
   // |    |||||||||||||||||||||    |
   // |    |||||||||||||||||||||    |
   // |      |             |        |
   // |      |             |        |
   // 0............................nwidth
   // .......leftD.........rightD
   // .....left.................right
   int left=leftD;
   int tmp1,tmp2,tmp3;
   for(int x=leftD-1;x>=0;x--){
     tmp1=image[y0*nwidth+x]&0xff;
     tmp2=tmp1;tmp3=tmp1;
     if(y0-1>=0) tmp2=image[(y0-1)*nwidth+x]&0xff;
     if(y0+1<nheight) tmp3=image[(y0+1)*nwidth+x]&0xff;
     // if(tmp1<nthreshold && tmp2<nthreshold && tmp3<nthreshold) break;  // or ...
     tmp2=(int)((tmp1+tmp2+tmp3)/3.0+0.5);                                // and ...
     if(tmp2<nthreshold && tmp1<nthreshold) break;
     else left=x;
   }

   int right=rightD;
   for(int x=rightD+1;x<=nwidth-1;x++){
     tmp1=image[y0*nwidth+x]&0xff;
     tmp2=tmp1;tmp3=tmp1;
     if(y0-1>=0) tmp2=image[(y0-1)*nwidth+x]&0xff;
     if(y0+1<nheight) tmp3=image[(y0+1)*nwidth+x]&0xff;
     // if(tmp1<nthreshold && tmp2<nthreshold && tmp3<nthreshold) break;  // or...
     tmp2=(int)((tmp1+tmp2+tmp3)/3.0+0.5);                                // and ...
     if(tmp2<nthreshold && tmp1<nthreshold) break;
     else right=x;
   }

   //System.out.println("LD="+leftD+",RD="+rightD+",L="+left+",R="+right);

   if(left==-1 || right==-1 || left==right){
     for(int x=0;x<nheight;x++) {
       for(int y=0;y<nwidth;y++) {
         image[x*nwidth+y]=0;
       }
     }
     return;
   }

   for(int x=0;x<left;x++){
     for(int y=0;y<nheight;y++){
       image[y*nwidth+x]=0;
     }
   }

   for(int x=right+1;x<nwidth;x++){
     for(int y=0;y<nheight;y++){
       image[y*nwidth+x]=0;
     }
   }

   for(int x=left;x<=right;x++){
     boolean zero=false;
     // y0上面部分
     for(int y=y0-1;y>=0;y--){
       if(zero==false){
         tmp=image[y*nwidth+x]&0xff;
         if(tmp>=nthreshold) image[y*nwidth+x]=1;
         else{
           image[y*nwidth+x]=0;
           zero=true;
         }
       }
       else image[y*nwidth+x]=0;
     }

     // y0部分
     int y=y0;
     tmp=image[y*nwidth+x]&0xff;
     if(tmp+1>=nthreshold) image[y*nwidth+x]=1;
     else image[y*nwidth+x]=0;

     // y0下面部分
     zero=false;
     for(y=y0+1;y<height;y++){
       if(zero==false){
         tmp=image[y*nwidth+x]&0xff;
         if(tmp>=nthreshold) image[y*nwidth+x]=1;
         else{
           image[y*nwidth+x]=0;
           zero=true;
         }
       }
       else image[y*nwidth+x]=0;
     }
   }
 }

 public void clearInPoint(byte[]array,int threshv){
   int []scr=new int[width*height];
   int []result=new int[width*height];
   int intv;
   for(int ii=1;ii<=width*height;ii++){
     intv=array[ii-1];
     if(intv<0) intv+=256;
     scr[ii-1]=intv;
   }

   for(int ii=0;ii<width;ii++) {
     result[ii]=0;
     result[(height-1)*width+ii]=0;
   }
   for(int ii=0;ii<height;ii++){
     result[width*ii]=0;
     result[width*ii+width-1]=0;
   }

   for(int ii=1;ii<width-1;ii++){
     for(int jj=1;jj<height-1;jj++){
       int kk=jj*width+ii;
       result[kk]=scr[kk];
       if(scr[kk]>=threshv){
         if(scr[kk-width-1]<threshv) continue;
         if(scr[kk-width]<threshv) continue;
         if(scr[kk-width+1]<threshv) continue;
         if(scr[kk-1]<threshv) continue;
         if(scr[kk+1]<threshv) continue;
         if(scr[kk+width-1]<threshv) continue;
         if(scr[kk+width]<threshv) continue;
         if(scr[kk+width+1]<threshv) continue;
         result[kk]=127;
       }
     }
   }

   for(int ii=0;ii<width*height;ii++){
     array[ii]=(byte)result[ii];
   }
 }

 public int getOptiumnThreshold(ImageProcessor ip,Rectangle rect,int threshMode){
   width=(int)rect.getWidth() ;
   height=(int)rect.getHeight() ;
   pixels=new byte[width*height];
   int n=0;
   for(int ii=rect.getLocation() .y;ii<=rect.getLocation() .y+height-1;ii++){
     for(int jj=rect.getLocation() .x;jj<=width+rect.getLocation() .x-1;jj++){
       pixels[n]=(byte)ip.getPixelValue(jj,ii);
       n++;
     }
   }
   setbinary(pixels);
   int thresh=(int) (calThreshold(threshMode));
   return thresh;
 }

 public void signInPoint(){
   int []result=new int[width*height];

   for(int ii=0;ii<width;ii++) {
     result[ii]=0;
     result[(height-1)*width+ii]=0;
   }
   for(int ii=0;ii<height;ii++){
     result[width*ii]=0;
     result[width*ii+width-1]=0;
   }

   for(int ii=1;ii<width-1;ii++){
     for(int jj=1;jj<height-1;jj++){
       int kk=jj*width+ii;
       result[kk]=binary[kk];
       if(binary[kk]==edge){
         if(binary[kk-width-1]==out) continue;
         if(binary[kk-width]==out) continue;
         if(binary[kk-width+1]==out) continue;
         if(binary[kk-1]==out) continue;
         if(binary[kk+1]==out) continue;
         if(binary[kk+width-1]==out) continue;
         if(binary[kk+width]==out) continue;
         if(binary[kk+width+1]==out) continue;
         result[kk]=in;
       }
     }
   }
   for(int ii=0;ii<width*height;ii++){
     binary[ii]=(byte)result[ii];
   }
 }

 public Vector searchBoundary(){
   int kk,xx,yy;
   int xxx,yyy;
   int []m={-1*width,-1,1,width,-1*width-1,-1*width+1,width-1,width+1};
   int []x={0, -1,1,0,-1, 1,-1,1};
   int []y={-1, 0,0,1,-1,-1, 1,1};
   int minx,miny,maxx,maxy;
   Vector v=new Vector();
   for(int ii=0;ii<height;ii++){
     for(int jj=0;jj<width;jj++){
       kk=ii*width+jj;
       xx=jj;
       yy=ii;
       if(binary[kk]==edge || binary[kk]==newedge){
         Polygon pg=new Polygon();
         Point startP=new Point(jj,ii);
         pg.addPoint(jj,ii);
         minx=maxx=jj;
         miny=maxy=ii;
         binary[kk]=dealing;
         boolean ct=true;
         while(ct){
           for(int ll=0;ll<8;ll++){
             if(kk+m[ll]<0 || kk+m[ll]>=width*height) continue;
             if(binary[kk+m[ll]]==edge || binary[kk+m[ll]]==newedge){
               kk+=m[ll];  xx+=x[ll];  yy+=y[ll];
               Point pt=new Point(xx,yy);
               if(pt.equals(startP) ==true) ct=false;
               else {
                 pg.addPoint(xx,yy);        binary[kk]=dealing;
                 if(xx>maxx) maxx=xx;    if(xx<minx) minx=xx;
                 if(yy>maxy) maxy=yy;    if(yy<miny) miny=yy;
               }
               break;
             }
             if(ll==7) ct=false;
           }
         }
         if(pg.npoints >=3){
           v.addElement(pg);
           for(yyy=miny;yyy<=maxy;yyy++){
             for(xxx=minx;xxx<=maxx;xxx++){
               if(binary[yyy*width+xxx]==dealing) binary[yyy*width+xxx]=dealed;
               else{
                 if(pg.contains(xxx,yyy)==true)  binary[yyy*width+xxx]=dealed;
               }
             }
           }
         }
       }
     }
   }
   return v;
 }

 public void deleteNotEdge(){
   int []result=new int[width*height];

   // .......
   // .......
   // .......
   // .......

   for(int ii=0;ii<width;ii++) {
     result[ii]=0;
     result[(height-1)*width+ii]=0;
   }
   for(int ii=0;ii<height;ii++){
     result[width*ii]=0;
     result[width*ii+width-1]=0;
   }

   int ins,outs;
   for(int ii=1;ii<width-1;ii++){
     for(int jj=1;jj<height-1;jj++){
       int kk=jj*width+ii;
       ins=outs=0;
       if(binary[kk]==edge || binary[kk]==newedge){
         if(binary[kk-width-1]==out) outs++;
         else if(binary[kk-width-1]==in)ins++;

         if(binary[kk-width]==out) outs++;
         else if(binary[kk-width]==in) ins++;

         if(binary[kk-width+1]==out) outs++;
         else if(binary[kk-width+1]==in) ins++;

         if(binary[kk-1]==out) outs++;
         else if(binary[kk-1]==in) ins++;

         if(binary[kk+1]==out) outs++;
         else if(binary[kk+1]==in)ins++;

         if(binary[kk+width-1]==out) outs++;
         else if(binary[kk+width-1]==in)ins++;

         if(binary[kk+width]==out) outs++;
         else if(binary[kk+width]==in)ins++;

         if(binary[kk+width+1]==out) outs++;
         else if(binary[kk+width+1]==in)ins++;

         if(ins==0 || outs==0){
           if(outs==0) result[kk]=notedgein;
           else result[kk]=notedgeout;
         }
         else{
           result[kk]=binary[kk];
         }
       }
     }
   }

   for(int ii=0;ii<width*height;ii++){
     binary[ii]=(byte)result[ii];
   }
 }

 public void goodBoundary(){
   int []result=new int[width*height];

   for(int ii=0;ii<width;ii++) {
     result[ii]=0;
     result[(height-1)*width+ii]=0;
   }
   for(int ii=0;ii<height;ii++){
     result[width*ii]=0;
     result[width*ii+width-1]=0;
   }

   for(int ii=1;ii<width-1;ii++){
     for(int jj=1;jj<height-1;jj++){
       int kk=jj*width+ii;
       if(binary[kk]==in){
         result[kk]=newedge;
         if(binary[kk-width-1]==out) continue;
         if(binary[kk-width]==out) continue;
         if(binary[kk-width+1]==out) continue;
         if(binary[kk-1]==out) continue;
         if(binary[kk+1]==out) continue;
         if(binary[kk+width-1]==out) continue;
         if(binary[kk+width]==out) continue;
         if(binary[kk+width+1]==out) continue;
         result[kk]=binary[kk];
       }
       else{
         result[kk]=binary[kk];
       }
     }
   }
   for(int ii=0;ii<width*height;ii++){
     binary[ii]=(byte)result[ii];
   }
 }

 public void invert(){
   if( (binary!=null) && (binary.length==width*height) )
   {
     for(int y=0;y<height;y++)
       for(int x=0;x<width;x++)
         binary[y*width+x]=(byte)( (binary[y*width+x]!=0)?0:1);
   }
 }

public void edgeRobert(int thresh,boolean isPolygon,Polygon pg){
  byte []result=new byte[height*width];
  int g_v,g_h,v_v,v_h,v;
  for(int i=0;i<width;i++){
    for(int j=0;j<height;j++){
      if(isPolygon==true){
        if(pg.contains(i,j)==false) continue;
      }
      g_v=g_h=v_v=v_h=0;
      for(int k=i-1;k<i+1;k++){
        for(int l=j-1;l<j+1;l++){
          if(k>=0 && l>=0 && k<width && l<height){
            if(k==i-1 && l==j-1) g_v=1;
            else if(k==i && l==j) g_v=-1;
            else g_v=0;

            if(k==i-1 && l==j) g_h=-1;
            else if(k==i && l==j-1) g_h=1;
            else g_h=0;

             v_v+=pixels[l*width+k]*g_v;
             v_h+=pixels[l*width+k]*g_h;
          }
        }
      }
      //result[j*width+i]=(byte)Math.sqrt(v_v*v_v+v_h*v_h);
      v=(int)Math.sqrt(v_v*v_v+v_h*v_h);
      if(v>=thresh)  result[j*width+i]=0;
      else result[j*width+i]=1;
    }
  }
  binary=result;
}

public void edgePrewitt(float thresh,boolean isPolygon,Polygon pg){
  byte []result=new byte[height*width];
  int g_v,g_h,v_v,v_h,v;
  for(int i=0;i<width;i++){
    for(int j=0;j<height;j++){
      if(isPolygon==true){
        if(pg.contains(i,j)==false) continue;
      }
      g_v=g_h=v_v=v_h=0;
      for(int k=i-1;k<i+2;k++){
        for(int l=j-1;l<j+2;l++){
          if(k>=0 && l>=0 && k<width && l<height){
            if(k==i-1) g_v=-1;
            else if(k==i+1) g_v=1;
            else if(k==i) g_v=0;

            if(l==j-1) g_h=1;
            else if(l==j+1) g_h=-1;
            else if(l==j) g_h=0;

             v_v+=pixels[l*width+k]*g_v;
             v_h+=pixels[l*width+k]*g_h;
          }
        }
      }
      v=(int)Math.sqrt(v_v*v_v+v_h*v_h);
      //result[j*width+i]=(byte)Math.sqrt(v_v*v_v+v_h*v_h);
      if(v>=thresh)  result[j*width+i]=0;
      else result[j*width+i]=1;
    }
  }
  binary=result;
}

public void edgeLaplacian(float thresh,boolean isPolygon,Polygon pg){
  byte []result=new byte[height*width];
  int v,p_g;
  int []g={-1,-1,-1,-1,8,-1,-1,-1,-1};
  for(int i=0;i<width;i++){
    for(int j=0;j<height;j++){
      if(isPolygon==true){
        if(pg.contains(i,j)==false) continue;
      }
      v=p_g=0;
      for(int k=i-1;k<i+2;k++){
        for(int l=j-1;l<j+2;l++){
          if(k>=0 && l>=0 && k<width && l<height){
             v+=pixels[l*width+k]*g[p_g];
             p_g++;
          }
        }
      }
      if(v<0) v=0;
      //result[j*width+i]=(byte)v;
      if(v>=thresh)  result[j*width+i]=0;
      else result[j*width+i]=1;
    }
  }
  binary=result;
}

public void edgeGuassLaplacian(float thresh,boolean isPolygon,Polygon pg){
  byte []result=new byte[height*width];
  int v,p_g;
  int []g={-2,-4,-4,-4,2,
           -4,0,8,0,-4,
           -4,8,24,8,-4,
           -4,0,8,0,-4,
           -2,-4,-4,-4,-2};
  for(int i=0;i<width;i++){
    for(int j=0;j<height;j++){
      if(isPolygon==true){
        if(pg.contains(i,j)==false) continue;
      }
      v=p_g=0;
      for(int k=i-2;k<i+3;k++){
        for(int l=j-2;l<j+3;l++){
          if(k>=0 && l>=0 && k<width && l<height){
             v+=pixels[l*width+k]*g[p_g];
             p_g++;
          }
        }
      }
      if(v<0) v=0;
      if(v>=thresh)  result[j*width+i]=0;
      else result[j*width+i]=1;
    }
  }
  binary=result;
}

public void edgeSobel(int thresh,boolean isPolygon,Polygon pg){
  byte []result=new byte[height*width];
  int g_v,g_h,v_v,v_h,v;
  for(int i=0;i<width;i++){
    for(int j=0;j<height;j++){
      if(isPolygon==true){
        if(pg.contains(i,j)==false) continue;
      }
      g_v=g_h=v_v=v_h=0;
      for(int k=i-1;k<i+2;k++){
        for(int l=j-1;l<j+2;l++){
          if(k>=0 && l>=0 && k<width && l<height){
            if(k==i-1){ if(l==j) g_v=-2;  else g_v=-1;  }
            if(k==i+1){ if(l==j) g_v=2;  else g_v=1;   }
            if(k==i) g_v=0;
            if(l==j-1){ if(k==i) g_h=2; else g_h=1;  }
            if(l==j+1){ if(k==i) g_h=-2;else g_h=-1; }
            if(l==j) g_h=0;
            v_v+=pixels[l*width+k]*g_v;
            v_h+=pixels[l*width+k]*g_h;
          }
        }
      }
      v=(int)(Math.sqrt(v_v*v_v+v_h*v_h)+0.5);
      if(v>=thresh)  result[j*width+i]=1;
      else result[j*width+i]=0;
    }
  }
  binary=result;
}

}
