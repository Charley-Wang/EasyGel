package easygel.image;

import ij.process.*;
import java.io.*;
import java.awt.*;

public class SaveBMP {
  private Bmp bmp;
  private ImageProcessor ip;
  private FileOutputStream is;

  public SaveBMP(Bmp bmp2,ImageProcessor ip,String fileName){
    this.bmp =bmp2;
    this.ip =ip;
    try{
      is=new FileOutputStream(fileName);
      write();
      is.close() ;
    }
    catch(IOException e2){
      e2.printStackTrace() ;
    }
    catch(Exception e3){
      e3.printStackTrace() ;
    }
  }

  private void writeInt(int num) throws IOException {
        byte []b=new byte[4];
        b[0]=(byte)(num & 0x000000FF);
        num=num>>8;
        b[1]=(byte)(num & 0x000000FF);
        num=num>>8;
        b[2]=(byte)(num & 0x000000FF);
        num=num>>8;
        b[3]=(byte)(num & 0x000000FF);
        is.write(b,0,4);
 }

private void writeShort(short num) throws IOException {
  int intnum=num;
  byte []b=new byte[2];
  b[0]=(byte)(intnum & 0x00FF);
  intnum=intnum>>8;
  b[1]=(byte)(intnum & 0x00FF);
  is.write(b,0,2);
}


void writeFileHeader()  throws IOException, Exception {
     writeShort(bmp.fileType);

     //writeInt(bmp.fileSize);
     int lenold = ((bmp.width * bmp.bitsPerPixel + 31) / 32) * 4*bmp.height *3;
     int lennew = ((bmp.width * 24 + 31) / 32) * 4*bmp.height *3;
     int intsize=bmp.fileSize +lennew-lenold;
     writeInt(intsize);

     writeShort(bmp.reserved1 );
     writeShort(bmp.reserved2 );
     writeInt(bmp.bitmapOffset );
}

void writeBitmapHeader() throws IOException {
  writeInt(bmp.size);
  writeInt(bmp.width );
  writeInt(bmp.height );
  writeShort(bmp.planes );
  //writeShort(bmp.bitsPerPixel );
  short shorti=24;
  writeShort(shorti);
  writeInt(bmp.compression );
  writeInt(bmp.sizeOfBitmap );
  writeInt(bmp.horzResolution );
  writeInt(bmp.vertResolution );
  writeInt(bmp.colorsUsed );
  writeInt(bmp.colorsImportant );
}


  void writePalette() throws IOException {
    int noOfEntries = bmp.actualColorsUsed ;
    if (noOfEntries>0) {
        byte reserved=0;
        for (int i = 0; i < noOfEntries; i++) {
            is.write(bmp.b[i]);
            is.write(bmp.g[i]);
            is.write(bmp.r[i]);
            is.write(reserved);
         }
        }
  }

  void unpack(byte[] rawData, int rawOffset, int bpp,
              byte[] byteData, int byteOffset, int w) throws Exception {
    int j = byteOffset;
    int k = rawOffset;
    byte mask;
    int pixPerByte;

    switch (bpp) {
      case 1: mask = (byte)0x01; pixPerByte = 8; break;
      case 4: mask = (byte)0x0f; pixPerByte = 2; break;
      case 8: mask = (byte)0xff; pixPerByte = 1; break;
      default: throw new Exception("Unsupported bits-per-pixel value");
    }

        for (int i = 0;;) {
                int shift = 8 - bpp;
                for (int ii = 0; ii < pixPerByte; ii++) {
                      byte br = rawData[k];
                        br >>= shift;
                        byteData[j] = (byte)(br & mask);
                        j++;
                        i++;
                        if (i == w) return;
                        shift -= bpp;
                }
                k++;
        }
  }


