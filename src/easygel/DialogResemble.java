package easygel;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.border.*;
import javax.swing.event.*;
import easygel.Tree;
// added by wxs 20030823 -01
import easygel.setting.*;
import java.io.*;
import java.util.*;

// a/d/m by wxs total=18

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogResemble extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private XYLayout xYLayout2 = new XYLayout();
  public  Vector Vtree=new Vector();
  private FrameMain mainfm;
  private JLabel jLabel1 = new JLabel();
  private TitledBorder titledBorder1;
  private Vector list1=new Vector();
  public Vector list2=new Vector();
  //private JTree jTree1 = new JTree();
  DefaultMutableTreeNode top=new DefaultMutableTreeNode("School");
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JList jList1 = new JList();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JScrollPane jScrollPane2 = new JScrollPane();
  private JList jList2 = new JList();
  private JButton jButton4 = new JButton();
  private JButton jButton5 = new JButton();
  private JButton jButton6 = new JButton();
  private JComboBox j07CofiCombo = new JComboBox();
  private JLabel jLabel6 = new JLabel();
  private JButton jButton7 = new JButton();
  private JTextField j07ErrorText = new JTextField();
  //private DialogShowTree dlgShowTree;
  private DialogDrawTree dlgDrawTree;
  private   DialogReport dlg;
  private int index;
  private TitledBorder titledBorder2;
  private TitledBorder titledBorder3;
  private TitledBorder titledBorder4;
  private TitledBorder titledBorder5;
  private TitledBorder titledBorder6;
  private JSlider jSlider1 = new JSlider();
  private TitledBorder titledBorder7;
  private JPanel jPanel2 = new JPanel();
  private TitledBorder titledBorder8;
  private JPanel jPanel3 = new JPanel();
  private TitledBorder titledBorder9;
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  public int N;
  public float vtlength;
  private XYLayout xYLayout3 = new XYLayout();
  private JButton jButton3 = new JButton();
  private XYLayout xYLayout4 = new XYLayout();
  private JButton jButton8 = new JButton();
  private JButton jButton9 = new JButton();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel7 = new JLabel();
  public JComboBox j07ModelCombo = new JComboBox();

  // added by wxs 20030823 -02
  private Vector set=new Vector();
  private boolean isVisible;
  private boolean isInit;

  // added by liusheng3
  public String algo=new String();

  public DialogResemble(FrameMain frame, String title, boolean modal) {
    super(frame, title, modal);
    mainfm=frame;
    // added by wxs 20030823 -03
    this.isInit=true;
    //String algo=new String();
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogResemble() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    titledBorder4 = new TitledBorder("");
    titledBorder5 = new TitledBorder("");
    titledBorder6 = new TitledBorder("");
    titledBorder7 = new TitledBorder("");
    titledBorder8 = new TitledBorder("");
    titledBorder9 = new TitledBorder("");
    panel1.setLayout(xYLayout2);
    this.getContentPane().setLayout(xYLayout1);
    jLabel1.setText("Error Allowance");
    xYLayout1.setWidth(442);
    xYLayout1.setHeight(262);
    jLabel4.setToolTipText("");
    jLabel4.setText("All Lanes");
    jLabel5.setText("Refer Lane");
    jButton4.setMargin(new Insets(0, 0, 0, 0));
    jButton4.setText(">>");
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
    jButton6.setMargin(new Insets(0, 0, 0, 0));
    jButton6.setText("Select All");
    jButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton6_actionPerformed(e);
      }
    });
    jLabel6.setText("Likeness Coefficient");
    jButton7.setMargin(new Insets(0, 0, 0, 0));
    jButton7.setText("Select Lane");
    jButton7.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton7_actionPerformed(e);
      }
    });
    j07ErrorText.setEditable(false);
    j07ErrorText.setText("3%");
    jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        jSlider1_stateChanged(e);
      }
    });
    jPanel2.setBorder(titledBorder8);
    jPanel2.setLayout(xYLayout4);
    jPanel3.setBorder(titledBorder9);
    jPanel3.setLayout(xYLayout3);
    jButton1.setMargin(new Insets(0, 0, 0, 0));
    jButton1.setText("Cancel");

    jButton2.setMargin(new Insets(0, 0, 0, 0));
    jButton2.setText("OK");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton3.setText("Show Evolutionary Tree");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jButton8.setMargin(new Insets(0, 0, 0, 0));
    jButton8.setText("Homology");
    jButton8.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton8_actionPerformed(e);
      }
    });
    jButton9.setMargin(new Insets(0, 0, 0, 0));
    jButton9.setText("Distance");
    jButton9.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton9_actionPerformed(e);
      }
    });
    jLabel2.setText("Show Matrix");
    jLabel3.setText("Evolutionary Tree");
    jLabel7.setText("Cluster Model");
    j07ModelCombo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j07ModelCombo_actionPerformed(e);
      }
    });
    getContentPane().add(panel1,  new XYConstraints(0, 0, -1, -1));
    jPanel2.add(jButton9,  new XYConstraints(11, 42, 63, 19));
    jPanel2.add(jButton8, new XYConstraints(87, 42, 63, 19));
    jPanel2.add(jLabel2,                         new XYConstraints(43, 2, 67, 28));
    this.getContentPane().add(jPanel3, new XYConstraints(23, 131, -1, 82));
    jPanel3.add(jButton3,    new XYConstraints(103, 2, 97, 23));
    jPanel3.add(jLabel3,    new XYConstraints(14, 3, 63, 23));
    jPanel3.add(j07ModelCombo, new XYConstraints(84, 39, 138, 19));
    jPanel3.add(jLabel7,  new XYConstraints(2, 41, 76, 16));
    this.getContentPane().add(jPanel2,       new XYConstraints(260, 131, 167, 82));
    this.getContentPane().add(jScrollPane2,  new XYConstraints(194, 34, 85, 89));
    this.getContentPane().add(jButton7, new XYConstraints(120, 35, 62, 19));
    this.getContentPane().add(jButton4, new XYConstraints(120, 58, 62, 19));
    this.getContentPane().add(jButton5, new XYConstraints(120, 80, 62, 19));
    this.getContentPane().add(jButton6, new XYConstraints(120, 103, 62, 19));
    this.getContentPane().add(jLabel4, new XYConstraints(39, 14, 63, 19));
    this.getContentPane().add(jLabel5, new XYConstraints(214, 14, 62, 20));
    this.getContentPane().add(jScrollPane1, new XYConstraints(23, 35, 85, 89));
    this.getContentPane().add(jLabel6,  new XYConstraints(328, 71, 67, 20));
    this.getContentPane().add(j07CofiCombo, new XYConstraints(298, 96, 118, 18));
    this.getContentPane().add(j07ErrorText,  new XYConstraints(343, 37, 36, 18));
    this.getContentPane().add(jSlider1,  new XYConstraints(381, 35, 50, 21));
    this.getContentPane().add(jLabel1, new XYConstraints(288, 34, 54, 25));
    jScrollPane1.getViewport().add(jList1, null);
    jScrollPane2.getViewport().add(jList2, null);
    this.getContentPane().add(jButton2,  new XYConstraints(273, 232, 73, 25));
    this.getContentPane().add(jButton1,  new XYConstraints(90, 232, 73, 25));
    //dlgShowTree=null;
    dlgDrawTree=null;
    j07CofiCombo.addItem("Nei&Li(Dice) Coefficient");
    j07CofiCombo.addItem("Jaccard Coefficient");
    j07CofiCombo.addItem("Cosine Coefficient");
    j07CofiCombo.addItem("Overlap Coefficient");
    //dlgShowTree=null;
    index=1;
    this.jSlider1 .setValue(3);
     this.getlane();
     if(this.mainfm .getExeMethod() ==0){
       this.jButton2 .setText("OK");
       this.jButton1 .setText("Cancle");
     }
     else{
       this.setLocation(this.mainfm.getWizardWinLocation()) ;
       this.jButton2 .setText("Next▼");
       this.jButton1 .setText("Last▲") ;
     }
    j07ModelCombo.addItem("UPGMA");
    j07ModelCombo.addItem("Neighbor Joining");
    j07ModelCombo.addItem("Single Linkage");
    j07ModelCombo.addItem("Complete Linkage");
    j07ModelCombo.addItem("WPGMA");
    j07ModelCombo.addItem("Centroid");
    j07ModelCombo.addItem("Median");
    j07ModelCombo.addItem("Ward's");

    // added by wxs 20030823 -04
    this.setInterfaceVFromFile();
    this.setInterfaceFromV();

    // added by wxs 20030823 -05
    if(this.mainfm.getExeMethod()==1) this.isVisible=true;
    this.setVisible(this.isVisible);
    this.isInit=false;

    // added by wxs 20030823 -06
    this.doExeMethodEasyKey();
  }



