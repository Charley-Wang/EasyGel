package easygel.image;

import java.io.*;
import java.util.Locale;
import ij.*;
import ij.io.*;
import java.awt.*;
import java.awt.image.*;
import ij.process.*;
import javax.swing.*;
import easygel.*;

public class OpenFile {
  //    打开图像格式的标识
  public static final int UNKNOWN=0,TIFF=1,DICOM=2,FITS=3,PGM=4,JPEG=5,
          GIF=6,LUT=7,BMP=8,ZIP=9,JAVA=10,ROI=11,TEXT=12,PNG=13,TIFF_AND_DICOM=14;

  //打开模式
  private int processorMode;
  //颜色模式
  private int colorMode;
  //比特深度
  private int bitDepth;

  //    Gif and Jepg
  /** 8-bit grayscale (unsigned)*/
  private static final int GRAY8 = 0;
  /** 16-bit grayscale (unsigned) */
  private static final int GRAY16 = 1;
  /** 32-bit floating-point grayscale */
  private static final int GRAY32 = 2;
  /** 8-bit indexed color */
  private static final int COLOR_256 = 3;
  /** 32-bit RGB color */
  private static final int COLOR_RGB = 4;

  private String path,name;
  private int width, height;
  private int imageFormat;        //图像文件的格式
  private ImageProcessor ip;
  //private String ipType;
  private ColorModel colorModel;  //Tiff的颜色格式
  private FileInfo[] tiffFileInfo;
  private Object pixels;
  private Image image;
  private FileInfo fileInfo;
  private Bmp bmp;
  private boolean isbmp;

  //private int imageMode;

  //private int imageType;

  public OpenFile(int fileType,String path,String name){
    isbmp=false;
    this.path =path;
    this.name =name;
    ip=null;
    image=null;
    pixels=null;
    if(fileType==this.TIFF) openTiff();
    if(fileType==this.GIF || fileType==this.JPEG) openGifJpeg();
    if(fileType==this.BMP) openBMP();
  }

  /*
  public int getImageMode(){
    return imageMode;
  }
  */

  public OpenFile(int fileType,String path,String name,Bmp bmp2){
    isbmp=true;
    this.bmp =bmp2;
    this.path =path;
    this.name =name;
    ip=null;
    image=null;
    pixels=null;
    if(fileType==this.TIFF )  openTiff();
    if(fileType==this.GIF ||fileType==this.JPEG ) openGifJpeg();
    if(fileType==this.BMP  )  openBMP();
  }

  public  Object getPixels(){
    return pixels;
  }

  public Image getImage(){
    return image;
  }

  public  ImageProcessor getImageProcessor(){
    return ip;
  }

  public FileInfo getFileInfo(){
    return fileInfo;
  }

  /*
  public String getipType(){
    return ipType;
  }
  */

  public int getProcessorMode(){
    return processorMode;
  }

  public int getColorMode(){
    return colorMode;
  }

  public int getBitDepth(){
    return bitDepth;
  }

  private void openBMP(){
    BMPDecoder bmp2;
    if(isbmp==false) bmp2=new BMPDecoder();
    else             bmp2=new BMPDecoder(this.bmp);
    image=bmp2.getImage(path,name);
    ip=bmp2.getIP();
    if(isbmp==true){
      this.bmp =bmp2.getBmp() ;
    }
    this.bitDepth=bmp2.getBitDepth();
    this.colorMode=bmp2.getColorMode();
    this.processorMode=bmp2.getProcessorMode();
  }

  public Bmp getBmp(){
    return bmp;
  }

