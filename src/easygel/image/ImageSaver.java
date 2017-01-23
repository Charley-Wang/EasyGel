package easygel.image;

import java.awt.*;
import java.io.*;
import java.util.zip.*;
import ij.process.*;
//import ij.measure.Calibration;
//import ij.plugin.filter.Analyzer;
//import ij.plugin.frame.Recorder;
import ij.io.*;
import ij.*;
import easygel.*;
import javax.swing.*;

/** Saves images in tiff, gif, jpeg format. */
public class ImageSaver {
  //private static String defaultDirectory = null;
  //private ImagePlus imp;

  //private String name;
  //private String directory;

  private ImageProcessor ip;
  private FileInfo fi;

  public ImageSaver(ImageProcessor ip,FileInfo fi) {
    this.ip=ip;
    this.fi =fi;
  }

  public static void saveIPAsImage(int format,String path,String name,
                                   ImageProcessor ip,FileInfo fileInfo,
                                   Bmp bmp,boolean isTip,FrameMain motherFrame){

    String saveFile=path+name;
    ImageSaver imageSaver=new ImageSaver(ip,fileInfo);
    if(format ==OpenFile.TIFF ){
      imageSaver.saveAsTiff(saveFile);
    }
    else if(format ==OpenFile.GIF ){
      try{
        GifEncoder gif=new GifEncoder(ip.createImage());
        FileOutputStream out=new FileOutputStream(saveFile);
        gif.write(out);
        out.close();
      }
      catch(IOException e2){
        e2.printStackTrace() ;
      }
    }
    else if(format ==OpenFile.JPEG ){
      imageSaver.saveAsJpeg(saveFile);
    }
    else if(format ==OpenFile.BMP ){
      SaveBMP saveBMP=new SaveBMP(bmp,ip,saveFile);
    }

    if(isTip==true){
      JOptionPane.showMessageDialog(motherFrame,"Image Saved","Image Saved",
                                    JOptionPane.INFORMATION_MESSAGE);
    }
  }


  public static OpenFileInformation createIPFromTmp(int fileFormat,FrameMain motherFrame,
      boolean isInit,Color bgColor,int width,int height,String savePath,String saveName){

    String tmpPath=motherFrame.getSystemDir()+"\\DATABASE\\";
    String name="";
    if(fileFormat ==OpenFile.TIFF )      name="TifTmp.tif";
    //else if(fileFormat ==OpenFile.GIF )  name="GifTmp.gif";
    else if(fileFormat ==OpenFile.GIF )  name="JpgTmp.gif";
    else if(fileFormat ==OpenFile.JPEG ) name="JpgTmp.jpg";
    else if(fileFormat ==OpenFile.BMP )  name="BmpTmp.bmp";

    OpenFile openFile;
    Bmp bmp2=null;
    int imageType2;
    ImageProcessor ip2;
    FileInfo fileInfo2=null;

    if(fileFormat==OpenFile.BMP){
      bmp2=new Bmp();
      openFile=new OpenFile(fileFormat,tmpPath,name,bmp2);
    }
    else{
      openFile=new OpenFile(fileFormat,tmpPath,name);
    }

    ip2=openFile.getImageProcessor();
    //imageType2=openFile.getImageType();
    ip2=ip2.createProcessor(width,height);

    if(fileFormat==OpenFile.BMP){
      bmp2=openFile.getBmp();
      bmp2.width=width;
      bmp2.height=height;
      bmp2.fileSize=width*height*bmp2.bitsPerPixel/8;
    }

    if(fileFormat==OpenFile.TIFF) fileInfo2=openFile.getFileInfo();
    else  fileInfo2=new FileInfo();
    fileInfo2.directory=savePath;
    fileInfo2.fileName=saveName;
    fileInfo2.width=width;
    fileInfo2.height=height;
    fileInfo2.pixels=ip2.getPixels();

    OpenFileInformation infor=new OpenFileInformation();
    infor.bmp=bmp2;
    infor.fileInfo=fileInfo2;
    infor.height=height;
    infor.width=width;
    infor.ip=ip2;

    if(isInit==true){
      for(int jj=0;jj<height;jj++){
        for(int ii=0;ii<width;ii++){
          ip2.putPixel(ii,jj,bgColor.getRGB());
        }
      }
    }

    return infor;
  }