public float getvtreelength(){
  Vector vt=this.Vtree;
  float length=0;
    int size=vt.size();
    Tree tr=(Tree)vt.elementAt(size-1);
    String left=tr.left;
    length=tr.resem;
    /*for(int ii=size-2;ii>=0;ii--){
      tr=(Tree)vt.elementAt(ii);
      if(left.equals(tr.parent)){
        length+=tr.resem;
        left=tr.left;
      }

    }*/
    vtlength=length;
    return  length;

}
 //**
 /**
  * 对得到的矩阵进行处理
  * @param aa 相似性比较的矩阵
  * @param vd 与这个矩阵对应的行列的含义
  * @return
  */
  private float  [][]dealarray(float [][]aa,Vector vd){
    int length=aa.length;
    String algorithm=new String();
    algorithm=(String)j07ModelCombo.getSelectedItem();
    float [][]bb=new float[length-1][length-1];

    if(length==2){
      Tree tr=new Tree();
      tr.left=(String)vd.elementAt(0);
      tr.right=(String)vd.elementAt(1);
      tr.parent=(String)vd.elementAt(0)+(String)vd.elementAt(1);
      tr.resem=aa[1][0];

      //Liusheng3 add
      if(algorithm=="Neighbor Joining"){
        tr.leftdis=aa[1][0]/2;
        tr.rightdis=aa[1][0]/2;
        // set precision
        String str1;
        str1=String.valueOf(tr.leftdis);
        if(str1.length()>=4) str1=str1.substring(0,4);
        tr.leftdis=(Float.valueOf(str1)).floatValue();
        str1=String.valueOf(tr.rightdis);
        if(str1.length()>=4) str1=str1.substring(0,4);
        tr.rightdis=(Float.valueOf(str1)).floatValue();
      }
     //End

     // set precision
     String str;
     str=String.valueOf(tr.resem);
     if(str.length()>=4) str=str.substring(0,4);
     tr.resem=(Float.valueOf(str)).floatValue();

     //Liusheng add at 8
     tr.ltpt=new Point(0,0);
     tr.rtpt=new Point(0,0);
     tr.tppt=new Point(0,0);
    //End
     this.Vtree.addElement(tr);
    }

    if(length>2) {
    float min;
    int  bi,bj;
    min=1000000;
    bi=bj=0;
    float []u=new float[length];
    if(algorithm=="Neighbor Joining"){
      float [][]tempaa=new float[length][length];
      for(int ii=0;ii<length;ii++){
         u[ii]=0;
        for(int jj=0;jj<length;jj++){
         if(ii>jj)  u[ii]+=aa[ii][jj];
         if(ii<jj) u[ii]+=aa[jj][ii];
        }
      }
       for(int ii=0;ii<length;ii++){
         for(int jj=0;jj<length;jj++){
           if(ii>jj) {
             float c1=(float)u[ii]/(length-2);
             float c2=(float)u[jj]/(length-2);
              tempaa[ii][jj]=(float)aa[ii][jj]-c1-c2;
              if(min>tempaa[ii][jj]){
                  min=tempaa[ii][jj];
                  bi=ii;
                  bj=jj;
                  }
           }
           else{
             tempaa[ii][jj]=aa[ii][jj];
           }
         }
       }

    }
    else{
      for(int ii=0;ii<length;ii++)
      {
          for(int jj=0;jj<length;jj++)
            //LIUSHENG ADD AT 8,
          {if((min>aa[ii][jj])&&(ii>jj)&&aa[ii][jj]>=0)
          //End
            {
            min=aa[ii][jj];
            bi=ii;
            bj=jj;

          }
          }
    }
    }

    Tree tr=new Tree();
    String s11,s22;
    s11=(String)vd.elementAt(bj);
    s22=(String)vd.elementAt(bi);
    tr.left=(String)vd.elementAt(bj);
    tr.right=(String)vd.elementAt(bi);

    //Liusheng3 add
    if(algorithm=="Neighbor Joining"){
      float a1=aa[bi][bj];
      float a2=u[bi];
      float a3=u[bj];
  //  tr.leftdis=(a1+((a2-a3)/(length-2))/2);
      tr.leftdis=((aa[bi][bj]+((u[bi]-u[bj])/(length-2)))/2);
      tr.rightdis=aa[bi][bj]-tr.leftdis;
      System.out.println("tr.leftdis"+tr.leftdis);
      System.out.println("tr.rightdis"+tr.rightdis);
      System.out.println("aa"+aa[bi][bj]);
      String str1;
      str1=String.valueOf(tr.leftdis);
      if(str1.length()>=4) str1=str1.substring(0,4);
      tr.leftdis=(Float.valueOf(str1)).floatValue();
      str1=String.valueOf(tr.rightdis);
      if(str1.length()>=4) str1=str1.substring(0,4);
      tr.rightdis=(Float.valueOf(str1)).floatValue();
    }
    //End

    //Liusheng add at 8
    tr.ltpt=new Point(0,0);
    tr.rtpt=new Point(0,0);
    tr.tppt=new Point(0,0);
     //End
     //Liusheng2 one line revised
    tr.parent=s11+s22;
     //  tr.parent="("+Integer.toString(index)+")"+"∣"+String.valueOf(min);
    tr.resem=min;

     // set precision
    String str;
    str=String.valueOf(tr.resem);
    if(str.length()>=4) str=str.substring(0,4);
    tr.resem=(Float.valueOf(str)).floatValue();

     //  System.out.println("resemble"+tr.resem);
    this.Vtree.addElement(tr);
     String s;
     //Liusheng2 one line revised
     s=tr.left+tr.right;
    // s="("+Integer.toString(index)+")"+"∣"+String.valueOf(min);
     index++;
   /*
     System.out.println("bifgf;gsdglkfjgdfgjdf;ggdfgdfg");
     System.out.println(vd.elementAt(bj));
         System.out.println(vd.elementAt(bi));

    System.out.println("bi"+bi);
    System.out.println(bj);
    System.out.println(min);
    */

     vd.removeElementAt(bi);
     vd.removeElementAt(bj);
     vd.insertElementAt(s,bj);
     for(int ii=0;ii<length;ii++) {
        for(int jj=0;jj<length;jj++){
          if(ii!=bi&&jj!=bi){
          if(ii==bj||jj==bj){
          if(ii==bj)
           {
          if(jj<bj){
            bb[ii][jj]=(aa[bi][jj]+aa[bj][jj])/2;
           if(algorithm=="Neighbor Joining") {
            bb[ii][jj]=aa[bi][jj]/2+aa[bj][jj]/2-aa[bi][bj]/2;
            }
           if(algorithm=="Single Linkage"){
           float component=Math.abs((aa[bi][jj]-aa[bj][jj]));
            bb[ii][jj]=aa[bi][jj]/2+aa[bj][jj]/2;
            bb[ii][jj]-=component/2 ;
           }
            if(algorithm=="Complete Linkage"){
           float component=Math.abs((aa[bi][jj]-aa[bj][jj]));
            bb[ii][jj]=aa[bi][jj]/2+aa[bj][jj]/2+component/2;
            }
            if(algorithm=="Centroid"){
            int Na=mainfm.getCurrentImage().getLayer1D().getlinenum(bi);
            int Nb=mainfm.getCurrentImage().getLayer1D().getlinenum(bj);
            float  c1=(float)Na/(Na+Nb);
            float  c2=(float)Nb/(Na+Nb);
            float  c31=(float)(Na+Nb)*(Na+Nb);
            float  c32=(float)Na*Nb;
            float  c3=(float)c32/c31;
             bb[ii][jj]=(float)aa[bi][jj]*c1+aa[bj][jj]*c2+aa[bi][bj]*c3;
            }
            if(algorithm=="Median"){
             bb[ii][jj]=(float)aa[bi][jj]/2+aa[bj][jj]/2-aa[bi][bj]/4;
            }
            if(algorithm=="Ward's"){
            int Np=mainfm.getCurrentImage().getLayer1D().getlinenum(bi);
            int Nq=mainfm.getCurrentImage().getLayer1D().getlinenum(bj);
            int Nr=mainfm.getCurrentImage().getLayer1D().getlinenum(jj);
            // int c1=()
            }
          }
            else
          {if(jj<bi){
            bb[ii][jj]=aa[ii][jj];
          }
            if(jj>bi)   bb[ii][jj-1] =aa[ii][jj];
          }
          }
          if(jj==bj)
          {if(ii<bj)  bb[ii][jj]=aa[ii][jj];
           else
           {if(bj<ii&&ii<bi) {
           bb[ii][jj]= (aa[ii][bj]+aa[bi][ii])/2;
           if(algorithm=="Neighbor Joining") {
           bb[ii][jj]=aa[bi][ii]/2+aa[ii][bj]/2-aa[bi][bj]/2;
          }
         if(algorithm=="Single Linkage"){
         float component=Math.abs((aa[bi][ii]-aa[ii][bj]));
         float temp=component;
          bb[ii][jj]=aa[bi][ii]/2+aa[ii][bj]/2-component/2;
          }
          if(algorithm=="Complete Linkage"){
         float component=Math.abs((aa[bi][ii]/2-aa[ii][bj]/2));
          bb[ii][jj]=aa[bi][ii]/2+aa[ii][bj]/2+component/2;
          }
          if(algorithm=="Centroid"){
          int Na=mainfm.getCurrentImage().getLayer1D().getlinenum(bi);
          int Nb=mainfm.getCurrentImage().getLayer1D().getlinenum(bj);
          float  c1=(float)Na/(Na+Nb);
          float  c2=(float)Nb/(Na+Nb);
          float  c31=(float)(Na+Nb)*(Na+Nb);
          float  c32=(float)Na*Nb;
          float  c3=(float)c32/c31;
           bb[ii][jj]=(float)aa[bi][ii]*c1+aa[ii][bj]*c2+aa[bi][bj]*c3;
          }
          if(algorithm=="Median"){
           bb[ii][jj]=(float)aa[bi][ii]/2+aa[ii][bj]/2-aa[bi][bj]/4;
          }
          if(algorithm=="Ward's"){
          int Np=mainfm.getCurrentImage().getLayer1D().getlinenum(bi);
          int Nq=mainfm.getCurrentImage().getLayer1D().getlinenum(bj);
          int Nr=mainfm.getCurrentImage().getLayer1D().getlinenum(jj);
          // int c1=()
            }
           }
           //???
           if(ii>bi) {
             bb[ii-1][jj]= (aa[ii][bj]+aa[ii][bi])/2;
             if(algorithm=="Neighbor Joining") {
           bb[ii-1][jj]=aa[ii][bi]/2+aa[ii][bj]/2-aa[bi][bj]/2;
           }
          if(algorithm=="Single Linkage"){
          float component=Math.abs((aa[ii][bi]-aa[ii][bj]));
         float temp=component;
          bb[ii-1][jj]=aa[ii][bj]/2+aa[ii][bi]/2-component/2;
           }
           if(algorithm=="Complete Linkage"){
          float component=Math.abs((aa[ii][bi]-aa[ii][bj]));
         float temp=component;
          bb[ii-1][jj]=aa[ii][bi]/2+aa[ii][bj]/2+component/2;
           }
           if(algorithm=="Centroid"){
           int Na=mainfm.getCurrentImage().getLayer1D().getlinenum(bi);
           int Nb=mainfm.getCurrentImage().getLayer1D().getlinenum(bj);
           float  c1=(float)Na/(Na+Nb);
           float  c2=(float)Nb/(Na+Nb);
           float  c31=(float)(Na+Nb)*(Na+Nb);
           float  c32=(float)Na*Nb;
           float  c3=(float)c32/c31;
            bb[ii-1][jj]=(float)aa[ii][bi]*c1+aa[ii][bj]*c2+aa[bi][bj]*c3;
           }
           if(algorithm=="Median"){
            bb[ii-1][jj]=(float)aa[ii][bi]/2+aa[ii][bj]/2-aa[bi][bj]/4;
           }
           if(algorithm=="Ward's"){
           int Np=mainfm.getCurrentImage().getLayer1D().getlinenum(bi);
           int Nq=mainfm.getCurrentImage().getLayer1D().getlinenum(bj);
           int Nr=mainfm.getCurrentImage().getLayer1D().getlinenum(jj);
           // int c1=()
            }
           }
           }
          }
        }
        else
        { if(ii<bi&&jj<bi)  bb[ii][jj]=aa[ii][jj];
         else
           {
         if(ii>bi&&jj<bi)  bb[ii-1][jj]=aa[ii][jj];
          if(jj>bi&&ii<bi)  bb[ii][jj-1]=aa[ii][jj];
         if(ii>bi&&jj>bi)    bb[ii-1][jj-1]=aa[ii][jj];
         }
         }
        }
        }

        }
    }
   // System.out.println("seperate");
    length=bb.length;
    if(bb.length>=2)
        this.dealarray(bb,vd);
   return bb;
  }

  private DefaultMutableTreeNode getNode(DefaultMutableTreeNode parent,String findStr){
    DefaultMutableTreeNode tempNode,returnNode;
    returnNode=null;

    tempNode=(DefaultMutableTreeNode)parent.getFirstChild();
    String strtemp=(String)tempNode.toString();
    if(strtemp.equals(findStr)) returnNode=tempNode;
    else {
      if(tempNode.isLeaf() ==false) returnNode=getNode(tempNode,findStr);
    }
    if(returnNode!=null) return returnNode;

    tempNode=(DefaultMutableTreeNode)parent.getLastChild() ;
    strtemp=(String)tempNode.toString();
   if( strtemp.equals(findStr) ) returnNode=tempNode;
    else {
      if(tempNode.isLeaf() ==false) returnNode=getNode(tempNode,findStr);
    }
    if(returnNode!=null) return returnNode;

    return returnNode;
  }



  void jButton2_actionPerformed(ActionEvent e) {
    this.dispose() ;
    // changed by wxs 20030827 -15
    if(this.mainfm .getExeMethod() >=1){
      this.mainfm .setWizardWinLocation(this.getLocation() ) ;
      this.mainfm.doStep(true);
    }
    /*
    if(dlgShowTree!=null)
          dlgShowTree.dispose();
        if(this.Vtree.size()>0)
          Vtree.removeAllElements();
        index=1;
       dlgShowTree=new DialogShowTree(mainfm ,"显示系统树",true);
    float [][]aa=new float[7][7];
    int length=aa.length;
    for(int ii=0;ii<length;ii++)
        for(int jj=0;jj<length;jj++)
        {aa[ii][jj]=0;
        }
       aa[1][0]=(float)19;
       aa[2][0]=(float)27;
           aa[3][0]=(float)8;
           aa[4][0]=(float)33;
           aa[5][0]=(float)18;
           aa[6][0]=13;
           aa[2][1]=31;
           aa[3][1]=18;
               aa[4][1]=36;
               aa[5][1]=1;
               aa[6][1]=(float)13;
       aa[3][2]=(float)26;
           aa[4][2]=(float)41;
           aa[5][2]=(float)32;
           aa[6][2]=(float)29;
           aa[4][3]=31;
           aa[5][3]=17;
           aa[6][3]=14;
               aa[5][4]=35;
               aa[6][4]=28;
               aa[6][5]=12;
               Vector vc=new Vector();
           vc.addElement("A");
           vc.addElement("B");
            vc.addElement("C");
             vc.addElement("D");
              vc.addElement("E");
              vc.addElement("F");
              vc.addElement("G");
   this.dealarray(aa,vc);
   this.showtree();
   for(int ii=0;ii<Vtree.size();ii++)
   {   tree tr=(tree)Vtree.elementAt(ii);
      // System.out.println(tr.left);
      // System.out.println(tr.right);
     //  System.out.println(tr.resem);
   }
    dlgShowTree.show();
    */
  }

  void jButton3_actionPerformed(ActionEvent e) {
    // added by liusheng3
    algo=(String)this.j07ModelCombo.getSelectedItem();
    //if(dlgShowTree!=null)  dlgShowTree.dispose();
    if(dlgDrawTree!=null)  dlgDrawTree.dispose();
    if(this.Vtree.size()>0)  Vtree.removeAllElements();
    index=1;
    // changed by wxs 20030827 -11
    //dlgDrawTree=new DialogDrawTree(mainfm ,"显示系统树",true);
    dlgDrawTree=new DialogDrawTree(mainfm ,"Show Evolutionary Tree",false,Vtree,this.N,this.algo);
    float [][]data=this.resemblearr(true);
    int len=list2.size();
    N=len;
    if(len<2)  return;
    Vector ColumnName=new Vector();
    for(int ii=0;ii<len;ii++){
      String str=(String)list2.elementAt(ii);
      ColumnName.addElement(str);
    }
    this.dealarray(data,ColumnName);
    // this.showtree();
    // dlgDrawTree.setSize(400,600);
    this.getvtreelength();
    dlgDrawTree.show();

    //dlgDrawTree.drawtree(this.Vtree,len);

    //added by wxs 20030827 -16
    dlgDrawTree.drawtree(this.Vtree,this.N,null);
    String str;
    str="⊿ Similarity -［Tolerance="+this.j07ErrorText.getText()+"］";
    System.out.println(this.j07ErrorText.getText());
    this.mainfm.getCurrentImage().setDoMethod(7,str);
  }

  void jButton7_actionPerformed(ActionEvent e) {
         int nLane;
          nLane=mainfm.getCurrentImage().getLayer1D().getlanenum();
           if(nLane>1){

           for(int ii=1;ii<=nLane/2;ii++){
              String str;
                str="Lane No: "+Integer.toString(ii);
                list1.addElement(str);
                 }
         }
         String []strlist=new String[nLane/2];
         for(int ii=1;ii<=nLane/2;ii++){
           strlist[ii-1]="Lane No: "+Integer.toString(ii);

         }

         this.jList1.setListData(strlist);
}

  void jButton4_actionPerformed(ActionEvent e) {
    String str;
    boolean add=true;
    str=(String)jList1.getSelectedValue();
   if(str==null)   return;
    for(int ii=0;ii<list2.size();ii++)
    { String str1;
      str1=(String)list2.elementAt(ii);
      if(str1==str)
      {add=false;
      break;
      }
        }
        if(add==true){
          list2.add(str);
        jList2.removeAll();
        this.rearrage(list2);
        for(int ii=0;ii<list2.size();ii++)
        { String str3=(String)list2.elementAt(ii);
         // System.out.println("list2"+str3);
        }
          jList2.setListData(list2);

        }
  }

  /***
   /**
    * 作用是把矢量安一定的顺序重排
    * @param list 要重排的矢量
    */


  public void rearrage(Vector list){
  String str,substr;
  int len=list.size();
  //System.out.println(len);
  for(int ii=0;ii<len-1;ii++){

    str=(String)list.elementAt(ii);
    int begin0,end0;
    begin0=str.indexOf("No");
     end0=str.indexOf("Band");
     String strsub;
    strsub=str.substring(begin0+1,end0);
    int v1=Integer.parseInt(strsub);
    for(int jj=ii+1;jj<len;jj++)
    {String str1;
    str1=(String)list.elementAt(jj);
    int begin,end;
    begin=str1.indexOf("No");
     end=str1.indexOf("Band");
     String str2;
    str2=str1.substring(begin+1,end);

    int v2=Integer.parseInt(str2);
   // System.out.println("comp"+v1);
   // System.out.println(v2);
    if(v1>v2)  {
      list.removeElementAt(ii);
      list.insertElementAt(str1,ii);
      list.removeElementAt(jj);
      list.insertElementAt(str,jj);
      //System.out.println("comp"+str);
   // System.out.println(str1);
    }
    }
  }


}

  void jButton5_actionPerformed(ActionEvent e) {
    String str;
          str=(String)jList2.getSelectedValue();
         if(str==null)   return;
          list2.remove(str);
          jList2.removeAll();
    jList2.setListData(list2);
  }

  void jButton6_actionPerformed(ActionEvent e) {
            int nLane;
             nLane=mainfm.getCurrentImage().getLayer1D().getlanenum();
             list2.removeAllElements();
             if(nLane>1){

            for(int ii=1;ii<=nLane/2;ii++){
                   String str;
                   str="No Lane: "+Integer.toString(ii);
                   list2.addElement(str);
                    }
              }
             String []strlist=new String[nLane/2];
            for(int ii=1;ii<=nLane/2;ii++){
             strlist[ii-1]="No Lane: "+Integer.toString(ii);

            }
           this.jList2.setListData(strlist);
  }
  //**
  /**
   * 取得相似性数组
   * @return
   */
  public float [][]resemblearr(boolean isdistance){
    int enderr;
    String strerr=this.j07ErrorText.getText();
    String coefficient=(String)j07CofiCombo.getSelectedItem();
    enderr=strerr.indexOf("%");
    String strsuberr;
    strsuberr=strerr.substring(0,enderr);
    float err=(float)Integer.parseInt(strsuberr)/100;
    int len=list2.size();
    if(len<2) return null;
    float [][]result=new float[len][len];
    for(int ii=0;ii<list2.size();ii++){
    String str=(String)list2.elementAt(ii);
    int begin0,end0;
    begin0=str.indexOf("No");
    end0=str.indexOf("Band");
    String strsub;
    strsub=str.substring(begin0+1,end0);
    int iii=Integer.parseInt(strsub);
    for(int jj=0;jj<list2.size();jj++){
         String str1=(String)list2.elementAt(jj);
        int begin1,end1;
         begin1=str1.indexOf("No");
         end1=str1.indexOf("Band");
         String strsub1;
         strsub1=str1.substring(begin1+1,end1);
        int jjj=Integer.parseInt(strsub1);
        if(ii==jj)  result[ii][jj]=1;
        if(ii<jj)    result[ii][jj]=0;
        if(ii>jj)
        { int resem= mainfm.getCurrentImage().getLayer1D().getresemblelinenum(iii,jjj,err);
         int Na=mainfm.getCurrentImage().getLayer1D().getlinenum(iii);
         int Nb=mainfm.getCurrentImage().getLayer1D().getlinenum(jjj);
        if(coefficient=="Nei&Li(Dice) Coefficient")
           { float rr=(float)resem/(Na+Nb);
             result[ii][jj]= rr=2*rr;
           }
          if(coefficient=="Jaccard Coefficient" ) {
            result[ii][jj]=(float)resem/(Na+Nb-resem);
          }

            if(coefficient=="Cosine Coefficient" )
              {result[ii][jj]= (float)(resem/(Math.sqrt((double)(Na*Nb))));
              }
            if(coefficient== "Overlap Coefficient" )
              {int aaaa=Math.min(Na,Nb);
               result[ii][jj]=(float)resem/aaaa;
              }

        }
       // System.out.println("result"+result[ii][jj]);
          }
    }
    if(isdistance==true){
      for(int ii=0;ii<len;ii++){
        for(int jj=0;jj<len;jj++){
          if(ii>=jj) {
           result[ii][jj]=1-result[ii][jj];
          }
        }
      }
    }
    return result;
  }

  /*
  void jButton10_actionPerformed(ActionEvent e) {
    DialogReport dlg=new DialogReport(this.mainfm,"",true);
    if(list2.size()<2)  return;
    float [][]data=resemblearr(true);
    int len=list2.size();
    int datalen=data.length;
    String []ColumnName=new String[len];
    String [][]newData=new String[datalen][datalen];
    for(int ii=0;ii<len;ii++){
    ColumnName[ii]=(String)list2.elementAt(ii);
    }
    for(int ii=0;ii<datalen;ii++){
      for(int jj=0;jj<datalen;jj++){
       String str=String.valueOf(data[ii][jj]);
       newData[ii][jj]=str;
      // System.out.println(str);
      }
    }
    //dlg.initPara(newData,ColumnName);
    dlg.setContext(this.getTitle()+"结果" ,this.getTitle() +"结果",ColumnName,newData);
    dlg.show() ;
  }
  */

  void jCheckBox5_actionPerformed(ActionEvent e) {
    DialogReport dlg=new DialogReport(this.mainfm,"Show graph",true);
    if(list2.size()<2)  return;
    float [][]data=resemblearr(false);
    int len=list2.size();
    int datalen=data.length;
    String []ColumnName=new String[len];
    String [][]newData=new String[datalen][datalen];
    for(int ii=0;ii<len;ii++){
      ColumnName[ii]=(String)list2.elementAt(ii);
    }
    for(int ii=0;ii<datalen;ii++){
      for(int jj=0;jj<datalen;jj++){
         String str=String.valueOf(data[ii][jj]);
         //modified by wxs
         //newData[ii][jj]=str;
         newData[ii][jj]=this.setPrecision(str,6) ;
      }
    }
    //dlg.initPara(newData,ColumnName);
    String []text=new String[1];
    text[0]=this.getTitle() +"Result";
    dlg.setContext(this.getTitle()+" result " ,text,ColumnName,newData,true);
    dlg.show() ;
  }

  /*
  void jCheckBox6_actionPerformed(ActionEvent e) {
    DialogReport dlg=new DialogReport(this.mainfm,"显示图表",true);
    if(list2.size()<2)  return;
     float [][]data=resemblearr(true);
     int len=list2.size();
     int datalen=data.length;
     String []ColumnName=new String[len];
     String [][]newData=new String[datalen][datalen];
     for(int ii=0;ii<len;ii++){
     ColumnName[ii]=(String)list2.elementAt(ii);
     }
     for(int ii=0;ii<datalen;ii++){
         for(int jj=0;jj<datalen;jj++){
            String str=String.valueOf(data[ii][jj]);
            //modified by wxs
            //newData[ii][jj]=str;
            newData[ii][jj]=this.setPrecision(str,6) ;
     }
    }
    dlg.setContext(this.getTitle()+"结果" ,this.getTitle() +"结果",ColumnName,newData);
    dlg.show() ;
  }
  */
  /*
  void jButton11_actionPerformed(ActionEvent e) {
    dlgShowTree=new DialogShowTree(mainfm ,"显示系统树",true);
    float [][]data=this.resemblearr(true);
    int len=list2.size();
    Vector ColumnName=new Vector();
    for(int ii=0;ii<len;ii++){
    String str=(String)list2.elementAt(ii);
    ColumnName.addElement(str);
    }
    this.dealarray(data,ColumnName);
    this.showtree();
    dlgShowTree.show();
  }
  */

  void jCheckBox2_actionPerformed(ActionEvent e) {
      //if(dlgShowTree!=null)  dlgShowTree.dispose();
      if(dlgDrawTree!=null)  dlgDrawTree.dispose();
      if(this.Vtree.size()>0)  Vtree.removeAllElements();
       index=1;
       dlgDrawTree=new DialogDrawTree(mainfm ,"Show Evolution Tree",true,Vtree,this.N,this.algo);
      float [][]data=this.resemblearr(true);
      int len=list2.size();
      if(len<2)  return;
       Vector ColumnName=new Vector();
      for(int ii=0;ii<len;ii++){
         String str=(String)list2.elementAt(ii);
         ColumnName.addElement(str);
       }
      this.dealarray(data,ColumnName);
     // this.showtree();
      // dlgDrawTree.setSize(400,600);
       N=len;
       dlgDrawTree.show();
     //  dlgDrawTree.drawtree(this.Vtree,len);
  }


  void jSlider1_stateChanged(ChangeEvent e) {
    this.j07ErrorText.setText(String.valueOf(jSlider1.getValue() )+"%"  );
  }

  private String setPrecision(String str,int bits){
    if(str.length() >bits) str=str.substring(0,bits);
    if(str.indexOf(".")==str.length() -1)  str=str+"0";
    return str;
  }

  void jButton8_actionPerformed(ActionEvent e) {
    // changed by wxs 20030827 -13
    //DialogReport dlg=new DialogReport(this.mainfm,"显示图表",true);
    DialogReport dlg=new DialogReport(this.mainfm,"Show Graph",false);
    if(list2.size()<2)  return;
    float [][]data=resemblearr(false);
    int len=list2.size();
    int datalen=data.length;
    String []ColumnName=new String[len];
    String [][]newData=new String[datalen][datalen];
    for(int ii=0;ii<len;ii++){
      ColumnName[ii]=(String)list2.elementAt(ii);
    }
    for(int ii=0;ii<datalen;ii++){
      for(int jj=0;jj<datalen;jj++){
        String str=String.valueOf(data[ii][jj]);
        //modified by wxs
        //newData[ii][jj]=str;
        newData[ii][jj]=this.setPrecision(str,6) ;
      }
    }
    //dlg.initPara(newData,ColumnName);
    String []text=new String[1];
    text[0]=this.getTitle() +"Result";
    dlg.setContext(this.getTitle()+" result " ,text,ColumnName,newData,true);
    dlg.show() ;

    //added by wxs 20030827 -18
    String str;
    str="⊿ similarity -［tolerance="+this.j07ErrorText.getText()+"］";
    this.mainfm.getCurrentImage().setDoMethod(7,str);
  }

  void jButton9_actionPerformed(ActionEvent e) {
    // changed by wxs 20030827 -12
    //DialogReport dlg=new DialogReport(this.mainfm,"显示图表",true);
    DialogReport dlg=new DialogReport(this.mainfm,"Show Graph",false);
    if(list2.size()<2)  return;
    float [][]data=resemblearr(true);
    int len=list2.size();
    int datalen=data.length;
    String []ColumnName=new String[len];
    String [][]newData=new String[datalen][datalen];
    for(int ii=0;ii<len;ii++){
      ColumnName[ii]=(String)list2.elementAt(ii);
    }
    for(int ii=0;ii<datalen;ii++){
      for(int jj=0;jj<datalen;jj++){
        String str=String.valueOf(data[ii][jj]);
        newData[ii][jj]=this.setPrecision(str,6);
      }
    }
    String []text=new String[1];
    text[0]=this.getTitle() +"Result";
    dlg.setContext(this.getTitle()+" result " ,text,ColumnName,newData,true);
    dlg.show() ;

    //added by wxs 20030827 -17
    String str;
    str="⊿ similarity -［tolerance="+this.j07ErrorText.getText()+"%］";
    this.mainfm.getCurrentImage().setDoMethod(7,str);
  }

  public void getlane(){
    int nLane;
          nLane=mainfm.getCurrentImage().getLayer1D().getlanenum();
           if(nLane>1){

           for(int ii=1;ii<=nLane/2;ii++){
              String str;
                str="Lane No: "+Integer.toString(ii);
                list1.addElement(str);
                 }
         }
         String []strlist=new String[nLane/2];
         for(int ii=1;ii<=nLane/2;ii++){
           strlist[ii-1]="Lane No: "+Integer.toString(ii);

         }

         this.jList1.setListData(strlist);
  }
  void j07ModelCombo_actionPerformed(ActionEvent e) {

  }

  // added by wxs 20030823 -07
  private void setInterfaceVFromFile(){
    String pathName=this.mainfm.getSystemDir()+"\\database\\"+
                    this.mainfm.getCurrentParaPre()+".1d";
    try{
      File file=new File(pathName);
      if(file.exists() ==true){
        ObjectInputStream in= new ObjectInputStream(new FileInputStream(file));
        int num=in.readInt();
        for(int ii=0;ii<num;ii++){
          Settings st=new Settings();
          st=(Settings)in.readObject();
          if(st.type.substring(0,2).equals("07"))  set.addElement(st);
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
    int exeMethod=this.mainfm.getExeMethod();
    boolean bln;
    int it;
    String str;
    bln=getSettings("07Need").paraBoolean;
    if(bln==true){
      if(exeMethod==2)  this.isVisible =getSettings("07Visible").paraBoolean;
      else this.isVisible=true;
      if(getSettings("07Selall").paraBoolean==true) jButton6_actionPerformed(null);
      this.j07ErrorText.setText(getSettings("07Error").paraString+"%");
      jSlider1.setValue(Integer.parseInt(getSettings("07Error").paraString));
      this.j07CofiCombo.setSelectedIndex(getSettings("07Cofi").paraInt);
      this.j07ModelCombo.setSelectedIndex(getSettings("07Model").paraInt);
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
    if(this.mainfm.getExeMethod()!=2) return;
    if(this.isVisible==true) return;
    String str;
    this.mainfm.getCurrentImage().setTitle("Similarity Comparison......");
    if(getSettings("07Tree").paraBoolean==true) jButton3_actionPerformed(null);
    if(getSettings("07Dist").paraBoolean==true) jButton9_actionPerformed(null);
    if(getSettings("07Same").paraBoolean==true) jButton8_actionPerformed(null);
    this.mainfm.getCurrentImage().setTitle("Finished");
    // donext
    this.mainfm.setWizardWinLocation(this.getLocation() ) ;
    this.mainfm.doStep(true)  ;
  }
}
