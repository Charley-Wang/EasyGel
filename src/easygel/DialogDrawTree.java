package easygel;

import java.awt.*;
import javax.swing.*;
import java.util.Vector;
import easygel.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import javax.swing.border.*;
import easygel.image.*;
import ij.process.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogDrawTree extends JDialog {
  private Vector VDrawTree=new Vector();
  private FrameMain frm;
  private JPanel jPanel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private Vector VTree=new Vector();
  private int Vpos;
  int N;
  private BorderLayout borderLayout1 = new BorderLayout();
  private JPanel jPanel2 = new JPanel();
  private JButton jButton3 = new JButton();
  private JButton jButton4 = new JButton();
  private TitledBorder titledBorder1;
  private String algorithm;
  private GridLayout gridLayout1 = new GridLayout();

  public DialogDrawTree(Frame frame, String title, boolean modal,Vector vTree,int NN,String algo) {
    super(frame, title, modal);
    try {
      // modified by wxs 20030827
      frm=(FrameMain)frame;
      //VTree=frm.dialogResemble.Vtree;
      this.VTree =vTree;
      //N=frm.dialogResemble.N;
      N=NN;
      //algorithm=frm.dialogResemble.algo;
      this.algorithm=algo;
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogDrawTree() {
    //this(null, "", false);
  }
  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    this.getContentPane().setBackground(Color.lightGray);
    this.getContentPane().setLayout(borderLayout1);
    jPanel1.setLayout(xYLayout1);
    jPanel1.setBackground(Color.white);
    jPanel1.setMinimumSize(new Dimension(700, 500));
    jPanel1.setPreferredSize(new Dimension(700, 500));
    jPanel2.setBorder(titledBorder1);
    jPanel2.setMinimumSize(new Dimension(700, 500));
    jPanel2.setPreferredSize(new Dimension(10, 40));
    jPanel2.setLayout(gridLayout1);
    jButton3.setText("Save");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jButton4.setText("Exit");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton4_actionPerformed(e);
      }
    });
    this.getContentPane().add(jPanel2, BorderLayout.SOUTH);
    jPanel2.add(jButton3, null);
    jPanel2.add(jButton4, null);
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
  }

  public class DTree{
    String left;
    String right;
    String strtop;
    Point PTtop;
    Point PTleft;
    Point PTright;
  }
  //Liusheng add at 8
  private void drawline(Point lt,Point rt,Point tp,String strleft,String strright,String left,String right){
    Graphics g;
       //g=this.getContentPane().getGraphics();
       g=this.jPanel1.getGraphics();
       Graphics2D g2d;
       g2d=(Graphics2D)g;
       g2d.setColor(new Color(255,0,0));
       if(left.length()<7)
         g2d.drawLine(lt.x+5,lt.y,tp.x,lt.y);
       else
         g2d.drawLine(lt.x,lt.y,tp.x,lt.y);
       g2d.drawLine(tp.x,lt.y,tp.x,rt.y);
       if(right.length()<7)
       g2d.drawLine(tp.x,rt.y,rt.x+5,rt.y);
       else
       {
       g2d.drawLine(tp.x,rt.y,rt.x,rt.y);
       }

       if(tp.x!=lt.x||(left.length()<7))
       g2d.drawString(strleft,(tp.x*3+lt.x)/4,lt.y);

       if(tp.x!=rt.x||(right.length()<7))
       g2d.drawString(strright,(tp.x*3+rt.x)/4,rt.y);
  }
  //end

  private void drawstring(Point pt,String str,boolean isSave,ImageProcessor ip){
    if(isSave==false){
      Graphics g;
      g=this.jPanel1.getGraphics();
      Graphics2D g2d;
      g2d=(Graphics2D)g;
      g2d.setColor(new Color(255,0,0));
      g2d.drawString(str,pt.x+20,pt.y+3);
    }
    else{
      ip.setColor(Color.red);
      ip.drawString(str,pt.x+20,pt.y+3);
    }
  }

  //Liusheng add at 8
  public Tree getchildtree(String parent){
    for(int ii=0;ii<VTree.size();ii++){
      String par;
      Tree tr=(Tree)VTree.elementAt(ii);
      par=tr.parent;
      if(parent.equals(par)){
        return tr;
      }
    }
    return null;
  }
  //Liusheng add at 8
  public int getbottompos(String parent,Vector vt){
    String str;
    str=parent;
    while(true){
      Tree tr=this.getchildtree(str);
      if(tr.ltpt.y<tr.rtpt.y){
        str=tr.right;
        if(str.length()<7)
          return tr.rtpt.y;
      }
      else
      { str=tr.left;
      if(str.length()<7)
          return tr.rtpt.y;
      }
    }
  }

  //Liusheng3 add
  public Tree getParentTree(String parent){
    for(int ii=0;ii<VTree.size();ii++){
      String par;
      Tree tr=(Tree)VTree.elementAt(ii);
      par=tr.left;
      if(parent.equals(par)){
        return tr;
      }
      par=tr.right;
      if(parent.equals(par)){
        return tr;
      }
   }
   return null;
  }

  //Liusheng add at 8
  public Point gettreepos(Tree tr, float depth,boolean isSave,ImageProcessor ip){
    int Width, Height;
    Width=this.getWidth();
    Height=this.getHeight();
    if(Width<Height-60) {
      Width-=60;
      Height=Width;
    }
    else {
      Height-=60;
      Width=Height;
    }
    if(tr.left.length()>6){
      Tree trleft=this.getchildtree(tr.left);
      tr.ltpt=gettreepos(trleft,depth,isSave,ip);
    }
    else {
      //Liusheng3 add
      tr.ltpt=new Point(Width*7/8,Vpos);
      this.drawstring(tr.ltpt,tr.left,isSave,ip);
      Vpos+=Height*7/(8*N);
    }
    if(tr.right.length()>6){
      Tree trright=this.getchildtree(tr.right);
      tr.rtpt=gettreepos(trright,depth,isSave,ip);
    }
    else {
      tr.rtpt=new Point(Width*7/8,Vpos);
      this.drawstring(tr.rtpt,tr.right,isSave,ip);
      Vpos+=Height*7/(8*N);
    }

    float resemble=Math.abs(tr.resem);
    if(this.algorithm!="Neighbor Joining")
      tr.tppt=new Point((int)(Width*7/8-(3*Width*resemble/(4*depth))),(tr.ltpt.y+tr.rtpt.y)/2);
    else
      tr.tppt=new Point((int)(tr.tppt.x),(tr.ltpt.y+tr.rtpt.y)/2);
    return tr.tppt;
    //End
  }

  //Liusheng3 add
  public void settoppos(){
    int wid=this.getWidth();
    int Height=this.getHeight();
    if(wid<Height-60){
      wid-=60;
      Height=wid;
    }
    else{
      Height-=60;
      wid=Height;
    }
    for(int ii=0;ii<VTree.size();ii++){
      Tree tr=(Tree)VTree.elementAt(ii);
      String left=tr.left;
      int leftN=0;
      while(tr!=null){
        leftN++;
        left=tr.left;
        if(left.length()<7) break;
        tr=this.getchildtree(left);
      }
      tr=(Tree)VTree.elementAt(ii);
      String right=tr.right;
      int rightN=0;
      while(tr!=null){
        rightN++;
        right=tr.right;
        if(right.length()<7) break;
        tr=this.getchildtree(right);
     }
     int n=leftN;
     if(leftN<rightN)  n=rightN;
     tr=(Tree)VTree.elementAt(ii);
     int nn=getTreeHNodeNum();
     tr.tppt.x=wid*3/4-3*wid*n/(5*nn);
    }
  }

  //Liusheng3 add
  public void getNBbotpos(){
    int wid=this.getWidth();
    for(int ii=0;ii<VTree.size();ii++){
      Tree tr=(Tree)VTree.elementAt(ii);
      if(tr.left.length()<7){
        String str=tr.left;
        tr.ltpt.x=wid/8+getNBBotdis(str);
    //  System.out.println(tr.ltpt);
    }
    if(tr.right.length()<7){
      String str=tr.right;
      tr.ltpt.x=wid/8+getNBBotdis(str);
  //    System.out.println(tr.rtpt);
    }
  }
}
public int getNBBotdis(String str){
  int length=0;
  Tree trtop=(Tree)VTree.elementAt(VTree.size()-1);
  int Width=this.getWidth();
  float depth=this.getNBtopdis();
  System.out.println("dep         " +depth);
  System.out.println("str"+str);
  Tree tr=this.getParentTree(str);
  while(tr!=null){
    if(str.equals(tr.left)){
      // System.out.println(tr.left);
      length+=3*Width*(tr.leftdis)/(4*depth);
    //  System.out.println(tr.leftdis);
    }
    else
    {
      length+=3*Width*(tr.rightdis)/(4*depth);
   //   System.out.println(tr.rightdis);
    }
    str=tr.parent;
    String par=tr.parent;
   // System.out.println(par);
    tr=this.getParentTree(par);
 //   System.out.println(length);
  }
  System.out.println("length");
  System.out.println(length);
  return (length+20);
}

