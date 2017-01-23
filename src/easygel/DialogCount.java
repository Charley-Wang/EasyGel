package easygel;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.awt.event.*;
import easygel.layer.*;
import java.util.*;
import easygel.*;
import easygel.setting.*;
import java.io.*;
import java.util.*;

/**
 *
 * <p>Title: 各种拟合操作窗口</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Deta-BioSciences</p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogCount extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JPanel jPanel1 = new JPanel();
  private TitledBorder titledBorder1;
  private JLabel jLabelRectTitle = new JLabel();
  private XYLayout xYLayout2 = new XYLayout();
  private JButton jButtonMouseSelObj = new JButton();
  private TitledBorder titledBorder2;
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  private JLabel jLabelStdTitle = new JLabel();
  private JLabel jLabelPhyTitle = new JLabel();
  private JButton jButton4 = new JButton();
  private JButton jButton5 = new JButton();
  private JLabel jLabel4 = new JLabel();
  private JComboBox jComboBoxFitMethod = new JComboBox();
  private JButton jButton6 = new JButton();
  private JButton jButton8 = new JButton();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JList jListAllObj = new JList();
  private JScrollPane jScrollPane2 = new JScrollPane();
  private JList jListStdObj = new JList();
  private JScrollPane jScrollPane3 = new JScrollPane();
  private JList jListStdPhy = new JList();
  private FrameMain frameMain;
  private JButton jButton1 = new JButton();
  private JButton jButton7 = new JButton();

  /**
   * 各种执行的序号及内容
   * 1: 1D分析——MW-pI值
   * 2: 1D分析——归一化定量
   * 3: 1D分析——校准定量
   * 4: 2D电泳——MW值计算
   * 5: 2D电泳——pI值计算
   * 6: 阵列分析——定量
   * 7: 其他类分析——MW-pI(Rf)值
   * 8: 其他类分析——定量
   * 9: 2D电泳——归一化定量
   * 10:2D电泳——校准定量
   */

  private int totalNo;              /** 共有几个要计算内容,即各分析要计算的个数和 */
  private int currentNo;            /** 当前执行序号 */

  // 当前窗口设置
  private String[] winTitle;        // 窗口标题
  private String[] rectTitle;       // 框架标题
  private String[] buttonSelObj;    // 用鼠标选择对象标题
  private String[] allObjects;      // 所有对象描述
  private String[] stdTitle;        // 标准描述
  private String[] stdObjects;      // 标准对象描述
  private String[] phyParaTitle;    // 对应物理量标题描述
  private String[] phyPara;         // 对应物理量
  private String[] fitMethods;      // 拟合方法描述
  private int[] defaultFitMethod;   // 默认方法
  private boolean[] isFixFitMethod; // 方法选择是否禁用

  // 标准选择的参量
  private int stdTotalNo;                    // 标准参量个数
  private Vector stdData=new Vector();       // 标准参量
  private Vector stdPhysics=new Vector();    // 标准参量对应物理量
  private String instruction;                // 标准参量对应物理量含义
  private String unit;                       // 标准参量对应物理量单位

  // 对标准参量的:xxx与data将形成拟合,confi[]为拟合产生的系数
  private double data[];
  private double xxx[];
  private double confi[];

  /** 生成的曲线类 */
  private Curve curve=null;

  //
  private int []stdObjectNo;      // 标准对象序号
  private int []nonstdObjectNo;   // 非标准对象序号

  // 执行方式以及一键方式参数
  private  int exeMethod;           // 0--单独执行方式
                                    // 1--Step by step方式
                                    // 2--全部默认方式,一键方式
  private Vector set=new Vector();
  private boolean isVisible;
  private boolean isInit;
  private int objects;
  private boolean canReport;

  /**
   * exeMethod==0/1用此法构造
   * @param frame
   * @param title
   * @param modal
   */
  public DialogCount(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    this.stdTotalNo =0;
    this.isInit=true;
    this.canReport=false;
    frameMain=(FrameMain)frame;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * exeMethod==2用此法构造
   * @param frame
   */
  public DialogCount(Frame frame) {
    super();
    frameMain=(FrameMain)frame;
  }

  public void setExeMethod(int exeMethod){
    this.exeMethod =exeMethod;
  }

  public int getExeMethod(){
    return this.exeMethod ;
  }

  public void setCurrentNo(int currentNo){
    this.currentNo =currentNo;
  }

  public int getCurrentNo(){
    return this.currentNo ;
  }

  public void setInterfaceInit(boolean isLast){
     //
    if(this.frameMain .getExeMethod() ==0){
      this.jButton8 .setText("OK");
      this.jButton7 .setText("Cancel");
    }
    else{
      this.setLocation(this.frameMain .getWizardWinLocation()) ;
      if(isLast==true) this.jButton8 .setText("OK" );
      else  this.jButton8 .setText("Next▼");
      this.jButton7 .setText("Last▲") ;
    }

    // 初始化界面
    this.jComboBoxFitMethod .removeAllItems() ;
    String []empty=new String[0];
    this.jListAllObj .setListData(empty);
    this.jListStdObj .setListData(empty);
    this.jListStdPhy .setListData(empty);

    // 初始化共有变量
    stdData.removeAllElements() ;
    stdPhysics.removeAllElements() ;
    data=null;
    xxx=null;
    confi=null;
    stdObjectNo=null;
    nonstdObjectNo=null;

    //
    setParaV();
    if(this.currentNo >=1 && this.currentNo <=3){
      Layer1D layer1D;
      layer1D=frameMain.getCurrentImage().getLayer1D();
      if(layer1D!=null){
        int laneNum=layer1D.getlanenum();
        String str[]=new String[laneNum];
        // added by wxs 20030826
        this.objects=laneNum/2;
        for(int ii=1;ii<=laneNum/2;ii++){
          str[ii-1]="Lane No: "+ii;
        }
        this.jListAllObj.setListData(str );
      }
    }
    else{
      LayerCell layerCell;
      layerCell=frameMain.getCurrentImage() .getLayerCell() ;
      int size=layerCell.getCurrent() .size() ;
      String str[]=new String[size];
      // added by wxs 20030826
      this.objects=size;
      for(int ii=1;ii<=size;ii++){
        str[ii-1]=String.valueOf(ii);
      }
      this.jListAllObj.setListData(str);
    }

    // added by wxs 20030827
    if(this.frameMain.getNowAnalyse()!=1) return;

    // added by wxs 20030823 -04
    this.setInterfaceVFromFile();
    this.setInterfaceFromV();

    // added by wxs 20030823 -05
    if(this.frameMain.getExeMethod()==1) this.isVisible=true;
    this.setVisible(this.isVisible);
    this.isInit=false;

    // added by wxs 20030823 -06
    this.doExeMethodEasyKey();
  }

  //设置各种方法的变量
  private void setParaV(){
    totalNo=10;
    winTitle=new String[totalNo];
    rectTitle=new String[totalNo];
    buttonSelObj=new String[totalNo];
    stdTitle=new String[totalNo];
    phyParaTitle=new String[totalNo];
    fitMethods=new String[8];
    isFixFitMethod=new boolean[totalNo];
    defaultFitMethod=new int [totalNo];

    winTitle[0]="〖6 OF 10〗1D Analysis——MW-pI";
    winTitle[1]="〖8 OF 10〗1D Analysis——Normalization";
    winTitle[2]="〖10 OF 10〗1D Analysis——Calibration";
    winTitle[3]="〖3 OF 6〗2D Gel Electrophoresis——Calculate MW";
    winTitle[4]="〖4 OF 6〗2D Gel Electrophoresis——Calcuate pI";
    winTitle[5]="〖3 OF 3〗Array Analysis";
    winTitle[6]="〖3 OF 4〗Other Analysis——MW-pI(Rf)";
    winTitle[7]="〖4 OF 4〗Other Analysis";
    winTitle[8]="〖5 OF 6〗2D Gel Electrophoresis——Normalization";
    winTitle[9]="〖6 OF 6〗2D Gel Electrophoresis——Calibration";

    rectTitle[0]="       Select Marker          ";
    rectTitle[1]="  Select Lane and Coefficient ";
    rectTitle[2]="     Select Land and MW       ";
    rectTitle[3]="         Select Band          ";
    rectTitle[4]=rectTitle[3];
    rectTitle[5]="         Select Array         ";
    rectTitle[6]="         Select Object        ";
    rectTitle[7]="         Select Ojbect        ";
    rectTitle[8]="  Select Lane and Coefficient ";
    rectTitle[9]="       Select Land and MW     ";

    buttonSelObj[0]="Select Lane with Mouse";
    buttonSelObj[1]="Select Lane with Mouse";
    buttonSelObj[2]="Select Lane with Mouse";
    buttonSelObj[3]="Select Band with Mouse";
    buttonSelObj[4]=buttonSelObj[3];
    buttonSelObj[5]="Select Array with Mouse";
    buttonSelObj[6]="Select Array with Mouse";
    buttonSelObj[7]="Select Object with Mouse";
    buttonSelObj[8]="Select Lane with Mouse";
    buttonSelObj[9]="Select Lane with Mouse";

    stdTitle[0]="Marker";
    stdTitle[1]="Lane";
    stdTitle[2]="Lane";
    stdTitle[3]="Band";
    stdTitle[4]=stdTitle[3];
    stdTitle[5]="Object";
    stdTitle[6]="Object";
    stdTitle[7]="Object";
    stdTitle[8]="Lane";
    stdTitle[9]="Lane";

    phyParaTitle[0]="      MW/pI    ";
    phyParaTitle[1]="  MW/pI/length ";
    phyParaTitle[2]="       MW      ";
    phyParaTitle[3]="   Select  MW  ";
    phyParaTitle[4]="   Select pI   ";
    phyParaTitle[5]="Standard Values";
    phyParaTitle[6]="  MW/pI/length ";
    phyParaTitle[7]="Standard Values";
    phyParaTitle[8]=" MW/pI/length  ";
    phyParaTitle[9]="      MW       ";

    fitMethods[0]="";
    fitMethods[1]="Linear Regression";
    fitMethods[2]="Quadratic Fit";
    fitMethods[3]="Cubic Fit";
    fitMethods[4]="Quartic Fit";
    fitMethods[5]="Quintic Fit";
    fitMethods[6]="Log Fit";
    fitMethods[7]="Ln Fit";

    defaultFitMethod[0]=3;
    defaultFitMethod[1]=1;
    defaultFitMethod[2]=1;
    defaultFitMethod[3]=3;
    defaultFitMethod[4]=3;
    defaultFitMethod[5]=1;
    defaultFitMethod[6]=3;
    defaultFitMethod[7]=1;
    defaultFitMethod[8]=1;
    defaultFitMethod[9]=1;

    isFixFitMethod[0]=false;
    isFixFitMethod[1]=true;
    isFixFitMethod[2]=true;
    isFixFitMethod[3]=false;
    isFixFitMethod[4]=false;
    isFixFitMethod[5]=false;
    isFixFitMethod[6]=false;
    isFixFitMethod[7]=false;
    isFixFitMethod[8]=true;
    isFixFitMethod[9]=true;

    this.setTitle(winTitle[this.currentNo -1]);
    this.jLabelRectTitle.setText(rectTitle[this.currentNo -1]);
    this.jButtonMouseSelObj .setText(buttonSelObj[this.currentNo -1]);
    this.jLabelStdTitle .setText(stdTitle[this.currentNo -1]);
    this.jLabelPhyTitle .setText(phyParaTitle[this.currentNo -1]);
    for(int ii=1;ii<=8;ii++){
      this.jComboBoxFitMethod .addItem(fitMethods[ii-1]);
    }
    this.jComboBoxFitMethod .setSelectedIndex(defaultFitMethod[this.currentNo-1 ]) ;
    this.jComboBoxFitMethod .setEnabled(!isFixFitMethod[this.currentNo -1]) ;
  }

  private void jbInit() throws Exception {
    //预定义各种方法的
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    panel1.setLayout(xYLayout1);
    this.setTitle("1D Analysi - MW-pI Value）");
    jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
    jPanel1.setLayout(xYLayout2);
    jLabelRectTitle.setText("Select Marker");
    jButtonMouseSelObj.setMargin(new Insets(0, 0, 0, 0));
    jButtonMouseSelObj.setText("Select Object with Mouse");
    jButtonMouseSelObj.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButtonMouseSelObj_actionPerformed(e);
      }
    });
    jButton2.setToolTipText("Copy from Left to Right");
    jButton2.setMargin(new Insets(0, 0, 0, 0));
    jButton2.setText(">>");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton3.setMargin(new Insets(0, 0, 0, 0));
    jButton3.setText("<<");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jLabelStdTitle.setText("Marker");
    jLabelPhyTitle.setText("MW/pI/length");
    jButton4.setMargin(new Insets(0, 0, 0, 0));
    jButton4.setText("import...");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton4_actionPerformed(e);
      }
    });
    jButton5.setMargin(new Insets(0, 0, 0, 0));
    jButton5.setText("<<");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton5_actionPerformed(e);
      }
    });
    jLabel4.setText("Fitting Mode：");
    jButton6.setText("Fitting");
    jButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton6_actionPerformed(e);
      }
    });
    jButton8.setMargin(new Insets(0, 0, 0, 0));
    jButton8.setText("OK");
    jButton8.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton8_actionPerformed(e);
      }
    });
    jButton1.setText("Report");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton7.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton7_actionPerformed(e);
      }
    });
    jButton7.setText("Cancel");
    jButton7.setMargin(new Insets(0, 0, 0, 0));
    jButton7.setPreferredSize(new Dimension(43, 29));
    jButton7.setMinimumSize(new Dimension(43, 29));
    jButton7.setMaximumSize(new Dimension(43, 29));
    panel1.setMinimumSize(new Dimension(442, 265));
    panel1.setPreferredSize(new Dimension(442, 265));
    jListStdPhy.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        jListStdPhy_mouseMoved(e);
      }
    });
    getContentPane().add(panel1);
    jPanel1.add(jButton3,    new XYConstraints(114, 115, 50, 18));
    jPanel1.add(jButton2,   new XYConstraints(114, 77, 50, 18));
    jPanel1.add(jButton5,  new XYConstraints(265, 115, 50, 18));
    jPanel1.add(jButton4,   new XYConstraints(266, 75, 50, 18));
    jPanel1.add(jScrollPane2, new XYConstraints(168, 62, 90, 85));
    jPanel1.add(jScrollPane3, new XYConstraints(323, 64, 85, 83));
    jPanel1.add(jScrollPane1, new XYConstraints(10, 64, 99, 84));
    jPanel1.add(jLabelPhyTitle, new XYConstraints(322, 38, 90, 15));
    jPanel1.add(jLabelStdTitle, new XYConstraints(180, 34, 72, 19));
    jPanel1.add(jButton1, new XYConstraints(329, 163, 66, 23));
    jPanel1.add(jButton6, new XYConstraints(241, 163, 66, 23));
    jPanel1.add(jComboBoxFitMethod, new XYConstraints(93, 163, 120, 19));
    jPanel1.add(jLabel4, new XYConstraints(18, 165, 63, 17));
    jPanel1.add(jButtonMouseSelObj, new XYConstraints(9, 36, 103, 24));
    jPanel1.add(jLabelRectTitle,       new XYConstraints(112, 9, -1, -1));
    jScrollPane1.getViewport().add(jListAllObj, null);
    jScrollPane3.getViewport().add(jListStdPhy, null);
    jScrollPane2.getViewport().add(jListStdObj, null);
    panel1.add(jButton7,    new XYConstraints(90, 232, 73, 25));
    panel1.add(jButton8,    new XYConstraints(273, 232, 73, 25));
    panel1.add(jPanel1,                             new XYConstraints(11, 15, 420, 203));

  }

  void jButtonMouseSelObj_actionPerformed(ActionEvent e) {

  }

  void jButton2_actionPerformed(ActionEvent e) {
    if(this.currentNo >=4){
      String [] empty=new String[0];
      this.jListStdObj.setListData(empty);
      int no=this.jListAllObj.getSelectedIndex() +1;
      this.stdData.addElement(new Integer(no));
      this.jListStdObj.setListData(this.stdData ) ;
    }
    else if(this.currentNo >=1 && this.currentNo <=3){
      if(this.jListAllObj .isSelectionEmpty() ==true) return;
      String [] empty=new String[0];
      this.jListStdObj.setListData(empty);
      this.jListStdPhy.setListData(empty) ;
      this.stdData .removeAllElements() ;
      this.stdPhysics .removeAllElements() ;
      this.stdTotalNo =1;
      int no=this.jListAllObj .getSelectedIndex() +1;
      this.stdData.addElement(new Integer(no));
      String str[]=new String[1];
      str[0]="第"+no+"泳道";
      this.jListStdObj .setListData(str);
      Layer1D layer1D;
      layer1D=this.frameMain .getCurrentImage() .getLayer1D() ;

      if(this.currentNo ==1){
        double Rf;
        double []locLine;
        Rectangle rect;
        rect=this.frameMain .getCurrentImage() .getROI();
        locLine=layer1D.getLinesLocation(no);
        int begin=rect.getLocation() .y;
        double L=rect.getHeight() ;
        this.xxx=new double[locLine.length];
        for(int ii=1;ii<=locLine.length ;ii++){
          Rf=(locLine[ii-1]-begin)/L;
            xxx[ii-1]=Rf;
        }
      }
      else if(this.currentNo ==2 || this.currentNo ==3){
        // 灰度积分值
        int lines=layer1D.getLines(no);
        Point pt;
        int xxxNum=0;
        LayerCell layerCell=this.frameMain .getCurrentImage() .getLayerCell() ;
        InfoCell cell;
        for(int ii=1;ii<=lines;ii++){
          pt=layer1D.getLineCenterPoint(no,ii);
          if(pt==null) continue;
          cell=layerCell.getInfoCell(pt);
          if(cell==null) continue;
          xxxNum++;
        }
        this.xxx=new double[xxxNum];
        int xxxno=0;
        for(int ii=1;ii<=lines;ii++){
          pt=layer1D.getLineCenterPoint(no,ii);
          if(pt==null) continue;
          cell=layerCell.getInfoCell(pt);
          if(cell==null) continue;
          xxx[xxxno]=cell.m_graySum ;
          xxxno++;
        }
      }
    }
  }

  void jButton3_actionPerformed(ActionEvent e) {
    if(this.currentNo >=1 && this.currentNo <=3){
      String [] empty=new String[0];
      this.jListStdObj.setListData(empty);
      this.jListStdPhy.setListData(empty) ;
      this.stdData .removeAllElements() ;
      this.stdPhysics .removeAllElements() ;
      this.stdTotalNo =0;
    }
    else{
     int no=this.jListStdObj.getSelectedIndex() ;
     this.stdData.removeElementAt(no);
     this.jListStdObj .setListData(this.stdData ) ;
    }
  }

  void jButton4_actionPerformed(ActionEvent e) {
    DialogStdPhy di=new DialogStdPhy(this.frameMain,"Select Standard",true);
    di.show();
    if(di.getInstrution() ==null)return;
    this.instruction =di.getInstrution() ;
    this.unit =di.getUnit() ;
    this.data =di.getData() ;
    String [] empty=new String[0];
    this.jListStdPhy.setListData(empty);
    String []str=new String[1];
    str[0]=this.instruction +"("+this.unit +"):";
    for(int ii=1;ii<=data.length ;ii++) str[0]+=this.data [ii-1]+" ";
    this.jListStdPhy .setListData(str );
  }

  void jButton5_actionPerformed(ActionEvent e) {
    String [] empty=new String[0];
    this.jListStdPhy.setListData(empty) ;
  }

  void jButton6_actionPerformed(ActionEvent e){
    // 必须选择标准系列
    if(this.jListAllObj.isSelectionEmpty() ==true) {
      if(this.frameMain.getExeMethod()!=2){
        JOptionPane.showMessageDialog(null,"Please select a standard","Alert",JOptionPane.ERROR_MESSAGE );
      }
      this.canReport=false;
      return;
    }

    // 为一键功能服务
    if(this.frameMain.getNowAnalyse()==1){
      if(this.frameMain.getNowStep()==6){
        String str;
        str="⊿ Calculate MW-pI━［Fitting Mode="+(String)(this.jComboBoxFitMethod.getSelectedObjects()[0]);
        str+="］";
        this.frameMain.getCurrentImage().setDoMethod(6,str);
      }
      else if(this.frameMain.getNowStep()==8){
        String str;
        str="⊿ Normalization";
        this.frameMain.getCurrentImage().setDoMethod(8,str);
      }
      else if(this.frameMain.getNowStep()==10){
        String str;
        str="⊿ Calibration";
        this.frameMain.getCurrentImage().setDoMethod(10,str);
      }
    }

    // 开始准备数据
    curve=new Curve();
    int begin=0;        // 电泳上样点坐标
    double L=0;         // 电泳泳道的整个长度
    double Rf=0;        // 条带Rf=Ln/L，其中Ln(本程序为xxx)指样品点距离上样点的距离
    LayerCell layerCell=this.frameMain .getCurrentImage() .getLayerCell() ;
    Layer1D  layer1D=this.frameMain .getCurrentImage() .getLayer1D() ;
    int  selectOption=0;

    if(jComboBoxFitMethod.getSelectedIndex() >=1 &&
       jComboBoxFitMethod .getSelectedIndex() <=7){
      // 准备xxx点,为2D电泳与其他分析,计算Rf值
      if(this.currentNo ==4 || this.currentNo ==5 || this.currentNo ==7){
        Rectangle rect=this.frameMain.getCurrentImage().getROI();
        if(this.frameMain.getCurrentImage().MW_2D_isDirection==true && this.currentNo!=7){
          if(this.frameMain.getCurrentImage().MW_2D_YDirection==false){
            begin=rect.getLocation().x;
            L=rect.getWidth() ;
            selectOption=0;
          }
          else{
            begin=rect.getLocation().y;
            L=rect.getHeight();
            selectOption=1;
          }
        }
        else{
          JOptionPane myOptionPane=new JOptionPane();
          Object[] options = { "Vertical", "Horizontal"};
          selectOption=myOptionPane.showOptionDialog(
              null, "Select direction for calcuating Rf",this.getTitle() ,
              JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE ,
              null, options, options[1]);
          if(selectOption==0){
            begin=rect.getLocation().x;
            L=rect.getWidth();
          }
          else{
            begin=rect.getLocation().y;
            L=rect.getHeight();
          }
          if(this.currentNo==4){
            this.frameMain.getCurrentImage().MW_2D_isDirection=true;
            if(selectOption==0) this.frameMain.getCurrentImage().MW_2D_YDirection=false;
            else this.frameMain.getCurrentImage().MW_2D_YDirection=true;
          }
          else if(this.currentNo==5){
            this.frameMain.getCurrentImage().MW_2D_isDirection=true;
            if(selectOption==0) this.frameMain.getCurrentImage().MW_2D_YDirection=true;
            else this.frameMain.getCurrentImage().MW_2D_YDirection=false;
          }
        }
        int size=this.stdData .size() ;
        this.xxx=new double[size];
        this.stdObjectNo =new int[size];
        InfoCell cell;
        for(int ii=1;ii<=size;ii++){
          cell=layerCell.getInfoCell(((Integer)stdData.elementAt(ii-1)).intValue());
          stdObjectNo[ii-1]=cell.cellNo;
          if(selectOption==0){ Rf=(cell.m_grayx -begin)/L;  }
          else{ Rf=(cell.m_grayy -begin)/L; }
          xxx[ii-1]=Rf;
        }
      }
      else if(this.currentNo ==6 || this.currentNo ==8||
              this.currentNo ==9 || this.currentNo ==10){
        int size=this.stdData .size() ;
        this.xxx=new double[size];
        this.stdObjectNo =new int[size];
        InfoCell cell;
        for(int ii=1;ii<=size;ii++){
          cell=layerCell.getInfoCell(((Integer)stdData.elementAt(ii-1)).intValue());
          stdObjectNo[ii-1]=cell.cellNo;
          xxx[ii-1]=cell.m_graySum;
        }
      }

      // 对数法的调整
      /*
      if(jComboBoxFitMethod .getSelectedIndex() ==6){
      double logE=Math.log(Math.E);
      for(int ii=1;ii<=xxx.length ;ii++)
      xxx[ii-1]=(Math.log(xxx[ii-1]))/logE;
      }
      else if(jComboBoxFitMethod .getSelectedIndex() ==7){
      for(int ii=1;ii<=xxx.length ;ii++)
      xxx[ii-1]=Math.log(xxx[ii-1]);
      }
      */

      String A="1";
      if(this.currentNo ==2){
        //A=JOptionPane.showInputDialog(
        //null,winTitle[currentNo]+"输入系数,如100");
      }

      // 准备点拟合
      int norder;
      if(jComboBoxFitMethod .getSelectedIndex()>=1 &&
         jComboBoxFitMethod .getSelectedIndex()<=5){
        norder=this.jComboBoxFitMethod .getSelectedIndex() ;
      }
      else{
        norder=1;
      }

      //confi=new double[norder+1];      非常重要！！！
      int fitlen;
      if(xxx.length >data.length ) fitlen=data.length ;
      else fitlen=xxx.length ;
      double x[]=new double[fitlen];
      double y[]=new double[fitlen];
      //System.out.println("norder="+norder);
      for(int ii=1;ii<=fitlen;ii++){
        x[ii-1]=xxx[ii-1];
        y[ii-1]=data[ii-1];
        //y[ii-1]*=(Double.parseDouble(A));
        //System.out.println(ii+"="+x[ii-1]+" "+y[ii-1]);
      }
      if(norder+1>x.length){
        // modified by wxs 20030828
        if(this.frameMain.getExeMethod()!=2){
          JOptionPane.showMessageDialog(null,"fitting order is too high","Error"
                                        ,JOptionPane.ERROR_MESSAGE );
        }
        this.canReport=false;
        return;
      }

      //设置曲线的坐标值（物理）性质
      double ext=0.05;
      double phyCoordXMin=x[0];
      double phyCoordXMax=x[0];
      double phyCoordYMin=y[0];
      double phyCoordYMax=y[0];
      for(int ii=1;ii<=fitlen;ii++){
        if(x[ii-1]<phyCoordXMin) phyCoordXMin=x[ii-1];
        if(x[ii-1]>phyCoordXMax) phyCoordXMax=x[ii-1];
        if(y[ii-1]<phyCoordYMin) phyCoordYMin=y[ii-1];
        if(y[ii-1]>phyCoordYMax) phyCoordYMax=y[ii-1];
      }
      curve.curveBeginX =phyCoordXMin;
      curve.curveEndX =phyCoordXMax;
      phyCoordXMin*=(1+ext);
      phyCoordXMax*=(1+ext);
      if(phyCoordXMin>0 && phyCoordXMin/(phyCoordXMax-phyCoordXMin)<ext*2.0){
        phyCoordXMin=0;
      }
      else{
        phyCoordXMin =phyCoordXMin*(1.0-ext);
      }
      phyCoordYMin*=(1+ext);
      phyCoordYMax*=(1+ext);
      if(phyCoordYMin>0 && phyCoordYMin/(phyCoordYMax-phyCoordYMin)<ext*2.0){
        phyCoordYMin=0;
      }
      else{
        phyCoordYMin =phyCoordYMin*(1.0-ext);
      }
      curve.phyCoordinateXBegin =phyCoordXMin;
      curve.phyCoordinateXEnd =phyCoordXMax;
      curve.phyCoordinateYBegin =phyCoordYMin;
      curve.phyCoordinateYEnd =phyCoordYMax;

      //拟合
      if(currentNo==2){
        confi=new double[2];
        double tx=0,ty=0;
        for(int ii=1;ii<=x.length ;ii++){
          tx+=x[ii-1];
          ty+=y[ii-1];
        }
        confi[0]=(Double.parseDouble(A))*ty/tx;
        confi[1]=0;
      }
      else{
        // 对数的调整
        double x2[]=new double[x.length] ;
        if(jComboBoxFitMethod .getSelectedIndex() ==6){
          double log10=Math.log(10);
          for(int ii=1;ii<=x.length ;ii++)   x2[ii-1]=(Math.log(x[ii-1]))/log10;
        }
        else if(jComboBoxFitMethod .getSelectedIndex() ==7){
          for(int ii=1;ii<=x.length ;ii++)   x2[ii-1]=Math.log(x[ii-1]);
        }
        else{
          for(int ii=1;ii<=x.length ;ii++)   x2[ii-1]=x[ii-1];
        }
        confi=(double[])MathEx.polyfit(x2,y,norder);
      }

      //计算各点
      if(this.currentNo ==4 || this.currentNo ==5 || this.currentNo ==7){
        //设置marker点
        curve.setStdPhy(x,y);
        //设置非marker点
        curve.setPolyfitNum(1);
        int size=layerCell.getCurrent() .size() -this.stdData .size() ;
        double []xxxx=new double[size];
        this.nonstdObjectNo =new int[size];
        InfoCell cell;
        int index=0;
        for(int ii=1;ii<=layerCell.getCurrent() .size() ;ii++){
          cell=(InfoCell)layerCell.getCurrent() .elementAt(ii-1);
          if(this.isStdPoint(cell.cellNo)  ==true) continue;
          this.nonstdObjectNo [index]=cell.cellNo ;
          if(selectOption==0){ Rf=(cell.m_grayx -begin)/L; }
          else{ Rf=(cell.m_grayy -begin)/L; }
          xxxx[index]=Rf;
          index++;
        }
        double []yyyy=null;
        if(jComboBoxFitMethod .getSelectedIndex() ==6){
          yyyy=MathEx.logValue(this.confi ,xxxx);
        }
        else if(jComboBoxFitMethod .getSelectedIndex() ==7){
          yyyy=MathEx.lnValue(this.confi ,xxxx);
        }
        else{
          yyyy=MathEx.PolyVal(this.confi ,xxxx);
        }
        curve.setPolyfitPoint(1,xxxx,yyyy,1);
      }
      else if(this.currentNo ==6 || this.currentNo ==8||
              this.currentNo ==9 || this.currentNo ==10){
        //设置marker点
        curve.setStdPhy(x,y);
        //设置非marker点
        curve.setPolyfitNum(1);
        int size=layerCell.getCurrent() .size() -this.stdData .size() ;
        double []xxxx=new double[size];
        this.nonstdObjectNo =new int[size];
        InfoCell cell;
        int index=0;
        for(int ii=1;ii<=layerCell.getCurrent() .size() ;ii++){
          cell=(InfoCell)layerCell.getCurrent() .elementAt(ii-1);
          if(this.isStdPoint(cell.cellNo)  ==true) continue;
          this.nonstdObjectNo [index]=cell.cellNo ;
          xxxx[index]=cell.m_graySum;
          index++;
        }
        double []yyyy=null;
        if(jComboBoxFitMethod .getSelectedIndex() ==6){
          yyyy=MathEx.logValue(this.confi ,xxxx);
        }
        else if(jComboBoxFitMethod .getSelectedIndex() ==7){
          yyyy=MathEx.lnValue(this.confi ,xxxx);
        }
        else{
          yyyy=MathEx.PolyVal(this.confi ,xxxx);
        }

        curve.setPolyfitPoint(1,xxxx,yyyy,1);
      }
      else if(this.currentNo >=1 && this.currentNo <=3){
        //为1D分析-计算
        int selLane=((Integer)this.stdData .elementAt(0)).intValue() ;
        int lanes=layer1D.getlanenum()/2;
        curve.setPolyfitNum(lanes-1);
        //设置marker点
        curve.setStdPhy(x,y);
        //设置非marker点
        int index=0;
        for(int jj=1;jj<=lanes;jj++){
          if(jj==selLane) continue;
          if(this.currentNo ==1){
            //第一种分析方法
            double []locLine;
            Rectangle rect;
            rect=this.frameMain .getCurrentImage() .getROI();
            locLine=layer1D.getLinesLocation(jj);
            begin=rect.getLocation() .y;
            L=rect.getHeight() ;
            double []xxxx=new double[locLine.length];
            for(int ii=1;ii<=locLine.length ;ii++){
              Rf=(locLine[ii-1]-begin)/L;
              xxxx[ii-1]=Rf;
            }
            double []yyyy=null;
            if(jComboBoxFitMethod .getSelectedIndex() ==6){
              yyyy=MathEx.logValue(this.confi ,xxxx);
            }
            else if(jComboBoxFitMethod .getSelectedIndex() ==7){
              yyyy=MathEx.lnValue(this.confi ,xxxx);
            }
            else{
              yyyy=MathEx.PolyVal(this.confi ,xxxx);
            }
            index++;
            curve.setPolyfitPoint(index,xxxx,yyyy,jj);
          }
          else if(this.currentNo ==2 || currentNo==3){
            //第二种分析方法
            int lines=layer1D.getLines(jj);
            Point pt;
            int points=0;
            InfoCell cell;
            for(int ii=1;ii<=lines;ii++){
              pt=layer1D.getLineCenterPoint(jj,ii);
              if(pt==null) continue;
              cell=layerCell.getInfoCell(pt);
              if(cell==null) continue;
              points++;
            }
            double []xxxx=new double[points];
            double []yyyy=new double[points];
            points=0;
            for(int ii=1;ii<=lines;ii++){
              pt=layer1D.getLineCenterPoint(jj,ii);
              if(pt==null) continue;
              cell=layerCell.getInfoCell(pt);
              if(cell==null) continue;
              xxxx[points]=cell.m_graySum ;
              yyyy[points]=confi[0]*xxxx[points];
              points++;
            }
            index++;
            curve.setPolyfitPoint(index,xxxx,yyyy,jj);

            //???  ==6 ,,==7
          }
        }
      }

      //调节精度
      int bitx,bity;
      bitx=this.countPrecision(curve.phyCoordinateXBegin ,
                               curve.phyCoordinateXEnd );
      bity=this.countPrecision(curve.phyCoordinateYBegin ,
                               curve.phyCoordinateYEnd );
      curve.phyCoordinateXBegin =toGood(curve.phyCoordinateXBegin,bitx);
      curve.phyCoordinateXEnd =toGood(curve.phyCoordinateXEnd,bitx);
      curve.phyCoordinateYBegin =toGood(curve.phyCoordinateYBegin,bity);
      curve.phyCoordinateYEnd =toGood(curve.phyCoordinateYEnd,bity);

      //设置曲线性质
      curve.curveFunction =this.jComboBoxFitMethod.getSelectedIndex();
      curve.setCurveCoefficient(this.confi );

      //显示拟合曲线的窗口
      DialogPolyfit polyfit;
      int w,h;
      double detx,dety;
      double minY,maxY;
      frameMain.showDialogPolyfit(this.getTitle() +"fitting curve") ;
      polyfit=frameMain.getDialogPolyfit() ;
      polyfit.setCurve(curve);
      w=polyfit.getDrawXLen() ;
      h=polyfit.getDrawYLen() ;

      //2--间隔字的个数，12--默认字长度
      detx=(curve.phyCoordinateXEnd -curve.phyCoordinateXBegin )*6/((double)w/12);
      dety=(curve.phyCoordinateYEnd -curve.phyCoordinateYBegin )*6/((double)h/12);

      //调节精度
      detx=this.toGood(detx,bitx+2);
      dety=this.toGood(dety,bity+2);
      polyfit.setAxisBeginEndNum(curve.phyCoordinateXBegin ,curve.phyCoordinateXEnd ,
                                 detx,true);
      polyfit.setAxisBeginEndNum(curve.phyCoordinateYBegin ,curve.phyCoordinateYEnd ,
                                 dety,false);
      polyfit.show();
      polyfit.beginDrawCurve() ;

      // added by wxs 20030828
      this.canReport=true;
    }
  }


  private double[]getMinMax(double []data,double []minmax){
    double []minmax2=minmax;
    for(int ii=1;ii<=data.length ;ii++){
      if(data[ii-1]<minmax2[0]) minmax2[0]=data[ii-1];
      if(data[ii-1]>minmax2[1]) minmax2[1]=data[ii-1];
    }
    return minmax2;
  }

  private int countPrecision(double min,double max){
    int bit;
    bit=(int)((Math.log(max-min))/Math.log(10));
    if(bit<0) bit*=-1;
    if(max-min>1) bit+=2;
    else bit+=4;
    return bit;
  }

  private double toGood(double data,int bits){
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

  void jButton1_actionPerformed(ActionEvent e) {
    if(curve==null) return;

    // added by wxs 20030828
    if(this.canReport==false) return;

    int stdPoints=curve.getStdPhy().getNumber() ;
    int nonSerials=curve.getPolyfitNum() ;
    int nonPoints=0;
    for(int ii=1;ii<=nonSerials;ii++){
      nonPoints+=curve.getPolyfitPoint(ii).getNumber() ;
    }

    String[] colNames=new String[5];
    colNames[0]="No";
    colNames[4]="Unit";
    if(this.currentNo ==1) {
      colNames[1]="Lane No";
      colNames[2]="Band No";
      colNames[3]="Rf";
    }
    else if(this.currentNo ==2 || this.currentNo ==3){
      colNames[1]="Lane No";
      colNames[2]="Band No";
      colNames[3]="Integral Value for Band";
    }
    else if(this.currentNo ==4 || this.currentNo ==5 || this.currentNo ==7){
      colNames[1]="Property";
      colNames[2]="Object No";
      colNames[3]="Rf";
      if(this.currentNo ==4 || this.currentNo ==5){
        if(this.frameMain.getCurrentImage().MW_2D_YDirection==true){
          if(this.currentNo==4) colNames[4]="MW - Y direction";
          else colNames[4]="pI - X direction";
        }
        else{
          if(this.currentNo==4) colNames[4]="MW - X direction";
          else colNames[4]="pI Y direction";
        }
      }
    }
    else if(this.currentNo ==6 || this.currentNo ==8 ||
            this.currentNo ==9 || this.currentNo ==10){
      colNames[1]="Property";
      colNames[2]="Object No";
      colNames[3]="Integral Value for Object";
    }

    String[][] dataTable=new String[nonPoints+stdPoints][colNames.length];

    // 显示精度
    int p=4;
    String text[]=new String[4];
    text[0]=this.winTitle[this.currentNo -1]+" result " ;
    Date date=new Date(System.currentTimeMillis() );
    text[1]="Date        : "+date.toString();
    text[2]="Fit Mode    : "+this.jComboBoxFitMethod.getSelectedItem();
    text[3]="Fit Formulat: Y=";
    int sel=this.jComboBoxFitMethod.getSelectedIndex() ;
    if(sel <=5 && sel >=1){
      text[3]+=String.valueOf(toGood(confi[0],p))+"X^"+String.valueOf(sel);
      for(int ii=sel-1;ii>=0;ii--){
        if(confi[sel-ii]>0){
          text[3]+="+"+String.valueOf(toGood(confi[sel-ii],p))+"X^"+String.valueOf(ii);
        }
        else if(confi[sel-ii]<0){
          text[3]+=String.valueOf(toGood(confi[sel-ii],p))+"X^"+String.valueOf(ii);
        }
      }
    }
    else if(sel==6){
      text[3]+=String.valueOf(toGood(confi[0],p))+"logX"+"+";
      text[3]+=String.valueOf(toGood(confi[1],p));
    }
    else if(sel==7){
      text[3]+=String.valueOf(toGood(confi[0],p))+"lnX"+"+";
      text[3]+=String.valueOf(toGood(confi[1],p));
    }

    SerialPoint sp=curve.getStdPhy() ;
    int no=0;
    for(int ii=1;ii<=stdPoints;ii++){
      dataTable[no][0]=String.valueOf(no);
      if(this.currentNo <=3)  dataTable[no][1]="Lane";
      else  dataTable[no][1]="Object";
      if(this.currentNo <=3) dataTable[no][2]=String.valueOf(ii);
      else  dataTable[no][2]=String.valueOf(this.stdObjectNo [ii-1]);
      dataTable[no][3]=String.valueOf(toGood(sp.getValue(ii),p));
      dataTable[no][4]=String.valueOf(toGood(sp.getValueY(ii),p));
      no++;
    }
    for(int jj=1;jj<=curve.getPolyfitNum() ;jj++){
       sp=curve.getPolyfitPoint(jj) ;
      for(int ii=1;ii<=sp.getNumber() ;ii++){
        dataTable[no][0]=String.valueOf(no);
        if(this.currentNo <=3) dataTable[no][1]=String.valueOf(sp.no);
        else  dataTable[no][1]="Object for fitting";
        if(this.currentNo <=3) dataTable[no][2]=String.valueOf(ii) ;
        else  dataTable[no][2]=String.valueOf(this.nonstdObjectNo[ii-1] ) ;
        dataTable[no][3]=String.valueOf(toGood(sp.getValue(ii),p));
        dataTable[no][4]=String.valueOf(toGood(sp.getValueY(ii),p));
        no++;
      }
    }

    // 显示之
    DialogReport di=new DialogReport(this.frameMain ,"",false);
    di.setContext(this.winTitle[this.currentNo -1]+" result ",text,colNames,dataTable,false);
    di.show() ;
  }

  private boolean isStdPoint(int index){
    for(int ii=1;ii<=this.stdData .size() ;ii++){
      if(((Integer)this.stdData .elementAt(ii-1)).intValue() ==index) return true;
    }
    return false;
  }

  void jButton8_actionPerformed(ActionEvent e) {
    this.dispose() ;
    if(this.frameMain .getExeMethod() >=1){
      this.frameMain .setWizardWinLocation(this.getLocation() ) ;
      this.frameMain.doStep(true);
    }
  }

  void jButton7_actionPerformed(ActionEvent e) {
    this.dispose() ;
    if(this.frameMain .getExeMethod() >=1){
      this.frameMain .setWizardWinLocation(this.getLocation() ) ;
      this.frameMain.doStep(false);
    }
  }

  // added by wxs 20030823 -07
  private void setInterfaceVFromFile(){
    String pathName=this.frameMain.getSystemDir()+"\\database\\"+
                    this.frameMain.getCurrentParaPre()+".1d";
    try{
      File file=new File(pathName);
      if(file.exists() ==true){
        ObjectInputStream in= new ObjectInputStream(new FileInputStream(file));
        int num=in.readInt();
        for(int ii=0;ii<num;ii++){
          Settings st=new Settings();
          st=(Settings)in.readObject();
          if(this.frameMain.getNowStep()==6){
            if(st.type.substring(0,2).equals("06"))  set.addElement(st);
          }
          else if(this.frameMain.getNowStep()==8){
            if(st.type.substring(0,2).equals("08"))  set.addElement(st);
          }
          else if(this.frameMain.getNowStep()==10){
            if(st.type.substring(0,2).equals("10"))  set.addElement(st);
          }
        }
      in.close();
      }
    }
    catch(IOException e){
      e.printStackTrace();
    }
    catch(ClassNotFoundException e){
      e.printStackTrace();
    }
  }

  // added by wxs 20030823 -08
  private void setInterfaceFromV(){
    int exeMethod=this.frameMain.getExeMethod();
    String [] empty=new String[0];
    boolean bln=true;
    int it=1;
    String str;
    if(this.frameMain.getNowStep()==6){
      bln=getSettings("06Need").paraBoolean;
    }
    else if(this.frameMain.getNowStep()==8){
      bln=getSettings("08Need").paraBoolean;
    }
    else if(this.frameMain.getNowStep()==10){
      bln=getSettings("10Need").paraBoolean;
    }
    if(bln==false){
      if(exeMethod==2) jButton8_actionPerformed(null);
    }
    else{
      if(this.frameMain.getNowStep()==6) {
        if(exeMethod==2)  this.isVisible =getSettings("06Visible").paraBoolean;
        else this.isVisible=true;
      }
      else if(this.frameMain.getNowStep()==8){
        if(exeMethod==2)  this.isVisible =getSettings("08Visible").paraBoolean;
        else this.isVisible=true;
      }
      else if(this.frameMain.getNowStep()==10){
        if(exeMethod==2)  this.isVisible =getSettings("10Visible").paraBoolean;
        else this.isVisible=true;
      }
      // select lane
      if(this.frameMain.getNowStep()==6){
        it=getSettings("06Sellane").paraInt;
      }
      else if(this.frameMain.getNowStep()==8){
        it=getSettings("08Sellane").paraInt;
      }
      else if(this.frameMain.getNowStep()==10){
        it=getSettings("10Sellane").paraInt;
      }
      if(it>0){
        if(it>objects) it=objects;
      }
      else if(it==0) it=1;
      else{
        if(it*-1>objects) it=1;
      }
      it--;
      String stra[]=new String[1];
      stra[0]="No Lane: "+(it+1);
      this.jListAllObj.setSelectedIndex(it);
      this.jListStdObj.setListData(empty);
      this.jListStdObj.setListData(stra);
      jButton2_actionPerformed(null);
      // polyfit
      if(this.frameMain.getNowStep()==6){
        it=getSettings("06Polyfit").paraInt;
        this.jComboBoxFitMethod.setSelectedIndex(it-1);
      }
      // import data
      if(this.frameMain.getNowStep()==6){
        this.instruction=getSettings("06Polyfit_Instruction").paraString;
        this.unit=getSettings("06Polyfit_Unit").paraString;
        str=getSettings("06Polyfit_Instruction").paraString  +
            "("+getSettings("06Polyfit_Unit").paraString +"):";
        int datalength=getSettings("06Polyfit_Datas").paraInt;
        data=new double[datalength];
        for(int ii=1;ii<=datalength ;ii++){
          str+=getSettings("06Polyfit_Data"+ii).paraDouble+" ";
          data[ii-1]=getSettings("06Polyfit_Data"+ii).paraDouble;
        }
        String stra2[]=new String[1];
        stra2[0]=str;
        this.jListStdPhy.setListData(empty);
        this.jListStdPhy.setListData(stra2);
      }
      else if(this.frameMain.getNowStep()==8){
        this.instruction=getSettings("08Polyfit_Instruction").paraString;
        this.unit=getSettings("08Polyfit_Unit").paraString;
        str=getSettings("08Polyfit_Instruction").paraString  +
            "("+getSettings("08Polyfit_Unit").paraString +"):";
        int datalength=getSettings("08Polyfit_Datas").paraInt;
        data=new double[datalength];
        for(int ii=1;ii<=datalength ;ii++){
          str+=getSettings("08Polyfit_Data"+ii).paraDouble+" ";
          data[ii-1]=getSettings("08Polyfit_Data"+ii).paraDouble;
        }
        String stra2[]=new String[1];
        stra2[0]=str;
        this.jListStdPhy.setListData(empty);
        this.jListStdPhy.setListData(stra2);
      }
      else if(this.frameMain.getNowStep()==10){
        this.instruction=getSettings("10Polyfit_Instruction").paraString;
        this.unit=getSettings("10Polyfit_Unit").paraString;
        str=getSettings("10Polyfit_Instruction").paraString  +
            "("+getSettings("10Polyfit_Unit").paraString +"):";
        int datalength=getSettings("10Polyfit_Datas").paraInt;
        data=new double[datalength];
        for(int ii=1;ii<=datalength ;ii++){
          str+=getSettings("10Polyfit_Data"+ii).paraDouble+" ";
          data[ii-1]=getSettings("10Polyfit_Data"+ii).paraDouble;
        }
        String stra2[]=new String[1];
        stra2[0]=str;
        this.jListStdPhy.setListData(empty);
        this.jListStdPhy.setListData(stra2);
      }
    }
  }

  // added by wxs 20030823 -09
  public Settings getSettings(String type){
    Settings st=new Settings();
    for(int ii=1;ii<=set.size();ii++){
      st=(Settings)set.elementAt(ii-1);
      if(st.type.equals(type)) break;
    }
    return st;
  }

  // added by wxs 20030823 -10
  public void doExeMethodEasyKey(){
    if(this.frameMain.getExeMethod()!=2) return;
    if(this.isVisible==true) return;
    String str;
    if(this.frameMain.getNowStep()==6){
      this.frameMain.getCurrentImage().setTitle("Calculate MW-pI......");
      jButton6_actionPerformed(null);
      if(getSettings("06Report").paraBoolean==true)
        jButton1_actionPerformed(null);
      this.frameMain.getCurrentImage().setTitle("MW-pI Finished");
    }
    else if(this.frameMain.getNowStep()==8){
      this.frameMain.getCurrentImage().setTitle("Normalization......");
      jButton6_actionPerformed(null);
      if(getSettings("08Report").paraBoolean==true)
        jButton1_actionPerformed(null);
      this.frameMain.getCurrentImage().setTitle("Normalization Finshed.");
    }
    else if(this.frameMain.getNowStep()==10){
      this.frameMain.getCurrentImage().setTitle("Calibration......");
      jButton6_actionPerformed(null);
      if(getSettings("10Report").paraBoolean==true)
        jButton1_actionPerformed(null);
      this.frameMain.getCurrentImage().setTitle("All Done.");
    }

    // donext
    this.frameMain.setWizardWinLocation(this.getLocation() ) ;
    this.frameMain.doStep(true)  ;
  }

  void jListStdPhy_mouseMoved(MouseEvent e) {
  }
}