  void writePixelData() throws IOException, Exception {
    //should not be deleted !
    //int len = ((bmp.width * bmp.bitsPerPixel  + 31) / 32) * 4;
    //int blank=len-bmp.width * bmp.bitsPerPixel /8;
    int len = ((bmp.width * 24  + 31) / 32) * 4;
    int blank=len-bmp.width * 24 /8;
    byte[] byteData=null;                // Unpacked data
    int[] intData=null;                  // Unpacked data
    byte blankByte=0;

    intData = new int[bmp.width *bmp.height];
    intData=(int[])ip.getPixels();

    int tempint;
    byte b0,b1,b2;
    for (int i = bmp.height - 1; i >= 0; i--) {
       for(int j=0;j<=bmp.width -1;j++){
           tempint=intData[i*bmp.width+j];
           b0=(byte)(tempint & 0x000000FF);
           tempint=tempint>>8;
           b1=(byte)(tempint & 0x000000FF);
           tempint=tempint>>8;
           b2=(byte)(tempint & 0x000000FF);
           is.write(b0);
           is.write(b1);
           is.write(b2);
       }
      for(int jjj=1;jjj<=blank;jjj++) is.write(blankByte);
    }

    /*
    if (bmp.bitsPerPixel  > 8){
        intData = new int[bmp.width *bmp.height];
        intData=(int[])ip.getPixels();
    }
    else{
        //byteData = new byte[bmp.width * bmp.height];
        //byteData=(byte[])ip.getPixels();
    }

    System.out.println("ip="+ip);

    int tempint;
    byte b0,b1,b2;
    for (int i = bmp.height - 1; i >= 0; i--) {
      if (bmp.bitsPerPixel > 8) {
           for(int j=0;j<=bmp.width -1;j++){
               tempint=intData[i*bmp.width+j];
               b0=(byte)(tempint & 0x000000FF);
               tempint=tempint>>8;
               b1=(byte)(tempint & 0x000000FF);
               tempint=tempint>>8;
               b2=(byte)(tempint & 0x000000FF);
               is.write(b0);
               is.write(b1);
               is.write(b2);
           }
          for(int jjj=1;jjj<=blank;jjj++) is.write(blankByte);
         }
      else {
             // unpack(rawData, rawOffset, bitsPerPixel,
             //                   byteData, offset, width);
            byte mask;
            int pixPerByte;
            int times;

            switch (bmp.bitsPerPixel) {
               case 1: mask = (byte)0x01; pixPerByte = 8; break;
               case 4: mask = (byte)0x0f; pixPerByte = 2; break;
               case 8: mask = (byte)0xff; pixPerByte = 1; break;
               default: throw new Exception("Unsupported bits-per-pixel value");
             }

             times=bmp.width /pixPerByte;
            int writeDataInt;
            byte writeDataByte;
            int shift;
            int pp;
            byte br;

            for (int jjj = 0;jjj<=times -1;jjj++){
                 shift = 8 -bmp.bitsPerPixel ;
                 writeDataInt=0;
                for (int ii = 0; ii < pixPerByte; ii++) {
                     pp=ip.getPixel(i*bmp.width +jjj*pixPerByte+ii+1,i+1);
                   if(i==108) System.out.println(i*bmp.width +jjj*pixPerByte+ii+1+":");
                   if(i==108) System.out.println(pp);
                     br=(byte)(pp & 0x000000FF);
                     //byte br = byteData[i*bmp.width +jjj*pixPerByte+ii];
                     br <<= shift;
                     writeDataInt+=(br&mask);
                     shift -= bmp.bitsPerPixel;
                 }
                 writeDataByte=(byte)(writeDataInt & 0x000000FF);
                 is.write(writeDataByte);
                if(i==108) System.out.println(writeDataByte) ;
             }

            for(int jjj=1;jjj<=blank;jjj++) is.write(blankByte);
        }
    }
    */
  }

 void write() throws IOException, Exception {
        writeFileHeader();
        writeBitmapHeader();
        writePalette();
        writePixelData();
}

}