  public static String[] selectFileToSave(int fileFormat,FrameMain motherFrame,String title){
    String pathName[]=new String[2];
    String end;
    end="";

    if(fileFormat ==OpenFile.TIFF ) end=".tif";
    else if(fileFormat ==OpenFile.GIF )end=".gif";
    else if(fileFormat ==OpenFile.JPEG )end=".jpg";
    else if(fileFormat ==OpenFile.BMP )end=".bmp";

    FileDialog filedlg=new FileDialog(motherFrame ,title+"：???"+end);
    if(motherFrame .getUser() .hira !=0){
      if(motherFrame.getUser() .rootWorkDir .length() !=0  ){
        filedlg.setDirectory(motherFrame .getUser() .rootWorkDir ) ;
      }
    }

    filedlg.setMode(filedlg.SAVE);
    filedlg.show();
    String saveFile="";
    if(filedlg.getName().length() !=0 && filedlg.getDirectory()!=null
       && filedlg.getFile()!=null){
      saveFile=filedlg.getDirectory() + filedlg.getFile();
      saveFile+=end;
      pathName[0]=filedlg.getDirectory();
      pathName[1]=filedlg.getFile()+end;
    }
    else  pathName=null;

    boolean checked=true;
    if(checked==true){
      File file=new File(saveFile);
      if(file.exists() ==true){
        JOptionPane myOptionPane=new JOptionPane();
        Object[] options = { "Overwrite", "Not overwrite"};
        int  selectOption;
        selectOption=myOptionPane.showOptionDialog(null,
            "Overwrite?", "Warning",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE ,
            null, options, options[0]);
        if(selectOption==1) pathName=null;
        file.delete();
      }
    }

    return pathName;
  }

  /*
  public FileSaver(ImagePlus imp) {
  this.imp = imp;
  fi = imp.getFileInfo();
  }
  */

          /** Resaves the image. Calls saveAsTiff() if this is a new image or if
                  the image was loaded using a URL. Returns false if saveAsTiff() is
                  called and the user selects cancel in the file save dialog box. */
          /*
          public boolean save() {
                  //if (imp.getURL()!=null)
                          return saveAsTiff();
          }

          String getPath(String type, String extension) {
                  name = imp.getTitle();
                  SaveDialog sd = new SaveDialog("Save as "+type, name, extension);
                  name = sd.getFileName();
                  if (name==null)
                          return null;
                  directory = sd.getDirectory();
                  imp.startTiming();
                  String path = directory+name;
                  return path;
          }
          */

          /** Save the image or stack in TIFF format using a save file
                  dialog. Returns false if the user selects cancel. */
          /*
          public boolean saveAsTiff() {
                  String path = getPath("TIFF", ".tif");
                  if (path==null)
                          return false;
                  if (imp.getStackSize()==1)
                          return saveAsTiff(path);
                  else
                          return saveAsTiffStack(path);
          }

          */

          /** Save the image in TIFF format using the specified path. */
          public boolean saveAsTiff(String path){
                  fi.nImages = 1;
                  //fi.description = getDescriptionString();
                  try {
                          TiffEncoder file = new TiffEncoder(fi);
                          fi.pixels =ip.getPixels();
                          DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
                          file.write(out);
                          out.close();
                  }
                  catch (IOException e) {
                          e.printStackTrace();
                          //showErrorMessage(e);
                          //return false;
                  }
                  //updateImp(fi, fi.TIFF);
                  return true;
          }

          public static void saveAllAsTiff(int []rgbs,String filePathName){

          }

          public static boolean okForGif(ImagePlus imp) {
                  int type = imp.getType();
                  if (type==ImagePlus.COLOR_RGB || type==ImagePlus.GRAY16 || type==ImagePlus.GRAY32) {
                          IJ.error("To save as Gif, the image must be \"8-bit\" or \"8-bit Color\".");
                          return false;
                  } else
                          return true;

          }

          /** Save the image in Gif format using the specified path. Returns
                  false if the image is not 8-bits or there is an I/O error. */
          public boolean saveAsGif(String path) {
                  //if (!okForGif(imp))
                  //        return false;
                  try {
                          //byte[] pixels = (byte[])imp.getProcessor().getPixels();
                        byte[] pixels = (byte[])ip.getPixels();
                          GifEncoder encoder = new GifEncoder(fi.width, fi.height, pixels, fi.reds, fi.greens, fi.blues);
                          OutputStream output = new BufferedOutputStream(new FileOutputStream(path));
                          encoder.write(output);
                          output.close();
                  }
                  catch (IOException e) {
                          e.printStackTrace() ;
                          //showErrorMessage(e);
                          //return false;
                  }
                  //updateImp(fi, fi.GIF_OR_JPG);
                  return true;
          }

