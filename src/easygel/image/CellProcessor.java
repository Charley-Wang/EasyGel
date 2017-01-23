package easygel.image;

import java.util.*;
import ij.process.*;
import java.awt.*;
import easygel.*;
import easygel.layer.*;
import easygel.image.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

//??? 表示问题

//the following operations all based on the coordinates:
// -------------------------->x  is width.
// |
// |
// |
// |
// V y is height.
//image data matrix from (0,0)--> in row order...

/**
 * ??? 不用继承ByteProcessor
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */
public class CellProcessor extends ByteProcessor {
  // constant values definition.
  // the input image mode: rgb mode or gray mode;
  public static final int  RGBmode=1;
  public static final int  GrayMode=2;
  // the methods to find the threshold for binarilizition.
  public static final int threshModeUser=-1;
  public static final int threshModeOtsu=0;
  public static final int threshModeWesz=1;
  public static final int threshModeWata=2;
  // the method of edge detection.
  public static final int edgeSobel=0;
  public static final int edgeRobert=1;
  public static final int edgeLap=2;
  public static final int edgePrewitt=3;
  public static final int edgeRobinson=4;
  public static final int edgeKirsch=5;
  public static final int edgeFrei=6;
  // added by wxs
  public static final int edgeNone=7;
  public static final int edgeGuassLap=8;
  public static final int edgeBing=9;
  // the following is Prewitt operators
  public static final int PrewittMask[][][]={
    {{1,1,1},{1,-2,1},{-1,-1,-1}},{{1,1,1},{-1,-2,1},{-1,-1,1}},
    {{-1,1,1},{-1,-2,1},{-1,1,1}},{{-1,-1,1},{-1,-2,1},{1,1,1}},
    {{-1,-1,-1},{1,2,1},{1,1,1}},{{1,-1,-1},{1,-2,-1},{1,1,1}},
    {{1,1,-1},{1,-2,-1},{1,1,-1}},{{1,1,1},{1,-2,-1},{1,-1,-1}}
  };
  // the following is Robinson operators
  public static final int RobinsonMask[][][]={
   {{1,2,1},{0,0,0},{-1,2,-1}},{{0,1,2},{-1,0,1},{-2,-1,0}},
   {{-1,0,1},{-2,0,2},{-1,0,1}},{{-2,-1,0},{-1,0,-1},{0,1,2}},
   {{-1,-2,-1},{0,0,0},{1,2,1}},{{0,-1,-2},{1,0,-1},{2,1,0}},
   {{1,0,-1},{2,0,-2},{1,0,-1}},{{2,1,0},{1,0,-1},{0,-1,-2}}
  };
  // the following is Kirsch  operators.
  public static final int KirschMask[][][]={
   {{5,5,5},{-3,0,-3},{-3,-3,-3}},{{5,5,-3},{5,0,-3},{-3,-3,-3}},
   {{5,-3,-3},{5,0,-3},{5,-3,-3}},{{-3,-3,-3},{5,0,-3},{5,5,-3}},
   {{-3,-3,-3},{-3,0,-3},{5,5,5}},{{-3,-3,-3},{-3,0,5},{-3,5,5}},
   {{-3,-3,5},{-3,0,5},{-3,-3,5}},{{-3,5,5},{-3,0,5},{-3,-3,-3}}
  };
  //the following is the connectivity used by function fill and clearborder.
  public static final int CONN8=0;
  public static final int CONN4=1;

  // public boolean IsSquare=false;
  // true means return square value ;otherwise
  // returns absolute value;
  protected  byte threshold;
  protected  byte []binary;
  // if not used, should be released. 各点的梯度算子值
  protected  byte []edgegray;
  protected Vector  cnturList=new Vector();
  public InfoCell m_cs_cur=null;

  //added by wxs
  private ImageProcessor ip;

  private static final int out=0;
  private static final int edge=1;
  private static final int in=2;
  private static final int newedge=3;
  private static final int notedgein=4;
  private static final int notedgeout=5;
  private static final int dealed=127;
  private static final int dealing=7;

  /** ??? ip2其他用之处
   * constructor,modified by wxs
   * @param width 图像的宽
   * @param height 图像的高
   * @param pixels 图像的灰度值,按[1,1],[2,1],[3,1];[1,2],[2,2],[3,2];...排列
   * @param ip2 用于InfoCell计算各点的灰度之用
   */
  public CellProcessor(int width,int height,byte []pixels,ImageProcessor ip2){
    super(width,height,pixels,null);
    this.ip=ip2;
    threshold=0;
  }

  /*
  public CellProcessor(Image img){
    super(img);
    threshold=0;
  }
  */

  /*
  public CellProcessor(ByteProcessor ip){
    super(ip.getWidth(),ip.getHeight());
    byte []px=new byte[ip.getWidth()*ip.getHeight()];
    byte[]py=(byte[])ip.getPixels();
    for(int i=0;i<ip.getWidth()*ip.getHeight();i++)
    {
      px[i]=py[i];
    }
    pixels=px;
    setRoi(null);
  }
  */


  /*
  //added by wxs
  public CellProcessor(ByteProcessor ip1,Rectangle ROI,ImageProcessor ip2){
    super((int)ROI.getWidth() ,(int)ROI.getHeight() );
    this.ip=ip2;
    byte []px=new byte[(int)(ROI.getWidth() *ROI.getHeight() )];
    byte []py=new byte[(int)(ROI.getWidth() *ROI.getHeight() )];
    int n=0;
    for(int ii=1;ii<=ROI.getHeight() ;ii++){
      for(int jj=1;jj<=ROI.getWidth() ;jj++){
        py[n]=(byte)ip1.getPixel(ii,jj);
        px[n]=py[n];
        n++;
      }
    }
    pixels=px;
    setRoi(null);
  }
  */

  //simple data members get/set functions.
  public int getwidth(){
    return width;
  }

  public int getheight(){
    return height;
  }

