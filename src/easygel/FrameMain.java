package easygel;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import com.borland.dx.sql.dataset.*;
import com.borland.jbcl.layout.*;
import easygel.image.*;
import easygel.layer.*;
import easygel.uiti.*;
import javax.swing.event.*;
import java.beans.*;
import easygel.image.*;
import easygel.setting.*;
import easygel.*;

// 总体原则
// 1、所有图像像素点均从[0,0]点开始。
//    对基本层及其他各层均要认真检查，并记录之。[]

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class FrameMain extends JFrame {
  private JPanel contentPane;
  private JMenuBar jMenuBar1 = new JMenuBar();
  private JMenu jMenuFile = new JMenu();
  private JMenu jMenuEdit = new JMenu();
  private JMenuItem jMenuUndo = new JMenuItem();
  private JToolBar jToolBar = new JToolBar();
  private JButton jButton1open = new JButton();
  private JButton jButton2save = new JButton();
  private JButton jButton3print = new JButton();
  private ImageIcon image1;
  private ImageIcon image2;
  private ImageIcon image3;
  private ImageIcon image4;
  private ImageIcon image5;
  private ImageIcon image6;
  private ImageIcon image7;
  private ImageIcon image8;
  private ImageIcon image9;
  private ImageIcon image10;
  private ImageIcon image11;
  private ImageIcon image12;
  private ImageIcon image13;
  private ImageIcon image14;
  private ImageIcon image15;
  private ImageIcon image16;

  private JLabel statusBar = new JLabel();
  private BorderLayout borderLayout1 = new BorderLayout();
  private JMenuItem jMenuOpenFile = new JMenuItem();
  private JPanel jPanelMain = new JPanel();
  private JMenuItem jMenuSaveImage = new JMenuItem();
  private JMenuItem jMenuPrintSet = new JMenuItem();
  private JMenuItem jMenuPrintPre = new JMenuItem();
  private JMenuItem jMenuPrint = new JMenuItem();
  private JMenuItem jMenuExit = new JMenuItem();
  private JMenuItem jMenuCopy = new JMenuItem();
  private JMenuItem jMenuPaste = new JMenuItem();
  private JMenuItem jMenuClearIn = new JMenuItem();
  private JMenuItem jMenuClearOut = new JMenuItem();
  private JMenuItem jMenuInsertText = new JMenuItem();
  private JMenuItem jMenuInsertLine = new JMenuItem();
  private JMenu jMenuImage = new JMenu();
  private JMenuItem jMenuClone = new JMenuItem();
  private JMenuItem jMenuInformation = new JMenuItem();
  private JMenuItem jMenuMove = new JMenuItem();
  private JMenuItem jMenuAmpROI = new JMenuItem();
  private JMenuItem jMenuCutROI = new JMenuItem();
  private JMenuItem jMenuAmpAll = new JMenuItem();
  private JMenuItem jMenuSmlAll = new JMenuItem();
  private JMenuItem jMenuDisp3D = new JMenuItem();
  private JMenuItem jMenuOpt = new JMenuItem();
  private JMenu jMenuAnalyse = new JMenu();
  private XYLayout xYLayout1 = new XYLayout();
  private Dimension iconDimension;

  //================ 图像分析总体参数设置 =========================
  //	多窗口（图像窗口）
  private Vector images=new Vector();
  //	系统信息
  private  boolean isHelp;               // 程序是否处于Help状态
  public String cmmStatus;              // 程序命令状态
  private DialogImage  currentImage;    // 当前操作图像窗口
  private String currentPath,currentName;
  private int currentWidth,currentHeight,currentFormat;
  private User user;                     // 当前用户基本情况
  private String systemDir;             // 可执行文件所在目录

  //    工具箱
  private DialogControlText controlText;
  private DialogControlLine controlLine;
  private DialogControl1D control1D;
  private DialogControlCell controlCell;
  private DialogOptimum dialogOptimum;
  private DialogColor dialogColor;
  private DialogRecLine dialogRecLine;
  public  DialogDrawHist dialogDrawHist;
  private DialogCount dialogCount;
  private DialogDelBackGround dialogDelBG;
  private DialogInformation dialogInformation;

  //private Dialog3D dialog3D;
  private DialogPolyfit dialogPolyfit;
  private DialogFontChooser dialogFontChooser;
  private DialogCountEcoli dialogCountEcoli;
  private DialogControlRevise dialogControlRevise;
  private DialogCompare dialogCompare;
  public DialogResemble dialogResemble;

  //    打印属性
  private PrinterJob job=PrinterJob.getPrinterJob() ;
  private PageFormat pageFormat=new PageFormat();

  //    选择操作对象"image/text/all"
  private String OperObject;

  //    copy,cut,paste
  private boolean isCopyed;
  private  int [][]copyData;
  private int copyWidth,copyHeight;

  //  执行方式
  private  int exeMethod;       // 0--单独执行方式
                                // 1--Step by step方式
                                // 2--全部默认方式
  private int nowAnalyse;
  private int nowStep;
  //private Window wizardWindow;
  private Point wizardLoaction;  // 各步的位置
  private int wizardWidth=293;  //  窗口的大小
  private int wizardHeith=440;

  //  为菜单服务
  public JPopupMenu mainPop=new JPopupMenu();

  //  默认参数文件
  private String currentParaPre;
  private EasyGelSet easyGelSet;


  //
  Vector window=new Vector();
  Vector windowBound =new Vector();
  //Point lastWinLoc;

  //============================================================

  private JMenu jMenuChgMode = new JMenu();
  private JMenuItem jMenuTo8Bits = new JMenuItem();
  private JMenuItem jMenuToRGB24Bits = new JMenuItem();
  private JMenu jMenuRotation = new JMenu();
  private JMenuItem jMenuRotation180 = new JMenuItem();
  private JMenuItem jMenuRotation90 = new JMenuItem();
  private JMenuItem jMenuUnRotation90 = new JMenuItem();
  private JMenuItem jMenuMirror = new JMenuItem();
  private JMenuItem jMenuVMirror = new JMenuItem();
  private JMenuItem jMenuRndAngle = new JMenuItem();
  private JMenu jMenuWindows = new JMenu();
  private JMenu jMenuReArrage = new JMenu();
  private JMenuItem jMenuArrageLayer = new JMenuItem();
  private JMenu jMenuSet = new JMenu();
  private JMenu jMenu2 = new JMenu();
  private JMenuItem jMenuItem1 = new JMenuItem();
  private JMenu jMenuMyHelp = new JMenu();
  private JMenuItem jMenuContent = new JMenuItem();
  private JMenuItem jMenuOnLine = new JMenuItem();
  private JMenuItem jMenuAbout = new JMenuItem();
  private JMenu jMenuTools = new JMenu();
  private JMenu jMenu5 = new JMenu();
  private JMenuItem jMenuItemSmooth = new JMenuItem();
  private JMenuItem jMenuItemSharpen = new JMenuItem();
  private JMenuItem jMenuItemFindedge = new JMenuItem();
  private JMenuItem jMenuItemAddnoise = new JMenuItem();
  private JMenuItem jMenuItemMedium = new JMenuItem();
  private JButton jButton4import = new JButton();
  private JButton jButton5undo = new JButton();
  private JButton jButton6selobject = new JButton();
  private JMenuItem jMenuItemSelect = new JMenuItem();
  private JMenu jMenu6 = new JMenu();
  private JMenuItem jMenuItemAutoReco = new JMenuItem();
  private JButton jButton7selrect = new JButton();
  private JButton jButton8big = new JButton();
  private JButton jButton9small = new JButton();
  private JButton jButton10move = new JButton();
  private JButton jButton111d = new JButton();
  private JButton jButton122d = new JButton();
  private JButton jButton13array = new JButton();
  private JButton jButton14others = new JButton();
  private JButton jButton15easy = new JButton();
  private JMenuItem jMenuItemHistgram = new JMenuItem();
  private JMenuItem jMenuItemSelROI = new JMenuItem();
  private JMenuItem jMenuItemCancelROI = new JMenuItem();
  private JMenuItem jMenuItemDelSelObjects = new JMenuItem();
  private JMenuItem jMenuItemCut = new JMenuItem();
  private JMenuItem jMenuItem3 = new JMenuItem();
  private JMenuItem jMenuItem10 = new JMenuItem();
  private JMenuItem jMenuItem11 = new JMenuItem();
  private JMenuItem jMenuItem12 = new JMenuItem();
  private JMenuItem jMenuItem1OriImage = new JMenuItem();
  private JButton jButton16online = new JButton();
  private JMenuItem jMenuItem13 = new JMenuItem();
  private JMenu jMenu4 = new JMenu();
  private JMenuItem jMenuItemColor = new JMenuItem();
  private JMenuItem jMenuItem2 = new JMenuItem();
  private JMenuItem jMenuItem14 = new JMenuItem();
  private JMenuItem jMenuItem15 = new JMenuItem();
  private JMenuItem jMenuItem16 = new JMenuItem();
  private JMenuItem jMenuItem17 = new JMenuItem();
  private JMenuItem jMenuItem18 = new JMenuItem();
  private JMenuItem jMenuItem19 = new JMenuItem();
  private JMenuItem jMenuItem20 = new JMenuItem();
  private JMenuItem jMenuItem21 = new JMenuItem();
  private JMenuItem jMenuItem23 = new JMenuItem();
  private JMenuItem jMenuItem25 = new JMenuItem();
  private JMenuItem jMenuItem26 = new JMenuItem();
  private JMenuItem jMenuItem27 = new JMenuItem();
  private JMenu jMenu7 = new JMenu();
  private JMenuItem jMenuItem5 = new JMenuItem();
  private JMenuItem jMenuItem29 = new JMenuItem();
  private JMenuItem jMenuItem30 = new JMenuItem();
  private JMenuItem jMenuItem31 = new JMenuItem();
  private JMenu jMenu8 = new JMenu();
  private JMenuItem jMenuItem32 = new JMenuItem();
  private JMenuItem jMenuItem34 = new JMenuItem();
  private JMenuItem jMenuItem35 = new JMenuItem();
  private JMenuItem jMenuItem36 = new JMenuItem();
  private JMenuItem jMenuItem37 = new JMenuItem();
  private JMenuItem jMenuItem38 = new JMenuItem();
  private JCheckBoxMenuItem jCheckBoxMenuItem1 = new JCheckBoxMenuItem();
  private JMenuItem jMenuItem7 = new JMenuItem();
  private JMenuItem jMenuItem8 = new JMenuItem();
  private JMenuItem jMenuItem9 = new JMenuItem();
  private JMenuItem jMenuItemSaveAll = new JMenuItem();
  private JMenuItem jMenuItem39 = new JMenuItem();
  private JMenuItem jMenuItem40 = new JMenuItem();
  private JMenuItem jMenuItem41 = new JMenuItem();
  private JMenuItem jMenuItem42 = new JMenuItem();

  private void setEnvirement(){
    this.systemDir=System.getProperties().getProperty("user.dir");
    this.user=new User();
    user.userName ="";
    user.rootWorkDir ="";
    user.hira =0;
    String sysDir=System.getProperties().getProperty("user.dir");
    File file=new File(sysDir+"\\database\\EasyGels.ini");
    this.easyGelSet=new EasyGelSet();
    if(file.exists() ==true){
      try{
        ObjectInputStream in= new ObjectInputStream(new FileInputStream(file));
        easyGelSet=(EasyGelSet)in.readObject();
      }
      catch(IOException e){
        e.printStackTrace();
      }
      catch(ClassNotFoundException e){
        e.printStackTrace();
      }
    }
    else{
      easyGelSet.iconSize=25;
      easyGelSet.imageDir=sysDir+"\\image";
      easyGelSet.waitSeconds=1;
      easyGelSet.paraPre="";
    }
  }

  // 加密所用的类
  private Class classLayerLine;
  private JMenu jMenu1 = new JMenu();
  private JMenuItem jMenuItem4 = new JMenuItem();
  private JMenuItem jMenuItem6 = new JMenuItem();
  private JMenuItem jMenuItem22 = new JMenuItem();
  private JMenuItem jMenuItem28 = new JMenuItem();
  private JMenuItem jMenuItem43 = new JMenuItem();
  private JMenuItem jMenuItem44 = new JMenuItem();
  private JMenuItem jMenuItem24 = new JMenuItem();

  public Class getClassLayerLine(){
    return this.classLayerLine;
  }

  //Construct the frame
  public FrameMain(Class clsLayerLine) {
    super("");

    this.classLayerLine=clsLayerLine;

    //JOptionPane.showMessageDialog(this,"enter framemain");
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);

    this.setEnvirement();

    //JOptionPane.showMessageDialog(null,System.getProperties().getProperty("java.com.wxs"));

    easygel.uiti.WxsUiti.setIcon(this);
    try {
      jbInit();
      this.setVisible(true);
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception  {
    //  初始化变量
    this.exeMethod =0;
    isHelp=false;
    cmmStatus="";
    controlText=null;
    currentImage=null;
    dialogDrawHist=null;
    dialogCount=null;
    dialogColor=null;
    dialogCompare=null;
    this.dialogRecLine =null;
    this.control1D =null;
    this.dialogPolyfit =null;
    this.controlCell =null;
    dialogCountEcoli=null;
    dialogControlRevise=null;
    this.dialogInformation =null;
    cmmStatus="basicSelText";
    isCopyed=false;
    dialogResemble=null;
    this.wizardLoaction =new Point(5,50);

    //set icon size
    //int size=25;
    //String systemDir;
    //File initDir;
    //systemDir=System.getProperties().getProperty("user.dir");
    /*
    initDir=new File(systemDir+"\\icon\\IconSize.ini");
    if(initDir.exists() ==true){
       try{
         FileInputStream inputFile=new FileInputStream(systemDir+"\\icon\\IconSize.ini");
         DataInputStream inputData=new DataInputStream(inputFile);
         String str;
         str=inputData.readLine();
         size=Integer.parseInt(str);
         inputData.close() ;
         inputFile.close() ;
       }
       catch(IOException e){
          e.printStackTrace();
       }
     }
    */
    int size=this.easyGelSet.iconSize;
    this.iconDimension =new Dimension(size, size);

    //屏幕最大化
    Dimension dimension;
    dimension=Toolkit.getDefaultToolkit().getScreenSize();
    WxsUiti.centerFrame(this,(int)dimension.getWidth() ,(int)dimension.getHeight() );
    //this.setSize(dimension);

    String imageDir;
    systemDir=System.getProperties().getProperty("user.dir");

    image1=new ImageIcon(systemDir+"\\icon\\open.jpg");
    image2=new ImageIcon(systemDir+"\\icon\\save.jpg");
    image3=new ImageIcon(systemDir+"\\icon\\print.jpg");
    image4=new ImageIcon(systemDir+"\\icon\\import.jpg");
    image5=new ImageIcon(systemDir+"\\icon\\undo.jpg");
    image6=new ImageIcon(systemDir+"\\icon\\selobject.jpg");
    image7=new ImageIcon(systemDir+"\\icon\\selrect.jpg");
    image8=new ImageIcon(systemDir+"\\icon\\big.jpg");
    image9=new ImageIcon(systemDir+"\\icon\\small.jpg");
    image10=new ImageIcon(systemDir+"\\icon\\move.jpg");
    image11=new ImageIcon(systemDir+"\\icon\\1d.jpg");
    image12=new ImageIcon(systemDir+"\\icon\\2d.jpg");
    image13=new ImageIcon(systemDir+"\\icon\\array.jpg");
    image14=new ImageIcon(systemDir+"\\icon\\others.jpg");
    image15=new ImageIcon(systemDir+"\\icon\\easy.jpg");
    image16=new ImageIcon(systemDir+"\\icon\\online.jpg");
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(borderLayout1);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    //this.setSize(new Dimension(566, 300));
    this.setTitle("Welcome to use EasyGel");
    //statusBar.setText("??? Text");
    jMenuFile.setText("File");
    jMenuEdit.setText("Edit");
    jMenuUndo.setToolTipText("Undo");
    jMenuUndo.setText("Undo");
    jMenuUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(90, java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuUndo.addActionListener(new ActionListener()  {
      public void actionPerformed(ActionEvent e) {
        jMenuUndo_actionPerformed(e);
      }
    });
    //jButton4.setIcon(image1);
    jButton4import.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton4import_actionPerformed(e);
      }
    });
    jButton4import.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton4import.setMaximumSize(iconDimension);
    jButton4import.setMinimumSize(iconDimension);
    jButton4import.setPreferredSize(iconDimension);
    jButton4import.setToolTipText("Import Image");
    jButton5undo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton5undo_actionPerformed(e);
      }
    });
    jButton5undo.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton5undo.setMaximumSize(iconDimension);
    jButton5undo.setMinimumSize(iconDimension);
    jButton5undo.setPreferredSize(iconDimension);
    jButton5undo.setToolTipText("Undo");
    jButton6selobject.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton6selobject_actionPerformed(e);
      }
    });
    jButton6selobject.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton6selobject.setMaximumSize(iconDimension);
    jButton6selobject.setMinimumSize(iconDimension);
    jButton6selobject.setPreferredSize(iconDimension);
    jButton6selobject.setToolTipText("Select Object");
    jMenuOpenFile.setToolTipText("Open an image");
    jMenuOpenFile.setText("Open image...");
    jMenuOpenFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(79, java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuOpenFile.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuOpenFile_actionPerformed(e);
      }
    });
    jPanelMain.setBorder(BorderFactory.createLoweredBevelBorder());
    jPanelMain.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        jPanelMain_mousePressed(e);
      }
      public void mouseClicked(MouseEvent e) {
        jPanelMain_mouseClicked(e);
      }
    });
    jPanelMain.setLayout(xYLayout1);
    jPanelMain.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        jPanelMain_mouseMoved(e);
      }
    });
    jMenuSaveImage.setToolTipText("Save Image");
    jMenuSaveImage.setActionCommand("Save Image...");
    jMenuSaveImage.setText("Save Image");
    jMenuSaveImage.setAccelerator(javax.swing.KeyStroke.getKeyStroke(83, java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuSaveImage.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuSaveImage_actionPerformed(e);
      }
    });
    jMenuPrintSet.setToolTipText("Setup for printing image");
    jMenuPrintSet.setText("Setup for printing...");
    jMenuPrintSet.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuPrintSet_actionPerformed(e);
      }
    });
    jMenuPrintPre.setToolTipText("Print Preview");
    jMenuPrintPre.setText("Print Preview...");
    jMenuPrintPre.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuPrintPre_actionPerformed(e);
      }
    });
    jMenuPrint.setToolTipText("Print Image");
    jMenuPrint.setText("Print");
    jMenuPrint.setAccelerator(javax.swing.KeyStroke.getKeyStroke(80, java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuPrint.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuPrint_actionPerformed(e);
      }
    });
    jMenuExit.setText("Exit");
    jMenuExit.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuExit_actionPerformed(e);
      }
    });

    jButton1open.setIcon(image1);
    jButton2save.setIcon(image2);
    jButton3print.setIcon(image3);
    jButton4import.setIcon(image4);
    jButton4import.setMargin(new Insets(0, 0, 0, 0));
    jButton4import.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton4import_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton4import_mouseExited(e);
      }
    });
    jButton5undo.setIcon(image5);
    jButton5undo.setMargin(new Insets(0, 0, 0, 0));
    jButton5undo.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton5undo_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton5undo_mouseExited(e);
      }
    });
    jButton6selobject.setIcon(image6);
    jButton6selobject.setMargin(new Insets(0, 0, 0, 0));
    jButton6selobject.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton6selobject_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton6selobject_mouseExited(e);
      }
    });
    jButton7selrect.setIcon(image7);
    jButton7selrect.setMargin(new Insets(0, 0, 0, 0));
    jButton7selrect.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton7selrect_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton7selrect_mouseExited(e);
      }
    });
    jButton7selrect.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton7selrect_actionPerformed(e);
      }
    });
    jButton8big.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton8big.setMaximumSize(iconDimension);
    jButton8big.setMinimumSize(iconDimension);
    jButton8big.setToolTipText("Zoom in");
    jButton8big.setIcon(image8);
    jButton8big.setMargin(new Insets(0, 0, 0, 0));
    jButton8big.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton8big_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton8big_mouseExited(e);
      }
    });
    jButton8big.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton8big_actionPerformed(e);
      }
    });
    jButton9small.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton9small.setMaximumSize(iconDimension);
    jButton9small.setMinimumSize(iconDimension);
    jButton9small.setToolTipText("Zoom out");
    jButton9small.setIcon(image9);
    jButton9small.setMargin(new Insets(0, 0, 0, 0));
    jButton9small.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton9small_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton9small_mouseExited(e);
      }
    });
    jButton9small.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton9small_actionPerformed(e);
      }
    });
    jButton10move.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton10move.setMaximumSize(iconDimension);
    jButton10move.setMinimumSize(iconDimension);
    jButton10move.setToolTipText("Move");
    jButton10move.setIcon(image10);
    jButton10move.setMargin(new Insets(0, 0, 0, 0));
    jButton10move.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton10move_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton10move_mouseExited(e);
      }
    });
    jButton10move.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton10move_actionPerformed(e);
      }
    });
    jButton111d.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton111d.setMaximumSize(iconDimension);
    jButton111d.setMinimumSize(iconDimension);
    jButton111d.setToolTipText("1D Analysis");
    jButton111d.setIcon(image11);
    jButton111d.setMargin(new Insets(0, 0, 0, 0));
    jButton111d.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton111d_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton111d_mouseExited(e);
      }
    });
    jButton111d.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton111d_actionPerformed(e);
      }
    });
    jButton122d.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton122d.setMaximumSize(iconDimension);
    jButton122d.setMinimumSize(iconDimension);
    jButton122d.setToolTipText("2D Analysis");
    jButton122d.setIcon(image12);
    jButton122d.setMargin(new Insets(0, 0, 0, 0));
    jButton122d.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton122d_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton122d_mouseExited(e);
      }
    });
    jButton122d.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton122d_actionPerformed(e);
      }
    });
    jButton13array.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton13array.setMaximumSize(iconDimension);
    jButton13array.setMinimumSize(iconDimension);
    jButton13array.setToolTipText("Array Analysis");
    jButton13array.setMargin(new Insets(0, 0, 0, 0));
    jButton13array.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton13array_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton13array_mouseExited(e);
      }
    });
    jButton13array.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton13array_actionPerformed(e);
      }
    });
    jButton14others.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton14others.setMaximumSize(iconDimension);
    jButton14others.setMinimumSize(iconDimension);
    jButton14others.setToolTipText("Other Analysis");
    jButton14others.setMargin(new Insets(0, 0, 0, 0));
    jButton14others.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton14others_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton14others_mouseExited(e);
      }
    });
    jButton14others.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton14others_actionPerformed(e);
      }
    });
    jButton15easy.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton15easy.setMaximumSize(iconDimension);
    jButton15easy.setMinimumSize(iconDimension);
    jButton15easy.setPreferredSize(iconDimension);
    jButton15easy.setToolTipText("One-Key for 1D Analysis");
    jButton15easy.setMargin(new Insets(0, 0, 0, 0));
    jButton15easy.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton15easy_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton15easy_mouseExited(e);
      }
    });
    jButton15easy.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton15easy_actionPerformed(e);
      }
    });

    jMenuCopy.setToolTipText("Copy ROI");
    jMenuCopy.setText("Copy");
    jMenuCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(67, java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuCopy.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuCopy_actionPerformed(e);
      }
    });
    jMenuPaste.setToolTipText("Copy image from clipboard");
    jMenuPaste.setText("Paste");
    jMenuPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(86, java.awt.event.KeyEvent.CTRL_MASK, false));
    jMenuPaste.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuPaste_actionPerformed(e);
      }
    });
    jMenuClearIn.setToolTipText("Clear ROI");
    jMenuClearIn.setText("Clear ROI");
    jMenuClearIn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuClearIn_actionPerformed(e);
      }
    });
    jMenuClearOut.setToolTipText("Clear Reverse ROI");
    jMenuClearOut.setText("Clear Reverse ROI");
    jMenuClearOut.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuClearOut_actionPerformed(e);
      }
    });
    jMenuInsertText.setToolTipText("Add MW");
    jMenuInsertText.setText("Add MW");
    jMenuInsertText.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuInsertText_actionPerformed(e);
      }
    });
    jMenuInsertLine.setToolTipText("Add Lines");
    jMenuInsertLine.setText("Add Lines");
    jMenuInsertLine.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuInsertLine_actionPerformed(e);
      }
    });
    jMenuImage.setFocusPainted(true);
    jMenuImage.setText("Image");
    jMenuClone.setText("Clone");
    jMenuClone.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuClone_actionPerformed(e);
      }
    });
    jMenuInformation.setText("Image Information");
    jMenuInformation.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuInformation_actionPerformed(e);
      }
    });
    jMenuMove.setText("Move Image");
    jMenuMove.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuMove_actionPerformed(e);
      }
    });
    jMenuAmpROI.setText("Zoom in ROI");
    jMenuAmpROI.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuAmpROI_actionPerformed(e);
      }
    });
    jMenuCutROI.setText("Clip ROI");
    jMenuCutROI.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuCutROI_actionPerformed(e);
      }
    });
    jMenuAmpAll.setText("Zoom in");
    jMenuAmpAll.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuAmpAll_actionPerformed(e);
      }
    });
    jMenuSmlAll.setText("Zoom out");
    jMenuSmlAll.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuSmlAll_actionPerformed(e);
      }
    });
    jMenuDisp3D.setText("Shown in 3D");
    jMenuDisp3D.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuDisp3D_actionPerformed(e);
      }
    });
    jMenuOpt.setText("Optimization");
    jMenuOpt.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuOpt_actionPerformed(e);
      }
    });
    jMenuAnalyse.setText("Analysis");
    jMenuChgMode.setText("Mode conversion");
    jMenuTo8Bits.setText("Gray Mode");
    jMenuTo8Bits.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuTo8Bits_actionPerformed(e);
      }
    });
    jMenuToRGB24Bits.setText("24 bits RGB");
    jMenuToRGB24Bits.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuToRGB24Bits_actionPerformed(e);
      }
    });
    jMenuRotation.setText("Rotation");
    jMenuRotation180.setText("Rotate 180 degree");
    jMenuRotation180.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuRotation180_actionPerformed(e);
      }
    });
    jMenuRotation90.setText("Rotate 90 degree CW");
    jMenuRotation90.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuRotation90_actionPerformed(e);
      }
    });
    jMenuUnRotation90.setText("Rotate 90 degree CCW");
    jMenuUnRotation90.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuUnRotation90_actionPerformed(e);
      }
    });
    jMenuMirror.setText("Flip Horizontal");
    jMenuMirror.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuMirror_actionPerformed(e);
      }
    });
    jMenuVMirror.setText("Flip Vertical");
    jMenuVMirror.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuVMirror_actionPerformed(e);
      }
    });
    jMenuRndAngle.setText("Rotate...");
    jMenuRndAngle.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuRndAngle_actionPerformed(e);
      }
    });
    jMenuWindows.setText("Window");
    jMenuReArrage.setText("Re-arrange");
    jMenuArrageLayer.setText("Cascade");
    jMenuArrageLayer.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuArrageLayer_actionPerformed(e);
      }
    });
    jMenuSet.setText("Setup");
    jMenuItem1.setText("Contents");
    jMenuMyHelp.setText("Help");
    jMenuContent.setText("Contents");
    jMenuContent.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuContent_actionPerformed(e);
      }
    });
    jMenuOnLine.setText("Inline Help");
    jMenuOnLine.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuOnLine_actionPerformed(e);
      }
    });
    jMenuAbout.setText("About");
    jMenuAbout.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuAbout_actionPerformed(e);
      }
    });
    contentPane.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        contentPane_mouseClicked(e);
      }
    });
    contentPane.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        contentPane_mouseMoved(e);
      }
    });
    jMenuTools.setText("Tools");
    jMenu5.setText("Filter");
    jMenuItemSmooth.setText("Smooth");
    jMenuItemSmooth.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemSmooth_actionPerformed(e);
      }
    });
    jMenuItemSharpen.setText("Sharpen");
    jMenuItemSharpen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemSharpen_actionPerformed(e);
      }
    });
    jMenuItemFindedge.setText("Find Boundary");
    jMenuItemFindedge.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemFindedge_actionPerformed(e);
      }
    });
    jMenuItemAddnoise.setText("Add Noise");
    jMenuItemAddnoise.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemAddnoise_actionPerformed(e);
      }
    });
    jMenuItemMedium.setText("Median");
    jMenuItemMedium.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemMedium_actionPerformed(e);
      }
    });
    jButton1open.setMaximumSize(iconDimension);
    jButton1open.setMinimumSize(iconDimension);
    jButton1open.setPreferredSize(iconDimension);
    jButton1open.setToolTipText("Open Image");
    jButton1open.setMargin(new Insets(0, 0, 0, 0));
    jButton1open.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton1open_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton1open_mouseExited(e);
      }
    });
    jButton1open.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1open_actionPerformed(e);
      }
    });
    jButton2save.setMaximumSize(iconDimension);
    jButton2save.setMinimumSize(iconDimension);
    jButton2save.setPreferredSize(iconDimension);
    jButton2save.setToolTipText("Save Image");
    jButton2save.setMargin(new Insets(0, 0, 0, 0));
    jButton2save.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton2save_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton2save_mouseExited(e);
      }
    });
    jButton2save.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2save_actionPerformed(e);
      }
    });
    jButton3print.setMaximumSize(iconDimension);
    jButton3print.setMinimumSize(iconDimension);
    jButton3print.setPreferredSize(iconDimension);
    jButton3print.setToolTipText("Print Image");
    jButton3print.setMargin(new Insets(0, 0, 0, 0));
    jButton3print.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton3print_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton3print_mouseExited(e);
      }
    });
    jButton3print.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3print_actionPerformed(e);
      }
    });
    jMenuItemSelect.setText("Select Object");
    jMenuItemSelect.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemSelect_actionPerformed(e);
      }
    });
    jMenu6.setText("1D Analysis");
    jMenuItemAutoReco.setText("Default Values for One-Click");
    jMenuItemAutoReco.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemAutoReco_actionPerformed(e);
      }
    });
    jButton7selrect.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton7selrect.setMaximumSize(iconDimension);
    jButton7selrect.setMinimumSize(iconDimension);
    jButton7selrect.setPreferredSize(iconDimension);
    jButton7selrect.setToolTipText("Select ROI");
    jMenuItemHistgram.setText("Histogram");
    jMenuItemHistgram.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemHistgram_actionPerformed(e);
      }
    });
    jMenuItemSelROI.setText("Select ROI");
    jMenuItemSelROI.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemSelROI_actionPerformed(e);
      }
    });
    jMenuItemCancelROI.setText("Cancel Select");
    jMenuItemCancelROI.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemCancelROI_actionPerformed(e);
      }
    });
    jMenuItemDelSelObjects.setText("Delete Selected Objects");
    jMenuItemDelSelObjects.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemDelSelObjects_actionPerformed(e);
      }
    });
    jMenuItemCut.setText("Cut");
    jMenuItemCut.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemCut_actionPerformed(e);
      }
    });
    jMenuItem3.setText("Close All");
    jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem3_actionPerformed(e);
      }
    });
    jMenuItem10.setText("Lane Recognition");
    jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem10_actionPerformed(e);
      }
    });
    jMenuItem11.setText("Remove Background");
    jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem11_actionPerformed(e);
      }
    });
    jMenuItem12.setText("MW-PI");
    jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem12_actionPerformed(e);
      }
    });
    jMenuItem1OriImage.setText("Image Recovery");
    jMenuItem1OriImage.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem1OriImage_actionPerformed(e);
      }
    });
    jButton16online.setMaximumSize(iconDimension);
    jButton16online.setMinimumSize(iconDimension);
    jButton16online.setPreferredSize(iconDimension);
    jButton16online.setToolTipText("Help");
    jButton16online.setMargin(new Insets(0, 0, 0, 0));
    jButton16online.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        jButton16online_mouseEntered(e);
      }
      public void mouseExited(MouseEvent e) {
        jButton16online_mouseExited(e);
      }
    });
    jButton16online.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton16online_actionPerformed(e);
      }
    });
    this.jButton10move .setIcon(image10);
    this.jButton111d .setIcon(image11);
    this.jButton122d .setIcon(image12);
    this.jButton13array .setIcon(image13);
    this.jButton14others .setIcon(image14);
    this.jButton15easy .setIcon(image15);
    this.jButton16online .setIcon(image16);
    this.jButton1open.setIcon(image1);
    this.jButton2save .setIcon(image2);
    this.jButton3print .setIcon(image3);
    this.jButton4import .setIcon(image4);
    this.jButton5undo .setIcon(image5);
    this.jButton6selobject .setIcon(image6);
    this.jButton7selrect .setIcon(image7);
    this.jButton8big .setIcon(image8);
    this.jButton9small .setIcon(image9);
    jMenuItem13.setText("Band Recognition");
    jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem13_actionPerformed(e);
      }
    });
    jMenuBar1.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusGained(FocusEvent e) {
        jMenuBar1_focusGained(e);
      }
    });
    jMenu4.setText("2D Analysis");
    jMenuItemColor.setText("Dim Color");
    jMenuItemColor.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemColor_actionPerformed(e);
      }
    });
    jMenuItem14.setText("One Click");
    jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem14_actionPerformed(e);
      }
    });
    jMenuItem2.setText("Wizard");
    jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem2_actionPerformed(e);
      }
    });
    jMenuItem15.setText("Rectify");
    jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem15_actionPerformed(e);
      }
    });
    jMenuItem16.setText("Boundary Detection");
    jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem16_actionPerformed(e);
      }
    });
    jMenuItem17.setText("Similarity");
    jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem17_actionPerformed(e);
      }
    });
    jMenuItem19.setText("Normalization");
    jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem19_actionPerformed(e);
      }
    });
    jMenuItem20.setText("Comparison");
    jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem20_actionPerformed(e);
      }
    });
    jMenuItem18.setText("Calibration");
    jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem18_actionPerformed(e);
      }
    });
    jMenuItem21.setText("Wizard Mode");
    jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem21_actionPerformed(e);
      }
    });
    jMenuItem23.setText("Object Recognition");
    jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem23_actionPerformed(e);
      }
    });
    jMenuItem25.setText("Remove Background");
    jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem25_actionPerformed(e);
      }
    });
    jMenuItem26.setText("Calcuate MW");
    jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem26_actionPerformed(e);
      }
    });
    jMenuItem27.setText("Normalization");
    jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem27_actionPerformed(e);
      }
    });
    jMenu7.setText("Array Analysis");
    jMenuItem5.setText("Wizard Mode");
    jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem5_actionPerformed(e);
      }
    });
    jMenuItem29.setText("Object Recognition");
    jMenuItem29.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem29_actionPerformed(e);
      }
    });
    jMenuItem30.setText("Remove Background");
    jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem30_actionPerformed(e);
      }
    });
    jMenuItem31.setText("Quantitative Analysis");
    jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem31_actionPerformed(e);
      }
    });
    jMenu8.setText("Other Analysis");
    jMenuItem32.setText("Wizard Mode");
    jMenuItem32.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem32_actionPerformed(e);
      }
    });
    jMenuItem34.setText("Object Recognition");
    jMenuItem34.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem34_actionPerformed(e);
      }
    });
    jMenuItem35.setText("Remove Background");
    jMenuItem35.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem35_actionPerformed(e);
      }
    });
    jMenuItem36.setText("MW-pI(Rf)");
    jMenuItem36.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem36_actionPerformed(e);
      }
    });
    jMenuItem37.setText("Quantitative Analysis");
    jMenuItem37.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem37_actionPerformed(e);
      }
    });
    jMenuItem38.setText("Stack Information");
    jMenuItem38.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem38_actionPerformed(e);
      }
    });
    jMenuBar1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        jMenuBar1_mouseMoved(e);
      }
    });
    jCheckBoxMenuItem1.setText("Adjust Window Size Automatically");
    jCheckBoxMenuItem1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jCheckBoxMenuItem1_actionPerformed(e);
      }
    });
    jMenuItem7.setText("Control Hardware");
    jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem7_actionPerformed(e);
      }
    });
    jMenuItem8.setText("Lable Tools");
    jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem8_actionPerformed(e);
      }
    });
    jMenuItem9.setText("Line Tools");
    jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem9_actionPerformed(e);
      }
    });
    jMenuItemSaveAll.setText("Save Image");
    jMenuItemSaveAll.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItemSaveAll_actionPerformed(e);
      }
    });
    jMenuBar1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jMenuBar1_mouseClicked(e);
      }
    });
    jMenuItem39.setText("Setup 3D Color");
    jMenuItem39.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem39_actionPerformed(e);
      }
    });
    jMenuItem40.setText("Invert");
    jMenuItem40.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem40_actionPerformed(e);
      }
    });
    jMenuItem41.setText("Setup for one-click of 1D Analysis");
    jMenuItem41.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem41_actionPerformed(e);
      }
    });
    jMenuItem42.setText("Display Analysis Information");
    jMenuItem42.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem42_actionPerformed(e);
      }
    });
    jMenu1.setText("Clone Counts");
    jMenuItem4.setText("Object Recognition");
    jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem4_actionPerformed(e);
      }
    });
    jMenuItem6.setText("Clone Counts");
    jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem6_actionPerformed(e);
      }
    });
    statusBar.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        statusBar_mouseClicked(e);
      }
    });
    jToolBar.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jToolBar_mouseClicked(e);
      }
    });
    jMenuItem22.setText("Minimal Arrangement");
    jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem22_actionPerformed(e);
      }
    });
    jMenuItem28.setText("Calcuate pI");
    jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem28_actionPerformed(e);
      }
    });
    jMenuItem43.setText("Rectify");
    jMenuItem43.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem43_actionPerformed(e);
      }
    });
    jMenuItem44.setText("Wizard Mode");
    jMenuItem44.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem44_actionPerformed(e);
      }
    });
    jMenuItem24.setText("Minimal Arrangement");
    jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem24_actionPerformed(e);
      }
    });
    jToolBar.add(jButton1open,null);
    jToolBar.add(jButton2save,null);
    jToolBar.add(jButton3print,null);
    jToolBar.add(jButton4import);
    jToolBar.add(jButton5undo);
    jToolBar.add(jButton6selobject);
    jToolBar.add(jButton7selrect, null);
    jToolBar.add(jButton8big, null);
    jToolBar.add(jButton9small, null);
    jToolBar.add(jButton10move, null);
    jToolBar.add(jButton111d, null);
    contentPane.add(statusBar, BorderLayout.SOUTH);
    jMenuFile.add(jMenuOpenFile);
    jMenuFile.add(jMenuSaveImage);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuItemSaveAll);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuPrintSet);
    jMenuFile.add(jMenuPrintPre);
    jMenuFile.add(jMenuPrint);
    jMenuFile.addSeparator();
    jMenuFile.add(jMenuExit);
    jMenuEdit.add(jMenuUndo);
    jMenuEdit.addSeparator();
    jMenuEdit.add(jMenuItemSelect);
    jMenuEdit.add(jMenuItemDelSelObjects);
    jMenuEdit.addSeparator();
    jMenuEdit.add(jMenuItemSelROI);
    jMenuEdit.add(jMenuItemCancelROI);
    jMenuEdit.addSeparator();
    jMenuEdit.add(jMenuItemCut);
    jMenuEdit.add(jMenuCopy);
    jMenuEdit.add(jMenuPaste);
    jMenuEdit.add(jMenuClearIn);
    jMenuEdit.add(jMenuClearOut);
    jMenuEdit.addSeparator();
    jMenuEdit.add(jMenuInsertText);
    jMenuEdit.add(jMenuInsertLine);
    jMenuEdit.addSeparator();
    jMenuEdit.add(jMenuItem8);
    jMenuEdit.add(jMenuItem9);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuEdit);
    jMenuBar1.add(jMenuImage);
    jMenuBar1.add(jMenuAnalyse);
    jMenuBar1.add(jMenuWindows);
    jMenuBar1.add(jMenuSet);
    jMenuBar1.add(jMenuTools);
    jMenuBar1.add(jMenuMyHelp);
    this.setJMenuBar(jMenuBar1);
    contentPane.add(jPanelMain, BorderLayout.CENTER);
    contentPane.add(jToolBar, BorderLayout.NORTH);
    jMenuImage.add(jMenuClone);
    jMenuImage.add(jMenuItemHistgram);
    jMenuImage.add(jMenuInformation);
    jMenuImage.add(jMenuChgMode);
    jMenuImage.addSeparator();
    jMenuImage.add(jMenuRotation);
    jMenuImage.add(jMenuMove);
    jMenuImage.add(jMenuAmpROI);
    jMenuImage.add(jMenuCutROI);
    jMenuImage.addSeparator();
    jMenuImage.add(jMenuAmpAll);
    jMenuImage.add(jMenuSmlAll);
    jMenuImage.add(jMenuItem1OriImage);
    jMenuImage.addSeparator();
    jMenuImage.add(jMenuDisp3D);
    jMenuImage.add(jMenuItem39);
    jMenuImage.addSeparator();
    jMenuImage.add(jMenuItem40);
    jMenuImage.add(jMenuItemColor);
    jMenuImage.add(jMenuOpt);
    //jMenuImage.add(jMenu4);
    jMenuChgMode.add(jMenuTo8Bits);
    jMenuChgMode.add(jMenuToRGB24Bits);
    jMenuRotation.add(jMenuRotation180);
    jMenuRotation.add(jMenuRotation90);
    jMenuRotation.add(jMenuUnRotation90);
    jMenuRotation.add(jMenuMirror);
    jMenuRotation.add(jMenuVMirror);
    jMenuRotation.add(jMenuRndAngle);
    jMenuWindows.add(jMenuReArrage);
    jMenuWindows.add(jMenuItem3);
    jMenuWindows.add(jCheckBoxMenuItem1);
    jMenuReArrage.add(jMenuArrageLayer);
    jMenuReArrage.add(jMenuItem22);
    jMenuReArrage.add(jMenuItem24);
    jMenuMyHelp.add(jMenuContent);
    jMenuMyHelp.add(jMenuOnLine);
    jMenuMyHelp.add(jMenuAbout);
    jMenuTools.add(jMenuItem7);
    jMenuSet.add(jMenuItem38);
    jMenuSet.add(jMenuItem41);
    jMenuImage.add(jMenu5);
    jMenu5.add(jMenuItemSmooth);
    jMenu5.add(jMenuItemSharpen);
    jMenu5.add(jMenuItemFindedge);
    jMenu5.add(jMenuItemAddnoise);
    jMenu5.add(jMenuItemMedium);
    jMenuAnalyse.add(jMenu6);
    jMenuAnalyse.add(jMenu4);
    jMenuAnalyse.add(jMenu1);
    jMenuAnalyse.add(jMenu7);
    jMenuAnalyse.add(jMenu8);
    jMenuAnalyse.add(jMenuItem42);
    jMenu6.add(jMenuItem14);
    jMenu6.add(jMenuItem2);
    jMenu6.add(jMenuItemAutoReco);
    jMenu6.addSeparator();
    jMenu6.add(jMenuItem10);
    jMenu6.add(jMenuItem15);
    jMenu6.add(jMenuItem11);
    jMenu6.add(jMenuItem13);
    jMenu6.add(jMenuItem16);
    jMenu6.add(jMenuItem12);
    jMenu6.add(jMenuItem17);
    jMenu6.add(jMenuItem19);
    jMenu6.add(jMenuItem20);
    jMenu6.add(jMenuItem18);
    jToolBar.add(jButton15easy, null);
    jToolBar.add(jButton122d, null);
    jToolBar.add(jButton13array, null);
    jToolBar.add(jButton14others, null);
    jToolBar.add(jButton16online, null);
    jMenu4.add(jMenuItem21);
    jMenu4.addSeparator();
    jMenu4.add(jMenuItem23);
    jMenu4.add(jMenuItem25);
    jMenu4.add(jMenuItem26);
    jMenu4.add(jMenuItem28);
    jMenu4.add(jMenuItem27);
    jMenu4.add(jMenuItem43);
    jMenu7.add(jMenuItem5);
    jMenu7.addSeparator();
    jMenu7.add(jMenuItem29);
    jMenu7.add(jMenuItem30);
    jMenu7.add(jMenuItem31);
    jMenu8.add(jMenuItem32);
    jMenu8.addSeparator();
    jMenu8.add(jMenuItem34);
    jMenu8.add(jMenuItem35);
    jMenu8.add(jMenuItem36);
    jMenu8.add(jMenuItem37);
    jMenu1.add(jMenuItem44);
    jMenu1.addSeparator();
    jMenu1.add(jMenuItem4);
    jMenu1.add(jMenuItem6);

    //   界面特别设计
    this.OperObject ="all";
    jButton3print.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton2save.setBorder(BorderFactory.createRaisedBevelBorder());
    jButton1open.setBorder(BorderFactory.createRaisedBevelBorder());

     //   自动调整窗口
    jCheckBoxMenuItem1.setSelected(true);
    //lastWinLoc=new Point(this.jPanelMain.getLocation().x+3,
    //                     this.jPanelMain.getLocation().y+this.jPanelMain.getHeight()-13);

    //
    currentParaPre=this.easyGelSet.paraPre;
  }

  //File | Exit action performed
  //Help | About action performed
  public void jMenuUndo_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Undo");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.docmmUndo();
  }
  //Overridden so we can exit when window is closed

  public  String getCmmStatus(){
    return cmmStatus;
  }

  public PrinterJob getPrinterJob(){
    return job;
  }

  public PageFormat getPageFormat(){
    return pageFormat;
  }

  public void setCmmStatus(String cmm){
    cmmStatus=cmm;
  }

  public void setCmmStatusDefault(){
    cmmStatus="NoOpt";
    this.currentImage.mySetCursor(false,Cursor.DEFAULT_CURSOR);
  }

  public  DialogImage getCurrentImage(){
    return currentImage;
  }

  void jPanelMain_mouseMoved(MouseEvent e) {
  }

  void jMenuExit_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Exit");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    Runtime.getRuntime().exit(0);
    System.exit(0);
  }

  void jMenuOpenFile_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("OpenFile");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    openFile();
  }

  private void disposeClosedDialogImage(){
    int size=images.size() ;
     DialogImage di;
    for(int ii=1;ii<=size;ii++){
      di=(DialogImage)images.elementAt(ii-1);
      if(di.isClosed() ==true){
        images.removeElement(di);
        di.dispose();
        ii--;
        size--;
      }
    }
  }

  public DialogInformation getDialogInforamtion(){
    return this.dialogInformation;
  }

  private void openFile(){
    this.disposeClosedDialogImage() ;
    String path,name;
    int width,height,imageFormat;
    DialogOpenFile dialogOpenFile= new DialogOpenFile(this,true);
    dialogOpenFile.show();
    path=dialogOpenFile.getFilePath();
    if(path!=""){
      name=dialogOpenFile.getFileName();
      width=dialogOpenFile.getFileWidth();
      height=dialogOpenFile.getFileHeight() ;
      imageFormat=dialogOpenFile.getFileFormat();
      dialogOpenFile.setVisible(false);
      dialogOpenFile.dispose();
      // 添加新图像
      DialogImage currentImage;
      try{
        currentImage=new DialogImage(path,name,width,height,
                                      imageFormat,this);
      }
      catch( OutOfMemoryError e) {
        JOptionPane myOptionPane=new JOptionPane();
        myOptionPane.showMessageDialog(this,"Memory Overflow","Memory Overflow"
                                       ,JOptionPane.WARNING_MESSAGE );
       return;
      }
      images.addElement(currentImage);
      currentImage.show();
      //
      currentName=name;
      currentPath=path;
      currentWidth=width;
      currentHeight=height;
      currentFormat=imageFormat;
      //
      currentImage.judgeBackground() ;
      boolean bg=currentImage.getBackgroundBlack() ;
      DialogBackground dbg=new DialogBackground(this,"Select Background:",true,currentImage,bg);
      dbg.show();
      currentImage.importRsult();
      currentImage.paintImage();
    }
    else{
      dialogOpenFile.dispose();
    }
  }

  public  void setCurrentImage(DialogImage img){
    currentImage =img;
    setTitle("The current image:"+currentImage.getImagePath()+currentImage.getImageName() );
  }

  void jMenuContent_actionPerformed(ActionEvent e) {
      Helper.Helper("Introduction");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
  }

  void jMenuAbout_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("AboutEasyGel");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    DialogAbout di=new DialogAbout(this,"关于",true);
    di.show();
  }

  void contentPane_mouseClicked(MouseEvent e) {
  }

  void contentPane_mouseMoved(MouseEvent e) {
  }

  void jMenuInsertText_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("InsertText");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    cmmStatus="insertText";
    this.currentImage.mySetCursor(true,DialogImage.CURADD);
  }

  void jMenuInsertLine_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("InsertLine");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    cmmStatus="insertLine";
  }

  //工具箱的操作
  void jMenuItemTextTool_actionPerformed(ActionEvent e) {
    this.showControlText(false,false);
  }

  public void showControlText(boolean createMode,boolean mode){
    if(controlText==null)
       controlText=new DialogControlText(this,"Lable Tools",mode,createMode);
    controlText.show();
  }

  public void createControlText(){
    controlText=new DialogControlText(this,"Lable Tools",false,true);
    controlText.show();
  }

  public void createControlLine(){
    if(controlLine==null)
      controlLine=new DialogControlLine(this,"Line Tools",false);
    controlLine.show();
  }

  public  DialogControlText getControlText(){
     return controlText;
  }

  public DialogControlLine getControlLine(){
    return controlLine;
  }

  void jMenuItemSelText_actionPerformed(ActionEvent e) {
    cmmStatus="selectText";
  }

  void jMenuItemLineTool_actionPerformed(ActionEvent e) {
    if(controlLine==null) {
      controlLine=new DialogControlLine(this,"Line Tools",false);
    }
    controlLine.show();
  }

  public void setUser(User user)  {
    this.user =user;
    if(user.hira !=0){
       if(user.rootWorkDir .length() !=0){
            user.rootWorkDir =user.rootWorkDir.trim();
           if(user.rootWorkDir .endsWith("\\")==true ||
                user.rootWorkDir .endsWith("/")==true){
                user.rootWorkDir=user.rootWorkDir.substring(1,user.rootWorkDir.length()-1);
           }
           File file=new File(user.rootWorkDir);
          if(file.isDirectory() ==false){
                file.mkdirs();
           }
        }
    }
  }



  public User getUser(){
    return user;
  }

  /**
   * 设置或检查用户工作目录的正确性
   */
  public void setOrCheckUserDir(){
    user.rootWorkDir=user.rootWorkDir.trim();
    if(user.rootWorkDir.length()==0){
      //只选择目录 ！！！
      JFileChooser chooseDir=new JFileChooser();
      chooseDir.setCurrentDirectory(new File(System.getProperties().getProperty("user.dir")));
      chooseDir.setDialogTitle("[Must Choose] Please select or create a folder:");
      selectDirAndSaveDB(chooseDir);
    }
    else{
      File file=new File(user.rootWorkDir);
      if(!file.isDirectory() ){
        JFileChooser chooseDir=new JFileChooser();
        chooseDir.setDialogTitle("[Must Choose] Please select or create a folder. There is an error in last setting:");
        selectDirAndSaveDB(chooseDir);
      }
    }
  }

  void  selectDirAndSaveDB(JFileChooser chooseDir){
    chooseDir.setFileSelectionMode(chooseDir.DIRECTORIES_ONLY);
    chooseDir.showDialog(null,"Select Folder");
    user.rootWorkDir =chooseDir.getSelectedFile().getPath();
    //开始修改数据库中目录
    Database database = new Database();
    database.setConnection(new com.borland.dx.sql.dataset.ConnectionDescriptor("jdbc:borland:dslocal:F:\\EasyGel\\EasyGel\\EasyGel.jds", "riceyi", "riceyi", false, "com.borland.datastore.jdbc.DataStoreDriver"));
    QueryDataSet qDataSet = new QueryDataSet();
    qDataSet.setQuery(new com.borland.dx.sql.dataset.QueryDescriptor(database, "SELECT \"user\".\"name\",\"user\".\"password\",\"user\".\"hira\",\"user\".\"rootpath\" " +
      "FROM\"user\" WHERE \"user\".\"name\"=\'"+user.userName +"\'", null, true, Load.ALL));
    qDataSet.executeQuery();
    if(qDataSet.getRowCount() >=1){
      qDataSet.goToRow(1);
      qDataSet.editRow();
      qDataSet.setString(4,user.rootWorkDir);
      qDataSet.saveChanges() ;
      qDataSet.refresh();
    }
    qDataSet.close();
  }


  void jMenuSaveImage_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("SaveFile");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(this.currentImage==null) return;
    currentImage.saveImageOnly();
  }

  void jMenuItemSharpen_actionPerformed(ActionEvent e) {
    if(currentImage==null) return;
    currentImage.docmmFilter("sharpen",1);
  }

  public  void setSystemDir(String systemDir){
    this.systemDir =systemDir;
  }
  public String getSystemDir(){
    return this.systemDir ;
  }

  void jMenuItemSmooth_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Filter");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.docmmFilter("smooth",1) ;
  }

  void jMenuItemFindedge_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Filter");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.docmmFilter("findedge",1) ;
  }

  void jMenuItemAddnoise_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Filter");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.docmmFilter("addnoise",1) ;
  }

  void jMenuItemMedium_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Filter");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.docmmFilter("medium",1) ;
  }

  void jMenuItemGamma_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Filter");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.docmmFilter("gamma",1) ;
  }

  void jMenuPrintSet_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("PrintSet");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    pageFormat=job.pageDialog(pageFormat);
  }

  void jMenuPrintPre_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("PrintPre");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    if(currentImage.getImage2() ==null) return;
    DialogPrintPre dpp=new DialogPrintPre (this,"打印预览",true,
                            pageFormat,job,currentImage.getImage2());
    dpp.show();
  }

  void jMenuPrint_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Print");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    if(currentImage.getImage2() ==null) return;
    DialogPrintPre dpp=new DialogPrintPre (this,"Pring Preview",true,
                          pageFormat,job,currentImage.getImage2());
    dpp.show();
 }


  void jMenuRotation180_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Rotation");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.docmmFilter("rotation180",1);
  }

  void jMenuRotation90_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Rotation");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.docmmFilter("rotation90",1);
  }

  void jMenuUnRotation90_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Rotation");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.docmmFilter("rotation-90",1);
  }

  void jMenuMirror_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Rotation");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.docmmFilter("mirror",1);
  }

  void jMenuVMirror_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Rotation");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.docmmFilter("vmirror",1);
  }

  void jMenuItemSelect_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("SelectObject");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    cmmStatus="basicSelText";
    this.currentImage.mySetCursor(true,DialogImage.CURSELECT);
  }

  void jMenuItemAutoReco_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana1DSetting");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    DialogSettings diSet=new DialogSettings(this,"Setup for defalut values:",true,
        this.systemDir+"\\database\\setting.ini",currentParaPre,this.systemDir);
    diSet.show();
    /*
    DialogSet1D set1D=new DialogSet1D(this,"易健参数设置：",true);
    set1D.show();
    */
  }

  void jMenuItemHistgram_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("ImageHist");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    dialogDrawHist=new DialogDrawHist(this,"",false);
    dialogDrawHist.show() ;
  }

  void jMenuClone_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("CloneImage");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    DialogImage currentImage=new DialogImage(currentPath,currentName,
        currentWidth,currentHeight,currentFormat,this);
    images.addElement(currentImage);
    currentImage.show();
  }

  void jMenuItemSelROI_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("SelectROI");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    cmmStatus="basicSelROI";
    //currentImage.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    currentImage.mySetCursor(true,DialogImage.CURRECT);
    currentImage.setSelROIPoints(0);
  }

  void jMenuItemCancelROI_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("DelSelectROI");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.clearROI();
  }

  void jMenuItemDelSelObjects_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("DelSelectObject");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.delSelectedObjects();
  }

  void jMenuInformation_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("ImageInformation");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    dialogInformation=new DialogInformation(this,"Image Information",false);
    dialogInformation.show();
  }

  void jMenuTo8Bits_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Convert8Bits");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    this.currentImage .convertToByte(1) ;
  }

  void jMenuRndAngle_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Rotation");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    this.currentImage.docmmFilter("rotationRam",1);
  }

  void jMenuOpt_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Optium");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    dialogOptimum=new DialogOptimum(this,"Optimize Image",false);
    dialogOptimum.show();
  }

  public void jMenuArrageLayer_actionPerformed(ActionEvent e){
    if(isHelp==true){
      Helper.Helper("WinLayerArrange");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    // DO NOT DELETE !!!
    /*
    int dist=60;
    int size;
    size=images.size();
    for(int ii=1;ii<=size;ii++){
      currentImage=(DialogImage)images.elementAt(ii-1);
      currentImage.setLocation(dist,dist);
      currentImage.show();
      dist+=35;
    }
    */
    Window win[]=this.getOwnedWindows();
    if(win==null) return;
    int dist=0;
    int x0=8,y0=84;
    for(int ii=1;ii<=win.length;ii++){
      if(win[ii-1].isVisible()==true){
        win[ii-1].setLocation(x0+dist,y0+dist);
        dist+=28;
      }
    }
  }

  public void jMenuItem3_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("WinCloseAll");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    int size;
    size=images.size();
    for(int ii=1;ii<=size;ii++){
      currentImage=(DialogImage)images.elementAt(ii-1);
      currentImage.dispose() ;
      images.removeElement(currentImage);
      ii--;
      size--;
    }
    currentImage=null;
    // close all the windows
    Window [] win=this.getOwnedWindows() ;
    for(int ii=1;ii<=win.length ;ii++){
      System.out.println(ii+win[ii-1].getClass().getName());
      win[ii-1].dispose() ;
    }
    System.gc() ;
    //
    this.setTitle("");
  }

  void jMenuOnLine_actionPerformed(ActionEvent e) {
    Cursor cursor;
    cursor=Helper.createHelpCursor();
    this.setCursor(cursor);
    isHelp=true;
  }

  void jMenuExportRes_actionPerformed(ActionEvent e) {
    if(currentImage==null) return;
    currentImage.exportResult();
  }

  void jMenuImportRes_actionPerformed(ActionEvent e) {
    if(currentImage==null) return;
    currentImage.importRsult();
    currentImage.paintImage();
  }

  void jMenuMove_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("MoveImage");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    cmmStatus="basicMoveImage";
    this.currentImage.mySetCursor(true,DialogImage.CURMOVE);
  }

  void jMenuAmpAll_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("AmpAll");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    this.currentImage.mySetCursor(true,DialogImage.CURBIG);
    currentImage.imageBigger();
  }

  void jMenuSmlAll_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("SmlAll");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    this.currentImage.mySetCursor(true,DialogImage.CURSMALL);
    currentImage.imageSmaller();
  }

  void jMenuItem1OriImage_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("OriImage");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.imageOrigin();
  }

  void jButton1open_actionPerformed(ActionEvent e) {

    jMenuOpenFile_actionPerformed(e);
  }

  void jButton2save_actionPerformed(ActionEvent e) {
    this.jMenuSaveImage_actionPerformed(e);
  }

  void jButton3print_actionPerformed(ActionEvent e) {
    this.jMenuPrint_actionPerformed(e);

  }

  void jButton4import_actionPerformed(ActionEvent e) {
    //this.postEvent(Event.PRINT_SCREEN );
  }

  void jButton5undo_actionPerformed(ActionEvent e) {
    this.jMenuUndo_actionPerformed(e);
  }

  void jButton6selobject_actionPerformed(ActionEvent e) {
    this.jMenuItemSelect_actionPerformed(e) ;
  }

  void jButton7selrect_actionPerformed(ActionEvent e) {
    this.jMenuItemSelROI_actionPerformed(e) ;
  }

  void jButton8big_actionPerformed(ActionEvent e) {
    jMenuAmpAll_actionPerformed(e);
  }

  void jButton9small_actionPerformed(ActionEvent e) {
    jMenuSmlAll_actionPerformed(e);
  }

  void jButton10move_actionPerformed(ActionEvent e) {
    this.jMenuMove_actionPerformed(e);
  }

  void jButton111d_actionPerformed(ActionEvent e) {
    jMenuItem2_actionPerformed(e);
  }

  void jButton122d_actionPerformed(ActionEvent e) {
    jMenuItem21_actionPerformed(e);
  }

  void jButton13array_actionPerformed(ActionEvent e) {
    jMenuItem5_actionPerformed(e);
  }

  void jButton14others_actionPerformed(ActionEvent e) {
    jMenuItem32_actionPerformed(e);
  }

  void jButton15easy_actionPerformed(ActionEvent e) {
    jMenuItem14_actionPerformed(null);
  }

  void jButton16online_actionPerformed(ActionEvent e) {
    jMenuOnLine_actionPerformed(e);
  }

  private void autoArrangeWindows(){
    if(jCheckBoxMenuItem1.isSelected() ==false) return;
    if(currentImage ==null || currentImage .isVisible() ==false)
      return;
    int currentW=this.currentImage .getWidth() ;
    int currentH=this.currentImage .getHeight() ;
    int wizardW=452,wizardH=301;
    this.wizardLoaction .x=this.jPanelMain .location() .x+10;
    this.wizardLoaction .y=this.jPanelMain .getHeight() /2-
                             wizardH/2+this.jPanelMain .location() .y;
    Point currentLocation=new Point(0,0);
    currentLocation.x=this.wizardLoaction .x+wizardW+10;
    currentLocation.y=this.currentImage .getLocation() .y;
    this.currentImage .setLocation(currentLocation);
  }

  //LiuSheng
  void jMenuItem10_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana1DRecoLane");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=1;
    autoArrangeWindows();
    control1D = new DialogControl1D(this,"〖1 OF 10〗1D Analysis -- Automatic Detection",false);
  }

  void jMenuItem13_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana1DRecoLine");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
     nowStep=4;
     dialogRecLine  = new DialogRecLine (this,"〖4 OF 10〗1D Analysis -- Bands Detection",false);
  }

  void jMenuItemCut_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Cut");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    copyData =this.currentImage.cutImage(1) ;
    if(copyData==null) return;
    isCopyed=true;
    this.copyWidth =(int)currentImage.getROI() .getWidth() +1;
    this.copyHeight =(int)currentImage.getROI() .getHeight()+1 ;
  }

  void jMenuBar1_focusGained(FocusEvent e) {
    //this.setTitle("gaind") ;
  }


  void jMenuItem12_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana1DMWpI");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=6;
    dialogCount=new DialogCount(this,"",false);
    dialogCount.setExeMethod(0);
    dialogCount.setCurrentNo(1);
    dialogCount.setInterfaceInit(false);
  }

  void jMenuCopy_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Copy");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    copyData =this.currentImage .copyImage() ;
    if(copyData==null) return;
    isCopyed=true;
    this.copyWidth =(int)currentImage.getROI() .getWidth() +1;
    this.copyHeight =(int)currentImage.getROI() .getHeight()+1 ;
  }

  void jMenuPaste_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Paste");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(isCopyed==false) return;
    cmmStatus="pasteImage";
  }

  public int[][] getCopyData(){
    return copyData;
  }
  public int getCopyWidht(){
    return copyWidth;
  }
  public int getCopyHeight(){
    return copyHeight;
  }

  void jMenuClearIn_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("ClearIn");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.clearROIIn(1) ;
  }

  void jMenuClearOut_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("ClearOut");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.clearROIOut(1);
  }

  void jMenuAmpROI_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("AmpROI");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    currentImage.ampROI();
  }

  void jMenuItemColor_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Color");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    dialogColor=new DialogColor(this,"Dim Color",false);
    dialogColor.show();
  }

  void jMenuItemRevert_actionPerformed(ActionEvent e) {
    if(currentImage==null) return;
    currentImage.imageRevert(1);
  }

  void jMenuItem11_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana1DBackGround");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=3;
    dialogDelBG=new DialogDelBackGround(
               this,"〖3 OF 10〗1D Analysis -- Remove Background",false);
    dialogDelBG.setExeMethod(0);
    dialogDelBG.setCurrentNo(1);
    dialogDelBG.setInterface();
  }

  public  void showHistgram(){
    if(dialogDrawHist==null) dialogDrawHist=new DialogDrawHist(this,"",false);
    dialogDrawHist.show() ;
    dialogDrawHist.toFront() ;
  }

  public DialogDrawHist getHistgram(){
    return this.dialogDrawHist ;
  }

  void jMenuDisp3D_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Disp3D");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(this.currentImage==null) return;
    this.currentImage.disp3D();
    //dialog3D=new Dialog3D(this,"",false);
    //dialog3D.show();
  }

  public  void showDialogPolyfit(String title){
    // if(dialogPolyfit==null)
    dialogPolyfit=new DialogPolyfit(this,title,false);
    dialogPolyfit.show();
  }

  public DialogPolyfit getDialogPolyfit(){
    return  this.dialogPolyfit ;
  }

  public DialogControlCell getControlCell(){
    return this.controlCell ;
  }

  public DialogFontChooser showGetDialogFontChooser(String title,Font font){
    dialogFontChooser=new DialogFontChooser(this,title,true,font);
    return dialogFontChooser;
  }

  void jMenuItem23_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana2DReco");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=1;
    autoArrangeWindows();
    controlCell=new DialogControlCell(this,"〖1 OF 6〗2D Analysis -- Object Detection",false,false,true,false);
    controlCell.show() ;
  }

  void jMenuItem16_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana1DRecoEdge");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=5;
    controlCell=new DialogControlCell(this,
                     "〖5 OF 10〗1D Analysis -- Find Band Boundary",false,true,false,false);
  }

  void jMenuItem19_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana1DOne");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=8;
    dialogCount=new DialogCount(this,"",false);
    dialogCount.setExeMethod(0);
    dialogCount.setCurrentNo(2);
    dialogCount.setInterfaceInit(false);
  }

  void jMenuItem18_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana1DAdjust");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=10;
    dialogCount=new DialogCount(this,"",false);
    dialogCount.setExeMethod(0);
    dialogCount.setCurrentNo(3);
    dialogCount.setInterfaceInit(true);
  }

  public int getNowStep(){
    return nowStep;
  }

  public int getNowAnalyse(){
    return nowAnalyse;
  }


  void jMenuItem25_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana2DBackGround");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=2;
    dialogDelBG=new DialogDelBackGround(this,"〖2 OF 6〗2D Analysis -- Remove Background",false);
    dialogDelBG.setExeMethod(0);
    dialogDelBG.setCurrentNo(2);
    dialogDelBG.setInterface();
    dialogDelBG.show();
  }

  void jMenuItem26_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana2DMW");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(e!=null) this.currentImage.MW_2D_isDirection=false;
    nowStep=3;
    dialogCount=new DialogCount(this,"",false);
    dialogCount.setExeMethod(0);
    dialogCount.setCurrentNo(4);
    dialogCount.setInterfaceInit(false);
    dialogCount.show();
  }

  void jMenuItem15_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana1DRevise");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=2;
    dialogControlRevise=new DialogControlRevise(this,"〖2 OF 10〗1D Analysis -- Rectify",false);
  }

  void jMenuItem20_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana1DOne");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=9;
    dialogCompare=new DialogCompare(this,"〖9 OF 10〗1D Analysis -- Comparison",false);
  }

  void jMenuItem17_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana1DResemble");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=7;
    dialogResemble=new DialogResemble(this,"〖7 OF 10〗1D Analysis -- Similarity",false);
  }

  void jMenuItem27_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana2DOne");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=5;
    dialogCount=new DialogCount(this,"",false);
    dialogCount.setExeMethod(0);
    dialogCount.setCurrentNo(9);
    dialogCount.setInterfaceInit(false);
    dialogCount.show();
  }

  void jMenuItem29_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("AnaArrayReco");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=1;
    autoArrangeWindows();
    controlCell=new DialogControlCell(this,"〖1 OF 3〗Array Analysis -- Object Detection",false,false,true,true);
    controlCell.show() ;
  }

  void jMenuItem34_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("AnaOtherReco");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=1;
    autoArrangeWindows();
    controlCell=new DialogControlCell(this,"〖1 OF 4〗Other Analysis -- Object Detection",false,false,true,false);
    controlCell.show() ;
  }

  void jMenuItem30_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("AnaArrayBackGround");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=2;
    dialogDelBG=new DialogDelBackGround(this,"〖2 OF 3〗Array Analysis -- Remove Background",false);
    dialogDelBG.setExeMethod(0);
    dialogDelBG.setCurrentNo(3);
    dialogDelBG.setInterface();
    dialogDelBG.show();
  }

  void jMenuItem35_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("AnaOtherBackGround");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=2;
    dialogDelBG=new DialogDelBackGround(
               this,"〖2 OF 4〗Other Analysis -- Remove Background",false);
    dialogDelBG.setExeMethod(0);
    dialogDelBG.setCurrentNo(4);
    dialogDelBG.setInterface();
    dialogDelBG.show();
  }

  void jMenuItem31_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("AnaArrayCount");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=3;
    dialogCount=new DialogCount(this,"",false);
    dialogCount.setExeMethod(0);
    dialogCount.setCurrentNo(6);
    dialogCount.setInterfaceInit(true);
    dialogCount.show();
  }

  void jMenuItem36_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("AnaOtherMWpI");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=3;
    dialogCount=new DialogCount(this,"",false);
    dialogCount.setExeMethod(0);
    dialogCount.setCurrentNo(7);
    dialogCount.setInterfaceInit(false);
    dialogCount.show();
  }

  void jMenuItem37_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("AnaOtherCount");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=4;
    dialogCount=new DialogCount(this,"",false);
    dialogCount.setExeMethod(0);
    dialogCount.setCurrentNo(8);
    dialogCount.setInterfaceInit(true);
    dialogCount.show();
  }

  public void setWizardWinLocation(Point p){
    this.wizardLoaction =p;
  }

  public Point getWizardWinLocation(){
    return this.wizardLoaction ;
  }

  /*
  private void doEasyKey(){
    if(this.exeMethod!=2) return;
    if(this.nowAnalyse ==1){
      if(this.nowStep ==1) this.control1D.doExeMethodEasyKey();
    }
  }
  */

  public void doStep(boolean isNext){
    if(this.exeMethod ==0) return;
    if(isNext==true) nowStep ++;
    else nowStep--;
    ActionEvent e=null;
    if(this.nowAnalyse ==1){
      if(nowStep==1) this.jMenuItem10_actionPerformed(e);
      else if(nowStep==2) this.jMenuItem15_actionPerformed(e);
      else if(nowStep==3) this.jMenuItem11_actionPerformed(e);
      else if(nowStep==4) this.jMenuItem13_actionPerformed(e);
      else if(nowStep==5) this.jMenuItem16_actionPerformed(e);
      else if(nowStep==6) this.jMenuItem12_actionPerformed(e);
      else if(nowStep==7) this.jMenuItem17_actionPerformed(e);
      else if(nowStep==8) this.jMenuItem19_actionPerformed(e);
      else if(nowStep==9) this.jMenuItem20_actionPerformed(e);
      else if(nowStep==10) this.jMenuItem18_actionPerformed(e);
      else if(nowStep==11){
        if(this.exeMethod==2){
          DialogDoInformation doinfor=new DialogDoInformation(this,"Analysis Information",false);
          doinfor.show();
          jMenuArrageLayer_actionPerformed(null);
        }
        this.exeMethod =0;
      }
    }
    else if(this.nowAnalyse ==2){
      if(nowStep==1) this.jMenuItem23_actionPerformed(null);
      else if(nowStep==2) this.jMenuItem25_actionPerformed(null);
      else if(nowStep==3) this.jMenuItem26_actionPerformed(null);
      else if(nowStep==4) this.jMenuItem28_actionPerformed(null);
      else if(nowStep==5) this.jMenuItem27_actionPerformed(null);
      else if(nowStep==6) this.jMenuItem43_actionPerformed(null);
      else if(nowStep==7) this.exeMethod =0;
    }
    else if(this.nowAnalyse ==3){
      if(nowStep==1) this.jMenuItem29_actionPerformed(e);
      else if(nowStep==2) this.jMenuItem30_actionPerformed(e);
      else if(nowStep==3) this.jMenuItem31_actionPerformed(e);
      else if(nowStep==4) this.exeMethod =0;
    }
    else if(this.nowAnalyse ==4){
      if(nowStep==1) this.jMenuItem34_actionPerformed(e);
      else if(nowStep==2) this.jMenuItem35_actionPerformed(e);
      else if(nowStep==3) this.jMenuItem36_actionPerformed(e);
      else if(nowStep==4) this.jMenuItem37_actionPerformed(e);
      else if(nowStep==5) this.exeMethod =0;
    }
    else if(this.nowAnalyse ==5){
      if(nowStep==1) this.jMenuItem4_actionPerformed(e);
      else if(nowStep==2) this.jMenuItem6_actionPerformed(e);
      else if(nowStep==3) this.exeMethod =0;
    }
  }

  public int getExeMethod(){
    return this.exeMethod ;
  }

  void jMenuItem2_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana1DWizard");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    this.exeMethod =1;
    this.nowAnalyse =1;
    this.nowStep =0;
    this.doStep(true);
  }

  void jMenuItem21_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana2DWizard");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    // 特别设置，为2D之MW/pI分析的方向设置准备
    this.currentImage.MW_2D_isDirection=false;
    this.exeMethod =1;
    this.nowAnalyse =2;
    this.nowStep =0;
    this.doStep(true);
  }

  void jMenuItem5_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("AnaArrayWizard");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    this.exeMethod =1;
    this.nowAnalyse =3;
    this.nowStep =0;
    this.doStep(true);
  }

  void jMenuItem32_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("AnaOtherWizard");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    this.exeMethod =1;
    this.nowAnalyse =4;
    this.nowStep =0;
    this.doStep(true);
  }

  void jMenuItem38_actionPerformed(ActionEvent e) {
    String str="Stack Size: "+(double)Runtime.getRuntime().totalMemory()/(1024.0f*1024.0f)+"M";
    str+=" Availabe Memory: "+(double)Runtime.getRuntime().freeMemory()/(1024.0f*1024.0f)+"M";
    this.setTitle(str);
  }

  void jMenuToRGB24Bits_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("ConvertRGB24");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(currentImage==null) return;
    this.currentImage .convertToRGB(1) ;
  }

  void jPanelMain_mousePressed(MouseEvent e) {
    if(e.getModifiers()==e.BUTTON3_MASK) {
      showPopMenu(jPanelMain ,e.getPoint().x,e.getPoint().y);
    }
  }

  public void showPopMenu(Component comp,int x,int y){
    // popup menu
    mainPop.add(jMenuFile);
    mainPop.add(jMenuEdit);
    mainPop.add(jMenuImage);
    mainPop.add(jMenuAnalyse);
    mainPop.add(jMenuWindows);
    mainPop.add(jMenuSet);
    mainPop.add(jMenuTools);
    mainPop.add(jMenuMyHelp);
    mainPop.show(comp,x,y);
  }

  public void showMainMenu(){
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenuEdit);
    jMenuBar1.add(jMenuImage);
    jMenuBar1.add(jMenuAnalyse);
    jMenuBar1.add(jMenuWindows);
    jMenuBar1.add(jMenuSet);
    jMenuBar1.add(jMenuTools);
    jMenuBar1.add(jMenuMyHelp);
    this.setJMenuBar(jMenuBar1);
  }

  void jMenuBar1_mouseMoved(MouseEvent e) {
    showMainMenu();
  }

  public DialogControlCell getDialogControlCell(){
    return controlCell;
  }

  void jButton10move_mouseEntered(MouseEvent e) {
    image10=new ImageIcon(systemDir+"\\icon\\move2.jpg");
    jButton10move.setIcon(image10);
  }

  void jButton10move_mouseExited(MouseEvent e) {
    image10=new ImageIcon(systemDir+"\\icon\\move.jpg");
    jButton10move.setIcon(image10);
  }

  void jButton1open_mouseEntered(MouseEvent e) {
    image1=new ImageIcon(systemDir+"\\icon\\open2.jpg");
    jButton1open.setIcon(image1);
  }

  void jButton1open_mouseExited(MouseEvent e) {
    image1=new ImageIcon(systemDir+"\\icon\\open.jpg");
    jButton1open.setIcon(image1);
  }

  void jButton2save_mouseEntered(MouseEvent e) {
    image2=new ImageIcon(systemDir+"\\icon\\save2.jpg");
    jButton2save.setIcon(image2);
  }

  void jButton2save_mouseExited(MouseEvent e) {
    image2=new ImageIcon(systemDir+"\\icon\\save.jpg");
    jButton2save.setIcon(image2);
  }

  void jButton3print_mouseEntered(MouseEvent e) {
    image3=new ImageIcon(systemDir+"\\icon\\print2.jpg");
    jButton3print.setIcon(image3);
  }

  void jButton3print_mouseExited(MouseEvent e) {
    image3=new ImageIcon(systemDir+"\\icon\\print.jpg");
    jButton3print.setIcon(image3);
  }

  void jButton4import_mouseEntered(MouseEvent e) {
    image4=new ImageIcon(systemDir+"\\icon\\import2.jpg");
    jButton4import.setIcon(image4);
  }

  void jButton4import_mouseExited(MouseEvent e) {
    image4=new ImageIcon(systemDir+"\\icon\\import.jpg");
    jButton4import.setIcon(image4);
  }

  void jButton5undo_mouseEntered(MouseEvent e) {
    image5=new ImageIcon(systemDir+"\\icon\\undo2.jpg");
    jButton5undo.setIcon(image5);
  }

  void jButton5undo_mouseExited(MouseEvent e) {
    image5=new ImageIcon(systemDir+"\\icon\\undo.jpg");
    jButton5undo.setIcon(image5);
  }

  void jButton6selobject_mouseEntered(MouseEvent e) {
    image6=new ImageIcon(systemDir+"\\icon\\selobject2.jpg");
    jButton6selobject.setIcon(image6);
  }

  void jButton6selobject_mouseExited(MouseEvent e) {
    image6=new ImageIcon(systemDir+"\\icon\\selobject.jpg");
    jButton6selobject.setIcon(image6);
  }

  void jButton7selrect_mouseEntered(MouseEvent e) {
    image7=new ImageIcon(systemDir+"\\icon\\selrect2.jpg");
    jButton7selrect.setIcon(image7);
  }

  void jButton7selrect_mouseExited(MouseEvent e) {
    image7=new ImageIcon(systemDir+"\\icon\\selrect.jpg");
    jButton7selrect.setIcon(image7);
  }

  void jButton8big_mouseEntered(MouseEvent e) {
    image8=new ImageIcon(systemDir+"\\icon\\big2.jpg");
    jButton8big.setIcon(image8);
  }

  void jButton8big_mouseExited(MouseEvent e) {
    image8=new ImageIcon(systemDir+"\\icon\\big.jpg");
    jButton8big.setIcon(image8);
  }

  void jButton9small_mouseEntered(MouseEvent e) {
    image9=new ImageIcon(systemDir+"\\icon\\small2.jpg");
    jButton9small.setIcon(image9);
  }

  void jButton9small_mouseExited(MouseEvent e) {
    image9=new ImageIcon(systemDir+"\\icon\\small.jpg");
    jButton9small.setIcon(image9);
  }

  void jButton111d_mouseEntered(MouseEvent e) {
    image11=new ImageIcon(systemDir+"\\icon\\1d2.jpg");
    jButton111d.setIcon(image11);
  }

  void jButton111d_mouseExited(MouseEvent e) {
    image11=new ImageIcon(systemDir+"\\icon\\1d.jpg");
    jButton111d.setIcon(image11);
  }

  void jButton122d_mouseEntered(MouseEvent e) {
    image12=new ImageIcon(systemDir+"\\icon\\2d2.jpg");
    jButton122d.setIcon(image12);
  }

  void jButton122d_mouseExited(MouseEvent e) {
    image12=new ImageIcon(systemDir+"\\icon\\2d.jpg");
    jButton122d.setIcon(image12);
  }

  void jButton13array_mouseEntered(MouseEvent e) {
    image13=new ImageIcon(systemDir+"\\icon\\array2.jpg");
    jButton13array.setIcon(image13);
  }

  void jButton13array_mouseExited(MouseEvent e) {
    image13=new ImageIcon(systemDir+"\\icon\\array.jpg");
    jButton13array.setIcon(image13);
  }

  void jButton14others_mouseEntered(MouseEvent e) {
    image14=new ImageIcon(systemDir+"\\icon\\others2.jpg");
    jButton14others.setIcon(image14);
  }

  void jButton14others_mouseExited(MouseEvent e) {
    image14=new ImageIcon(systemDir+"\\icon\\others.jpg");
    jButton14others.setIcon(image14);
  }

  void jButton15easy_mouseEntered(MouseEvent e) {
    image15=new ImageIcon(systemDir+"\\icon\\easy2.jpg");
    jButton15easy.setIcon(image15);
  }

  void jButton15easy_mouseExited(MouseEvent e) {
    image15=new ImageIcon(systemDir+"\\icon\\easy.jpg");
    jButton15easy.setIcon(image15);
  }

  void jButton16online_mouseEntered(MouseEvent e) {
    image16=new ImageIcon(systemDir+"\\icon\\online2.jpg");
    jButton16online.setIcon(image16);
  }

  void jButton16online_mouseExited(MouseEvent e) {
    image16=new ImageIcon(systemDir+"\\icon\\online.jpg");
    jButton16online.setIcon(image16);
  }

  void jMenuItem7_actionPerformed(ActionEvent e) {
    Process process;
    try{
      process=Runtime.getRuntime().exec(systemDir+"\\CtrHard.exe");
    }
    catch(IOException e2){
      e2.printStackTrace();
    }
  }

  void jMenuItem8_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("TextTool");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    this.showControlText(false,false);
  }

  void jMenuItem9_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("LineTool");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(controlLine==null) {
      controlLine=new DialogControlLine(this,"Line Tools",false);
    }
    controlLine.show();
  }

  void jMenuItemSaveAll_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("SaveImageText");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(this.currentImage==null) return;
    this.currentImage.saveImageAndLayers();
  }

  void jMenuBar1_mouseClicked(MouseEvent e) {
  }

  void jMenuItemOpenTiff_actionPerformed(ActionEvent e) {
    this.disposeClosedDialogImage() ;
    String path2,name2;
    int width,height,imageFormat;
    boolean bg;

    name2=this.currentImage.getTiffName();
    path2=this.currentImage.getTiffPath();
    width=this.currentImage.getTiffWidth();
    height=this.currentImage.getTiffHeight();
    bg=this.currentImage.getBackgroundBlack();
    imageFormat=OpenFile.TIFF;
    // 添加新图像
    DialogImage currentImage;
    try{
      currentImage=new DialogImage(path2,name2,width,height,
                                   imageFormat,this);
      currentImage.setBackgroundBlank(bg);
    }
    catch( OutOfMemoryError e2) {
      JOptionPane myOptionPane=new JOptionPane();
      myOptionPane.showMessageDialog(this,"Memory Overflow","Memory Overflow"
                                     ,JOptionPane.WARNING_MESSAGE );
      return;
    }
    images.addElement(currentImage);
    currentImage.show();
    //
    currentName=name2;
    currentPath=path2;
    currentWidth=width;
    currentHeight=height;
    currentFormat=imageFormat;
    //
    /*
    currentImage.judgeBackground();
    boolean bg=currentImage.getBackgroundBlack() ;
    DialogBackground dbg=new DialogBackground(this,"背景选择:",true,currentImage,bg);
    dbg.show();
    */

    currentImage.importRsult();
    currentImage.paintImage();
  }

  void jMenuItem39_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Set3DColor");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(this.currentImage==null) return;
    this.currentImage.set3DColor();
  }

  void jPanelMain_mouseClicked(MouseEvent e) {
    Window win[]=this.getOwnedWindows();
    Rectangle rect;
    for(int ii=1;ii<=win.length;ii++){
      Point pt=new Point(win[ii-1].getLocation().x+win[ii-1].getWidth()-this.jPanelMain.getLocation().x,
      win[ii-1].getLocation().y-this.jPanelMain.getLocation().y-60);
      if(e.getPoint().x>=pt.x-10 && e.getPoint().x<=pt.x+25 &&
         e.getPoint().y>=pt.y && e.getPoint().y<=pt.y+win[ii-1].getHeight()){
           rect=this.getWinRect(win[ii-1]);
           if(rect==null){
             this.window.addElement(win[ii-1]);
             this.windowBound.addElement(win[ii-1].getBounds());
             rearrangeWindow();
             break;
           }
           else{
             int winNo=this.findWinInVector(win[ii-1]);
             if(winNo==-1) return;
             //this.window.removeElementAt(winNo);
             this.windowBound.removeElementAt(winNo);
             this.windowBound.insertElementAt(win[ii-1].getBounds(),winNo);
             win[ii-1].setBounds(rect);
             win[ii-1].toFront();
             //rearrangeWindow();
             break;
           }
      }
    }
  }

  private int findWinInVector(Window win){
    int rt=-1;
    int size=this.window.size();
    Window winTmp;
    for(int ii=1;ii<=size;ii++){
      winTmp=(Window)this.window.elementAt(ii-1);
      if(winTmp.equals(win)){
        rt=ii-1;
        break;
      }
    }
    return rt;
  }

  private void rearrangeWindow(){
    int winLen=25;
    int winHDist=15;
    int winVDist=20;
    int winH=10;
    int winLocX=this.jPanelMain.getLocation().x+winVDist;
    int winLocY=this.jPanelMain.getLocation().y+this.jPanelMain.getHeight()-winH-winVDist;
    int winW=this.jPanelMain.getWidth();
    int size=this.window.size();
    Window winTmp;
    for(int ii=1;ii<=size;ii++){
      winTmp=(Window)this.window.elementAt(ii-1);
      if(winTmp.isVisible()==false) continue;
      winTmp.setLocation(winLocX,winLocY);
      winTmp.setSize(winLen,winH);
      winLocX+=winTmp.getWidth()+winHDist;
      if( winLocX+winLen+winHDist >= winW*0.9){
        winLocX=this.jPanelMain.getLocation().x+winVDist;
        winLocY-=(winH+winVDist);
      }
    }
  }

  /*
  private void setAllWindowsToBack(){
    Window [] win=this.getOwnedWindows() ;
    for(int ii=1;ii<=win.length ;ii++){
      win[ii-1].toBack();
    }
  }
  */

  private void rearrangeWindowRight(){
    int winLen=25;
    int winHDist=120;
    int winVDist=1;
    int winH=10;
    int winLocX=this.jPanelMain.getWidth()-winH-winHDist;
    int winLocY=this.jPanelMain.getLocation().y+55;
    int winW=this.jPanelMain.getWidth();
    int size=this.window.size();
    Window winTmp;
    for(int ii=1;ii<=size;ii++){
      winTmp=(Window)this.window.elementAt(ii-1);
      if(winTmp.isVisible()==false) continue;
      winTmp.setLocation(winLocX,winLocY);
      winTmp.setSize(winLen,winH);
      winLocY+=winTmp.getHeight()+winVDist;
      //if( winLocX+winLen+winHDist >= winW*0.9){
      //  winLocX=this.jPanelMain.getLocation().x+winVDist;
      //  winLocY-=(winH+winVDist);
      //}
    }
  }

  private Rectangle getWinRect(Window win){
    Rectangle rect=null;
    Window winv;
    int n=window.size();
    for(int ii=1;ii<=n;ii++){
      winv=(Window)window.elementAt(ii-1);
      if(winv.equals(win)){
        rect=(Rectangle)windowBound.elementAt(ii-1);
        //window.removeElementAt(ii-1);
        //windowBound.removeElementAt(ii-1);
        break;
      }
    }
    return rect;
  }

  void jMenuItem40_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("RevertImage");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(this.currentImage ==null) return;
    this.currentImage.imageRevert(1);
  }

  public void setCurrentParaPre(String para){
    this.currentParaPre=para;
  }

  public String getCurrentParaPre(){
    return this.currentParaPre;
  }

  void jMenuItem41_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("DoOneKey");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    DialogSettings diSet=new DialogSettings(this,"Setup for Defalut Values:",true,
        this.systemDir+"\\database\\setting.ini",currentParaPre,this.systemDir);
    diSet.show();
  }

  void jMenuItem14_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("DoOneKey");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    this.setCursor(this.currentImage.createWaitCursor());
    this.exeMethod =2;
    this.nowAnalyse =1;
    this.nowStep =0;
    this.doStep(true);
    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
  }

  public EasyGelSet getEasyGelSet(){
    return easyGelSet;
  }

  public void saveEasyGelSet(){
    File file=new File(systemDir+"\\database\\EasyGels.ini");
    if(file.exists()==true) file.delete();
    try{
      ObjectOutputStream o=new ObjectOutputStream(
          new FileOutputStream(systemDir+"\\database\\EasyGels.ini"));
      o.writeObject(this.easyGelSet);
    }
    catch(Exception e3){
      e3.printStackTrace() ;
   }
  }

  void jMenuItem42_actionPerformed(ActionEvent e){
    if(isHelp==true){
      Helper.Helper("DispAnaInformation");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    DialogDoInformation doinfor=new DialogDoInformation(this,"Analysis Information",true);
    doinfor.show();
  }

  void jMenuCutROI_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("CutROI");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    this.currentImage.clipROI();
  }

  void statusBar_mouseClicked(MouseEvent e) {

  }

  void jToolBar_mouseClicked(MouseEvent e) {
    this.openFile();
  }

  public void jMenuItem22_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("WinMinBottomArrange");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    Window win[]=this.getOwnedWindows();
    Rectangle rect;
    for(int ii=1;ii<=win.length;ii++){
      if(win[ii-1].isVisible()==false) continue;
      rect=this.getWinRect(win[ii-1]);
      if(rect==null){
        this.window.addElement(win[ii-1]);
        this.windowBound.addElement(win[ii-1].getBounds());
      }
    }
    rearrangeWindow();
  }

  void jMenuItem28_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana2DpI");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    if(e!=null) this.currentImage.MW_2D_isDirection=false;
    nowStep=4;
    dialogCount=new DialogCount(this,"",false);
    dialogCount.setExeMethod(0);
    dialogCount.setCurrentNo(5);
    dialogCount.setInterfaceInit(false);
    dialogCount.show();
  }

  void jMenuItem43_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("Ana2DAdjust");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=6;
    dialogCount=new DialogCount(this,"",false);
    dialogCount.setExeMethod(0);
    dialogCount.setCurrentNo(10);
    dialogCount.setInterfaceInit(true);
    dialogCount.show();
  }

  void jMenuItem4_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("AnaEcoliReco");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=1;
    autoArrangeWindows();
    controlCell=new DialogControlCell(this,"〖1 OF 2〗Clone Counts -- Object Detection",false,false,true,true);
    controlCell.show() ;
  }

  void jMenuItem6_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("AnaEcoliCount");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    nowStep=2;
    dialogCountEcoli=new DialogCountEcoli(this,"〖2 OF 2〗Clone Counts -- Clone Counts",false);
    dialogCountEcoli.show();
  }

  void jMenuItem44_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("AnaEcoliWizard");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    this.exeMethod =1;
    this.nowAnalyse =5;
    this.nowStep =0;
    this.doStep(true);
  }

  void jMenuItem24_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("WinMinRightArrange");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
    Window win[]=this.getOwnedWindows();
    Rectangle rect;
    for(int ii=1;ii<=win.length;ii++){
      if(win[ii-1].isVisible()==false) continue;
      rect=this.getWinRect(win[ii-1]);
      if(rect==null){
        this.window.addElement(win[ii-1]);
        this.windowBound.addElement(win[ii-1].getBounds());
      }
    }
    rearrangeWindowRight();
  }

  void jCheckBoxMenuItem1_actionPerformed(ActionEvent e) {
    if(isHelp==true){
      Helper.Helper("WinAdjust");
      this.setCursor(Cursor.DEFAULT_CURSOR );
      isHelp=false;
      return;
    }
  }

  // end of the class
}