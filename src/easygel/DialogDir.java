package easygel;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogDir extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JFileChooser jFileChooser1 = new JFileChooser();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  private String dir;
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JTextArea jTextArea1 = new JTextArea();
  private boolean isInit;
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private String initDir;

  public DialogDir(Frame frame, String title, boolean modal,String initDir) {
    super(frame, title, modal);
    isInit=true;
    this.initDir =initDir;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogDir() {
    this(null, "", false,"");
  }

  private void jbInit() throws Exception {
    panel1.setLayout(xYLayout1);
    jButton1.setText("Create New Folder");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setText("Select Folder");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton3.setText("Exit");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jFileChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
      public void propertyChange(PropertyChangeEvent e) {
        jFileChooser1_propertyChange(e);
      }
    });
    jLabel1.setText("Folder");
    jLabel2.setText("Select");
    panel1.setMinimumSize(new Dimension(468, 248));
    panel1.setPreferredSize(new Dimension(468, 248));
    getContentPane().add(panel1);
    panel1.add(jFileChooser1, new XYConstraints(9, 8, 448, 153));
    panel1.add(jButton2,      new XYConstraints(192, 216, -1, 24));
    panel1.add(jScrollPane1,      new XYConstraints(49, 161, 407, 45));
    panel1.add(jLabel2,  new XYConstraints(15, 180, 34, 22));
    panel1.add(jLabel1, new XYConstraints(14, 158, 28, 26));
    panel1.add(jButton3,                                         new XYConstraints(337, 216, 83, 24));
    panel1.add(jButton1,          new XYConstraints(47, 216, -1, 24));
    jScrollPane1.getViewport().add(jTextArea1, null);
    this.isInit =false;
    File file=new File(this.initDir );
    if(file.isDirectory() ==true){
      this.jFileChooser1 .setCurrentDirectory(file.getParentFile() );
      this.jFileChooser1 .setSelectedFile(file);
    }
  }

  public String getDir(){
    return dir;
  }

  void jButton2_actionPerformed(ActionEvent e) {
    dir=this.jTextArea1 .getText() ;
    File file=new File(dir);
    if(file.isDirectory() ==false) dir="";
    this.setVisible(false);
  }

  void jFileChooser1_propertyChange(PropertyChangeEvent e) {
    if(this.isInit ==true) return;
    try{
      File file=this.jFileChooser1 .getSelectedFile() ;
      if(file.isDirectory() ==true) dir=file.getPath();
      else{
        String name,path;
        name="\\"+file.getName();
        path=file.getPath();
       int index=path.lastIndexOf(name);
        dir=path.substring(0,index);
      }
      dir+="\\";
     this.jTextArea1 .setText(dir);
    }
    catch(Exception e2){
      return;
    }
  }

  void jButton1_actionPerformed(ActionEvent e) {
    String subDir=JOptionPane.showInputDialog("New folder name: ");
    if(subDir.length() ==0) return;
    File file=this.jFileChooser1 .getSelectedFile() ;
    if(file.isDirectory() ==true) dir=file.getPath();
    else{
      String name,path;
      name="\\"+file.getName();
      path=file.getPath();
     int index=path.lastIndexOf(name);
      dir=path.substring(0,index);
    }
    dir+="\\"+subDir+"\\";
    this.jTextArea1 .setText(dir);

    //
    file=new File(dir);
    try{
      jFileChooser1.getFileSystemView().createNewFolder(file);
      jFileChooser1.setCurrentDirectory(file.getParentFile());
      jFileChooser1.setSelectedFile(file.getParentFile());
      jFileChooser1.setSelectedFile(file);
    }
    catch(Exception e2){
    }
  }

  void jButton3_actionPerformed(ActionEvent e) {
    dir="";
    this.setVisible(false);
  }
}