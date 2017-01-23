package easygel.setting;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: whole package need change to English </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogAddNewSetting extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JLabel jLabel1 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private JLabel jLabel2 = new JLabel();
  private JTextField jTextField2 = new JTextField();

  public DialogAddNewSetting(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public DialogAddNewSetting() {
    this(null, "", false);
  }
  private void jbInit() throws Exception {
    panel1.setLayout(xYLayout1);
    jLabel1.setText("Name");
    jButton1.setMargin(new Insets(0, 0, 0, 0));
    jButton1.setText("Cancel");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setMargin(new Insets(0, 0, 0, 0));
    jButton2.setText("OK");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    jLabel2.setText("Creator");
    this.setTitle("Name for new parameters");
    panel1.setMinimumSize(new Dimension(225, 96));
    panel1.setPreferredSize(new Dimension(225, 96));
    getContentPane().add(panel1);
    panel1.add(jTextField1, new XYConstraints(67, 6, 148, -1));
    panel1.add(jTextField2, new XYConstraints(67, 36, 148, 22));
    panel1.add(jLabel1, new XYConstraints(11, 8, 42, 21));
    panel1.add(jButton1,  new XYConstraints(44, 67, 50, 20));
    panel1.add(jButton2, new XYConstraints(134, 67, 50, 20));
    panel1.add(jLabel2, new XYConstraints(12, 37, 42, 21));

    easygel.uiti.WxsUiti.centerDialog(this,225,96);
  }

  public String getName(){
    return this.jTextField1.getText();
  }

  public String getUser(){
    return this.jTextField2.getText();
  }

  void jButton2_actionPerformed(ActionEvent e) {
    this.setVisible(false);
  }

  void jButton1_actionPerformed(ActionEvent e) {
    this.jTextField1.setText("");
    this.jTextField2.setText("");
  }
}