          /** Always returns true. */
          public static boolean okForJpeg(ImagePlus imp) {
                  return true;
          }

          /** Save the image in JPEG format using the specified path. */
          public boolean saveAsJpeg(String path) {
                  //Object jpegWriter = null;
                  //if (IJ.isJava2()) {
                  //        WindowManager.setTempCurrentImage(imp);
                  //        jpegWriter = IJ.runPlugIn("ij.plugin.JpegWriter", path);
                  //        WindowManager.setTempCurrentImage(null);
                 // }
                 // if (jpegWriter==null) {
                          try {
                                  OutputStream output = new BufferedOutputStream(new FileOutputStream(path));
                                  //JpegEncoder encoder = new JpegEncoder(imp.getImage(), JpegEncoder.getQuality(), output);
                                  JpegEncoder encoder = new JpegEncoder(ip.createImage() , JpegEncoder.getQuality(), output);
                                  encoder.Compress();
                                  output.close();
                          }
                          catch (IOException e) {
                                  e.printStackTrace() ;
                                  //showErrorMessage(e);
                                  //return false;
                          }
                 // }
                  /*
                  if (!(imp.getType()==ImagePlus.GRAY16 || imp.getType()==ImagePlus.GRAY32))
                          updateImp(fi, fi.GIF_OR_JPG);
                  */
                  return true;
          }

/*
          private void updateImp(FileInfo fi, int fileFormat) {
                  imp.changes = false;
                  if (name!=null) {
                          fi.fileFormat = fileFormat;
                          fi.fileName = name;
                          fi.directory = directory;
                          if (fileFormat==fi.TIFF)
                                  fi.offset = TiffEncoder.IMAGE_START;
                          fi.description = null;
                          imp.setTitle(name);
                          imp.setFileInfo(fi);
                  }
          }
*/

          /** Returns a string containing information about the specified  image. */
          String getDescriptionString() {
                  StringBuffer sb = new StringBuffer(100);
                  sb.append("ImageJ="+ImageJ.VERSION+"\n");
                  if (fi.nImages>1)
                          sb.append("images="+fi.nImages+"\n");
                  if (fi.unit!=null)
                          sb.append("unit="+fi.unit+"\n");
                  if (fi.valueUnit!=null) {
                          sb.append("cf="+fi.calibrationFunction+"\n");
                          if (fi.coefficients!=null) {
                                  for (int i=0; i<fi.coefficients.length; i++)
                                          sb.append("c"+i+"="+fi.coefficients[i]+"\n");
                          }
                          sb.append("vunit="+fi.valueUnit+"\n");
                  }

                  // get stack z-spacing and fps
                  if (fi.nImages>1) {
                          if (fi.pixelDepth!=0.0 && fi.pixelDepth!=1.0)
                                  sb.append("spacing="+fi.pixelDepth+"\n");
                          if (fi.frameInterval!=0.0) {
                                  double fps = 1.0/fi.frameInterval;
                                  if ((int)fps==fps)
                                          sb.append("fps="+(int)fps+"\n");
                                  else
                                          sb.append("fps="+fps+"\n");
                          }
                  }

                  // get min and max display values
                  //ImageProcessor ip = imp.getProcessor();
                  double min = ip.getMin();
                  double max = ip.getMax();
                  //???
                 // int type = imp.getType();
                  int type=0;
                  boolean enhancedLut = (type==ImagePlus.GRAY8 || type==ImagePlus.COLOR_256) && (min!=0.0 || max !=255.0);
                  if (enhancedLut || type==ImagePlus.GRAY16 || type==ImagePlus.GRAY32) {
                          sb.append("min="+min+"\n");
                          sb.append("max="+max+"\n");
                  }

                  // get non-zero origins
                  /*
                  Calibration cal = imp.getCalibration();
                  if (cal.xOrigin!=0.0)
                          sb.append("xorigin="+cal.xOrigin+"\n");
                  if (cal.yOrigin!=0.0)
                          sb.append("yorigin="+cal.yOrigin+"\n");
                  if (cal.zOrigin!=0.0)
                          sb.append("zorigin="+cal.zOrigin+"\n");
                  if (cal.info!=null && cal.info.length()<=64 && cal.info.indexOf('=')==-1 && cal.info.indexOf('\n')==-1)
                          sb.append("info="+cal.info+"\n");
                  sb.append((char)0);
                  */
                  return new String(sb);
          }

}