  public Object getgraypixels(){
    return this.pixels;
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

  public  Vector getCntrList(){
    return this.cnturList;
  }

  public void setgraypixels(int wid,int high,byte[]pixels){
    if(pixels==null)
    {
      width=wid;
      height=high;
      this.pixels=new byte[height*width];
    }
    if(pixels.length==wid*high)
    {
      width=wid;
      height=high;
      this.pixels=pixels;
    }
  }

  //operations

  /*
  //rescale the image to the full 8-bit gray level so that clearly find edge.
  public void rescale(){
    Rectangle rt=getRoi();
    if(rt.width==0 && rt.height==0)
      return;
    int maxval=0;
    int minval=0;
    for(int i=rt.y;i<rt.height+rt.y;i++)
      for(int j=rt.x;j<rt.width+rt.x;j++)
    {
      int temp=pixels[i*width+j]&0xff;
      if(temp>maxval)
      {
        maxval=temp;
      }
      if(temp<minval)
      {
        minval=temp;
      }
    }
   if(maxval==minval)
      return;
   for(int i=rt.y;i<rt.height+rt.y;i++)
     for(int j=rt.x;j<rt.width+rt.x;j++)
     {
       int temp=pixels[i*width+j]&0xff;
       float t=255*(temp-minval)/(maxval-minval);
        temp=(int)t;
        pixels[i*width+j]=(byte)temp;
   }
  }
  */

  /**
   * 边界查找，并二值化
   * @param nEdgeMode 使用边缘检测算子类型，默认为sobel算子，
   * @param nthreshMode 计算最佳二值化阈值的方法默认为ostu方法，
   *                    得到的阈值可能太小，导致二值化后的边界不连续，
   *                    需要乘以个系数,
   * @param coff 通常设为.1，根据结果再作适当调整。
   * @param nthresh 为直接指定二值化阈值，通常不需要。
   * @param square 用于边缘检测时是否用平方根还是绝对值，用于几种检测算子
   */
  public void searchEdge(int nEdgeMode,int nthreshMode,
                         float coff,int nthresh,boolean square){
    switch(nEdgeMode){
      case edgeSobel:
        // edgeSobel(square);break;
        edgeSobel();break;
      case edgePrewitt:
        //edgeDir(PrewittMask);break;
        edgePrewitt();break;
      case edgeRobinson:
        edgeDir(RobinsonMask);break;
      case edgeKirsch:
        edgeDir(KirschMask);break;
      case edgeRobert :
        edgeRobert(); break;
      case edgeLap:
        edgeLaplacian();break;
      case edgeGuassLap:
        edgeGuassLaplacian();break;
      case edgeBing:
        edgeBingROICut() ;break;
      default:
        edgeSobel(square);break;
    }

    if(true) return;

    int threshold=nthresh;
    switch(nthreshMode)
    {
      case threshModeOtsu:
        threshold=Otsu();
        binary(edgegray,width,height,(int)(coff*threshold));
        break;
      case threshModeWata:
        threshold=Wata();
        binary(edgegray,width,height,(int)(coff*threshold));
        break;
      case threshModeWesz:
        threshold=Wesz();
        binary(edgegray,width,height,(int)(coff*threshold));
        break;
      default:
        binary(edgegray,width,height,(int)(coff*threshold));
    }
    binary=edgegray;
    edgegray=null;
    dumpbin(binary,width,height,"ff.txt");
  }

  /**
   * 寻找边界，Sobel算子，刘勇做的
   * @param square == true  用平方根
   *                  false 用绝对值
   * 影响：生成各点梯度算子Sobel边缘算子值edgegray
   */
  private void edgeSobel(boolean square){
    byte []result=new byte[height*width];
    int maxx=width-2;
    int maxy=height-2;
    for(int i=0;i<width;i++){
      result[i]=0;
      result[(height-1)*width+i]=0;
    }
    for(int j=0;j<height;j++){
      result[j*width]=0;
      result[j*width+width-1]=0;
    }
    for(int x=1;x<=maxx;x++){
      for(int y=1;y<=maxy;y++){
          int vx=(int)(pixels[(y-1)*width+x+1]&0xff+pixels[y*width+x+1]&0xff+pixels[y*width+x+1]&0xff+pixels[(y+1)*width+x+1]&0xff-
                (pixels[(y-1)*width+x-1]&0xff+pixels[y*width+x-1]&0xff+pixels[y*width+x-1]&0xff+pixels[(y+1)*width+x-1]&0xff));
          int vy=(int)(pixels[(y+1)*width+x-1]&0xff+pixels[(y+1)*width+x]&0xff+pixels[(y+1)*width+x]&0xff+pixels[(y+1)*width+x+1]&0xff-
                (pixels[(y-1)*width+x-1]&0xff+pixels[(y-1)*width+x]&0xff+pixels[(y-1)*width+x]&0xff+pixels[(y-1)*width+x+1]&0xff));
          double v;
          if(square){
            v=vx*vx+vy*vy;
            v=Math.sqrt(v);
          }
          else{
            v=Math.abs(vx)+Math.abs(vy);
          }
          int temp=(int)v;
           result[y*width+x]=(byte)temp;
          if(temp>255) result[y*width+x]=-1;
      }
    }
    edgegray=result;
  }

  /**
   * 寻找边界
   * @param template 为掩模模板（3*3卷积核，corrvolution kernel）
   * 影响：生成各点梯度算子Sobel边缘算子值edgegray
   */
  public void edgeDir(int [][][]template){
    // just support 3*3 template.
    System.out.println("templen="+template.length );
    if(template.length!=8) return;
    byte []result=new byte[height*width];
    int maxx=width-2;
    int maxy=height-2;
    for(int i=0;i<width;i++){
      result[i]=0;
      result[(height-1)*width+i]=0;
    }
    for(int j=0;j<height;j++){
      result[j*width]=0;
      result[j*width+width-1]=0;
    }
    double v=0;
    for(int x=1;x<=maxx;x++){
      for(int y=1;y<=maxy;y++){
        double maxv=0;
        for(int it=0;it<8;it++){
          v=0;
          for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
              v=v+(pixels[(y+i-1)*width+x+j-1]&0xff)*template[it][i][j];
            }
          }
          maxv=(v>maxv)?v:maxv;
        }
        int temp=(int)maxv;
        temp=(temp>255)?255:temp;
        result[y*width+x]=(byte)temp;
      }
    }
    edgegray=result;
    for(int ii=1;ii<=width*height;ii++)
      System.out.println("result("+ii+")="+result[ii-1]);
  }

  private void edgeRobert(){
    byte []result=new byte[height*width];
    int g_v,g_h,v_v,v_h;
    for(int i=0;i<width;i++){
      for(int j=0;j<height;j++){
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
        result[j*width+i]=(byte)Math.sqrt(v_v*v_v+v_h*v_h);
      }
    }
    edgegray=result;
  }

  private void edgePrewitt(){
    byte []result=new byte[height*width];
    int g_v,g_h,v_v,v_h;
    for(int i=0;i<width;i++){
      for(int j=0;j<height;j++){
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
        result[j*width+i]=(byte)Math.sqrt(v_v*v_v+v_h*v_h);
      }
    }
    edgegray=result;
  }

  private void edgeLaplacian(){
    byte []result=new byte[height*width];
    int v,p_g;
    int []g={-1,-1,-1,-1,8,-1,-1,-1,-1};
    for(int i=0;i<width;i++){
      for(int j=0;j<height;j++){
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
        result[j*width+i]=(byte)v;
      }
    }
    edgegray=result;
  }

  private void edgeGuassLaplacian(){
    byte []result=new byte[height*width];
    int v,p_g;
    int []g={-2,-4,-4,-4,2,
              -4,0,8,0,-4,
              -4,8,24,8,-4,
              -4,0,8,0,-4,
              -2,-4,-4,-4,-2};
    for(int i=0;i<width;i++){
      for(int j=0;j<height;j++){
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
        result[j*width+i]=(byte)v;
      }
    }
    edgegray=result;
  }

  // 并行区域分割算法
  private void edgeBingROICut(){
    int nNs_y[]=new int[256];
    byte []result=new byte[height*width];

    // 计算灰度分布
    int gray;
    byte v;
    for(int i=0;i<255;i++) nNs_y[i]=0;
    for(int i=0;i<height;i++){
      for(int j=0;j<width;j++){
          v=pixels[i*width+j];
         if(((v&0x80)>>7)==1) gray=256+v;
         else gray=v;
         if(gray>=0 && gray<=255) (nNs_y[gray])++;
      }
    }

    // 迭代阈值
    int T1,T2;
    T1=200;
    T2=0;

    // 临时变量
    int temp0,temp1,temp2,temp3;
    temp0=temp1=temp2=temp3=0;
    while(true){
      System.out.println("T1="+T1);
      // 计算下一个迭代阈值
      for(int i=0;i<T1+1;i++){
        temp0+=nNs_y[i]*i;
        temp1+=nNs_y[i];
      }
      for(int i=T1+1;i<256;i++){
        temp2+=nNs_y[i]*i;
        temp3+=nNs_y[i];
      }
      T2=(temp0/temp1+temp2/temp3)/2;
      // 看迭代结果是否已收敛
      if(T1==T2) break;
      else T1=T2;
    }

    // 对各像素进行灰度转化
    int loc;
    for(int i=0;i<height;i++){
      for(int j=0;j<width;j++){
        loc=width*i+j;
        v=pixels[loc];
        if(((v&0x80)>>7)==1) gray=256+v;
        else gray=v;
        if(gray<T1) result[loc]=0;
        else result[loc]=100;
         //System.out.println("\n"+result[loc]);
         //System.out.print("x="+j+"y="+i+"      "+T1+", "+gray+",  "+v);
      }
    }
    edgegray=result;
  }

  private void edgeSobel(){
    byte []result=new byte[height*width];
    int g_v,g_h,v_v,v_h;
    for(int i=0;i<width;i++){
      for(int j=0;j<height;j++){
        g_v=g_h=v_v=v_h=0;
        for(int k=i-1;k<i+2;k++){
          for(int l=j-1;l<j+2;l++){
            if(k>=0 && l>=0 && k<width && l<height){
              if(k==i-1){
                if(l==j) g_v=-2;
                else g_v=-1;
               }
               if(k==i+1){
                 if(l==j) g_v=2;
                 else g_v=1;
               }
               if(k==i) g_v=0;

               if(l==j-1){
                if(k==i) g_h=2;
                else g_h=1;
               }
               if(l==j+1){
                 if(k==i) g_h=-2;
                 else g_h=-1;
               }
               if(l==j) g_h=0;

               v_v+=pixels[l*width+k]*g_v;
               v_h+=pixels[l*width+k]*g_h;
            }
          }
        }
        result[j*width+i]=(byte)Math.sqrt(v_v*v_v+v_h*v_h);
      }
    }
    edgegray=result;
  }


  public int getThreshold( ){
    return threshold&0xff;
  }

  public void setThreshold( int thresh){
    threshold=(byte)thresh;
  }

  public int calThreshold(int Mode){
    switch(Mode)
    {
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

  public int Otsu(){
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

  public int Wata(){
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

 public int Wesz(){
   return 127;
 }

 public int []graylevel(){
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

 public void dilate(boolean [][]mask,int high,int wid,Point pt){
   //the mask:----->y
   //         |1 0 0
   //         |0 1 0
   //         |0 1 0 i.e.
   //         v  x
   if(mask==null)
   {
     mask=new boolean[3][3];//default uses diamond structure .
     mask[0][0]=false;
     mask[0][1]=true;
     mask[0][2]=false;
     mask[1][0]=true;
     mask[1][1]=true;
     mask[1][2]=true;
     mask[2][0]=false;
     mask[2][1]=true;
     mask[2][2]=false;
     high=3;
     wid=3;
     pt.x=1;
     pt.y=1;
   }
   else if(mask.length!=high*wid)
   {
     System.out.println("Invalid parameters:during dilate");
     return;
   }
   byte []temp=new byte[height*width];
   for(int x=0;x<height;x++)
     for(int y=0;y<width;y++)
       temp[x*width+y]=0;
   for(int  x=0;x<height;x++)
     for(int y=0;y<width;y++)
     {
       if(binary[x*width+y]!=0)
       {
         for(int i=-pt.x;i<high-pt.x;i++)
           for(int j=-pt.y;j<wid-pt.y;j++)
           {
             int tmp1=x+i;
             int tmp2=y+j;
             boolean b=false;
             if( (tmp1>=0) && (tmp1<height) && (tmp2>=0) && (tmp2<width) )
             {
               b=(((binary[tmp1*width+tmp2]!=0)||mask[i+pt.x][j+pt.y]));
              if(b)
                  temp[tmp1*width+tmp2]=(byte)1;
             }
           }
       }
     }
  binary=temp;
 }

 public void erode(boolean [][]mask,int high,int wid,Point pt){
 //the mask:----->y
 //         |1 0 0
 //         |0 1 0
 //         |0 1 0 i.e.
 //         v  x
 if(mask==null)
 {
   mask=new boolean[3][3];//default uses diamond structure .
   mask[0][0]=false;
   mask[0][1]=true;
   mask[0][2]=false;
   mask[1][0]=true;
   mask[1][1]=true;
   mask[1][2]=true;
   mask[2][0]=false;
   mask[2][1]=true;
   mask[2][2]=false;
   high=3;
   wid=3;
   pt.x=1;
   pt.y=1;
 }
 else if(mask.length!=high*wid)
 {
   System.out.println("Invalid parameters:during erode");
   return;
 }
 byte []temp=new byte[height*width];
 for(int x=0;x<height;x++)
   for(int y=0;y<width;y++)
     temp[x*width+y]=0;
 for(int  x=0;x<height;x++)
   for(int y=0;y<width;y++)
   {
     if(binary[x*width+y]!=0)
     {
       boolean b=true;
       for(int i=-pt.x;i<high-pt.x;i++)
         for(int j=-pt.y;j<wid-pt.y;j++)
         {
           int tmp1=x+i;
           int tmp2=y+j;
           if( (tmp1>=0) && (tmp1<height) && (tmp2>=0) && (tmp2<width) )
           {
               if( (mask[i+pt.x][j+pt.y])  && (binary[tmp1*width+tmp2]==0) )
               {
                 b=false;
                 break;
               }
           }
           else
           {
             b=false;
             break;
           }

         }
      temp[x*width+y]=(byte)(b?1:0);
     }
   }
  binary=temp;
 }

// fillval is the value to be filled with. ptOrg is the start point.
// if scale==null,then try to fill the entire image
// else
 public void fill(byte fillval,Point ptOrg){
   Point ptStart=new Point(ptOrg);
   if(ptStart==null)
     return;
   if(fillval==0)//can't use 0 for filling operation.
     fillval=-1;// 0 means the element which will be filled
   if(binary[ptStart.y*width+ptStart.x]!=0)
   return;
   ptStart.x=ptStart.x+1;
   ptStart.y=ptStart.y+1;
   ptStart.y=(ptStart.y>=height)?height-1:ptStart.y;
   ptStart.x=(ptStart.x>=width)?width-1:ptStart.x;
   Vector queue=new Vector();
   byte []temp=new byte[(height+2)*(width+2)];
   for(int y=1;y<=height;y++)
     for(int x=1;x<=width;x++)
       temp[y*(width+2)+x]=binary[(y-1)*width+x-1];
   for(int y=0;y<=height+1;y++)
   {
     temp[y*(width+2)]=1;
     temp[y*(width+2)+width+1]=1;
   }
   for(int x=0;x<=width+1;x++)
   {
     temp[x]=1;
     temp[(height+1)*(width+2)+x]=1;
   }
   queue.addElement(ptStart);
   Point pt=null;
   while(!queue.isEmpty())
   {
     pt=(Point)queue.remove(0);
    if(temp[pt.y*(width+2)+pt.x]!=0)
      continue;
     temp[pt.y*(width+2)+pt.x]=fillval;
     if(temp[(pt.y+1)*(width+2)+pt.x]==0)
       queue.add(new Point(pt.x,pt.y+1));
     if(temp[pt.y*(width+2)+pt.x+1]==0)
       queue.add(new Point(pt.x+1,pt.y));
     if(temp[pt.y*(width+2)+pt.x-1]==0)
       queue.add(new Point(pt.x-1,pt.y));
     if(temp[(pt.y-1)*(width+2)+pt.x]==0)
       queue.add(new Point(pt.x,pt.y-1));
//     dumpbin(temp,width+2,height+2,"test.txt");
   }
   for(int x=0;x<height;x++)
     for(int y=0;y<width;y++)
     {
       binary[x*width+y]=temp[(x+1)*(width+2)+y+1];
     }
 }

 public void fillholes(){//this function will fill the cells using 0;
   // while using 1 outside..
   // dumpbin(binary,width,height,"test2.txt");
   fill((byte)2,new Point(0,0));
   for(int x=0;x<height;x++)
     for(int y=0;y<width;y++)
       if( binary[x*width+y]==2)
         binary[x*width+y]=(byte)0;
       else
         binary[x*width+y]=1;
 }

 public void clearborder(int nConnectivity){
   Vector queue=new Vector();
   if(nConnectivity==CONN4)
     clrbdr_init4(queue);
   else
     clrbdr_init8(queue);
  while(!queue.isEmpty())
  {
    Point pt=(Point)queue.remove(0);
    if(nConnectivity==CONN4)
    {
      if(binary[pt.y*width+pt.x+1]!=0)
      {
        binary[pt.y*width+pt.x+1]=0;
        queue.add(new Point(pt.x+1,pt.y));
      }
      if(binary[pt.y*width+pt.x-1]!=0)
      {
        binary[pt.y*width+pt.x-1]=0;
        queue.add(new Point(pt.x-1,pt.y));
      }
      if(binary[(pt.y+1)*width+pt.x]!=0)
      {
        binary[(pt.y+1)*width+pt.x]=0;
        queue.add(new Point(pt.x,pt.y+1));
      }
      if(binary[(pt.y-1)*width+pt.x]!=0)
      {
        binary[(pt.y-1)*width+pt.x]=0;
        queue.add(new Point(pt.x,pt.y-1));
      }
    }
    else
    {
      for(int i=-1;i<2;i++)
        for(int j=-1;j<2;j++)
        {
          if(binary[(pt.y+i)*width+pt.x+j]!=0)
          {
            binary[(pt.y+i)*width+pt.x+j]=0;
            queue.add(new Point(pt.x+j,pt.y+i));
          }
        }
    }
  }
 }

 private void clrbdr_init8(Vector queue){
   if(binary[0]!=0)
   {
     binary[0]=0;
     if(binary[width+1]!=0)
     {
       binary[width+1]=0;
       queue.add(new Point(1,1));
     }
   }
  if(binary[(height-1)*width]!=0)
  {
    binary[(height-1)*width]=0;
    if(binary[(height-2)*width+1]!=0)
    {
      binary[(height-2)*width+1]=0;
      queue.add(new Point(1,height-2));
    }
  }
  if(binary[width-1]!=0)
  {
    binary[width-1]=0;
    if(binary[width+width-2]!=0)
    {
      binary[width+width-2]=0;
      queue.add(new Point(width-2,1));
    }
  }
  if(binary[(height-1)*width+width-1]!=0)
  {
    binary[(height-1)*width+width-1]=0;
    if(binary[(height-2)*width+width-2]!=0)
    {
      binary[(height-2)*width+width-2]=0;
      queue.add(new Point(width-2,height-2));
    }
  }
 //to clear the left and right border...
  for(int x=1;x<height-1;x++)
  {
    if(binary[x*width]!=0)
      for(int i=-1;i<2;i++)
      {
        if(binary[(x+i)*width+1]!=0)
        {
           binary[(x+i)*width+1]=0;
          if( (x+i==0)||(x+i==height-1) )
            continue;
          queue.add(new Point(1,x+i));
        }
      }
    if(binary[x*width+width-1]!=0)
      for(int i=-1;i<2;i++)
      {
        if(binary[(x+i)*width+width-2]!=0)
        {
           binary[(x+i)*width+width-2]=0;
          if( (x+i==0)||(x+i==height-1))
            continue;
          queue.add(new Point(width-2,x+i));
        }
     }
  }
  //to clear the top and bottom border.......
  for(int y=1;y<width-1;y++)
  {
    if(binary[y]!=0)
      for(int i=-1;i<2;i++)
      {
        if(binary[width+y+i]!=0)
        {
           binary[width+y+i]=0;
           if((y+i==0)||(y+i==width-1) )
             continue;
           queue.add(new Point(y+i,1));
        }
      }
    if(binary[(height-1)*width+y]!=0)
      for(int i=-1;i<2;i++)
      {
        if(binary[(height-2)*width+y+i]!=0)
        {
          binary[(height-2)*width+y+i]=0;
          if((y+i==0)||(y+i==width-1) )
             continue;
          queue.add(new Point(y+i,height-2));
        }
     }
  }
 for(int x=0;x<height;x++)
 {
   binary[x*width]=0;
   binary[x*width+width-1]=0;
 }
 for(int y=0;y<width;y++)
 {
   binary[y]=0;
   binary[y+(height-1)*width]=0;
 }
//  dumpbin(binary,width,height,"test.txt");
 }

 private void clrbdr_init4(Vector queue){
   binary[0]=0;
   binary[width-1]=0;
   binary[(height-1)*width]=0;
   binary[(height-1)*width+width-1]=0;
   //to clear the left and right border...
   for(int x=1;x<height-1;x++)
   {
     if( (binary[x*width]!=0) && (binary[x*width+1]!=0) )
     {
     //       binary[x][0]=0;
       binary[x*width+1]=0;
       queue.add(new Point(1,x));
     }
     binary[x*width]=0;
     if( (binary[x*width+width-1]!=0) && (binary[x*width+width-2]!=0) )
     {
       binary[x*width+width-2]=0;
       queue.add(new Point(width-2,x));
     }
     binary[x*width+width-1]=0;
   }
   //to clear th top and bottom border.......
   for(int y=1;y<width-1;y++)
   {
     if( (binary[y]!=0) && (binary[width+y]!=0) )
     {
       binary[width+y]=0;
       queue.add(new Point(y,1));
     }
     binary[y]=0;
     if( (binary[(height-1)*width+y]!=0) && (binary[(height-2)*width+y]!=0) )
     {
       binary[(height-2)*width+y]=0;
       queue.add(new Point(y,height-2));
     }
     binary[(height-1)*width+y]=0;
   }
 }

 // return the contour number
 public int cnturFind(){
   Vector queue=new Vector();
   for(int i=0;i<height;i++)
   {
     binary[i*width]=2;
     binary[i*width+width-1]=2;
     Conturpt p=new Conturpt(new Point(0,i),6);
     queue.add(p);
   }
   Conturpt pt=(Conturpt)queue.remove(0);
   pt.s=8;
   queue.add(0,pt);
   //initializing.
   //contour searching begin from the left border.
   for(int j=0;j<width;j++)
   {
     binary[j]=2;
     binary[(height-1)*width+j]=2;
   }
   //  dumpbin(binary,width,height,"test.txt");
   int size=queue.size();

   while( size>0)
   {
     Conturpt pp=(Conturpt)queue.remove(0);
     Point curPt=pp.pt;
     int s=pp.s;
     int s0=s;
     if( (s==8) && ( size>1) ){
         s0=s;
         Conturpt pttemp=(Conturpt)queue.remove(0);
         curPt=new Point(pttemp.pt);
         s=pttemp.s;
     }
     else if (s==8)  {
        break;
     }

     if( (s0>3) &&( s0<8) && (s>4) &&(s<8) ){
       int p=curPt.x;
       //  %%%%%%%%%%
       while (p<width-3 )  {
         byte a=binary[curPt.y*width+p+1];
         byte b=binary[curPt.y*width+p+2];
         byte c=binary[curPt.y*width+p+3];
         if( (( a==0) &&( b==1)) || ((a==0) && (b==2) && (c==0)) ) {
           Rectangle rect=new Rectangle();
           //  dumpbin(binary,width,height,"test2.txt");
           Vector trace=cnturTrace(new Point(p+2,curPt.y),rect);
           if(trace==null)   break;
           int nsize=trace.size();
           Polygon cntur=new Polygon();
           //the last point is identical to the origin.
           for(int i=0;i<nsize-1;i++) {
             //add the contour into the total.
             //add the point to the queue.
             Conturpt cnpt=(Conturpt)trace.remove(0);
             Point tempp=cnpt.pt;
             cntur.addPoint(tempp.x,tempp.y);
             queue.add(cnpt);
           }
          int no=cnturList.size()+1;

          JOptionPane.showMessageDialog(null,"wring in CellProcessor!!!!!!!!!!!!!!!!!!!!");
          // if use , will change following lines
          //InfoCell cl=new InfoCell(cntur,pixels,width,height,false,no,ip);
          //cnturList.add(cl);
          break;
         }
         else if( ((a==0) && ( b>2 )) || ((a==1)&& (b==2)) ){
           break;
         }
         p=p+1;
       }
     }
     s0=s;
     size=queue.size();
   }
   System.out.println("before dumpbin");
   dumpbin(binary,width,height,"teste.txt");
   System.out.println("after dumpbin");
   return cnturList.size();
 }

 public Vector cnturTrace(final Point ptStart,Rectangle rt){
   final Point ptOrg=new Point(ptStart);
   if(binary[ptOrg.y*width+ptOrg.x]==0)
     return null;
   if(binary[ptOrg.y*width+ptOrg.x-1]!=0)
     return null;
   Vector trace=new Vector();
   Point topleft=new Point(ptStart);
   Point btmright=new Point(ptStart);
  //begin contour tracing....
  //
   Point curPt=new Point(ptOrg.x,ptOrg.y);
   //add the start point into contour.
   binary[curPt.y*width+curPt.x]++;
   trace.add(new Conturpt(new Point(curPt),8));
   //
   boolean first=true;
   boolean found=false;
   int s=6;
   while( (curPt.x!=ptOrg.x) || (curPt.y!=ptOrg.y) || first )
   {
     found=false;
     int i=0;
     while( (!found) && (i<3) )
     {
        i=i+1;
       int temp1=(s+7)%8;//attention!!!!!!!!!!!
        Point pt=neigb(curPt,temp1);
        Point pt2=neigb(curPt,s);
        temp1=(s+1)%8;
        Point pt3=neigb(curPt,temp1);
        if( (pt.x<0)||(pt.x>=width) || (pt.y<0)||(pt.y>=height) )
          return null;
        if( (pt2.x<0)||(pt2.x>=width) || (pt2.y<0)||(pt2.y>=height) )
                  return null;
        if( (pt2.x<0)||(pt2.x>=width) || (pt2.y<0)||(pt2.y>=height) )
                  return null;
        if( (pt3.x<0)||(pt3.x>=width) || (pt3.y<0)||(pt3.y>=height) )
          return null;
        byte tmp=binary[pt.y*width+pt.x];
        byte tmp1=binary[pt2.y*width+pt2.x];
        byte tmp2=binary[pt3.y*width+pt3.x];
        if( tmp>0 )
        {
          //add the point into contour.
          trace.add(new Conturpt(new Point(pt),s));
          binary[pt.y*width+pt.x]++;
          //
          curPt.x=pt.x;
          curPt.y=pt.y;
          s=(s+6)%8;
          found=true;
        }
        else if (tmp1>0)
        {
          //add the pt2 into contour.
          trace.add(new Conturpt(new Point(pt2),s));
          binary[pt2.y*width+pt2.x]++;
          //
          curPt.x=pt2.x;
          curPt.y=pt2.y;
          found=true;
        }
        else if ( tmp2>0)
        {
          //add the pt3 into contour.
          trace.add(new Conturpt(new Point(pt3),s));
          binary[pt3.y*width+pt3.x]++;
          //
          curPt.x=pt3.x;
          curPt.y=pt3.y;
          found=true;
        }
        else
           s=(s+2)%8;
     }
    if( i==3 )
        break;
   //find the minimum rectangle that can contain the contour.
    Conturpt t=(Conturpt)trace.get(trace.size()-1);
    btmright.x=(t.pt.x>btmright.x)?t.pt.x:btmright.x;
    btmright.y=(t.pt.y>btmright.y)?t.pt.y:btmright.y;
    topleft.x=(t.pt.x<topleft.x)?t.pt.x:topleft.x;
    topleft.y=(t.pt.y<topleft.y)?t.pt.y:topleft.y;
    first=false;
   }
  if( (curPt.x==ptOrg.x) && (curPt.y==ptOrg.y) )
  {
    binary[ptOrg.y*width+ptOrg.x]--;
    Conturpt poi=(Conturpt)trace.get(trace.size()-1);
    poi.s=8;
  }
  rt.setBounds(topleft.x,topleft.y,btmright.x-topleft.x,btmright.y-topleft.y);
  return trace;
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
           System.out.println("No:"+v.size()+","+ pg);
           for(yyy=miny;yyy<=maxy;yyy++){
             for(xxx=minx;xxx<=maxx;xxx++){
               if(binary[yyy*width+xxx]==dealing) binary[yyy*width+xxx]=dealed;
               else{
                 if(pg.contains(xxx,yyy)==true)
                   binary[yyy*width+xxx]=dealed;
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


 public void calgeometry(){
   InfoCell cs;
   if( (cnturList==null) || (cnturList.isEmpty( )) )
       return;
   int nsize=cnturList.size();
   for(int x=0;x<nsize;x++)
   {
     cs=(InfoCell)cnturList.get(x);
     cs.calgeom();
   }
 }

 public static Point neigb(Point p,int s){
  switch(s%8)
  {
    case 0:
      return new Point(p.x+1,p.y);
    case 1:
      return new Point(p.x+1,p.y-1);
    case 2:
      return new Point(p.x,p.y-1);
    case 3:
      return new Point(p.x-1,p.y-1);
    case 4:
      return new Point(p.x-1,p.y);
    case 5:
      return new Point(p.x-1,p.y+1);
    case 6:
      return new Point(p.x,p.y+1);
    case 7:
      return new Point(p.x+1,p.y+1);
    default:
      return new Point(p);
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
 //滚球法去本底
 public void DelBgnd(int nRadius){
   if( (nRadius>255) || (nRadius<=0) )
     return ;
   Rectangle rt=getRoi();
  Vector ptlist=(Vector)MathEx.ArcBresh(nRadius,new Point(nRadius,nRadius));
  Polygon pg=new Polygon();
  for(int i=0;i<ptlist.size();i++)
  {
    Point pt=(Point)ptlist.remove(0);
    pg.addPoint(pt.x,pt.y);
  }
  CellFillReturn cs=InfoCell.fillCell(pg,(short)1);
  for(int i=0;i<cs.m_rect.width;i++)
    for(int j=0;j<cs.m_rect.height;j++)
    {
      if(cs.m_mask[i][j]!=0)
      {
        cs.m_mask[i][j]=(short)Math.sqrt(nRadius*nRadius-(i-nRadius)*(i-nRadius)-(j-nRadius)*(j-nRadius));
      }
    }
  rt.setBounds(rt.x+nRadius,rt.y+nRadius,rt.width-2*nRadius,rt.height-2*nRadius);
  byte[][]bgnd=new byte[rt.height][rt.width];
  for(int y=rt.y;y<rt.y+rt.height;y++)
    for(int x=rt.x;x<rt.x+rt.width;x++)
    {
      bgnd[y-rt.y][x-rt.x]=0;
      boolean bflag=false;
      int i=(pixels[y*width+x]&0xff)-nRadius;
      for(;i>=0;i--)
      {
        bflag=false;
        for(int j=0;j<cs.m_rect.width;j++)
        {
          if(bflag)
            break;
          for(int k=0;k<cs.m_rect.height;k++)
          {
            if( (pixels[(y+k-nRadius)*width+x+j-nRadius]&0xff) <cs.m_mask[k][j])
            {
              bflag=true;
              break;
            }
          }
        }
       if(bflag)
         continue;
       bgnd[y-rt.y][x-rt.x]=(byte)i;
       break;
      }
    }
  for(int y=rt.y;y<rt.y+rt.height;y++)
    for(int x=rt.x;x<rt.x+rt.width;x++)
      pixels[y*width+x]=(byte)(pixels[y*width+x]&0xff-(int)bgnd[y-rt.y][x-rt.x]);
 }
 //平均值去本底
 /*
 public void DelBgndCnst(byte val){
   int navg=0;
   if(m_cs_cur!=null)
   {
     double narea=m_cs_cur.getarea();
     double nsum=m_cs_cur.getGraySum();
     short[][]mask=m_cs_cur.getmask();
     navg=(int)(narea/nsum);
     Rectangle rt=m_cs_cur.getBounds();
     for(int y=rt.y;y<rt.y+rt.height;y++)
       for(int x=rt.x;x<rt.x+rt.width;x++)
         if(mask[y-rt.y][x-rt.x]!=0)
           pixels[y*width+x]=(byte)( (pixels[y*width+x]&0xff)-val );
  }
  else
  {
    Rectangle rt=getRoi();
    for(int y=rt.y;y<rt.y+rt.height;y++)
     for(int x=rt.x;x<rt.x+rt.width;x++)
       pixels[y*width+x]=(byte)( (pixels[y*width+x]&0xff)-val );
  }
 }
 public void DelBgndAvg(){
   int navg=0;
   if(m_cs_cur!=null)
   {
     double narea=m_cs_cur.getarea();
     double nsum=m_cs_cur.getGraySum();
     navg=(int)(narea/nsum);
   }
   else
   {
     Rectangle rt=getRoi();
     long graysum=0;
     for(int y=rt.y;y<rt.y+rt.height;y++)
      for(int x=rt.x;x<rt.x+rt.width;x++)
        graysum=graysum+(pixels[y*width+x]&0xff);
     navg=(int)(graysum/(rt.width*rt.height));
   }
   DelBgndCnst((byte)navg);
 }
 public void DelBgndMin(){
   int nMin;
   Rectangle rt=getRoi();
   nMin=pixels[rt.y*width+rt.x]&0xff;
   for(int y=rt.y;y<rt.y+rt.height;y++)
     for(int x=rt.x;x<rt.x+rt.width;x++)
       if((pixels[y*width+x]&0xff)<nMin)
         nMin=pixels[y*width+x]&0xff;
   DelBgndCnst((byte)nMin);
 }
 */
 //返回本底值
 public static byte[]Rolling(byte[]graydata,int nRadius){
   int[]mask=new int[2*nRadius-1];
   byte[]bgnd=new byte[graydata.length];
   byte[]x=new byte[graydata.length+2*nRadius];
   for(int i=0;i<nRadius;i++)
     x[i]=graydata[0];
   for(int i=nRadius;i<graydata.length+nRadius;i++)
     x[i]=graydata[i-nRadius];
   for(int i=graydata.length+nRadius;i<graydata.length+2*nRadius;i++)
     x[i]=graydata[graydata.length-1];
   for(int i=0;i<nRadius;i++)
   {
     mask[i+nRadius-1]=(int)Math.sqrt(nRadius*nRadius-i*i);
     mask[nRadius-i-1]=mask[i+nRadius-1];
   }
   for(int i=nRadius;i<x.length-nRadius;i++)
   {
     int val=x[i]&0xff;
     boolean bcon=false;
     bgnd[i-nRadius]=0;
     for(int h=val-nRadius;h>=0;h--)
     {
       bcon=false;
       for(int j=0;j<2*nRadius-1;j++)
       {
         int temp=x[i-nRadius+1+j]&0xff;
        if(temp<(h+mask[j]))
         {
             bcon=true;
            break;
         }
       }
       if(!bcon)
       {
         bgnd[i-nRadius]=(byte)h;
         break;
       }
     }
   }
   return bgnd;
 }

 public static int[]Rolling(int[]graydata,int nRadius){
   int[]mask=new int[2*nRadius-1];
   int[]bgnd=new int[graydata.length];
   int[]x=new int[graydata.length+2*nRadius];
   for(int i=graydata.length+nRadius;i<graydata.length+2*nRadius;i++)
     x[i]=graydata[graydata.length-1];
   for(int i=0;i<nRadius;i++)
     x[i]=graydata[0];
   for(int i=nRadius;i<graydata.length+nRadius;i++)
     x[i]=graydata[i-nRadius];
   for(int i=0;i<nRadius;i++)
   {
     mask[i+nRadius-1]=(int)Math.sqrt(nRadius*nRadius-i*i);
     mask[nRadius-i-1]=mask[i+nRadius-1];
   }
   for(int i=nRadius;i<x.length-nRadius;i++)
   {
     //int val=x[i]&0xff;
     int val=x[i];
     boolean bcon=false;
     bgnd[i-nRadius]=0;
     for(int h=val-nRadius;h>=0;h--)
     {
       bcon=false;
       for(int j=0;j<2*nRadius-1;j++)
       {
         int temp=x[i-nRadius+1+j]&0xff;
         if(temp<(h+mask[j]))
         {
             bcon=true;
            break;
         }
       }
       if(!bcon)
       {
         bgnd[i-nRadius]=(byte)h;
         break;
       }
     }
   }

   for(int ii=1;ii<=graydata.length ;ii++){
     if(bgnd[ii-1]<0) bgnd[ii-1]=0;
     System.out.println("old="+graydata[ii-1]+",bg="+bgnd[ii-1]);
   }
   return bgnd;
 }


 public void DelBgndRolling(int nRadius){
   Rectangle rt=getRoi();
   if(rt==null)
    {
     rt=new Rectangle(0,0,width,height);
     setRoi(rt);
    }
   byte[]x=new byte[rt.height];
   for(int i=rt.y;i<rt.height+rt.y;i++)
   {
     int tmp=0;
     for(int j=rt.x;j<rt.x+rt.width;j++)
       tmp=tmp+(pixels[i*width+j]&0xff);
     x[i-rt.y]=(byte)(tmp/rt.width);
   }
   byte[]bgnd=Rolling(x,nRadius);
   for(int i=rt.y;i<rt.height+rt.y;i++)
     for(int j=rt.x;j<rt.x+rt.width;j++)
       pixels[i*width+j]=(byte)(pixels[i*width+j]-bgnd[i-rt.y]);
   // removed by wxs 因为想要把CellProcessor,不知是否其他处可以???
   //rescale();
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

 /*
 private byte[] template(byte[] lpDIBBits, int lWidth, int lHeight,
                         int iTempH, int iTempW,
                         int iTempMX, int iTempMY,
                         float fpArray, float fCoef) {
   int lpSrc,lpDst;
   byte []lpNewDIBBits;
   float []result;
   // 计算结果
   float fResult;
   // 初始化图像为原始图像
   lpNewDIBBits= lpDIBBits;

   int i,j,k,l;

   // 行(除去边缘几行)
   for(i = iTempMY; i < lHeight - iTempH + iTempMY + 1; i++) {
     // 列(除去边缘几列)
     for(j = iTempMX; j < lWidth - iTempW + iTempMX + 1; j++) {
       // 指向新DIB第i行，第j个象素的指针
       lpDst = lWidth * (lHeight - 1 - i) + j;
       fResult = 0;
       // 计算
       for (k = 0; k < iTempH; k++) {
         for (l = 0; l < iTempW; l++) {
           // 指向DIB第i - iTempMY + k行，第j - iTempMX + l个象素的指针
           lpSrc = lWidth * (lHeight - 1 - i + iTempMY - k) + j - iTempMX + l;
           // 保存象素值
           fResult +=lpNewDIBBits[lpSrc] * fpArray[k * iTempW + l];
         }
       }
       // 乘上系数
       fResult *= fCoef;
       // 取绝对值
       fResult = Math.abs(fResult);
       // 判断是否超过255
       if(fResult > 255.0f) {
         // 直接赋值为255
         result[lpDst] = (byte)255;
       }
       else {
         // 赋值
         result[lpDst] =(byte)( fResult + 0.5f);
       }
     }
   }

   // 复制变换后的图像
   return result;
 }
 */


 public Image CreateBinImage(){
   //   if(cm==null)
   //     makeDefaultColorModel();
   byte []r=new byte[2];
   r[0]=0;
   r[1]=-1;
   byte []g=new byte[2];
   g[0]=0;
   g[1]=-1;
   byte[]b=new byte[2];
   b[0]=0;
   b[1]=-1;
   cm=new IndexColorModel(1,2,r,g,b,null);
   MemoryImageSource src=new MemoryImageSource(width, height, cm,binary, 0, width);
   src.setAnimated(true);
   src.setFullBufferUpdates(true);
   Image img = Toolkit.getDefaultToolkit().createImage(src);
   return img;
 }

 public static void dumpbin(byte[]x,int nwidth,int nheight,String filename){
   try
   {
     FileOutputStream sout=new FileOutputStream(filename);
     PrintStream ps=new PrintStream(sout);
     for(int i=0;i<nwidth*nheight;i++)
     {
       if((i%nwidth)==0)
         ps.print('\n');
       ps.print(x[i]&0xff);
     }
     ps.close();
   }
   catch(java.io.IOException e)
   {
     System.out.print(e.toString());
   }
 }

}

