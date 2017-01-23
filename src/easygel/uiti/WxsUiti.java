package easygel.uiti;

import java.awt.*;
import java.io.*;
import ij.io.*;
import javax.swing.*;
import java.util.*;
import easygel.*;
import java.lang.reflect.*;

public class WxsUiti {
  public WxsUiti() {
  }
  public  static  void centerFrame(Frame frame,int width,int height){
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setSize(new Dimension(width,height));
    frame.setLocation((screenSize.width - width) / 2,
        (screenSize.height - height) / 2);
  }
  public static void setIcon(Frame frame){
    String systemDir,imageDir;
    Image image;
    systemDir=System.getProperties().getProperty("user.dir");
    image=Toolkit.getDefaultToolkit().createImage(systemDir+"\\ICON\\EasyGels.jpg");
    frame.setIconImage(image);
  }
  public  static  void centerDialog(Dialog dialog,int width,int height){
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    dialog.setSize(new Dimension(width,height));
    dialog.setLocation((screenSize.width - width) / 2,
        (screenSize.height - height) / 2);
  }
  public  static String getDir(String filePathName) {
    int i =  filePathName.lastIndexOf('/');
    if (i==-1)
        i = filePathName.lastIndexOf('\\');
    if (i>0)
        return filePathName.substring(0, i+1);
    else
        return "";
  }

  // 将Gif的Image img保存
  public static  void gifimgSaveToFile(Image img,String path,String name) throws IOException {
    try{
      File f = new File(path+name);
      FileOutputStream out=new FileOutputStream(f);
      GifEncoder gif=new GifEncoder(img);
      gif.write(out);
    }
    catch(IOException e){
      System.out.println(e.toString());
    }
  }

  public static double setPrecision(double data,int bits){
    String str=String.valueOf(data);
    if(str.length() >bits) str=str.substring(0,bits);
    if(str.indexOf(".")==str.length() -1)
        str=str+"0";
    Double dou;
    dou=Double.valueOf(str);
    double doub;
    doub=dou.doubleValue() ;
    return doub;
  }

  public static String setPrecision(String str,int bits){
    if(str.length() >bits) str=str.substring(0,bits);
    if(str.indexOf(".")==str.length() -1)  str=str+"0";
    return str;
  }

  public static String getSetting(User user,int no){
    String str=null;
    return str;
  }

  // 要求图像背景为：黑色！！
  public static int[] rollingBall(int []data,int r){
    int []bg=new int[data.length ];
    int []ball=new int[r*2+1];
    int min=255;
    for(int ii=0;ii<=r;ii++){
      ball[r+ii]=(int)Math.sqrt(r*r-ii*ii);
      ball[r-ii]=ball[r+ii];
    }
    for(int ii=0;ii<data.length ;ii++){
      int v=data[ii];
      bg[ii]=0;
      boolean ok=false;
      for(int jj=-1*r;jj<=v-r+1;jj++){
        for(int kk=0;kk<2*r;kk++){
          int xx=ii-r+kk;
          int yy=jj+ball[kk];
          if(xx>=0 && xx<=data.length -1){
            if(yy==data[xx]) {
              ok=true;
              bg[ii]=jj;
              if(jj<min) min=jj;
              break;
            }
          }
        }
       if(ok==true) break;
      }
    }
    System.out.println("begin");
    for(int ii=0;ii<data.length ;ii++){
      //System.out.println("ii"+ii+1+"="+bg[ii]);
      //bg[ii]-=min;
    }
    return bg;
  }

  public static Method getClassMethod(Class className,String methodName,Object []para){
    Method md[]=className.getMethods();
    Class cls[],cls1,cls2;
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
            if(cls1.equals(cls2)==false) {
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

  public static Class changeClass(Class cl){
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

  public static Object call(Class className,Object classObject,String methodName,Object []para){
    Object obj=null;
    try{
      Method method=getClassMethod(className,methodName,para);
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

}

