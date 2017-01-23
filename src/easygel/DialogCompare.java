package easygel;
import javax.swing.table.*;
import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import javax.swing.border.*;
import java.awt.event.*;
import easygel.*;
import java.util.Vector;
import java.lang.Object;
import javax.swing.event.*;
// added by wxs 20030823 -01
import easygel.setting.*;
import java.io.*;
import java.util.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogCompare extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JLabel jLabel1 = new JLabel();
  private JList jList1 = new JList();
  private JLabel jLabel2 = new JLabel();
  private JList jList2 = new JList();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private JLabel jLabel3 = new JLabel();
  private JTextField j09ErrorText = new JTextField();
  private JLabel jLabel4 = new JLabel();
  private JComboBox j09MainlaneCombo = new JComboBox();
  private JLabel jLabel5 = new JLabel();
  private JLabel jLabel6 = new JLabel();
  private JList jList3 = new JList();
  private JList jList4 = new JList();
  private JButton jButton3 = new JButton();
  private JButton jButton4 = new JButton();
  private TitledBorder titledBorder1;
  private TitledBorder titledBorder2;
  private TitledBorder titledBorder3;
  private FrameMain frameMain;
  public Vector list1;
  private Vector list3;
  public  Vector list2;
  public Vector list4;
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JScrollPane jScrollPane2 = new JScrollPane();
  private JScrollPane jScrollPane3 = new JScrollPane();
  private JScrollPane jScrollPane4 = new JScrollPane();
  private JScrollBar jScrollBar1 = new JScrollBar();
 // private JTable jTable1 = new JTable();
  private TitledBorder titledBorder4;
  String[] colNames={"Name","Age"};
  String[][] dataTable={{"Wxs1","25"},{"Wxr","30"}};

  private DefaultTableModel dt=new DefaultTableModel(dataTable,colNames);
  private JButton jButton11 = new JButton();
  private BorderLayout borderLayout1 = new BorderLayout();
  private JButton jButton8 = new JButton();
  private JButton jButton6 = new JButton();
  private JButton jButton5 = new JButton();
  private JButton jButton10 = new JButton();
  private JButton jButton9 = new JButton();
  private JButton jButton7 = new JButton();
  private JButton jButton12 = new JButton();

  // added by wxs 20030823 -02
  private Vector set=new Vector();
  private boolean isVisible;
  private boolean isInit;

  public DialogCompare(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    frameMain=(FrameMain)frame;
    // added by wxs 20030823 -03
    this.isInit=true;
    try {
      list1=new Vector();
      list3=new Vector();
      list2=new Vector();
      list4=new Vector();
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogCompare() {
    this(null, "", false);
    list1=new Vector();
    list3=new Vector();
    list2=new Vector();
    list4=new Vector();
  }

  //Liusheng add at 8
  private void Intialize(){
    int nLane;
    // frameMain.getCurrentImage().getLayer1D
    nLane=frameMain.getCurrentImage().getLayer1D().getlanenum();
    if(nLane>1){
      j09MainlaneCombo.removeAllItems();
      j09MainlaneCombo.addItem("Virtual Lane");
      for(int ii=1;ii<=nLane/2;ii++){
        String str;
        str="Lane No: "+Integer.toString(ii);
        list1.addElement(str);
        list2.addElement(str);
        j09MainlaneCombo.addItem(str);
      }
      String []strlist=new String[nLane/2];
      for(int ii=1;ii<=nLane/2;ii++){
        strlist[ii-1]="Lane No: "+Integer.toString(ii);
      }
      this.jList1.setListData(strlist);
      this.jList2.setListData(strlist);
    }
    String str,str1;
    str=this.j09ErrorText.getText();
    int end=str.indexOf("%");
    str1=str.substring(0,end);
    // System.out.println(str1+"%");
    int aa=Integer.parseInt(str1);
    float err=(float)aa/100;
    // System.out.println("err"+err);
    frameMain.getCurrentImage().getLayer1D().getvirtuallane(err);
    int laneN=1;
    int nline=0;
    this.j09MainlaneCombo.setSelectedIndex(0);
    if(this.j09MainlaneCombo.getSelectedIndex()==0)
      nline=frameMain.getCurrentImage().getLayer1D().virtlane.size();
    String []strl=new String[nline];
    list3.removeAllElements();
    for(int ii=1;ii<=nline;ii++){
      strl[ii-1]="Band No: "+Integer.toString(ii);
      list3.addElement(strl[ii-1]);
      list4.addElement(strl[ii-1]);
    }
    jList3.removeAll();
    jList3.setListData(strl);
    jList4.removeAll();
    jList4.setListData(list4);
  }
  //End


  private void jbInit() throws Exception {
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    titledBorder4 = new TitledBorder("");
    panel1.setLayout(xYLayout1);
    jLabel1.setText("All Lanes");
    jList1.setBorder(BorderFactory.createLoweredBevelBorder());
    jList1.setToolTipText("");
    jList1.setVisibleRowCount(4);
    jLabel2.setText("Refer Lane");
    jList2.setBorder(BorderFactory.createLoweredBevelBorder());
    jButton1.setMargin(new Insets(0, 0, 0, 0));
    jButton1.setText(">>");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setMargin(new Insets(0, 0, 0, 0));
    jButton2.setText("≮");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jLabel3.setText("Error Allowance-Rf");
    j09ErrorText.setEditable(false);
    j09ErrorText.setText("3%");
    jLabel4.setText("Major Matching Lane");
    jLabel5.setText("All Bands");
    jLabel6.setText("Refer Band");
    jList3.setBorder(BorderFactory.createLoweredBevelBorder());
    jList4.setBorder(BorderFactory.createLoweredBevelBorder());
    jButton3.setMargin(new Insets(0, 0, 0, 0));
    jButton3.setText(">>");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jButton4.setMargin(new Insets(0, 0, 0, 0));
    jButton4.setText("≮");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton4_actionPerformed(e);
      }
    });
    j09MainlaneCombo.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        j09MainlaneCombo_focusLost(e);
      }
      public void focusGained(FocusEvent e) {
        j09MainlaneCombo_focusGained(e);
      }
    });
    jScrollBar1.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
      public void adjustmentValueChanged(AdjustmentEvent e) {
        jScrollBar1_adjustmentValueChanged(e);
      }
    });
    panel1.setForeground(Color.white);
    panel1.setMinimumSize(new Dimension(442, 265));
    panel1.setPreferredSize(new Dimension(442, 265));
    j09MainlaneCombo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        j09MainlaneCombo_actionPerformed(e);
      }
    });
    jButton11.setMargin(new Insets(0, 0, 0, 0));
    jButton11.setText("Select All");
    jButton11.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton11_actionPerformed(e);
      }
    });
    this.getContentPane().setLayout(borderLayout1);
    jScrollBar1.setOrientation(JScrollBar.HORIZONTAL);
    jScrollBar1.setValue(3);
    jScrollBar1.setVisibleAmount(0);
    jButton8.setMargin(new Insets(0, 0, 0, 0));
    jButton8.setText("OK");
    jButton8.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton8_actionPerformed(e);
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
    jButton5.setMargin(new Insets(0, 0, 0, 0));
    jButton5.setText("Compare");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton5_actionPerformed(e);
      }
    });
    jButton10.setMargin(new Insets(0, 0, 0, 0));
    jButton10.setText("Show Matrix");
    jButton10.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton10_actionPerformed(e);
      }
    });
    jButton9.setMargin(new Insets(0, 0, 0, 0));
    jButton9.setText("Select Lane");
    jButton9.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton9_actionPerformed(e);
      }
    });
    jButton7.setMargin(new Insets(0, 0, 0, 0));
    jButton7.setText("Select All");
    jButton7.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton7_actionPerformed(e);
      }
    });
    jButton12.setMargin(new Insets(0, 0, 0, 0));
    jButton12.setText("Compare All");
    jButton12.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton12_actionPerformed(e);
      }
    });
    panel1.add(jButton8,    new XYConstraints(273, 232, 73, 25));
    panel1.add(jButton6,    new XYConstraints(90, 232, 73, 25));
    panel1.add(jScrollPane2,         new XYConstraints(194, 28, 90, 86));
    panel1.add(jButton9,  new XYConstraints(133, 30, 48, 19));
    panel1.add(jButton11,  new XYConstraints(133, 51, 48, 19));
    panel1.add(jButton2,  new XYConstraints(133, 93, 48, 19));
    panel1.add(jButton1,  new XYConstraints(133, 72, 48, 19));
    panel1.add(jLabel2,       new XYConstraints(217, 7, 60, -1));
    panel1.add(jScrollPane1,     new XYConstraints(28, 30, 90, 82));
    panel1.add(jLabel1,    new XYConstraints(50, 7, 63, 23));
    panel1.add(jScrollPane4, new XYConstraints(194, 136, 90, 82));
    panel1.add(jLabel6,     new XYConstraints(216, 118, 56, 16));
    panel1.add(jLabel5,                                     new XYConstraints(49, 117, 64, 17));
    panel1.add(jScrollPane3, new XYConstraints(27, 136, 90, 82));
    panel1.add(jScrollBar1,    new XYConstraints(310, 110, 109, 18));
    panel1.add(j09ErrorText, new XYConstraints(309, 85, 109, 18));
    panel1.add(jLabel3, new XYConstraints(331, 62, 86, 22));
    panel1.add(jLabel4,  new XYConstraints(326, 17, 64, 21));
    panel1.add(j09MainlaneCombo, new XYConstraints(309, 37, 109, 18));
    panel1.add(jButton4, new XYConstraints(134, 189, 48, 19));
    panel1.add(jButton3,  new XYConstraints(133, 164, 48, 19));
    panel1.add(jButton7,     new XYConstraints(134, 137, 48, 20));
    panel1.add(jButton5,     new XYConstraints(326, 167, 75, 20));
    panel1.add(jButton12,      new XYConstraints(326, 137, 75, 20));
    panel1.add(jButton10,          new XYConstraints(326, 196, 75, 20));
    jScrollPane3.getViewport().add(jList3, null);
    jScrollPane4.getViewport().add(jList4, null);
    jScrollPane1.getViewport().add(jList1, null);
    jScrollPane2.getViewport().add(jList2, null);
    this.getContentPane().add(panel1, BorderLayout.WEST);

    this.j09MainlaneCombo.addItem("Virtual Lane");

    if(this.frameMain .getExeMethod() ==0){
      this.jButton8 .setText("OK");
      this.jButton6 .setText("Cancel");
    }
    else{
      this.setLocation(this.frameMain .getWizardWinLocation()) ;
      this.jButton8 .setText("Next▼");
      this.jButton6 .setText("Last▲") ;
    }

    j09MainlaneCombo.addFocusListener(new java.awt.event.FocusAdapter() {
      public void focusLost(FocusEvent e) {
        j09MainlaneCombo_focusLost(e);
      }
    });
    this.Intialize();

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

  void jButton8_actionPerformed(ActionEvent e) {
    this.dispose() ;
    // modified by wxs 20030826 -12
    if(this.frameMain .getExeMethod() >=1){
      this.frameMain .setWizardWinLocation(this.getLocation() ) ;
      this.frameMain.doStep(true);
    }
  }

  void jButton9_actionPerformed(ActionEvent e) {
       int nLane;
       // frameMain.getCurrentImage().getLayer1D
       nLane=frameMain.getCurrentImage().getLayer1D().getlanenum();
       if(nLane>1){
        j09MainlaneCombo.removeAllItems();
        j09MainlaneCombo.addItem("Virtual Lane");
       for(int ii=1;ii<=nLane/2;ii++){
          String str;
            str="Lane No: "+Integer.toString(ii);
            list1.addElement(str);
            j09MainlaneCombo.addItem(str);
                 }
        String []strlist=new String[nLane/2];
         for(int ii=1;ii<=nLane/2;ii++){
           strlist[ii-1]="Lane No: "+Integer.toString(ii);

         }

         this.jList1.setListData(strlist);
       }
       String str,str1;
       str=this.j09ErrorText.getText();
       int end=str.indexOf("%");
       str1=str.substring(0,end);
      // System.out.println(str1+"%");
       int aa=Integer.parseInt(str1);
       float err=(float)aa/100;
       // System.out.println("err"+err);
       frameMain.getCurrentImage().getLayer1D().getvirtuallane(err);
  }

  void j09MainlaneCombo_focusLost(FocusEvent e) {
    int laneN=1;
    int nline=0;
    if(this.j09MainlaneCombo.getSelectedIndex()>0){
     laneN=this.j09MainlaneCombo.getSelectedIndex();
     nline=this.frameMain.getCurrentImage().getLayer1D().getlinenum(laneN);
    }
    if(this.j09MainlaneCombo.getSelectedIndex()==0){
    nline=frameMain.getCurrentImage().getLayer1D().virtlane.size();
    }
    String []str=new String[nline];
    list3.removeAllElements();
    for(int ii=1;ii<=nline;ii++){
    str[ii-1]="Band No: "+Integer.toString(ii);
    list3.addElement(str[ii-1]);
    }
    jList3.removeAll();
    jList3.setListData(str);
    boolean  find=false;
 /*   int len=jList4.getMaxSelectionIndex();
    for(int ii=0;ii<len;ii++){
     for(int jj=0;jj<str.length;jj++){
          String s1=(String)list4.elementAt(ii);
          String s2=(String)str[jj];
          if(s1.equals(s2)){
           find=true;
           break;
          }
     }
     if(find==true) {

       find=false;
     }
     else
     {
      list4.removeElementAt(ii);
      ii--;
      len--;
     }

    }
     jList4.setListData(list4);*/
    list4.removeAllElements();
    jList4.setListData(list4);
  }

  void jButton5_actionPerformed(ActionEvent e) {
    if(list4.size()==0)
      return;
    this.comparelane();
  }

  void jButton1_actionPerformed(ActionEvent e) {
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

  void jButton3_actionPerformed(ActionEvent e) {
      String str;
      boolean add=true;
      str=(String)jList3.getSelectedValue();
     if(str==null)   return;
      for(int ii=0;ii<list4.size();ii++)
      { String str1;
        str1=(String)list4.elementAt(ii);
        if(str1==str)
        {add=false;
        break;
        }
          }
          if(add==true){
          list4.add(str);
          jList4.removeAll();
          rearrage(list4);
          jList4.setListData(list4);

        }

  }

  void jButton2_actionPerformed(ActionEvent e) {
      String str;
      str=(String)jList2.getSelectedValue();
     if(str==null)   return;
      list2.remove(str);
      jList2.removeAll();
    jList2.setListData(list2);
  }

  void jButton4_actionPerformed(ActionEvent e) {

    String str;
   str=(String)jList4.getSelectedValue();
   if(str==null)   return;
   list4.remove(str);
   jList4.removeAll();
   jList4.setListData(list4);
  }
//Liusheng add later
  public int [][]comparelane(){

    int [][]result=new int[list2.size()][list4.size()];
    for(int ii=0;ii<list2.size();ii++){
    String str,substr;
     int begin,end;
    str=(String)list2.elementAt(ii);
    begin=str.indexOf("Band");
     end=str.indexOf("No");
    substr=str.substring(begin+1,end);
    int nlane=Integer.parseInt(substr);
    int mainlane;
     mainlane=this.j09MainlaneCombo.getSelectedIndex();
    int []line=new int[list4.size()];
    //System.out.println("line");
    for(int jj=0;jj<list4.size();jj++)
    {String str1,substr1;
    str1=(String)list4.elementAt(jj);

     begin=str1.indexOf("Band");
     end=str1.indexOf("No");
     String str2;
    str2=str1.substring(begin+1,end);

    line[jj]=Integer.parseInt(str2);
  //   System.out.println(line[jj]);
    }

    String strerr,strerr1;
         strerr=this.j09ErrorText.getText();
         int enderr=strerr.indexOf("%");
         strerr1=strerr.substring(0,enderr);
        // System.out.println(str1+"%");
         int aa=Integer.parseInt(strerr1);
         float err=(float)aa/100;
         result[ii]=frameMain.getCurrentImage().getLayer1D().compareline(mainlane,nlane,line,err);
  // frameMain.getCurrentImage().getLayer1D().compareline(0,nlane,line);
  //   substr=str.substring(1,2);
     //System.out.println(substr);
   //  System.out.println("dasd"+line[0]);
   /* String strshow;
    strshow=null;
    for(int kk=0;kk<result.length;kk++){

      strshow+=Integer.toString(result[kk]);
    }*/
//   this.jTextPane1.setText(strshow);
  // jTextPane1.setText(strshow);
    }
    return result;
  }
public void rearrage(Vector list){
  String str,substr;

  int len=list.size();
  //System.out.println(len);
  for(int ii=0;ii<len-1;ii++){

    str=(String)list.elementAt(ii);
    int begin0,end0;
    begin0=str.indexOf("Band");
     end0=str.indexOf("No");
     String strsub;
    strsub=str.substring(begin0+1,end0);

    int v1=Integer.parseInt(strsub);


    for(int jj=ii+1;jj<len;jj++)
    {String str1;
    str1=(String)list.elementAt(jj);
    int begin,end;
    begin=str1.indexOf("Band");
     end=str1.indexOf("No");
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

  void jScrollBar1_adjustmentValueChanged(AdjustmentEvent e) {
    String str;
    str=Integer.toString(jScrollBar1.getValue())+"%";
    this.j09ErrorText.setText(str);
  }

  void jButton10_actionPerformed(ActionEvent e) {
    // modified by wxs 20030826 -
    DialogReport dlg=new DialogReport(this.frameMain ,"Compare" ,false);
    if(list2.size()<1||list4.size()<1)  return;
    int [][]data=this.comparelane();
    int len=list4.size();
    int dataleni=data.length;
    int datalenj=data[0].length;
    String []ColumnName=new String[len];
    String [][]newData=new String[dataleni][datalenj];
    for(int ii=0;ii<len;ii++){
      ColumnName[ii]=(String)list4.elementAt(ii);
    }
    for(int ii=0;ii<dataleni;ii++){
      for(int jj=0;jj<datalenj;jj++){
        String str=String.valueOf(data[ii][jj]);
        newData[ii][jj]=str;
      }
    }
    String []text=new String[1];
    text[0]=this.getTitle() +"result";
    dlg.setContext(this.getTitle() +"result",text,ColumnName,newData,false);
    dlg.show() ;
    //
    String str="⊿ Compare━［Major Matching Lane="+(String)this.j09MainlaneCombo.getSelectedItem()
              +"］［Rf="+this.j09ErrorText.getText()+"］";
    this.frameMain.getCurrentImage().setDoMethod(9,str);
  }

  void j09MainlaneCombo_actionPerformed(ActionEvent e) {

  }

  void j09MainlaneCombo_focusGained(FocusEvent e) {
    int laneN=1;
   int nline=0;
   if(this.j09MainlaneCombo.getSelectedIndex()>0)
   { laneN=this.j09MainlaneCombo.getSelectedIndex();
    nline=this.frameMain.getCurrentImage().getLayer1D().getlinenum(laneN);
   }
   if(this.j09MainlaneCombo.getSelectedIndex()==0)
    nline=frameMain.getCurrentImage().getLayer1D().virtlane.size();
   String []str=new String[nline];
   list3.removeAllElements();
   for(int ii=1;ii<=nline;ii++){

   str[ii-1]="Band No"+Integer.toString(ii);
   list3.addElement(str[ii-1]);
   }
   jList3.removeAll();
    jList3.setListData(str);
    boolean  find=false;
    list4.removeAllElements();
    jList4.setListData(list4);
  }

  void jButton11_actionPerformed(ActionEvent e) {
    list2.removeAllElements();
    for(int ii=0;ii<list1.size();ii++){
       String str;
       str=(String)list1.elementAt(ii);
       list2.addElement(str);
     }
    this.jList2.setListData(list2);
  }
public void getlane(){
  int nLane;
  // frameMain.getCurrentImage().getLayer1D
  nLane=frameMain.getCurrentImage().getLayer1D().getlanenum();
  System.out.println(nLane);
  if(nLane>1){
   j09MainlaneCombo.removeAllItems();
   list1.removeAllElements();
   j09MainlaneCombo.addItem("Virtual Lane");
  for(int ii=1;ii<=nLane/2;ii++){
     String str;
       str="Lane No: "+Integer.toString(ii);
       list1.addElement(str);
       j09MainlaneCombo.addItem(str);
            }
   String []strlist=new String[nLane/2];
    for(int ii=1;ii<=nLane/2;ii++){
      strlist[ii-1]="Lane No: "+Integer.toString(ii);

    }

    this.jList1.setListData(strlist);
  }
  String str,str1;
  str=this.j09ErrorText.getText();
  int end=str.indexOf("%");
  str1=str.substring(0,end);
  int aa=Integer.parseInt(str1);
  float err=(float)aa/100;
  frameMain.getCurrentImage().getLayer1D().getvirtuallane(err);
}

  void jButton6_actionPerformed(ActionEvent e) {
    this.dispose() ;
    // modified by wxs 20030826 -11
    if(this.frameMain .getExeMethod() >=1){
      this.frameMain .setWizardWinLocation(this.getLocation() ) ;
      this.frameMain.doStep(false);
    }
  }

  void jButton7_actionPerformed(ActionEvent e) {
    list4.removeAllElements();
    for(int ii=0;ii<list3.size();ii++){
      String str;
      str=(String)list3.elementAt(ii);
      list4.addElement(str);
     }
    jList4.setListData(list4);
  }
//Liusheng add at 8
  void jButton12_actionPerformed(ActionEvent e) {
       this.Intialize();
  }
//End

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
          if(st.type.substring(0,2).equals("09"))  set.addElement(st);
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
    boolean bln;
    int it;
    String str;
    bln=getSettings("09Need").paraBoolean;
    if(bln==true){
      if(exeMethod==2)  this.isVisible =getSettings("09Visible").paraBoolean;
      else this.isVisible=true;
      this.j09ErrorText.setText(getSettings("09Error").paraString+"%");
      str=getSettings("09Error").paraString;
      this.jScrollBar1.setValue(Integer.parseInt(str));
      boolean bln2=getSettings("09Compareall").paraBoolean;
      if(bln2==false){
        it=getSettings("09Lane").paraInt;
        if(it>=this.j09MainlaneCombo.getItemCount()) it=0;
        this.j09MainlaneCombo.setSelectedIndex(it);
        if(getSettings("09Line").paraBoolean==true) jButton7_actionPerformed(null);
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
    if(getSettings("09Need").paraBoolean==true){
      this.frameMain.getCurrentImage().setTitle("Compare......");
      if(getSettings("09Compareall").paraBoolean==true) this.Intialize();
      jButton10_actionPerformed(null);
      this.frameMain.getCurrentImage().setTitle("Finished.");
    }
    // donext
    this.frameMain.setWizardWinLocation(this.getLocation() ) ;
    this.frameMain.doStep(true)  ;
  }
}