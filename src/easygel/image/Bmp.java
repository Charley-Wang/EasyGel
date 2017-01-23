package easygel.image;

public class Bmp {
  //public FileHeader fileHeader=new FileHeader();
  //public BmpHeader bmpHeader=new BmpHeader();
  //public Palette palette=new Palette();

  //class FileHeader{
    public short fileType = 0x4d42;// always "BM"
    public int fileSize;           // size of file in bytes
    public short reserved1 = 0;    // always 0
    public short reserved2 = 0;    // always 0
    public  int bitmapOffset;
  //}

  //class BmpHeader{
     // Actual contents (40 bytes):
     public  int size;                 // size of this header in bytes
     public  short planes;             // no. of color planes: always 1
     public  int sizeOfBitmap;         // size of bitmap in bytes (may be 0: if so, calculate)
     public  int horzResolution;       // horizontal resolution, pixels/meter (may be 0)
     public  int vertResolution;       // vertical resolution, pixels/meter (may be 0)
     public  int colorsUsed;           // no. of colors in palette (if 0, calculate)
     public  int colorsImportant;      // no. of important colors (appear first in palette) (0 means all are important)
     public int width;                 // image width in pixels
     public int height;                // image height in pixels
     public short bitsPerPixel;        // 1, 4, 8, or 24 (no color map)
     public int compression;           // 0 (none), 1 (8-bit RLE), or 2 (4-bit RLE)
     //辅助信息
     public   int actualColorsUsed;
  //}

  //class Palette{
 //   public  byte r[]=new byte[bmpHeader.actualColorsUsed];
 //   public  byte g[]=new byte[bmpHeader.actualColorsUsed];
 //   public  byte b[]=new byte[bmpHeader.actualColorsUsed];
  //}

    public  byte r[];
    public  byte g[];
    public  byte b[];

  public Bmp(){

  }

  public Bmp duplicate(){
    Bmp n=new Bmp();
    if(r==null) n.r=null;
    else duplicateBytes(n.r,r);
    if(g==null) n.g=null;
    else duplicateBytes(n.g,g);
    if(b==null) n.b=null;
    else duplicateBytes(n.b,b);

    n.fileSize=fileSize;
    n.bitmapOffset=bitmapOffset;
    n.size=size;
    n.planes=planes;
    n.sizeOfBitmap=sizeOfBitmap;
    n.horzResolution=horzResolution;
    n.vertResolution=vertResolution;
    n.colorsUsed=colorsUsed;
    n.colorsImportant=colorsImportant;
    n.width=width;
    n.height=height;
    n.bitsPerPixel=bitsPerPixel;
    n.compression=compression;
    n.actualColorsUsed=actualColorsUsed;

    return n;
  }

  private void duplicateBytes(byte []n,byte []o){
    int sz=o.length;
    if(sz>=1) n=new byte[sz];
    else n=null;
    for(int ii=1;ii<=sz;ii++) n[ii-1]=o[ii-1];
  }

}