public float getNBtopdis(){
   float length=0;
   Tree tr=(Tree)VTree.elementAt(VTree.size()-1);
   while(tr!=null){
     length+=tr.leftdis;
     tr=this.getchildtree(tr.left);
   }
   return length;
}

  //Liusheng3 add
  public int  getTreeHNodeNum(){
    int len=0;
    for(int ii=0;ii<VTree.size();ii++){
      Tree tr=(Tree)VTree.elementAt(ii);
       int L=0;
      if(tr.left.length()<7){

        String par=tr.parent;
       while(true){
          L++;
         Tree trsub=this.getParentTree(par);
         if(trsub==null) break;
         par=trsub.parent;
        }
      }
      if(len<L) len=L;
    }
    return len;
  }
  //End

  //Liusheng add at 8
  public void drawtree(Vector VT,int NN,ImageProcessor ip){
     int Width,Height,Xstep,Ystep;
      Width=300;
      Height=300;
      Width=this.getWidth();
      Height=this.getHeight();
      if(Width<Height-60)
      {
        Width-=60;
        Height=Width;
      }
      else
      {
        Height-=60;
        Width=Height;
      }
        float depth;
      Tree trtop=(Tree)VT.elementAt(VT.size()-1);
      depth=trtop.resem;
      // modified by wxs 20030827-
      //N=frm.dialogResemble.N;
      N=NN;
      System.out.println("Ndfd"+N);
      if(N==0) return;
      this.Vpos=Height*7/(8*N);
      if(this.algorithm=="Neighbor Joining"){
        // this.getNBbotpos();
        this.settoppos();
      }
      this.gettreepos(trtop,depth,false,ip);

      for(int ii=0;ii<VT.size();ii++){
         Tree tr=(Tree)VT.elementAt(ii);
         float res=tr.resem;
         String strleft=new String();
         String strright=new String();
         if(this.algorithm=="Neighbor Joining"){
          strleft=String.valueOf(tr.leftdis);
          strright=String.valueOf(tr.rightdis);
         }
         else
         {
           strleft=String.valueOf(res);
           strright=String.valueOf(res);
         }
         String left,right;
         left=tr.left;
         right=tr.right;

        this.drawline(tr.ltpt,tr.rtpt,tr.tppt,strleft,strright,left,right);

        //JOptionPane.showMessageDialog(null,str,"图像及元素均保存",JOptionPane.INFORMATION_MESSAGE);
      }
  }

  public void paint(Graphics g){
    super.paint(g);      // very important!!!!
    this.drawtree(frm.dialogResemble.Vtree,this.N,null);
  }

  void jButton3_actionPerformed(ActionEvent e) {
    //this.drawtree(frm.dialogResemble.Vtree,this.N);
    save();
  }

  private void save(){
    int format=OpenFile.JPEG;
    String pathName[]=ImageSaver.selectFileToSave(format,this.frm,"选择保存文件名称");
    int width=getWidth();
    int height=getHeight();
    OpenFileInformation infor=ImageSaver.createIPFromTmp(format,frm,
        true,Color.white,width,height,pathName[0],pathName[1]);
    ImageProcessor ip=infor.ip;
    this.saveTree(frm.dialogResemble.Vtree,this.N,ip);
    ImageSaver.saveIPAsImage(format,infor.fileInfo.directory,
                             infor.fileInfo.fileName,infor.ip,
                             infor.fileInfo,infor.bmp,true,this.frm);
  }

  public void saveTree(Vector VT,int NN,ImageProcessor ip){
    int Width,Height;
    int Xstep,Ystep;
    Width=300;
    Height=300;
    Width=this.getWidth();
    Height=this.getHeight();
    if(Width<Height-60){
      Width-=60;    Height=Width;
    }
    else {
      Height-=60;   Width=Height;
    }
    float depth;
    Tree trtop=(Tree)VT.elementAt(VT.size()-1);
    depth=trtop.resem;
    N=NN;
    if(N==0) return;
    this.Vpos=Height*7/(8*N);
    if(this.algorithm=="Neighbor Joining") this.settoppos();
    this.gettreepos(trtop,depth,true,ip);

    for(int ii=0;ii<VT.size();ii++){
      Tree tr=(Tree)VT.elementAt(ii);
      float res=tr.resem;
      String strleft=new String();
      String strright=new String();
      if(this.algorithm=="Neighbor Joining"){
        strleft=String.valueOf(tr.leftdis);
        strright=String.valueOf(tr.rightdis);
      }
      else {
        strleft=String.valueOf(res);
        strright=String.valueOf(res);
      }
      String left,right;
      left=tr.left;
      right=tr.right;
      this.saveLine(tr.ltpt,tr.rtpt,tr.tppt,strleft,strright,left,right,ip);
    }
  }

  private void saveLine(Point lt,Point rt,Point tp,String strleft,
                        String strright,String left,String right,ImageProcessor ip){
    ip.setColor(new Color(255,0,0));
    if(left.length()<7)  ip.drawLine(lt.x+5,lt.y,tp.x,lt.y);
    else                 ip.drawLine(lt.x,lt.y,tp.x,lt.y);
    ip.drawLine(tp.x,lt.y,tp.x,rt.y);
    if(right.length()<7) ip.drawLine(tp.x,rt.y,rt.x+5,rt.y);
    else                 ip.drawLine(tp.x,rt.y,rt.x,rt.y);
    if(tp.x!=lt.x||(left.length()<7))   ip.drawString(strleft,(tp.x*3+lt.x)/4,lt.y);
    if(tp.x!=rt.x||(right.length()<7))  ip.drawString(strright,(tp.x*3+rt.x)/4,rt.y);
  }

  void jButton4_actionPerformed(ActionEvent e) {
     this.dispose();
  }
}

