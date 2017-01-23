package easygel;

/**
 * <p>Title:总体说明</p>
 * <p>???:表示仍有问题 </p>
 * <p>！！！:表示编程心得 </p>
 */

import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.util.*;

import easygel.*;
import easygel.setting.*;

import java.net.*;

import cryptix.security.rsa.*;
import cryptix.security.*;
import cryptix.math.*;

// --------------------------------   此为EasyGel的最初启动文件

public class AppEasyGel extends ClassLoader{
  EasyGelSet easySet;

  public AppEasyGel() {
    // spMessage , will be delete
    //JOptionPane.showMessageDialog(null,"Hello 20030918");

    // set current time
    int now0;
    long mills;
    Time time;
    mills=System.currentTimeMillis();
    time=new Time(mills);
    now0=time.getMinutes()*60+time.getSeconds();

    // get basic setting of the EasyGels
    String sysDir=System.getProperties().getProperty("user.dir");
    File file=new File(sysDir+"\\database\\EasyGels.ini");
    easySet=new EasyGelSet();
    if(file.exists() ==true){
      try{
        ObjectInputStream in= new ObjectInputStream(new FileInputStream(file));
        easySet=(EasyGelSet)in.readObject();
      }
      catch(IOException e){
        e.printStackTrace();
      }
      catch(ClassNotFoundException e){
        e.printStackTrace();
      }
    }
    else{
      easySet.iconSize=25;
      easySet.imageDir=sysDir+"\\image";
      easySet.waitSeconds=1;
      easySet.paraPre="";
    }

    // 让JavaVM等待easySet.waitSeconds秒时间
    long now;
    mills=System.currentTimeMillis();
    time=new Time(mills);
    now=time.getMinutes()*60+time.getSeconds();
    if(now-now0<0) now=now+60*60;
    while(now-now0<easySet.waitSeconds){
      mills=System.currentTimeMillis();
      time=new Time(mills);
      now=time.getMinutes()*60+time.getSeconds();
      if(now-now0<0) now=now+60*60;
    }

    // 停止JavaVM等待
    File file2=new File(sysDir+"\\database\\EasyGels.CNN");
    if(file2.exists()==true) {
      file2.delete();
    }

    // Class定义
    Class layerLine=null;
    Class classDog=null;

    // 正式运行本Java系统
    File file3=new File(sysDir+"\\database\\LayerLine.class");
    if(file3.exists() ==true){
      try{
        int len=(int)file3.length();
        byte data[]=new byte[len];
        FileInputStream in2=new FileInputStream(file3);
        in2.read(data);
        in2.close();
        layerLine=defineClass("LayerLine",data,0,len);
      }
      catch(IOException e){
        e.printStackTrace();
      }
    }

    file3=new File(sysDir+"\\database\\GSDOG.class");
    if(file3.exists() ==true){
      try{
        int len=(int)file3.length();
        byte data[]=new byte[len];
        FileInputStream in2=new FileInputStream(file3);
        in2.read(data);
        in2.close();
        classDog=defineClass("GSDOG",data,0,len);
      }
      catch(IOException e){
        e.printStackTrace();
      }
    }

    this.checkDog(classDog);

    /*
    layerLine=myDefineClass("layerline.en","LayerLine",
                            "4187A9D84E302F8778E7E37268921475390D90A3B81D1F14E0F3F9991A146DBE",
                            "CC01FCF6A8B4A9D3AE0D6CC28E7EC306AE50122D045E230A04FED3FABDF15325",
                            "11",
                            "B8B1FC6E60234F01419326F7AB09079302FDEF3E28A94FF485DCF1FE497C83F7",
                            "1E4D7F03B2961F4A44DA815A9F5C8439E321EFABC636195D3AE522D01611B260C0C42901646C473AA9063D517B175074665C54B7226E2206AED4DDD4A488BDC5",
                            "932F445B1A46E11F979306939853A6D006128C1DC2BD9FC4D4EB3B606B316269BF482E4739C152CD492C98FCD88B516159579CE4AFB0AA8CB777FB6F944F27B3");
    */
    FrameMain myFrameMain=new FrameMain(layerLine);
  }

  //Main method
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    AppEasyGel hello=new AppEasyGel();
  }

  private void checkDog(Class classDog){

  }

  private Class myDefineClass(String encryptFileName,String className,
                              String su,String sq,String se,
                              String sp,String sd,String sn){
    Class cls=null;

    String systemDir=System.getProperties().getProperty("user.dir");
    String sourceFileName=systemDir+"\\database\\"+encryptFileName;

    // open the file to decrypt
    File file = new File(sourceFileName);
    DataInputStream in = null;
    try {
      in = new DataInputStream(new FileInputStream(file));
    } catch (IOException exception) {
      System.exit(0);
    }

    BigInteger n = new BigInteger(sn);
    BigInteger e = new BigInteger(se);
    BigInteger d = new BigInteger(sd);
    BigInteger p = new BigInteger(sp);
    BigInteger q = new BigInteger(sq);
    BigInteger u = new BigInteger(su);

    // make the secretKey
    SecretKey sk = new SecretKey(n,e,d,p,q,u);

    // basic information
    long lenPerLine=0;
    long lines=0;
    long lastLen=0;

    // decrypt the data
    int len;
    byte[] source;
    byte[] decript;
    BigInteger big;
    BigInteger bigde;
    byte[] target=null;
    try {
      lenPerLine=in.readLong();
      lines=in.readLong();
      lastLen=in.readLong();
      target=new byte[(int)(lenPerLine*lines+lastLen)];
      int bufLoc=0;
      for(long ii=1;ii<=lines;ii++){
        len=in.readInt();
        source=new byte[len];
        in.read(source);
        big=new BigInteger(source);
        bigde=sk.decrypt(big);
        decript=bigde.toByteArray();
        byte zb=0;
        for(int zz=1;zz<=lenPerLine-decript.length;zz++){
          target[bufLoc]=zb;
          bufLoc++;
        }
        for(int zz=1;zz<=decript.length;zz++){
          target[bufLoc]=decript[zz-1];
          bufLoc++;
        }
      }
      if(lastLen!=0){
        byte []last=new byte[(int)lastLen];
        in.read(last);
         for(int zz=1;zz<=last.length;zz++){
          target[bufLoc]=last[zz-1];
          bufLoc++;
        }
      }
      in.close();
     }
    catch (IOException exception) {
      System.out.println("Error reading encrypted File");
    }

    // create the file to save the decrypted data
    File file2 = new File(systemDir+"\\"+"init.class");
    DataOutputStream out = null;
    try {
      out = new DataOutputStream(new FileOutputStream(file2));
      out.write(target,0,target.length);
      out.close();
    }
    catch (IOException exception) {
      System.exit(0);
    }

    // define the class
    cls=this.defineClass(className,target,0,target.length);
    return cls;
  }
}