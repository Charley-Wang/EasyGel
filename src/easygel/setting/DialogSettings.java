package easygel.setting;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;
import easygel.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogSettings extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JList jList1 = new JList();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  private JButton jButton4 = new JButton();
  private JButton jButton5 = new JButton();
  private JButton jButton6 = new JButton();

  private Vector setInfo=new Vector();
  private String filePathName;
  private String currName;
  private FrameMain frameMain;
  private String systemDir;

  public DialogSettings(FrameMain frame, String title, boolean modal,
                        String file,String currentName,String systemDir) {
    super(frame, title, modal);
    filePathName=file;
    this.systemDir =systemDir;
    currName=currentName;
    this.frameMain=frame;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogSettings() {
  }

  private void jbInit() throws Exception {
    panel1.setLayout(xYLayout1);
    jButton1.setText("Append");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setText("Delete");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton3.setMargin(new Insets(0, 0, 0, 0));
    jButton3.setText("Current Value");
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
    jButton5.setText("Modify");
    jButton5.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton5_actionPerformed(e);
      }
    });
    jButton6.setText("Browse");
    jButton6.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton6_actionPerformed(e);
      }
    });
    this.setTitle("Default Values");
    panel1.setMinimumSize(new Dimension(456, 199));
    panel1.setPreferredSize(new Dimension(456, 199));
    easygel.uiti.WxsUiti.centerDialog(this,456,199);
    jList1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jList1_mouseClicked(e);
      }
    });
    getContentPane().add(panel1);
    panel1.add(jScrollPane1,       new XYConstraints(10, 11, 349, 181));
    panel1.add(jButton3,  new XYConstraints(368, 134, 78, -1));
    panel1.add(jButton4, new XYConstraints(368, 164, 78, 25));
    panel1.add(jButton2, new XYConstraints(368, 73, 78, 25));
    panel1.add(jButton6, new XYConstraints(368, 13, 78, 25));
    panel1.add(jButton1, new XYConstraints(368, 43, 78, 25));
    panel1.add(jButton5, new XYConstraints(368, 104, 78, 25));
    jScrollPane1.getViewport().add(jList1, null);

    if(this.filePathName.length()!=0){
      this.readFile(this.filePathName);
      this.setInterface(this.currName);
    }
  }

  private void writeFile(String pathName){
    try{
      ObjectOutputStream o=new ObjectOutputStream(
          new FileOutputStream(pathName));
      int num=setInfo.size();
      o.writeInt(num);
      for(int ii=0;ii<setInfo.size();ii++)
        o.writeObject((SettingsInfo)setInfo.elementAt(ii));
      o.close();
     }
    catch(Exception e3){
      e3.printStackTrace() ;
    }
  }

  private void readFile(String pathName){
    try{
      setInfo.removeAllElements();
      File file=new File(pathName);
      if(file.exists() ==true){
        ObjectInputStream in= new ObjectInputStream(new FileInputStream(file));
        int num=in.readInt();
        for(int ii=0;ii<num;ii++){
          SettingsInfo st=new SettingsInfo();
          st=(SettingsInfo)in.readObject();
          setInfo.addElement(st);
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

  private void setInterface(String currentName){
    if(setInfo==null) return;
    int num=setInfo.size();
    this.jList1.removeAll();
    if(num<0) return;
    String []stra=new String[num];
    for(int ii=0;ii<num;ii++){
      String str;
      SettingsInfo st=(SettingsInfo)setInfo.elementAt(ii);
      if(currentName.equals(st.name)) str="√";
      else str="×";
      //str+="  序号:"+st.no+"  名称:"+st.name;
      //str+="  文件名称:"+st.fileName+"  建立者:"+st.user+"  日期:"+st.date;
      str+="  Name:"+st.name;
      str+="  User:"+st.user+"  Date:"+st.date;
      stra[ii]=str;
    }
    this.jList1.setListData(stra);
  }

  private boolean existSetting(String name){
    boolean exist=false;
    for(int ii=0;ii<this.setInfo.size();ii++){
      if(((SettingsInfo)setInfo.elementAt(ii)).name.equals(name)){
        exist=true;
        break;
      }
    }
    return exist;
  }

  void jButton1_actionPerformed(ActionEvent e) {
    String name,user;
    DialogAddNewSetting add=new DialogAddNewSetting(this.frameMain,"分析设置",true);
    add.show();
    name=add.getName();
    user=add.getUser();
    if(name.equals("")) return;
    //fileName=add.getFileName();
    add.dispose();
    if(this.existSetting(name)==true){
      JOptionPane.showMessageDialog(this.frameMain,"File exits, please reset",
                                    "File exits, please rese",JOptionPane.ERROR_MESSAGE);
      return;
    }

    DialogSet1D di1D=new DialogSet1D(this.frameMain,"1D Analysis",true,
                                     systemDir+"\\database\\"+name+".1d",
                                     DialogSet1D.TYPE_ADD,"");
    di1D.show();
    if(di1D.getOK()==false) return;
    di1D.dispose();

    SettingsInfo info=new SettingsInfo();
    Date date=new Date(System.currentTimeMillis());
    info.date=date.toString();
    //info.fileName=fileName;
    //info.isSelected=false;
    info.name=name;
    //info.no=
    info.user=user;
    this.setInfo.addElement(info);

    File file=new File(this.filePathName);
    if(file.exists()==true) file.delete();
    this.writeFile(this.filePathName);

    this.setInterface(this.currName);
  }

  void jButton6_actionPerformed(ActionEvent e) {
    if(this.jList1.isSelectionEmpty()==true) return;
    String name1D;
    name1D=systemDir+"\\database\\";
    int index=this.jList1.getSelectedIndex();
    SettingsInfo info=(SettingsInfo)setInfo.elementAt(index);
    name1D+=info.name;
    name1D+=".1d";
    DialogSet1D di1D=new DialogSet1D(this.frameMain,"1D Analysis",true,
                                     "",DialogSet1D.TYPE_BROWSE,name1D);
    di1D.show();
  }

  void jButton4_actionPerformed(ActionEvent e) {
    this.setVisible(false);
  }

  void jButton3_actionPerformed(ActionEvent e) {
    if(this.jList1.isSelectionEmpty()==true) return;
    int index=this.jList1.getSelectedIndex();
    SettingsInfo info=(SettingsInfo)setInfo.elementAt(index);
    this.frameMain.setCurrentParaPre(info.name);
    this.currName=info.name;
    this.setInterface(this.currName);

    //
    this.frameMain.getEasyGelSet().paraPre=this.currName;
    this.frameMain.saveEasyGelSet();
  }

  void jList1_mouseClicked(MouseEvent e) {
    if(e.getClickCount()==2){
      jButton6_actionPerformed(null);
    }
  }

  void jButton5_actionPerformed(ActionEvent e) {
    if(this.jList1.isSelectionEmpty()==true) return;
    String name1D;
    name1D=systemDir+"\\database\\";
    int index=this.jList1.getSelectedIndex();
    SettingsInfo info=(SettingsInfo)setInfo.elementAt(index);
    name1D+=info.name;
    name1D+=".1d";
    DialogSet1D di1D=new DialogSet1D(this.frameMain,"1D Analysis",true,
                                     name1D,DialogSet1D.TYPE_CHANGE,name1D);
    di1D.show();
  }

  void jButton2_actionPerformed(ActionEvent e){
    if(this.jList1.isSelectionEmpty()==true) return;
    String name1D;
    name1D=systemDir+"\\database\\";
    int index=this.jList1.getSelectedIndex();
    SettingsInfo info=(SettingsInfo)setInfo.elementAt(index);
    name1D+=info.name;
    name1D+=".1d";
    File file=new File(name1D);
    if(file.exists()==true) file.delete();

    //setInfo.removeElement(info);
    setInfo.removeElementAt(index);
    writeFile(this.filePathName);
    readFile(this.filePathName);
    setInterface(this.currName);
  }
}