  private void openGifJpeg(){
    String file=path+name;
    //Image image2;
    //image = Toolkit.getDefaultToolkit().createImage(file);
    //image2 = Toolkit.getDefaultToolkit().getImage("f:\\JpgTmp.jpg");
    //image1=new ImageIcon(systemDir+"\\icon\\open.jpg");
    ImageIcon icon;
    //image = Toolkit.getDefaultToolkit().getImage(file);
    icon=new ImageIcon(file);
    image=icon.getImage();

    if (image!=null) {
      //ImagePlus imp = null;
      //imp = new ImagePlus(name, img);
      gifjpgProcessor();
      /*
      if (imp.getType()==ImagePlus.COLOR_RGB)
      convertGrayJpegTo8Bits(imp);
      FileInfo fi = new FileInfo();
      fi.fileFormat = fi.GIF_OR_JPG;
      fi.fileName = name;
      fi.directory = dir;
      imp.setFileInfo(fi);
      */
    }
  }

  private void gifjpgProcessor() {
    LookUpTable lut = new LookUpTable(image);
    int type;
    if (lut.getMapSize() > 0) {
      if (lut.isGrayscale())  {
        type = GRAY8;
        this.colorMode=DialogImage.COLOR_GRAY;
      }
      else{
        type = COLOR_256;
        // ???
        this.colorMode=DialogImage.COLOR_INDEX;
      }
    }
    else{
      type = COLOR_RGB;
      this.colorMode=DialogImage.COLOR_RGB;
    }

    //???
    //imageType=type;

    if(type==COLOR_RGB) {
      this.bitDepth=24;
      this.processorMode=DialogImage.PROCESSOR_COLOR;
      ip = new ColorProcessor(image);
    }
    else {
      this.bitDepth=8;
      this.processorMode=DialogImage.PROCESSOR_BYTE;
      ip = new ByteProcessor(image);
    }
    /*
    if (type==COLOR_RGB) ip = new ColorProcessor(getImage());
    else if (ip==null || (ip instanceof ColorProcessor)) {
    ip = new ByteProcessor(getImage());  }
    */
  }

  private void  openTiff(){
    TiffDecoder td = new TiffDecoder(path, name);
    if (IJ.debugMode) td.enableDebugging();
    FileInfo[] info=null;
    try {
      info = td.getTiffInfo();
      tiffFileInfo=info;
      fileInfo=info[0];
      this.bitDepth=fileInfo.getBytesPerPixel()*8;
      //???
      //imageType=fileInfo.fileType;
    }
    catch (IOException e) {
      IJ.showMessage("TiffDecoder", e.getMessage());
    }
    ImagePlus imp = null;
    if (IJ.debugMode) IJ.log(info[0].info); // dump tiff tags
    tiffProcessor(info[0]);
  }

  /*
  public int getImageType(){
    return  imageType;
  }
  */

  private void tiffProcessor(FileInfo fi){
    width = fi.width;
    height = fi.height;
    colorModel=FileOpener.createColorModel(fi);
    switch (fi.fileType) {
      case FileInfo.GRAY8:
      case FileInfo.COLOR8:
      case FileInfo.BITMAP:
        pixels = readPixels(fi);
        ip=new ByteProcessor(width, height, (byte[])pixels, colorModel);
        image=ip.createImage();
        this.processorMode=DialogImage.PROCESSOR_BYTE;
        break;
      case FileInfo.GRAY16_SIGNED:
      case FileInfo.GRAY16_UNSIGNED:
         pixels = readPixels(fi);
         ip=new ShortProcessor(width, height, (short[])pixels, colorModel);
         image=ip.createImage();
         this.processorMode=DialogImage.PROCESSOR_SHORT;
         break;
      case FileInfo.GRAY32_INT:
      case FileInfo.GRAY32_FLOAT:
         pixels = readPixels(fi);
         ip=new FloatProcessor(width, height, (float[])pixels, colorModel);
         image=ip.createImage();
         this.processorMode=DialogImage.PROCESSOR_FLOAT;
         break;
      case FileInfo.RGB:
      case FileInfo.BGR:
      case FileInfo.ARGB:
      case FileInfo.RGB_PLANAR:
          pixels = readPixels(fi);
          ip=new ColorProcessor(width, height, (int[])pixels);
          image=ip.createImage();
          this.processorMode=DialogImage.PROCESSOR_COLOR;
          break;
      default :
        //???
        ip=null;
        image=null;
    }

    switch (fi.fileType) {
      case FileInfo.GRAY8:
      case FileInfo.GRAY16_SIGNED:
      case FileInfo.GRAY16_UNSIGNED:
      case FileInfo.GRAY32_INT:
      case FileInfo.GRAY32_FLOAT:
        this.colorMode=DialogImage.COLOR_GRAY;
        break;
      case FileInfo.COLOR8:
      case FileInfo.BITMAP:
        // ???
        this.colorMode=DialogImage.COLOR_INDEX;
        break;
      case FileInfo.RGB:
      case FileInfo.BGR:
      case FileInfo.ARGB:
      case FileInfo.RGB_PLANAR:
        // ???
        this.colorMode=DialogImage.COLOR_RGB;
        break;
      default :
        this.colorMode=DialogImage.COLOR_UNKNOWN;
    }
  }

