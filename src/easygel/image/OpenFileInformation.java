package easygel.image;

import ij.process.*;
import ij.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class OpenFileInformation {
  public ImageProcessor ip;
  public int width,height;
  public Bmp bmp;
  public FileInfo fileInfo;
  private int imageType;
  public OpenFileInformation() {
    bmp=new Bmp();
    fileInfo=new FileInfo();
  }
}