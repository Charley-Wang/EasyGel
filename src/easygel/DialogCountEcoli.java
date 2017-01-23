package easygel;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import ij.process.*;
import easygel.layer.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;

public class DialogCountEcoli extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JSlider jSlider1 = new JSlider();
  private JSlider jSlider2 = new JSlider();
  private JSlider jSlider3 = new JSlider();
  private JSlider jSlider4 = new JSlider();
  private JButton jButton2 = new JButton();
  private JButton jButton4 = new JButton();
  private TitledBorder titledBorder1;
  private FrameMain frameMain;
  private int []xxx=new int[256];
  private int []histgram=new int[256];
  //private int []histgram2=new int[256];
  private JTextField jTextField1 = new JTextField();
  private JTextField jTextField2 = new JTextField();
  private JTextField jTextField3 = new JTextField();
  private JTextField jTextField4 = new JTextField();
  private boolean isInit;
  private JButton jButton6 = new JButton();
  private BasicStroke basicStroke;
  private TitledBorder titledBorder2;
  private TitledBorder titledBorder3;
  private JRadioButton jRadioHistROI = new JRadioButton();
  private JRadioButton jRadioHistBact = new JRadioButton();
  private JRadioButton jRadioLineConti = new JRadioButton();
  private JRadioButton jRadioLineDot = new JRadioButton();
  private ButtonGroup buttonGroup1 = new ButtonGroup();
  private ButtonGroup buttonGroup2 = new ButtonGroup();
  private JPanel jPanel1 = new JPanel();
  private JButton jButton1 = new JButton();
  private JButton jButton3 = new JButton();

  public DialogCountEcoli(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    isInit=true;
    frameMain=(FrameMain)frame;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogCountEcoli() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    panel1.setLayout(xYLayout1);
    jLabel1.setForeground(new Color(255, 98, 0));
    jLabel1.setText("White Plaque Begin");
    jLabel2.setForeground(new Color(255, 98, 0));
    jLabel2.setText("White Plaque End");
    jLabel3.setForeground(Color.blue);
    jLabel3.setText("Blue Plaque Begin");
    jLabel4.setForeground(Color.blue);
    jLabel4.setText("Blue Plaque End");
    jButton2.setMargin(new Insets(0, 0, 0, 0));
    jButton2.setText("Report");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton4.setMargin(new Insets(0, 0, 0, 0));
    jButton4.setText("OK");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton4_actionPerformed(e);
      }
    });
    jSlider1.setMaximum(0);
    jSlider2.setMaximum(0);
    jSlider3.setMaximum(0);
    jSlider4.setMaximum(0);
    jSlider1.setMaximum(255);
    jSlider1.setForeground(Color.yellow);
    jSlider1.setAlignmentX((float) 0.0);
    jSlider1.setAlignmentY((float) 0.0);
    jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jSlider1_stateChanged(e);
      }
    });
    jSlider2.setMaximum(255);
    jSlider2.setForeground(Color.yellow);
    jSlider2.setAlignmentX((float) 0.0);
    jSlider2.setAlignmentY((float) 0.0);
    jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jSlider2_stateChanged(e);
      }
    });
    jSlider3.setMaximum(255);
    jSlider3.setForeground(new Color(92, 203, 255));
    jSlider3.setAlignmentX((float) 0.0);
    jSlider3.setAlignmentY((float) 0.0);
    jSlider3.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jSlider3_stateChanged(e);
      }
    });
    jSlider4.setMaximum(255);
    jSlider4.setForeground(Color.blue);
    jSlider4.setAlignmentX((float) 0.0);
    jSlider4.setAlignmentY((float) 0.0);
    jSlider4.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jSlider4_stateChanged(e);
      }
    });
    jTextField1.setText("jTextField1");
    jTextField2.setText("jTextField2");
    jTextField3.setText("jTextField3");
    jTextField4.setText("jTextField4");
    jTextField1.setForeground(new Color(255, 98, 0));
    jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextField1_focusLost(e);
      }
    });
    jTextField2.setForeground(new Color(255, 98, 0));
    jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextField2_focusLost(e);
      }
    });
    jTextField3.setForeground(Color.blue);
    jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextField3_focusLost(e);
      }
    });
    jTextField4.setForeground(Color.blue);
    jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        jTextField4_focusLost(e);
      }
    });
    jButton6.setMaximumSize(new Dimension(43, 29));
    jButton6.setMinimumSize(new Dimension(43, 29));
    jButton6.setPreferredSize(new Dimension(43, 29));
    jButton6.setMargin(new Insets(0, 0, 0, 0));
    jButton6.setText("Cancel");
    jButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton6_actionPerformed(e);
      }
    });
    panel1.setMinimumSize(new Dimension(442, 265));
    panel1.setPreferredSize(new Dimension(442, 265));
    jRadioHistROI.setForeground(Color.blue);
    jRadioHistROI.setSelected(true);
    jRadioHistROI.setText("Gray level image for ROI");
    jRadioHistROI.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jRadioHistROI_actionPerformed(e);
      }
    });
    jRadioHistBact.setForeground(Color.blue);
    jRadioHistBact.setText("Gray level image for Clone");
    jRadioHistBact.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jRadioHistBact_actionPerformed(e);
      }
    });
    jRadioLineConti.setForeground(Color.magenta);
    jRadioLineConti.setSelected(true);
    jRadioLineConti.setText("Draw continues points");
    jRadioLineConti.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jRadioLineConti_actionPerformed(e);
      }
    });
    jRadioLineDot.setForeground(Color.magenta);
    jRadioLineDot.setText("Draw discrete points");
    jRadioLineDot.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jRadioLineDot_actionPerformed(e);
      }
    });
    jPanel1.setBackground(Color.white);
    jPanel1.setForeground(Color.white);
    jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton1.setToolTipText("Adding white clone by left mouse button and adding blue clone by right mouse button");
    jButton1.setMargin(new Insets(0, 0, 0, 0));
    jButton1.setText("Adding clone");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton3.setToolTipText("Delete clone by left mouse button");
    jButton3.setMargin(new Insets(0, 0, 0, 0));
    jButton3.setText("Delete clones");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    this.getContentPane().add(panel1, BorderLayout.CENTER);
    panel1.add(jTextField2,  new XYConstraints(65, 199, 39, 17));
    panel1.add(jSlider2, new XYConstraints(108, 197, 113, 23));
    panel1.add(jLabel1, new XYConstraints(24, 169, 45, -1));
    panel1.add(jTextField1, new XYConstraints(65, 170, 39, 17));
    panel1.add(jSlider1, new XYConstraints(108, 167, 113, 23));
    panel1.add(jLabel2, new XYConstraints(24, 198, 49, -1));
    panel1.add(jTextField4,  new XYConstraints(269, 197, 39, 17));
    panel1.add(jSlider4, new XYConstraints(311, 195, 113, 23));
    panel1.add(jLabel3, new XYConstraints(229, 172, 45, 15));
    panel1.add(jTextField3, new XYConstraints(269, 170, 39, 17));
    panel1.add(jSlider3, new XYConstraints(312, 166, 113, 23));
    panel1.add(jLabel4, new XYConstraints(229, 198, 43, -1));
    panel1.add(jRadioLineConti,  new XYConstraints(342, 68, 78, 14));
    panel1.add(jRadioLineDot, new XYConstraints(342, 91, 78, 14));
    panel1.add(jButton2, new XYConstraints(344, 123, 78, 24));
    panel1.add(jRadioHistROI, new XYConstraints(342, 9, 100, 17));
    panel1.add(jRadioHistBact, new XYConstraints(342, 32, -1, 18));
    panel1.add(jPanel1,            new XYConstraints(0, 0, 306, 156));
    panel1.add(jButton1,     new XYConstraints(128, 232, 73, 25));
    panel1.add(jButton3,     new XYConstraints(231, 232, 73, 25));
    panel1.add(jButton6,  new XYConstraints(26, 232, 73, 25));
    panel1.add(jButton4,          new XYConstraints(333, 232, 73, 25));

    this.countROIHistgram();

    this.jSlider1 .setValue(0);
    this.jSlider2 .setValue(160);
    this.jSlider3 .setValue(170);
    this.jSlider4 .setValue(255);
    this.jTextField1 .setText("0");
    this.jTextField2 .setText("160");
    this.jTextField3 .setText("170");
    this.jTextField4 .setText("255");

    float width=2;
    int cap=BasicStroke.CAP_SQUARE ;
    int join=BasicStroke.JOIN_BEVEL;
    float miterlimit=10.0f;
    float dash_phase=0;
    basicStroke= new BasicStroke(width, cap, join, miterlimit);

    isInit=false;

    // Verifier
    jTextField1.setInputVerifier(new Verifier(Verifier.INT,true,0,true,255,this,true));
    jTextField2.setInputVerifier(new Verifier(Verifier.INT,true,0,true,255,this,true));
    jTextField3.setInputVerifier(new Verifier(Verifier.INT,true,0,true,255,this,true));
    jTextField4.setInputVerifier(new Verifier(Verifier.INT,true,0,true,255,this,true));

    if(this.frameMain .getExeMethod() ==0){
      this.jButton4 .setText("OK");
      this.jButton6 .setText("Cancel");
    }
    else{
      this.setLocation(this.frameMain .getWizardWinLocation()) ;
      //this.jButton4 .setText("Next▼");
      this.jButton4 .setText("OK");
      this.jButton6 .setText("Last▲") ;
    }
    buttonGroup1.add(jRadioHistROI);
    buttonGroup1.add(jRadioHistBact);
    buttonGroup2.add(jRadioLineConti);
    buttonGroup2.add(jRadioLineDot);
  }

  private void countROIHistgram(){
    // 计算整个ROI区域
    for(int ii=1;ii<=256;ii++) histgram[ii-1]=0;
    for(int ii=1;ii<=256;ii++) xxx[ii-1]=ii-1;
    Rectangle rect=frameMain.getCurrentImage() .getROI() ;
    ImageProcessor ip=this.frameMain .getCurrentImage() .getIP() ;
    int v,x,y;
    int max=0;
    for(int ii=1;ii<=rect.getWidth() ;ii++){
      for(int jj=1;jj<=rect.getHeight() ;jj++){
        x=rect.getLocation().x;
        y=rect.getLocation().y;
        v=(int)ip.getPixelValue(x+ii-1,y+jj-1);
        if(v>=0 && v<=255) histgram[v]++;
        if(histgram[v]>max) max=histgram[v];
      }
    }
    for(int ii=1;ii<=256;ii++){
      //System.out.println(ii+"="+histgram[ii-1]);
      histgram[ii-1]*=256*0.618/max;
      histgram[ii-1]=(int)(256.0*0.618)-histgram[ii-1]+10;
      // add 10 to histgram
      //histgram2[ii-1]=histgram[ii-1]+10;
    }
  }

  private void countBacterialHistgram(){
    // 只计算cell中的内容
    for(int ii=1;ii<=256;ii++) histgram[ii-1]=0;
    for(int ii=1;ii<=256;ii++) xxx[ii-1]=ii-1;
    ImageProcessor ip=this.frameMain .getCurrentImage() .getIP() ;
    int v,x,y;
    int max=0;
    LayerCell layerCell=this.frameMain.getCurrentImage().getLayerCell();
    Vector vector=layerCell.getCurrent();
    int sz=vector.size();
    InfoCell cell;
    Polygon pg;
    Rectangle rect2;
    int x1,x2,y1,y2;
    for(int ii=1;ii<=sz;ii++){
      cell=(InfoCell)vector.elementAt(ii-1);
      pg=cell.m_polygon;
      rect2=pg.getBounds();
      x1=rect2.getLocation().x;
      y1=rect2.getLocation().y;
      x2=(int)(x1+rect2.getWidth()+1);
      y2=(int)(y1+rect2.getHeight()+1);
      for(int pp=x1;pp<=x2;pp++){
        for(int qq=y1;qq<=y2;qq++){
          if(pg.contains(pp,qq)==true){
            v=(int)ip.getPixelValue(pp,qq);
            if(v>=0 && v<=255) histgram[v]++;
            if(histgram[v]>max) max=histgram[v];
          }
        }
      }
    }
    for(int ii=1;ii<=256;ii++){
      //System.out.println(ii+"="+histgram[ii-1]);
      histgram[ii-1]*=256*0.618/max;
      histgram[ii-1]=(int)(256.0*0.618)-histgram[ii-1]+10;
      // add 10 to histgram
      //histgram2[ii-1]=histgram[ii-1]+10;
    }
  }

  public void paint(Graphics g){
    super.paint(g);
    Graphics2D g2d;
    g2d=(Graphics2D)g;
    int w0,w1,b0,b1,tmp;
    w0=this.jSlider1.getValue();
    w1=this.jSlider2.getValue();
    b0=this.jSlider3.getValue();
    b1=this.jSlider4.getValue();
    if(w1<w0) {tmp=w0;w0=w1;w1=tmp;}
    if(b1<b0) {tmp=b0;b0=b1;b1=tmp;}
    int c[]=new int[256];
    for(int ii=1;ii<=256;ii++) c[ii-1]=0;
    for(int ii=w0;ii<=w1;ii++) c[ii]+=10;
    for(int ii=b0;ii<=b1;ii++) c[ii]+=100;
    Color color;
    //g2d.setColor(Color.white);
    //g2d.clearRect(10,10,80,80);
    g2d.setStroke(basicStroke);
    for(int ii=1;ii<=255;ii++){
      if(c[ii-1]==0) color=Color.black;
      else if(c[ii-1]==10) color=new Color(255,98,0);
      else if(c[ii-1]==100) color=Color.blue;
      else color=Color.green;
      g2d.setColor(color);
      if(this.jRadioLineConti.isSelected()==true){
        g2d.drawLine(ii-1,histgram[ii-1],ii,histgram[ii]);
      }
      else{
        g2d.drawLine(ii-1,histgram[ii-1],ii-1,histgram[ii-1]);
      }
    }
    //g2d.setColor(Color.black);
    //g2d.drawLine(1,180,255,180);
    //g2d.drawLine(2,1,2,180);
    /*
    g.setColor(Color.black);
    g.drawPolyline(getxx(0,w0),getyy(0,w0,histgram),w0+1);
    g.setColor(new Color(255,98,0));
    g.drawPolyline(getxx(w0,w1),getyy(w0,w1,histgram),w1-w0+1);
    g.setColor(Color.black);
    g.drawPolyline(getxx(w1,255),getyy(w1,255,histgram),255-w1+1);

    g.setColor(Color.red);
    g.drawPolyline(getxx(0,b0),getyy(0,b0,histgram2),b0+1);
    g.setColor(Color.blue);
    g.drawPolyline(getxx(b0,b1),getyy(b0,b1,histgram2),b1-b0+1);
    g.setColor(Color.red);
    g.drawPolyline(getxx(b1,255),getyy(b1,255,histgram2),255-b1+1);
    */
  }

  private int[] getxx(int begin,int end){
    int []xx=new int[end-begin+1];
    for(int ii=begin;ii<=end;ii++) xx[ii-begin]=ii;
    return xx;
  }

  private int[] getyy(int begin,int end,int []hist){
    int []yy=new int[end-begin+1];
    for(int ii=begin;ii<=end;ii++) yy[ii-begin]=hist[ii];
    return yy;
  }

  private void setYellowBlue(){
    LayerCell layerCell=this.frameMain .getCurrentImage() .getLayerCell() ;
    InfoCell cell;
    int size=layerCell.getCurrent() .size() ;
    int min1,max1,min2,max2,temp;
    min1=jSlider1.getValue();
    max1=jSlider2.getValue();
    min2=jSlider3.getValue();
    max2=jSlider4.getValue();
    if(min1>max1){
      temp=min1;
      min1=max1;
      max1=temp;
    }
    if(min2>max2){
      temp=min2;
      min2=max2;
      max2=temp;
    }
    for(int ii=1;ii<=size;ii++){
      cell=(InfoCell)layerCell.getCurrent().elementAt(ii-1);
      if(cell.m_area ==0) {
        cell.edgeColor =Color.black ;
       continue;
      }
      //int hist=(int)(256.0*0.618-cell.m_graySum /cell.m_area )+10;
      int hist=(int)(cell.m_graySum /cell.m_area);
      if(hist>=min1 && hist<=max1) cell.edgeColor =Color.yellow ;
      else if(hist>=min2 && hist<=max2) cell.edgeColor =Color.blue  ;
      else cell.edgeColor =Color.pink  ;
    }
    this.frameMain .getCurrentImage() .paintImage() ;
  }

  void jButton1_actionPerformed(ActionEvent e) {
    //this.frameMain.getCurrentImage().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    this.frameMain.getCurrentImage().mySetCursor(true,DialogImage.CURADD);
    this.frameMain.setCmmStatus("AddBacterial");
  }

  void jSlider1_stateChanged(ChangeEvent e) {
    if(isInit==true) return;
    this.jTextField1 .setText(String.valueOf(jSlider1.getValue()) );
    this.setYellowBlue() ;
    this.repaint();
  }

  void jSlider2_stateChanged(ChangeEvent e) {
    if(isInit==true) return;
    this.jTextField2 .setText(String.valueOf(jSlider2.getValue()) );
    this.setYellowBlue() ;
    this.repaint();
  }

  void jSlider3_stateChanged(ChangeEvent e) {
    if(isInit==true) return;
    this.jTextField3 .setText(String.valueOf(jSlider3.getValue()) );
    this.setYellowBlue() ;
    this.repaint();
  }

  void jSlider4_stateChanged(ChangeEvent e) {
    if(isInit==true) return;
    this.jTextField4 .setText(String.valueOf(jSlider4.getValue()) );
    this.setYellowBlue() ;
    this.repaint();
  }

  void jButton4_actionPerformed(ActionEvent e) {
    this.dispose() ;
    if(this.frameMain .getExeMethod() ==1){
      this.frameMain .setWizardWinLocation(this.getLocation() ) ;
      this.frameMain.doStep(true);
    }
  }

  void jButton2_actionPerformed(ActionEvent e) {
    //String[] colNames={"序号","斑点名称","斑点个数","占蓝白斑点％","占总斑点％"};
    String[] colNames={"No","Clone Name","# of Clones","blue-white％"};
    Vector vector=this.frameMain .getCurrentImage() .getLayerCell().getCurrent() ;
    int size=vector.size() ;
    //String[][] dataTable=new String[3][colNames.length];
    String[][] dataTable=new String[2][colNames.length];
    InfoCell cell;
    int yellow=0,blue=0;
    for(int ii=1;ii<=size;ii++){
      cell=(InfoCell)vector.elementAt(ii-1);
      if(cell.edgeColor .equals(Color.blue ) ) blue++;
      else if(cell.edgeColor .equals(Color.yellow ) ) yellow++;
    }

    Point pt=this.frameMain.getCurrentImage().getWhiteBlueBacterialNum();
    yellow+=pt.x;
    blue+=pt.y;
    size+=pt.x;
    size+=pt.y;

    if(size==0 || blue+yellow==0) return;

    // 精度设置
    int p=4;
    double temp;

    dataTable[0][0]="1";
    dataTable[0][1]="White Clone";
    dataTable[0][2]=String.valueOf(yellow);
    temp=(double)yellow*100.0/((double)blue+(double)yellow);
    dataTable[0][3]=String.valueOf(this.setPrecision(temp,p));
    double white=Double.valueOf(dataTable[0][3]).doubleValue();

    //temp=(double)yellow*100.0/(double)size;
    //dataTable[0][4]=String.valueOf(this.setPrecision(temp,p));

    dataTable[1][0]="2";
    dataTable[1][1]="Blue Clone";
    dataTable[1][2]=String.valueOf(blue);
    //temp=(double)blue*100.0/((double)blue+(double)yellow);
    //dataTable[1][3]=String.valueOf(setPrecision(temp,p));
    dataTable[1][3]=String.valueOf(setPrecision(100-white,p));
    //temp=(double)blue*100.0/(double)size;
    //dataTable[1][4]=String.valueOf(setPrecision(temp,p));

    /*
    dataTable[2][0]="3";
    dataTable[2][1]="其他斑点";
    dataTable[2][2]=String.valueOf(size-yellow-blue);
    dataTable[2][3]="";
    temp=((double)size-(double)yellow-(double)blue)*100.0/(double)size;
    dataTable[2][4]=String.valueOf(setPrecision(temp,p));
    */

    //String []text=new String[4];
    String []text=new String[3];
    text[0]=this.getTitle();
    Date date=new Date(System.currentTimeMillis() );
    text[1]="Date: "+date.toString();
    //text[2]="总斑点数为："+String.valueOf(size);
    //text[3]="蓝白斑点数："+String.valueOf(yellow+blue);
    text[2]="# of white-blue"+String.valueOf(yellow+blue);
    // 显示之
    DialogReport di=new DialogReport(this.frameMain ,"",true);
    di.setContext(this.getTitle() +" results ",text,colNames,dataTable,false);
    di.show() ;
  }

  private double setPrecision(double data,int bits){
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

  void jButton6_actionPerformed(ActionEvent e) {
    this.dispose() ;
    if(this.frameMain .getExeMethod() ==1){
      this.frameMain .setWizardWinLocation(this.getLocation() ) ;
      this.frameMain.doStep(false);
    }
  }

  void jRadioHistROI_actionPerformed(ActionEvent e) {
    if(this.isInit==true) return;
    if(this.jRadioHistBact.isSelected()==true) this.countBacterialHistgram();
    else this.countROIHistgram();
    this.repaint();
  }

  void jRadioHistBact_actionPerformed(ActionEvent e) {
    jRadioHistROI_actionPerformed(e);
  }

  void jRadioLineConti_actionPerformed(ActionEvent e) {
    if(this.isInit==true) return;
    this.repaint();
  }

  void jRadioLineDot_actionPerformed(ActionEvent e) {
    if(this.isInit==true) return;
    this.repaint();
  }

  void jButton3_actionPerformed(ActionEvent e) {
    //this.frameMain.getCurrentImage().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    this.frameMain.getCurrentImage().mySetCursor(true,DialogImage.CURDELETE);
    this.frameMain.setCmmStatus("DeleteBacterial");
  }

  void jTextField1_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(jTextField1.getText());
      jSlider1.setValue(tmp);
    }
    catch(NumberFormatException e2){
    }
  }

  void jTextField3_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(jTextField3.getText());
      jSlider3.setValue(tmp);
    }
    catch(NumberFormatException e2){
    }
  }

  void jTextField2_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(jTextField2.getText());
      jSlider2.setValue(tmp);
    }
    catch(NumberFormatException e2){
    }
  }

  void jTextField4_focusLost(FocusEvent e) {
    try{
      int tmp=Integer.parseInt(jTextField4.getText());
      jSlider4.setValue(tmp);
    }
    catch(NumberFormatException e2){
    }
  }

}