package easygel;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogDoInformation extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JScrollPane jScrollPane1 = new JScrollPane();
  private JList jList1 = new JList();
  private FrameMain frameMain;
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  private JButton jButton4 = new JButton();

  public DialogDoInformation(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    this.frameMain=(FrameMain)frame;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogDoInformation() {
    this(null, "", false);
  }

  private void jbInit() throws Exception {
    panel1.setLayout(xYLayout1);
    panel1.setMinimumSize(new Dimension(509, 257));
    panel1.setPreferredSize(new Dimension(509, 257));
    jScrollPane1.setForeground(Color.blue);
    jButton1.setMargin(new Insets(0, 0, 0, 0));
    jButton1.setText("Cascade");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setMargin(new Insets(0, 0, 0, 0));
    jButton2.setText("Exit");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jButton3.setMargin(new Insets(0, 0, 0, 0));
    jButton3.setText("Min");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton3_actionPerformed(e);
      }
    });
    jButton4.setMargin(new Insets(0, 0, 0, 0));
    jButton4.setText("Close all");
    jButton4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton4_actionPerformed(e);
      }
    });
    getContentPane().add(panel1);
    panel1.add(jScrollPane1,new XYConstraints(11, 10, 487, 200));
    panel1.add(jButton1,  new XYConstraints(45, 219, 80, 27));
    panel1.add(jButton3, new XYConstraints(154, 219, 80, 27));
    panel1.add(jButton4, new XYConstraints(263, 219, 80, 27));
    panel1.add(jButton2, new XYConstraints(372, 219, 80, 27));
    jScrollPane1.getViewport().add(jList1, null);
    this.setInterface();
  }

  private void setInterface(){
    if(this.frameMain.getCurrentImage()==null) return;
    String []data=new String[11];
    data[0]="⊿ Processing files -［file name="+this.frameMain.getCurrentImage().getImagePath()+
            this.frameMain.getCurrentImage().getImageName()+"］";
    for(int ii=1;ii<=10;ii++)
      data[ii]=this.frameMain.getCurrentImage().getDoMethods()[ii-1];
    jList1.setListData(data);
  }

  void jButton2_actionPerformed(ActionEvent e) {
    this.dispose();
  }

  void jButton1_actionPerformed(ActionEvent e) {
    this.frameMain.jMenuArrageLayer_actionPerformed(null);
  }

  void jButton3_actionPerformed(ActionEvent e) {
    this.frameMain.jMenuItem22_actionPerformed(null);
  }

  void jButton4_actionPerformed(ActionEvent e) {
    this.frameMain.jMenuItem3_actionPerformed(null);;
  }
}