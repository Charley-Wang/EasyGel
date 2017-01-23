package easygel;
import java.math.*;
import java.awt.*;
import java.util.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class MathEx {

  public MathEx() {
  }
  //height is the total number of the linear equations
  //width is the number of the variables and the constant
  //return value is the vector contained of the resolution.
  //if null means wrong parameters
  // may be caused by null input
  // not enough re.....
  static  Object gauss(double[][]mat,int height,int width){
   System.out.println(mat.length);
    if( (mat==null) ||(mat.length!=height) )
        return null;
   double [][]res=new double[height][width];
   double eps=1e-19;
   if(height+1<width)
     return null;
   for(int i=0;i<height;i++)
     for(int j=0;j<width;j++)
       res[i][j]=mat[i][j];
   for(int k=0;k<height;k++)
   {
     int k1=k+1;
     double p=0;
     int j1=k;
     for(int j=k;j<height;j++)
     {
       if(Math.abs(res[j][k])>=Math.abs(p))
       {
          p=res[j][k];
          j1=j;
       }
     }
     if(j1!=k)
     {
       for(int j=k;j<width;j++)
       {
         double t=res[k][j];
         res[k][j]=res[j1][j];
         res[j1][j]=t;
       }
     }
     double c=res[k][k];
     if(Math.abs(c)<eps)
       return null;
     for(int j=k;j<width;j++)
       res[k][j]=res[k][j]/c;
     for(int i=k1;i<height;i++)
     {
       if(Math.abs(res[i][k])<eps)
         continue;
       double temp=res[i][k];
       for(int j=k;j<width;j++)
       {
        res[i][j]=res[i][j]/temp;
        res[i][j]=res[i][j]-res[k][j];
        System.out.println(res[i][j]);
       }
     }
    }
 /* for(int it=2;it<height;it++)
  {
      int i=width-it;
      int nn=height-1;
      for(int j=i;j<nn;j++)
      {
        res[i][width]=res[i][width]-res[i][j+1]*res[j+1][width];
      }
    }*/
    double []coff=new double[width-1];
    for(int i=width-2;i>=0;i--)
    {
      coff[i]=res[i][width-1];
      for(int j=width-2;j>i;j--)
      {
        coff[i]=coff[i]-res[i][j]*coff[j];
      }
    }
    return coff;
  }
  public static Object polyfit(double[]x,double []y,int nOrder){
    if( (x==null) ||(y==null) ||(x.length!=y.length) )
    {
      return null;
    }
    int ndata=x.length;
    double []temp2=new double[ndata];
    double [][]p=new double[nOrder+1][nOrder+2];//增广矩阵
    int nsize=2*nOrder+1;
    double []temp3=new double[nsize];
    double res=0;
    for(int i=0;i<ndata;i++)
      temp2[i]=1;
    for(int j=0;j<=nOrder;j++)
    {
      res=0;
      p[j][nOrder+1]=0;
      for(int i=0;i<ndata;i++)
      {
        res=res+temp2[i];
        p[j][nOrder+1]=p[j][nOrder+1]+temp2[i]*y[i];
        temp2[i]=temp2[i]*x[i];
      }
      temp3[j]=res;
    }
    for(int j=nOrder+1;j<nsize;j++)
    {
      res=0;
      for(int i=0;i<ndata;i++)
      {
        res=res+temp2[i];
        temp2[i]=temp2[i]*x[i];
      }
      temp3[j]=res;
    }
    for(int i=0;i<=nOrder;i++)
      for(int j=0;j<=nOrder;j++)
        p[i][j]=temp3[nOrder-j+i];
    double []result=(double[])gauss(p,nOrder+1,nOrder+2);
    return result;
  }
  public static Object lineDDA(Point ptStart,Point ptEnd){
    double k;
    double x,y,x_inc,y_inc;
    x=ptStart.x;
    y=ptStart.y;
    Vector ptList=new Vector();
    k=Math.abs(ptEnd.x-ptStart.x);
    if(Math.abs(ptEnd.y-ptStart.y)>k)
    {
      k=Math.abs(ptEnd.y-ptStart.y);
    }
    x_inc=(ptEnd.x-ptStart.x)/k;
    y_inc=(ptEnd.y-ptStart.y)/k;
    for(int i=1;i<=k;k++)
    {
      ptList.add(ptList.size(),new Point((int)x,(int)y));
      x=x+x_inc;
      y=y+y_inc;
    }
    return ptList;
  }
 public static Object lineBresh(Point ptStart,Point ptEnd){
   double x=ptStart.x;
   double y=ptStart.y;
   double delta_x=Math.abs(ptEnd.x-ptStart.x);
   double delta_y=Math.abs(ptEnd.y-ptStart.y);
   double flag1=(ptEnd.x-ptStart.x>=0)?1:-1;
   double flag2=(ptEnd.y-ptStart.y>=0)?1:-1;
   boolean bchang=false;
   Vector ptList=new Vector();
   if(delta_y>delta_x)
   {
     double temp=delta_x;
     delta_x=delta_y;
     delta_y=temp;
     bchang=true;
   }
   else
   {
     bchang=false;
   }
   double f=2*delta_y-delta_x;
   for(int i=1;i<delta_x;i++)
   {
     ptList.add(ptList.size(),new Point((int)x,(int)y));
     if(f>=0)
     {
       if(bchang)
         x=x+flag1;
       else
         y=y+flag2;
       f=f-2*delta_x;
     }
     if(bchang)
       y=y+flag2;
     else
       x=x+flag1;
     f=f+2*delta_y;
   }
   return ptList;
 }
 public static Object ArcBresh(int nRadius,Point ptOrg){
   int x=0;
   int y=nRadius;
   int d=3-2*nRadius;
   Vector ptList=new Vector();
   while(x<y)
   {
     ptList.add(new Point(x,y));
     if(d<0)
       d=d+4*x+6;
     else
     {
       d=d+4*(x-y)+10;
       y--;
     }
     x++;
   }
   if(x==y)
     ptList.add(new Point(x,y));
   int nsize=ptList.size();
   int nTobecopied=nsize;
   if(x==y)
     nTobecopied=nsize-1;
   for(int i=nTobecopied-1;i>=0;i--)
   {
     Point p=(Point)ptList.get(i);
     Point tmp=new Point(p.y,p.x);
     ptList.add(tmp);
   }
   nsize=ptList.size();
   for(int i=nsize-2;i>=0;i--)
   {
     Point p=(Point)ptList.get(i);
     Point tmp=new Point(p.x,-p.y);
     ptList.add(tmp);
   }
   nsize=ptList.size();
   for(int i=nsize-2;i>=1;i--)
   {
     Point p=(Point)ptList.get(i);
     Point tmp=new Point(-p.x,p.y);
     ptList.add(tmp);
   }
   nsize=ptList.size();
   for(int i=0;i<nsize;i++)
   {
     Point p=(Point)ptList.get(i);
     p.x=p.x+ptOrg.x;
     p.y=p.y+ptOrg.y;
   }
   return ptList;
 }

 public static double[] PolyVal(double[]coff,double[]x){
   if( (coff==null)||(x==null) )
     return null;
   double[] val=new double[x.length];
   double xpow=1;
   for(int j=0;j<x.length;j++)
   {
     xpow=1;
     val[j]=0;
     for(int i=0;i<coff.length;i++)
     {
       val[j]=val[j]+coff[coff.length-i-1]*xpow;
       xpow=xpow*x[j];
     }
   }
   return val;
 }

 public static double[] logValue(double[]coff,double[]x){
   if( (coff==null)||(x==null) )  return null;
   double[] val=new double[x.length];
   // y=a*logx+b
   double a=coff[0],b=coff[1];
   double log10=Math.log(10);
   for(int j=0;j<x.length;j++) {
     val[j]=b+a*(Math.log(x[j]))/(log10);
     //val[j]=Math.exp((x[j]-b)*Math.log(10)/a);
   }
   return val;
 }

 public static double[] lnValue(double[]coff,double[]x){
   if( (coff==null)||(x==null) )  return null;
   double[] val=new double[x.length];
   // y=a*lnx+b
   double a=coff[0],b=coff[1];
   for(int j=0;j<x.length;j++) {
     val[j]=b+a*(Math.log(x[j]));
     //val[j]=Math.exp((x[j]-b)/a);
   }
   return val;
 }

}