 private Object readPixels(FileInfo fi) {
   Object pixels = null;
   try {
     InputStream is =FileOpener.createInputStream(fi);
     if (is==null) return null;
     ImageReader reader = new ImageReader(fi);
     pixels = reader.readPixels(is);
     is.close();
   }
   catch (Exception e) {
     IJ.log("FileOpener.readPixels(): " + e);
   }
   return pixels;
  }

  public  static int getFileType(String path, String name) {
    InputStream is;
    byte[] buf = new byte[132];
    try {
      is = new FileInputStream(path);
      is.read(buf, 0, 132);
      is.close();
    }
    catch (IOException e) {
      return UNKNOWN;
    }
    int b0=buf[0]&255, b1=buf[1]&255, b2=buf[2]&255, b3=buf[3]&255;

    // Combined TIFF and DICOM created by GE Senographe scanners
    if (buf[128]==68 && buf[129]==73 && buf[130]==67 && buf[131]==77
        && ((b0==73 && b1==73)||(b0==77 && b1==77)))
      return TIFF_AND_DICOM;

    // Big-endian TIFF ("MM")
    if (b0==73 && b1==73 && b2==42 && b3==0) return TIFF;
    // Little-endian TIFF ("II")
    if (b0==77 && b1==77 && b2==0 && b3==42) return TIFF;
    // JPEG
    if (b0==255 && b1==216 && b2==255) return JPEG;
    // GIF ("GIF8")
    if (b0==71 && b1==73 && b2==70 && b3==56) return GIF;
    // DICOM ("DICM" at offset 128)
    if (buf[128]==68 && buf[129]==73 && buf[130]==67 && buf[131]==77) return DICOM;
    // ACR/NEMA with first tag = (00008,00xx)
    if (b0==8 && b1==0 && b3==0) return DICOM;
    // FITS ("SIMP")
    if (b0==83 && b1==73 && b2==77 && b3==80) return FITS;
    // PGM ("P2" or "P5")
    if (b0==80&&(b1==50||b1==53)&&(b2==10||b2==13||b2==32||b2==9)) return PGM;
    // Lookup table
    name = name.toLowerCase(Locale.US);
    if (name.endsWith(".lut")) return LUT;
    // BMP ("BM")
    if (b0==66 && b1==77 && name.endsWith(".bmp")) return BMP;
    // PNG
    if (b0==137 && b1==80 && b2==78 && b3==71 && IJ.isJava2()) return PNG;
    // ZIP containing a TIFF
    if (name.endsWith(".zip")) return ZIP;
    // Java source file
    if (name.endsWith(".java")) return JAVA;
    // ImageJ, NIH Image, Scion Image for Windows ROI
    // "Iout"
    if (b0==73 && b1==111) return ROI;
    // Text file
    if (name.endsWith(".txt") || (b0>=32 && b0<=126 && b1>=32 && b1<=126
                                  && b2>=32 && b2<=126 && b3>=32 && b3<=126 && buf[8]>=32 && buf[8]<=126))
      return TEXT;
    // Others...
    return UNKNOWN;
  }
}