package easygel.image;

import java.awt.*;
import javax.swing.*;
import com.borland.jbcl.layout.*;
import easygel.*;
import java.awt.event.*;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author 王兴胜、易厚富
 * @version 1.0
 */

public class DialogBackground extends JDialog {
  private JPanel panel1 = new JPanel();
  private XYLayout xYLayout1 = new XYLayout();
  private JButton jButton1 = new JButton();
  private JRadioButton jRadioButton1 = new JRadioButton();
  private JRadioButton jRadioButton2 = new JRadioButton();
  private JTextField jTextField1 = new JTextField();
  private JTextField jTextField2 = new JTextField();
  private ButtonGroup buttonGroup=new ButtonGroup();
  private DialogImage di;
  private boolean bg;

  public DialogBackground(Frame frame, String title, boolean modal,DialogImage di,boolean bg) {
    super(frame, title, modal);
    this.di =di;
    this.bg =bg;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    this.setLocation((int)((screenSize.getWidth()-161)/2),(int)(screenSize.getHeight()-88)/2);

    panel1.setLayout(xYLayout1);
    jButton1.setMargin(new Insets(0, 0, 0, 0));
    jButton1.setText("OK");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jRadioButton1.setBackground(SystemColor.desktop);
    jRadioButton1.setText("Black");
    jRadioButton2.setBackground(SystemColor.desktop);
    jRadioButton2.setForeground(Color.white);
    jRadioButton2.setText("White");
    panel1.setBackground(SystemColor.desktop);
    panel1.setForeground(Color.orange);
    panel1.setMinimumSize(new Dimension(161, 89));
    panel1.setPreferredSize(new Dimension(161, 89));
    jTextField1.setBackground(Color.black);
    jTextField1.setEnabled(false);
    jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jTextField1_mouseClicked(e);
      }
    });
    jTextField2.setEnabled(false);
    jTextField2.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        jTextField2_mouseClicked(e);
      }
    });
    getContentPane().add(panel1);
    panel1.add(jTextField2,  new XYConstraints(92, 29, 39, 19));
    panel1.add(jButton1, new XYConstraints(54, 59, 47, 22));
    panel1.add(jRadioButton1, new XYConstraints(18, 3, 52, 24));
    panel1.add(jRadioButton2, new XYConstraints(18, 27, 52, 24));
    panel1.add(jTextField1, new XYConstraints(92, 4, 39, 19));
    buttonGroup.add(jRadioButton1);
    buttonGroup.add(jRadioButton2);
    if(this.bg==true) {
      jRadioButton1.setSelected(true);
    }
    else{
      jRadioButton2.setSelected(true);
    }
  }

  void jButton1_actionPerformed(ActionEvent e) {
    if( jRadioButton1.isSelected() ==true){
      di.setBackgroundBlank(true);
    }
    else{
      di.setBackgroundBlank(false);
    }
    this.dispose() ;
  }

  void jTextField1_mouseClicked(MouseEvent e) {
    this.jRadioButton1 .setSelected(true);
  }

  void jTextField2_mouseClicked(MouseEvent e) {
    this.jRadioButton2 .setSelected(true);
  }
}