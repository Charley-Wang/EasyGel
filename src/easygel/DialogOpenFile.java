package easygel;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import easygel.file.*;
import java.beans.*;
import java.io.*;
import easygel.image.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogOpenFile extends JDialog {
  private JPanel jPanel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JFileChooser jFileChooser1 = new JFileChooser();
  private JPanel jPaneImage = new JPanel();
  private JCheckBox jCheckBoxPreview = new JCheckBox();
  private File mFile;
  private  boolean preview;   //是否提供预览
  private Image img;
  private Graphics imageGraphics;
  private  boolean isInit;
  private String imageDir="";

  //   需返回的参数，对每一种均需提供
  private String path,name;
  private  int width,height;
  private int imageFormat;
  private FrameMain frameMain;

  public DialogOpenFile(Frame frame, boolean modal) {
    super(frame, "", modal);
    try {
      frameMain=(FrameMain)frame;
      isInit=true;
      path="";
      name="";
      width=0;
      height=0;
      imageFormat=OpenFile.UNKNOWN;
      this.readImageDir() ;
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  private  void readImageDir(){
    /*
    String systemDir;
    File initDir;
    systemDir=System.getProperties().getProperty("user.dir");
    initDir=new File(systemDir+"\\database\\ImageDir.ini");
    if(initDir.exists() ==true){
      try{
        FileInputStream inputFile=new FileInputStream(systemDir+"\\database\\ImageDir.ini");
        DataInputStream inputData=new DataInputStream(inputFile);
        imageDir=inputData.readLine();
        inputData.close() ;
        inputFile.close() ;
        initDir=new File(imageDir);
        if(initDir.isDirectory() ==false){
          imageDir=systemDir;
        }
      }
      catch(IOException e){
        e.printStackTrace();
      }
    }
    else{
      imageDir=systemDir;
    }
    */
    File file=new File(this.frameMain.getEasyGelSet().imageDir);
    if(file.exists()==false){
      imageDir=this.frameMain.getSystemDir();
      this.frameMain.getEasyGelSet().imageDir=imageDir;
      this.frameMain.saveEasyGelSet();
    }
    else{
      imageDir=this.frameMain.getEasyGelSet().imageDir;
    }
    this.jFileChooser1 .setCurrentDirectory(new File(imageDir));
  }

  /**
   * <p>获得图像的路径</p>
   * @return 如果为空自串，则说明操作不成功。
   */
  public String getFilePath(){
    return path;
  }

  /**
   * <p>获得图像的名称（包括后缀）</p>
   * @return 如果为空自串，则说明操作不成功。
   */
  public String getFileName(){
    return name;
  }

  /**
   *
   * @return 获得图像的宽
   */
  public int getFileWidth(){
    return width;
  }

  /**
   *
   * @return 获得图像的高
   */
  public int getFileHeight(){
    return height;
  }

  /**
   *
   * @return 获得图像的格式，用easygel.image.OpenFile.*取之
   */
  public int getFileFormat(){
    return imageFormat;
  }

  private void jbInit() throws Exception {
    jPanel1.setLayout(xYLayout1);
    this.setSize(584,296);
    this.addWindowListener(new java.awt.event.WindowAdapter() {
    });
    easygel.uiti.WxsUiti.centerDialog(this,584,296);
    jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jFileChooser1_actionPerformed(e);
      }
    });
    String []extension={"tif","gif","bmp","jpg"};
    String []des={"TIFF","GIF","BMP","JPG"};
    ExampleFileFilter filter1 = new ExampleFileFilter(extension[0],des[0]);
    ExampleFileFilter filter2 = new ExampleFileFilter(extension[1],des[1]);
    ExampleFileFilter filter3 = new ExampleFileFilter(extension[2],des[2]);
    ExampleFileFilter filter4 = new ExampleFileFilter(extension[3],des[3]);
    jFileChooser1.addChoosableFileFilter(filter2);
    jFileChooser1.addChoosableFileFilter(filter3);
    jFileChooser1.addChoosableFileFilter(filter1);
    jFileChooser1.addChoosableFileFilter(filter4);
    jPaneImage.setBorder(BorderFactory.createLoweredBevelBorder());
    jCheckBoxPreview.setToolTipText("");
    jCheckBoxPreview.setText("Preview");
    jCheckBoxPreview.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxPreview_actionPerformed(e);
      }
    });
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.setResizable(false);
    this.setTitle("Please select an image: ");
    jFileChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
      public void propertyChange(PropertyChangeEvent e) {
        jFileChooser1_propertyChange(e);
      }
    });
    jFileChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jFileChooser1_mouseClicked(e);
      }
    });
    jFileChooser1.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
      public void vetoableChange(PropertyChangeEvent e) throws PropertyVetoException {
        jFileChooser1_vetoableChange(e);
      }
    });
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jFileChooser1, new XYConstraints(20, 20, 374, 236));
    jPanel1.add(jPaneImage,   new XYConstraints(415, 30, 150, 180));
    jPanel1.add(jCheckBoxPreview, new XYConstraints(452, 218, 74, 25));
    jCheckBoxPreview.setSelected(true);
    preview=true;
    isInit=false;
  }

  void jFileChooser1_actionPerformed(ActionEvent e) {
    if(e.getActionCommand()=="CancelSelection" ){
      path="";
      name="";
      width=0;
      height=0;
      imageFormat=OpenFile.UNKNOWN;
      this.setVisible(false);
    }
    else{
         mFile=jFileChooser1.getSelectedFile();
        int fileType;
        if(mFile!=null && mFile.isDirectory() ==false){
           path=easygel.uiti.WxsUiti.getDir(mFile.getPath());
           name=mFile.getName();
           DialogImage currentImage;
           fileType=OpenFile.UNKNOWN ;
          try{
             fileType=OpenFile.getFileType(mFile.getPath(),mFile.getName());
           }
          catch( OutOfMemoryError e2) {
            return;
           }
          if(fileType==OpenFile .JPEG || fileType==OpenFile.GIF || fileType==OpenFile.TIFF || fileType==OpenFile.BMP )
             {
               this.writeImageDir() ;
               this.setVisible(false) ;
             }
      }
    }
  }

  void jFileChooser1_propertyChange(PropertyChangeEvent e) {
    if(isInit==true) return;
    this.selectFileChanged() ;
  }

  void selectFileChanged(){
    int fileType;
    ImageIcon myImage;

    mFile=jFileChooser1.getSelectedFile();
    if(preview==true){
      if(mFile!=null && mFile.isDirectory() ==false){
        path=easygel.uiti.WxsUiti.getDir(mFile.getPath());
        name=mFile.getName();
        fileType=OpenFile.getFileType(mFile.getPath(),mFile.getName());
        imageFormat=fileType;
       if(fileType==OpenFile .JPEG  || fileType==OpenFile.GIF ){
          myImage=new ImageIcon(mFile.getPath());
          img=myImage.getImage();
          this.drawImage() ;
        }
       else if(fileType==OpenFile.TIFF  || fileType==OpenFile.BMP ){
         OpenFile openFile;
         try{
            openFile=new OpenFile(fileType,path,name);
         }
         catch( OutOfMemoryError e2) {
           this.clearImage() ;
           this.jPaneImage .getGraphics() .setColor(Color.red );
           this.jPaneImage .getGraphics() .drawString("图太大！",50,50);
           return;
         }
          img=openFile.getImage();
         this.drawImage() ;
       }
     }
   }
  }

  void clearImage(){
    if(this.isVisible()==false) return;
    int imageWidth,imageHeight;
    Graphics g;
    imageWidth=jPaneImage.getWidth()-4 ;
    imageHeight=jPaneImage.getHeight()-4 ;
    g=jPaneImage.getGraphics();
    try{
      g.clearRect(1,1,imageWidth+1,imageHeight+1);
    }
    catch(Exception e){
      e.printStackTrace();
    }
    //g.clearRect(0,0,imageWidth-1,imageHeight-1);
  }

  void drawImage(){
    int imageWidth,imageHeight,locx,locy,w,h;
    float wp,hp;
    width=img.getWidth(null) ;
    height=img.getHeight(null);
    imageWidth=jPaneImage.getWidth()-4 ;
    imageHeight=jPaneImage.getHeight()-4 ;
    this.clearImage() ;
    if(width>imageWidth||height>imageHeight){
      wp=width/imageWidth;
      hp=height/imageHeight;
      if(wp>hp){
        w=imageWidth;
        h=height*w/width;
      }
      else{
        h=imageHeight;
        w=width*h/height;
      }
      locx=(imageWidth-w)/2+2;
      locy=(imageHeight-h)/2+2;
      imageWidth=w;
      imageHeight=h;
    }
    else{
      locx=(imageWidth+4-width)/2;
      locy=(imageHeight+4-height)/2;
      imageWidth=width;
      imageHeight=height;
    }
    if(locx<2) locx=2;
    if(locy<2) locy=2;
    if(imageWidth>jPaneImage.getWidth()-4 )  imageWidth=jPaneImage.getWidth()-4;
    if(imageHeight>jPaneImage.getHeight()-4 )  imageHeight=jPaneImage.getHeight()-4;
    Graphics g;
    g=jPaneImage.getGraphics();
    try{
      g.drawImage(img,locx,locy,imageWidth,imageHeight,jPaneImage);
    }
    catch(Exception e){

    }
  }

  void jFileChooser1_mouseClicked(MouseEvent e) {
    writeImageDir();
  }

  void jFileChooser1_vetoableChange(PropertyChangeEvent e) throws PropertyVetoException {
    JOptionPane.showMessageDialog(null,"vetoable");
  }

  void jCheckBoxPreview_actionPerformed(ActionEvent e) {
    if(isInit==true) return;
    if(preview==true) {
      preview=false;
      this.clearImage() ;
      }
    else {
      preview=true;
      this.selectFileChanged() ;
    }
  }

  void writeImageDir(){
    if(imageDir==this.jFileChooser1 .getCurrentDirectory() .getPath() ){
      return;
    }
    else{
      imageDir=this.jFileChooser1.getCurrentDirectory().getPath();
      this.frameMain.getEasyGelSet().imageDir=imageDir;
      this.frameMain.saveEasyGelSet();
      /*
      String systemDir;
      File initDir;
      systemDir=System.getProperties().getProperty("user.dir");
      initDir=new File(systemDir+"\\database\\ImageDir.ini");
      if(initDir.exists() ==true) {
        initDir.delete() ;
        System.out.println("delete file");
      }
      try{
          FileOutputStream inputFile=new FileOutputStream(systemDir+"\\database\\ImageDir.ini");
          DataOutputStream inputData=new DataOutputStream(inputFile);
          inputData.writeBytes(imageDir);
          System.out.println("write data");
          inputData.close() ;
          inputFile.close() ;
      }
      catch(IOException e2){
        e2.printStackTrace();
      }
      */
    }
  }
}