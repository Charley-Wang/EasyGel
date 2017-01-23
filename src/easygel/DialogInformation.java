package easygel;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.awt.event.*;
import easygel.image.*;

public class DialogInformation extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private TitledBorder titledBorder1;
  private FrameMain frameMain;
  private TitledBorder titledBorder2;
  private JTabbedPane jTabbedPane1 = new JTabbedPane();
  private JPanel jPanel1 = new JPanel();
  private XYLayout xYLayout2 = new XYLayout();
  private JLabel jLabel11 = new JLabel();
  private JLabel jLabel10 = new JLabel();
  private JLabel jLabel9 = new JLabel();
  private JLabel jLabel8 = new JLabel();
  private JLabel jLabel7 = new JLabel();
  private JLabel jLabelImagePath = new JLabel();
  private JLabel jLabelImageColorMode = new JLabel();
  private JLabel jLabelImageHeight = new JLabel();
  private JLabel jLabelImageWidth = new JLabel();
  private JLabel jLabelImageSuffix = new JLabel();
  private JLabel jLabelImageName = new JLabel();
  private JPanel jPanel2 = new JPanel();
  private XYLayout xYLayout3 = new XYLayout();
  private JRadioButton jRadioButtonImageColorModeColor = new JRadioButton();
  private JRadioButton jRadioButtonImageColorModeGray = new JRadioButton();
  private JRadioButton jRadioButtonImageColorModeIndex = new JRadioButton();
  private JLabel jLabelImageBitDepth = new JLabel();
  private JLabel jLabelImageCoordinate = new JLabel();
  private JLabel jLabelImageRGB = new JLabel();
  private JLabel jLabelImageGray = new JLabel();
  private JLabel jLabelWindowCoordinate = new JLabel();
  private ButtonGroup buttonGroup1 = new ButtonGroup();
  private XYLayout xYLayout4 = new XYLayout();

  public DialogInformation(FrameMain frame, String title, boolean modal) {
    super(frame, title, modal);
    frameMain=frame;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogInformation() {
    this(null, "", false);
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    panel1.setLayout(xYLayout1);
    jPanel1.setLayout(xYLayout2);
    jLabel11.setForeground(Color.blue);
    jLabel11.setText("Path for saving: ");
    jLabel10.setForeground(Color.blue);
    jLabel10.setText("Color mode: ");
    jLabel9.setForeground(Color.blue);
    jLabel9.setText("Resolution: ");
    jLabel8.setForeground(Color.blue);
    jLabel8.setText("Suffix of images: ");
    jLabel7.setForeground(Color.blue);
    jLabel7.setText("File name of image: ");
    jLabelImagePath.setForeground(Color.red);
    jLabelImagePath.setBorder(BorderFactory.createLoweredBevelBorder());
    jLabelImagePath.setText("jLabel6");
    jLabelImageColorMode.setForeground(Color.red);
    jLabelImageColorMode.setBorder(BorderFactory.createLoweredBevelBorder());
    jLabelImageColorMode.setText("jLabel5");
    jLabelImageColorMode.setText("");
    jLabelImageHeight.setForeground(Color.red);
    jLabelImageHeight.setBorder(BorderFactory.createLoweredBevelBorder());
    jLabelImageHeight.setText("jLabel4");
    jLabelImageWidth.setForeground(Color.red);
    jLabelImageWidth.setBorder(BorderFactory.createLoweredBevelBorder());
    jLabelImageWidth.setText("jLabel3");
    jLabelImageSuffix.setForeground(Color.red);
    jLabelImageSuffix.setBorder(BorderFactory.createLoweredBevelBorder());
    jLabelImageSuffix.setText("jLabel2");
    jLabelImageName.setForeground(Color.red);
    jLabelImageName.setBorder(BorderFactory.createLoweredBevelBorder());
    jLabelImageName.setText("jLabel1");
    jPanel2.setLayout(xYLayout3);
    jRadioButtonImageColorModeColor.setText("Color");
    jRadioButtonImageColorModeGray.setText("Gray");
    jRadioButtonImageColorModeIndex.setText("Index");
    jLabelImageBitDepth.setText("Bit: 24");
    jLabelImageCoordinate.setText("[127,598]");
    jLabelImageRGB.setText("RGB:(234,43,123)");
    jLabelImageGray.setText("Gray: 4587");
    jLabelWindowCoordinate.setText("Win: [0,78]");
    jPanel2.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(FocusEvent e) {
        jPanel2_focusGained(e);
      }
    });
    jPanel1.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(FocusEvent e) {
        jPanel1_focusGained(e);
      }
    });
    this.getContentPane().setLayout(xYLayout4);
    xYLayout4.setWidth(169);
    xYLayout4.setHeight(202);
    jPanel1.add(jLabel10,  new XYConstraints(23, 92, 72, 21));
    jPanel1.add(jLabel11, new XYConstraints(23, 118, 72, 21));
    jPanel1.add(jLabelImageName, new XYConstraints(89, 15, 310, 21));
    jPanel1.add(jLabelImageSuffix, new XYConstraints(89, 41, 310, 21));
    jPanel1.add(jLabelImageWidth, new XYConstraints(89, 66, 142, 21));
    jPanel1.add(jLabelImageHeight, new XYConstraints(257, 66, 142, 21));
    jPanel1.add(jLabelImageColorMode, new XYConstraints(89, 92, 310, 21));
    jPanel1.add(jLabelImagePath, new XYConstraints(89, 118, 310, 21));
    jPanel1.add(jLabel7, new XYConstraints(23, 15, 72, 21));
    jPanel1.add(jLabel8, new XYConstraints(23, 41, 72, 21));
    jPanel1.add(jLabel9, new XYConstraints(23, 67, 72, 21));
    jTabbedPane1.add(jPanel2,     "Dynamic Information");
    jPanel2.add(jLabelImageCoordinate,  new XYConstraints(10, 57, 178, 20));
    jPanel2.add(jLabelWindowCoordinate, new XYConstraints(10, 82, 178, 20));
    jPanel2.add(jLabelImageRGB, new XYConstraints(10, 106, 178, 20));
    jPanel2.add(jLabelImageGray, new XYConstraints(10, 130, 178, 20));
    jPanel2.add(jLabelImageBitDepth, new XYConstraints(10, 10, 64, -1));
    jPanel2.add(jRadioButtonImageColorModeColor, new XYConstraints(10, 32, 51, 21));
    jPanel2.add(jRadioButtonImageColorModeGray, new XYConstraints(63, 32, 51, 21));
    jPanel2.add(jRadioButtonImageColorModeIndex, new XYConstraints(117, 32, 51, 21));
    this.getContentPane().add(panel1,  new XYConstraints(5, 5, -1, -1));
    panel1.add(jTabbedPane1,            new XYConstraints(-1, 0, 428, 186));
    jTabbedPane1.add(jPanel1,  "Basic Information");

    this.setBasicInformation();
    buttonGroup1.add(jRadioButtonImageColorModeColor);
    buttonGroup1.add(jRadioButtonImageColorModeGray);
    buttonGroup1.add(jRadioButtonImageColorModeIndex);
  }

  void jButton1_actionPerformed(ActionEvent e) {
    this.dispose() ;
  }

  public void setFileInformation(String name,String path,int fileFormat,
                                 int width,int height,
                                 int colorMode,int bitDepth){
    this.jLabelImageName.setText(name);
    this.jLabelImagePath.setText(path);
    if(fileFormat==OpenFile.BMP) this.jLabelImageSuffix.setText("BMP");
    else if(fileFormat==OpenFile.TIFF ) this.jLabelImageSuffix.setText("TIF");
    else if(fileFormat==OpenFile.JPEG ) this.jLabelImageSuffix.setText("JPG");
    else this.jLabelImageSuffix.setText("GIF");
    this.jLabelImageWidth.setText(String.valueOf(width));
    this.jLabelImageHeight.setText(String.valueOf(height));
    if(colorMode==DialogImage.COLOR_GRAY){
      this.jLabelImageColorMode.setText("Gray Mode");
      this.jRadioButtonImageColorModeGray.setSelected(true);
    }
    else if(colorMode==DialogImage.COLOR_INDEX){
      this.jLabelImageColorMode.setText("Index Mode");
      this.jRadioButtonImageColorModeIndex.setSelected(true);
    }
    else if(colorMode==DialogImage.COLOR_RGB){
      this.jLabelImageColorMode.setText("Color Mode");
      this.jRadioButtonImageColorModeColor.setSelected(true);
    }
    this.jLabelImageBitDepth.setText("Bits: "+String.valueOf(bitDepth));
  }

  public void setImageLocRGBGray(Point pt,Color c,int gray,Point ptw){
    this.jLabelImageCoordinate.setText("Image:［"+pt.x+","+pt.y+"］");
    if(c==null)  this.jLabelImageRGB.setText("RGB: ");
    else  this.jLabelImageRGB.setText("RGB: （"+c.getRed()+","+
                                c.getGreen()+","+c.getBlue()+"）");
    this.jLabelImageGray.setText("Gray: "+gray);
    this.jLabelWindowCoordinate.setText("XY:［"+ptw.x+","+ptw.y+"］");
  }

  public void setBasicInformation(){
    DialogImage di;
    Image image;
    di=frameMain.getCurrentImage();
    int fileType=di.getImageFormat() ;
    if(fileType==OpenFile.TIFF )  jLabelImageSuffix.setText("TIF");
    else if(fileType==OpenFile.GIF) jLabelImageSuffix.setText("GIF");
    else if(fileType==OpenFile.JPEG)   jLabelImageSuffix.setText("JPG");
    else if(fileType==OpenFile.BMP)   jLabelImageSuffix.setText("BMP");
    jLabelImagePath.setText(di.getImagePath());
    jLabelImageHeight.setText(Integer.toString(di.getImageHeight()));
    jLabelImageWidth.setText(Integer.toString(di.getImageWidth()));
    jLabelImageName.setText(di.getImageName());

    int colorMode=this.frameMain.getCurrentImage().getColorMode();
    if(colorMode==DialogImage.COLOR_GRAY){
      this.jLabelImageColorMode.setText("Gray Mode");
      this.jRadioButtonImageColorModeGray.setSelected(true);
    }
    else if(colorMode==DialogImage.COLOR_INDEX){
      this.jLabelImageColorMode.setText("Index Mode");
      this.jRadioButtonImageColorModeIndex.setSelected(true);
    }
    else if(colorMode==DialogImage.COLOR_RGB){
      this.jLabelImageColorMode.setText("Color Mode");
      this.jRadioButtonImageColorModeColor.setSelected(true);
    }

    int bitDepth=this.frameMain.getCurrentImage().getBitDepth();
    this.jLabelImageBitDepth.setText("Bits: "+String.valueOf(bitDepth));
  }

  void jPanel2_focusGained(FocusEvent e) {
    this.setSize(100,100);
  }

  void jPanel1_focusGained(FocusEvent e) {
    this.setSize(200,100);
  }
}