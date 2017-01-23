package easygel;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.awt.print.*;
import javax.swing.border.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogPrintPre extends JDialog {
  private JPanel panel1 = new JPanel();
  private Graphics g;
  Graphics2D g2d;
  private Cursor cursor;

  private PrinterJob job;
  private PageFormat pageFormat;
  private  int imageH,imageW;
  private  int pageX1,pageY1;
  private Image image;
  private int imagex,imagey;
  private int oldimagex,oldimagey;
  private  double scale ;

  private Point move1,move2;

  public DialogPrintPre(Frame frame, String title, boolean modal,
                        PageFormat pf,PrinterJob jb,Image ig) {
    super(frame, title, modal);
    this.scale =1;
    this.job =jb;
    this.pageFormat =pf;
    this.image =ig;
    imagex=1;
    imagey=1;
    oldimagex=1;
    oldimagey=1;
    try {
      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    //easygel.uiti.WxsUiti.centerDialog(this, 494,371);
    easygel.uiti.WxsUiti.centerDialog(this, 247,371);
    panel1.setLayout(borderLayout1);
    //this.setTitle("");
    panel1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        panel1_mousePressed(e);
      }
      public void mouseReleased(MouseEvent e) {
        panel1_mouseReleased(e);
      }
    });
    panel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        panel1_mouseDragged(e);
      }
    });
    panel1.setToolTipText("Moving by mouse");
    jPanel1.setBorder(titledBorder1);
    jPanel1.setLayout(gridLayout1);
    jPanel2.setBorder(BorderFactory.createLoweredBevelBorder());
    jButtonExit.setMargin(new Insets(0, 0, 0, 0));
    jButtonExit.setText("Exit");
    jButtonExit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonExit_actionPerformed(e);
      }
    });
    jButtonPrint.setMargin(new Insets(0, 0, 0, 0));
    jButtonPrint.setText("Print");
    jButtonPrint.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonPrint_actionPerformed(e);
      }
    });
    jButtonAmp.setMargin(new Insets(0, 0, 0, 0));
    jButtonAmp.setText("Zoom in");
    jButtonAmp.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonAmp_actionPerformed(e);
      }
    });
    jButtonSmall.setMargin(new Insets(0, 0, 0, 0));
    jButtonSmall.setText("Zoom out");
    jButtonSmall.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonSmall_actionPerformed(e);
      }
    });
    getContentPane().add(panel1, BorderLayout.CENTER);
    jPanel1.add(jButtonPrint, null);
    jPanel1.add(jButtonAmp, null);
    jPanel1.add(jButtonSmall, null);
    jPanel1.add(jButtonExit, null);
    panel1.add(jPanel2, BorderLayout.CENTER);
    panel1.add(jPanel1, BorderLayout.SOUTH);
    this.repaint();
  }

  void jButtonExit_actionPerformed(ActionEvent e) {
    this.dispose() ;
  }

  private void drawImage(){
    //设置屏幕可容纳的高宽
    g=this.jPanel2 .getGraphics() ;
    g2d=(Graphics2D)g;
    imageH=jPanel2.getHeight();
    imageW=jPanel2.getWidth();
    pageX1=0;
    pageY1=0;
    double perH,perW,perPage;
    perH=imageH/pageFormat.getHeight() ;
    perW=imageW/pageFormat.getWidth() ;
    if(perH>perW) perPage=perW;
    else        perPage=perH;
    g2d.drawRect(pageX1,pageY1,
              (int)(pageFormat.getWidth()*perPage),
              (int)(pageFormat.getHeight()*perPage));
    g2d.setColor(new Color(200,0,0));
    g2d.drawRect((int)((pageX1+pageFormat.getImageableX())*perPage),
               (int)((pageY1+pageFormat.getImageableY())*perPage),
              (int)(pageFormat.getImageableWidth() *perPage),
              (int)(pageFormat.getImageableHeight() *perPage));

    g2d.drawImage(image,
                  imagex+(int)((pageX1+pageFormat.getImageableX())*perPage),
                  imagey+(int)((pageY1+pageFormat.getImageableY())*perPage),
                  (int)(image.getWidth(this)*scale*perPage),
                  (int)(image.getHeight(this)*scale*perPage),
                 this);
  }

  void jButtonPrint_actionPerformed(ActionEvent e) {
    double perH,perW,perPage;
    perH=imageH/pageFormat.getHeight() ;
    perW=imageW/pageFormat.getWidth() ;
    if(perH>perW) perPage=perW;
    else        perPage=perH;
    int hh,ww;
    ww=(int)(scale*image.getWidth(this));
    hh=(int)(scale*image.getHeight(this));
    job.setPrintable(new MyPrintable(this.image ,imagex,imagey,perPage,ww,hh)) ;
    try{
      job.print() ;
    }
    catch(PrinterException pe){
      System.out.println(pe.toString());
    };
  }

  class MyPrintable implements Printable{
    private Image image;
    private int drawH,drawW;
    private int locx,locy;
    double per;
    public MyPrintable(Image img,int locx,int locy,double per,int w,int h){
      this.image =img;
      this.locx =locx;
      this.locy =locy;
      this.per =per;
      this.drawH =h;
      this.drawW =w;
    }
    public int print(Graphics g,PageFormat pf,int index){
      if(index==0){
        g.drawImage(image,(int)(locx/per),(int)(locy/per),drawW,drawH,null);
        return Printable.PAGE_EXISTS ;
      }
      return Printable.NO_SUCH_PAGE ;
    }
  }

  void panel1_mousePressed(MouseEvent e) {
    cursor=new Cursor(Cursor.CROSSHAIR_CURSOR);
    this.setCursor(cursor);
    move1=e.getPoint();
  }

  void panel1_mouseReleased(MouseEvent e) {
    Cursor cursor;
    cursor=new Cursor(Cursor.DEFAULT_CURSOR );
    this.setCursor(cursor);
    oldimagex=imagex;
    oldimagey=imagey;
  }

  public void paint(Graphics g){
    super.paint(g);
    drawImage();
  }

  void jButtonAmp_actionPerformed(ActionEvent e) {
    String str;
    str=JOptionPane.showInputDialog(null,"Please select a fold for zoom in: ");
    if(str==null) return;
    double scale2;
    scale2=Double.parseDouble(str);
    scale=scale2;
    this.repaint() ;
  }

  void jButtonSmall_actionPerformed(ActionEvent e) {
    String str;
    str=JOptionPane.showInputDialog(null,"Please select a fold for zoom out: ");
    if(str==null) return;
    double scale2;
    scale2=1/(Double.parseDouble(str));
    scale=scale2;
    this.repaint();
  }

  void panel1_mouseDragged(MouseEvent e) {
    move2=e.getPoint();
    imagex=oldimagex+(int)(move2.getX() -move1.getX()) ;
    imagey=oldimagey+(int)(move2.getY() -move1.getY()) ;
    this.repaint() ;
  }

  private JPanel jPanel1 = new JPanel();
  private TitledBorder titledBorder1;
  private JPanel jPanel2 = new JPanel();
  private JButton jButtonExit = new JButton();
  private JButton jButtonPrint = new JButton();
  private JButton jButtonAmp = new JButton();
  private JButton jButtonSmall = new JButton();
  private GridLayout gridLayout1 = new GridLayout();
  private BorderLayout borderLayout1 = new BorderLayout();
}